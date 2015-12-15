package com.robrua.orianna.type.dto.match;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "matchdetail")
public class MatchDetail extends OriannaDto {
    private static final long serialVersionUID = -5583614253740686126L;
    private Integer mapId;
    private Long matchCreation, matchDuration;
    @Id
    private Long matchId;

    private String matchMode, matchType, matchVersion, platformId, queueType, region, season;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ParticipantIdentity> participantIdentities;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Participant> participants;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> teams;

    @OneToOne(cascade = CascadeType.ALL)
    private Timeline timeline;

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
        if(!(obj instanceof MatchDetail)) {
            return false;
        }
        final MatchDetail other = (MatchDetail)obj;
        if(mapId == null) {
            if(other.mapId != null) {
                return false;
            }
        }
        else if(!mapId.equals(other.mapId)) {
            return false;
        }
        if(matchCreation == null) {
            if(other.matchCreation != null) {
                return false;
            }
        }
        else if(!matchCreation.equals(other.matchCreation)) {
            return false;
        }
        if(matchDuration == null) {
            if(other.matchDuration != null) {
                return false;
            }
        }
        else if(!matchDuration.equals(other.matchDuration)) {
            return false;
        }
        if(matchId == null) {
            if(other.matchId != null) {
                return false;
            }
        }
        else if(!matchId.equals(other.matchId)) {
            return false;
        }
        if(matchMode == null) {
            if(other.matchMode != null) {
                return false;
            }
        }
        else if(!matchMode.equals(other.matchMode)) {
            return false;
        }
        if(matchType == null) {
            if(other.matchType != null) {
                return false;
            }
        }
        else if(!matchType.equals(other.matchType)) {
            return false;
        }
        if(matchVersion == null) {
            if(other.matchVersion != null) {
                return false;
            }
        }
        else if(!matchVersion.equals(other.matchVersion)) {
            return false;
        }
        if(participantIdentities == null) {
            if(other.participantIdentities != null) {
                return false;
            }
        }
        else if(!participantIdentities.equals(other.participantIdentities)) {
            return false;
        }
        if(participants == null) {
            if(other.participants != null) {
                return false;
            }
        }
        else if(!participants.equals(other.participants)) {
            return false;
        }
        if(platformId == null) {
            if(other.platformId != null) {
                return false;
            }
        }
        else if(!platformId.equals(other.platformId)) {
            return false;
        }
        if(queueType == null) {
            if(other.queueType != null) {
                return false;
            }
        }
        else if(!queueType.equals(other.queueType)) {
            return false;
        }
        if(region == null) {
            if(other.region != null) {
                return false;
            }
        }
        else if(!region.equals(other.region)) {
            return false;
        }
        if(season == null) {
            if(other.season != null) {
                return false;
            }
        }
        else if(!season.equals(other.season)) {
            return false;
        }
        if(teams == null) {
            if(other.teams != null) {
                return false;
            }
        }
        else if(!teams.equals(other.teams)) {
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
     * Gets all stored champion IDs for batch lookup
     *
     * @return the champion IDs
     */
    public Set<Long> getChampionIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Participant p : participants) {
            set.add(p.getChampionId().longValue());
        }
        for(final Team t : teams) {
            if(t.getBans() != null) {
                for(final BannedChampion ban : t.getBans()) {
                    set.add(ban.getChampionId().longValue());
                }
            }
        }

        return set;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "matchId";
        }
        return null;
    }

    /**
     * Gets all stored item IDs for batch lookup
     *
     * @return the item IDs
     */
    public Set<Long> getItemIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Participant p : participants) {
            final ParticipantStats s = p.getStats();
            set.add(s.getItem0());
            set.add(s.getItem1());
            set.add(s.getItem2());
            set.add(s.getItem3());
            set.add(s.getItem4());
            set.add(s.getItem5());
            set.add(s.getItem6());
        }

        return set;
    }

    /**
     * @return the mapId
     */
    public Integer getMapId() {
        return mapId;
    }

    /**
     * Gets all stored mastery IDs for batch lookup
     *
     * @return the mastery IDs
     */
    public Set<Long> getMasteryIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Participant p : participants) {
            if(p.getMasteries() != null) {
                for(final Mastery mastery : p.getMasteries()) {
                    set.add(mastery.getMasteryId());
                }
            }
        }

        return set;
    }

    /**
     * @return the matchCreation
     */
    public Long getMatchCreation() {
        return matchCreation;
    }

    /**
     * @return the matchDuration
     */
    public Long getMatchDuration() {
        return matchDuration;
    }

    /**
     * @return the matchId
     */
    public Long getMatchId() {
        return matchId;
    }

    /**
     * @return the matchMode
     */
    public String getMatchMode() {
        return matchMode;
    }

    /**
     * @return the matchType
     */
    public String getMatchType() {
        return matchType;
    }

    /**
     * @return the matchVersion
     */
    public String getMatchVersion() {
        return matchVersion;
    }

    /**
     * @return the participantIdentities
     */
    public List<ParticipantIdentity> getParticipantIdentities() {
        return participantIdentities;
    }

    /**
     * @return the participants
     */
    public List<Participant> getParticipants() {
        return participants;
    }

    /**
     * @return the platformId
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * @return the queueType
     */
    public String getQueueType() {
        return queueType;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Gets all stored rune IDs for batch lookup
     *
     * @return the rune IDs
     */
    public Set<Long> getRuneIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Participant p : participants) {
            if(p.getRunes() != null) {
                for(final Rune rune : p.getRunes()) {
                    set.add(rune.getRuneId());
                }
            }
        }

        return set;
    }

    /**
     * @return the season
     */
    public String getSeason() {
        return season;
    }

    /**
     * Gets all stored summoner IDs for batch lookup
     *
     * @return the summoner IDs
     */
    public Set<Long> getSummonerIDs() {
        final Set<Long> set = new HashSet<>();
        for(final ParticipantIdentity p : participantIdentities) {
            if(p.getPlayer() != null) {
                set.add(p.getPlayer().getSummonerId());
            }
        }

        return set;
    }

    /**
     * Gets all stored summoner names for batch lookup
     *
     * @return the summoner names
     */
    public Set<String> getSummonerNames() {
        final Set<String> set = new HashSet<>();
        for(final ParticipantIdentity p : participantIdentities) {
            if(p.getPlayer() != null) {
                set.add(p.getPlayer().getSummonerName());
            }
        }

        return set;
    }

    /**
     * Gets all stored summoner spell IDs for batch lookup
     *
     * @return the summoner spell IDs
     */
    public Set<Long> getSummonerSpellIDs() {
        final Set<Long> set = new HashSet<>();
        for(final Participant p : participants) {
            set.add(p.getSpell1Id().longValue());
            set.add(p.getSpell1Id().longValue());
        }

        return set;
    }

    /**
     * @return the teams
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * @return the timeline
     */
    public Timeline getTimeline() {
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
        result = prime * result + (mapId == null ? 0 : mapId.hashCode());
        result = prime * result + (matchCreation == null ? 0 : matchCreation.hashCode());
        result = prime * result + (matchDuration == null ? 0 : matchDuration.hashCode());
        result = prime * result + (matchId == null ? 0 : matchId.hashCode());
        result = prime * result + (matchMode == null ? 0 : matchMode.hashCode());
        result = prime * result + (matchType == null ? 0 : matchType.hashCode());
        result = prime * result + (matchVersion == null ? 0 : matchVersion.hashCode());
        result = prime * result + (participantIdentities == null ? 0 : participantIdentities.hashCode());
        result = prime * result + (participants == null ? 0 : participants.hashCode());
        result = prime * result + (platformId == null ? 0 : platformId.hashCode());
        result = prime * result + (queueType == null ? 0 : queueType.hashCode());
        result = prime * result + (region == null ? 0 : region.hashCode());
        result = prime * result + (season == null ? 0 : season.hashCode());
        result = prime * result + (teams == null ? 0 : teams.hashCode());
        result = prime * result + (timeline == null ? 0 : timeline.hashCode());
        return result;
    }

    /**
     * @param mapId
     *            the mapId to set
     */
    public void setMapId(final Integer mapId) {
        this.mapId = mapId;
    }

    /**
     * @param matchCreation
     *            the matchCreation to set
     */
    public void setMatchCreation(final Long matchCreation) {
        this.matchCreation = matchCreation;
    }

    /**
     * @param matchDuration
     *            the matchDuration to set
     */
    public void setMatchDuration(final Long matchDuration) {
        this.matchDuration = matchDuration;
    }

    /**
     * @param matchId
     *            the matchId to set
     */
    public void setMatchId(final Long matchId) {
        this.matchId = matchId;
    }

    /**
     * @param matchMode
     *            the matchMode to set
     */
    public void setMatchMode(final String matchMode) {
        this.matchMode = matchMode;
    }

    /**
     * @param matchType
     *            the matchType to set
     */
    public void setMatchType(final String matchType) {
        this.matchType = matchType;
    }

    /**
     * @param matchVersion
     *            the matchVersion to set
     */
    public void setMatchVersion(final String matchVersion) {
        this.matchVersion = matchVersion;
    }

    /**
     * @param participantIdentities
     *            the participantIdentities to set
     */
    public void setParticipantIdentities(final List<ParticipantIdentity> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    /**
     * @param participants
     *            the participants to set
     */
    public void setParticipants(final List<Participant> participants) {
        this.participants = participants;
    }

    /**
     * @param platformId
     *            the platformId to set
     */
    public void setPlatformId(final String platformId) {
        this.platformId = platformId;
    }

    /**
     * @param queueType
     *            the queueType to set
     */
    public void setQueueType(final String queueType) {
        this.queueType = queueType;
    }

    /**
     * @param region
     *            the region to set
     */
    public void setRegion(final String region) {
        this.region = region;
    }

    /**
     * @param season
     *            the season to set
     */
    public void setSeason(final String season) {
        this.season = season;
    }

    /**
     * @param teams
     *            the teams to set
     */
    public void setTeams(final List<Team> teams) {
        this.teams = teams;
    }

    /**
     * @param timeline
     *            the timeline to set
     */
    public void setTimeline(final Timeline timeline) {
        this.timeline = timeline;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MatchDetail [mapId=" + mapId + ", matchCreation=" + matchCreation + ", matchDuration=" + matchDuration + ", matchId=" + matchId + ", matchMode="
                + matchMode + ", matchType=" + matchType + ", matchVersion=" + matchVersion + ", platformId=" + platformId + ", queueType=" + queueType
                + ", region=" + region + ", season=" + season + ", participantIdentities=" + participantIdentities + ", participants=" + participants
                + ", teams=" + teams + ", timeline=" + timeline + "]";
    }
}
