package com.merakianalytics.orianna.datapipeline.riotapi;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.dto.account.Account;

import java.util.Iterator;
import java.util.Map;

public class AccountAPI extends RiotAPIService {
    public AccountAPI(final RiotAPI.Configuration config, final HTTPClient client, final Map<Platform, RateLimiter> applicationRateLimiters,
            final Map<Platform, Object> applicationRateLimiterLocks) {
        super(config, client, applicationRateLimiters, applicationRateLimiterLocks);
    }

    @SuppressWarnings("unchecked")
    @GetMany(Account.class)
    public CloseableIterator<Account> getManyAccount(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<String> puuids = (Iterable<String>)query.get("puuids");
        final Iterable<Map.Entry<String, String>> riotIds = (Iterable<Map.Entry<String, String>>)query.get("riotIds");
        Utilities.checkAtLeastOneNotNull(puuids, "puuids", riotIds, "riotIds");

        final Iterator<String> puuidsIterator;
        final Iterator<Map.Entry<String, String>> riotIdsIterator;
        final String baseEndpoint;
        final String limiter;

        if (puuids != null) {
            puuidsIterator = puuids.iterator();
            riotIdsIterator = null;
            baseEndpoint = "riot/account/v1/accounts/by-puuid/";
            limiter = "riot/account/v1/accounts/by-puuid/puuid";
        }
        else if (riotIds != null) {
            puuidsIterator = null;
            riotIdsIterator = riotIds.iterator();
            baseEndpoint = "/riot/account/v1/accounts/by-riot-id/";
            limiter = "/riot/account/v1/accounts/by-riot-id";
        }
        else {
            return null;
        }

        return CloseableIterators.from(new Iterator<Account>() {
            @Override
            public boolean hasNext() {
                if (puuidsIterator != null) {
                    return puuidsIterator.hasNext();
                }
                return riotIdsIterator.hasNext();
            }

            @Override
            public Account next() {
                if (puuidsIterator != null) {
                    final String identifier = puuidsIterator.next();
                    final String endpoint = baseEndpoint + identifier;
                    final Account data = get(Account.class, endpoint, platform, limiter, true);

                    if (data == null) {
                        return null;
                    }

                    data.setPlatform(platform.getTag());
                    return data;
                }
                else {
                    final Map.Entry<String, String> identifier = riotIdsIterator.next();
                    final String endpoint = baseEndpoint + identifier.getKey() + "/" + identifier.getValue();
                    final Account data = get(Account.class, endpoint, platform, limiter, true);
                    if (data == null) {
                        return null;
                    }
                    data.setPlatform(platform.getTag());
                    return data;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Get(Account.class)
    public Account getAccount(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String puuid = (String)query.get("puuid");
        final String gameName = (String)query.get("gameName");
        final String tagLine = (String)query.get("tagLine");
        Utilities.checkAtLeastOneNotNull(puuid, "puuid", gameName, "gameName", tagLine, "tagLine");

        String endpoint;
        String limiter;
        if (puuid != null) {
            endpoint = "riot/account/v1/accounts/by-puuid/" + puuid;
            limiter = "riot/account/v1/accounts/by-puuid/puuid";
        }
        else if (gameName != null && tagLine != null) {
            endpoint = "riot/account/v1/accounts/by-riot-id/" + gameName + "/" + tagLine;
            limiter = "riot/account/v1/accounts/by-riot-id/gameName/tagLine";
        }
        else {
            return null;
        }

        final Account data = get(Account.class, endpoint, platform, limiter, true);
        if (data == null) {
            return null;
        }

        data.setPlatform(platform.getTag());
        return data;
    }
}
