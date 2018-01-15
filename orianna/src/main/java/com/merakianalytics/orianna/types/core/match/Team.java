package com.merakianalytics.orianna.types.core.match;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class Team extends OriannaObject<com.merakianalytics.orianna.types.data.match.Team> {
    private static final long serialVersionUID = -5787154563875265507L;

    private final Supplier<SearchableList<Champion>> bans = Suppliers.memoize(new Supplier<SearchableList<Champion>>() {
        @Override
        public SearchableList<Champion> get() {
            return SearchableLists.unmodifiableFrom(
                Champions.withIds(coreData.getBans()).withPlatform(Platform.withTag(coreData.getPlatform())).withVersion(coreData.getVersion()).get());
        }
    });

    private Match match;

    private final Supplier<SearchableList<Participant>> participants = Suppliers.memoize(new Supplier<SearchableList<Participant>>() {
        @Override
        public SearchableList<Participant> get() {
            final List<Participant> participants = new ArrayList<>(match.getParticipants().size() / 2);
            for(final Participant participant : match.getParticipants()) {
                if(participant.getCoreData().getTeam() == coreData.getTeamId()) {
                    participants.add(participant);
                }
            }
            match = null;
            return SearchableLists.unmodifiableFrom(participants);
        }
    });

    public Team(final com.merakianalytics.orianna.types.data.match.Team coreData, final Match match) {
        super(coreData);
        this.match = match;
    }

    @Searchable({Champion.class, String.class, int.class})
    public SearchableList<Champion> getBans() {
        return bans.get();
    }

    public int getBaronKills() {
        return coreData.getBaronKills();
    }

    public int getDominionScore() {
        return coreData.getDominionScore();
    }

    public int getDragonKills() {
        return coreData.getDragonKills();
    }

    public int getInhibitorKills() {
        return coreData.getInhibitorKills();
    }

    @Searchable({Summoner.class, Champion.class, Item.class, String.class, long.class, int.class})
    public SearchableList<Participant> getParticipants() {
        return participants.get();
    }

    public int getRiftHeraldKills() {
        return coreData.getRiftHeraldKills();
    }

    public Side getSide() {
        return Side.withId(coreData.getTeamId());
    }

    public int getTowerKills() {
        return coreData.getTowerKills();
    }

    public int getVilemawKills() {
        return coreData.getVilemawKills();
    }

    public boolean isFirstBaronKiller() {
        return coreData.isFirstBaronKiller();
    }

    public boolean isFirstBloodKiller() {
        return coreData.isFirstBloodKiller();
    }

    public boolean isFirstDragonKiller() {
        return coreData.isFirstDragonKiller();
    }

    public boolean isFirstInhibitorKiller() {
        return coreData.isFirstInhibitorKiller();
    }

    public boolean isFirstRiftHeraldKiller() {
        return coreData.isFirstRiftHeraldKiller();
    }

    public boolean isFirstTowerKiller() {
        return coreData.isFirstTowerKiller();
    }

    public boolean isWinner() {
        return coreData.isWinner();
    }
}
