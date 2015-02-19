package com.robrua.orianna.type.core.matchhistory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.GameMap;
import com.robrua.orianna.type.core.common.GameMode;
import com.robrua.orianna.type.core.common.GameType;
import com.robrua.orianna.type.core.common.PlatformID;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.core.match.Match;
import com.robrua.orianna.type.core.match.Participant;

public class MatchSummary extends OriannaObject<com.robrua.orianna.type.dto.matchhistory.MatchSummary> {
    private static final long serialVersionUID = 4485966256046956113L;
    private List<Participant> participants;

    /**
     * @param data
     *            the underlying dto
     */
    public MatchSummary(final com.robrua.orianna.type.dto.matchhistory.MatchSummary data) {
        super(data, com.robrua.orianna.type.dto.matchhistory.MatchSummary.class);
    }

    /**
     * Match creation time. Designates when the team select lobby is created
     * and/or the match is made through match making, not when the game actually
     * starts.
     *
     * @return match creation time. Designates when the team select lobby is
     *         created and/or the match is made through match making, not when
     *         the game actually starts.
     */
    public Date getCreation() {
        return super.getDate(data.getMatchCreation());
    }

    /**
     * Match duration (in seconds)
     *
     * @return match duration (in seconds)
     */
    public long getDuration() {
        return super.getLong(data.getMatchDuration());
    }

    /**
     * ID of the match
     *
     * @return ID of the match
     */
    public long getID() {
        return super.getLong(data.getMatchId());
    }

    /**
     * Match map
     *
     * @return match map
     */
    public GameMap getMap() {
        return GameMap.forID(super.getInteger(data.getMapId()));
    }

    /**
     * The match (via match API)
     *
     * @return the match (via match API)
     */
    public Match getMatch() {
        return RiotAPI.getMatch(getID());
    }

    /**
     * Match mode
     *
     * @return match mode
     */
    public GameMode getMode() {
        return GameMode.valueOf(super.getString(data.getMatchMode()));
    }

    /**
     * Participant information
     *
     * @return participant information
     */
    public List<Participant> getParticipants() {
        if(participants == null) {
            participants = new ArrayList<>();
            for(int i = 0; i < data.getParticipants().size(); i++) {
                final Participant p = new Participant(data.getParticipants().get(i), data.getParticipantIdentities().get(i));
                participants.add(p);
            }
        }

        return Collections.unmodifiableList(participants);
    }

    /**
     * Platform ID of the match
     *
     * @return platform ID of the match
     */
    public PlatformID getPlatformID() {
        return PlatformID.valueOf(super.getString(data.getPlatformId()));
    }

    /**
     * Match queue type
     *
     * @return match queue type
     */
    public QueueType getQueueType() {
        return QueueType.valueOf(super.getString(data.getQueueType()));
    }

    /**
     * Region where the match was played
     *
     * @return region where the match was played
     */
    public Region getRegion() {
        return Region.valueOf(super.getString(data.getRegion()));
    }

    /**
     * Season match was played
     *
     * @return season match was played
     */
    public Season getSeason() {
        return Season.valueOf(super.getString(data.getSeason()));
    }

    /**
     * Match type
     *
     * @return match type
     */
    public GameType getType() {
        return GameType.valueOf(super.getString(data.getMatchType()));
    }

    /**
     * Match version
     *
     * @return match version
     */
    public String getVersion() {
        return super.getString(data.getMatchVersion());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MatchSummary (" + getID() + ")";
    }
}
