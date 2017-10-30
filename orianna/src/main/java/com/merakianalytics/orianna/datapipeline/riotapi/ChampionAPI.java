package com.merakianalytics.orianna.datapipeline.riotapi;

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
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI.Configuration;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.dto.champion.Champion;
import com.merakianalytics.orianna.types.dto.champion.ChampionList;

public class ChampionAPI extends RiotAPIService {
    public ChampionAPI(final Configuration config, final HTTPClient client, final Map<Platform, RateLimiter> applicationRateLimiters,
        final Map<Platform, Object> applicationRateLimiterLocks) {
        super(config, client, applicationRateLimiters, applicationRateLimiterLocks);
    }

    @Get(Champion.class)
    public Champion getChampion(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number id = (Number)query.get("id");
        Utilities.checkNotNull(platform, "platform", id, "id");

        final String endpoint = "lol/platform/v3/champions/" + id;
        final Champion data = get(Champion.class, endpoint, platform, "lol/platform/v3/champions/id");

        data.setPlatform(platform.getTag());
        return data;
    }

    @Get(ChampionList.class)
    public ChampionList getChampionList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Boolean freeToPlay = query.get("freeToPlay") == null ? Boolean.FALSE : (Boolean)query.get("freeToPlay");
        Utilities.checkNotNull(platform, "platform");

        final String endpoint = "lol/platform/v3/champions";
        final ChampionList data = get(ChampionList.class, endpoint, platform, ImmutableMap.of("freeToPlay", freeToPlay.toString()),
            "lol/platform/v3/champions");

        data.setPlatform(platform.getTag());
        data.setFreeToPlay(freeToPlay);
        return data;
    }

    @SuppressWarnings("unchecked")
    @GetMany(Champion.class)
    public CloseableIterator<Champion> getManyChampion(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> ids = (Iterable<Number>)query.get("ids");
        Utilities.checkNotNull(platform, "platform", ids, "ids");

        final Iterator<Number> iterator = ids.iterator();
        return CloseableIterators.from(new Iterator<Champion>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Champion next() {
                final Number id = iterator.next();

                final String endpoint = "lol/platform/v3/champions/" + id;
                final Champion data = get(Champion.class, endpoint, platform, "lol/platform/v3/champions/id");

                data.setPlatform(platform.getTag());
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionList.class)
    public CloseableIterator<ChampionList> getManyChampionList(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        final Boolean freeToPlay = query.get("freeToPlay") == null ? Boolean.FALSE : (Boolean)query.get("freeToPlay");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<ChampionList>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionList next() {
                final Platform platform = iterator.next();

                final String endpoint = "lol/platform/v3/champions";
                final ChampionList data = get(ChampionList.class, endpoint, platform, ImmutableMap.of("freeToPlay", freeToPlay.toString()),
                    "lol/platform/v3/champions");

                data.setPlatform(platform.getTag());
                data.setFreeToPlay(freeToPlay);
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }
}
