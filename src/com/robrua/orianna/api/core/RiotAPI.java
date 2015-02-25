package com.robrua.orianna.api.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.store.Cache;
import com.robrua.orianna.store.DataStore;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.api.MultiRateLimiter;
import com.robrua.orianna.type.api.RateLimit;
import com.robrua.orianna.type.api.RateLimiter;
import com.robrua.orianna.type.core.champion.ChampionStatus;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.match.Match;
import com.robrua.orianna.type.core.matchhistory.MatchSummary;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.staticdata.Item;
import com.robrua.orianna.type.core.staticdata.MapDetails;
import com.robrua.orianna.type.core.staticdata.Mastery;
import com.robrua.orianna.type.core.staticdata.Realm;
import com.robrua.orianna.type.core.staticdata.Rune;
import com.robrua.orianna.type.core.staticdata.SummonerSpell;
import com.robrua.orianna.type.core.stats.ChampionStats;
import com.robrua.orianna.type.core.stats.PlayerStatsSummary;
import com.robrua.orianna.type.core.stats.PlayerStatsSummaryType;
import com.robrua.orianna.type.core.status.Shard;
import com.robrua.orianna.type.core.status.ShardStatus;
import com.robrua.orianna.type.core.summoner.MasteryPage;
import com.robrua.orianna.type.core.summoner.RunePage;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.core.team.Team;

/**
 * Accesses the <a href="http://developer.riotgames.com/api/methods">LoL REST
 * API</a> and provides results in easy-to-use Java objects.
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public abstract class RiotAPI {
    static LoadPolicy loadPolicy = LoadPolicy.UPFRONT;
    static DataStore store = new Cache();

    /**
     * @param queueType
     *            the ranked queue type to get the challenger league of
     * @return the challenger league
     */
    public static League getChallenger(final QueueType queueType) {
        return LeagueAPI.getChallenger(queueType);
    }

    /**
     * @param ID
     *            the ID of the champion to get
     * @return the champion
     */
    public static Champion getChampion(final long ID) {
        return StaticDataAPI.getChampion(ID);
    }

    /**
     * @return all the champions
     */
    public static List<Champion> getChampions() {
        return StaticDataAPI.getChampions();
    }

    /**
     * @param IDs
     *            the IDs of the champions to get
     * @return the champions
     */
    public static List<Champion> getChampions(final List<Long> IDs) {
        return StaticDataAPI.getChampions(IDs);
    }

    /**
     * @param IDs
     *            the IDs of the champions to get
     * @return the champions
     */
    public static List<Champion> getChampions(final long... IDs) {
        return StaticDataAPI.getChampions(IDs);
    }

    /**
     * @param champion
     *            the champion to get status for
     * @return the champion's status
     */
    public static ChampionStatus getChampionStatus(final Champion champion) {
        return ChampionAPI.getChampionStatus(champion);
    }

    /**
     * @param ID
     *            the ID of the champion to get status for
     * @return the champion's status
     */
    public static ChampionStatus getChampionStatus(final long ID) {
        return ChampionAPI.getChampionStatus(ID);
    }

    /**
     * @return all champion's statuses
     */
    public static Map<Champion, ChampionStatus> getChampionStatuses() {
        return ChampionAPI.getChampionStatuses();
    }

    /**
     * @param freeToPlay
     *            whether to only get free to play champions
     * @return all (possibly only free to play) champion's statuses
     */
    public static Map<Champion, ChampionStatus> getChampionStatuses(final boolean freeToPlay) {
        return ChampionAPI.getChampionStatuses(freeToPlay);
    }

    /**
     * @param IDs
     *            the IDs of the champions to get statuses for
     * @return the champions' statuses
     */
    public static List<ChampionStatus> getChampionStatuses(final List<Long> IDs) {
        return ChampionAPI.getChampionStatuses(IDs);
    }

    /**
     * @param IDs
     *            the IDs of the champions to get statuses for
     * @return the champions' statuses
     */
    public static List<ChampionStatus> getChampionStatuses(final long... IDs) {
        return ChampionAPI.getChampionStatuses(IDs);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get current game for
     * @return the summoner's current game or null if they aren't in one
     */
    public static CurrentGame getCurrentGame(final long summonerID) {
        return CurrentGameAPI.getCurrentGame(summonerID);
    }

    /**
     * @param summonerName
     *            the name of the summoner to get current game for
     * @return the summoner's current game or null if they aren't in one
     */
    public static CurrentGame getCurrentGame(final String summonerName) {
        return CurrentGameAPI.getCurrentGame(summonerName);
    }

    /**
     * @param summoner
     *            the summoner to get current game for
     * @return the summoner's current game or null if they aren't in one
     */
    public static CurrentGame getCurrentGame(final Summoner summoner) {
        return CurrentGameAPI.getCurrentGame(summoner);
    }

    /**
     * Gets a rate limiter for the default development rate limit
     *
     * @return default development rate limiter
     */
    public static RateLimiter getDefaultDevelopmentRateLimiter() {
        return new MultiRateLimiter(new RateLimit(10, 10), new RateLimit(500, 600));
    }

    /**
     * @return the current featured games
     */
    public static List<CurrentGame> getFeaturedGames() {
        return FeaturedGamesAPI.getFeaturedGames();
    }

    /**
     * @param ID
     *            the ID of the item to get
     * @return the item
     */
    public static Item getItem(final long ID) {
        return StaticDataAPI.getItem(ID);
    }

    /**
     * @return all the items
     */
    public static List<Item> getItems() {
        return StaticDataAPI.getItems();
    }

    /**
     * @param IDs
     *            the IDs of the items to get
     * @return the items
     */
    public static List<Item> getItems(final List<Long> IDs) {
        return StaticDataAPI.getItems(IDs);
    }

    /**
     * @param IDs
     *            the IDs of the items to get
     * @return the items
     */
    public static List<Item> getItems(final long... IDs) {
        return StaticDataAPI.getItems(IDs);
    }

    /**
     * @return the languages
     */
    public static List<String> getLanguages() {
        return StaticDataAPI.getLanguages();
    }

    /**
     * @return the language strings
     */
    public static Map<String, String> getLanguageStrings() {
        return StaticDataAPI.getLanguageStrings();
    }

    /**
     * @param summoners
     *            the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummoner(final List<Summoner> summoners) {
        return LeagueAPI.getLeagueEntriesBySummoner(summoners);
    }

    /**
     * @param summoners
     *            the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummoner(final Summoner... summoners) {
        return LeagueAPI.getLeagueEntriesBySummoner(summoners);
    }

    /**
     * @param summoner
     *            the summoner to get league entries for
     * @return the summoner's league entries
     */
    public static List<League> getLeagueEntriesBySummoner(final Summoner summoner) {
        return LeagueAPI.getLeagueEntriesBySummoner(summoner);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummonerID(final List<Long> summonerIDs) {
        return LeagueAPI.getLeagueEntriesBySummonerID(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummonerID(final long... summonerIDs) {
        return LeagueAPI.getLeagueEntriesBySummonerID(summonerIDs);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get league entries for
     * @return the summoner's league entries
     */
    public static List<League> getLeagueEntriesBySummonerID(final long summonerID) {
        return LeagueAPI.getLeagueEntriesBySummonerID(summonerID);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummonerName(final List<String> summonerNames) {
        return LeagueAPI.getLeagueEntriesBySummonerName(summonerNames);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummonerName(final String... summonerNames) {
        return LeagueAPI.getLeagueEntriesBySummonerName(summonerNames);
    }

    /**
     * @param summonerName
     *            the name of the summoner to get league entries for
     * @return the summoner's league entries
     */
    public static List<League> getLeagueEntriesBySummonerName(final String summonerName) {
        return LeagueAPI.getLeagueEntriesBySummonerName(summonerName);
    }

    /**
     * @param teams
     *            the teams to get league entries for
     * @return the teams' league entries
     */
    public static List<List<League>> getLeagueEntriesByTeam(final List<Team> teams) {
        return LeagueAPI.getLeagueEntriesByTeam(teams);
    }

    /**
     * @param teams
     *            the teams to get league entries for
     * @return the teams' league entries
     */
    public static List<List<League>> getLeagueEntriesByTeam(final Team... teams) {
        return LeagueAPI.getLeagueEntriesByTeam(teams);
    }

    /**
     * @param team
     *            the team to get league entries for
     * @return the team's league entries
     */
    public static List<League> getLeagueEntriesByTeam(final Team team) {
        return LeagueAPI.getLeagueEntriesByTeam(team);
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get league entries for
     * @return the teams' league entries
     */
    public static List<List<League>> getLeagueEntriesByTeamID(final List<String> teamIDs) {
        return LeagueAPI.getLeagueEntriesByTeamID(teamIDs);
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get league entries for
     * @return the teams' league entries
     */
    public static List<List<League>> getLeagueEntriesByTeamID(final String... teamIDs) {
        return LeagueAPI.getLeagueEntriesByTeamID(teamIDs);
    }

    /**
     * @param teamID
     *            the ID of the team to get league entries for
     * @return the team's league entries
     */
    public static List<League> getLeagueEntriesByTeamID(final String teamID) {
        return LeagueAPI.getLeagueEntriesByTeamID(teamID);
    }

    /**
     * @param summoners
     *            the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummoner(final List<Summoner> summoners) {
        return LeagueAPI.getLeaguesBySummoner(summoners);
    }

    /**
     * @param summoners
     *            the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummoner(final Summoner... summoners) {
        return LeagueAPI.getLeaguesBySummoner(summoners);
    }

    /**
     * @param summoner
     *            the summoner to get leagues for
     * @return the summoner's leagues
     */
    public static List<League> getLeaguesBySummoner(final Summoner summoner) {
        return LeagueAPI.getLeaguesBySummoner(summoner);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummonerID(final List<Long> summonerIDs) {
        return LeagueAPI.getLeaguesBySummonerID(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummonerID(final long... summonerIDs) {
        return LeagueAPI.getLeaguesBySummonerID(summonerIDs);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get leagues for
     * @return the summoner's leagues
     */
    public static List<League> getLeaguesBySummonerID(final long summonerID) {
        return LeagueAPI.getLeaguesBySummonerID(summonerID);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummonerName(final List<String> summonerNames) {
        return LeagueAPI.getLeaguesBySummonerName(summonerNames);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummonerName(final String... summonerNames) {
        return LeagueAPI.getLeaguesBySummonerName(summonerNames);
    }

    /**
     * @param summonerName
     *            the name of the summoner to get leagues for
     * @return the summoner's leagues
     */
    public static List<League> getLeaguesBySummonerName(final String summonerName) {
        return LeagueAPI.getLeaguesBySummonerName(summonerName);
    }

    /**
     * @param teams
     *            the teams to get leagues for
     * @return the teams' leagues
     */
    public static List<List<League>> getLeaguesByTeam(final List<Team> teams) {
        return LeagueAPI.getLeaguesByTeam(teams);
    }

    /**
     * @param teams
     *            the teams to get leagues for
     * @return the teams' leagues
     */
    public static List<List<League>> getLeaguesByTeam(final Team... teams) {
        return LeagueAPI.getLeaguesByTeam(teams);
    }

    /**
     * @param team
     *            the team to get leagues for
     * @return the team's leagues
     */
    public static List<League> getLeaguesByTeam(final Team team) {
        return LeagueAPI.getLeaguesByTeam(team);
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get leagues for
     * @return the teams' leagues
     */
    public static List<List<League>> getLeaguesByTeamID(final List<String> teamIDs) {
        return LeagueAPI.getLeaguesByTeamID(teamIDs);
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get leagues for
     * @return the teams' leagues
     */
    public static List<List<League>> getLeaguesByTeamID(final String... teamIDs) {
        return LeagueAPI.getLeaguesByTeamID(teamIDs);
    }

    /**
     * @param teamID
     *            the ID of the team to get leagues for
     * @return the team's leagues
     */
    public static List<League> getLeaguesByTeamID(final String teamID) {
        return LeagueAPI.getLeaguesByTeamID(teamID);
    }

    /**
     * @return information for the maps
     */
    public static List<MapDetails> getMapInformation() {
        return StaticDataAPI.getMapInformation();
    }

    /**
     * @return all the masteries
     */
    public static List<Mastery> getMasteries() {
        return StaticDataAPI.getMasteries();
    }

    /**
     * @param IDs
     *            the IDs of the masteries to get
     * @return the masteries
     */
    public static List<Mastery> getMasteries(final List<Long> IDs) {
        return StaticDataAPI.getMasteries(IDs);
    }

    /**
     * @param IDs
     *            the IDs of the masteries to get
     * @return the masteries
     */
    public static List<Mastery> getMasteries(final long... IDs) {
        return StaticDataAPI.getMasteries(IDs);
    }

    /**
     * @param ID
     *            the ID of the mastery to get
     * @return the mastery
     */
    public static Mastery getMastery(final long ID) {
        return StaticDataAPI.getMastery(ID);
    }

    public static Mastery getMasteryByID(final long ID) {
        return null;
    }

    /**
     * @param summoners
     *            the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPages(final List<Summoner> summoners) {
        return SummonerAPI.getMasteryPages(summoners);
    }

    /**
     * @param summoners
     *            the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPages(final Summoner... summoners) {
        return SummonerAPI.getMasteryPages(summoners);
    }

    /**
     * @param summoner
     *            the summoner to get mastery pages for
     * @return the summoner's mastery pages
     */
    public static List<MasteryPage> getMasteryPages(final Summoner summoner) {
        return SummonerAPI.getMasteryPages(summoner);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPagesByID(final List<Long> summonerIDs) {
        return SummonerAPI.getMasteryPagesByID(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPagesByID(final long... summonerIDs) {
        return SummonerAPI.getMasteryPagesByID(summonerIDs);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get mastery pages for
     * @return the summoner's mastery pages
     */
    public static List<MasteryPage> getMasteryPagesByID(final long summonerID) {
        return SummonerAPI.getMasteryPagesByID(summonerID);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPagesByName(final List<String> summonerNames) {
        return SummonerAPI.getMasteryPagesByName(summonerNames);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPagesByName(final String... summonerNames) {
        return SummonerAPI.getMasteryPagesByName(summonerNames);
    }

    /**
     * @param summonerName
     *            the name of the summoner to get mastery pages for
     * @return the summoner's mastery pages
     */
    public static List<MasteryPage> getMasteryPagesByName(final String summonerName) {
        return SummonerAPI.getMasteryPagesByName(summonerName);
    }

    /**
     * @param ID
     *            the ID of the match to get
     * @return the match
     */
    public static Match getMatch(final long ID) {
        return MatchAPI.getMatch(ID);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID) {
        return MatchHistoryAPI.getMatchHistory(summonerID);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @return the 15 most recent matches after beginIndex for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID, final int beginIndex) {
        return MatchHistoryAPI.getMatchHistory(summonerID, beginIndex);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param championIDs
     *            the champions to limit games to
     * @return the 15 most recent matches after beginIndex for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID, final int beginIndex, final List<Long> championIDs) {
        return MatchHistoryAPI.getMatchHistory(summonerID, beginIndex, championIDs);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the 15 most recent matches after beginIndex for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID, final int beginIndex, final QueueType queueType) {
        return MatchHistoryAPI.getMatchHistory(summonerID, beginIndex, queueType);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @return the 15 most recent matches after beginIndex for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID, final int beginIndex, final QueueType queueType, final List<Long> championIDs) {
        return MatchHistoryAPI.getMatchHistory(summonerID, beginIndex, queueType, championIDs);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @param championIDs
     *            the champions to limit games to
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID, final List<Long> championIDs) {
        return MatchHistoryAPI.getMatchHistory(summonerID, championIDs);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID, final QueueType queueType) {
        return MatchHistoryAPI.getMatchHistory(summonerID, queueType);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID, final QueueType queueType, final List<Long> championIDs) {
        return MatchHistoryAPI.getMatchHistory(summonerID, queueType, championIDs);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner) {
        return MatchHistoryAPI.getMatchHistory(summoner);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @return the 15 most recent matches after beginIndex for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner, final int beginIndex) {
        return MatchHistoryAPI.getMatchHistory(summoner, beginIndex);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param champions
     *            the champions to limit games to
     * @return the 15 most recent matches after beginIndex for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner, final int beginIndex, final List<Champion> champions) {
        return MatchHistoryAPI.getMatchHistory(summoner, beginIndex, champions);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the 15 most recent matches after beginIndex for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner, final int beginIndex, final QueueType queueType) {
        return MatchHistoryAPI.getMatchHistory(summoner, beginIndex, queueType);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param champions
     *            the champions to limit games to
     * @return the 15 most recent matches after beginIndex for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner, final int beginIndex, final QueueType queueType, final List<Champion> champions) {
        return MatchHistoryAPI.getMatchHistory(summoner, beginIndex, queueType, champions);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @param champions
     *            the champions to limit games to
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner, final List<Champion> champions) {
        return MatchHistoryAPI.getMatchHistory(summoner, champions);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner, final QueueType queueType) {
        return MatchHistoryAPI.getMatchHistory(summoner, queueType);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param champions
     *            the champions to limit games to
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner, final QueueType queueType, final List<Champion> champions) {
        return MatchHistoryAPI.getMatchHistory(summoner, queueType, champions);
    }

    /**
     * @param summonerID
     *            the summoner to get ranked stats for
     * @return the ranked stats for the summoner by champion (null entry
     *         contains totals over all champions)
     */
    public static Map<Champion, ChampionStats> getRankedStats(final long summonerID) {
        return StatsAPI.getRankedStats(summonerID);
    }

    /**
     * @param summonerID
     *            the summoner to get ranked stats for
     * @param season
     *            the season to get ranked stats for
     * @return the ranked stats for the summoner by champion (null entry
     *         contains totals over all champions)
     */
    public static Map<Champion, ChampionStats> getRankedStats(final long summonerID, final Season season) {
        return StatsAPI.getRankedStats(summonerID, season);
    }

    /**
     * @param summoner
     *            the summoner to get ranked stats for
     * @return the ranked stats for the summoner by champion (null entry
     *         contains totals over all champions)
     */
    public static Map<Champion, ChampionStats> getRankedStats(final Summoner summoner) {
        return StatsAPI.getRankedStats(summoner);
    }

    /**
     * @param summoner
     *            the summoner to get ranked stats for
     * @param season
     *            the season to get ranked stats for
     * @return the ranked stats for the summoner by champion (null entry
     *         contains totals over all champions)
     */
    public static Map<Champion, ChampionStats> getRankedStats(final Summoner summoner, final Season season) {
        return StatsAPI.getRankedStats(summoner, season);
    }

    /**
     * @return the realm
     */
    public static Realm getRealm() {
        return StaticDataAPI.getRealm();
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get recent games for
     * @return the summoner's recent games
     */
    public static List<Game> getRecentGames(final long summonerID) {
        return GameAPI.getRecentGames(summonerID);
    }

    /**
     * @param summonerName
     *            the name of the summoner to get recent games for
     * @return the summoner's recent games
     */
    public static List<Game> getRecentGames(final String summonerName) {
        return GameAPI.getRecentGames(summonerName);
    }

    /**
     * @param summoner
     *            the summoner to get recent games for
     * @return the summoner's recent games
     */
    public static List<Game> getRecentGames(final Summoner summoner) {
        return GameAPI.getRecentGames(summoner);
    }

    /**
     * @param ID
     *            the ID of the rune to get
     * @return the rune
     */
    public static Rune getRune(final long ID) {
        return StaticDataAPI.getRune(ID);
    }

    public static Rune getRuneByID(final long ID) {
        return null;
    }

    /**
     * @param summoners
     *            the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePages(final List<Summoner> summoners) {
        return SummonerAPI.getRunePages(summoners);
    }

    /**
     * @param summoners
     *            the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePages(final Summoner... summoners) {
        return SummonerAPI.getRunePages(summoners);
    }

    /**
     * @param summoner
     *            the summoner to get rune pages for
     * @return the summoner's rune pages
     */
    public static List<RunePage> getRunePages(final Summoner summoner) {
        return SummonerAPI.getRunePages(summoner);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePagesByID(final List<Long> summonerIDs) {
        return SummonerAPI.getRunePagesByID(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePagesByID(final long... summonerIDs) {
        return SummonerAPI.getRunePagesByID(summonerIDs);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get rune pages for
     * @return the summoner's rune pages
     */
    public static List<RunePage> getRunePagesByID(final long summonerID) {
        return SummonerAPI.getRunePagesByID(summonerID);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePagesByName(final List<String> summonerNames) {
        return SummonerAPI.getRunePagesByName(summonerNames);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePagesByName(final String... summonerNames) {
        return SummonerAPI.getRunePagesByName(summonerNames);
    }

    /**
     * @param summonerName
     *            the name of the summoner to get rune pages for
     * @return the summoner's rune pages
     */
    public static List<RunePage> getRunePagesByName(final String summonerName) {
        return SummonerAPI.getRunePagesByName(summonerName);
    }

    /**
     * @return all the runes
     */
    public static List<Rune> getRunes() {
        return StaticDataAPI.getRunes();
    }

    /**
     * @param IDs
     *            the IDs of the runes to get
     * @return the runes
     */
    public static List<Rune> getRunes(final List<Long> IDs) {
        return StaticDataAPI.getRunes(IDs);
    }

    /**
     * @param IDs
     *            the IDs of the runes to get
     * @return the runes
     */
    public static List<Rune> getRunes(final long... IDs) {
        return StaticDataAPI.getRunes(IDs);
    }

    /**
     * @param region
     *            the region to get the shard for
     * @return the status shard for that region
     */
    public static ShardStatus getShard(final Region region) {
        return StatusAPI.getShard(region);
    }

    /**
     * @return the status shard list
     */
    public static List<Shard> getShards() {
        return StatusAPI.getShards();
    }

    /**
     * @param summonerID
     *            the summoner to get stats for
     * @return the stats for the summoner by queue type
     */
    public static Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final long summonerID) {
        return StatsAPI.getStats(summonerID);
    }

    /**
     * @param summonerID
     *            the summoner to get stats for
     * @param season
     *            the season to get stats for
     * @return the stats for the summoner by queue type
     */
    public static Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final long summonerID, final Season season) {
        return StatsAPI.getStats(summonerID, season);
    }

    /**
     * @param summoner
     *            the summoner to get stats for
     * @return the stats for the summoner by queue type
     */
    public static Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final Summoner summoner) {
        return StatsAPI.getStats(summoner);
    }

    /**
     * @param summoner
     *            the summoner to get stats for
     * @param season
     *            the season to get stats for
     * @return the stats for the summoner by queue type
     */
    public static Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final Summoner summoner, final Season season) {
        return StatsAPI.getStats(summoner, season);
    }

    /**
     * @param ID
     *            the ID of the summoner to get
     * @return the summoner
     */
    public static Summoner getSummonerByID(final long ID) {
        return SummonerAPI.getSummonerByID(ID);
    }

    /**
     * @param name
     *            the name of the summoner to get
     * @return the summoners
     */
    public static Summoner getSummonerByName(final String name) {
        return SummonerAPI.getSummonerByName(name);
    }

    /**
     * @param ID
     *            the ID of the summoner to get the names of
     * @return the summoner's name
     */
    public static String getSummonerName(final long ID) {
        return SummonerAPI.getSummonerName(ID);
    }

    /**
     * @param IDs
     *            the IDs of the summoners to get
     * @return the summoners
     */
    public static List<Summoner> getSummonersByID(final List<Long> IDs) {
        return SummonerAPI.getSummonersByID(IDs);
    }

    /**
     * @param IDs
     *            the IDs of the summoners to get
     * @return the summoners
     */
    public static List<Summoner> getSummonersByID(final long... IDs) {
        return SummonerAPI.getSummonersByID(IDs);
    }

    /**
     * @param names
     *            the names of the summoners to get
     * @return the summoners
     */
    public static List<Summoner> getSummonersByName(final List<String> names) {
        return SummonerAPI.getSummonersByName(names);
    }

    /**
     * @param names
     *            the names of the summoners to get
     * @return the summoners
     */
    public static List<Summoner> getSummonersByName(final String... names) {
        return SummonerAPI.getSummonersByName(names);
    }

    /**
     * @param IDs
     *            the IDs of the summoners to get the names of
     * @return the summoners' names
     */
    public static List<String> getSummonersNames(final List<Long> IDs) {
        return SummonerAPI.getSummonersNames(IDs);
    }

    /**
     * @param IDs
     *            the IDs of the summoners to get the names of
     * @return the summoners' names
     */
    public static List<String> getSummonersNames(final long... IDs) {
        return SummonerAPI.getSummonersNames(IDs);
    }

    /**
     * @param ID
     *            the ID of the summoner spell to get
     * @return the summoner spell
     */
    public static SummonerSpell getSummonerSpell(final long ID) {
        return StaticDataAPI.getSummonerSpell(ID);
    }

    public static SummonerSpell getSummonerSpellByID(final long ID) {
        return null;
    }

    /**
     * @return all the summoner spells
     */
    public static List<SummonerSpell> getSummonerSpells() {
        return StaticDataAPI.getSummonerSpells();
    }

    /**
     * @param IDs
     *            the IDs of the summoner spells to get
     * @return the summoner spells
     */
    public static List<SummonerSpell> getSummonerSpells(final List<Long> IDs) {
        return StaticDataAPI.getSummonerSpells(IDs);
    }

    /**
     * @param IDs
     *            the IDs of the summoner spells to get
     * @return the summoner spells
     */
    public static List<SummonerSpell> getSummonerSpells(final long... IDs) {
        return StaticDataAPI.getSummonerSpells(IDs);
    }

    /**
     * @param teamID
     *            the ID of the team to get
     * @return the team
     */
    public static Team getTeam(final String teamID) {
        return TeamAPI.getTeam(teamID);
    }

    public static Team getTeamByID(final String ID) {
        return null;
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get
     * @return the teams
     */
    public static List<Team> getTeams(final List<String> teamIDs) {
        return TeamAPI.getTeams(teamIDs);
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get
     * @return the teams
     */
    public static List<Team> getTeams(final String... teamIDs) {
        return TeamAPI.getTeams(teamIDs);
    }

    /**
     * @param summoners
     *            the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummoner(final List<Summoner> summoners) {
        return TeamAPI.getTeamsBySummoner(summoners);
    }

    /**
     * @param summoners
     *            the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummoner(final Summoner... summoners) {
        return TeamAPI.getTeamsBySummoner(summoners);
    }

    /**
     * @param summoner
     *            the summoner to get teams for
     * @return the summoners' teams
     */
    public static List<Team> getTeamsBySummoner(final Summoner summoner) {
        return TeamAPI.getTeamsBySummoner(summoner);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummonerID(final List<Long> summonerIDs) {
        return TeamAPI.getTeamsBySummonerID(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummonerID(final long... summonerIDs) {
        return TeamAPI.getTeamsBySummonerID(summonerIDs);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get teams for
     * @return the summoner's teams
     */
    public static List<Team> getTeamsBySummonerID(final long summonerID) {
        return TeamAPI.getTeamsBySummonerID(summonerID);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummonerName(final List<String> summonerNames) {
        return TeamAPI.getTeamsBySummonerName(summonerNames);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummonerName(final String... summonerNames) {
        return TeamAPI.getTeamsBySummonerName(summonerNames);
    }

    /**
     * @param summonerName
     *            the names of the summoner to get teams for
     * @return the summoner's teams
     */
    public static List<List<Team>> getTeamsBySummonerName(final String summonerName) {
        return TeamAPI.getTeamsBySummonerName(summonerName);
    }

    /**
     * @return the versions
     */
    public static List<String> getVersions() {
        return StaticDataAPI.getVersions();
    }

    /**
     * Removes any set proxy
     */
    public static void removeProxy() {
        BaseRiotAPI.removeProxy();
    }

    /**
     * Sets the API Key to use for queries
     *
     * @param newAPIKey
     *            the API Key to use for queries
     */
    public static void setAPIKey(final String newAPIKey) {
        BaseRiotAPI.setAPIKey(newAPIKey);
    }

    /**
     * Sets the load policy for the API
     *
     * @param policy
     *            the new load policy
     */
    public static void setLoadPolicy(final LoadPolicy policy) {
        loadPolicy = policy;
    }

    /**
     * Sets the server mirror to hit for queries
     *
     * @param newMirror
     *            the server mirror to hit for queries
     */
    public static void setMirror(final Region newMirror) {
        BaseRiotAPI.setMirror(newMirror);
    }

    /**
     * Sets the proxy to access the API through
     *
     * @param IP
     *            the IP address of the proxy server
     * @param port
     *            the working port for the proxy server
     */
    public static void setProxy(final String IP, final int port) {
        BaseRiotAPI.setProxy(IP, port);
    }

    /**
     * Sets multiple new rate limits for the API, removing any old ones
     *
     * @param limits
     *            the rate limits to apply
     */
    public static void setRateLimit(final Collection<RateLimit> limits) {
        BaseRiotAPI.setRateLimit(limits);
    }

    /**
     * Sets a new rate limit for the API, removing any old ones
     *
     * @param callsPerEpoch
     *            the number of calls allowed in each epoch
     * @param secondsPerEpoch
     *            the number of seconds in each epoch
     */
    public static void setRateLimit(final int callsPerEpoch, final int secondsPerEpoch) {
        BaseRiotAPI.setRateLimit(callsPerEpoch, secondsPerEpoch);
    }

    /**
     * Sets a new rate limit for the API, removing any old ones
     *
     * @param limit
     *            the new rate limit
     */
    public static void setRateLimit(final RateLimit limit) {
        BaseRiotAPI.setRateLimit(limit);
    }

    /**
     * Sets multiple new rate limits for the API, removing any old ones
     *
     * @param limits
     *            the rate limits to apply
     */
    public static void setRateLimit(final RateLimit... limits) {
        BaseRiotAPI.setRateLimit(limits);
    }

    /**
     * Sets the region to get information from the API for
     *
     * @param newRegion
     *            the region to get information from the API for
     */
    public static void setRegion(final Region newRegion) {
        BaseRiotAPI.setRegion(newRegion);
    }
}
