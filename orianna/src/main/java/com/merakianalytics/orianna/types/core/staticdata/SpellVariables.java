package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.*;
import com.merakianalytics.orianna.types.core.OriannaObject;

import java.util.*;

public class SpellVariables extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.SpellVariables> {
    private static final long serialVersionUID = -8759913162668284032L;

    private final Supplier<List<Number>> coefficients = Suppliers.memoize(new Supplier<List<Number>>() {
        @Override
        public List<Number> get() {
            return Collections.unmodifiableList(coreData.getCoefficients());
        }
    });

    public SpellVariables(final com.merakianalytics.orianna.types.data.staticdata.SpellVariables coreData) {
        super(coreData);
    }

    public List<Number> getCoefficients() {
        return coefficients.get();
    }

    public String getDynamic() {
        return coreData.getDynamic();
    }

    public String getKey() {
        return coreData.getKey();
    }

    public String getLink() {
        return coreData.getLink();
    }

    public String getRanksWith() {
        return coreData.getRanksWith();
    }
}
