package com.merakianalytics.orianna.types.core.spectator;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.GameType;
import com.merakianalytics.orianna.types.common.Map;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class FeaturedGame extends OriannaObject<com.merakianalytics.orianna.types.data.spectator.FeaturedGame> {
    private static final long serialVersionUID = 1986854843022789219L;

    private final Supplier<SearchableList<Champion>> blueTeamBans = Suppliers.memoize(new Supplier<SearchableList<Champion>>() {
        @Override
        public SearchableList<Champion> get() {
            return SearchableLists.unmodifiableFrom(Champions.withIds(coreData.getBlueTeamBans()).withPlatform(Platform.withTag(coreData.getPlatform())).get());
        }
    });

    private final Supplier<SearchableList<Participant>> players = Suppliers.memoize(new Supplier<SearchableList<Participant>>() {
        @Override
        public SearchableList<Participant> get() {
            final List<Participant> players = new ArrayList<>(coreData.getPlayers().size());
            for(final com.merakianalytics.orianna.types.data.spectator.Participant player : coreData.getPlayers()) {
                players.add(new Participant(player));
            }
            return SearchableLists.unmodifiableFrom(players);
        }
    });

    private final Supplier<SearchableList<Champion>> redTeamBans = Suppliers.memoize(new Supplier<SearchableList<Champion>>() {
        @Override
        public SearchableList<Champion> get() {
            return SearchableLists.unmodifiableFrom(Champions.withIds(coreData.getRedTeamBans()).withPlatform(Platform.withTag(coreData.getPlatform())).get());
        }
    });

    public FeaturedGame(final com.merakianalytics.orianna.types.data.spectator.FeaturedGame coreData) {
        super(coreData);
    }

    @Searchable({Champion.class, String.class, int.class})
    public SearchableList<Champion> blueTeamBans() {
        return blueTeamBans.get();
    }

    public DateTime getCreationTime() {
        return coreData.getCreationTime();
    }

    public Duration getDuration() {
        return coreData.getDuration();
    }

    public long getId() {
        return coreData.getId();
    }

    public Map getMap() {
        return Map.withId(coreData.getMap());
    }

    public GameMode getMode() {
        return GameMode.valueOf(coreData.getMode());
    }

    public String getObserverEncryptionKey() {
        return coreData.getObserverEncryptionKey();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    @Searchable({Summoner.class, Champion.class, String.class, long.class, int.class})
    public SearchableList<Participant> getPlayers() {
        return players.get();
    }

    public Queue getQueue() {
        return Queue.withId(coreData.getQueue());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public GameType getType() {
        return GameType.valueOf(coreData.getType());
    }

    @Searchable({Champion.class, String.class, int.class})
    public SearchableList<Champion> redTeamBans() {
        return redTeamBans.get();
    }
}
