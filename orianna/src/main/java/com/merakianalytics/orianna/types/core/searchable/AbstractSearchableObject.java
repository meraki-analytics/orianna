package com.merakianalytics.orianna.types.core.searchable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

public class AbstractSearchableObject implements SearchableObject {
    private static Map<Class<?>, Object> locks = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchableObject.class);
    private static Map<Class<?>, Multimap<Class<?>, Method>> searchable = new ConcurrentHashMap<>();

    @Override
    public boolean contains(final Object item) {
        if(item == null) {
            return false;
        }

        final Collection<Method> targets = getSearchTypes().get(item.getClass());
        if(targets == null || targets.isEmpty()) {
            return false;
        }

        for(final Method method : targets) {
            Object result;
            try {
                result = method.invoke(this);
            } catch(final InvocationTargetException e) {
                LOGGER.error("Failed to invoke search method " + method.getName() + "!", e);
                throw new RuntimeException(e.getCause());
            } catch(IllegalAccessException | IllegalArgumentException e) {
                LOGGER.error("Failed to invoke search method " + method.getName() + "!", e);
                throw new RuntimeException(e);
            }

            if(item.equals(result)) {
                return true;
            } else if(result instanceof SearchableObject) {
                final SearchableObject obj = (SearchableObject)result;
                if(obj.contains(item)) {
                    return true;
                }
            }
        }

        return false;
    }

    private Multimap<Class<?>, Method> getSearchTypes() {
        final Class<?> clazz = this.getClass();

        Multimap<Class<?>, Method> searchTypes = searchable.get(clazz);
        if(searchTypes == null) {
            Object lock = locks.get(clazz);
            if(lock == null) {
                synchronized(locks) {
                    lock = locks.get(clazz);
                    if(lock == null) {
                        lock = new Object();
                        locks.put(clazz, lock);
                    }
                }
            }

            synchronized(lock) {
                searchTypes = searchable.get(clazz);
                if(searchTypes == null) {
                    final ImmutableMultimap.Builder<Class<?>, Method> builder = ImmutableMultimap.builder();
                    for(final Method method : clazz.getMethods()) {
                        if(method.isAnnotationPresent(Searchable.class)) {
                            final Searchable annotation = method.getAnnotation(Searchable.class);
                            for(final Class<?> c : annotation.value()) {
                                builder.put(c, method);
                            }
                        }
                    }
                    searchTypes = builder.build();
                    searchable.put(clazz, searchTypes);
                }
            }
        }
        return searchTypes;
    }
}
