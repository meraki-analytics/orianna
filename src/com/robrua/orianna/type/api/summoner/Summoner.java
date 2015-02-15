package com.robrua.orianna.type.api.summoner;

import java.util.Date;

import com.robrua.orianna.type.api.OriannaObject;

public class Summoner extends OriannaObject<com.robrua.orianna.type.dto.summoner.Summoner> {
    private static final long serialVersionUID = 5207241015938391741L;

    /**
     * @param data
     *            the underlying dto
     */
    public Summoner(final com.robrua.orianna.type.dto.summoner.Summoner data) {
        super(data, com.robrua.orianna.type.dto.summoner.Summoner.class);
    }

    /**
     * Summoner ID
     *
     * @return summoner ID
     */
    public long getID() {
        return super.getLong(data.getId());
    }

    /**
     * Summoner level associated with the summoner
     *
     * @return summoner level associated with the summoner
     */
    public long getLevel() {
        return super.getLong(data.getSummonerLevel());
    }

    /**
     * Summoner name
     *
     * @return Summoner name
     */
    public String getName() {
        return super.getString(data.getName());
    }

    /**
     * ID of the summoner icon associated with the summoner
     *
     * @return ID of the summoner icon associated with the summoner
     */
    public int getProfileIconID() {
        return super.getInteger(data.getProfileIconId());
    }

    /**
     * Date summoner was last modified. The following events will update this
     * timestamp: profile icon change, playing the tutorial or advanced
     * tutorial, finishing a game, summoner name change
     *
     * @return date summoner was last modified. The following events will update
     *         this timestamp: profile icon change, playing the tutorial or
     *         advanced tutorial, finishing a game, summoner name change
     */
    public Date getRevisionDate() {
        return super.getDate(data.getRevisionDate());
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
