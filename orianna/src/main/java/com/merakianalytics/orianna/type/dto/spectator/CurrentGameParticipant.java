package com.merakianalytics.orianna.type.dto.spectator;

import java.util.List;

import com.merakianalytics.orianna.type.dto.DataObject;

public class CurrentGameParticipant extends DataObject {
    private static final long serialVersionUID = -1450269492846583071L;
    private boolean bot;
    private List<Mastery> masteries;
    private long profileIconId, championId, teamId, spell2Id, spell1Id, summonerId;
    private List<Rune> runes;
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
        final CurrentGameParticipant other = (CurrentGameParticipant)obj;
        if(bot != other.bot) {
            return false;
        }
        if(championId != other.championId) {
            return false;
        }
        if(masteries == null) {
            if(other.masteries != null) {
                return false;
            }
        } else if(!masteries.equals(other.masteries)) {
            return false;
        }
        if(profileIconId != other.profileIconId) {
            return false;
        }
        if(runes == null) {
            if(other.runes != null) {
                return false;
            }
        } else if(!runes.equals(other.runes)) {
            return false;
        }
        if(spell1Id != other.spell1Id) {
            return false;
        }
        if(spell2Id != other.spell2Id) {
            return false;
        }
        if(summonerId != other.summonerId) {
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
     * @return the masteries
     */
    public List<Mastery> getMasteries() {
        return masteries;
    }

    /**
     * @return the profileIconId
     */
    public long getProfileIconId() {
        return profileIconId;
    }

    /**
     * @return the runes
     */
    public List<Rune> getRunes() {
        return runes;
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
     * @return the summonerId
     */
    public long getSummonerId() {
        return summonerId;
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
        result = prime * result + (masteries == null ? 0 : masteries.hashCode());
        result = prime * result + (int)(profileIconId ^ profileIconId >>> 32);
        result = prime * result + (runes == null ? 0 : runes.hashCode());
        result = prime * result + (int)(spell1Id ^ spell1Id >>> 32);
        result = prime * result + (int)(spell2Id ^ spell2Id >>> 32);
        result = prime * result + (int)(summonerId ^ summonerId >>> 32);
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
     * @param masteries
     *        the masteries to set
     */
    public void setMasteries(final List<Mastery> masteries) {
        this.masteries = masteries;
    }

    /**
     * @param profileIconId
     *        the profileIconId to set
     */
    public void setProfileIconId(final long profileIconId) {
        this.profileIconId = profileIconId;
    }

    /**
     * @param runes
     *        the runes to set
     */
    public void setRunes(final List<Rune> runes) {
        this.runes = runes;
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
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final long summonerId) {
        this.summonerId = summonerId;
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
