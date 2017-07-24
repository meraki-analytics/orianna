package com.merakianalytics.orianna.type.dto.champion;

import java.util.List;

import com.merakianalytics.orianna.type.dto.DataObject;

public class ChampionList extends DataObject {
    private static final long serialVersionUID = -5019164862747328752L;
    private List<Champion> champions;
    private boolean freeToPlay;
    private String platform;

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
        if(getClass() != obj.getClass()) {
            return false;
        }
        final ChampionList other = (ChampionList)obj;
        if(champions == null) {
            if(other.champions != null) {
                return false;
            }
        } else if(!champions.equals(other.champions)) {
            return false;
        }
        if(freeToPlay != other.freeToPlay) {
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
     * @return the champions
     */
    public List<Champion> getChampions() {
        return champions;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (champions == null ? 0 : champions.hashCode());
        result = prime * result + (freeToPlay ? 1231 : 1237);
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        return result;
    }

    /**
     * @return the freeToPlay
     */
    public boolean isFreeToPlay() {
        return freeToPlay;
    }

    /**
     * @param champions
     *        the champions to set
     */
    public void setChampions(final List<Champion> champions) {
        this.champions = champions;
    }

    /**
     * @param freeToPlay
     *        the freeToPlay to set
     */
    public void setFreeToPlay(final boolean freeToPlay) {
        this.freeToPlay = freeToPlay;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
}
