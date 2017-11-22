package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class RecommendedItems extends CoreData.ListProxy<ItemSet> {
    private static final long serialVersionUID = 3130193939924356290L;
    private boolean priority;
    private String title, type, map, mode;

    public RecommendedItems() {
        super();
    }

    public RecommendedItems(final int initialCapacity) {
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
        final RecommendedItems other = (RecommendedItems)obj;
        if(map == null) {
            if(other.map != null) {
                return false;
            }
        } else if(!map.equals(other.map)) {
            return false;
        }
        if(mode == null) {
            if(other.mode != null) {
                return false;
            }
        } else if(!mode.equals(other.mode)) {
            return false;
        }
        if(priority != other.priority) {
            return false;
        }
        if(title == null) {
            if(other.title != null) {
                return false;
            }
        } else if(!title.equals(other.title)) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        } else if(!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    /**
     * @return the map
     */
    public String getMap() {
        return map;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (map == null ? 0 : map.hashCode());
        result = prime * result + (mode == null ? 0 : mode.hashCode());
        result = prime * result + (priority ? 1231 : 1237);
        result = prime * result + (title == null ? 0 : title.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    /**
     * @return the priority
     */
    public boolean isPriority() {
        return priority;
    }

    /**
     * @param map
     *        the map to set
     */
    public void setMap(final String map) {
        this.map = map;
    }

    /**
     * @param mode
     *        the mode to set
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * @param priority
     *        the priority to set
     */
    public void setPriority(final boolean priority) {
        this.priority = priority;
    }

    /**
     * @param title
     *        the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }
}
