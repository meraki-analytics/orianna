package com.merakianalytics.orianna.types.dto.match;

import java.util.List;
import java.util.Set;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Matchlist extends DataObject {
    private static final long serialVersionUID = 6693751756707534012L;
    private long accountId, startTime, endTime, maxTimeRange;
    private List<MatchReference> matches;
    private String platform;
    private Set<Integer> queues, seasons, champions;
    private boolean recent;
    private int totalGames, startIndex, endIndex, maxSize;

    /**
     * @return the accountId
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * @return the champions
     */
    public Set<Integer> getChampions() {
        return champions;
    }

    /**
     * @return the endIndex
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * @return the matches
     */
    public List<MatchReference> getMatches() {
        return matches;
    }

    /**
     * @return the maxSize
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * @return the maxTimeRange
     */
    public long getMaxTimeRange() {
        return maxTimeRange;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the queues
     */
    public Set<Integer> getQueues() {
        return queues;
    }

    /**
     * @return the seasons
     */
    public Set<Integer> getSeasons() {
        return seasons;
    }

    /**
     * @return the startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @return the totalGames
     */
    public int getTotalGames() {
        return totalGames;
    }

    /**
     * @return the recent
     */
    public boolean isRecent() {
        return recent;
    }

    /**
     * @param accountId
     *        the accountId to set
     */
    public void setAccountId(final long accountId) {
        this.accountId = accountId;
    }

    /**
     * @param champions
     *        the champions to set
     */
    public void setChampions(final Set<Integer> champions) {
        this.champions = champions;
    }

    /**
     * @param endIndex
     *        the endIndex to set
     */
    public void setEndIndex(final int endIndex) {
        this.endIndex = endIndex;
    }

    /**
     * @param endTime
     *        the endTime to set
     */
    public void setEndTime(final long endTime) {
        this.endTime = endTime;
    }

    /**
     * @param matches
     *        the matches to set
     */
    public void setMatches(final List<MatchReference> matches) {
        this.matches = matches;
    }

    /**
     * @param maxSize
     *        the maxSize to set
     */
    public void setMaxSize(final int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * @param maxTimeRange
     *        the maxTimeRange to set
     */
    public void setMaxTimeRange(final long maxTimeRange) {
        this.maxTimeRange = maxTimeRange;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param queues
     *        the queues to set
     */
    public void setQueues(final Set<Integer> queues) {
        this.queues = queues;
    }

    /**
     * @param recent
     *        the recent to set
     */
    public void setRecent(final boolean recent) {
        this.recent = recent;
    }

    /**
     * @param seasons
     *        the seasons to set
     */
    public void setSeasons(final Set<Integer> seasons) {
        this.seasons = seasons;
    }

    /**
     * @param startIndex
     *        the startIndex to set
     */
    public void setStartIndex(final int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @param startTime
     *        the startTime to set
     */
    public void setStartTime(final long startTime) {
        this.startTime = startTime;
    }

    /**
     * @param totalGames
     *        the totalGames to set
     */
    public void setTotalGames(final int totalGames) {
        this.totalGames = totalGames;
    }
}
