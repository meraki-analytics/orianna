package com.merakianalytics.orianna.type.dto.staticdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.merakianalytics.orianna.type.dto.DataObject;

public class Versions extends DataObject implements List<String> {
    public static class Deserializer extends StdDeserializer<Versions> {
        private static final long serialVersionUID = -6171575927291506395L;

        public Deserializer() {
            this(null);
        }

        protected Deserializer(final Class<?> vc) {
            super(vc);
        }

        @Override
        public Versions deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JsonProcessingException {
            final List<String> versions = parser.readValueAs(new TypeReference<ArrayList<String>>() {});
            final Versions result = new Versions();
            result.versions = versions;
            return result;
        }
    }

    private static final long serialVersionUID = -5692520197763698261L;

    private String platform;
    private List<String> versions;

    @Override
    public void add(final int index, final String element) {
        versions.add(index, element);
    }

    @Override
    public boolean add(final String e) {
        return versions.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends String> c) {
        return versions.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends String> c) {
        return versions.addAll(index, c);
    }

    @Override
    public void clear() {
        versions.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return versions.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return versions.containsAll(c);
    }

    @Override
    public String get(final int index) {
        return versions.get(index);
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    @Override
    public int indexOf(final Object o) {
        return versions.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return versions.isEmpty();
    }

    @Override
    public Iterator<String> iterator() {
        return versions.iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        return versions.lastIndexOf(o);
    }

    @Override
    public ListIterator<String> listIterator() {
        return versions.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(final int index) {
        return versions.listIterator(index);
    }

    @Override
    public String remove(final int index) {
        return versions.remove(index);
    }

    @Override
    public boolean remove(final Object o) {
        return versions.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return versions.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return versions.retainAll(c);
    }

    @Override
    public String set(final int index, final String element) {
        return versions.set(index, element);
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    @Override
    public int size() {
        return versions.size();
    }

    @Override
    public List<String> subList(final int fromIndex, final int toIndex) {
        return versions.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return versions.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return versions.toArray(a);
    }
}
