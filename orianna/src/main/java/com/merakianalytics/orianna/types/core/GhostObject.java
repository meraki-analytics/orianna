package com.merakianalytics.orianna.types.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.merakianalytics.orianna.types.data.CoreData;

public abstract class GhostObject<T extends CoreData> extends OriannaObject<T> {
    public static interface LoadHook {
        public void call();
    }

    private static final long serialVersionUID = -1133820478440391056L;
    private final Map<String, Object> groupLocks = new ConcurrentHashMap<>();
    private final Map<String, Boolean> groups = new ConcurrentHashMap<>();
    private final Object loadHookLock = new Object();
    private Map<String, Set<LoadHook>> loadHooks;

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
                synchronized(loadHookLock) {
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
