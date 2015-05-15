package com.robrua.orianna.store;

import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

/**
 * For use when you want absolutely no caching/data storage to be done automatically
 */
public class VoidDataStore extends DataStore {
    @Override
    protected boolean allowsNullStoreKeys() {
        return true;
    }

    @Override
    protected <T extends OriannaObject<?>> boolean checkHasAll(Class<T> type) {
        return false;
    }

    @Override
    protected <T extends OriannaObject<?>> void doDelete(Class<T> type, List<?> keys) {
        // Do nothing
    }

    @Override
    protected <T extends OriannaObject<?>> void doDelete(Class<T> type, Object key) {
        // Do nothing
    }

    @Override
    protected <T extends OriannaObject<?>> List<T> doGet(Class<T> type, List<?> keys) {
        return Collections.emptyList();
    }

    @Override
    protected <T extends OriannaObject<?>> T doGet(Class<T> type, Object key) {
        return null;
    }

    @Override
    protected <T extends OriannaObject<?>> List<T> doGetAll(Class<T> type) {
        return Collections.emptyList();
    }

    @Override
    protected <T extends OriannaObject<?>> CloseableIterator<T> doGetIterator(Class<T> type) {
        return CloseableIterator.emptyIterator();
    }

    @Override
    protected <T extends OriannaObject<?>> void doStore(List<T> objs, List<?> keys, boolean isFullSet) {
        // Do nothing
    }

    @Override
    protected <T extends OriannaObject<?>> void doStore(T obj, Object key) {
        // Do nothing
    }
}
