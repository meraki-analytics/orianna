package com.merakianalytics.datapipelines.sinks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.merakianalytics.datapipelines.PipelineContext;

public abstract class AbstractDataSink implements DataSink {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataSink.class);

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

    private void initialize() {
        synchronized(initLock) {
            if(putMethods != null && putManyMethods != null) {
                return;
            }

            final Class<? extends AbstractDataSink> clazz = this.getClass();

            putMethods = new HashMap<>();
            putManyMethods = new HashMap<>();

            for(final Method method : clazz.getMethods()) {
                if(method.isAnnotationPresent(Put.class)) {
                    final Put annotation = method.getAnnotation(Put.class);
                    putMethods.put(annotation.value(), method);
                }

                if(method.isAnnotationPresent(PutMany.class)) {
                    final PutMany annotation = method.getAnnotation(PutMany.class);
                    putManyMethods.put(annotation.value(), method);
                }
            }

            putMethods = Collections.unmodifiableMap(putMethods);
            putManyMethods = Collections.unmodifiableMap(putManyMethods);
        }
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
        if(putManyMethods == null) {
            initialize();
        }

        return putManyMethods;
    }

    private Map<Class<?>, Method> putMethods() {
        if(putMethods == null) {
            initialize();
        }

        return putMethods;
    }
}
