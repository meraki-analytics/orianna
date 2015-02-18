package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.status.Shard;
import com.robrua.orianna.type.core.status.ShardStatus;

public abstract class StatusAPI {
    /**
     * @param region
     *            the region to get the shard for
     * @return the status shard for that region
     */
    public static ShardStatus getShard(final Region region) {
        return new ShardStatus(BaseRiotAPI.getShard(region));
    }

    /**
     * @return the status shard list
     */
    public static List<Shard> getShards() {
        final List<com.robrua.orianna.type.dto.status.Shard> shrds = BaseRiotAPI.getShards();
        final List<Shard> shards = new ArrayList<>();
        for(final com.robrua.orianna.type.dto.status.Shard shard : shrds) {
            shards.add(new Shard(shard));
        }

        return Collections.unmodifiableList(shards);
    }
}
