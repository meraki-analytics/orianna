package lib.orianna.type.match;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

public class Event implements Serializable {
    private static final long serialVersionUID = 1693312946675761958L;
    public final List<Participant> assistingParticipants;
    public final BuildingType buildingType;
    public final Participant creator, killer, victim;
    public final EventType eventType;
    public final LaneType laneType;
    public final MonsterType monsterType;
    public final Position position;
    public final MatchTeam team;
    public final Duration timestamp;
    public final TowerType towerType;
    public final WardType wardType;

    public Event(final List<Participant> assistingParticipants, final Participant creator, final Participant killer, final Participant victim,
            final MatchTeam team, final Duration timestamp, final Position position, final EventType eventType, final BuildingType buildingType,
            final LaneType laneType, final MonsterType monsterType, final TowerType towerType, final WardType wardType) {
        this.assistingParticipants = assistingParticipants;
        this.creator = creator;
        this.killer = killer;
        this.victim = victim;
        this.team = team;
        this.timestamp = timestamp;
        this.position = position;
        this.eventType = eventType;
        this.buildingType = buildingType;
        this.laneType = laneType;
        this.monsterType = monsterType;
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
        if(monsterType != other.monsterType) {
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
        result = prime * result + (killer == null ? 0 : killer.hashCode());
        result = prime * result + (laneType == null ? 0 : laneType.hashCode());
        result = prime * result + (monsterType == null ? 0 : monsterType.hashCode());
        result = prime * result + (position == null ? 0 : position.hashCode());
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
