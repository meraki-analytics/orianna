package com.robrua.orianna.type.dto.match;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "event")
public class Event extends OriannaDto {
    private static final long serialVersionUID = -802105921853149453L;
    private String ascendedType, buildingType, eventType, laneType, levelUpType, monsterType, pointCaptured, towerType, wardType;
    @ElementCollection
    @CollectionTable(name = "event_assistingparticipantid", joinColumns = @JoinColumn(name = "event_id") )
    private List<Integer> assistingParticipantIds;
    private Integer creatorId, itemAfter, itemBefore, itemId, killerId, participantId, skillSlot, teamId, victimId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @OneToOne(cascade = CascadeType.ALL)
    private Position position;

    private Long timestamp;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        if(ascendedType == null) {
            if(other.ascendedType != null) {
                return false;
            }
        }
        else if(!ascendedType.equals(other.ascendedType)) {
            return false;
        }
        if(assistingParticipantIds == null) {
            if(other.assistingParticipantIds != null) {
                return false;
            }
        }
        else if(!assistingParticipantIds.equals(other.assistingParticipantIds)) {
            return false;
        }
        if(buildingType == null) {
            if(other.buildingType != null) {
                return false;
            }
        }
        else if(!buildingType.equals(other.buildingType)) {
            return false;
        }
        if(creatorId == null) {
            if(other.creatorId != null) {
                return false;
            }
        }
        else if(!creatorId.equals(other.creatorId)) {
            return false;
        }
        if(eventType == null) {
            if(other.eventType != null) {
                return false;
            }
        }
        else if(!eventType.equals(other.eventType)) {
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
        if(itemId == null) {
            if(other.itemId != null) {
                return false;
            }
        }
        else if(!itemId.equals(other.itemId)) {
            return false;
        }
        if(killerId == null) {
            if(other.killerId != null) {
                return false;
            }
        }
        else if(!killerId.equals(other.killerId)) {
            return false;
        }
        if(laneType == null) {
            if(other.laneType != null) {
                return false;
            }
        }
        else if(!laneType.equals(other.laneType)) {
            return false;
        }
        if(levelUpType == null) {
            if(other.levelUpType != null) {
                return false;
            }
        }
        else if(!levelUpType.equals(other.levelUpType)) {
            return false;
        }
        if(monsterType == null) {
            if(other.monsterType != null) {
                return false;
            }
        }
        else if(!monsterType.equals(other.monsterType)) {
            return false;
        }
        if(participantId == null) {
            if(other.participantId != null) {
                return false;
            }
        }
        else if(!participantId.equals(other.participantId)) {
            return false;
        }
        if(pointCaptured == null) {
            if(other.pointCaptured != null) {
                return false;
            }
        }
        else if(!pointCaptured.equals(other.pointCaptured)) {
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
        if(skillSlot == null) {
            if(other.skillSlot != null) {
                return false;
            }
        }
        else if(!skillSlot.equals(other.skillSlot)) {
            return false;
        }
        if(teamId == null) {
            if(other.teamId != null) {
                return false;
            }
        }
        else if(!teamId.equals(other.teamId)) {
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
        if(towerType == null) {
            if(other.towerType != null) {
                return false;
            }
        }
        else if(!towerType.equals(other.towerType)) {
            return false;
        }
        if(victimId == null) {
            if(other.victimId != null) {
                return false;
            }
        }
        else if(!victimId.equals(other.victimId)) {
            return false;
        }
        if(wardType == null) {
            if(other.wardType != null) {
                return false;
            }
        }
        else if(!wardType.equals(other.wardType)) {
            return false;
        }
        return true;
    }

    /**
     * @return the ascendedType
     */
    public String getAscendedType() {
        return ascendedType;
    }

    /**
     * @return the assistingParticipantIds
     */
    public List<Integer> getAssistingParticipantIds() {
        return assistingParticipantIds;
    }

    /**
     * @return the buildingType
     */
    public String getBuildingType() {
        return buildingType;
    }

    /**
     * @return the creatorId
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @return the itemAfter
     */
    public Integer getItemAfter() {
        return itemAfter;
    }

    /**
     * @return the itemBefore
     */
    public Integer getItemBefore() {
        return itemBefore;
    }

    /**
     * @return the itemId
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @return the killerId
     */
    public Integer getKillerId() {
        return killerId;
    }

    /**
     * @return the laneType
     */
    public String getLaneType() {
        return laneType;
    }

    /**
     * @return the levelUpType
     */
    public String getLevelUpType() {
        return levelUpType;
    }

    /**
     * @return the monsterType
     */
    public String getMonsterType() {
        return monsterType;
    }

    /**
     * @return the participantId
     */
    public Integer getParticipantId() {
        return participantId;
    }

    /**
     * @return the pointCaptured
     */
    public String getPointCaptured() {
        return pointCaptured;
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @return the skillSlot
     */
    public Integer getSkillSlot() {
        return skillSlot;
    }

    /**
     * @return the teamId
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @return the towerType
     */
    public String getTowerType() {
        return towerType;
    }

    /**
     * @return the victimId
     */
    public Integer getVictimId() {
        return victimId;
    }

    /**
     * @return the wardType
     */
    public String getWardType() {
        return wardType;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ascendedType == null ? 0 : ascendedType.hashCode());
        result = prime * result + (assistingParticipantIds == null ? 0 : assistingParticipantIds.hashCode());
        result = prime * result + (buildingType == null ? 0 : buildingType.hashCode());
        result = prime * result + (creatorId == null ? 0 : creatorId.hashCode());
        result = prime * result + (eventType == null ? 0 : eventType.hashCode());
        result = prime * result + (itemAfter == null ? 0 : itemAfter.hashCode());
        result = prime * result + (itemBefore == null ? 0 : itemBefore.hashCode());
        result = prime * result + (itemId == null ? 0 : itemId.hashCode());
        result = prime * result + (killerId == null ? 0 : killerId.hashCode());
        result = prime * result + (laneType == null ? 0 : laneType.hashCode());
        result = prime * result + (levelUpType == null ? 0 : levelUpType.hashCode());
        result = prime * result + (monsterType == null ? 0 : monsterType.hashCode());
        result = prime * result + (participantId == null ? 0 : participantId.hashCode());
        result = prime * result + (pointCaptured == null ? 0 : pointCaptured.hashCode());
        result = prime * result + (position == null ? 0 : position.hashCode());
        result = prime * result + (skillSlot == null ? 0 : skillSlot.hashCode());
        result = prime * result + (teamId == null ? 0 : teamId.hashCode());
        result = prime * result + (timestamp == null ? 0 : timestamp.hashCode());
        result = prime * result + (towerType == null ? 0 : towerType.hashCode());
        result = prime * result + (victimId == null ? 0 : victimId.hashCode());
        result = prime * result + (wardType == null ? 0 : wardType.hashCode());
        return result;
    }

    /**
     * @param ascendedType
     *            the ascendedType to set
     */
    public void setAscendedType(final String ascendedType) {
        this.ascendedType = ascendedType;
    }

    /**
     * @param assistingParticipantIds
     *            the assistingParticipantIds to set
     */
    public void setAssistingParticipantIds(final List<Integer> assistingParticipantIds) {
        this.assistingParticipantIds = assistingParticipantIds;
    }

    /**
     * @param buildingType
     *            the buildingType to set
     */
    public void setBuildingType(final String buildingType) {
        this.buildingType = buildingType;
    }

    /**
     * @param creatorId
     *            the creatorId to set
     */
    public void setCreatorId(final Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @param eventType
     *            the eventType to set
     */
    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }

    /**
     * @param itemAfter
     *            the itemAfter to set
     */
    public void setItemAfter(final Integer itemAfter) {
        this.itemAfter = itemAfter;
    }

    /**
     * @param itemBefore
     *            the itemBefore to set
     */
    public void setItemBefore(final Integer itemBefore) {
        this.itemBefore = itemBefore;
    }

    /**
     * @param itemId
     *            the itemId to set
     */
    public void setItemId(final Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * @param killerId
     *            the killerId to set
     */
    public void setKillerId(final Integer killerId) {
        this.killerId = killerId;
    }

    /**
     * @param laneType
     *            the laneType to set
     */
    public void setLaneType(final String laneType) {
        this.laneType = laneType;
    }

    /**
     * @param levelUpType
     *            the levelUpType to set
     */
    public void setLevelUpType(final String levelUpType) {
        this.levelUpType = levelUpType;
    }

    /**
     * @param monsterType
     *            the monsterType to set
     */
    public void setMonsterType(final String monsterType) {
        this.monsterType = monsterType;
    }

    /**
     * @param participantId
     *            the participantId to set
     */
    public void setParticipantId(final Integer participantId) {
        this.participantId = participantId;
    }

    /**
     * @param pointCaptured
     *            the pointCaptured to set
     */
    public void setPointCaptured(final String pointCaptured) {
        this.pointCaptured = pointCaptured;
    }

    /**
     * @param position
     *            the position to set
     */
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * @param skillSlot
     *            the skillSlot to set
     */
    public void setSkillSlot(final Integer skillSlot) {
        this.skillSlot = skillSlot;
    }

    /**
     * @param teamId
     *            the teamId to set
     */
    public void setTeamId(final Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @param towerType
     *            the towerType to set
     */
    public void setTowerType(final String towerType) {
        this.towerType = towerType;
    }

    /**
     * @param victimId
     *            the victimId to set
     */
    public void setVictimId(final Integer victimId) {
        this.victimId = victimId;
    }

    /**
     * @param wardType
     *            the wardType to set
     */
    public void setWardType(final String wardType) {
        this.wardType = wardType;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Event [ascendedType=" + ascendedType + ", buildingType=" + buildingType + ", eventType=" + eventType + ", laneType=" + laneType
                + ", levelUpType=" + levelUpType + ", monsterType=" + monsterType + ", pointCaptured=" + pointCaptured + ", towerType=" + towerType
                + ", wardType=" + wardType + ", assistingParticipantIds=" + assistingParticipantIds + ", creatorId=" + creatorId + ", itemAfter=" + itemAfter
                + ", itemBefore=" + itemBefore + ", itemId=" + itemId + ", killerId=" + killerId + ", participantId=" + participantId + ", skillSlot="
                + skillSlot + ", teamId=" + teamId + ", victimId=" + victimId + ", position=" + position + ", timestamp=" + timestamp + "]";
    }
}
