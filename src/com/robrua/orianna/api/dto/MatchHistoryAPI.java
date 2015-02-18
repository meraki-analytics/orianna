package com.robrua.orianna.api.dto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.robrua.orianna.api.Utils;
import com.robrua.orianna.type.api.ParamsBuilder;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.dto.matchhistory.PlayerHistory;

public abstract class MatchHistoryAPI {
    private static final Set<QueueType> RANKED_QUEUES = new HashSet<>(Arrays.asList(new QueueType[] {QueueType.RANKED_SOLO_5x5, QueueType.RANKED_TEAM_3x3,
            QueueType.RANKED_TEAM_5x5}));

    /**
     * Gets the 15 most recent matches for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchhistory") + "/matchhistory/" + summonerID;
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), PlayerHistory.class);
    }

    /**
     * Gets the 15 most recent matches after beginIndex for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final int beginIndex) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchhistory") + "/matchhistory/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", beginIndex).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), PlayerHistory.class);
    }

    /**
     * Gets the 15 most recent matches after beginIndex for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param championIDs
     *            the champions to limit games to
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final int beginIndex, final List<Long> championIDs) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchhistory") + "/matchhistory/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", beginIndex).add("championIds", Utils.getIDString(championIDs)).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), PlayerHistory.class);
    }

    /**
     * Gets the 15 most recent matches after beginIndex for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final int beginIndex, final QueueType queueType) {
        if(!RANKED_QUEUES.contains(queueType)) {
            throw new IllegalArgumentException("Can't get challenger for a non-ranked queue type!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("matchhistory") + "/matchhistory/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", beginIndex).add("rankedQueues", queueType).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), PlayerHistory.class);
    }

    /**
     * Gets the 15 most recent matches after beginIndex for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final int beginIndex, final QueueType queueType, final List<Long> championIDs) {
        if(!RANKED_QUEUES.contains(queueType)) {
            throw new IllegalArgumentException("Can't get challenger for a non-ranked queue type!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("matchhistory") + "/matchhistory/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("beginIndex", beginIndex).add("rankedQueues", queueType)
                .add("championIds", Utils.getIDString(championIDs)).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), PlayerHistory.class);
    }

    /**
     * Gets the 15 most recent matches for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param championIDs
     *            the champions to limit games to
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final List<Long> championIDs) {
        final String request = BaseRiotAPI.API_VERSIONS.get("matchhistory") + "/matchhistory/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("championIds", Utils.getIDString(championIDs)).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), PlayerHistory.class);
    }

    /**
     * Gets the 15 most recent matches for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final QueueType queueType) {
        if(!RANKED_QUEUES.contains(queueType)) {
            throw new IllegalArgumentException("Can't get challenger for a non-ranked queue type!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("matchhistory") + "/matchhistory/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("rankedQueues", queueType).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), PlayerHistory.class);
    }

    /**
     * Gets the 15 most recent matches for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final QueueType queueType, final List<Long> championIDs) {
        if(!RANKED_QUEUES.contains(queueType)) {
            throw new IllegalArgumentException("Can't get challenger for a non-ranked queue type!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("matchhistory") + "/matchhistory/" + summonerID;
        final Map<String, String> params = new ParamsBuilder().add("rankedQueues", queueType).add("championIds", Utils.getIDString(championIDs)).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), PlayerHistory.class);
    }
}
