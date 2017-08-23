package com.merakianalytics.orianna.datapipeline.common;

import com.google.common.base.Preconditions;

public abstract class Utilities {
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
        for(int i = 1; i < objectNamePairs.length; i++) {
            sb.append(", " + objectNamePairs[i]);
        }
        throw new IllegalArgumentException("All of the following query parameters were missing! At least one is required: " + sb.substring(2));
    }

    public static void checkNotNull(final Object... objectNamePairs) {
        if(objectNamePairs.length == 0) {
            return;
        } else if(objectNamePairs.length % 2 != 0) {
            throw new IllegalArgumentException("checkNotNull argument count must be even!");
        }

        for(int i = 0; i < objectNamePairs.length; i += 2) {
            Preconditions.checkNotNull(objectNamePairs[i], "Required query parameter %s was missing!", objectNamePairs[i + 1]);
        }
    }
}
