package com.robrua.orianna.type.team;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.robrua.orianna.type.summoner.Summoner;

public class TeamMemberInformation implements Serializable {
    private static final long serialVersionUID = 1653939607333249391L;
    public final LocalDateTime inviteDate, joinDate;
    public final Summoner player;
    public final String status;

    public TeamMemberInformation(final LocalDateTime inviteDate, final LocalDateTime joinDate, final Summoner player, final String status) {
        this.inviteDate = inviteDate;
        this.joinDate = joinDate;
        this.player = player;
        this.status = status;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof TeamMemberInformation)) {
            return false;
        }
        final TeamMemberInformation other = (TeamMemberInformation)obj;
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
        if(player == null) {
            if(other.player != null) {
                return false;
            }
        }
        else if(!player.equals(other.player)) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (inviteDate == null ? 0 : inviteDate.hashCode());
        result = prime * result + (joinDate == null ? 0 : joinDate.hashCode());
        result = prime * result + (player == null ? 0 : player.hashCode());
        result = prime * result + (status == null ? 0 : status.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return player.toString();
    }
}
