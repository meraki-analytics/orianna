package com.robrua.orianna.api.core;

import java.util.ArrayList;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.summoner.Summoner;

public abstract class CurrentGameAPI {
    /**
     * @param summonerID
     *            the ID of the summoner to get current game for
     * @return the summoner's current game or null if they aren't in one
     */
    public static CurrentGame getCurrentGame(final long summonerID) {
        final com.robrua.orianna.type.dto.currentgame.CurrentGameInfo game = BaseRiotAPI.getCurrentGame(summonerID);

        if(game == null) {
            return null;
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getChampionsByID(new ArrayList<>(game.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(game.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(game.getRuneIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(game.getSummonerSpellIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(game.getSummonerIDs()));
        }

        return new CurrentGame(game);
    }

    /**
     * @param summonerName
     *            the name of the summoner to get current game for
     * @return the summoner's current game or null if they aren't in one
     */
    public static CurrentGame getCurrentGame(final String summonerName) {
        return getCurrentGame(RiotAPI.getSummonerByName(summonerName));
    }

    /**
     * @param summoner
     *            the summoner to get current game for
     * @return the summoner's current game or null if they aren't in one
     */
    public static CurrentGame getCurrentGame(final Summoner summoner) {
        return getCurrentGame(summoner.getID());
    }
}
