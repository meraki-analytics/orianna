.. _type-api:

************************
Using the Data Class API
************************

Champion Mastery API
====================

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
    import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
    import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScore;
    import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScores;
    import com.merakianalytics.orianna.types.core.staticdata.Champion;
    import com.merakianalytics.orianna.types.core.staticdata.Champions;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;
    import com.merakianalytics.orianna.types.core.summoner.Summoners;

    public class Example {
        public static void main(String[] args) {
            List<Summoner> summoners = Summoners.named("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();
            List<Champion> champions = Champions.named("Annie", "Thresh").withRegion(Region.NORTH_AMERICA).get();

            ChampionMasteries masteries = ChampionMasteries.forSummoner(summoners.get(0)).get();
            ChampionMastery mastery = ChampionMastery.forSummoner(summoners.get(0)).withChampion(champions.get(0)).get();
            ChampionMasteryScore score = ChampionMasteryScore.forSummoner(summoners.get(0)).get();

            List<ChampionMasteries> manyMasteries = ChampionMasteries.forSummoners(summoners).get();
            List<ChampionMastery> manyMastery = ChampionMasteries.forSummoner(summoners.get(0)).withChampions(champions).get();
            List<ChampionMasteryScore> manyScores = ChampionMasteryScores.forSummoners(summoners).get();
        }
    }

Champion Status API
===================

Champion Status information is avilable directly on the Champion within Orianna.

.. code-block:: java

    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.staticdata.Champions;

    public class Example {
        public static void main(String[] args) {
            Champions champions = Champions.withRegion(Region.NORTH_AMERICA).get();

            boolean firstFree = champions.get(0).isFreeToPlay();
            boolean lastEnabled = champions.get(champions.size() - 1).isEnabled();
        }
    }

League API
==========

.. code-block:: java

    import java.util.List;
    import java.util.stream.Collectors;

    import com.merakianalytics.orianna.types.common.Queue;
    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.league.League;
    import com.merakianalytics.orianna.types.core.league.LeaguePosition;
    import com.merakianalytics.orianna.types.core.league.LeaguePositions;
    import com.merakianalytics.orianna.types.core.league.Leagues;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;
    import com.merakianalytics.orianna.types.core.summoner.Summoners;

    public class Example {
        public static void main(String[] args) {
            League challenger = League.challengerInQueue(Queue.RANKED_SOLO_5x5).withRegion(Region.NORTH_AMERICA).get();
            League master = League.masterInQueue(Queue.RANKED_SOLO_5x5).withRegion(Region.NORTH_AMERICA).get();

            List<League> allChallenger = Leagues.challengerInQueues(Queue.RANKED).withRegion(Region.NORTH_AMERICA).get();
            List<League> allMaster = Leagues.masterInQueues(Queue.RANKED).withRegion(Region.NORTH_AMERICA).get();

            List<Summoner> summoners = Summoners.named("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();

            LeaguePositions positions = LeaguePositions.forSummoner(summoners.get(1)).get();
            List<LeaguePositions> manyPositions = LeaguePositions.forSummoners(summoners).get();

            League league = positions.get(0).getLeague();
            List<League> manyLeagues = positions.stream().map(LeaguePosition::getLeague).collect(Collectors.toList());

            String leagueId = league.getId();
            List<String> manyLeagueIds = manyLeagues.stream().map(League::getId).collect(Collectors.toList());

            league = League.withId(leagueId).withRegion(Region.NORTH_AMERICA).get();
            manyLeagues = Leagues.withIds(manyLeagueIds).withRegion(Region.NORTH_AMERICA).get();
        }
    }

Static Data API
===============

.. code-block:: java

    import com.merakianalytics.orianna.types.common.Region;
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
    import com.merakianalytics.orianna.types.core.staticdata.ReforgedRune;
    import com.merakianalytics.orianna.types.core.staticdata.ReforgedRunes;
    import com.merakianalytics.orianna.types.core.staticdata.Rune;
    import com.merakianalytics.orianna.types.core.staticdata.Runes;
    import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
    import com.merakianalytics.orianna.types.core.staticdata.SummonerSpells;
    import com.merakianalytics.orianna.types.core.staticdata.Versions;

    public class Example {
        public static void main(String[] args) {
            // Champions
            Champions champions = Champions.withRegion(Region.NORTH_AMERICA).get();

            Champion champion = Champion.named("Annie").withRegion(Region.NORTH_AMERICA).get();
            champion = Champion.withId(1).withRegion(Region.NORTH_AMERICA).get();

            // Items
            Items items = Items.withRegion(Region.NORTH_AMERICA).get();

            Item item = Item.named("Infinity Edge").withRegion(Region.NORTH_AMERICA).get();
            item = Item.withId(3031).withRegion(Region.NORTH_AMERICA).get();

            // Language Strings
            LanguageStrings languageStrings = LanguageStrings.withRegion(Region.NORTH_AMERICA).get();

            // Languages
            Languages languages = Languages.withRegion(Region.NORTH_AMERICA).get();

            // Maps
            Maps maps = Maps.withRegion(Region.NORTH_AMERICA).get();

            Map map = Map.named("Howling Abyss").withRegion(Region.NORTH_AMERICA).get();
            map = Map.withId(12).withRegion(Region.NORTH_AMERICA).get();

            // Masteries
            Masteries masteries = Masteries.withRegion(Region.NORTH_AMERICA).get();

            Mastery mastery = Mastery.named("Warlord's Bloodlust").withVersion("7.23.1").withRegion(Region.NORTH_AMERICA).get();
            mastery = Mastery.withId(6161).withVersion("7.23.1").withRegion(Region.NORTH_AMERICA).get();

            // Profile Icons
            ProfileIcons profileIcons = ProfileIcons.withRegion(Region.NORTH_AMERICA).get();

            ProfileIcon profileIcon = ProfileIcon.withId(4).withRegion(Region.NORTH_AMERICA).get();

            // Realms
            Realm realm = Realm.withRegion(Region.NORTH_AMERICA).get();

            // Reforged Runes
            ReforgedRunes reforgedRunes = ReforgedRunes.withRegion(Region.NORTH_AMERICA).get();

            ReforgedRune reforgedRune = ReforgedRune.named("Electrocute").withRegion(Region.NORTH_AMERICA).get();
            reforgedRune = ReforgedRune.withId(8112).withRegion(Region.NORTH_AMERICA).get();

            // Runes
            Runes runes = Runes.withRegion(Region.NORTH_AMERICA).get();

            Rune rune = Rune.named("Greater Quintessence of Attack Speed").withVersion("7.23.1").withRegion(Region.NORTH_AMERICA).get();
            rune = Rune.withId(5337).withVersion("7.23.1").withRegion(Region.NORTH_AMERICA).get();

            // Summoner Spells
            SummonerSpells summonerSpells = SummonerSpells.withRegion(Region.NORTH_AMERICA).get();

            SummonerSpell summonerSpell = SummonerSpell.named("Flash").withRegion(Region.NORTH_AMERICA).get();
            summonerSpell = SummonerSpell.withId(4).withRegion(Region.NORTH_AMERICA).get();

            // Versions
            Versions versions = Versions.withRegion(Region.NORTH_AMERICA).get();
        }
    }

Status API
==========

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.types.common.Platform;
    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.status.ShardStatus;
    import com.merakianalytics.orianna.types.core.status.ShardStatuses;

    public class Example {
        public static void main(String[] args) {
            ShardStatus status = ShardStatus.forRegion(Region.NORTH_AMERICA).get();
            List<ShardStatus> statuses = ShardStatuses.forPlatforms(Platform.NORTH_AMERICA, Platform.EUROPE_WEST).get();
        }
    }

Match API
=========

Note that when using ``MatchHistory`` in the Match API, Orianna loads your paginated MatchList from the Riot API as needed automatically in the background, allowing ``MatchHistory`` to give you access to your entire Match History with one request.

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.match.Match;
    import com.merakianalytics.orianna.types.core.match.MatchHistories;
    import com.merakianalytics.orianna.types.core.match.MatchHistory;
    import com.merakianalytics.orianna.types.core.match.Matches;
    import com.merakianalytics.orianna.types.core.match.Timeline;
    import com.merakianalytics.orianna.types.core.match.Timelines;
    import com.merakianalytics.orianna.types.core.match.TournamentMatches;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;
    import com.merakianalytics.orianna.types.core.summoner.Summoners;

    public class Example {
        public static void main(String[] args) {
            TournamentMatches tournamentMatches = TournamentMatches.forTournamentCode("YOUR-TOURNAMENT-CODE").withRegion(Region.NORTH_AMERICA).get();
            List<TournamentMatches> manyTournamentMatches = TournamentMatches.forTournamentCodes("TOURNAMENT-CODE-ONE", "TOURNAMENT-CODE-TWO").withRegion(Region.NORTH_AMERICA).get();

            Match match = Match.withId(2718292415L).withRegion(Region.NORTH_AMERICA).get();
            List<Match> matches = Matches.withIds(2718292415L, 2718244702L).withRegion(Region.NORTH_AMERICA).get();

            List<Summoner> summoners = Summoners.named("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();

            MatchHistory history = MatchHistory.forSummoner(summoners.get(0)).get();
            List<MatchHistory> histories = MatchHistories.forSummoners(summoners).get();

            MatchHistory recentHistory = MatchHistory.forSummoner(summoners.get(0)).fromRecentMatches().get();
            List<MatchHistory> recentHistories = MatchHistories.forSummoners(summoners).fromRecentMatches().get();

            Timeline timeline = Timeline.withId(2718292415L).withRegion(Region.NORTH_AMERICA).get();
            List<Timeline> timelines = Timelines.withIds(2718292415L, 2718244702L).withRegion(Region.NORTH_AMERICA).get();
        }
    }

Spectator API
=============

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
    import com.merakianalytics.orianna.types.core.spectator.CurrentMatches;
    import com.merakianalytics.orianna.types.core.spectator.FeaturedMatches;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;
    import com.merakianalytics.orianna.types.core.summoner.Summoners;

    public class Example {
        public static void main(String[] args) {
            List<Summoner> summoners = Summoners.named("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();

            CurrentMatch match = CurrentMatch.forSummoner(summoners.get(0)).get();
            boolean inGame = match.exists();

            List<CurrentMatch> matches = CurrentMatches.forSummoners(summoners).get();

            FeaturedMatches featuredMatches = FeaturedMatches.forRegion(Region.NORTH_AMERICA).get();
            List<FeaturedMatches> manyFeaturedMatches = FeaturedMatches.forRegions(Region.NORTH_AMERICA, Region.EUROPE_WEST).get();
        }
    }

Summoner API
============

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;
    import com.merakianalytics.orianna.types.core.summoner.Summoners;

    public class Example {
        public static void main(String[] args) {
            Summoner summoner = Summoner.named("FatalElement").withRegion(Region.NORTH_AMERICA).get();
            summoner = Summoner.withId(22508641L).withRegion(Region.NORTH_AMERICA).get();
            summoner = Summoner.withAccountId(36321079L).withRegion(Region.NORTH_AMERICA).get();

            List<Summoner> summoners = Summoners.named("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();
            summoners = Summoners.withIds(22508641L, 21359666L).withRegion(Region.NORTH_AMERICA).get();
            summoners = Summoners.withAccountIds(36321079L, 34718348L).withRegion(Region.NORTH_AMERICA).get();
        }
    }

Third Party Code API
====================

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;
    import com.merakianalytics.orianna.types.core.summoner.Summoners;
    import com.merakianalytics.orianna.types.core.thirdpartycode.VerificationString;
    import com.merakianalytics.orianna.types.core.thirdpartycode.VerificationStrings;

    public class Example {
        public static void main(String[] args) {
            List<Summoner> summoners = Summoners.named("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();

            VerificationString verificationString = VerificationString.forSummoner(summoners.get(0)).get();
            List<VerificationString> verificationStrings = VerificationStrings.forSummoners(summoners).get();
        }
    }
