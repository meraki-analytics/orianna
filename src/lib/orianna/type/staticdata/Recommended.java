package lib.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

public class Recommended implements Serializable {
    private static final long serialVersionUID = 7313482686022025895L;
    public final List<Block> blocks;
    public final String champion, map, mode, title, type;
    public final Boolean priority;

    public Recommended(final List<Block> blocks, final String champion, final String map, final String mode, final String title, final String type,
            final Boolean priority) {
        this.blocks = blocks;
        this.champion = champion;
        this.map = map;
        this.mode = mode;
        this.title = title;
        this.type = type;
        this.priority = priority;
    }

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

    @Override
    public String toString() {
        return title;
    }
}
