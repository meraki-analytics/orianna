package com.robrua.orianna.type.core.team;

import java.util.Date;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.MissingDataException;

public class TeamMemberInfo extends OriannaObject<com.robrua.orianna.type.dto.team.TeamMemberInfo> {
    private static final long serialVersionUID = -4891040362567811255L;
    private Summoner summoner;

    /**
     * @param data
     *            the underlying dto
     */
    public TeamMemberInfo(final com.robrua.orianna.type.dto.team.TeamMemberInfo data) {
        super(data, com.robrua.orianna.type.dto.team.TeamMemberInfo.class);
    }

    /**
     * Date that team member was invited to team
     *
     * @return date that team member was invited to team
     */
    public Date getInviteDate() {
        return super.getDate(data.getInviteDate());
    }

    /**
     * Date that team member joined team
     *
     * @return date that team member joined team
     */
    public Date getJoinDate() {
        return super.getDate(data.getJoinDate());
    }

    /**
     * The status of the player
     *
     * @return the status of the player
     */
    public String getStatus() {
        return super.getString(data.getStatus());
    }

    /**
     * The summoner
     *
     * @return the summoner
     */
    public Summoner getSummoner() {
        if(summoner != null) {
            return summoner;
        }

        final Long l = data.getPlayerId();
        if(l == null) {
            throw new MissingDataException("Player ID is null.");
        }

        summoner = RiotAPI.getSummonerByID(l.longValue());
        return summoner;
    }

    /**
     * The summoner's ID
     *
     * @return the summoner's ID
     */
    public long getSummonerID() {
        return super.getLong(data.getPlayerId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TeamMemberInfo (" + getSummoner() + ")";
    }
}
