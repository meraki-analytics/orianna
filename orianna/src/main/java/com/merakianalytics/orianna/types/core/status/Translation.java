package com.merakianalytics.orianna.types.core.status;

import org.joda.time.DateTime;

import com.merakianalytics.orianna.types.core.OriannaObject;

public class Translation extends OriannaObject<com.merakianalytics.orianna.types.data.status.Translation> {
    private static final long serialVersionUID = -4749668131338577444L;

    public Translation(final com.merakianalytics.orianna.types.data.status.Translation coreData) {
        super(coreData);
    }

    public String getContent() {
        return coreData.getContent();
    }

    public String getLocale() {
        return coreData.getLocale();
    }

    public DateTime getUpdated() {
        return coreData.getUpdated();
    }
}
