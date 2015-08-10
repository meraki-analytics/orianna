package com.robrua.orianna.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.exception.OriannaException;

/**
 * A very simple filesystem-based DB
 *
 * @author Rob Rua (robrua@alumni.cmu.edu)
 */
public class FileSystemDB extends DataStore {
    /**
     * Iterator for DB entries on the file system
     *
     * @author Rob Rua (robrua@alumni.cmu.edu)
     */
    public static class FSIterator<T extends OriannaObject<?>> implements Iterator<T> {
        private final Class<T> clazz;
        private final Iterator<File> files;
        private File lastFile = null;

        /**
         * @param folder
         *            root DB folder for type
         * @param clazz
         *            the type
         */
        private FSIterator(final File folder, final Class<T> clazz) {
            files = Arrays.asList(folder.listFiles()).iterator();
            this.clazz = clazz;
        }

        @Override
        public boolean hasNext() {
            return files.hasNext();
        }

        @Override
        public T next() {
            lastFile = files.next();
            return read(lastFile, clazz);
        }

        @Override
        public void remove() {
            if(lastFile != null) {
                lastFile.delete();
            }
            files.remove();
        }
    }

    private static final String HAVE_ALL_NAME = "meta";

    /**
     * Reads an object from a file
     *
     * @param file
     *            the file to read from
     * @param type
     *            the type of the object stored
     * @return the object in the file
     */
    @SuppressWarnings("unchecked")
    private static <T extends OriannaObject<?>> T read(final File file, final Class<T> type) {
        try {
            return (T)type.getDeclaredConstructors()[0].newInstance(readFile(file));
        }
        catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
            return null;
        }
    }

    /**
     * Reads an object from a file
     *
     * @param file
     *            the file to read from
     * @return the object in the file
     */
    private static Object readFile(final File file) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            return ois.readObject();
        }
        catch(IOException | ClassNotFoundException e) {
            throw new OriannaException("Unable to read file!");
        }
        finally {
            if(ois != null) {
                try {
                    ois.close();
                }
                catch(final IOException e) {
                    throw new OriannaException("Unable to close file stream!");
                }
            }
        }
    }

    /**
     * Writes an object to a file
     *
     * @param file
     *            the file to write to
     * @param object
     *            the object to write
     */
    private static void write(final File file, final OriannaObject<?> object) {
        writeFile(file, object.getDto());
    }

    /**
     * Writes an object to a file
     *
     * @param file
     *            the file to write to
     * @param object
     *            the object to write
     */
    private static void writeFile(final File file, final Object object) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
        }
        catch(final IOException e) {
            throw new OriannaException("Unable to write to file!");
        }
        finally {
            if(oos != null) {
                try {
                    oos.close();
                }
                catch(final IOException e) {
                    throw new OriannaException("Unable to close file stream!");
                }
            }
        }
    }

    private final Map<Class<? extends OriannaObject<?>>, Boolean> haveAll;
    private final File root;

    /**
     * @param root
     *            the root folder of the DB
     */
    @SuppressWarnings("unchecked")
    public FileSystemDB(final File root) {
        if(root == null || root.exists() && !root.isDirectory()) {
            throw new IllegalArgumentException("Root file must be a directory!");
        }

        this.root = root;
        if(!root.exists()) {
            root.mkdirs();
        }

        final File allFile = new File(root, HAVE_ALL_NAME);
        if(!allFile.exists()) {
            haveAll = new HashMap<>();
        }
        else {
            haveAll = (HashMap<Class<? extends OriannaObject<?>>, Boolean>)readFile(allFile);
        }
    }

    /**
     * @param rootPath
     *            the path to the root folder of the DB
     */
    public FileSystemDB(final String rootPath) {
        this(new File(rootPath));
    }

    @Override
    protected boolean allowsNullStoreKeys() {
        return false;
    }

    @Override
    protected <T extends OriannaObject<?>> boolean checkHasAll(final Class<T> type) {
        final Boolean val = haveAll.get(type);

        if(val == null) {
            return false;
        }

        return val.booleanValue();
    }

    @Override
    protected <T extends OriannaObject<?>> void doDelete(final Class<T> type, final List<?> keys) {
        final File folder = getFolder(type);

        for(final Object key : keys) {
            final File file = getFile(folder, key);
            if(file.exists()) {
                file.delete();
            }
        }
    }

    @Override
    protected <T extends OriannaObject<?>> void doDelete(final Class<T> type, final Object key) {
        final File file = getFile(getFolder(type), key);

        if(file.exists()) {
            file.delete();
        }
    }

    @Override
    protected <T extends OriannaObject<?>> List<T> doGet(final Class<T> type, final List<?> keys) {
        final File folder = getFolder(type);
        final List<T> result = new ArrayList<>(keys.size());

        for(final Object key : keys) {
            final File file = getFile(folder, key);
            if(file.exists()) {
                result.add(read(file, type));
            }
            else {
                result.add(null);
            }
        }

        return result;
    }

    @Override
    protected <T extends OriannaObject<?>> T doGet(final Class<T> type, final Object key) {
        final File file = getFile(getFolder(type), key);

        if(!file.exists()) {
            return null;
        }
        return read(file, type);
    }

    @Override
    protected <T extends OriannaObject<?>> List<T> doGetAll(final Class<T> type) {
        final File folder = getFolder(type);

        final List<T> result = new ArrayList<>();
        for(final File file : folder.listFiles()) {
            result.add(read(file, type));
        }

        return result;
    }

    @Override
    public <T extends OriannaObject<?>> CloseableIterator<T> doGetIterator(final Class<T> type) {
        final File folder = getFolder(type);
        return CloseableIterator.fromIterator(new FSIterator<>(folder, type));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends OriannaObject<?>> void doStore(final List<T> objs, final List<?> keys, final boolean isFullSet) {
        final Class<T> type = (Class<T>)objs.get(0).getClass();
        if(isFullSet) {
            haveAll.put(type, Boolean.TRUE);
            writeFile(new File(root, HAVE_ALL_NAME), haveAll);
        }

        final File folder = getFolder(type);

        for(int i = 0; i < objs.size(); i++) {
            write(getFile(folder, keys.get(i)), objs.get(i));
        }
    }

    @Override
    protected <T extends OriannaObject<?>> void doStore(final T obj, final Object key) {
        write(getFile(getFolder(obj.getClass()), key), obj);
    }

    /**
     * @param folder
     *            the folder for the object's type
     * @param key
     *            the key
     * @return the file that the key is or would be stored in
     */
    private File getFile(final File folder, final Object key) {
        final int hashCode = Arrays.hashCode(new Object[] {key, key.getClass().getCanonicalName()});
        return new File(folder, Integer.toString(hashCode));
    }

    /**
     * @param type
     *            a type
     * @return the folder objects of that type are found in
     */
    private File getFolder(final Class<?> type) {
        final File folder = new File(root, Integer.toString(type.getCanonicalName().hashCode()));

        if(!folder.exists()) {
            folder.mkdir();
        }

        return folder;
    }
}
