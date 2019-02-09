package com.merakianalytics.orianna.types.dto.summoner;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Summoner extends DataObject {
    private static final long serialVersionUID = 2765678846662981362L;
    private String name, platform, puuid, accountId, id;
    private int profileIconId = -1;
    private long summonerLevel, revisionDate;

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
        if(accountId == null) {
            if(other.accountId != null) {
                return false;
            }
        } else if(!accountId.equals(other.accountId)) {
            return false;
        }
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        } else if(!id.equals(other.id)) {
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
        if(puuid == null) {
            if(other.puuid != null) {
                return false;
            }
        } else if(!puuid.equals(other.puuid)) {
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
    public String getAccountId() {
        return accountId;
    }

    /**
     * @return the id
     */
    public String getId() {
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
     * @return the puuid
     */
    public String getPuuid() {
        return puuid;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (accountId == null ? 0 : accountId.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + profileIconId;
        result = prime * result + (puuid == null ? 0 : puuid.hashCode());
        result = prime * result + (int)(revisionDate ^ revisionDate >>> 32);
        result = prime * result + (int)(summonerLevel ^ summonerLevel >>> 32);
        return result;
    }

    /**
     * @param accountId
     *        the accountId to set
     */
    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final String id) {
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
     * @param puuid
     *        the puuid to set
     */
    public void setPuuid(final String puuid) {
        this.puuid = puuid;
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
