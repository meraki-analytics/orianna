package com.merakianalytics.orianna.types.core.spectator;

import java.util.List;

import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public abstract class Player extends OriannaObject<com.merakianalytics.orianna.types.data.spectator.Player> {
    private static final long serialVersionUID = 4241892066509529815L;

    public Player(final com.merakianalytics.orianna.types.data.spectator.Player coreData) {
        super(coreData);
    }

    @Searchable({Champion.class, String.class, int.class})
    public abstract Champion getChampion();

    public abstract List<GameCustomizationObject> getCustomizationObjects();

    public abstract ProfileIcon getProfileIcon();

    public abstract Runes getRunes();

    @Searchable({Summoner.class, String.class, long.class})
    public abstract Summoner getSummoner();

    public abstract SummonerSpell getSummonerSpellD();

    public abstract SummonerSpell getSummonerSpellF();

    public abstract CurrentMatchTeam getTeam();

    public abstract boolean isBot();
}
