package com.merakianalytics.orianna.types.data.spectator;

import com.merakianalytics.orianna.types.common.Team;
import com.merakianalytics.orianna.types.data.CoreData;

public class Participant extends CoreData {
    private static final long serialVersionUID = 923195762217384059L;
    private boolean bot;
    private int profileIconId, championId, summonerSpellDId, summonerSpellFId;
    private String summonerName;
    private Team team;

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
        if(summonerName == null) {
            if(other.summonerName != null) {
                return false;
            }
        } else if(!summonerName.equals(other.summonerName)) {
            return false;
        }
        if(summonerSpellDId != other.summonerSpellDId) {
            return false;
        }
        if(summonerSpellFId != other.summonerSpellFId) {
            return false;
        }
        if(team != other.team) {
            return false;
        }
        return true;
    }

    /**
     * @return the championId
     */
    public int getChampionId() {
        return championId;
    }

    /**
     * @return the profileIconId
     */
    public int getProfileIconId() {
        return profileIconId;
    }

    /**
     * @return the summonerName
     */
    public String getSummonerName() {
        return summonerName;
    }

    /**
     * @return the summonerSpellDId
     */
    public int getSummonerSpellDId() {
        return summonerSpellDId;
    }

    /**
     * @return the summonerSpellFId
     */
    public int getSummonerSpellFId() {
        return summonerSpellFId;
    }

    /**
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bot ? 1231 : 1237);
        result = prime * result + championId;
        result = prime * result + profileIconId;
        result = prime * result + (summonerName == null ? 0 : summonerName.hashCode());
        result = prime * result + summonerSpellDId;
        result = prime * result + summonerSpellFId;
        result = prime * result + (team == null ? 0 : team.hashCode());
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
    public void setChampionId(final int championId) {
        this.championId = championId;
    }

    /**
     * @param profileIconId
     *        the profileIconId to set
     */
    public void setProfileIconId(final int profileIconId) {
        this.profileIconId = profileIconId;
    }

    /**
     * @param summonerName
     *        the summonerName to set
     */
    public void setSummonerName(final String summonerName) {
        this.summonerName = summonerName;
    }

    /**
     * @param summonerSpellDId
     *        the summonerSpellDId to set
     */
    public void setSummonerSpellDId(final int summonerSpellDId) {
        this.summonerSpellDId = summonerSpellDId;
    }

    /**
     * @param summonerSpellFId
     *        the summonerSpellFId to set
     */
    public void setSummonerSpellFId(final int summonerSpellFId) {
        this.summonerSpellFId = summonerSpellFId;
    }

    /**
     * @param team
     *        the team to set
     */
    public void setTeam(final Team team) {
        this.team = team;
    }
}
