package com.robrua.orianna.type.dto.currentgame;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity(name = "currentgame.Participant")
@Table(name = "currentgameparticipant")
public class Participant extends OriannaDto {
    private static final long serialVersionUID = -2053302121405517115L;
    private Boolean bot;
    private Long championId, profileIconId, spell1Id, spell2Id, summonerId, teamId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Mastery> masteries;

    @OneToMany(cascade = CascadeType.ALL)
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
        if(!(obj instanceof Participant)) {
            return false;
        }
        final Participant other = (Participant)obj;
        if(bot == null) {
            if(other.bot != null) {
                return false;
            }
        }
        else if(!bot.equals(other.bot)) {
            return false;
        }
        if(championId == null) {
            if(other.championId != null) {
                return false;
            }
        }
        else if(!championId.equals(other.championId)) {
            return false;
        }
        if(masteries == null) {
            if(other.masteries != null) {
                return false;
            }
        }
        else if(!masteries.equals(other.masteries)) {
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
        if(runes == null) {
            if(other.runes != null) {
                return false;
            }
        }
        else if(!runes.equals(other.runes)) {
            return false;
        }
        if(spell1Id == null) {
            if(other.spell1Id != null) {
                return false;
            }
        }
        else if(!spell1Id.equals(other.spell1Id)) {
            return false;
        }
        if(spell2Id == null) {
            if(other.spell2Id != null) {
                return false;
            }
        }
        else if(!spell2Id.equals(other.spell2Id)) {
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
        if(teamId == null) {
            if(other.teamId != null) {
                return false;
            }
        }
        else if(!teamId.equals(other.teamId)) {
            return false;
        }
        return true;
    }

    /**
     * @return the bot
     */
    public Boolean getBot() {
        return bot;
    }

    /**
     * @return the championId
     */
    public Long getChampionId() {
        return championId;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
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
    public Long getProfileIconId() {
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
    public Long getSpell1Id() {
        return spell1Id;
    }

    /**
     * @return the spell2Id
     */
    public Long getSpell2Id() {
        return spell2Id;
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

    /**
     * @return the teamId
     */
    public Long getTeamId() {
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
        result = prime * result + (bot == null ? 0 : bot.hashCode());
        result = prime * result + (championId == null ? 0 : championId.hashCode());
        result = prime * result + (masteries == null ? 0 : masteries.hashCode());
        result = prime * result + (profileIconId == null ? 0 : profileIconId.hashCode());
        result = prime * result + (runes == null ? 0 : runes.hashCode());
        result = prime * result + (spell1Id == null ? 0 : spell1Id.hashCode());
        result = prime * result + (spell2Id == null ? 0 : spell2Id.hashCode());
        result = prime * result + (summonerId == null ? 0 : summonerId.hashCode());
        result = prime * result + (summonerName == null ? 0 : summonerName.hashCode());
        result = prime * result + (teamId == null ? 0 : teamId.hashCode());
        return result;
    }

    /**
     * @param bot
     *            the bot to set
     */
    public void setBot(final Boolean bot) {
        this.bot = bot;
    }

    /**
     * @param championId
     *            the championId to set
     */
    public void setChampionId(final Long championId) {
        this.championId = championId;
    }

    /**
     * @param masteries
     *            the masteries to set
     */
    public void setMasteries(final List<Mastery> masteries) {
        this.masteries = masteries;
    }

    /**
     * @param profileIconId
     *            the profileIconId to set
     */
    public void setProfileIconId(final Long profileIconId) {
        this.profileIconId = profileIconId;
    }

    /**
     * @param runes
     *            the runes to set
     */
    public void setRunes(final List<Rune> runes) {
        this.runes = runes;
    }

    /**
     * @param spell1Id
     *            the spell1Id to set
     */
    public void setSpell1Id(final Long spell1Id) {
        this.spell1Id = spell1Id;
    }

    /**
     * @param spell2Id
     *            the spell2Id to set
     */
    public void setSpell2Id(final Long spell2Id) {
        this.spell2Id = spell2Id;
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

    /**
     * @param teamId
     *            the teamId to set
     */
    public void setTeamId(final Long teamId) {
        this.teamId = teamId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Participant [bot=" + bot + ", championId=" + championId + ", profileIconId=" + profileIconId + ", spell1Id=" + spell1Id + ", spell2Id="
                + spell2Id + ", summonerId=" + summonerId + ", teamId=" + teamId + ", masteries=" + masteries + ", runes=" + runes + ", summonerName="
                + summonerName + "]";
    }
}
