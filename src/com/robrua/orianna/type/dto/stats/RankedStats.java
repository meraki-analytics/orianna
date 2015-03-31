package com.robrua.orianna.type.dto.stats;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "rankedstats")
public class RankedStats extends OriannaDto {
    private static final long serialVersionUID = 1193813117107198821L;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ChampionStats> champions;

    private Long modifyDate;

    @Id
    private Long summonerId;

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
        if(!(obj instanceof RankedStats)) {
            return false;
        }
        final RankedStats other = (RankedStats)obj;
        if(champions == null) {
            if(other.champions != null) {
                return false;
            }
        }
        else if(!champions.equals(other.champions)) {
            return false;
        }
        if(modifyDate == null) {
            if(other.modifyDate != null) {
                return false;
            }
        }
        else if(!modifyDate.equals(other.modifyDate)) {
            return false;
        }
        if(summonerId == null) {
            if(other.summonerId != null) {
                return false;
            }
        }
        else if(!summonerId.equals(other.summonerId)) {
            return false;
        }
        return true;
    }

    /**
     * Gets all stored champion IDs for batch lookup
     *
     * @return the champion IDs
     */
    public Set<Long> getChampionIDs() {
        final Set<Long> set = new HashSet<>();
        for(final ChampionStats stats : champions) {
            set.add(stats.getId().longValue());
        }

        return set;
    }

    /**
     * @return the champions
     */
    public List<ChampionStats> getChampions() {
        return champions;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "summonerId";
        }
        return null;
    }

    /**
     * @return the modifyDate
     */
    public Long getModifyDate() {
        return modifyDate;
    }

    /**
     * @return the summonerId
     */
    public Long getSummonerId() {
        return summonerId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (champions == null ? 0 : champions.hashCode());
        result = prime * result + (modifyDate == null ? 0 : modifyDate.hashCode());
        result = prime * result + (summonerId == null ? 0 : summonerId.hashCode());
        return result;
    }

    /**
     * @param champions
     *            the champions to set
     */
    public void setChampions(final List<ChampionStats> champions) {
        this.champions = champions;
    }

    /**
     * @param modifyDate
     *            the modifyDate to set
     */
    public void setModifyDate(final Long modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * @param summonerId
     *            the summonerId to set
     */
    public void setSummonerId(final Long summonerId) {
        this.summonerId = summonerId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RankedStats [champions=" + champions + ", modifyDate=" + modifyDate + ", summonerId=" + summonerId + "]";
    }
}
