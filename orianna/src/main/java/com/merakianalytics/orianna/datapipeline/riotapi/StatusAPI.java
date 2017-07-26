package com.merakianalytics.orianna.datapipeline.riotapi;

import java.util.Iterator;
import java.util.Map;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.RateLimiter;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI.FailedRequestStrategies.FailedRequestStrategy;
import com.merakianalytics.orianna.type.common.Platform;
import com.merakianalytics.orianna.type.dto.status.ShardStatus;

public class StatusAPI extends RiotAPI.Service {
    public StatusAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client) {
        super(key, applicationRateLimiters, client);
    }

    public StatusAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client,
                     final FailedRequestStrategy timeoutStrategy, final FailedRequestStrategy http404Strategy, final FailedRequestStrategy http429Strategy,
                     final FailedRequestStrategy http500Strategy, final FailedRequestStrategy http503Strategy) {
        super(key, applicationRateLimiters, client, timeoutStrategy, http404Strategy, http429Strategy, http500Strategy, http503Strategy);
    }

    @SuppressWarnings("unchecked")
    @GetMany(ShardStatus.class)
    public CloseableIterator<ShardStatus> getManyShardStatus(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<ShardStatus>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ShardStatus next() {
                final Platform platform = iterator.next();

                final String endpoint = "lol/status/v3/shard-data";
                final ShardStatus data = get(ShardStatus.class, endpoint, platform, "lol/status/v3/shard-data");

                return data;
            }
        });
    }

    @Get(ShardStatus.class)
    public ShardStatus getShardStatus(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String endpoint = "lol/status/v3/shard-data";
        final ShardStatus data = get(ShardStatus.class, endpoint, platform, "lol/status/v3/shard-data");

        return data;
    }
}
