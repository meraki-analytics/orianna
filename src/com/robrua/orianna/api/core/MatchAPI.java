package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            RiotAPI.getChampionsByID(new ArrayList<>(mtch.getChampionIDs()));
            RiotAPI.getMasteries(new ArrayList<>(mtch.getMasteryIDs()));
            RiotAPI.getRunes(new ArrayList<>(mtch.getRuneIDs()));
            RiotAPI.getSummonersByID(new ArrayList<>(mtch.getSummonerIDs()));
            RiotAPI.getSummonerSpells(new ArrayList<>(mtch.getSummonerSpellIDs()));
        }

        RiotAPI.store.store(match, ID);

        return match;
    }

    /**
     * @param IDs
     *            the IDs of the matches to get
     * @return the matches
     */
    public synchronized static List<Match> getMatches(final List<Long> IDs) {
        if(IDs.isEmpty()) {
            return Collections.emptyList();
        }

        Set<Long> itemIDs = null, championIDs = null, masteryIDs = null, runeIDs = null, summonerIDs = null, summonerSpellIDs = null;
        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            itemIDs = new HashSet<>();
            championIDs = new HashSet<>();
            masteryIDs = new HashSet<>();
            runeIDs = new HashSet<>();
            summonerIDs = new HashSet<>();
            summonerSpellIDs = new HashSet<>();
        }

        final List<Match> matches = RiotAPI.store.get(Match.class, IDs);
        final List<Match> newMatches = new ArrayList<>();
        final List<Long> newIDs = new ArrayList<>();
        for(int i = 0; i < matches.size(); i++) {
            if(matches.get(i) == null) {
                final MatchDetail mtch = BaseRiotAPI.getMatch(IDs.get(i));

                if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
                    itemIDs.addAll(mtch.getItemIDs());
                    championIDs.addAll(mtch.getChampionIDs());
                    masteryIDs.addAll(mtch.getMasteryIDs());
                    runeIDs.addAll(mtch.getRuneIDs());
                    summonerIDs.addAll(mtch.getSummonerIDs());
                    summonerSpellIDs.addAll(mtch.getSummonerSpellIDs());
                }

                final Match match = new Match(mtch);
                newMatches.add(match);
                newIDs.add(IDs.get(i));
                matches.set(i, match);
            }
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(itemIDs));
            RiotAPI.getChampionsByID(new ArrayList<>(championIDs));
            RiotAPI.getMasteries(new ArrayList<>(masteryIDs));
            RiotAPI.getRunes(new ArrayList<>(runeIDs));
            RiotAPI.getSummonersByID(new ArrayList<>(summonerIDs));
            RiotAPI.getSummonerSpells(new ArrayList<>(summonerSpellIDs));
        }

        RiotAPI.store.store(newMatches, newIDs, false);

        return matches;
    }
}
