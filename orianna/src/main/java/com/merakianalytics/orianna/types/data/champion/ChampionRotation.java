package com.merakianalytics.orianna.types.data.champion;

import java.util.Set;

import com.merakianalytics.orianna.types.data.CoreData;

public class ChampionRotation extends CoreData {
    private static final long serialVersionUID = 2890283121406577730L;
    private Set<Integer> freeChampionIds, freeChampionIdsForNewPlayers;
    private int maxNewPlayerLevel;
    private String platform;

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final ChampionRotation other = (ChampionRotation)obj;
        if(freeChampionIds == null) {
            if(other.freeChampionIds != null) {
                return false;
            }
        } else if(!freeChampionIds.equals(other.freeChampionIds)) {
            return false;
        }
        if(freeChampionIdsForNewPlayers == null) {
            if(other.freeChampionIdsForNewPlayers != null) {
                return false;
            }
        } else if(!freeChampionIdsForNewPlayers.equals(other.freeChampionIdsForNewPlayers)) {
            return false;
        }
        if(maxNewPlayerLevel != other.maxNewPlayerLevel) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        return true;
    }

    /**
     * @return the freeChampionIds
     */
    public Set<Integer> getFreeChampionIds() {
        return freeChampionIds;
    }

    /**
     * @return the freeChampionIdsForNewPlayers
     */
    public Set<Integer> getFreeChampionIdsForNewPlayers() {
        return freeChampionIdsForNewPlayers;
    }

    /**
     * @return the maxNewPlayerLevel
     */
    public int getMaxNewPlayerLevel() {
        return maxNewPlayerLevel;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (freeChampionIds == null ? 0 : freeChampionIds.hashCode());
        result = prime * result + (freeChampionIdsForNewPlayers == null ? 0 : freeChampionIdsForNewPlayers.hashCode());
        result = prime * result + maxNewPlayerLevel;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        return result;
    }

    /**
     * @param freeChampionIds
     *        the freeChampionIds to set
     */
    public void setFreeChampionIds(final Set<Integer> freeChampionIds) {
        this.freeChampionIds = freeChampionIds;
    }

    /**
     * @param freeChampionIdsForNewPlayers
     *        the freeChampionIdsForNewPlayers to set
     */
    public void setFreeChampionIdsForNewPlayers(final Set<Integer> freeChampionIdsForNewPlayers) {
        this.freeChampionIdsForNewPlayers = freeChampionIdsForNewPlayers;
    }

    /**
     * @param maxNewPlayerLevel
     *        the maxNewPlayerLevel to set
     */
    public void setMaxNewPlayerLevel(final int maxNewPlayerLevel) {
        this.maxNewPlayerLevel = maxNewPlayerLevel;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
}
