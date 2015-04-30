package com.robrua.orianna.type.dto.summoner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "masterypages")
public class MasteryPages extends OriannaDto {
    private static final long serialVersionUID = -7964030783531757808L;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MasteryPage> pages;

    @Id
    private Long summonerId;

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
        if(!(obj instanceof MasteryPages)) {
            return false;
        }
        final MasteryPages other = (MasteryPages)obj;
        if(pages == null) {
            if(other.pages != null) {
                return false;
            }
        }
        else if(!pages.equals(other.pages)) {
            return false;
        }
        if(summonerId == null) {
            if(other.summonerId != null) {
                return false;
            }
        }
        else if(!summonerId.equals(other.summonerId)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "summonerId";
        }
        return null;
    }

    /**
     * Gets all stored mastery IDs for batch lookup
     *
     * @return the mastery IDs
     */
    public Set<Long> getMasteryIDs() {
        final Set<Long> set = new HashSet<>();
        for(final MasteryPage page : pages) {
            if(page.getMasteries() != null) {
                for(final Mastery mastery : page.getMasteries()) {
                    set.add(mastery.getId().longValue());
                }
            }
        }

        return set;
    }

    /**
     * @return the pages
     */
    public List<MasteryPage> getPages() {
        return pages;
    }

    /**
     * @return the summonerId
     */
    public Long getSummonerId() {
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
        result = prime * result + (pages == null ? 0 : pages.hashCode());
        result = prime * result + (summonerId == null ? 0 : summonerId.hashCode());
        return result;
    }

    /**
     * @param pages
     *            the pages to set
     */
    public void setPages(final List<MasteryPage> pages) {
        this.pages = pages;
    }

    /**
     * @param summonerId
     *            the summonerId to set
     */
    public void setSummonerId(final Long summonerId) {
        this.summonerId = summonerId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MasteryPages [pages=" + pages + ", summonerId=" + summonerId + "]";
    }
}
