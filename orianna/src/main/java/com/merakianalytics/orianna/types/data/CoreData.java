package com.merakianalytics.orianna.types.data;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merakianalytics.orianna.types.common.OriannaException;

public abstract class CoreData implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public static abstract class ListProxy<T> extends CoreData implements List<T> {
        private static final long serialVersionUID = 4033035619862826400L;

        @JsonProperty
        private final List<T> data;

        public ListProxy() {
            data = new ArrayList<>();
        }

        public ListProxy(final int initialCapacity) {
            data = new ArrayList<>(initialCapacity);
        }

        @Override
        public void add(final int index, final T item) {
            data.add(index, item);
        }

        @Override
        public boolean add(final T item) {
            return data.add(item);
        }

        @Override
        public boolean addAll(final Collection<? extends T> items) {
            return data.addAll(items);
        }

        @Override
        public boolean addAll(final int index, final Collection<? extends T> items) {
            return data.addAll(items);
        }

        @Override
        public void clear() {
            data.clear();
        }

        @Override
        public boolean contains(final Object item) {
            return data.contains(item);
        }

        @Override
        public boolean containsAll(final Collection<?> items) {
            return data.containsAll(items);
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
            final ListProxy other = (ListProxy)obj;
            if(data == null) {
                if(other.data != null) {
                    return false;
                }
            } else if(!data.equals(other.data)) {
                return false;
            }
            return true;
        }

        @Override
        public T get(final int index) {
            return data.get(index);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (data == null ? 0 : data.hashCode());
            return result;
        }

        @Override
        public int indexOf(final Object item) {
            return data.indexOf(item);
        }

        @Override
        @JsonIgnore
        public boolean isEmpty() {
            return data.isEmpty();
        }

        @Override
        public Iterator<T> iterator() {
            return data.iterator();
        }

        @Override
        public int lastIndexOf(final Object item) {
            return data.lastIndexOf(item);
        }

        @Override
        public ListIterator<T> listIterator() {
            return data.listIterator();
        }

        @Override
        public ListIterator<T> listIterator(final int index) {
            return data.listIterator(index);
        }

        @Override
        public T remove(final int item) {
            return data.remove(item);
        }

        @Override
        public boolean remove(final Object item) {
            return data.remove(item);
        }

        @Override
        public boolean removeAll(final Collection<?> items) {
            return data.removeAll(items);
        }

        @Override
        public boolean retainAll(final Collection<?> items) {
            return data.retainAll(items);
        }

        @Override
        public T set(final int index, final T item) {
            return data.set(index, item);
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
        public <T> T[] toArray(final T[] a) {
            return data.toArray(a);
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public static class MapProxy<K, V> extends CoreData implements Map<K, V> {
        private static final long serialVersionUID = -5422339913625018678L;
        private final Map<K, V> data = new HashMap<>();

        @Override
        public void clear() {
            data.clear();
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
        @JsonIgnore
        public boolean isEmpty() {
            return data.isEmpty();
        }

        @Override
        public Set<K> keySet() {
            return data.keySet();
        }

        @Override
        public V put(final K key, final V value) {
            return data.put(key, value);
        }

        @Override
        public void putAll(final Map<? extends K, ? extends V> m) {
            data.putAll(m);
        }

        @Override
        public V remove(final Object key) {
            return data.remove(key);
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

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(CoreData.class);
    private static final ObjectMapper MSGPACK_MAPPER = new ObjectMapper(new MessagePackFactory());
    private static final long serialVersionUID = 4701497355193788806L;

    public static <T extends CoreData> T fromBytes(final Class<T> type, final byte[] msgpack) {
        try {
            return MSGPACK_MAPPER.readValue(msgpack, type);
        } catch(final IOException e) {
            LOGGER.error("Failed to deserialize " + type.getCanonicalName() + "!", e);
            throw new OriannaException("Failed to deserialize object of type " + type.getName() + " from MsgPack! Report this to the orianna team.", e);
        }
    }

    public static <T extends CoreData> T fromJSON(final Class<T> type, final String json) {
        try {
            return JSON_MAPPER.readValue(json, type);
        } catch(final IOException e) {
            LOGGER.error("Failed to deserialize " + type.getCanonicalName() + "!", e);
            throw new OriannaException("Failed to deserialize object of type " + type.getName() + " from JSON! Report this to the orianna team.", e);
        }
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
