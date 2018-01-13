package com.merakianalytics.orianna.types.core.championmastery;

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
import com.merakianalytics.orianna.types.core.searchable.SearchableListWrapper;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class ChampionMasteries extends GhostObject.ListProxy<ChampionMastery, com.merakianalytics.orianna.types.data.championmastery.ChampionMastery, com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries> {
    public static class Builder {
        public class SubBuilder {
            private final Iterable<Champion> champions;
            private final boolean streaming = false;

            public SubBuilder(final Iterable<Champion> champions) {
                this.champions = champions;
            }

            public SearchableList<ChampionMastery> get() {
                final List<Integer> ids = new ArrayList<>();
                for(final Champion champion : champions) {
                    ids.add(champion.getId());
                }

                final ImmutableMap.Builder<String, Object> builder =
                    ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("summonerId", summoner.getId()).put("championIds", ids);

                final CloseableIterator<ChampionMastery> result =
                    Orianna.getSettings().getPipeline().getMany(ChampionMastery.class, builder.build(), streaming);
                return streaming ? SearchableListWrapper.of(CloseableIterators.toLazyList(result))
                    : SearchableListWrapper.of(CloseableIterators.toList(result));
            }
        }

        private final Summoner summoner;

        private Builder(final Summoner summoner) {
            this.summoner = summoner;
        }

        public ChampionMasteries get() {
            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("summonerId", summoner.getId());

            return Orianna.getSettings().getPipeline().get(ChampionMasteries.class, builder.build());
        }

        public SubBuilder withChampions(final Champion... champions) {
            return new SubBuilder(Arrays.asList(champions));
        }

        public SubBuilder withChampions(final Iterable<Champion> champions) {
            return new SubBuilder(champions);
        }
    }

    private static final long serialVersionUID = -3488497876893210608L;

    public static Builder forSummoner(final Summoner summoner) {
        return new Builder(summoner);
    }

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            return Summoner.withId(coreData.getSummonerId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public ChampionMasteries(final com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries coreData) {
        super(coreData, 1, new Function<com.merakianalytics.orianna.types.data.championmastery.ChampionMastery, ChampionMastery>() {
            @Override
            public ChampionMastery apply(final com.merakianalytics.orianna.types.data.championmastery.ChampionMastery data) {
                final ChampionMastery mastery = new ChampionMastery(data);
                mastery.markAsGhostLoaded(ChampionMastery.CHAMPION_MASTERY_LOAD_GROUP);
                return mastery;
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
                coreData =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries.class, builder.build());
                loadListProxyData();
                break;
            default:
                break;
        }
    }
}
