package com.merakianalytics.orianna.type.data.champion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.merakianalytics.orianna.type.common.Platform;
import com.merakianalytics.orianna.type.common.Region;
import com.merakianalytics.orianna.type.data.CoreData;

@JsonDeserialize(using = ChampionStatusList.Deserializer.class)
@JsonSerialize(using = ChampionStatusList.Serializer.class)
public class ChampionStatusList extends CoreData implements List<ChampionStatus> {
    public static class Deserializer extends JsonDeserializer<ChampionStatusList> {
        @Override
        public ChampionStatusList deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
            final List<ChampionStatus> statuses = parser.readValueAs(new TypeReference<ArrayList<ChampionStatus>>() {});
            final ChampionStatusList result = new ChampionStatusList();
            result.statuses = statuses;
            return result;
        }
    }

    public static class Serializer extends JsonSerializer<ChampionStatusList> {
        @Override
        public void serialize(final ChampionStatusList statuses, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
            generator.writeObject(statuses.statuses);
        }
    }

    private static final long serialVersionUID = -7185225840957807199L;

    private boolean freeToPlay;
    private Platform platform;
    private List<ChampionStatus> statuses;

    @Override
    public boolean add(final ChampionStatus e) {
        return statuses.add(e);
    }

    @Override
    public void add(final int index, final ChampionStatus element) {
        statuses.add(index, element);
    }

    @Override
    public boolean addAll(final Collection<? extends ChampionStatus> c) {
        return statuses.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends ChampionStatus> c) {
        return statuses.addAll(index, c);
    }

    @Override
    public void clear() {
        statuses.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return statuses.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return statuses.containsAll(c);
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
        final ChampionStatusList other = (ChampionStatusList)obj;
        if(statuses == null) {
            if(other.statuses != null) {
                return false;
            }
        } else if(!statuses.equals(other.statuses)) {
            return false;
        }
        if(freeToPlay != other.freeToPlay) {
            return false;
        }
        if(platform != other.platform) {
            return false;
        }
        return true;
    }

    @Override
    public ChampionStatus get(final int index) {
        return statuses.get(index);
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the region
     */
    @JsonIgnore
    public Region getRegion() {
        return platform == null ? null : platform.getRegion();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (statuses == null ? 0 : statuses.hashCode());
        result = prime * result + (freeToPlay ? 1231 : 1237);
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        return result;
    }

    @Override
    public int indexOf(final Object o) {
        return statuses.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return statuses.isEmpty();
    }

    /**
     * @return the freeToPlay
     */
    public boolean isFreeToPlay() {
        return freeToPlay;
    }

    @Override
    public Iterator<ChampionStatus> iterator() {
        return statuses.iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        return statuses.lastIndexOf(o);
    }

    @Override
    public ListIterator<ChampionStatus> listIterator() {
        return statuses.listIterator();
    }

    @Override
    public ListIterator<ChampionStatus> listIterator(final int index) {
        return statuses.listIterator(index);
    }

    @Override
    public ChampionStatus remove(final int index) {
        return statuses.remove(index);
    }

    @Override
    public boolean remove(final Object o) {
        return statuses.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return statuses.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return statuses.retainAll(c);
    }

    @Override
    public ChampionStatus set(final int index, final ChampionStatus element) {
        return statuses.set(index, element);
    }

    /**
     * @param freeToPlay
     *        the freeToPlay to set
     */
    public void setFreeToPlay(final boolean freeToPlay) {
        this.freeToPlay = freeToPlay;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param region
     *        the region to set
     */
    public void setRegion(final Region region) {
        platform = region == null ? null : region.getPlatform();
    }

    @Override
    public int size() {
        return statuses.size();
    }

    @Override
    public List<ChampionStatus> subList(final int fromIndex, final int toIndex) {
        return statuses.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return statuses.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return statuses.toArray(a);
    }
}
