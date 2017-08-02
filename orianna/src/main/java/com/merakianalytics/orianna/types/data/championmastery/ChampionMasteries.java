package com.merakianalytics.orianna.types.data.championmastery;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.data.CoreData;

public class ChampionMasteries extends CoreData implements List<ChampionMastery> {
    private static final long serialVersionUID = 3532345605312788979L;
    private List<ChampionMastery> masteries;
    private Platform platform;
    private long summonerId;

    @Override
    public boolean add(final ChampionMastery e) {
        return masteries.add(e);
    }

    @Override
    public void add(final int index, final ChampionMastery element) {
        masteries.add(index, element);
    }

    @Override
    public boolean addAll(final Collection<? extends ChampionMastery> c) {
        return masteries.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends ChampionMastery> c) {
        return masteries.addAll(index, c);
    }

    @Override
    public void clear() {
        masteries.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return masteries.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return masteries.containsAll(c);
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
        final ChampionMasteries other = (ChampionMasteries)obj;
        if(masteries == null) {
            if(other.masteries != null) {
                return false;
            }
        } else if(!masteries.equals(other.masteries)) {
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
    public ChampionMastery get(final int index) {
        return masteries.get(index);
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
        result = prime * result + (masteries == null ? 0 : masteries.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        return result;
    }

    @Override
    public int indexOf(final Object o) {
        return masteries.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return masteries.isEmpty();
    }

    @Override
    public Iterator<ChampionMastery> iterator() {
        return masteries.iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        return masteries.lastIndexOf(o);
    }

    @Override
    public ListIterator<ChampionMastery> listIterator() {
        return masteries.listIterator();
    }

    @Override
    public ListIterator<ChampionMastery> listIterator(final int index) {
        return masteries.listIterator(index);
    }

    @Override
    public ChampionMastery remove(final int index) {
        return masteries.remove(index);
    }

    @Override
    public boolean remove(final Object o) {
        return masteries.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return masteries.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return masteries.retainAll(c);
    }

    @Override
    public ChampionMastery set(final int index, final ChampionMastery element) {
        return masteries.set(index, element);
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
        return masteries.size();
    }

    @Override
    public List<ChampionMastery> subList(final int fromIndex, final int toIndex) {
        return masteries.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return masteries.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return masteries.toArray(a);
    }
}
