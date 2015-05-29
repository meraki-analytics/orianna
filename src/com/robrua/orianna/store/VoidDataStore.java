package com.robrua.orianna.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

/**
 * For use when you want absolutely no caching/data storage to be done
 * automatically
 */
public class VoidDataStore extends DataStore {
    @Override
    protected boolean allowsNullStoreKeys() {
        return true;
    }

    @Override
    protected <T extends OriannaObject<?>> boolean checkHasAll(final Class<T> type) {
        return false;
    }

    @Override
    protected <T extends OriannaObject<?>> void doDelete(final Class<T> type, final List<?> keys) {
        // Do nothing
    }

    @Override
    protected <T extends OriannaObject<?>> void doDelete(final Class<T> type, final Object key) {
        // Do nothing
    }

    @Override
    protected <T extends OriannaObject<?>> List<T> doGet(final Class<T> type, final List<?> keys) {
        final List<T> result = new ArrayList<>(keys.size());
        for(int i = 0; i < keys.size(); i++) {
            result.add(null);
        }
        return result;
    }

    @Override
    protected <T extends OriannaObject<?>> T doGet(final Class<T> type, final Object key) {
        return null;
    }

    @Override
    protected <T extends OriannaObject<?>> List<T> doGetAll(final Class<T> type) {
        return Collections.emptyList();
    }

    @Override
    protected <T extends OriannaObject<?>> CloseableIterator<T> doGetIterator(final Class<T> type) {
        return CloseableIterator.emptyIterator();
    }

    @Override
    protected <T extends OriannaObject<?>> void doStore(final List<T> objs, final List<?> keys, final boolean isFullSet) {
        // Do nothing
    }

    @Override
    protected <T extends OriannaObject<?>> void doStore(final T obj, final Object key) {
        // Do nothing
    }
}
