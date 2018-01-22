package com.merakianalytics.orianna.types.dto.staticdata;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class ReforgedRunePath extends DataObject {
    private static final long serialVersionUID = 4065548610702730382L;
    private int id;
    private String key, name, icon;
    private List<ReforgedRuneSlot> slots;

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
        final ReforgedRunePath other = (ReforgedRunePath)obj;
        if(icon == null) {
            if(other.icon != null) {
                return false;
            }
        } else if(!icon.equals(other.icon)) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        } else if(!key.equals(other.key)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(slots == null) {
            if(other.slots != null) {
                return false;
            }
        } else if(!slots.equals(other.slots)) {
            return false;
        }
        return true;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the slots
     */
    public List<ReforgedRuneSlot> getSlots() {
        return slots;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (icon == null ? 0 : icon.hashCode());
        result = prime * result + id;
        result = prime * result + (key == null ? 0 : key.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (slots == null ? 0 : slots.hashCode());
        return result;
    }

    /**
     * @param icon
     *        the icon to set
     */
    public void setIcon(final String icon) {
        this.icon = icon;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param key
     *        the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param slots
     *        the slots to set
     */
    public void setSlots(final List<ReforgedRuneSlot> slots) {
        this.slots = slots;
    }
}
