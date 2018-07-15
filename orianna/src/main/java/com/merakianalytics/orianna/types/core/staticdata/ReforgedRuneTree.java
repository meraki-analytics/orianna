package com.merakianalytics.orianna.types.core.staticdata;

import java.util.HashMap;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class ReforgedRuneTree extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneTree> {
    private static final long serialVersionUID = 3917722561308840927L;

    private int counter = 0;

    private final Supplier<ReforgedRunePath> domination = Suppliers.memoize(new Supplier<ReforgedRunePath>() {
        @Override
        public ReforgedRunePath get() {
            if(coreData.getDomination() == null) {
                if(++counter >= 5) {
                    runes = null;
                }
                return null;
            }
            final ReforgedRunePath path = new ReforgedRunePath(coreData.getDomination(), runes);
            if(++counter >= 5) {
                runes = null;
            }
            return path;
        }
    });

    private final Supplier<ReforgedRunePath> inspiration = Suppliers.memoize(new Supplier<ReforgedRunePath>() {
        @Override
        public ReforgedRunePath get() {
            if(coreData.getInspiration() == null) {
                if(++counter >= 5) {
                    runes = null;
                }
                return null;
            }
            final ReforgedRunePath path = new ReforgedRunePath(coreData.getInspiration(), runes);
            if(++counter >= 5) {
                runes = null;
            }
            return path;
        }
    });

    private final Supplier<ReforgedRunePath> precision = Suppliers.memoize(new Supplier<ReforgedRunePath>() {
        @Override
        public ReforgedRunePath get() {
            if(coreData.getPrecision() == null) {
                if(++counter >= 5) {
                    runes = null;
                }
                return null;
            }
            final ReforgedRunePath path = new ReforgedRunePath(coreData.getPrecision(), runes);
            if(++counter >= 5) {
                runes = null;
            }
            return path;
        }
    });

    private final Supplier<ReforgedRunePath> resolve = Suppliers.memoize(new Supplier<ReforgedRunePath>() {
        @Override
        public ReforgedRunePath get() {
            if(coreData.getResolve() == null) {
                if(++counter >= 5) {
                    runes = null;
                }
                return null;
            }
            final ReforgedRunePath path = new ReforgedRunePath(coreData.getResolve(), runes);
            if(++counter >= 5) {
                runes = null;
            }
            return path;
        }
    });

    private java.util.Map<Integer, ReforgedRune> runes;
    private final Supplier<ReforgedRunePath> sorcery = Suppliers.memoize(new Supplier<ReforgedRunePath>() {
        @Override
        public ReforgedRunePath get() {
            if(coreData.getSorcery() == null) {
                if(++counter >= 5) {
                    runes = null;
                }
                return null;
            }
            final ReforgedRunePath path = new ReforgedRunePath(coreData.getSorcery(), runes);
            if(++counter >= 5) {
                runes = null;
            }
            return path;
        }
    });

    public ReforgedRuneTree(final com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneTree coreData, final List<ReforgedRune> runes) {
        super(coreData);
        this.runes = new HashMap<>();
        for(final ReforgedRune rune : runes) {
            this.runes.put(rune.getId(), rune);
        }
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
