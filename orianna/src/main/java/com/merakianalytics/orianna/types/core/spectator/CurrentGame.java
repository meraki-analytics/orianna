package com.merakianalytics.orianna.types.core.spectator;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.GameType;
import com.merakianalytics.orianna.types.common.Map;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableListWrapper;
import com.merakianalytics.orianna.types.core.searchable.UnmodifiableSearchableList;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class CurrentGame extends GhostObject<com.merakianalytics.orianna.types.data.spectator.CurrentGame> {
    public static class Builder {
        private final Summoner summoner;

        private Builder(final Summoner summoner) {
            this.summoner = summoner;
        }

        public CurrentGame get() {
            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("summonerId", summoner.getId());

            return Orianna.getSettings().getPipeline().get(CurrentGame.class, builder.build());
        }
    }

    public static final String CURRENT_GAME_LOAD_GROUP = "current-game";
    private static final long serialVersionUID = 2151849959267002960L;

    public static CurrentGame forSummoner(final Summoner summoner) {
        return new Builder(summoner).get();
    }

    private final Supplier<SearchableList<Champion>> blueTeamBans = Suppliers.memoize(new Supplier<SearchableList<Champion>>() {
        @Override
        public SearchableList<Champion> get() {
            load(CURRENT_GAME_LOAD_GROUP);
            return UnmodifiableSearchableList.of(Champions.withIds(coreData.getBlueTeamBans()).withPlatform(Platform.withTag(coreData.getPlatform())).get());
        }
    });

    private final Supplier<SearchableList<Player>> players = Suppliers.memoize(new Supplier<SearchableList<Player>>() {
        @Override
        public SearchableList<Player> get() {
            load(CURRENT_GAME_LOAD_GROUP);
            final List<Player> players = new ArrayList<>(coreData.getPlayers().size());
            for(final com.merakianalytics.orianna.types.data.spectator.Player player : coreData.getPlayers()) {
                players.add(new Player(player));
            }
            return UnmodifiableSearchableList.of(SearchableListWrapper.of(players));
        }
    });

    private final Supplier<SearchableList<Champion>> redTeamBans = Suppliers.memoize(new Supplier<SearchableList<Champion>>() {
        @Override
        public SearchableList<Champion> get() {
            load(CURRENT_GAME_LOAD_GROUP);
            return UnmodifiableSearchableList.of(Champions.withIds(coreData.getRedTeamBans()).withPlatform(Platform.withTag(coreData.getPlatform())).get());
        }
    });

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            return Summoner.withId(coreData.getSummonerId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public CurrentGame(final com.merakianalytics.orianna.types.data.spectator.CurrentGame coreData) {
        super(coreData, 1);
    }

    @Searchable({Champion.class, String.class, int.class})
    public SearchableList<Champion> getBlueTeamBans() {
        return blueTeamBans.get();
    }

    public DateTime getCreationTime() {
        load(CURRENT_GAME_LOAD_GROUP);
        return coreData.getCreationTime();
    }

    public Duration getDuration() {
        load(CURRENT_GAME_LOAD_GROUP);
        return coreData.getDuration();
    }

    public long getId() {
        load(CURRENT_GAME_LOAD_GROUP);
        return coreData.getId();
    }

    public Map getMap() {
        load(CURRENT_GAME_LOAD_GROUP);
        return Map.withId(coreData.getMap());
    }

    public GameMode getMode() {
        load(CURRENT_GAME_LOAD_GROUP);
        return GameMode.valueOf(coreData.getMode());
    }

    public String getObserverEncryptionKey() {
        load(CURRENT_GAME_LOAD_GROUP);
        return coreData.getObserverEncryptionKey();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    @Searchable({Summoner.class, Champion.class, String.class, long.class, int.class})
    public SearchableList<Player> getPlayers() {
        return players.get();
    }

    public Queue getQueue() {
        load(CURRENT_GAME_LOAD_GROUP);
        return Queue.withId(coreData.getQueue());
    }

    @Searchable({Champion.class, String.class, int.class})
    public SearchableList<Champion> getRedTeamBans() {
        return redTeamBans.get();
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    @Searchable({Summoner.class, String.class, long.class})
    public Summoner getSummoner() {
        return summoner.get();
    }

    public GameType getType() {
        load(CURRENT_GAME_LOAD_GROUP);
        return GameType.valueOf(coreData.getType());
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case CURRENT_GAME_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                if(coreData.getSummonerId() != 0L) {
                    builder.put("summonerId", coreData.getSummonerId());
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.spectator.CurrentGame.class, builder.build());
                break;
            default:
                break;
        }
    }
}
