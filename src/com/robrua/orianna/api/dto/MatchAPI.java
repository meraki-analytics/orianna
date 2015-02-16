package com.robrua.orianna.api.dto;

import java.util.Map;

import com.robrua.orianna.type.api.ParamsBuilder;
import com.robrua.orianna.type.dto.match.MatchDetail;

public abstract class MatchAPI {
    /**
     * @param ID
     *            the ID of the match to look up
     * @return the match
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/967/3313">Riot
     *      API Specification</a>
     */
    public static MatchDetail getMatch(final long ID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("match") + "/match/" + ID;
        final Map<String, String> params = new ParamsBuilder().add("includeTimeline", true).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), MatchDetail.class);
    }
}
