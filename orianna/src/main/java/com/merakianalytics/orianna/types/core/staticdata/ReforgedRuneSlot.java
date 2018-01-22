package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Collections;

import com.merakianalytics.orianna.types.core.OriannaObject;

public class ReforgedRuneSlot extends OriannaObject.ListProxy<ReforgedRune, Integer, com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneSlot> {
    private static final long serialVersionUID = 5586589043798659449L;

    public ReforgedRuneSlot(final com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneSlot coreData) {
        super(coreData, Collections.<ReforgedRune> emptyList()); // TODO: this
    }
}
