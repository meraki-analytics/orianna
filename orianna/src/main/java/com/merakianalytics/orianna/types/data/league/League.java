package com.merakianalytics.orianna.types.data.league;

import com.merakianalytics.orianna.types.data.CoreData;

public class League extends CoreData.ListProxy<LeagueEntry> {
    private static final long serialVersionUID = -238522505205131178L;
    private String name, id, platform, queue, tier;

    public League() {
        super();
    }

    public League(final int initialCapacity) {
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
        final League other = (League)obj;
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        } else if(!id.equals(other.id)) {
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
     * @return the id
     */
    public String getId() {
        return id;
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
        int result = super.hashCode();
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (tier == null ? 0 : tier.hashCode());
        return result;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final String id) {
        this.id = id;
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
