package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.CoreData;

public class Versions extends CoreData.ListProxy<String> {
    private static final long serialVersionUID = 3141786492164530971L;
    private Platform platform;

    public Versions() {
        super();
    }

    public Versions(final int initialCapacity) {
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
        final Versions other = (Versions)obj;
        if(platform != other.platform) {
            return false;
        }
        return true;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
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
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }
}
