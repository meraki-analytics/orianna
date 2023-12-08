package com.merakianalytics.orianna.types.core.account;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

import java.util.Arrays;
import java.util.List;

public class Account extends GhostObject<com.merakianalytics.orianna.types.data.account.Account> {
    public static class Builder {
        private enum KeyType {
            RIOT_ID,
            PUUID
        }
        private Platform platform;
        private String gameName, tagLine, puuid;

        private Builder(final String gameName, final String tagLine) {
            this.gameName = gameName;
            this.tagLine = tagLine;
        }
        private Builder(final String puuid) {
            this.puuid = puuid;
        }

        public Account get() {
            if (puuid == null && (gameName == null || tagLine == null)) {
                throw new IllegalStateException("Must set a puuid, or a game name and a tag line for the Account!");
            }

            if (platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if (platform == null) {
                    throw new IllegalStateException(
                            "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);

            if (puuid != null) {
                builder.put("puuid", puuid);
            }
            else {
                builder.put("gameName", gameName);
                builder.put("tagLine", tagLine);
            }

            return Orianna.getSettings().getPipeline().get(Account.class, builder.build());
        }

        public Account.Builder withPlatform(final Platform platform) {
            this.platform = platform;
            return this;
        }

        public Account.Builder withRegion(final Region region) {
            platform = region.getPlatform();
            return this;
        }
    }

    public static final String ACCOUNT_LOAD_GROUP = "account";

    public static Account.Builder withRiotId(final String gameName, final String tagLine) {
        return new Account.Builder(gameName, tagLine);
    }
    public static Account.Builder withPuuid(final String puuid) {
        return new Account.Builder(puuid);
    }

    public Account(final com.merakianalytics.orianna.types.data.account.Account coreData) {
        super (coreData, 1);
    }

    @Override
    public boolean exists() {
        if(coreData.getPuuid() == null) {
            load(ACCOUNT_LOAD_GROUP);
        }
        return coreData.getPuuid() != null;
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
                ACCOUNT_LOAD_GROUP
        });
    }

    @Override
    protected void loadCoreData(String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch (group) {
            case ACCOUNT_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if (coreData.getPuuid() != null) {
                    builder.put("puuid", coreData.getPuuid());
                }
                if (coreData.getGameName() != null) {
                    builder.put("gameName", coreData.getGameName());
                }
                if (coreData.getTagLine() != null) {
                    builder.put("tagLine", coreData.getTagLine());
                }
                if (coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                final com.merakianalytics.orianna.types.data.account.Account data =
                        Orianna.getSettings().getPipeline().get(
                                com.merakianalytics.orianna.types.data.account.Account.class, builder.build()
                        );

                if (data != null) {
                    coreData = data;
                }
                break;
            default:
                break;
        }
    }

    @Searchable(String.class)
    public String getPuuid() {
        if(coreData.getPuuid() == null) {
            load(ACCOUNT_LOAD_GROUP);
        }
        return coreData.getPuuid();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }
}
