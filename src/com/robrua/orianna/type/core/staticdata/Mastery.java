package com.robrua.orianna.type.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class Mastery extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Mastery> {
    private static final long serialVersionUID = -5248806360824691743L;
    private List<String> description, sanitizedDescription;
    private Image image;

    /**
     * @param data
     *            the underlying dto
     */
    public Mastery(final com.robrua.orianna.type.dto.staticdata.Mastery data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Mastery.class);
    }

    /**
     * Description
     *
     * @return description
     */
    public List<String> getDescription() {
        if(description == null) {
            description = new ArrayList<>();
            description.addAll(data.getDescription());
        }

        return Collections.unmodifiableList(description);
    }

    /**
     * ID
     *
     * @return ID
     */
    public long getID() {
        return super.getInteger(data.getId());
    }

    public Image getImage() {
        if(image == null) {
            image = new Image(data.getImage());
        }

        return image;
    }

    /**
     * Name
     *
     * @return name
     */
    public String getName() {
        return super.getString(data.getName()).trim();
    }

    /**
     * Prereq name
     *
     * @return prereq name
     */
    public String getPrereq() {
        return super.getString(data.getPrereq());
    }

    /**
     * Ranks
     *
     * @return ranks
     */
    public int getRanks() {
        return super.getInteger(data.getRanks());
    }

    /**
     * Sanitized description
     *
     * @return sanitized description
     */
    public List<String> getSanitizedDescription() {
        if(sanitizedDescription == null) {
            sanitizedDescription = new ArrayList<>();
            sanitizedDescription.addAll(data.getSanitizedDescription());
        }

        return Collections.unmodifiableList(sanitizedDescription);
    }

    /**
     * Type
     *
     * @return type
     */
    public MasteryType getType() {
        return MasteryType.valueOf(super.getString(data.getMasteryTree()));
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getName();
    }
}
