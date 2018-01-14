package com.merakianalytics.orianna.types.core.league;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class League extends GhostObject.ListProxy<LeagueEntry, com.merakianalytics.orianna.types.data.league.LeagueEntry, com.merakianalytics.orianna.types.data.league.League> {
    public static class Builder {
        private final String id;
        private Platform platform;

        private Builder(final String id) {
            this.id = id;
        }

        public League get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("leagueId", id);

            return Orianna.getSettings().getPipeline().get(League.class, builder.build());
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

    public static class SelectBuilder {
        public class SubBuilder {
            private Platform platform;
            private final Queue queue;

            private SubBuilder(final Queue queue) {
                this.queue = queue;
            }

            public League get() {
                if(platform == null) {
                    platform = Orianna.getSettings().getDefaultPlatform();
                }

                final ImmutableMap.Builder<String, Object> builder =
                    ImmutableMap.<String, Object> builder().put("platform", platform).put("tier", tier).put("queue", queue);

                return Orianna.getSettings().getPipeline().get(League.class, builder.build());
            }

            public SubBuilder withPlatform(final Platform platform) {
                this.platform = platform;
                return this;
            }

            public SubBuilder withRegion(final Region region) {
                platform = region.getPlatform();
                return this;
            }
        }

        private final Tier tier;

        private SelectBuilder(final Tier tier) {
            this.tier = tier;
        }

        public SubBuilder inQueue(final Queue queue) {
            if(!Queue.RANKED.contains(queue)) {
                final StringBuilder sb = new StringBuilder();
                for(final Queue q : Queue.RANKED) {
                    sb.append(", " + q);
                }
                throw new IllegalArgumentException("Queue must be one of [" + sb.substring(2) + "]!");
            }
            return new SubBuilder(queue);
        }
    }

    private static final long serialVersionUID = -4287829961173669465L;

    public static SelectBuilder.SubBuilder challengerInQueue(final Queue queue) {
        return new SelectBuilder(Tier.CHALLENGER).inQueue(queue);
    }

    public static SelectBuilder.SubBuilder masterInQueue(final Queue queue) {
        return new SelectBuilder(Tier.MASTER).inQueue(queue);
    }

    public static Builder withId(final String id) {
        return new Builder(id);
    }

    public League(final com.merakianalytics.orianna.types.data.league.League coreData) {
        super(coreData, 1, new Function<com.merakianalytics.orianna.types.data.league.LeagueEntry, LeagueEntry>() {
            @Override
            public LeagueEntry apply(final com.merakianalytics.orianna.types.data.league.LeagueEntry data) {
                return new LeagueEntry(data);
            }
        });
    }

    @Searchable({String.class})
    public String getId() {
        if(coreData.getId() == null) {
            load(LIST_PROXY_LOAD_GROUP);
        }
        return coreData.getId();
    }

    @Searchable({String.class})
    public String getName() {
        load(LIST_PROXY_LOAD_GROUP);
        return coreData.getName();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Queue getQueue() {
        if(coreData.getQueue() == null) {
            load(LIST_PROXY_LOAD_GROUP);
        }
        return Queue.valueOf(coreData.getQueue());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public Tier getTier() {
        if(coreData.getTier() == null) {
            load(LIST_PROXY_LOAD_GROUP);
        }
        return Tier.valueOf(coreData.getTier());
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
                if(coreData.getId() != null) {
                    builder.put("leagueId", coreData.getId());
                }
                if(coreData.getTier() != null) {
                    builder.put("tier", Tier.valueOf(coreData.getTier()));
                }
                if(coreData.getQueue() != null) {
                    builder.put("queue", Queue.valueOf(coreData.getQueue()));
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.league.League.class, builder.build());
                loadListProxyData();
                break;
            default:
                break;
        }
    }
}
