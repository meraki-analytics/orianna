package com.robrua.orianna.type.dto.staticdata;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity(name = "staticdata.Mastery")
@Table(name = "mastery")
public class Mastery extends OriannaDto {
    private static final long serialVersionUID = -4569475685529096111L;
    @ElementCollection
    @CollectionTable(name = "mastery_description", joinColumns = @JoinColumn(name = "mastery_id") )
    private List<String> description;
    @Id
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    private String masteryTree, name, prereq;

    private Integer ranks;

    @ElementCollection
    @CollectionTable(name = "mastery_sanitizeddescription", joinColumns = @JoinColumn(name = "mastery_id") )
    private List<String> sanitizedDescription;

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
        if(!(obj instanceof Mastery)) {
            return false;
        }
        final Mastery other = (Mastery)obj;
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        }
        else if(!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Integer.class)) {
            return "id";
        }
        if(keyType.equals(String.class)) {
            return "name";
        }
        return null;
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
    public Integer getId() {
        return id;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
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
     * @return the prereq
     */
    public String getPrereq() {
        return prereq;
    }

    /**
     * @return the ranks
     */
    public Integer getRanks() {
        return ranks;
    }

    /**
     * @return the sanitizedDescription
     */
    public List<String> getSanitizedDescription() {
        return sanitizedDescription;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final List<String> description) {
        this.description = description;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @param image
     *            the image to set
     */
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * @param masteryTree
     *            the masteryTree to set
     */
    public void setMasteryTree(final String masteryTree) {
        this.masteryTree = masteryTree;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param prereq
     *            the prereq to set
     */
    public void setPrereq(final String prereq) {
        this.prereq = prereq;
    }

    /**
     * @param ranks
     *            the ranks to set
     */
    public void setRanks(final Integer ranks) {
        this.ranks = ranks;
    }

    /**
     * @param sanitizedDescription
     *            the sanitizedDescription to set
     */
    public void setSanitizedDescription(final List<String> sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Mastery [id=" + id + ", ranks=" + ranks + ", description=" + description + ", sanitizedDescription=" + sanitizedDescription + ", image=" + image
                + ", masteryTree=" + masteryTree + ", name=" + name + ", prereq=" + prereq + "]";
    }
}
