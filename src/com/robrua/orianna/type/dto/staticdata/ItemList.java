package com.robrua.orianna.type.dto.staticdata;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "itemlist")
public class ItemList extends OriannaDto {
    private static final long serialVersionUID = 6176599592434124709L;
    @OneToOne(cascade = CascadeType.ALL)
    private BasicData basic;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, Item> data;

    @Id
    private final long dbId = 0;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Group> groups;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemTree> tree;

    private String type, version;

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
        if(!(obj instanceof ItemList)) {
            return false;
        }
        final ItemList other = (ItemList)obj;
        if(basic == null) {
            if(other.basic != null) {
                return false;
            }
        }
        else if(!basic.equals(other.basic)) {
            return false;
        }
        if(data == null) {
            if(other.data != null) {
                return false;
            }
        }
        else if(!data.equals(other.data)) {
            return false;
        }
        if(groups == null) {
            if(other.groups != null) {
                return false;
            }
        }
        else if(!groups.equals(other.groups)) {
            return false;
        }
        if(tree == null) {
            if(other.tree != null) {
                return false;
            }
        }
        else if(!tree.equals(other.tree)) {
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
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        }
        else if(!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    /**
     * @return the basic
     */
    public BasicData getBasic() {
        return basic;
    }

    /**
     * @return the data
     */
    public Map<String, Item> getData() {
        return data;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * @return the tree
     */
    public List<ItemTree> getTree() {
        return tree;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (basic == null ? 0 : basic.hashCode());
        result = prime * result + (data == null ? 0 : data.hashCode());
        result = prime * result + (groups == null ? 0 : groups.hashCode());
        result = prime * result + (tree == null ? 0 : tree.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param basic
     *            the basic to set
     */
    public void setBasic(final BasicData basic) {
        this.basic = basic;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final Map<String, Item> data) {
        this.data = data;
    }

    /**
     * @param groups
     *            the groups to set
     */
    public void setGroups(final List<Group> groups) {
        this.groups = groups;
    }

    /**
     * @param tree
     *            the tree to set
     */
    public void setTree(final List<ItemTree> tree) {
        this.tree = tree;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ItemList [basic=" + basic + ", data=" + data + ", groups=" + groups + ", tree=" + tree + ", type=" + type + ", version=" + version + "]";
    }
}
