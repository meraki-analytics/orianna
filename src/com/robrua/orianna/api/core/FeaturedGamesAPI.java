package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.dto.featuredgames.FeaturedGames;

public abstract class FeaturedGamesAPI {
    /**
     * @return the current featured games
     */
    public static List<CurrentGame> getFeaturedGames() {
        final List<CurrentGame> games = new ArrayList<>();

        final FeaturedGames gms = BaseRiotAPI.getFeaturedGames();
        for(final com.robrua.orianna.type.dto.currentgame.CurrentGameInfo game : gms.getGameList()) {
            games.add(new CurrentGame(game));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(gms.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(gms.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(gms.getRuneIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(gms.getSummonerSpellIDs()));
            RiotAPI.getSummonersByName(new ArrayList<>(gms.getSummonerNames()));
        }

        return Collections.unmodifiableList(games);
    }
}
