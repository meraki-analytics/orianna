package com.merakianalytics.orianna.types.dto.staticdata;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.orianna.types.dto.DataObject;

public class ItemList extends DataObject {
    private static final long serialVersionUID = 3935373185410370517L;
    private Map<String, Item> data;
    private List<Group> groups;
    private Set<String> includedData;
    private List<ItemTree> tree;
    private String version, locale, platform, type;

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
        final ItemList other = (ItemList)obj;
        if(data == null) {
            if(other.data != null) {
                return false;
            }
        } else if(!data.equals(other.data)) {
            return false;
        }
        if(groups == null) {
            if(other.groups != null) {
                return false;
            }
        } else if(!groups.equals(other.groups)) {
            return false;
        }
        if(includedData == null) {
            if(other.includedData != null) {
                return false;
            }
        } else if(!includedData.equals(other.includedData)) {
            return false;
        }
        if(locale == null) {
            if(other.locale != null) {
                return false;
            }
        } else if(!locale.equals(other.locale)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(tree == null) {
            if(other.tree != null) {
                return false;
            }
        } else if(!tree.equals(other.tree)) {
            return false;
        }
        if(type == null) {
            if(other.type != null) {
                return false;
            }
        } else if(!type.equals(other.type)) {
            return false;
        }
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        } else if(!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    /**
     * @return the data
     */
    public Map<String, Item> getData() {
        return data;
    }

    /**
     * @return the groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * @return the includedData
     */
    public Set<String> getIncludedData() {
        return includedData;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
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
        result = prime * result + (data == null ? 0 : data.hashCode());
        result = prime * result + (groups == null ? 0 : groups.hashCode());
        result = prime * result + (includedData == null ? 0 : includedData.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (tree == null ? 0 : tree.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param data
     *        the data to set
     */
    public void setData(final Map<String, Item> data) {
        this.data = data;
    }

    /**
     * @param groups
     *        the groups to set
     */
    public void setGroups(final List<Group> groups) {
        this.groups = groups;
    }

    /**
     * @param includedData
     *        the includedData to set
     */
    public void setIncludedData(final Set<String> includedData) {
        this.includedData = includedData;
    }

    /**
     * @param locale
     *        the locale to set
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param tree
     *        the tree to set
     */
    public void setTree(final List<ItemTree> tree) {
        this.tree = tree;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
