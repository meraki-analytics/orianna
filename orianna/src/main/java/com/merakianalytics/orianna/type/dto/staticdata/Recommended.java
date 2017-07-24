package com.merakianalytics.orianna.type.dto.staticdata;

import java.util.List;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Recommended extends DataObject {
    private static final long serialVersionUID = -1289817351999848211L;
    private List<Block> blocks;
    private String map, champion, title, mode, type;
    private boolean priority;

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
        final Recommended other = (Recommended)obj;
        if(blocks == null) {
            if(other.blocks != null) {
                return false;
            }
        } else if(!blocks.equals(other.blocks)) {
            return false;
        }
        if(champion == null) {
            if(other.champion != null) {
                return false;
            }
        } else if(!champion.equals(other.champion)) {
            return false;
        }
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
     * @return the blocks
     */
    public List<Block> getBlocks() {
        return blocks;
    }

    /**
     * @return the champion
     */
    public String getChampion() {
        return champion;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (blocks == null ? 0 : blocks.hashCode());
        result = prime * result + (champion == null ? 0 : champion.hashCode());
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
     * @param blocks
     *        the blocks to set
     */
    public void setBlocks(final List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * @param champion
     *        the champion to set
     */
    public void setChampion(final String champion) {
        this.champion = champion;
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
