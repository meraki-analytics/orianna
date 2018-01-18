package com.merakianalytics.orianna.types.core.match;

import com.merakianalytics.orianna.types.common.Lane;
import com.merakianalytics.orianna.types.common.Role;
import com.merakianalytics.orianna.types.common.RunePath;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public abstract class Participant extends OriannaObject<com.merakianalytics.orianna.types.data.match.Participant> {
    private static final long serialVersionUID = 5673949825555239078L;

    public Participant(final com.merakianalytics.orianna.types.data.match.Participant coreData) {
        super(coreData);
    }

    @Searchable({Champion.class, String.class, int.class})
    public abstract Champion getChampion();

    public abstract Tier getHighestTierInSeason();

    @Searchable({Item.class, String.class, int.class})
    public abstract SearchableList<Item> getItems();

    public abstract Lane getLane();

    public abstract Summoner getPreTransferSummoner();

    public abstract RunePath getPrimaryRunePath();

    public abstract ProfileIcon getProfileIcon();

    public abstract Role getRole();

    @Searchable({int.class})
    public abstract SearchableList<RuneStats> getRuneStats();

    public abstract RunePath getSecondaryRunePath();

    public abstract ParticipantStats getStats();

    @Searchable({Summoner.class, String.class, long.class})
    public abstract Summoner getSummoner();

    public abstract SummonerSpell getSummonerSpellD();

    public abstract SummonerSpell getSummonerSpellF();

    public abstract Team getTeam();

    public abstract ParticipantTimeline getTimeline();
}
