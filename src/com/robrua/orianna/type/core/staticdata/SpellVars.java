package com.robrua.orianna.type.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class SpellVars extends OriannaObject<com.robrua.orianna.type.dto.staticdata.SpellVars> {
    private static final long serialVersionUID = -4227459097754405106L;
    private List<Double> coeffs;

    /**
     * @param data
     *            the underlying dto
     */
    public SpellVars(final com.robrua.orianna.type.dto.staticdata.SpellVars data) {
        super(data, com.robrua.orianna.type.dto.staticdata.SpellVars.class);
    }

    /**
     * Coefficients
     *
     * @return coefficients
     */
    public List<Double> getCoeffs() {
        if(coeffs == null) {
            coeffs = new ArrayList<>();
            coeffs.addAll(data.getCoeff());
        }

        return Collections.unmodifiableList(coeffs);
    }

    /**
     * Dyn
     *
     * @return dyn
     */
    public String getDyn() {
        return super.getString(data.getDyn());
    }

    /**
     * Key
     *
     * @return key
     */
    public String getKey() {
        return super.getString(data.getKey());
    }

    /**
     * Link
     *
     * @return link
     */
    public String getLink() {
        return super.getString(data.getLink());
    }

    /**
     * Ranks with
     *
     * @return ranks with
     */
    public String getRanksWith() {
        return super.getString(data.getRanksWith());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SpellVars";
    }
}
