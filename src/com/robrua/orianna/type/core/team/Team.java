package com.robrua.orianna.type.core.team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.league.League;

public class Team extends OriannaObject<com.robrua.orianna.type.dto.team.Team> {
    private static final long serialVersionUID = -6729037169787253289L;
    private List<MatchHistorySummary> matchHistory;
    private Roster roster;
    private List<TeamStatDetail> statDetails;

    /**
     * @param data
     *            the underlying dto
     */
    public Team(final com.robrua.orianna.type.dto.team.Team data) {
        super(data, com.robrua.orianna.type.dto.team.Team.class);
    }

    /**
     * Date that team was created
     *
     * @return date that team was created
     */
    public Date getCreateDate() {
        return super.getDate(data.getCreateDate());
    }

    /**
     * The team ID
     *
     * @return the team ID
     */
    public String getID() {
        return super.getString(data.getFullId());
    }

    /**
     * Date that last game played by team ended
     *
     * @return date that last game played by team ended
     */
    public Date getLastGameDate() {
        return super.getDate(data.getLastGameDate());
    }

    /**
     * Date that last member joined
     *
     * @return date that last member joined
     */
    public Date getLastJoinDate() {
        return super.getDate(data.getLastJoinDate());
    }

    /**
     * Date that team last joined the ranked team queue
     *
     * @return date that team last joined the ranked team queue
     */
    public Date getLastQueueDate() {
        return super.getDate(data.getLastJoinedRankedTeamQueueDate());
    }

    /**
     * The team's league entries
     *
     * @return the team's league entries
     */
    public List<League> getLeagueEntries() {
        return RiotAPI.getLeagueEntriesByTeamID(getID());
    }

    /**
     * The team's leagues
     *
     * @return the team's leagues
     */
    public List<League> getLeagues() {
        return RiotAPI.getLeaguesByTeamID(getID());
    }

    /**
     * The match history
     *
     * @return the match history
     */
    public List<MatchHistorySummary> getMatchHistory() {
        if(matchHistory == null) {
            matchHistory = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.team.MatchHistorySummary match : data.getMatchHistory()) {
                matchHistory.add(new MatchHistorySummary(match));
            }
        }

        return Collections.unmodifiableList(matchHistory);
    }

    /**
     * Date that team was last modified
     *
     * @return date that team was last modified
     */
    public Date getModifyDate() {
        return super.getDate(data.getModifyDate());
    }

    /**
     * The team name
     *
     * @return the team name
     */
    public String getName() {
        return super.getString(data.getName());
    }

    /**
     * The roster
     *
     * @return the roster
     */
    public Roster getRoster() {
        if(roster == null) {
            roster = new Roster(data.getRoster());
        }

        return roster;
    }

    /**
     * Date that second to last member joined
     *
     * @return date that second to last member joined
     */
    public Date getSecondLastJoinDate() {
        return super.getDate(data.getSecondLastJoinDate());
    }

    /**
     * The team stat details
     *
     * @return the team stat details
     */
    public List<TeamStatDetail> getStatDetails() {
        if(statDetails == null) {
            statDetails = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.team.TeamStatDetail d : data.getTeamStatDetails()) {
                statDetails.add(new TeamStatDetail(d));
            }
        }

        return Collections.unmodifiableList(statDetails);
    }

    /**
     * The status
     *
     * @return the status
     */
    public String getStatus() {
        return super.getString(data.getStatus());
    }

    /**
     * The team tag
     *
     * @return the team tag
     */
    public String getTag() {
        return super.getString(data.getTag());
    }

    /**
     * Date that third to last member joined
     *
     * @return date that third to last member joined
     */
    public Date getThirdLastJoinDate() {
        return super.getDate(data.getThirdLastJoinDate());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getName();
    }
}
