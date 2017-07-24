package com.merakianalytics.orianna.type.dto.match;

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

public class TournamentMatches extends DataObject implements List<Long> {
    public static class Deserializer extends StdDeserializer<TournamentMatches> {
        private static final long serialVersionUID = -6955746479477089189L;

        public Deserializer() {
            this(null);
        }

        protected Deserializer(final Class<?> vc) {
            super(vc);
        }

        @Override
        public TournamentMatches deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JsonProcessingException {
            final List<Long> ids = parser.readValueAs(new TypeReference<ArrayList<Long>>() {});
            final TournamentMatches result = new TournamentMatches();
            result.ids = ids;
            return result;
        }
    }

    private static final long serialVersionUID = -8177611799836384800L;
    private List<Long> ids;
    private String platform, tournamentCode;

    @Override
    public void add(final int index, final Long element) {
        ids.add(index, element);
    }

    @Override
    public boolean add(final Long e) {
        return ids.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends Long> c) {
        return ids.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends Long> c) {
        return ids.addAll(index, c);
    }

    @Override
    public void clear() {
        ids.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return ids.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return ids.containsAll(c);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        final TournamentMatches other = (TournamentMatches)obj;
        if(ids == null) {
            if(other.ids != null) {
                return false;
            }
        } else if(!ids.equals(other.ids)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(tournamentCode == null) {
            if(other.tournamentCode != null) {
                return false;
            }
        } else if(!tournamentCode.equals(other.tournamentCode)) {
            return false;
        }
        return true;
    }

    @Override
    public Long get(final int index) {
        return ids.get(index);
    }

    /**
     * @return the ids
     */
    public List<Long> getIds() {
        return ids;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the tournamentCode
     */
    public String getTournamentCode() {
        return tournamentCode;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ids == null ? 0 : ids.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (tournamentCode == null ? 0 : tournamentCode.hashCode());
        return result;
    }

    @Override
    public int indexOf(final Object o) {
        return ids.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return ids.isEmpty();
    }

    @Override
    public Iterator<Long> iterator() {
        return ids.iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        return ids.lastIndexOf(o);
    }

    @Override
    public ListIterator<Long> listIterator() {
        return ids.listIterator();
    }

    @Override
    public ListIterator<Long> listIterator(final int index) {
        return ids.listIterator(index);
    }

    @Override
    public Long remove(final int index) {
        return ids.remove(index);
    }

    @Override
    public boolean remove(final Object o) {
        return ids.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return ids.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return ids.retainAll(c);
    }

    @Override
    public Long set(final int index, final Long element) {
        return ids.set(index, element);
    }

    /**
     * @param ids
     *        the ids to set
     */
    public void setIds(final List<Long> ids) {
        this.ids = ids;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param tournamentCode
     *        the tournamentCode to set
     */
    public void setTournamentCode(final String tournamentCode) {
        this.tournamentCode = tournamentCode;
    }

    @Override
    public int size() {
        return ids.size();
    }

    @Override
    public List<Long> subList(final int fromIndex, final int toIndex) {
        return ids.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return ids.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return ids.toArray(a);
    }
}
