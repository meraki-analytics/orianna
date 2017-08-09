package com.merakianalytics.datapipelines;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sinks.Put;
import com.merakianalytics.datapipelines.sinks.PutMany;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;

public abstract class AbstractDataStore implements DataStore {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataStore.class);

    private Map<Class<?>, Method> getManyMethods;
    private Map<Class<?>, Method> getMethods;

    private final Object initLock = new Object();

    private Map<Class<?>, Method> putManyMethods;
    private Map<Class<?>, Method> putMethods;

    @Override
    public Set<Class<?>> accepts() {
        final Set<Class<?>> provides = new HashSet<>();
        provides.addAll(putMethods().keySet());
        provides.addAll(putManyMethods().keySet());
        return Collections.unmodifiableSet(provides);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(final Class<T> type, final Map<String, Object> query, final PipelineContext context) {
        try {
            final Method method = getMethods().get(type);

            if(method == null) {
                throw new NotSupportedException(type.getCanonicalName() + " is not supported by this DataSource!");
            }

            return (T)method.invoke(this, query, context);
        } catch(final InvocationTargetException e) {
            LOGGER.error("Failed to invoke get method for " + type.getCanonicalName() + ".", e);
            throw new RuntimeException(e.getCause());
        } catch(IllegalAccessException | IllegalArgumentException e) {
            LOGGER.error("Failed to invoke get method for " + type.getCanonicalName() + ".", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> CloseableIterator<T> getMany(final Class<T> type, final Map<String, Object> query, final PipelineContext context) {
        try {
            final Method method = getManyMethods().get(type);

            if(method == null) {
                throw new NotSupportedException(type.getCanonicalName() + " is not supported by this DataSource!");
            }

            return (CloseableIterator<T>)method.invoke(this, query, context);
        } catch(final InvocationTargetException e) {
            LOGGER.error("Failed to invoke getMany method for " + type.getCanonicalName() + ".", e);
            throw new RuntimeException(e.getCause());
        } catch(IllegalAccessException | IllegalArgumentException e) {
            LOGGER.error("Failed to invoke getMany method for " + type.getCanonicalName() + ".", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getManyAsList(final Class<T> type, final Map<String, Object> query, final PipelineContext context) {
        return CloseableIterators.toList(getMany(type, query, context));
    }

    @Override
    public <T> Set<T> getManyAsSet(final Class<T> type, final Map<String, Object> query, final PipelineContext context) {
        return CloseableIterators.toSet(getMany(type, query, context));
    }

    private Map<Class<?>, Method> getManyMethods() {
        synchronized(initLock) {
            if(getManyMethods == null) {
                initialize();
            }
        }
        return getManyMethods;
    }

    private Map<Class<?>, Method> getMethods() {
        synchronized(initLock) {
            if(getMethods == null) {
                initialize();
            }
        }
        return getMethods;
    }

    private void initialize() {
        synchronized(initLock) {
            if(getMethods != null && getManyMethods != null && putMethods != null && putManyMethods != null) {
                return;
            }

            final Class<? extends AbstractDataStore> clazz = this.getClass();

            getMethods = new HashMap<>();
            getManyMethods = new HashMap<>();

            putMethods = new HashMap<>();
            putManyMethods = new HashMap<>();

            for(final Method method : clazz.getMethods()) {
                if(method.isAnnotationPresent(Get.class)) {
                    final Get annotation = method.getAnnotation(Get.class);
                    getMethods.put(annotation.value(), method);
                }

                if(method.isAnnotationPresent(GetMany.class)) {
                    final GetMany annotation = method.getAnnotation(GetMany.class);
                    getManyMethods.put(annotation.value(), method);
                }

                if(method.isAnnotationPresent(Put.class)) {
                    final Put annotation = method.getAnnotation(Put.class);
                    putMethods.put(annotation.value(), method);
                }

                if(method.isAnnotationPresent(PutMany.class)) {
                    final PutMany annotation = method.getAnnotation(PutMany.class);
                    putManyMethods.put(annotation.value(), method);
                }
            }

            getMethods = Collections.unmodifiableMap(getMethods);
            getManyMethods = Collections.unmodifiableMap(getManyMethods);

            putMethods = Collections.unmodifiableMap(putMethods);
            putManyMethods = Collections.unmodifiableMap(putManyMethods);
        }
    }

    @Override
    public Set<Class<?>> provides() {
        final Set<Class<?>> provides = new HashSet<>();
        provides.addAll(getMethods().keySet());
        provides.addAll(getManyMethods().keySet());
        return Collections.unmodifiableSet(provides);
    }

    @Override
    public <T> void put(final Class<T> type, final T item, final PipelineContext context) {
        try {
            putMethods().get(type).invoke(this, item, context);
        } catch(final InvocationTargetException e) {
            LOGGER.error("Failed to invoke put method for " + type.getCanonicalName() + ".", e);
            throw new RuntimeException(e.getCause());
        } catch(IllegalAccessException | IllegalArgumentException e) {
            LOGGER.error("Failed to invoke put method for " + type.getCanonicalName() + ".", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> void putMany(final Class<T> type, final Iterable<T> items, final PipelineContext context) {
        try {
            putManyMethods().get(type).invoke(this, items, context);
        } catch(final InvocationTargetException e) {
            LOGGER.error("Failed to invoke putMany method for " + type.getCanonicalName() + ".", e);
            throw new RuntimeException(e.getCause());
        } catch(IllegalAccessException | IllegalArgumentException e) {
            LOGGER.error("Failed to invoke putMany method for " + type.getCanonicalName() + ".", e);
            throw new RuntimeException(e);
        }
    }

    private Map<Class<?>, Method> putManyMethods() {
        synchronized(initLock) {
            if(putManyMethods == null) {
                initialize();
            }
        }
        return putManyMethods;
    }

    private Map<Class<?>, Method> putMethods() {
        synchronized(initLock) {
            if(putMethods == null) {
                initialize();
            }
        }

        return putMethods;
    }
}
