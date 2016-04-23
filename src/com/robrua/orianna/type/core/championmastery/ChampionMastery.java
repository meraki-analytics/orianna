package com.robrua.orianna.type.core.championmastery;

import java.util.Date;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.MissingDataException;

public class ChampionMastery extends OriannaObject<com.robrua.orianna.type.dto.championmastery.ChampionMastery> {
    private static final long serialVersionUID = 3565349746905192000L;
    private Champion champion;
    private Summoner player;

    /**
     * @param data
     *            the underlying dto
     */
    public ChampionMastery(final com.robrua.orianna.type.dto.championmastery.ChampionMastery data) {
        super(data, com.robrua.orianna.type.dto.championmastery.ChampionMastery.class);
    }

    /**
     * Gets the champion this mastery score is for
     *
     * @return the champion this mastery score is for
     */
    public Champion getChampion() {
        if(champion != null) {
            return champion;
        }

        final Long l = data.getChampionId();
        if(l == null) {
            throw new MissingDataException("Champion ID is null.");
        }

        champion = RiotAPI.getChampionByID(l.longValue());
        return champion;
    }

    /**
     * The ID of the champion
     *
     * @return the ID of the champion
     */
    public long getChampionID() {
        return super.getLong(data.getChampionId());
    }

    /**
     * The mastery level for the champion
     *
     * @return the mastery level for the champion
     */
    public int getChampionLevel() {
        return super.getInteger(data.getChampionLevel());
    }

    /**
     * The mastery points for the champion
     *
     * @return the mastery points for the champion
     */
    public long getChampionPoints() {
        return super.getInteger(data.getChampionPoints());
    }

    /**
     * The number of mastery points since the last mastery level
     *
     * @return the number of mastery points since the last mastery level
     */
    public long getChampionPointsSinceLastLevel() {
        return super.getLong(data.getChampionPointsSinceLastLevel());
    }

    /**
     * The number of mastery points since the last mastery level
     *
     * @return the number of mastery points since the last mastery level
     */
    public long getChampionPointsUntilNextLevel() {
        return super.getLong(data.getChampionPointsUntilNextLevel());
    }

    /**
     * Whether a hextech chest has been granted for this champion
     *
     * @return whether a hextech chest has been granted for this champion
     */
    public boolean getChestGranted() {
        return super.getBoolean(data.getChestGranted());
    }

    /**
     * The highest grade for this champion this season
     *
     * @return the highest grade for this champion this season
     */
    public String getHighestGrade() {
        return super.getString(data.getHighestGrade());
    }

    /**
     * The last time this champion was played by the summoner
     *
     * @return the last time this champion was played by the summoner
     */
    public Date getLastPlayTime() {
        return super.getDate(data.getLastPlayTime());
    }

    /**
     * The summoner this champion mastery is for
     *
     * @return the summoner this champion mastery is for
     */
    public Summoner getPlayer() {
        if(player != null) {
            return player;
        }

        final Long l = data.getPlayerId();
        if(l == null) {
            throw new MissingDataException("Player ID is null.");
        }

        player = RiotAPI.getSummonerByID(l);
        return player;
    }

    @Override
    public String toString() {
        return getChampion() + " (" + getChampionLevel() + ")";
    }
}
