package com.merakianalytics.orianna.datapipeline.riotapi;

import java.util.Iterator;
import java.util.Map;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.QueryValidationException;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI.Configuration;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.dto.summoner.Summoner;

public class SummonerAPI extends RiotAPIService {
    public SummonerAPI(final Configuration config, final HTTPClient client, final Map<Platform, RateLimiter> applicationRateLimiters,
        final Map<Platform, Object> applicationRateLimiterLocks) {
        super(config, client, applicationRateLimiters, applicationRateLimiterLocks);
    }

    @SuppressWarnings("unchecked")
    @GetMany(Summoner.class)
    public CloseableIterator<Summoner> getManySummoner(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<String> puuids = (Iterable<String>)query.get("puuids");
        final Iterable<String> accountIds = (Iterable<String>)query.get("accountIds");
        final Iterable<String> summonerIds = (Iterable<String>)query.get("ids");
        final Iterable<String> summonerNames = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(puuids, "puuids", accountIds, "accountIds", summonerIds, "ids", summonerNames, "names");

        final Iterator<String> iterator;
        final String baseEndpoint;
        final String limiter;
        final boolean names;
        if(puuids != null) {
            iterator = puuids.iterator();
            baseEndpoint = "lol/summoner/v4/summoners/by-puuid/";
            limiter = "lol/summoner/v4/summoners/by-puuid/puuid";
            names = false;
        } else if(accountIds != null) {
            iterator = accountIds.iterator();
            baseEndpoint = "lol/summoner/v4/summoners/by-account/";
            limiter = "lol/summoner/v4/summoners/by-account/accountId";
            names = false;
        } else if(summonerIds != null) {
            iterator = summonerIds.iterator();
            baseEndpoint = "lol/summoner/v4/summoners/";
            limiter = "lol/summoner/v4/summoners/summonerId";
            names = false;
        } else if(summonerNames != null) {
            iterator = summonerNames.iterator();
            baseEndpoint = "lol/summoner/v4/summoners/by-name/";
            limiter = "lol/summoner/v4/summoners/by-name/summonerName";
            names = true;
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
                final String identifier = iterator.next();
                if(names) {
                    try {
                        Utilities.checkSummonerName(identifier);
                    } catch(final QueryValidationException e) {
                        return null;
                    }
                }

                final String endpoint = baseEndpoint + identifier;
                final Summoner data = get(Summoner.class, endpoint, platform, limiter);
                if(data == null) {
                    return null;
                }

                data.setPlatform(platform.getTag());
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Get(Summoner.class)
    public Summoner getSummoner(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String puuid = (String)query.get("puuid");
        final String accountId = (String)query.get("accountId");
        final String summonerId = (String)query.get("id");
        final String summonerName = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(puuid, "puuid", accountId, "accountId", summonerId, "id", summonerName, "name");

        String endpoint;
        String limiter;
        if(puuid != null) {
            endpoint = "lol/summoner/v4/summoners/by-puuid/" + puuid;
            limiter = "lol/summoner/v4/summoners/by-puuid/puuid";
        } else if(accountId != null) {
            endpoint = "lol/summoner/v4/summoners/by-account/" + accountId;
            limiter = "lol/summoner/v4/summoners/by-account/accountId";
        } else if(summonerId != null) {
            endpoint = "lol/summoner/v4/summoners/" + summonerId;
            limiter = "lol/summoner/v4/summoners/summonerId";
        } else if(summonerName != null) {
            Utilities.checkSummonerName(summonerName);
            endpoint = "lol/summoner/v4/summoners/by-name/" + summonerName;
            limiter = "lol/summoner/v4/summoners/by-name/summonerName";
        } else {
            return null;
        }

        final Summoner data = get(Summoner.class, endpoint, platform, limiter);
        if(data == null) {
            return null;
        }

        data.setPlatform(platform.getTag());
        return data;
    }
}
