package com.robrua.orianna.type.dto.matchlist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "matchlist")
public class MatchList extends OriannaDto {
    private static final long serialVersionUID = -4291081119304498770L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Integer endIndex, startIndex, totalGames;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MatchReference> matches;

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
        if(!(obj instanceof MatchList)) {
            return false;
        }
        final MatchList other = (MatchList)obj;
        if(endIndex == null) {
            if(other.endIndex != null) {
                return false;
            }
        }
        else if(!endIndex.equals(other.endIndex)) {
            return false;
        }
        if(matches == null) {
            if(other.matches != null) {
                return false;
            }
        }
        else if(!matches.equals(other.matches)) {
            return false;
        }
        if(startIndex == null) {
            if(other.startIndex != null) {
                return false;
            }
        }
        else if(!startIndex.equals(other.startIndex)) {
            return false;
        }
        if(totalGames == null) {
            if(other.totalGames != null) {
                return false;
            }
        }
        else if(!totalGames.equals(other.totalGames)) {
            return false;
        }
        return true;
    }

    /**
     * Gets all stored champion IDs for batch lookup
     *
     * @return the champion IDs
     */
    public Set<Long> getChampionIDs() {
        final Set<Long> set = new HashSet<>();
        for(final MatchReference match : matches) {
            set.add(match.getChampion());
        }

        return set;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the endIndex
     */
    public Integer getEndIndex() {
        return endIndex;
    }

    /**
     * @return the matches
     */
    public List<MatchReference> getMatches() {
        return matches;
    }

    /**
     * @return the startIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * @return the totalGames
     */
    public Integer getTotalGames() {
        return totalGames;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (endIndex == null ? 0 : endIndex.hashCode());
        result = prime * result + (matches == null ? 0 : matches.hashCode());
        result = prime * result + (startIndex == null ? 0 : startIndex.hashCode());
        result = prime * result + (totalGames == null ? 0 : totalGames.hashCode());
        return result;
    }

    /**
     * @param endIndex
     *            the endIndex to set
     */
    public void setEndIndex(final Integer endIndex) {
        this.endIndex = endIndex;
    }

    /**
     * @param matches
     *            the matches to set
     */
    public void setMatches(final List<MatchReference> matches) {
        this.matches = matches;
    }

    /**
     * @param startIndex
     *            the startIndex to set
     */
    public void setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @param totalGames
     *            the totalGames to set
     */
    public void setTotalGames(final Integer totalGames) {
        this.totalGames = totalGames;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MatchList [matches=" + matches + ", endIndex=" + endIndex + ", startIndex=" + startIndex + ", totalGames=" + totalGames + "]";
    }

}
