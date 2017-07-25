package com.merakianalytics.datapipelines.sources;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;

public class CompositeDataSource implements DataSource {
    private final Map<Class<?>, List<DataSource>> sources;

    public CompositeDataSource(final Collection<? extends DataSource> sources) {
        final Map<Class<?>, List<DataSource>> sourceMapping = new HashMap<>();
        for(final DataSource source : sources) {
            for(final Class<?> provided : source.provides()) {
                List<DataSource> forType = sourceMapping.get(provided);

                if(forType == null) {
                    forType = new LinkedList<>();
                    sourceMapping.put(provided, forType);
                }

                forType.add(source);
            }
        }

        for(final Class<?> type : sourceMapping.keySet()) {
            sourceMapping.put(type, Collections.unmodifiableList(sourceMapping.get(type)));
        }
        this.sources = Collections.unmodifiableMap(sourceMapping);
    }

    @Override
    public <T> T get(final Class<T> type, final Map<String, Object> query, final PipelineContext context) {
        final List<DataSource> sources = this.sources.get(type);

        if(sources == null) {
            return null;
        }

        for(final DataSource source : sources) {
            final T result = source.get(type, query, context);
            if(result != null) {
                return result;
            }
        }

        return null;
    }

    @Override
    public <T> CloseableIterator<T> getMany(final Class<T> type, final Map<String, Object> query, final PipelineContext context) {
        final List<DataSource> sources = this.sources.get(type);

        if(sources == null) {
            return null;
        }

        for(final DataSource source : sources) {
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
        return sources.keySet();
    }
}
