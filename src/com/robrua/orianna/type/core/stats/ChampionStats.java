package com.robrua.orianna.type.core.stats;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.exception.MissingDataException;

public class ChampionStats extends OriannaObject<com.robrua.orianna.type.dto.stats.ChampionStats> {
    private static final long serialVersionUID = -6400924448623294320L;
    private Champion champion;
    private AggregatedStats stats;

    /**
     * @param data
     *            the underlying dto
     */
    public ChampionStats(final com.robrua.orianna.type.dto.stats.ChampionStats data) {
        super(data, com.robrua.orianna.type.dto.stats.ChampionStats.class);
    }

    /**
     * Champion the stats are for. Null represents combined stats for all
     * champions.
     *
     * @return champion the stats are for. Null represents combined stats for
     *         all champions.
     */
    public Champion getChampion() {
        if(champion != null) {
            return champion;
        }

        final Integer i = data.getId();
        if(i == null) {
            throw new MissingDataException("Champion ID is null.");
        }
        else if(i == 0) {
            return null;
        }

        champion = RiotAPI.getChampionByID(i.longValue());
        return champion;
    }

    /**
     * ID of the champion the stats are for. 0 represents combined stats for all
     * champions.
     *
     * @return ID of the champion the stats are for. 0 represents combined stats
     *         for all champions.
     */
    public int getChampionID() {
        return super.getInteger(data.getId());
    }

    /**
     * Aggregated stats associated with the champion
     *
     * @return aggregated stats associated with the champion.
     */
    public AggregatedStats getStats() {
        if(stats == null) {
            stats = new AggregatedStats(data.getStats());
        }

        return stats;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ChampionStats (" + getChampion() + ")";
    }
}
