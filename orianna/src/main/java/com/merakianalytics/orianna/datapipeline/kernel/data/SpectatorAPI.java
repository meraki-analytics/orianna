package com.merakianalytics.orianna.datapipeline.kernel.data;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.datapipeline.kernel.data.Kernel.Configuration;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.data.spectator.FeaturedMatches;

public class SpectatorAPI extends KernelService {
    public SpectatorAPI(final Configuration config, final HTTPClient client) {
        super(config, client);
    }

    @Get(CurrentMatch.class)
    public CurrentMatch getCurrentMatch(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String summonerId = (String)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final String endpoint = "lol/spectator/v4/active-games/by-summoner/" + summonerId;
        final CurrentMatch data = get(CurrentMatch.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        return data;
    }

    @Get(FeaturedMatches.class)
    public FeaturedMatches getFeaturedMatches(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String endpoint = "lol/spectator/v4/featured-games";
        final FeaturedMatches data = get(FeaturedMatches.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        return data;
    }

    @SuppressWarnings("unchecked")
    @GetMany(CurrentMatch.class)
    public CloseableIterator<CurrentMatch> getManyCurrentMatch(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> summonerIds = (Iterable<String>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<String> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<CurrentMatch>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public CurrentMatch next() {
                final String summonerId = iterator.next();

                final String endpoint = "lol/spectator/v4/active-games/by-summoner/" + summonerId;
                final CurrentMatch data = get(CurrentMatch.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
                if(data == null) {
                    return null;
                }

                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(FeaturedMatches.class)
    public CloseableIterator<FeaturedMatches> getManyFeaturedMatches(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<FeaturedMatches>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public FeaturedMatches next() {
                final Platform platform = iterator.next();

                final String endpoint = "lol/spectator/v4/featured-games";
                final FeaturedMatches data = get(FeaturedMatches.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
                if(data == null) {
                    return null;
                }

                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }
}
