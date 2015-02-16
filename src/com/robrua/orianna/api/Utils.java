package com.robrua.orianna.api;

import java.util.ArrayList;
import java.util.List;

public abstract class Utils {
    /**
     * Breaks a list of IDs into smaller lists under the limited size
     *
     * @param IDs
     *            the IDs to split
     * @param limit
     *            the list size limit
     * @return the split lists
     */
    public static List<List<Long>> breakUpIDs(final List<Long> IDs, final int limit) {
        final List<List<Long>> result = new ArrayList<>(IDs.size() / limit);
        List<Long> list = null;
        for(int i = 0; i < IDs.size(); i++) {
            if(i % limit == 0 && list != null) {
                result.add(list);
                list = new ArrayList<>(limit);
            }
        }

        return result;
    }

    /**
     * Converts an array of longs into a list
     *
     * @param vals
     *            the values to convert
     * @return the result list
     */
    public static List<Long> convert(final long... vals) {
        final List<Long> values = new ArrayList<>(vals.length);
        for(final long val : vals) {
            values.add(val);
        }

        return values;
    }

    /**
     * Stringifies a list of IDs
     *
     * @param IDs
     *            the IDs
     * @return a string of the IDs
     */
    public static <T> String getIDString(final List<T> IDs) {
        final StringBuilder sb = new StringBuilder();
        for(final T ID : IDs) {
            sb.append("," + ID.toString());
        }
        return sb.substring(1);
    }
}
