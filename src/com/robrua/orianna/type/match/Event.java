package com.robrua.orianna.type.match;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

import com.robrua.orianna.type.staticdata.Item;

public class Event implements Serializable {
    private static final long serialVersionUID = 4005138019588930090L;
    public final List<Participant> assistingParticipants;
    public final BuildingType buildingType;
    public final Participant creator, killer, victim, participant;
    public final EventType eventType;
    public final Item itemAfter, itemBefore, item;
    public final LaneType laneType;
    public final LevelUpType levelUpType;
    public final MonsterType monsterType;
    public final Position position;
    public final Side side;
    public final Integer skillSlot;
    public final MatchTeam team;
    public final Duration timestamp;
    public final TowerType towerType;
    public final WardType wardType;

    public Event(final List<Participant> assistingParticipants, final BuildingType buildingType, final Participant creator, final Participant killer,
            final Participant victim, final Participant participant, final EventType eventType, final Item itemAfter, final Item itemBefore, final Item item,
            final LaneType laneType, final LevelUpType levelUpType, final MonsterType monsterType, final Position position, final Side side,
            final Integer skillSlot, final MatchTeam team, final Duration timestamp, final TowerType towerType, final WardType wardType) {
        this.assistingParticipants = assistingParticipants;
        this.buildingType = buildingType;
        this.creator = creator;
        this.killer = killer;
        this.victim = victim;
        this.participant = participant;
        this.eventType = eventType;
        this.itemAfter = itemAfter;
        this.itemBefore = itemBefore;
        this.item = item;
        this.laneType = laneType;
        this.levelUpType = levelUpType;
        this.monsterType = monsterType;
        this.position = position;
        this.side = side;
        this.skillSlot = skillSlot;
        this.team = team;
        this.timestamp = timestamp;
        this.towerType = towerType;
        this.wardType = wardType;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Event)) {
            return false;
        }
        final Event other = (Event)obj;
        if(assistingParticipants == null) {
            if(other.assistingParticipants != null) {
                return false;
            }
        }
        else if(!assistingParticipants.equals(other.assistingParticipants)) {
            return false;
        }
        if(buildingType != other.buildingType) {
            return false;
        }
        if(creator == null) {
            if(other.creator != null) {
                return false;
            }
        }
        else if(!creator.equals(other.creator)) {
            return false;
        }
        if(eventType != other.eventType) {
            return false;
        }
        if(item == null) {
            if(other.item != null) {
                return false;
            }
        }
        else if(!item.equals(other.item)) {
            return false;
        }
        if(itemAfter == null) {
            if(other.itemAfter != null) {
                return false;
            }
        }
        else if(!itemAfter.equals(other.itemAfter)) {
            return false;
        }
        if(itemBefore == null) {
            if(other.itemBefore != null) {
                return false;
            }
        }
        else if(!itemBefore.equals(other.itemBefore)) {
            return false;
        }
        if(killer == null) {
            if(other.killer != null) {
                return false;
            }
        }
        else if(!killer.equals(other.killer)) {
            return false;
        }
        if(laneType != other.laneType) {
            return false;
        }
        if(levelUpType != other.levelUpType) {
            return false;
        }
        if(monsterType != other.monsterType) {
            return false;
        }
        if(participant == null) {
            if(other.participant != null) {
                return false;
            }
        }
        else if(!participant.equals(other.participant)) {
            return false;
        }
        if(position == null) {
            if(other.position != null) {
                return false;
            }
        }
        else if(!position.equals(other.position)) {
            return false;
        }
        if(side != other.side) {
            return false;
        }
        if(skillSlot == null) {
            if(other.skillSlot != null) {
                return false;
            }
        }
        else if(!skillSlot.equals(other.skillSlot)) {
            return false;
        }
        if(team == null) {
            if(other.team != null) {
                return false;
            }
        }
        else if(!team.equals(other.team)) {
            return false;
        }
        if(timestamp == null) {
            if(other.timestamp != null) {
                return false;
            }
        }
        else if(!timestamp.equals(other.timestamp)) {
            return false;
        }
        if(towerType != other.towerType) {
            return false;
        }
        if(victim == null) {
            if(other.victim != null) {
                return false;
            }
        }
        else if(!victim.equals(other.victim)) {
            return false;
        }
        if(wardType != other.wardType) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (assistingParticipants == null ? 0 : assistingParticipants.hashCode());
        result = prime * result + (buildingType == null ? 0 : buildingType.hashCode());
        result = prime * result + (creator == null ? 0 : creator.hashCode());
        result = prime * result + (eventType == null ? 0 : eventType.hashCode());
        result = prime * result + (item == null ? 0 : item.hashCode());
        result = prime * result + (itemAfter == null ? 0 : itemAfter.hashCode());
        result = prime * result + (itemBefore == null ? 0 : itemBefore.hashCode());
        result = prime * result + (killer == null ? 0 : killer.hashCode());
        result = prime * result + (laneType == null ? 0 : laneType.hashCode());
        result = prime * result + (levelUpType == null ? 0 : levelUpType.hashCode());
        result = prime * result + (monsterType == null ? 0 : monsterType.hashCode());
        result = prime * result + (participant == null ? 0 : participant.hashCode());
        result = prime * result + (position == null ? 0 : position.hashCode());
        result = prime * result + (side == null ? 0 : side.hashCode());
        result = prime * result + (skillSlot == null ? 0 : skillSlot.hashCode());
        result = prime * result + (team == null ? 0 : team.hashCode());
        result = prime * result + (timestamp == null ? 0 : timestamp.hashCode());
        result = prime * result + (towerType == null ? 0 : towerType.hashCode());
        result = prime * result + (victim == null ? 0 : victim.hashCode());
        result = prime * result + (wardType == null ? 0 : wardType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Event [timestamp=" + timestamp + ", position=" + position + ", eventType=" + eventType + "]";
    }
}
