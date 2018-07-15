package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Function;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class ReforgedRuneSlot extends OriannaObject.ListProxy<ReforgedRune, Integer, com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneSlot> {
    private static final long serialVersionUID = 5586589043798659449L;

    public ReforgedRuneSlot(final com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneSlot coreData,
        final java.util.Map<Integer, ReforgedRune> runes) {
        super(coreData, new Function<Integer, ReforgedRune>() {
            @Override
            public ReforgedRune apply(final Integer id) {
                return runes.get(id);
            }
        });
    }
}
