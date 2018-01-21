package com.merakianalytics.orianna.types.core.spectator;

import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.staticdata.Champion;

public abstract class CurrentMatchTeam extends OriannaObject<com.merakianalytics.orianna.types.data.spectator.Team> {
    private static final long serialVersionUID = 3485303289560691869L;

    public CurrentMatchTeam(final com.merakianalytics.orianna.types.data.spectator.Team coreData) {
        super(coreData);
    }

    public abstract SearchableList<Champion> getBans();

    public abstract SearchableList<Player> getParticipants();

    public abstract Side getSide();
}
