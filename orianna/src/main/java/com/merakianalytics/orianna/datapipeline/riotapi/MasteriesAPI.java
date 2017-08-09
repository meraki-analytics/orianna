package com.merakianalytics.orianna.datapipeline.riotapi;

import java.util.Iterator;
import java.util.Map;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.dto.masteries.MasteryPages;

public class MasteriesAPI extends RiotAPI.Service {
    public MasteriesAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final RateLimiter.Type limitingType,
                        final double limitingShare, final HTTPClient client, final Configuration config) {
        super(key, applicationRateLimiters, limitingType, limitingShare, client, config);
    }

    @SuppressWarnings("unchecked")
    @GetMany(MasteryPages.class)
    public CloseableIterator<MasteryPages> getManyMasteryPages(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> summonerIds = (Iterable<Number>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<Number> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<MasteryPages>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MasteryPages next() {
                final Number summonerId = iterator.next();

                final String endpoint = "lol/platform/v3/masteries/by-summoner/" + summonerId;
                final MasteryPages data = get(MasteryPages.class, endpoint, platform, "lol/platform/v3/masteries/by-summoner/summonerId");

                data.setPlatform(platform.getTag());
                return data;
            }
        });
    }

    @Get(MasteryPages.class)
    public MasteryPages getMasteryPages(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number summonerId = (Number)query.get("summonerId");
        Utilities.checkNotNull(summonerId, "summonerId");

        final String endpoint = "lol/platform/v3/masteries/by-summoner/" + summonerId;
        final MasteryPages data = get(MasteryPages.class, endpoint, platform, "lol/platform/v3/masteries/by-summoner/summonerId");

        data.setPlatform(platform.getTag());
        return data;
    }
}
