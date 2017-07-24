package com.merakianalytics.datapipelines.sinks;

import java.util.Set;

import com.merakianalytics.datapipelines.PipelineContext;

public interface DataSink {
    public Set<Class<?>> accepts();

    public <T> void put(final Class<T> type, final T item, final PipelineContext context);

    public <T> void putMany(final Class<T> type, final Iterable<T> items, final PipelineContext context);
}
