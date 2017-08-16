package com.merakianalytics.orianna.types.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.merakianalytics.orianna.types.data.CoreData;

public abstract class GhostObject<T extends CoreData> extends OriannaObject<T> {
    private static final long serialVersionUID = -1133820478440391056L;
    private final Map<String, Object> groupLocks = new ConcurrentHashMap<>();
    private final Map<String, Boolean> groups = new ConcurrentHashMap<>();

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

            synchronized(lock) {
                loaded = groups.get(group);
                if(loaded == null || !loaded) {
                    loadCoreData(group);
                    groups.put(group, Boolean.TRUE);
                }
            }
        }
    }

    protected abstract void loadCoreData(String group);
}
