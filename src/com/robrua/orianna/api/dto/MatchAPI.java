package com.robrua.orianna.api.dto;

import java.util.Map;

import com.robrua.orianna.type.api.ParamsBuilder;
import com.robrua.orianna.type.dto.match.MatchDetail;

public abstract class MatchAPI {
    /**
     * @param ID
     *            the ID of the match to look up
     * @return the match
     * @see <a href="https://developer.riotgames.com/api/methods#!/967/3313">
     *      Riot API Specification</a>
     */
    public static MatchDetail getMatch(final long ID) {
        return getMatch(ID, true);
    }

    /**
     * @param ID
     *            the ID of the match to look up
     * @param includeTimeline
     *            whether to include timeline data in the returned match
     * @return the match
     * @see <a href="https://developer.riotgames.com/api/methods#!/967/3313">
     *      Riot API Specification</a>
     */
    public static MatchDetail getMatch(final long ID, final boolean includeTimeline) {
        final String request = BaseRiotAPI.API_VERSIONS.get("match") + "/match/" + ID;
        final Map<String, String> params = new ParamsBuilder().add("includeTimeline", includeTimeline).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchDetail.class);
    }
}
