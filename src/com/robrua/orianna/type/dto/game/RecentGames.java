package com.robrua.orianna.type.dto.game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "recentgame")
public class RecentGames extends OriannaDto {
    private static final long serialVersionUID = -6915315454587967212L;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Game> games;

    @Id
    private Long summonerId;

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
        if(!(obj instanceof RecentGames)) {
            return false;
        }
        final RecentGames other = (RecentGames)obj;
        if(games == null) {
            if(other.games != null) {
                return false;
            }
        }
        else if(!games.equals(other.games)) {
            return false;
        }
        if(summonerId == null) {
            if(other.summonerId != null) {
                return false;
            }
        }
        else if(!summonerId.equals(other.summonerId)) {
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
        for(final Game game : games) {
            set.addAll(game.getChampionIDs());
        }

        return set;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "summonerId";
        }
        return null;
    }

    /**
     * @return the games
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     * Gets all stored item IDs for batch lookup
     *
     * @return the item IDs
     */
    public Set<Long> getItemIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Game game : games) {
            set.addAll(game.getItemIDs());
        }

        return set;
    }

    /**
     * @return the summonerId
     */
    public Long getSummonerId() {
        return summonerId;
    }

    /**
     * Gets all stored summoner IDs for batch lookup
     *
     * @return the summoner IDs
     */
    public Set<Long> getSummonerIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Game game : games) {
            set.addAll(game.getSummonerIDs());
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
        for(final Game game : games) {
            set.addAll(game.getSummonerSpellIDs());
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
        result = prime * result + (games == null ? 0 : games.hashCode());
        result = prime * result + (summonerId == null ? 0 : summonerId.hashCode());
        return result;
    }

    /**
     * @param games
     *            the games to set
     */
    public void setGames(final List<Game> games) {
        this.games = games;
    }

    /**
     * @param summonerId
     *            the summonerId to set
     */
    public void setSummonerId(final Long summonerId) {
        this.summonerId = summonerId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RecentGames [games=" + games + ", summonerId=" + summonerId + "]";
    }
}
