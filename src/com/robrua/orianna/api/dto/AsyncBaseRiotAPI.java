package com.robrua.orianna.api.dto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.type.api.Action;
import com.robrua.orianna.type.api.RateLimit;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.dto.championmastery.ChampionMastery;
import com.robrua.orianna.type.dto.currentgame.CurrentGameInfo;
import com.robrua.orianna.type.dto.featuredgames.FeaturedGames;
import com.robrua.orianna.type.dto.game.RecentGames;
import com.robrua.orianna.type.dto.league.League;
import com.robrua.orianna.type.dto.match.MatchDetail;
import com.robrua.orianna.type.dto.matchlist.MatchList;
import com.robrua.orianna.type.dto.staticdata.Item;
import com.robrua.orianna.type.dto.staticdata.ItemList;
import com.robrua.orianna.type.dto.staticdata.LanguageStrings;
import com.robrua.orianna.type.dto.staticdata.MapData;
import com.robrua.orianna.type.dto.staticdata.Mastery;
import com.robrua.orianna.type.dto.staticdata.MasteryList;
import com.robrua.orianna.type.dto.staticdata.Realm;
import com.robrua.orianna.type.dto.staticdata.Rune;
import com.robrua.orianna.type.dto.staticdata.RuneList;
import com.robrua.orianna.type.dto.staticdata.SummonerSpell;
import com.robrua.orianna.type.dto.staticdata.SummonerSpellList;
import com.robrua.orianna.type.dto.stats.PlayerStatsSummaryList;
import com.robrua.orianna.type.dto.stats.RankedStats;
import com.robrua.orianna.type.dto.status.Shard;
import com.robrua.orianna.type.dto.status.ShardStatus;
import com.robrua.orianna.type.dto.summoner.MasteryPages;
import com.robrua.orianna.type.dto.summoner.RunePages;
import com.robrua.orianna.type.dto.summoner.Summoner;
import com.robrua.orianna.type.dto.team.Team;
import com.robrua.orianna.type.exception.APIException;

/**
 * Queries the <a href="http://developer.riotgames.com/api/methods">LoL REST
 * API</a> asynchronously
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class AsyncBaseRiotAPI {
    /**
     * @param action
     *            what to do with the challenger league
     * @param queueType
     *            the queue type to get the challenger league for
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3243">
     *      Riot API Specification</a>
     */
    public static void getChallenger(final Action<League> action, final QueueType queueType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getChallenger(queueType);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getChallenger(queueType));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champion
     * @param ID
     *            the champion's ID
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3256">
     *      Riot API Specification</a>
     */
    public static void getChampion(final Action<com.robrua.orianna.type.dto.staticdata.Champion> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getChampion(ID);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getChampion(ID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champion mastery
     * @param summonerID
     *            the ID of the summoner to get mastery for
     * @param championID
     *            the ID of the champion to get mastery for
     * @see <a href="https://developer.riotgames.com/api/methods#!/1071/3697">
     *      Riot API Specification</a>
     */
    public static void getChampionMastery(final Action<ChampionMastery> action, final long summonerID, final long championID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getChampionMastery(summonerID, championID);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getChampionMastery(summonerID, championID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champion masteries
     * @param summonerID
     *            the summoner to get champion mastery for
     * @see <a href="https://developer.riotgames.com/api/methods#!/1071/3696">
     *      Riot API Specification</a>
     */
    public static void getChampionMastery(final Action<List<ChampionMastery>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getChampionMastery(summonerID);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getChampionMastery(summonerID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the list all of champions
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3260">
     *      Riot API Specification</a>
     */
    public static void getChampions(final Action<com.robrua.orianna.type.dto.staticdata.ChampionList> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getChampions();
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getChampions());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champion
     * @param ID
     *            the ID of the champion to look up
     * @see <a href="https://developer.riotgames.com/api/methods#!/958/3289">
     *      Riot API Specification</a>
     */
    public static void getChampionStatus(final Action<com.robrua.orianna.type.dto.champion.Champion> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionAPI.getChampionStatus(ID);
                }
                else {
                    try {
                        action.perform(ChampionAPI.getChampionStatus(ID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with all champions
     * @param freeToPlay
     *            whether to only return free champions
     * @see <a href="https://developer.riotgames.com/api/methods#!/958/3290">
     *      Riot API Specification</a>
     */
    public static void getChampionStatuses(final Action<com.robrua.orianna.type.dto.champion.ChampionList> action, final boolean freeToPlay) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionAPI.getChampionStatuses(freeToPlay);
                }
                else {
                    try {
                        action.perform(ChampionAPI.getChampionStatuses(freeToPlay));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's current game
     * @param summonerID
     *            summoner to look up current game for
     * @see <a href="https://developer.riotgames.com/api/methods#!/956/3287">
     *      Riot API Specification</a>
     */
    public static void getCurrentGame(final Action<CurrentGameInfo> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    CurrentGameAPI.getCurrentGame(summonerID);
                }
                else {
                    try {
                        action.perform(CurrentGameAPI.getCurrentGame(summonerID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the featured games
     * @see <a href="https://developer.riotgames.com/api/methods#!/957/3288">
     *      Riot API Specification</a>
     */
    public static void getFeaturedGames(final Action<FeaturedGames> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    FeaturedGamesAPI.getFeaturedGames();
                }
                else {
                    try {
                        action.perform(FeaturedGamesAPI.getFeaturedGames());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the item
     * @param ID
     *            the item's ID
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3253">
     *      Riot API Specification</a>
     */
    public static void getItem(final Action<Item> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getItem(ID);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getItem(ID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the list of all items
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3248">
     *      Riot API Specification</a>
     */
    public static void getItems(final Action<ItemList> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getItems();
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getItems());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the languages
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3258">
     *      Riot API Specification</a>
     */
    public static void getLanguages(final Action<List<String>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getLanguages();
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getLanguages());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the language strings
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3250">
     *      Riot API Specification</a>
     */
    public static void getLanguageStrings(final Action<LanguageStrings> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getLanguageStrings();
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getLanguageStrings());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the map information
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3262">
     *      Riot API Specification</a>
     */
    public static void getMapInformation(final Action<MapData> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getMapInformation();
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getMapInformation());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the master league
     * @param queueType
     *            the queue type to get the master league for
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3354">
     *      Riot API Specification</a>
     */
    public static void getMaster(final Action<League> action, final QueueType queueType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getMaster(queueType);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getMaster(queueType));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the list of all masteries
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3251">
     *      Riot API Specification</a>
     */
    public static void getMasteries(final Action<MasteryList> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getMasteries();
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getMasteries());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the mastery
     * @param ID
     *            the mastery's ID
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3252">
     *      Riot API Specification</a>
     */
    public static void getMastery(final Action<Mastery> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getMastery(ID);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getMastery(ID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match
     * @param ID
     *            the ID of the match to look up
     * @see <a href="https://developer.riotgames.com/api/methods#!/967/3313">
     *      Riot API Specification</a>
     */
    public static void getMatch(final Action<MatchDetail> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchAPI.getMatch(ID);
                }
                else {
                    try {
                        action.perform(MatchAPI.getMatch(ID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match
     * @param ID
     *            the ID of the match to look up
     * @param includeTimeline
     *            whether to include timeline data in the returned match
     * @see <a href="https://developer.riotgames.com/api/methods#!/967/3313">
     *      Riot API Specification</a>
     */
    public static void getMatch(final Action<MatchDetail> action, final long ID, final boolean includeTimeline) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchAPI.getMatch(ID, includeTimeline);
                }
                else {
                    try {
                        action.perform(MatchAPI.getMatch(ID, includeTimeline));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final int numMatches) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, numMatches);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, numMatches));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final int numMatches, final int beginIndex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, numMatches, beginIndex);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, numMatches, beginIndex));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final int numMatches, final int beginIndex, final long beginTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final int numMatches, final int beginIndex, final long beginTime,
            final long endTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime, endTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime, endTime));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final int numMatches, final int beginIndex, final long beginTime,
            final long endTime, final List<QueueType> queueTypes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime, endTime, queueTypes);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime, endTime, queueTypes));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final int numMatches, final int beginIndex, final long beginTime,
            final long endTime, final List<QueueType> queueTypes, final List<Long> championIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @param seasons
     *            the seasons to limit games to
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final int numMatches, final int beginIndex, final long beginTime,
            final long endTime, final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs, seasons);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs, seasons));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final long beginTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, beginTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, beginTime));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final long beginTime, final long endTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, beginTime, endTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, beginTime, endTime));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final long beginTime, final long endTime,
            final List<QueueType> queueTypes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, beginTime, endTime, queueTypes);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, beginTime, endTime, queueTypes));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final long beginTime, final long endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, beginTime, endTime, queueTypes, championIDs);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, beginTime, endTime, queueTypes, championIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @param seasons
     *            the seasons to limit games to
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static void getMatchList(final Action<MatchList> action, final long summonerID, final long beginTime, final long endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerID, beginTime, endTime, queueTypes, championIDs, seasons);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerID, beginTime, endTime, queueTypes, championIDs, seasons));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the ranked stats for that summoner
     * @param summonerID
     *            the ID of the summoner to get ranked stats for
     * @see <a href="https://developer.riotgames.com/api/methods#!/961/3297">
     *      Riot API Specification</a>
     */
    public static void getRankedStats(final Action<RankedStats> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StatsAPI.getRankedStats(summonerID);
                }
                else {
                    try {
                        action.perform(StatsAPI.getRankedStats(summonerID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the ranked stats for that summoner
     * @param summonerID
     *            the ID of the summoner to get ranked stats for
     * @param season
     *            the season to get stats for
     * @see <a href="https://developer.riotgames.com/api/methods#!/961/3297">
     *      Riot API Specification</a>
     */
    public static void getRankedStats(final Action<RankedStats> action, final long summonerID, final Season season) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StatsAPI.getRankedStats(summonerID, season);
                }
                else {
                    try {
                        action.perform(StatsAPI.getRankedStats(summonerID, season));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the realm
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3259">
     *      Riot API Specification</a>
     */
    public static void getRealm(final Action<Realm> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getRealm();
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getRealm());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's recent games
     * @param summonerID
     *            the ID of the summoner to look up recent games for
     * @see <a href="https://developer.riotgames.com/api/methods#!/959/3291">
     *      Riot API Specification</a>
     */
    public static void getRecentGames(final Action<RecentGames> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    GameAPI.getRecentGames(summonerID);
                }
                else {
                    try {
                        action.perform(GameAPI.getRecentGames(summonerID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the rune
     * @param ID
     *            the rune's ID
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3255">
     *      Riot API Specification</a>
     */
    public static void getRune(final Action<Rune> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getRune(ID);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getRune(ID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the list of all runes
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3249">
     *      Riot API Specification</a>
     */
    public static void getRunes(final Action<RuneList> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getRunes();
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getRunes());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the shard
     * @param region
     *            the region's shard to get
     * @see <a href="https://developer.riotgames.com/api/methods#!/908/3142">
     *      Riot API Specification</a>
     */
    public static void getShard(final Action<ShardStatus> action, final Region region) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StatusAPI.getShard(region);
                }
                else {
                    try {
                        action.perform(StatusAPI.getShard(region));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the list of all shards
     * @see <a href="https://developer.riotgames.com/api/methods#!/908/3143">
     *      Riot API Specification</a>
     */
    public static void getShards(final Action<List<Shard>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StatusAPI.getShards();
                }
                else {
                    try {
                        action.perform(StatusAPI.getShards());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the stats for that summoner
     * @param summonerID
     *            the ID of the summoner to get stats for
     * @see <a href="https://developer.riotgames.com/api/methods#!/961/3298">
     *      Riot API Specification</a>
     */
    public static void getStats(final Action<PlayerStatsSummaryList> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StatsAPI.getStats(summonerID);
                }
                else {
                    try {
                        action.perform(StatsAPI.getStats(summonerID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the stats for that summoner
     * @param summonerID
     *            the ID of the summoner to get stats for
     * @param season
     *            the season to get stats for
     * @see <a href="https://developer.riotgames.com/api/methods#!/961/3298">
     *      Riot API Specification</a>
     */
    public static void getStats(final Action<PlayerStatsSummaryList> action, final long summonerID, final Season season) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StatsAPI.getStats(summonerID, season);
                }
                else {
                    try {
                        action.perform(StatsAPI.getStats(summonerID, season));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' league entries
     * @param summonerIDs
     *            the summoners to get league entries for
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3245">
     *      Riot API Specification</a>
     */
    public static void getSummonerLeagueEntries(final Action<Map<Long, List<League>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getSummonerLeagueEntries(summonerIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getSummonerLeagueEntries(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' league entries
     * @param summonerIDs
     *            the summoners to get league entries for
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3245">
     *      Riot API Specification</a>
     */
    public static void getSummonerLeagueEntries(final Action<Map<Long, List<League>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getSummonerLeagueEntries(summonerIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getSummonerLeagueEntries(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' league
     * @param summonerIDs
     *            the summoners to get leagues for
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3241">
     *      Riot API Specification</a>
     */
    public static void getSummonerLeagues(final Action<Map<Long, List<League>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getSummonerLeagues(summonerIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getSummonerLeagues(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' league
     * @param summonerIDs
     *            the summoners to get leagues for
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3241">
     *      Riot API Specification</a>
     */
    public static void getSummonerLeagues(final Action<Map<Long, List<League>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getSummonerLeagues(summonerIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getSummonerLeagues(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners
     * @param summonerIDs
     *            the IDs of the summoners to get
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3293">
     *      Riot API Specification</a>
     */
    public static void getSummonersByID(final Action<Map<Long, Summoner>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersByID(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersByID(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners
     * @param summonerIDs
     *            the IDs of the summoners to get
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3293">
     *      Riot API Specification</a>
     */
    public static void getSummonersByID(final Action<Map<Long, Summoner>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersByID(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersByID(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners
     * @param summonerNames
     *            the names of the summoners to get
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3292">
     *      Riot API Specification</a>
     */
    public static void getSummonersByName(final Action<Map<String, Summoner>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersByName(summonerNames);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersByName(summonerNames));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners
     * @param summonerNames
     *            the names of the summoners to get
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3292">
     *      Riot API Specification</a>
     */
    public static void getSummonersByName(final Action<Map<String, Summoner>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersByName(summonerNames);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersByName(summonerNames));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' masteries
     * @param summonerIDs
     *            the IDs of the summoners to get masteries for
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3295">
     *      Riot API Specification</a>
     */
    public static void getSummonersMasteries(final Action<Map<Long, MasteryPages>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersMasteries(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersMasteries(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' masteries
     * @param summonerIDs
     *            the IDs of the summoners to get masteries for
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3295">
     *      Riot API Specification</a>
     */
    public static void getSummonersMasteries(final Action<Map<Long, MasteryPages>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersMasteries(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersMasteries(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' names
     * @param summonerIDs
     *            the IDs of the summoners to get names of
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3296">
     *      Riot API Specification</a>
     */
    public static void getSummonersNames(final Action<Map<Long, String>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersNames(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersNames(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' names
     * @param summonerIDs
     *            the IDs of the summoners to get names of
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3296">
     *      Riot API Specification</a>
     */
    public static void getSummonersNames(final Action<Map<Long, String>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersNames(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersNames(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner spell
     * @param ID
     *            the summoner spell's ID
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3254">
     *      Riot API Specification</a>
     */
    public static void getSummonerSpell(final Action<SummonerSpell> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getSummonerSpell(ID);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getSummonerSpell(ID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the list of all summoner spells
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3261">
     *      Riot API Specification</a>
     */
    public static void getSummonerSpells(final Action<SummonerSpellList> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getSummonerSpells();
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getSummonerSpells());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' runes
     * @param summonerIDs
     *            the IDs of the summoners to get runes for
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3294">
     *      Riot API Specification</a>
     */
    public static void getSummonersRunes(final Action<Map<Long, RunePages>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersRunes(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersRunes(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' runes
     * @param summonerIDs
     *            the IDs of the summoners to get runes for
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3294">
     *      Riot API Specification</a>
     */
    public static void getSummonersRunes(final Action<Map<Long, RunePages>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersRunes(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersRunes(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the team's leagues
     * @param teamIDs
     *            the summoners to get leagues for
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3242">
     *      Riot API Specification</a>
     */
    public static void getTeamLeagueEntries(final Action<Map<String, List<League>>> action, final List<String> teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getTeamLeagueEntries(teamIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getTeamLeagueEntries(teamIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the team's leagues
     * @param teamIDs
     *            the summoners to get leagues for
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3242">
     *      Riot API Specification</a>
     */
    public static void getTeamLeagueEntries(final Action<Map<String, List<League>>> action, final String... teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getTeamLeagueEntries(teamIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getTeamLeagueEntries(teamIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the team's leagues
     * @param teamIDs
     *            the summoners to get leagues for
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3242">
     *      Riot API Specification</a>
     */
    public static void getTeamLeagues(final Action<Map<String, List<League>>> action, final List<String> teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getTeamLeagues(teamIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getTeamLeagues(teamIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the team's leagues
     * @param teamIDs
     *            the summoners to get leagues for
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3242">
     *      Riot API Specification</a>
     */
    public static void getTeamLeagues(final Action<Map<String, List<League>>> action, final String... teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getTeamLeagues(teamIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getTeamLeagues(teamIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams
     * @param IDs
     *            the IDs of the teams
     * @see <a href="https://developer.riotgames.com/api/methods#!/937/3246">
     *      Riot API Specification</a>
     */
    public static void getTeamsByID(final Action<Map<String, Team>> action, final List<String> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsByID(IDs);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsByID(IDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams
     * @param IDs
     *            the IDs of the teams
     * @see <a href="https://developer.riotgames.com/api/methods#!/937/3246">
     *      Riot API Specification</a>
     */
    public static void getTeamsByID(final Action<Map<String, Team>> action, final String... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsByID(IDs);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsByID(IDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' teams
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     * @see <a href="https://developer.riotgames.com/api/methods#!/937/3247">
     *      Riot API Specification</a>
     */
    public static void getTeamsBySummoner(final Action<Map<Long, List<Team>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummoner(summonerIDs);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummoner(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' teams
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     * @see <a href="https://developer.riotgames.com/api/methods#!/937/3247">
     *      Riot API Specification</a>
     */
    public static void getTeamsBySummoner(final Action<Map<Long, List<Team>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummoner(summonerIDs);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummoner(summonerIDs));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the masteries
     * @param summonerID
     *            the summoner to get masteries for
     * @param count
     *            the number of top champions to get
     * @see <a href="https://developer.riotgames.com/api/methods#!/1071/3692">
     *      Riot API Specification</a>
     */
    public static void getTopChampionMastery(final Action<List<ChampionMastery>> action, final long summonerID, final int count) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getTopChampionMastery(summonerID, count);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getTopChampionMastery(summonerID, count));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the result
     * @param summonerID
     *            the summoner to get total mastery level for
     * @see <a href="https://developer.riotgames.com/api/methods#!/1071/3698">
     *      Riot API Specification</a>
     */
    public static void getTotalMasteryLevel(final Action<Integer> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getTotalMasteryLevel(summonerID);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getTotalMasteryLevel(summonerID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the versions
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3257">
     *      Riot API Specification</a>
     */
    public static void getVersions(final Action<List<String>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getVersions();
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getVersions());
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * If turned on, prints the URI of calls made to stdout
     *
     * @param on
     *            whether to print the URI of calls to stdout
     */
    public static void printCalls(final boolean on) {
        BaseRiotAPI.printCalls(on);
    }

    /**
     * Reset locale to the default
     */
    public static void removeLocale() {
        BaseRiotAPI.removeLocale();
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
     * Sets the locale (language) to return API results for
     *
     * @param newLocale
     *            the locale to return results for
     */
    public static void setLocale(final String newLocale) {
        BaseRiotAPI.setLocale(newLocale);
    }

    /**
     * Sets the server mirror to hit for queries
     *
     * @param newMirror
     *            the server mirror to hit for queries
     *
     * @deprecated Region and Mirror must always match now. This method now does
     *             nothing.
     */
    @Deprecated
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
     *            the rate limit
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
