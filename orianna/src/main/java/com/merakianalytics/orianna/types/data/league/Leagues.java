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

public class Leagues extends CoreData implements List<League> {
    private static final long serialVersionUID = -7434950182653725644L;
    private final List<League> leagues = new ArrayList<>();
    private Platform platform;
    private long summonerId;

    @Override
    public void add(final int index, final League element) {
        leagues.add(index, element);
    }

    @Override
    public boolean add(final League e) {
        return leagues.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends League> c) {
        return leagues.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends League> c) {
        return leagues.addAll(index, c);
    }

    @Override
    public void clear() {
        leagues.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return leagues.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return leagues.containsAll(c);
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
        final Leagues other = (Leagues)obj;
        if(leagues == null) {
            if(other.leagues != null) {
                return false;
            }
        } else if(!leagues.equals(other.leagues)) {
            return false;
        }
        if(platform != other.platform) {
            return false;
        }
        if(summonerId != other.summonerId) {
            return false;
        }
        return true;
    }

    @Override
    public League get(final int index) {
        return leagues.get(index);
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
        result = prime * result + (leagues == null ? 0 : leagues.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        return result;
    }

    @Override
    public int indexOf(final Object o) {
        return leagues.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return leagues.isEmpty();
    }

    @Override
    public Iterator<League> iterator() {
        return leagues.iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        return leagues.lastIndexOf(o);
    }

    @Override
    public ListIterator<League> listIterator() {
        return leagues.listIterator();
    }

    @Override
    public ListIterator<League> listIterator(final int index) {
        return leagues.listIterator(index);
    }

    @Override
    public League remove(final int index) {
        return leagues.remove(index);
    }

    @Override
    public boolean remove(final Object o) {
        return leagues.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return leagues.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return leagues.retainAll(c);
    }

    @Override
    public League set(final int index, final League element) {
        return leagues.set(index, element);
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
        return leagues.size();
    }

    @Override
    public List<League> subList(final int fromIndex, final int toIndex) {
        return leagues.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return leagues.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return leagues.toArray(a);
    }
}
