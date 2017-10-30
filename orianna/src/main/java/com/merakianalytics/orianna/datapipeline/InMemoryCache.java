package com.merakianalytics.orianna.datapipeline;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.cache2k.CacheEntry;
import org.cache2k.expiry.ExpiryPolicy;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.AbstractDataStore;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.sinks.Put;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.orianna.types.UniqueKeys;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.core.GhostObject.LoadHook;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Maps;
import com.merakianalytics.orianna.types.core.staticdata.Mastery;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcons;

public class InMemoryCache extends AbstractDataStore {
    public static class Configuration {
        private Map<String, Integer> expirationPeriods = Collections.emptyMap();

        /**
         * @return the expirationPeriods
         */
        public Map<String, Integer> getExpirationPeriods() {
            return expirationPeriods;
        }

        /**
         * @param expirationPeriods
         *        the expirationPeriods to set
         */
        public void setExpirationPeriods(final Map<String, Integer> expirationPeriods) {
            this.expirationPeriods = expirationPeriods;
        }
    }

    private class Policy implements ExpiryPolicy<Integer, Object> {
        @Override
        public long calculateExpiryTime(final Integer key, final Object value, final long loadTime, final CacheEntry<Integer, Object> oldEntry) {
            return loadTime + expirationPeriods.get(value.getClass()).longValue();
        }
    }

    private static final Map<Class<?>, Long> DEFAULT_EXPIRATION_PERIODS = ImmutableMap.<Class<?>, Long> builder()
        .put(Champion.class, new Long(Hours.hours(6).toStandardDuration().getMillis()))
        .put(Item.class, new Long(Hours.hours(6).toStandardDuration().getMillis()))
        .put(LanguageStrings.class, new Long(Hours.hours(6).toStandardDuration().getMillis()))
        .put(Languages.class, new Long(Hours.hours(6).toStandardDuration().getMillis()))
        .put(Maps.class, new Long(Hours.hours(6).toStandardDuration().getMillis()))
        .put(Mastery.class, new Long(Hours.hours(6).toStandardDuration().getMillis()))
        .put(ProfileIcons.class, new Long(Hours.hours(6).toStandardDuration().getMillis()))
        .build();

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryCache.class);
    private final Cache<Integer, Object> cache;
    private final Map<Class<?>, Long> expirationPeriods;

    public InMemoryCache() {
        this(new Configuration());
    }

    public InMemoryCache(final Configuration config) {
        final Map<Class<?>, Long> periods = new HashMap<>();
        periods.putAll(DEFAULT_EXPIRATION_PERIODS);

        for(final String className : config.getExpirationPeriods().keySet()) {
            try {
                final Class<?> clazz = Class.forName(className);
                // Configured expiration periods are set in minutes
                periods.put(clazz, new Long(Minutes.minutes(config.getExpirationPeriods().get(className)).toStandardDuration().getMillis()));
            } catch(final ClassNotFoundException e) {
                LOGGER.error("Couldn't find class by name " + className + "!", e);
                throw new OriannaException("Couldn't find class by name " + className + "!", e);
            }
        }

        expirationPeriods = Collections.unmodifiableMap(periods);

        cache = new Cache2kBuilder<Integer, Object>() {}.disableLastModificationTime(true).disableStatistics(true).expiryPolicy(new Policy())
            .keepDataAfterExpired(false).permitNullValues(false).storeByReference(true).build();
    }

    @Get(Champion.class)
    public Champion getChampion(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forChampionQuery(query);
        return (Champion)cache.get(key);
    }

    @Get(Item.class)
    public Item getItem(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forItemQuery(query);
        return (Item)cache.get(key);
    }

    @Get(Languages.class)
    public Languages getLanguages(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forLanguagesQuery(query);
        return (Languages)cache.get(key);
    }

    @Get(LanguageStrings.class)
    public LanguageStrings getLanguageStrings(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forLanguageStringsQuery(query);
        return (LanguageStrings)cache.get(key);
    }

    @Get(Maps.class)
    public Maps getMaps(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMapsQuery(query);
        return (Maps)cache.get(key);
    }

    @Get(Mastery.class)
    public Mastery getMastery(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forMasteryQuery(query);
        return (Mastery)cache.get(key);
    }

    @Get(ProfileIcons.class)
    public ProfileIcons getProfileIcons(final Map<String, Object> query, final PipelineContext context) {
        final int key = UniqueKeys.forProfileIconsQuery(query);
        return (ProfileIcons)cache.get(key);
    }

    @Put(Champion.class)
    public void putChampion(final Champion champion, final PipelineContext context) {
        final int[] keys = UniqueKeys.forChampion(champion);

        if(keys.length < 2) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putChampion(champion, null);
                }
            };

            champion.registerGhostLoadHook(hook, Champion.CHAMPION_LOAD_GROUP);
            champion.registerGhostLoadHook(hook, Champion.STATUS_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, champion);
        }
    }

    @Put(Item.class)
    public void putItem(final Item item, final PipelineContext context) {
        final int[] keys = UniqueKeys.forItem(item);

        if(keys.length < 2) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putItem(item, null);
                }
            };

            item.registerGhostLoadHook(hook, Item.ITEM_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, item);
        }
    }

    @Put(Languages.class)
    public void putLanguages(final Languages languages, final PipelineContext context) {
        final int key = UniqueKeys.forLanguages(languages);
        cache.put(key, languages);
    }

    @Put(LanguageStrings.class)
    public void putLanguageStrings(final LanguageStrings languageStrings, final PipelineContext context) {
        final int key = UniqueKeys.forLanguageStrings(languageStrings);
        cache.put(key, languageStrings);
    }

    @Put(Maps.class)
    public void putMaps(final Maps maps, final PipelineContext context) {
        final int key = UniqueKeys.forMaps(maps);
        cache.put(key, maps);
    }

    @Put(Mastery.class)
    public void putMastery(final Mastery item, final PipelineContext context) {
        final int[] keys = UniqueKeys.forMastery(item);

        if(keys.length < 2) {
            final LoadHook hook = new LoadHook() {
                @Override
                public void call() {
                    putMastery(item, null);
                }
            };

            item.registerGhostLoadHook(hook, Mastery.MASTERY_LOAD_GROUP);
        }

        for(final int key : keys) {
            cache.put(key, item);
        }
    }

    @Put(ProfileIcons.class)
    public void putProfileIcons(final ProfileIcons profileIcons, final PipelineContext context) {
        final int key = UniqueKeys.forProfileIcons(profileIcons);
        cache.put(key, profileIcons);
    }
}
