package com.merakianalytics.orianna.types.data.match;

import java.util.List;

import org.joda.time.Duration;

import com.merakianalytics.orianna.types.common.AscensionType;
import com.merakianalytics.orianna.types.common.BuildingType;
import com.merakianalytics.orianna.types.common.EventType;
import com.merakianalytics.orianna.types.common.LaneType;
import com.merakianalytics.orianna.types.common.LevelUpType;
import com.merakianalytics.orianna.types.common.MonsterSubType;
import com.merakianalytics.orianna.types.common.MonsterType;
import com.merakianalytics.orianna.types.common.Point;
import com.merakianalytics.orianna.types.common.Skill;
import com.merakianalytics.orianna.types.common.TurretType;
import com.merakianalytics.orianna.types.common.WardType;
import com.merakianalytics.orianna.types.data.CoreData;

public class Event extends CoreData {
    private static final long serialVersionUID = -3729596181763268808L;
    private AscensionType ascensionType;
    private List<Integer> assistingParticipants;
    private BuildingType buildingType;
    private Point capturedPoint;
    private int killerId, victimId, afterId, itemId, participantId, creatorId, beforeId;
    private LaneType laneType;
    private LevelUpType levelUpType;
    private MonsterSubType monsterSubType;
    private MonsterType monsterType;
    private Position position;
    private Skill skill;
    private com.merakianalytics.orianna.types.common.Side team;
    private Duration timestamp;
    private TurretType turretType;
    private EventType type;
    private WardType wardType;

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event)obj;
        if(afterId != other.afterId) {
            return false;
        }
        if(ascensionType != other.ascensionType) {
            return false;
        }
        if(assistingParticipants == null) {
            if(other.assistingParticipants != null) {
                return false;
            }
        } else if(!assistingParticipants.equals(other.assistingParticipants)) {
            return false;
        }
        if(beforeId != other.beforeId) {
            return false;
        }
        if(buildingType != other.buildingType) {
            return false;
        }
        if(capturedPoint != other.capturedPoint) {
            return false;
        }
        if(creatorId != other.creatorId) {
            return false;
        }
        if(itemId != other.itemId) {
            return false;
        }
        if(killerId != other.killerId) {
            return false;
        }
        if(laneType != other.laneType) {
            return false;
        }
        if(levelUpType != other.levelUpType) {
            return false;
        }
        if(monsterSubType != other.monsterSubType) {
            return false;
        }
        if(monsterType != other.monsterType) {
            return false;
        }
        if(participantId != other.participantId) {
            return false;
        }
        if(position == null) {
            if(other.position != null) {
                return false;
            }
        } else if(!position.equals(other.position)) {
            return false;
        }
        if(skill != other.skill) {
            return false;
        }
        if(team != other.team) {
            return false;
        }
        if(timestamp == null) {
            if(other.timestamp != null) {
                return false;
            }
        } else if(!timestamp.equals(other.timestamp)) {
            return false;
        }
        if(turretType != other.turretType) {
            return false;
        }
        if(type != other.type) {
            return false;
        }
        if(victimId != other.victimId) {
            return false;
        }
        if(wardType != other.wardType) {
            return false;
        }
        return true;
    }

    /**
     * @return the afterId
     */
    public int getAfterId() {
        return afterId;
    }

    /**
     * @return the ascensionType
     */
    public AscensionType getAscensionType() {
        return ascensionType;
    }

    /**
     * @return the assistingParticipants
     */
    public List<Integer> getAssistingParticipants() {
        return assistingParticipants;
    }

    /**
     * @return the beforeId
     */
    public int getBeforeId() {
        return beforeId;
    }

    /**
     * @return the buildingType
     */
    public BuildingType getBuildingType() {
        return buildingType;
    }

    /**
     * @return the capturedPoint
     */
    public Point getCapturedPoint() {
        return capturedPoint;
    }

    /**
     * @return the creatorId
     */
    public int getCreatorId() {
        return creatorId;
    }

    /**
     * @return the itemId
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @return the killerId
     */
    public int getKillerId() {
        return killerId;
    }

    /**
     * @return the laneType
     */
    public LaneType getLaneType() {
        return laneType;
    }

    /**
     * @return the levelUpType
     */
    public LevelUpType getLevelUpType() {
        return levelUpType;
    }

    /**
     * @return the monsterSubType
     */
    public MonsterSubType getMonsterSubType() {
        return monsterSubType;
    }

    /**
     * @return the monsterType
     */
    public MonsterType getMonsterType() {
        return monsterType;
    }

    /**
     * @return the participantId
     */
    public int getParticipantId() {
        return participantId;
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @return the skill
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * @return the team
     */
    public com.merakianalytics.orianna.types.common.Side getTeam() {
        return team;
    }

    /**
     * @return the timestamp
     */
    public Duration getTimestamp() {
        return timestamp;
    }

    /**
     * @return the turretType
     */
    public TurretType getTurretType() {
        return turretType;
    }

    /**
     * @return the type
     */
    public EventType getType() {
        return type;
    }

    /**
     * @return the victimId
     */
    public int getVictimId() {
        return victimId;
    }

    /**
     * @return the wardType
     */
    public WardType getWardType() {
        return wardType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + afterId;
        result = prime * result + (ascensionType == null ? 0 : ascensionType.hashCode());
        result = prime * result + (assistingParticipants == null ? 0 : assistingParticipants.hashCode());
        result = prime * result + beforeId;
        result = prime * result + (buildingType == null ? 0 : buildingType.hashCode());
        result = prime * result + (capturedPoint == null ? 0 : capturedPoint.hashCode());
        result = prime * result + creatorId;
        result = prime * result + itemId;
        result = prime * result + killerId;
        result = prime * result + (laneType == null ? 0 : laneType.hashCode());
        result = prime * result + (levelUpType == null ? 0 : levelUpType.hashCode());
        result = prime * result + (monsterSubType == null ? 0 : monsterSubType.hashCode());
        result = prime * result + (monsterType == null ? 0 : monsterType.hashCode());
        result = prime * result + participantId;
        result = prime * result + (position == null ? 0 : position.hashCode());
        result = prime * result + (skill == null ? 0 : skill.hashCode());
        result = prime * result + (team == null ? 0 : team.hashCode());
        result = prime * result + (timestamp == null ? 0 : timestamp.hashCode());
        result = prime * result + (turretType == null ? 0 : turretType.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + victimId;
        result = prime * result + (wardType == null ? 0 : wardType.hashCode());
        return result;
    }

    /**
     * @param afterId
     *        the afterId to set
     */
    public void setAfterId(final int afterId) {
        this.afterId = afterId;
    }

    /**
     * @param ascensionType
     *        the ascensionType to set
     */
    public void setAscensionType(final AscensionType ascensionType) {
        this.ascensionType = ascensionType;
    }

    /**
     * @param assistingParticipants
     *        the assistingParticipants to set
     */
    public void setAssistingParticipants(final List<Integer> assistingParticipants) {
        this.assistingParticipants = assistingParticipants;
    }

    /**
     * @param beforeId
     *        the beforeId to set
     */
    public void setBeforeId(final int beforeId) {
        this.beforeId = beforeId;
    }

    /**
     * @param buildingType
     *        the buildingType to set
     */
    public void setBuildingType(final BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    /**
     * @param capturedPoint
     *        the capturedPoint to set
     */
    public void setCapturedPoint(final Point capturedPoint) {
        this.capturedPoint = capturedPoint;
    }

    /**
     * @param creatorId
     *        the creatorId to set
     */
    public void setCreatorId(final int creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @param itemId
     *        the itemId to set
     */
    public void setItemId(final int itemId) {
        this.itemId = itemId;
    }

    /**
     * @param killerId
     *        the killerId to set
     */
    public void setKillerId(final int killerId) {
        this.killerId = killerId;
    }

    /**
     * @param laneType
     *        the laneType to set
     */
    public void setLaneType(final LaneType laneType) {
        this.laneType = laneType;
    }

    /**
     * @param levelUpType
     *        the levelUpType to set
     */
    public void setLevelUpType(final LevelUpType levelUpType) {
        this.levelUpType = levelUpType;
    }

    /**
     * @param monsterSubType
     *        the monsterSubType to set
     */
    public void setMonsterSubType(final MonsterSubType monsterSubType) {
        this.monsterSubType = monsterSubType;
    }

    /**
     * @param monsterType
     *        the monsterType to set
     */
    public void setMonsterType(final MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    /**
     * @param participantId
     *        the participantId to set
     */
    public void setParticipantId(final int participantId) {
        this.participantId = participantId;
    }

    /**
     * @param position
     *        the position to set
     */
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * @param skill
     *        the skill to set
     */
    public void setSkill(final Skill skill) {
        this.skill = skill;
    }

    /**
     * @param team
     *        the team to set
     */
    public void setTeam(final com.merakianalytics.orianna.types.common.Side team) {
        this.team = team;
    }

    /**
     * @param timestamp
     *        the timestamp to set
     */
    public void setTimestamp(final Duration timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @param turretType
     *        the turretType to set
     */
    public void setTurretType(final TurretType turretType) {
        this.turretType = turretType;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final EventType type) {
        this.type = type;
    }

    /**
     * @param victimId
     *        the victimId to set
     */
    public void setVictimId(final int victimId) {
        this.victimId = victimId;
    }

    /**
     * @param wardType
     *        the wardType to set
     */
    public void setWardType(final WardType wardType) {
        this.wardType = wardType;
    }
}
