package com.merakianalytics.orianna.types.data.match;

import org.joda.time.Duration;

import com.merakianalytics.orianna.types.data.CoreData;

public class Timeline extends CoreData.ListProxy<Frame> {
    private static final long serialVersionUID = -6737503541733571588L;
    private long id;
    private Duration interval;
    private String platform;

    public Timeline() {
        super();
    }

    public Timeline(final int initialCapacity) {
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
        final Timeline other = (Timeline)obj;
        if(id != other.id) {
            return false;
        }
        if(interval == null) {
            if(other.interval != null) {
                return false;
            }
        } else if(!interval.equals(other.interval)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the interval
     */
    public Duration getInterval() {
        return interval;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + (interval == null ? 0 : interval.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        return result;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * @param interval
     *        the interval to set
     */
    public void setInterval(final Duration interval) {
        this.interval = interval;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
}
