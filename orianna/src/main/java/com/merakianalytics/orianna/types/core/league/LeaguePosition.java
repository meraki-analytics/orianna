package com.merakianalytics.orianna.types.core.league;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Division;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class LeaguePosition extends OriannaObject<com.merakianalytics.orianna.types.data.league.LeaguePosition> {
    private static final long serialVersionUID = -2669084579712906539L;

    private final Supplier<Series> promos = Suppliers.memoize(new Supplier<Series>() {
        @Override
        public Series get() {
            return new Series(coreData.getPromos());
        }
    });

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            final Summoner summoner = Summoner.withId(coreData.getSummonerId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            if(summoner.getCoreData().getName() == null) {
                summoner.getCoreData().setName(coreData.getSummonerName());
            }
            return summoner;
        }
    });

    public LeaguePosition(final com.merakianalytics.orianna.types.data.league.LeaguePosition coreData) {
        super(coreData);
    }

    public Division getDivision() {
        return Division.valueOf(coreData.getDivision());
    }

    public League getLeague() {
        return League.withId(coreData.getLeagueId()).get();
    }

    @Searchable({String.class})
    public String getLeagueId() {
        return coreData.getLeagueId();
    }

    public int getLeaguePoints() {
        return coreData.getLeaguePoints();
    }

    public int getLosses() {
        return coreData.getLosses();
    }

    @Searchable({String.class})
    public String getName() {
        return coreData.getName();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Series getPromos() {
        return promos.get();
    }

    public Queue getQueue() {
        return Queue.valueOf(coreData.getQueue());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    @Searchable({Summoner.class, String.class, long.class})
    public Summoner getSummoner() {
        return summoner.get();
    }

    public Tier getTier() {
        return Tier.valueOf(coreData.getTier());
    }

    public int getWins() {
        return coreData.getWins();
    }

    public boolean isFreshBlood() {
        return coreData.isFreshBlood();
    }

    public boolean isInactive() {
        return coreData.isInactive();
    }

    public boolean isOnHotStreak() {
        return coreData.isOnHotStreak();
    }

    public boolean isVeteran() {
        return coreData.isVeteran();
    }
}
