package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.ChampionStats;
import com.robrua.orianna.type.core.stats.PlayerStatsSummary;
import com.robrua.orianna.type.core.stats.PlayerStatsSummaryType;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.dto.stats.PlayerStatsSummaryList;
import com.robrua.orianna.type.dto.stats.RankedStats;

public abstract class StatsAPI {
    /**
     * @param summonerID
     *            the summoner to get ranked stats for
     * @return the ranked stats for the summoner by champion (null entry
     *         contains totals over all champions)
     */
    public static Map<Champion, ChampionStats> getRankedStats(final long summonerID) {
        final RankedStats sts = BaseRiotAPI.getRankedStats(summonerID);

        final List<Long> index = new ArrayList<>(sts.getChampionIDs());
        final List<Champion> champions = RiotAPI.getChampionsByID(index);

        final Map<Champion, ChampionStats> stats = new HashMap<>();
        for(final com.robrua.orianna.type.dto.stats.ChampionStats champion : sts.getChampions()) {
            stats.put(champions.get(index.indexOf(champion.getId().longValue())), new ChampionStats(champion));
        }

        return Collections.unmodifiableMap(stats);
    }

    /**
     * @param summonerID
     *            the summoner to get ranked stats for
     * @param season
     *            the season to get ranked stats for
     * @return the ranked stats for the summoner by champion (null entry
     *         contains totals over all champions)
     */
    public static Map<Champion, ChampionStats> getRankedStats(final long summonerID, final Season season) {
        final RankedStats sts = BaseRiotAPI.getRankedStats(summonerID, season);

        final List<Long> index = new ArrayList<>(sts.getChampionIDs());
        final List<Champion> champions = RiotAPI.getChampionsByID(index);

        final Map<Champion, ChampionStats> stats = new HashMap<>();
        for(final com.robrua.orianna.type.dto.stats.ChampionStats champion : sts.getChampions()) {
            stats.put(champions.get(index.indexOf(champion.getId().longValue())), new ChampionStats(champion));
        }

        return Collections.unmodifiableMap(stats);
    }

    /**
     * @param summoner
     *            the summoner to get ranked stats for
     * @return the ranked stats for the summoner by champion (null entry
     *         contains totals over all champions)
     */
    public static Map<Champion, ChampionStats> getRankedStats(final Summoner summoner) {
        return getRankedStats(summoner.getID());
    }

    /**
     * @param summoner
     *            the summoner to get ranked stats for
     * @param season
     *            the season to get ranked stats for
     * @return the ranked stats for the summoner by champion (null entry
     *         contains totals over all champions)
     */
    public static Map<Champion, ChampionStats> getRankedStats(final Summoner summoner, final Season season) {
        return getRankedStats(summoner.getID(), season);
    }

    /**
     * @param summonerID
     *            the summoner to get stats for
     * @return the stats for the summoner by queue type
     */
    public static Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final long summonerID) {
        final PlayerStatsSummaryList sts = BaseRiotAPI.getStats(summonerID);
        if(sts == null || sts.getPlayerStatSummaries() == null) {
            return null;
        }

        final Map<PlayerStatsSummaryType, PlayerStatsSummary> stats = new HashMap<>();
        for(final com.robrua.orianna.type.dto.stats.PlayerStatsSummary sum : sts.getPlayerStatSummaries()) {
            stats.put(PlayerStatsSummaryType.valueOf(sum.getPlayerStatSummaryType()), new PlayerStatsSummary(sum));
        }

        return Collections.unmodifiableMap(stats);
    }

    /**
     * @param summonerID
     *            the summoner to get stats for
     * @param season
     *            the season to get stats for
     * @return the stats for the summoner by queue type
     */
    public static Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final long summonerID, final Season season) {
        final PlayerStatsSummaryList sts = BaseRiotAPI.getStats(summonerID, season);
        if(sts == null || sts.getPlayerStatSummaries() == null) {
            return null;
        }

        final Map<PlayerStatsSummaryType, PlayerStatsSummary> stats = new HashMap<>();
        for(final com.robrua.orianna.type.dto.stats.PlayerStatsSummary sum : sts.getPlayerStatSummaries()) {
            stats.put(PlayerStatsSummaryType.valueOf(sum.getPlayerStatSummaryType()), new PlayerStatsSummary(sum));
        }

        return Collections.unmodifiableMap(stats);
    }

    /**
     * @param summoner
     *            the summoner to get stats for
     * @return the stats for the summoner by queue type
     */
    public static Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final Summoner summoner) {
        return getStats(summoner.getID());
    }

    /**
     * @param summoner
     *            the summoner to get stats for
     * @param season
     *            the season to get stats for
     * @return the stats for the summoner by queue type
     */
    public static Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final Summoner summoner, final Season season) {
        return getStats(summoner.getID(), season);
    }
}
