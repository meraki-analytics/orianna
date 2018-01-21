package com.merakianalytics.orianna.types.core.staticdata;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class SpellVariables extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.SpellVariables> {
    private static final long serialVersionUID = -8759913162668284032L;

    private final Supplier<List<Double>> coefficients = Suppliers.memoize(new Supplier<List<Double>>() {
        @Override
        public List<Double> get() {
            if(coreData.getCoefficients() == null) {
                return null;
            }
            return Collections.unmodifiableList(coreData.getCoefficients());
        }
    });

    public SpellVariables(final com.merakianalytics.orianna.types.data.staticdata.SpellVariables coreData) {
        super(coreData);
    }

    public List<Double> getCoefficients() {
        return coefficients.get();
    }

    public String getDynamic() {
        return coreData.getDynamic();
    }

    @Searchable(String.class)
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
