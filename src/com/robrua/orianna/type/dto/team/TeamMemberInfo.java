package com.robrua.orianna.type.dto.team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "teammemberinfo")
public class TeamMemberInfo extends OriannaDto {
    private static final long serialVersionUID = -8383019016544221742L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;
    private Long inviteDate, joinDate, playerId;

    private String status;

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
        if(!(obj instanceof TeamMemberInfo)) {
            return false;
        }
        final TeamMemberInfo other = (TeamMemberInfo)obj;
        if(inviteDate == null) {
            if(other.inviteDate != null) {
                return false;
            }
        }
        else if(!inviteDate.equals(other.inviteDate)) {
            return false;
        }
        if(joinDate == null) {
            if(other.joinDate != null) {
                return false;
            }
        }
        else if(!joinDate.equals(other.joinDate)) {
            return false;
        }
        if(playerId == null) {
            if(other.playerId != null) {
                return false;
            }
        }
        else if(!playerId.equals(other.playerId)) {
            return false;
        }
        if(status == null) {
            if(other.status != null) {
                return false;
            }
        }
        else if(!status.equals(other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the inviteDate
     */
    public Long getInviteDate() {
        return inviteDate;
    }

    /**
     * @return the joinDate
     */
    public Long getJoinDate() {
        return joinDate;
    }

    /**
     * @return the playerId
     */
    public Long getPlayerId() {
        return playerId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (inviteDate == null ? 0 : inviteDate.hashCode());
        result = prime * result + (joinDate == null ? 0 : joinDate.hashCode());
        result = prime * result + (playerId == null ? 0 : playerId.hashCode());
        result = prime * result + (status == null ? 0 : status.hashCode());
        return result;
    }

    /**
     * @param inviteDate
     *            the inviteDate to set
     */
    public void setInviteDate(final Long inviteDate) {
        this.inviteDate = inviteDate;
    }

    /**
     * @param joinDate
     *            the joinDate to set
     */
    public void setJoinDate(final Long joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * @param playerId
     *            the playerId to set
     */
    public void setPlayerId(final Long playerId) {
        this.playerId = playerId;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TeamMemberInfo [inviteDate=" + inviteDate + ", joinDate=" + joinDate + ", playerId=" + playerId + ", status=" + status + "]";
    }
}
