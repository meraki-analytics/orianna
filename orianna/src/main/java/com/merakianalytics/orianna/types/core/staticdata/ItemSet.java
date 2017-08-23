package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class ItemSet extends OriannaObject.MapProxy<Item, Integer, Integer, Integer, com.merakianalytics.orianna.types.data.staticdata.ItemSet> {
    private static final long serialVersionUID = -8917142329487068965L;

    public ItemSet(final com.merakianalytics.orianna.types.data.staticdata.ItemSet coreData) {
        super(coreData, new Function<Integer, Item>() {
            @Override
            public Item apply(final Integer id) {
                return Item.withId(id, coreData.getPlatform(), coreData.getVersion(), coreData.getLocale());
            }
        }, Functions.<Integer> identity());
    }

    public String getType() {
        return coreData.getType();
    }

    public boolean isRecMath() {
        return coreData.isRecMath();
    }
}
