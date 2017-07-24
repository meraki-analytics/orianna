package com.merakianalytics.datapipelines.iterators;

import java.util.Iterator;

public interface CloseableIterator<T> extends Iterator<T>, AutoCloseable {
    @Override
    public void close();
}
