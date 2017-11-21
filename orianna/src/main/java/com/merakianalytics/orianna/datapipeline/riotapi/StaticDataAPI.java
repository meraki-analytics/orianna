package com.merakianalytics.orianna.datapipeline.riotapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI.Configuration;
import com.merakianalytics.orianna.types.common.Platform;
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

public class StaticDataAPI extends RiotAPIService {
    private static String getCurrentVersion(final Platform platform, final PipelineContext context) {
        final Realm realm = context.getPipeline().get(Realm.class, ImmutableMap.<String, Object> of("platform", platform));
        return realm.getV();
    }

    public StaticDataAPI(final Configuration config, final HTTPClient client, final Map<Platform, RateLimiter> applicationRateLimiters,
        final Map<Platform, Object> applicationRateLimiterLocks) {
        super(config, client, applicationRateLimiters, applicationRateLimiterLocks);
    }

    @SuppressWarnings("unchecked")
    @Get(Champion.class)
    public Champion getChampion(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        final String key = (String)query.get("key");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name", key, "key");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        Champion data = null;
        if(id != null) {
            final String endpoint = "lol/static-data/v3/champions/" + id;

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("locale", locale);
            parameters.put("version", version);
            parameters.putAll("tags", includedData);

            data = get(Champion.class, endpoint, platform, parameters, "lol/static-data/v3/champions/id");
        } else {
            final String endpoint = "lol/static-data/v3/champions";

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("locale", locale);
            parameters.put("version", version);
            parameters.putAll("tags", includedData);
            parameters.put("dataById", Boolean.FALSE.toString());

            final ChampionList list = get(ChampionList.class, endpoint, platform, parameters, "lol/static-data/v3/champions");

            if(key != null) {
                data = list.getData().get(key);
            } else {
                for(final Champion champion : list.getData().values()) {
                    if(name.equals(champion.getName())) {
                        data = champion;
                        break;
                    }
                }
            }
        }
        if(data == null) {
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
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");
        final Boolean dataById = query.get("dataById") == null ? Boolean.FALSE : (Boolean)query.get("dataById");

        final String endpoint = "lol/static-data/v3/champions";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }
        parameters.putAll("tags", includedData);
        parameters.put("dataById", dataById.toString());

        final ChampionList data = get(ChampionList.class, endpoint, platform, parameters, "lol/static-data/v3/champions");
        if(data == null) {
            return null;
        }

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
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        Item data = null;
        if(id != null) {
            final String endpoint = "lol/static-data/v3/items/" + id;

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("locale", locale);
            parameters.put("version", version);
            parameters.putAll("tags", includedData);

            data = get(Item.class, endpoint, platform, parameters, "lol/static-data/v3/items/id");
        } else {
            final String endpoint = "lol/static-data/v3/items";

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("locale", locale);
            parameters.put("version", version);
            parameters.putAll("tags", includedData);

            final ItemList list = get(ItemList.class, endpoint, platform, parameters, "lol/static-data/v3/items");
            for(final Item item : list.getData().values()) {
                if(name.equals(item.getName())) {
                    data = item;
                    break;
                }
            }
        }
        if(data == null) {
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
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final String endpoint = "lol/static-data/v3/items";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }
        parameters.putAll("tags", includedData);

        final ItemList data = get(ItemList.class, endpoint, platform, parameters, "lol/static-data/v3/items");
        if(data == null) {
            return null;
        }

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

        final String endpoint = "lol/static-data/v3/languages";
        final Languages data = get(Languages.class, endpoint, platform, "lol/static-data/v3/languages");
        if(data == null) {
            return null;
        }

        data.setPlatform(platform.getTag());
        return data;
    }

    @Get(LanguageStrings.class)
    public LanguageStrings getLanguageStrings(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }

        final String endpoint = "lol/static-data/v3/language-strings";
        final LanguageStrings data = get(LanguageStrings.class, endpoint, platform, parameters, "lol/static-data/v3/language-strings");
        if(data == null) {
            return null;
        }

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
        final Iterable<String> keys = (Iterable<String>)query.get("keys");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names", keys, "keys");
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final String endpoint = "lol/static-data/v3/champions";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }
        parameters.putAll("tags", includedData);
        parameters.put("dataById", Boolean.toString(ids != null));

        final ChampionList data = get(ChampionList.class, endpoint, platform, parameters, "lol/static-data/v3/champions");
        if(data == null) {
            return null;
        }

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        final Map<String, Champion> byName = ids == null && keys == null ? new HashMap<String, Champion>() : null;
        for(final Champion champion : data.getData().values()) {
            champion.setPlatform(platform.getTag());
            champion.setVersion(data.getVersion());
            champion.setLocale(locale);
            champion.setIncludedData(includedData);

            if(ids == null && keys == null) {
                byName.put(champion.getName(), champion);
            }
        }

        final Iterator<?> iterator;
        if(ids == null && keys == null) {
            iterator = names.iterator();
        } else if(ids == null) {
            iterator = keys.iterator();
        } else {
            iterator = ids.iterator();
        }

        return CloseableIterators.from(new Iterator<Champion>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Champion next() {
                if(ids == null && keys == null) {
                    final String name = (String)iterator.next();
                    return byName.get(name);
                } else {
                    final Object idOrKey = iterator.next();
                    return data.getData().get(idOrKey.toString());
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
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");
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

                final String endpoint = "lol/static-data/v3/champions";

                final Multimap<String, String> params = HashMultimap.create(parameters);
                params.put("version", version);

                final ChampionList data = get(ChampionList.class, endpoint, platform, params, "lol/static-data/v3/champions");
                if(data == null) {
                    return null;
                }

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
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final String endpoint = "lol/static-data/v3/items";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }
        parameters.putAll("tags", includedData);

        final ItemList data = get(ItemList.class, endpoint, platform, parameters, "lol/static-data/v3/items");
        if(data == null) {
            return null;
        }

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
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        parameters.putAll("tags", includedData);

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<ItemList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ItemList next() {
                final String version = iterator.next();

                final String endpoint = "lol/static-data/v3/items";

                final Multimap<String, String> params = HashMultimap.create(parameters);
                params.put("version", version);

                final ItemList data = get(ItemList.class, endpoint, platform, params, "lol/static-data/v3/items");
                if(data == null) {
                    return null;
                }

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

                final String endpoint = "lol/static-data/v3/languages";
                final Languages data = get(Languages.class, endpoint, platform, "lol/static-data/v3/languages");
                if(data == null) {
                    return null;
                }

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
        final String version = (String)query.get("version");

        final Multimap<String, String> parameters = HashMultimap.create();
        if(version != null) {
            parameters.put("version", version);
        }

        final Iterator<String> iterator = locales.iterator();
        return CloseableIterators.from(new Iterator<LanguageStrings>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public LanguageStrings next() {
                final String locale = iterator.next();

                final Multimap<String, String> params = HashMultimap.create(parameters);
                params.put("locale", locale);

                final String endpoint = "lol/static-data/v3/language-strings";
                final LanguageStrings data = get(LanguageStrings.class, endpoint, platform, params, "lol/static-data/v3/language-strings");
                if(data == null) {
                    return null;
                }

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

                final String endpoint = "lol/static-data/v3/maps";
                final MapData data = get(MapData.class, endpoint, platform, ImmutableMultimap.of("locale", locale, "version", version),
                    "lol/static-data/v3/maps");
                if(data == null) {
                    return null;
                }

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
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final String endpoint = "lol/static-data/v3/masteries";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }
        parameters.putAll("tags", includedData);

        final MasteryList data = get(MasteryList.class, endpoint, platform, parameters, "lol/static-data/v3/masteries");
        if(data == null) {
            return null;
        }

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        final Map<String, Mastery> byName = ids == null ? new HashMap<String, Mastery>() : null;
        for(final Mastery mastery : data.getData().values()) {
            mastery.setPlatform(platform.getTag());
            mastery.setVersion(data.getVersion());
            mastery.setLocale(locale);
            mastery.setIncludedData(includedData);

            if(ids == null) {
                byName.put(mastery.getName(), mastery);
            }
        }

        final Iterator<?> iterator = ids == null ? names.iterator() : ids.iterator();
        return CloseableIterators.from(new Iterator<Mastery>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Mastery next() {
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
    @GetMany(MasteryList.class)
    public CloseableIterator<MasteryList> getManyMasteryList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> versions = (Iterable<String>)query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        parameters.putAll("tags", includedData);

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<MasteryList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MasteryList next() {
                final String version = iterator.next();

                final String endpoint = "lol/static-data/v3/masteries";

                final Multimap<String, String> params = HashMultimap.create(parameters);
                params.put("version", version);

                final MasteryList data = get(MasteryList.class, endpoint, platform, params, "lol/static-data/v3/masteries");
                if(data == null) {
                    return null;
                }

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

                final String endpoint = "lol/static-data/v3/profile-icons";
                final ProfileIconData data = get(ProfileIconData.class, endpoint, platform, ImmutableMultimap.of("locale", locale, "version", version),
                    "lol/static-data/v3/profile-icons");
                if(data == null) {
                    return null;
                }

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

                final String endpoint = "lol/static-data/v3/realms";
                final Realm data = get(Realm.class, endpoint, platform, "lol/static-data/v3/realms");
                if(data == null) {
                    return null;
                }

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
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final String endpoint = "lol/static-data/v3/runes";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }
        parameters.putAll("tags", includedData);

        final RuneList data = get(RuneList.class, endpoint, platform, parameters, "lol/static-data/v3/runes");
        if(data == null) {
            return null;
        }

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        final Map<String, Rune> byName = ids == null ? new HashMap<String, Rune>() : null;
        for(final Rune rune : data.getData().values()) {
            rune.setPlatform(platform.getTag());
            rune.setVersion(data.getVersion());
            rune.setLocale(locale);
            rune.setIncludedData(includedData);

            if(ids == null) {
                byName.put(rune.getName(), rune);
            }
        }

        final Iterator<?> iterator = ids == null ? names.iterator() : ids.iterator();
        return CloseableIterators.from(new Iterator<Rune>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Rune next() {
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
    @GetMany(RuneList.class)
    public CloseableIterator<RuneList> getManyRuneList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> versions = (Iterable<String>)query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        parameters.putAll("tags", includedData);

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<RuneList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public RuneList next() {
                final String version = iterator.next();

                final String endpoint = "lol/static-data/v3/runes";

                final Multimap<String, String> params = HashMultimap.create(parameters);
                params.put("version", version);

                final RuneList data = get(RuneList.class, endpoint, platform, params, "lol/static-data/v3/runes");
                if(data == null) {
                    return null;
                }

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
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final String endpoint = "lol/static-data/v3/summoner-spells";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }
        parameters.putAll("tags", includedData);

        final SummonerSpellList data = get(SummonerSpellList.class, endpoint, platform, parameters, "lol/static-data/v3/summoner-spells");
        if(data == null) {
            return null;
        }

        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        final Map<String, SummonerSpell> byName = ids == null ? new HashMap<String, SummonerSpell>() : null;
        for(final SummonerSpell item : data.getData().values()) {
            item.setPlatform(platform.getTag());
            item.setVersion(data.getVersion());
            item.setLocale(locale);
            item.setIncludedData(includedData);

            if(ids == null) {
                byName.put(item.getName(), item);
            }
        }

        final Iterator<?> iterator = ids == null ? names.iterator() : ids.iterator();
        return CloseableIterators.from(new Iterator<SummonerSpell>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public SummonerSpell next() {
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
    @GetMany(SummonerSpellList.class)
    public CloseableIterator<SummonerSpellList> getManySummonerSpellList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> versions = (Iterable<String>)query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");
        final Boolean dataById = query.get("dataById") == null ? Boolean.FALSE : (Boolean)query.get("dataById");

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        parameters.putAll("tags", includedData);
        parameters.put("dataById", dataById.toString());

        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<SummonerSpellList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public SummonerSpellList next() {
                final String version = iterator.next();

                final String endpoint = "lol/static-data/v3/summoner-spells";

                final Multimap<String, String> params = HashMultimap.create(parameters);
                params.put("version", version);

                final SummonerSpellList data = get(SummonerSpellList.class, endpoint, platform, params, "lol/static-data/v3/summoner-spells");
                if(data == null) {
                    return null;
                }

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

                final String endpoint = "lol/static-data/v3/versions";
                final Versions data = get(Versions.class, endpoint, platform, "lol/static-data/v3/versions");
                if(data == null) {
                    return null;
                }

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
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final String endpoint = "lol/static-data/v3/maps";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }

        final MapData data = get(MapData.class, endpoint, platform, parameters, "lol/static-data/v3/maps");
        if(data == null) {
            return null;
        }

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
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        Mastery data = null;
        if(id != null) {
            final String endpoint = "lol/static-data/v3/masteries/" + id;

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("locale", locale);
            parameters.put("version", version);
            parameters.putAll("tags", includedData);

            data = get(Mastery.class, endpoint, platform, parameters, "lol/static-data/v3/masteries/id");
        } else {
            final String endpoint = "lol/static-data/v3/masteries";

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("locale", locale);
            parameters.put("version", version);
            parameters.putAll("tags", includedData);

            final MasteryList list = get(MasteryList.class, endpoint, platform, parameters, "lol/static-data/v3/items");
            for(final Mastery mastery : list.getData().values()) {
                if(name.equals(mastery.getName())) {
                    data = mastery;
                    break;
                }
            }
        }
        if(data == null) {
            return null;
        }

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
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final String endpoint = "lol/static-data/v3/masteries";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }
        parameters.putAll("tags", includedData);

        final MasteryList data = get(MasteryList.class, endpoint, platform, parameters, "lol/static-data/v3/masteries");
        if(data == null) {
            return null;
        }

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
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }

        final String endpoint = "lol/static-data/v3/profile-icons";
        final ProfileIconData data = get(ProfileIconData.class, endpoint, platform, parameters, "lol/static-data/v3/profile-icons");
        if(data == null) {
            return null;
        }

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

        final String endpoint = "lol/static-data/v3/realms";
        final Realm data = get(Realm.class, endpoint, platform, "lol/static-data/v3/realms");
        if(data == null) {
            return null;
        }

        data.setPlatform(platform.getTag());
        return data;
    }

    @SuppressWarnings("unchecked")
    @Get(Rune.class)
    public Rune getRune(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        Rune data = null;
        if(id != null) {
            final String endpoint = "lol/static-data/v3/runes/" + id;

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("locale", locale);
            parameters.put("version", version);
            parameters.putAll("tags", includedData);

            data = get(Rune.class, endpoint, platform, parameters, "lol/static-data/v3/runes/id");
        } else {
            final String endpoint = "lol/static-data/v3/runes";

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("locale", locale);
            parameters.put("version", version);
            parameters.putAll("tags", includedData);

            final RuneList list = get(RuneList.class, endpoint, platform, parameters, "lol/static-data/v3/runes");
            for(final Rune rune : list.getData().values()) {
                if(name.equals(rune.getName())) {
                    data = rune;
                    break;
                }
            }
        }
        if(data == null) {
            return null;
        }

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
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        final String endpoint = "lol/static-data/v3/runes";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }
        parameters.putAll("tags", includedData);

        final RuneList data = get(RuneList.class, endpoint, platform, parameters, "lol/static-data/v3/runes");
        if(data == null) {
            return null;
        }

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
        Utilities.checkNotNull(platform, "platform");
        final Number id = (Number)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");

        SummonerSpell data = null;
        if(id != null) {
            final String endpoint = "lol/static-data/v3/summoner-spells/" + id;

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("locale", locale);
            parameters.put("version", version);
            parameters.putAll("tags", includedData);

            data = get(SummonerSpell.class, endpoint, platform, parameters, "lol/static-data/v3/summoner-spells/id");
        } else {
            final String endpoint = "lol/static-data/v3/summoner-spells";

            final Multimap<String, String> parameters = HashMultimap.create();
            parameters.put("locale", locale);
            parameters.put("version", version);
            parameters.putAll("tags", includedData);

            final SummonerSpellList list = get(SummonerSpellList.class, endpoint, platform, parameters, "lol/static-data/v3/summoner-spells");
            for(final SummonerSpell spell : list.getData().values()) {
                if(name.equals(spell.getName())) {
                    data = spell;
                    break;
                }
            }
        }
        if(data == null) {
            return null;
        }

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
        final String version = (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? Collections.<String> emptySet() : (Set<String>)query.get("includedData");
        final Boolean dataById = query.get("dataById") == null ? Boolean.FALSE : (Boolean)query.get("dataById");

        final String endpoint = "lol/static-data/v3/summoner-spells";

        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        if(version != null) {
            parameters.put("version", version);
        }
        parameters.putAll("tags", includedData);
        parameters.put("dataById", dataById.toString());

        final SummonerSpellList data = get(SummonerSpellList.class, endpoint, platform, parameters, "lol/static-data/v3/summoner-spells");
        if(data == null) {
            return null;
        }

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

        final String endpoint = "lol/static-data/v3/versions";
        final Versions data = get(Versions.class, endpoint, platform, "lol/static-data/v3/versions");
        if(data == null) {
            return null;
        }

        data.setPlatform(platform.getTag());
        return data;
    }
}
