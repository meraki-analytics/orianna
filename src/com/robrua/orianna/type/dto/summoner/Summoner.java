package com.robrua.orianna.type.dto.summoner;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "summoner")
public class Summoner extends OriannaDto {
    private static final long serialVersionUID = -2752289282845515894L;
    @Id
    private Long id;
    private String name;
    private Integer profileIconId;

    private Long revisionDate, summonerLevel;

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
        if(!(obj instanceof Summoner)) {
            return false;
        }
        final Summoner other = (Summoner)obj;
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        }
        else if(!id.equals(other.id)) {
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
        if(profileIconId == null) {
            if(other.profileIconId != null) {
                return false;
            }
        }
        else if(!profileIconId.equals(other.profileIconId)) {
            return false;
        }
        if(revisionDate == null) {
            if(other.revisionDate != null) {
                return false;
            }
        }
        else if(!revisionDate.equals(other.revisionDate)) {
            return false;
        }
        if(summonerLevel == null) {
            if(other.summonerLevel != null) {
                return false;
            }
        }
        else if(!summonerLevel.equals(other.summonerLevel)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "id";
        }
        if(keyType.equals(String.class)) {
            return "name";
        }
        return null;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the profileIconId
     */
    public Integer getProfileIconId() {
        return profileIconId;
    }

    /**
     * @return the revisionDate
     */
    public Long getRevisionDate() {
        return revisionDate;
    }

    /**
     * @return the summonerLevel
     */
    public Long getSummonerLevel() {
        return summonerLevel;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (profileIconId == null ? 0 : profileIconId.hashCode());
        result = prime * result + (revisionDate == null ? 0 : revisionDate.hashCode());
        result = prime * result + (summonerLevel == null ? 0 : summonerLevel.hashCode());
        return result;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param profileIconId
     *            the profileIconId to set
     */
    public void setProfileIconId(final Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    /**
     * @param revisionDate
     *            the revisionDate to set
     */
    public void setRevisionDate(final Long revisionDate) {
        this.revisionDate = revisionDate;
    }

    /**
     * @param summonerLevel
     *            the summonerLevel to set
     */
    public void setSummonerLevel(final Long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Summoner [id=" + id + ", revisionDate=" + revisionDate + ", summonerLevel=" + summonerLevel + ", name=" + name + ", profileIconId="
                + profileIconId + "]";
    }
}
