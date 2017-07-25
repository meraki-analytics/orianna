package com.merakianalytics.orianna.type.dto.spectator;

import java.util.List;

import com.merakianalytics.orianna.type.dto.DataObject;

public class FeaturedGames extends DataObject {
    private static final long serialVersionUID = -355626288926056661L;
    private long clientRefreshInterval;
    private List<FeaturedGameInfo> gameList;
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
        final FeaturedGames other = (FeaturedGames)obj;
        if(clientRefreshInterval != other.clientRefreshInterval) {
            return false;
        }
        if(gameList == null) {
            if(other.gameList != null) {
                return false;
            }
        } else if(!gameList.equals(other.gameList)) {
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
     * @return the clientRefreshInterval
     */
    public long getClientRefreshInterval() {
        return clientRefreshInterval;
    }

    /**
     * @return the gameList
     */
    public List<FeaturedGameInfo> getGameList() {
        return gameList;
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
        result = prime * result + (int)(clientRefreshInterval ^ clientRefreshInterval >>> 32);
        result = prime * result + (gameList == null ? 0 : gameList.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        return result;
    }

    /**
     * @param clientRefreshInterval
     *        the clientRefreshInterval to set
     */
    public void setClientRefreshInterval(final long clientRefreshInterval) {
        this.clientRefreshInterval = clientRefreshInterval;
    }

    /**
     * @param gameList
     *        the gameList to set
     */
    public void setGameList(final List<FeaturedGameInfo> gameList) {
        this.gameList = gameList;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
}
