package com.robrua.orianna.type.core.matchlist;

import java.util.Date;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.Lane;
import com.robrua.orianna.type.core.common.PlatformID;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Role;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.core.match.Match;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.exception.MissingDataException;

public class MatchReference extends OriannaObject<com.robrua.orianna.type.dto.matchlist.MatchReference> {
    private static final long serialVersionUID = 640038236526052116L;

    private Champion champion;

    /**
     * @param data
     *            the underlying dto
     */
    public MatchReference(final com.robrua.orianna.type.dto.matchlist.MatchReference data) {
        super(data, com.robrua.orianna.type.dto.matchlist.MatchReference.class);
    }

    /**
     * The focus player's champion
     *
     * @return the focus player's champion
     */
    public Champion getChampion() {
        if(champion != null) {
            return champion;
        }

        final Long l = data.getChampion();
        if(l == null) {
            throw new MissingDataException("Champion ID is null.");
        }

        champion = RiotAPI.getChampionByID(l);
        return champion;
    }

    /**
     * Champion ID
     *
     * @return champion ID
     */
    public long getChampionID() {
        return super.getLong(data.getChampion());
    }

    /**
     * The match ID
     *
     * @return match ID
     */
    public long getID() {
        return super.getLong(data.getMatchId());
    }

    /**
     * The lane the focus player played in
     *
     * @return lane the focus player played in
     */
    public Lane getLane() {
        return Lane.valueOf(super.getString(data.getLane()));
    }

    /**
     * The match (via match API)
     *
     * @return the match (via match API)
     */
    public Match getMatch() {
        return RiotAPI.getMatchByReference(this);
    }

    /**
     * The match (via match API)
     *
     * @param includeTimeline
     *            whether to include timeline data in the returned match
     * @return the match (via match API)
     */
    public Match getMatch(final boolean includeTimeline) {
        return RiotAPI.getMatchByReference(this, includeTimeline);
    }

    /**
     * The platform ID for the match
     *
     * @return platform ID for the match
     */
    public PlatformID getPlatformID() {
        return PlatformID.valueOf(super.getString(data.getPlatformId()));
    }

    /**
     * The queue type for the match
     *
     * @return queue type for the match
     */
    public QueueType getQueueType() {
        return QueueType.valueOf(super.getString(data.getQueue()));
    }

    /**
     * The focus player's role
     *
     * @return focus player's role
     */
    public Role getRole() {
        return Role.valueOf(super.getString(data.getRole()));
    }

    /**
     * The season for the match
     *
     * @return season for the match
     */
    public Season getSeason() {
        return Season.valueOf(super.getString(data.getSeason()));
    }

    /**
     * The timestamp of the match
     *
     * @return timestamp of the match
     */
    public Date getTimestamp() {
        return super.getDate(data.getTimestamp());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MatchReference (" + getID() + ")";
    }
}
