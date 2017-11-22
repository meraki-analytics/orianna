package com.merakianalytics.orianna.types.core.summoner;

import org.joda.time.DateTime;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class Summoner extends GhostObject<com.merakianalytics.orianna.types.data.summoner.Summoner> {
    public static class Builder {
        private Long id, accountId;
        private String name;
        private Platform platform;

        private Builder(final long id, final boolean isSummonerId) {
            if(isSummonerId) {
                this.id = id;
            } else {
                accountId = id;
            }
        }

        private Builder(final String name) {
            this.name = name;
        }

        public Summoner get() {
            if(name == null && id == null && accountId == null) {
                throw new IllegalStateException("Must set an ID, account ID, or name for the Summoner!");
            }

            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);

            if(id != null) {
                builder.put("id", id);
            } else if(accountId != null) {
                builder.put("accountId", accountId);
            } else {
                builder.put("name", name);
            }

            return Orianna.getSettings().getPipeline().get(Summoner.class, builder.build());
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

    private static final long serialVersionUID = 4280855397190856618L;
    public static final String SUMMONER_LOAD_GROUP = "summoner";

    public static Builder named(final String name) {
        return new Builder(name);
    }

    public static Builder withAccountId(final long accountId) {
        return new Builder(accountId, false);
    }

    public static Builder withId(final long id) {
        return new Builder(id, true);
    }

    public Summoner(final com.merakianalytics.orianna.types.data.summoner.Summoner coreData) {
        super(coreData, 1);
    }

    @Searchable(long.class)
    public long getAccountId() {
        if(coreData.getAccountId() == 0L) {
            load(SUMMONER_LOAD_GROUP);
        }
        return coreData.getAccountId();
    }

    @Searchable(long.class)
    public long getId() {
        if(coreData.getId() == 0L) {
            load(SUMMONER_LOAD_GROUP);
        }
        return coreData.getId();
    }

    public int getLevel() {
        load(SUMMONER_LOAD_GROUP);
        return coreData.getLevel();
    }

    @Searchable(String.class)
    public String getName() {
        if(coreData.getName() == null) {
            load(SUMMONER_LOAD_GROUP);
        }
        return coreData.getName();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public int getProfileIcon() {
        load(SUMMONER_LOAD_GROUP);
        // TODO: Make this profileIcon instead
        return coreData.getProfileIconId();
    }

    public DateTime getUpdate() {
        load(SUMMONER_LOAD_GROUP);
        return coreData.getUpdated();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case SUMMONER_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getId() != 0) {
                    builder.put("id", coreData.getId());
                }
                if(coreData.getAccountId() != 0) {
                    builder.put("accountId", coreData.getAccountId());
                }
                if(coreData.getName() != null) {
                    builder.put("name", coreData.getName());
                }
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.summoner.Summoner.class, builder.build());
                break;
            default:
                break;
        }
    }
}
