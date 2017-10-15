package com.merakianalytics.orianna;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.io.*;
import com.merakianalytics.datapipelines.DataPipeline;
import com.merakianalytics.datapipelines.transformers.DataTransformer;
import com.merakianalytics.orianna.datapipeline.*;
import com.merakianalytics.orianna.datapipeline.riotapi.*;
import com.merakianalytics.orianna.datapipeline.transformers.*;
import com.merakianalytics.orianna.types.common.*;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.dto.staticdata.Realm;
import org.slf4j.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
                                    new ImageDataSource(),
                                    new JSONDataSource(),
                                    new DataDragonSource(),
                                    new RiotAPI()
            );
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

    public static Champion getChampion(final int id) {
        return Champion.withId(id);
    }

    public static Champion getChampion(final int id, final Platform platform) {
        return Champion.withId(id, platform);
    }

    public static Champion getChampion(final int id, final Platform platform, final String version) {
        return Champion.withId(id, platform, version);
    }

    public static Champion getChampion(final int id, final Platform platform, final String version, final String locale) {
        return Champion.withId(id, platform, version, locale);
    }

    public static Champion getChampion(final int id, final Region region) {
        return Champion.withId(id, region);
    }

    public static Champion getChampion(final int id, final Region region, final String version) {
        return Champion.withId(id, region, version);
    }

    public static Champion getChampion(final int id, final Region region, final String version, final String locale) {
        return Champion.withId(id, region, version, locale);
    }

    public static Champion getChampion(final String name) {
        return Champion.named(name);
    }

    public static Champion getChampion(final String name, final Platform platform) {
        return Champion.named(name, platform);
    }

    public static Champion getChampion(final String name, final Platform platform, final String version) {
        return Champion.named(name, platform, version);
    }

    public static Champion getChampion(final String name, final Platform platform, final String version, final String locale) {
        return Champion.named(name, platform, version, locale);
    }

    public static Champion getChampion(final String name, final Region region) {
        return Champion.named(name, region);
    }

    public static Champion getChampion(final String name, final Region region, final String version) {
        return Champion.named(name, region, version);
    }

    public static Champion getChampion(final String name, final Region region, final String version, final String locale) {
        return Champion.named(name, region, version, locale);
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

    private Orianna() {}
}
