package com.merakianalytics.orianna.types.data.staticdata;

import java.util.List;
import java.util.Set;

import com.merakianalytics.orianna.types.common.MasteryTree;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.CoreData;

public class Mastery extends CoreData {
    private static final long serialVersionUID = 5777091654319772826L;
    private List<String> descriptions, sanitizedDescriptions;
    private Image image;
    private Set<String> includedData;
    private String name, version, locale;
    private Platform platform;
    private int prerequisite, id, points;
    private MasteryTree tree;

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
        final Mastery other = (Mastery)obj;
        if(descriptions == null) {
            if(other.descriptions != null) {
                return false;
            }
        } else if(!descriptions.equals(other.descriptions)) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(image == null) {
            if(other.image != null) {
                return false;
            }
        } else if(!image.equals(other.image)) {
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
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(platform != other.platform) {
            return false;
        }
        if(points != other.points) {
            return false;
        }
        if(prerequisite != other.prerequisite) {
            return false;
        }
        if(sanitizedDescriptions == null) {
            if(other.sanitizedDescriptions != null) {
                return false;
            }
        } else if(!sanitizedDescriptions.equals(other.sanitizedDescriptions)) {
            return false;
        }
        if(tree != other.tree) {
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
     * @return the descriptions
     */
    public List<String> getDescriptions() {
        return descriptions;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return the prerequisite
     */
    public int getPrerequisite() {
        return prerequisite;
    }

    /**
     * @return the sanitizedDescriptions
     */
    public List<String> getSanitizedDescriptions() {
        return sanitizedDescriptions;
    }

    /**
     * @return the tree
     */
    public MasteryTree getTree() {
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
        int result = 1;
        result = prime * result + (descriptions == null ? 0 : descriptions.hashCode());
        result = prime * result + id;
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (includedData == null ? 0 : includedData.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + points;
        result = prime * result + prerequisite;
        result = prime * result + (sanitizedDescriptions == null ? 0 : sanitizedDescriptions.hashCode());
        result = prime * result + (tree == null ? 0 : tree.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param descriptions
     *        the descriptions to set
     */
    public void setDescriptions(final List<String> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param image
     *        the image to set
     */
    public void setImage(final Image image) {
        this.image = image;
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
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param points
     *        the points to set
     */
    public void setPoints(final int points) {
        this.points = points;
    }

    /**
     * @param prerequisite
     *        the prerequisite to set
     */
    public void setPrerequisite(final int prerequisite) {
        this.prerequisite = prerequisite;
    }

    /**
     * @param sanitizedDescriptions
     *        the sanitizedDescriptions to set
     */
    public void setSanitizedDescriptions(final List<String> sanitizedDescriptions) {
        this.sanitizedDescriptions = sanitizedDescriptions;
    }

    /**
     * @param tree
     *        the tree to set
     */
    public void setTree(final MasteryTree tree) {
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
