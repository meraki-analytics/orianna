package com.merakianalytics.orianna.types.data.staticdata;

import org.joda.time.DateTime;

import com.merakianalytics.orianna.types.data.CoreData;

public class Patch extends CoreData {
    private static final long serialVersionUID = -7009192986793480792L;
    private String platform, name;
    private int season = -1;
    private DateTime startTime, endTime;

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
        final Patch other = (Patch)obj;
        if(endTime == null) {
            if(other.endTime != null) {
                return false;
            }
        } else if(!endTime.equals(other.endTime)) {
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
        if(season != other.season) {
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
     * @return the endTime
     */
    public DateTime getEndTime() {
        return endTime;
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
     * @return the season
     */
    public int getSeason() {
        return season;
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
        int result = 1;
        result = prime * result + (endTime == null ? 0 : endTime.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + season;
        result = prime * result + (startTime == null ? 0 : startTime.hashCode());
        return result;
    }

    /**
     * @param endTime
     *        the endTime to set
     */
    public void setEndTime(final DateTime endTime) {
        this.endTime = endTime;
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
     * @param season
     *        the season to set
     */
    public void setSeason(final int season) {
        this.season = season;
    }

    /**
     * @param startTime
     *        the startTime to set
     */
    public void setStartTime(final DateTime startTime) {
        this.startTime = startTime;
    }
}
