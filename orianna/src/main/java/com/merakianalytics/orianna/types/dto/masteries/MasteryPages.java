package com.merakianalytics.orianna.types.dto.masteries;

import java.util.Set;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MasteryPages extends DataObject {
    private static final long serialVersionUID = 2944250667111706965L;
    private Set<MasteryPage> pages;
    private String platform;
    private long summonerId;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        final MasteryPages other = (MasteryPages)obj;
        if(pages == null) {
            if(other.pages != null) {
                return false;
            }
        } else if(!pages.equals(other.pages)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(summonerId != other.summonerId) {
            return false;
        }
        return true;
    }

    /**
     * @return the pages
     */
    public Set<MasteryPage> getPages() {
        return pages;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the summonerId
     */
    public long getSummonerId() {
        return summonerId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (pages == null ? 0 : pages.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        return result;
    }

    /**
     * @param pages
     *        the pages to set
     */
    public void setPages(final Set<MasteryPage> pages) {
        this.pages = pages;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final long summonerId) {
        this.summonerId = summonerId;
    }
}
