package com.robrua.orianna.api;

import java.util.ArrayList;
import java.util.List;

public abstract class Utils {
    /**
     * Breaks a list of IDs into smaller lists under the limited size
     *
     * @param <T>
     *            the type of the IDs
     * @param IDs
     *            the IDs to split
     * @param limit
     *            the list size limit
     * @return the split lists
     */
    public static <T> List<List<T>> breakUpList(final List<T> IDs, final int limit) {
        final List<List<T>> result = new ArrayList<>(IDs.size() / limit + 1);
        List<T> list = new ArrayList<>();
        for(int i = 0; i < IDs.size(); i++) {
            if(i % limit == 0 && i != 0) {
                result.add(list);
                list = new ArrayList<>();
            }
            list.add(IDs.get(i));
        }
        result.add(list);

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
     * @param <T>
     *            the type of the IDs
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

    /**
     * Converts a list of Longs to Integers
     *
     * @param longs
     *            the longs
     * @return a list of Integers
     */
    public static List<Integer> toIntegers(final List<Long> longs) {
        final List<Integer> ints = new ArrayList<>();
        for(final Long lng : longs) {
            ints.add(lng.intValue());
        }

        return ints;
    }
}
