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
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.dto.summoner.Summoner;

public class SummonerAPI extends RiotAPI.Service {
    public SummonerAPI(final String key, final Map<Platform, RateLimiter> applicationRateLimiters,
                       final Collection<RateLimiter.Configuration> applicationLimiterConfigs, final HTTPClient client, final Configuration config) {
        super(key, applicationRateLimiters, applicationLimiterConfigs, client, config);
    }

    @SuppressWarnings("unchecked")
    @GetMany(Summoner.class)
    public CloseableIterator<Summoner> getManySummoner(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<Number> summonerIds = (Iterable<Number>)query.get("summonerIds");
        final Iterable<Number> accountIds = (Iterable<Number>)query.get("accountIds");
        final Iterable<String> summonerNames = (Iterable<String>)query.get("summonerNames");
        Utilities.checkAtLeastOneNotNull(summonerIds, "summonerIds", accountIds, "accountIds", summonerNames, "summonerNames");

        final Iterator<?> iterator;
        final String baseEndpoint;
        final String limiter;
        if(summonerIds != null) {
            iterator = summonerIds.iterator();
            baseEndpoint = "lol/summoner/v3/summoners/";
            limiter = "lol/summoner/v3/summoners/summonerId";
        } else if(accountIds != null) {
            iterator = accountIds.iterator();
            baseEndpoint = "lol/summoner/v3/summoners/by-account/";
            limiter = "lol/summoner/v3/summoners/by-account/accountId";
        } else if(summonerNames != null) {
            iterator = summonerNames.iterator();
            baseEndpoint = "lol/summoner/v3/summoners/by-name/";
            limiter = "lol/summoner/v3/summoners/by-name/summonerName";
        } else {
            return null;
        }

        return CloseableIterators.from(new Iterator<Summoner>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Summoner next() {
                final Object identifier = iterator.next();

                final String endpoint = baseEndpoint + identifier;
                final Summoner data = get(Summoner.class, endpoint, platform, limiter);

                data.setPlatform(platform.getTag());
                return data;
            }
        });
    }

    @Get(Summoner.class)
    public Summoner getSummoner(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Number summonerId = (Number)query.get("summonerId");
        final Number accountId = (Number)query.get("accountId");
        final String summonerName = (String)query.get("summonerName");

        String endpoint;
        String limiter;
        if(summonerId != null) {
            endpoint = "lol/summoner/v3/summoners/" + summonerId;
            limiter = "lol/summoner/v3/summoners/summonerId";
        } else if(accountId != null) {
            endpoint = "lol/summoner/v3/summoners/by-account/" + accountId;
            limiter = "lol/summoner/v3/summoners/by-account/accountId";
        } else if(summonerName != null) {
            endpoint = "lol/summoner/v3/summoners/by-name/" + summonerName;
            limiter = "lol/summoner/v3/summoners/by-name/summonerName";
        } else {
            return null;
        }

        final Summoner data = get(Summoner.class, endpoint, platform, limiter);

        data.setPlatform(platform.getTag());
        return data;
    }
}
