package com.merakianalytics.orianna.types.data.league;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.data.CoreData;

public class League extends CoreData implements List<LeagueEntry> {
    private static final long serialVersionUID = 6679525366413760473L;
    private final List<LeagueEntry> entries = new ArrayList<>();
    private String name;
    private Platform platform;
    private Queue queue;
    private long summonerId;
    private Tier tier;

    @Override
    public void add(final int index, final LeagueEntry element) {
        entries.add(index, element);
    }

    @Override
    public boolean add(final LeagueEntry e) {
        return entries.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends LeagueEntry> c) {
        return entries.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends LeagueEntry> c) {
        return entries.addAll(index, c);
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return entries.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return entries.containsAll(c);
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
        final League other = (League)obj;
        if(entries == null) {
            if(other.entries != null) {
                return false;
            }
        } else if(!entries.equals(other.entries)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(platform != other.platform) {
            return false;
        }
        if(queue != other.queue) {
            return false;
        }
        if(summonerId != other.summonerId) {
            return false;
        }
        if(tier != other.tier) {
            return false;
        }
        return true;
    }

    @Override
    public LeagueEntry get(final int index) {
        return entries.get(index);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the queue
     */
    public Queue getQueue() {
        return queue;
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

    /**
     * @return the tier
     */
    public Tier getTier() {
        return tier;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (entries == null ? 0 : entries.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
        result = prime * result + (tier == null ? 0 : tier.hashCode());
        return result;
    }

    @Override
    public int indexOf(final Object o) {
        return entries.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public Iterator<LeagueEntry> iterator() {
        return entries.iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        return entries.lastIndexOf(o);
    }

    @Override
    public ListIterator<LeagueEntry> listIterator() {
        return entries.listIterator();
    }

    @Override
    public ListIterator<LeagueEntry> listIterator(final int index) {
        return entries.listIterator(index);
    }

    @Override
    public LeagueEntry remove(final int index) {
        return entries.remove(index);
    }

    @Override
    public boolean remove(final Object o) {
        return entries.remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return entries.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return entries.retainAll(c);
    }

    @Override
    public LeagueEntry set(final int index, final LeagueEntry element) {
        return entries.set(index, element);
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param queue
     *        the queue to set
     */
    public void setQueue(final Queue queue) {
        this.queue = queue;
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

    /**
     * @param tier
     *        the tier to set
     */
    public void setTier(final Tier tier) {
        this.tier = tier;
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public List<LeagueEntry> subList(final int fromIndex, final int toIndex) {
        return entries.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return entries.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return entries.toArray(a);
    }
}
