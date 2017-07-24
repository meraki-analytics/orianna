package com.merakianalytics.orianna.type.dto.champion;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Champion extends DataObject {
    private static final long serialVersionUID = -1306681058318007398L;
    private long id;
    private String platform;
    private boolean rankedPlayEnabled, botEnabled, botMmEnabled, active, freeToPlay;

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
        final Champion other = (Champion)obj;
        if(active != other.active) {
            return false;
        }
        if(botEnabled != other.botEnabled) {
            return false;
        }
        if(botMmEnabled != other.botMmEnabled) {
            return false;
        }
        if(freeToPlay != other.freeToPlay) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(rankedPlayEnabled != other.rankedPlayEnabled) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
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
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + (botEnabled ? 1231 : 1237);
        result = prime * result + (botMmEnabled ? 1231 : 1237);
        result = prime * result + (freeToPlay ? 1231 : 1237);
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (rankedPlayEnabled ? 1231 : 1237);
        return result;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @return the botEnabled
     */
    public boolean isBotEnabled() {
        return botEnabled;
    }

    /**
     * @return the botMmEnabled
     */
    public boolean isBotMmEnabled() {
        return botMmEnabled;
    }

    /**
     * @return the freeToPlay
     */
    public boolean isFreeToPlay() {
        return freeToPlay;
    }

    /**
     * @return the rankedPlayEnabled
     */
    public boolean isRankedPlayEnabled() {
        return rankedPlayEnabled;
    }

    /**
     * @param active
     *        the active to set
     */
    public void setActive(final boolean active) {
        this.active = active;
    }

    /**
     * @param botEnabled
     *        the botEnabled to set
     */
    public void setBotEnabled(final boolean botEnabled) {
        this.botEnabled = botEnabled;
    }

    /**
     * @param botMmEnabled
     *        the botMmEnabled to set
     */
    public void setBotMmEnabled(final boolean botMmEnabled) {
        this.botMmEnabled = botMmEnabled;
    }

    /**
     * @param freeToPlay
     *        the freeToPlay to set
     */
    public void setFreeToPlay(final boolean freeToPlay) {
        this.freeToPlay = freeToPlay;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param rankedPlayEnabled
     *        the rankedPlayEnabled to set
     */
    public void setRankedPlayEnabled(final boolean rankedPlayEnabled) {
        this.rankedPlayEnabled = rankedPlayEnabled;
    }
}
