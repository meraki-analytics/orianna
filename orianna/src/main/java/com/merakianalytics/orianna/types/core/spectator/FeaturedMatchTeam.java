package com.merakianalytics.orianna.types.core.spectator;

import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.staticdata.Champion;

public abstract class FeaturedMatchTeam extends OriannaObject<com.merakianalytics.orianna.types.data.spectator.Team> {
    private static final long serialVersionUID = 5123436228430917207L;

    public FeaturedMatchTeam(final com.merakianalytics.orianna.types.data.spectator.Team coreData) {
        super(coreData);
    }

    public abstract SearchableList<Champion> getBans();

    public abstract SearchableList<Participant> getParticipants();

    public abstract Side getSide();
}
