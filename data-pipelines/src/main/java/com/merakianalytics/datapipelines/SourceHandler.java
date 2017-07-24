package com.merakianalytics.datapipelines;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.DataSource;

public class SourceHandler<P, A> {
    public class TransformingIterator implements CloseableIterator<P> {
        private final PipelineContext context;
        private final CloseableIterator<A> iterator;

        public TransformingIterator(final CloseableIterator<A> iterator, final PipelineContext context) {
            this.iterator = iterator;
            this.context = context;
        }

        @Override
        public void close() {
            iterator.close();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public P next() {
            final A item = iterator.next();

            for(final SinkHandler<A, ?> sink : preTransform) {
                sink.put(item, context);
            }

            final P transformed = transform.transform(item, context);

            for(final SinkHandler<P, ?> sink : postTransform) {
                sink.put(transformed, context);
            }

            return transformed;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceHandler.class);
    private final Class<A> acquiredType;
    private final Set<SinkHandler<P, ?>> postTransform;
    private final Set<SinkHandler<A, ?>> preTransform;
    private final Class<P> providedType;
    private final DataSource source;
    private final ChainTransform<A, P> transform;

    public SourceHandler(final DataSource source, final ChainTransform<A, P> transform, final Set<SinkHandler<A, ?>> preTransform,
                         final Set<SinkHandler<P, ?>> postTransform, final Class<P> providedType, final Class<A> acquiredType) {
        this.source = source;
        this.transform = transform;
        this.preTransform = preTransform;
        this.postTransform = postTransform;
        this.providedType = providedType;
        this.acquiredType = acquiredType;
    }

    public Class<A> acquiredType() {
        return acquiredType;
    }

    public P get(final Map<String, Object> query, final PipelineContext context) {
        final A item = source.get(acquiredType, query, context);

        if(item == null) {
            return null;
        }

        for(final SinkHandler<A, ?> sink : preTransform) {
            sink.put(item, context);
        }

        final P converted = transform.transform(item, context);

        for(final SinkHandler<P, ?> sink : postTransform) {
            sink.put(converted, context);
        }

        return converted;
    }

    public CloseableIterator<P> getMany(final Map<String, Object> query, final PipelineContext context) {
        return getMany(query, context, false);
    }

    public CloseableIterator<P> getMany(final Map<String, Object> query, final PipelineContext context, final boolean streaming) {
        if(!streaming) {
            final List<A> collector = new LinkedList<>();
            try(CloseableIterator<A> result = source.getMany(acquiredType, query, context)) {
                if(result == null) {
                    return null;
                }

                while(result.hasNext()) {
                    collector.add(result.next());
                }
            } catch(final Exception e) {
                LOGGER.error("Error closing results iterator!", e);
                throw new RuntimeException(e);
            }

            for(final SinkHandler<A, ?> sink : preTransform) {
                sink.putMany(collector, context);
            }

            final List<P> transformed = new LinkedList<>();
            for(final A item : collector) {
                transformed.add(transform.transform(item, context));
            }

            for(final SinkHandler<P, ?> sink : postTransform) {
                sink.putMany(transformed, context);
            }

            return CloseableIterators.from(transformed);
        } else {
            final CloseableIterator<A> result = source.getMany(acquiredType, query, context);
            if(result == null) {
                return null;
            }
            return new TransformingIterator(result, context);
        }
    }

    public Class<P> providedType() {
        return providedType;
    }
}
