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

public class Languages extends DataObject implements List<String> {
    public static class Deserializer extends StdDeserializer<Languages> {
        private static final long serialVersionUID = 4117329180076155940L;

        public Deserializer() {
            this(null);
        }

        protected Deserializer(final Class<?> vc) {
            super(vc);
        }

        @Override
        public Languages deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JsonProcessingException {
            final List<String> languages = parser.readValueAs(new TypeReference<ArrayList<String>>() {});
            final Languages result = new Languages();
            result.languages = languages;
            return result;
        }
    }

    private static final long serialVersionUID = -4611716582566540599L;

    private List<String> languages;
    private String platform;

    @Override
    public void add(final int index, final String element) {
        languages.add(index, element);
    }

    @Override
    public boolean add(final String e) {
        return languages.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends String> c) {
        return languages.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends String> c) {
        return languages.addAll(index, c);
    }

    @Override
    public void clear() {
        languages.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return languages.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return languages.containsAll(c);
    }

    @Override
    public String get(final int index) {
        return languages.get(index);
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    @Override
    public int indexOf(final Object o) {
        return languages.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return languages.isEmpty();
    }

    @Override
    public Iterator<String> iterator() {
        return languages.iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        return languages.lastIndexOf(o);
    }

    @Override
    public ListIterator<String> listIterator() {
        return languages.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(final int index) {
        return languages.listIterator(index);
    }

    @Override
    public String remove(final int index) {
        return languages.remove(index);
    }

    @Override
    public boolean remove(final Object o) {
        return languages.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return languages.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return languages.retainAll(c);
    }

    @Override
    public String set(final int index, final String element) {
        return languages.set(index, element);
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
        return languages.size();
    }

    @Override
    public List<String> subList(final int fromIndex, final int toIndex) {
        return languages.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return languages.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return languages.toArray(a);
    }
}
