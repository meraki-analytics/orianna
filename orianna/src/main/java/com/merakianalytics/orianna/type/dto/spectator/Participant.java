package com.merakianalytics.orianna.type.dto.spectator;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Participant extends DataObject {
    private static final long serialVersionUID = 4398902702506004752L;
    private boolean bot;
    private long profileIconId, championId, spell2Id, teamId, spell1Id;
    private String summonerName;

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
        final Participant other = (Participant)obj;
        if(bot != other.bot) {
            return false;
        }
        if(championId != other.championId) {
            return false;
        }
        if(profileIconId != other.profileIconId) {
            return false;
        }
        if(spell1Id != other.spell1Id) {
            return false;
        }
        if(spell2Id != other.spell2Id) {
            return false;
        }
        if(summonerName == null) {
            if(other.summonerName != null) {
                return false;
            }
        } else if(!summonerName.equals(other.summonerName)) {
            return false;
        }
        if(teamId != other.teamId) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public long getChampionId() {
        return championId;
    }

    /**
     * @return the profileIconId
     */
    public long getProfileIconId() {
        return profileIconId;
    }

    /**
     * @return the spell1Id
     */
    public long getSpell1Id() {
        return spell1Id;
    }

    /**
     * @return the spell2Id
     */
    public long getSpell2Id() {
        return spell2Id;
    }

    /**
     * @return the summonerName
     */
    public String getSummonerName() {
        return summonerName;
    }

    /**
     * @return the teamId
     */
    public long getTeamId() {
        return teamId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bot ? 1231 : 1237);
        result = prime * result + (int)(championId ^ championId >>> 32);
        result = prime * result + (int)(profileIconId ^ profileIconId >>> 32);
        result = prime * result + (int)(spell1Id ^ spell1Id >>> 32);
        result = prime * result + (int)(spell2Id ^ spell2Id >>> 32);
        result = prime * result + (summonerName == null ? 0 : summonerName.hashCode());
        result = prime * result + (int)(teamId ^ teamId >>> 32);
        return result;
    }

    /**
     * @return the bot
     */
    public boolean isBot() {
        return bot;
    }

    /**
     * @param bot
     *        the bot to set
     */
    public void setBot(final boolean bot) {
        this.bot = bot;
    }

    /**
     * @param championId
     *        the championId to set
     */
    public void setChampionId(final long championId) {
        this.championId = championId;
    }

    /**
     * @param profileIconId
     *        the profileIconId to set
     */
    public void setProfileIconId(final long profileIconId) {
        this.profileIconId = profileIconId;
    }

    /**
     * @param spell1Id
     *        the spell1Id to set
     */
    public void setSpell1Id(final long spell1Id) {
        this.spell1Id = spell1Id;
    }

    /**
     * @param spell2Id
     *        the spell2Id to set
     */
    public void setSpell2Id(final long spell2Id) {
        this.spell2Id = spell2Id;
    }

    /**
     * @param summonerName
     *        the summonerName to set
     */
    public void setSummonerName(final String summonerName) {
        this.summonerName = summonerName;
    }

    /**
     * @param teamId
     *        the teamId to set
     */
    public void setTeamId(final long teamId) {
        this.teamId = teamId;
    }
}
