package com.merakianalytics.datapipelines.iterators;

import java.util.Collections;
import java.util.Iterator;

public abstract class CloseableIterators {
    public static <T> CloseableIterator<T> empty() {
        return from(Collections.<T> emptyIterator());
    }

    public static <T> CloseableIterator<T> from(final Iterable<T> iterable) {
        return from(iterable.iterator());
    }

    public static <T> CloseableIterator<T> from(final Iterator<T> iterator) {
        if(iterator instanceof CloseableIterator) {
            return (CloseableIterator<T>)iterator;
        }

        return new CloseableIterator<T>() {
            @Override
            public void close() {
                // Do nothing
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return iterator.next();
            }

            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }
}
