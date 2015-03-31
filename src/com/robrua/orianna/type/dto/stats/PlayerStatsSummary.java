package com.robrua.orianna.type.dto.stats;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "playerstatssummary")
public class PlayerStatsSummary extends OriannaDto {
    private static final long serialVersionUID = -6006209553573430917L;
    @OneToOne(cascade = CascadeType.ALL)
    private AggregatedStats aggregatedStats;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;
    private Integer losses, wins;

    private Long modifyDate;

    private String playerStatSummaryType;

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
        if(!(obj instanceof PlayerStatsSummary)) {
            return false;
        }
        final PlayerStatsSummary other = (PlayerStatsSummary)obj;
        if(aggregatedStats == null) {
            if(other.aggregatedStats != null) {
                return false;
            }
        }
        else if(!aggregatedStats.equals(other.aggregatedStats)) {
            return false;
        }
        if(losses == null) {
            if(other.losses != null) {
                return false;
            }
        }
        else if(!losses.equals(other.losses)) {
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
        if(playerStatSummaryType == null) {
            if(other.playerStatSummaryType != null) {
                return false;
            }
        }
        else if(!playerStatSummaryType.equals(other.playerStatSummaryType)) {
            return false;
        }
        if(wins == null) {
            if(other.wins != null) {
                return false;
            }
        }
        else if(!wins.equals(other.wins)) {
            return false;
        }
        return true;
    }

    /**
     * @return the aggregatedStats
     */
    public AggregatedStats getAggregatedStats() {
        return aggregatedStats;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the losses
     */
    public Integer getLosses() {
        return losses;
    }

    /**
     * @return the modifyDate
     */
    public Long getModifyDate() {
        return modifyDate;
    }

    /**
     * @return the playerStatSummaryType
     */
    public String getPlayerStatSummaryType() {
        return playerStatSummaryType;
    }

    /**
     * @return the wins
     */
    public Integer getWins() {
        return wins;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (aggregatedStats == null ? 0 : aggregatedStats.hashCode());
        result = prime * result + (losses == null ? 0 : losses.hashCode());
        result = prime * result + (modifyDate == null ? 0 : modifyDate.hashCode());
        result = prime * result + (playerStatSummaryType == null ? 0 : playerStatSummaryType.hashCode());
        result = prime * result + (wins == null ? 0 : wins.hashCode());
        return result;
    }

    /**
     * @param aggregatedStats
     *            the aggregatedStats to set
     */
    public void setAggregatedStats(final AggregatedStats aggregatedStats) {
        this.aggregatedStats = aggregatedStats;
    }

    /**
     * @param losses
     *            the losses to set
     */
    public void setLosses(final Integer losses) {
        this.losses = losses;
    }

    /**
     * @param modifyDate
     *            the modifyDate to set
     */
    public void setModifyDate(final Long modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * @param playerStatSummaryType
     *            the playerStatSummaryType to set
     */
    public void setPlayerStatSummaryType(final String playerStatSummaryType) {
        this.playerStatSummaryType = playerStatSummaryType;
    }

    /**
     * @param wins
     *            the wins to set
     */
    public void setWins(final Integer wins) {
        this.wins = wins;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PlayerStatsSummary [aggregatedStats=" + aggregatedStats + ", losses=" + losses + ", wins=" + wins + ", modifyDate=" + modifyDate
                + ", playerStatSummaryType=" + playerStatSummaryType + "]";
    }
}
