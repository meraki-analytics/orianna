package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class ReforgedRuneTree extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneTree> {
    private static final long serialVersionUID = 3917722561308840927L;

    private final Supplier<ReforgedRunePath> domination = Suppliers.memoize(new Supplier<ReforgedRunePath>() {
        @Override
        public ReforgedRunePath get() {
            if(coreData.getDomination() == null) {
                return null;
            }
            return new ReforgedRunePath(coreData.getDomination());
        }
    });

    private final Supplier<ReforgedRunePath> inspiration = Suppliers.memoize(new Supplier<ReforgedRunePath>() {
        @Override
        public ReforgedRunePath get() {
            if(coreData.getInspiration() == null) {
                return null;
            }
            return new ReforgedRunePath(coreData.getInspiration());
        }
    });

    private final Supplier<ReforgedRunePath> precision = Suppliers.memoize(new Supplier<ReforgedRunePath>() {
        @Override
        public ReforgedRunePath get() {
            if(coreData.getPrecision() == null) {
                return null;
            }
            return new ReforgedRunePath(coreData.getPrecision());
        }
    });

    private final Supplier<ReforgedRunePath> resolve = Suppliers.memoize(new Supplier<ReforgedRunePath>() {
        @Override
        public ReforgedRunePath get() {
            if(coreData.getResolve() == null) {
                return null;
            }
            return new ReforgedRunePath(coreData.getResolve());
        }
    });

    private final Supplier<ReforgedRunePath> sorcery = Suppliers.memoize(new Supplier<ReforgedRunePath>() {
        @Override
        public ReforgedRunePath get() {
            if(coreData.getSorcery() == null) {
                return null;
            }
            return new ReforgedRunePath(coreData.getSorcery());
        }
    });

    public ReforgedRuneTree(final com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneTree coreData) {
        super(coreData);
    }

    public ReforgedRunePath getDomination() {
        return domination.get();
    }

    public ReforgedRunePath getInspiration() {
        return inspiration.get();
    }

    public ReforgedRunePath getPrecision() {
        return precision.get();
    }

    public ReforgedRunePath getResolve() {
        return resolve.get();
    }

    public ReforgedRunePath getSorcery() {
        return sorcery.get();
    }
}
