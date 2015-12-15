package com.robrua.orianna.api.dto;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.robrua.orianna.api.Utils;
import com.robrua.orianna.type.dto.team.Team;

public abstract class TeamAPI {
    /**
     * @param IDs
     *            the IDs of the teams
     * @return the teams
     * @see <a href="https://developer.riotgames.com/api/methods#!/937/3246">
     *      Riot API Specification</a>
     */
    public static Map<String, Team> getTeamsByID(final List<String> IDs) {
        if(IDs.size() > 10) {
            throw new IllegalArgumentException("Can only get up to 10 teams at a time!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("team") + "/team/" + Utils.getIDString(IDs);
        final Type type = new TypeToken<Map<String, Team>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param IDs
     *            the IDs of the teams
     * @return the teams
     * @see <a href="https://developer.riotgames.com/api/methods#!/937/3246">
     *      Riot API Specification</a>
     */
    public static Map<String, Team> getTeamsByID(final String... IDs) {
        return getTeamsByID(Arrays.asList(IDs));
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     * @return the summoners' teams
     * @see <a href="https://developer.riotgames.com/api/methods#!/937/3247">
     *      Riot API Specification</a>
     */
    public static Map<Long, List<Team>> getTeamsBySummoner(final List<Long> summonerIDs) {
        if(summonerIDs.size() > 10) {
            throw new IllegalArgumentException("Can only get teams for up to 10 summoners at a time!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("team") + "/team/by-summoner/" + Utils.getIDString(summonerIDs);
        final Type type = new TypeToken<Map<Long, List<Team>>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     * @return the summoners' teams
     * @see <a href="https://developer.riotgames.com/api/methods#!/937/3247">
     *      Riot API Specification</a>
     */
    public static Map<Long, List<Team>> getTeamsBySummoner(final long... summonerIDs) {
        return getTeamsBySummoner(Utils.convert(summonerIDs));
    }
}
