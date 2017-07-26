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
import com.merakianalytics.orianna.type.dto.spectator.CurrentGameInfo;
import com.merakianalytics.orianna.type.dto.spectator.FeaturedGames;

public class SpectatorAPI extends RiotAPI.Service {
    public SpectatorAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client) {
        super(key, applicationRateLimiters, client);
    }

    public SpectatorAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client,
                        final FailedRequestStrategy timeoutStrategy, final FailedRequestStrategy http404Strategy, final FailedRequestStrategy http429Strategy,
                        final FailedRequestStrategy http500Strategy, final FailedRequestStrategy http503Strategy) {
        super(key, applicationRateLimiters, client, timeoutStrategy, http404Strategy, http429Strategy, http500Strategy, http503Strategy);
    }

    @Get(CurrentGameInfo.class)
    public CurrentGameInfo getCurrentGameInfo(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number summonerId = (Number)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final String endpoint = "lol/spectator/v3/active-games/by-summoner/" + summonerId;
        final CurrentGameInfo data = get(CurrentGameInfo.class, endpoint, platform, "lol/spectator/v3/active-games/by-summoner/summonerId");

        data.setSummonerId(summonerId.longValue());
        return data;
    }

    @Get(FeaturedGames.class)
    public FeaturedGames getFeaturedGames(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String endpoint = "lol/spectator/v3/featured-games";
        final FeaturedGames data = get(FeaturedGames.class, endpoint, platform, "lol/spectator/v3/featured-games");

        data.setPlatform(platform.getTag());
        return data;
    }

    @SuppressWarnings("unchecked")
    @GetMany(CurrentGameInfo.class)
    public CloseableIterator<CurrentGameInfo> getManyCurrentGameInfo(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> summonerIds = (Iterable<Number>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<Number> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<CurrentGameInfo>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public CurrentGameInfo next() {
                final Number summonerId = iterator.next();

                final String endpoint = "lol/spectator/v3/active-games/by-summoner/" + summonerId;
                final CurrentGameInfo data = get(CurrentGameInfo.class, endpoint, platform, "lol/spectator/v3/active-games/by-summoner/summonerId");

                data.setSummonerId(summonerId.longValue());
                return data;
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(FeaturedGames.class)
    public CloseableIterator<FeaturedGames> getManyFeaturedGames(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<FeaturedGames>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public FeaturedGames next() {
                final Platform platform = iterator.next();

                final String endpoint = "lol/spectator/v3/featured-games";
                final FeaturedGames data = get(FeaturedGames.class, endpoint, platform, "lol/spectator/v3/featured-games");

                data.setPlatform(platform.getTag());
                return data;
            }
        });
    }
}
