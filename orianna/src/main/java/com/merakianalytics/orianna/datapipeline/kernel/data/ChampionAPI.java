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
import com.merakianalytics.orianna.types.data.champion.ChampionRotation;

public class ChampionAPI extends KernelService {
    public ChampionAPI(final Configuration config, final HTTPClient client) {
        super(config, client);
    }

    @Get(ChampionRotation.class)
    public ChampionRotation getChampionRotation(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String endpoint = "lol/platform/v3/champion-rotations";
        final ChampionRotation data = get(ChampionRotation.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        return data;
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionRotation.class)
    public CloseableIterator<ChampionRotation> getManyChampionRotation(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<ChampionRotation>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionRotation next() {
                final Platform platform = iterator.next();

                final String endpoint = "lol/platform/v3/champion-rotations";
                final ChampionRotation data = get(ChampionRotation.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
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
