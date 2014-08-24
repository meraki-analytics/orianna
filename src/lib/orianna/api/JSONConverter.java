package lib.orianna.api;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lib.orianna.api.queryspecs.Region;
import lib.orianna.api.queryspecs.Season;
import lib.orianna.type.champion.ChampionStatus;
import lib.orianna.type.game.Game;
import lib.orianna.type.game.GameMode;
import lib.orianna.type.game.GamePlayer;
import lib.orianna.type.game.GameType;
import lib.orianna.type.game.RawStats;
import lib.orianna.type.game.SubType;
import lib.orianna.type.league.League;
import lib.orianna.type.league.LeagueEntry;
import lib.orianna.type.league.LeagueType;
import lib.orianna.type.league.MiniSeries;
import lib.orianna.type.league.Tier;
import lib.orianna.type.match.BannedChampion;
import lib.orianna.type.match.BuildingType;
import lib.orianna.type.match.Event;
import lib.orianna.type.match.EventType;
import lib.orianna.type.match.Frame;
import lib.orianna.type.match.Lane;
import lib.orianna.type.match.LaneType;
import lib.orianna.type.match.MatchMap;
import lib.orianna.type.match.MatchSummary;
import lib.orianna.type.match.MatchTeam;
import lib.orianna.type.match.MatchTimeline;
import lib.orianna.type.match.MonsterType;
import lib.orianna.type.match.Participant;
import lib.orianna.type.match.ParticipantFrame;
import lib.orianna.type.match.ParticipantStats;
import lib.orianna.type.match.ParticipantTimeline;
import lib.orianna.type.match.ParticipantTimelineData;
import lib.orianna.type.match.Player;
import lib.orianna.type.match.Position;
import lib.orianna.type.match.QueueType;
import lib.orianna.type.match.Role;
import lib.orianna.type.match.Side;
import lib.orianna.type.match.TowerType;
import lib.orianna.type.match.WardType;
import lib.orianna.type.staticdata.BasicDataStats;
import lib.orianna.type.staticdata.Block;
import lib.orianna.type.staticdata.BlockItem;
import lib.orianna.type.staticdata.Champion;
import lib.orianna.type.staticdata.ChampionSpell;
import lib.orianna.type.staticdata.Gold;
import lib.orianna.type.staticdata.Image;
import lib.orianna.type.staticdata.Information;
import lib.orianna.type.staticdata.Item;
import lib.orianna.type.staticdata.LevelTip;
import lib.orianna.type.staticdata.Mastery;
import lib.orianna.type.staticdata.MasteryTree;
import lib.orianna.type.staticdata.MasteryTreeItem;
import lib.orianna.type.staticdata.MasteryTreeList;
import lib.orianna.type.staticdata.MetaData;
import lib.orianna.type.staticdata.Passive;
import lib.orianna.type.staticdata.Realm;
import lib.orianna.type.staticdata.Recommended;
import lib.orianna.type.staticdata.Rune;
import lib.orianna.type.staticdata.RuneType;
import lib.orianna.type.staticdata.Skin;
import lib.orianna.type.staticdata.SpellVariables;
import lib.orianna.type.staticdata.Stats;
import lib.orianna.type.staticdata.SummonerSpell;
import lib.orianna.type.stats.AggregatedStats;
import lib.orianna.type.stats.ChampionStats;
import lib.orianna.type.stats.PlayerStatsSummary;
import lib.orianna.type.stats.PlayerStatsSummaryType;
import lib.orianna.type.summoner.MasteryPage;
import lib.orianna.type.summoner.MasterySlot;
import lib.orianna.type.summoner.RunePage;
import lib.orianna.type.summoner.RuneSlot;
import lib.orianna.type.summoner.Summoner;
import lib.orianna.type.team.MatchHistorySummary;
import lib.orianna.type.team.Roster;
import lib.orianna.type.team.Team;
import lib.orianna.type.team.TeamMemberInformation;
import lib.orianna.type.team.TeamStatDetail;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Used to convert the responses from the JSONRiotAPI to the Orianna typesystem.
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class JSONConverter {
    private static Integer convertInteger(final Object object) {
        final Long longVersion = (Long)object;
        if(longVersion == null) {
            return null;
        }
        return longVersion.intValue();
    }

    private static LocalDateTime getDateTime(final JSONObject object, final String key) {
        final Long epoch = (Long)object.get(key);
        if(epoch == null) {
            return null;
        }
        return LocalDateTime.ofEpochSecond(epoch / 1000, 0, ZoneOffset.UTC);
    }

    private static List<Double> getDoubleList(final JSONArray list) {
        return getList(list, d -> (Double)d);
    }

    private static List<Double> getDoubleList(final JSONObject object, final String key) {
        return getList(object, key, d -> (Double)d);
    }

    private static Duration getDuration(final JSONObject object, final String key, final TemporalUnit unit) {
        final Long amt = (Long)object.get(key);
        if(amt == null) {
            return null;
        }
        return Duration.of(amt, unit);
    }

    private static Integer getInteger(final JSONObject object, final String key) {
        return convertInteger(object.get(key));
    }

    private static List<Integer> getIntegerList(final JSONArray list) {
        return getList(list, JSONConverter::convertInteger);
    }

    private static List<Integer> getIntegerList(final JSONObject object, final String key) {
        return getList(object, key, JSONConverter::convertInteger);
    }

    protected static <T> List<T> getList(final JSONArray list, final Function<Object, T> mapper) {
        return getList(list, mapper, null);
    }

    protected static <T> List<T> getList(final JSONArray list, final Function<Object, T> mapper, final Comparator<? super T> sorter) {
        final List<T> result = new ArrayList<T>(list.size());
        for(final Object obj : list) {
            final T item = mapper.apply(obj);
            result.add(item);
        }

        if(sorter != null) {
            result.sort(sorter);
        }

        return Collections.unmodifiableList(result);
    }

    protected static <T> List<T> getList(final JSONObject object, final String key, final Function<Object, T> mapper) {
        return getList(object, key, mapper, null);
    }

    protected static <T> List<T> getList(final JSONObject object, final String key, final Function<Object, T> mapper, final Comparator<? super T> sorter) {
        final JSONArray list = (JSONArray)object.get(key);
        if(list == null) {
            return null;
        }

        return getList(list, mapper, sorter);
    }

    protected static <T> List<T> getListFromMap(final JSONObject map, final Function<Object, T> mapper) {
        return getListFromMap(map, mapper, null);
    }

    protected static <T> List<T> getListFromMap(final JSONObject map, final Function<Object, T> mapper, final Comparator<? super T> sorter) {
        final List<T> result = new ArrayList<T>(map.size());
        for(final Object obj : map.values()) {
            final T item = mapper.apply(obj);
            result.add(item);
        }

        if(sorter != null) {
            result.sort(sorter);
        }

        return Collections.unmodifiableList(result);
    }

    protected static <T> List<T> getListFromMap(final JSONObject object, final String key, final Function<Object, T> mapper) {
        return getListFromMap(object, key, mapper, null);
    }

    protected static <T> List<T> getListFromMap(final JSONObject object, final String key, final Function<Object, T> mapper, final Comparator<? super T> sorter) {
        final JSONObject map = (JSONObject)object.get(key);
        if(map == null) {
            return null;
        }

        return getListFromMap(map, mapper, sorter);
    }

    private static MatchMap getMap(final Integer ID) {
        switch(ID) {
            case 1:
                return MatchMap.SUMMONERS_RIFT_SUMMER;
            case 2:
                return MatchMap.SUMMONERS_RIFT_AUTUMN;
            case 3:
                return MatchMap.PROVING_GROUNDS;
            case 4:
                return MatchMap.TWISTED_TREELINE_ORIGINAL;
            case 8:
                return MatchMap.CRYSTAL_SCAR;
            case 10:
                return MatchMap.TWISTED_TREELINE;
            case 12:
                return MatchMap.HOWLING_ABYSS;
            default:
                return null;
        }
    }

    protected static <T> Map<Object, T> getMapFromList(final JSONArray list, final Function<Object, T> mapper) {
        final Map<Object, T> result = new HashMap<Object, T>(list.size());
        for(final Object obj : list) {
            final T item = mapper.apply(obj);
            result.put(obj, item);
        }

        return Collections.unmodifiableMap(result);
    }

    protected static <T> Map<Object, T> getMapFromList(final JSONObject object, final String key, final Function<Object, T> mapper) {
        final JSONArray list = (JSONArray)object.get(key);
        if(list == null) {
            return null;
        }

        return getMapFromList(list, mapper);
    }

    private static Map<String, Pattern> getScrapedStatPatterns() {
        final Map<String, Pattern> patterns = new HashMap<String, Pattern>();
        patterns.put("PercentCooldownMod", Pattern.compile("\\+(\\d+) *% Cooldown Reduction *(<br>|</stats>|</passive>|$)"));
        patterns.put("FlatArmorPenetrationMod", Pattern.compile("\\+(\\d+) *Armor Penetration *(<br>|</stats>|</passive>|$)"));
        patterns.put("PercentArmorPenetrationMod", Pattern.compile("ignores (\\d+)% of the target's Armor"));
        patterns.put("FlatMagicPenetrationMod", Pattern.compile("\\+(\\d+) *Magic Penetration *(<br>|</stats>|</passive>|$)"));
        patterns.put("PercentMagicPenetrationMod", Pattern.compile("ignores (\\d+)% of the target's Magic Resist"));
        patterns.put("FlatGoldPer10Mod", Pattern.compile("\\+(\\d+) *Gold per 10 seconds *(<br>|</stats>|</passive>|$)"));

        return patterns;
    }

    private static Side getSide(final JSONObject object, final String key) {
        final Integer teamId = getInteger(object, key);
        if(teamId == null) {
            return null;
        }
        else if(teamId == 100) {
            return Side.BLUE;
        }
        else if(teamId == 200) {
            return Side.PURPLE;
        }

        return null;
    }

    private static List<String> getStringList(final JSONObject object, final String key) {
        return getList(object, key, s -> (String)s);
    }

    private final RiotAPI API;

    /**
     * @param API
     *            a RiotAPI which will be used to replace foreign key ID values
     *            with the specified object
     */
    public JSONConverter(final RiotAPI API) {
        this.API = API;
    }

    public AggregatedStats getAggregatedStatsFromJSON(final JSONObject aggregatedStatsInfo) {
        if(aggregatedStatsInfo == null) {
            return null;
        }

        final Integer averageAssists = getInteger(aggregatedStatsInfo, "averageAssists");
        final Integer averageChampionsKilled = getInteger(aggregatedStatsInfo, "averageChampionsKilled");
        final Integer averageCombatPlayerScore = getInteger(aggregatedStatsInfo, "averageCombatPlayerScore");
        final Integer averageNodeCapture = getInteger(aggregatedStatsInfo, "averageNodeCapture");
        final Integer averageNodeCaptureAssist = getInteger(aggregatedStatsInfo, "averageNodeCaptureAssist");
        final Integer averageNodeNeutralize = getInteger(aggregatedStatsInfo, "averageNodeNeutralize");
        final Integer averageNodeNeutralizeAssist = getInteger(aggregatedStatsInfo, "averageNodeNeutralizeAssist");
        final Integer averageNumDeaths = getInteger(aggregatedStatsInfo, "averageNumDeaths");
        final Integer averageObjectivePlayerScore = getInteger(aggregatedStatsInfo, "averageObjectivePlayerScore");
        final Integer averageTeamObjective = getInteger(aggregatedStatsInfo, "averageTeamObjective");
        final Integer averageTotalPlayerScore = getInteger(aggregatedStatsInfo, "averageTotalPlayerScore");
        final Integer botGamesPlayed = getInteger(aggregatedStatsInfo, "botGamesPlayed");
        final Integer killingSpree = getInteger(aggregatedStatsInfo, "killingSpree");
        final Integer maxAssists = getInteger(aggregatedStatsInfo, "maxAssists");
        final Integer maxChampionsKilled = getInteger(aggregatedStatsInfo, "maxChampionsKilled");
        final Integer maxCombatPlayerScore = getInteger(aggregatedStatsInfo, "maxCombatPlayerScore");
        final Integer maxLargestCriticalStrike = getInteger(aggregatedStatsInfo, "maxLargestCriticalStrike");
        final Integer maxLargestKillingSpree = getInteger(aggregatedStatsInfo, "maxLargestKillingSpree");
        final Integer maxNodeCapture = getInteger(aggregatedStatsInfo, "maxNodeCapture");
        final Integer maxNodeCaputreAssist = getInteger(aggregatedStatsInfo, "maxNodeCaputreAssist");
        final Integer maxNodeNeutralize = getInteger(aggregatedStatsInfo, "maxNodeNeutralize");
        final Integer maxNodeNeutralizeAssist = getInteger(aggregatedStatsInfo, "maxNodeNeutralizeAssist");
        final Integer maxNumDeaths = getInteger(aggregatedStatsInfo, "maxNumDeaths");
        final Integer maxObjectivePlayerScore = getInteger(aggregatedStatsInfo, "maxObjectivePlayerScore");
        final Integer maxTeamObjective = getInteger(aggregatedStatsInfo, "maxTeamObjective");
        final Integer maxTimePlayed = getInteger(aggregatedStatsInfo, "maxTimePlayed");
        final Integer maxTimeSpentLiving = getInteger(aggregatedStatsInfo, "maxTimeSpentLiving");
        final Integer maxTotalPlayerScore = getInteger(aggregatedStatsInfo, "maxTotalPlayerScore");
        final Integer mostChampionKillsPerSession = getInteger(aggregatedStatsInfo, "mostChampionKillsPerSession");
        final Integer mostSpellsCast = getInteger(aggregatedStatsInfo, "mostSpellsCast");
        final Integer normalGamesPlayed = getInteger(aggregatedStatsInfo, "normalGamesPlayed");
        final Integer rankedPremadeGamesPlayed = getInteger(aggregatedStatsInfo, "rankedPremadeGamesPlayed");
        final Integer rankedSoloGamesPlayed = getInteger(aggregatedStatsInfo, "rankedSoloGamesPlayed");
        final Integer totalAssists = getInteger(aggregatedStatsInfo, "totalAssists");
        final Integer totalChampionKills = getInteger(aggregatedStatsInfo, "totalChampionKills");
        final Integer totalDamageDealt = getInteger(aggregatedStatsInfo, "totalDamageDealt");
        final Integer totalDamageTaken = getInteger(aggregatedStatsInfo, "totalDamageTaken");
        final Integer totalDeathsPerSession = getInteger(aggregatedStatsInfo, "totalDeathsPerSession");
        final Integer totalDoubleKills = getInteger(aggregatedStatsInfo, "totalDoubleKills");
        final Integer totalFirstBlood = getInteger(aggregatedStatsInfo, "totalFirstBlood");
        final Integer totalGoldEarned = getInteger(aggregatedStatsInfo, "totalGoldEarned");
        final Integer totalHeal = getInteger(aggregatedStatsInfo, "totalHeal");
        final Integer totalMagicDamageDealt = getInteger(aggregatedStatsInfo, "totalMagicDamageDealt");
        final Integer totalMinionKills = getInteger(aggregatedStatsInfo, "totalMinionKills");
        final Integer totalNeutralMinionsKilled = getInteger(aggregatedStatsInfo, "totalNeutralMinionsKilled");
        final Integer totalNodeCapture = getInteger(aggregatedStatsInfo, "totalNodeCapture");
        final Integer totalNodeNeutralize = getInteger(aggregatedStatsInfo, "totalNodeNeutralize");
        final Integer totalPentaKills = getInteger(aggregatedStatsInfo, "totalPentaKills");
        final Integer totalPhysicalDamageDealt = getInteger(aggregatedStatsInfo, "totalPhysicalDamageDealt");
        final Integer totalQuadraKills = getInteger(aggregatedStatsInfo, "totalQuadraKills");
        final Integer totalSessionsLost = getInteger(aggregatedStatsInfo, "totalSessionsLost");
        final Integer totalSessionsWon = getInteger(aggregatedStatsInfo, "totalSessionsWon");
        final Integer totalTripleKills = getInteger(aggregatedStatsInfo, "totalTripleKills");
        final Integer totalTurretsKilled = getInteger(aggregatedStatsInfo, "totalTurretsKilled");
        final Integer totalUnrealKills = getInteger(aggregatedStatsInfo, "totalUnrealKills");

        return new AggregatedStats(averageAssists, averageChampionsKilled, averageCombatPlayerScore, averageNodeCapture, averageNodeCaptureAssist,
                averageNodeNeutralize, averageNodeNeutralizeAssist, averageNumDeaths, averageObjectivePlayerScore, averageTeamObjective,
                averageTotalPlayerScore, botGamesPlayed, killingSpree, maxAssists, maxChampionsKilled, maxCombatPlayerScore, maxLargestCriticalStrike,
                maxLargestKillingSpree, maxNodeCapture, maxNodeCaputreAssist, maxNodeNeutralize, maxNodeNeutralizeAssist, maxNumDeaths,
                maxObjectivePlayerScore, maxTeamObjective, maxTimePlayed, maxTimeSpentLiving, maxTotalPlayerScore, mostChampionKillsPerSession, mostSpellsCast,
                normalGamesPlayed, rankedPremadeGamesPlayed, rankedSoloGamesPlayed, totalAssists, totalChampionKills, totalDamageDealt, totalDamageTaken,
                totalDeathsPerSession, totalDoubleKills, totalFirstBlood, totalGoldEarned, totalHeal, totalMagicDamageDealt, totalMinionKills,
                totalNeutralMinionsKilled, totalNodeCapture, totalNodeNeutralize, totalPentaKills, totalPhysicalDamageDealt, totalQuadraKills,
                totalSessionsLost, totalSessionsWon, totalTripleKills, totalTurretsKilled, totalUnrealKills);
    }

    private List<GamePlayer> getAllGamePlayersFromJSON(final JSONArray playerList) {
        if(playerList == null) {
            return null;
        }

        final List<Long> summonerIDs = getList(playerList, m -> (Long)((JSONObject)m).get("summonerId"));
        final List<Summoner> summoners = API.getSummonersByID(summonerIDs);
        final Map<Long, Summoner> mapping = new HashMap<Long, Summoner>();
        for(final Summoner summoner : summoners) {
            mapping.put(summoner.ID, summoner);
        }

        return getList(playerList, p -> {
            final JSONObject player = (JSONObject)p;
            return getGamePlayerFromJSON(mapping.get(player.get("summonerId")), player);
        });
    }

    private List<GamePlayer> getAllGamePlayersFromJSON(final Map<Long, Summoner> players, final JSONArray playerList) {
        if(playerList == null) {
            return null;
        }

        return getList(playerList, p -> {
            final JSONObject player = (JSONObject)p;
            return getGamePlayerFromJSON(players.get(player.get("summonerId")), player);
        });
    }

    protected List<Game> getAllGamesFromJSON(final JSONArray gameList) {
        if(gameList == null) {
            return null;
        }

        final Map<Object, JSONArray> playerLists = getMapFromList(gameList, g -> (JSONArray)((JSONObject)g).get("fellowPlayers"));
        final List<Long> summonerIDs = new ArrayList<Long>();
        final Map<Object, List<Long>> ownership = new HashMap<Object, List<Long>>();
        for(final Object obj : playerLists.keySet()) {
            final List<Long> IDs = getList(playerLists.get(obj), m -> (Long)((JSONObject)m).get("summonerId"));
            summonerIDs.addAll(IDs);
            ownership.put(obj, IDs);
        }

        final List<Summoner> summoners = API.getSummonersByID(summonerIDs);

        final Map<Long, Summoner> IDMapping = new HashMap<Long, Summoner>();
        for(final Summoner summoner : summoners) {
            IDMapping.put(summoner.ID, summoner);
        }

        final Map<Object, Map<Long, Summoner>> mapping = new HashMap<Object, Map<Long, Summoner>>();
        for(final Object obj : ownership.keySet()) {
            final Map<Long, Summoner> subset = new HashMap<Long, Summoner>();
            for(final Long ID : ownership.get(obj)) {
                subset.put(ID, IDMapping.get(ID));
            }
            mapping.put(obj, Collections.unmodifiableMap(subset));
        }

        return getList(gameList, g -> getGameFromJSON(mapping.get(g), (JSONObject)g));
    }

    private List<LeagueEntry> getAllLeagueEntriesFromJSON(final JSONArray leagueEntryList) {
        if(leagueEntryList == null) {
            return null;
        }

        final String sampleID = (String)((JSONObject)leagueEntryList.get(0)).get("playerOrTeamId");
        boolean summonerLeague;
        try {
            Long.parseLong(sampleID);
            summonerLeague = true;
        }
        catch(final NumberFormatException e) {
            summonerLeague = false;
        }

        if(summonerLeague) {
            final List<Long> summonerIDs = getList(leagueEntryList, m -> Long.parseLong((String)((JSONObject)m).get("playerOrTeamId")));
            final List<Summoner> summoners = API.getSummonersByID(summonerIDs);

            final Map<String, Summoner> mapping = new HashMap<String, Summoner>();
            for(final Summoner summoner : summoners) {
                mapping.put(Long.toString(summoner.ID), summoner);
            }

            return getList(leagueEntryList, e -> {
                final JSONObject entry = (JSONObject)e;
                return getLeagueEntryFromJSON(mapping.get(entry.get("playerOrTeamId")), entry);
            }, (final LeagueEntry e0, final LeagueEntry e1) -> e1.leaguePoints.compareTo(e0.leaguePoints));
        }
        else {
            final List<String> teamIDs = getList(leagueEntryList, m -> (String)((JSONObject)m).get("playerOrTeamId"));
            final List<Team> teams = API.getTeams(teamIDs);
            final Map<String, Team> mapping = new HashMap<String, Team>();
            for(final Team team : teams) {
                mapping.put(team.ID, team);
            }

            return getList(leagueEntryList, e -> {
                final JSONObject entry = (JSONObject)e;
                return getLeagueEntryFromJSON(mapping.get(entry.get("playerOrTeamId")), entry);
            });
        }
    }

    protected Map<Long, List<Team>> getAllSummonersTeamsFromJSON(final JSONObject teamList, final List<Long> IDs) {
        if(teamList == null || IDs.size() != teamList.size()) {
            return null;
        }

        final Map<Long, Map<JSONObject, List<Long>>> rosterIDs = new HashMap<Long, Map<JSONObject, List<Long>>>();
        final List<Long> summonerIDs = new ArrayList<Long>();
        for(final Long ID : IDs) {
            final Map<JSONObject, List<Long>> oneID = new HashMap<JSONObject, List<Long>>();
            final JSONArray teams = (JSONArray)teamList.get(ID.toString());
            for(final Object teamObj : teams) {
                final JSONObject team = (JSONObject)teamObj;
                final JSONObject roster = (JSONObject)team.get("roster");
                final JSONArray memberList = (JSONArray)roster.get("memberList");
                if(memberList == null) {
                    // Would've already been cached in a non-empty memberList
                    summonerIDs.add((Long)roster.get("ownerId"));
                    oneID.put(team, new ArrayList<Long>());
                    continue;
                }

                final List<Long> memberIDs = new ArrayList<Long>();
                for(final Object memberObj : memberList) {
                    final JSONObject member = (JSONObject)memberObj;
                    final Long memberID = (Long)member.get("playerId");
                    summonerIDs.add(memberID);
                    memberIDs.add(memberID);
                }
                oneID.put(team, memberIDs);
            }
            rosterIDs.put(ID, oneID);
        }

        final List<Summoner> summoners = API.getSummonersByID(summonerIDs);

        final Map<Long, Summoner> mapping = new HashMap<Long, Summoner>();
        for(final Summoner summoner : summoners) {
            mapping.put(summoner.ID, summoner);
        }

        final Map<Long, Map<JSONObject, List<Summoner>>> rosters = new HashMap<Long, Map<JSONObject, List<Summoner>>>();
        for(final Long ID : rosterIDs.keySet()) {
            final Map<JSONObject, List<Long>> teamsByID = rosterIDs.get(ID);
            final Map<JSONObject, List<Summoner>> teamsBySummoner = new HashMap<JSONObject, List<Summoner>>();
            for(final JSONObject team : teamsByID.keySet()) {
                final List<Long> rosterByID = teamsByID.get(team);
                final List<Summoner> rosterSummoners = new ArrayList<Summoner>();
                for(final Long summonerID : rosterByID) {
                    rosterSummoners.add(mapping.get(summonerID));
                }
                teamsBySummoner.put(team, rosterSummoners);
            }
            rosters.put(ID, teamsBySummoner);
        }

        final Map<Long, List<Team>> allTeams = new HashMap<Long, List<Team>>();
        for(final Long ID : IDs) {
            allTeams.put(ID, getAllTeamsFromJSON(rosters.get(ID)));
        }
        return Collections.unmodifiableMap(allTeams);
    }

    private List<TeamMemberInformation> getAllTeamMemberInfoFromJSON(final JSONArray teamMemberList) {
        if(teamMemberList == null) {
            return null;
        }

        final List<Long> summonerIDs = getList(teamMemberList, m -> (Long)((JSONObject)m).get("playerId"));
        final List<Summoner> summoners = API.getSummonersByID(summonerIDs);
        final Map<Long, Summoner> mapping = new HashMap<Long, Summoner>();
        for(final Summoner summoner : summoners) {
            mapping.put(summoner.ID, summoner);
        }

        return getList(teamMemberList, m -> {
            final JSONObject member = (JSONObject)m;
            return getTeamMemberInfoFromJSON(mapping.get(member.get("playerId")), member);
        });
    }

    private List<TeamMemberInformation> getAllTeamMemberInfoFromJSON(final List<Summoner> summoners, final JSONArray teamMemberList) {
        if(teamMemberList == null) {
            return null;
        }

        final Map<Long, Summoner> mapping = new HashMap<Long, Summoner>();
        for(final Summoner summoner : summoners) {
            mapping.put(summoner.ID, summoner);
        }

        return getList(teamMemberList, m -> {
            final JSONObject member = (JSONObject)m;
            return getTeamMemberInfoFromJSON(mapping.get(member.get("playerId")), member);
        });
    }

    protected List<Team> getAllTeamsFromJSON(final JSONObject teamList, final List<String> teamIDs) {
        final List<Long> summonerIDs = new ArrayList<Long>();
        final Map<JSONObject, List<Long>> rosterIDs = new HashMap<JSONObject, List<Long>>();
        for(final String teamID : teamIDs) {
            final JSONObject team = (JSONObject)teamList.get(teamID);
            final JSONObject roster = (JSONObject)team.get("roster");
            final JSONArray memberList = (JSONArray)roster.get("memberList");
            if(memberList == null) {
                // Would've already been cached in a non-empty memberList
                summonerIDs.add((Long)roster.get("ownerId"));
                rosterIDs.put(team, new ArrayList<Long>());
                continue;
            }

            final List<Long> IDs = new ArrayList<Long>();
            for(final Object memberObj : memberList) {
                final JSONObject member = (JSONObject)memberObj;
                final Long memberID = (Long)member.get("playerId");
                summonerIDs.add(memberID);
                IDs.add(memberID);
            }
            rosterIDs.put(team, IDs);
        }

        final List<Summoner> summoners = API.getSummonersByID(summonerIDs);

        final Map<Long, Summoner> mapping = new HashMap<Long, Summoner>();
        for(final Summoner summoner : summoners) {
            mapping.put(summoner.ID, summoner);
        }

        final Map<JSONObject, List<Summoner>> rosters = new HashMap<JSONObject, List<Summoner>>();
        for(final JSONObject team : rosterIDs.keySet()) {
            final List<Long> rosterByID = rosterIDs.get(team);
            final List<Summoner> rosterBySummoner = new ArrayList<Summoner>();
            for(final Long summonerID : rosterByID) {
                rosterBySummoner.add(mapping.get(summonerID));
            }
            rosters.put(team, rosterBySummoner);
        }

        return getAllTeamsFromJSON(rosters);
    }

    private List<Team> getAllTeamsFromJSON(final Map<JSONObject, List<Summoner>> rosters) {
        final List<Team> teams = new ArrayList<Team>();
        for(final JSONObject team : rosters.keySet()) {
            teams.add(getTeamFromJSON(rosters.get(team), team));
        }
        return Collections.unmodifiableList(teams);
    }

    public BannedChampion getBannedChampionFromJSON(final JSONObject bannedChampionInfo) {
        if(bannedChampionInfo == null) {
            return null;
        }

        final Champion champion = API.getChampion(getInteger(bannedChampionInfo, "championId"));
        final Integer pickTurn = getInteger(bannedChampionInfo, "pickTurn");
        final Side team = getSide(bannedChampionInfo, "teamId");

        return new BannedChampion(champion, pickTurn, team);
    }

    public BasicDataStats getBasicDataStatsFromJSON(final JSONObject basicDataStatsInfo) {
        if(basicDataStatsInfo == null) {
            return null;
        }

        final Double flatArmorMod = (Double)basicDataStatsInfo.get("FlatArmorMod");
        final Double flatArmorPenetrationMod = (Double)basicDataStatsInfo.get("FlatArmorPenetrationMod");
        final Double flatAttackSpeedMod = (Double)basicDataStatsInfo.get("FlatAttackSpeedMod");
        final Double flatBlockMod = (Double)basicDataStatsInfo.get("FlatBlockMod");
        final Double flatCritChanceMod = (Double)basicDataStatsInfo.get("FlatCritChanceMod");
        final Double flatCritDamageMod = (Double)basicDataStatsInfo.get("FlatCritDamageMod");
        final Double flatEXPBonus = (Double)basicDataStatsInfo.get("FlatEXPBonus");
        final Double flatEnergyPoolMod = (Double)basicDataStatsInfo.get("FlatEnergyPoolMod");
        final Double flatEnergyRegenMod = (Double)basicDataStatsInfo.get("FlatEnergyRegenMod");
        final Double flatGoldPer10Mod = (Double)basicDataStatsInfo.get("FlatGoldPer10Mod");
        final Double flatHPPoolMod = (Double)basicDataStatsInfo.get("FlatHPPoolMod");
        final Double flatHPRegenMod = (Double)basicDataStatsInfo.get("FlatHPRegenMod");
        final Double flatMPPoolMod = (Double)basicDataStatsInfo.get("FlatMPPoolMod");
        final Double flatMPRegenMod = (Double)basicDataStatsInfo.get("FlatMPRegenMod");
        final Double flatMagicDamageMod = (Double)basicDataStatsInfo.get("FlatMagicDamageMod");
        final Double flatMagicPenetrationMod = (Double)basicDataStatsInfo.get("FlatMagicPenetrationMod");
        final Double flatMovementSpeedMod = (Double)basicDataStatsInfo.get("FlatMovementSpeedMod");
        final Double flatPhysicalDamageMod = (Double)basicDataStatsInfo.get("FlatPhysicalDamageMod");
        final Double flatSpellBlockMod = (Double)basicDataStatsInfo.get("FlatSpellBlockMod");
        final Double percentArmorMod = (Double)basicDataStatsInfo.get("PercentArmorMod");
        final Double percentArmorPenetrationMod = (Double)basicDataStatsInfo.get("PercentArmorPenetrationMod");
        final Double percentAttackSpeedMod = (Double)basicDataStatsInfo.get("PercentAttackSpeedMod");
        final Double percentBlockMod = (Double)basicDataStatsInfo.get("PercentBlockMod");
        final Double percentCooldownMod = (Double)basicDataStatsInfo.get("PercentCooldownMod");
        final Double percentCritChanceMod = (Double)basicDataStatsInfo.get("PercentCritChanceMod");
        final Double percentCritDamageMod = (Double)basicDataStatsInfo.get("PercentCritDamageMod");
        final Double percentDodgeMod = (Double)basicDataStatsInfo.get("PercentDodgeMod");
        final Double percentEXPBonus = (Double)basicDataStatsInfo.get("PercentEXPBonus");
        final Double percentHPPoolMod = (Double)basicDataStatsInfo.get("PercentHPPoolMod");
        final Double percentHPRegenMod = (Double)basicDataStatsInfo.get("PercentHPRegenMod");
        final Double percentLifeStealMod = (Double)basicDataStatsInfo.get("PercentLifeStealMod");
        final Double percentMPPoolMod = (Double)basicDataStatsInfo.get("PercentMPPoolMod");
        final Double percentMPRegenMod = (Double)basicDataStatsInfo.get("PercentMPRegenMod");
        final Double percentMagicDamageMod = (Double)basicDataStatsInfo.get("PercentMagicDamageMod");
        final Double percentMagicPenetrationMod = (Double)basicDataStatsInfo.get("PercentMagicPenetrationMod");
        final Double percentMovementSpeedMod = (Double)basicDataStatsInfo.get("PercentMovementSpeedMod");
        final Double percentPhysicalDamageMod = (Double)basicDataStatsInfo.get("PercentPhysicalDamageMod");
        final Double percentSpellBlockMod = (Double)basicDataStatsInfo.get("PercentSpellBlockMod");
        final Double percentSpellVampMod = (Double)basicDataStatsInfo.get("PercentSpellVampMod");
        final Double rFlatArmorModPerLevel = (Double)basicDataStatsInfo.get("rFlatArmorModPerLevel");
        final Double rFlatArmorPenetrationMod = (Double)basicDataStatsInfo.get("rFlatArmorPenetrationMod");
        final Double rFlatArmorPenetrationModPerLevel = (Double)basicDataStatsInfo.get("rFlatArmorPenetrationModPerLevel");
        final Double rFlatCritChanceModPerLevel = (Double)basicDataStatsInfo.get("rFlatCritChanceModPerLevel");
        final Double rFlatCritDamageModPerLevel = (Double)basicDataStatsInfo.get("rFlatCritDamageModPerLevel");
        final Double rFlatDodgeMod = (Double)basicDataStatsInfo.get("rFlatDodgeMod");
        final Double rFlatDodgeModPerLevel = (Double)basicDataStatsInfo.get("rFlatDodgeModPerLevel");
        final Double rFlatEnergyModPerLevel = (Double)basicDataStatsInfo.get("rFlatEnergyModPerLevel");
        final Double rFlatEnergyRegenModPerLevel = (Double)basicDataStatsInfo.get("rFlatEnergyRegenModPerLevel");
        final Double rFlatGoldPer10Mod = (Double)basicDataStatsInfo.get("rFlatGoldPer10Mod");
        final Double rFlatHPModPerLevel = (Double)basicDataStatsInfo.get("rFlatHPModPerLevel");
        final Double rFlatHPRegenModPerLevel = (Double)basicDataStatsInfo.get("rFlatHPRegenModPerLevel");
        final Double rFlatMPModPerLevel = (Double)basicDataStatsInfo.get("rFlatMPModPerLevel");
        final Double rFlatMPRegenModPerLevel = (Double)basicDataStatsInfo.get("rFlatMPRegenModPerLevel");
        final Double rFlatMagicDamageModPerLevel = (Double)basicDataStatsInfo.get("rFlatMagicDamageModPerLevel");
        final Double rFlatMagicPenetrationMod = (Double)basicDataStatsInfo.get("rFlatMagicPenetrationMod");
        final Double rFlatMagicPenetrationModPerLevel = (Double)basicDataStatsInfo.get("rFlatMagicPenetrationModPerLevel");
        final Double rFlatMovementSpeedModPerLevel = (Double)basicDataStatsInfo.get("rFlatMovementSpeedModPerLevel");
        final Double rFlatPhysicalDamageModPerLevel = (Double)basicDataStatsInfo.get("rFlatPhysicalDamageModPerLevel");
        final Double rFlatSpellBlockModPerLevel = (Double)basicDataStatsInfo.get("rFlatSpellBlockModPerLevel");
        final Double rFlatTimeDeadMod = (Double)basicDataStatsInfo.get("rFlatTimeDeadMod");
        final Double rFlatTimeDeadModPerLevel = (Double)basicDataStatsInfo.get("rFlatTimeDeadModPerLevel");
        final Double rPercentArmorPenetrationMod = (Double)basicDataStatsInfo.get("rPercentArmorPenetrationMod");
        final Double rPercentArmorPenetrationModPerLevel = (Double)basicDataStatsInfo.get("rPercentArmorPenetrationModPerLevel");
        final Double rPercentAttackSpeedModPerLevel = (Double)basicDataStatsInfo.get("rPercentAttackSpeedModPerLevel");
        final Double rPercentCooldownMod = (Double)basicDataStatsInfo.get("rPercentCooldownMod");
        final Double rPercentCooldownModPerLevel = (Double)basicDataStatsInfo.get("rPercentCooldownModPerLevel");
        final Double rPercentMagicPenetrationMod = (Double)basicDataStatsInfo.get("rPercentMagicPenetrationMod");
        final Double rPercentMagicPenetrationModPerLevel = (Double)basicDataStatsInfo.get("rPercentMagicPenetrationModPerLevel");
        final Double rPercentMovementSpeedModPerLevel = (Double)basicDataStatsInfo.get("rPercentMovementSpeedModPerLevel");
        final Double rPercentTimeDeadMod = (Double)basicDataStatsInfo.get("rPercentTimeDeadMod");
        final Double rPercentTimeDeadModPerLevel = (Double)basicDataStatsInfo.get("rPercentTimeDeadModPerLevel");

        return new BasicDataStats(flatArmorMod, flatArmorPenetrationMod, flatAttackSpeedMod, flatBlockMod, flatCritChanceMod, flatCritDamageMod, flatEXPBonus,
                flatEnergyPoolMod, flatEnergyRegenMod, flatGoldPer10Mod, flatHPPoolMod, flatHPRegenMod, flatMPPoolMod, flatMPRegenMod, flatMagicDamageMod,
                flatMagicPenetrationMod, flatMovementSpeedMod, flatPhysicalDamageMod, flatSpellBlockMod, percentArmorMod, percentArmorPenetrationMod,
                percentAttackSpeedMod, percentBlockMod, percentCooldownMod, percentCritChanceMod, percentCritDamageMod, percentDodgeMod, percentEXPBonus,
                percentHPPoolMod, percentHPRegenMod, percentLifeStealMod, percentMPPoolMod, percentMPRegenMod, percentMagicDamageMod,
                percentMagicPenetrationMod, percentMovementSpeedMod, percentPhysicalDamageMod, percentSpellBlockMod, percentSpellVampMod,
                rFlatArmorModPerLevel, rFlatArmorPenetrationMod, rFlatArmorPenetrationModPerLevel, rFlatCritChanceModPerLevel, rFlatCritDamageModPerLevel,
                rFlatDodgeMod, rFlatDodgeModPerLevel, rFlatEnergyModPerLevel, rFlatEnergyRegenModPerLevel, rFlatGoldPer10Mod, rFlatHPModPerLevel,
                rFlatHPRegenModPerLevel, rFlatMPModPerLevel, rFlatMPRegenModPerLevel, rFlatMagicDamageModPerLevel, rFlatMagicPenetrationMod,
                rFlatMagicPenetrationModPerLevel, rFlatMovementSpeedModPerLevel, rFlatPhysicalDamageModPerLevel, rFlatSpellBlockModPerLevel, rFlatTimeDeadMod,
                rFlatTimeDeadModPerLevel, rPercentArmorPenetrationMod, rPercentArmorPenetrationModPerLevel, rPercentAttackSpeedModPerLevel,
                rPercentCooldownMod, rPercentCooldownModPerLevel, rPercentMagicPenetrationMod, rPercentMagicPenetrationModPerLevel,
                rPercentMovementSpeedModPerLevel, rPercentTimeDeadMod, rPercentTimeDeadModPerLevel);
    }

    public Block getBlockFromJSON(final JSONObject blockInfo) {
        if(blockInfo == null) {
            return null;
        }

        final Boolean recMath = (Boolean)blockInfo.get("recMath");
        final String type = (String)blockInfo.get("type");
        final List<BlockItem> items = getList(blockInfo, "items", b -> getBlockItemFromJSON((JSONObject)b));

        return new Block(items, recMath, type);
    }

    public BlockItem getBlockItemFromJSON(final JSONObject blockItemInfo) {
        if(blockItemInfo == null) {
            return null;
        }

        final Integer count = getInteger(blockItemInfo, "count");
        final Integer ID = getInteger(blockItemInfo, "id");

        /*
         * Riot's recommended items include some IDs that no longer exist in the
         * database. For now, this will be the fix.
         */
        // TODO: Remove this after rito fixes it.
        Item item = null;
        if(ID != 3186 && ID != 3176 && ID != 3210) {
            item = API.getItem(ID);
        }

        return new BlockItem(count, item);
    }

    public Champion getChampionFromJSON(final JSONObject championInfo) {
        if(championInfo == null) {
            return null;
        }

        final List<String> allyTips = getStringList(championInfo, "allytips");
        final List<String> enemyTips = getStringList(championInfo, "enemytips");
        final List<String> tags = getStringList(championInfo, "tags");
        final String blurb = (String)championInfo.get("blurb");
        final String key = (String)championInfo.get("key");
        final String lore = (String)championInfo.get("lore");
        final String name = (String)championInfo.get("name");
        final String partype = (String)championInfo.get("partype");
        final String title = (String)championInfo.get("title");
        final Integer ID = getInteger(championInfo, "id");
        final Image image = getImageFromJSON((JSONObject)championInfo.get("image"));
        final Information info = getInformationFromJSON((JSONObject)championInfo.get("info"));
        final Passive passive = getPassiveFromJSON((JSONObject)championInfo.get("passive"));
        final Stats stats = getStatsFromJSON((JSONObject)championInfo.get("stats"));
        final List<Recommended> recommended = getList(championInfo, "recommended", r -> getRecommendedFromJSON((JSONObject)r));
        final List<Skin> skins = getList(championInfo, "skins", s -> getSkinFromJSON((JSONObject)s));
        final List<ChampionSpell> spells = getList(championInfo, "spells", s -> getChampionSpellFromJSON((JSONObject)s));

        return new Champion(allyTips, enemyTips, tags, blurb, key, lore, name, partype, title, ID, image, info, passive, recommended, skins, spells, stats);
    }

    public ChampionSpell getChampionSpellFromJSON(final JSONObject championSpellInfo) {
        if(championSpellInfo == null) {
            return null;
        }

        final List<Double> cooldown = getDoubleList(championSpellInfo, "cooldown");
        final List<Integer> cost = getIntegerList(championSpellInfo, "cost");
        final List<String> effectBurn = getStringList(championSpellInfo, "effectBurn");
        final Integer maxRank = getInteger(championSpellInfo, "maxrank");
        final String cooldownBurn = (String)championSpellInfo.get("cooldownBurn");
        final String costBurn = (String)championSpellInfo.get("costBurn");
        final String costType = (String)championSpellInfo.get("costType");
        final String description = (String)championSpellInfo.get("description");
        final String key = (String)championSpellInfo.get("key");
        final String name = (String)championSpellInfo.get("name");
        final String rangeBurn = (String)championSpellInfo.get("rangeBurn");
        final String resource = (String)championSpellInfo.get("resource");
        final String sanitizedDescription = (String)championSpellInfo.get("sanitizedDescription");
        final String sanitizedTooltip = (String)championSpellInfo.get("sanitizedTooltip");
        final String tooltip = (String)championSpellInfo.get("tooltip");
        final Image image = getImageFromJSON((JSONObject)championSpellInfo.get("image"));
        final LevelTip levelTip = getLevelTipFromJSON((JSONObject)championSpellInfo.get("leveltip"));
        final List<Image> altImages = getList(championSpellInfo, "altimages", i -> getImageFromJSON((JSONObject)i));
        final List<SpellVariables> vars = getList(championSpellInfo, "vars", v -> getSpellVariablesFromJSON((JSONObject)v));
        final List<List<Double>> effect = getList(championSpellInfo, "effect", l -> getDoubleList((JSONArray)l));

        List<Integer> range;
        final Object val = championSpellInfo.get("range");
        if(val instanceof String) {
            range = null;
        }
        else {
            range = getIntegerList((JSONArray)val);
        }

        return new ChampionSpell(altImages, cooldown, cooldownBurn, costBurn, costType, description, key, name, rangeBurn, resource, sanitizedDescription,
                sanitizedTooltip, tooltip, cost, range, effect, effectBurn, image, levelTip, maxRank, vars);
    }

    public ChampionStats getChampionStatsFromJSON(final JSONObject championStatsInfo) {
        if(championStatsInfo == null) {
            return null;
        }

        final AggregatedStats stats = getAggregatedStatsFromJSON((JSONObject)championStatsInfo.get("stats"));
        Champion champion;
        final Integer championID = getInteger(championStatsInfo, "id");
        if(championID == null || championID == 0) {
            champion = null;
        }
        else {
            champion = API.getChampion(championID);
        }

        return new ChampionStats(champion, stats);
    }

    public ChampionStatus getChampionStatusFromJSON(final JSONObject championStatusInfo) {
        if(championStatusInfo == null) {
            return null;
        }

        final Boolean active = (Boolean)championStatusInfo.get("active");
        final Boolean botEnabled = (Boolean)championStatusInfo.get("botEnabled");
        final Boolean botMmEnabled = (Boolean)championStatusInfo.get("botMmEnabled");
        final Boolean freeToPlay = (Boolean)championStatusInfo.get("freeToPlay");
        final Boolean rankedPlayEnabled = (Boolean)championStatusInfo.get("rankedPlayEnabled");
        final Champion champion = API.getChampion(getInteger(championStatusInfo, "id"));

        return new ChampionStatus(active, botEnabled, botMmEnabled, freeToPlay, rankedPlayEnabled, champion);
    }

    public Event getEventFromJSON(final JSONObject eventInfo, final Map<Integer, Participant> participants, final Map<Side, MatchTeam> teams) {
        if(eventInfo == null) {
            return null;
        }

        String typeName = (String)eventInfo.get("buildingType");
        final BuildingType buildingType = typeName == null ? null : BuildingType.valueOf(typeName);
        typeName = (String)eventInfo.get("eventType");
        final EventType eventType = typeName == null ? null : EventType.valueOf(typeName);
        typeName = (String)eventInfo.get("laneType");
        final LaneType laneType = typeName == null ? null : LaneType.valueOf(typeName);
        typeName = (String)eventInfo.get("monsterType");
        final MonsterType monsterType = typeName == null ? null : MonsterType.valueOf(typeName);
        typeName = (String)eventInfo.get("towerType");
        final TowerType towerType = typeName == null ? null : TowerType.valueOf(typeName);
        typeName = (String)eventInfo.get("wardType");
        final WardType wardType = typeName == null ? null : WardType.valueOf(typeName);

        final Position position = getPositionFromJSON((JSONObject)eventInfo.get("position"));
        final Duration timestamp = getDuration(eventInfo, "timestamp", ChronoUnit.MILLIS);
        final Participant creator = participants.get(getInteger(eventInfo, "creatorId"));
        final Participant killer = participants.get(getInteger(eventInfo, "killerId"));
        final Participant victim = participants.get(getInteger(eventInfo, "victimId"));
        final MatchTeam team = teams.get(getSide(eventInfo, "teamId"));

        final List<Integer> participantIDs = getIntegerList(eventInfo, "assistingParticipantIds");
        final List<Participant> assistingParticipants = participantIDs == null ? null : participantIDs.stream().map((ID) -> participants.get(ID))
                .collect(Collectors.toList());

        return new Event(assistingParticipants, creator, killer, victim, team, timestamp, position, eventType, buildingType, laneType, monsterType, towerType,
                wardType);
    }

    @SuppressWarnings("unchecked")
    public Frame getFrameFromJSON(final JSONObject frameInfo, final Map<Integer, Participant> participants, final Map<Side, MatchTeam> teams) {
        if(frameInfo == null) {
            return null;
        }

        final List<Event> events = getList(frameInfo, "events", (e) -> getEventFromJSON((JSONObject)e, participants, teams));
        final Duration timestamp = getDuration(frameInfo, "timestamp", ChronoUnit.MILLIS);

        Map<Participant, ParticipantFrame> participantFrames;

        final Map<String, JSONObject> pFrames = (Map<String, JSONObject>)frameInfo.get("participantFrames");
        if(pFrames == null) {
            participantFrames = null;
        }
        else {
            participantFrames = pFrames
                    .entrySet()
                    .stream()
                    .collect(
                            Collectors.toMap((entry) -> participants.get(new Integer(entry.getKey())),
                                    (entry) -> getParticipantFrameFromJSON(entry.getValue(), participants.get(new Integer(entry.getKey())))));
        }

        return new Frame(events, participantFrames, timestamp);
    }

    public Game getGameFromJSON(final JSONObject gameInfo) {
        if(gameInfo == null) {
            return null;
        }

        final Champion champion = API.getChampion(getInteger(gameInfo, "championId"));
        final LocalDateTime createDate = getDateTime(gameInfo, "createDate");
        final Long ID = (Long)gameInfo.get("gameId");
        final GameMode gameMode = GameMode.valueOf((String)gameInfo.get("gameMode"));
        final GameType gameType = GameType.valueOf((String)gameInfo.get("gameType"));
        final SubType subType = SubType.valueOf((String)gameInfo.get("subType"));
        final Boolean invalid = (Boolean)gameInfo.get("invalid");
        final Integer IPEarned = getInteger(gameInfo, "ipEarned");
        final Integer level = getInteger(gameInfo, "level");
        final MatchMap map = getMap(getInteger(gameInfo, "mapId"));
        final SummonerSpell spell1 = API.getSummonerSpell(getInteger(gameInfo, "spell1"));
        final SummonerSpell spell2 = API.getSummonerSpell(getInteger(gameInfo, "spell2"));
        final RawStats stats = getRawStatsFromJSON((JSONObject)gameInfo.get("stats"));
        final Side team = getSide(gameInfo, "teamId");
        final List<GamePlayer> fellowPlayers = getAllGamePlayersFromJSON((JSONArray)gameInfo.get("fellowPlayers"));

        return new Game(champion, createDate, fellowPlayers, gameMode, gameType, ID, invalid, IPEarned, level, map, spell1, spell2, stats, subType, team);
    }

    private Game getGameFromJSON(final Map<Long, Summoner> players, final JSONObject gameInfo) {
        if(gameInfo == null) {
            return null;
        }

        final Champion champion = API.getChampion(getInteger(gameInfo, "championId"));
        final LocalDateTime createDate = getDateTime(gameInfo, "createDate");
        final Long ID = (Long)gameInfo.get("gameId");
        final GameMode gameMode = GameMode.valueOf((String)gameInfo.get("gameMode"));
        final GameType gameType = GameType.valueOf((String)gameInfo.get("gameType"));
        final SubType subType = SubType.valueOf((String)gameInfo.get("subType"));
        final Boolean invalid = (Boolean)gameInfo.get("invalid");
        final Integer IPEarned = getInteger(gameInfo, "ipEarned");
        final Integer level = getInteger(gameInfo, "level");
        final MatchMap map = getMap(getInteger(gameInfo, "mapId"));
        final SummonerSpell spell1 = API.getSummonerSpell(getInteger(gameInfo, "spell1"));
        final SummonerSpell spell2 = API.getSummonerSpell(getInteger(gameInfo, "spell2"));
        final RawStats stats = getRawStatsFromJSON((JSONObject)gameInfo.get("stats"));
        final Side team = getSide(gameInfo, "teamId");
        final List<GamePlayer> fellowPlayers = getAllGamePlayersFromJSON(players, (JSONArray)gameInfo.get("fellowPlayers"));

        return new Game(champion, createDate, fellowPlayers, gameMode, gameType, ID, invalid, IPEarned, level, map, spell1, spell2, stats, subType, team);
    }

    public lib.orianna.type.game.GamePlayer getGamePlayerFromJSON(final JSONObject playerInfo) {
        if(playerInfo == null) {
            return null;
        }

        final Champion champion = API.getChampion(getInteger(playerInfo, "championId"));
        final Summoner summoner = API.getSummonerByID((Long)playerInfo.get("summonerId"));
        final Side team = getSide(playerInfo, "teamId");

        return new GamePlayer(champion, summoner, team);
    }

    public GamePlayer getGamePlayerFromJSON(final Summoner summoner, final JSONObject playerInfo) {
        if(playerInfo == null) {
            return null;
        }

        final Champion champion = API.getChampion(getInteger(playerInfo, "championId"));
        final Side team = getSide(playerInfo, "teamId");

        return new GamePlayer(champion, summoner, team);
    }

    public Gold getGoldFromJSON(final JSONObject goldInfo) {
        if(goldInfo == null) {
            return null;
        }

        final Integer base = getInteger(goldInfo, "base");
        final Integer sell = getInteger(goldInfo, "sell");
        final Integer total = getInteger(goldInfo, "total");
        final Boolean purchasable = (Boolean)goldInfo.get("purchasable");

        return new Gold(base, sell, total, purchasable);
    }

    public Image getImageFromJSON(final JSONObject imageInfo) {
        if(imageInfo == null) {
            return null;
        }

        final String full = (String)imageInfo.get("full");
        final String group = (String)imageInfo.get("group");
        final String sprite = (String)imageInfo.get("sprite");
        final Integer h = getInteger(imageInfo, "h");
        final Integer w = getInteger(imageInfo, "w");
        final Integer x = getInteger(imageInfo, "x");
        final Integer y = getInteger(imageInfo, "y");

        return new Image(full, group, sprite, h, w, x, y);
    }

    public Information getInformationFromJSON(final JSONObject informationInfo) {
        if(informationInfo == null) {
            return null;
        }

        final Integer attack = getInteger(informationInfo, "attack");
        final Integer defense = getInteger(informationInfo, "defense");
        final Integer difficulty = getInteger(informationInfo, "difficulty");
        final Integer magic = getInteger(informationInfo, "magic");

        return new Information(attack, defense, difficulty, magic);
    }

    private Item getItem(final JSONObject object, final String key) {
        final Integer ID = getInteger(object, key);
        if(ID == null) {
            return null;
        }

        return API.getItem(ID);
    }

    @SuppressWarnings("unchecked")
    public Item getItemFromJSON(final JSONObject itemInfo) {
        if(itemInfo == null) {
            return null;
        }

        final String colloq = (String)itemInfo.get("colloq");
        final String description = (String)itemInfo.get("description");
        final String group = (String)itemInfo.get("group");
        final String name = (String)itemInfo.get("name");
        final String plaintext = (String)itemInfo.get("plaintext");
        final String requiredChampion = (String)itemInfo.get("requiredChampion");
        final String sanitizedDescription = (String)itemInfo.get("sanitizedDescription");
        final Boolean consumeOnFull = (Boolean)itemInfo.get("consumeOnFull");
        final Boolean consumed = (Boolean)itemInfo.get("consumed");
        final Boolean hideFromAll = (Boolean)itemInfo.get("hideFromAll");
        final Boolean inStore = (Boolean)itemInfo.get("inStore");
        final Integer depth = getInteger(itemInfo, "depth");
        final Integer ID = getInteger(itemInfo, "id");
        final Integer specialRecipe = getInteger(itemInfo, "specialRecipe");
        final Integer stacks = getInteger(itemInfo, "stacks");
        final Gold gold = getGoldFromJSON((JSONObject)itemInfo.get("gold"));
        final Image image = getImageFromJSON((JSONObject)itemInfo.get("image"));
        final MetaData rune = getMetaDataFromJSON((JSONObject)itemInfo.get("rune"));
        final List<String> from = getStringList(itemInfo, "from");
        final List<String> into = getStringList(itemInfo, "into");
        final List<String> tags = getStringList(itemInfo, "tags");
        Map<String, Boolean> maps = (Map<String, Boolean>)itemInfo.get("maps");
        if(maps != null) {
            maps = Collections.unmodifiableMap(maps);
        }

        /*
         * There's a few things Riot doesn't send as stats yet, so we have to
         * parse them out of the description and add them ourselves.
         */
        // TODO: Remove this after rito fixes it.
        final JSONObject statsObj = (JSONObject)itemInfo.get("stats");
        final Map<String, Pattern> patterns = getScrapedStatPatterns();
        patterns.keySet().parallelStream().forEach((stat) -> {
            final Matcher matcher = patterns.get(stat).matcher(description);
            if(matcher.find()) {
                statsObj.put(stat, new Double(matcher.group(1)));
            }
        });

        final BasicDataStats stats = getBasicDataStatsFromJSON(statsObj);

        return new Item(colloq, description, group, name, plaintext, requiredChampion, sanitizedDescription, consumeOnFull, consumed, hideFromAll, inStore,
                depth, ID, specialRecipe, stacks, from, into, tags, gold, image, maps, rune, stats);
    }

    public LeagueEntry getLeagueEntryFromJSON(final JSONObject leagueEntryInfo) {
        if(leagueEntryInfo == null) {
            return null;
        }

        final String division = (String)leagueEntryInfo.get("division");
        final String playerOrTeamName = (String)leagueEntryInfo.get("playerOrTeamName");
        final Boolean isFreshBlood = (Boolean)leagueEntryInfo.get("isFreshBlood");
        final Boolean isHotStreak = (Boolean)leagueEntryInfo.get("isHotStreak");
        final Boolean isInactive = (Boolean)leagueEntryInfo.get("isInactive");
        final Boolean isVeteran = (Boolean)leagueEntryInfo.get("isVeteran");
        final Integer leaguePoints = getInteger(leagueEntryInfo, "leaguePoints");
        final Integer wins = getInteger(leagueEntryInfo, "wins");
        final MiniSeries miniSeries = getMiniSeriesFromJSON((JSONObject)leagueEntryInfo.get("miniSeries"));

        final String IDStr = (String)leagueEntryInfo.get("playerOrTeamId");
        try {
            final Long ID = Long.parseLong(IDStr);
            final Summoner player = API.getSummonerByID(ID);
            if(player == null) {
                throw new NumberFormatException("IDStr wasn't a summoner ID!");
            }

            return new LeagueEntry(player, division, playerOrTeamName, isFreshBlood, isHotStreak, isInactive, isVeteran, leaguePoints, wins, miniSeries);
        }
        catch(final NumberFormatException e) {
            final Team team = API.getTeam(IDStr);
            return new LeagueEntry(team, division, playerOrTeamName, isFreshBlood, isHotStreak, isInactive, isVeteran, leaguePoints, wins, miniSeries);
        }
    }

    private LeagueEntry getLeagueEntryFromJSON(final Summoner player, final JSONObject leagueEntryInfo) {
        if(leagueEntryInfo == null) {
            return null;
        }

        final String division = (String)leagueEntryInfo.get("division");
        final String playerOrTeamName = (String)leagueEntryInfo.get("playerOrTeamName");
        final Boolean isFreshBlood = (Boolean)leagueEntryInfo.get("isFreshBlood");
        final Boolean isHotStreak = (Boolean)leagueEntryInfo.get("isHotStreak");
        final Boolean isInactive = (Boolean)leagueEntryInfo.get("isInactive");
        final Boolean isVeteran = (Boolean)leagueEntryInfo.get("isVeteran");
        final Integer leaguePoints = getInteger(leagueEntryInfo, "leaguePoints");
        final Integer wins = getInteger(leagueEntryInfo, "wins");
        final MiniSeries miniSeries = getMiniSeriesFromJSON((JSONObject)leagueEntryInfo.get("miniSeries"));

        return new LeagueEntry(player, division, playerOrTeamName, isFreshBlood, isHotStreak, isInactive, isVeteran, leaguePoints, wins, miniSeries);
    }

    private LeagueEntry getLeagueEntryFromJSON(final Team team, final JSONObject leagueEntryInfo) {
        if(leagueEntryInfo == null) {
            return null;
        }

        final String division = (String)leagueEntryInfo.get("division");
        final String playerOrTeamName = (String)leagueEntryInfo.get("playerOrTeamName");
        final Boolean isFreshBlood = (Boolean)leagueEntryInfo.get("isFreshBlood");
        final Boolean isHotStreak = (Boolean)leagueEntryInfo.get("isHotStreak");
        final Boolean isInactive = (Boolean)leagueEntryInfo.get("isInactive");
        final Boolean isVeteran = (Boolean)leagueEntryInfo.get("isVeteran");
        final Integer leaguePoints = getInteger(leagueEntryInfo, "leaguePoints");
        final Integer wins = getInteger(leagueEntryInfo, "wins");
        final MiniSeries miniSeries = getMiniSeriesFromJSON((JSONObject)leagueEntryInfo.get("miniSeries"));

        return new LeagueEntry(team, division, playerOrTeamName, isFreshBlood, isHotStreak, isInactive, isVeteran, leaguePoints, wins, miniSeries);
    }

    public League getLeagueFromJSON(final JSONObject leagueInfo) {
        if(leagueInfo == null) {
            return null;
        }

        final String name = (String)leagueInfo.get("name");
        final LeagueType queue = LeagueType.valueOf((String)leagueInfo.get("queue"));
        final Tier tier = Tier.valueOf((String)leagueInfo.get("tier"));
        final List<LeagueEntry> entries = getAllLeagueEntriesFromJSON((JSONArray)leagueInfo.get("entries"));

        return new League(entries, queue, name, tier);
    }

    public LevelTip getLevelTipFromJSON(final JSONObject levelTipInfo) {
        if(levelTipInfo == null) {
            return null;
        }

        final List<String> effect = getStringList(levelTipInfo, "effect");
        final List<String> label = getStringList(levelTipInfo, "label");

        return new LevelTip(effect, label);
    }

    public Mastery getMasteryFromJSON(final JSONObject masteryInfo) {
        if(masteryInfo == null) {
            return null;
        }

        final List<String> description = getStringList(masteryInfo, "description");
        final List<String> sanitizedDescription = getStringList(masteryInfo, "sanitizedDescription");
        final Integer ID = getInteger(masteryInfo, "id");
        final Integer ranks = getInteger(masteryInfo, "ranks");
        final Image image = getImageFromJSON((JSONObject)masteryInfo.get("image"));
        final String name = (String)masteryInfo.get("name");
        final String prereq = (String)masteryInfo.get("prereq");

        return new Mastery(description, sanitizedDescription, ID, ranks, image, name, prereq);
    }

    public MasteryPage getMasteryPageFromJSON(final JSONObject masteryPageInfo, final MasteryTree masteryTree) {
        if(masteryPageInfo == null) {
            return null;
        }

        final Long ID = (Long)masteryPageInfo.get("id");
        final Boolean current = (Boolean)masteryPageInfo.get("current");
        final String name = (String)masteryPageInfo.get("name");
        final List<MasterySlot> masteries = getList(masteryPageInfo, "masteries", m -> getMasterySlotFromJSON((JSONObject)m, masteryTree));

        return new MasteryPage(current, ID, masteries, name);
    }

    public MasterySlot getMasterySlotFromJSON(final JSONObject masterySlotInfo, final MasteryTree masteryTree) {
        if(masterySlotInfo == null) {
            return null;
        }

        final Integer rank = getInteger(masterySlotInfo, "rank");
        final Integer ID = getInteger(masterySlotInfo, "id");
        final Mastery mastery = API.getMastery(ID);

        return new MasterySlot(mastery, rank, masteryTree.typeOf(mastery));
    }

    public MasteryTree getMasteryTreeFromJSON(final JSONObject masteryTreeInfo) {
        if(masteryTreeInfo == null) {
            return null;
        }

        final List<MasteryTreeList> defense = getList(masteryTreeInfo, "Defense", l -> getMasteryTreeListFromJSON((JSONObject)l));
        final List<MasteryTreeList> offense = getList(masteryTreeInfo, "Offense", l -> getMasteryTreeListFromJSON((JSONObject)l));
        final List<MasteryTreeList> utility = getList(masteryTreeInfo, "Utility", l -> getMasteryTreeListFromJSON((JSONObject)l));

        return new MasteryTree(defense, offense, utility);
    }

    public MasteryTreeItem getMasteryTreeItemFromJSON(final JSONObject masteryTreeItemInfo) {
        if(masteryTreeItemInfo == null) {
            return null;
        }

        final Mastery mastery = API.getMastery(getInteger(masteryTreeItemInfo, "masteryId"));
        final String prereq = (String)masteryTreeItemInfo.get("prereq");

        return new MasteryTreeItem(mastery, prereq);
    }

    public MasteryTreeList getMasteryTreeListFromJSON(final JSONObject masteryTreeListInfo) {
        if(masteryTreeListInfo == null) {
            return null;
        }

        final List<MasteryTreeItem> masteryTreeItems = getList(masteryTreeListInfo, "masteryTreeItems", i -> getMasteryTreeItemFromJSON((JSONObject)i));

        return new MasteryTreeList(masteryTreeItems);
    }

    public List<MatchSummary> getMatchHistoryFromJSON(final JSONArray matchHistoryInfo) {
        if(matchHistoryInfo == null) {
            return null;
        }

        final List<String> summonerNames = new ArrayList<String>();
        for(final Object msi : matchHistoryInfo) {
            final JSONObject matchSummaryInfo = (JSONObject)msi;
            final JSONArray participantIdentityList = (JSONArray)matchSummaryInfo.get("participantIdentities");
            final List<JSONObject> plrs = getList(participantIdentityList, p -> (JSONObject)((JSONObject)p).get("player"));
            final List<String> names = plrs.stream().map((plr) -> (String)plr.get("summonerName")).collect(Collectors.toList());
            summonerNames.addAll(names);
        }

        final List<Summoner> summoners = API.getSummoners(summonerNames);
        final Map<String, Summoner> mapping = new HashMap<String, Summoner>();
        for(final Summoner summoner : summoners) {
            mapping.put(summoner.name, summoner);
        }

        return getList(matchHistoryInfo, (m) -> getMatchSummaryFromJSON(mapping, (JSONObject)m));
    }

    public MatchHistorySummary getMatchHistorySummaryFromJSON(final JSONObject matchHistorySummaryInfo) {
        if(matchHistorySummaryInfo == null) {
            return null;
        }

        final Integer assists = getInteger(matchHistorySummaryInfo, "assists");
        final Integer deaths = getInteger(matchHistorySummaryInfo, "deaths");
        final Integer kills = getInteger(matchHistorySummaryInfo, "kills");
        final MatchMap map = getMap(getInteger(matchHistorySummaryInfo, "mapId"));
        final Integer opposingTeamKills = getInteger(matchHistorySummaryInfo, "opposingTeamKills");
        final LocalDateTime date = getDateTime(matchHistorySummaryInfo, "date");
        final Long gameID = (Long)matchHistorySummaryInfo.get("gameId");
        final String gameMode = (String)matchHistorySummaryInfo.get("gameMode");
        final String opposingTeamName = (String)matchHistorySummaryInfo.get("opposingTeamName");
        final Boolean invalid = (Boolean)matchHistorySummaryInfo.get("invalid");
        final Boolean win = (Boolean)matchHistorySummaryInfo.get("win");

        return new MatchHistorySummary(assists, deaths, kills, opposingTeamKills, map, date, gameID, gameMode, opposingTeamName, invalid, win);
    }

    public MatchSummary getMatchSummaryFromJSON(final JSONObject matchSummaryInfo) {
        if(matchSummaryInfo == null) {
            return null;
        }

        final MatchMap map = getMap(getInteger(matchSummaryInfo, "mapId"));
        final LocalDateTime creation = getDateTime(matchSummaryInfo, "matchCreation");
        final Duration duration = getDuration(matchSummaryInfo, "matchDuration", ChronoUnit.SECONDS);
        final Long ID = (Long)matchSummaryInfo.get("matchId");
        final String version = (String)matchSummaryInfo.get("matchVersion");
        final QueueType queueType = QueueType.valueOf((String)matchSummaryInfo.get("queueType"));
        final Region region = Region.valueOf((String)matchSummaryInfo.get("region"));
        final Season season = Season.valueOf((String)matchSummaryInfo.get("season"));
        final List<MatchTeam> teams = getList(matchSummaryInfo, "teams", (t) -> getMatchTeamFromJSON((JSONObject)t));

        final Map<Side, MatchTeam> teamColors = teams.stream().collect(Collectors.toMap((team) -> team.side, (team) -> team));

        final JSONArray identities = (JSONArray)matchSummaryInfo.get("participantIdentities");
        final Map<Integer, Player> participantPlayers = new HashMap<Integer, Player>();
        for(final Object iden : identities) {
            final JSONObject identity = (JSONObject)iden;
            participantPlayers.put(getInteger(identity, "participantId"), getPlayerFromJSON((JSONObject)identity.get("player")));
        }

        final List<Participant> participants = getList(matchSummaryInfo, "participants",
                (p) -> getParticipantFromJSON((JSONObject)p, teamColors, participantPlayers));

        final Map<Integer, Participant> participantIDs = participants.stream().collect(
                Collectors.toMap((participant) -> participant.ID, (participant) -> participant));

        final MatchTimeline timeline = getMatchTimelineFromJSON((JSONObject)matchSummaryInfo.get("timeline"), participantIDs, teamColors);

        final JSONArray participantIdentityList = (JSONArray)matchSummaryInfo.get("participantIdentities");
        final List<JSONObject> plrs = getList(participantIdentityList, p -> (JSONObject)((JSONObject)p).get("player"));

        final boolean haveIdentities = plrs.size() > 0 && plrs.get(0) != null;

        final Map<String, Summoner> mapping = new HashMap<String, Summoner>();
        if(haveIdentities) {
            final List<String> summonerNames = plrs.stream().map((plr) -> (String)plr.get("summonerName")).collect(Collectors.toList());

            final List<Summoner> summoners = API.getSummoners(summonerNames);
            for(final Summoner summoner : summoners) {
                mapping.put(summoner.name, summoner);
            }
        }

        return new MatchSummary(creation, duration, ID, map, participants, queueType, region, season, teams, timeline, version);
    }

    private MatchSummary getMatchSummaryFromJSON(final Map<String, Summoner> summoners, final JSONObject matchSummaryInfo) {
        if(matchSummaryInfo == null) {
            return null;
        }

        final MatchMap map = getMap(getInteger(matchSummaryInfo, "mapId"));
        final LocalDateTime creation = getDateTime(matchSummaryInfo, "matchCreation");
        final Duration duration = getDuration(matchSummaryInfo, "matchDuration", ChronoUnit.SECONDS);
        final Long ID = (Long)matchSummaryInfo.get("matchId");
        final String version = (String)matchSummaryInfo.get("matchVersion");
        final QueueType queueType = QueueType.valueOf((String)matchSummaryInfo.get("queueType"));
        final Region region = Region.valueOf((String)matchSummaryInfo.get("region"));
        final Season season = Season.valueOf((String)matchSummaryInfo.get("season"));
        final List<MatchTeam> teams = getList(matchSummaryInfo, "teams", (t) -> getMatchTeamFromJSON((JSONObject)t));

        final Map<Side, MatchTeam> teamColors = teams == null ? null : teams.stream().collect(Collectors.toMap((team) -> team.side, (team) -> team));

        final JSONArray identities = (JSONArray)matchSummaryInfo.get("participantIdentities");
        final Map<Integer, Player> participantPlayers = new HashMap<Integer, Player>();
        for(final Object iden : identities) {
            final JSONObject identity = (JSONObject)iden;
            participantPlayers.put(getInteger(identity, "participantId"), getPlayerFromJSON((JSONObject)identity.get("player")));
        }

        final List<Participant> participants = getList(matchSummaryInfo, "participants",
                (p) -> getParticipantFromJSON((JSONObject)p, teamColors, participantPlayers));

        final Map<Integer, Participant> participantIDs = participants.stream().collect(
                Collectors.toMap((participant) -> participant.ID, (participant) -> participant));

        final MatchTimeline timeline = getMatchTimelineFromJSON((JSONObject)matchSummaryInfo.get("timeline"), participantIDs, teamColors);

        return new MatchSummary(creation, duration, ID, map, participants, queueType, region, season, teams, timeline, version);
    }

    public MatchTeam getMatchTeamFromJSON(final JSONObject matchTeamInfo) {
        if(matchTeamInfo == null) {
            return null;
        }

        final List<BannedChampion> bans = getList(matchTeamInfo, "bans", (b) -> getBannedChampionFromJSON((JSONObject)b));
        final Integer baronKills = getInteger(matchTeamInfo, "baronKills");
        final Integer dragonKills = getInteger(matchTeamInfo, "dragonKills");
        final Integer inhibitorKills = getInteger(matchTeamInfo, "inhibitorKills");
        final Integer towerKills = getInteger(matchTeamInfo, "towerKills");
        final Integer vilemawKills = getInteger(matchTeamInfo, "vilemawKills");
        final Boolean firstBaron = (Boolean)matchTeamInfo.get("firstBaron");
        final Boolean firstBlood = (Boolean)matchTeamInfo.get("firstBlood");
        final Boolean firstDragon = (Boolean)matchTeamInfo.get("firstDragon");
        final Boolean firstInhibitor = (Boolean)matchTeamInfo.get("firstInhibitor");
        final Boolean firstTower = (Boolean)matchTeamInfo.get("firstTower");
        final Boolean winner = (Boolean)matchTeamInfo.get("winner");
        final Side side = getSide(matchTeamInfo, "teamId");

        return new MatchTeam(bans, dragonKills, baronKills, inhibitorKills, towerKills, vilemawKills, firstBaron, firstBlood, firstDragon, firstInhibitor,
                firstTower, winner, side);
    }

    public MatchTimeline getMatchTimelineFromJSON(final JSONObject matchTimelineInfo, final Map<Integer, Participant> participants,
            final Map<Side, MatchTeam> teams) {
        if(matchTimelineInfo == null) {
            return null;
        }

        final Duration frameInterval = getDuration(matchTimelineInfo, "frameInterval", ChronoUnit.MILLIS);
        final List<Frame> frames = getList(matchTimelineInfo, "frames", (f) -> getFrameFromJSON((JSONObject)f, participants, teams));

        return new MatchTimeline(frameInterval, frames);
    }

    public MetaData getMetaDataFromJSON(final JSONObject metaDataInfo) {
        if(metaDataInfo == null) {
            return null;
        }

        final Boolean isRune = (Boolean)metaDataInfo.get("isRune");
        final String tier = (String)metaDataInfo.get("tier");
        final String type = (String)metaDataInfo.get("type");

        return new MetaData(isRune, tier, RuneType.fromString(type));
    }

    public MiniSeries getMiniSeriesFromJSON(final JSONObject miniSeriesInfo) {
        if(miniSeriesInfo == null) {
            return null;
        }

        final Integer losses = getInteger(miniSeriesInfo, "losses");
        final Integer target = getInteger(miniSeriesInfo, "target");
        final Integer wins = getInteger(miniSeriesInfo, "wins");
        final String progress = (String)miniSeriesInfo.get("progress");

        return new MiniSeries(losses, target, wins, progress);
    }

    public ParticipantFrame getParticipantFrameFromJSON(final JSONObject participantFrameInfo, final Participant participant) {
        if(participantFrameInfo == null) {
            return null;
        }

        final Position position = getPositionFromJSON((JSONObject)participantFrameInfo.get("position"));
        final Integer currentGold = getInteger(participantFrameInfo, "currentGold");
        final Integer jungleMinionsKilled = getInteger(participantFrameInfo, "jungleMinionsKilled");
        final Integer level = getInteger(participantFrameInfo, "level");
        final Integer minionsKilled = getInteger(participantFrameInfo, "minionsKilled");
        final Integer totalGold = getInteger(participantFrameInfo, "totalGold");
        final Integer XP = getInteger(participantFrameInfo, "xp");

        return new ParticipantFrame(currentGold, jungleMinionsKilled, level, minionsKilled, totalGold, XP, participant, position);
    }

    public Participant getParticipantFromJSON(final JSONObject participantInfo, final Map<Side, MatchTeam> teams, final Map<Integer, Player> players) {
        if(participantInfo == null) {
            return null;
        }

        final Champion champion = API.getChampion(getInteger(participantInfo, "championId"));
        final Integer ID = getInteger(participantInfo, "participantId");
        final SummonerSpell spell1 = API.getSummonerSpell(getInteger(participantInfo, "spell1Id"));
        final SummonerSpell spell2 = API.getSummonerSpell(getInteger(participantInfo, "spell2Id"));
        final ParticipantStats stats = getParticipantStatsFromJSON((JSONObject)participantInfo.get("stats"));
        final ParticipantTimeline timeline = getParticipantTimelineFromJSON((JSONObject)participantInfo.get("timeline"));
        final MatchTeam team = teams == null ? null : teams.get(getSide(participantInfo, "teamId"));

        if(team != null) {
            return new Participant(champion, ID, spell1, spell2, stats, team, timeline, players.get(ID));
        }
        else {
            return new Participant(champion, ID, spell1, spell2, stats, getSide(participantInfo, "teamId"), timeline, players.get(ID));
        }
    }

    public ParticipantStats getParticipantStatsFromJSON(final JSONObject participantStatsInfo) {
        if(participantStatsInfo == null) {
            return null;
        }

        final Long assists = (Long)participantStatsInfo.get("assists");
        final Long champLevel = (Long)participantStatsInfo.get("champLevel");
        final Long combatPlayerScore = (Long)participantStatsInfo.get("combatPlayerScore");
        final Long deaths = (Long)participantStatsInfo.get("deaths");
        final Long doubleKills = (Long)participantStatsInfo.get("doubleKills");
        final Long goldEarned = (Long)participantStatsInfo.get("goldEarned");
        final Long goldSpent = (Long)participantStatsInfo.get("goldSpent");
        final Long inhibitorKills = (Long)participantStatsInfo.get("inhibitorKills");
        final Long killingSpress = (Long)participantStatsInfo.get("killingSpress");
        final Long kills = (Long)participantStatsInfo.get("kills");
        final Long largestCriticalStrike = (Long)participantStatsInfo.get("largestCriticalStrike");
        final Long largestKillingSpree = (Long)participantStatsInfo.get("largestKillingSpree");
        final Long largestMultiKill = (Long)participantStatsInfo.get("largestMultiKill");
        final Long magicDamageDealt = (Long)participantStatsInfo.get("magicDamageDealt");
        final Long magicDamageDealtToChampions = (Long)participantStatsInfo.get("magicDamageDealtToChampions");
        final Long magicDamageTaken = (Long)participantStatsInfo.get("magicDamageTaken");
        final Long minionsKilled = (Long)participantStatsInfo.get("minionsKilled");
        final Long neutralMinionsKilled = (Long)participantStatsInfo.get("neutralMinionsKilled");
        final Long neutralMinionsKilledEnemyJungle = (Long)participantStatsInfo.get("neutralMinionsKilledEnemyJungle");
        final Long neutralMinionsKilledTeamJungle = (Long)participantStatsInfo.get("neutralMinionsKilledTeamJungle");
        final Long nodeCapture = (Long)participantStatsInfo.get("nodeCapture");
        final Long nodeCaptureAssist = (Long)participantStatsInfo.get("nodeCaptureAssist");
        final Long nodeNeutralize = (Long)participantStatsInfo.get("nodeNeutralize");
        final Long nodeNeutralizeAssist = (Long)participantStatsInfo.get("nodeNeutralizeAssist");
        final Long objectivePlayerScore = (Long)participantStatsInfo.get("objectivePlayerScore");
        final Long pentaKills = (Long)participantStatsInfo.get("pentaKills");
        final Long physicalDamageDealt = (Long)participantStatsInfo.get("physicalDamageDealt");
        final Long physicalDamageDealtToChampions = (Long)participantStatsInfo.get("physicalDamageDealtToChampions");
        final Long physicalDamageTaken = (Long)participantStatsInfo.get("physicalDamageTaken");
        final Long quadraKills = (Long)participantStatsInfo.get("quadraKills");
        final Long sightWardsBoughtInGame = (Long)participantStatsInfo.get("sightWardsBoughtInGame");
        final Long teamObjective = (Long)participantStatsInfo.get("teamObjective");
        final Long totalDamageDealt = (Long)participantStatsInfo.get("totalDamageDealt");
        final Long totalDamageDealtToChampions = (Long)participantStatsInfo.get("totalDamageDealtToChampions");
        final Long totalDamageTaken = (Long)participantStatsInfo.get("totalDamageTaken");
        final Long totalHeal = (Long)participantStatsInfo.get("totalHeal");
        final Long totalPlayerScore = (Long)participantStatsInfo.get("totalPlayerScore");
        final Long totalScoreRank = (Long)participantStatsInfo.get("totalScoreRank");
        final Long totalTimeCrowdControlDealt = (Long)participantStatsInfo.get("totalTimeCrowdControlDealt");
        final Long totalUnitsHealed = (Long)participantStatsInfo.get("totalUnitsHealed");
        final Long towerKills = (Long)participantStatsInfo.get("towerKills");
        final Long tripleKills = (Long)participantStatsInfo.get("tripleKills");
        final Long trueDamageDealt = (Long)participantStatsInfo.get("trueDamageDealt");
        final Long trueDamageDealtToChampions = (Long)participantStatsInfo.get("trueDamageDealtToChampions");
        final Long trueDamageTaken = (Long)participantStatsInfo.get("trueDamageTaken");
        final Long unrealKills = (Long)participantStatsInfo.get("unrealKills");
        final Long visionWardsBoughtInGame = (Long)participantStatsInfo.get("visionWardsBoughtInGame");
        final Long wardsKilled = (Long)participantStatsInfo.get("wardsKilled");
        final Long wardsPlaced = (Long)participantStatsInfo.get("wardsPlaced");
        final Boolean firstBloodAssist = (Boolean)participantStatsInfo.get("firstBloodAssist");
        final Boolean firstBloodKill = (Boolean)participantStatsInfo.get("firstBloodKill");
        final Boolean firstInhibitorAssist = (Boolean)participantStatsInfo.get("firstInhibitorAssist");
        final Boolean firstInhibitorKill = (Boolean)participantStatsInfo.get("firstInhibitorKill");
        final Boolean firstTowerAssist = (Boolean)participantStatsInfo.get("firstTowerAssist");
        final Boolean firstTowerKill = (Boolean)participantStatsInfo.get("firstTowerKill");
        final Boolean winner = (Boolean)participantStatsInfo.get("winner");

        Integer itemID = getInteger(participantStatsInfo, "item0");
        final Item item0 = itemID == 0 ? null : API.getItem(itemID);
        itemID = getInteger(participantStatsInfo, "item1");
        final Item item1 = itemID == 0 ? null : API.getItem(itemID);
        itemID = getInteger(participantStatsInfo, "item2");
        final Item item2 = itemID == 0 ? null : API.getItem(itemID);
        itemID = getInteger(participantStatsInfo, "item3");
        final Item item3 = itemID == 0 ? null : API.getItem(itemID);
        itemID = getInteger(participantStatsInfo, "item4");
        final Item item4 = itemID == 0 ? null : API.getItem(itemID);
        itemID = getInteger(participantStatsInfo, "item5");
        final Item item5 = itemID == 0 ? null : API.getItem(itemID);
        itemID = getInteger(participantStatsInfo, "item6");
        final Item item6 = itemID == 0 ? null : API.getItem(itemID);

        return new ParticipantStats(assists, champLevel, combatPlayerScore, deaths, doubleKills, goldEarned, goldSpent, inhibitorKills, killingSpress, kills,
                largestCriticalStrike, largestKillingSpree, largestMultiKill, magicDamageDealt, magicDamageDealtToChampions, magicDamageTaken, minionsKilled,
                neutralMinionsKilled, neutralMinionsKilledEnemyJungle, neutralMinionsKilledTeamJungle, nodeCapture, nodeCaptureAssist, nodeNeutralize,
                nodeNeutralizeAssist, objectivePlayerScore, pentaKills, physicalDamageDealt, physicalDamageDealtToChampions, physicalDamageTaken, quadraKills,
                sightWardsBoughtInGame, teamObjective, totalDamageDealt, totalDamageDealtToChampions, totalDamageTaken, totalHeal, totalPlayerScore,
                totalScoreRank, totalTimeCrowdControlDealt, totalUnitsHealed, towerKills, tripleKills, trueDamageDealt, trueDamageDealtToChampions,
                trueDamageTaken, unrealKills, visionWardsBoughtInGame, wardsKilled, wardsPlaced, winner, firstBloodAssist, firstBloodKill,
                firstInhibitorAssist, firstInhibitorKill, firstTowerAssist, firstTowerKill, item0, item1, item2, item3, item4, item5, item6);
    }

    public ParticipantTimelineData getParticipantTimelineDataFromJSON(final JSONObject participantTimelineDataInfo) {
        if(participantTimelineDataInfo == null) {
            return null;
        }

        final Double tenToTwenty = (Double)participantTimelineDataInfo.get("tenToTwenty");
        final Double thirtyToEnd = (Double)participantTimelineDataInfo.get("thirtyToEnd");
        final Double twentyToThirty = (Double)participantTimelineDataInfo.get("twentyToThirty");
        final Double zeroToTen = (Double)participantTimelineDataInfo.get("zeroToTen");

        return new ParticipantTimelineData(tenToTwenty, thirtyToEnd, twentyToThirty, zeroToTen);
    }

    public ParticipantTimeline getParticipantTimelineFromJSON(final JSONObject participantTimelineInfo) {
        if(participantTimelineInfo == null) {
            return null;
        }

        final ParticipantTimelineData ancientGolemAssistsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("ancientGolemAssistsPerMinCounts"));
        final ParticipantTimelineData ancientGolemKillsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("ancientGolemKillsPerMinCounts"));
        final ParticipantTimelineData assistedLaneDeathsPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("assistedLaneDeathsPerMinDeltas"));
        final ParticipantTimelineData assistedLaneKillsPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("assistedLaneKillsPerMinDeltas"));
        final ParticipantTimelineData baronAssistsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("baronAssistsPerMinCounts"));
        final ParticipantTimelineData baronKillsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("baronKillsPerMinCounts"));
        final ParticipantTimelineData creepsPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo.get("creepsPerMinDeltas"));
        final ParticipantTimelineData CSDiffPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo.get("csDiffPerMinDeltas"));
        final ParticipantTimelineData damageTakenDiffPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("damageTakenDiffPerMinDeltas"));
        final ParticipantTimelineData damageTakenPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("damageTakenPerMinDeltas"));
        final ParticipantTimelineData dragonAssistsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("dragonAssistsPerMinCounts"));
        final ParticipantTimelineData dragonKillsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("dragonKillsPerMinCounts"));
        final ParticipantTimelineData elderLizardAssistsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("elderLizardAssistsPerMinCounts"));
        final ParticipantTimelineData elderLizardKillsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("elderLizardKillsPerMinCounts"));
        final ParticipantTimelineData goldPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo.get("goldPerMinDeltas"));
        final ParticipantTimelineData inhibitorAssistsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("inhibitorAssistsPerMinCounts"));
        final ParticipantTimelineData inhibitorKillsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("inhibitorKillsPerMinCounts"));
        final ParticipantTimelineData towerAssistsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("towerAssistsPerMinCounts"));
        final ParticipantTimelineData towerKillsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("towerKillsPerMinCounts"));
        final ParticipantTimelineData towerKillsPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("towerKillsPerMinDeltas"));
        final ParticipantTimelineData vilemawAssistsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("vilemawAssistsPerMinCounts"));
        final ParticipantTimelineData vilemawKillsPerMinCounts = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo
                .get("vilemawKillsPerMinCounts"));
        final ParticipantTimelineData wardsPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo.get("wardsPerMinDeltas"));
        final ParticipantTimelineData XPDiffPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo.get("xpDiffPerMinDeltas"));
        final ParticipantTimelineData XPPerMinDeltas = getParticipantTimelineDataFromJSON((JSONObject)participantTimelineInfo.get("xpPerMinDeltas"));
        final Lane lane = Lane.valueOf((String)participantTimelineInfo.get("lane"));
        final Role role = Role.valueOf((String)participantTimelineInfo.get("role"));

        return new ParticipantTimeline(ancientGolemAssistsPerMinCounts, ancientGolemKillsPerMinCounts, assistedLaneDeathsPerMinDeltas,
                assistedLaneKillsPerMinDeltas, baronAssistsPerMinCounts, baronKillsPerMinCounts, creepsPerMinDeltas, CSDiffPerMinDeltas,
                damageTakenDiffPerMinDeltas, damageTakenPerMinDeltas, dragonAssistsPerMinCounts, dragonKillsPerMinCounts, elderLizardAssistsPerMinCounts,
                elderLizardKillsPerMinCounts, goldPerMinDeltas, inhibitorAssistsPerMinCounts, inhibitorKillsPerMinCounts, towerAssistsPerMinCounts,
                towerKillsPerMinCounts, towerKillsPerMinDeltas, vilemawAssistsPerMinCounts, vilemawKillsPerMinCounts, wardsPerMinDeltas, XPDiffPerMinDeltas,
                XPPerMinDeltas, lane, role);
    }

    public Passive getPassiveFromJSON(final JSONObject passiveInfo) {
        if(passiveInfo == null) {
            return null;
        }

        final String description = (String)passiveInfo.get("description");
        final String name = (String)passiveInfo.get("name");
        final String sanitizedDescription = (String)passiveInfo.get("sanitizedDescription");
        final Image image = getImageFromJSON((JSONObject)passiveInfo.get("image"));

        return new Passive(description, name, sanitizedDescription, image);
    }

    public Player getPlayerFromJSON(final JSONObject playerInfo) {
        if(playerInfo == null) {
            return null;
        }

        final String matchHistoryURI = (String)playerInfo.get("matchHistoryUri");
        final Integer profileIconID = getInteger(playerInfo, "profileIcon");
        final Summoner summoner = API.getSummoner((String)playerInfo.get("summonerName"));

        return new Player(matchHistoryURI, summoner, profileIconID);
    }

    public PlayerStatsSummary getPlayerStatsSummaryFromJSON(final JSONObject playerStatsSummaryInfo) {
        if(playerStatsSummaryInfo == null) {
            return null;
        }

        final Integer losses = getInteger(playerStatsSummaryInfo, "losses");
        final Integer wins = getInteger(playerStatsSummaryInfo, "wins");
        final LocalDateTime modifyDate = getDateTime(playerStatsSummaryInfo, "modifyDate");
        final AggregatedStats aggregatedStats = getAggregatedStatsFromJSON((JSONObject)playerStatsSummaryInfo.get("aggregatedStats"));
        final PlayerStatsSummaryType playerStatSummaryType = PlayerStatsSummaryType.valueOf((String)playerStatsSummaryInfo.get("playerStatSummaryType"));

        return new PlayerStatsSummary(aggregatedStats, playerStatSummaryType, modifyDate, wins, losses);
    }

    public Position getPositionFromJSON(final JSONObject positionInfo) {
        if(positionInfo == null) {
            return null;
        }

        final Integer x = getInteger(positionInfo, "x");
        final Integer y = getInteger(positionInfo, "y");

        return new Position(x, y);
    }

    public RawStats getRawStatsFromJSON(final JSONObject rawStatsInfo) {
        if(rawStatsInfo == null) {
            return null;
        }

        final Integer assists = getInteger(rawStatsInfo, "assists");
        final Integer barracksKilled = getInteger(rawStatsInfo, "barracksKilled");
        final Integer championsKilled = getInteger(rawStatsInfo, "championsKilled");
        final Integer combatPlayerScore = getInteger(rawStatsInfo, "combatPlayerScore");
        final Integer consumablesPurchased = getInteger(rawStatsInfo, "consumablesPurchased");
        final Integer damageDealtPlayer = getInteger(rawStatsInfo, "damageDealtPlayer");
        final Integer doubleKills = getInteger(rawStatsInfo, "doubleKills");
        final Integer firstBlood = getInteger(rawStatsInfo, "firstBlood");
        final Integer gold = getInteger(rawStatsInfo, "gold");
        final Integer goldEarned = getInteger(rawStatsInfo, "goldEarned");
        final Integer goldSpent = getInteger(rawStatsInfo, "goldSpent");
        final Integer itemsPurchased = getInteger(rawStatsInfo, "itemsPurchased");
        final Integer killingSprees = getInteger(rawStatsInfo, "killingSprees");
        final Integer largestCriticalStrike = getInteger(rawStatsInfo, "largestCriticalStrike");
        final Integer largestKillingSpree = getInteger(rawStatsInfo, "largestKillingSpree");
        final Integer largestMultiKill = getInteger(rawStatsInfo, "largestMultiKill");
        final Integer legendaryItemsCreated = getInteger(rawStatsInfo, "legendaryItemsCreated");
        final Integer level = getInteger(rawStatsInfo, "level");
        final Integer magicDamageDealtPlayer = getInteger(rawStatsInfo, "magicDamageDealtPlayer");
        final Integer magicDamageDealtToChampions = getInteger(rawStatsInfo, "magicDamageDealtToChampions");
        final Integer magicDamageTaken = getInteger(rawStatsInfo, "magicDamageTaken");
        final Integer minionsDenied = getInteger(rawStatsInfo, "minionsDenied");
        final Integer minionsKilled = getInteger(rawStatsInfo, "minionsKilled");
        final Integer neutralMinionsKilled = getInteger(rawStatsInfo, "neutralMinionsKilled");
        final Integer neutralMinionsKilledEnemyJungle = getInteger(rawStatsInfo, "neutralMinionsKilledEnemyJungle");
        final Integer neutralMinionsKilledYourJungle = getInteger(rawStatsInfo, "neutralMinionsKilledYourJungle");
        final Integer nodeCapture = getInteger(rawStatsInfo, "nodeCapture");
        final Integer nodeCaptureAssist = getInteger(rawStatsInfo, "nodeCaptureAssist");
        final Integer nodeNeutralize = getInteger(rawStatsInfo, "nodeNeutralize");
        final Integer nodeNeutralizeAssist = getInteger(rawStatsInfo, "nodeNeutralizeAssist");
        final Integer numDeaths = getInteger(rawStatsInfo, "numDeaths");
        final Integer numItemsBought = getInteger(rawStatsInfo, "numItemsBought");
        final Integer objectivePlayerScore = getInteger(rawStatsInfo, "objectivePlayerScore");
        final Integer pentaKills = getInteger(rawStatsInfo, "pentaKills");
        final Integer physicalDamageDealtPlayer = getInteger(rawStatsInfo, "physicalDamageDealtPlayer");
        final Integer physicalDamageDealtToChampions = getInteger(rawStatsInfo, "physicalDamageDealtToChampions");
        final Integer physicalDamageTaken = getInteger(rawStatsInfo, "physicalDamageTaken");
        final Integer quadraKills = getInteger(rawStatsInfo, "quadraKills");
        final Integer sightWardsBought = getInteger(rawStatsInfo, "sightWardsBought");
        final Integer spell1Cast = getInteger(rawStatsInfo, "spell1Cast");
        final Integer spell2Cast = getInteger(rawStatsInfo, "spell2Cast");
        final Integer spell3Cast = getInteger(rawStatsInfo, "spell3Cast");
        final Integer spell4Cast = getInteger(rawStatsInfo, "spell4Cast");
        final Integer summonSpell1Cast = getInteger(rawStatsInfo, "summonSpell1Cast");
        final Integer summonSpell2Cast = getInteger(rawStatsInfo, "summonSpell2Cast");
        final Integer superMonsterKilled = getInteger(rawStatsInfo, "superMonsterKilled");
        final Integer team = getInteger(rawStatsInfo, "team");
        final Integer teamObjective = getInteger(rawStatsInfo, "teamObjective");
        final Integer timePlayed = getInteger(rawStatsInfo, "timePlayed");
        final Integer totalDamageDealt = getInteger(rawStatsInfo, "totalDamageDealt");
        final Integer totalDamageDealtToChampions = getInteger(rawStatsInfo, "totalDamageDealtToChampions");
        final Integer totalDamageTaken = getInteger(rawStatsInfo, "totalDamageTaken");
        final Integer totalHeal = getInteger(rawStatsInfo, "totalHeal");
        final Integer totalPlayerScore = getInteger(rawStatsInfo, "totalPlayerScore");
        final Integer totalScoreRank = getInteger(rawStatsInfo, "totalScoreRank");
        final Integer totalTimeCrowdControlDealt = getInteger(rawStatsInfo, "totalTimeCrowdControlDealt");
        final Integer totalUnitsHealed = getInteger(rawStatsInfo, "totalUnitsHealed");
        final Integer tripleKills = getInteger(rawStatsInfo, "tripleKills");
        final Integer trueDamageDealtPlayer = getInteger(rawStatsInfo, "trueDamageDealtPlayer");
        final Integer trueDamageDealtToChampions = getInteger(rawStatsInfo, "trueDamageDealtToChampions");
        final Integer trueDamageTaken = getInteger(rawStatsInfo, "trueDamageTaken");
        final Integer turretsKilled = getInteger(rawStatsInfo, "turretsKilled");
        final Integer unrealKills = getInteger(rawStatsInfo, "unrealKills");
        final Integer victoryPointTotal = getInteger(rawStatsInfo, "victoryPointTotal");
        final Integer visionWardsBought = getInteger(rawStatsInfo, "visionWardsBought");
        final Integer wardKilled = getInteger(rawStatsInfo, "wardKilled");
        final Integer wardPlaced = getInteger(rawStatsInfo, "wardPlaced");
        final Boolean nexusKilled = (Boolean)rawStatsInfo.get("nexusKilled");
        final Boolean win = (Boolean)rawStatsInfo.get("win");
        final Item item0 = getItem(rawStatsInfo, "item0");
        final Item item1 = getItem(rawStatsInfo, "item1");
        final Item item2 = getItem(rawStatsInfo, "item2");
        final Item item3 = getItem(rawStatsInfo, "item3");
        final Item item4 = getItem(rawStatsInfo, "item4");
        final Item item5 = getItem(rawStatsInfo, "item5");
        final Item item6 = getItem(rawStatsInfo, "item6");

        return new RawStats(nexusKilled, win, assists, barracksKilled, championsKilled, combatPlayerScore, consumablesPurchased, damageDealtPlayer,
                doubleKills, firstBlood, gold, goldEarned, goldSpent, itemsPurchased, killingSprees, largestCriticalStrike, largestKillingSpree,
                largestMultiKill, legendaryItemsCreated, level, magicDamageDealtPlayer, magicDamageDealtToChampions, magicDamageTaken, minionsDenied,
                minionsKilled, neutralMinionsKilled, neutralMinionsKilledEnemyJungle, neutralMinionsKilledYourJungle, nodeCapture, nodeCaptureAssist,
                nodeNeutralize, nodeNeutralizeAssist, numDeaths, numItemsBought, objectivePlayerScore, pentaKills, physicalDamageDealtPlayer,
                physicalDamageDealtToChampions, physicalDamageTaken, quadraKills, sightWardsBought, spell1Cast, spell2Cast, spell3Cast, spell4Cast,
                summonSpell1Cast, summonSpell2Cast, superMonsterKilled, team, teamObjective, timePlayed, totalDamageDealt, totalDamageDealtToChampions,
                totalDamageTaken, totalHeal, totalPlayerScore, totalScoreRank, totalTimeCrowdControlDealt, totalUnitsHealed, tripleKills,
                trueDamageDealtPlayer, trueDamageDealtToChampions, trueDamageTaken, turretsKilled, unrealKills, victoryPointTotal, visionWardsBought,
                wardKilled, wardPlaced, item0, item1, item2, item3, item4, item5, item6);
    }

    @SuppressWarnings("unchecked")
    public Realm getRealmFromJSON(final JSONObject realmInfo) {
        if(realmInfo == null) {
            return null;
        }

        final String cdn = (String)realmInfo.get("cdn");
        final String css = (String)realmInfo.get("css");
        final String dd = (String)realmInfo.get("dd");
        final String l = (String)realmInfo.get("l");
        final String lg = (String)realmInfo.get("lg");
        final String store = (String)realmInfo.get("store");
        final String v = (String)realmInfo.get("v");
        final Integer profileIconMax = getInteger(realmInfo, "profileiconmax");
        Map<String, String> n = (Map<String, String>)realmInfo.get("n");
        if(n != null) {
            n = Collections.unmodifiableMap(n);
        }

        return new Realm(cdn, css, dd, l, lg, store, v, profileIconMax, n);
    }

    public Recommended getRecommendedFromJSON(final JSONObject recommendedInfo) {
        if(recommendedInfo == null) {
            return null;
        }

        final String champion = (String)recommendedInfo.get("champion");
        final String map = (String)recommendedInfo.get("map");
        final String mode = (String)recommendedInfo.get("mode");
        final String title = (String)recommendedInfo.get("title");
        final String type = (String)recommendedInfo.get("type");
        final Boolean priority = (Boolean)recommendedInfo.get("priority");
        final List<Block> blocks = getList(recommendedInfo, "blocks", b -> getBlockFromJSON((JSONObject)b));

        return new Recommended(blocks, champion, map, mode, title, type, priority);
    }

    public Roster getRosterFromJSON(final JSONObject rosterInfo) {
        if(rosterInfo == null) {
            return null;
        }

        final JSONArray members = (JSONArray)rosterInfo.get("memberList");
        final List<TeamMemberInformation> memberList = getAllTeamMemberInfoFromJSON(members);
        final Summoner owner = API.getSummonerByID((Long)rosterInfo.get("ownerId"));
        return new Roster(memberList, owner);
    }

    public Roster getRosterFromJSON(final List<Summoner> roster, final JSONObject rosterInfo) {
        if(rosterInfo == null) {
            return null;
        }

        final JSONArray members = (JSONArray)rosterInfo.get("memberList");
        final List<TeamMemberInformation> memberList = getAllTeamMemberInfoFromJSON(roster, members);
        final Summoner owner = API.getSummonerByID((Long)rosterInfo.get("ownerId"));
        return new Roster(memberList, owner);
    }

    @SuppressWarnings("unchecked")
    public Rune getRuneFromJSON(final JSONObject runeInfo) {
        if(runeInfo == null) {
            return null;
        }

        final String colloq = (String)runeInfo.get("colloq");
        final String description = (String)runeInfo.get("description");
        final String group = (String)runeInfo.get("group");
        final String name = (String)runeInfo.get("name");
        final String plaintext = (String)runeInfo.get("plaintext");
        final String requiredChampion = (String)runeInfo.get("requiredChampion");
        final String sanitizedDescription = (String)runeInfo.get("sanitizedDescription");
        final Boolean consumeOnFull = (Boolean)runeInfo.get("consumeOnFull");
        final Boolean consumed = (Boolean)runeInfo.get("consumed");
        final Boolean hideFromAll = (Boolean)runeInfo.get("hideFromAll");
        final Boolean inStore = (Boolean)runeInfo.get("inStore");
        final Integer depth = getInteger(runeInfo, "depth");
        final Integer ID = getInteger(runeInfo, "id");
        final Integer specialRecipe = getInteger(runeInfo, "specialRecipe");
        final Integer stacks = getInteger(runeInfo, "stacks");
        final List<String> from = getStringList(runeInfo, "from");
        final List<String> into = getStringList(runeInfo, "into");
        final List<String> tags = getStringList(runeInfo, "tags");
        final Gold gold = getGoldFromJSON((JSONObject)runeInfo.get("gold"));
        final Image image = getImageFromJSON((JSONObject)runeInfo.get("image"));
        final MetaData rune = getMetaDataFromJSON((JSONObject)runeInfo.get("rune"));
        final BasicDataStats stats = getBasicDataStatsFromJSON((JSONObject)runeInfo.get("stats"));
        Map<String, Boolean> maps = (Map<String, Boolean>)runeInfo.get("maps");
        if(maps != null) {
            maps = Collections.unmodifiableMap(maps);
        }

        return new Rune(colloq, description, group, name, plaintext, requiredChampion, sanitizedDescription, consumeOnFull, consumed, hideFromAll, inStore,
                depth, ID, specialRecipe, stacks, from, into, tags, gold, image, maps, rune, stats);
    }

    public RunePage getRunePageFromJSON(final JSONObject runePageInfo) {
        if(runePageInfo == null) {
            return null;
        }

        final Boolean current = (Boolean)runePageInfo.get("current");
        final Long ID = (Long)runePageInfo.get("id");
        final String name = (String)runePageInfo.get("name");
        final List<RuneSlot> slots = getList(runePageInfo, "slots", s -> getRuneSlotFromJSON((JSONObject)s));

        return new RunePage(current, ID, name, slots);
    }

    public RuneSlot getRuneSlotFromJSON(final JSONObject runeSlotInfo) {
        if(runeSlotInfo == null) {
            return null;
        }

        final Integer runeSlotID = getInteger(runeSlotInfo, "runeSlotId");
        final Integer ID = getInteger(runeSlotInfo, "runeId");
        final Rune rune = API.getRune(ID);

        return new RuneSlot(runeSlotID, rune);
    }

    public Skin getSkinFromJSON(final JSONObject skinInfo) {
        if(skinInfo == null) {
            return null;
        }

        final Integer ID = getInteger(skinInfo, "id");
        final Integer num = getInteger(skinInfo, "num");
        final String name = (String)skinInfo.get("name");

        return new Skin(ID, num, name);
    }

    public SpellVariables getSpellVariablesFromJSON(final JSONObject spellVarsInfo) {
        if(spellVarsInfo == null) {
            return null;
        }

        final List<Double> coeff = getDoubleList(spellVarsInfo, "coeff");
        final String dyn = (String)spellVarsInfo.get("dyn");
        final String key = (String)spellVarsInfo.get("key");
        final String link = (String)spellVarsInfo.get("link");
        final String ranksWith = (String)spellVarsInfo.get("ranksWith");

        return new SpellVariables(dyn, key, link, ranksWith, coeff);
    }

    public Stats getStatsFromJSON(final JSONObject statsInfo) {
        if(statsInfo == null) {
            return null;
        }

        final Double armor = (Double)statsInfo.get("armor");
        final Double armorPerLevel = (Double)statsInfo.get("armorperlevel");
        final Double attackDamage = (Double)statsInfo.get("attackdamage");
        final Double attackDamagePerLevel = (Double)statsInfo.get("attackdamageperlevel");
        final Double attackRange = (Double)statsInfo.get("attackrange");
        final Double attackSpeedOffset = (Double)statsInfo.get("attackspeedoffset");
        final Double attackSpeedPerLevel = (Double)statsInfo.get("attackspeedperlevel");
        final Double crit = (Double)statsInfo.get("crit");
        final Double critPerLevel = (Double)statsInfo.get("critperlevel");
        final Double HP = (Double)statsInfo.get("hp");
        final Double HPPerLevel = (Double)statsInfo.get("hpperlevel");
        final Double HPRegen = (Double)statsInfo.get("hpregen");
        final Double HPRegenPerLevel = (Double)statsInfo.get("hpregenperlevel");
        final Double moveSpeed = (Double)statsInfo.get("movespeed");
        final Double MP = (Double)statsInfo.get("mp");
        final Double MPPerLevel = (Double)statsInfo.get("mpperlevel");
        final Double MPRegen = (Double)statsInfo.get("mpregen");
        final Double MPRegenPerLevel = (Double)statsInfo.get("mpregenperlevel");
        final Double spellBlock = (Double)statsInfo.get("spellblock");
        final Double spellBlockPerLevel = (Double)statsInfo.get("spellblockperlevel");

        return new Stats(armor, armorPerLevel, attackDamage, attackDamagePerLevel, attackRange, attackSpeedOffset, attackSpeedPerLevel, crit, critPerLevel, HP,
                HPPerLevel, HPRegen, HPRegenPerLevel, moveSpeed, MP, MPPerLevel, MPRegen, MPRegenPerLevel, spellBlock, spellBlockPerLevel);
    }

    public Summoner getSummonerFromJSON(final JSONObject summonerInfo) {
        if(summonerInfo == null) {
            return null;
        }

        final Long ID = (Long)summonerInfo.get("id");
        final Long summonerLevel = (Long)summonerInfo.get("summonerLevel");
        final Integer profileIconID = getInteger(summonerInfo, "profileIconId");
        final LocalDateTime revisionDate = getDateTime(summonerInfo, "revisionDate");
        final String name = (String)summonerInfo.get("name");

        return new Summoner(ID, summonerLevel, name, profileIconID, revisionDate);
    }

    public SummonerSpell getSummonerSpellFromJSON(final JSONObject summonerSpellInfo) {
        final List<Double> cooldown = getDoubleList(summonerSpellInfo, "cooldown");
        final String cooldownBurn = (String)summonerSpellInfo.get("cooldownBurn");
        final String costBurn = (String)summonerSpellInfo.get("costBurn");
        final String costType = (String)summonerSpellInfo.get("costType");
        final String description = (String)summonerSpellInfo.get("description");
        final String key = (String)summonerSpellInfo.get("key");
        final String name = (String)summonerSpellInfo.get("name");
        final String rangeBurn = (String)summonerSpellInfo.get("rangeBurn");
        final String resource = (String)summonerSpellInfo.get("resource");
        final String sanitizedDescription = (String)summonerSpellInfo.get("sanitizedDescription");
        final String sanitizedTooltip = (String)summonerSpellInfo.get("sanitizedTooltip");
        final String tooltip = (String)summonerSpellInfo.get("tooltip");
        final List<Integer> cost = getIntegerList(summonerSpellInfo, "cost");
        final List<List<Double>> effect = getList(summonerSpellInfo, "effect", l -> getDoubleList((JSONArray)l));
        final List<String> effectBurn = getStringList(summonerSpellInfo, "effectBurn");
        final List<String> modes = getStringList(summonerSpellInfo, "modes");
        final Integer ID = getInteger(summonerSpellInfo, "id");
        final Integer maxRank = getInteger(summonerSpellInfo, "maxrank");
        final Integer summonerLevel = getInteger(summonerSpellInfo, "summonerLevel");
        final Image image = getImageFromJSON((JSONObject)summonerSpellInfo.get("image"));
        final LevelTip levelTip = getLevelTipFromJSON((JSONObject)summonerSpellInfo.get("leveltip"));
        final List<SpellVariables> vars = getList(summonerSpellInfo, "vars", v -> getSpellVariablesFromJSON((JSONObject)v));

        List<Integer> range;
        final Object val = summonerSpellInfo.get("range");
        if(val instanceof String) {
            range = null;
        }
        else {
            range = getIntegerList((JSONArray)val);
        }

        return new SummonerSpell(cooldownBurn, costBurn, costType, description, key, name, rangeBurn, resource, sanitizedDescription, sanitizedTooltip,
                tooltip, cooldown, cost, range, effect, effectBurn, modes, ID, maxRank, summonerLevel, image, levelTip, vars);
    }

    public Team getTeamFromJSON(final JSONObject teamInfo) {
        if(teamInfo == null) {
            return null;
        }

        final LocalDateTime createDate = getDateTime(teamInfo, "createDate");
        final LocalDateTime lastGameDate = getDateTime(teamInfo, "lastGameDate");
        final LocalDateTime lastJoinDate = getDateTime(teamInfo, "lastJoinDate");
        final LocalDateTime lastJoinedRankedTeamQueueDate = getDateTime(teamInfo, "lastJoinedRankedTeamQueueDate");
        final LocalDateTime modifyDate = getDateTime(teamInfo, "modifyDate");
        final LocalDateTime secondLastJoinDate = getDateTime(teamInfo, "secondLastDate");
        final LocalDateTime thirdLastJoinDate = getDateTime(teamInfo, "thirdLastDate");
        final String ID = (String)teamInfo.get("fullId");
        final String name = (String)teamInfo.get("name");
        final String status = (String)teamInfo.get("status");
        final String tag = (String)teamInfo.get("tag");
        final Roster roster = getRosterFromJSON((JSONObject)teamInfo.get("roster"));
        final List<MatchHistorySummary> matchHistory = getList(teamInfo, "matchHistory", m -> getMatchHistorySummaryFromJSON((JSONObject)m));
        final List<TeamStatDetail> teamStatDetails = getList(teamInfo, "teamStatDetails", t -> getTeamStatDetailFromJSON((JSONObject)t));

        return new Team(createDate, lastGameDate, lastJoinDate, lastJoinedRankedTeamQueueDate, modifyDate, secondLastJoinDate, thirdLastJoinDate, ID, name,
                status, tag, matchHistory, roster, teamStatDetails);
    }

    private Team getTeamFromJSON(final List<Summoner> rosterSummoners, final JSONObject teamInfo) {
        if(teamInfo == null) {
            return null;
        }

        final LocalDateTime createDate = getDateTime(teamInfo, "createDate");
        final LocalDateTime lastGameDate = getDateTime(teamInfo, "lastGameDate");
        final LocalDateTime lastJoinDate = getDateTime(teamInfo, "lastJoinDate");
        final LocalDateTime lastJoinedRankedTeamQueueDate = getDateTime(teamInfo, "lastJoinedRankedTeamQueueDate");
        final LocalDateTime modifyDate = getDateTime(teamInfo, "modifyDate");
        final LocalDateTime secondLastJoinDate = getDateTime(teamInfo, "secondLastDate");
        final LocalDateTime thirdLastJoinDate = getDateTime(teamInfo, "thirdLastDate");
        final String ID = (String)teamInfo.get("fullId");
        final String name = (String)teamInfo.get("name");
        final String status = (String)teamInfo.get("status");
        final String tag = (String)teamInfo.get("tag");
        final Roster roster = getRosterFromJSON(rosterSummoners, (JSONObject)teamInfo.get("roster"));
        final List<MatchHistorySummary> matchHistory = getList(teamInfo, "matchHistory", m -> getMatchHistorySummaryFromJSON((JSONObject)m));
        final List<TeamStatDetail> teamStatDetails = getList(teamInfo, "teamStatDetails", t -> getTeamStatDetailFromJSON((JSONObject)t));

        return new Team(createDate, lastGameDate, lastJoinDate, lastJoinedRankedTeamQueueDate, modifyDate, secondLastJoinDate, thirdLastJoinDate, ID, name,
                status, tag, matchHistory, roster, teamStatDetails);
    }

    public TeamMemberInformation getTeamMemberInfoFromJSON(final JSONObject teamMemberInfo) {
        if(teamMemberInfo == null) {
            return null;
        }

        return getTeamMemberInfoFromJSON(API.getSummonerByID((Long)teamMemberInfo.get("playerId")), teamMemberInfo);
    }

    private TeamMemberInformation getTeamMemberInfoFromJSON(final Summoner player, final JSONObject teamMemberInfo) {
        if(teamMemberInfo == null) {
            return null;
        }

        final String status = (String)teamMemberInfo.get("status");
        final LocalDateTime inviteDate = getDateTime(teamMemberInfo, "inviteDate");
        final LocalDateTime joinDate = getDateTime(teamMemberInfo, "joinDate");

        return new TeamMemberInformation(inviteDate, joinDate, player, status);
    }

    public TeamStatDetail getTeamStatDetailFromJSON(final JSONObject teamStatDetailInfo) {
        if(teamStatDetailInfo == null) {
            return null;
        }

        final String teamStatType = (String)teamStatDetailInfo.get("teamStatType");
        final Integer averageGamesPlayed = getInteger(teamStatDetailInfo, "averageGamesPlayed");
        final Integer losses = getInteger(teamStatDetailInfo, "losses");
        final Integer wins = getInteger(teamStatDetailInfo, "wins");

        return new TeamStatDetail(averageGamesPlayed, losses, wins, teamStatType);
    }
}
