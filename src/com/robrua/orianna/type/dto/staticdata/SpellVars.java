package com.robrua.orianna.type.dto.staticdata;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "spellvars")
public class SpellVars extends OriannaDto {
    private static final long serialVersionUID = 3196309764964468106L;
    @ElementCollection
    @CollectionTable(name = "spellvars_coeff", joinColumns = @JoinColumn(name = "spellvars_id") )
    private List<Double> coeff;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private String dyn, link, ranksWith;

    @Column(name = "keyy")
    private String key;

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
        if(!(obj instanceof SpellVars)) {
            return false;
        }
        final SpellVars other = (SpellVars)obj;
        if(coeff == null) {
            if(other.coeff != null) {
                return false;
            }
        }
        else if(!coeff.equals(other.coeff)) {
            return false;
        }
        if(dyn == null) {
            if(other.dyn != null) {
                return false;
            }
        }
        else if(!dyn.equals(other.dyn)) {
            return false;
        }
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        }
        else if(!key.equals(other.key)) {
            return false;
        }
        if(link == null) {
            if(other.link != null) {
                return false;
            }
        }
        else if(!link.equals(other.link)) {
            return false;
        }
        if(ranksWith == null) {
            if(other.ranksWith != null) {
                return false;
            }
        }
        else if(!ranksWith.equals(other.ranksWith)) {
            return false;
        }
        return true;
    }

    /**
     * @return the coeff
     */
    public List<Double> getCoeff() {
        return coeff;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the dyn
     */
    public String getDyn() {
        return dyn;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (coeff == null ? 0 : coeff.hashCode());
        result = prime * result + (dyn == null ? 0 : dyn.hashCode());
        result = prime * result + (key == null ? 0 : key.hashCode());
        result = prime * result + (link == null ? 0 : link.hashCode());
        result = prime * result + (ranksWith == null ? 0 : ranksWith.hashCode());
        return result;
    }

    /**
     * @param coeff
     *            the coeff to set
     */
    public void setCoeff(final List<Double> coeff) {
        this.coeff = coeff;
    }

    /**
     * @param dyn
     *            the dyn to set
     */
    public void setDyn(final String dyn) {
        this.dyn = dyn;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @param link
     *            the link to set
     */
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * @param ranksWith
     *            the ranksWith to set
     */
    public void setRanksWith(final String ranksWith) {
        this.ranksWith = ranksWith;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SpellVars [coeff=" + coeff + ", dyn=" + dyn + ", key=" + key + ", link=" + link + ", ranksWith=" + ranksWith + "]";
    }
}
