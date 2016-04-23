package com.robrua.orianna.api.core;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.store.DataStore;
import com.robrua.orianna.type.api.Action;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.api.RateLimit;
import com.robrua.orianna.type.core.champion.ChampionStatus;
import com.robrua.orianna.type.core.championmastery.ChampionMastery;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.match.Match;
import com.robrua.orianna.type.core.matchlist.MatchReference;
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
import com.robrua.orianna.type.exception.APIException;

/**
 * Accesses the <a href="http://developer.riotgames.com/api/methods">LoL REST
 * API</a> asynchronously and provides results in easy-to-use Java objects.
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public abstract class AsyncRiotAPI {

    /**
     * @param action
     *            what to do with the challenger league
     * @param queueType
     *            the ranked queue type to get the challenger league of
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
     *            the ID of the champion to get
     */
    public static void getChampionByID(final Action<Champion> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getChampionByID(ID);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getChampionByID(ID));
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
     * @param name
     *            the name of the champion to get
     */
    public static void getChampionByName(final Action<Champion> action, final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getChampionByName(name);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getChampionByName(name));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param action
     *            what to do with the mastery information
     * @param summonerID
     *            the summoner to get rating for
     * @param champion
     *            the champion to get rating for
     */
    public static void getChampionMastery(final Action<ChampionMastery> action, final long summonerID, final Champion champion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getChampionMastery(summonerID, champion);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getChampionMastery(summonerID, champion));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param action
     *            what to do with the mastery information
     * @param summonerID
     *            the summoner to get rating for
     * @param championID
     *            the champion to get rating for
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
     * Gets the summoner's mastery on a champion
     *
     * @param action
     *            what to do with the mastery information
     * @param summonerName
     *            the summoner to get rating for
     * @param champion
     *            the champion to get rating for
     */
    public static void getChampionMastery(final Action<ChampionMastery> action, final String summonerName, final Champion champion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getChampionMastery(summonerName, champion);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getChampionMastery(summonerName, champion));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param action
     *            what to do with the mastery information
     * @param summonerName
     *            the summoner to get rating for
     * @param championID
     *            the champion to get rating for
     */
    public static void getChampionMastery(final Action<ChampionMastery> action, final String summonerName, final long championID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getChampionMastery(summonerName, championID);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getChampionMastery(summonerName, championID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param action
     *            what to do with the mastery information
     * @param summoner
     *            the summoner to get rating for
     * @param champion
     *            the champion to get rating for
     */
    public static void getChampionMastery(final Action<ChampionMastery> action, final Summoner summoner, final Champion champion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getChampionMastery(summoner, champion);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getChampionMastery(summoner, champion));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param action
     *            what to do with the mastery information
     * @param summoner
     *            the summoner to get rating for
     * @param championID
     *            the champion to get rating for
     */
    public static void getChampionMastery(final Action<ChampionMastery> action, final Summoner summoner, final long championID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getChampionMastery(summoner, championID);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getChampionMastery(summoner, championID));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the champion mastery ratings for a summoner
     *
     * @param action
     *            what to do with the mastery information
     * @param summonerID
     *            the summoner to get ratings for
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
     * Gets the champion mastery ratings for a summoner
     *
     * @param action
     *            what to do with the mastery information
     * @param summonerName
     *            the summoner to get ratings for
     */
    public static void getChampionMastery(final Action<List<ChampionMastery>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getChampionMastery(summonerName);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getChampionMastery(summonerName));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the champion mastery ratings for a summoner
     *
     * @param action
     *            what to do with the mastery information
     * @param summoner
     *            the summoner to get ratings for
     */
    public static void getChampionMastery(final Action<List<ChampionMastery>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getChampionMastery(summoner);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getChampionMastery(summoner));
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
     *            what to do with all the champions
     */
    public static void getChampions(final Action<List<Champion>> action) {
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
     *            what to do with the champions
     * @param IDs
     *            the IDs of the champions to get
     */
    public static void getChampionsByID(final Action<List<Champion>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getChampionsByID(IDs);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getChampionsByID(IDs));
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
     *            what to do with the champions
     * @param IDs
     *            the IDs of the champions to get
     */
    public static void getChampionsByID(final Action<List<Champion>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getChampionsByID(IDs);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getChampionsByID(IDs));
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
     *            what to do with the champions
     * @param names
     *            the names of the champions to get
     */
    public static void getChampionsByName(final Action<List<Champion>> action, final List<String> names) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getChampionsByName(names);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getChampionsByName(names));
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
     *            what to do with the champions
     * @param names
     *            the names of the champions to get
     */
    public static void getChampionsByName(final Action<List<Champion>> action, final String... names) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getChampionsByName(names);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getChampionsByName(names));
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
     *            what to do with the champion's status
     * @param champion
     *            the champion to get status for
     */
    public static void getChampionStatus(final Action<ChampionStatus> action, final Champion champion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionAPI.getChampionStatus(champion);
                }
                else {
                    try {
                        action.perform(ChampionAPI.getChampionStatus(champion));
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
     *            what to do with the champion's status
     * @param ID
     *            the ID of the champion to get status for
     */
    public static void getChampionStatus(final Action<ChampionStatus> action, final long ID) {
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
     *            what to do with the champions' statuses
     * @param IDs
     *            the IDs of the champions to get statuses for
     */
    public static void getChampionStatuses(final Action<List<ChampionStatus>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionAPI.getChampionStatuses(IDs);
                }
                else {
                    try {
                        action.perform(ChampionAPI.getChampionStatuses(IDs));
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
     *            what to do with the champions' statuses
     * @param IDs
     *            the IDs of the champions to get statuses for
     */
    public static void getChampionStatuses(final Action<List<ChampionStatus>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionAPI.getChampionStatuses(IDs);
                }
                else {
                    try {
                        action.perform(ChampionAPI.getChampionStatuses(IDs));
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
     *            what to do with all champion's statuses
     */
    public static void getChampionStatuses(final Action<Map<Champion, ChampionStatus>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionAPI.getChampionStatuses();
                }
                else {
                    try {
                        action.perform(ChampionAPI.getChampionStatuses());
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
     *            what to do with all (possibly only free to play) champion's
     *            statuses
     * @param freeToPlay
     *            whether to only get free to play champions
     */
    public static void getChampionStatuses(final Action<Map<Champion, ChampionStatus>> action, final boolean freeToPlay) {
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
     *            what to do with the summoner's current game or null if they
     *            aren't in one
     * @param summonerID
     *            the ID of the summoner to get current game for
     */
    public static void getCurrentGame(final Action<CurrentGame> action, final long summonerID) {
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
     *            what to do with the summoner's current game or null if they
     *            aren't in one
     * @param summonerName
     *            the name of the summoner to get current game for
     */
    public static void getCurrentGame(final Action<CurrentGame> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    CurrentGameAPI.getCurrentGame(summonerName);
                }
                else {
                    try {
                        action.perform(CurrentGameAPI.getCurrentGame(summonerName));
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
     *            what to do with the summoner's current game or null if they
     *            aren't in one
     * @param summoner
     *            the summoner to get current game for
     */
    public static void getCurrentGame(final Action<CurrentGame> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    CurrentGameAPI.getCurrentGame(summoner);
                }
                else {
                    try {
                        action.perform(CurrentGameAPI.getCurrentGame(summoner));
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
     *            what to do with the current featured games
     */
    public static void getFeaturedGames(final Action<List<CurrentGame>> action) {
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
     *            the ID of the item to get
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
     *            what to do with all the items
     */
    public static void getItems(final Action<List<Item>> action) {
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
     *            what to do with the items
     * @param IDs
     *            the IDs of the items to get
     */
    public static void getItems(final Action<List<Item>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getItems(IDs);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getItems(IDs));
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
     *            what to do with the items
     * @param IDs
     *            the IDs of the items to get
     */
    public static void getItems(final Action<List<Item>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getItems(IDs);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getItems(IDs));
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
     */
    public static void getLanguageStrings(final Action<Map<String, String>> action) {
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
     *            what to do with the summoner's league entries
     * @param summoner
     *            the summoner to get league entries for
     */
    public static void getLeagueEntriesBySummoner(final Action<List<League>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesBySummoner(summoner);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesBySummoner(summoner));
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
     * @param summoners
     *            the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummoner(final Action<List<List<League>>> action, final List<Summoner> summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesBySummoner(summoners);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesBySummoner(summoners));
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
     * @param summoners
     *            the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummoner(final Action<List<List<League>>> action, final Summoner... summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesBySummoner(summoners);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesBySummoner(summoners));
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
     *            what to do with the summoner's league entries
     * @param summonerID
     *            the ID of the summoner to get league entries for
     */
    public static void getLeagueEntriesBySummonerID(final Action<List<League>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesBySummonerID(summonerID);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesBySummonerID(summonerID));
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
     *            the IDs of the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummonerID(final Action<List<List<League>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesBySummonerID(summonerIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesBySummonerID(summonerIDs));
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
     *            the IDs of the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummonerID(final Action<List<List<League>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesBySummonerID(summonerIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesBySummonerID(summonerIDs));
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
     *            what to do with the summoner's league entries
     * @param summonerName
     *            the name of the summoner to get league entries for
     */
    public static void getLeagueEntriesBySummonerName(final Action<List<League>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesBySummonerName(summonerName);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesBySummonerName(summonerName));
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
     * @param summonerNames
     *            the names of the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummonerName(final Action<List<List<League>>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesBySummonerName(summonerNames);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesBySummonerName(summonerNames));
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
     * @param summonerNames
     *            the names of the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummonerName(final Action<List<List<League>>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesBySummonerName(summonerNames);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesBySummonerName(summonerNames));
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
     *            what to do with the team's league entries
     * @param team
     *            the team to get league entries for
     */
    public static void getLeagueEntriesByTeam(final Action<List<League>> action, final Team team) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesByTeam(team);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesByTeam(team));
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
     *            what to do with the teams' league entries
     * @param teams
     *            the teams to get league entries for
     */
    public static void getLeagueEntriesByTeam(final Action<List<List<League>>> action, final List<Team> teams) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesByTeam(teams);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesByTeam(teams));
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
     *            what to do with the teams' league entries
     * @param teams
     *            the teams to get league entries for
     */
    public static void getLeagueEntriesByTeam(final Action<List<List<League>>> action, final Team... teams) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesByTeam(teams);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesByTeam(teams));
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
     *            what to do with the team's league entries
     * @param teamID
     *            the ID of the team to get league entries for
     */
    public static void getLeagueEntriesByTeamID(final Action<List<League>> action, final String teamID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesByTeamID(teamID);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesByTeamID(teamID));
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
     *            what to do with the teams' league entries
     * @param teamIDs
     *            the IDs of the teams to get league entries for
     */
    public static void getLeagueEntriesByTeamID(final Action<List<List<League>>> action, final List<String> teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesByTeamID(teamIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesByTeamID(teamIDs));
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
     *            what to do with the teams' league entries
     * @param teamIDs
     *            the IDs of the teams to get league entries for
     */
    public static void getLeagueEntriesByTeamID(final Action<List<List<League>>> action, final String... teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeagueEntriesByTeamID(teamIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeagueEntriesByTeamID(teamIDs));
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
     *            what to do with the summoner's leagues
     * @param summoner
     *            the summoner to get leagues for
     */
    public static void getLeaguesBySummoner(final Action<List<League>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesBySummoner(summoner);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesBySummoner(summoner));
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
     *            what to do with the summoners' leagues
     * @param summoners
     *            the summoners to get leagues for
     */
    public static void getLeaguesBySummoner(final Action<List<List<League>>> action, final List<Summoner> summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesBySummoner(summoners);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesBySummoner(summoners));
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
     *            what to do with the summoners' leagues
     * @param summoners
     *            the summoners to get leagues for
     */
    public static void getLeaguesBySummoner(final Action<List<List<League>>> action, final Summoner... summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesBySummoner(summoners);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesBySummoner(summoners));
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
     *            what to do with the summoner's leagues
     * @param summonerID
     *            the ID of the summoner to get leagues for
     */
    public static void getLeaguesBySummonerID(final Action<List<League>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesBySummonerID(summonerID);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesBySummonerID(summonerID));
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
     *            what to do with the summoners' leagues
     * @param summonerIDs
     *            the IDs of the summoners to get leagues for
     */
    public static void getLeaguesBySummonerID(final Action<List<List<League>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesBySummonerID(summonerIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesBySummonerID(summonerIDs));
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
     *            what to do with the summoners' leagues
     * @param summonerIDs
     *            the IDs of the summoners to get leagues for
     */
    public static void getLeaguesBySummonerID(final Action<List<List<League>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesBySummonerID(summonerIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesBySummonerID(summonerIDs));
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
     *            what to do with the summoner's leagues
     * @param summonerName
     *            the name of the summoner to get leagues for
     */
    public static void getLeaguesBySummonerName(final Action<List<League>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesBySummonerName(summonerName);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesBySummonerName(summonerName));
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
     *            what to do with the summoners' leagues
     * @param summonerNames
     *            the names of the summoners to get leagues for
     */
    public static void getLeaguesBySummonerName(final Action<List<List<League>>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesBySummonerName(summonerNames);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesBySummonerName(summonerNames));
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
     *            what to do with the summoners' leagues
     * @param summonerNames
     *            the names of the summoners to get leagues for
     */
    public static void getLeaguesBySummonerName(final Action<List<List<League>>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesBySummonerName(summonerNames);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesBySummonerName(summonerNames));
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
     * @param team
     *            the team to get leagues for
     */
    public static void getLeaguesByTeam(final Action<List<League>> action, final Team team) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesByTeam(team);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesByTeam(team));
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
     *            what to do with the teams' leagues
     * @param teams
     *            the teams to get leagues for
     */
    public static void getLeaguesByTeam(final Action<List<List<League>>> action, final List<Team> teams) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesByTeam(teams);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesByTeam(teams));
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
     *            what to do with the teams' leagues
     * @param teams
     *            the teams to get leagues for
     */
    public static void getLeaguesByTeam(final Action<List<List<League>>> action, final Team... teams) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesByTeam(teams);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesByTeam(teams));
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
     * @param teamID
     *            the ID of the team to get leagues for
     */
    public static void getLeaguesByTeamID(final Action<List<League>> action, final String teamID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesByTeamID(teamID);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesByTeamID(teamID));
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
     *            what to do with the teams' leagues
     * @param teamIDs
     *            the IDs of the teams to get leagues for
     */
    public static void getLeaguesByTeamID(final Action<List<List<League>>> action, final List<String> teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesByTeamID(teamIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesByTeamID(teamIDs));
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
     *            what to do with the teams' leagues
     * @param teamIDs
     *            the IDs of the teams to get leagues for
     */
    public static void getLeaguesByTeamID(final Action<List<List<League>>> action, final String... teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    LeagueAPI.getLeaguesByTeamID(teamIDs);
                }
                else {
                    try {
                        action.perform(LeagueAPI.getLeaguesByTeamID(teamIDs));
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
     *            what to do with information for the maps
     */
    public static void getMapInformation(final Action<List<MapDetails>> action) {
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
     *            the ranked queue type to get the master league of
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
     *            what to do with all the masteries
     */
    public static void getMasteries(final Action<List<Mastery>> action) {
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
     *            what to do with the masteries
     * @param IDs
     *            the IDs of the masteries to get
     */
    public static void getMasteries(final Action<List<Mastery>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getMasteries(IDs);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getMasteries(IDs));
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
     * @param IDs
     *            the IDs of the masteries to get
     */
    public static void getMasteries(final Action<List<Mastery>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getMasteries(IDs);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getMasteries(IDs));
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
     *            the ID of the mastery to get
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
     *            what to do with the summoners' mastery pages
     * @param summoners
     *            the summoners to get mastery pages for
     */
    public static void getMasteryPages(final Action<List<List<MasteryPage>>> action, final List<Summoner> summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getMasteryPages(summoners);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getMasteryPages(summoners));
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
     *            what to do with the summoners' mastery pages
     * @param summoners
     *            the summoners to get mastery pages for
     */
    public static void getMasteryPages(final Action<List<List<MasteryPage>>> action, final Summoner... summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getMasteryPages(summoners);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getMasteryPages(summoners));
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
     *            what to do with the summoner's mastery pages
     * @param summoner
     *            the summoner to get mastery pages for
     */
    public static void getMasteryPages(final Action<List<MasteryPage>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getMasteryPages(summoner);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getMasteryPages(summoner));
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
     *            what to do with the summoners' mastery pages
     * @param summonerIDs
     *            the IDs of the summoners to get mastery pages for
     */
    public static void getMasteryPagesByID(final Action<List<List<MasteryPage>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getMasteryPagesByID(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getMasteryPagesByID(summonerIDs));
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
     *            what to do with the summoners' mastery pages
     * @param summonerIDs
     *            the IDs of the summoners to get mastery pages for
     */
    public static void getMasteryPagesByID(final Action<List<List<MasteryPage>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getMasteryPagesByID(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getMasteryPagesByID(summonerIDs));
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
     *            what to do with the summoner's mastery pages
     * @param summonerID
     *            the ID of the summoner to get mastery pages for
     */
    public static void getMasteryPagesByID(final Action<List<MasteryPage>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getMasteryPagesByID(summonerID);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getMasteryPagesByID(summonerID));
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
     *            what to do with the summoners' mastery pages
     * @param summonerNames
     *            the names of the summoners to get mastery pages for
     */
    public static void getMasteryPagesByName(final Action<List<List<MasteryPage>>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getMasteryPagesByName(summonerNames);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getMasteryPagesByName(summonerNames));
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
     *            what to do with the summoners' mastery pages
     * @param summonerNames
     *            the names of the summoners to get mastery pages for
     */
    public static void getMasteryPagesByName(final Action<List<List<MasteryPage>>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getMasteryPagesByName(summonerNames);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getMasteryPagesByName(summonerNames));
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
     *            what to do with the summoner's mastery pages
     * @param summonerName
     *            the name of the summoner to get mastery pages for
     */
    public static void getMasteryPagesByName(final Action<List<MasteryPage>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getMasteryPagesByName(summonerName);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getMasteryPagesByName(summonerName));
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
     *            the ID of the match to get
     */
    public static void getMatch(final Action<Match> action, final long ID) {
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
     *            the ID of the match to get
     * @param includeTimeline
     *            whether to include timeline data in the returned match
     */
    public static void getMatch(final Action<Match> action, final long ID, final boolean includeTimeline) {
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
     *            what to do with the match
     * @param reference
     *            the match reference to get the match for
     */
    public static void getMatchByReference(final Action<Match> action, final MatchReference reference) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchAPI.getMatchByReference(reference);
                }
                else {
                    try {
                        action.perform(MatchAPI.getMatchByReference(reference));
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
     * @param reference
     *            the match reference to get the match for
     * @param includeTimeline
     *            whether to include timeline data in the returned match
     */
    public static void getMatchByReference(final Action<Match> action, final MatchReference reference, final boolean includeTimeline) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchAPI.getMatchByReference(reference, includeTimeline);
                }
                else {
                    try {
                        action.perform(MatchAPI.getMatchByReference(reference, includeTimeline));
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
     *            what to do with the matches
     * @param IDs
     *            the match IDs to get
     */
    public static void getMatches(final Action<List<Match>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchAPI.getMatches(IDs);
                }
                else {
                    try {
                        action.perform(MatchAPI.getMatches(IDs));
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
     *            what to do with the matches
     * @param IDs
     *            the match IDs to get
     * @param includeTimeline
     *            whether to include timeline data in the returned matches
     */
    public static void getMatches(final Action<List<Match>> action, final List<Long> IDs, final boolean includeTimeline) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchAPI.getMatches(IDs, includeTimeline);
                }
                else {
                    try {
                        action.perform(MatchAPI.getMatches(IDs, includeTimeline));
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
     *            what to do with the matches
     * @param references
     *            the match references to get the matches for
     */
    public static void getMatchesByReference(final Action<List<Match>> action, final List<MatchReference> references) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchAPI.getMatchesByReference(references);
                }
                else {
                    try {
                        action.perform(MatchAPI.getMatchesByReference(references));
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
     *            what to do with the matches
     * @param references
     *            the match references to get the matches for
     * @param includeTimeline
     *            whether to include timeline data in the returned matches
     */
    public static void getMatchesByReference(final Action<List<Match>> action, final List<MatchReference> references, final boolean includeTimeline) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchAPI.getMatchesByReference(references, includeTimeline);
                }
                else {
                    try {
                        action.perform(MatchAPI.getMatchesByReference(references, includeTimeline));
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
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID) {
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
     * @param beginTime
     *            The begin time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final Date beginTime) {
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
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final Date beginTime, final Date endTime) {
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
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final Date beginTime, final Date endTime,
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
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final Date beginTime, final Date endTime,
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
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @param seasons
     *            the seasons to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final Date beginTime, final Date endTime,
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
     *            what to do with the match list
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final int numMatches) {
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
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final int numMatches, final int beginIndex) {
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
     *            The begin time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final int numMatches, final int beginIndex,
            final Date beginTime) {
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
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime) {
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
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime, final List<QueueType> queueTypes) {
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
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs) {
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
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @param seasons
     *            the seasons to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final long summonerID, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
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
     * @param summonerName
     *            the name of the summoner to get match history for
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final Date beginTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, beginTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, beginTime));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final Date beginTime, final Date endTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, beginTime, endTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, beginTime, endTime));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final Date beginTime, final Date endTime,
            final List<QueueType> queueTypes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, beginTime, endTime, queueTypes);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, beginTime, endTime, queueTypes));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final Date beginTime, final Date endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, beginTime, endTime, queueTypes, championIDs);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, beginTime, endTime, queueTypes, championIDs));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @param seasons
     *            the seasons to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final Date beginTime, final Date endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, beginTime, endTime, queueTypes, championIDs, seasons);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, beginTime, endTime, queueTypes, championIDs, seasons));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final int numMatches) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, numMatches);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, numMatches));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final int numMatches, final int beginIndex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, numMatches, beginIndex);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, numMatches, beginIndex));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final int numMatches, final int beginIndex,
            final Date beginTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, numMatches, beginIndex, beginTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, numMatches, beginIndex, beginTime));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, numMatches, beginIndex, beginTime, endTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, numMatches, beginIndex, beginTime, endTime));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime, final List<QueueType> queueTypes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, numMatches, beginIndex, beginTime, endTime, queueTypes);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, numMatches, beginIndex, beginTime, endTime, queueTypes));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs));
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
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @param seasons
     *            the seasons to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final String summonerName, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summonerName, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs, seasons);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summonerName, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs, seasons));
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
     * @param summoner
     *            the summoner to get match history for
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner));
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
     * @param summoner
     *            the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final Date beginTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, beginTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, beginTime));
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
     * @param summoner
     *            the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final Date beginTime, final Date endTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, beginTime, endTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, beginTime, endTime));
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
     * @param summoner
     *            the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final Date beginTime, final Date endTime,
            final List<QueueType> queueTypes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, beginTime, endTime, queueTypes);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, beginTime, endTime, queueTypes));
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
     * @param summoner
     *            the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final Date beginTime, final Date endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, beginTime, endTime, queueTypes, championIDs);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, beginTime, endTime, queueTypes, championIDs));
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
     * @param summoner
     *            the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @param seasons
     *            the seasons to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final Date beginTime, final Date endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, beginTime, endTime, queueTypes, championIDs, seasons);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, beginTime, endTime, queueTypes, championIDs, seasons));
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
     * @param summoner
     *            the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final int numMatches) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, numMatches);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, numMatches));
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
     * @param summoner
     *            the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final int numMatches, final int beginIndex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, numMatches, beginIndex);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, numMatches, beginIndex));
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
     * @param summoner
     *            the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final int numMatches, final int beginIndex,
            final Date beginTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, numMatches, beginIndex, beginTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, numMatches, beginIndex, beginTime));
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
     * @param summoner
     *            the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, numMatches, beginIndex, beginTime, endTime);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, numMatches, beginIndex, beginTime, endTime));
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
     * @param summoner
     *            the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime, final List<QueueType> queueTypes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, numMatches, beginIndex, beginTime, endTime, queueTypes);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, numMatches, beginIndex, beginTime, endTime, queueTypes));
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
     * @param summoner
     *            the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs));
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
     * @param summoner
     *            the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @param seasons
     *            the seasons to limit games to
     */
    public static void getMatchList(final Action<List<MatchReference>> action, final Summoner summoner, final int numMatches, final int beginIndex,
            final Date beginTime, final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    MatchListAPI.getMatchList(summoner, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs, seasons);
                }
                else {
                    try {
                        action.perform(MatchListAPI.getMatchList(summoner, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs, seasons));
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
     *            what to do with the ranked stats for the summoner by champion
     *            (null entry contains totals over all champions)
     * @param summonerID
     *            the summoner to get ranked stats for
     */
    public static void getRankedStats(final Action<Map<Champion, ChampionStats>> action, final long summonerID) {
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
     *            what to do with the ranked stats for the summoner by champion
     *            (null entry contains totals over all champions)
     * @param summonerID
     *            the summoner to get ranked stats for
     * @param season
     *            the season to get ranked stats for
     */
    public static void getRankedStats(final Action<Map<Champion, ChampionStats>> action, final long summonerID, final Season season) {
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
     *            what to do with the ranked stats for the summoner by champion
     *            (null entry contains totals over all champions)
     * @param summoner
     *            the summoner to get ranked stats for
     */
    public static void getRankedStats(final Action<Map<Champion, ChampionStats>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StatsAPI.getRankedStats(summoner);
                }
                else {
                    try {
                        action.perform(StatsAPI.getRankedStats(summoner));
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
     *            what to do with the ranked stats for the summoner by champion
     *            (null entry contains totals over all champions)
     * @param summoner
     *            the summoner to get ranked stats for
     * @param season
     *            the season to get ranked stats for
     */
    public static void getRankedStats(final Action<Map<Champion, ChampionStats>> action, final Summoner summoner, final Season season) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StatsAPI.getRankedStats(summoner, season);
                }
                else {
                    try {
                        action.perform(StatsAPI.getRankedStats(summoner, season));
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
     *            the ID of the summoner to get recent games for
     */
    public static void getRecentGames(final Action<List<Game>> action, final long summonerID) {
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
     *            what to do with the summoner's recent games
     * @param summonerName
     *            the name of the summoner to get recent games for
     */
    public static void getRecentGames(final Action<List<Game>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    GameAPI.getRecentGames(summonerName);
                }
                else {
                    try {
                        action.perform(GameAPI.getRecentGames(summonerName));
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
     * @param summoner
     *            the summoner to get recent games for
     */
    public static void getRecentGames(final Action<List<Game>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    GameAPI.getRecentGames(summoner);
                }
                else {
                    try {
                        action.perform(GameAPI.getRecentGames(summoner));
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
     *            the ID of the rune to get
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
     *            what to do with the summoners' rune pages
     * @param summoners
     *            the summoners to get rune pages for
     */
    public static void getRunePages(final Action<List<List<RunePage>>> action, final List<Summoner> summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getRunePages(summoners);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getRunePages(summoners));
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
     *            what to do with the summoners' rune pages
     * @param summoners
     *            the summoners to get rune pages for
     */
    public static void getRunePages(final Action<List<List<RunePage>>> action, final Summoner... summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getRunePages(summoners);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getRunePages(summoners));
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
     *            what to do with the summoner's rune pages
     * @param summoner
     *            the summoner to get rune pages for
     */
    public static void getRunePages(final Action<List<RunePage>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getRunePages(summoner);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getRunePages(summoner));
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
     *            what to do with the summoners' rune pages
     * @param summonerIDs
     *            the IDs of the summoners to get rune pages for
     */
    public static void getRunePagesByID(final Action<List<List<RunePage>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getRunePagesByID(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getRunePagesByID(summonerIDs));
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
     *            what to do with the summoners' rune pages
     * @param summonerIDs
     *            the IDs of the summoners to get rune pages for
     */
    public static void getRunePagesByID(final Action<List<List<RunePage>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getRunePagesByID(summonerIDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getRunePagesByID(summonerIDs));
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
     *            what to do with the summoner's rune pages
     * @param summonerID
     *            the ID of the summoner to get rune pages for
     */
    public static void getRunePagesByID(final Action<List<RunePage>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getRunePagesByID(summonerID);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getRunePagesByID(summonerID));
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
     *            what to do with the summoners' rune pages
     * @param summonerNames
     *            the names of the summoners to get rune pages for
     */
    public static void getRunePagesByName(final Action<List<List<RunePage>>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getRunePagesByName(summonerNames);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getRunePagesByName(summonerNames));
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
     *            what to do with the summoners' rune pages
     * @param summonerNames
     *            the names of the summoners to get rune pages for
     */
    public static void getRunePagesByName(final Action<List<List<RunePage>>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getRunePagesByName(summonerNames);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getRunePagesByName(summonerNames));
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
     *            what to do with the summoner's rune pages
     * @param summonerName
     *            the name of the summoner to get rune pages for
     */
    public static void getRunePagesByName(final Action<List<RunePage>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getRunePagesByName(summonerName);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getRunePagesByName(summonerName));
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
     *            what to do with all the runes
     */
    public static void getRunes(final Action<List<Rune>> action) {
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
     *            what to do with the runes
     * @param IDs
     *            the IDs of the runes to get
     */
    public static void getRunes(final Action<List<Rune>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getRunes(IDs);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getRunes(IDs));
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
     *            what to do with the runes
     * @param IDs
     *            the IDs of the runes to get
     */
    public static void getRunes(final Action<List<Rune>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getRunes(IDs);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getRunes(IDs));
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
     *            what to do with the status shard for that region
     * @param region
     *            the region to get the shard for
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
     *            what to do with the status shard list
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
     *            what to do with the stats for the summoner by queue type
     * @param summonerID
     *            the summoner to get stats for
     */
    public static void getStats(final Action<Map<PlayerStatsSummaryType, PlayerStatsSummary>> action, final long summonerID) {
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
     *            what to do with the stats for the summoner by queue type
     * @param summonerID
     *            the summoner to get stats for
     * @param season
     *            the season to get stats for
     */
    public static void getStats(final Action<Map<PlayerStatsSummaryType, PlayerStatsSummary>> action, final long summonerID, final Season season) {
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
     *            what to do with the stats for the summoner by queue type
     * @param summoner
     *            the summoner to get stats for
     */
    public static void getStats(final Action<Map<PlayerStatsSummaryType, PlayerStatsSummary>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StatsAPI.getStats(summoner);
                }
                else {
                    try {
                        action.perform(StatsAPI.getStats(summoner));
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
     *            what to do with the stats for the summoner by queue type
     * @param summoner
     *            the summoner to get stats for
     * @param season
     *            the season to get stats for
     */
    public static void getStats(final Action<Map<PlayerStatsSummaryType, PlayerStatsSummary>> action, final Summoner summoner, final Season season) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StatsAPI.getStats(summoner, season);
                }
                else {
                    try {
                        action.perform(StatsAPI.getStats(summoner, season));
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
     *            what to do with the summoner
     * @param ID
     *            the ID of the summoner to get
     */
    public static void getSummonerByID(final Action<Summoner> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonerByID(ID);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonerByID(ID));
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
     * @param name
     *            the name of the summoner to get
     */
    public static void getSummonerByName(final Action<Summoner> action, final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonerByName(name);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonerByName(name));
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
     *            what to do with the summoner's name
     * @param ID
     *            the ID of the summoner to get the names of
     */
    public static void getSummonerName(final Action<String> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonerName(ID);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonerName(ID));
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
     * @param IDs
     *            the IDs of the summoners to get
     */
    public static void getSummonersByID(final Action<List<Summoner>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersByID(IDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersByID(IDs));
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
     * @param IDs
     *            the IDs of the summoners to get
     */
    public static void getSummonersByID(final Action<List<Summoner>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersByID(IDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersByID(IDs));
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
     * @param names
     *            the names of the summoners to get
     */
    public static void getSummonersByName(final Action<List<Summoner>> action, final List<String> names) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersByName(names);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersByName(names));
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
     * @param names
     *            the names of the summoners to get
     */
    public static void getSummonersByName(final Action<List<Summoner>> action, final String... names) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersByName(names);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersByName(names));
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
     * @param IDs
     *            the IDs of the summoners to get the names of
     */
    public static void getSummonersNames(final Action<List<String>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersNames(IDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersNames(IDs));
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
     * @param IDs
     *            the IDs of the summoners to get the names of
     */
    public static void getSummonersNames(final Action<List<String>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    SummonerAPI.getSummonersNames(IDs);
                }
                else {
                    try {
                        action.perform(SummonerAPI.getSummonersNames(IDs));
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
     *            the ID of the summoner spell to get
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
     *            what to do with all the summoner spells
     */
    public static void getSummonerSpells(final Action<List<SummonerSpell>> action) {
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
     *            what to do with the summoner spells
     * @param IDs
     *            the IDs of the summoner spells to get
     */
    public static void getSummonerSpells(final Action<List<SummonerSpell>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getSummonerSpells(IDs);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getSummonerSpells(IDs));
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
     *            what to do with the summoner spells
     * @param IDs
     *            the IDs of the summoner spells to get
     */
    public static void getSummonerSpells(final Action<List<SummonerSpell>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    StaticDataAPI.getSummonerSpells(IDs);
                }
                else {
                    try {
                        action.perform(StaticDataAPI.getSummonerSpells(IDs));
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
     *            what to do with the team
     * @param teamID
     *            the ID of the team to get
     */
    public static void getTeam(final Action<Team> action, final String teamID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeam(teamID);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeam(teamID));
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
     * @param teamIDs
     *            the IDs of the teams to get
     */
    public static void getTeams(final Action<List<Team>> action, final List<String> teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeams(teamIDs);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeams(teamIDs));
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
     * @param teamIDs
     *            the IDs of the teams to get
     */
    public static void getTeams(final Action<List<Team>> action, final String... teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeams(teamIDs);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeams(teamIDs));
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
     * @param summoners
     *            the summoners to get teams for
     */
    public static void getTeamsBySummoner(final Action<List<List<Team>>> action, final List<Summoner> summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummoner(summoners);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummoner(summoners));
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
     * @param summoners
     *            the summoners to get teams for
     */
    public static void getTeamsBySummoner(final Action<List<List<Team>>> action, final Summoner... summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummoner(summoners);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummoner(summoners));
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
     * @param summoner
     *            the summoner to get teams for
     */
    public static void getTeamsBySummoner(final Action<List<Team>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummoner(summoner);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummoner(summoner));
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
     */
    public static void getTeamsBySummonerID(final Action<List<List<Team>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummonerID(summonerIDs);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummonerID(summonerIDs));
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
     */
    public static void getTeamsBySummonerID(final Action<List<List<Team>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummonerID(summonerIDs);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummonerID(summonerIDs));
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
     *            what to do with the summoner's teams
     * @param summonerID
     *            the ID of the summoner to get teams for
     */
    public static void getTeamsBySummonerID(final Action<List<Team>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummonerID(summonerID);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummonerID(summonerID));
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
     * @param summonerNames
     *            the names of the summoners to get teams for
     */
    public static void getTeamsBySummonerName(final Action<List<List<Team>>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummonerName(summonerNames);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummonerName(summonerNames));
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
     * @param summonerNames
     *            the names of the summoners to get teams for
     */
    public static void getTeamsBySummonerName(final Action<List<List<Team>>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummonerName(summonerNames);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummonerName(summonerNames));
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
     *            what to do with the summoner's teams
     * @param summonerName
     *            the names of the summoner to get teams for
     */
    public static void getTeamsBySummonerName(final Action<List<List<Team>>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    TeamAPI.getTeamsBySummonerName(summonerName);
                }
                else {
                    try {
                        action.perform(TeamAPI.getTeamsBySummonerName(summonerName));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the top champion mastery ratings for a summoner
     *
     * @param action
     *            what to do with the mastery information
     * @param summonerID
     *            the summoner to get ratings for
     * @param count
     *            the number of top champions to get
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
     * Gets the top champion mastery ratings for a summoner
     *
     * @param action
     *            what to do with the mastery information
     * @param summonerName
     *            the summoner to get ratings for
     * @param count
     *            the number of top champions to get
     */
    public static void getTopChampionMastery(final Action<List<ChampionMastery>> action, final String summonerName, final int count) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getTopChampionMastery(summonerName, count);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getTopChampionMastery(summonerName, count));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the top champion mastery ratings for a summoner
     *
     * @param action
     *            what to do with the mastery information
     * @param summoner
     *            the summoner to get ratings for
     * @param count
     *            the number of top champions to get
     */
    public static void getTopChampionMastery(final Action<List<ChampionMastery>> action, final Summoner summoner, final int count) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getTopChampionMastery(summoner, count);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getTopChampionMastery(summoner, count));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the total mastery level for a summoner
     *
     * @param action
     *            what to do with the total mastery level
     * @param summonerID
     *            the summoner to get mastery level for
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
     * Gets the total mastery level for a summoner
     *
     * @param action
     *            what to do with the total mastery level
     * @param summonerName
     *            the summoner to get mastery level for
     */
    public static void getTotalMasteryLevel(final Action<Integer> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getTotalMasteryLevel(summonerName);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getTotalMasteryLevel(summonerName));
                    }
                    catch(final APIException e) {
                        action.handle(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Gets the total mastery level for a summoner
     *
     * @param action
     *            what to do with the total mastery level
     * @param summoner
     *            the summoner to get mastery level for
     */
    public static void getTotalMasteryLevel(final Action<Integer> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(action == null) {
                    ChampionMasteryAPI.getTotalMasteryLevel(summoner);
                }
                else {
                    try {
                        action.perform(ChampionMasteryAPI.getTotalMasteryLevel(summoner));
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
     * Sets the data store to use for caching
     *
     * @param newStore
     *            the data store to use for caching
     */
    public static void setDataStore(final DataStore newStore) {
        RiotAPI.setDataStore(newStore);
    }

    /**
     * Sets the load policy for the API
     *
     * @param policy
     *            the new load policy
     */
    public static void setLoadPolicy(final LoadPolicy policy) {
        RiotAPI.setLoadPolicy(policy);
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
