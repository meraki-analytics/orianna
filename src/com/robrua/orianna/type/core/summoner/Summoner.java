package com.robrua.orianna.type.core.summoner;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.championmastery.ChampionMastery;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.matchlist.MatchReference;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.stats.ChampionStats;
import com.robrua.orianna.type.core.stats.PlayerStatsSummary;
import com.robrua.orianna.type.core.stats.PlayerStatsSummaryType;
import com.robrua.orianna.type.core.team.Team;

public class Summoner extends OriannaObject<com.robrua.orianna.type.dto.summoner.Summoner> {
    private static final long serialVersionUID = 5207241015938391741L;

    /**
     * Gets the match history for the summoner
     *
     * @param numMatches
     *            the maximum number of matches to get
     * @return the match list for this summoner
     */
    public static List<MatchReference> getMatchList(final int numMatches) {
        return RiotAPI.getMatchList(numMatches);
    }

    /**
     * @param data
     *            the underlying dto
     */
    public Summoner(final com.robrua.orianna.type.dto.summoner.Summoner data) {
        super(data, com.robrua.orianna.type.dto.summoner.Summoner.class);
    }

    /**
     * Gets the champion mastery ratings for this summoner
     *
     * @return the summoner's champion mastery ratings
     */
    public List<ChampionMastery> getChampionMastery() {
        return RiotAPI.getChampionMastery(getID());
    }

    /**
     * Gets the summoner's mastery on a champion
     *
     * @param champion
     *            the champion to get rating for
     * @return the summoner's champion mastery rating
     */
    public ChampionMastery getChampionMastery(final Champion champion) {
        return RiotAPI.getChampionMastery(getID(), champion.getID());
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
     * Gets the match history for the summoner
     *
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList() {
        return RiotAPI.getMatchList(this);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param beginTime
     *            The begin time to use for fetching games
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final Date beginTime) {
        return RiotAPI.getMatchList(this, beginTime);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final Date beginTime, final Date endTime) {
        return RiotAPI.getMatchList(this, beginTime, endTime);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final Date beginTime, final Date endTime, final List<QueueType> queueTypes) {
        return RiotAPI.getMatchList(this, beginTime, endTime, queueTypes);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final Date beginTime, final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs) {
        return RiotAPI.getMatchList(this, beginTime, endTime, queueTypes, championIDs);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @param seasons
     *            the seasons to limit games to
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final Date beginTime, final Date endTime, final List<QueueType> queueTypes, final List<Long> championIDs,
            final List<Season> seasons) {
        return RiotAPI.getMatchList(this, beginTime, endTime, queueTypes, championIDs, seasons);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final int numMatches, final int beginIndex) {
        return RiotAPI.getMatchList(this, numMatches, beginIndex);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final int numMatches, final int beginIndex, final Date beginTime) {
        return RiotAPI.getMatchList(this, numMatches, beginIndex, beginTime);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final int numMatches, final int beginIndex, final Date beginTime, final Date endTime) {
        return RiotAPI.getMatchList(this, numMatches, beginIndex, beginTime, endTime);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final int numMatches, final int beginIndex, final Date beginTime, final Date endTime,
            final List<QueueType> queueTypes) {
        return RiotAPI.getMatchList(this, numMatches, beginIndex, beginTime, endTime, queueTypes);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final int numMatches, final int beginIndex, final Date beginTime, final Date endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs) {
        return RiotAPI.getMatchList(this, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs);
    }

    /**
     * Gets the match history for the summoner
     *
     * @param numMatches
     *            the maximum number of matches to get
     * @param beginIndex
     *            the game index to start from
     * @param beginTime
     *            The begin time to use for fetching games
     * @param endTime
     *            The end time to use for fetching games
     * @param queueTypes
     *            the queue types to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @param seasons
     *            the seasons to limit games to
     * @return the match list for this summoner
     */
    public List<MatchReference> getMatchList(final int numMatches, final int beginIndex, final Date beginTime, final Date endTime,
            final List<QueueType> queueTypes, final List<Long> championIDs, final List<Season> seasons) {
        return RiotAPI.getMatchList(this, numMatches, beginIndex, beginTime, endTime, queueTypes, championIDs, seasons);
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

    /**
     * Gets the top champion mastery ratings for this summoner
     *
     * @param count
     *            the number of top champions to get
     * @return the summoner's champion mastery ratings
     */
    public List<ChampionMastery> getTopChampionMastery(final int count) {
        return RiotAPI.getTopChampionMastery(getID(), count);
    }

    /**
     * Gets the total mastery level for this summoner
     *
     * @return the summoner's total mastery level
     */
    public int getTotalMasteryLevel() {
        return RiotAPI.getTotalMasteryLevel(getID());
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
