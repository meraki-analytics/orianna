package com.merakianalytics.orianna.datapipeline.kernel;

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
import com.merakianalytics.orianna.datapipeline.kernel.Kernel.Configuration;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.dto.champion.ChampionInfo;

public class ChampionAPI extends KernelService {
    public ChampionAPI(final Configuration config, final HTTPClient client) {
        super(config, client);
    }

    @Get(ChampionInfo.class)
    public ChampionInfo getChampionInfo(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String endpoint = "lol/platform/v3/champion-rotations";
        final ChampionInfo data = get(ChampionInfo.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
        if(data == null) {
            return null;
        }

        data.setPlatform(platform.getTag());
        return data;
    }

    @SuppressWarnings("unchecked")
    @GetMany(ChampionInfo.class)
    public CloseableIterator<ChampionInfo> getManyChampionInfo(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<ChampionInfo>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ChampionInfo next() {
                final Platform platform = iterator.next();

                final String endpoint = "lol/platform/v3/champion-rotations";
                final ChampionInfo data = get(ChampionInfo.class, endpoint, ImmutableMap.of("platform", platform.getTag()));
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
}
