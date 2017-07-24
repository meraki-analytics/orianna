package com.merakianalytics.orianna.type.dto.league;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.merakianalytics.orianna.type.dto.DataObject;

@JsonDeserialize(using = SummonerPositions.Deserializer.class)
public class SummonerPositions extends DataObject implements Set<LeaguePosition> {
    public static class Deserializer extends StdDeserializer<SummonerPositions> {
        private static final long serialVersionUID = 5004306891217083493L;

        public Deserializer() {
            this(null);
        }

        protected Deserializer(final Class<?> vc) {
            super(vc);
        }

        @Override
        public SummonerPositions deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JsonProcessingException {
            final Set<LeaguePosition> leaguePositions = parser.readValueAs(new TypeReference<HashSet<LeaguePosition>>() {});
            final SummonerPositions result = new SummonerPositions();
            result.leaguePositions = leaguePositions;
            return result;
        }
    }

    private static final long serialVersionUID = -8575164188621191128L;

    private Set<LeaguePosition> leaguePositions;
    private String platform;
    private long summonerId;

    @Override
    public boolean add(final LeaguePosition e) {
        return leaguePositions.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends LeaguePosition> c) {
        return leaguePositions.addAll(c);
    }

    @Override
    public void clear() {
        leaguePositions.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return leaguePositions.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return leaguePositions.containsAll(c);
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
        final SummonerPositions other = (SummonerPositions)obj;
        if(leaguePositions == null) {
            if(other.leaguePositions != null) {
                return false;
            }
        } else if(!leaguePositions.equals(other.leaguePositions)) {
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
        result = prime * result + (leaguePositions == null ? 0 : leaguePositions.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        return result;
    }

    @Override
    public boolean isEmpty() {
        return leaguePositions.isEmpty();
    }

    @Override
    public Iterator<LeaguePosition> iterator() {
        return leaguePositions.iterator();
    }

    @Override
    public boolean remove(final Object o) {
        return leaguePositions.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return leaguePositions.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return leaguePositions.retainAll(c);
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
        return leaguePositions.size();
    }

    @Override
    public Object[] toArray() {
        return leaguePositions.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return leaguePositions.toArray(a);
    }
}
