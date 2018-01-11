package com.merakianalytics.orianna.types.core.searchable;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Predicate;

public class UnmodifiableSearchableList<T> implements SearchableList<T> {
    public static class UnmodifiableListIterator<T> implements ListIterator<T> {
        private final ListIterator<T> iterator;

        private UnmodifiableListIterator(final ListIterator<T> iterator) {
            this.iterator = iterator;
        }

        @Override
        public void add(final T element) {
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
        public void set(final T element) {
            throw new UnsupportedOperationException();
        }
    }

    public static <T> SearchableList<T> of(final SearchableList<T> list) {
        return new UnmodifiableSearchableList<>(list);
    }

    private final SearchableList<T> list;

    public UnmodifiableSearchableList(final SearchableList<T> list) {
        this.list = list;
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

    @Override
    public boolean contains(final Object item) {
        return list.contains(item);
    }

    @Override
    public boolean containsAll(final Collection<?> items) {
        return list.containsAll(items);
    }

    @Override
    public void delete(final Object item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(final Predicate<T> predicate) {
        throw new UnsupportedOperationException();
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
        final UnmodifiableSearchableList other = (UnmodifiableSearchableList)obj;
        if(list == null) {
            if(other.list != null) {
                return false;
            }
        } else if(!list.equals(other.list)) {
            return false;
        }
        return true;
    }

    @Override
    public SearchableList<T> filter(final Predicate<T> predicate) {
        return UnmodifiableSearchableList.of(list.filter(predicate));
    }

    @Override
    public SearchableList<T> filter(final Predicate<T> predicate, final boolean streaming) {
        return UnmodifiableSearchableList.of(list.filter(predicate, streaming));
    }

    @Override
    public T find(final Object item) {
        return list.find(item);
    }

    @Override
    public T find(final Predicate<T> predicate) {
        return list.find(predicate);
    }

    @Override
    public T get(final int index) {
        return list.get(index);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (list == null ? 0 : list.hashCode());
        return result;
    }

    @Override
    public int indexOf(final Object item) {
        return list.indexOf(item);
    }

    @Override
    @JsonIgnore
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return new UnmodifiableListIterator<>(list.listIterator());
    }

    @Override
    public int lastIndexOf(final Object item) {
        return list.lastIndexOf(item);
    }

    @Override
    public ListIterator<T> listIterator() {
        return new UnmodifiableListIterator<>(list.listIterator());
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new UnmodifiableListIterator<>(list.listIterator(index));
    }

    @Override
    public T remove(final int item) {
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
    public SearchableList<T> search(final Object item) {
        return UnmodifiableSearchableList.of(list.search(item));
    }

    @Override
    public SearchableList<T> search(final Object item, final boolean streaming) {
        return UnmodifiableSearchableList.of(list.search(item, streaming));
    }

    @Override
    public T set(final int index, final T item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<T> subList(final int fromIndex, final int toIndex) {
        return Collections.unmodifiableList(list.subList(fromIndex, toIndex));
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> T[] toArray(final T[] a) {
        return list.toArray(a);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for(final T element : this) {
            sb.append(", " + element);
        }
        return "[" + sb.substring(2) + "]";
    }
}
