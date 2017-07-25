package com.merakianalytics.orianna.type.dto.summoner;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Summoner extends DataObject {
    private static final long serialVersionUID = 8693230711620500210L;
    private String name, platform;
    private int profileIconId;
    private long summonerLevel, revisionDate, id, accountId;

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
        final Summoner other = (Summoner)obj;
        if(accountId != other.accountId) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(profileIconId != other.profileIconId) {
            return false;
        }
        if(revisionDate != other.revisionDate) {
            return false;
        }
        if(summonerLevel != other.summonerLevel) {
            return false;
        }
        return true;
    }

    /**
     * @return the accountId
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
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
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the profileIconId
     */
    public int getProfileIconId() {
        return profileIconId;
    }

    /**
     * @return the revisionDate
     */
    public long getRevisionDate() {
        return revisionDate;
    }

    /**
     * @return the summonerLevel
     */
    public long getSummonerLevel() {
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
        result = prime * result + (int)(accountId ^ accountId >>> 32);
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + profileIconId;
        result = prime * result + (int)(revisionDate ^ revisionDate >>> 32);
        result = prime * result + (int)(summonerLevel ^ summonerLevel >>> 32);
        return result;
    }

    /**
     * @param accountId
     *        the accountId to set
     */
    public void setAccountId(final long accountId) {
        this.accountId = accountId;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final long id) {
        this.id = id;
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
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param profileIconId
     *        the profileIconId to set
     */
    public void setProfileIconId(final int profileIconId) {
        this.profileIconId = profileIconId;
    }

    /**
     * @param revisionDate
     *        the revisionDate to set
     */
    public void setRevisionDate(final long revisionDate) {
        this.revisionDate = revisionDate;
    }

    /**
     * @param summonerLevel
     *        the summonerLevel to set
     */
    public void setSummonerLevel(final long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }
}
