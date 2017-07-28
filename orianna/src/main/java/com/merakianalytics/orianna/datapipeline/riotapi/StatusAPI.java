package com.merakianalytics.orianna.datapipeline.riotapi;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;
import com.merakianalytics.orianna.type.common.Platform;
import com.merakianalytics.orianna.type.dto.status.ShardStatus;

public class StatusAPI extends RiotAPI.Service {
    public StatusAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters,
                     final Collection<RateLimiter.Configuration> applicationLimiterConfigs, final HTTPClient client, final Configuration config) {
        super(key, applicationRateLimiters, applicationLimiterConfigs, client, config);
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
