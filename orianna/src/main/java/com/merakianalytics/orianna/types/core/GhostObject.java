package com.merakianalytics.orianna.types.core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Function;
import com.merakianalytics.orianna.types.data.CoreData;

public abstract class GhostObject<T extends CoreData> extends OriannaObject<T> {
    public static interface LoadHook {
        public void call();
    }

    public abstract static class MapProxy<K, V, CK, CV, P extends CoreData.MapProxy<CK, CV>> extends GhostObject<P> implements Map<K, V> {
        public static final String MAP_PROXY_LOAD_GROUP = "map-proxy";
        private static final long serialVersionUID = 2266943596124327746L;
        private Map<K, V> data;
        private Function<CK, K> keyTransform;
        private Function<CV, V> valueTransform;

        public MapProxy(final P coreData, final int loadGroups) {
            super(coreData, loadGroups);
        }

        public MapProxy(final P coreData, final int loadGroups, final Function<CK, K> keyTransform,
                        final Function<CV, V> valueTransform) {
            super(coreData, loadGroups);

            this.keyTransform = keyTransform;
            this.valueTransform = valueTransform;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsKey(final Object key) {
            load(MAP_PROXY_LOAD_GROUP);
            return data.containsKey(key);
        }

        @Override
        public boolean containsValue(final Object value) {
            load(MAP_PROXY_LOAD_GROUP);
            return data.containsValue(value);
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            load(MAP_PROXY_LOAD_GROUP);
            return data.entrySet();
        }

        @Override
        public V get(final Object key) {
            load(MAP_PROXY_LOAD_GROUP);
            return data.get(key);
        }

        @Override
        public boolean isEmpty() {
            load(MAP_PROXY_LOAD_GROUP);
            return data.isEmpty();
        }

        @Override
        public Set<K> keySet() {
            load(MAP_PROXY_LOAD_GROUP);
            return data.keySet();
        }

        @SuppressWarnings("unchecked")
        protected void loadMapProxyData() {
            if(keyTransform == null && valueTransform == null) {
                data = (Map<K, V>)Collections.unmodifiableMap(coreData);
            } else {
                data = new HashMap<>();
                for(final CK coreKey : coreData.keySet()) {
                    final CV coreValue = coreData.get(coreKey);
                    final K key = keyTransform.apply(coreKey);
                    final V value = valueTransform.apply(coreValue);
                    data.put(key, value);
                }
                keyTransform = null;
                valueTransform = null;
            }
        }

        @Override
        public V put(final K key, final V value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(final Map<? extends K, ? extends V> items) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V remove(final Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            load(MAP_PROXY_LOAD_GROUP);
            return data.size();
        }

        @Override
        public Collection<V> values() {
            load(MAP_PROXY_LOAD_GROUP);
            return data.values();
        }
    }
    private static final long serialVersionUID = -1133820478440391056L;
    private final Map<String, Object> groupLocks;
    private final Map<String, Boolean> groups;
    private final Object loadHookLock = new Object();

    private Map<String, Set<LoadHook>> loadHooks;

    public GhostObject(final T coreData, final int loadGroups) {
        super(coreData);
        groupLocks = new ConcurrentHashMap<>(loadGroups);
        groups = new ConcurrentHashMap<>(loadGroups);
    }

    protected void load(final String group) {
        Boolean loaded = groups.get(group);
        if(loaded == null || !loaded) {
            Object lock = groupLocks.get(group);
            if(lock == null) {
                synchronized(groupLocks) {
                    lock = groupLocks.get(group);
                    if(lock == null) {
                        lock = new Object();
                        groupLocks.put(group, lock);
                    }
                }
            }

            boolean callHooks = false;
            synchronized(lock) {
                loaded = groups.get(group);
                if(loaded == null || !loaded) {
                    loadCoreData(group);
                    groups.put(group, Boolean.TRUE);
                    callHooks = true;
                }
            }

            if(callHooks) {
                synchronized(loadHookLock) {
                    if(loadHooks != null) {
                        for(final LoadHook hook : loadHooks.get(group)) {
                            hook.call();
                        }
                        loadHooks.remove(group);
                        if(loadHooks.isEmpty()) {
                            loadHooks = null;
                        }
                    }
                }
            }
        }
    }

    protected abstract void loadCoreData(String group);

    public void registerGhostLoadHook(final LoadHook hook, final String group) {
        final Boolean loaded = groups.get(group);
        if(loaded == null || !loaded) {
            synchronized(loadHookLock) {
                if(loadHooks == null) {
                    loadHooks = new HashMap<>();
                }
                Set<LoadHook> hooks = loadHooks.get(group);
                if(hooks == null) {
                    hooks = new HashSet<>();
                    loadHooks.put(group, hooks);
                }
                hooks.add(hook);
            }
        }
    }
}
