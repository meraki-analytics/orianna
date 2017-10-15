package com.merakianalytics.orianna.datapipeline;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Function;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.AbstractDataSource;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.TimeoutException;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.dto.DataObject;
import com.merakianalytics.orianna.types.dto.staticdata.Champion;
import com.merakianalytics.orianna.types.dto.staticdata.ChampionList;
import com.merakianalytics.orianna.types.dto.staticdata.Item;
import com.merakianalytics.orianna.types.dto.staticdata.ItemList;
import com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.dto.staticdata.Languages;
import com.merakianalytics.orianna.types.dto.staticdata.MapData;
import com.merakianalytics.orianna.types.dto.staticdata.MapDetails;
import com.merakianalytics.orianna.types.dto.staticdata.Mastery;
import com.merakianalytics.orianna.types.dto.staticdata.MasteryList;
import com.merakianalytics.orianna.types.dto.staticdata.ProfileIconData;
import com.merakianalytics.orianna.types.dto.staticdata.ProfileIconDetails;
import com.merakianalytics.orianna.types.dto.staticdata.Realm;
import com.merakianalytics.orianna.types.dto.staticdata.Rune;
import com.merakianalytics.orianna.types.dto.staticdata.RuneList;
import com.merakianalytics.orianna.types.dto.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.dto.staticdata.SummonerSpellList;
import com.merakianalytics.orianna.types.dto.staticdata.Versions;

public class DataDragon extends AbstractDataSource {
    public static class Configuration {
        private static final HTTPClient.Configuration DEFAULT_REQUESTS = new HTTPClient.Configuration();
        private HTTPClient.Configuration requests = DEFAULT_REQUESTS;

        /**
         * @return the requests
         */
        public HTTPClient.Configuration getRequests() {
            return requests;
        }

        /**
         * @param requests
         *        the requests to set
         */
        public void setRequests(final HTTPClient.Configuration requests) {
            this.requests = requests;
        }
    }

    private static final Function<JsonNode, JsonNode> CHAMPION_COEFF_PROCESSOR = new Function<JsonNode, JsonNode>() {
        @Override
        public JsonNode apply(final JsonNode championTree) {
            if(championTree == null) {
                return championTree;
            }

            final JsonNode temp = championTree.get("spells");
            if(temp == null) {
                return championTree;
            }

            for(final JsonNode spell : temp) {
                SPELL_COEFF_PROCESSOR.apply(spell);
            }
            return championTree;
        }
    };

    private static final Function<JsonNode, JsonNode> CHAMPION_LIST_COEFF_PROCESSOR = new Function<JsonNode, JsonNode>() {
        @Override
        public JsonNode apply(final JsonNode championListTree) {
            if(championListTree == null) {
                return championListTree;
            }

            final JsonNode temp = championListTree.get("data");
            if(temp == null) {
                return championListTree;
            }

            for(final JsonNode champion : temp) {
                CHAMPION_COEFF_PROCESSOR.apply(champion);
            }
            return championListTree;
        }
    };

    private static final Logger LOGGER = LoggerFactory.getLogger(DataDragon.class);

    private static final Function<JsonNode, JsonNode> SPELL_COEFF_PROCESSOR = new Function<JsonNode, JsonNode>() {
        @Override
        public JsonNode apply(final JsonNode spellTree) {
            final JsonNode temp = spellTree.get("vars");
            if(temp == null) {
                return spellTree;
            }

            for(final JsonNode vars : temp) {
                if(vars == null) {
                    continue;
                }

                final JsonNode coeff = vars.get("coeff");
                if(coeff == null) {
                    continue;
                } else if(!coeff.isArray()) {
                    ((ObjectNode)vars).putArray("coeff").add(coeff.asDouble());
                }
            }
            return spellTree;
        }
    };

    private static final Function<JsonNode, JsonNode> SPELL_LIST_COEFF_PROCESSOR = new Function<JsonNode, JsonNode>() {
        @Override
        public JsonNode apply(final JsonNode spellListTree) {
            final JsonNode temp = spellListTree.get("data");
            if(temp == null) {
                return spellListTree;
            }

            for(final JsonNode spell : temp) {
                SPELL_COEFF_PROCESSOR.apply(spell);
            }
            return spellListTree;
        }
    };

    private static String filterToField(final String json, final String field, final String value) {
        try {
            final JsonNode parent = new ObjectMapper().readTree(json).get("data");
            for(final Iterator<String> it = parent.fieldNames(); it.hasNext();) {
                final String fieldName = it.next();
                final JsonNode child = parent.get(fieldName);

                if(child instanceof ObjectNode) {
                    final ObjectNode childObject = (ObjectNode)child;
                    if("USE_PARENT".equalsIgnoreCase(field) && fieldName.equalsIgnoreCase(value)) {
                        return childObject.toString();
                    }

                    if(child.has(field) && childObject.get(field).asText().equalsIgnoreCase(value)) {
                        return childObject.toString();
                    }
                }
            }
            return json;
        } catch(final IOException e) {
            e.printStackTrace();
            return json;
        }

    }

    private static String filterToTags(final String json, final Set<String> includedData) {
        if(includedData.contains("all")) {
            return json;
        }

        final Set<String> defaultChampionTags = ImmutableSet.of("title", "id", /* "key", */ "name");
        final Set<String> defaultItemTags = ImmutableSet.of("plaintext", "description", "id", "name");
        final Set<String> defaultMasteryTags = ImmutableSet.of("description", "id", "name");
        final Set<String> defaultRuneTags = ImmutableSet.of("description", "rune", "id", "name");
        final Set<String> defaultSummonerSpellTags = ImmutableSet.of("name", /* "key", */ "summonerLevel", "id", "description");

        final Set<String> okTags = new ImmutableSet.Builder<String>().addAll(defaultChampionTags)
                                                                     .addAll(defaultItemTags)
                                                                     .addAll(defaultMasteryTags)
                                                                     .addAll(defaultRuneTags)
                                                                     .addAll(defaultSummonerSpellTags)
                                                                     .addAll(includedData)
                                                                     .build();

        try {
            final JsonNode parent = new ObjectMapper().readTree(json);
            final JsonNode data = parent.get("data");
            for(final JsonNode child : data) {
                if(child instanceof ObjectNode) {
                    final ObjectNode childObject = (ObjectNode)child;

                    if(childObject.get("key") != null) {
                        childObject.set("id", childObject.get("key"));
                    }

                    childObject.retain(okTags);
                }
            }
            return parent.toString();
        } catch(final IOException e) {
            e.printStackTrace();
            return json;
        }
    }

    private static String getCurrentVersion(final Platform platform, final PipelineContext context) {
        final Realm realm = context.getPipeline().get(Realm.class, ImmutableMap.<String, Object> of("platform", platform));
        return realm.getV();
    }

    private final HTTPClient client;

    public DataDragon() {
        this(new Configuration());
    }

    public DataDragon(final Configuration config) {
        client = new HTTPClient(config.getRequests());
    }

    private String get(final String file, final String version, final String locale) {
        String URL = "http://ddragon.leagueoflegends.com";
        if(version != null && locale != null) {
            URL += "/cdn/" + version + "/data/" + locale + "/";
        }
        URL += file + ".json";

        try {
            return client.get(URL).getBody();
        } catch(final TimeoutException e) {
            LOGGER.info("Get request timed out to " + URL + "!", e);
            return null;
        } catch(final IOException e) {
            LOGGER.error("Get request failed to " + URL + "!", e);
            throw new OriannaException("Something went wrong with a request to DataDragon API at " + URL + "! Report this to the orianna team.", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Get(Champion.class)
    public Champion getChampion(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("championFull", version, locale);
        final String filteredToTags = filterToTags(makeById(content), includedData);
        final String filteredToId = id == null ? filterToField(filteredToTags, "name", name) : filterToField(filteredToTags, "id", id.toString());

        final Champion data = DataObject.fromJSON(Champion.class, CHAMPION_COEFF_PROCESSOR, filteredToId);
        if(data.getId() == 0) {
            return null;
        }

        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(ChampionList.class)
    public ChampionList getChampionList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");
        final Boolean dataById = query.get("dataById") == null ? Boolean.FALSE : (Boolean)query.get("dataById");

        final String content = swapKeyAndId(get("championFull", version, locale));
        final String filteredToTags = filterToTags(dataById ? makeById(content) : content, includedData);
        final ChampionList data = DataObject.fromJSON(ChampionList.class, CHAMPION_LIST_COEFF_PROCESSOR, filteredToTags);

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for(final Champion champion : data.getData().values()) {
            champion.setPlatform(platform.getTag());
            champion.setVersion(data.getVersion());
            champion.setLocale(locale);
            champion.setIncludedData(includedData);
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(Item.class)
    public Item getItem(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number id = (Number)query.get("id");
        Utilities.checkNotNull(platform, "platform", id, "id");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("item", version, locale);
        final String filteredToTags = filterToTags(content, includedData);
        final String filteredToId = filterToField(filteredToTags, "USE_PARENT", id.toString());
        final Item data = DataObject.fromJSON(Item.class, filteredToId);

        if(data.getId() == 0) {
            return null;
        }

        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(ItemList.class)
    public ItemList getItemList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("item", version, locale);
        final String filteredToTags = filterToTags(content, includedData);
        final ItemList data = DataObject.fromJSON(ItemList.class, filteredToTags);

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for(final Item item : data.getData().values()) {
            item.setPlatform(platform.getTag());
            item.setVersion(data.getVersion());
            item.setLocale(locale);
            item.setIncludedData(includedData);
        }
        return data;
    }

    @Get(Languages.class)
    public Languages getLanguages(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String content = get("/cdn/languages", null, null);
        final Languages data = DataObject.fromJSON(Languages.class, content);

        data.setPlatform(platform.getTag());
        return data;
    }

    @Get(LanguageStrings.class)
    public LanguageStrings getLanguageStrings(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final String content = get("language", version, locale);
        final LanguageStrings data = DataObject.fromJSON(LanguageStrings.class, content);

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        return data;
    }

    @SuppressWarnings("unchecked")
    @GetMany(Champion.class)
    public CloseableIterator<Champion> getManyChampion(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("championFull", version, locale);
        final String filteredToTags = filterToTags(makeById(content), includedData);

        final ChampionList data = DataObject.fromJSON(ChampionList.class, CHAMPION_COEFF_PROCESSOR, filteredToTags);

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        final Map<String, Champion> byName = ids == null ? new HashMap<String, Champion>() : null;
        for(final Champion champion : data.getData().values()) {
            champion.setPlatform(platform.getTag());
            champion.setVersion(data.getVersion());
            champion.setLocale(locale);
            champion.setIncludedData(includedData);

            if(ids == null) {
                byName.put(champion.getName(), champion);
            }
        }

        final Iterator<?> iterator = ids == null ? names.iterator() : ids.iterator();
        return CloseableIterators.from(new Iterator<Champion>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Champion next() {
                if(ids != null) {
                    final Number id = (Number)iterator.next();
                    return data.getData().get(id.toString());
                } else {
                    final String name = (String)iterator.next();
                    return byName.get(name);
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionList.class)
    public CloseableIterator<ChampionList> getManyChampionList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> versions = (Iterable<String>)query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");
        final Boolean dataById = query.get("dataById") == null ? Boolean.FALSE : (Boolean)query.get("dataById");

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        parameters.putAll("tags", includedData);
        parameters.put("dataById", dataById.toString());

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<ChampionList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionList next() {
                final String version = iterator.next();

                final String content = swapKeyAndId(get("championFull", version, locale));
                final String filteredToTags = filterToTags(dataById ? makeById(content) : content, includedData);
                final ChampionList data = DataObject.fromJSON(ChampionList.class, CHAMPION_LIST_COEFF_PROCESSOR, filteredToTags);

                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                data.setIncludedData(includedData);
                for(final Champion champion : data.getData().values()) {
                    champion.setPlatform(platform.getTag());
                    champion.setVersion(data.getVersion());
                    champion.setLocale(locale);
                    champion.setIncludedData(includedData);
                }
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Item.class)
    public CloseableIterator<Item> getManyItem(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        Utilities.checkNotNull(platform, "platform", ids, "ids");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("item", version, locale);
        final String filteredToTags = filterToTags(content, includedData);
        final ItemList data = DataObject.fromJSON(ItemList.class, filteredToTags);

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for(final Item item : data.getData().values()) {
            item.setPlatform(platform.getTag());
            item.setVersion(data.getVersion());
            item.setLocale(locale);
            item.setIncludedData(includedData);
        }

        final Iterator<Number> iterator = ids.iterator();
        return CloseableIterators.from(new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Item next() {
                final Number id = iterator.next();
                return data.getData().get(id.toString());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ItemList.class)
    public CloseableIterator<ItemList> getManyItemList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> versions = (Iterable<String>)query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<ItemList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ItemList next() {
                final String version = iterator.next();

                final String content = get("item", version, locale);
                final String filteredToTags = filterToTags(content, includedData);
                final ItemList data = DataObject.fromJSON(ItemList.class, filteredToTags);

                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                data.setIncludedData(includedData);
                for(final Item item : data.getData().values()) {
                    item.setPlatform(platform.getTag());
                    item.setVersion(data.getVersion());
                    item.setLocale(locale);
                    item.setIncludedData(includedData);
                }
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Languages.class)
    public CloseableIterator<Languages> getManyLanguages(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<Languages>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Languages next() {
                final Platform platform = iterator.next();

                final String content = get("/cdn/languages", null, null);
                final Languages data = DataObject.fromJSON(Languages.class, content);

                data.setPlatform(platform.getTag());
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(LanguageStrings.class)
    public CloseableIterator<LanguageStrings> getManyLanguageStrings(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> locales = (Iterable<String>)query.get("locales");
        Utilities.checkNotNull(platform, "platform", locales, "locales");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");

        final Iterator<String> iterator = locales.iterator();
        return CloseableIterators.from(new Iterator<LanguageStrings>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public LanguageStrings next() {
                final String locale = iterator.next();

                final String content = get("language", version, locale);
                final LanguageStrings data = DataObject.fromJSON(LanguageStrings.class, content);

                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(MapData.class)
    public CloseableIterator<MapData> getManyMapData(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> versions = (Iterable<String>)query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<MapData>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MapData next() {
                final String version = iterator.next();

                final String content = get("map", version, locale);
                final MapData data = DataObject.fromJSON(MapData.class, content);

                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                for(final MapDetails map : data.getData().values()) {
                    map.setPlatform(platform.getTag());
                    map.setVersion(data.getVersion());
                    map.setLocale(locale);
                }
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Mastery.class)
    public CloseableIterator<Mastery> getManyMastery(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        Utilities.checkNotNull(platform, "platform", ids, "ids");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("mastery", version, locale);
        final String filteredToTags = filterToTags(content, includedData);
        final MasteryList data = DataObject.fromJSON(MasteryList.class, filteredToTags);

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for(final Mastery mastery : data.getData().values()) {
            mastery.setPlatform(platform.getTag());
            mastery.setVersion(data.getVersion());
            mastery.setLocale(locale);
            mastery.setIncludedData(includedData);
        }

        final Iterator<Number> iterator = ids.iterator();
        return CloseableIterators.from(new Iterator<Mastery>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Mastery next() {
                final Number id = iterator.next();
                return data.getData().get(id.toString());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(MasteryList.class)
    public CloseableIterator<MasteryList> getManyMasteryList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> versions = (Iterable<String>)query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<MasteryList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MasteryList next() {
                final String version = iterator.next();

                final String content = get("mastery", version, locale);
                final String filteredToTags = filterToTags(content, includedData);
                final MasteryList data = DataObject.fromJSON(MasteryList.class, filteredToTags);

                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                data.setIncludedData(includedData);
                for(final Mastery mastery : data.getData().values()) {
                    mastery.setPlatform(platform.getTag());
                    mastery.setVersion(data.getVersion());
                    mastery.setLocale(locale);
                    mastery.setIncludedData(includedData);
                }
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ProfileIconData.class)
    public CloseableIterator<ProfileIconData> getManyProfileIconData(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> versions = (Iterable<String>)query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<ProfileIconData>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ProfileIconData next() {
                final String version = iterator.next();

                final String content = get("profileicon", version, locale);
                final ProfileIconData data = DataObject.fromJSON(ProfileIconData.class, content);

                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                for(final ProfileIconDetails icon : data.getData().values()) {
                    icon.setPlatform(platform.getTag());
                    icon.setVersion(data.getVersion());
                    icon.setLocale(locale);
                }
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Realm.class)
    public CloseableIterator<Realm> getManyRealm(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<Realm>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Realm next() {
                final Platform platform = iterator.next();

                final String content = get("/realms/" + platform.getRegion().getTag(), null, null);
                final Realm data = DataObject.fromJSON(Realm.class, content);

                data.setPlatform(platform.getTag());
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Rune.class)
    public CloseableIterator<Rune> getManyRune(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        Utilities.checkNotNull(platform, "platform", ids, "ids");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("rune", version, locale);
        final String filteredToTags = filterToTags(content, includedData);
        final RuneList data = DataObject.fromJSON(RuneList.class, filteredToTags);

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for(final Rune rune : data.getData().values()) {
            rune.setPlatform(platform.getTag());
            rune.setVersion(data.getVersion());
            rune.setLocale(locale);
            rune.setIncludedData(includedData);
        }

        final Iterator<Number> iterator = ids.iterator();
        return CloseableIterators.from(new Iterator<Rune>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Rune next() {
                final Number id = iterator.next();
                return data.getData().get(id.toString());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(RuneList.class)
    public CloseableIterator<RuneList> getManyRuneList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> versions = (Iterable<String>)query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<RuneList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public RuneList next() {
                final String version = iterator.next();

                final String content = get("rune", version, locale);
                final String filteredToTags = filterToTags(content, includedData);
                final RuneList data = DataObject.fromJSON(RuneList.class, filteredToTags);

                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                data.setIncludedData(includedData);
                for(final Rune rune : data.getData().values()) {
                    rune.setPlatform(platform.getTag());
                    rune.setVersion(data.getVersion());
                    rune.setLocale(locale);
                    rune.setIncludedData(includedData);
                }
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(SummonerSpell.class)
    public CloseableIterator<SummonerSpell> getManySummonerSpell(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        Utilities.checkNotNull(platform, "platform", ids, "ids");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("summoner", version, locale);
        final String filtered = filterToTags(content, includedData);

        final SummonerSpellList data = DataObject.fromJSON(SummonerSpellList.class, SPELL_COEFF_PROCESSOR, filtered);
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for(final SummonerSpell spell : data.getData().values()) {
            spell.setPlatform(platform.getTag());
            spell.setVersion(data.getVersion());
            spell.setLocale(locale);
            spell.setIncludedData(includedData);
        }

        final Iterator<Number> iterator = ids.iterator();
        return CloseableIterators.from(new Iterator<SummonerSpell>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public SummonerSpell next() {
                final Number id = iterator.next();
                return data.getData().get(id.toString());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(SummonerSpellList.class)
    public CloseableIterator<SummonerSpellList> getManySummonerSpellList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> versions = (Iterable<String>)query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<SummonerSpellList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public SummonerSpellList next() {
                final String version = iterator.next();

                final String content = get("summoner", version, locale);
                final String filtered = filterToTags(content, includedData);

                final SummonerSpellList data = DataObject.fromJSON(SummonerSpellList.class, SPELL_LIST_COEFF_PROCESSOR, filtered);
                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                data.setIncludedData(includedData);
                for(final SummonerSpell spell : data.getData().values()) {
                    spell.setPlatform(platform.getTag());
                    spell.setVersion(data.getVersion());
                    spell.setLocale(locale);
                    spell.setIncludedData(includedData);
                }
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Versions.class)
    public CloseableIterator<Versions> getManyVersions(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<Versions>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Versions next() {
                final Platform platform = iterator.next();
                Utilities.checkNotNull(platform, "platform");

                final String content = get("/api/versions", null, null);

                final Versions data = DataObject.fromJSON(Versions.class, content);
                data.setPlatform(platform.getTag());

                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Get(MapData.class)
    public MapData getMapData(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final String content = get("map", version, locale);
        final MapData data = DataObject.fromJSON(MapData.class, content);
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        for(final MapDetails map : data.getData().values()) {
            map.setPlatform(platform.getTag());
            map.setVersion(data.getVersion());
            map.setLocale(locale);
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(Mastery.class)
    public Mastery getMastery(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number id = (Number)query.get("id");
        Utilities.checkNotNull(platform, "platform", id, "id");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("mastery", version, locale);
        final String filteredToTags = filterToTags(content, includedData);
        final String filteredToId = filterToField(filteredToTags, "USE_PARENT", id.toString());
        final Mastery data = DataObject.fromJSON(Mastery.class, filteredToId);

        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(MasteryList.class)
    public MasteryList getMasteryList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("mastery", version, locale);
        final String filteredToTags = filterToTags(content, includedData);
        final MasteryList data = DataObject.fromJSON(MasteryList.class, filteredToTags);

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for(final Mastery mastery : data.getData().values()) {
            mastery.setPlatform(platform.getTag());
            mastery.setVersion(data.getVersion());
            mastery.setLocale(locale);
            mastery.setIncludedData(includedData);
        }
        return data;
    }

    @Get(ProfileIconData.class)
    public ProfileIconData getProfileIconData(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final String content = get("profileicon", version, locale);
        final ProfileIconData data = DataObject.fromJSON(ProfileIconData.class, content);
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        for(final ProfileIconDetails icon : data.getData().values()) {
            icon.setPlatform(platform.getTag());
            icon.setVersion(data.getVersion());
            icon.setLocale(locale);
        }
        return data;
    }

    @Get(Realm.class)
    public Realm getRealm(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String content = get("/realms/" + platform.getRegion().getTag(), null, null);
        final Realm data = DataObject.fromJSON(Realm.class, content);

        data.setPlatform(platform.getTag());
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(Rune.class)
    public Rune getRune(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number id = (Number)query.get("id");
        Utilities.checkNotNull(platform, "platform", id, "id");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("rune", version, locale);
        final String filteredToTags = filterToTags(content, includedData);
        final String filteredToId = filterToField(filteredToTags, "USE_PARENT", id.toString());
        final Rune data = DataObject.fromJSON(Rune.class, filteredToId);

        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(RuneList.class)
    public RuneList getRuneList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("rune", version, locale);
        final String filteredToTags = filterToTags(content, includedData);
        final RuneList data = DataObject.fromJSON(RuneList.class, filteredToTags);

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for(final Rune rune : data.getData().values()) {
            rune.setPlatform(platform.getTag());
            rune.setVersion(data.getVersion());
            rune.setLocale(locale);
            rune.setIncludedData(includedData);
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(SummonerSpell.class)
    public SummonerSpell getSummonerSpell(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number id = (Number)query.get("id");
        Utilities.checkNotNull(platform, "platform", id, "id");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("summoner", version, locale);
        final String filteredToTag = filterToTags(content, includedData);
        final String filteredToId = filterToField(filteredToTag, "id", id.toString());

        final SummonerSpell data = DataObject.fromJSON(SummonerSpell.class, SPELL_COEFF_PROCESSOR, filteredToId);
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(SummonerSpellList.class)
    public SummonerSpellList getSummonerSpellList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("summoner", version, locale);
        final String filtered = filterToTags(content, includedData);

        final SummonerSpellList data = DataObject.fromJSON(SummonerSpellList.class, SPELL_LIST_COEFF_PROCESSOR, filtered);
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for(final SummonerSpell spell : data.getData().values()) {
            spell.setPlatform(platform.getTag());
            spell.setVersion(data.getVersion());
            spell.setLocale(locale);
            spell.setIncludedData(includedData);
        }
        return data;
    }

    @Get(Versions.class)
    public Versions getVersions(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String content = get("/api/versions", null, null);

        final Versions data = DataObject.fromJSON(Versions.class, content);
        data.setPlatform(platform.getTag());

        return data;
    }

    private String makeById(final String json) {
        try {
            final JsonNode dataClone = new ObjectMapper().readTree(json).get("data");

            final JsonNode parent = new ObjectMapper().readTree(json);
            final ObjectNode data = (ObjectNode)parent.get("data");

            for(final JsonNode child : dataClone) {
                if(child instanceof ObjectNode) {
                    final ObjectNode childObject = (ObjectNode)child;
                    childObject.set("id", childObject.get("key"));
                    data.set(childObject.get("key").asText(), child);
                }
            }

            for(final Iterator<String> iterator = dataClone.fieldNames(); iterator.hasNext();) {
                final String field = iterator.next();
                data.remove(field);
            }

            return parent.toString();
        } catch(final IOException e) {
            e.printStackTrace();
            return json;
        }
    }

    private String swapKeyAndId(final String json) {
        try {
            final JsonNode parent = new ObjectMapper().readTree(json);
            final ObjectNode data = (ObjectNode)parent.get("data");

            for(final JsonNode child : data) {
                if(child instanceof ObjectNode) {
                    final ObjectNode childObject = (ObjectNode)child;
                    childObject.set("id", childObject.get("key"));
                }
            }

            return parent.toString();
        } catch(final IOException e) {
            e.printStackTrace();
            return json;
        }
    }
}
