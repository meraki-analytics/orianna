package com.robrua.orianna.type.core.common;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.status.ShardStatus;

public enum Region {
    BR, EUNE, EUW, JP, KR, LAN, LAS, NA, OCE, PBE, RU, TR;

    /**
     * The status shard for this region
     *
     * @return the status shard for this region
     */
    public ShardStatus getShard() {
        return RiotAPI.getShard(this);
    }
}
