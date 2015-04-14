package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.matchhistory.MatchSummary;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.dto.matchhistory.PlayerHistory;

public abstract class MatchHistoryAPI {
    /**
     * @param summonerID
     *            the summoner to get match history for
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID) {
        final PlayerHistory hist = BaseRiotAPI.getMatchHistory(summonerID);

        if(hist.getMatches() == null) {
            return null;
        }

        final List<MatchSummary> history = new ArrayList<>(hist.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchhistory.MatchSummary match : hist.getMatches()) {
            history.add(new MatchSummary(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(hist.getItemIDs()));
            RiotAPI.getChampionsByID(new ArrayList<>(hist.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(hist.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(hist.getRuneIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(hist.getSummonerIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(hist.getSummonerSpellIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @return the 15 most recent matches after beginIndex for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID, final int beginIndex) {
        final PlayerHistory hist = BaseRiotAPI.getMatchHistory(summonerID, beginIndex);

        if(hist.getMatches() == null) {
            return null;
        }

        final List<MatchSummary> history = new ArrayList<>(hist.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchhistory.MatchSummary match : hist.getMatches()) {
            history.add(new MatchSummary(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(hist.getItemIDs()));
            RiotAPI.getChampionsByID(new ArrayList<>(hist.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(hist.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(hist.getRuneIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(hist.getSummonerIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(hist.getSummonerSpellIDs()));
        }

        return Collections.unmodifiableList(history);
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
        if(championIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final PlayerHistory hist = BaseRiotAPI.getMatchHistory(summonerID, beginIndex, championIDs);

        if(hist.getMatches() == null) {
            return null;
        }

        final List<MatchSummary> history = new ArrayList<>(hist.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchhistory.MatchSummary match : hist.getMatches()) {
            history.add(new MatchSummary(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(hist.getItemIDs()));
            RiotAPI.getChampionsByID(new ArrayList<>(hist.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(hist.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(hist.getRuneIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(hist.getSummonerIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(hist.getSummonerSpellIDs()));
        }

        return Collections.unmodifiableList(history);
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
        final PlayerHistory hist = BaseRiotAPI.getMatchHistory(summonerID, beginIndex, queueType);

        if(hist.getMatches() == null) {
            return null;
        }

        final List<MatchSummary> history = new ArrayList<>(hist.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchhistory.MatchSummary match : hist.getMatches()) {
            history.add(new MatchSummary(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(hist.getItemIDs()));
            RiotAPI.getChampionsByID(new ArrayList<>(hist.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(hist.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(hist.getRuneIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(hist.getSummonerIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(hist.getSummonerSpellIDs()));
        }

        return Collections.unmodifiableList(history);
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
        if(championIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final PlayerHistory hist = BaseRiotAPI.getMatchHistory(summonerID, beginIndex, queueType, championIDs);

        if(hist.getMatches() == null) {
            return null;
        }

        final List<MatchSummary> history = new ArrayList<>(hist.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchhistory.MatchSummary match : hist.getMatches()) {
            history.add(new MatchSummary(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(hist.getItemIDs()));
            RiotAPI.getChampionsByID(new ArrayList<>(hist.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(hist.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(hist.getRuneIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(hist.getSummonerIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(hist.getSummonerSpellIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @param championIDs
     *            the champions to limit games to
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID, final List<Long> championIDs) {
        if(championIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final PlayerHistory hist = BaseRiotAPI.getMatchHistory(summonerID, championIDs);

        if(hist.getMatches() == null) {
            return null;
        }

        final List<MatchSummary> history = new ArrayList<>(hist.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchhistory.MatchSummary match : hist.getMatches()) {
            history.add(new MatchSummary(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(hist.getItemIDs()));
            RiotAPI.getChampionsByID(new ArrayList<>(hist.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(hist.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(hist.getRuneIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(hist.getSummonerIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(hist.getSummonerSpellIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * @param summonerID
     *            the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final long summonerID, final QueueType queueType) {
        final PlayerHistory hist = BaseRiotAPI.getMatchHistory(summonerID, queueType);

        if(hist.getMatches() == null) {
            return null;
        }

        final List<MatchSummary> history = new ArrayList<>(hist.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchhistory.MatchSummary match : hist.getMatches()) {
            history.add(new MatchSummary(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(hist.getItemIDs()));
            RiotAPI.getChampionsByID(new ArrayList<>(hist.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(hist.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(hist.getRuneIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(hist.getSummonerIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(hist.getSummonerSpellIDs()));
        }

        return Collections.unmodifiableList(history);
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
        if(championIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final PlayerHistory hist = BaseRiotAPI.getMatchHistory(summonerID, queueType, championIDs);

        if(hist.getMatches() == null) {
            return null;
        }

        final List<MatchSummary> history = new ArrayList<>(hist.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchhistory.MatchSummary match : hist.getMatches()) {
            history.add(new MatchSummary(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(hist.getItemIDs()));
            RiotAPI.getChampionsByID(new ArrayList<>(hist.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(hist.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(hist.getRuneIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(hist.getSummonerIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(hist.getSummonerSpellIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner) {
        return getMatchHistory(summoner.getID());
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @return the 15 most recent matches after beginIndex for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner, final int beginIndex) {
        return getMatchHistory(summoner.getID(), beginIndex);
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
        final List<Long> IDs = new ArrayList<>();
        for(final Champion champion : champions) {
            IDs.add(champion.getID());
        }

        return getMatchHistory(summoner.getID(), beginIndex, IDs);
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
        return getMatchHistory(summoner.getID(), beginIndex, queueType);
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
        final List<Long> IDs = new ArrayList<>();
        for(final Champion champion : champions) {
            IDs.add(champion.getID());
        }

        return getMatchHistory(summoner.getID(), beginIndex, queueType, IDs);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @param champions
     *            the champions to limit games to
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner, final List<Champion> champions) {
        final List<Long> IDs = new ArrayList<>();
        for(final Champion champion : champions) {
            IDs.add(champion.getID());
        }

        return getMatchHistory(summoner.getID(), IDs);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the 15 most recent matches for the summoner
     */
    public static List<MatchSummary> getMatchHistory(final Summoner summoner, final QueueType queueType) {
        return getMatchHistory(summoner.getID(), queueType);
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
        final List<Long> IDs = new ArrayList<>();
        for(final Champion champion : champions) {
            IDs.add(champion.getID());
        }

        return getMatchHistory(summoner.getID(), queueType, IDs);
    }
}
