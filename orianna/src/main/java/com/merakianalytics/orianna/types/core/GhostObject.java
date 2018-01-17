package com.merakianalytics.orianna.types.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Function;
import com.merakianalytics.orianna.types.data.CoreData;

public abstract class GhostObject<T extends CoreData> extends OriannaObject<T> {
    public static abstract class ListProxy<T, C, L extends CoreData.ListProxy<C>> extends GhostObject<L> implements List<T> {
        // TODO: ListProxy should be a SearchableList
        public class ListProxyIterator implements ListIterator<T> {
            private final ListIterator<T> iterator = data.listIterator();

            @Override
            public void add(final T item) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return iterator.hasPrevious();
            }

            @Override
            public T next() {
                return iterator.next();
            }

            @Override
            public int nextIndex() {
                return iterator.nextIndex();
            }

            @Override
            public T previous() {
                return iterator.previous();
            }

            @Override
            public int previousIndex() {
                return iterator.previousIndex();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(final T item) {
                throw new UnsupportedOperationException();
            }
        }

        public static final String LIST_PROXY_LOAD_GROUP = "list-proxy";
        private static final long serialVersionUID = 781726166394753316L;
        private List<T> data;

        public ListProxy(final L coreData, final int loadGroups) {
            super(coreData, loadGroups);
        }

        @Override
        public void add(final int index, final T item) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean add(final T item) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(final Collection<? extends T> items) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(final int index, final Collection<? extends T> items) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsAll(final Collection<?> items) {
            load(LIST_PROXY_LOAD_GROUP);
            return data.containsAll(items);
        }

        @Override
        public T get(final int index) {
            load(LIST_PROXY_LOAD_GROUP);
            return data.get(index);
        }

        @Override
        public int indexOf(final Object item) {
            load(LIST_PROXY_LOAD_GROUP);
            return data.indexOf(item);
        }

        @Override
        public boolean isEmpty() {
            load(LIST_PROXY_LOAD_GROUP);
            return data.isEmpty();
        }

        @Override
        public Iterator<T> iterator() {
            load(LIST_PROXY_LOAD_GROUP);
            return new ListProxyIterator();
        }

        @Override
        public int lastIndexOf(final Object item) {
            load(LIST_PROXY_LOAD_GROUP);
            return data.lastIndexOf(item);
        }

        @Override
        public ListIterator<T> listIterator() {
            load(LIST_PROXY_LOAD_GROUP);
            return new ListProxyIterator();
        }

        @Override
        public ListIterator<T> listIterator(final int index) {
            load(LIST_PROXY_LOAD_GROUP);
            return new ListProxyIterator();
        }

        @SuppressWarnings("unchecked")
        protected void loadListProxyData(Function<C, T> transform) {
            if(transform == null) {
                data = (List<T>)Collections.unmodifiableList(coreData);
            } else {
                data = new ArrayList<>(coreData.size());
                for(final C item : coreData) {
                    data.add(transform.apply(item));
                }
                data = Collections.unmodifiableList(data);
                transform = null;
            }
        }

        @Override
        public T remove(final int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(final Object item) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(final Collection<?> items) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(final Collection<?> items) {
            throw new UnsupportedOperationException();
        }

        @Override
        public T set(final int index, final T item) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            load(LIST_PROXY_LOAD_GROUP);
            return data.size();
        }

        @Override
        public List<T> subList(final int fromIndex, final int toIndex) {
            load(LIST_PROXY_LOAD_GROUP);
            return data.subList(fromIndex, toIndex);
        }

        @Override
        public Object[] toArray() {
            load(LIST_PROXY_LOAD_GROUP);
            return data.toArray();
        }

        @SuppressWarnings("hiding")
        @Override
        public <T> T[] toArray(final T[] array) {
            load(LIST_PROXY_LOAD_GROUP);
            return data.toArray(array);
        }
    }

    public static interface LoadHook {
        public void call();
    }

    public abstract static class MapProxy<K, V, CK, CV, P extends CoreData.MapProxy<CK, CV>> extends GhostObject<P> implements Map<K, V> {
        // TODO: SearchableMap should be written and MapProxy should be a SearchableMap
        public static final String MAP_PROXY_LOAD_GROUP = "map-proxy";
        private static final long serialVersionUID = 3151240839151598711L;
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
                data = Collections.unmodifiableMap(data);
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

    public void markAsGhostLoaded(final String group) {
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

            boolean removeHooks = false;
            synchronized(lock) {
                loaded = groups.get(group);
                if(loaded == null || !loaded) {
                    groups.put(group, Boolean.TRUE);
                    removeHooks = true;
                }
            }

            if(removeHooks) {
                synchronized(loadHookLock) {
                    if(loadHooks != null) {
                        loadHooks.remove(group);
                        if(loadHooks.isEmpty()) {
                            loadHooks = null;
                        }
                    }
                }
            }
        }
    }

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
