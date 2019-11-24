package com.merakianalytics.orianna.types.core.status;

import com.merakianalytics.orianna.types.core.OriannaObject;

public class Translation extends OriannaObject<com.merakianalytics.orianna.types.data.status.Translation> {
    private static final long serialVersionUID = 1239359235508212254L;

    public Translation(final com.merakianalytics.orianna.types.data.status.Translation coreData) {
        super(coreData);
    }

    public String getContent() {
        return coreData.getContent();
    }

    public String getHeading() {
        return coreData.getHeading();
    }

    public String getLocale() {
        return coreData.getLocale();
    }
}
