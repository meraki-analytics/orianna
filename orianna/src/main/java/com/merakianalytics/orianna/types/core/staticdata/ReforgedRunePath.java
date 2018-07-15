package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Function;
import com.merakianalytics.orianna.types.common.RunePath;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class ReforgedRunePath extends OriannaObject.ListProxy<ReforgedRuneSlot, com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneSlot, com.merakianalytics.orianna.types.data.staticdata.ReforgedRunePath> {
    private static final long serialVersionUID = -7986072459001642330L;

    public ReforgedRunePath(final com.merakianalytics.orianna.types.data.staticdata.ReforgedRunePath coreData,
        final java.util.Map<Integer, ReforgedRune> runes) {
        super(coreData, new Function<com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneSlot, ReforgedRuneSlot>() {
            @Override
            public ReforgedRuneSlot apply(final com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneSlot data) {
                return new ReforgedRuneSlot(data, runes);
            }
        });
    }

    public int getId() {
        return coreData.getId();
    }

    public String getImage() {
        return coreData.getImage();
    }

    public String getKey() {
        return coreData.getKey();
    }

    public String getName() {
        return coreData.getName();
    }

    public RunePath getPath() {
        return RunePath.withId(coreData.getId());
    }
}
