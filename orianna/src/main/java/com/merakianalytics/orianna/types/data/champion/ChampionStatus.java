package com.merakianalytics.orianna.types.data.champion;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.CoreData;

public class ChampionStatus extends CoreData {
    private static final long serialVersionUID = 4229915263452433550L;
    private boolean enabledInRanked, enabledInCustoms, enabledInCoopVsAI, enabled, freeToPlay;
    private int id;
    private Platform platform;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
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
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }
}
