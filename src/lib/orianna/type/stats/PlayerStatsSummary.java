package lib.orianna.type.stats;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PlayerStatsSummary implements Serializable {
    private static final long serialVersionUID = -7694790679885114372L;
    public final AggregatedStats aggregatedStats;
    public final LocalDateTime modifyDate;
    public final PlayerStatsSummaryType playerStatSummaryType;
    public final Integer wins, losses;

    public PlayerStatsSummary(final AggregatedStats aggregatedStats, final PlayerStatsSummaryType playerStatSummaryType, final LocalDateTime modifyDate,
            final Integer wins, final Integer losses) {
        this.aggregatedStats = aggregatedStats;
        this.playerStatSummaryType = playerStatSummaryType;
        this.modifyDate = modifyDate;
        this.wins = wins;
        this.losses = losses;
    }

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
        if(playerStatSummaryType != other.playerStatSummaryType) {
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

    @Override
    public String toString() {
        return "PlayerStatsSummary [wins=" + wins + ", losses=" + losses + ", playerStatSummaryType=" + playerStatSummaryType + "]";
    }
}
