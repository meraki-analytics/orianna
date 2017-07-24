package com.merakianalytics.datapipelines;

import java.util.Iterator;

import com.merakianalytics.datapipelines.sinks.DataSink;

public class SinkHandler<A, S> {
    public class TransformingIterable implements Iterable<S> {
        private final PipelineContext context;
        private final Iterable<A> iterable;

        public TransformingIterable(final Iterable<A> iterable, final PipelineContext context) {
            this.iterable = iterable;
            this.context = context;
        }

        @Override
        public Iterator<S> iterator() {
            return new TransformingIterator(iterable.iterator(), context);
        }
    }

    public class TransformingIterator implements Iterator<S> {
        private final PipelineContext context;
        private final Iterator<A> iterator;

        public TransformingIterator(final Iterator<A> iterator, final PipelineContext context) {
            this.iterator = iterator;
            this.context = context;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public S next() {
            return transform.transform(iterator.next(), context);
        }
    }
    private final Class<A> acceptedType;
    private final DataSink sink;
    private final Class<S> storedType;
    private final ChainTransform<A, S> transform;

    public SinkHandler(final DataSink sink, final ChainTransform<A, S> transform, final Class<A> acceptedType, final Class<S> storedType) {
        this.sink = sink;
        this.transform = transform;
        this.acceptedType = acceptedType;
        this.storedType = storedType;
    }

    public Class<A> acceptedType() {
        return acceptedType;
    }

    public void put(final A item, final PipelineContext context) {
        final S transformed = transform.transform(item, context);
        sink.put(storedType, transformed, context);
    }

    public void putMany(final Iterable<A> items, final PipelineContext context) {
        final Iterable<S> transforming = new TransformingIterable(items, context);
        sink.putMany(storedType, transforming, context);
    }

    public Class<S> storedType() {
        return storedType;
    }
}
