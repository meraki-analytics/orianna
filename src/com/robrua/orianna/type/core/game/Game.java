package com.robrua.orianna.type.core.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.GameMap;
import com.robrua.orianna.type.core.common.GameMode;
import com.robrua.orianna.type.core.common.GameType;
import com.robrua.orianna.type.core.common.Side;
import com.robrua.orianna.type.core.common.SubType;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.staticdata.SummonerSpell;
import com.robrua.orianna.type.exception.MissingDataException;

public class Game extends OriannaObject<com.robrua.orianna.type.dto.game.Game> {
    private static final long serialVersionUID = 2934777978027724335L;
    private Champion champion;
    private List<Player> fellowPlayers;
    private SummonerSpell spell1, spell2;
    private RawStats stats;

    /**
     * @param data
     *            the underlying dto
     */
    public Game(final com.robrua.orianna.type.dto.game.Game data) {
        super(data, com.robrua.orianna.type.dto.game.Game.class);
    }

    /**
     * Champion associated with game
     *
     * @return champion associated with game
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
     * Champion ID associated with game
     *
     * @return champion ID associated with game
     */
    public long getChampionID() {
        return super.getInteger(data.getChampionId());
    }

    /**
     * Date that end game data was recorded
     *
     * @return date that end game data was recorded
     */
    public Date getCreateDate() {
        return super.getDate(data.getCreateDate());
    }

    /**
     * Other players associated with the game
     *
     * @return other players associated with the game
     */
    public List<Player> getFellowPlayers() {
        if(fellowPlayers == null) {
            if(data.getFellowPlayers() == null) {
                return Collections.emptyList();
            }

            fellowPlayers = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.game.Player player : data.getFellowPlayers()) {
                fellowPlayers.add(new Player(player));
            }
        }

        return Collections.unmodifiableList(fellowPlayers);
    }

    /**
     * Game ID
     *
     * @return game ID
     */
    public long getID() {
        return super.getLong(data.getGameId());
    }

    /**
     * Invalid flag
     *
     * @return invalid flag
     */
    public boolean getInvalid() {
        return super.getBoolean(data.getInvalid());
    }

    /**
     * IP Earned
     *
     * @return IP Earned
     */
    public int getIPEarned() {
        return super.getInteger(data.getIpEarned());
    }

    /**
     * Level
     *
     * @return level
     */
    public int getLevel() {
        return super.getInteger(data.getLevel());
    }

    /**
     * Map
     *
     * @return map
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
     * Statistics associated with the game for this summoner
     *
     * @return statistics associated with the game for this summoner
     */
    public RawStats getStats() {
        if(stats == null) {
            stats = new RawStats(data.getStats());
        }

        return stats;
    }

    /**
     * Game sub-type
     *
     * @return game sub-type
     */
    public SubType getSubType() {
        return SubType.valueOf(super.getString(data.getSubType()));
    }

    /**
     * First summoner spell
     *
     * @return first summoner spell
     */
    public SummonerSpell getSummonerSpell1() {
        if(spell1 != null) {
            return spell1;
        }

        final Integer i = data.getSpell1();
        if(i == null) {
            throw new MissingDataException("Summoner Spell #1 ID is null.");
        }

        spell1 = RiotAPI.getSummonerSpell(i.longValue());
        return spell1;
    }

    /**
     * ID of first summoner spell
     *
     * @return ID of first summoner spell
     */
    public long getSummonerSpell1ID() {
        return super.getInteger(data.getSpell1());
    }

    /**
     * First summoner spell
     *
     * @return first summoner spell
     */
    public SummonerSpell getSummonerSpell2() {
        if(spell2 != null) {
            return spell2;
        }

        final Integer i = data.getSpell2();
        if(i == null) {
            throw new MissingDataException("Summoner Spell #2 ID is null.");
        }

        spell2 = RiotAPI.getSummonerSpell(i.longValue());
        return spell2;
    }

    /**
     * ID of second summoner spell
     *
     * @return ID of second summoner spell
     */
    public long getSummonerSpell2ID() {
        return super.getInteger(data.getSpell2());
    }

    /**
     * Team associated with the game
     *
     * @return team associated with the game
     */
    public Side getTeam() {
        return Side.forID(super.getInteger(data.getTeamId()));
    }

    /**
     * Game type
     *
     * @return game type
     */
    public GameType getType() {
        return GameType.valueOf(super.getString(data.getGameType()));
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Game (" + getChampion() + " @ " + getCreateDate() + ")";
    }
}
