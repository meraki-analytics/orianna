package com.merakianalytics.orianna.types.data.staticdata;

import java.util.List;

import com.merakianalytics.orianna.types.data.CoreData;

public class ItemTree extends CoreData {
    private static final long serialVersionUID = 6198799968608387701L;
    private String header;
    private List<String> tags;

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
        final ItemTree other = (ItemTree)obj;
        if(header == null) {
            if(other.header != null) {
                return false;
            }
        } else if(!header.equals(other.header)) {
            return false;
        }
        if(tags == null) {
            if(other.tags != null) {
                return false;
            }
        } else if(!tags.equals(other.tags)) {
            return false;
        }
        return true;
    }

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (header == null ? 0 : header.hashCode());
        result = prime * result + (tags == null ? 0 : tags.hashCode());
        return result;
    }

    /**
     * @param header
     *        the header to set
     */
    public void setHeader(final String header) {
        this.header = header;
    }

    /**
     * @param tags
     *        the tags to set
     */
    public void setTags(final List<String> tags) {
        this.tags = tags;
    }
}
