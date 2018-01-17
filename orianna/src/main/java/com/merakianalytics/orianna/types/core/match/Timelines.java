package com.merakianalytics.orianna.types.core.match;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

public class Timelines {
    public static class Builder {
        private final Iterable<Long> ids;
        private Platform platform;
        private boolean streaming = false;

        private Builder(final Iterable<Long> ids) {
            this.ids = ids;
        }

        public SearchableList<Timeline> get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("matchIds", ids);

            final CloseableIterator<Timeline> result = Orianna.getSettings().getPipeline().getMany(Timeline.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public Builder streaming() {
            streaming = true;
            return this;
        }

        public Builder withPlatform(final Platform platform) {
            this.platform = platform;
            return this;
        }

        public Builder withRegion(final Region region) {
            platform = region.getPlatform();
            return this;
        }
    }

    public static Builder withIds(final Iterable<Long> ids) {
        return new Builder(ids);
    }

    public static Builder withIds(final long... ids) {
        final List<Long> list = new ArrayList<>(ids.length);
        for(final long id : ids) {
            list.add(id);
        }
        return new Builder(list);
    }
}
