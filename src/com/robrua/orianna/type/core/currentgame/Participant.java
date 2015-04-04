package com.robrua.orianna.type.core.currentgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.Side;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.staticdata.SummonerSpell;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.MissingDataException;

public class Participant extends OriannaObject<com.robrua.orianna.type.dto.currentgame.Participant> {
    private static final long serialVersionUID = 4888024655001066355L;
    private Champion champion;
    private List<MasteryRank> masteries;
    private List<RuneCount> runes;
    private SummonerSpell spell1, spell2;
    private Summoner summoner;

    /**
     * @param data
     *            the underlying dto
     */
    public Participant(final com.robrua.orianna.type.dto.currentgame.Participant data) {
        super(data, com.robrua.orianna.type.dto.currentgame.Participant.class);
    }

    /**
     * Flag indicating whether or not this participant is a bot
     *
     * @return whether or not this participant is a bot
     */
    public boolean getBot() {
        return super.getBoolean(data.getBot());
    }

    /**
     * The champion this participant picked
     *
     * @return the champion this participant picked
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
     * The ID of the champion played by this participant
     *
     * @return the ID of the champion played by this participant
     */
    public long getChampionID() {
        return super.getLong(data.getChampionId());
    }

    /**
     * The masteries used by this participant
     *
     * @return the masteries used by this participant
     */
    public List<MasteryRank> getMasteries() {
        if(masteries == null) {
            masteries = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.currentgame.Mastery mastery : data.getMasteries()) {
                masteries.add(new MasteryRank(mastery));
            }
        }

        return Collections.unmodifiableList(masteries);
    }

    /**
     * The ID of the profile icon used by this participant
     *
     * @return the ID of the profile icon used by this participant
     */
    public long getProfileIconID() {
        return super.getLong(data.getProfileIconId());
    }

    /**
     * The runes used by this participant
     *
     * @return the runes used by this participant
     */
    public List<RuneCount> getRunes() {
        if(runes == null) {
            runes = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.currentgame.Rune rune : data.getRunes()) {
                runes.add(new RuneCount(rune));
            }
        }

        return Collections.unmodifiableList(runes);
    }

    /**
     * The summoner for this participant
     *
     * @return the summoner for this participant
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
     * The summoner ID of this participant
     *
     * @return the summoner ID of this participant
     */
    public long getSummonerID() {
        return super.getLong(data.getSummonerId());
    }

    /**
     * The summoner name of this participant
     *
     * @return the summoner name of this participant
     */
    public String getSummonerName() {
        return super.getString(data.getSummonerName());
    }

    /**
     * The first summoner spell
     *
     * @return the first summoner spell
     */
    public SummonerSpell getSummonerSpell1() {
        if(spell1 != null) {
            return spell1;
        }

        final Long l = data.getSpell1Id();
        if(l == null) {
            throw new MissingDataException("Summoner Spell #1 ID is null.");
        }

        spell1 = RiotAPI.getSummonerSpell(l.longValue());
        return spell1;
    }

    /**
     * The ID of the first summoner spell used by this participant
     *
     * @return the ID of the first summoner spell used by this participant
     */
    public long getSummonerSpell1ID() {
        return super.getLong(data.getSpell1Id());
    }

    /**
     * The second summoner spell
     *
     * @return the second summoner spell
     */
    public SummonerSpell getSummonerSpell2() {
        if(spell2 != null) {
            return spell2;
        }

        final Long l = data.getSpell2Id();
        if(l == null) {
            throw new MissingDataException("Summoner Spell #2 ID is null.");
        }

        spell2 = RiotAPI.getSummonerSpell(l.longValue());
        return spell2;
    }

    /**
     * The ID of the second summoner spell used by this participant
     *
     * @return the ID of the second summoner spell used by this participant
     */
    public long getSummonerSpell2ID() {
        return super.getLong(data.getSpell2Id());
    }

    /**
     * The team that the participant is on
     *
     * @return the team that the participant is on
     */
    public Side getTeam() {
        return Side.forID(super.getLong(data.getTeamId()));
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Participant (" + getSummonerName() + " as " + getChampion() + ")";
    }
}
