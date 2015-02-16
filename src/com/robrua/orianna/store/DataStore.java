package com.robrua.orianna.store;

import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public interface DataStore {
    public <T extends OriannaObject<?>> void delete(Class<T> type, List<Object> keys);

    public <T extends OriannaObject<?>> void delete(Class<T> type, Object key);

    public <T extends OriannaObject<?>> List<T> get(Class<T> type, List<Object> keys);

    public <T extends OriannaObject<?>> T get(Class<T> type, Object key);

    public <T extends OriannaObject<?>> void store(List<T> objs, List<T> keys);

    public <T extends OriannaObject<?>> void store(T obj, Object key);
}
