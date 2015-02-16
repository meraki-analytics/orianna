package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.store.Cache;
import com.robrua.orianna.store.DataStore;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.api.MultiRateLimiter;
import com.robrua.orianna.type.api.RateLimit;
import com.robrua.orianna.type.api.RateLimiter;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.staticdata.Item;
import com.robrua.orianna.type.core.staticdata.Mastery;
import com.robrua.orianna.type.core.staticdata.Rune;
import com.robrua.orianna.type.core.staticdata.SummonerSpell;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.core.team.Team;

/**
 * Accesses the <a href="http://developer.riotgames.com/api/methods">LoL REST
 * API</a> and provides results in easy-to-use Java objects.
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public abstract class RiotAPI {
    private static LoadPolicy loadPolicy = LoadPolicy.LAZY;
    private static DataStore store = new Cache();

    public static Champion getChampion(final long ID) {
        Champion champion = store.get(Champion.class, ID);
        if(champion != null) {
            return champion;
        }

        final com.robrua.orianna.type.dto.staticdata.Champion champ = BaseRiotAPI.getChampion(ID);
        if(loadPolicy == LoadPolicy.UPFRONT) {
            getItems(new ArrayList<>(champ.getItemIDs()));
        }

        champion = new Champion(champ);
        store.store(champion, ID);

        return champion;
    }

    /**
     * Gets a rate limiter for the default development rate limit
     *
     * @return default development rate limiter
     */
    public static RateLimiter getDefaultDevelopmentRateLimiter() {
        return new MultiRateLimiter(new RateLimit(10, 10), new RateLimit(500, 600));
    }

    public static Item getItem(final long ID) {
        return null;
    }

    public static List<Item> getItems(final List<Long> IDs) {
        return null;
    }

    public static Mastery getMasteryByID(final long ID) {
        return null;
    }

    public static Rune getRuneByID(final long ID) {
        return null;
    }

    public static Summoner getSummonerByID(final long ID) {
        return null;
    }

    public static SummonerSpell getSummonerSpellByID(final long ID) {
        return null;
    }

    public static Team getTeamByID(final String ID) {
        return null;
    }

    /**
     * Sets the API Key to use for queries
     *
     * @param newAPIKey
     *            the API Key to use for queries
     */
    public static void setAPIKey(final String newAPIKey) {
        BaseRiotAPI.setAPIKey(newAPIKey);
    }

    /**
     * Sets the load policy for the API
     *
     * @param policy
     *            the new load policy
     */
    public static void setLoadPolicy(final LoadPolicy policy) {
        loadPolicy = policy;
    }

    /**
     * Sets the server mirror to hit for queries
     *
     * @param newMirror
     *            the server mirror to hit for queries
     */
    public static void setMirror(final Region newMirror) {
        BaseRiotAPI.setMirror(newMirror);
    }

    /**
     * Sets multiple new rate limits for the API, removing any old ones
     *
     * @param limits
     *            the rate limits to apply
     */
    public static void setRateLimit(final Collection<RateLimit> limits) {
        BaseRiotAPI.setRateLimit(limits);
    }

    /**
     * Sets a new rate limit for the API, removing any old ones
     *
     * @param callsPerEpoch
     *            the number of calls allowed in each epoch
     * @param secondsPerEpoch
     *            the number of seconds in each epoch
     */
    public static void setRateLimit(final int callsPerEpoch, final int secondsPerEpoch) {
        BaseRiotAPI.setRateLimit(callsPerEpoch, secondsPerEpoch);
    }

    /**
     * Sets a new rate limit for the API, removing any old ones
     *
     * @param callsPerEpoch
     *            the number of calls allowed in each epoch
     * @param secondsPerEpoch
     *            the number of seconds in each epoch
     */
    public static void setRateLimit(final RateLimit limit) {
        BaseRiotAPI.setRateLimit(limit);
    }

    /**
     * Sets multiple new rate limits for the API, removing any old ones
     *
     * @param limits
     *            the rate limits to apply
     */
    public static void setRateLimit(final RateLimit... limits) {
        BaseRiotAPI.setRateLimit(limits);
    }

    /**
     * Sets the region to get information from the API for
     *
     * @param newRegion
     *            the region to get information from the API for
     */
    public static void setRegion(final Region newRegion) {
        BaseRiotAPI.setRegion(newRegion);
    }
}
