package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class MasteryTreeItem extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.MasteryTreeItem> {
    private static final long serialVersionUID = 5712211737463684969L;

    private final Supplier<Mastery> mastery = Suppliers.memoize(new Supplier<Mastery>() {
        @Override
        public Mastery get() {
            if(coreData.getId() == 0) {
                return null;
            }
            return Mastery.withId(coreData.getId()).withPlatform(Platform.withTag(coreData.getPlatform())).withVersion(coreData.getVersion())
                .withLocale(coreData.getLocale()).get();
        }
    });

    private final Supplier<Mastery> prerequisite = Suppliers.memoize(new Supplier<Mastery>() {
        @Override
        public Mastery get() {
            if(coreData.getPrerequisiteId() == 0) {
                return null;
            }
            return Mastery.withId(coreData.getPrerequisiteId()).withPlatform(Platform.withTag(coreData.getPlatform())).withVersion(coreData.getVersion())
                .withLocale(coreData.getLocale()).get();
        }
    });

    public MasteryTreeItem(final com.merakianalytics.orianna.types.data.staticdata.MasteryTreeItem coreData) {
        super(coreData);
    }

    public Mastery getMastery() {
        return mastery.get();
    }

    public Mastery getPrerequisite() {
        return prerequisite.get();
    }
}
