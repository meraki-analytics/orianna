package com.merakianalytics.orianna.datapipeline.common;

import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

public abstract class Utilities {
    private static final int MAX_SUMMONER_NAME_LENGTH = 16;
    private static final Pattern VALID_SUMMONER_NAME = Pattern.compile("^[0-9\\p{L} _\\.]+$");

    public static void checkAtLeastOneNotNull(final Object... objectNamePairs) {
        if(objectNamePairs.length == 0) {
            return;
        } else if(objectNamePairs.length % 2 != 0) {
            throw new IllegalArgumentException("checkAtLeastOneNotNull argument count must be even!");
        }

        for(int i = 0; i < objectNamePairs.length; i += 2) {
            if(objectNamePairs[i] != null) {
                return;
            }
        }

        final StringBuilder sb = new StringBuilder();
        for(int i = 1; i < objectNamePairs.length; i += 2) {
            sb.append(", " + objectNamePairs[i]);
        }
        throw new QueryValidationException("All of the following query parameters were missing! At least one is required: " + sb.substring(2));
    }

    public static void checkNotNull(final Object... objectNamePairs) {
        if(objectNamePairs.length == 0) {
            return;
        } else if(objectNamePairs.length % 2 != 0) {
            throw new IllegalArgumentException("checkNotNull argument count must be even!");
        }

        for(int i = 0; i < objectNamePairs.length; i += 2) {
            try {
                Preconditions.checkNotNull(objectNamePairs[i], "Required query parameter %s was missing!", objectNamePairs[i + 1]);
            } catch(final IllegalArgumentException e) {
                throw new QueryValidationException(e.getMessage());
            }
        }
    }

    public static void checkSummonerName(final String name) {
        if(name == null || name.length() > MAX_SUMMONER_NAME_LENGTH || !VALID_SUMMONER_NAME.matcher(name).matches()) {
            throw new QueryValidationException(name + " is not a valid Summoner name!");
        }
    }
}
