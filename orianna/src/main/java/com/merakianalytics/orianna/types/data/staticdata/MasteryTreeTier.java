package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class MasteryTreeTier extends CoreData.ListProxy<MasteryTreeItem> {
    private static final long serialVersionUID = 589645418400098055L;

    public MasteryTreeTier() {
        super();
    }

    public MasteryTreeTier(final int initialCapacity) {
        super(initialCapacity);
    }
}
