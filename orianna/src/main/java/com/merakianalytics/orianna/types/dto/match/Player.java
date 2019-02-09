package com.merakianalytics.orianna.types.dto.match;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Player extends DataObject {
    private static final long serialVersionUID = -6098509258697874128L;
    private String currentPlatformId, summonerName, matchHistoryUri, platformId, currentAccountId, summonerId, accountId;
    private int profileIcon;

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
        final Player other = (Player)obj;
        if(accountId == null) {
            if(other.accountId != null) {
                return false;
            }
        } else if(!accountId.equals(other.accountId)) {
            return false;
        }
        if(currentAccountId == null) {
            if(other.currentAccountId != null) {
                return false;
            }
        } else if(!currentAccountId.equals(other.currentAccountId)) {
            return false;
        }
        if(currentPlatformId == null) {
            if(other.currentPlatformId != null) {
                return false;
            }
        } else if(!currentPlatformId.equals(other.currentPlatformId)) {
            return false;
        }
        if(matchHistoryUri == null) {
            if(other.matchHistoryUri != null) {
                return false;
            }
        } else if(!matchHistoryUri.equals(other.matchHistoryUri)) {
            return false;
        }
        if(platformId == null) {
            if(other.platformId != null) {
                return false;
            }
        } else if(!platformId.equals(other.platformId)) {
            return false;
        }
        if(profileIcon != other.profileIcon) {
            return false;
        }
        if(summonerId == null) {
            if(other.summonerId != null) {
                return false;
            }
        } else if(!summonerId.equals(other.summonerId)) {
            return false;
        }
        if(summonerName == null) {
            if(other.summonerName != null) {
                return false;
            }
        } else if(!summonerName.equals(other.summonerName)) {
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
     * @return the currentAccountId
     */
    public String getCurrentAccountId() {
        return currentAccountId;
    }

    /**
     * @return the currentPlatformId
     */
    public String getCurrentPlatformId() {
        return currentPlatformId;
    }

    /**
     * @return the matchHistoryUri
     */
    public String getMatchHistoryUri() {
        return matchHistoryUri;
    }

    /**
     * @return the platformId
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * @return the profileIcon
     */
    public int getProfileIcon() {
        return profileIcon;
    }

    /**
     * @return the summonerId
     */
    public String getSummonerId() {
        return summonerId;
    }

    /**
     * @return the summonerName
     */
    public String getSummonerName() {
        return summonerName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (accountId == null ? 0 : accountId.hashCode());
        result = prime * result + (currentAccountId == null ? 0 : currentAccountId.hashCode());
        result = prime * result + (currentPlatformId == null ? 0 : currentPlatformId.hashCode());
        result = prime * result + (matchHistoryUri == null ? 0 : matchHistoryUri.hashCode());
        result = prime * result + (platformId == null ? 0 : platformId.hashCode());
        result = prime * result + profileIcon;
        result = prime * result + (summonerId == null ? 0 : summonerId.hashCode());
        result = prime * result + (summonerName == null ? 0 : summonerName.hashCode());
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
     * @param currentAccountId
     *        the currentAccountId to set
     */
    public void setCurrentAccountId(final String currentAccountId) {
        this.currentAccountId = currentAccountId;
    }

    /**
     * @param currentPlatformId
     *        the currentPlatformId to set
     */
    public void setCurrentPlatformId(final String currentPlatformId) {
        this.currentPlatformId = currentPlatformId;
    }

    /**
     * @param matchHistoryUri
     *        the matchHistoryUri to set
     */
    public void setMatchHistoryUri(final String matchHistoryUri) {
        this.matchHistoryUri = matchHistoryUri;
    }

    /**
     * @param platformId
     *        the platformId to set
     */
    public void setPlatformId(final String platformId) {
        this.platformId = platformId;
    }

    /**
     * @param profileIcon
     *        the profileIcon to set
     */
    public void setProfileIcon(final int profileIcon) {
        this.profileIcon = profileIcon;
    }

    /**
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final String summonerId) {
        this.summonerId = summonerId;
    }

    /**
     * @param summonerName
     *        the summonerName to set
     */
    public void setSummonerName(final String summonerName) {
        this.summonerName = summonerName;
    }
}
