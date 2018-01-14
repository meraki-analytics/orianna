package com.merakianalytics.orianna.types.core.spectator;

import com.merakianalytics.orianna.types.core.OriannaObject;

public class GameCustomizationObject extends OriannaObject<com.merakianalytics.orianna.types.data.spectator.GameCustomizationObject> {
    private static final long serialVersionUID = -5738452634787853940L;

    public GameCustomizationObject(final com.merakianalytics.orianna.types.data.spectator.GameCustomizationObject coreData) {
        super(coreData);
    }

    public String getCategory() {
        return coreData.getCategory();
    }

    public String getContent() {
        return coreData.getContent();
    }
}
