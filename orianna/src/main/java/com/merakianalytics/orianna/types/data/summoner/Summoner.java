package com.merakianalytics.orianna.types.data.summoner;

import org.joda.time.DateTime;

import com.merakianalytics.orianna.types.data.CoreData;

public class Summoner extends CoreData {
    private static final long serialVersionUID = -5216558332373601872L;
    private long id, accountId;
    private String name, platform;
    private int profileIconId = -1;
    private int level;
    private DateTime updated;

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
        if(level != other.level) {
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
        if(updated == null) {
            if(other.updated != null) {
                return false;
            }
        } else if(!updated.equals(other.updated)) {
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
     * @return the level
     */
    public int getLevel() {
        return level;
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
     * @return the updated
     */
    public DateTime getUpdated() {
        return updated;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(accountId ^ accountId >>> 32);
        result = prime * result + (int)(id ^ id >>> 32);
        result = prime * result + level;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + profileIconId;
        result = prime * result + (updated == null ? 0 : updated.hashCode());
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
     * @param level
     *        the level to set
     */
    public void setLevel(final int level) {
        this.level = level;
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
     * @param updated
     *        the updated to set
     */
    public void setUpdated(final DateTime updated) {
        this.updated = updated;
    }
}
