package com.merakianalytics.orianna.types.core.staticdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

public class Maps extends GhostObject.ListProxy<Map, com.merakianalytics.orianna.types.data.staticdata.Map, com.merakianalytics.orianna.types.data.staticdata.Maps> {
    public static class Builder {
        private Platform platform;
        private String version, locale;

        private Builder() {}

        public Maps get() {
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

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version)
                .put("locale", locale);

            return Orianna.getSettings().getPipeline().get(Maps.class, builder.build());
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

    public static class SubsetBuilder {
        private Iterable<Integer> ids;
        private Iterable<String> names;
        private Platform platform;
        private boolean streaming = false;
        private String version, locale;

        @SuppressWarnings("unchecked")
        private SubsetBuilder(final Iterable<?> ids, final boolean areIds) {
            if(areIds) {
                this.ids = (Iterable<Integer>)ids;
            } else {
                names = (Iterable<String>)ids;
            }
        }

        public SearchableList<Map> get() {
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

            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version).put("locale", locale);

            if(ids != null) {
                builder.put("ids", ids);
            } else {
                builder.put("names", names);
            }

            final CloseableIterator<Map> result = Orianna.getSettings().getPipeline().getMany(Map.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public SubsetBuilder streaming() {
            streaming = true;
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

    private static final long serialVersionUID = -7076727260509732625L;

    public static Maps get() {
        return new Builder().get();
    }

    public static SubsetBuilder named(final Iterable<String> names) {
        return new SubsetBuilder(names, false);
    }

    public static SubsetBuilder named(final String... names) {
        return new SubsetBuilder(Arrays.asList(names), false);
    }

    public static SubsetBuilder withIds(final int... ids) {
        final List<Integer> list = new ArrayList<>(ids.length);
        for(final int id : ids) {
            list.add(id);
        }
        return new SubsetBuilder(list, true);
    }

    public static SubsetBuilder withIds(final Iterable<Integer> ids) {
        return new SubsetBuilder(ids, true);
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

    public Maps(final com.merakianalytics.orianna.types.data.staticdata.Maps coreData) {
        super(coreData, 1);
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            LIST_PROXY_LOAD_GROUP
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
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.Maps.class, builder.build());
                loadListProxyData(new Function<com.merakianalytics.orianna.types.data.staticdata.Map, Map>() {
                    @Override
                    public Map apply(final com.merakianalytics.orianna.types.data.staticdata.Map data) {
                        final Map map = new Map(data);
                        map.markAsGhostLoaded(Map.MAP_LOAD_GROUP);
                        return map;
                    }
                });
                break;
            default:
                break;
        }
    }
}
