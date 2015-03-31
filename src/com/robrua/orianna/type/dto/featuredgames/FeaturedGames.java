package com.robrua.orianna.type.dto.featuredgames;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;
import com.robrua.orianna.type.dto.currentgame.CurrentGameInfo;

@Entity
@Table(name = "featuredgame")
public class FeaturedGames extends OriannaDto {
    private static final long serialVersionUID = 4926425430293315274L;
    private Long clientRefreshInterval;

    @Id
    private final long dbId = 0;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CurrentGameInfo> gameList;

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
        if(!(obj instanceof FeaturedGames)) {
            return false;
        }
        final FeaturedGames other = (FeaturedGames)obj;
        if(clientRefreshInterval == null) {
            if(other.clientRefreshInterval != null) {
                return false;
            }
        }
        else if(!clientRefreshInterval.equals(other.clientRefreshInterval)) {
            return false;
        }
        if(gameList == null) {
            if(other.gameList != null) {
                return false;
            }
        }
        else if(!gameList.equals(other.gameList)) {
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
        for(final CurrentGameInfo game : gameList) {
            set.addAll(game.getChampionIDs());
        }

        return set;
    }

    /**
     * @return the clientRefreshInterval
     */
    public Long getClientRefreshInterval() {
        return clientRefreshInterval;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the gameList
     */
    public List<CurrentGameInfo> getGameList() {
        return gameList;
    }

    /**
     * Gets all stored mastery IDs for batch lookup
     *
     * @return the mastery IDs
     */
    public Set<Long> getMasteryIDs() {
        final Set<Long> set = new HashSet<>();
        for(final CurrentGameInfo game : gameList) {
            set.addAll(game.getMasteryIDs());
        }

        return set;
    }

    /**
     * Gets all stored rune IDs for batch lookup
     *
     * @return the rune IDs
     */
    public Set<Long> getRuneIDs() {
        final Set<Long> set = new HashSet<>();
        for(final CurrentGameInfo game : gameList) {
            set.addAll(game.getRuneIDs());
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
        for(final CurrentGameInfo game : gameList) {
            set.addAll(game.getSummonerIDs());
        }

        return set;
    }

    /**
     * Gets all stored summoner names for batch lookup
     *
     * @return the summoner names
     */
    public Set<String> getSummonerNames() {
        final Set<String> set = new HashSet<>();
        for(final CurrentGameInfo game : gameList) {
            set.addAll(game.getSummonerNames());
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
        for(final CurrentGameInfo game : gameList) {
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
        result = prime * result + (clientRefreshInterval == null ? 0 : clientRefreshInterval.hashCode());
        result = prime * result + (gameList == null ? 0 : gameList.hashCode());
        return result;
    }

    /**
     * @param clientRefreshInterval
     *            the clientRefreshInterval to set
     */
    public void setClientRefreshInterval(final Long clientRefreshInterval) {
        this.clientRefreshInterval = clientRefreshInterval;
    }

    /**
     * @param gameList
     *            the gameList to set
     */
    public void setGameList(final List<CurrentGameInfo> gameList) {
        this.gameList = gameList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FeaturedGames [clientRefreshInterval=" + clientRefreshInterval + ", gameList=" + gameList + "]";
    }
}
