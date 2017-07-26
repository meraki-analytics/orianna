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
import com.merakianalytics.orianna.type.dto.runes.RunePages;

public class RunesAPI extends RiotAPI.Service {
    public RunesAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client) {
        super(key, applicationRateLimiters, client);
    }

    public RunesAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client,
                    final FailedRequestStrategy timeoutStrategy, final FailedRequestStrategy http404Strategy, final FailedRequestStrategy http429Strategy,
                    final FailedRequestStrategy http500Strategy, final FailedRequestStrategy http503Strategy) {
        super(key, applicationRateLimiters, client, timeoutStrategy, http404Strategy, http429Strategy, http500Strategy, http503Strategy);
    }

    @SuppressWarnings("unchecked")
    @GetMany(RunePages.class)
    public CloseableIterator<RunePages> getManyRunePages(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> summonerIds = (Iterable<Number>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<Number> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<RunePages>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public RunePages next() {
                final Number summonerId = iterator.next();

                final String endpoint = "lol/platform/v3/runes/by-summoner/" + summonerId;
                final RunePages data = get(RunePages.class, endpoint, platform, "lol/platform/v3/runes/by-summoner/summonerId");

                data.setPlatform(platform.getTag());
                return data;
            }
        });
    }

    @Get(RunePages.class)
    public RunePages getRunePages(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number summonerId = (Number)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final String endpoint = "lol/platform/v3/runes/by-summoner/" + summonerId;
        final RunePages data = get(RunePages.class, endpoint, platform, "lol/platform/v3/runes/by-summoner/summonerId");

        data.setPlatform(platform.getTag());
        return data;
    }
}
