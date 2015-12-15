package com.robrua.orianna.api.dto;

import com.robrua.orianna.type.dto.game.RecentGames;

public abstract class GameAPI {
    /**
     * @param summonerID
     *            the ID of the summoner to look up recent games for
     * @return the summoner's recent games
     * @see <a href="https://developer.riotgames.com/api/methods#!/959/3291">
     *      Riot API Specification</a>
     */
    public static RecentGames getRecentGames(final long summonerID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("game") + "/game/by-summoner/" + summonerID + "/recent";
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), RecentGames.class);
    }
}
