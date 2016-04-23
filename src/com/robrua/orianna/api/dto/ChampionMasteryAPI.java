package com.robrua.orianna.api.dto;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.robrua.orianna.type.core.common.PlatformID;
import com.robrua.orianna.type.dto.championmastery.ChampionMastery;

public abstract class ChampionMasteryAPI {
    /**
     * @param summonerID
     *            the summoner to get champion mastery for
     * @return the summoner's mastery for all their champions
     * @see <a href="https://developer.riotgames.com/api/methods#!/1071/3696">
     *      Riot API Specification</a>
     */
    public static List<ChampionMastery> getChampionMastery(final long summonerID) {
        final PlatformID platform = PlatformID.fromRegion(BaseRiotAPI.region);

        final String request = "/championmastery/location/" + platform + "/player/" + summonerID + "/champions";
        final Type type = new TypeToken<List<ChampionMastery>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.getRoot(request, null, false), type);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get mastery for
     * @param championID
     *            the ID of the champion to get mastery for
     * @return the champion's mastery score
     * @see <a href="https://developer.riotgames.com/api/methods#!/1071/3697">
     *      Riot API Specification</a>
     */
    public static ChampionMastery getChampionMastery(final long summonerID, final long championID) {
        final PlatformID platform = PlatformID.fromRegion(BaseRiotAPI.region);

        final String request = "/championmastery/location/" + platform + "/player/" + summonerID + "/champion/" + championID;
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.getRoot(request, null, false), ChampionMastery.class);
    }

    /**
     * @param summonerID
     *            the summoner to get masteries for
     * @param count
     *            the number of top champions to get
     * @return the *count* highest champion masteries for the summoner
     * @see <a href="https://developer.riotgames.com/api/methods#!/1071/3692">
     *      Riot API Specification</a>
     */
    public static List<ChampionMastery> getTopChampionMastery(final long summonerID, final int count) {
        if(count <= 0) {
            throw new IllegalArgumentException("Count must be positive!");
        }

        final PlatformID platform = PlatformID.fromRegion(BaseRiotAPI.region);
        final Map<String, String> params = new HashMap<>();
        params.put("count", Integer.toString(count));

        final String request = "/championmastery/location/" + platform + "/player/" + summonerID + "/topchampions";
        final Type type = new TypeToken<List<ChampionMastery>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.getRoot(request, params, false), type);
    }

    /**
     * @param summonerID
     *            the summoner to get total mastery level for
     * @return the summoner's total mastery level
     * @see <a href="https://developer.riotgames.com/api/methods#!/1071/3698">
     *      Riot API Specification</a>
     */
    public static int getTotalMasteryLevel(final long summonerID) {
        final PlatformID platform = PlatformID.fromRegion(BaseRiotAPI.region);

        final String request = "/championmastery/location/" + platform + "/player/" + summonerID + "/score";
        return Integer.parseInt(BaseRiotAPI.getRoot(request, null, false));
    }
}
