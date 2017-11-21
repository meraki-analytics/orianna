package com.merakianalytics.orianna.types.core.summoner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;

public abstract class Summoners {
    public static class Builder {
        private Iterable<Long> ids, accountIds;
        private Iterable<String> names;
        private Platform platform;
        private boolean streaming = false;

        private Builder(final Iterable<Long> ids, final boolean areSummonerIds) {
            if(areSummonerIds) {
                this.ids = ids;
            } else {
                accountIds = ids;
            }
        }

        private Builder(final Iterable<String> names) {
            this.names = names;
        }

        public List<Summoner> get() {
            if(names == null && ids == null && accountIds == null) {
                throw new IllegalStateException("Must IDs, account IDs, or names for the Summoners!");
            }

            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);

            if(ids != null) {
                builder.put("ids", ids);
            } else if(accountIds != null) {
                builder.put("accountIds", accountIds);
            } else {
                builder.put("names", names);
            }

            final CloseableIterator<Summoner> result = Orianna.getSettings().getPipeline().getMany(Summoner.class, builder.build(), streaming);
            return streaming ? CloseableIterators.toLazyList(result) : CloseableIterators.toList(result);
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

    public static Builder named(final Iterable<String> names) {
        return new Builder(names);
    }

    public static Builder named(final String... names) {
        return new Builder(Arrays.asList(names));
    }

    public static Builder withAccountIds(final Iterable<Long> accountIds) {
        return new Builder(accountIds, false);
    }

    public static Builder withAccountIds(final long... accountIds) {
        final List<Long> list = new ArrayList<>(accountIds.length);
        for(final long id : accountIds) {
            list.add(id);
        }
        return new Builder(list, false);
    }

    public static Builder withIds(final Iterable<Long> ids) {
        return new Builder(ids, true);
    }

    public static Builder withIds(final long... ids) {
        final List<Long> list = new ArrayList<>(ids.length);
        for(final long id : ids) {
            list.add(id);
        }
        return new Builder(list, true);
    }
}
