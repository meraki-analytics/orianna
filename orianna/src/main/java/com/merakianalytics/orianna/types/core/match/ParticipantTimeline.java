package com.merakianalytics.orianna.types.core.match;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class ParticipantTimeline extends OriannaObject<com.merakianalytics.orianna.types.data.match.ParticipantTimeline> {
    private static final long serialVersionUID = -9013789487241071327L;

    private final Supplier<StatTotals> creepScore = Suppliers.memoize(new Supplier<StatTotals>() {
        @Override
        public StatTotals get() {
            if(coreData.getCreepScore() == null) {
                return null;
            }
            return new StatTotals(coreData.getCreepScore());
        }
    });

    private final Supplier<StatTotals> creepScoreDifference = Suppliers.memoize(new Supplier<StatTotals>() {
        @Override
        public StatTotals get() {
            if(coreData.getCreepScoreDifference() == null) {
                return null;
            }
            return new StatTotals(coreData.getCreepScoreDifference());
        }
    });

    private final Supplier<StatTotals> damageTaken = Suppliers.memoize(new Supplier<StatTotals>() {
        @Override
        public StatTotals get() {
            if(coreData.getDamageTaken() == null) {
                return null;
            }
            return new StatTotals(coreData.getDamageTaken());
        }
    });

    private final Supplier<StatTotals> damageTakenDifference = Suppliers.memoize(new Supplier<StatTotals>() {
        @Override
        public StatTotals get() {
            if(coreData.getDamageTakenDifference() == null) {
                return null;
            }
            return new StatTotals(coreData.getDamageTakenDifference());
        }
    });

    private final Supplier<StatTotals> experience = Suppliers.memoize(new Supplier<StatTotals>() {
        @Override
        public StatTotals get() {
            if(coreData.getExperience() == null) {
                return null;
            }
            return new StatTotals(coreData.getExperience());
        }
    });

    private final Supplier<StatTotals> experienceDifference = Suppliers.memoize(new Supplier<StatTotals>() {
        @Override
        public StatTotals get() {
            if(coreData.getExperienceDifference() == null) {
                return null;
            }
            return new StatTotals(coreData.getExperienceDifference());
        }
    });

    private final Supplier<StatTotals> gold = Suppliers.memoize(new Supplier<StatTotals>() {
        @Override
        public StatTotals get() {
            if(coreData.getGold() == null) {
                return null;
            }
            return new StatTotals(coreData.getGold());
        }
    });

    public ParticipantTimeline(final com.merakianalytics.orianna.types.data.match.ParticipantTimeline coreData) {
        super(coreData);
    }

    public StatTotals getCreepScore() {
        return creepScore.get();
    }

    public StatTotals getCreepScoreDifference() {
        return creepScoreDifference.get();
    }

    public StatTotals getDamageTaken() {
        return damageTaken.get();
    }

    public StatTotals getDamageTakenDifference() {
        return damageTakenDifference.get();
    }

    public StatTotals getExperience() {
        return experience.get();
    }

    public StatTotals getExperienceDifference() {
        return experienceDifference.get();
    }

    public StatTotals getGold() {
        return gold.get();
    }
}
