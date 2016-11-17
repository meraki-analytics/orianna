package com.robrua.orianna.api.dto;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.reflect.TypeToken;
import com.robrua.orianna.api.Utils;
import com.robrua.orianna.type.api.ParamsBuilder;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.dto.league.League;

public abstract class LeagueAPI {
    private static final Set<QueueType> RANKED_QUEUES = new HashSet<>(Arrays.asList(new QueueType[] {QueueType.TEAM_BUILDER_DRAFT_RANKED_5x5,
            QueueType.RANKED_SOLO_5x5, QueueType.RANKED_TEAM_3x3, QueueType.RANKED_TEAM_5x5, QueueType.RANKED_FLEX_SR, QueueType.RANKED_FLEX_TT}));

    /**
     * @param queueType
     *            the queue type to get the challenger league for
     * @return the challenger league
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3243">
     *      Riot API Specification</a>
     */
    public static League getChallenger(final QueueType queueType) {
        if(!RANKED_QUEUES.contains(queueType)) {
            throw new IllegalArgumentException("Can't get challenger for a non-ranked queue type!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("league") + "/league/challenger";
        final Map<String, String> params = new ParamsBuilder().add("type", queueType).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), League.class);
    }

    /**
     * @param queueType
     *            the queue type to get the master league for
     * @return the master league
     * @see <a href="https://developer.riotgames.com/api/methods#!/985/3354">
     *      Riot API Specification</a>
     */
    public static League getMaster(final QueueType queueType) {
        if(!RANKED_QUEUES.contains(queueType)) {
            throw new IllegalArgumentException("Can't get master for a non-ranked queue type!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("league") + "/league/master";
        final Map<String, String> params = new ParamsBuilder().add("type", queueType).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), League.class);
    }

    /**
     * @param summonerIDs
     *            the summoners to get league entries for
     * @return the summoners' league entries
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3245">
     *      Riot API Specification</a>
     */
    public static Map<Long, List<League>> getSummonerLeagueEntries(final List<Long> summonerIDs) {
        if(summonerIDs.isEmpty()) {
            return new HashMap<>();
        }
        if(summonerIDs.size() > 10) {
            throw new IllegalArgumentException("Can't get league entries for more than 10 summoners!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("league") + "/league/by-summoner/" + Utils.getIDString(summonerIDs) + "/entry";
        final Type type = new TypeToken<Map<Long, List<League>>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param summonerIDs
     *            the summoners to get league entries for
     * @return the summoners' league entries
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3245">
     *      Riot API Specification</a>
     */
    public static Map<Long, List<League>> getSummonerLeagueEntries(final long... summonerIDs) {
        return getSummonerLeagueEntries(Utils.convert(summonerIDs));
    }

    /**
     * @param summonerIDs
     *            the summoners to get leagues for
     * @return the summoners' league
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3241">
     *      Riot API Specification</a>
     */
    public static Map<Long, List<League>> getSummonerLeagues(final List<Long> summonerIDs) {
        if(summonerIDs.isEmpty()) {
            return new HashMap<>();
        }
        if(summonerIDs.size() > 10) {
            throw new IllegalArgumentException("Can't get leagues for more than 10 summoners!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("league") + "/league/by-summoner/" + Utils.getIDString(summonerIDs);
        final Type type = new TypeToken<Map<Long, List<League>>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param summonerIDs
     *            the summoners to get leagues for
     * @return the summoners' league
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3241">
     *      Riot API Specification</a>
     */
    public static Map<Long, List<League>> getSummonerLeagues(final long... summonerIDs) {
        return getSummonerLeagues(Utils.convert(summonerIDs));
    }

    /**
     * @param teamIDs
     *            the summoners to get leagues for
     * @return the team's leagues
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3242">
     *      Riot API Specification</a>
     */
    public static Map<String, List<League>> getTeamLeagueEntries(final List<String> teamIDs) {
        if(teamIDs.isEmpty()) {
            return new HashMap<>();
        }
        if(teamIDs.size() > 10) {
            throw new IllegalArgumentException("Can't get leagues for more than 10 teams!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("league") + "/league/by-team/" + Utils.getIDString(teamIDs) + "/entry";
        final Type type = new TypeToken<Map<String, List<League>>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param teamIDs
     *            the summoners to get leagues for
     * @return the team's leagues
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3242">
     *      Riot API Specification</a>
     */
    public static Map<String, List<League>> getTeamLeagueEntries(final String... teamIDs) {
        return getTeamLeagueEntries(Arrays.asList(teamIDs));
    }

    /**
     * @param teamIDs
     *            the summoners to get leagues for
     * @return the team's leagues
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3242">
     *      Riot API Specification</a>
     */
    public static Map<String, List<League>> getTeamLeagues(final List<String> teamIDs) {
        if(teamIDs.isEmpty()) {
            return new HashMap<>();
        }
        if(teamIDs.size() > 10) {
            throw new IllegalArgumentException("Can't get leagues for more than 10 teams!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("league") + "/league/by-team/" + Utils.getIDString(teamIDs);
        final Type type = new TypeToken<Map<String, List<League>>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param teamIDs
     *            the summoners to get leagues for
     * @return the team's leagues
     * @see <a href="https://developer.riotgames.com/api/methods#!/936/3242">
     *      Riot API Specification</a>
     */
    public static Map<String, List<League>> getTeamLeagues(final String... teamIDs) {
        return getTeamLeagues(Arrays.asList(teamIDs));
    }
}
