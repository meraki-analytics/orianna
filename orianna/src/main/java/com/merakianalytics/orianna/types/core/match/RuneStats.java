package com.merakianalytics.orianna.types.core.match;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class RuneStats extends OriannaObject<com.merakianalytics.orianna.types.data.match.RuneStats> {
    private static final long serialVersionUID = 3663530937677122757L;

    private final Supplier<List<Integer>> variables = Suppliers.memoize(new Supplier<List<Integer>>() {
        @Override
        public List<Integer> get() {
            if(coreData.getVariables() == null) {
                return null;
            }
            return Collections.unmodifiableList(coreData.getVariables());
        }
    });

    public RuneStats(final com.merakianalytics.orianna.types.data.match.RuneStats coreData) {
        super(coreData);
    }

    @Searchable({int.class})
    public int getId() {
        return coreData.getId();
    }

    public List<Integer> getVariables() {
        return variables.get();
    }
}
