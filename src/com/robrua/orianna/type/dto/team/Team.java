package com.robrua.orianna.type.dto.team;

import java.util.List;

import com.robrua.orianna.type.dto.OriannaDto;

public class Team extends OriannaDto {
    private static final long serialVersionUID = 7937264447395600368L;
    private Long createDate, lastGameDate, lastJoinDate, lastJoinedRankedTeamQueueDate, modifyDate, secondLastJoinDate, thirdLastJoinDate;
    private String fullId, name, status, tag;
    private List<MatchHistorySummary> matchHistory;
    private Roster roster;
    private List<TeamStatDetail> teamStatDetails;

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
        if(!(obj instanceof Team)) {
            return false;
        }
        final Team other = (Team)obj;
        if(fullId == null) {
            if(other.fullId != null) {
                return false;
            }
        }
        else if(!fullId.equals(other.fullId)) {
            return false;
        }
        return true;
    }

    /**
     * @return the createDate
     */
    public Long getCreateDate() {
        return createDate;
    }

    /**
     * @return the fullId
     */
    public String getFullId() {
        return fullId;
    }

    /**
     * @return the lastGameDate
     */
    public Long getLastGameDate() {
        return lastGameDate;
    }

    /**
     * @return the lastJoinDate
     */
    public Long getLastJoinDate() {
        return lastJoinDate;
    }

    /**
     * @return the lastJoinedRankedTeamQueueDate
     */
    public Long getLastJoinedRankedTeamQueueDate() {
        return lastJoinedRankedTeamQueueDate;
    }

    /**
     * @return the matchHistory
     */
    public List<MatchHistorySummary> getMatchHistory() {
        return matchHistory;
    }

    /**
     * @return the modifyDate
     */
    public Long getModifyDate() {
        return modifyDate;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the roster
     */
    public Roster getRoster() {
        return roster;
    }

    /**
     * @return the secondLastJoinDate
     */
    public Long getSecondLastJoinDate() {
        return secondLastJoinDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @return the teamStatDetails
     */
    public List<TeamStatDetail> getTeamStatDetails() {
        return teamStatDetails;
    }

    /**
     * @return the thirdLastJoinDate
     */
    public Long getThirdLastJoinDate() {
        return thirdLastJoinDate;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (fullId == null ? 0 : fullId.hashCode());
        return result;
    }

    /**
     * @param createDate
     *            the createDate to set
     */
    public void setCreateDate(final Long createDate) {
        this.createDate = createDate;
    }

    /**
     * @param fullId
     *            the fullId to set
     */
    public void setFullId(final String fullId) {
        this.fullId = fullId;
    }

    /**
     * @param lastGameDate
     *            the lastGameDate to set
     */
    public void setLastGameDate(final Long lastGameDate) {
        this.lastGameDate = lastGameDate;
    }

    /**
     * @param lastJoinDate
     *            the lastJoinDate to set
     */
    public void setLastJoinDate(final Long lastJoinDate) {
        this.lastJoinDate = lastJoinDate;
    }

    /**
     * @param lastJoinedRankedTeamQueueDate
     *            the lastJoinedRankedTeamQueueDate to set
     */
    public void setLastJoinedRankedTeamQueueDate(final Long lastJoinedRankedTeamQueueDate) {
        this.lastJoinedRankedTeamQueueDate = lastJoinedRankedTeamQueueDate;
    }

    /**
     * @param matchHistory
     *            the matchHistory to set
     */
    public void setMatchHistory(final List<MatchHistorySummary> matchHistory) {
        this.matchHistory = matchHistory;
    }

    /**
     * @param modifyDate
     *            the modifyDate to set
     */
    public void setModifyDate(final Long modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param roster
     *            the roster to set
     */
    public void setRoster(final Roster roster) {
        this.roster = roster;
    }

    /**
     * @param secondLastJoinDate
     *            the secondLastJoinDate to set
     */
    public void setSecondLastJoinDate(final Long secondLastJoinDate) {
        this.secondLastJoinDate = secondLastJoinDate;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * @param tag
     *            the tag to set
     */
    public void setTag(final String tag) {
        this.tag = tag;
    }

    /**
     * @param teamStatDetails
     *            the teamStatDetails to set
     */
    public void setTeamStatDetails(final List<TeamStatDetail> teamStatDetails) {
        this.teamStatDetails = teamStatDetails;
    }

    /**
     * @param thirdLastJoinDate
     *            the thirdLastJoinDate to set
     */
    public void setThirdLastJoinDate(final Long thirdLastJoinDate) {
        this.thirdLastJoinDate = thirdLastJoinDate;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Team [createDate=" + createDate + ", lastGameDate=" + lastGameDate + ", lastJoinDate=" + lastJoinDate + ", lastJoinedRankedTeamQueueDate="
                + lastJoinedRankedTeamQueueDate + ", modifyDate=" + modifyDate + ", secondLastJoinDate=" + secondLastJoinDate + ", thirdLastJoinDate="
                + thirdLastJoinDate + ", fullId=" + fullId + ", name=" + name + ", status=" + status + ", tag=" + tag + ", matchHistory=" + matchHistory
                + ", roster=" + roster + ", teamStatDetails=" + teamStatDetails + "]";
    }
}
