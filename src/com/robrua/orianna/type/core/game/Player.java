package com.robrua.orianna.type.core.game;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.Side;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.MissingDataException;

public class Player extends OriannaObject<com.robrua.orianna.type.dto.game.Player> {
    private static final long serialVersionUID = -1548477059435968471L;
    private Champion champion;
    private Summoner summoner;

    /**
     * @param data
     *            the underlying dto
     */
    public Player(final com.robrua.orianna.type.dto.game.Player data) {
        super(data, com.robrua.orianna.type.dto.game.Player.class);
    }

    /**
     * Champion associated with the player
     *
     * @return champion associated with the player
     */
    public Champion getChampion() {
        if(champion != null) {
            return champion;
        }

        final Integer i = data.getChampionId();
        if(i == null) {
            throw new MissingDataException("Champion ID is null.");
        }

        champion = RiotAPI.getChampionByID(i.longValue());
        return champion;
    }

    /**
     * Champion id associated with player
     *
     * @return champion id associated with player
     */
    public long getChampionID() {
        return super.getInteger(data.getChampionId());
    }

    /**
     * Summoner associated with the player
     *
     * @return summoner associated with the player
     */
    public Summoner getSummoner() {
        if(summoner != null) {
            return summoner;
        }

        final Long l = data.getSummonerId();
        if(l == null) {
            throw new MissingDataException("Summoner ID is null.");
        }

        summoner = RiotAPI.getSummonerByID(l.longValue());
        return summoner;
    }

    /**
     * Summoner id associated with player
     *
     * @return summoner id associated with player
     */
    public long getSummonerID() {
        return super.getLong(data.getSummonerId());
    }

    /**
     * Team associated with the player
     *
     * @return team associated with the player
     */
    public Side getTeam() {
        return Side.forID(super.getInteger(data.getTeamId()));
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Player (" + getSummoner() + " on " + getChampion() + ")";
    }
}
