package com.merakianalytics.orianna;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.merakianalytics.datapipelines.DataPipeline;
import com.merakianalytics.orianna.datapipeline.DataDragon;
import com.merakianalytics.orianna.datapipeline.GhostObjectSource;
import com.merakianalytics.orianna.datapipeline.ImageDataSource;
import com.merakianalytics.orianna.datapipeline.InMemoryCache;
import com.merakianalytics.orianna.datapipeline.PipelineConfiguration;
import com.merakianalytics.orianna.datapipeline.PipelineConfiguration.PipelineElementConfiguration;
import com.merakianalytics.orianna.datapipeline.PipelineConfiguration.TransformerConfiguration;
import com.merakianalytics.orianna.datapipeline.common.expiration.ExpirationPeriod;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI;
import com.merakianalytics.orianna.datapipeline.transformers.dtodata.ChampionMasteryTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.dtodata.ChampionTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.dtodata.LeagueTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.dtodata.MatchTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.dtodata.SpectatorTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.dtodata.StaticDataTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.dtodata.StatusTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.dtodata.SummonerTransformer;
import com.merakianalytics.orianna.datapipeline.transformers.dtodata.ThirdPartyCodeTransformer;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScore;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScores;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.Items;
import com.merakianalytics.orianna.types.core.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Map;
import com.merakianalytics.orianna.types.core.staticdata.Maps;
import com.merakianalytics.orianna.types.core.staticdata.Masteries;
import com.merakianalytics.orianna.types.core.staticdata.Mastery;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcons;
import com.merakianalytics.orianna.types.core.staticdata.Realm;
import com.merakianalytics.orianna.types.core.staticdata.Rune;
import com.merakianalytics.orianna.types.core.staticdata.Runes;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpells;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.summoner.Summoners;

public abstract class Orianna {
    public static class Configuration {
        private static final ExpirationPeriod DEFAULT_CURRENT_VERSION_EXPIRATION = ExpirationPeriod.create(6L, TimeUnit.HOURS);
        private static final String DEFAULT_DEFAULT_LOCALE = null;
        private static final Platform DEFAULT_DEFAULT_PLATFORM = Platform.NORTH_AMERICA;

        private static PipelineConfiguration getDefaultPipeline() {
            final PipelineConfiguration config = new PipelineConfiguration();

            final Set<TransformerConfiguration> transformers = ImmutableSet.of(
                TransformerConfiguration.defaultConfiguration(ChampionMasteryTransformer.class),
                TransformerConfiguration.defaultConfiguration(ChampionTransformer.class),
                TransformerConfiguration.defaultConfiguration(LeagueTransformer.class),
                TransformerConfiguration.defaultConfiguration(MatchTransformer.class),
                TransformerConfiguration.defaultConfiguration(SpectatorTransformer.class),
                TransformerConfiguration.defaultConfiguration(StaticDataTransformer.class),
                TransformerConfiguration.defaultConfiguration(StatusTransformer.class),
                TransformerConfiguration.defaultConfiguration(SummonerTransformer.class),
                TransformerConfiguration.defaultConfiguration(ThirdPartyCodeTransformer.class));
            config.setTransformers(transformers);

            final List<PipelineElementConfiguration> elements = ImmutableList.of(
                PipelineElementConfiguration.defaultConfiguration(InMemoryCache.class),
                PipelineElementConfiguration.defaultConfiguration(GhostObjectSource.class),
                PipelineElementConfiguration.defaultConfiguration(DataDragon.class),
                PipelineElementConfiguration.defaultConfiguration(RiotAPI.class),
                PipelineElementConfiguration.defaultConfiguration(ImageDataSource.class));
            config.setElements(elements);

            return config;
        }

        private ExpirationPeriod currentVersionExpiration = DEFAULT_CURRENT_VERSION_EXPIRATION;
        private String defaultLocale = DEFAULT_DEFAULT_LOCALE;
        private Platform defaultPlatform = DEFAULT_DEFAULT_PLATFORM;
        private PipelineConfiguration pipeline = getDefaultPipeline();

        /**
         * @return the currentVersionExpiration
         */
        public ExpirationPeriod getCurrentVersionExpiration() {
            return currentVersionExpiration;
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
        public PipelineConfiguration getPipeline() {
            return pipeline;
        }

        /**
         * @param currentVersionExpiration
         *        the currentVersionExpiration to set
         */
        public void setCurrentVersionExpiration(final ExpirationPeriod currentVersionExpiration) {
            this.currentVersionExpiration = currentVersionExpiration;
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
        public void setPipeline(final PipelineConfiguration pipeline) {
            this.pipeline = pipeline;
        }
    }

    public static class Settings {
        private final Supplier<String> currentVersion;
        private final String defaultLocale;
        private final Platform defaultPlatform;
        private final DataPipeline pipeline;

        private Settings(final Configuration config) {
            pipeline = PipelineConfiguration.toPipeline(config.getPipeline());
            defaultPlatform = config.getDefaultPlatform();
            defaultLocale = config.getDefaultLocale();
            currentVersion = Suppliers.memoizeWithExpiration(new Supplier<String>() {
                @Override
                public String get() {
                    return pipeline
                        .get(com.merakianalytics.orianna.types.dto.staticdata.Realm.class, ImmutableMap.<String, Object> of("platform", defaultPlatform))
                        .getV();
                }
            }, config.getCurrentVersionExpiration().getPeriod(), config.getCurrentVersionExpiration().getUnit());
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

    private static final String CONFIGURATION_PATH_ENVIRONMENT_VARIABLE = "ORIANNA_CONFIGURATION_PATH";
    private static final Logger LOGGER = LoggerFactory.getLogger(Orianna.class);
    private static Settings settings = defaultSettings();

    private static Settings defaultSettings() {
        final String configPath = System.getenv(CONFIGURATION_PATH_ENVIRONMENT_VARIABLE);
        if(configPath != null && !configPath.isEmpty()) {
            try {
                return new Settings(getConfiguration(Files.asCharSource(new File(configPath), Charset.forName("UTF-8"))));
            } catch(final OriannaException e) {
                LOGGER.error("Failed to load environment-configured configuration from " + configPath + "! Using default configuration from resources instead.",
                    e);
            }
        }

        try {
            return new Settings(getConfiguration(
                Resources.asCharSource(Resources.getResource("com/merakianalytics/orianna/default-orianna-config.json"), Charset.forName("UTF-8"))));
        } catch(final OriannaException e) {
            LOGGER.error("Failed to load default configuration from resources! Using default constuctor instead.");
            return new Settings(new Configuration());
        }
    }

    public static ChampionMasteries.Builder getChampionMasteriesForSummoner(final Summoner summoner) {
        return ChampionMasteries.forSummoner(summoner);
    }

    public static ChampionMastery.Builder getChampionMasteryForSummoner(final Summoner summoner) {
        return ChampionMastery.forSummoner(summoner);
    }

    public static ChampionMasteryScore.Builder getChampionMasteryScoreForSummoner(final Summoner summoner) {
        return ChampionMasteryScore.forSummoner(summoner);
    }

    public static ChampionMasteryScores.Builder getChampionMasteryScoresForSummoners(final Iterable<Summoner> summoners) {
        return ChampionMasteryScores.forSummoners(summoners);
    }

    public static ChampionMasteryScores.Builder getChampionMasteryScoresForSummoners(final Summoner... summoners) {
        return ChampionMasteryScores.forSummoners(summoners);
    }

    public static Champion.Builder getChampionNamed(final String name) {
        return Champion.named(name);
    }

    public static Champions getChampions() {
        return Champions.get();
    }

    public static Champions.SubsetBuilder getChampionsNamed(final Iterable<String> names) {
        return Champions.named(names);
    }

    public static Champions.SubsetBuilder getChampionsNamed(final String... names) {
        return Champions.named(names);
    }

    public static Champions.SubsetBuilder getChampionsWithIds(final int... ids) {
        return Champions.withIds(ids);
    }

    public static Champions.SubsetBuilder getChampionsWithIds(final Iterable<Integer> ids) {
        return Champions.withIds(ids);
    }

    public static Champions.Builder getChampionsWithIncludedData(final Set<String> includedData) {
        return Champions.withIncludedData(includedData);
    }

    public static Champions.SubsetBuilder getChampionsWithKeys(final Iterable<String> keys) {
        return Champions.withKeys(keys);
    }

    public static Champions.SubsetBuilder getChampionsWithKeys(final String... keys) {
        return Champions.withKeys(keys);
    }

    public static Champions.Builder getChampionsWithLocale(final String locale) {
        return Champions.withLocale(locale);
    }

    public static Champions.Builder getChampionsWithPlatform(final Platform platform) {
        return Champions.withPlatform(platform);
    }

    public static Champions.Builder getChampionsWithRegion(final Region region) {
        return Champions.withRegion(region);
    }

    public static Champions.Builder getChampionsWithVersion(final String version) {
        return Champions.withVersion(version);
    }

    public static Champion.Builder getChampionWithId(final int id) {
        return Champion.withId(id);
    }

    public static Champion.Builder getChampionWithKey(final String key) {
        return Champion.withKey(key);
    }

    private static Configuration getConfiguration(final CharSource configJSON) {
        final ObjectMapper mapper = new ObjectMapper().enable(Feature.ALLOW_COMMENTS);
        try {
            return mapper.readValue(configJSON.read(), Configuration.class);
        } catch(final IOException e) {
            LOGGER.error("Failed to load configuration JSON!", e);
            throw new OriannaException("Failed to load configuration JSON!", e);
        }
    }

    public static Item.Builder getItemNamed(final String name) {
        return Item.named(name);
    }

    public static Items getItems() {
        return Items.get();
    }

    public static Items.SubsetBuilder getItemsNamed(final Iterable<String> names) {
        return Items.named(names);
    }

    public static Items.SubsetBuilder getItemsNamed(final String... names) {
        return Items.named(names);
    }

    public static Items.SubsetBuilder getItemsWithIds(final int... ids) {
        return Items.withIds(ids);
    }

    public static Items.SubsetBuilder getItemsWithIds(final Iterable<Integer> ids) {
        return Items.withIds(ids);
    }

    public static Items.Builder getItemsWithIncludedData(final Set<String> includedData) {
        return Items.withIncludedData(includedData);
    }

    public static Items.Builder getItemsWithLocale(final String locale) {
        return Items.withLocale(locale);
    }

    public static Items.Builder getItemsWithPlatform(final Platform platform) {
        return Items.withPlatform(platform);
    }

    public static Items.Builder getItemsWithRegion(final Region region) {
        return Items.withRegion(region);
    }

    public static Items.Builder getItemsWithVersion(final String version) {
        return Items.withVersion(version);
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

    public static Map.Builder getMapNamed(final String name) {
        return Map.named(name);
    }

    public static Maps getMaps() {
        return Maps.get();
    }

    public static Maps.SubsetBuilder getMapsNamed(final Iterable<String> names) {
        return Maps.named(names);
    }

    public static Maps.SubsetBuilder getMapsNamed(final String... names) {
        return Maps.named(names);
    }

    public static Maps.SubsetBuilder getMapsWithIds(final int... ids) {
        return Maps.withIds(ids);
    }

    public static Maps.SubsetBuilder getMapsWithIds(final Iterable<Integer> ids) {
        return Maps.withIds(ids);
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

    public static Map.Builder getMapWithId(final int id) {
        return Map.withId(id);
    }

    public static Masteries getMasteries() {
        return Masteries.get();
    }

    public static Masteries.SubsetBuilder getMasteriesNamed(final Iterable<String> names) {
        return Masteries.named(names);
    }

    public static Masteries.SubsetBuilder getMasteriesNamed(final String... names) {
        return Masteries.named(names);
    }

    public static Masteries.SubsetBuilder getMasteriesWithIds(final int... ids) {
        return Masteries.withIds(ids);
    }

    public static Masteries.SubsetBuilder getMasteriesWithIds(final Iterable<Integer> ids) {
        return Masteries.withIds(ids);
    }

    public static Masteries.Builder getMasteriesWithIncludedData(final Set<String> includedData) {
        return Masteries.withIncludedData(includedData);
    }

    public static Masteries.Builder getMasteriesWithLocale(final String locale) {
        return Masteries.withLocale(locale);
    }

    public static Masteries.Builder getMasteriesWithPlatform(final Platform platform) {
        return Masteries.withPlatform(platform);
    }

    public static Masteries.Builder getMasteriesWithRegion(final Region region) {
        return Masteries.withRegion(region);
    }

    public static Masteries.Builder getMasteriesWithVersion(final String version) {
        return Masteries.withVersion(version);
    }

    public static Mastery.Builder getMasteryNamed(final String name) {
        return Mastery.named(name);
    }

    public static Mastery.Builder getMasteryWithId(final int id) {
        return Mastery.withId(id);
    }

    public static ProfileIcons getProfileIcons() {
        return ProfileIcons.get();
    }

    public static ProfileIcons.SubsetBuilder getProfileIconsWithIds(final int... ids) {
        return ProfileIcons.withIds(ids);
    }

    public static ProfileIcons.SubsetBuilder getProfileIconsWithIds(final Iterable<Integer> ids) {
        return ProfileIcons.withIds(ids);
    }

    public static ProfileIcons.Builder getProfileIconsWithLocale(final String locale) {
        return ProfileIcons.withLocale(locale);
    }

    public static ProfileIcons.Builder getProfileIconsWithPlatform(final Platform platform) {
        return ProfileIcons.withPlatform(platform);
    }

    public static ProfileIcons.Builder getProfileIconsWithRegion(final Region region) {
        return ProfileIcons.withRegion(region);
    }

    public static ProfileIcons.Builder getProfileIconsWithVersion(final String version) {
        return ProfileIcons.withVersion(version);
    }

    public static ProfileIcon.Builder getProfileIconWithId(final int id) {
        return ProfileIcon.withId(id);
    }

    public static Realm getRealm() {
        return Realm.get();
    }

    public static Realm.Builder getRealmWithPlatform(final Platform platform) {
        return Realm.withPlatform(platform);
    }

    public static Realm.Builder getRealmWithRegion(final Region region) {
        return Realm.withRegion(region);
    }

    public static Rune.Builder getRuneNamed(final String name) {
        return Rune.named(name);
    }

    public static Runes getRunes() {
        return Runes.get();
    }

    public static Runes.SubsetBuilder getRunesNamed(final Iterable<String> names) {
        return Runes.named(names);
    }

    public static Runes.SubsetBuilder getRunesNamed(final String... names) {
        return Runes.named(names);
    }

    public static Runes.SubsetBuilder getRunesWithIds(final int... ids) {
        return Runes.withIds(ids);
    }

    public static Runes.SubsetBuilder getRunesWithIds(final Iterable<Integer> ids) {
        return Runes.withIds(ids);
    }

    public static Runes.Builder getRunesWithIncludedData(final Set<String> includedData) {
        return Runes.withIncludedData(includedData);
    }

    public static Runes.Builder getRunesWithLocale(final String locale) {
        return Runes.withLocale(locale);
    }

    public static Runes.Builder getRunesWithPlatform(final Platform platform) {
        return Runes.withPlatform(platform);
    }

    public static Runes.Builder getRunesWithRegion(final Region region) {
        return Runes.withRegion(region);
    }

    public static Runes.Builder getRunesWithVersion(final String version) {
        return Runes.withVersion(version);
    }

    public static Rune.Builder getRuneWithId(final int id) {
        return Rune.withId(id);
    }

    public static Settings getSettings() {
        return settings;
    }

    public static Summoner.Builder getSummonerNamed(final String name) {
        return Summoner.named(name);
    }

    public static Summoners.Builder getSummonersNamed(final Iterable<String> names) {
        return Summoners.named(names);
    }

    public static Summoners.Builder getSummonersNamed(final String... names) {
        return Summoners.named(names);
    }

    public static SummonerSpell.Builder getSummonerSpellNamed(final String name) {
        return SummonerSpell.named(name);
    }

    public static SummonerSpells getSummonerSpells() {
        return SummonerSpells.get();
    }

    public static SummonerSpells.SubsetBuilder getSummonerSpellsNamed(final Iterable<String> names) {
        return SummonerSpells.named(names);
    }

    public static SummonerSpells.SubsetBuilder getSummonerSpellsNamed(final String... names) {
        return SummonerSpells.named(names);
    }

    public static SummonerSpells.SubsetBuilder getSummonerSpellsWithIds(final int... ids) {
        return SummonerSpells.withIds(ids);
    }

    public static SummonerSpells.SubsetBuilder getSummonerSpellsWithIds(final Iterable<Integer> ids) {
        return SummonerSpells.withIds(ids);
    }

    public static SummonerSpells.Builder getSummonerSpellsWithIncludedData(final Set<String> includedData) {
        return SummonerSpells.withIncludedData(includedData);
    }

    public static SummonerSpells.Builder getSummonerSpellsWithLocale(final String locale) {
        return SummonerSpells.withLocale(locale);
    }

    public static SummonerSpells.Builder getSummonerSpellsWithPlatform(final Platform platform) {
        return SummonerSpells.withPlatform(platform);
    }

    public static SummonerSpells.Builder getSummonerSpellsWithRegion(final Region region) {
        return SummonerSpells.withRegion(region);
    }

    public static SummonerSpells.Builder getSummonerSpellsWithVersion(final String version) {
        return SummonerSpells.withVersion(version);
    }

    public static SummonerSpell.Builder getSummonerSpellWithId(final int id) {
        return SummonerSpell.withId(id);
    }

    public static Summoners.Builder getSummonersWithAccountIds(final Iterable<Long> accountIds) {
        return Summoners.withAccountIds(accountIds);
    }

    public static Summoners.Builder getSummonersWithAccountIds(final long... accountIds) {
        return Summoners.withAccountIds(accountIds);
    }

    public static Summoners.Builder getSummonersWithIds(final Iterable<Long> ids) {
        return Summoners.withIds(ids);
    }

    public static Summoners.Builder getSummonersWithIds(final long... ids) {
        return Summoners.withIds(ids);
    }

    public static Summoner.Builder getSummonerWithAccountId(final long id) {
        return Summoner.withAccountId(id);
    }

    public static Summoner.Builder getSummonerWithId(final long id) {
        return Summoner.withId(id);
    }

    public static Versions getVersions() {
        return Versions.get();
    }

    public static Versions.Builder getVersionsWithPlatform(final Platform platform) {
        return Versions.withPlatform(platform);
    }

    public static Versions.Builder getVersionsWithRegion(final Region region) {
        return Versions.withRegion(region);
    }

    public static void loadConfiguration(final CharSource configJSON) {
        loadConfiguration(getConfiguration(configJSON));
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
