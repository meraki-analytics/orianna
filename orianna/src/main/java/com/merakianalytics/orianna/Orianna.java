package com.merakianalytics.orianna;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.merakianalytics.datapipelines.DataPipeline;
import com.merakianalytics.datapipelines.transformers.DataTransformer;
import com.merakianalytics.orianna.datapipeline.DataDragon;
import com.merakianalytics.orianna.datapipeline.ImageDataSource;
import com.merakianalytics.orianna.datapipeline.InMemoryCache;
import com.merakianalytics.orianna.datapipeline.UnloadedGhostObjectSource;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI;
import com.merakianalytics.orianna.datapipeline.transformers.ChampionMasteryTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.ChampionTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.LeagueTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.MasteriesTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.MatchTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.RunesTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.SpectatorTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.StaticDataTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.StatusTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.SummonerTransformer;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Maps;
import com.merakianalytics.orianna.types.core.staticdata.Mastery;
import com.merakianalytics.orianna.types.dto.staticdata.Realm;

public abstract class Orianna {
    public static class Configuration {
        private static final long DEFAULT_CURRENT_VERSION_EXPIRATION = 6;
        private static final TimeUnit DEFAULT_CURRENT_VERSION_EXPIRATION_UNIT = TimeUnit.HOURS;
        private static final String DEFAULT_DEFAULT_LOCALE = null;
        private static final Platform DEFAULT_DEFAULT_PLATFORM = Platform.NORTH_AMERICA;

        private static DataPipeline getDefaultPipeline() {
            final Set<DataTransformer> transformers = ImmutableSet.<DataTransformer> builder().add(new ChampionMasteryTransformer())
                .add(new ChampionTransformer()).add(new LeagueTransformer()).add(new MasteriesTransformer())
                .add(new MatchTransformer()).add(new RunesTransformer()).add(new SpectatorTransformer())
                .add(new StaticDataTransformer()).add(new StatusTransformer()).add(new SummonerTransformer())
                .build();

            return new DataPipeline(transformers,
                new InMemoryCache(),
                new UnloadedGhostObjectSource(),
                new DataDragon(),
                new RiotAPI(),
                new ImageDataSource());
        }

        private long currentVersionExpiration = DEFAULT_CURRENT_VERSION_EXPIRATION;
        private TimeUnit currentVersionExpirationUnit = DEFAULT_CURRENT_VERSION_EXPIRATION_UNIT;
        private String defaultLocale = DEFAULT_DEFAULT_LOCALE;
        private Platform defaultPlatform = DEFAULT_DEFAULT_PLATFORM;
        private DataPipeline pipeline = getDefaultPipeline();

        /**
         * @return the currentVersionExpiration
         */
        public long getCurrentVersionExpiration() {
            return currentVersionExpiration;
        }

        /**
         * @return the currentVersionExpirationUnit
         */
        public TimeUnit getCurrentVersionExpirationUnit() {
            return currentVersionExpirationUnit;
        }

        /**
         * @return the defaultLocale
         */
        public String getDefaultLocale() {
            return defaultLocale;
        }

        /**
         * @return the defaultPlatform
         */
        public Platform getDefaultPlatform() {
            return defaultPlatform;
        }

        /**
         * @return the pipeline
         */
        public DataPipeline getPipeline() {
            return pipeline;
        }

        /**
         * @param currentVersionExpiration
         *        the currentVersionExpiration to set
         */
        public void setCurrentVersionExpiration(final long currentVersionExpiration) {
            this.currentVersionExpiration = currentVersionExpiration;
        }

        /**
         * @param currentVersionExpirationUnit
         *        the currentVersionExpirationUnit to set
         */
        public void setCurrentVersionExpirationUnit(final TimeUnit currentVersionExpirationUnit) {
            this.currentVersionExpirationUnit = currentVersionExpirationUnit;
        }

        /**
         * @param defaultLocale
         *        the defaultLocale to set
         */
        public void setDefaultLocale(final String defaultLocale) {
            this.defaultLocale = defaultLocale;
        }

        /**
         * @param defaultPlatform
         *        the defaultPlatform to set
         */
        public void setDefaultPlatform(final Platform defaultPlatform) {
            this.defaultPlatform = defaultPlatform;
        }

        /**
         * @param pipeline
         *        the pipeline to set
         */
        public void setPipeline(final DataPipeline pipeline) {
            this.pipeline = pipeline;
        }
    }

    public static class Settings {
        private final Supplier<String> currentVersion;
        private final String defaultLocale;
        private final Platform defaultPlatform;
        private final DataPipeline pipeline;

        public Settings(final Configuration config) {
            pipeline = config.getPipeline();
            defaultPlatform = config.getDefaultPlatform();
            defaultLocale = config.getDefaultLocale();
            currentVersion = Suppliers.memoizeWithExpiration(new Supplier<String>() {
                @Override
                public String get() {
                    return pipeline.get(Realm.class, ImmutableMap.<String, Object> of("platform", defaultPlatform)).getV();
                }
            }, config.getCurrentVersionExpiration(), config.getCurrentVersionExpirationUnit());
        }

        /**
         * @return the currentVersion
         */
        public String getCurrentVersion() {
            return currentVersion.get();
        }

        /**
         * @return the defaultLocale
         */
        public String getDefaultLocale() {
            return defaultLocale == null ? defaultPlatform.getDefaultLocale() : defaultLocale;
        }

        /**
         * @return the defaultPlatform
         */
        public Platform getDefaultPlatform() {
            return defaultPlatform;
        }

        /**
         * @return the pipeline
         */
        public DataPipeline getPipeline() {
            return pipeline;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Orianna.class);
    private static Settings settings = new Settings(new Configuration());

    public static Champion.Builder getChampionNamed(final String name) {
        return Champion.named(name);
    }

    public static Champion.Builder getChampionWithId(final int id) {
        return Champion.withId(id);
    }

    public static Item.Builder getItemNamed(final String name) {
        return Item.named(name);
    }

    public static Item.Builder getItemWithId(final int id) {
        return Item.withId(id);
    }

    public static Languages getLanguages() {
        return Languages.get();
    }

    public static LanguageStrings getLanguageStrings() {
        return LanguageStrings.get();
    }

    public static LanguageStrings.Builder getLanguageStringsWithLocale(final String locale) {
        return LanguageStrings.withLocale(locale);
    }

    public static LanguageStrings.Builder getLanguageStringsWithPlatform(final Platform platform) {
        return LanguageStrings.withPlatform(platform);
    }

    public static LanguageStrings.Builder getLanguageStringsWithRegion(final Region region) {
        return LanguageStrings.withRegion(region);
    }

    public static LanguageStrings.Builder getLanguageStringsWithVersion(final String version) {
        return LanguageStrings.withVersion(version);
    }

    public static Languages.Builder getLanguagesWithPlatform(final Platform platform) {
        return Languages.withPlatform(platform);
    }

    public static Languages.Builder getLanguagesWithRegion(final Region region) {
        return Languages.withRegion(region);
    }

    public static Maps getMaps() {
        return Maps.get();
    }

    public static Maps.Builder getMapsWithLocale(final String locale) {
        return Maps.withLocale(locale);
    }

    public static Maps.Builder getMapsWithPlatform(final Platform platform) {
        return Maps.withPlatform(platform);
    }

    public static Maps.Builder getMapsWithRegion(final Region region) {
        return Maps.withRegion(region);
    }

    public static Maps.Builder getMapsWithVersion(final String version) {
        return Maps.withVersion(version);
    }

    public static Mastery.Builder getMasteryNamed(final String name) {
        return Mastery.named(name);
    }

    public static Mastery.Builder getMasteryWithId(final int id) {
        return Mastery.withId(id);
    }

    public static Settings getSettings() {
        return settings;
    }

    public static void loadConfiguration(final CharSource configJSON) {
        final ObjectMapper mapper = new ObjectMapper().enable(Feature.ALLOW_COMMENTS);
        try {
            final Configuration config = mapper.readValue(configJSON.read(), Configuration.class);
            loadConfiguration(config);
        } catch(final IOException e) {
            LOGGER.error("Failed to load configuration JSON!", e);
            throw new OriannaException("Failed to load configuration JSON!", e);
        }
    }

    public static void loadConfiguration(final Configuration config) {
        settings = new Settings(config);
    }

    public static void loadConfiguration(final File configJSON) {
        loadConfiguration(Files.asCharSource(configJSON, Charset.forName("UTF-8")));
    }

    public static void loadConfiguration(final String configJSONResourcePath) {
        loadConfiguration(Resources.asCharSource(Resources.getResource(configJSONResourcePath), Charset.forName("UTF-8")));
    }
}
