package com.merakianalytics.orianna.types.core.searchable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Predicate;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.LazyList;

public class SearchableListWrapper<T> implements SearchableList<T> {
    private final List<T> list;

    public SearchableListWrapper(final List<T> list) {
        this.list = list;
    }
    
    public static <T> SearchableList<T> of(List<T> list) {
        return new SearchableListWrapper<T>(list);
    }

    @Override
    public void add(final int index, final T item) {
        list.add(index, item);
    }

    @Override
    public boolean add(final T item) {
        return list.add(item);
    }

    @Override
    public boolean addAll(final Collection<? extends T> items) {
        return list.addAll(items);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends T> items) {
        return list.addAll(index, items);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(final Object item) {
        for(final T element : list) {
            if(contains(element, item)) {
                return true;
            }
        }
        return false;
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
        for(final Object item : items) {
            if(!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void delete(final Object item) {
        delete(new Predicate<T>() {
            @Override
            public boolean apply(final T element) {
                return contains(element, item);
            }
        });
    }

    @Override
    public void delete(final Predicate<T> predicate) {
        final Iterator<T> iterator = list.iterator();
        while(iterator.hasNext()) {
            final T element = iterator.next();
            if(predicate.apply(element)) {
                iterator.remove();
            }
        }
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
        final SearchableListWrapper other = (SearchableListWrapper)obj;
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
        return filter(predicate, false);
    }

    @Override
    public SearchableList<T> filter(final Predicate<T> predicate, final boolean streaming) {
        if(!streaming) {
            final List<T> results = new ArrayList<>();
            for(final T element : list) {
                if(predicate.apply(element)) {
                    results.add(element);
                }
            }
            return new SearchableListWrapper<>(results);
        } else {
            final CloseableIterator<T> iterator = new CloseableIterator<T>() {
                private final Iterator<T> iterator = list.iterator();
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
                        if(predicate.apply(n)) {
                            next = n;
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public T next() {
                    final T n = next;
                    next = null;
                    return n;
                }

                @Override
                public void remove() {
                    iterator.remove();
                }
            };

            return new SearchableListWrapper<>(new LazyList<>(iterator));
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
        for(final T element : list) {
            if(predicate.apply(element)) {
                return element;
            }
        }
        return null;
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
        return list.iterator();
    }

    @Override
    public int lastIndexOf(final Object item) {
        return list.lastIndexOf(item);
    }

    @Override
    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return list.listIterator(index);
    }

    @Override
    public T remove(final int item) {
        return list.remove(item);
    }

    @Override
    public boolean remove(final Object item) {
        return list.remove(item);
    }

    @Override
    public boolean removeAll(final Collection<?> items) {
        return list.removeAll(items);
    }

    @Override
    public boolean retainAll(final Collection<?> items) {
        return list.retainAll(items);
    }

    @Override
    public SearchableList<T> search(final Object item) {
        return search(item, false);
    }

    @Override
    public SearchableList<T> search(final Object item, final boolean streaming) {
        return filter(new Predicate<T>() {
            @Override
            public boolean apply(final T element) {
                return contains(element, item);
            }
        }, streaming);
    }

    @Override
    public T set(final int index, final T item) {
        return list.set(index, item);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<T> subList(final int fromIndex, final int toIndex) {
        return list.subList(fromIndex, toIndex);
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
