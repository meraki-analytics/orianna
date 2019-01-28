package com.merakianalytics.orianna.types.core.summoner;

import java.util.Arrays;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

public abstract class Summoners {
    public static class Builder {
        private enum KeyType {
                ACCOUNT_ID,
                ID,
                NAME,
                PUUID;
        }

        private Platform platform;
        private Iterable<String> puuids, accountIds, ids, names;
        private boolean streaming = false;

        private Builder(final Iterable<String> keys, final KeyType keyType) {
            switch(keyType) {
                case ACCOUNT_ID:
                    accountIds = keys;
                    break;
                case ID:
                    ids = keys;
                    break;
                case NAME:
                    names = keys;
                    break;
                case PUUID:
                    puuids = keys;
                    break;
                default:
                    break;
            }
        }

        public SearchableList<Summoner> get() {
            if(puuids == null && accountIds == null && names == null && ids == null) {
                throw new IllegalStateException("Must PUUIDs, account IDs, IDs, or names for the Summoners!");
            }

            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);

            if(puuids != null) {
                builder.put("puuids", puuids);
            } else if(accountIds != null) {
                builder.put("accountIds", accountIds);
            } else if(ids != null) {
                builder.put("ids", ids);
            } else {
                builder.put("names", names);
            }

            final CloseableIterator<Summoner> result = Orianna.getSettings().getPipeline().getMany(Summoner.class, builder.build(), streaming);
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

    public static Builder named(final Iterable<String> names) {
        return new Builder(names, Builder.KeyType.NAME);
    }

    public static Builder named(final String... names) {
        return new Builder(Arrays.asList(names), Builder.KeyType.NAME);
    }

    public static Builder withAccountIds(final Iterable<String> accountIds) {
        return new Builder(accountIds, Builder.KeyType.ACCOUNT_ID);
    }

    public static Builder withAccountIds(final String... accountIds) {
        return new Builder(Arrays.asList(accountIds), Builder.KeyType.ACCOUNT_ID);
    }

    public static Builder withIds(final Iterable<String> ids) {
        return new Builder(ids, Builder.KeyType.ID);
    }

    public static Builder withIds(final String... ids) {
        return new Builder(Arrays.asList(ids), Builder.KeyType.ID);
    }

    public static Builder withPuuids(final Iterable<String> puuids) {
        return new Builder(puuids, Builder.KeyType.PUUID);
    }

    public static Builder withPuuids(final String... puuids) {
        return new Builder(Arrays.asList(puuids), Builder.KeyType.PUUID);
    }
}
