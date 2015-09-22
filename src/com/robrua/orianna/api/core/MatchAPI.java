package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.core.match.Match;
import com.robrua.orianna.type.core.matchlist.MatchReference;
import com.robrua.orianna.type.dto.match.MatchDetail;

public abstract class MatchAPI {
    /**
     * @param ID
     *            the ID of the match to get
     * @return the match
     */
    public synchronized static Match getMatch(final long ID) {
        return getMatch(ID, true);
    }

    /**
     * @param ID
     *            the ID of the match to get
     * @param includeTimeline
     *            whether to include timeline data in the returned match
     * @return the match
     */
    public synchronized static Match getMatch(final long ID, final boolean includeTimeline) {
        Match match = RiotAPI.store.get(Match.class, ID);
        if(match != null && (match.getTimeline() != null || !includeTimeline)) {
            return match;
        }

        final MatchDetail mtch = BaseRiotAPI.getMatch(ID, includeTimeline);
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
     * @param reference
     *            the match reference to get the match for
     * @return the match
     */
    public synchronized static Match getMatchByReference(final MatchReference reference) {
        return getMatch(reference.getID(), true);
    }

    /**
     * @param reference
     *            the match reference to get the match for
     * @param includeTimeline
     *            whether to include timeline data in the returned match
     * @return the match
     */
    public synchronized static Match getMatchByReference(final MatchReference reference, final boolean includeTimeline) {
        return getMatch(reference.getID(), includeTimeline);
    }

    /**
     * @param IDs
     *            the IDs of the matches to get
     * @return the matches
     */
    public synchronized static List<Match> getMatches(final List<Long> IDs) {
        return getMatches(IDs, true);
    }

    /**
     * @param IDs
     *            the IDs of the matches to get
     * @param includeTimeline
     *            whether to include timeline data in the returned matches
     * @return the matches
     */
    public synchronized static List<Match> getMatches(final List<Long> IDs, final boolean includeTimeline) {
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
            if(matches.get(i) == null || includeTimeline && matches.get(i).getTimeline() == null) {
                final MatchDetail mtch = BaseRiotAPI.getMatch(IDs.get(i), includeTimeline);

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

    /**
     * @param references
     *            the match references to get the matches for
     * @return the matches
     */
    public synchronized static List<Match> getMatchesByReference(final List<MatchReference> references) {
        final List<Long> IDs = new ArrayList<>(references.size());
        for(final MatchReference ref : references) {
            IDs.add(ref.getID());
        }
        return getMatches(IDs, true);
    }

    /**
     * @param references
     *            the match references to get the matches for
     * @param includeTimeline
     *            whether to include timeline data in the returned matches
     * @return the matches
     */
    public synchronized static List<Match> getMatchesByReference(final List<MatchReference> references, final boolean includeTimeline) {
        final List<Long> IDs = new ArrayList<>(references.size());
        for(final MatchReference ref : references) {
            IDs.add(ref.getID());
        }
        return getMatches(IDs, includeTimeline);
    }
}
