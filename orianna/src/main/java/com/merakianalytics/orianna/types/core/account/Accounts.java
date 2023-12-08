package com.merakianalytics.orianna.types.core.account;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class Accounts {
    public static class Builder {
        private enum KeyType {
            RIOT_ID,
            PUUID
        }
        private Platform platform;
        private Iterable<String> puuids;
        private Iterable<Map.Entry<String, String>> riotIds;
        private boolean streaming = false;

        private Builder(final List<Map.Entry<String, String>> keys) {
            riotIds = new Iterable<Map.Entry<String, String>>() {
                @Override
                public Iterator<Map.Entry<String, String>> iterator() {
                    return keys.iterator();
                }
            };
        }
        private Builder(final Iterable<String> keys) {
            this.puuids = keys;
        }

        public SearchableList<Account> get() {
            if (puuids == null && riotIds == null) {
                throw new IllegalStateException("Must set a puuid, or a game name and a tag line for the Accounts!");
            }

            if (platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if (platform == null) {
                    throw new IllegalStateException(
                            "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);

            if (puuids != null) {
                builder.put("puuids", puuids);
            }
            else {
                builder.put("riotIds", riotIds);
            }

            final CloseableIterator<Account> result = Orianna.getSettings().getPipeline().getMany(Account.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));

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

    public static Builder withRiotIds(final List<Map.Entry<String, String>> riotIds) {
        return new Builder(riotIds);
    }
    public static Builder withPuuids(final String... puuids) {
        return new Builder(Arrays.asList(puuids));
    }
}
