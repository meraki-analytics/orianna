package com.robrua.orianna.api.dto;

import com.robrua.orianna.type.dto.featuredgames.FeaturedGames;

public abstract class FeaturedGamesAPI {
    /**
     * @return the featured games
     * @see <a href="https://developer.riotgames.com/api/methods#!/957/3288">
     *      Riot API Specification</a>
     */
    public static FeaturedGames getFeaturedGames() {
        final String request = "/observer-mode/rest/featured";
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.getRoot(request, null, false), FeaturedGames.class);
    }
}
