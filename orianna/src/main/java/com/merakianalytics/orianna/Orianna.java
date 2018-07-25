package com.merakianalytics.orianna;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
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
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScore;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScores;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.league.Leagues;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistories;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Matches;
import com.merakianalytics.orianna.types.core.match.Timeline;
import com.merakianalytics.orianna.types.core.match.Timelines;
import com.merakianalytics.orianna.types.core.match.TournamentMatches;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatches;
import com.merakianalytics.orianna.types.core.spectator.FeaturedMatches;
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
import com.merakianalytics.orianna.types.core.staticdata.Realms;
import com.merakianalytics.orianna.types.core.staticdata.ReforgedRune;
import com.merakianalytics.orianna.types.core.staticdata.ReforgedRunes;
import com.merakianalytics.orianna.types.core.staticdata.Rune;
import com.merakianalytics.orianna.types.core.staticdata.Runes;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpells;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.status.ShardStatus;
import com.merakianalytics.orianna.types.core.status.ShardStatuses;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.summoner.Summoners;
import com.merakianalytics.orianna.types.core.thirdpartycode.VerificationString;
import com.merakianalytics.orianna.types.core.thirdpartycode.VerificationStrings;

public abstract class Orianna {
    public static class Configuration {
        private static final ExpirationPeriod DEFAULT_CURRENT_VERSION_EXPIRATION = ExpirationPeriod.create(6L, TimeUnit.HOURS);
        private static final String DEFAULT_DEFAULT_LOCALE = null;
        private static final Platform DEFAULT_DEFAULT_PLATFORM = null;

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
        private final Configuration configuration;
        private final java.util.Map<Platform, Supplier<String>> currentVersion;
        private final Object currentVersionLock = new Object();
        private Supplier<DataPipeline> pipeline;

        private Settings(final Configuration config) {
            pipeline = newPipelineSupplier();
            configuration = config;
            currentVersion = new ConcurrentHashMap<>();
        }

        /**
         * @param platform
         *        the platform to get the current version for
         * @return the currentVersion the that platform
         */
        public String getCurrentVersion(final Platform platform) {
            Supplier<String> version = currentVersion.get(platform);
            if(version == null) {
                synchronized(currentVersionLock) {
                    version = currentVersion.get(platform);
                    if(version == null) {
                        version = newVersionSupplier(platform);
                        currentVersion.put(platform, version);
                    }
                }
            }
            return version.get();
        }

        /**
         * @return the defaultLocale
         */
        public String getDefaultLocale() {
            return configuration.getDefaultLocale();
        }

        /**
         * @return the defaultPlatform
         */
        public Platform getDefaultPlatform() {
            return configuration.getDefaultPlatform();
        }

        /**
         * @return the pipeline
         */
        public DataPipeline getPipeline() {
            return pipeline.get();
        }

        private Supplier<DataPipeline> newPipelineSupplier() {
            return Suppliers.memoize(new Supplier<DataPipeline>() {
                @Override
                public DataPipeline get() {
                    return PipelineConfiguration.toPipeline(configuration.getPipeline());
                }
            });
        }

        private Supplier<String> newVersionSupplier(final Platform platform) {
            Supplier<String> supplier = new Supplier<String>() {
                @Override
                public String get() {
                    return platform.getRealm().getVersion();
                }
            };

            if(configuration.getCurrentVersionExpiration().getPeriod() < 0) {
                supplier = Suppliers.memoize(supplier);
            } else if(configuration.getCurrentVersionExpiration().getPeriod() > 0) {
                supplier = Suppliers.memoizeWithExpiration(supplier, configuration.getCurrentVersionExpiration().getPeriod(),
                    configuration.getCurrentVersionExpiration().getUnit());
            }
            return supplier;
        }

        private void setDefaultLocale(final String defaultLocale) {
            configuration.setDefaultLocale(defaultLocale);
        }

        private void setDefaultPlatform(final Platform defaultPlatform) {
            configuration.setDefaultPlatform(defaultPlatform);
        }

        private void setRiotAPIKey(final String key) {
            boolean changed = false;
            for(final PipelineElementConfiguration element : configuration.getPipeline().getElements()) {
                if(RiotAPI.class.getCanonicalName().equals(element.getClassName())) {
                    ((ObjectNode)element.getConfig()).set("apiKey", new TextNode(key));
                    changed = true;
                    break;
                }
            }

            if(changed) {
                pipeline = newPipelineSupplier();
            }
        }
    }

    private static final String CONFIGURATION_PATH_ENVIRONMENT_VARIABLE = "ORIANNA_CONFIGURATION_PATH";
    private static final Logger LOGGER = LoggerFactory.getLogger(Orianna.class);
    private static Settings settings = defaultSettings();

    public static League.SelectBuilder.SubBuilder challengerLeagueInQueue(final Queue queue) {
        return League.challengerInQueue(queue);
    }

    public static Leagues.SelectBuilder.SubBuilder challengerLeaguesInQueues(final Iterable<Queue> queues) {
        return Leagues.challengerInQueues(queues);
    }

    public static Leagues.SelectBuilder.SubBuilder challengerLeaguesInQueues(final Queue... queues) {
        return Leagues.challengerInQueues(queues);
    }

    public static ChampionMasteries.Builder championMasteriesForSummoner(final Summoner summoner) {
        return ChampionMasteries.forSummoner(summoner);
    }

    public static ChampionMasteries.ManyBuilder championMasteriesForSummoners(final Iterable<Summoner> summoners) {
        return ChampionMasteries.forSummoners(summoners);
    }

    public static ChampionMasteries.ManyBuilder championMasteriesForSummoners(final Summoner... summoners) {
        return ChampionMasteries.forSummoners(summoners);
    }

    public static ChampionMastery.Builder championMasteryForSummoner(final Summoner summoner) {
        return ChampionMastery.forSummoner(summoner);
    }

    public static ChampionMasteryScore.Builder championMasteryScoreForSummoner(final Summoner summoner) {
        return ChampionMasteryScore.forSummoner(summoner);
    }

    public static ChampionMasteryScores.Builder championMasteryScoresForSummoners(final Iterable<Summoner> summoners) {
        return ChampionMasteryScores.forSummoners(summoners);
    }

    public static ChampionMasteryScores.Builder championMasteryScoresForSummoners(final Summoner... summoners) {
        return ChampionMasteryScores.forSummoners(summoners);
    }

    public static Champion.Builder championNamed(final String name) {
        return Champion.named(name);
    }

    public static Champions.SubsetBuilder championsNamed(final Iterable<String> names) {
        return Champions.named(names);
    }

    public static Champions.SubsetBuilder championsNamed(final String... names) {
        return Champions.named(names);
    }

    public static Champions.SubsetBuilder championsWithIds(final int... ids) {
        return Champions.withIds(ids);
    }

    public static Champions.SubsetBuilder championsWithIds(final Iterable<Integer> ids) {
        return Champions.withIds(ids);
    }

    public static Champions.Builder championsWithIncludedData(final Iterable<String> includedData) {
        return Champions.withIncludedData(includedData);
    }

    public static Champions.Builder championsWithIncludedData(final String... includedData) {
        return Champions.withIncludedData(includedData);
    }

    public static Champions.SubsetBuilder championsWithKeys(final Iterable<String> keys) {
        return Champions.withKeys(keys);
    }

    public static Champions.SubsetBuilder championsWithKeys(final String... keys) {
        return Champions.withKeys(keys);
    }

    public static Champions.Builder championsWithLocale(final String locale) {
        return Champions.withLocale(locale);
    }

    public static Champions.Builder championsWithPlatform(final Platform platform) {
        return Champions.withPlatform(platform);
    }

    public static Champions.Builder championsWithRegion(final Region region) {
        return Champions.withRegion(region);
    }

    public static Champions.Builder championsWithVersion(final String version) {
        return Champions.withVersion(version);
    }

    public static Champion.Builder championWithId(final int id) {
        return Champion.withId(id);
    }

    public static Champion.Builder championWithKey(final String key) {
        return Champion.withKey(key);
    }

    public static CurrentMatches.Builder currentMatchesForSummoners(final Iterable<Summoner> summoners) {
        return CurrentMatches.forSummoners(summoners);
    }

    public static CurrentMatches.Builder currentMatchesForSummoners(final Summoner... summoners) {
        return CurrentMatches.forSummoners(summoners);
    }

    public static CurrentMatch.Builder currentMatchForSummoner(final Summoner summoner) {
        return CurrentMatch.forSummoner(summoner);
    }

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

    public static FeaturedMatches.Builder featuredMatchesForPlatform(final Platform platform) {
        return FeaturedMatches.forPlatform(platform);
    }

    public static FeaturedMatches.ManyBuilder featuredMatchesForPlatforms(final Iterable<Platform> platforms) {
        return FeaturedMatches.forPlatforms(platforms);
    }

    public static FeaturedMatches.ManyBuilder featuredMatchesForPlatforms(final Platform... platforms) {
        return FeaturedMatches.forPlatforms(platforms);
    }

    public static FeaturedMatches.Builder featuredMatchesForRegion(final Region region) {
        return FeaturedMatches.forRegion(region);
    }

    public static FeaturedMatches.ManyBuilder featuredMatchesForRegions(final Iterable<Region> regions) {
        return FeaturedMatches.forRegions(regions);
    }

    public static FeaturedMatches.ManyBuilder featuredMatchesForRegions(final Region... regions) {
        return FeaturedMatches.forRegions(regions);
    }

    public static Champions getChampions() {
        return Champions.get();
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

    public static FeaturedMatches getFeaturedMatches() {
        return FeaturedMatches.get();
    }

    public static Items getItems() {
        return Items.get();
    }

    public static Languages getLanguages() {
        return Languages.get();
    }

    public static LanguageStrings getLanguageStrings() {
        return LanguageStrings.get();
    }

    public static Maps getMaps() {
        return Maps.get();
    }

    public static Masteries getMasteries() {
        return Masteries.get();
    }

    public static ProfileIcons getProfileIcons() {
        return ProfileIcons.get();
    }

    public static ReforgedRunes getReforgedRunes() {
        return ReforgedRunes.get();
    }

    public static Runes getRunes() {
        return Runes.get();
    }

    public static Settings getSettings() {
        return settings;
    }

    public static ShardStatus getShardStatus() {
        return ShardStatus.get();
    }

    public static SummonerSpells getSummonerSpells() {
        return SummonerSpells.get();
    }

    public static Versions getVersions() {
        return Versions.get();
    }

    public static Item.Builder itemNamed(final String name) {
        return Item.named(name);
    }

    public static Items.SubsetBuilder itemsNamed(final Iterable<String> names) {
        return Items.named(names);
    }

    public static Items.SubsetBuilder itemsNamed(final String... names) {
        return Items.named(names);
    }

    public static Items.SubsetBuilder itemsWithIds(final int... ids) {
        return Items.withIds(ids);
    }

    public static Items.SubsetBuilder itemsWithIds(final Iterable<Integer> ids) {
        return Items.withIds(ids);
    }

    public static Items.Builder itemsWithIncludedData(final Iterable<String> includedData) {
        return Items.withIncludedData(includedData);
    }

    public static Items.Builder itemsWithIncludedData(final String... includedData) {
        return Items.withIncludedData(includedData);
    }

    public static Items.Builder itemsWithLocale(final String locale) {
        return Items.withLocale(locale);
    }

    public static Items.Builder itemsWithPlatform(final Platform platform) {
        return Items.withPlatform(platform);
    }

    public static Items.Builder itemsWithRegion(final Region region) {
        return Items.withRegion(region);
    }

    public static Items.Builder itemsWithVersion(final String version) {
        return Items.withVersion(version);
    }

    public static Item.Builder itemWithId(final int id) {
        return Item.withId(id);
    }

    public static LanguageStrings.Builder languageStringsWithLocale(final String locale) {
        return LanguageStrings.withLocale(locale);
    }

    public static LanguageStrings.Builder languageStringsWithPlatform(final Platform platform) {
        return LanguageStrings.withPlatform(platform);
    }

    public static LanguageStrings.Builder languageStringsWithRegion(final Region region) {
        return LanguageStrings.withRegion(region);
    }

    public static LanguageStrings.Builder languageStringsWithVersion(final String version) {
        return LanguageStrings.withVersion(version);
    }

    public static Languages.Builder languagesWithPlatform(final Platform platform) {
        return Languages.withPlatform(platform);
    }

    public static Languages.Builder languagesWithRegion(final Region region) {
        return Languages.withRegion(region);
    }

    public static LeaguePositions.Builder leaguePositionsForSummoner(final Summoner summoner) {
        return LeaguePositions.forSummoner(summoner);
    }

    public static LeaguePositions.ManyBuilder leaguePositionsForSummoners(final Iterable<Summoner> summoners) {
        return LeaguePositions.forSummoners(summoners);
    }

    public static LeaguePositions.ManyBuilder leaguePositionsForSummoners(final Summoner... summoners) {
        return LeaguePositions.forSummoners(summoners);
    }

    public static Leagues.Builder leaguesWithIds(final Iterable<String> ids) {
        return Leagues.withIds(ids);
    }

    public static Leagues.Builder leaguesWithIds(final String... ids) {
        return Leagues.withIds(ids);
    }

    public static League.Builder leagueWithId(final String id) {
        return League.withId(id);
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

    public static Map.Builder mapNamed(final String name) {
        return Map.named(name);
    }

    public static Maps.Builder mapsWithLocale(final String locale) {
        return Maps.withLocale(locale);
    }

    public static Maps.Builder mapsWithPlatform(final Platform platform) {
        return Maps.withPlatform(platform);
    }

    public static Maps.Builder mapsWithRegion(final Region region) {
        return Maps.withRegion(region);
    }

    public static Maps.Builder mapsWithVersion(final String version) {
        return Maps.withVersion(version);
    }

    public static Map.Builder mapWithId(final int id) {
        return Map.withId(id);
    }

    public static Masteries.SubsetBuilder masteriesNamed(final Iterable<String> names) {
        return Masteries.named(names);
    }

    public static Masteries.SubsetBuilder masteriesNamed(final String... names) {
        return Masteries.named(names);
    }

    public static Masteries.SubsetBuilder masteriesWithIds(final int... ids) {
        return Masteries.withIds(ids);
    }

    public static Masteries.SubsetBuilder masteriesWithIds(final Iterable<Integer> ids) {
        return Masteries.withIds(ids);
    }

    public static Masteries.Builder masteriesWithIncludedData(final Iterable<String> includedData) {
        return Masteries.withIncludedData(includedData);
    }

    public static Masteries.Builder masteriesWithIncludedData(final String... includedData) {
        return Masteries.withIncludedData(includedData);
    }

    public static Masteries.Builder masteriesWithLocale(final String locale) {
        return Masteries.withLocale(locale);
    }

    public static Masteries.Builder masteriesWithPlatform(final Platform platform) {
        return Masteries.withPlatform(platform);
    }

    public static Masteries.Builder masteriesWithRegion(final Region region) {
        return Masteries.withRegion(region);
    }

    public static Masteries.Builder masteriesWithVersion(final String version) {
        return Masteries.withVersion(version);
    }

    public static League.SelectBuilder.SubBuilder masterLeagueInQueue(final Queue queue) {
        return League.masterInQueue(queue);
    }

    public static Leagues.SelectBuilder.SubBuilder masterLeaguesInQueues(final Iterable<Queue> queues) {
        return Leagues.masterInQueues(queues);
    }

    public static Leagues.SelectBuilder.SubBuilder masterLeaguesInQueues(final Queue... queues) {
        return Leagues.masterInQueues(queues);
    }

    public static Mastery.Builder masteryNamed(final String name) {
        return Mastery.named(name);
    }

    public static Mastery.Builder masteryWithId(final int id) {
        return Mastery.withId(id);
    }

    public static Matches.Builder matchesWithIds(final Iterable<Long> ids) {
        return Matches.withIds(ids);
    }

    public static Matches.Builder matchesWithIds(final long... ids) {
        return Matches.withIds(ids);
    }

    public static MatchHistories.Builder matchHistoriesForSummoners(final Iterable<Summoner> summoners) {
        return MatchHistories.forSummoners(summoners);
    }

    public static MatchHistories.Builder matchHistoriesForSummoners(final Summoner... summoners) {
        return MatchHistories.forSummoners(summoners);
    }

    public static MatchHistory.Builder matchHistoryForSummoner(final Summoner summoner) {
        return MatchHistory.forSummoner(summoner);
    }

    public static Match.Builder matchWithId(final long id) {
        return Match.withId(id);
    }

    public static ProfileIcons.Builder profileIconsWithLocale(final String locale) {
        return ProfileIcons.withLocale(locale);
    }

    public static ProfileIcons.Builder profileIconsWithPlatform(final Platform platform) {
        return ProfileIcons.withPlatform(platform);
    }

    public static ProfileIcons.Builder profileIconsWithRegion(final Region region) {
        return ProfileIcons.withRegion(region);
    }

    public static ProfileIcons.Builder profileIconsWithVersion(final String version) {
        return ProfileIcons.withVersion(version);
    }

    public static ProfileIcon.Builder profileIconWithId(final int id) {
        return ProfileIcon.withId(id);
    }

    public static Realms.Builder realmsWithPlatforms(final Iterable<Platform> platforms) {
        return Realms.withPlatforms(platforms);
    }

    public static Realms.Builder realmsWithPlatforms(final Platform... platforms) {
        return Realms.withPlatforms(platforms);
    }

    public static Realms.Builder realmsWithRegions(final Iterable<Region> regions) {
        return Realms.withRegions(regions);
    }

    public static Realms.Builder realmsWithRegions(final Region... regions) {
        return Realms.withRegions(regions);
    }

    public static Realm.Builder realmWithPlatform(final Platform platform) {
        return Realm.withPlatform(platform);
    }

    public static Realm.Builder realmWithRegion(final Region region) {
        return Realm.withRegion(region);
    }

    public static ReforgedRune.Builder reforgedRuneNamed(final String name) {
        return ReforgedRune.named(name);
    }

    public static ReforgedRunes.SubsetBuilder reforgedRunesNamed(final Iterable<String> names) {
        return ReforgedRunes.named(names);
    }

    public static ReforgedRunes.SubsetBuilder reforgedRunesNamed(final String... names) {
        return ReforgedRunes.named(names);
    }

    public static ReforgedRunes.SubsetBuilder reforgedRunesWithIds(final int... ids) {
        return ReforgedRunes.withIds(ids);
    }

    public static ReforgedRunes.SubsetBuilder reforgedRunesWithIds(final Iterable<Integer> ids) {
        return ReforgedRunes.withIds(ids);
    }

    public static ReforgedRunes.SubsetBuilder reforgedRunesWithKeys(final Iterable<String> keys) {
        return ReforgedRunes.withKeys(keys);
    }

    public static ReforgedRunes.SubsetBuilder reforgedRunesWithKeys(final String... keys) {
        return ReforgedRunes.withKeys(keys);
    }

    public static ReforgedRunes.Builder reforgedRunesWithLocale(final String locale) {
        return ReforgedRunes.withLocale(locale);
    }

    public static ReforgedRunes.Builder reforgedRunesWithPlatform(final Platform platform) {
        return ReforgedRunes.withPlatform(platform);
    }

    public static ReforgedRunes.Builder reforgedRunesWithRegion(final Region region) {
        return ReforgedRunes.withRegion(region);
    }

    public static ReforgedRunes.Builder reforgedRunesWithVersion(final String version) {
        return ReforgedRunes.withVersion(version);
    }

    public static ReforgedRune.Builder reforgedRuneWithId(final int id) {
        return ReforgedRune.withId(id);
    }

    public static ReforgedRune.Builder reforgedRuneWithKey(final String key) {
        return ReforgedRune.withKey(key);
    }

    public static Rune.Builder runeNamed(final String name) {
        return Rune.named(name);
    }

    public static Runes.SubsetBuilder runesNamed(final Iterable<String> names) {
        return Runes.named(names);
    }

    public static Runes.SubsetBuilder runesNamed(final String... names) {
        return Runes.named(names);
    }

    public static Runes.SubsetBuilder runesWithIds(final int... ids) {
        return Runes.withIds(ids);
    }

    public static Runes.SubsetBuilder runesWithIds(final Iterable<Integer> ids) {
        return Runes.withIds(ids);
    }

    public static Runes.Builder runesWithIncludedData(final Iterable<String> includedData) {
        return Runes.withIncludedData(includedData);
    }

    public static Runes.Builder runesWithIncludedData(final String... includedData) {
        return Runes.withIncludedData(includedData);
    }

    public static Runes.Builder runesWithLocale(final String locale) {
        return Runes.withLocale(locale);
    }

    public static Runes.Builder runesWithPlatform(final Platform platform) {
        return Runes.withPlatform(platform);
    }

    public static Runes.Builder runesWithRegion(final Region region) {
        return Runes.withRegion(region);
    }

    public static Runes.Builder runesWithVersion(final String version) {
        return Runes.withVersion(version);
    }

    public static Rune.Builder runeWithId(final int id) {
        return Rune.withId(id);
    }

    /**
     * Sets the default locale. The default locale will be used for locale-aware information from the Static Data API like champion names and descriptions.
     * The default locale starts null, and if it is set to null the default locale for the region/platform the data is from will be used.
     *
     * @param defaultLocale
     *        the default locale
     */
    public static void setDefaultLocale(final String defaultLocale) {
        settings.setDefaultLocale(defaultLocale);
    }

    /**
     * Sets the default platform. If this is not set or is set to null, platforms must be provided for all API queries.
     *
     * @param defaultPlatform
     *        the defaultPlatform
     */
    public static void setDefaultPlatform(final Platform defaultPlatform) {
        settings.setDefaultPlatform(defaultPlatform);
    }

    /**
     * Sets the default region. If this is not set or is set to null, regions must be provided for all API queries.
     *
     * @param defaultRegion
     *        the defaultRegion
     */
    public static void setDefaultRegion(final Region defaultRegion) {
        settings.setDefaultPlatform(defaultRegion.getPlatform());
    }

    /**
     * Sets the Riot API key if {@link com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI} is being used in the pipeline.
     * Also forces a full reset of the pipeline.
     *
     * @param key
     *        the Riot API key to use
     */
    public static void setRiotAPIKey(final String key) {
        settings.setRiotAPIKey(key);
    }

    public static ShardStatuses.Builder shardStatusesWithPlatforms(final Iterable<Platform> platforms) {
        return ShardStatuses.withPlatforms(platforms);
    }

    public static ShardStatuses.Builder shardStatusesWithPlatforms(final Platform... platforms) {
        return ShardStatuses.withPlatforms(platforms);
    }

    public static ShardStatuses.Builder shardStatusesWithRegions(final Iterable<Region> regions) {
        return ShardStatuses.withRegions(regions);
    }

    public static ShardStatuses.Builder shardStatusesWithRegions(final Region... regions) {
        return ShardStatuses.withRegions(regions);
    }

    public static ShardStatus.Builder shardStatusWithPlatform(final Platform platform) {
        return ShardStatus.withPlatform(platform);
    }

    public static ShardStatus.Builder shardStatusWithRegion(final Region region) {
        return ShardStatus.withRegion(region);
    }

    public static Summoner.Builder summonerNamed(final String name) {
        return Summoner.named(name);
    }

    public static Summoners.Builder summonersNamed(final Iterable<String> names) {
        return Summoners.named(names);
    }

    public static Summoners.Builder summonersNamed(final String... names) {
        return Summoners.named(names);
    }

    public static SummonerSpell.Builder summonerSpellNamed(final String name) {
        return SummonerSpell.named(name);
    }

    public static SummonerSpells.SubsetBuilder summonerSpellsNamed(final Iterable<String> names) {
        return SummonerSpells.named(names);
    }

    public static SummonerSpells.SubsetBuilder summonerSpellsNamed(final String... names) {
        return SummonerSpells.named(names);
    }

    public static SummonerSpells.SubsetBuilder summonerSpellsWithIds(final int... ids) {
        return SummonerSpells.withIds(ids);
    }

    public static SummonerSpells.SubsetBuilder summonerSpellsWithIds(final Iterable<Integer> ids) {
        return SummonerSpells.withIds(ids);
    }

    public static SummonerSpells.Builder summonerSpellsWithIncludedData(final Iterable<String> includedData) {
        return SummonerSpells.withIncludedData(includedData);
    }

    public static SummonerSpells.Builder summonerSpellsWithIncludedData(final String... includedData) {
        return SummonerSpells.withIncludedData(includedData);
    }

    public static SummonerSpells.Builder summonerSpellsWithLocale(final String locale) {
        return SummonerSpells.withLocale(locale);
    }

    public static SummonerSpells.Builder summonerSpellsWithPlatform(final Platform platform) {
        return SummonerSpells.withPlatform(platform);
    }

    public static SummonerSpells.Builder summonerSpellsWithRegion(final Region region) {
        return SummonerSpells.withRegion(region);
    }

    public static SummonerSpells.Builder summonerSpellsWithVersion(final String version) {
        return SummonerSpells.withVersion(version);
    }

    public static SummonerSpell.Builder summonerSpellWithId(final int id) {
        return SummonerSpell.withId(id);
    }

    public static Summoners.Builder summonersWithAccountIds(final Iterable<Long> accountIds) {
        return Summoners.withAccountIds(accountIds);
    }

    public static Summoners.Builder summonersWithAccountIds(final long... accountIds) {
        return Summoners.withAccountIds(accountIds);
    }

    public static Summoners.Builder summonersWithIds(final Iterable<Long> ids) {
        return Summoners.withIds(ids);
    }

    public static Summoners.Builder summonersWithIds(final long... ids) {
        return Summoners.withIds(ids);
    }

    public static Summoner.Builder summonerWithAccountId(final long accountId) {
        return Summoner.withAccountId(accountId);
    }

    public static Summoner.Builder summonerWithId(final long id) {
        return Summoner.withId(id);
    }

    public static Timelines.Builder timelinesWithIds(final Iterable<Long> ids) {
        return Timelines.withIds(ids);
    }

    public static Timelines.Builder timelinesWithIds(final long... ids) {
        return Timelines.withIds(ids);
    }

    public static Timeline.Builder timelineWithId(final long id) {
        return Timeline.withId(id);
    }

    public static TournamentMatches.Builder tournamentMatchesForTournamentCode(final String tournamentCode) {
        return TournamentMatches.forTournamentCode(tournamentCode);
    }

    public static TournamentMatches.ManyBuilder tournamentMatchesForTournamentCodes(final Iterable<String> tournamentCodes) {
        return TournamentMatches.forTournamentCodes(tournamentCodes);
    }

    public static TournamentMatches.ManyBuilder tournamentMatchesForTournamentCodes(final String... tournamentCodes) {
        return TournamentMatches.forTournamentCodes(tournamentCodes);
    }

    public static VerificationString.Builder verificationStringForSummoner(final Summoner summoner) {
        return VerificationString.forSummoner(summoner);
    }

    public static VerificationStrings.Builder verificationStringsForSummoners(final Iterable<Summoner> summoners) {
        return VerificationStrings.forSummoners(summoners);
    }

    public static VerificationStrings.Builder verificationStringsForSummoners(final Summoner... summoners) {
        return VerificationStrings.forSummoners(summoners);
    }

    public static Versions.Builder versionsWithPlatform(final Platform platform) {
        return Versions.withPlatform(platform);
    }

    public static Versions.Builder versionsWithRegion(final Region region) {
        return Versions.withRegion(region);
    }
}
