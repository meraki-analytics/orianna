package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.core.championmastery.ChampionMastery;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.summoner.Summoner;

public abstract class ChampionMasteryAPI {
    /**
     * Gets the champion mastery ratings for a summoner
     *
     * @param summonerID
     *            the summoner to get ratings for
     * @return the summoner's champion mastery ratings
     */
    public static List<ChampionMastery> getChampionMastery(final long summonerID) {
        final List<com.robrua.orianna.type.dto.championmastery.ChampionMastery> masteries = BaseRiotAPI.getChampionMastery(summonerID);
        final List<ChampionMastery> results = new ArrayList<>(masteries.size());
        for(final com.robrua.orianna.type.dto.championmastery.ChampionMastery m : masteries) {
            results.add(new ChampionMastery(m));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampions();
        }

        return Collections.unmodifiableList(results);
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param summonerID
     *            the summoner to get rating for
     * @param champion
     *            the champion to get rating for
     * @return the summoner's champion mastery rating
     */
    public static ChampionMastery getChampionMastery(final long summonerID, final Champion champion) {
        return getChampionMastery(summonerID, champion.getID());
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param summonerID
     *            the summoner to get rating for
     * @param championID
     *            the champion to get rating for
     * @return the summoner's champion mastery rating
     */
    public static ChampionMastery getChampionMastery(final long summonerID, final long championID) {
        return new ChampionMastery(BaseRiotAPI.getChampionMastery(summonerID, championID));
    }

    /**
     * Gets the champion mastery ratings for a summoner
     *
     * @param summonerName
     *            the summoner to get ratings for
     * @return the summoner's champion mastery ratings
     */
    public static List<ChampionMastery> getChampionMastery(final String summonerName) {
        return getChampionMastery(RiotAPI.getSummonerByName(summonerName).getID());
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param summonerName
     *            the summoner to get rating for
     * @param champion
     *            the champion to get rating for
     * @return the summoner's champion mastery rating
     */
    public static ChampionMastery getChampionMastery(final String summonerName, final Champion champion) {
        return getChampionMastery(RiotAPI.getSummonerByName(summonerName).getID(), champion.getID());
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param summonerName
     *            the summoner to get rating for
     * @param championID
     *            the champion to get rating for
     * @return the summoner's champion mastery rating
     */
    public static ChampionMastery getChampionMastery(final String summonerName, final long championID) {
        return getChampionMastery(RiotAPI.getSummonerByName(summonerName).getID(), championID);
    }

    /**
     * Gets the champion mastery ratings for a summoner
     *
     * @param summoner
     *            the summoner to get ratings for
     * @return the summoner's champion mastery ratings
     */
    public static List<ChampionMastery> getChampionMastery(final Summoner summoner) {
        return getChampionMastery(summoner.getID());
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param summoner
     *            the summoner to get rating for
     * @param champion
     *            the champion to get rating for
     * @return the summoner's champion mastery rating
     */
    public static ChampionMastery getChampionMastery(final Summoner summoner, final Champion champion) {
        return getChampionMastery(summoner.getID(), champion.getID());
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param summoner
     *            the summoner to get rating for
     * @param championID
     *            the champion to get rating for
     * @return the summoner's champion mastery rating
     */
    public static ChampionMastery getChampionMastery(final Summoner summoner, final long championID) {
        return getChampionMastery(summoner.getID(), championID);
    }

    /**
     * Gets the top champion mastery ratings for a summoner
     *
     * @param summonerID
     *            the summoner to get ratings for
     * @param count
     *            the number of top champions to get
     * @return the summoner's champion mastery ratings
     */
    public static List<ChampionMastery> getTopChampionMastery(final long summonerID, final int count) {
        final List<com.robrua.orianna.type.dto.championmastery.ChampionMastery> masteries = BaseRiotAPI.getTopChampionMastery(summonerID, count);
        final List<ChampionMastery> results = new ArrayList<>(masteries.size());
        for(final com.robrua.orianna.type.dto.championmastery.ChampionMastery m : masteries) {
            results.add(new ChampionMastery(m));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampions();
        }

        return Collections.unmodifiableList(results);
    }

    /**
     * Gets the top champion mastery ratings for a summoner
     *
     * @param summonerName
     *            the summoner to get ratings for
     * @param count
     *            the number of top champions to get
     * @return the summoner's champion mastery ratings
     */
    public static List<ChampionMastery> getTopChampionMastery(final String summonerName, final int count) {
        return getTopChampionMastery(RiotAPI.getSummonerByName(summonerName).getID(), count);
    }

    /**
     * Gets the top champion mastery ratings for a summoner
     *
     * @param summoner
     *            the summoner to get ratings for
     * @param count
     *            the number of top champions to get
     * @return the summoner's champion mastery ratings
     */
    public static List<ChampionMastery> getTopChampionMastery(final Summoner summoner, final int count) {
        return getTopChampionMastery(summoner.getID(), count);
    }

    /**
     * Gets the total mastery level for a summoner
     *
     * @param summonerID
     *            the summoner to get mastery level for
     * @return the summoner's total mastery level
     */
    public static int getTotalMasteryLevel(final long summonerID) {
        return BaseRiotAPI.getTotalMasteryLevel(summonerID);
    }

    /**
     * Gets the total mastery level for a summoner
     *
     * @param summonerName
     *            the summoner to get mastery level for
     * @return the summoner's total mastery level
     */
    public static int getTotalMasteryLevel(final String summonerName) {
        return BaseRiotAPI.getTotalMasteryLevel(RiotAPI.getSummonerByName(summonerName).getID());
    }

    /**
     * Gets the total mastery level for a summoner
     *
     * @param summoner
     *            the summoner to get mastery level for
     * @return the summoner's total mastery level
     */
    public static int getTotalMasteryLevel(final Summoner summoner) {
        return BaseRiotAPI.getTotalMasteryLevel(summoner.getID());
    }
}
