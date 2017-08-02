package com.merakianalytics.orianna.types.dto.match;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MatchEvent extends DataObject {
    private static final long serialVersionUID = 6204646184948219767L;
    private List<Integer> assistingParticipantIds;
    private String eventType, towerType, ascendedType, levelUpType, pointCaptured, wardType, monsterType, type, monsterSubType, laneType, buildingType;
    private MatchPosition position;
    private int teamId, killerId, skillSlot, victimId, afterId, itemId, participantId, creatorId, beforeId;
    private long timestamp;

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
        if(getClass() != obj.getClass()) {
            return false;
        }
        final MatchEvent other = (MatchEvent)obj;
        if(afterId != other.afterId) {
            return false;
        }
        if(ascendedType == null) {
            if(other.ascendedType != null) {
                return false;
            }
        } else if(!ascendedType.equals(other.ascendedType)) {
            return false;
        }
        if(assistingParticipantIds == null) {
            if(other.assistingParticipantIds != null) {
                return false;
            }
        } else if(!assistingParticipantIds.equals(other.assistingParticipantIds)) {
            return false;
        }
        if(beforeId != other.beforeId) {
            return false;
        }
        if(buildingType == null) {
            if(other.buildingType != null) {
                return false;
            }
        } else if(!buildingType.equals(other.buildingType)) {
            return false;
        }
        if(creatorId != other.creatorId) {
            return false;
        }
        if(eventType == null) {
            if(other.eventType != null) {
                return false;
            }
        } else if(!eventType.equals(other.eventType)) {
            return false;
        }
        if(itemId != other.itemId) {
            return false;
        }
        if(killerId != other.killerId) {
            return false;
        }
        if(laneType == null) {
            if(other.laneType != null) {
                return false;
            }
        } else if(!laneType.equals(other.laneType)) {
            return false;
        }
        if(levelUpType == null) {
            if(other.levelUpType != null) {
                return false;
            }
        } else if(!levelUpType.equals(other.levelUpType)) {
            return false;
        }
        if(monsterSubType == null) {
            if(other.monsterSubType != null) {
                return false;
            }
        } else if(!monsterSubType.equals(other.monsterSubType)) {
            return false;
        }
        if(monsterType == null) {
            if(other.monsterType != null) {
                return false;
            }
        } else if(!monsterType.equals(other.monsterType)) {
            return false;
        }
        if(participantId != other.participantId) {
            return false;
        }
        if(pointCaptured == null) {
            if(other.pointCaptured != null) {
                return false;
            }
        } else if(!pointCaptured.equals(other.pointCaptured)) {
            return false;
        }
        if(position == null) {
            if(other.position != null) {
                return false;
            }
        } else if(!position.equals(other.position)) {
            return false;
        }
        if(skillSlot != other.skillSlot) {
            return false;
        }
        if(teamId != other.teamId) {
            return false;
        }
        if(timestamp != other.timestamp) {
            return false;
        }
        if(towerType == null) {
            if(other.towerType != null) {
                return false;
            }
        } else if(!towerType.equals(other.towerType)) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        } else if(!type.equals(other.type)) {
            return false;
        }
        if(victimId != other.victimId) {
            return false;
        }
        if(wardType == null) {
            if(other.wardType != null) {
                return false;
            }
        } else if(!wardType.equals(other.wardType)) {
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
     * @return the beforeId
     */
    public int getBeforeId() {
        return beforeId;
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
    public int getCreatorId() {
        return creatorId;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
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
     * @return the monsterSubType
     */
    public String getMonsterSubType() {
        return monsterSubType;
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
    public int getParticipantId() {
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
    public MatchPosition getPosition() {
        return position;
    }

    /**
     * @return the skillSlot
     */
    public int getSkillSlot() {
        return skillSlot;
    }

    /**
     * @return the teamId
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @return the towerType
     */
    public String getTowerType() {
        return towerType;
    }

    /**
     * @return the type
     */
    public String getType() {
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
        result = prime * result + afterId;
        result = prime * result + (ascendedType == null ? 0 : ascendedType.hashCode());
        result = prime * result + (assistingParticipantIds == null ? 0 : assistingParticipantIds.hashCode());
        result = prime * result + beforeId;
        result = prime * result + (buildingType == null ? 0 : buildingType.hashCode());
        result = prime * result + creatorId;
        result = prime * result + (eventType == null ? 0 : eventType.hashCode());
        result = prime * result + itemId;
        result = prime * result + killerId;
        result = prime * result + (laneType == null ? 0 : laneType.hashCode());
        result = prime * result + (levelUpType == null ? 0 : levelUpType.hashCode());
        result = prime * result + (monsterSubType == null ? 0 : monsterSubType.hashCode());
        result = prime * result + (monsterType == null ? 0 : monsterType.hashCode());
        result = prime * result + participantId;
        result = prime * result + (pointCaptured == null ? 0 : pointCaptured.hashCode());
        result = prime * result + (position == null ? 0 : position.hashCode());
        result = prime * result + skillSlot;
        result = prime * result + teamId;
        result = prime * result + (int)(timestamp ^ timestamp >>> 32);
        result = prime * result + (towerType == null ? 0 : towerType.hashCode());
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
     * @param ascendedType
     *        the ascendedType to set
     */
    public void setAscendedType(final String ascendedType) {
        this.ascendedType = ascendedType;
    }

    /**
     * @param assistingParticipantIds
     *        the assistingParticipantIds to set
     */
    public void setAssistingParticipantIds(final List<Integer> assistingParticipantIds) {
        this.assistingParticipantIds = assistingParticipantIds;
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
    public void setBuildingType(final String buildingType) {
        this.buildingType = buildingType;
    }

    /**
     * @param creatorId
     *        the creatorId to set
     */
    public void setCreatorId(final int creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @param eventType
     *        the eventType to set
     */
    public void setEventType(final String eventType) {
        this.eventType = eventType;
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
    public void setLaneType(final String laneType) {
        this.laneType = laneType;
    }

    /**
     * @param levelUpType
     *        the levelUpType to set
     */
    public void setLevelUpType(final String levelUpType) {
        this.levelUpType = levelUpType;
    }

    /**
     * @param monsterSubType
     *        the monsterSubType to set
     */
    public void setMonsterSubType(final String monsterSubType) {
        this.monsterSubType = monsterSubType;
    }

    /**
     * @param monsterType
     *        the monsterType to set
     */
    public void setMonsterType(final String monsterType) {
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
     * @param pointCaptured
     *        the pointCaptured to set
     */
    public void setPointCaptured(final String pointCaptured) {
        this.pointCaptured = pointCaptured;
    }

    /**
     * @param position
     *        the position to set
     */
    public void setPosition(final MatchPosition position) {
        this.position = position;
    }

    /**
     * @param skillSlot
     *        the skillSlot to set
     */
    public void setSkillSlot(final int skillSlot) {
        this.skillSlot = skillSlot;
    }

    /**
     * @param teamId
     *        the teamId to set
     */
    public void setTeamId(final int teamId) {
        this.teamId = teamId;
    }

    /**
     * @param timestamp
     *        the timestamp to set
     */
    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @param towerType
     *        the towerType to set
     */
    public void setTowerType(final String towerType) {
        this.towerType = towerType;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final String type) {
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
    public void setWardType(final String wardType) {
        this.wardType = wardType;
    }
}
