package com.robrua.orianna.type.core.summoner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class MasteryPage extends OriannaObject<com.robrua.orianna.type.dto.summoner.MasteryPage> {
    private static final long serialVersionUID = -1422944241793890565L;
    private List<MasteryRank> masteries;

    /**
     * @param data
     *            the underlying dto
     */
    public MasteryPage(final com.robrua.orianna.type.dto.summoner.MasteryPage data) {
        super(data, com.robrua.orianna.type.dto.summoner.MasteryPage.class);
    }

    /**
     * Indicates if the mastery page is the current mastery page
     *
     * @return whether the mastery page is the current mastery page
     */
    public boolean getCurrent() {
        return super.getBoolean(data.getCurrent());
    }

    /**
     * Mastery page ID
     *
     * @return mastery page ID
     */
    public long getID() {
        return super.getLong(data.getId());
    }

    /**
     * Collection of masteries associated with the mastery page
     *
     * @return collection of masteries associated with the mastery page
     */
    public List<MasteryRank> getMasteries() {
        if(masteries == null) {
            masteries = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.summoner.Mastery mastery : data.getMasteries()) {
                masteries.add(new MasteryRank(mastery));
            }
        }

        return Collections.unmodifiableList(masteries);
    }

    /**
     * Mastery page name
     *
     * @return mastery page name
     */
    public String getName() {
        return super.getString(data.getName());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MasteryPage (" + getName() + ")";
    }
}
