package com.merakianalytics.orianna.types.core.match;

import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public abstract class Team extends OriannaObject<com.merakianalytics.orianna.types.data.match.Team> {
    private static final long serialVersionUID = -5161738749707752541L;

    public Team(final com.merakianalytics.orianna.types.data.match.Team coreData) {
        super(coreData);
    }

    @Searchable({Champion.class, String.class, int.class})
    public abstract SearchableList<Champion> getBans();

    public abstract int getBaronKills();

    public abstract int getDominionScore();

    public abstract int getDragonKills();

    public abstract int getInhibitorKills();

    @Searchable({Summoner.class, Champion.class, Item.class, SummonerSpell.class, String.class, long.class, int.class})
    public abstract SearchableList<Participant> getParticipants();

    public abstract int getRiftHeraldKills();

    public abstract Side getSide();

    public abstract int getTowerKills();

    public abstract int getVilemawKills();

    public abstract boolean isFirstBaronKiller();

    public abstract boolean isFirstBloodKiller();

    public abstract boolean isFirstDragonKiller();

    public abstract boolean isFirstInhibitorKiller();

    public abstract boolean isFirstRiftHeraldKiller();

    public abstract boolean isFirstTowerKiller();

    public abstract boolean isWinner();
}
