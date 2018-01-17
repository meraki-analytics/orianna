package com.merakianalytics.orianna.types.core.match;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class ParticipantFrame extends OriannaObject<com.merakianalytics.orianna.types.data.match.ParticipantFrame> {
    private static final long serialVersionUID = -8233781937537156206L;

    private final Supplier<Position> position = Suppliers.memoize(new Supplier<Position>() {
        @Override
        public Position get() {
            return new Position(coreData.getPosition());
        }
    });

    public ParticipantFrame(final com.merakianalytics.orianna.types.data.match.ParticipantFrame coreData) {
        super(coreData);
    }

    public int getCreepScore() {
        return coreData.getCreepScore();
    }

    public int getDominionScore() {
        return coreData.getDominionScore();
    }

    public int getExperience() {
        return coreData.getExperience();
    }

    public int getGold() {
        return coreData.getGold();
    }

    public int getGoldEarned() {
        return coreData.getGoldEarned();
    }

    public int getLevel() {
        return coreData.getLevel();
    }

    public int getNeutralMinionsKilled() {
        return coreData.getNeutralMinionsKilled();
    }

    public Position getPosition() {
        return position.get();
    }

    public int getTeamScore() {
        return coreData.getTeamScore();
    }
}
