package com.robrua.orianna.type.dto.stats;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "championstats")
public class ChampionStats extends OriannaDto {
    private static final long serialVersionUID = 273933358275960460L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @Column(name = "champion_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private AggregatedStats stats;

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
        if(!(obj instanceof ChampionStats)) {
            return false;
        }
        final ChampionStats other = (ChampionStats)obj;
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        }
        else if(!id.equals(other.id)) {
            return false;
        }
        if(stats == null) {
            if(other.stats != null) {
                return false;
            }
        }
        else if(!stats.equals(other.stats)) {
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
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the stats
     */
    public AggregatedStats getStats() {
        return stats;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (stats == null ? 0 : stats.hashCode());
        return result;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @param stats
     *            the stats to set
     */
    public void setStats(final AggregatedStats stats) {
        this.stats = stats;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ChampionStats [id=" + id + ", stats=" + stats + "]";
    }
}
