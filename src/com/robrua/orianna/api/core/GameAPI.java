package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.dto.game.RecentGames;

public abstract class GameAPI {
    /**
     * @param summonerID
     *            the ID of the summoner to get recent games for
     * @return the summoner's recent games
     */
    public static List<Game> getRecentGames(final long summonerID) {
        final List<Game> games = new ArrayList<>();

        final RecentGames gms = BaseRiotAPI.getRecentGames(summonerID);
        for(final com.robrua.orianna.type.dto.game.Game game : gms.getGames()) {
            games.add(new Game(game));
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(gms.getItemIDs()));
            RiotAPI.getChampionsByID(new ArrayList<>(gms.getChampionIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(gms.getSummonerSpellIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(gms.getSummonerIDs()));
        }

        return Collections.unmodifiableList(games);
    }

    /**
     * @param summonerName
     *            the name of the summoner to get recent games for
     * @return the summoner's recent games
     */
    public static List<Game> getRecentGames(final String summonerName) {
        return getRecentGames(RiotAPI.getSummonerByName(summonerName));
    }

    /**
     * @param summoner
     *            the summoner to get recent games for
     * @return the summoner's recent games
     */
    public static List<Game> getRecentGames(final Summoner summoner) {
        return getRecentGames(summoner.getID());
    }
}
