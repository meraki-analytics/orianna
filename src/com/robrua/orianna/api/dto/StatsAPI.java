package com.robrua.orianna.api.dto;

import java.util.Map;

import com.robrua.orianna.type.api.ParamsBuilder;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.dto.stats.PlayerStatsSummaryList;
import com.robrua.orianna.type.dto.stats.RankedStats;

public abstract class StatsAPI {
    /**
     * @param summonerID
     *            the ID of the summoner to get ranked stats for
     * @return the ranked stats for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/961/3297">
     *      Riot API Specification</a>
     */
    public static RankedStats getRankedStats(final long summonerID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("stats") + "/stats/by-summoner/" + summonerID + "/ranked";
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), RankedStats.class);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get ranked stats for
     * @param season
     *            the season to get stats for
     * @return the ranked stats for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/961/3297">
     *      Riot API Specification</a>
     */
    public static RankedStats getRankedStats(final long summonerID, final Season season) {
        final String request = BaseRiotAPI.API_VERSIONS.get("stats") + "/stats/by-summoner/" + summonerID + "/ranked";
        final Map<String, String> params = new ParamsBuilder().add("season", season).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), RankedStats.class);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get stats for
     * @return the stats for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/961/3298">
     *      Riot API Specification</a>
     */
    public static PlayerStatsSummaryList getStats(final long summonerID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("stats") + "/stats/by-summoner/" + summonerID + "/summary";
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), PlayerStatsSummaryList.class);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get stats for
     * @param season
     *            the season to get stats for
     * @return the stats for that summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/961/3298">
     *      Riot API Specification</a>
     */
    public static PlayerStatsSummaryList getStats(final long summonerID, final Season season) {
        final String request = BaseRiotAPI.API_VERSIONS.get("stats") + "/stats/by-summoner/" + summonerID + "/summary";
        final Map<String, String> params = new ParamsBuilder().add("season", season).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), PlayerStatsSummaryList.class);
    }
}
