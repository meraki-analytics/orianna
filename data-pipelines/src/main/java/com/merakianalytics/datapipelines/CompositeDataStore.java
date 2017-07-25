package com.merakianalytics.datapipelines;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;

public class CompositeDataStore implements DataStore {
    private final Map<Class<?>, List<DataStore>> getStores;
    private final Map<Class<?>, List<DataStore>> putStores;

    public CompositeDataStore(final Collection<DataStore> stores) {
        final Map<Class<?>, List<DataStore>> getMapping = new HashMap<>();
        final Map<Class<?>, List<DataStore>> putMapping = new HashMap<>();
        for(final DataStore store : stores) {
            for(final Class<?> provided : store.provides()) {
                List<DataStore> forType = putMapping.get(provided);

                if(forType == null) {
                    forType = new LinkedList<>();
                    getMapping.put(provided, forType);
                }

                forType.add(store);
            }

            for(final Class<?> accepted : store.accepts()) {
                List<DataStore> forType = putMapping.get(accepted);

                if(forType == null) {
                    forType = new LinkedList<>();
                    putMapping.put(accepted, forType);
                }

                forType.add(store);
            }
        }

        for(final Class<?> type : getMapping.keySet()) {
            getMapping.put(type, Collections.unmodifiableList(getMapping.get(type)));
        }
        getStores = Collections.unmodifiableMap(getMapping);
        for(final Class<?> type : putMapping.keySet()) {
            putMapping.put(type, Collections.unmodifiableList(putMapping.get(type)));
        }
        putStores = Collections.unmodifiableMap(putMapping);
    }

    @Override
    public Set<Class<?>> accepts() {
        return putStores.keySet();
    }

    @Override
    public <T> T get(final Class<T> type, final Map<String, Object> query, final PipelineContext context) {
        final List<DataStore> sources = getStores.get(type);

        if(sources == null) {
            return null;
        }

        for(final DataStore source : sources) {
            final T result = source.get(type, query, context);
            if(result != null) {
                return result;
            }
        }

        return null;
    }

    @Override
    public <T> CloseableIterator<T> getMany(final Class<T> type, final Map<String, Object> query, final PipelineContext context) {
        final List<DataStore> sources = getStores.get(type);

        if(sources == null) {
            return null;
        }

        for(final DataStore source : sources) {
            final CloseableIterator<T> result = source.getMany(type, query, context);
            if(result != null) {
                return result;
            }
        }

        return null;
    }

    @Override
    public <T> List<T> getManyAsList(final Class<T> type, final Map<String, Object> query, final PipelineContext context) {
        return CloseableIterators.toList(getMany(type, query, context));
    }

    @Override
    public <T> Set<T> getManyAsSet(final Class<T> type, final Map<String, Object> query, final PipelineContext context) {
        return CloseableIterators.toSet(getMany(type, query, context));
    }

    @Override
    public Set<Class<?>> provides() {
        return getStores.keySet();
    }

    @Override
    public <T> void put(final Class<T> type, final T item, final PipelineContext context) {
        final List<DataStore> sinks = putStores.get(type);

        if(sinks == null) {
            return;
        }

        for(final DataStore sink : sinks) {
            sink.put(type, item, context);
        }
    }

    @Override
    public <T> void putMany(final Class<T> type, final Iterable<T> items, final PipelineContext context) {
        final List<DataStore> sinks = putStores.get(type);

        if(sinks == null) {
            return;
        }

        for(final DataStore sink : sinks) {
            sink.putMany(type, items, context);
        }
    }
}
