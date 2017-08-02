package com.merakianalytics.orianna.types.dto.league;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

@JsonDeserialize(using = SummonerLeagues.Deserializer.class)
@JsonSerialize(using = SummonerLeagues.Serializer.class)
public class SummonerLeagues extends DataObject implements Set<LeagueList> {
    public static class Deserializer extends JsonDeserializer<SummonerLeagues> {
        @Override
        public SummonerLeagues deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
            final Set<LeagueList> leagueLists = parser.readValueAs(new TypeReference<HashSet<LeagueList>>() {});
            final SummonerLeagues result = new SummonerLeagues();
            result.leagueLists = leagueLists;
            return result;
        }
    }

    public static class Serializer extends JsonSerializer<SummonerLeagues> {
        @Override
        public void serialize(final SummonerLeagues leagues, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
            generator.writeObject(leagues.leagueLists);
        }
    }

    private static final long serialVersionUID = 2258306460397359005L;

    private Set<LeagueList> leagueLists;
    private String platform;
    private long summonerId;

    @Override
    public boolean add(final LeagueList e) {
        return leagueLists.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends LeagueList> c) {
        return leagueLists.addAll(c);
    }

    @Override
    public void clear() {
        leagueLists.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return leagueLists.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return leagueLists.containsAll(c);
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
        final SummonerLeagues other = (SummonerLeagues)obj;
        if(leagueLists == null) {
            if(other.leagueLists != null) {
                return false;
            }
        } else if(!leagueLists.equals(other.leagueLists)) {
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
        result = prime * result + (leagueLists == null ? 0 : leagueLists.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        return result;
    }

    @Override
    public boolean isEmpty() {
        return leagueLists.isEmpty();
    }

    @Override
    public Iterator<LeagueList> iterator() {
        return leagueLists.iterator();
    }

    @Override
    public boolean remove(final Object o) {
        return leagueLists.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return leagueLists.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return leagueLists.retainAll(c);
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
        return leagueLists.size();
    }

    @Override
    public Object[] toArray() {
        return leagueLists.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return leagueLists.toArray(a);
    }
}
