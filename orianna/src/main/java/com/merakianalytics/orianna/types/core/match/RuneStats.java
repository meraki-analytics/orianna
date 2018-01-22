package com.merakianalytics.orianna.types.core.match;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.staticdata.ReforgedRune;
import com.merakianalytics.orianna.types.core.staticdata.Versions;

public class RuneStats extends OriannaObject<com.merakianalytics.orianna.types.data.match.RuneStats> {
    private static final long serialVersionUID = 3663530937677122757L;

    private final Supplier<ReforgedRune> rune = Suppliers.memoize(new Supplier<ReforgedRune>() {
        @Override
        public ReforgedRune get() {
            if(coreData.getId() == 0) {
                return null;
            }
            final String version = Versions.withPlatform(Platform.withTag(coreData.getPlatform())).get().truncate(coreData.getVersion());
            return ReforgedRune.withId(coreData.getId()).withPlatform(Platform.withTag(coreData.getPlatform())).withVersion(version).get();
        }
    });

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

    @Searchable({ReforgedRune.class, String.class, int.class})
    public ReforgedRune getRune() {
        return rune.get();
    }

    public List<Integer> getVariables() {
        return variables.get();
    }
}
