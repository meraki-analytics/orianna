package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Function;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class MasteryTreeTier extends OriannaObject.ListProxy<MasteryTreeItem, com.merakianalytics.orianna.types.data.staticdata.MasteryTreeItem, com.merakianalytics.orianna.types.data.staticdata.MasteryTreeTier> {
    private static final long serialVersionUID = 2705400673641683393L;

    public MasteryTreeTier(final com.merakianalytics.orianna.types.data.staticdata.MasteryTreeTier coreData) {
        super(coreData, new Function<com.merakianalytics.orianna.types.data.staticdata.MasteryTreeItem, MasteryTreeItem>() {
            @Override
            public MasteryTreeItem apply(final com.merakianalytics.orianna.types.data.staticdata.MasteryTreeItem item) {
                return new MasteryTreeItem(item);
            }
        });
    }
}
