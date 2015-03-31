package com.robrua.orianna.type.dto.staticdata;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "metadata")
public class Passive extends OriannaDto {
    private static final long serialVersionUID = -217432027804125776L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @Lob
    private String description, sanitizedDescription;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    private String name;

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
        if(!(obj instanceof Passive)) {
            return false;
        }
        final Passive other = (Passive)obj;
        if(description == null) {
            if(other.description != null) {
                return false;
            }
        }
        else if(!description.equals(other.description)) {
            return false;
        }
        if(image == null) {
            if(other.image != null) {
                return false;
            }
        }
        else if(!image.equals(other.image)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        }
        else if(!name.equals(other.name)) {
            return false;
        }
        if(sanitizedDescription == null) {
            if(other.sanitizedDescription != null) {
                return false;
            }
        }
        else if(!sanitizedDescription.equals(other.sanitizedDescription)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the sanitizedDescription
     */
    public String getSanitizedDescription() {
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
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (sanitizedDescription == null ? 0 : sanitizedDescription.hashCode());
        return result;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param image
     *            the image to set
     */
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param sanitizedDescription
     *            the sanitizedDescription to set
     */
    public void setSanitizedDescription(final String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Passive [description=" + description + ", name=" + name + ", sanitizedDescription=" + sanitizedDescription + ", image=" + image + "]";
    }
}
