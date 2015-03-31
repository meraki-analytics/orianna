package com.robrua.orianna.type.dto.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity(name = "match.Player")
@Table(name = "matchplayer")
public class Player extends OriannaDto {
    private static final long serialVersionUID = -5513104603133796172L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;
    private String matchHistoryUri, summonerName;
    private Integer profileIcon;

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
        if(!(obj instanceof Player)) {
            return false;
        }
        final Player other = (Player)obj;
        if(matchHistoryUri == null) {
            if(other.matchHistoryUri != null) {
                return false;
            }
        }
        else if(!matchHistoryUri.equals(other.matchHistoryUri)) {
            return false;
        }
        if(profileIcon == null) {
            if(other.profileIcon != null) {
                return false;
            }
        }
        else if(!profileIcon.equals(other.profileIcon)) {
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
        if(summonerName == null) {
            if(other.summonerName != null) {
                return false;
            }
        }
        else if(!summonerName.equals(other.summonerName)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Integer.class) || keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the matchHistoryUri
     */
    public String getMatchHistoryUri() {
        return matchHistoryUri;
    }

    /**
     * @return the profileIcon
     */
    public Integer getProfileIcon() {
        return profileIcon;
    }

    /**
     * @return the summonerId
     */
    public Long getSummonerId() {
        return summonerId;
    }

    /**
     * @return the summonerName
     */
    public String getSummonerName() {
        return summonerName;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (matchHistoryUri == null ? 0 : matchHistoryUri.hashCode());
        result = prime * result + (profileIcon == null ? 0 : profileIcon.hashCode());
        result = prime * result + (summonerId == null ? 0 : summonerId.hashCode());
        result = prime * result + (summonerName == null ? 0 : summonerName.hashCode());
        return result;
    }

    /**
     * @param matchHistoryUri
     *            the matchHistoryUri to set
     */
    public void setMatchHistoryUri(final String matchHistoryUri) {
        this.matchHistoryUri = matchHistoryUri;
    }

    /**
     * @param profileIcon
     *            the profileIcon to set
     */
    public void setProfileIcon(final Integer profileIcon) {
        this.profileIcon = profileIcon;
    }

    /**
     * @param summonerId
     *            the summonerId to set
     */
    public void setSummonerId(final Long summonerId) {
        this.summonerId = summonerId;
    }

    /**
     * @param summonerName
     *            the summonerName to set
     */
    public void setSummonerName(final String summonerName) {
        this.summonerName = summonerName;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Player [matchHistoryUri=" + matchHistoryUri + ", summonerName=" + summonerName + ", profileIcon=" + profileIcon + ", summonerId=" + summonerId
                + "]";
    }
}
