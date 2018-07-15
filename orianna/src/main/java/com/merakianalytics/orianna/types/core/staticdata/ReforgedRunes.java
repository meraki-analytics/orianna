package com.merakianalytics.orianna.types.core.staticdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

public class ReforgedRunes extends GhostObject.ListProxy<ReforgedRune, com.merakianalytics.orianna.types.data.staticdata.ReforgedRune, com.merakianalytics.orianna.types.data.staticdata.ReforgedRunes> {
    public static class Builder {
        private Platform platform;
        private String version, locale;

        private Builder() {}

        public ReforgedRunes get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            if(version == null) {
                version = Orianna.getSettings().getCurrentVersion(platform);
            }

            if(locale == null) {
                locale = Orianna.getSettings().getDefaultLocale();
                locale = locale == null ? platform.getDefaultLocale() : locale;
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version)
                .put("locale", locale);

            return Orianna.getSettings().getPipeline().get(ReforgedRunes.class, builder.build());
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

        public SearchableList<com.merakianalytics.orianna.types.core.staticdata.ReforgedRune> get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            if(version == null) {
                version = Orianna.getSettings().getCurrentVersion(platform);
            }

            if(locale == null) {
                locale = Orianna.getSettings().getDefaultLocale();
                locale = locale == null ? platform.getDefaultLocale() : locale;
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("version", version)
                .put("locale", locale);

            if(ids != null) {
                builder.put("ids", ids);
            } else if(names != null) {
                builder.put("names", names);
            } else {
                builder.put("keys", keys);
            }

            final CloseableIterator<com.merakianalytics.orianna.types.core.staticdata.ReforgedRune> result =
                Orianna.getSettings().getPipeline().getMany(com.merakianalytics.orianna.types.core.staticdata.ReforgedRune.class, builder.build(), streaming);
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

    private static final long serialVersionUID = -766565807713688277L;

    public static ReforgedRunes get() {
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

    private final Supplier<ReforgedRuneTree> tree = Suppliers.memoize(new Supplier<ReforgedRuneTree>() {
        @Override
        public ReforgedRuneTree get() {
            load(LIST_PROXY_LOAD_GROUP);
            if(coreData.getTree() == null) {
                return null;
            }
            return new ReforgedRuneTree(coreData.getTree(), ReforgedRunes.this);
        }
    });

    public ReforgedRunes(final com.merakianalytics.orianna.types.data.staticdata.ReforgedRunes coreData) {
        super(coreData, 1);
    }

    @Override
    public boolean exists() {
        if(coreData.isEmpty()) {
            load(LIST_PROXY_LOAD_GROUP);
        }
        return !coreData.isEmpty();
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

    public ReforgedRuneTree getTree() {
        return tree.get();
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
                final com.merakianalytics.orianna.types.data.staticdata.ReforgedRunes data =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.staticdata.ReforgedRunes.class, builder.build());
                if(data != null) {
                    coreData = data;
                }
                loadListProxyData(new Function<com.merakianalytics.orianna.types.data.staticdata.ReforgedRune, ReforgedRune>() {
                    @Override
                    public ReforgedRune apply(final com.merakianalytics.orianna.types.data.staticdata.ReforgedRune data) {
                        final ReforgedRune rune = new ReforgedRune(data);
                        rune.markAsGhostLoaded(ReforgedRune.REFORGED_RUNE_LOAD_GROUP);
                        return rune;
                    }
                });
                break;
            default:
                break;
        }
    }
}
