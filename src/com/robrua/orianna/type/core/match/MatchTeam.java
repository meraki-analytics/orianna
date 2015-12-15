package com.robrua.orianna.type.core.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.Side;

public class MatchTeam extends OriannaObject<com.robrua.orianna.type.dto.match.Team> {
    private static final long serialVersionUID = 4826210355495078925L;
    private List<BannedChampion> bans;

    /**
     * @param data
     *            the underlying dto
     */
    public MatchTeam(final com.robrua.orianna.type.dto.match.Team data) {
        super(data, com.robrua.orianna.type.dto.match.Team.class);
    }

    /**
     * If game was draft mode, contains banned champion data
     *
     * @return banned champion data
     */
    public List<BannedChampion> getBans() {
        if(bans == null) {
            bans = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.match.BannedChampion ban : data.getBans()) {
                bans.add(new BannedChampion(ban));
            }
        }

        return Collections.unmodifiableList(bans);
    }

    /**
     * Number of times the team killed baron
     *
     * @return number of times the team killed baron
     */
    public int getBaronKills() {
        return super.getInteger(data.getBaronKills());
    }

    /**
     * If game was a dominion game, specifies the points the team had at game
     * end
     *
     * @return the points the team had at game end
     */
    public long getDominionVictoryScore() {
        return super.getLong(data.getDominionVictoryScore());
    }

    /**
     * Number of times the team killed dragon
     *
     * @return number of times the team killed dragon
     */
    public int getDragonKills() {
        return super.getInteger(data.getDragonKills());
    }

    /**
     * Flag indicating whether or not the team got the first baron kill
     *
     * @return whether or not the team got the first baron kill
     */
    public boolean getFirstBaron() {
        return super.getBoolean(data.getFirstBaron());
    }

    /**
     * Flag indicating whether or not the team got first blood
     *
     * @return whether or not the team got first blood
     */
    public boolean getFirstBlood() {
        return super.getBoolean(data.getFirstBlood());
    }

    /**
     * Flag indicating whether or not the team got the first dragon kill
     *
     * @return whether or not the team got the first dragon kill
     */
    public boolean getFirstDragon() {
        return super.getBoolean(data.getFirstDragon());
    }

    /**
     * Flag indicating whether or not the team destroyed the first inhibitor
     *
     * @return whether or not the team destroyed the first inhibitor
     */
    public boolean getFirstInhibitor() {
        return super.getBoolean(data.getFirstInhibitor());
    }

    /**
     * Flag indicating whether or not the team killed the first rift herald
     *
     * @return whether or not the team killed the first rift herald
     */
    public boolean getFirstRiftHerald() {
        return super.getBoolean(data.getFirstRiftHerald());
    }

    /**
     * Flag indicating whether or not the team destroyed the first tower
     *
     * @return whether or not the team destroyed the first tower
     */
    public boolean getFirstTower() {
        return super.getBoolean(data.getFirstTower());
    }

    /**
     * Number of inhibitors the team destroyed
     *
     * @return number of inhibitors the team destroyed
     */
    public int getInhibitorKills() {
        return super.getInteger(data.getInhibitorKills());
    }

    /**
     * Number of rift heralds the team killed
     *
     * @return number of rift heralds the team killed
     */
    public int getRiftHeraldKills() {
        return super.getInteger(data.getRiftHeraldKills());
    }

    /**
     * The side that this team played
     *
     * @return the side that this team played
     */
    public Side getSide() {
        return Side.forID(super.getInteger(data.getTeamId()));
    }

    /**
     * Number of towers the team destroyed
     *
     * @return number of towers the team destroyed
     */
    public int getTowerKills() {
        return super.getInteger(data.getTowerKills());
    }

    /**
     * Number of times the team killed vilemaw
     *
     * @return number of times the team killed vilemaw
     */
    public int getVilemawKills() {
        return super.getInteger(data.getVilemawKills());
    }

    /**
     * Flag indicating whether or not the team won
     *
     * @return whether or not the team won
     */
    public boolean getWinner() {
        return super.getBoolean(data.getWinner());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MatchTeam (" + getSide() + ")";
    }
}
