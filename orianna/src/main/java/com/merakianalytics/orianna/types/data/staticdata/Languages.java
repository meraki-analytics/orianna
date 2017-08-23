package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.CoreData;

public class Languages extends CoreData.ListProxy<String> {
    private static final long serialVersionUID = -1864233446419957632L;
    private Platform platform;

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
        final Languages other = (Languages)obj;
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
