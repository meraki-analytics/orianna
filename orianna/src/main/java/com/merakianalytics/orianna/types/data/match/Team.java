package com.merakianalytics.orianna.types.data.match;

import java.util.List;

import com.merakianalytics.orianna.types.data.CoreData;

public class Team extends CoreData {
    private static final long serialVersionUID = 4843385559866396877L;
    private List<Integer> bans;
    private int baronKills, riftHeraldKills, vilemawKills, inhibitorKills, towerKills, dominionScore, dragonKills, teamId;
    private boolean firstDragonKiller, firstInhibitorKiller, firstRiftHeraldKiller, firstBaronKiller, firstBloodKiller, firstTowerKiller, winner;
    private String platform, version;

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
        final Team other = (Team)obj;
        if(bans == null) {
            if(other.bans != null) {
                return false;
            }
        } else if(!bans.equals(other.bans)) {
            return false;
        }
        if(baronKills != other.baronKills) {
            return false;
        }
        if(dominionScore != other.dominionScore) {
            return false;
        }
        if(dragonKills != other.dragonKills) {
            return false;
        }
        if(firstBaronKiller != other.firstBaronKiller) {
            return false;
        }
        if(firstBloodKiller != other.firstBloodKiller) {
            return false;
        }
        if(firstDragonKiller != other.firstDragonKiller) {
            return false;
        }
        if(firstInhibitorKiller != other.firstInhibitorKiller) {
            return false;
        }
        if(firstRiftHeraldKiller != other.firstRiftHeraldKiller) {
            return false;
        }
        if(firstTowerKiller != other.firstTowerKiller) {
            return false;
        }
        if(inhibitorKills != other.inhibitorKills) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(riftHeraldKills != other.riftHeraldKills) {
            return false;
        }
        if(teamId != other.teamId) {
            return false;
        }
        if(towerKills != other.towerKills) {
            return false;
        }
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        } else if(!version.equals(other.version)) {
            return false;
        }
        if(vilemawKills != other.vilemawKills) {
            return false;
        }
        if(winner != other.winner) {
            return false;
        }
        return true;
    }

    /**
     * @return the bans
     */
    public List<Integer> getBans() {
        return bans;
    }

    /**
     * @return the baronKills
     */
    public int getBaronKills() {
        return baronKills;
    }

    /**
     * @return the dominionScore
     */
    public int getDominionScore() {
        return dominionScore;
    }

    /**
     * @return the dragonKills
     */
    public int getDragonKills() {
        return dragonKills;
    }

    /**
     * @return the inhibitorKills
     */
    public int getInhibitorKills() {
        return inhibitorKills;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the riftHeraldKills
     */
    public int getRiftHeraldKills() {
        return riftHeraldKills;
    }

    /**
     * @return the teamId
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * @return the towerKills
     */
    public int getTowerKills() {
        return towerKills;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the vilemawKills
     */
    public int getVilemawKills() {
        return vilemawKills;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bans == null ? 0 : bans.hashCode());
        result = prime * result + baronKills;
        result = prime * result + dominionScore;
        result = prime * result + dragonKills;
        result = prime * result + (firstBaronKiller ? 1231 : 1237);
        result = prime * result + (firstBloodKiller ? 1231 : 1237);
        result = prime * result + (firstDragonKiller ? 1231 : 1237);
        result = prime * result + (firstInhibitorKiller ? 1231 : 1237);
        result = prime * result + (firstRiftHeraldKiller ? 1231 : 1237);
        result = prime * result + (firstTowerKiller ? 1231 : 1237);
        result = prime * result + inhibitorKills;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + riftHeraldKills;
        result = prime * result + teamId;
        result = prime * result + towerKills;
        result = prime * result + (version == null ? 0 : version.hashCode());
        result = prime * result + vilemawKills;
        result = prime * result + (winner ? 1231 : 1237);
        return result;
    }

    /**
     * @return the firstBaronKiller
     */
    public boolean isFirstBaronKiller() {
        return firstBaronKiller;
    }

    /**
     * @return the firstBloodKiller
     */
    public boolean isFirstBloodKiller() {
        return firstBloodKiller;
    }

    /**
     * @return the firstDragonKiller
     */
    public boolean isFirstDragonKiller() {
        return firstDragonKiller;
    }

    /**
     * @return the firstInhibitorKiller
     */
    public boolean isFirstInhibitorKiller() {
        return firstInhibitorKiller;
    }

    /**
     * @return the firstRiftHeraldKiller
     */
    public boolean isFirstRiftHeraldKiller() {
        return firstRiftHeraldKiller;
    }

    /**
     * @return the firstTowerKiller
     */
    public boolean isFirstTowerKiller() {
        return firstTowerKiller;
    }

    /**
     * @return the winner
     */
    public boolean isWinner() {
        return winner;
    }

    /**
     * @param bans
     *        the bans to set
     */
    public void setBans(final List<Integer> bans) {
        this.bans = bans;
    }

    /**
     * @param baronKills
     *        the baronKills to set
     */
    public void setBaronKills(final int baronKills) {
        this.baronKills = baronKills;
    }

    /**
     * @param dominionScore
     *        the dominionScore to set
     */
    public void setDominionScore(final int dominionScore) {
        this.dominionScore = dominionScore;
    }

    /**
     * @param dragonKills
     *        the dragonKills to set
     */
    public void setDragonKills(final int dragonKills) {
        this.dragonKills = dragonKills;
    }

    /**
     * @param firstBaronKiller
     *        the firstBaronKiller to set
     */
    public void setFirstBaronKiller(final boolean firstBaronKiller) {
        this.firstBaronKiller = firstBaronKiller;
    }

    /**
     * @param firstBloodKiller
     *        the firstBloodKiller to set
     */
    public void setFirstBloodKiller(final boolean firstBloodKiller) {
        this.firstBloodKiller = firstBloodKiller;
    }

    /**
     * @param firstDragonKiller
     *        the firstDragonKiller to set
     */
    public void setFirstDragonKiller(final boolean firstDragonKiller) {
        this.firstDragonKiller = firstDragonKiller;
    }

    /**
     * @param firstInhibitorKiller
     *        the firstInhibitorKiller to set
     */
    public void setFirstInhibitorKiller(final boolean firstInhibitorKiller) {
        this.firstInhibitorKiller = firstInhibitorKiller;
    }

    /**
     * @param firstRiftHeraldKiller
     *        the firstRiftHeraldKiller to set
     */
    public void setFirstRiftHeraldKiller(final boolean firstRiftHeraldKiller) {
        this.firstRiftHeraldKiller = firstRiftHeraldKiller;
    }

    /**
     * @param firstTowerKiller
     *        the firstTowerKiller to set
     */
    public void setFirstTowerKiller(final boolean firstTowerKiller) {
        this.firstTowerKiller = firstTowerKiller;
    }

    /**
     * @param inhibitorKills
     *        the inhibitorKills to set
     */
    public void setInhibitorKills(final int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param riftHeraldKills
     *        the riftHeraldKills to set
     */
    public void setRiftHeraldKills(final int riftHeraldKills) {
        this.riftHeraldKills = riftHeraldKills;
    }

    /**
     * @param teamId
     *        the teamId to set
     */
    public void setTeamId(final int teamId) {
        this.teamId = teamId;
    }

    /**
     * @param towerKills
     *        the towerKills to set
     */
    public void setTowerKills(final int towerKills) {
        this.towerKills = towerKills;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * @param vilemawKills
     *        the vilemawKills to set
     */
    public void setVilemawKills(final int vilemawKills) {
        this.vilemawKills = vilemawKills;
    }

    /**
     * @param winner
     *        the winner to set
     */
    public void setWinner(final boolean winner) {
        this.winner = winner;
    }
}
