package com.merakianalytics.orianna.type.dto.match;

import java.util.List;

import com.merakianalytics.orianna.type.dto.DataObject;

public class TeamStats extends DataObject {
    private static final long serialVersionUID = 7571269845686606334L;
    private List<TeamBans> bans;
    private int baronKills, riftHeraldKills, teamId, vilemawKills, inhibitorKills, towerKills, dominionVictoryScore, dragonKills;
    private boolean firstDragon, firstInhibitor, firstRiftHerald, firstBaron, firstBlood, firstTower;
    private String win;

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
        final TeamStats other = (TeamStats)obj;
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
        if(dominionVictoryScore != other.dominionVictoryScore) {
            return false;
        }
        if(dragonKills != other.dragonKills) {
            return false;
        }
        if(firstBaron != other.firstBaron) {
            return false;
        }
        if(firstBlood != other.firstBlood) {
            return false;
        }
        if(firstDragon != other.firstDragon) {
            return false;
        }
        if(firstInhibitor != other.firstInhibitor) {
            return false;
        }
        if(firstRiftHerald != other.firstRiftHerald) {
            return false;
        }
        if(firstTower != other.firstTower) {
            return false;
        }
        if(inhibitorKills != other.inhibitorKills) {
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
        if(vilemawKills != other.vilemawKills) {
            return false;
        }
        if(win == null) {
            if(other.win != null) {
                return false;
            }
        } else if(!win.equals(other.win)) {
            return false;
        }
        return true;
    }

    /**
     * @return the bans
     */
    public List<TeamBans> getBans() {
        return bans;
    }

    /**
     * @return the baronKills
     */
    public int getBaronKills() {
        return baronKills;
    }

    /**
     * @return the dominionVictoryScore
     */
    public int getDominionVictoryScore() {
        return dominionVictoryScore;
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
     * @return the vilemawKills
     */
    public int getVilemawKills() {
        return vilemawKills;
    }

    /**
     * @return the win
     */
    public String getWin() {
        return win;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bans == null ? 0 : bans.hashCode());
        result = prime * result + baronKills;
        result = prime * result + dominionVictoryScore;
        result = prime * result + dragonKills;
        result = prime * result + (firstBaron ? 1231 : 1237);
        result = prime * result + (firstBlood ? 1231 : 1237);
        result = prime * result + (firstDragon ? 1231 : 1237);
        result = prime * result + (firstInhibitor ? 1231 : 1237);
        result = prime * result + (firstRiftHerald ? 1231 : 1237);
        result = prime * result + (firstTower ? 1231 : 1237);
        result = prime * result + inhibitorKills;
        result = prime * result + riftHeraldKills;
        result = prime * result + teamId;
        result = prime * result + towerKills;
        result = prime * result + vilemawKills;
        result = prime * result + (win == null ? 0 : win.hashCode());
        return result;
    }

    /**
     * @return the firstBaron
     */
    public boolean isFirstBaron() {
        return firstBaron;
    }

    /**
     * @return the firstBlood
     */
    public boolean isFirstBlood() {
        return firstBlood;
    }

    /**
     * @return the firstDragon
     */
    public boolean isFirstDragon() {
        return firstDragon;
    }

    /**
     * @return the firstInhibitor
     */
    public boolean isFirstInhibitor() {
        return firstInhibitor;
    }

    /**
     * @return the firstRiftHerald
     */
    public boolean isFirstRiftHerald() {
        return firstRiftHerald;
    }

    /**
     * @return the firstTower
     */
    public boolean isFirstTower() {
        return firstTower;
    }

    /**
     * @param bans
     *        the bans to set
     */
    public void setBans(final List<TeamBans> bans) {
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
     * @param dominionVictoryScore
     *        the dominionVictoryScore to set
     */
    public void setDominionVictoryScore(final int dominionVictoryScore) {
        this.dominionVictoryScore = dominionVictoryScore;
    }

    /**
     * @param dragonKills
     *        the dragonKills to set
     */
    public void setDragonKills(final int dragonKills) {
        this.dragonKills = dragonKills;
    }

    /**
     * @param firstBaron
     *        the firstBaron to set
     */
    public void setFirstBaron(final boolean firstBaron) {
        this.firstBaron = firstBaron;
    }

    /**
     * @param firstBlood
     *        the firstBlood to set
     */
    public void setFirstBlood(final boolean firstBlood) {
        this.firstBlood = firstBlood;
    }

    /**
     * @param firstDragon
     *        the firstDragon to set
     */
    public void setFirstDragon(final boolean firstDragon) {
        this.firstDragon = firstDragon;
    }

    /**
     * @param firstInhibitor
     *        the firstInhibitor to set
     */
    public void setFirstInhibitor(final boolean firstInhibitor) {
        this.firstInhibitor = firstInhibitor;
    }

    /**
     * @param firstRiftHerald
     *        the firstRiftHerald to set
     */
    public void setFirstRiftHerald(final boolean firstRiftHerald) {
        this.firstRiftHerald = firstRiftHerald;
    }

    /**
     * @param firstTower
     *        the firstTower to set
     */
    public void setFirstTower(final boolean firstTower) {
        this.firstTower = firstTower;
    }

    /**
     * @param inhibitorKills
     *        the inhibitorKills to set
     */
    public void setInhibitorKills(final int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
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
     * @param vilemawKills
     *        the vilemawKills to set
     */
    public void setVilemawKills(final int vilemawKills) {
        this.vilemawKills = vilemawKills;
    }

    /**
     * @param win
     *        the win to set
     */
    public void setWin(final String win) {
        this.win = win;
    }
}
