package com.merakianalytics.orianna.types.core.spectator;

import com.merakianalytics.orianna.types.common.RunePath;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class Runes extends OriannaObject<com.merakianalytics.orianna.types.data.spectator.Runes> {
    private static final long serialVersionUID = 3692909600109196352L;

    public Runes(final com.merakianalytics.orianna.types.data.spectator.Runes coreData) {
        super(coreData);
    }

    public RunePath getPrimaryPath() {
        return RunePath.withId(coreData.getPrimaryPath());
    }

    public RunePath getSecondaryPath() {
        return RunePath.withId(coreData.getSecondaryPath());
    }
}
