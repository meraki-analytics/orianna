package com.robrua.orianna.type.core.champion;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.exception.MissingDataException;

public class ChampionStatus extends OriannaObject<com.robrua.orianna.type.dto.champion.Champion> {
    private static final long serialVersionUID = 7054230582822888243L;
    private Champion champion;

    /**
     * @param data
     *            the underlying dto
     */
    public ChampionStatus(final com.robrua.orianna.type.dto.champion.Champion data) {
        super(data, com.robrua.orianna.type.dto.champion.Champion.class);
    }

    /**
     * Indicates if the champion is active
     *
     * @return whether the champion is active
     */
    public boolean getActive() {
        return super.getBoolean(data.getActive());
    }

    /**
     * Bot enabled flag (for custom games)
     *
     * @return whether the champion is enabled for bots in Customs
     */
    public boolean getBotEnabled() {
        return super.getBoolean(data.getBotEnabled());
    }

    /**
     * Bot Match Made enabled flag (for Co-op vs. AI games)
     *
     * @return whether the champion is enabled for bots in Co-op vs. AI
     */
    public boolean getBotMmEnabled() {
        return super.getBoolean(data.getBotMmEnabled());
    }

    /**
     * Gets the champion this status applies to
     *
     * @return the champion this status applies to
     */
    public Champion getChampion() {
        if(champion != null) {
            return champion;
        }

        final Long l = data.getId();
        if(l == null) {
            throw new MissingDataException("Champion ID is null.");
        }

        champion = RiotAPI.getChampionByID(l.longValue());
        return champion;
    }

    /**
     * Gets the ID of the champion this status applies to
     *
     * @return the ID of the champion this status applies to
     */
    public long getChampionID() {
        return super.getLong(data.getId());
    }

    /**
     * Indicates if the champion is free to play. Free to play champions are
     * rotated periodically.
     *
     * @return whether the champion is free to play
     */
    public boolean getFreeToPlay() {
        return super.getBoolean(data.getFreeToPlay());
    }

    /**
     * Ranked play enabled flag.
     *
     * @return whether the champion is enabled for Ranked
     */
    public boolean getRankedPlayEnabled() {
        return super.getBoolean(data.getRankedPlayEnabled());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ChampionStatus (" + getChampion() + ")";
    }
}
