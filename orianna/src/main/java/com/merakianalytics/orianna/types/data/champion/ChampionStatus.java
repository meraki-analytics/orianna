package com.merakianalytics.orianna.types.data.champion;

import com.merakianalytics.orianna.types.data.CoreData;

public class ChampionStatus extends CoreData {
    private static final long serialVersionUID = 2761744072076540550L;
    private boolean enabledInRanked, enabledInCustoms, enabledInCoopVsAI, enabled, freeToPlay;
    private int id;
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
        final ChampionStatus other = (ChampionStatus)obj;
        if(enabled != other.enabled) {
            return false;
        }
        if(enabledInCoopVsAI != other.enabledInCoopVsAI) {
            return false;
        }
        if(enabledInCustoms != other.enabledInCustoms) {
            return false;
        }
        if(enabledInRanked != other.enabledInRanked) {
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
        return true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
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
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result + (enabledInCoopVsAI ? 1231 : 1237);
        result = prime * result + (enabledInCustoms ? 1231 : 1237);
        result = prime * result + (enabledInRanked ? 1231 : 1237);
        result = prime * result + (freeToPlay ? 1231 : 1237);
        result = prime * result + id;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        return result;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @return the enabledInCoopVsAI
     */
    public boolean isEnabledInCoopVsAI() {
        return enabledInCoopVsAI;
    }

    /**
     * @return the enabledInCustoms
     */
    public boolean isEnabledInCustoms() {
        return enabledInCustoms;
    }

    /**
     * @return the enabledInRanked
     */
    public boolean isEnabledInRanked() {
        return enabledInRanked;
    }

    /**
     * @return the freeToPlay
     */
    public boolean isFreeToPlay() {
        return freeToPlay;
    }

    /**
     * @param enabled
     *        the enabled to set
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @param enabledInCoopVsAI
     *        the enabledInCoopVsAI to set
     */
    public void setEnabledInCoopVsAI(final boolean enabledInCoopVsAI) {
        this.enabledInCoopVsAI = enabledInCoopVsAI;
    }

    /**
     * @param enabledInCustoms
     *        the enabledInCustoms to set
     */
    public void setEnabledInCustoms(final boolean enabledInCustoms) {
        this.enabledInCustoms = enabledInCustoms;
    }

    /**
     * @param enabledInRanked
     *        the enabledInRanked to set
     */
    public void setEnabledInRanked(final boolean enabledInRanked) {
        this.enabledInRanked = enabledInRanked;
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
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
}
