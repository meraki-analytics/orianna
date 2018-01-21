package com.merakianalytics.orianna.types.core.spectator;

import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public abstract class Participant extends OriannaObject<com.merakianalytics.orianna.types.data.spectator.Participant> {
    private static final long serialVersionUID = -919093957040517072L;

    public Participant(final com.merakianalytics.orianna.types.data.spectator.Participant coreData) {
        super(coreData);
    }

    public abstract Champion getChampion();

    public abstract ProfileIcon getProfileIcon();

    public abstract Summoner getSummoner();

    public abstract SummonerSpell getSummonerSpellD();

    public abstract SummonerSpell getSummonerSpellF();

    public abstract FeaturedMatchTeam getTeam();

    public abstract boolean isBot();
}
