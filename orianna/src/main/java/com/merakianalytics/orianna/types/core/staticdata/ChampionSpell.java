package com.merakianalytics.orianna.types.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

public class ChampionSpell extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.ChampionSpell> {
    private static final long serialVersionUID = 766914644995245142L;

    private final Supplier<SearchableList<Image>> alternativeImages = Suppliers.memoize(new Supplier<SearchableList<Image>>() {
        @Override
        public SearchableList<Image> get() {
            final List<Image> alternativeImages = new ArrayList<>(coreData.getAlternativeImages().size());
            for(final com.merakianalytics.orianna.types.data.staticdata.Image image : coreData.getAlternativeImages()) {
                alternativeImages.add(new Image(image));
            }
            return SearchableLists.unmodifiableFrom(alternativeImages);
        }
    });

    private final Supplier<List<Double>> cooldowns = Suppliers.memoize(new Supplier<List<Double>>() {
        @Override
        public List<Double> get() {
            return Collections.unmodifiableList(coreData.getCooldowns());
        }
    });

    private final Supplier<List<Integer>> costs = Suppliers.memoize(new Supplier<List<Integer>>() {
        @Override
        public List<Integer> get() {
            return Collections.unmodifiableList(coreData.getCosts());
        }
    });

    private final Supplier<List<List<Double>>> effects = Suppliers.memoize(new Supplier<List<List<Double>>>() {
        @Override
        public List<List<Double>> get() {
            final List<List<Double>> views = new ArrayList<>(coreData.getEffects().size());
            for(final List<Double> effect : coreData.getEffects()) {
                views.add(Collections.unmodifiableList(effect));
            }
            return Collections.unmodifiableList(views);
        }
    });

    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            return new Image(coreData.getImage());
        }
    });

    private final Supplier<List<String>> levelUpEffects = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            return Collections.unmodifiableList(coreData.getLevelUpEffects());
        }
    });

    private final Supplier<List<String>> levelUpKeywords = Suppliers.memoize(new Supplier<List<String>>() {
        @Override
        public List<String> get() {
            return Collections.unmodifiableList(coreData.getLevelUpKeywords());
        }
    });

    private final Supplier<List<Integer>> ranges = Suppliers.memoize(new Supplier<List<Integer>>() {
        @Override
        public List<Integer> get() {
            return Collections.unmodifiableList(coreData.getRanges());
        }
    });

    private final Supplier<SearchableList<SpellVariables>> variables = Suppliers.memoize(new Supplier<SearchableList<SpellVariables>>() {
        @Override
        public SearchableList<SpellVariables> get() {
            final List<SpellVariables> variables = new ArrayList<>(coreData.getVariables().size());
            for(final com.merakianalytics.orianna.types.data.staticdata.SpellVariables vars : coreData.getVariables()) {
                variables.add(new SpellVariables(vars));
            }
            return SearchableLists.unmodifiableFrom(variables);
        }
    });

    public ChampionSpell(final com.merakianalytics.orianna.types.data.staticdata.ChampionSpell coreData) {
        super(coreData);
    }

    public SearchableList<Image> getAlternativeImages() {
        return alternativeImages.get();
    }

    public List<Double> getCooldowns() {
        return cooldowns.get();
    }

    public List<Integer> getCosts() {
        return costs.get();
    }

    public String getDescription() {
        return coreData.getDescription();
    }

    public List<List<Double>> getEffects() {
        return effects.get();
    }

    public Image getImage() {
        return image.get();
    }

    @Searchable(String.class)
    public String getKey() {
        return coreData.getKey();
    }

    public List<String> getLevelUpEffects() {
        return levelUpEffects.get();
    }

    public List<String> getLevelUpKeywords() {
        return levelUpKeywords.get();
    }

    public int getMaxRank() {
        return coreData.getMaxRank();
    }

    @Searchable(String.class)
    public String getName() {
        return coreData.getName();
    }

    public List<Integer> getRanges() {
        return ranges.get();
    }

    public String getResource() {
        return coreData.getResource();
    }

    public String getResourceDescription() {
        return coreData.getResourceDescription();
    }

    public String getSanitizedDescription() {
        return coreData.getSanitizedDescription();
    }

    public String getSanitizedTooltip() {
        return coreData.getSanitizedTooltip();
    }

    public String getTooltip() {
        return coreData.getTooltip();
    }

    public SearchableList<SpellVariables> getVariables() {
        return variables.get();
    }
}
