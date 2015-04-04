package com.robrua.orianna.api.dto;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.robrua.orianna.type.api.ParamsBuilder;

public abstract class ChallengeAPI {
    /**
     * @param bucketStartTime
     *            the start time for the 5-minute bucket to query
     * @return randomized match IDs from that bucket
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/980/3340">Riot
     *      API Specification</a>
     */
    public static List<Long> getURFMatchIDs(final Date bucketStartTime) {
        final Calendar date = Calendar.getInstance();
        date.setTime(bucketStartTime);
        if(date.get(Calendar.MINUTE) % 5 != 0 || date.get(Calendar.SECOND) != 0) {
            throw new IllegalArgumentException("Bucket start time must be an even 5 minute offset!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("challenge") + "/game/ids";
        final Map<String, String> params = new ParamsBuilder().add("beginDate", date.getTimeInMillis() / 1000L).build();
        final Type type = new TypeToken<List<Long>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), type);
    }

    /**
     * @param bucketStartTime
     *            the start time for the 5-minute bucket to query (in epoch
     *            milliseconds)
     * @return randomized match IDs from that bucket
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/980/3340">Riot
     *      API Specification</a>
     */
    public static List<Long> getURFMatchIDs(final long bucketStartTime) {
        final Calendar date = Calendar.getInstance();
        date.setTimeInMillis(bucketStartTime);
        if(date.get(Calendar.MINUTE) % 5 != 0 || date.get(Calendar.SECOND) != 0) {
            throw new IllegalArgumentException("Bucket start time must be an even 5 minute offset!");
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("challenge") + "/game/ids";
        final Map<String, String> params = new ParamsBuilder().add("beginDate", date.getTimeInMillis() / 1000L).build();
        final Type type = new TypeToken<List<Long>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, false), type);
    }
}
