package com.merakianalytics.orianna.types.data.league;

import com.merakianalytics.orianna.types.data.CoreData;

public class LeagueEntry extends CoreData {
    private static final long serialVersionUID = 2972094204019879043L;
    private String division, summonerName, platform, summonerId, queue, tier, leagueId;
    private boolean onHotStreak, veteran, inactive, freshBlood;
    private Series promos;
    private int wins, losses, leaguePoints;

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @return the leagueId
     */
    public String getLeagueId() {
        return leagueId;
    }

    /**
     * @return the leaguePoints
     */
    public int getLeaguePoints() {
        return leaguePoints;
    }

    /**
     * @return the losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the promos
     */
    public Series getPromos() {
        return promos;
    }

    /**
     * @return the queue
     */
    public String getQueue() {
        return queue;
    }

    /**
     * @return the summonerId
     */
    public String getSummonerId() {
        return summonerId;
    }

    /**
     * @return the summonerName
     */
    public String getSummonerName() {
        return summonerName;
    }

    /**
     * @return the tier
     */
    public String getTier() {
        return tier;
    }

    /**
     * @return the wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * @return the freshBlood
     */
    public boolean isFreshBlood() {
        return freshBlood;
    }

    /**
     * @return the inactive
     */
    public boolean isInactive() {
        return inactive;
    }

    /**
     * @return the onHotStreak
     */
    public boolean isOnHotStreak() {
        return onHotStreak;
    }

    /**
     * @return the veteran
     */
    public boolean isVeteran() {
        return veteran;
    }

    /**
     * @param division
     *        the division to set
     */
    public void setDivision(final String division) {
        this.division = division;
    }

    /**
     * @param freshBlood
     *        the freshBlood to set
     */
    public void setFreshBlood(final boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    /**
     * @param inactive
     *        the inactive to set
     */
    public void setInactive(final boolean inactive) {
        this.inactive = inactive;
    }

    /**
     * @param leagueId
     *        the leagueId to set
     */
    public void setLeagueId(final String leagueId) {
        this.leagueId = leagueId;
    }

    /**
     * @param leaguePoints
     *        the leaguePoints to set
     */
    public void setLeaguePoints(final int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    /**
     * @param losses
     *        the losses to set
     */
    public void setLosses(final int losses) {
        this.losses = losses;
    }

    /**
     * @param onHotStreak
     *        the onHotStreak to set
     */
    public void setOnHotStreak(final boolean onHotStreak) {
        this.onHotStreak = onHotStreak;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param promos
     *        the promos to set
     */
    public void setPromos(final Series promos) {
        this.promos = promos;
    }

    /**
     * @param queue
     *        the queue to set
     */
    public void setQueue(final String queue) {
        this.queue = queue;
    }

    /**
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final String summonerId) {
        this.summonerId = summonerId;
    }

    /**
     * @param summonerName
     *        the summonerName to set
     */
    public void setSummonerName(final String summonerName) {
        this.summonerName = summonerName;
    }

    /**
     * @param tier
     *        the tier to set
     */
    public void setTier(final String tier) {
        this.tier = tier;
    }

    /**
     * @param veteran
     *        the veteran to set
     */
    public void setVeteran(final boolean veteran) {
        this.veteran = veteran;
    }

    /**
     * @param wins
     *        the wins to set
     */
    public void setWins(final int wins) {
        this.wins = wins;
    }
}
