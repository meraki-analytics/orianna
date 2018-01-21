package com.merakianalytics.orianna.types.data.spectator;

import java.util.List;

import com.merakianalytics.orianna.types.data.CoreData;

public class Team extends CoreData {
    private static final long serialVersionUID = 6157643673390149489L;
    private List<Integer> bans;
    private String platform;
    private int side;

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
        final Team other = (Team)obj;
        if(bans == null) {
            if(other.bans != null) {
                return false;
            }
        } else if(!bans.equals(other.bans)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(side != other.side) {
            return false;
        }
        return true;
    }

    /**
     * @return the bans
     */
    public List<Integer> getBans() {
        return bans;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the side
     */
    public int getSide() {
        return side;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bans == null ? 0 : bans.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + side;
        return result;
    }

    /**
     * @param bans
     *        the bans to set
     */
    public void setBans(final List<Integer> bans) {
        this.bans = bans;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param side
     *        the side to set
     */
    public void setSide(final int side) {
        this.side = side;
    }
}
