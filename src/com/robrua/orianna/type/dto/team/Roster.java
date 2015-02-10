package com.robrua.orianna.type.dto.team;

import java.util.List;

import com.robrua.orianna.type.dto.OriannaDto;

public class Roster extends OriannaDto {
    private static final long serialVersionUID = 851410403015877412L;
    private List<TeamMemberInfo> memberList;
    private Long ownerId;

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
        if(!(obj instanceof Roster)) {
            return false;
        }
        final Roster other = (Roster)obj;
        if(memberList == null) {
            if(other.memberList != null) {
                return false;
            }
        }
        else if(!memberList.equals(other.memberList)) {
            return false;
        }
        if(ownerId == null) {
            if(other.ownerId != null) {
                return false;
            }
        }
        else if(!ownerId.equals(other.ownerId)) {
            return false;
        }
        return true;
    }

    /**
     * @return the memberList
     */
    public List<TeamMemberInfo> getMemberList() {
        return memberList;
    }

    /**
     * @return the ownerId
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (memberList == null ? 0 : memberList.hashCode());
        result = prime * result + (ownerId == null ? 0 : ownerId.hashCode());
        return result;
    }

    /**
     * @param memberList
     *            the memberList to set
     */
    public void setMemberList(final List<TeamMemberInfo> memberList) {
        this.memberList = memberList;
    }

    /**
     * @param ownerId
     *            the ownerId to set
     */
    public void setOwnerId(final Long ownerId) {
        this.ownerId = ownerId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Roster [memberList=" + memberList + ", ownerId=" + ownerId + "]";
    }
}
