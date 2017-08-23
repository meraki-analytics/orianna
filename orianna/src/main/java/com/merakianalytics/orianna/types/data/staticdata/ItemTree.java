package com.merakianalytics.orianna.types.data.staticdata;

import java.util.List;

import com.merakianalytics.orianna.types.data.CoreData;

public class ItemTree extends CoreData {
    private static final long serialVersionUID = 6198799968608387701L;
    private String header;
    private List<String> tags;

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
