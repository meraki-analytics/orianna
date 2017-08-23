package com.merakianalytics.orianna.types.data.match;

import java.util.Set;

import org.joda.time.DateTime;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.data.CoreData;

public class MatchList extends CoreData.ListProxy<MatchReference> {
    private static final long serialVersionUID = 1738736473942367646L;
    private long accountId;
    private Set<Integer> champions;
    private Platform platform;
    private Set<Queue> queues;
    private boolean recent;
    private Set<Season> seasons;
    private int startIndex, endIndex;
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
        if(endTime == null) {
            if(other.endTime != null) {
                return false;
            }
        } else if(!endTime.equals(other.endTime)) {
            return false;
        }
        if(platform != other.platform) {
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
    public DateTime getEndTime() {
        return endTime;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the queues
     */
    public Set<Queue> getQueues() {
        return queues;
    }

    /**
     * @return the seasons
     */
    public Set<Season> getSeasons() {
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
        result = prime * result + (int)(accountId ^ accountId >>> 32);
        result = prime * result + (champions == null ? 0 : champions.hashCode());
        result = prime * result + endIndex;
        result = prime * result + (endTime == null ? 0 : endTime.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (queues == null ? 0 : queues.hashCode());
        result = prime * result + (recent ? 1231 : 1237);
        result = prime * result + (seasons == null ? 0 : seasons.hashCode());
        result = prime * result + startIndex;
        result = prime * result + (startTime == null ? 0 : startTime.hashCode());
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
    public void setEndTime(final DateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param queues
     *        the queues to set
     */
    public void setQueues(final Set<Queue> queues) {
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
    public void setSeasons(final Set<Season> seasons) {
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
