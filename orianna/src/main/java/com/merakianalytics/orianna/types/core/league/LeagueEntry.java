package com.merakianalytics.orianna.types.core.league;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Division;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class LeagueEntry extends OriannaObject<com.merakianalytics.orianna.types.data.league.LeagueEntry> {
    private static final long serialVersionUID = -8320702451565649681L;

    private final Supplier<Series> promos = Suppliers.memoize(new Supplier<Series>() {
        @Override
        public Series get() {
            if(coreData.getPromos() == null) {
                return null;
            }
            return new Series(coreData.getPromos());
        }
    });

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            if(coreData.getSummonerId() == null) {
                return null;
            }
            final Summoner summoner = Summoner.withId(coreData.getSummonerId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            if(summoner.getCoreData().getName() == null) {
                summoner.getCoreData().setName(coreData.getSummonerName());
            }
            return summoner;
        }
    });

    public LeagueEntry(final com.merakianalytics.orianna.types.data.league.LeagueEntry coreData) {
        super(coreData);
    }

    public Division getDivision() {
        return Division.valueOf(coreData.getDivision());
    }

    public int getLeaguePoints() {
        return coreData.getLeaguePoints();
    }

    public int getLosses() {
        return coreData.getLosses();
    }

    public Series getPromos() {
        return promos.get();
    }

    @Searchable({Summoner.class, String.class, long.class})
    public Summoner getSummoner() {
        return summoner.get();
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
