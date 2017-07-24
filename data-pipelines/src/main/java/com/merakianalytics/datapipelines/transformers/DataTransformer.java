package com.merakianalytics.datapipelines.transformers;

import java.util.Map;
import java.util.Set;

import com.merakianalytics.datapipelines.PipelineContext;

public interface DataTransformer {
    public int cost();

    public <F, T> T transform(final Class<F> fromType, final Class<T> toType, final F item, final PipelineContext context);

    public Map<Class<?>, Set<Class<?>>> transforms();
}
