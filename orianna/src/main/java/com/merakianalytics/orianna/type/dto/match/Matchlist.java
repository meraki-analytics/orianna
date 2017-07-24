package com.merakianalytics.orianna.type.dto.match;

import java.util.List;
import java.util.Set;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Matchlist extends DataObject {
    private static final long serialVersionUID = 6693751756707534012L;
    private long accountId, startTime, endTime;
    private List<MatchReference> matches;
    private String platform;
    private Set<Integer> queues, seasons, champions;
    private boolean recent;
    private int totalGames, startIndex, endIndex;

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
        if(getClass() != obj.getClass()) {
            return false;
        }
        final Matchlist other = (Matchlist)obj;
        if(accountId != other.accountId) {
            return false;
        }
        if(champions == null) {
            if(other.champions != null) {
                return false;
            }
        } else if(!champions.equals(other.champions)) {
            return false;
        }
        if(endIndex != other.endIndex) {
            return false;
        }
        if(endTime != other.endTime) {
            return false;
        }
        if(matches == null) {
            if(other.matches != null) {
                return false;
            }
        } else if(!matches.equals(other.matches)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(queues == null) {
            if(other.queues != null) {
                return false;
            }
        } else if(!queues.equals(other.queues)) {
            return false;
        }
        if(recent != other.recent) {
            return false;
        }
        if(seasons == null) {
            if(other.seasons != null) {
                return false;
            }
        } else if(!seasons.equals(other.seasons)) {
            return false;
        }
        if(startIndex != other.startIndex) {
            return false;
        }
        if(startTime != other.startTime) {
            return false;
        }
        if(totalGames != other.totalGames) {
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
     * @return the champions
     */
    public Set<Integer> getChampions() {
        return champions;
    }

    /**
     * @return the endIndex
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * @return the matches
     */
    public List<MatchReference> getMatches() {
        return matches;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the queues
     */
    public Set<Integer> getQueues() {
        return queues;
    }

    /**
     * @return the seasons
     */
    public Set<Integer> getSeasons() {
        return seasons;
    }

    /**
     * @return the startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @return the totalGames
     */
    public int getTotalGames() {
        return totalGames;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(accountId ^ accountId >>> 32);
        result = prime * result + (champions == null ? 0 : champions.hashCode());
        result = prime * result + endIndex;
        result = prime * result + (int)(endTime ^ endTime >>> 32);
        result = prime * result + (matches == null ? 0 : matches.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (queues == null ? 0 : queues.hashCode());
        result = prime * result + (recent ? 1231 : 1237);
        result = prime * result + (seasons == null ? 0 : seasons.hashCode());
        result = prime * result + startIndex;
        result = prime * result + (int)(startTime ^ startTime >>> 32);
        result = prime * result + totalGames;
        return result;
    }

    /**
     * @return the recent
     */
    public boolean isRecent() {
        return recent;
    }

    /**
     * @param accountId
     *        the accountId to set
     */
    public void setAccountId(final long accountId) {
        this.accountId = accountId;
    }

    /**
     * @param champions
     *        the champions to set
     */
    public void setChampions(final Set<Integer> champions) {
        this.champions = champions;
    }

    /**
     * @param endIndex
     *        the endIndex to set
     */
    public void setEndIndex(final int endIndex) {
        this.endIndex = endIndex;
    }

    /**
     * @param endTime
     *        the endTime to set
     */
    public void setEndTime(final long endTime) {
        this.endTime = endTime;
    }

    /**
     * @param matches
     *        the matches to set
     */
    public void setMatches(final List<MatchReference> matches) {
        this.matches = matches;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param queues
     *        the queues to set
     */
    public void setQueues(final Set<Integer> queues) {
        this.queues = queues;
    }

    /**
     * @param recent
     *        the recent to set
     */
    public void setRecent(final boolean recent) {
        this.recent = recent;
    }

    /**
     * @param seasons
     *        the seasons to set
     */
    public void setSeasons(final Set<Integer> seasons) {
        this.seasons = seasons;
    }

    /**
     * @param startIndex
     *        the startIndex to set
     */
    public void setStartIndex(final int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @param startTime
     *        the startTime to set
     */
    public void setStartTime(final long startTime) {
        this.startTime = startTime;
    }

    /**
     * @param totalGames
     *        the totalGames to set
     */
    public void setTotalGames(final int totalGames) {
        this.totalGames = totalGames;
    }
}
