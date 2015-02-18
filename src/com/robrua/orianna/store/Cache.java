package com.robrua.orianna.store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.type.core.OriannaObject;

/**
 * An in-memory cache for data
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class Cache implements DataStore {
    private final Map<Class<?>, Map<Object, OriannaObject<?>>> cache = new HashMap<>();
    private final Map<Class<?>, Boolean> haveAll = new HashMap<>();

    @Override
    public <T extends OriannaObject<?>> void delete(final Class<T> type, final List<?> keys) {
        if(keys.isEmpty()) {
            return;
        }

        final Map<Object, OriannaObject<?>> forType = cache.get(type);
        if(forType == null) {
            return;
        }

        for(final Object key : keys) {
            forType.remove(key);
        }
    }

    @Override
    public <T extends OriannaObject<?>> void delete(final Class<T> type, final Object key) {
        final Map<Object, OriannaObject<?>> forType = cache.get(type);
        if(forType == null) {
            return;
        }

        forType.remove(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends OriannaObject<?>> List<T> get(final Class<T> type, final List<?> keys) {
        final List<T> list = new ArrayList<>(keys.size());
        if(keys.isEmpty()) {
            return list;
        }

        final Map<Object, OriannaObject<?>> forType = cache.get(type);
        if(forType == null) {
            for(int i = 0; i < keys.size(); i++) {
                list.add(null);
            }

            return list;
        }

        for(int i = 0; i < keys.size(); i++) {
            list.add(i, (T)forType.get(keys.get(i)));
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends OriannaObject<?>> T get(final Class<T> type, final Object key) {
        final Map<Object, OriannaObject<?>> forType = cache.get(type);
        if(forType == null) {
            return null;
        }

        return (T)forType.get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends OriannaObject<?>> List<T> getAll(final Class<T> type) {
        final Boolean haveAll = this.haveAll.get(type);

        if(haveAll == null || haveAll == Boolean.FALSE) {
            return null;
        }

        final Map<Object, OriannaObject<?>> forType = cache.get(type);
        if(forType == null) {
            return null;
        }

        final List<T> results = new ArrayList<>((Collection<T>)forType.values());
        return results;
    }

    @Override
    public <T extends OriannaObject<?>> void store(final List<T> objs, final List<?> keys, final boolean isFullSet) {
        if(keys.size() != objs.size()) {
            throw new IllegalArgumentException("Each object must have a key");
        }
        else if(keys.isEmpty()) {
            return;
        }

        final Class<?> clazz = objs.get(0).getClass();

        if(isFullSet) {
            haveAll.put(clazz, true);
        }

        Map<Object, OriannaObject<?>> forType = cache.get(clazz);
        if(forType == null) {
            forType = new HashMap<>();
            cache.put(clazz, forType);
        }

        for(int i = 0; i < keys.size(); i++) {
            forType.put(keys.get(i), objs.get(i));
        }
    }

    @Override
    public <T extends OriannaObject<?>> void store(final T obj, final Object key) {
        final Class<?> clazz = obj.getClass();

        Map<Object, OriannaObject<?>> forType = cache.get(clazz);
        if(forType == null) {
            forType = new HashMap<>();
            cache.put(clazz, forType);
        }

        forType.put(key, obj);
    }
}
