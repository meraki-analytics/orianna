.. _orianna-api:

***************************
Using the Orianna Class API
***************************

See the `JavaDocs for the Orianna Class <http://javadoc.io/page/com.merakianalytics.orianna/orianna/latest/com/merakianalytics/orianna/Orianna.html>`__ for the full details of the Orianna Class API.
Examples of getting the available API data are shown below.

Champion Mastery API
====================

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.Orianna;
    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
    import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
    import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScore;
    import com.merakianalytics.orianna.types.core.staticdata.Champion;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;

    public class Example {
        public static void main(String[] args) {
            List<Summoner> summoners = Orianna.summonersNamed("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();
            List<Champion> champions = Orianna.championsNamed("Annie", "Thresh").withRegion(Region.NORTH_AMERICA).get();

            ChampionMasteries masteries = Orianna.championMasteriesForSummoner(summoners.get(0)).get();
            ChampionMastery mastery = Orianna.championMasteryForSummoner(summoners.get(0)).withChampion(champions.get(0)).get();
            ChampionMasteryScore score = Orianna.championMasteryScoreForSummoner(summoners.get(0)).get();

            List<ChampionMasteries> manyMasteries = Orianna.championMasteriesForSummoners(summoners).get();
            List<ChampionMastery> manyMastery = Orianna.championMasteriesForSummoner(summoners.get(0)).withChampions(champions).get();
            List<ChampionMasteryScore> manyScores = Orianna.championMasteryScoresForSummoners(summoners).get();
        }
    }

Champion Status API
===================

Champion Status information is avilable directly on the Champion within Orianna.

.. code-block:: java

    import com.merakianalytics.orianna.Orianna;
    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.staticdata.Champions;

    public class Example {
        public static void main(String[] args) {
            Champions champions = Orianna.championsWithRegion(Region.NORTH_AMERICA).get();

            boolean firstFree = champions.get(0).isFreeToPlay();
            boolean lastEnabled = champions.get(champions.size() - 1).isEnabled();
        }
    }

League API
==========

.. code-block:: java

    import java.util.List;
    import java.util.stream.Collectors;

    import com.merakianalytics.orianna.Orianna;
    import com.merakianalytics.orianna.types.common.Queue;
    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.league.League;
    import com.merakianalytics.orianna.types.core.league.LeaguePosition;
    import com.merakianalytics.orianna.types.core.league.LeaguePositions;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;

    public class Example {
        public static void main(String[] args) {
            League challenger = Orianna.challengerLeagueInQueue(Queue.RANKED_SOLO_5x5).withRegion(Region.NORTH_AMERICA).get();
            League master = Orianna.masterLeagueInQueue(Queue.RANKED_SOLO_5x5).withRegion(Region.NORTH_AMERICA).get();

            List<League> allChallenger = Orianna.challengerLeaguesInQueues(Queue.RANKED).withRegion(Region.NORTH_AMERICA).get();
            List<League> allMaster = Orianna.masterLeaguesInQueues(Queue.RANKED).withRegion(Region.NORTH_AMERICA).get();

            List<Summoner> summoners = Orianna.summonersNamed("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();

            LeaguePositions positions = Orianna.leaguePositionsForSummoner(summoners.get(1)).get();
            List<LeaguePositions> manyPositions = Orianna.leaguePositionsForSummoners(summoners).get();

            League league = positions.get(0).getLeague();
            List<League> manyLeagues = positions.stream().map(LeaguePosition::getLeague).collect(Collectors.toList());

            String leagueId = league.getId();
            List<String> manyLeagueIds = manyLeagues.stream().map(League::getId).collect(Collectors.toList());

            league = Orianna.leagueWithId(leagueId).withRegion(Region.NORTH_AMERICA).get();
            manyLeagues = Orianna.leaguesWithIds(manyLeagueIds).withRegion(Region.NORTH_AMERICA).get();
        }
    }

Static Data API
===============

.. code-block:: java

    import com.merakianalytics.orianna.Orianna;
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
            Champions champions = Orianna.championsWithRegion(Region.NORTH_AMERICA).get();

            Champion champion = Orianna.championNamed("Annie").withRegion(Region.NORTH_AMERICA).get();
            champion = Orianna.championWithId(1).withRegion(Region.NORTH_AMERICA).get();

            // Items
            Items items = Orianna.itemsWithRegion(Region.NORTH_AMERICA).get();

            Item item = Orianna.itemNamed("Infinity Edge").withRegion(Region.NORTH_AMERICA).get();
            item = Orianna.itemWithId(3031).withRegion(Region.NORTH_AMERICA).get();

            // Language Strings
            LanguageStrings languageStrings = Orianna.languageStringsWithRegion(Region.NORTH_AMERICA).get();

            // Languages
            Languages languages = Orianna.languagesWithRegion(Region.NORTH_AMERICA).get();

            // Maps
            Maps maps = Orianna.mapsWithRegion(Region.NORTH_AMERICA).get();

            Map map = Orianna.mapNamed("Howling Abyss").withRegion(Region.NORTH_AMERICA).get();
            map = Orianna.mapWithId(12).withRegion(Region.NORTH_AMERICA).get();

            // Masteries
            Masteries masteries = Orianna.masteriesWithRegion(Region.NORTH_AMERICA).get();

            Mastery mastery = Orianna.masteryNamed("Warlord's Bloodlust").withVersion("7.23.1").withRegion(Region.NORTH_AMERICA).get();
            mastery = Orianna.masteryWithId(6161).withVersion("7.23.1").withRegion(Region.NORTH_AMERICA).get();

            // Profile Icons
            ProfileIcons profileIcons = Orianna.profileIconsWithRegion(Region.NORTH_AMERICA).get();

            ProfileIcon profileIcon = Orianna.profileIconWithId(4).withRegion(Region.NORTH_AMERICA).get();

            // Realms
            Realm realm = Orianna.realmWithRegion(Region.NORTH_AMERICA).get();

            // Reforged Runes
            ReforgedRunes reforgedRunes = Orianna.reforgedRunesWithRegion(Region.NORTH_AMERICA).get();

            ReforgedRune reforgedRune = Orianna.reforgedRuneNamed("Electrocute").withRegion(Region.NORTH_AMERICA).get();
            reforgedRune = Orianna.reforgedRuneWithId(8112).withRegion(Region.NORTH_AMERICA).get();

            // Runes
            Runes runes = Orianna.runesWithRegion(Region.NORTH_AMERICA).get();

            Rune rune = Orianna.runeNamed("Greater Quintessence of Attack Speed").withVersion("7.23.1").withRegion(Region.NORTH_AMERICA).get();
            rune = Orianna.runeWithId(5337).withVersion("7.23.1").withRegion(Region.NORTH_AMERICA).get();

            // Summoner Spells
            SummonerSpells summonerSpells = Orianna.summonerSpellsWithRegion(Region.NORTH_AMERICA).get();

            SummonerSpell summonerSpell = Orianna.summonerSpellNamed("Flash").withRegion(Region.NORTH_AMERICA).get();
            summonerSpell = Orianna.summonerSpellWithId(4).withRegion(Region.NORTH_AMERICA).get();

            // Versions
            Versions versions = Orianna.versionsWithRegion(Region.NORTH_AMERICA).get();
        }
    }

Status API
==========

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.Orianna;
    import com.merakianalytics.orianna.types.common.Platform;
    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.status.ShardStatus;

    public class Example {
        public static void main(String[] args) {
            ShardStatus status = Orianna.shardStatusForRegion(Region.NORTH_AMERICA).get();
            List<ShardStatus> statuses = Orianna.shardStatusesForPlatforms(Platform.NORTH_AMERICA, Platform.EUROPE_WEST).get();
        }
    }

Match API
=========

Note that when using ``MatchHistory`` in the Match API, Orianna loads your paginated MatchList from the Riot API as needed automatically in the background, allowing ``MatchHistory`` to give you access to your entire Match History with one request.

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.Orianna;
    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.match.Match;
    import com.merakianalytics.orianna.types.core.match.MatchHistory;
    import com.merakianalytics.orianna.types.core.match.Timeline;
    import com.merakianalytics.orianna.types.core.match.TournamentMatches;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;

    public class Example {
        public static void main(String[] args) {
            TournamentMatches tournamentMatches = Orianna.tournamentMatchesForTournamentCode("YOUR-TOURNAMENT-CODE").withRegion(Region.NORTH_AMERICA).get();
            List<TournamentMatches> manyTournamentMatches = Orianna.tournamentMatchesForTournamentCodes("TOURNAMENT-CODE-ONE", "TOURNAMENT-CODE-TWO").withRegion(Region.NORTH_AMERICA).get();

            Match match = Orianna.matchWithId(2718292415L).withRegion(Region.NORTH_AMERICA).get();
            List<Match> matches = Orianna.matchesWithIds(2718292415L, 2718244702L).withRegion(Region.NORTH_AMERICA).get();

            List<Summoner> summoners = Orianna.summonersNamed("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();

            MatchHistory history = Orianna.matchHistoryForSummoner(summoners.get(0)).get();
            List<MatchHistory> histories = Orianna.matchHistoriesForSummoners(summoners).get();

            MatchHistory recentHistory = Orianna.matchHistoryForSummoner(summoners.get(0)).fromRecentMatches().get();
            List<MatchHistory> recentHistories = Orianna.matchHistoriesForSummoners(summoners).fromRecentMatches().get();

            Timeline timeline = Orianna.timelineWithId(2718292415L).withRegion(Region.NORTH_AMERICA).get();
            List<Timeline> timelines = Orianna.timelinesWithIds(2718292415L, 2718244702L).withRegion(Region.NORTH_AMERICA).get();
        }
    }

Spectator API
=============

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.Orianna;
    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
    import com.merakianalytics.orianna.types.core.spectator.FeaturedMatches;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;

    public class Example {
        public static void main(String[] args) {
            List<Summoner> summoners = Orianna.summonersNamed("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();

            CurrentMatch match = Orianna.currentMatchForSummoner(summoners.get(0)).get();
            boolean inGame = match.exists();

            List<CurrentMatch> matches = Orianna.currentMatchesForSummoners(summoners).get();

            FeaturedMatches featuredMatches = Orianna.featuredMatchesForRegion(Region.NORTH_AMERICA).get();
            List<FeaturedMatches> manyFeaturedMatches = Orianna.featuredMatchesForRegions(Region.NORTH_AMERICA, Region.EUROPE_WEST).get();
        }
    }

Summoner API
============

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.Orianna;
    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;

    public class Example {
        public static void main(String[] args) {
            Summoner summoner = Orianna.summonerNamed("FatalElement").withRegion(Region.NORTH_AMERICA).get();
            summoner = Orianna.summonerWithId(22508641L).withRegion(Region.NORTH_AMERICA).get();
            summoner = Orianna.summonerWithAccountId(36321079L).withRegion(Region.NORTH_AMERICA).get();

            List<Summoner> summoners = Orianna.summonersNamed("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();
            summoners = Orianna.summonersWithIds(22508641L, 21359666L).withRegion(Region.NORTH_AMERICA).get();
            summoners = Orianna.summonersWithAccountIds(36321079L, 34718348L).withRegion(Region.NORTH_AMERICA).get();
        }
    }

Third Party Code API
====================

.. code-block:: java

    import java.util.List;

    import com.merakianalytics.orianna.Orianna;
    import com.merakianalytics.orianna.types.common.Region;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;
    import com.merakianalytics.orianna.types.core.thirdpartycode.VerificationString;

    public class Example {
        public static void main(String[] args) {
            List<Summoner> summoners = Orianna.summonersNamed("FatalElement", "Kalturi").withRegion(Region.NORTH_AMERICA).get();

            VerificationString verificationString = Orianna.verificationStringForSummoner(summoners.get(0)).get();
            List<VerificationString> verificationStrings = Orianna.verificationStringsForSummoners(summoners).get();
        }
    }
