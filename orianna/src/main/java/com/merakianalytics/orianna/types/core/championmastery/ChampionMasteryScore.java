package com.merakianalytics.orianna.types.core.championmastery;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class ChampionMasteryScore extends GhostObject<com.merakianalytics.orianna.types.data.championmastery.ChampionMasteryScore> implements Comparable<ChampionMasteryScore> {
    public static class Builder {
        private final Summoner summoner;

        private Builder(final Summoner summoner) {
            this.summoner = summoner;
        }

        public ChampionMasteryScore get() {
            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("puuid", summoner.getPuuid());

            return Orianna.getSettings().getPipeline().get(ChampionMasteryScore.class, builder.build());
        }
    }

    public static final String CHAMPION_MASTERY_SCORE_LOAD_GROUP = "champion-mastery-score";
    private static final long serialVersionUID = 5794183951997021894L;

    public static Builder forSummoner(final Summoner summoner) {
        return new Builder(summoner);
    }

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            if(coreData.getPuuid() == null) {
                return null;
            }
            return Summoner.withPuuid(coreData.getPuuid()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public ChampionMasteryScore(final com.merakianalytics.orianna.types.data.championmastery.ChampionMasteryScore coreData) {
        super(coreData, 1);
    }

    @Override
    public int compareTo(final ChampionMasteryScore o) {
        return Integer.compare(getScore(), o.getScore());
    }

    @Override
    public boolean exists() {
        if(coreData.getScore() == 0) {
            load(CHAMPION_MASTERY_SCORE_LOAD_GROUP);
        }
        return coreData.getScore() != 0;
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            CHAMPION_MASTERY_SCORE_LOAD_GROUP
        });
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public int getScore() {
        if(coreData.getScore() == 0) {
            load(CHAMPION_MASTERY_SCORE_LOAD_GROUP);
        }
        return coreData.getScore();
    }

    @Searchable({Summoner.class, String.class, long.class})
    public Summoner getSummoner() {
        return summoner.get();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case CHAMPION_MASTERY_SCORE_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPuuid() != null) {
                    builder.put("puuid", coreData.getPuuid());
                }
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                final com.merakianalytics.orianna.types.data.championmastery.ChampionMasteryScore data =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.championmastery.ChampionMasteryScore.class, builder.build());
                if(data != null) {
                    coreData = data;
                }
                break;
            default:
                break;
        }
    }
}
