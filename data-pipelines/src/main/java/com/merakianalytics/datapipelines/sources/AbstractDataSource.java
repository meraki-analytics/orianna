package com.merakianalytics.datapipelines.sources;

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

import com.merakianalytics.datapipelines.NotSupportedException;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;

public abstract class AbstractDataSource implements DataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataSource.class);

    private Map<Class<?>, Method> getManyMethods;
    private Map<Class<?>, Method> getMethods;

    private final Object initLock = new Object();

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
        if(getManyMethods == null) {
            initialize();
        }

        return getManyMethods;
    }

    private Map<Class<?>, Method> getMethods() {
        if(getMethods == null) {
            initialize();
        }

        return getMethods;
    }

    private void initialize() {
        synchronized(initLock) {
            if(getMethods != null && getManyMethods != null) {
                return;
            }

            final Class<? extends AbstractDataSource> clazz = this.getClass();

            getMethods = new HashMap<>();
            getManyMethods = new HashMap<>();

            for(final Method method : clazz.getMethods()) {
                if(method.isAnnotationPresent(Get.class)) {
                    final Get annotation = method.getAnnotation(Get.class);
                    getMethods.put(annotation.value(), method);
                }

                if(method.isAnnotationPresent(GetMany.class)) {
                    final GetMany annotation = method.getAnnotation(GetMany.class);
                    getManyMethods.put(annotation.value(), method);
                }
            }

            getMethods = Collections.unmodifiableMap(getMethods);
            getManyMethods = Collections.unmodifiableMap(getManyMethods);
        }
    }

    @Override
    public Set<Class<?>> provides() {
        final Set<Class<?>> provides = new HashSet<>();
        provides.addAll(getMethods().keySet());
        provides.addAll(getManyMethods().keySet());
        return Collections.unmodifiableSet(provides);
    }
}
