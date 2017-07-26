package com.merakianalytics.datapipelines;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sinks.DataSink;
import com.merakianalytics.datapipelines.sources.DataSource;
import com.merakianalytics.datapipelines.transformers.DataTransformer;

public class DataPipeline {
    private final Map<Class<?>, Object> sinkHandlerLocks;
    private final Map<Class<?>, Set<SinkHandler<?, ?>>> sinkHandlers;
    private final Set<DataSink> sinks;
    private final Map<Class<?>, Object> sourceHandlerLocks;
    private final Map<Class<?>, List<SourceHandler<?, ?>>> sourceHandlers;
    private final List<DataSource> sources;
    private final Map<DataSource, Set<DataSink>> sourceTargets;
    private final TypeGraph typeGraph;

    public DataPipeline(final Collection<? extends DataTransformer> transformers, final List<? extends PipelineElement> elements) {
        typeGraph = new TypeGraph(transformers);

        final Map<DataSource, Set<DataSink>> targets = new HashMap<>();
        final List<DataSource> sources = new LinkedList<>();
        final Set<DataSink> sinks = new HashSet<>();
        for(final PipelineElement element : elements) {
            if(element instanceof DataSource) {
                final DataSource source = (DataSource)element;
                sources.add(source);
                targets.put(source, Collections.unmodifiableSet(new HashSet<>(sinks)));
            }

            if(element instanceof DataSink) {
                sinks.add((DataSink)element);
            }
        }
        this.sources = Collections.unmodifiableList(sources);
        this.sinks = Collections.unmodifiableSet(sinks);
        sourceTargets = Collections.unmodifiableMap(targets);
        sourceHandlers = new ConcurrentHashMap<>();
        sinkHandlers = new ConcurrentHashMap<>();
        sourceHandlerLocks = new ConcurrentHashMap<>();
        sinkHandlerLocks = new ConcurrentHashMap<>();
    }

    public DataPipeline(final Collection<? extends DataTransformer> transformers, final PipelineElement... elements) {
        this(transformers, Arrays.asList(elements));
    }

    public DataPipeline(final PipelineElement... elements) {
        this(new LinkedList<DataTransformer>(), Arrays.asList(elements));
    }

    @SuppressWarnings({"unchecked", "rawtypes"}) // Can't get specific generic types for new SinkHandler
    private Set<SinkHandler<?, ?>> createSinkHandlers(final Class<?> type, final Set<DataSink> targets) {
        final Set<SinkHandler<?, ?>> handlers = new HashSet<>();
        for(final DataSink sink : targets) {
            final ChainTransform<?, ?> transform = getBestTransform(type, sink.accepts());

            if(transform != null) {
                handlers.add(new SinkHandler(sink, transform, transform.from(), transform.to()));
            }
        }
        return Collections.unmodifiableSet(handlers);
    }

    @SuppressWarnings({"unchecked", "rawtypes"}) // Can't get specific generic types for new SourceHandler
    private List<SourceHandler<?, ?>> createSourceHandlers(final Class<?> type) {
        final List<SourceHandler<?, ?>> handlers = new LinkedList<>();
        for(final DataSource source : sources) {
            if(source.provides().contains(type)) {
                final Set<SinkHandler<?, ?>> sinkHandlers = createSinkHandlers(type, sourceTargets.get(source));
                handlers.add(new SourceHandler(source, ChainTransform.identity(type), sinkHandlers, Collections.emptySet(), type, type));
            } else {
                final ChainTransform<?, ?> transform = getBestTransform(source.provides(), type);

                if(transform != null) {
                    final Set<SinkHandler<?, ?>> preHandlers = createSinkHandlers(transform.from(), sourceTargets.get(source));
                    final Set<SinkHandler<?, ?>> postHandlers = createSinkHandlers(transform.to(), sourceTargets.get(source));
                    handlers.add(new SourceHandler(source, transform, preHandlers, postHandlers, transform.to(), transform.from()));
                }
            }
        }
        return Collections.unmodifiableList(handlers);
    }

    @SuppressWarnings("unchecked") // Pipeline ensures the proper type will be returned from get
    public <T> T get(final Class<T> type, final Map<String, Object> query) {
        final List<SourceHandler<?, ?>> handlers = getSourceHandlers(type);

        if(handlers.isEmpty()) {
            return null;
        }

        final PipelineContext context = newContext();

        for(final SourceHandler<?, ?> handler : handlers) {
            final T result = (T)handler.get(query, context);
            if(result != null) {
                return result;
            }
        }
        return null;
    }

    private ChainTransform<?, ?> getBestTransform(final Class<?> source, final Set<Class<?>> options) {
        ChainTransform<?, ?> best = null;
        int bestCost = Integer.MAX_VALUE;
        for(final Class<?> option : options) {
            final ChainTransform<?, ?> transform = typeGraph.getTransform(source, option);
            if(transform != null && transform.cost() < bestCost) {
                best = transform;
                bestCost = transform.cost();
            }
        }
        return best;
    }

    private ChainTransform<?, ?> getBestTransform(final Set<Class<?>> options, final Class<?> target) {
        ChainTransform<?, ?> best = null;
        int bestCost = Integer.MAX_VALUE;
        for(final Class<?> option : options) {
            final ChainTransform<?, ?> transform = typeGraph.getTransform(option, target);
            if(transform != null && transform.cost() < bestCost) {
                best = transform;
                bestCost = transform.cost();
            }
        }
        return best;
    }

    public <T> CloseableIterator<T> getMany(final Class<T> type, final Map<String, Object> query) {
        return getMany(type, query, false);
    }

    @SuppressWarnings("unchecked") // Pipeline ensures the proper type will be returned from getMany
    public <T> CloseableIterator<T> getMany(final Class<T> type, final Map<String, Object> query, final boolean streaming) {
        final List<SourceHandler<?, ?>> handlers = getSourceHandlers(type);

        if(handlers.isEmpty()) {
            return null;
        }

        final PipelineContext context = newContext();

        for(final SourceHandler<?, ?> handler : handlers) {
            final CloseableIterator<T> result = (CloseableIterator<T>)handler.getMany(query, context, streaming);
            if(result != null) {
                return result;
            }
        }
        return null;
    }

    public <T> List<T> getManyAsList(final Class<T> type, final Map<String, Object> query) {
        return CloseableIterators.toList(getMany(type, query, false));
    }

    public <T> Set<T> getManyAsSet(final Class<T> type, final Map<String, Object> query) {
        return CloseableIterators.toSet(getMany(type, query, false));
    }

    private Set<SinkHandler<?, ?>> getSinkHandlers(final Class<?> type) {
        Set<SinkHandler<?, ?>> handlers = sinkHandlers.get(type);

        if(handlers == null) {
            Object lock = sinkHandlerLocks.get(type);
            if(lock == null) {
                synchronized(sinkHandlerLocks) {
                    lock = sinkHandlerLocks.get(type);
                    if(lock == null) {
                        lock = new Object();
                        sinkHandlerLocks.put(type, lock);
                    }
                }
            }
            synchronized(lock) {
                handlers = sinkHandlers.get(type);
                if(handlers == null) {
                    handlers = createSinkHandlers(type, sinks);
                    sinkHandlers.put(type, handlers);
                }
            }
        }

        return handlers;
    }

    private List<SourceHandler<?, ?>> getSourceHandlers(final Class<?> type) {
        List<SourceHandler<?, ?>> handlers = sourceHandlers.get(type);

        if(handlers == null) {
            Object lock = sourceHandlerLocks.get(type);
            if(lock == null) {
                synchronized(sourceHandlerLocks) {
                    lock = sourceHandlerLocks.get(type);
                    if(lock == null) {
                        lock = new Object();
                        sourceHandlerLocks.put(type, lock);
                    }
                }
            }
            synchronized(lock) {
                handlers = sourceHandlers.get(type);
                if(handlers == null) {
                    handlers = createSourceHandlers(type);
                    sourceHandlers.put(type, handlers);
                }
            }
        }

        return handlers;
    }

    private PipelineContext newContext() {
        final PipelineContext context = new PipelineContext();
        context.setPipeline(this);
        return context;
    }

    @SuppressWarnings("unchecked") // Pipeline ensures the proper type will be accepted by put
    public <T> void put(final Class<T> type, final T item) {
        final Set<SinkHandler<?, ?>> handlers = getSinkHandlers(type);

        if(handlers.isEmpty()) {
            return;
        }

        final PipelineContext context = newContext();

        for(final SinkHandler<?, ?> handler : handlers) {
            ((SinkHandler<T, ?>)handler).put(item, context);
        }
    }

    @SuppressWarnings("unchecked") // Pipeline ensures the proper type will be accepted by putMany
    public <T> void putMany(final Class<T> type, final Iterable<T> items) {
        final Set<SinkHandler<?, ?>> handlers = getSinkHandlers(type);

        if(handlers.isEmpty()) {
            return;
        }

        final PipelineContext context = newContext();

        for(final SinkHandler<?, ?> handler : handlers) {
            ((SinkHandler<T, ?>)handler).putMany(items, context);
        }
    }
}
