package com.merakianalytics.orianna.types.data.staticdata;

import java.util.List;

import com.merakianalytics.orianna.types.data.CoreData;

public class SpellVariables extends CoreData {
    private static final long serialVersionUID = 448643445379458097L;
    private List<Double> coefficients;
    private String ranksWith, dynamic, link, key;

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
        final SpellVariables other = (SpellVariables)obj;
        if(coefficients == null) {
            if(other.coefficients != null) {
                return false;
            }
        } else if(!coefficients.equals(other.coefficients)) {
            return false;
        }
        if(dynamic == null) {
            if(other.dynamic != null) {
                return false;
            }
        } else if(!dynamic.equals(other.dynamic)) {
            return false;
        }
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        } else if(!key.equals(other.key)) {
            return false;
        }
        if(link == null) {
            if(other.link != null) {
                return false;
            }
        } else if(!link.equals(other.link)) {
            return false;
        }
        if(ranksWith == null) {
            if(other.ranksWith != null) {
                return false;
            }
        } else if(!ranksWith.equals(other.ranksWith)) {
            return false;
        }
        return true;
    }

    /**
     * @return the coefficients
     */
    public List<Double> getCoefficients() {
        return coefficients;
    }

    /**
     * @return the dynamic
     */
    public String getDynamic() {
        return dynamic;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @return the ranksWith
     */
    public String getRanksWith() {
        return ranksWith;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (coefficients == null ? 0 : coefficients.hashCode());
        result = prime * result + (dynamic == null ? 0 : dynamic.hashCode());
        result = prime * result + (key == null ? 0 : key.hashCode());
        result = prime * result + (link == null ? 0 : link.hashCode());
        result = prime * result + (ranksWith == null ? 0 : ranksWith.hashCode());
        return result;
    }

    /**
     * @param coefficients
     *        the coefficients to set
     */
    public void setCoefficients(final List<Double> coefficients) {
        this.coefficients = coefficients;
    }

    /**
     * @param dynamic
     *        the dynamic to set
     */
    public void setDynamic(final String dynamic) {
        this.dynamic = dynamic;
    }

    /**
     * @param key
     *        the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @param link
     *        the link to set
     */
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * @param ranksWith
     *        the ranksWith to set
     */
    public void setRanksWith(final String ranksWith) {
        this.ranksWith = ranksWith;
    }
}
