package com.robrua.orianna.type.core.league;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.core.team.Team;
import com.robrua.orianna.type.exception.MissingDataException;

public class LeagueEntry extends OriannaObject<com.robrua.orianna.type.dto.league.LeagueEntry> {
    private static final long serialVersionUID = -3761278269953249105L;
    private MiniSeries miniSeries;
    private Summoner summoner;
    private Team team;

    /**
     * @param data
     *            the underlying dto
     */
    public LeagueEntry(final com.robrua.orianna.type.dto.league.LeagueEntry data) {
        super(data, com.robrua.orianna.type.dto.league.LeagueEntry.class);
    }

    /**
     * The league division of the participant
     *
     * @return the league division of the participant
     */
    public String getDivision() {
        return super.getString(data.getDivision());
    }

    /**
     * Specifies if the participant is fresh blood
     *
     * @return whether the participant is fresh blood
     */
    public boolean getFreshBlood() {
        return super.getBoolean(data.getIsFreshBlood());
    }

    /**
     * Specifies if the participant is on a hot streak
     *
     * @return whether participant is on a hot streak
     */
    public boolean getHotStreak() {
        return super.getBoolean(data.getIsHotStreak());
    }

    /**
     * The ID of the participant (i.e., summoner or team) represented by this
     * entry
     *
     * @return the ID of the participant (i.e., summoner or team) represented by
     *         this entry
     */
    public String getID() {
        return super.getString(data.getPlayerOrTeamId());
    }

    /**
     * Specifies if the participant is inactive
     *
     * @return whether the participant is inactive
     */
    public boolean getInactive() {
        return super.getBoolean(data.getIsInactive());
    }

    /**
     * The league points of the participant
     *
     * @return the league points of the participant
     */
    public int getLeaguePoints() {
        return super.getInteger(data.getLeaguePoints());
    }

    /**
     * The number of losses for the participant
     *
     * @return the number of losses for the participant
     */
    public int getLosses() {
        return super.getInteger(data.getLosses());
    }

    /**
     * Mini series data for the participant. Only present if the participant is
     * currently in a mini series.
     *
     * @return mini series data for the participant if the participant is
     *         currently in one.
     */
    public MiniSeries getMiniSeries() {
        if(miniSeries == null) {
            miniSeries = new MiniSeries(data.getMiniSeries());
        }

        return miniSeries;
    }

    /**
     * The name of the the participant (i.e., summoner or team) represented by
     * this entry
     *
     * @return the name of the the participant (i.e., summoner or team)
     *         represented by this entry
     */
    public String getName() {
        return super.getString(data.getPlayerOrTeamName());
    }

    /**
     * The summoner, if the ID is a summoner ID
     *
     * @return the summoner, or null if the ID is a team ID
     */
    public Summoner getSummoner() {
        if(summoner != null) {
            return summoner;
        }

        final String s = data.getPlayerOrTeamId();
        if(s == null) {
            throw new MissingDataException("Player/Team ID is null.");
        }

        try {
            final long summonerID = Long.parseLong(s);
            summoner = RiotAPI.getSummonerByID(summonerID);
            return summoner;
        }
        catch(final NumberFormatException e) {
            return null;
        }
    }

    /**
     * The team, if the ID is a team ID
     *
     * @return the team, or null if the ID is a summoner ID
     */
    public Team getTeam() {
        if(team != null) {
            return team;
        }

        final String s = data.getPlayerOrTeamId();
        if(s == null) {
            throw new MissingDataException("Player/Team ID is null.");
        }

        try {
            Long.parseLong(s);
            // If the string fits into a long it's a summoner ID, not a team ID
            return null;
        }
        catch(final NumberFormatException e) {
            team = RiotAPI.getTeam(s);
            return team;
        }
    }

    /**
     * Specifies if the participant is a veteran
     *
     * @return whether the participant is a veteran
     */
    public boolean getVeteran() {
        return super.getBoolean(data.getIsVeteran());
    }

    /**
     * The number of wins for the participant
     *
     * @return the number of wins for the participant
     */
    public int getWins() {
        return super.getInteger(data.getWins());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LeagueEntry (" + getName() + " " + getLeaguePoints() + "LP)";
    }
}
