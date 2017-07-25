package com.merakianalytics.datapipelines.sources;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.PipelineElement;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;

public interface DataSource extends PipelineElement {
    public <T> T get(final Class<T> type, final Map<String, Object> query, final PipelineContext context);

    public <T> CloseableIterator<T> getMany(final Class<T> type, final Map<String, Object> query, final PipelineContext context);

    public <T> List<T> getManyAsList(final Class<T> type, final Map<String, Object> query, final PipelineContext context);

    public <T> Set<T> getManyAsSet(final Class<T> type, final Map<String, Object> query, final PipelineContext context);

    public Set<Class<?>> provides();
}
