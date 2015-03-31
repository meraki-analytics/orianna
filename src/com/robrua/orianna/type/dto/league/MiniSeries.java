package com.robrua.orianna.type.dto.league;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "miniseries")
public class MiniSeries extends OriannaDto {
    private static final long serialVersionUID = -7559487310510378384L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;
    private Integer losses, target, wins;

    private String progress;

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
        if(!(obj instanceof MiniSeries)) {
            return false;
        }
        final MiniSeries other = (MiniSeries)obj;
        if(losses == null) {
            if(other.losses != null) {
                return false;
            }
        }
        else if(!losses.equals(other.losses)) {
            return false;
        }
        if(progress == null) {
            if(other.progress != null) {
                return false;
            }
        }
        else if(!progress.equals(other.progress)) {
            return false;
        }
        if(target == null) {
            if(other.target != null) {
                return false;
            }
        }
        else if(!target.equals(other.target)) {
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
     * @return the progress
     */
    public String getProgress() {
        return progress;
    }

    /**
     * @return the target
     */
    public Integer getTarget() {
        return target;
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
        result = prime * result + (losses == null ? 0 : losses.hashCode());
        result = prime * result + (progress == null ? 0 : progress.hashCode());
        result = prime * result + (target == null ? 0 : target.hashCode());
        result = prime * result + (wins == null ? 0 : wins.hashCode());
        return result;
    }

    /**
     * @param losses
     *            the losses to set
     */
    public void setLosses(final Integer losses) {
        this.losses = losses;
    }

    /**
     * @param progress
     *            the progress to set
     */
    public void setProgress(final String progress) {
        this.progress = progress;
    }

    /**
     * @param target
     *            the target to set
     */
    public void setTarget(final Integer target) {
        this.target = target;
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
        return "MiniSeries [losses=" + losses + ", target=" + target + ", wins=" + wins + ", progress=" + progress + "]";
    }
}
