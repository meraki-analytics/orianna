package com.merakianalytics.orianna.types.core.championmastery;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class ChampionMastery extends GhostObject<com.merakianalytics.orianna.types.data.championmastery.ChampionMastery> implements Comparable<ChampionMastery> {
    public static class Builder {
        public class SubBuilder {
            private final Champion champion;

            private SubBuilder(final Champion champion) {
                this.champion = champion;
            }

            public ChampionMastery get() {
                final ImmutableMap.Builder<String, Object> builder =
                    ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("puuid", summoner.getPuuid()).put("championId",
                        champion.getId());

                return Orianna.getSettings().getPipeline().get(ChampionMastery.class, builder.build());
            }
        }

        private final Summoner summoner;

        private Builder(final Summoner summoner) {
            this.summoner = summoner;
        }

        public SubBuilder withChampion(final Champion champion) {
            return new SubBuilder(champion);
        }
    }

    public static final String CHAMPION_MASTERY_LOAD_GROUP = "champion-mastery";
    private static final long serialVersionUID = -4377419492958529379L;

    public static Builder forSummoner(final Summoner summoner) {
        return new Builder(summoner);
    }

    private final Supplier<Champion> champion = Suppliers.memoize(new Supplier<Champion>() {
        @Override
        public Champion get() {
            if(coreData.getChampionId() == 0) {
                return null;
            }
            return Champion.withId(coreData.getChampionId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            if(coreData.getPuuid() == null) {
                return null;
            }
            return Summoner.withPuuid(coreData.getPuuid()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public ChampionMastery(final com.merakianalytics.orianna.types.data.championmastery.ChampionMastery coreData) {
        super(coreData, 1);
    }

    @Override
    public int compareTo(final ChampionMastery o) {
        final int result = Integer.compare(getLevel(), o.getLevel());
        if(result != 0) {
            return result;
        }
        return Integer.compare(getPoints(), o.getPoints());
    }

    @Override
    public boolean exists() {
        if(coreData.getPoints() == 0) {
            load(CHAMPION_MASTERY_LOAD_GROUP);
        }
        return coreData.getPoints() != 0;
    }

    @Searchable({Champion.class, String.class, int.class})
    public Champion getChampion() {
        return champion.get();
    }

    public DateTime getLastPlayed() {
        if(coreData.getLastPlayed() == null) {
            load(CHAMPION_MASTERY_LOAD_GROUP);
        }
        return coreData.getLastPlayed();
    }

    public int getLevel() {
        if(coreData.getLevel() == 0) {
            load(CHAMPION_MASTERY_LOAD_GROUP);
        }
        return coreData.getLevel();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            CHAMPION_MASTERY_LOAD_GROUP
        });
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public int getPoints() {
        if(coreData.getPoints() == 0) {
            load(CHAMPION_MASTERY_LOAD_GROUP);
        }
        return coreData.getPoints();
    }

    public int getPointsSinceLastLevel() {
        if(coreData.getPointsSinceLastLevel() == 0) {
            load(CHAMPION_MASTERY_LOAD_GROUP);
        }
        return coreData.getPointsSinceLastLevel();
    }

    public int getPointsUntilNextLevel() {
        if(coreData.getPointsUntilNextLevel() == 0) {
            load(CHAMPION_MASTERY_LOAD_GROUP);
        }
        return coreData.getPointsUntilNextLevel();
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    @Searchable({Summoner.class, String.class, long.class})
    public Summoner getSummoner() {
        return summoner.get();
    }

    public int getTokens() {
        if(coreData.getTokens() == 0) {
            load(CHAMPION_MASTERY_LOAD_GROUP);
        }
        return coreData.getTokens();
    }

    public boolean isChestGranted() {
        if(coreData.isChestGranted()) {
            load(CHAMPION_MASTERY_LOAD_GROUP);
        }
        return coreData.isChestGranted();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case CHAMPION_MASTERY_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPuuid() != null) {
                    builder.put("puuid", coreData.getPuuid());
                }
                if(coreData.getChampionId() != 0) {
                    builder.put("championId", coreData.getChampionId());
                }
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                final com.merakianalytics.orianna.types.data.championmastery.ChampionMastery data =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.championmastery.ChampionMastery.class, builder.build());
                if(data != null) {
                    coreData = data;
                }
                break;
            default:
                break;
        }
    }
}
