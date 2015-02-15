package com.robrua.orianna.type.dto.matchhistory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.robrua.orianna.type.dto.OriannaDto;

public class PlayerHistory extends OriannaDto {
    private static final long serialVersionUID = 6788206005416074179L;
    private List<MatchSummary> matches;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof PlayerHistory)) {
            return false;
        }
        final PlayerHistory other = (PlayerHistory)obj;
        if(matches == null) {
            if(other.matches != null) {
                return false;
            }
        }
        else if(!matches.equals(other.matches)) {
            return false;
        }
        return true;
    }

    /**
     * Gets all stored champion IDs for batch lookup
     *
     * @return the champion IDs
     */
    public Set<Long> getChampionIDs() {
        final Set<Long> set = new HashSet<>();
        for(final MatchSummary match : matches) {
            set.addAll(match.getChampionIDs());
        }

        return set;
    }

    /**
     * Gets all stored item IDs for batch lookup
     *
     * @return the item IDs
     */
    public Set<Long> getItemIDs() {
        final Set<Long> set = new HashSet<>();
        for(final MatchSummary match : matches) {
            set.addAll(match.getItemIDs());
        }

        return set;
    }

    /**
     * Gets all stored mastery IDs for batch lookup
     *
     * @return the mastery IDs
     */
    public Set<Long> getMasteryIDs() {
        final Set<Long> set = new HashSet<>();
        for(final MatchSummary match : matches) {
            set.addAll(match.getMasteryIDs());
        }

        return set;
    }

    /**
     * @return the matches
     */
    public List<MatchSummary> getMatches() {
        return matches;
    }

    /**
     * Gets all stored rune IDs for batch lookup
     *
     * @return the rune IDs
     */
    public Set<Long> getRuneIDs() {
        final Set<Long> set = new HashSet<>();
        for(final MatchSummary match : matches) {
            set.addAll(match.getRuneIDs());
        }

        return set;
    }

    /**
     * Gets all stored summoner IDs for batch lookup
     *
     * @return the summoner IDs
     */
    public Set<Long> getSummonerIDs() {
        final Set<Long> set = new HashSet<>();
        for(final MatchSummary match : matches) {
            set.addAll(match.getSummonerIDs());
        }

        return set;
    }

    /**
     * Gets all stored summoner spell IDs for batch lookup
     *
     * @return the summoner spell IDs
     */
    public Set<Long> getSummonerSpellIDs() {
        final Set<Long> set = new HashSet<>();
        for(final MatchSummary match : matches) {
            set.addAll(match.getSummonerSpellIDs());
        }

        return set;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (matches == null ? 0 : matches.hashCode());
        return result;
    }

    /**
     * @param matches
     *            the matches to set
     */
    public void setMatches(final List<MatchSummary> matches) {
        this.matches = matches;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PlayerHistory [matches=" + matches + "]";
    }
}
