package com.merakianalytics.orianna.types.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.LazyList;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.core.searchable.AbstractSearchableObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableObject;
import com.merakianalytics.orianna.types.data.CoreData;

public abstract class OriannaObject<T extends CoreData> extends AbstractSearchableObject implements Serializable {
    public static class ListProxy<T, C, L extends CoreData.ListProxy<C>> extends OriannaObject<L> implements SearchableList<T> {
        public class ListProxyIterator implements ListIterator<T> {
            private final ListIterator<T> iterator = data.listIterator();

            @Override
            public void add(final T item) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return iterator.hasPrevious();
            }

            @Override
            public T next() {
                return iterator.next();
            }

            @Override
            public int nextIndex() {
                return iterator.nextIndex();
            }

            @Override
            public T previous() {
                return iterator.previous();
            }

            @Override
            public int previousIndex() {
                return iterator.previousIndex();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(final T item) {
                throw new UnsupportedOperationException();
            }
        }

        private static final long serialVersionUID = -3228932387340246571L;
        private final Class<L> coreType;
        private final List<T> data;

        public ListProxy(final L coreData, final Function<C, T> transform, final Class<L> coreType) {
            super(coreData);
            this.coreType = coreType;

            data = new ArrayList<>(coreData.size());
            for(final C item : coreData) {
                data.add(transform.apply(item));
            }
        }

        private ListProxy(final L coreData, final List<T> data, final Class<L> coreType) {
            super(coreData);
            this.data = data;
            this.coreType = coreType;
        }

        @Override
        public void add(final int index, final T item) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean add(final T item) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(final Collection<? extends T> items) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(final int index, final Collection<? extends T> items) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        private boolean contains(final T element, final Object item) {
            if(item == null && element == null) {
                return true;
            } else if(item == null || element == null) {
                return false;
            } else if(item.equals(element)) {
                return true;
            } else if(element instanceof SearchableObject && ((SearchableObject)element).contains(item)) {
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(final Collection<?> items) {
            return data.containsAll(items);
        }

        @Override
        public void delete(final Object query) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void delete(final Predicate<T> predicate) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ListProxy<T, C, L> filter(final Predicate<T> predicate) {
            return filter(predicate, false);
        }

        @Override
        public ListProxy<T, C, L> filter(final Predicate<T> predicate, final boolean streaming) {
            final L data;
            try {
                data = coreType.newInstance();
            } catch(InstantiationException | IllegalAccessException e) {
                LOGGER.error("Failed to instantiate type " + coreType.getCanonicalName());
                throw new OriannaException("Failed to instantiate type " + coreType.getCanonicalName() + "! Report this to the orianna team!", e);
            }
            if(!streaming) {
                final List<T> results = new ArrayList<>();
                for(int i = 0; i < size(); i++) {
                    final T element = this.data.get(i);
                    if(predicate.apply(element)) {
                        data.add(coreData.get(i));
                        results.add(element);
                    }
                }
                return new ListProxy<>(data, results, coreType);
            } else {
                final CloseableIterator<T> iterator = new CloseableIterator<T>() {
                    private int index = -1;
                    private final Iterator<T> iterator = iterator();
                    private T next = null;

                    @Override
                    public void close() {}

                    @Override
                    public boolean hasNext() {
                        if(next != null) {
                            return true;
                        }

                        while(iterator.hasNext()) {
                            final T n = iterator.next();
                            index++;
                            if(predicate.apply(n)) {
                                next = n;
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override
                    public T next() {
                        if(!hasNext()) {
                            throw new NoSuchElementException();
                        }

                        final T n = next;
                        next = null;
                        data.add(coreData.get(index));
                        return n;
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };

                return new ListProxy<>(data, new LazyList<>(iterator), coreType);
            }
        }

        @Override
        public T find(final Object item) {
            return find(new Predicate<T>() {
                @Override
                public boolean apply(final T element) {
                    return contains(element, item);
                }
            });
        }

        @Override
        public T find(final Predicate<T> predicate) {
            for(final T element : this) {
                if(predicate.apply(element)) {
                    return element;
                }
            }
            return null;
        }

        @Override
        public T get(final int index) {
            return data.get(index);
        }

        @Override
        public int indexOf(final Object item) {
            return data.indexOf(item);
        }

        @Override
        public boolean isEmpty() {
            return data.isEmpty();
        }

        @Override
        public Iterator<T> iterator() {
            return new ListProxyIterator();
        }

        @Override
        public int lastIndexOf(final Object item) {
            return data.lastIndexOf(item);
        }

        @Override
        public ListIterator<T> listIterator() {
            return new ListProxyIterator();
        }

        @Override
        public ListIterator<T> listIterator(final int index) {
            return new ListProxyIterator();
        }

        @Override
        public T remove(final int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(final Object item) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(final Collection<?> items) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(final Collection<?> items) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ListProxy<T, C, L> search(final Object item) {
            return search(item, false);
        }

        @Override
        public ListProxy<T, C, L> search(final Object item, final boolean streaming) {
            return filter(new Predicate<T>() {
                @Override
                public boolean apply(final T element) {
                    return contains(element, item);
                }
            }, streaming);
        }

        @Override
        public T set(final int index, final T item) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return data.size();
        }

        @Override
        public List<T> subList(final int fromIndex, final int toIndex) {
            return data.subList(fromIndex, toIndex);
        }

        @Override
        public Object[] toArray() {
            return data.toArray();
        }

        @SuppressWarnings("hiding")
        @Override
        public <T> T[] toArray(final T[] array) {
            return data.toArray(array);
        }
    }

    public static class MapProxy<K, V, CK, CV, P extends CoreData.MapProxy<CK, CV>> extends OriannaObject<P> implements Map<K, V> {
        private static final long serialVersionUID = 2266943596124327746L;
        private final Map<K, V> data;

        public MapProxy(final P coreData, final Function<CK, K> keyTransform,
                        final Function<CV, V> valueTransform) {
            super(coreData);

            data = new HashMap<>();
            for(final CK coreKey : coreData.keySet()) {
                final CV coreValue = coreData.get(coreKey);
                final K key = keyTransform.apply(coreKey);
                final V value = valueTransform.apply(coreValue);
                data.put(key, value);
            }
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsKey(final Object key) {
            return data.containsKey(key);
        }

        @Override
        public boolean containsValue(final Object value) {
            return data.containsValue(value);
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            return data.entrySet();
        }

        @Override
        public V get(final Object key) {
            return data.get(key);
        }

        @Override
        public boolean isEmpty() {
            return data.isEmpty();
        }

        @Override
        public Set<K> keySet() {
            return data.keySet();
        }

        @Override
        public V put(final K key, final V value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(final Map<? extends K, ? extends V> items) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V remove(final Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return data.size();
        }

        @Override
        public Collection<V> values() {
            return data.values();
        }
    }

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper().registerModule(new JodaModule());
    private static final Logger LOGGER = LoggerFactory.getLogger(OriannaObject.class);
    private static final ObjectMapper MSGPACK_MAPPER = new ObjectMapper(new MessagePackFactory()).registerModule(new JodaModule());
    private static final long serialVersionUID = 5467384615747172442L;

    @JsonValue
    protected T coreData;

    public OriannaObject(final T coreData) {
        this.coreData = coreData;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        final OriannaObject other = (OriannaObject)obj;
        if(coreData == null) {
            if(other.coreData != null) {
                return false;
            }
        } else if(!coreData.equals(other.coreData)) {
            return false;
        }
        return true;
    }

    public T getCoreData() {
        return coreData;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (coreData == null ? 0 : coreData.hashCode());
        return result;
    }

    public byte[] toBytes() {
        try {
            return MSGPACK_MAPPER.writeValueAsBytes(this);
        } catch(final JsonProcessingException e) {
            LOGGER.error("Failed to serialize " + this.getClass().getCanonicalName() + "!", e);
            throw new OriannaException("Failed to serialize object of type " + this.getClass().getName() + " to MsgPack! Report this to the orianna team.", e);
        }
    }

    public String toJSON() {
        try {
            return JSON_MAPPER.writeValueAsString(this);
        } catch(final JsonProcessingException e) {
            LOGGER.error("Failed to serialize " + this.getClass().getCanonicalName() + "!", e);
            throw new OriannaException("Failed to serialize object of type " + this.getClass().getName() + " to JSON! Report this to the orianna team.", e);
        }
    }

    @Override
    public String toString() {
        try {
            return JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch(final JsonProcessingException e) {
            LOGGER.error("Failed to serialize " + this.getClass().getCanonicalName() + "!", e);
            throw new OriannaException("Failed to serialize object of type " + this.getClass().getName() + " to JSON! Report this to the orianna team.", e);
        }
    }
}
