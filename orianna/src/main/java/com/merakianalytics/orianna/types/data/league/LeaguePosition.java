package com.merakianalytics.orianna.types.data.league;

import com.merakianalytics.orianna.types.data.CoreData;

public class LeaguePosition extends CoreData {
    private static final long serialVersionUID = 7745380809131095259L;
    private String name, summonerName, leagueId, division, platform, queue, tier;
    private boolean onHotStreak, veteran, inactive, freshBlood;
    private Series promos;
    private long summonerId;
    private int wins, losses, leaguePoints;

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
        final LeaguePosition other = (LeaguePosition)obj;
        if(division == null) {
            if(other.division != null) {
                return false;
            }
        } else if(!division.equals(other.division)) {
            return false;
        }
        if(freshBlood != other.freshBlood) {
            return false;
        }
        if(inactive != other.inactive) {
            return false;
        }
        if(leagueId == null) {
            if(other.leagueId != null) {
                return false;
            }
        } else if(!leagueId.equals(other.leagueId)) {
            return false;
        }
        if(leaguePoints != other.leaguePoints) {
            return false;
        }
        if(losses != other.losses) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(onHotStreak != other.onHotStreak) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(promos == null) {
            if(other.promos != null) {
                return false;
            }
        } else if(!promos.equals(other.promos)) {
            return false;
        }
        if(queue == null) {
            if(other.queue != null) {
                return false;
            }
        } else if(!queue.equals(other.queue)) {
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
        if(tier == null) {
            if(other.tier != null) {
                return false;
            }
        } else if(!tier.equals(other.tier)) {
            return false;
        }
        if(veteran != other.veteran) {
            return false;
        }
        if(wins != other.wins) {
            return false;
        }
        return true;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @return the leagueId
     */
    public String getLeagueId() {
        return leagueId;
    }

    /**
     * @return the leaguePoints
     */
    public int getLeaguePoints() {
        return leaguePoints;
    }

    /**
     * @return the losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the promos
     */
    public Series getPromos() {
        return promos;
    }

    /**
     * @return the queue
     */
    public String getQueue() {
        return queue;
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
     * @return the tier
     */
    public String getTier() {
        return tier;
    }

    /**
     * @return the wins
     */
    public int getWins() {
        return wins;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (division == null ? 0 : division.hashCode());
        result = prime * result + (freshBlood ? 1231 : 1237);
        result = prime * result + (inactive ? 1231 : 1237);
        result = prime * result + (leagueId == null ? 0 : leagueId.hashCode());
        result = prime * result + leaguePoints;
        result = prime * result + losses;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (onHotStreak ? 1231 : 1237);
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (promos == null ? 0 : promos.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        result = prime * result + (summonerName == null ? 0 : summonerName.hashCode());
        result = prime * result + (tier == null ? 0 : tier.hashCode());
        result = prime * result + (veteran ? 1231 : 1237);
        result = prime * result + wins;
        return result;
    }

    /**
     * @return the freshBlood
     */
    public boolean isFreshBlood() {
        return freshBlood;
    }

    /**
     * @return the inactive
     */
    public boolean isInactive() {
        return inactive;
    }

    /**
     * @return the onHotStreak
     */
    public boolean isOnHotStreak() {
        return onHotStreak;
    }

    /**
     * @return the veteran
     */
    public boolean isVeteran() {
        return veteran;
    }

    /**
     * @param division
     *        the division to set
     */
    public void setDivision(final String division) {
        this.division = division;
    }

    /**
     * @param freshBlood
     *        the freshBlood to set
     */
    public void setFreshBlood(final boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    /**
     * @param inactive
     *        the inactive to set
     */
    public void setInactive(final boolean inactive) {
        this.inactive = inactive;
    }

    /**
     * @param leagueId
     *        the leagueId to set
     */
    public void setLeagueId(final String leagueId) {
        this.leagueId = leagueId;
    }

    /**
     * @param leaguePoints
     *        the leaguePoints to set
     */
    public void setLeaguePoints(final int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    /**
     * @param losses
     *        the losses to set
     */
    public void setLosses(final int losses) {
        this.losses = losses;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param onHotStreak
     *        the onHotStreak to set
     */
    public void setOnHotStreak(final boolean onHotStreak) {
        this.onHotStreak = onHotStreak;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param promos
     *        the promos to set
     */
    public void setPromos(final Series promos) {
        this.promos = promos;
    }

    /**
     * @param queue
     *        the queue to set
     */
    public void setQueue(final String queue) {
        this.queue = queue;
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
     * @param tier
     *        the tier to set
     */
    public void setTier(final String tier) {
        this.tier = tier;
    }

    /**
     * @param veteran
     *        the veteran to set
     */
    public void setVeteran(final boolean veteran) {
        this.veteran = veteran;
    }

    /**
     * @param wins
     *        the wins to set
     */
    public void setWins(final int wins) {
        this.wins = wins;
    }
}
