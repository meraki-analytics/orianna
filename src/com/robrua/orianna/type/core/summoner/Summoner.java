package com.robrua.orianna.type.core.summoner;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.matchhistory.MatchSummary;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.ChampionStats;
import com.robrua.orianna.type.core.stats.PlayerStatsSummary;
import com.robrua.orianna.type.core.stats.PlayerStatsSummaryType;
import com.robrua.orianna.type.core.team.Team;

public class Summoner extends OriannaObject<com.robrua.orianna.type.dto.summoner.Summoner> {
    private static final long serialVersionUID = 5207241015938391741L;

    /**
     * @param data
     *            the underlying dto
     */
    public Summoner(final com.robrua.orianna.type.dto.summoner.Summoner data) {
        super(data, com.robrua.orianna.type.dto.summoner.Summoner.class);
    }

    /**
     * The summoner's current game
     *
     * @return the summoner's current game
     */
    public CurrentGame getCurrentGame() {
        return RiotAPI.getCurrentGame(getID());
    }

    /**
     * Summoner ID
     *
     * @return summoner ID
     */
    public long getID() {
        return super.getLong(data.getId());
    }

    /**
     * The summoner's league entries
     *
     * @return the summoner's league entries
     */
    public List<League> getLeagueEntries() {
        return RiotAPI.getLeagueEntriesBySummonerID(getID());
    }

    /**
     * The summoner's leagues
     *
     * @return the summoner's leagues
     */
    public List<League> getLeagues() {
        return RiotAPI.getLeaguesBySummonerID(getID());
    }

    /**
     * Summoner level associated with the summoner
     *
     * @return summoner level associated with the summoner
     */
    public long getLevel() {
        return super.getLong(data.getSummonerLevel());
    }

    /**
     * Summoner's mastery pages
     *
     * @return summoner's mastery pages
     */
    public List<MasteryPage> getMasteryPages() {
        return RiotAPI.getMasteryPagesByID(getID());
    }

    /**
     * Gets the summoner's 15 most recent matches
     *
     * @return the summoner's 15 most recent matches
     */
    public List<MatchSummary> getMatchHistory() {
        return RiotAPI.getMatchHistory(getID());
    }

    /**
     * Gets the summoner's 15 most recent matches after beginIndex
     *
     * @param beginIndex
     *            the game index to start from
     * @return the summoner's 15 most recent matches after beginIndex
     */
    public List<MatchSummary> getMatchHistory(final int beginIndex) {
        return RiotAPI.getMatchHistory(this, beginIndex);
    }

    /**
     * Gets the summoner's 15 most recent matches after beginIndex
     *
     * @param beginIndex
     *            the game index to start from
     * @param champions
     *            the champions to limit games to
     * @return the summoner's 15 most recent matches after beginIndex
     */
    public List<MatchSummary> getMatchHistory(final int beginIndex, final List<Champion> champions) {
        return RiotAPI.getMatchHistory(this, beginIndex, champions);
    }

    /**
     * Gets the summoner's 15 most recent matches after beginIndex
     *
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the summoner's 15 most recent matches after beginIndex
     */
    public List<MatchSummary> getMatchHistory(final int beginIndex, final QueueType queueType) {
        return RiotAPI.getMatchHistory(this, beginIndex, queueType);
    }

    /**
     * Gets the summoner's 15 most recent matches after beginIndex
     *
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param champions
     *            the champions to limit games to
     * @return the summoner's 15 most recent matches after beginIndex
     */
    public List<MatchSummary> getMatchHistory(final int beginIndex, final QueueType queueType, final List<Champion> champions) {
        return RiotAPI.getMatchHistory(this, beginIndex, queueType, champions);
    }

    /**
     * Gets the summoner's 15 most recent matches
     *
     * @param champions
     *            the champions to limit games to
     * @return the summoner's 15 most recent matches
     */
    public List<MatchSummary> getMatchHistory(final List<Champion> champions) {
        return RiotAPI.getMatchHistory(this, champions);
    }

    /**
     * Gets the summoner's 15 most recent matches
     *
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the summoner's 15 most recent matches
     */
    public List<MatchSummary> getMatchHistory(final QueueType queueType) {
        return RiotAPI.getMatchHistory(this, queueType);
    }

    /**
     * Gets the summoner's 15 most recent matches
     *
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param champions
     *            the champions to limit games to
     * @return the summoner's 15 most recent matches
     */
    public List<MatchSummary> getMatchHistory(final QueueType queueType, final List<Champion> champions) {
        return RiotAPI.getMatchHistory(this, queueType, champions);
    }

    /**
     * Summoner name
     *
     * @return summoner name
     */
    public String getName() {
        return super.getString(data.getName());
    }

    /**
     * ID of the summoner icon associated with the summoner
     *
     * @return ID of the summoner icon associated with the summoner
     */
    public int getProfileIconID() {
        return super.getInteger(data.getProfileIconId());
    }

    /**
     * @return the ranked stats for this summoner by champion (null entry
     *         contains totals over all champions)
     */
    public Map<Champion, ChampionStats> getRankedStats() {
        return RiotAPI.getRankedStats(getID());
    }

    /**
     * @param season
     *            the season to get ranked stats for
     * @return the ranked stats for this summoner by champion (null entry
     *         contains totals over all champions)
     */
    public Map<Champion, ChampionStats> getRankedStats(final Season season) {
        return RiotAPI.getRankedStats(getID(), season);
    }

    /**
     * Date summoner was last modified. The following events will update this
     * timestamp: profile icon change, playing the tutorial or advanced
     * tutorial, finishing a game, summoner name change
     *
     * @return date summoner was last modified. The following events will update
     *         this timestamp: profile icon change, playing the tutorial or
     *         advanced tutorial, finishing a game, summoner name change
     */
    public Date getRevisionDate() {
        return super.getDate(data.getRevisionDate());
    }

    /**
     * Summoner's rune pages
     *
     * @return summoner's rune pages
     */
    public List<RunePage> getRunePages() {
        return RiotAPI.getRunePagesByID(getID());
    }

    /**
     * @param season
     *            the season to get stats for
     * @return the stats for this summoner by queue type
     */
    public Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats() {
        return RiotAPI.getStats(getID());
    }

    /**
     * @param season
     *            the season to get stats for
     * @return the stats for this summoner by queue type
     */
    public Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final Season season) {
        return RiotAPI.getStats(getID(), season);
    }

    /**
     * The summoner's teams
     *
     * @return the summoner's teams
     */
    public List<Team> getTeams() {
        return RiotAPI.getTeamsBySummonerID(getID());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getName();
    }
}
