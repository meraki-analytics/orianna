package com.merakianalytics.orianna.types.data.match;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.data.CoreData;

public class Participant extends CoreData {
    private static final long serialVersionUID = 1992039734399932614L;
    private long currentAccountId, summonerId, accountId;
    private Platform currentPlatform, platform;
    private Tier highestTierInSeason;
    private ParticipantStats stats;
    private String summonerName, matchHistoryURI;
    private int summonerSpellDId, summonerSpellFId, championId, profileIconId, participantId;
    private Side team;
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
        if(accountId != other.accountId) {
            return false;
        }
        if(championId != other.championId) {
            return false;
        }
        if(currentAccountId != other.currentAccountId) {
            return false;
        }
        if(currentPlatform != other.currentPlatform) {
            return false;
        }
        if(highestTierInSeason != other.highestTierInSeason) {
            return false;
        }
        if(matchHistoryURI == null) {
            if(other.matchHistoryURI != null) {
                return false;
            }
        } else if(!matchHistoryURI.equals(other.matchHistoryURI)) {
            return false;
        }
        if(participantId != other.participantId) {
            return false;
        }
        if(platform != other.platform) {
            return false;
        }
        if(profileIconId != other.profileIconId) {
            return false;
        }
        if(stats == null) {
            if(other.stats != null) {
                return false;
            }
        } else if(!stats.equals(other.stats)) {
            return false;
        }
        if(summonerId != other.summonerId) {
            return false;
        }
        if(summonerName == null) {
            if(other.summonerName != null) {
                return false;
            }
        } else if(!summonerName.equals(other.summonerName)) {
            return false;
        }
        if(summonerSpellDId != other.summonerSpellDId) {
            return false;
        }
        if(summonerSpellFId != other.summonerSpellFId) {
            return false;
        }
        if(team != other.team) {
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
     * @return the accountId
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * @return the championId
     */
    public int getChampionId() {
        return championId;
    }

    /**
     * @return the currentAccountId
     */
    public long getCurrentAccountId() {
        return currentAccountId;
    }

    /**
     * @return the currentPlatform
     */
    public Platform getCurrentPlatform() {
        return currentPlatform;
    }

    /**
     * @return the highestTierInSeason
     */
    public Tier getHighestTierInSeason() {
        return highestTierInSeason;
    }

    /**
     * @return the matchHistoryURI
     */
    public String getMatchHistoryURI() {
        return matchHistoryURI;
    }

    /**
     * @return the participantId
     */
    public int getParticipantId() {
        return participantId;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the profileIconId
     */
    public int getProfileIconId() {
        return profileIconId;
    }

    /**
     * @return the stats
     */
    public ParticipantStats getStats() {
        return stats;
    }

    /**
     * @return the summonerId
     */
    public long getSummonerId() {
        return summonerId;
    }

    /**
     * @return the summonerName
     */
    public String getSummonerName() {
        return summonerName;
    }

    /**
     * @return the summonerSpellDId
     */
    public int getSummonerSpellDId() {
        return summonerSpellDId;
    }

    /**
     * @return the summonerSpellFId
     */
    public int getSummonerSpellFId() {
        return summonerSpellFId;
    }

    /**
     * @return the team
     */
    public Side getTeam() {
        return team;
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
        result = prime * result + (int)(accountId ^ accountId >>> 32);
        result = prime * result + championId;
        result = prime * result + (int)(currentAccountId ^ currentAccountId >>> 32);
        result = prime * result + (currentPlatform == null ? 0 : currentPlatform.hashCode());
        result = prime * result + (highestTierInSeason == null ? 0 : highestTierInSeason.hashCode());
        result = prime * result + (matchHistoryURI == null ? 0 : matchHistoryURI.hashCode());
        result = prime * result + participantId;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + profileIconId;
        result = prime * result + (stats == null ? 0 : stats.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        result = prime * result + (summonerName == null ? 0 : summonerName.hashCode());
        result = prime * result + summonerSpellDId;
        result = prime * result + summonerSpellFId;
        result = prime * result + (team == null ? 0 : team.hashCode());
        result = prime * result + (timeline == null ? 0 : timeline.hashCode());
        return result;
    }

    /**
     * @param accountId
     *        the accountId to set
     */
    public void setAccountId(final long accountId) {
        this.accountId = accountId;
    }

    /**
     * @param championId
     *        the championId to set
     */
    public void setChampionId(final int championId) {
        this.championId = championId;
    }

    /**
     * @param currentAccountId
     *        the currentAccountId to set
     */
    public void setCurrentAccountId(final long currentAccountId) {
        this.currentAccountId = currentAccountId;
    }

    /**
     * @param currentPlatform
     *        the currentPlatform to set
     */
    public void setCurrentPlatform(final Platform currentPlatform) {
        this.currentPlatform = currentPlatform;
    }

    /**
     * @param highestTierInSeason
     *        the highestTierInSeason to set
     */
    public void setHighestTierInSeason(final Tier highestTierInSeason) {
        this.highestTierInSeason = highestTierInSeason;
    }

    /**
     * @param matchHistoryURI
     *        the matchHistoryURI to set
     */
    public void setMatchHistoryURI(final String matchHistoryURI) {
        this.matchHistoryURI = matchHistoryURI;
    }

    /**
     * @param participantId
     *        the participantId to set
     */
    public void setParticipantId(final int participantId) {
        this.participantId = participantId;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param profileIconId
     *        the profileIconId to set
     */
    public void setProfileIconId(final int profileIconId) {
        this.profileIconId = profileIconId;
    }

    /**
     * @param stats
     *        the stats to set
     */
    public void setStats(final ParticipantStats stats) {
        this.stats = stats;
    }

    /**
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final long summonerId) {
        this.summonerId = summonerId;
    }

    /**
     * @param summonerName
     *        the summonerName to set
     */
    public void setSummonerName(final String summonerName) {
        this.summonerName = summonerName;
    }

    /**
     * @param summonerSpellDId
     *        the summonerSpellDId to set
     */
    public void setSummonerSpellDId(final int summonerSpellDId) {
        this.summonerSpellDId = summonerSpellDId;
    }

    /**
     * @param summonerSpellFId
     *        the summonerSpellFId to set
     */
    public void setSummonerSpellFId(final int summonerSpellFId) {
        this.summonerSpellFId = summonerSpellFId;
    }

    /**
     * @param team
     *        the team to set
     */
    public void setTeam(final Side team) {
        this.team = team;
    }

    /**
     * @param timeline
     *        the timeline to set
     */
    public void setTimeline(final ParticipantTimeline timeline) {
        this.timeline = timeline;
    }
}
