package com.robrua.orianna.type.dto.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity(name = "match.Rune")
@Table(name = "matchrune")
public class Rune extends OriannaDto {
    private static final long serialVersionUID = -631274938807489347L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Long rank, runeId;

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
        if(!(obj instanceof Rune)) {
            return false;
        }
        final Rune other = (Rune)obj;
        if(rank == null) {
            if(other.rank != null) {
                return false;
            }
        }
        else if(!rank.equals(other.rank)) {
            return false;
        }
        if(runeId == null) {
            if(other.runeId != null) {
                return false;
            }
        }
        else if(!runeId.equals(other.runeId)) {
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
     * @return the rank
     */
    public Long getRank() {
        return rank;
    }

    /**
     * @return the runeId
     */
    public Long getRuneId() {
        return runeId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (rank == null ? 0 : rank.hashCode());
        result = prime * result + (runeId == null ? 0 : runeId.hashCode());
        return result;
    }

    /**
     * @param rank
     *            the rank to set
     */
    public void setRank(final Long rank) {
        this.rank = rank;
    }

    /**
     * @param runeId
     *            the runeId to set
     */
    public void setRuneId(final Long runeId) {
        this.runeId = runeId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Rune [rank=" + rank + ", runeId=" + runeId + "]";
    }
}
