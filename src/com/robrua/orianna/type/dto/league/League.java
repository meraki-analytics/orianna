package com.robrua.orianna.type.dto.league;

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
@Table(name = "league")
public class League extends OriannaDto {
    private static final long serialVersionUID = -742544678368330943L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LeagueEntry> entries;

    private String name, participantId, queue, tier;

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
        if(!(obj instanceof League)) {
            return false;
        }
        final League other = (League)obj;
        if(entries == null) {
            if(other.entries != null) {
                return false;
            }
        }
        else if(!entries.equals(other.entries)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        }
        else if(!name.equals(other.name)) {
            return false;
        }
        if(participantId == null) {
            if(other.participantId != null) {
                return false;
            }
        }
        else if(!participantId.equals(other.participantId)) {
            return false;
        }
        if(queue == null) {
            if(other.queue != null) {
                return false;
            }
        }
        else if(!queue.equals(other.queue)) {
            return false;
        }
        if(tier == null) {
            if(other.tier != null) {
                return false;
            }
        }
        else if(!tier.equals(other.tier)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the entries
     */
    public List<LeagueEntry> getEntries() {
        return entries;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the participantId
     */
    public String getParticipantId() {
        return participantId;
    }

    /**
     * @return the queue
     */
    public String getQueue() {
        return queue;
    }

    /**
     * Gets all stored summoner IDs for batch lookup
     *
     * @return the summoner IDs
     */
    public Set<Long> getSummonerIDs() {
        final Set<Long> set = new HashSet<>();

        long ID;
        if(participantId != null) {
            try {
                ID = Long.parseLong(participantId);
                set.add(ID);
            }
            catch(final NumberFormatException e) {
                // Don't add team ID to the set
            }
        }

        for(final LeagueEntry entry : entries) {
            if(entry.getPlayerOrTeamId() != null) {
                try {
                    ID = Long.parseLong(entry.getPlayerOrTeamId());
                    set.add(ID);
                }
                catch(final NumberFormatException e) {
                    // Don't add team ID to the set
                }
            }
        }

        return set;
    }

    /**
     * Gets all stored team IDs for batch lookup
     *
     * @return the team IDs
     */
    public Set<String> getTeamIDs() {
        final Set<String> set = new HashSet<>();

        if(participantId != null) {
            try {
                Long.parseLong(participantId);
                // Don't add summoner ID to the set
            }
            catch(final NumberFormatException e) {
                set.add(participantId);
            }
        }

        for(final LeagueEntry entry : entries) {
            if(entry.getPlayerOrTeamId() != null) {
                try {
                    Long.parseLong(entry.getPlayerOrTeamId());
                    // Don't add summoner ID to the set
                }
                catch(final NumberFormatException e) {
                    set.add(entry.getPlayerOrTeamId());
                }
            }
        }

        return set;
    }

    /**
     * @return the tier
     */
    public String getTier() {
        return tier;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (entries == null ? 0 : entries.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (participantId == null ? 0 : participantId.hashCode());
        result = prime * result + (queue == null ? 0 : queue.hashCode());
        result = prime * result + (tier == null ? 0 : tier.hashCode());
        return result;
    }

    /**
     * @param entries
     *            the entries to set
     */
    public void setEntries(final List<LeagueEntry> entries) {
        this.entries = entries;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param participantId
     *            the participantId to set
     */
    public void setParticipantId(final String participantId) {
        this.participantId = participantId;
    }

    /**
     * @param queue
     *            the queue to set
     */
    public void setQueue(final String queue) {
        this.queue = queue;
    }

    /**
     * @param tier
     *            the tier to set
     */
    public void setTier(final String tier) {
        this.tier = tier;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "League [entries=" + entries + ", name=" + name + ", participantId=" + participantId + ", queue=" + queue + ", tier=" + tier + "]";
    }
}
