package com.robrua.orianna.store;

import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

/**
 * Interface for local storage, be it in-memory, on disk, or otherwise
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public abstract class DataStore {
    /**
     * @return true if this DataStore doesn't need key information to store data
     */
    protected abstract boolean allowsNullStoreKeys();

    /**
     * @param <T>
     *            the type of object to check for
     * @param type
     *            the type of object to check for
     * @return whether the data store contains the full set of possible objects
     *         of this type
     */
    protected abstract <T extends OriannaObject<?>> boolean checkHasAll(Class<T> type);

    /**
     * @param <T>
     *            the type of object to delete
     * @param type
     *            the type of object to delete
     * @param keys
     *            the keys to delete
     */
    public <T extends OriannaObject<?>> void delete(final Class<T> type, final List<?> keys) {
        if(type == null || keys == null || keys.isEmpty()) {
            return;
        }

        doDelete(type, keys);
    }

    /**
     * @param <T>
     *            the type of object to delete
     * @param type
     *            the type of object to delete
     * @param key
     *            the key to delete
     */
    public <T extends OriannaObject<?>> void delete(final Class<T> type, final Object key) {
        if(type == null || key == null) {
            return;
        }

        doDelete(type, key);
    }

    /**
     * @param <T>
     *            the type of object to delete
     * @param type
     *            the type of object to delete
     * @param keys
     *            the keys to delete
     */
    protected abstract <T extends OriannaObject<?>> void doDelete(Class<T> type, List<?> keys);

    /**
     * @param <T>
     *            the type of object to delete
     * @param type
     *            the type of object to delete
     * @param key
     *            the key to delete
     */
    protected abstract <T extends OriannaObject<?>> void doDelete(Class<T> type, Object key);

    /**
     * @param <T>
     *            the type of object to get
     * @param type
     *            the type of object to get
     * @param keys
     *            the keys to get
     * @return a list of retrieved objects (same length as keys list, with null
     *         entries if not found)
     */
    protected abstract <T extends OriannaObject<?>> List<T> doGet(Class<T> type, List<?> keys);

    /**
     * @param <T>
     *            the type of object to get
     * @param type
     *            the type of object to get
     * @param key
     *            the key to get
     * @return the retrieved object (null if not found)
     */
    protected abstract <T extends OriannaObject<?>> T doGet(Class<T> type, Object key);

    /**
     * @param <T>
     *            the type of object to get
     * @param type
     *            the type of object to get
     * @return all objects of that type in the data store
     */
    protected abstract <T extends OriannaObject<?>> List<T> doGetAll(Class<T> type);

    /**
     * @param <T>
     *            the type of object to get
     * @param type
     *            the type of object to get
     * @return an iterator over objects of that type in the data store
     */
    protected abstract <T extends OriannaObject<?>> CloseableIterator<T> doGetIterator(final Class<T> type);

    /**
     * @param <T>
     *            the type of object to store
     * @param objs
     *            the objects to store
     * @param keys
     *            the keys to store them with
     * @param isFullSet
     *            whether these are all the objects of that type that can exist
     */
    protected abstract <T extends OriannaObject<?>> void doStore(List<T> objs, List<?> keys, boolean isFullSet);

    /**
     * @param <T>
     *            the type of object to store
     * @param obj
     *            the object to store
     * @param key
     *            the key to store it with
     */
    protected abstract <T extends OriannaObject<?>> void doStore(T obj, Object key);

    /**
     * @param <T>
     *            the type of object to get
     * @param type
     *            the type of object to get
     * @param keys
     *            the keys to get
     * @return a list of retrieved objects (same length as keys list, with null
     *         entries if not found)
     */
    public <T extends OriannaObject<?>> List<T> get(final Class<T> type, final List<?> keys) {
        if(type == null || keys == null || keys.isEmpty()) {
            return Collections.emptyList();
        }

        return doGet(type, keys);
    }

    /**
     * @param <T>
     *            the type of object to get
     * @param type
     *            the type of object to get
     * @param key
     *            the key to get
     * @return the retrieved object (null if not found)
     */
    public <T extends OriannaObject<?>> T get(final Class<T> type, final Object key) {
        if(type == null || key == null) {
            return null;
        }

        return doGet(type, key);
    }

    /**
     * @param <T>
     *            the type of object to get
     * @param type
     *            the type of object to get
     * @return all objects of that type in the data store
     */
    public <T extends OriannaObject<?>> List<T> getAll(final Class<T> type) {
        if(type == null) {
            return Collections.emptyList();
        }

        return doGetAll(type);
    }

    /**
     * @param <T>
     *            the type of object to get
     * @param type
     *            the type of object to get
     * @return an iterator over objects of that type in the data store
     */
    public <T extends OriannaObject<?>> CloseableIterator<T> getIterator(final Class<T> type) {
        if(type == null) {
            return CloseableIterator.emptyIterator();
        }

        return doGetIterator(type);
    }

    /**
     * @param <T>
     *            the type of object to check for
     * @param type
     *            the type of object to check for
     * @return whether the data store contains the full set of possible objects
     *         of this type
     */
    public <T extends OriannaObject<?>> boolean hasAll(final Class<T> type) {
        if(type == null) {
            return false;
        }

        return checkHasAll(type);
    }

    /**
     * @param <T>
     *            the type of object to store
     * @param objs
     *            the objects to store
     * @param keys
     *            the keys to store them with
     * @param isFullSet
     *            whether these are all the objects of that type that can exist
     */
    public <T extends OriannaObject<?>> void store(final List<T> objs, final List<?> keys, final boolean isFullSet) {
        if(objs == null || !allowsNullStoreKeys() && (keys == null || objs.size() != keys.size()) || objs.isEmpty()) {
            return;
        }

        doStore(objs, keys, isFullSet);
    }

    /**
     * @param <T>
     *            the type of object to store
     * @param obj
     *            the object to store
     * @param key
     *            the key to store it with
     */
    public <T extends OriannaObject<?>> void store(final T obj, final Object key) {
        if(obj == null || !allowsNullStoreKeys() && key == null) {
            return;
        }

        doStore(obj, key);
    }
}
