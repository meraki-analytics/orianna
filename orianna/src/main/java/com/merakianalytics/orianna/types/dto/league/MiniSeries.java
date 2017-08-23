package com.merakianalytics.orianna.types.dto.league;

import com.merakianalytics.orianna.types.dto.DataObject;

public class MiniSeries extends DataObject {
    private static final long serialVersionUID = 6265349801098708717L;
    private String progress;
    private int wins, losses, target;

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
        if(getClass() != obj.getClass()) {
            return false;
        }
        final MiniSeries other = (MiniSeries)obj;
        if(losses != other.losses) {
            return false;
        }
        if(progress == null) {
            if(other.progress != null) {
                return false;
            }
        } else if(!progress.equals(other.progress)) {
            return false;
        }
        if(target != other.target) {
            return false;
        }
        if(wins != other.wins) {
            return false;
        }
        return true;
    }

    /**
     * @return the losses
     */
    public int getLosses() {
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
    public int getTarget() {
        return target;
    }

    /**
     * @return the wins
     */
    public int getWins() {
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
        result = prime * result + losses;
        result = prime * result + (progress == null ? 0 : progress.hashCode());
        result = prime * result + target;
        result = prime * result + wins;
        return result;
    }

    /**
     * @param losses
     *        the losses to set
     */
    public void setLosses(final int losses) {
        this.losses = losses;
    }

    /**
     * @param progress
     *        the progress to set
     */
    public void setProgress(final String progress) {
        this.progress = progress;
    }

    /**
     * @param target
     *        the target to set
     */
    public void setTarget(final int target) {
        this.target = target;
    }

    /**
     * @param wins
     *        the wins to set
     */
    public void setWins(final int wins) {
        this.wins = wins;
    }
}
