package com.merakianalytics.orianna.types.dto.league;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class LeagueList extends DataObject {
    private static final long serialVersionUID = 4191507968433712121L;
    private List<LeagueItem> entries;
    private String tier, queue, name, platform, leagueId;

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
        final LeagueList other = (LeagueList)obj;
        if(entries == null) {
            if(other.entries != null) {
                return false;
            }
        } else if(!entries.equals(other.entries)) {
            return false;
        }
        if(leagueId == null) {
            if(other.leagueId != null) {
                return false;
            }
        } else if(!leagueId.equals(other.leagueId)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
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
     * @return the entries
     */
    public List<LeagueItem> getEntries() {
        return entries;
    }

    /**
     * @return the leagueId
     */
    public String getLeagueId() {
        return leagueId;
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
        result = prime * result + (entries == null ? 0 : entries.hashCode());
        result = prime * result + (leagueId == null ? 0 : leagueId.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (tier == null ? 0 : tier.hashCode());
        return result;
    }

    /**
     * @param entries
     *        the entries to set
     */
    public void setEntries(final List<LeagueItem> entries) {
        this.entries = entries;
    }

    /**
     * @param leagueId
     *        the leagueId to set
     */
    public void setLeagueId(final String leagueId) {
        this.leagueId = leagueId;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
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
