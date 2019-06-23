package com.merakianalytics.orianna.types.dto.league;

import com.merakianalytics.orianna.types.dto.DataObject;

public class LeagueItem extends DataObject {
    private static final long serialVersionUID = 2521820374904983414L;
    private boolean hotStreak, veteran, freshBlood, inactive;
    private MiniSeries miniSeries;
    private String summonerName, rank, summonerId;
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
        final LeagueItem other = (LeagueItem)obj;
        if(freshBlood != other.freshBlood) {
            return false;
        }
        if(hotStreak != other.hotStreak) {
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
        if(miniSeries == null) {
            if(other.miniSeries != null) {
                return false;
            }
        } else if(!miniSeries.equals(other.miniSeries)) {
            return false;
        }
        if(rank == null) {
            if(other.rank != null) {
                return false;
            }
        } else if(!rank.equals(other.rank)) {
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
     * @return the miniSeries
     */
    public MiniSeries getMiniSeries() {
        return miniSeries;
    }

    /**
     * @return the rank
     */
    public String getRank() {
        return rank;
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
        result = prime * result + (freshBlood ? 1231 : 1237);
        result = prime * result + (hotStreak ? 1231 : 1237);
        result = prime * result + (inactive ? 1231 : 1237);
        result = prime * result + leaguePoints;
        result = prime * result + losses;
        result = prime * result + (miniSeries == null ? 0 : miniSeries.hashCode());
        result = prime * result + (rank == null ? 0 : rank.hashCode());
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
     * @return the hotStreak
     */
    public boolean isHotStreak() {
        return hotStreak;
    }

    /**
     * @return the inactive
     */
    public boolean isInactive() {
        return inactive;
    }

    /**
     * @return the veteran
     */
    public boolean isVeteran() {
        return veteran;
    }

    /**
     * @param freshBlood
     *        the freshBlood to set
     */
    public void setFreshBlood(final boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    /**
     * @param hotStreak
     *        the hotStreak to set
     */
    public void setHotStreak(final boolean hotStreak) {
        this.hotStreak = hotStreak;
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
     * @param miniSeries
     *        the miniSeries to set
     */
    public void setMiniSeries(final MiniSeries miniSeries) {
        this.miniSeries = miniSeries;
    }

    /**
     * @param rank
     *        the rank to set
     */
    public void setRank(final String rank) {
        this.rank = rank;
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
