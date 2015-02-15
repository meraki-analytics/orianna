package com.robrua.orianna.type.core.team;

import java.util.Date;

import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.GameMap;
import com.robrua.orianna.type.core.common.GameMode;

public class MatchHistorySummary extends OriannaObject<com.robrua.orianna.type.dto.team.MatchHistorySummary> {
    private static final long serialVersionUID = 6501411416539571812L;

    /**
     * @param data
     *            the underlying dto
     */
    public MatchHistorySummary(final com.robrua.orianna.type.dto.team.MatchHistorySummary data) {
        super(data, com.robrua.orianna.type.dto.team.MatchHistorySummary.class);
    }

    /**
     * Assist count
     *
     * @return assist count
     */
    public int getAssists() {
        return super.getInteger(data.getAssists());
    }

    /**
     * Date that match was completed
     *
     * @return date that match was completed
     */
    public Date getDate() {
        return super.getDate(data.getDate());
    }

    /**
     * Death count
     *
     * @return death count
     */
    public int getDeaths() {
        return super.getInteger(data.getDeaths());
    }

    /**
     * The game ID
     *
     * @return the game ID
     */
    public long getGameID() {
        return super.getLong(data.getGameId());
    }

    /**
     * Whether the match is invalid
     *
     * @return whether the match is invalid
     */
    public boolean getInvalid() {
        return super.getBoolean(data.getInvalid());
    }

    /**
     * Kill count
     *
     * @return kill count
     */
    public int getKills() {
        return super.getInteger(data.getKills());
    }

    /**
     * The map
     *
     * @return the map
     */
    public GameMap getMap() {
        return GameMap.forID(super.getInteger(data.getMapId()));
    }

    /**
     * Game mode
     *
     * @return game mode
     */
    public GameMode getMode() {
        return GameMode.valueOf(super.getString(data.getGameMode()));
    }

    /**
     * Opposing team kills
     *
     * @return opposing team kills
     */
    public int getOpposingTeamKills() {
        return super.getInteger(data.getOpposingTeamKills());
    }

    /**
     * Opposing team name
     *
     * @return opposing team name
     */
    public String getOpposingTeamName() {
        return super.getString(data.getOpposingTeamName());
    }

    /**
     * Whether the game was won
     *
     * @return whether the game was won
     */
    public boolean getWin() {
        return super.getBoolean(data.getWin());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MatchHistorySummary (vs " + getOpposingTeamName() + ")";
    }
}
