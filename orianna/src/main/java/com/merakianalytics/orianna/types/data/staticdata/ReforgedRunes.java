package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class ReforgedRunes extends CoreData.ListProxy<ReforgedRune> {
    private static final long serialVersionUID = -1084713550962362034L;
    private String platform, version, locale;
    private ReforgedRuneTree tree;

    public ReforgedRunes() {
        super();
    }

    public ReforgedRunes(final int initialCapacity) {
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
        final ReforgedRunes other = (ReforgedRunes)obj;
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
    public ReforgedRuneTree getTree() {
        return tree;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (tree == null ? 0 : tree.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
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
    public void setTree(final ReforgedRuneTree tree) {
        this.tree = tree;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
