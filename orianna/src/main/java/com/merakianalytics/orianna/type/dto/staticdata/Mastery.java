package com.merakianalytics.orianna.type.dto.staticdata;

import java.util.List;
import java.util.Set;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Mastery extends DataObject {
    private static final long serialVersionUID = 8545285298490790563L;
    private Image image;
    private Set<String> includedData;
    private String prereq, masteryTree, name, platform, version, locale;
    private int ranks, id;
    private List<String> sanitizedDescription, description;

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
        final Mastery other = (Mastery)obj;
        if(description == null) {
            if(other.description != null) {
                return false;
            }
        } else if(!description.equals(other.description)) {
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
        if(masteryTree == null) {
            if(other.masteryTree != null) {
                return false;
            }
        } else if(!masteryTree.equals(other.masteryTree)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(prereq == null) {
            if(other.prereq != null) {
                return false;
            }
        } else if(!prereq.equals(other.prereq)) {
            return false;
        }
        if(ranks != other.ranks) {
            return false;
        }
        if(sanitizedDescription == null) {
            if(other.sanitizedDescription != null) {
                return false;
            }
        } else if(!sanitizedDescription.equals(other.sanitizedDescription)) {
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
     * @return the description
     */
    public List<String> getDescription() {
        return description;
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
     * @return the masteryTree
     */
    public String getMasteryTree() {
        return masteryTree;
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
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the prereq
     */
    public String getPrereq() {
        return prereq;
    }

    /**
     * @return the ranks
     */
    public int getRanks() {
        return ranks;
    }

    /**
     * @return the sanitizedDescription
     */
    public List<String> getSanitizedDescription() {
        return sanitizedDescription;
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
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + id;
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (includedData == null ? 0 : includedData.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + (masteryTree == null ? 0 : masteryTree.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (prereq == null ? 0 : prereq.hashCode());
        result = prime * result + ranks;
        result = prime * result + (sanitizedDescription == null ? 0 : sanitizedDescription.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param description
     *        the description to set
     */
    public void setDescription(final List<String> description) {
        this.description = description;
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
     * @param masteryTree
     *        the masteryTree to set
     */
    public void setMasteryTree(final String masteryTree) {
        this.masteryTree = masteryTree;
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
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param prereq
     *        the prereq to set
     */
    public void setPrereq(final String prereq) {
        this.prereq = prereq;
    }

    /**
     * @param ranks
     *        the ranks to set
     */
    public void setRanks(final int ranks) {
        this.ranks = ranks;
    }

    /**
     * @param sanitizedDescription
     *        the sanitizedDescription to set
     */
    public void setSanitizedDescription(final List<String> sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
