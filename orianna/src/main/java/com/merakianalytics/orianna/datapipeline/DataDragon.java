package com.merakianalytics.orianna.datapipeline;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
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
        private static final long DEFAULT_CACHE_DURATION = 6;
        private static final TimeUnit DEFAULT_CACHE_DURATION_UNIT = TimeUnit.HOURS;
        private static final HTTPClient.Configuration DEFAULT_REQUESTS = new HTTPClient.Configuration();
        private long cacheDuration = DEFAULT_CACHE_DURATION;
        private TimeUnit cacheDurationUnit = DEFAULT_CACHE_DURATION_UNIT;
        private HTTPClient.Configuration requests = DEFAULT_REQUESTS;

        /**
         * @return the cacheDuration
         */
        public long getCacheDuration() {
            return cacheDuration;
        }

        /**
         * @return the cacheDurationUnit
         */
        public TimeUnit getCacheDurationUnit() {
            return cacheDurationUnit;
        }

        /**
         * @return the requests
         */
        public HTTPClient.Configuration getRequests() {
            return requests;
        }

        /**
         * @param cacheDuration
         *        the cacheDuration to set
         */
        public void setCacheDuration(final long cacheDuration) {
            this.cacheDuration = cacheDuration;
        }

        /**
         * @param cacheDurationUnit
         *        the cacheDurationUnit to set
         */
        public void setCacheDurationUnit(final TimeUnit cacheDurationUnit) {
            this.cacheDurationUnit = cacheDurationUnit;
        }

        /**
         * @param requests
         *        the requests to set
         */
        public void setRequests(final HTTPClient.Configuration requests) {
            this.requests = requests;
        }
    }

    private static class IncludedDataProcessor implements Function<JsonNode, JsonNode> {
        public static final Set<String> DEFAULT_CHAMPION_TAGS = ImmutableSet.of("title", "id", "key", "name");
        public static final Set<String> DEFAULT_ITEM_TAGS = ImmutableSet.of("plaintext", "description", "id", "name");
        public static final Set<String> DEFAULT_MASTERY_TAGS = ImmutableSet.of("description", "id", "name");
        public static final Set<String> DEFAULT_RUNE_TAGS = ImmutableSet.of("description", "rune", "id", "name");
        public static final Set<String> DEFAULT_SUMMONER_SPELL_TAGS = ImmutableSet.of("name", "key", "summonerLevel", "id", "description");
        private final boolean all;
        private final Set<String> includedData;

        public IncludedDataProcessor(final Set<String> includedData) {
            this.includedData = includedData;
            all = includedData.contains("all");
        }

        @Override
        public JsonNode apply(final JsonNode tree) {
            if(all) {
                return tree;
            }

            ((ObjectNode)tree).retain(includedData);
            return tree;
        }
    }

    private static class RequestMetadata {
        public String file, version, locale;

        @Override
        public boolean equals(final Object obj) {
            if(this == obj) {
                return true;
            }
            if(obj == null) {
                return false;
            }
            if(getClass() != obj.getClass()) {
                return false;
            }
            final RequestMetadata other = (RequestMetadata)obj;
            if(file == null) {
                if(other.file != null) {
                    return false;
                }
            } else if(!file.equals(other.file)) {
                return false;
            }
            if(locale == null) {
                if(other.locale != null) {
                    return false;
                }
            } else if(!locale.equals(other.locale)) {
                return false;
            }
            if(version == null) {
                if(other.version != null) {
                    return false;
                }
            } else if(!version.equals(other.version)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (file == null ? 0 : file.hashCode());
            result = prime * result + (locale == null ? 0 : locale.hashCode());
            result = prime * result + (version == null ? 0 : version.hashCode());
            return result;
        }
    }

    private static final Function<JsonNode, JsonNode> CHAMPION_PROCESSOR = new Function<JsonNode, JsonNode>() {
        @Override
        public JsonNode apply(final JsonNode championTree) {
            if(championTree == null) {
                return championTree;
            }

            // Swap key and id. They're reversed between ddragon and the API.
            if(championTree.has("key") && championTree.has("id")) {
                final ObjectNode champion = (ObjectNode)championTree;
                final String id = champion.get("key").asText();
                champion.set("key", champion.get("id"));
                champion.set("id", new IntNode(Integer.parseInt(id)));
            }

            // Fix spell coeff field
            final JsonNode temp = championTree.get("spells");
            if(temp == null) {
                return championTree;
            }

            for(final JsonNode spell : temp) {
                SPELL_PROCESSOR.apply(spell);
            }
            return championTree;
        }
    };

    private static final Logger LOGGER = LoggerFactory.getLogger(DataDragon.class);

    private static final Function<JsonNode, JsonNode> SPELL_PROCESSOR = new Function<JsonNode, JsonNode>() {
        @Override
        public JsonNode apply(final JsonNode spellTree) {
            if(spellTree == null) {
                return spellTree;
            }

            // Swap key and id. They're reversed between ddragon and the API.
            if(spellTree.has("key") && spellTree.has("id")) {
                final ObjectNode spell = (ObjectNode)spellTree;
                final String id = spell.get("key").asText();
                spell.set("key", spell.get("id"));
                spell.set("id", new IntNode(Integer.parseInt(id)));
            }

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

    private static String getCurrentVersion(final Platform platform, final PipelineContext context) {
        final Realm realm = context.getPipeline().get(Realm.class, ImmutableMap.<String, Object> of("platform", platform));
        return realm.getV();
    }

    private final ConcurrentHashMap<RequestMetadata, Supplier<String>> cache = new ConcurrentHashMap<>();
    private final long cacheDuration;
    private final TimeUnit cacheDurationUnit;
    private final ConcurrentHashMap<RequestMetadata, Object> cacheLocks = new ConcurrentHashMap<>();

    private final HTTPClient client;

    public DataDragon() {
        this(new Configuration());
    }

    public DataDragon(final Configuration config) {
        client = new HTTPClient(config.getRequests());
        cacheDuration = config.getCacheDuration();
        cacheDurationUnit = config.getCacheDurationUnit();
    }

    private String get(final String file, final String version, final String locale) {
        final RequestMetadata metadata = new RequestMetadata();
        metadata.file = file;
        metadata.version = version;
        metadata.locale = locale;

        Supplier<String> supplier = cache.get(metadata);
        if(supplier == null) {
            Object lock = cacheLocks.get(metadata);
            if(lock == null) {
                synchronized(cacheLocks) {
                    lock = cacheLocks.get(metadata);
                    if(lock == null) {
                        lock = new Object();
                        cacheLocks.put(metadata, lock);
                    }
                }
            }

            synchronized(lock) {
                supplier = cache.get(metadata);
                if(supplier == null) {
                    supplier = Suppliers.memoizeWithExpiration(new Supplier<String>() {
                        @Override
                        public String get() {
                            String URL = "https://ddragon.leagueoflegends.com/";
                            if(version != null && locale != null) {
                                URL += "cdn/" + version + "/data/" + locale + "/";
                            }
                            URL += file + ".json";

                            try {
                                return client.get(URL).getBody();
                            } catch(final TimeoutException e) {
                                LOGGER.info("Get request timed out to " + URL + "!", e);
                                return null;
                            } catch(final IOException e) {
                                LOGGER.error("Get request failed to " + URL + "!", e);
                                throw new OriannaException("Something went wrong with a request to DataDragon API at " + URL
                                                           + "! Report this to the orianna team.", e);
                            }
                        }
                    }, cacheDuration, cacheDurationUnit);
                    cache.put(metadata, supplier);
                }
            }
        }

        return supplier.get();
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

        final Champion data = DataObject.fromJSON(Champion.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_CHAMPION_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final JsonNode temp = tree.get("data");
                if(temp == null) {
                    return tree;
                }

                for(final JsonNode champion : temp) {
                    final JsonNode idNode = champion.get("key");
                    final JsonNode nameNode = champion.get("name");

                    if(id != null && idNode != null && id.intValue() == idNode.asInt() || name != null && nameNode != null && name.equals(nameNode.asText())) {
                        INCLUDED_DATA_PROCESSOR.apply(champion);
                        CHAMPION_PROCESSOR.apply(champion);
                        return champion;
                    }
                }

                return tree;
            }
        }, content);

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

        final String content = get("championFull", version, locale);

        final ChampionList data = DataObject.fromJSON(ChampionList.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_CHAMPION_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final ObjectNode data = (ObjectNode)tree.get("data");
                if(data == null) {
                    return tree;
                }

                for(final JsonNode champion : data) {
                    INCLUDED_DATA_PROCESSOR.apply(champion);
                    CHAMPION_PROCESSOR.apply(champion);

                    if(dataById) {
                        final String key = champion.get("key").asText();
                        final int id = champion.get("id").asInt();

                        data.remove(key);
                        data.set(Integer.toString(id), champion);
                    }
                }
                return tree;
            }
        }, content);

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
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("item", version, locale);

        final Item data = DataObject.fromJSON(Item.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_ITEM_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final JsonNode temp = tree.get("data");
                if(temp == null) {
                    return tree;
                }

                final Iterator<String> ids = temp.fieldNames();
                while(ids.hasNext()) {
                    final String itemId = ids.next();
                    final ObjectNode item = (ObjectNode)temp.get(itemId);
                    final String itemName = item.get("name").asText();

                    if(id != null && id.intValue() == Integer.parseInt(itemId) || name.equals(itemName)) {
                        item.set("id", new IntNode(id.intValue()));

                        INCLUDED_DATA_PROCESSOR.apply(item);
                        return item;
                    }
                }

                return tree;
            }
        }, content);

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

        final ItemList data = DataObject.fromJSON(ItemList.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_ITEM_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final JsonNode temp = tree.get("data");
                if(temp == null) {
                    return tree;
                }

                final Iterator<String> ids = temp.fieldNames();
                while(ids.hasNext()) {
                    final String itemId = ids.next();

                    final ObjectNode item = (ObjectNode)temp.get(itemId);
                    item.set("id", new IntNode(Integer.parseInt(itemId)));

                    INCLUDED_DATA_PROCESSOR.apply(item);
                }

                return tree;
            }
        }, content);

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

        final String content = get("cdn/languages", null, null);
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

        final ChampionList data = DataObject.fromJSON(ChampionList.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_CHAMPION_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final ObjectNode data = (ObjectNode)tree.get("data");
                if(data == null) {
                    return tree;
                }

                for(final JsonNode champion : data) {
                    INCLUDED_DATA_PROCESSOR.apply(champion);
                    CHAMPION_PROCESSOR.apply(champion);

                    final String key = champion.get("key").asText();
                    final int id = champion.get("id").asInt();

                    data.remove(key);
                    data.set(Integer.toString(id), champion);
                }
                return tree;
            }
        }, content);

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

                final String content = get("championFull", version, locale);

                final ChampionList data = DataObject.fromJSON(ChampionList.class, new Function<JsonNode, JsonNode>() {
                    private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                        IncludedDataProcessor.DEFAULT_CHAMPION_TAGS));

                    @Override
                    public JsonNode apply(final JsonNode tree) {
                        if(tree == null) {
                            return tree;
                        }

                        final ObjectNode data = (ObjectNode)tree.get("data");
                        if(data == null) {
                            return tree;
                        }

                        for(final JsonNode champion : data) {
                            INCLUDED_DATA_PROCESSOR.apply(champion);
                            CHAMPION_PROCESSOR.apply(champion);

                            if(dataById) {
                                final String key = champion.get("key").asText();
                                final int id = champion.get("id").asInt();

                                data.remove(key);
                                data.set(Integer.toString(id), champion);
                            }
                        }
                        return tree;
                    }
                }, content);

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
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final String content = get("item", version, locale);

        final ItemList data = DataObject.fromJSON(ItemList.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_ITEM_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final JsonNode temp = tree.get("data");
                if(temp == null) {
                    return tree;
                }

                final Iterator<String> ids = temp.fieldNames();
                while(ids.hasNext()) {
                    final String itemId = ids.next();

                    final ObjectNode item = (ObjectNode)temp.get(itemId);
                    item.set("id", new IntNode(Integer.parseInt(itemId)));

                    INCLUDED_DATA_PROCESSOR.apply(item);
                }

                return tree;
            }
        }, content);

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        final Map<String, Item> byName = ids == null ? new HashMap<String, Item>() : null;
        for(final Item item : data.getData().values()) {
            item.setPlatform(platform.getTag());
            item.setVersion(data.getVersion());
            item.setLocale(locale);
            item.setIncludedData(includedData);

            if(ids == null) {
                byName.put(item.getName(), item);
            }
        }

        final Iterator<?> iterator = ids == null ? names.iterator() : ids.iterator();
        return CloseableIterators.from(new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Item next() {
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

                final ItemList data = DataObject.fromJSON(ItemList.class, new Function<JsonNode, JsonNode>() {
                    private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                        IncludedDataProcessor.DEFAULT_ITEM_TAGS));

                    @Override
                    public JsonNode apply(final JsonNode tree) {
                        if(tree == null) {
                            return tree;
                        }

                        final JsonNode temp = tree.get("data");
                        if(temp == null) {
                            return tree;
                        }

                        final Iterator<String> ids = temp.fieldNames();
                        while(ids.hasNext()) {
                            final String itemId = ids.next();

                            final ObjectNode item = (ObjectNode)temp.get(itemId);
                            item.set("id", new IntNode(Integer.parseInt(itemId)));

                            INCLUDED_DATA_PROCESSOR.apply(item);
                        }

                        return tree;
                    }
                }, content);

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

                final String content = get("cdn/languages", null, null);
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

        final MasteryList data = DataObject.fromJSON(MasteryList.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_MASTERY_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final JsonNode temp = tree.get("data");
                if(temp == null) {
                    return tree;
                }

                for(final JsonNode mastery : temp) {
                    INCLUDED_DATA_PROCESSOR.apply(mastery);
                }

                return tree;
            }
        }, content);

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

                final MasteryList data = DataObject.fromJSON(MasteryList.class, new Function<JsonNode, JsonNode>() {
                    private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                        IncludedDataProcessor.DEFAULT_MASTERY_TAGS));

                    @Override
                    public JsonNode apply(final JsonNode tree) {
                        if(tree == null) {
                            return tree;
                        }

                        final JsonNode temp = tree.get("data");
                        if(temp == null) {
                            return tree;
                        }

                        for(final JsonNode mastery : temp) {
                            INCLUDED_DATA_PROCESSOR.apply(mastery);
                        }

                        return tree;
                    }
                }, content);

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

                final String content = get("realms/" + platform.getRegion().getTag().toLowerCase(), null, null);
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

        final RuneList data = DataObject.fromJSON(RuneList.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_RUNE_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final JsonNode temp = tree.get("data");
                if(temp == null) {
                    return tree;
                }

                final Iterator<String> ids = temp.fieldNames();
                while(ids.hasNext()) {
                    final String runeId = ids.next();

                    final ObjectNode rune = (ObjectNode)temp.get(runeId);
                    rune.set("id", new IntNode(Integer.parseInt(runeId)));

                    INCLUDED_DATA_PROCESSOR.apply(rune);
                }

                return tree;
            }
        }, content);

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

                final RuneList data = DataObject.fromJSON(RuneList.class, new Function<JsonNode, JsonNode>() {
                    private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                        IncludedDataProcessor.DEFAULT_RUNE_TAGS));

                    @Override
                    public JsonNode apply(final JsonNode tree) {
                        if(tree == null) {
                            return tree;
                        }

                        final JsonNode temp = tree.get("data");
                        if(temp == null) {
                            return tree;
                        }

                        final Iterator<String> ids = temp.fieldNames();
                        while(ids.hasNext()) {
                            final String runeId = ids.next();

                            final ObjectNode rune = (ObjectNode)temp.get(runeId);
                            rune.set("id", new IntNode(Integer.parseInt(runeId)));

                            INCLUDED_DATA_PROCESSOR.apply(rune);
                        }

                        return tree;
                    }
                }, content);

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
        final Boolean dataById = query.get("dataById") == null ? Boolean.FALSE : (Boolean)query.get("dataById");

        final String content = get("summoner", version, locale);

        final SummonerSpellList data = DataObject.fromJSON(SummonerSpellList.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_SUMMONER_SPELL_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final ObjectNode data = (ObjectNode)tree.get("data");
                if(data == null) {
                    return tree;
                }

                for(final JsonNode spell : data) {
                    INCLUDED_DATA_PROCESSOR.apply(spell);
                    SPELL_PROCESSOR.apply(spell);

                    if(dataById) {
                        final String key = spell.get("key").asText();
                        final int id = spell.get("id").asInt();

                        data.remove(key);
                        data.set(Integer.toString(id), spell);
                    }
                }
                return tree;
            }
        }, content);

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
        final Boolean dataById = query.get("dataById") == null ? Boolean.FALSE : (Boolean)query.get("dataById");

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

                final SummonerSpellList data = DataObject.fromJSON(SummonerSpellList.class, new Function<JsonNode, JsonNode>() {
                    private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                        IncludedDataProcessor.DEFAULT_SUMMONER_SPELL_TAGS));

                    @Override
                    public JsonNode apply(final JsonNode tree) {
                        if(tree == null) {
                            return tree;
                        }

                        final ObjectNode data = (ObjectNode)tree.get("data");
                        if(data == null) {
                            return tree;
                        }

                        for(final JsonNode spell : data) {
                            INCLUDED_DATA_PROCESSOR.apply(spell);
                            SPELL_PROCESSOR.apply(spell);

                            if(dataById) {
                                final String key = spell.get("key").asText();
                                final int id = spell.get("id").asInt();

                                data.remove(key);
                                data.set(Integer.toString(id), spell);
                            }
                        }
                        return tree;
                    }
                }, content);

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

                final String content = get("api/versions", null, null);

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

        final Mastery data = DataObject.fromJSON(Mastery.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_MASTERY_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final JsonNode temp = tree.get("data");
                if(temp == null) {
                    return tree;
                }

                for(final JsonNode mastery : temp) {
                    final JsonNode idNode = mastery.get("id");

                    if(idNode != null && id.intValue() == idNode.asInt()) {
                        INCLUDED_DATA_PROCESSOR.apply(mastery);
                        return mastery;
                    }
                }

                return tree;
            }
        }, content);

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

        final MasteryList data = DataObject.fromJSON(MasteryList.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_MASTERY_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final JsonNode temp = tree.get("data");
                if(temp == null) {
                    return tree;
                }

                for(final JsonNode mastery : temp) {
                    INCLUDED_DATA_PROCESSOR.apply(mastery);
                }

                return tree;
            }
        }, content);

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

        final String content = get("realms/" + platform.getRegion().getTag().toLowerCase(), null, null);
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

        final Rune data = DataObject.fromJSON(Rune.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_RUNE_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final JsonNode temp = tree.get("data");
                if(temp == null) {
                    return tree;
                }

                final Iterator<String> ids = temp.fieldNames();
                while(ids.hasNext()) {
                    final String runeId = ids.next();
                    final int idValue = Integer.parseInt(runeId);

                    if(idValue == id.intValue()) {
                        final ObjectNode rune = (ObjectNode)temp.get(runeId);
                        rune.set("id", new IntNode(idValue));

                        INCLUDED_DATA_PROCESSOR.apply(rune);
                        return rune;
                    }
                }

                return tree;
            }
        }, content);

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

        final RuneList data = DataObject.fromJSON(RuneList.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_RUNE_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final JsonNode temp = tree.get("data");
                if(temp == null) {
                    return tree;
                }

                final Iterator<String> ids = temp.fieldNames();
                while(ids.hasNext()) {
                    final String runeId = ids.next();

                    final ObjectNode rune = (ObjectNode)temp.get(runeId);
                    rune.set("id", new IntNode(Integer.parseInt(runeId)));

                    INCLUDED_DATA_PROCESSOR.apply(rune);
                }

                return tree;
            }
        }, content);

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

        final SummonerSpell data = DataObject.fromJSON(SummonerSpell.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_SUMMONER_SPELL_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final ObjectNode data = (ObjectNode)tree.get("data");
                if(data == null) {
                    return tree;
                }

                for(final JsonNode spell : data) {
                    final JsonNode idNode = spell.get("key");

                    if(idNode != null && id.intValue() == idNode.asInt()) {
                        INCLUDED_DATA_PROCESSOR.apply(spell);
                        CHAMPION_PROCESSOR.apply(spell);
                        return spell;
                    }
                }
                return tree;
            }
        }, content);

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
        final Boolean dataById = query.get("dataById") == null ? Boolean.FALSE : (Boolean)query.get("dataById");

        final String content = get("summoner", version, locale);

        final SummonerSpellList data = DataObject.fromJSON(SummonerSpellList.class, new Function<JsonNode, JsonNode>() {
            private final Function<JsonNode, JsonNode> INCLUDED_DATA_PROCESSOR = new IncludedDataProcessor(Sets.union(includedData,
                IncludedDataProcessor.DEFAULT_SUMMONER_SPELL_TAGS));

            @Override
            public JsonNode apply(final JsonNode tree) {
                if(tree == null) {
                    return tree;
                }

                final ObjectNode data = (ObjectNode)tree.get("data");
                if(data == null) {
                    return tree;
                }

                for(final JsonNode spell : data) {
                    INCLUDED_DATA_PROCESSOR.apply(spell);
                    SPELL_PROCESSOR.apply(spell);

                    if(dataById) {
                        final String key = spell.get("key").asText();
                        final int id = spell.get("id").asInt();

                        data.remove(key);
                        data.set(Integer.toString(id), spell);
                    }
                }
                return tree;
            }
        }, content);

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

        final String content = get("api/versions", null, null);

        final Versions data = DataObject.fromJSON(Versions.class, content);
        data.setPlatform(platform.getTag());

        return data;
    }
}
