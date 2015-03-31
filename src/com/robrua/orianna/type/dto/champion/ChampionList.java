package com.robrua.orianna.type.dto.champion;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity(name = "champion.ChampionList")
@Table(name = "championstatuslist")
public class ChampionList extends OriannaDto {
    private static final long serialVersionUID = -9016590706568633546L;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Champion> champions;

    @Id
    private final long dbId = 0;

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
        if(!(obj instanceof ChampionList)) {
            return false;
        }
        final ChampionList other = (ChampionList)obj;
        if(champions == null) {
            if(other.champions != null) {
                return false;
            }
        }
        else if(!champions.equals(other.champions)) {
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
        for(final Champion champion : champions) {
            set.add(champion.getId());
        }

        return set;
    }

    /**
     * @return the champions
     */
    public List<Champion> getChampions() {
        return champions;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
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
        return result;
    }

    /**
     * @param champions
     *            the champions to set
     */
    public void setChampions(final List<Champion> champions) {
        this.champions = champions;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ChampionList [champions=" + champions + "]";
    }
}
