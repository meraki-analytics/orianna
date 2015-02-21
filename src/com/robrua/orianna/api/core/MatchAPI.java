package com.robrua.orianna.api.core;

import java.util.ArrayList;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.core.match.Match;
import com.robrua.orianna.type.dto.match.MatchDetail;

public abstract class MatchAPI {
    /**
     * @param ID
     *            the ID of the match to get
     * @return the match
     */
    public synchronized static Match getMatch(final long ID) {
        Match match = RiotAPI.store.get(Match.class, ID);
        if(match != null) {
            return match;
        }

        final MatchDetail mtch = BaseRiotAPI.getMatch(ID);
        match = new Match(mtch);

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(mtch.getItemIDs()));
            RiotAPI.getChampions(new ArrayList<>(mtch.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(mtch.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(mtch.getRuneIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(mtch.getSummonerIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(mtch.getSummonerSpellIDs()));
        }

        RiotAPI.store.store(match, ID);

        return match;
    }
}
