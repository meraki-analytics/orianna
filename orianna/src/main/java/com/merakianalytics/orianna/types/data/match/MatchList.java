package com.merakianalytics.orianna.types.data.match;

import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.merakianalytics.orianna.types.data.CoreData;

public class MatchList extends CoreData.ListProxy<MatchReference> {
    private static final long serialVersionUID = -9207577178873187266L;
    private Set<Integer> champions, queues, seasons;
    private Duration maxTimeRange;
    private String platform, accountId;
    private int startIndex, endIndex, maxSize;
    private DateTime startTime, endTime;

    public MatchList() {
        super();
    }

    public MatchList(final int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(!super.equals(obj)) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final MatchList other = (MatchList)obj;
        if(accountId == null) {
            if(other.accountId != null) {
                return false;
            }
        } else if(!accountId.equals(other.accountId)) {
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
        if(endTime == null) {
            if(other.endTime != null) {
                return false;
            }
        } else if(!endTime.equals(other.endTime)) {
            return false;
        }
        if(maxSize != other.maxSize) {
            return false;
        }
        if(maxTimeRange == null) {
            if(other.maxTimeRange != null) {
                return false;
            }
        } else if(!maxTimeRange.equals(other.maxTimeRange)) {
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
        if(startTime == null) {
            if(other.startTime != null) {
                return false;
            }
        } else if(!startTime.equals(other.startTime)) {
            return false;
        }
        return true;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
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
    public DateTime getEndTime() {
        return endTime;
    }

    /**
     * @return the maxSize
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * @return the maxTimeRange
     */
    public Duration getMaxTimeRange() {
        return maxTimeRange;
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
    public DateTime getStartTime() {
        return startTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (accountId == null ? 0 : accountId.hashCode());
        result = prime * result + (champions == null ? 0 : champions.hashCode());
        result = prime * result + endIndex;
        result = prime * result + (endTime == null ? 0 : endTime.hashCode());
        result = prime * result + maxSize;
        result = prime * result + (maxTimeRange == null ? 0 : maxTimeRange.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (queues == null ? 0 : queues.hashCode());
        result = prime * result + (seasons == null ? 0 : seasons.hashCode());
        result = prime * result + startIndex;
        result = prime * result + (startTime == null ? 0 : startTime.hashCode());
        return result;
    }

    /**
     * @param accountId
     *        the accountId to set
     */
    public void setAccountId(final String accountId) {
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
    public void setEndTime(final DateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @param maxSize
     *        the maxSize to set
     */
    public void setMaxSize(final int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * @param maxTimeRange
     *        the maxTimeRange to set
     */
    public void setMaxTimeRange(final Duration maxTimeRange) {
        this.maxTimeRange = maxTimeRange;
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
    public void setStartTime(final DateTime startTime) {
        this.startTime = startTime;
    }
}
