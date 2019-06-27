package com.merakianalytics.orianna.types.core.match;

import org.joda.time.Duration;

import com.merakianalytics.orianna.types.common.AscensionType;
import com.merakianalytics.orianna.types.common.BuildingType;
import com.merakianalytics.orianna.types.common.EventType;
import com.merakianalytics.orianna.types.common.LaneType;
import com.merakianalytics.orianna.types.common.LevelUpType;
import com.merakianalytics.orianna.types.common.MonsterSubType;
import com.merakianalytics.orianna.types.common.MonsterType;
import com.merakianalytics.orianna.types.common.Point;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.common.Skill;
import com.merakianalytics.orianna.types.common.TurretType;
import com.merakianalytics.orianna.types.common.WardType;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.staticdata.Item;

public abstract class Event extends OriannaObject<com.merakianalytics.orianna.types.data.match.Event> {
    private static final long serialVersionUID = -2437642335207262614L;

    public Event(final com.merakianalytics.orianna.types.data.match.Event coreData) {
        super(coreData);
    }

    public abstract Item getAfter();

    public abstract AscensionType getAscensionType();

    public abstract SearchableList<Participant> getAssistingParticipants();

    public abstract Item getBefore();

    public abstract BuildingType getBuildingType();

    public abstract Point getCapturedPoint();

    public abstract Participant getCreator();

    public abstract Item getItem();

    public abstract Participant getKiller();

    public abstract LaneType getLaneType();

    public abstract LevelUpType getLevelUpType();

    public abstract MonsterSubType getMonsterSubType();

    public abstract MonsterType getMonsterType();

    public abstract Participant getParticipant();

    public abstract Position getPosition();

    public abstract Side getSide();

    public abstract Skill getSkill();

    public abstract Team getTeam();

    public abstract Duration getTimestamp();

    public abstract TurretType getTurretType();

    public abstract EventType getType();

    public abstract Participant getVictim();

    public abstract WardType getWardType();
}
