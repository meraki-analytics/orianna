package com.robrua.orianna.store;

import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

/**
 * Interface for local storage, be it in-memory, on disk, or otherwise
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public interface DataStore {
    /**
     * @param <T>
     *            the type of object to delete
     * @param type
     *            the type of object to delete
     * @param keys
     *            the keys to delete
     */
    public <T extends OriannaObject<?>> void delete(Class<T> type, List<?> keys);

    /**
     * @param <T>
     *            the type of object to delete
     * @param type
     *            the type of object to delete
     * @param key
     *            the key to delete
     */
    public <T extends OriannaObject<?>> void delete(Class<T> type, Object key);

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
    public <T extends OriannaObject<?>> List<T> get(Class<T> type, List<?> keys);

    /**
     * @param <T>
     *            the type of object to get
     * @param type
     *            the type of object to get
     * @param key
     *            the key to get
     * @return the retrieved object (null if not found)
     */
    public <T extends OriannaObject<?>> T get(Class<T> type, Object key);

    /**
     * @param <T>
     *            the type of object to get
     * @param type
     *            the type of object to get
     * @return if all objects have been stored, all objects. If not, null.
     */
    public <T extends OriannaObject<?>> List<T> getAll(Class<T> type);

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
    public <T extends OriannaObject<?>> void store(List<T> objs, List<?> keys, boolean isFullSet);

    /**
     * @param <T>
     *            the type of object to store
     * @param obj
     *            the object to store
     * @param key
     *            the key to store it with
     */
    public <T extends OriannaObject<?>> void store(T obj, Object key);
}
