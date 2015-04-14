package com.robrua.orianna.type.core.league;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Tier;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.core.team.Team;
import com.robrua.orianna.type.exception.MissingDataException;

public class League extends OriannaObject<com.robrua.orianna.type.dto.league.League> {
    private static final long serialVersionUID = -5641296195275085013L;
    private List<LeagueEntry> entries;
    private Summoner summoner;
    private Team team;

    /**
     * @param data
     *            the underlying dto
     */
    public League(final com.robrua.orianna.type.dto.league.League data) {
        super(data, com.robrua.orianna.type.dto.league.League.class);
    }

    /**
     * The requested league entries sorted by LP
     *
     * @return the requested league entries sorted by LP
     */
    public List<LeagueEntry> getEntries() {
        if(entries == null) {
            entries = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.league.LeagueEntry entry : data.getEntries()) {
                entries.add(new LeagueEntry(entry));
            }
            Collections.sort(entries, new Comparator<LeagueEntry>() {
                @Override
                public int compare(final LeagueEntry o1, final LeagueEntry o2) {
                    return Integer.compare(o2.getLeaguePoints(), o1.getLeaguePoints());
                }
            });
        }

        return Collections.unmodifiableList(entries);
    }

    /**
     * This name is an internal place-holder name only. Display and localization
     * of names in the game client are handled client-side.
     *
     * @return the internal place-holder name
     */
    public String getName() {
        return super.getString(data.getName());
    }

    /**
     * Gets the entry for the relevant participant that is a member of this
     * league.
     *
     * @return the relevant participant entry
     */
    public LeagueEntry getParticipantEntry() {
        if(getEntries().size() == 1) {
            return getEntries().get(0);
        }

        for(final LeagueEntry e : getEntries()) {
            if(e.getID().equals(getParticipantID())) {
                return e;
            }
        }

        return null;
    }

    /**
     * Specifies the relevant participant that is a member of this league (i.e.,
     * a requested summoner ID, a requested team ID, or the ID of a team to
     * which one of the requested summoners belongs). Only present when full
     * league is requested so that participant's entry can be identified. Not
     * present when individual entry is requested.
     *
     * @return the relevant participant ID
     */
    public String getParticipantID() {
        return super.getString(data.getParticipantId());
    }

    /**
     * Gets the participant summoner (if participantID is a summoner ID)
     *
     * @return the participant summoner, or null if participantID was a team ID
     */
    public Summoner getParticipantSummoner() {
        if(summoner != null) {
            return summoner;
        }

        final String s = data.getParticipantId();
        if(s == null) {
            throw new MissingDataException("Participant ID is null.");
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
     * Gets the participant team (if participantID is a team ID)
     *
     * @return the participant team, or null if participantID was a summoner ID
     */
    public Team getParticipantTeam() {
        if(team != null) {
            return team;
        }

        final String s = data.getParticipantId();
        if(s == null) {
            throw new MissingDataException("Participant ID is null.");
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
     * The league's queue type
     *
     * @return the league's queue type
     */
    public QueueType getQueueType() {
        return QueueType.valueOf(super.getString(data.getQueue()));
    }

    /**
     * The league's tier
     *
     * @return the league's tier
     */
    public Tier getTier() {
        return Tier.valueOf(super.getString(data.getTier()));
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "League (" + getName() + ")";
    }
}
