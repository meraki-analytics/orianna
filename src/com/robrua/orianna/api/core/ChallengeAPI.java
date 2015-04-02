package com.robrua.orianna.api.core;

import java.util.Date;
import java.util.List;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.match.Match;

public abstract class ChallengeAPI {
    /**
     * @param bucketStartTime
     *            the start time for the 5-minute bucket to query
     * @return randomized matches from that bucket
     */
    public static List<Match> getURFMatches(final Date bucketStartTime) {
        return RiotAPI.getMatches(getURFMatchIDs(bucketStartTime));
    }

    /**
     * @param bucketStartTime
     *            the start time for the 5-minute bucket to query (in epoch
     *            seconds)
     * @return randomized matches from that bucket
     */
    public static List<Match> getURFMatches(final long bucketStartTime) {
        return RiotAPI.getMatches(getURFMatchIDs(bucketStartTime));
    }

    /**
     * @param bucketStartTime
     *            the start time for the 5-minute bucket to query
     * @return randomized match IDs from that bucket
     */
    public static List<Long> getURFMatchIDs(final Date bucketStartTime) {
        return BaseRiotAPI.getURFMatchIDs(bucketStartTime);
    }

    /**
     * @param bucketStartTime
     *            the start time for the 5-minute bucket to query (in epoch
     *            seconds)
     * @return randomized match IDs from that bucket
     */
    public static List<Long> getURFMatchIDs(final long bucketStartTime) {
        return BaseRiotAPI.getURFMatchIDs(bucketStartTime);
    }
}
