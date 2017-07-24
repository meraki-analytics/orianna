package com.merakianalytics.datapipelines.transformers;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.datapipelines.PipelineContext;

public class CompositeDataTransformer implements DataTransformer {
    private final int cost;
    private final Map<Class<?>, Map<Class<?>, DataTransformer>> transformers;
    private final Map<Class<?>, Set<Class<?>>> transforms;

    public CompositeDataTransformer(final Collection<DataTransformer> transformers) {
        int maxCost = Integer.MIN_VALUE;

        final Map<Class<?>, Map<Class<?>, DataTransformer>> transformerMapping = new HashMap<>();
        for(final DataTransformer transformer : transformers) {
            if(transformer.cost() > maxCost) {
                maxCost = transformer.cost();
            }

            final Map<Class<?>, Set<Class<?>>> transforms = transformer.transforms();
            for(final Class<?> from : transforms.keySet()) {
                Map<Class<?>, DataTransformer> forType = transformerMapping.get(from);

                if(forType == null) {
                    forType = new HashMap<>();
                    transformerMapping.put(from, forType);
                }

                for(final Class<?> to : forType.keySet()) {
                    final DataTransformer current = forType.get(to);

                    if(current == null || transformer.cost() < current.cost()) {
                        forType.put(to, transformer);
                    }
                }
            }
        }

        for(final Class<?> fromType : transformerMapping.keySet()) {
            transformerMapping.put(fromType, Collections.unmodifiableMap(transformerMapping.get(fromType)));
        }
        this.transformers = Collections.unmodifiableMap(transformerMapping);

        final Map<Class<?>, Set<Class<?>>> transforms = new HashMap<>();
        for(final Class<?> from : transformerMapping.keySet()) {
            transforms.put(from, Collections.unmodifiableSet(transformerMapping.get(from).keySet()));
        }
        this.transforms = Collections.unmodifiableMap(transforms);

        cost = maxCost;
    }

    @Override
    public int cost() {
        return cost;
    }

    @Override
    public <F, T> T transform(final Class<F> fromType, final Class<T> toType, final F item, final PipelineContext context) {
        final Map<Class<?>, DataTransformer> forType = transformers.get(fromType);

        if(forType == null) {
            return null;
        }

        final DataTransformer transformer = forType.get(toType);

        if(transformer == null) {
            return null;
        }

        return transformer.transform(fromType, toType, item, context);
    }

    @Override
    public Map<Class<?>, Set<Class<?>>> transforms() {
        return transforms;
    }
}
