package com.robrua.orianna.api.dto;

import java.util.Map;

import com.robrua.orianna.type.api.ParamsBuilder;
import com.robrua.orianna.type.dto.champion.Champion;
import com.robrua.orianna.type.dto.champion.ChampionList;

public abstract class ChampionAPI {
    /**
     * @param ID
     *            the ID of the champion to look up
     * @return the champion
     * @see <a href="https://developer.riotgames.com/api/methods#!/958/3289">
     *      Riot API Specification</a>
     */
    public static Champion getChampionStatus(final long ID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("champion") + "/champion/" + ID;
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), Champion.class);
    }

    /**
     * @param freeToPlay
     *            whether to only return free champions
     * @return all champions
     * @see <a href="https://developer.riotgames.com/api/methods#!/958/3290">
     *      Riot API Specification</a>
     */
    public static ChampionList getChampionStatuses(final boolean freeToPlay) {
        final String request = BaseRiotAPI.API_VERSIONS.get("champion") + "/champion";
        final Map<String, String> params = new ParamsBuilder().add("freeToPlay", freeToPlay).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), ChampionList.class);
    }
}
