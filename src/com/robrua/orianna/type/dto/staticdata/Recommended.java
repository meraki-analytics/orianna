package com.robrua.orianna.type.dto.staticdata;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "recommended")
public class Recommended extends OriannaDto {
    private static final long serialVersionUID = -7097240520770298303L;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Block> blocks;
    private String champion, map, mode, title, type;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Boolean priority;

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
        if(!(obj instanceof Recommended)) {
            return false;
        }
        final Recommended other = (Recommended)obj;
        if(blocks == null) {
            if(other.blocks != null) {
                return false;
            }
        }
        else if(!blocks.equals(other.blocks)) {
            return false;
        }
        if(champion == null) {
            if(other.champion != null) {
                return false;
            }
        }
        else if(!champion.equals(other.champion)) {
            return false;
        }
        if(map == null) {
            if(other.map != null) {
                return false;
            }
        }
        else if(!map.equals(other.map)) {
            return false;
        }
        if(mode == null) {
            if(other.mode != null) {
                return false;
            }
        }
        else if(!mode.equals(other.mode)) {
            return false;
        }
        if(priority == null) {
            if(other.priority != null) {
                return false;
            }
        }
        else if(!priority.equals(other.priority)) {
            return false;
        }
        if(title == null) {
            if(other.title != null) {
                return false;
            }
        }
        else if(!title.equals(other.title)) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        }
        else if(!type.equals(other.type)) {
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

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
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
     * @return the priority
     */
    public Boolean getPriority() {
        return priority;
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
        result = prime * result + (priority == null ? 0 : priority.hashCode());
        result = prime * result + (title == null ? 0 : title.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    /**
     * @param blocks
     *            the blocks to set
     */
    public void setBlocks(final List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * @param champion
     *            the champion to set
     */
    public void setChampion(final String champion) {
        this.champion = champion;
    }

    /**
     * @param map
     *            the map to set
     */
    public void setMap(final String map) {
        this.map = map;
    }

    /**
     * @param mode
     *            the mode to set
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(final Boolean priority) {
        this.priority = priority;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Recommended [blocks=" + blocks + ", champion=" + champion + ", map=" + map + ", mode=" + mode + ", title=" + title + ", type=" + type
                + ", priority=" + priority + "]";
    }
}
