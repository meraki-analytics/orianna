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
import com.merakianalytics.orianna.types.data.thirdpartycode.VerificationString;

public class ThirdPartyCodeAPI extends KernelService {
    public ThirdPartyCodeAPI(final Configuration config, final HTTPClient client) {
        super(config, client);
    }

    @SuppressWarnings("unchecked")
    @GetMany(VerificationString.class)
    public CloseableIterator<VerificationString> getManyVerificationString(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> summonerIds = (Iterable<String>)query.get("summonerIds");
        Utilities.checkNotNull(platform, "platform", summonerIds, "summonerIds");

        final Iterator<String> iterator = summonerIds.iterator();
        return CloseableIterators.from(new Iterator<VerificationString>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public VerificationString next() {
                final String summonerId = iterator.next();

                final String endpoint = "lol/platform/v4/third-party-code/by-summoner/" + summonerId;
                final VerificationString data = get(VerificationString.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
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

    @Get(VerificationString.class)
    public VerificationString getVerificationString(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final String summonerId = (String)query.get("summonerId");
        Utilities.checkNotNull(platform, "platform", summonerId, "summonerId");

        final String endpoint = "lol/platform/v4/third-party-code/by-summoner/" + summonerId;
        final VerificationString data = get(VerificationString.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        return data;
    }
}
