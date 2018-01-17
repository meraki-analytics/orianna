package com.merakianalytics.orianna.types.core.match;

import com.merakianalytics.orianna.types.core.OriannaObject;

public class Position extends OriannaObject<com.merakianalytics.orianna.types.data.match.Position> {
    private static final long serialVersionUID = -5247967831201155642L;

    public Position(final com.merakianalytics.orianna.types.data.match.Position coreData) {
        super(coreData);
    }

    public int getX() {
        return coreData.getX();
    }

    public int getY() {
        return coreData.getY();
    }
}
