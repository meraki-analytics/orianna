package com.robrua.orianna.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.robrua.orianna.type.core.OriannaObject;

public class Cache implements DataStore {
    private final Map<Class<?>, Map<Object, OriannaObject<?>>> cache = new ConcurrentHashMap<>();

    @Override
    public <T extends OriannaObject<?>> void delete(final Class<T> type, final List<Object> keys) {
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
    public <T extends OriannaObject<?>> List<T> get(final Class<T> type, final List<Object> keys) {
        final List<T> list = new ArrayList<>(keys.size());
        if(keys.isEmpty()) {
            return list;
        }

        final Map<Object, OriannaObject<?>> forType = cache.get(type);
        if(forType == null) {
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

    @Override
    public <T extends OriannaObject<?>> void store(final List<T> objs, final List<T> keys) {
        if(keys.size() != objs.size()) {
            throw new IllegalArgumentException("Each object must have a key");
        }
        else if(keys.isEmpty()) {
            return;
        }

        final Class<?> clazz = objs.get(0).getClass();

        Map<Object, OriannaObject<?>> forType = cache.get(clazz);
        if(forType == null) {
            forType = new ConcurrentHashMap<>();
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
            forType = new ConcurrentHashMap<>();
            cache.put(clazz, forType);
        }

        forType.put(key, obj);
    }
}
