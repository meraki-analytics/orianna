package com.merakianalytics.orianna.types.dto.championmastery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.merakianalytics.orianna.types.dto.DataObject;

@JsonDeserialize(using = ChampionMasteries.Deserializer.class)
@JsonSerialize(using = ChampionMasteries.Serializer.class)
public class ChampionMasteries extends DataObject implements List<ChampionMastery> {
    public static class Deserializer extends JsonDeserializer<ChampionMasteries> {
        @Override
        public ChampionMasteries deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
            final List<ChampionMastery> championMasteries = parser.readValueAs(new TypeReference<ArrayList<ChampionMastery>>() {});
            final ChampionMasteries result = new ChampionMasteries();
            result.championMasteries = championMasteries;
            return result;
        }
    }

    public static class Serializer extends JsonSerializer<ChampionMasteries> {
        @Override
        public void serialize(final ChampionMasteries masteries, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
            generator.writeObject(masteries.championMasteries);
        }
    }

    private static final long serialVersionUID = 6193594237368849840L;

    private List<ChampionMastery> championMasteries;
    private String platform;
    private long summonerId;

    @Override
    public boolean add(final ChampionMastery e) {
        return championMasteries.add(e);
    }

    @Override
    public void add(final int index, final ChampionMastery element) {
        championMasteries.add(index, element);
    }

    @Override
    public boolean addAll(final Collection<? extends ChampionMastery> c) {
        return championMasteries.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends ChampionMastery> c) {
        return championMasteries.addAll(index, c);
    }

    @Override
    public void clear() {
        championMasteries.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return championMasteries.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return championMasteries.containsAll(c);
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
        final ChampionMasteries other = (ChampionMasteries)obj;
        if(championMasteries == null) {
            if(other.championMasteries != null) {
                return false;
            }
        } else if(!championMasteries.equals(other.championMasteries)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(summonerId != other.summonerId) {
            return false;
        }
        return true;
    }

    @Override
    public ChampionMastery get(final int index) {
        return championMasteries.get(index);
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the summonerId
     */
    public long getSummonerId() {
        return summonerId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (championMasteries == null ? 0 : championMasteries.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        return result;
    }

    @Override
    public int indexOf(final Object o) {
        return championMasteries.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return championMasteries.isEmpty();
    }

    @Override
    public Iterator<ChampionMastery> iterator() {
        return championMasteries.iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        return championMasteries.lastIndexOf(o);
    }

    @Override
    public ListIterator<ChampionMastery> listIterator() {
        return championMasteries.listIterator();
    }

    @Override
    public ListIterator<ChampionMastery> listIterator(final int index) {
        return championMasteries.listIterator(index);
    }

    @Override
    public ChampionMastery remove(final int index) {
        return championMasteries.remove(index);
    }

    @Override
    public boolean remove(final Object o) {
        return championMasteries.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return championMasteries.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return championMasteries.retainAll(c);
    }

    @Override
    public ChampionMastery set(final int index, final ChampionMastery element) {
        return championMasteries.set(index, element);
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final long summonerId) {
        this.summonerId = summonerId;
    }

    @Override
    public int size() {
        return championMasteries.size();
    }

    @Override
    public List<ChampionMastery> subList(final int fromIndex, final int toIndex) {
        return championMasteries.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return championMasteries.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return championMasteries.toArray(a);
    }
}
