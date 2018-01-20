package com.merakianalytics.orianna.types.core.staticdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion.ChampionData;
import com.merakianalytics.orianna.types.data.champion.ChampionStatus;
import com.merakianalytics.orianna.types.data.champion.ChampionStatuses;

public class Champions extends GhostObject.ListProxy<com.merakianalytics.orianna.types.core.staticdata.Champion, com.merakianalytics.orianna.types.data.staticdata.Champion, com.merakianalytics.orianna.types.data.staticdata.Champions> {
    public static class Builder {
        private Set<String> includedData;
        private Platform platform;
        private String version, locale;

        private Builder() {}

        public Champions get() {
            if(version == null) {
                version = Orianna.getSettings().getCurrentVersion();
            }

            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            if(locale == null) {
                locale = Orianna.getSettings().getDefaultLocale();
                locale = locale == null ? platform.getDefaultLocale() : locale;
            }

            if(includedData == null) {
                includedData = ImmutableSet.of("all");
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version)
                .put("locale", locale).put("includedData", includedData);

            return Orianna.getSettings().getPipeline().get(Champions.class, builder.build());
        }

        public Builder withIncludedData(final Iterable<String> includedData) {
            this.includedData = Sets.newHashSet(includedData);
            return this;
        }

        public Builder withIncludedData(final String... includedData) {
            this.includedData = Sets.newHashSet(includedData);
            return this;
        }

        public Builder withLocale(final String locale) {
            this.locale = locale;
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

        public Builder withVersion(final String version) {
            this.version = version;
            return this;
        }
    }

    private class Champion extends com.merakianalytics.orianna.types.core.staticdata.Champion {
        private static final long serialVersionUID = -8875727517478281658L;

        public Champion(final ChampionData coreData) {
            super(coreData);
        }

        @Override
        public boolean isEnabled() {
            Champions.this.load(STATUS_LOAD_GROUP);
            return super.isEnabled();
        }

        @Override
        public boolean isEnabledInCoopVsAI() {
            Champions.this.load(STATUS_LOAD_GROUP);
            return super.isEnabled();
        }

        @Override
        public boolean isEnabledInCustoms() {
            Champions.this.load(STATUS_LOAD_GROUP);
            return super.isEnabled();
        }

        @Override
        public boolean isEnabledInRanked() {
            Champions.this.load(STATUS_LOAD_GROUP);
            return super.isEnabled();
        }

        @Override
        public boolean isFreeToPlay() {
            Champions.this.load(STATUS_LOAD_GROUP);
            return super.isEnabled();
        }
    }

    public static class SubsetBuilder {
        private Iterable<Integer> ids;
        private Set<String> includedData;
        private Iterable<String> names, keys;
        private Platform platform;
        private boolean streaming = false;
        private String version, locale;

        private SubsetBuilder(final Iterable<Integer> ids) {
            this.ids = ids;
        }

        private SubsetBuilder(final Iterable<String> keys, final boolean areNames) {
            if(areNames) {
                names = keys;
            } else {
                this.keys = keys;
            }
        }

        public SearchableList<com.merakianalytics.orianna.types.core.staticdata.Champion> get() {
            if(version == null) {
                version = Orianna.getSettings().getCurrentVersion();
            }

            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            if(locale == null) {
                locale = Orianna.getSettings().getDefaultLocale();
                locale = locale == null ? platform.getDefaultLocale() : locale;
            }

            if(includedData == null) {
                includedData = ImmutableSet.of("all");
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version)
                .put("locale", locale).put("includedData", includedData);

            if(ids != null) {
                builder.put("ids", ids);
            } else if(names != null) {
                builder.put("names", names);
            } else {
                builder.put("keys", keys);
            }

            final CloseableIterator<com.merakianalytics.orianna.types.core.staticdata.Champion> result =
                Orianna.getSettings().getPipeline().getMany(com.merakianalytics.orianna.types.core.staticdata.Champion.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public SubsetBuilder streaming() {
            streaming = true;
            return this;
        }

        public SubsetBuilder withIncludedData(final Iterable<String> includedData) {
            this.includedData = Sets.newHashSet(includedData);
            return this;
        }

        public SubsetBuilder withIncludedData(final String... includedData) {
            this.includedData = Sets.newHashSet(includedData);
            return this;
        }

        public SubsetBuilder withLocale(final String locale) {
            this.locale = locale;
            return this;
        }

        public SubsetBuilder withPlatform(final Platform platform) {
            this.platform = platform;
            return this;
        }

        public SubsetBuilder withRegion(final Region region) {
            platform = region.getPlatform();
            return this;
        }

        public SubsetBuilder withVersion(final String version) {
            this.version = version;
            return this;
        }
    }

    private static final long serialVersionUID = -5852031149115607129L;

    public static Champions get() {
        return new Builder().get();
    }

    public static SubsetBuilder named(final Iterable<String> names) {
        return new SubsetBuilder(names, true);
    }

    public static SubsetBuilder named(final String... names) {
        return new SubsetBuilder(Arrays.asList(names), true);
    }

    public static SubsetBuilder withIds(final int... ids) {
        final List<Integer> list = new ArrayList<>(ids.length);
        for(final int id : ids) {
            list.add(id);
        }
        return new SubsetBuilder(list);
    }

    public static SubsetBuilder withIds(final Iterable<Integer> ids) {
        return new SubsetBuilder(ids);
    }

    public static Builder withIncludedData(final Set<String> includedData) {
        return new Builder().withIncludedData(includedData);
    }

    public static SubsetBuilder withKeys(final Iterable<String> keys) {
        return new SubsetBuilder(keys, false);
    }

    public static SubsetBuilder withKeys(final String... keys) {
        return new SubsetBuilder(Arrays.asList(keys), false);
    }

    public static Builder withLocale(final String locale) {
        return new Builder().withLocale(locale);
    }

    public static Builder withPlatform(final Platform platform) {
        return new Builder().withPlatform(platform);
    }

    public static Builder withRegion(final Region region) {
        return new Builder().withRegion(region);
    }

    public static Builder withVersion(final String version) {
        return new Builder().withVersion(version);
    }

    private final Supplier<Set<String>> includedData = Suppliers.memoize(new Supplier<Set<String>>() {
        @Override
        public Set<String> get() {
            return Collections.unmodifiableSet(coreData.getIncludedData());
        }
    });

    public Champions(final com.merakianalytics.orianna.types.data.staticdata.Champions coreData) {
        super(coreData, 1);
    }

    public String getFormat() {
        load(LIST_PROXY_LOAD_GROUP);
        return coreData.getFormat();
    }

    public Set<String> getIncludedData() {
        return includedData.get();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            LIST_PROXY_LOAD_GROUP,
            com.merakianalytics.orianna.types.core.staticdata.Champion.STATUS_LOAD_GROUP
        });
    }

    public String getLocale() {
        return coreData.getLocale();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public String getType() {
        load(LIST_PROXY_LOAD_GROUP);
        return coreData.getType();
    }

    public String getVersion() {
        return coreData.getVersion();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case LIST_PROXY_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                if(coreData.getVersion() != null) {
                    builder.put("version", coreData.getVersion());
                }
                if(coreData.getLocale() != null) {
                    builder.put("locale", coreData.getLocale());
                }
                if(coreData.getIncludedData() != null) {
                    builder.put("includedData", coreData.getIncludedData());
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Champions.class, builder.build());

                loadListProxyData(
                    new Function<com.merakianalytics.orianna.types.data.staticdata.Champion, com.merakianalytics.orianna.types.core.staticdata.Champion>() {
                        @Override
                        public com.merakianalytics.orianna.types.core.staticdata.Champion
                            apply(final com.merakianalytics.orianna.types.data.staticdata.Champion input) {
                            final ChampionData data = new ChampionData();
                            data.setChampion(input);
                            final com.merakianalytics.orianna.types.core.staticdata.Champion champion = new Champion(data);
                            champion.markAsGhostLoaded(com.merakianalytics.orianna.types.core.staticdata.Champion.CHAMPION_LOAD_GROUP);
                            return champion;
                        }
                    });
                break;
            case com.merakianalytics.orianna.types.core.staticdata.Champion.STATUS_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                final ChampionStatuses list = Orianna.getSettings().getPipeline().get(ChampionStatuses.class, builder.build());

                final java.util.Map<Integer, ChampionStatus> statuses = new HashMap<>();
                for(final ChampionStatus status : list) {
                    statuses.put(status.getId(), status);
                }

                for(final com.merakianalytics.orianna.types.core.staticdata.Champion champion : this) {
                    champion.getCoreData().setStatus(statuses.get(champion.getId()));
                }
                break;
            default:
                break;
        }
    }
}
