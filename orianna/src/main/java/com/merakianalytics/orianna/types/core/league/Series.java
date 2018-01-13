package com.merakianalytics.orianna.types.core.league;

import com.merakianalytics.orianna.types.core.OriannaObject;

public class Series extends OriannaObject<com.merakianalytics.orianna.types.data.league.Series> {
    private static final long serialVersionUID = -883134054114085873L;

    public Series(final com.merakianalytics.orianna.types.data.league.Series coreData) {
        super(coreData);
    }

    public int getLosses() {
        return coreData.getLosses();
    }

    public String getProgess() {
        return coreData.getProgress();
    }

    public int getTarget() {
        return coreData.getTarget();
    }

    public int getWins() {
        return coreData.getWins();
    }
}
