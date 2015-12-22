package com.robrua.orianna.type.core.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.Side;
import com.robrua.orianna.type.core.common.Tier;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.staticdata.SummonerSpell;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.dto.match.CombinedParticipant;
import com.robrua.orianna.type.dto.match.ParticipantIdentity;
import com.robrua.orianna.type.dto.match.Player;
import com.robrua.orianna.type.exception.MissingDataException;

public class Participant extends OriannaObject<CombinedParticipant> {
    private static final long serialVersionUID = -1560511644551713929L;
    private Champion champion;
    private List<ParticipantMastery> masteries;
    private List<ParticipantRune> runes;
    private SummonerSpell spell1, spell2;
    private ParticipantStats stats;
    private Summoner summoner;
    private ParticipantTimeline timeline;

    /**
     * @param participantData
     *            the underlying participant dto
     * @param identityData
     *            the underlying identity dto
     */
    public Participant(final com.robrua.orianna.type.dto.match.Participant participantData,
            final com.robrua.orianna.type.dto.match.ParticipantIdentity identityData) {
        super(new CombinedParticipant(participantData, identityData), com.robrua.orianna.type.dto.match.CombinedParticipant.class);
    }

    /**
     * The participant's champion
     *
     * @return the participant's champion
     */
    public Champion getChampion() {
        if(champion != null) {
            return champion;
        }

        final Integer i = data.getParticipant().getChampionId();
        if(i == null) {
            throw new MissingDataException("Champion ID is null.");
        }

        champion = RiotAPI.getChampionByID(i.longValue());
        return champion;
    }

    /**
     * Champion ID
     *
     * @return champion ID
     */
    public long getChampionID() {
        return super.getInteger(data.getParticipant().getChampionId());
    }

    /**
     * List of mastery information
     *
     * @return list of mastery information
     */
    public List<ParticipantMastery> getMasteries() {
        if(masteries == null) {
            masteries = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.match.Mastery mastery : data.getParticipant().getMasteries()) {
                masteries.add(new ParticipantMastery(mastery));
            }
        }

        return Collections.unmodifiableList(masteries);
    }

    /**
     * Match history URI for this participant
     *
     * @return match history URI for this participant
     */
    public String getMatchHistoryURI() {
        return super.getString(data.getIdentity().getPlayer().getMatchHistoryUri());
    }

    /**
     * The ID of this participant for timeline reference
     *
     * @return the ID of this participant for timeline reference
     */
    public int getParticipantID() {
        return super.getInteger(data.getParticipant().getParticipantId());
    }

    /**
     * Highest ranked tier achieved for the previous season. Used to display
     * border in game loading screen.
     *
     * @return highest ranked tier achieved for the previous season. Used to
     *         display border in game loading screen.
     */
    public Tier getPreviousSeasonTier() {
        try {
            return Tier.valueOf(super.getString(data.getParticipant().getHighestAchievedSeasonTier()));
        }
        catch(final IllegalArgumentException e) {
            return Tier.UNRANKED;
        }
    }

    /**
     * Profile icon ID
     *
     * @return profile icon ID
     */
    public int getProfileIconID() {
        return super.getInteger(data.getIdentity().getPlayer().getProfileIcon());
    }

    /**
     * List of rune information
     *
     * @return list of rune information
     */
    public List<ParticipantRune> getRunes() {
        if(runes == null) {
            runes = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.match.Rune rune : data.getParticipant().getRunes()) {
                runes.add(new ParticipantRune(rune));
            }
        }

        return Collections.unmodifiableList(runes);
    }

    /**
     * Participant stats
     *
     * @return participant stats
     */
    public ParticipantStats getStats() {
        if(stats == null) {
            stats = new ParticipantStats(data.getParticipant().getStats());
        }

        return stats;
    }

    /**
     * The summoner this participant represents
     *
     * @return the summoner this participant represents
     */
    public Summoner getSummoner() {
        if(summoner != null) {
            return summoner;
        }

        final ParticipantIdentity identity = data.getIdentity();
        if(identity == null) {
            return null;
        }

        final Player player = identity.getPlayer();
        if(player == null) {
            return null;
        }

        final Long l = player.getSummonerId();
        if(l == null) {
            throw new MissingDataException("Summoner ID is null.");
        }

        summoner = RiotAPI.getSummonerByID(l.longValue());
        return summoner;
    }

    /**
     * The ID of the summoner this participant represents
     *
     * @return the ID of the summoner this participant represents
     */
    public long getSummonerID() {
        if(data.getIdentity() == null || data.getIdentity().getPlayer() == null) {
            return -1;
        }
        return super.getLong(data.getIdentity().getPlayer().getSummonerId());
    }

    /**
     * The name of the summoner this participant represents
     *
     * @return the name of the summoner this participant represents
     */
    public String getSummonerName() {
        if(data.getIdentity() == null || data.getIdentity().getPlayer() == null) {
            return null;
        }
        return super.getString(data.getIdentity().getPlayer().getSummonerName());
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

        final Integer i = data.getParticipant().getSpell1Id();
        if(i == null) {
            throw new MissingDataException("Summoner Spell #1 ID is null.");
        }

        spell1 = RiotAPI.getSummonerSpell(i.longValue());
        return spell1;
    }

    /**
     * First summoner spell ID
     *
     * @return first summoner spell ID
     */
    public long getSummonerSpell1ID() {
        return super.getInteger(data.getParticipant().getSpell1Id());
    }

    /**
     * Second summoner spell
     *
     * @return second summoner spell
     */
    public SummonerSpell getSummonerSpell2() {
        if(spell2 != null) {
            return spell2;
        }

        final Integer i = data.getParticipant().getSpell2Id();
        if(i == null) {
            throw new MissingDataException("Summoner Spell #2 ID is null.");
        }

        spell2 = RiotAPI.getSummonerSpell(i.longValue());
        return spell2;
    }

    /**
     * Second summoner spell ID
     *
     * @return second summoner spell ID
     */
    public long getSummonerSpell2ID() {
        return super.getInteger(data.getParticipant().getSpell2Id());
    }

    /**
     * The participant's team
     *
     * @return the participant's team
     */
    public Side getTeam() {
        return Side.forID(super.getInteger(data.getParticipant().getTeamId()));
    }

    /**
     * Timeline data. Delta fields refer to values for the specified period
     * (e.g., the gold per minute over the first 10 minutes of the game versus
     * the second 20 minutes of the game. Diffs fields refer to the deltas
     * versus the calculated lane opponent(s).
     *
     * @return timeline data
     */
    public ParticipantTimeline getTimeline() {
        if(timeline == null) {
            timeline = new ParticipantTimeline(data.getParticipant().getTimeline());
        }

        return timeline;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if(getSummonerName() != null) {
            return "Participant (" + getSummonerName() + " on " + getChampion() + ")";
        }
        return "Participant (" + getChampion() + ")";
    }
}
