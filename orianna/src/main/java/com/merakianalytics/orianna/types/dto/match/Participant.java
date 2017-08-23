package com.merakianalytics.orianna.types.dto.match;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Participant extends DataObject {
    private static final long serialVersionUID = -5954775193579988257L;
    private String highestAchievedSeasonTier;
    private List<Mastery> masteries;
    private int participantId, teamId, spell2Id, spell1Id, championId;
    private List<Rune> runes;
    private ParticipantStats stats;
    private ParticipantTimeline timeline;

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final Participant other = (Participant)obj;
        if(championId != other.championId) {
            return false;
        }
        if(highestAchievedSeasonTier == null) {
            if(other.highestAchievedSeasonTier != null) {
                return false;
            }
        } else if(!highestAchievedSeasonTier.equals(other.highestAchievedSeasonTier)) {
            return false;
        }
        if(masteries == null) {
            if(other.masteries != null) {
                return false;
            }
        } else if(!masteries.equals(other.masteries)) {
            return false;
        }
        if(participantId != other.participantId) {
            return false;
        }
        if(runes == null) {
            if(other.runes != null) {
                return false;
            }
        } else if(!runes.equals(other.runes)) {
            return false;
        }
        if(spell1Id != other.spell1Id) {
            return false;
        }
        if(spell2Id != other.spell2Id) {
            return false;
        }
        if(stats == null) {
            if(other.stats != null) {
                return false;
            }
        } else if(!stats.equals(other.stats)) {
            return false;
        }
        if(teamId != other.teamId) {
            return false;
        }
        if(timeline == null) {
            if(other.timeline != null) {
                return false;
            }
        } else if(!timeline.equals(other.timeline)) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public int getChampionId() {
        return championId;
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
    public int getParticipantId() {
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
    public int getSpell1Id() {
        return spell1Id;
    }

    /**
     * @return the spell2Id
     */
    public int getSpell2Id() {
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
    public int getTeamId() {
        return teamId;
    }

    /**
     * @return the timeline
     */
    public ParticipantTimeline getTimeline() {
        return timeline;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + championId;
        result = prime * result + (highestAchievedSeasonTier == null ? 0 : highestAchievedSeasonTier.hashCode());
        result = prime * result + (masteries == null ? 0 : masteries.hashCode());
        result = prime * result + participantId;
        result = prime * result + (runes == null ? 0 : runes.hashCode());
        result = prime * result + spell1Id;
        result = prime * result + spell2Id;
        result = prime * result + (stats == null ? 0 : stats.hashCode());
        result = prime * result + teamId;
        result = prime * result + (timeline == null ? 0 : timeline.hashCode());
        return result;
    }

    /**
     * @param championId
     *        the championId to set
     */
    public void setChampionId(final int championId) {
        this.championId = championId;
    }

    /**
     * @param highestAchievedSeasonTier
     *        the highestAchievedSeasonTier to set
     */
    public void setHighestAchievedSeasonTier(final String highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    /**
     * @param masteries
     *        the masteries to set
     */
    public void setMasteries(final List<Mastery> masteries) {
        this.masteries = masteries;
    }

    /**
     * @param participantId
     *        the participantId to set
     */
    public void setParticipantId(final int participantId) {
        this.participantId = participantId;
    }

    /**
     * @param runes
     *        the runes to set
     */
    public void setRunes(final List<Rune> runes) {
        this.runes = runes;
    }

    /**
     * @param spell1Id
     *        the spell1Id to set
     */
    public void setSpell1Id(final int spell1Id) {
        this.spell1Id = spell1Id;
    }

    /**
     * @param spell2Id
     *        the spell2Id to set
     */
    public void setSpell2Id(final int spell2Id) {
        this.spell2Id = spell2Id;
    }

    /**
     * @param stats
     *        the stats to set
     */
    public void setStats(final ParticipantStats stats) {
        this.stats = stats;
    }

    /**
     * @param teamId
     *        the teamId to set
     */
    public void setTeamId(final int teamId) {
        this.teamId = teamId;
    }

    /**
     * @param timeline
     *        the timeline to set
     */
    public void setTimeline(final ParticipantTimeline timeline) {
        this.timeline = timeline;
    }
}
