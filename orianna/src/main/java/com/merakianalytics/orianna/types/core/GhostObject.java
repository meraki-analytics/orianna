package com.merakianalytics.orianna.types.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.merakianalytics.orianna.types.data.CoreData;

public abstract class GhostObject<T extends CoreData> extends OriannaObject<T> {
    public static interface LoadHook {
        public void call();
    }
    private static final long serialVersionUID = -1133820478440391056L;
    private final Map<String, Object> groupLocks = new ConcurrentHashMap<>();
    private final Map<String, Boolean> groups = new ConcurrentHashMap<>();

    private final Multimap<String, LoadHook> loadHooks = Multimaps.synchronizedMultimap(HashMultimap.<String, LoadHook> create());

    public GhostObject() {
        super(null);
    }

    public GhostObject(final T coreData) {
        super(coreData);
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
                for(final LoadHook hook : loadHooks.get(group)) {
                    hook.call();
                }
                loadHooks.removeAll(group);
            }
        }
    }

    protected abstract void loadCoreData(String group);

    public void registerGhostLoadHook(final LoadHook hook, final String group) {
        final Boolean loaded = groups.get(group);
        if(loaded == null || !loaded) {
            loadHooks.put(group, hook);
        }
    }
}
