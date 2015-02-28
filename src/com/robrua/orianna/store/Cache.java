package com.robrua.orianna.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.type.core.OriannaObject;

/**
 * An in-memory cache for data
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class Cache extends DataStore {
    private final Map<Class<? extends OriannaObject<?>>, Map<Object, OriannaObject<?>>> cache = new HashMap<>();
    private final Map<Class<? extends OriannaObject<?>>, Boolean> haveAll = new HashMap<>();

    @Override
    protected <T extends OriannaObject<?>> boolean checkHasAll(final Class<T> type) {
        final Boolean val = haveAll.get(type);

        if(val == null) {
            return false;
        }

        return val.booleanValue();
    }

    @Override
    protected <T extends OriannaObject<?>> void doDelete(final Class<T> type, final List<?> keys) {
        final Map<Object, OriannaObject<?>> stored = cache.get(type);

        if(stored != null) {
            for(final Object key : keys) {
                stored.remove(key);
            }
        }
    }

    @Override
    protected <T extends OriannaObject<?>> void doDelete(final Class<T> type, final Object key) {
        final Map<Object, OriannaObject<?>> stored = cache.get(type);

        if(stored != null) {
            stored.remove(key);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends OriannaObject<?>> List<T> doGet(final Class<T> type, final List<?> keys) {
        final Map<Object, OriannaObject<?>> stored = cache.get(type);

        final List<T> result = new ArrayList<>(keys.size());
        if(stored == null) {
            for(int i = 0; i < keys.size(); i++) {
                result.add(null);
            }
        }
        else {
            for(final Object key : keys) {
                result.add((T)stored.get(key));
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends OriannaObject<?>> T doGet(final Class<T> type, final Object key) {
        final Map<Object, OriannaObject<?>> stored = cache.get(type);

        if(stored == null) {
            return null;
        }

        return (T)stored.get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends OriannaObject<?>> List<T> doGetAll(final Class<T> type) {
        final Map<Object, OriannaObject<?>> stored = cache.get(type);

        if(stored == null) {
            return Collections.emptyList();
        }

        final List<T> result = new ArrayList<>();
        for(final Object val : stored.values()) {
            result.add((T)val);
        }
        return result;
    }

    @Override
    protected <T extends OriannaObject<?>> void doStore(final Class<T> type, final List<T> objs, final List<?> keys, final boolean isFullSet) {
        if(isFullSet) {
            haveAll.put(type, Boolean.TRUE);
        }

        Map<Object, OriannaObject<?>> stored = cache.get(type);
        if(stored == null) {
            stored = new HashMap<>();
            cache.put(type, stored);
        }

        for(int i = 0; i < objs.size(); i++) {
            stored.put(keys.get(i), objs.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends OriannaObject<?>> void doStore(final T obj, final Object key) {
        final Class<T> type = (Class<T>)obj.getClass();
        Map<Object, OriannaObject<?>> stored = cache.get(type);
        if(stored == null) {
            stored = new HashMap<>();
            cache.put(type, stored);
        }

        stored.put(key, obj);
    }
}
