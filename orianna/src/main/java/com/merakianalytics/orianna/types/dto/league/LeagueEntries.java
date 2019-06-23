package com.merakianalytics.orianna.types.dto.league;

import com.merakianalytics.orianna.types.dto.DataObject;

public class LeagueEntries extends DataObject.SetProxy<LeagueEntry> {
    private static final long serialVersionUID = 6598031825026805624L;
    private String division, tier, queue, platform;
    private int page;

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
        final LeagueEntries other = (LeagueEntries)obj;
        if(division == null) {
            if(other.division != null) {
                return false;
            }
        } else if(!division.equals(other.division)) {
            return false;
        }
        if(page != other.page) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(queue == null) {
            if(other.queue != null) {
                return false;
            }
        } else if(!queue.equals(other.queue)) {
            return false;
        }
        if(tier == null) {
            if(other.tier != null) {
                return false;
            }
        } else if(!tier.equals(other.tier)) {
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
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the queue
     */
    public String getQueue() {
        return queue;
    }

    /**
     * @return the tier
     */
    public String getTier() {
        return tier;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (division == null ? 0 : division.hashCode());
        result = prime * result + page;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (tier == null ? 0 : tier.hashCode());
        return result;
    }

    /**
     * @param division
     *        the division to set
     */
    public void setDivision(final String division) {
        this.division = division;
    }

    /**
     * @param page
     *        the page to set
     */
    public void setPage(final int page) {
        this.page = page;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param queue
     *        the queue to set
     */
    public void setQueue(final String queue) {
        this.queue = queue;
    }

    /**
     * @param tier
     *        the tier to set
     */
    public void setTier(final String tier) {
        this.tier = tier;
    }
}
