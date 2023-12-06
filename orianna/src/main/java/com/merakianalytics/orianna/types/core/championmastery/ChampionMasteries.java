package com.merakianalytics.orianna.types.core.championmastery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
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
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class ChampionMasteries extends GhostObject.ListProxy<ChampionMastery, com.merakianalytics.orianna.types.data.championmastery.ChampionMastery, com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries> {
    public static class Builder {
        public class SubBuilder {
            private final Iterable<Champion> champions;
            private boolean streaming = false;

            private SubBuilder(final Iterable<Champion> champions) {
                this.champions = champions;
            }

            public SearchableList<ChampionMastery> get() {
                final List<Integer> ids = new ArrayList<>();
                for(final Champion champion : champions) {
                    ids.add(champion.getId());
                }

                final ImmutableMap.Builder<String, Object> builder =
                    ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("puuid", summoner.getPuuid()).put("championIds", ids);

                final CloseableIterator<ChampionMastery> result =
                    Orianna.getSettings().getPipeline().getMany(ChampionMastery.class, builder.build(), streaming);
                return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
            }

            public SubBuilder streaming() {
                streaming = true;
                return this;
            }
        }

        private final Summoner summoner;

        private Builder(final Summoner summoner) {
            this.summoner = summoner;
        }

        public ChampionMasteries get() {
            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("puuid", summoner.getPuuid());

            return Orianna.getSettings().getPipeline().get(ChampionMasteries.class, builder.build());
        }

        public SubBuilder withChampions(final Champion... champions) {
            return new SubBuilder(Arrays.asList(champions));
        }

        public SubBuilder withChampions(final Iterable<Champion> champions) {
            return new SubBuilder(champions);
        }
    }

    public static class ManyBuilder {
        private boolean streaming = false;
        private final Iterable<Summoner> summoners;

        private ManyBuilder(final Iterable<Summoner> summoners) {
            this.summoners = summoners;
        }

        public SearchableList<ChampionMasteries> get() {
            final List<String> ids = new ArrayList<>();
            final Iterator<Summoner> iterator = summoners.iterator();
            Summoner summoner = iterator.next();

            if(summoner == null) {
                return SearchableLists.from(Collections.<ChampionMasteries> emptyList());
            }

            final Platform platform = summoner.getPlatform();
            ids.add(summoner.getPuuid());
            while(iterator.hasNext()) {
                summoner = iterator.next();

                if(platform != summoner.getPlatform()) {
                    throw new IllegalArgumentException("All summoners must be from the same platform/region!");
                }
                ids.add(summoner.getPuuid());
            }

            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", platform).put("puuids", ids);

            final CloseableIterator<ChampionMasteries> result =
                Orianna.getSettings().getPipeline().getMany(ChampionMasteries.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public ManyBuilder streaming() {
            streaming = true;
            return this;
        }
    }

    private static final long serialVersionUID = -3488497876893210608L;

    public static Builder forSummoner(final Summoner summoner) {
        return new Builder(summoner);
    }

    public static ManyBuilder forSummoners(final Iterable<Summoner> summoners) {
        return new ManyBuilder(summoners);
    }

    public static ManyBuilder forSummoners(final Summoner... summoners) {
        return new ManyBuilder(Arrays.asList(summoners));
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

    public ChampionMasteries(final com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries coreData) {
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

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    @Searchable({Summoner.class, String.class, long.class})
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
                if(coreData.getPuuid() != null) {
                    builder.put("puuid", coreData.getPuuid());
                }
                final com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries data =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries.class, builder.build());
                if(data != null) {
                    coreData = data;
                }
                loadListProxyData(new Function<com.merakianalytics.orianna.types.data.championmastery.ChampionMastery, ChampionMastery>() {
                    @Override
                    public ChampionMastery apply(final com.merakianalytics.orianna.types.data.championmastery.ChampionMastery data) {
                        final ChampionMastery mastery = new ChampionMastery(data);
                        mastery.markAsGhostLoaded(ChampionMastery.CHAMPION_MASTERY_LOAD_GROUP);
                        return mastery;
                    }
                });
                break;
            default:
                break;
        }
    }
}
