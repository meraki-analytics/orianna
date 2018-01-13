package com.merakianalytics.orianna.types.core.league;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class LeaguePositions extends GhostObject.ListProxy<LeaguePosition, com.merakianalytics.orianna.types.data.league.LeaguePosition, com.merakianalytics.orianna.types.data.league.LeaguePositions> {
    public static class Builder {
        private final Summoner summoner;

        private Builder(final Summoner summoner) {
            this.summoner = summoner;
        }

        public LeaguePositions get() {
            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("summonerId", summoner.getId());

            return Orianna.getSettings().getPipeline().get(LeaguePositions.class, builder.build());
        }
    }

    private static final long serialVersionUID = -8683905800046401766L;

    public static Builder forSummoner(final Summoner summoner) {
        return new Builder(summoner);
    }

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            return Summoner.withId(coreData.getSummonerId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public LeaguePositions(final com.merakianalytics.orianna.types.data.league.LeaguePositions coreData) {
        super(coreData, 1, new Function<com.merakianalytics.orianna.types.data.league.LeaguePosition, LeaguePosition>() {
            @Override
            public LeaguePosition apply(final com.merakianalytics.orianna.types.data.league.LeaguePosition data) {
                return new LeaguePosition(data);
            }
        });
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public Summoner getSummoner() {
        return summoner.get();
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
                if(coreData.getSummonerId() != 0L) {
                    builder.put("summonerId", coreData.getSummonerId());
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.league.LeaguePositions.class, builder.build());
                loadListProxyData();
                break;
            default:
                break;
        }
    }
}
