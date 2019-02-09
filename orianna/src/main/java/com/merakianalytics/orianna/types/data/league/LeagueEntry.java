package com.merakianalytics.orianna.types.data.league;

import com.merakianalytics.orianna.types.data.CoreData;

public class LeagueEntry extends CoreData {
    private static final long serialVersionUID = -386628078546948368L;
    private String division, summonerName, platform, summonerId;
    private boolean onHotStreak, veteran, inactive, freshBlood;
    private Series promos;
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
        final LeagueEntry other = (LeagueEntry)obj;
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
        if(leaguePoints != other.leaguePoints) {
            return false;
        }
        if(losses != other.losses) {
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
        if(summonerId == null) {
            if(other.summonerId != null) {
                return false;
            }
        } else if(!summonerId.equals(other.summonerId)) {
            return false;
        }
        if(summonerName == null) {
            if(other.summonerName != null) {
                return false;
            }
        } else if(!summonerName.equals(other.summonerName)) {
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
     * @return the summonerId
     */
    public String getSummonerId() {
        return summonerId;
    }

    /**
     * @return the summonerName
     */
    public String getSummonerName() {
        return summonerName;
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
        result = prime * result + leaguePoints;
        result = prime * result + losses;
        result = prime * result + (onHotStreak ? 1231 : 1237);
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (promos == null ? 0 : promos.hashCode());
        result = prime * result + (summonerId == null ? 0 : summonerId.hashCode());
        result = prime * result + (summonerName == null ? 0 : summonerName.hashCode());
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
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final String summonerId) {
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
