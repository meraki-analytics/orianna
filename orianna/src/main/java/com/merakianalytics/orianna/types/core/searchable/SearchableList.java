package com.merakianalytics.orianna.types.core.searchable;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.Predicate;

public interface SearchableList<T> extends SearchableObject, List<T>, Serializable {
    public void delete(Object query);

    public void delete(Predicate<T> predicate);

    public SearchableList<T> filter(Predicate<T> predicate);

    public SearchableList<T> filter(Predicate<T> predicate, boolean streaming);

    public T find(Object query);

    public T find(Predicate<T> predicate);

    public SearchableList<T> search(Object query);

    public SearchableList<T> search(Object query, boolean streaming);
}
