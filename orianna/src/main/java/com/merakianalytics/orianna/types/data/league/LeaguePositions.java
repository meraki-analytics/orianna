package com.merakianalytics.orianna.types.data.league;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.data.CoreData;

public class LeaguePositions extends CoreData implements List<LeaguePosition> {
    private static final long serialVersionUID = 1457442481837252048L;
    private Platform platform;
    private final List<LeaguePosition> positions = new ArrayList<>();
    private long summonerId;

    @Override
    public void add(final int index, final LeaguePosition element) {
        positions.add(index, element);
    }

    @Override
    public boolean add(final LeaguePosition e) {
        return positions.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends LeaguePosition> c) {
        return positions.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends LeaguePosition> c) {
        return positions.addAll(index, c);
    }

    @Override
    public void clear() {
        positions.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return positions.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return positions.containsAll(c);
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
        final LeaguePositions other = (LeaguePositions)obj;
        if(platform != other.platform) {
            return false;
        }
        if(positions == null) {
            if(other.positions != null) {
                return false;
            }
        } else if(!positions.equals(other.positions)) {
            return false;
        }
        if(summonerId != other.summonerId) {
            return false;
        }
        return true;
    }

    @Override
    public LeaguePosition get(final int index) {
        return positions.get(index);
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

    /**
     * @return the summonerId
     */
    public long getSummonerId() {
        return summonerId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (positions == null ? 0 : positions.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        return result;
    }

    @Override
    public int indexOf(final Object o) {
        return positions.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return positions.isEmpty();
    }

    @Override
    public Iterator<LeaguePosition> iterator() {
        return positions.iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        return positions.lastIndexOf(o);
    }

    @Override
    public ListIterator<LeaguePosition> listIterator() {
        return positions.listIterator();
    }

    @Override
    public ListIterator<LeaguePosition> listIterator(final int index) {
        return positions.listIterator(index);
    }

    @Override
    public LeaguePosition remove(final int index) {
        return positions.remove(index);
    }

    @Override
    public boolean remove(final Object o) {
        return positions.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return positions.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return positions.retainAll(c);
    }

    @Override
    public LeaguePosition set(final int index, final LeaguePosition element) {
        return positions.set(index, element);
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

    /**
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final long summonerId) {
        this.summonerId = summonerId;
    }

    @Override
    public int size() {
        return positions.size();
    }

    @Override
    public List<LeaguePosition> subList(final int fromIndex, final int toIndex) {
        return positions.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return positions.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return positions.toArray(a);
    }
}
