package com.robrua.orianna.api.dto;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;

import com.google.gson.reflect.TypeToken;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.dto.status.Shard;
import com.robrua.orianna.type.dto.status.ShardStatus;
import com.robrua.orianna.type.exception.OriannaException;

public abstract class StatusAPI {
    /**
     * @param region
     *            the region's shard to get
     * @return the shard
     * @see <a href="https://developer.riotgames.com/api/methods#!/908/3142">
     *      Riot API Specification</a>
     */
    public static ShardStatus getShard(final Region region) {
        try {
            final URI uri = new URIBuilder().setScheme("http").setHost("status.leagueoflegends.com").setPath("/shards/" + region.toString().toLowerCase())
                    .build();
            return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(uri, true), ShardStatus.class);
        }
        catch(final URISyntaxException e) {
            throw new OriannaException("Generated http request wasn't valid! Report this to the Orianna team.");
        }
    }

    /**
     * @return the list of all shards
     * @see <a href="https://developer.riotgames.com/api/methods#!/908/3143">
     *      Riot API Specification</a>
     */
    public static List<Shard> getShards() {
        try {
            final URI uri = new URIBuilder().setScheme("http").setHost("status.leagueoflegends.com").setPath("/shards").build();
            final Type type = new TypeToken<List<Shard>>() {}.getType();
            return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(uri, true), type);
        }
        catch(final URISyntaxException e) {
            throw new OriannaException("Generated http request wasn't valid! Report this to the Orianna team.");
        }
    }
}
