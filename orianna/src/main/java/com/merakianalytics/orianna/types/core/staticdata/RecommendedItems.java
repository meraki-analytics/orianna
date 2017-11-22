package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class RecommendedItems extends OriannaObject.ListProxy<ItemSet, com.merakianalytics.orianna.types.data.staticdata.ItemSet, com.merakianalytics.orianna.types.data.staticdata.RecommendedItems> {
    private static final Map<String, com.merakianalytics.orianna.types.common.Map> MAP_CONVERSIONS = ImmutableMap.of("SR",
        com.merakianalytics.orianna.types.common.Map.SUMMONERS_RIFT, "TT", com.merakianalytics.orianna.types.common.Map.TWISTED_TREELINE, "HA",
        com.merakianalytics.orianna.types.common.Map.HOWLING_ABYSS, "CS", com.merakianalytics.orianna.types.common.Map.THE_CRYSTAL_SCAR);

    private static final long serialVersionUID = 7485737031964917542L;

    public RecommendedItems(final com.merakianalytics.orianna.types.data.staticdata.RecommendedItems coreData) {
        super(coreData, new Function<com.merakianalytics.orianna.types.data.staticdata.ItemSet, ItemSet>() {
            @Override
            public ItemSet apply(final com.merakianalytics.orianna.types.data.staticdata.ItemSet set) {
                return new ItemSet(set);
            }
        });
    }

    @Searchable(com.merakianalytics.orianna.types.common.Map.class)
    public com.merakianalytics.orianna.types.common.Map getMap() {
        return MAP_CONVERSIONS.get(coreData.getMap());
    }

    @Searchable(GameMode.class)
    public GameMode getMode() {
        return GameMode.valueOf(coreData.getMode());
    }

    @Searchable(String.class)
    public String getTitle() {
        return coreData.getTitle();
    }

    public String getType() {
        return coreData.getType();
    }

    public boolean isPriority() {
        return coreData.isPriority();
    }
}
