package com.robrua.orianna.api.dto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.robrua.orianna.type.api.ParamsBuilder;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.dto.matchlist.MatchList;

public abstract class MatchListAPI {
    private static final Set<QueueType> RANKED_QUEUES = new HashSet<>(Arrays.asList(new QueueType[] {QueueType.TEAM_BUILDER_DRAFT_RANKED_5x5,
            QueueType.RANKED_SOLO_5x5, QueueType.RANKED_TEAM_3x3, QueueType.RANKED_TEAM_5x5, QueueType.RANKED_FLEX_SR, QueueType.RANKED_FLEX_TT}));

    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), MatchList.class);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param numMatches
     *            the maximum number of matches to get
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final int numMatches) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", 0).add("endIndex", numMatches).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
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
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final int numMatches, final int beginIndex) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", beginIndex).add("endIndex", beginIndex + numMatches).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
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
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final int numMatches, final int beginIndex, final long beginTime) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", beginIndex).add("endIndex", beginIndex + numMatches)
                .add("beginTime", beginTime).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
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
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final int numMatches, final int beginIndex, final long beginTime, final long endTime) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", beginIndex).add("endIndex", beginIndex + numMatches)
                .add("beginTime", beginTime).add("endTime", endTime).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
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
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final int numMatches, final int beginIndex, final long beginTime, final long endTime,
            final List<QueueType> queueTypes) {
        for(final QueueType queue : queueTypes) {
            if(!RANKED_QUEUES.contains(queue)) {
                throw new IllegalArgumentException("Can't get match history for a non-ranked queue type!");
            }
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", beginIndex).add("endIndex", beginIndex + numMatches)
                .add("beginTime", beginTime).add("endTime", endTime).addIfNotNull("rankedQueues", queueTypes).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
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
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final int numMatches, final int beginIndex, final long beginTime, final long endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", beginIndex).add("endIndex", beginIndex + numMatches)
                .add("beginTime", beginTime).add("endTime", endTime).addIfNotNull("rankedQueues", queueTypes).addIfNotNull("championIds", championIDs).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
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
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final int numMatches, final int beginIndex, final long beginTime, final long endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        for(final QueueType queue : queueTypes) {
            if(!RANKED_QUEUES.contains(queue)) {
                throw new IllegalArgumentException("Can't get match history for a non-ranked queue type!");
            }
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", beginIndex).add("endIndex", beginIndex + numMatches)
                .add("beginTime", beginTime).add("endTime", endTime).addIfNotNull("rankedQueues", queueTypes).addIfNotNull("championIds", championIDs)
                .addIfNotNull("seasons", seasons).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final long beginTime) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginTime", beginTime).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginTime
     *            The begin time to use for fetching games specified as epoch
     *            milliseconds
     * @param endTime
     *            The end time to use for fetching games specified as epoch
     *            milliseconds
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final long beginTime, final long endTime) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginTime", beginTime).add("endTime", endTime).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final long beginTime, final long endTime, final List<QueueType> queueTypes) {
        for(final QueueType queue : queueTypes) {
            if(!RANKED_QUEUES.contains(queue)) {
                throw new IllegalArgumentException("Can't get match history for a non-ranked queue type!");
            }
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginTime", beginTime).add("endTime", endTime).addIfNotNull("rankedQueues", queueTypes)
                .build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final long beginTime, final long endTime, final List<QueueType> queueTypes,
            final List<Long> championIDs) {
        for(final QueueType queue : queueTypes) {
            if(!RANKED_QUEUES.contains(queue)) {
                throw new IllegalArgumentException("Can't get match history for a non-ranked queue type!");
            }
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginTime", beginTime).add("endTime", endTime).addIfNotNull("rankedQueues", queueTypes)
                .addIfNotNull("championIds", championIDs).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
    }

    /**
     * Gets the match history for the summoner
     *
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
     * @return the match list for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1013/3439">
     *      Riot API Specification</a>
     */
    public static MatchList getMatchList(final long summonerID, final long beginTime, final long endTime, final List<QueueType> queueTypes,
            final List<Long> championIDs, final List<Season> seasons) {
        for(final QueueType queue : queueTypes) {
            if(!RANKED_QUEUES.contains(queue)) {
                throw new IllegalArgumentException("Can't get match history for a non-ranked queue type!");
            }
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("matchlist") + "/matchlist/by-summoner/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginTime", beginTime).add("endTime", endTime).addIfNotNull("rankedQueues", queueTypes)
                .addIfNotNull("championIds", championIDs).addIfNotNull("seasons", seasons).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchList.class);
    }
}
