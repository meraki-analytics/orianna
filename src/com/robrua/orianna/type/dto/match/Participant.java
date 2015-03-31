package com.robrua.orianna.type.dto.match;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity(name = "match.Participant")
@Table(name = "matchparticipant")
public class Participant extends OriannaDto {
    private static final long serialVersionUID = -7039354946393429237L;
    private Integer championId, participantId, spell1Id, spell2Id, teamId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private String highestAchievedSeasonTier;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Mastery> masteries;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rune> runes;

    @OneToOne(cascade = CascadeType.ALL)
    private ParticipantStats stats;

    @OneToOne(cascade = CascadeType.ALL)
    private ParticipantTimeline timeline;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Participant)) {
            return false;
        }
        final Participant other = (Participant)obj;
        if(championId == null) {
            if(other.championId != null) {
                return false;
            }
        }
        else if(!championId.equals(other.championId)) {
            return false;
        }
        if(highestAchievedSeasonTier == null) {
            if(other.highestAchievedSeasonTier != null) {
                return false;
            }
        }
        else if(!highestAchievedSeasonTier.equals(other.highestAchievedSeasonTier)) {
            return false;
        }
        if(masteries == null) {
            if(other.masteries != null) {
                return false;
            }
        }
        else if(!masteries.equals(other.masteries)) {
            return false;
        }
        if(participantId == null) {
            if(other.participantId != null) {
                return false;
            }
        }
        else if(!participantId.equals(other.participantId)) {
            return false;
        }
        if(runes == null) {
            if(other.runes != null) {
                return false;
            }
        }
        else if(!runes.equals(other.runes)) {
            return false;
        }
        if(spell1Id == null) {
            if(other.spell1Id != null) {
                return false;
            }
        }
        else if(!spell1Id.equals(other.spell1Id)) {
            return false;
        }
        if(spell2Id == null) {
            if(other.spell2Id != null) {
                return false;
            }
        }
        else if(!spell2Id.equals(other.spell2Id)) {
            return false;
        }
        if(stats == null) {
            if(other.stats != null) {
                return false;
            }
        }
        else if(!stats.equals(other.stats)) {
            return false;
        }
        if(teamId == null) {
            if(other.teamId != null) {
                return false;
            }
        }
        else if(!teamId.equals(other.teamId)) {
            return false;
        }
        if(timeline == null) {
            if(other.timeline != null) {
                return false;
            }
        }
        else if(!timeline.equals(other.timeline)) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public Integer getChampionId() {
        return championId;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the highestAchievedSeasonTier
     */
    public String getHighestAchievedSeasonTier() {
        return highestAchievedSeasonTier;
    }

    /**
     * @return the masteries
     */
    public List<Mastery> getMasteries() {
        return masteries;
    }

    /**
     * @return the participantId
     */
    public Integer getParticipantId() {
        return participantId;
    }

    /**
     * @return the runes
     */
    public List<Rune> getRunes() {
        return runes;
    }

    /**
     * @return the spell1Id
     */
    public Integer getSpell1Id() {
        return spell1Id;
    }

    /**
     * @return the spell2Id
     */
    public Integer getSpell2Id() {
        return spell2Id;
    }

    /**
     * @return the stats
     */
    public ParticipantStats getStats() {
        return stats;
    }

    /**
     * @return the teamId
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * @return the timeline
     */
    public ParticipantTimeline getTimeline() {
        return timeline;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (championId == null ? 0 : championId.hashCode());
        result = prime * result + (highestAchievedSeasonTier == null ? 0 : highestAchievedSeasonTier.hashCode());
        result = prime * result + (masteries == null ? 0 : masteries.hashCode());
        result = prime * result + (participantId == null ? 0 : participantId.hashCode());
        result = prime * result + (runes == null ? 0 : runes.hashCode());
        result = prime * result + (spell1Id == null ? 0 : spell1Id.hashCode());
        result = prime * result + (spell2Id == null ? 0 : spell2Id.hashCode());
        result = prime * result + (stats == null ? 0 : stats.hashCode());
        result = prime * result + (teamId == null ? 0 : teamId.hashCode());
        result = prime * result + (timeline == null ? 0 : timeline.hashCode());
        return result;
    }

    /**
     * @param championId
     *            the championId to set
     */
    public void setChampionId(final Integer championId) {
        this.championId = championId;
    }

    /**
     * @param highestAchievedSeasonTier
     *            the highestAchievedSeasonTier to set
     */
    public void setHighestAchievedSeasonTier(final String highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    /**
     * @param masteries
     *            the masteries to set
     */
    public void setMasteries(final List<Mastery> masteries) {
        this.masteries = masteries;
    }

    /**
     * @param participantId
     *            the participantId to set
     */
    public void setParticipantId(final Integer participantId) {
        this.participantId = participantId;
    }

    /**
     * @param runes
     *            the runes to set
     */
    public void setRunes(final List<Rune> runes) {
        this.runes = runes;
    }

    /**
     * @param spell1Id
     *            the spell1Id to set
     */
    public void setSpell1Id(final Integer spell1Id) {
        this.spell1Id = spell1Id;
    }

    /**
     * @param spell2Id
     *            the spell2Id to set
     */
    public void setSpell2Id(final Integer spell2Id) {
        this.spell2Id = spell2Id;
    }

    /**
     * @param stats
     *            the stats to set
     */
    public void setStats(final ParticipantStats stats) {
        this.stats = stats;
    }

    /**
     * @param teamId
     *            the teamId to set
     */
    public void setTeamId(final Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * @param timeline
     *            the timeline to set
     */
    public void setTimeline(final ParticipantTimeline timeline) {
        this.timeline = timeline;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Participant [championId=" + championId + ", participantId=" + participantId + ", spell1Id=" + spell1Id + ", spell2Id=" + spell2Id + ", teamId="
                + teamId + ", highestAchievedSeasonTier=" + highestAchievedSeasonTier + ", masteries=" + masteries + ", runes=" + runes + ", stats=" + stats
                + ", timeline=" + timeline + "]";
    }
}
