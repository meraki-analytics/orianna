package com.robrua.orianna.store;

import java.util.Collections;
import java.util.Iterator;

/**
 * An iterator that may need to be closed
 *
 * @author Rob Rua (robrua@alumni.cmu.edu)
 */
public abstract class CloseableIterator<T> implements Iterator<T>, AutoCloseable {
    /**
     * @param <T>
     *            the type to iterate
     * @return an empty iterator
     */
    @SuppressWarnings("unchecked")
    public static <T> CloseableIterator<T> emptyIterator() {
        return fromIterator((Iterator<T>)Collections.emptyIterator());
    }

    /**
     * @param <T>
     *            the type to iterate
     * @param iterator
     *            the non-closeable iterator to wrap
     * @return a wrapped version of the iterator
     */
    public static <T> CloseableIterator<T> fromIterator(final Iterator<T> iterator) {
        return new CloseableIterator<T>() {

            @Override
            public void close() throws Exception {
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
