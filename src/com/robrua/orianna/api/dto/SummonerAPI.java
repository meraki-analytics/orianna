package com.robrua.orianna.api.dto;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.robrua.orianna.api.Utils;
import com.robrua.orianna.type.dto.summoner.MasteryPages;
import com.robrua.orianna.type.dto.summoner.RunePages;
import com.robrua.orianna.type.dto.summoner.Summoner;

public abstract class SummonerAPI {
    /**
     * @param summonerIDs
     *            the IDs of the summoners to get
     * @return the summoners
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3293">
     *      Riot API Specification</a>
     */
    public static Map<Long, Summoner> getSummonersByID(final List<Long> summonerIDs) {
        if(summonerIDs.size() > 40) {
            throw new IllegalArgumentException("Can only get up to 40 summoners at a time!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("summoner") + "/summoner/" + Utils.getIDString(summonerIDs);
        final Type type = new TypeToken<Map<Long, Summoner>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get
     * @return the summoners
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3293">
     *      Riot API Specification</a>
     */
    public static Map<Long, Summoner> getSummonersByID(final long... summonerIDs) {
        return getSummonersByID(Utils.convert(summonerIDs));
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get
     * @return the summoners
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3292">
     *      Riot API Specification</a>
     */
    public static Map<String, Summoner> getSummonersByName(final List<String> summonerNames) {
        if(summonerNames.size() > 40) {
            throw new IllegalArgumentException("Can only get up to 40 summoners at a time!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("summoner") + "/summoner/by-name/" + Utils.getIDString(summonerNames);
        final Type type = new TypeToken<Map<String, Summoner>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get
     * @return the summoners
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3292">
     *      Riot API Specification</a>
     */
    public static Map<String, Summoner> getSummonersByName(final String... summonerNames) {
        return getSummonersByName(Arrays.asList(summonerNames));
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get masteries for
     * @return the summoners' masteries
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3295">
     *      Riot API Specification</a>
     */
    public static Map<Long, MasteryPages> getSummonersMasteries(final List<Long> summonerIDs) {
        if(summonerIDs.size() > 40) {
            throw new IllegalArgumentException("Can only get up to 40 summoners' masteries at a time!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("summoner") + "/summoner/" + Utils.getIDString(summonerIDs) + "/masteries";
        final Type type = new TypeToken<Map<Long, MasteryPages>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get masteries for
     * @return the summoners' masteries
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3295">
     *      Riot API Specification</a>
     */
    public static Map<Long, MasteryPages> getSummonersMasteries(final long... summonerIDs) {
        return getSummonersMasteries(Utils.convert(summonerIDs));
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get names of
     * @return the summoners' names
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3296">
     *      Riot API Specification</a>
     */
    public static Map<Long, String> getSummonersNames(final List<Long> summonerIDs) {
        if(summonerIDs.size() > 40) {
            throw new IllegalArgumentException("Can only get up to 40 summoner names at a time!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("summoner") + "/summoner/" + Utils.getIDString(summonerIDs) + "/name";
        final Type type = new TypeToken<Map<Long, String>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get names of
     * @return the summoners' names
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3296">
     *      Riot API Specification</a>
     */
    public static Map<Long, String> getSummonersNames(final long... summonerIDs) {
        return getSummonersNames(Utils.convert(summonerIDs));
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get runes for
     * @return the summoners' runes
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3294">
     *      Riot API Specification</a>
     */
    public static Map<Long, RunePages> getSummonersRunes(final List<Long> summonerIDs) {
        if(summonerIDs.size() > 40) {
            throw new IllegalArgumentException("Can only get up to 40 summoners' runes at a time!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("summoner") + "/summoner/" + Utils.getIDString(summonerIDs) + "/runes";
        final Type type = new TypeToken<Map<Long, RunePages>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, false), type);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get runes for
     * @return the summoners' runes
     * @see <a href="https://developer.riotgames.com/api/methods#!/960/3294">
     *      Riot API Specification</a>
     */
    public static Map<Long, RunePages> getSummonersRunes(final long... summonerIDs) {
        return getSummonersRunes(Utils.convert(summonerIDs));
    }
}
