package com.merakianalytics.orianna.types.core.staticdata;

import com.merakianalytics.orianna.types.core.OriannaObject;

public class ItemGroup extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.ItemGroup> {
    private static final long serialVersionUID = 2558683247172514177L;

    public ItemGroup(final com.merakianalytics.orianna.types.data.staticdata.ItemGroup coreData) {
        super(coreData);
    }

    public String getKey() {
        return coreData.getKey();
    }

    public int getMax() {
        return coreData.getMax();
    }
}
