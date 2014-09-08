package com.robrua.orianna.type.staticdata;

import java.io.Serializable;

public class Passive implements Serializable {
    private static final long serialVersionUID = -5400734147807812153L;
    public final String description, name, sanitizedDescription;
    public final Image image;

    public Passive(final String description, final String name, final String sanitizedDescription, final Image image) {
        this.description = description;
        this.name = name;
        this.sanitizedDescription = sanitizedDescription;
        this.image = image;
    }

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (sanitizedDescription == null ? 0 : sanitizedDescription.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
