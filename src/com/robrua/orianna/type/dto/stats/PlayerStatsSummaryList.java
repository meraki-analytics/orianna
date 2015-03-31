package com.robrua.orianna.type.dto.stats;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "playerstatssummarylist")
public class PlayerStatsSummaryList extends OriannaDto {
    private static final long serialVersionUID = 31774821362693998L;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PlayerStatsSummary> playerStatSummaries;

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
        if(!(obj instanceof PlayerStatsSummaryList)) {
            return false;
        }
        final PlayerStatsSummaryList other = (PlayerStatsSummaryList)obj;
        if(playerStatSummaries == null) {
            if(other.playerStatSummaries != null) {
                return false;
            }
        }
        else if(!playerStatSummaries.equals(other.playerStatSummaries)) {
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

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "summonerId";
        }
        return null;
    }

    /**
     * @return the playerStatSummaries
     */
    public List<PlayerStatsSummary> getPlayerStatSummaries() {
        return playerStatSummaries;
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
        result = prime * result + (playerStatSummaries == null ? 0 : playerStatSummaries.hashCode());
        result = prime * result + (summonerId == null ? 0 : summonerId.hashCode());
        return result;
    }

    /**
     * @param playerStatSummaries
     *            the playerStatSummaries to set
     */
    public void setPlayerStatSummaries(final List<PlayerStatsSummary> playerStatSummaries) {
        this.playerStatSummaries = playerStatSummaries;
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
        return "PlayerStatsSummaryList [playerStatSummaries=" + playerStatSummaries + ", summonerId=" + summonerId + "]";
    }
}
