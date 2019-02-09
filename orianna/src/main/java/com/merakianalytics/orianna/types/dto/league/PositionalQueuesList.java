package com.merakianalytics.orianna.types.dto.league;

import com.merakianalytics.orianna.types.dto.DataObject;

public class PositionalQueuesList extends DataObject.ListProxy<String> {
    private static final long serialVersionUID = -2684536089075701179L;
    private String platform;

    public PositionalQueuesList() {
        super();
    }

    public PositionalQueuesList(final int initialCapacity) {
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
        final PositionalQueuesList other = (PositionalQueuesList)obj;
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
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        return result;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
}
