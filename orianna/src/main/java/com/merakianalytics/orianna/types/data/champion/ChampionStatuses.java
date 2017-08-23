package com.merakianalytics.orianna.types.data.champion;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.CoreData;

public class ChampionStatuses extends CoreData.ListProxy<ChampionStatus> {
    private static final long serialVersionUID = 8545188470790391941L;
    private boolean freeToPlay;
    private Platform platform;

    public ChampionStatuses() {
        super();
    }

    public ChampionStatuses(final int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
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
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }
}
