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
import com.merakianalytics.orianna.type.common.Platform;
import com.merakianalytics.orianna.type.dto.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.type.dto.championmastery.ChampionMastery;
import com.merakianalytics.orianna.type.dto.championmastery.ChampionMasteryScore;

public class ChampionMasteryAPI extends RiotAPI.Service {
    public ChampionMasteryAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client) {
        super(key, applicationRateLimiters, client);
    }

    @Get(ChampionMastery.class)
    public ChampionMastery getChampionMastery(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final long summonerId = ((Number)query.get("summonerId")).longValue();
        final long championId = ((Number)query.get("championId")).longValue();

        final String endpoint = "lol/champion-mastery/v3/champion-masteries/by-summoner/" + summonerId + "/by-champion/" + championId;
        final ChampionMastery data = get(ChampionMastery.class, endpoint, platform,
                                         "lol/champion-mastery/v3/champion-masteries/by-summoner/summonerId/by-champion/championId");

        data.setPlatform(platform.getTag());
        return data;
    }

    @Get(ChampionMasteries.class)
    public ChampionMasteries getChampionMasteryList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final long summonerId = ((Number)query.get("summonerId")).longValue();

        final String endpoint = "lol/champion-mastery/v3/champion-masteries/by-summoner/" + summonerId;
        final ChampionMasteries data = get(ChampionMasteries.class, endpoint, platform,
                                           "lol/champion-mastery/v3/champion-masteries/by-summoner/summonerId");

        data.setSummonerId(summonerId);
        data.setPlatform(platform.getTag());
        for(final ChampionMastery mastery : data) {
            mastery.setPlatform(platform.getTag());
        }
        return data;
    }

    @Get(ChampionMasteryScore.class)
    public ChampionMasteryScore getChampionMasteryScore(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final long summonerId = ((Number)query.get("summonerId")).longValue();

        final String endpoint = "lol/champion-mastery/v3/scores/by-summoner/" + summonerId;
        final ChampionMasteryScore data = get(ChampionMasteryScore.class, endpoint, platform, "lol/champion-mastery/v3/scores/by-summoner/summonerId");

        data.setPlatform(platform.getTag());
        data.setSummonerId(summonerId);
        return data;
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionMastery.class)
    public CloseableIterator<ChampionMastery> getManyChampionMastery(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final long summonerId = ((Number)query.get("summonerId")).longValue();
        final Iterable<Number> championIds = (Iterable<Number>)query.get("championIds");

        final Iterator<Number> iterator = championIds.iterator();
        return CloseableIterators.from(new Iterator<ChampionMastery>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionMastery next() {
                final long championId = iterator.next().longValue();

                final String endpoint = "lol/champion-mastery/v3/champion-masteries/by-summoner/" + summonerId + "/by-champion/" + championId;
                final ChampionMastery data = get(ChampionMastery.class, endpoint, platform,
                                                 "lol/champion-mastery/v3/champion-masteries/by-summoner/summonerId/by-champion/championId");

                data.setPlatform(platform.getTag());
                return data;
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionMasteries.class)
    public CloseableIterator<ChampionMasteries> getManyChampionMasteryList(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> summonerIds = (Iterable<Number>)query.get("summonerIds");

        final Iterator<Number> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<ChampionMasteries>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionMasteries next() {
                final long summonerId = iterator.next().longValue();

                final String endpoint = "lol/champion-mastery/v3/champion-masteries/by-summoner/" + summonerId;
                final ChampionMasteries data = get(ChampionMasteries.class, endpoint, platform,
                                                   "lol/champion-mastery/v3/champion-masteries/by-summoner/summonerId");

                data.setSummonerId(summonerId);
                data.setPlatform(platform.getTag());
                for(final ChampionMastery mastery : data) {
                    mastery.setPlatform(platform.getTag());
                }
                return data;
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionMasteryScore.class)
    public CloseableIterator<ChampionMasteryScore> getManyChampionMasteryScore(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> summonerIds = (Iterable<Number>)query.get("summonerIds");

        final Iterator<Number> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<ChampionMasteryScore>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionMasteryScore next() {
                final long summonerId = iterator.next().longValue();

                final String endpoint = "lol/champion-mastery/v3/scores/by-summoner/" + summonerId;
                final ChampionMasteryScore data = get(ChampionMasteryScore.class, endpoint, platform, "lol/champion-mastery/v3/scores/by-summoner/summonerId");

                data.setPlatform(platform.getTag());
                data.setSummonerId(summonerId);
                return data;
            }
        });
    }
}
