package com.merakianalytics.orianna.types.dto.spectator;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class CurrentGameParticipant extends DataObject {
    private static final long serialVersionUID = -3010619107564679228L;
    private boolean bot;
    private List<GameCustomizationObject> gameCustomizationObjects;
    private Perks perks;
    private long profileIconId, championId, teamId, spell2Id, spell1Id, summonerId;
    private String summonerName;

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
        if(gameCustomizationObjects == null) {
            if(other.gameCustomizationObjects != null) {
                return false;
            }
        } else if(!gameCustomizationObjects.equals(other.gameCustomizationObjects)) {
            return false;
        }
        if(perks == null) {
            if(other.perks != null) {
                return false;
            }
        } else if(!perks.equals(other.perks)) {
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
     * @return the gameCustomizationObjects
     */
    public List<GameCustomizationObject> getGameCustomizationObjects() {
        return gameCustomizationObjects;
    }

    /**
     * @return the perks
     */
    public Perks getPerks() {
        return perks;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bot ? 1231 : 1237);
        result = prime * result + (int)(championId ^ championId >>> 32);
        result = prime * result + (gameCustomizationObjects == null ? 0 : gameCustomizationObjects.hashCode());
        result = prime * result + (perks == null ? 0 : perks.hashCode());
        result = prime * result + (int)(profileIconId ^ profileIconId >>> 32);
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
     * @param gameCustomizationObjects
     *        the gameCustomizationObjects to set
     */
    public void setGameCustomizationObjects(final List<GameCustomizationObject> gameCustomizationObjects) {
        this.gameCustomizationObjects = gameCustomizationObjects;
    }

    /**
     * @param perks
     *        the perks to set
     */
    public void setPerks(final Perks perks) {
        this.perks = perks;
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
