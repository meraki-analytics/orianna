package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class ReforgedRuneTree extends CoreData {
    private static final long serialVersionUID = -7891267532058941565L;
    private ReforgedRunePath domination, inspriation, precision, resolve, sorcery;

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
        final ReforgedRuneTree other = (ReforgedRuneTree)obj;
        if(domination == null) {
            if(other.domination != null) {
                return false;
            }
        } else if(!domination.equals(other.domination)) {
            return false;
        }
        if(inspriation == null) {
            if(other.inspriation != null) {
                return false;
            }
        } else if(!inspriation.equals(other.inspriation)) {
            return false;
        }
        if(precision == null) {
            if(other.precision != null) {
                return false;
            }
        } else if(!precision.equals(other.precision)) {
            return false;
        }
        if(resolve == null) {
            if(other.resolve != null) {
                return false;
            }
        } else if(!resolve.equals(other.resolve)) {
            return false;
        }
        if(sorcery == null) {
            if(other.sorcery != null) {
                return false;
            }
        } else if(!sorcery.equals(other.sorcery)) {
            return false;
        }
        return true;
    }

    /**
     * @return the domination
     */
    public ReforgedRunePath getDomination() {
        return domination;
    }

    /**
     * @return the inspriation
     */
    public ReforgedRunePath getInspiration() {
        return inspriation;
    }

    /**
     * @return the precision
     */
    public ReforgedRunePath getPrecision() {
        return precision;
    }

    /**
     * @return the resolve
     */
    public ReforgedRunePath getResolve() {
        return resolve;
    }

    /**
     * @return the sorcery
     */
    public ReforgedRunePath getSorcery() {
        return sorcery;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (domination == null ? 0 : domination.hashCode());
        result = prime * result + (inspriation == null ? 0 : inspriation.hashCode());
        result = prime * result + (precision == null ? 0 : precision.hashCode());
        result = prime * result + (resolve == null ? 0 : resolve.hashCode());
        result = prime * result + (sorcery == null ? 0 : sorcery.hashCode());
        return result;
    }

    /**
     * @param domination
     *        the domination to set
     */
    public void setDomination(final ReforgedRunePath domination) {
        this.domination = domination;
    }

    /**
     * @param inspriation
     *        the inspriation to set
     */
    public void setInspriation(final ReforgedRunePath inspriation) {
        this.inspriation = inspriation;
    }

    /**
     * @param precision
     *        the precision to set
     */
    public void setPrecision(final ReforgedRunePath precision) {
        this.precision = precision;
    }

    /**
     * @param resolve
     *        the resolve to set
     */
    public void setResolve(final ReforgedRunePath resolve) {
        this.resolve = resolve;
    }

    /**
     * @param sorcery
     *        the sorcery to set
     */
    public void setSorcery(final ReforgedRunePath sorcery) {
        this.sorcery = sorcery;
    }
}
