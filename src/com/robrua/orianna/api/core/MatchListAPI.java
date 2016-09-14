package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.core.matchlist.MatchReference;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.dto.matchlist.MatchList;

public abstract class MatchListAPI {
    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID) {
        final MatchList list = BaseRiotAPI.getMatchList(summonerID);

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final Date beginTime) {
        MatchList list;
        if(beginTime == null) {
            list = BaseRiotAPI.getMatchList(summonerID, 0L);
        }
        else {
            list = BaseRiotAPI.getMatchList(summonerID, beginTime.getTime());
        }

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final Date beginTime, Date endTime) {
        if(endTime == null) {
            endTime = new Date();
        }

        MatchList list;
        if(beginTime == null) {
            list = BaseRiotAPI.getMatchList(summonerID, 0L, endTime.getTime());
        }
        else {
            list = BaseRiotAPI.getMatchList(summonerID, beginTime.getTime(), endTime.getTime());
        }

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final Date beginTime, Date endTime, final List<QueueType> queueTypes) {
        if(endTime == null) {
            endTime = new Date();
        }

        MatchList list;
        if(beginTime == null) {
            list = BaseRiotAPI.getMatchList(summonerID, 0L, endTime.getTime(), queueTypes);
        }
        else {
            list = BaseRiotAPI.getMatchList(summonerID, beginTime.getTime(), endTime.getTime(), queueTypes);
        }

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final Date beginTime, Date endTime, final List<QueueType> queueTypes,
            final List<Long> championIDs) {
        if(endTime == null) {
            endTime = new Date();
        }

        MatchList list;
        if(beginTime == null) {
            list = BaseRiotAPI.getMatchList(summonerID, 0L, endTime.getTime(), queueTypes, championIDs);
        }
        else {
            list = BaseRiotAPI.getMatchList(summonerID, beginTime.getTime(), endTime.getTime(), queueTypes, championIDs);
        }

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final Date beginTime, Date endTime, final List<QueueType> queueTypes,
            final List<Long> championIDs, final List<Season> seasons) {
        if(endTime == null) {
            endTime = new Date();
        }

        MatchList list;
        if(beginTime == null) {
            list = BaseRiotAPI.getMatchList(summonerID, 0L, endTime.getTime(), queueTypes, championIDs, seasons);
        }
        else {
            list = BaseRiotAPI.getMatchList(summonerID, beginTime.getTime(), endTime.getTime(), queueTypes, championIDs, seasons);
        }

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final int numMatches) {
        final MatchList list = BaseRiotAPI.getMatchList(summonerID, numMatches);

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final int numMatches, final int beginIndex) {
        final MatchList list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex);

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final int numMatches, final int beginIndex, final Date beginTime) {
        MatchList list;
        if(beginTime == null) {
            list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex, 0L);
        }
        else {
            list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime.getTime());
        }

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final int numMatches, final int beginIndex, final Date beginTime, Date endTime) {
        if(endTime == null) {
            endTime = new Date();
        }

        MatchList list;
        if(beginTime == null) {
            list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex, 0L, endTime.getTime());
        }
        else {
            list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime.getTime(), endTime.getTime());
        }

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final int numMatches, final int beginIndex, final Date beginTime, Date endTime,
            final List<QueueType> queueTypes) {
        if(endTime == null) {
            endTime = new Date();
        }

        MatchList list;
        if(beginTime == null) {
            list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex, 0L, endTime.getTime(), queueTypes);
        }
        else {
            list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime.getTime(), endTime.getTime(), queueTypes);
        }

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final int numMatches, final int beginIndex, final Date beginTime, Date endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs) {
        if(endTime == null) {
            endTime = new Date();
        }

        MatchList list;
        if(beginTime == null) {
            list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex, 0L, endTime.getTime(), queueTypes, championIDs);
        }
        else {
            list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime.getTime(), endTime.getTime(), queueTypes, championIDs);
        }

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final long summonerID, final int numMatches, final int beginIndex, final Date beginTime, Date endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        if(endTime == null) {
            endTime = new Date();
        }

        MatchList list;
        if(beginTime == null) {
            list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex, 0L, endTime.getTime(), queueTypes, championIDs, seasons);
        }
        else {
            list = BaseRiotAPI.getMatchList(summonerID, numMatches, beginIndex, beginTime.getTime(), endTime.getTime(), queueTypes, championIDs, seasons);
        }

        if(list.getMatches() == null) {
            return Collections.emptyList();
        }

        final List<MatchReference> history = new ArrayList<>(list.getMatches().size());
        for(final com.robrua.orianna.type.dto.matchlist.MatchReference match : list.getMatches()) {
            history.add(new MatchReference(match));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(list.getChampionIDs()));
        }

        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerName
     *            the name of the summoner to get match history for
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID());
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final Date beginTime) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), beginTime);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final Date beginTime, final Date endTime) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), beginTime, endTime);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final Date beginTime, final Date endTime, final List<QueueType> queueTypes) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), beginTime, endTime, queueTypes);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final Date beginTime, final Date endTime, final List<QueueType> queueTypes,
            final List<Long> championIDs) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), beginTime, endTime, queueTypes, championIDs);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final Date beginTime, final Date endTime, final List<QueueType> queueTypes,
            final List<Long> championIDs, final List<Season> seasons) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), beginTime, endTime, queueTypes, championIDs, seasons);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final int numMatches) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), numMatches);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final int numMatches, final int beginIndex) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), numMatches, beginIndex);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerName
     *            the name of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final int numMatches, final int beginIndex, final Date beginTime) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), numMatches, beginIndex, beginTime);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final int numMatches, final int beginIndex, final Date beginTime,
            final Date endTime) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), numMatches, beginIndex, beginTime, endTime);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final int numMatches, final int beginIndex, final Date beginTime,
            final Date endTime, final List<QueueType> queueTypes) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), numMatches, beginIndex, beginTime, endTime, queueTypes);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final int numMatches, final int beginIndex, final Date beginTime,
            final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final String summonerName, final int numMatches, final int beginIndex, final Date beginTime,
            final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        return getMatchList(RiotAPI.getSummonerByName(summonerName).getID(), numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs, seasons);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summoner
     *            the summoner to get match history for
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner) {
        return getMatchList(summoner.getID());
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summoner
     *            the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final Date beginTime) {
        return getMatchList(summoner.getID(), beginTime);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summoner
     *            the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final Date beginTime, final Date endTime) {
        return getMatchList(summoner.getID(), beginTime, endTime);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summoner
     *            the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final Date beginTime, final Date endTime, final List<QueueType> queueTypes) {
        return getMatchList(summoner.getID(), beginTime, endTime, queueTypes);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final Date beginTime, final Date endTime, final List<QueueType> queueTypes,
            final List<Long> championIDs) {
        return getMatchList(summoner.getID(), beginTime, endTime, queueTypes, championIDs);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final Date beginTime, final Date endTime, final List<QueueType> queueTypes,
            final List<Long> championIDs, final List<Season> seasons) {
        return getMatchList(summoner.getID(), beginTime, endTime, queueTypes, championIDs, seasons);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summoner
     *            the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final int numMatches) {
        return getMatchList(summoner.getID(), numMatches);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summoner
     *            the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final int numMatches, final int beginIndex) {
        return getMatchList(summoner.getID(), numMatches, beginIndex);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summoner
     *            the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final int numMatches, final int beginIndex, final Date beginTime) {
        return getMatchList(summoner.getID(), numMatches, beginIndex, beginTime);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final int numMatches, final int beginIndex, final Date beginTime,
            final Date endTime) {
        return getMatchList(summoner.getID(), numMatches, beginIndex, beginTime, endTime);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final int numMatches, final int beginIndex, final Date beginTime,
            final Date endTime, final List<QueueType> queueTypes) {
        return getMatchList(summoner.getID(), numMatches, beginIndex, beginTime, endTime, queueTypes);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final int numMatches, final int beginIndex, final Date beginTime,
            final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs) {
        return getMatchList(summoner.getID(), numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     */
    public static List<MatchReference> getMatchList(final Summoner summoner, final int numMatches, final int beginIndex, final Date beginTime,
            final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        return getMatchList(summoner.getID(), numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs, seasons);
    }
}
