package com.robrua.orianna.type.core.team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.MissingDataException;

public class Roster extends OriannaObject<com.robrua.orianna.type.dto.team.Roster> {
    private static final long serialVersionUID = 3320561622192076811L;
    private List<TeamMemberInfo> memberList;
    private Summoner owner;

    /**
     * @param data
     *            the underlying dto
     */
    public Roster(final com.robrua.orianna.type.dto.team.Roster data) {
        super(data, com.robrua.orianna.type.dto.team.Roster.class);
    }

    /**
     * The member list
     *
     * @return the member list
     */
    public List<TeamMemberInfo> getMemberList() {
        if(memberList == null) {
            memberList = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.team.TeamMemberInfo m : data.getMemberList()) {
                memberList.add(new TeamMemberInfo(m));
            }
        }

        return Collections.unmodifiableList(memberList);
    }

    /**
     * The team's owner
     *
     * @return the team's owner
     */
    public Summoner getOwner() {
        if(owner != null) {
            return owner;
        }

        final Long l = data.getOwnerId();
        if(l == null) {
            throw new MissingDataException("Owner ID is null.");
        }

        owner = RiotAPI.getSummonerByID(l.longValue());
        return owner;
    }

    /**
     * The summoner ID of the owner
     *
     * @return the summoner ID of the owner
     */
    public long getOwnerID() {
        return super.getLong(data.getOwnerId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Roster (" + getMemberList() + ")";
    }
}
