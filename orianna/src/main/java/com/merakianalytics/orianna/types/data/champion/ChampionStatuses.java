package com.merakianalytics.orianna.types.data.champion;

import com.merakianalytics.orianna.types.data.CoreData;

public class ChampionStatuses extends CoreData.ListProxy<ChampionStatus> {
    private static final long serialVersionUID = -691536865912805170L;
    private boolean freeToPlay;
    private String platform;

    public ChampionStatuses() {
        super();
    }

    public ChampionStatuses(final int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(!super.equals(obj)) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final ChampionStatuses other = (ChampionStatuses)obj;
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
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
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
