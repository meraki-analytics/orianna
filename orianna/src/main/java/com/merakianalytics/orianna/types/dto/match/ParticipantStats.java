package com.merakianalytics.orianna.types.dto.match;

import com.merakianalytics.orianna.types.dto.DataObject;

public class ParticipantStats extends DataObject {
    private static final long serialVersionUID = 7776142818024268605L;
    private int altarsCaptured, altarsNeutralized, assists, champLevel, combatPlayerScore, deaths, doubleKills, goldEarned, goldSpent, inhibitorKills, item0,
            item1, item2, item3, item4, item5, item6, killingSprees, kills, largestCriticalStrike, largestKillingSpree, largestMultiKill,
            longestTimeSpentLiving, neutralMinionsKilled, neutralMinionsKilledEnemyJungle, neutralMinionsKilledTeamJungle, nodeCapture, nodeCaptureAssist,
            nodeNeutralize, nodeNeutralizeAssist, objectivePlayerScore, participantId, pentaKills, quadraKills, sightWardsBoughtInGame, teamObjective,
            totalMinionsKilled, totalPlayerScore, totalScoreRank, totalTimeCrowdControlDealt, totalUnitsHealed, tripleKills, turretKills, unrealKills,
            visionWardsBoughtInGame, wardsKilled, wardsPlaced;
    private long damageDealtToObjectives, damageDealtToTurrets, damageSelfMitigated, magicalDamageTaken, magicDamageDealt, magicDamageDealtToChampions,
            physicalDamageDealt, physicalDamageDealtToChampions, physicalDamageTaken, timeCCingOthers, totalDamageDealt, totalDamageDealtToChampions,
            totalDamageTaken, totalHeal, trueDamageDealt, trueDamageDealtToChampions, trueDamageTaken, visionScore;
    private boolean firstBloodAssist, firstBloodKill, firstInhibitorAssist, firstInhibitorKill, firstTowerAssist, firstTowerKill, win;

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
        final ParticipantStats other = (ParticipantStats)obj;
        if(altarsCaptured != other.altarsCaptured) {
            return false;
        }
        if(altarsNeutralized != other.altarsNeutralized) {
            return false;
        }
        if(assists != other.assists) {
            return false;
        }
        if(champLevel != other.champLevel) {
            return false;
        }
        if(combatPlayerScore != other.combatPlayerScore) {
            return false;
        }
        if(damageDealtToObjectives != other.damageDealtToObjectives) {
            return false;
        }
        if(damageDealtToTurrets != other.damageDealtToTurrets) {
            return false;
        }
        if(damageSelfMitigated != other.damageSelfMitigated) {
            return false;
        }
        if(deaths != other.deaths) {
            return false;
        }
        if(doubleKills != other.doubleKills) {
            return false;
        }
        if(firstBloodAssist != other.firstBloodAssist) {
            return false;
        }
        if(firstBloodKill != other.firstBloodKill) {
            return false;
        }
        if(firstInhibitorAssist != other.firstInhibitorAssist) {
            return false;
        }
        if(firstInhibitorKill != other.firstInhibitorKill) {
            return false;
        }
        if(firstTowerAssist != other.firstTowerAssist) {
            return false;
        }
        if(firstTowerKill != other.firstTowerKill) {
            return false;
        }
        if(goldEarned != other.goldEarned) {
            return false;
        }
        if(goldSpent != other.goldSpent) {
            return false;
        }
        if(inhibitorKills != other.inhibitorKills) {
            return false;
        }
        if(item0 != other.item0) {
            return false;
        }
        if(item1 != other.item1) {
            return false;
        }
        if(item2 != other.item2) {
            return false;
        }
        if(item3 != other.item3) {
            return false;
        }
        if(item4 != other.item4) {
            return false;
        }
        if(item5 != other.item5) {
            return false;
        }
        if(item6 != other.item6) {
            return false;
        }
        if(killingSprees != other.killingSprees) {
            return false;
        }
        if(kills != other.kills) {
            return false;
        }
        if(largestCriticalStrike != other.largestCriticalStrike) {
            return false;
        }
        if(largestKillingSpree != other.largestKillingSpree) {
            return false;
        }
        if(largestMultiKill != other.largestMultiKill) {
            return false;
        }
        if(longestTimeSpentLiving != other.longestTimeSpentLiving) {
            return false;
        }
        if(magicDamageDealt != other.magicDamageDealt) {
            return false;
        }
        if(magicDamageDealtToChampions != other.magicDamageDealtToChampions) {
            return false;
        }
        if(magicalDamageTaken != other.magicalDamageTaken) {
            return false;
        }
        if(neutralMinionsKilled != other.neutralMinionsKilled) {
            return false;
        }
        if(neutralMinionsKilledEnemyJungle != other.neutralMinionsKilledEnemyJungle) {
            return false;
        }
        if(neutralMinionsKilledTeamJungle != other.neutralMinionsKilledTeamJungle) {
            return false;
        }
        if(nodeCapture != other.nodeCapture) {
            return false;
        }
        if(nodeCaptureAssist != other.nodeCaptureAssist) {
            return false;
        }
        if(nodeNeutralize != other.nodeNeutralize) {
            return false;
        }
        if(nodeNeutralizeAssist != other.nodeNeutralizeAssist) {
            return false;
        }
        if(objectivePlayerScore != other.objectivePlayerScore) {
            return false;
        }
        if(participantId != other.participantId) {
            return false;
        }
        if(pentaKills != other.pentaKills) {
            return false;
        }
        if(physicalDamageDealt != other.physicalDamageDealt) {
            return false;
        }
        if(physicalDamageDealtToChampions != other.physicalDamageDealtToChampions) {
            return false;
        }
        if(physicalDamageTaken != other.physicalDamageTaken) {
            return false;
        }
        if(quadraKills != other.quadraKills) {
            return false;
        }
        if(sightWardsBoughtInGame != other.sightWardsBoughtInGame) {
            return false;
        }
        if(teamObjective != other.teamObjective) {
            return false;
        }
        if(timeCCingOthers != other.timeCCingOthers) {
            return false;
        }
        if(totalDamageDealt != other.totalDamageDealt) {
            return false;
        }
        if(totalDamageDealtToChampions != other.totalDamageDealtToChampions) {
            return false;
        }
        if(totalDamageTaken != other.totalDamageTaken) {
            return false;
        }
        if(totalHeal != other.totalHeal) {
            return false;
        }
        if(totalMinionsKilled != other.totalMinionsKilled) {
            return false;
        }
        if(totalPlayerScore != other.totalPlayerScore) {
            return false;
        }
        if(totalScoreRank != other.totalScoreRank) {
            return false;
        }
        if(totalTimeCrowdControlDealt != other.totalTimeCrowdControlDealt) {
            return false;
        }
        if(totalUnitsHealed != other.totalUnitsHealed) {
            return false;
        }
        if(tripleKills != other.tripleKills) {
            return false;
        }
        if(trueDamageDealt != other.trueDamageDealt) {
            return false;
        }
        if(trueDamageDealtToChampions != other.trueDamageDealtToChampions) {
            return false;
        }
        if(trueDamageTaken != other.trueDamageTaken) {
            return false;
        }
        if(turretKills != other.turretKills) {
            return false;
        }
        if(unrealKills != other.unrealKills) {
            return false;
        }
        if(visionScore != other.visionScore) {
            return false;
        }
        if(visionWardsBoughtInGame != other.visionWardsBoughtInGame) {
            return false;
        }
        if(wardsKilled != other.wardsKilled) {
            return false;
        }
        if(wardsPlaced != other.wardsPlaced) {
            return false;
        }
        if(win != other.win) {
            return false;
        }
        return true;
    }

    /**
     * @return the altarsCaptured
     */
    public int getAltarsCaptured() {
        return altarsCaptured;
    }

    /**
     * @return the altarsNeutralized
     */
    public int getAltarsNeutralized() {
        return altarsNeutralized;
    }

    /**
     * @return the assists
     */
    public int getAssists() {
        return assists;
    }

    /**
     * @return the champLevel
     */
    public int getChampLevel() {
        return champLevel;
    }

    /**
     * @return the combatPlayerScore
     */
    public int getCombatPlayerScore() {
        return combatPlayerScore;
    }

    /**
     * @return the damageDealtToObjectives
     */
    public long getDamageDealtToObjectives() {
        return damageDealtToObjectives;
    }

    /**
     * @return the damageDealtToTurrets
     */
    public long getDamageDealtToTurrets() {
        return damageDealtToTurrets;
    }

    /**
     * @return the damageSelfMitigated
     */
    public long getDamageSelfMitigated() {
        return damageSelfMitigated;
    }

    /**
     * @return the deaths
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * @return the doubleKills
     */
    public int getDoubleKills() {
        return doubleKills;
    }

    /**
     * @return the goldEarned
     */
    public int getGoldEarned() {
        return goldEarned;
    }

    /**
     * @return the goldSpent
     */
    public int getGoldSpent() {
        return goldSpent;
    }

    /**
     * @return the inhibitorKills
     */
    public int getInhibitorKills() {
        return inhibitorKills;
    }

    /**
     * @return the item0
     */
    public int getItem0() {
        return item0;
    }

    /**
     * @return the item1
     */
    public int getItem1() {
        return item1;
    }

    /**
     * @return the item2
     */
    public int getItem2() {
        return item2;
    }

    /**
     * @return the item3
     */
    public int getItem3() {
        return item3;
    }

    /**
     * @return the item4
     */
    public int getItem4() {
        return item4;
    }

    /**
     * @return the item5
     */
    public int getItem5() {
        return item5;
    }

    /**
     * @return the item6
     */
    public int getItem6() {
        return item6;
    }

    /**
     * @return the killingSprees
     */
    public int getKillingSprees() {
        return killingSprees;
    }

    /**
     * @return the kills
     */
    public int getKills() {
        return kills;
    }

    /**
     * @return the largestCriticalStrike
     */
    public int getLargestCriticalStrike() {
        return largestCriticalStrike;
    }

    /**
     * @return the largestKillingSpree
     */
    public int getLargestKillingSpree() {
        return largestKillingSpree;
    }

    /**
     * @return the largestMultiKill
     */
    public int getLargestMultiKill() {
        return largestMultiKill;
    }

    /**
     * @return the longestTimeSpentLiving
     */
    public int getLongestTimeSpentLiving() {
        return longestTimeSpentLiving;
    }

    /**
     * @return the magicalDamageTaken
     */
    public long getMagicalDamageTaken() {
        return magicalDamageTaken;
    }

    /**
     * @return the magicDamageDealt
     */
    public long getMagicDamageDealt() {
        return magicDamageDealt;
    }

    /**
     * @return the magicDamageDealtToChampions
     */
    public long getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    /**
     * @return the neutralMinionsKilled
     */
    public int getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    /**
     * @return the neutralMinionsKilledEnemyJungle
     */
    public int getNeutralMinionsKilledEnemyJungle() {
        return neutralMinionsKilledEnemyJungle;
    }

    /**
     * @return the neutralMinionsKilledTeamJungle
     */
    public int getNeutralMinionsKilledTeamJungle() {
        return neutralMinionsKilledTeamJungle;
    }

    /**
     * @return the nodeCapture
     */
    public int getNodeCapture() {
        return nodeCapture;
    }

    /**
     * @return the nodeCaptureAssist
     */
    public int getNodeCaptureAssist() {
        return nodeCaptureAssist;
    }

    /**
     * @return the nodeNeutralize
     */
    public int getNodeNeutralize() {
        return nodeNeutralize;
    }

    /**
     * @return the nodeNeutralizeAssist
     */
    public int getNodeNeutralizeAssist() {
        return nodeNeutralizeAssist;
    }

    /**
     * @return the objectivePlayerScore
     */
    public int getObjectivePlayerScore() {
        return objectivePlayerScore;
    }

    /**
     * @return the participantId
     */
    public int getParticipantId() {
        return participantId;
    }

    /**
     * @return the pentaKills
     */
    public int getPentaKills() {
        return pentaKills;
    }

    /**
     * @return the physicalDamageDealt
     */
    public long getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    /**
     * @return the physicalDamageDealtToChampions
     */
    public long getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    /**
     * @return the physicalDamageTaken
     */
    public long getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    /**
     * @return the quadraKills
     */
    public int getQuadraKills() {
        return quadraKills;
    }

    /**
     * @return the sightWardsBoughtInGame
     */
    public int getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    /**
     * @return the teamObjective
     */
    public int getTeamObjective() {
        return teamObjective;
    }

    /**
     * @return the timeCCingOthers
     */
    public long getTimeCCingOthers() {
        return timeCCingOthers;
    }

    /**
     * @return the totalDamageDealt
     */
    public long getTotalDamageDealt() {
        return totalDamageDealt;
    }

    /**
     * @return the totalDamageDealtToChampions
     */
    public long getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    /**
     * @return the totalDamageTaken
     */
    public long getTotalDamageTaken() {
        return totalDamageTaken;
    }

    /**
     * @return the totalHeal
     */
    public long getTotalHeal() {
        return totalHeal;
    }

    /**
     * @return the totalMinionsKilled
     */
    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    /**
     * @return the totalPlayerScore
     */
    public int getTotalPlayerScore() {
        return totalPlayerScore;
    }

    /**
     * @return the totalScoreRank
     */
    public int getTotalScoreRank() {
        return totalScoreRank;
    }

    /**
     * @return the totalTimeCrowdControlDealt
     */
    public int getTotalTimeCrowdControlDealt() {
        return totalTimeCrowdControlDealt;
    }

    /**
     * @return the totalUnitsHealed
     */
    public int getTotalUnitsHealed() {
        return totalUnitsHealed;
    }

    /**
     * @return the tripleKills
     */
    public int getTripleKills() {
        return tripleKills;
    }

    /**
     * @return the trueDamageDealt
     */
    public long getTrueDamageDealt() {
        return trueDamageDealt;
    }

    /**
     * @return the trueDamageDealtToChampions
     */
    public long getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    /**
     * @return the trueDamageTaken
     */
    public long getTrueDamageTaken() {
        return trueDamageTaken;
    }

    /**
     * @return the turretKills
     */
    public int getTurretKills() {
        return turretKills;
    }

    /**
     * @return the unrealKills
     */
    public int getUnrealKills() {
        return unrealKills;
    }

    /**
     * @return the visionScore
     */
    public long getVisionScore() {
        return visionScore;
    }

    /**
     * @return the visionWardsBoughtInGame
     */
    public int getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    /**
     * @return the wardsKilled
     */
    public int getWardsKilled() {
        return wardsKilled;
    }

    /**
     * @return the wardsPlaced
     */
    public int getWardsPlaced() {
        return wardsPlaced;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + altarsCaptured;
        result = prime * result + altarsNeutralized;
        result = prime * result + assists;
        result = prime * result + champLevel;
        result = prime * result + combatPlayerScore;
        result = prime * result + (int)(damageDealtToObjectives ^ damageDealtToObjectives >>> 32);
        result = prime * result + (int)(damageDealtToTurrets ^ damageDealtToTurrets >>> 32);
        result = prime * result + (int)(damageSelfMitigated ^ damageSelfMitigated >>> 32);
        result = prime * result + deaths;
        result = prime * result + doubleKills;
        result = prime * result + (firstBloodAssist ? 1231 : 1237);
        result = prime * result + (firstBloodKill ? 1231 : 1237);
        result = prime * result + (firstInhibitorAssist ? 1231 : 1237);
        result = prime * result + (firstInhibitorKill ? 1231 : 1237);
        result = prime * result + (firstTowerAssist ? 1231 : 1237);
        result = prime * result + (firstTowerKill ? 1231 : 1237);
        result = prime * result + goldEarned;
        result = prime * result + goldSpent;
        result = prime * result + inhibitorKills;
        result = prime * result + item0;
        result = prime * result + item1;
        result = prime * result + item2;
        result = prime * result + item3;
        result = prime * result + item4;
        result = prime * result + item5;
        result = prime * result + item6;
        result = prime * result + killingSprees;
        result = prime * result + kills;
        result = prime * result + largestCriticalStrike;
        result = prime * result + largestKillingSpree;
        result = prime * result + largestMultiKill;
        result = prime * result + longestTimeSpentLiving;
        result = prime * result + (int)(magicDamageDealt ^ magicDamageDealt >>> 32);
        result = prime * result + (int)(magicDamageDealtToChampions ^ magicDamageDealtToChampions >>> 32);
        result = prime * result + (int)(magicalDamageTaken ^ magicalDamageTaken >>> 32);
        result = prime * result + neutralMinionsKilled;
        result = prime * result + neutralMinionsKilledEnemyJungle;
        result = prime * result + neutralMinionsKilledTeamJungle;
        result = prime * result + nodeCapture;
        result = prime * result + nodeCaptureAssist;
        result = prime * result + nodeNeutralize;
        result = prime * result + nodeNeutralizeAssist;
        result = prime * result + objectivePlayerScore;
        result = prime * result + participantId;
        result = prime * result + pentaKills;
        result = prime * result + (int)(physicalDamageDealt ^ physicalDamageDealt >>> 32);
        result = prime * result + (int)(physicalDamageDealtToChampions ^ physicalDamageDealtToChampions >>> 32);
        result = prime * result + (int)(physicalDamageTaken ^ physicalDamageTaken >>> 32);
        result = prime * result + quadraKills;
        result = prime * result + sightWardsBoughtInGame;
        result = prime * result + teamObjective;
        result = prime * result + (int)(timeCCingOthers ^ timeCCingOthers >>> 32);
        result = prime * result + (int)(totalDamageDealt ^ totalDamageDealt >>> 32);
        result = prime * result + (int)(totalDamageDealtToChampions ^ totalDamageDealtToChampions >>> 32);
        result = prime * result + (int)(totalDamageTaken ^ totalDamageTaken >>> 32);
        result = prime * result + (int)(totalHeal ^ totalHeal >>> 32);
        result = prime * result + totalMinionsKilled;
        result = prime * result + totalPlayerScore;
        result = prime * result + totalScoreRank;
        result = prime * result + totalTimeCrowdControlDealt;
        result = prime * result + totalUnitsHealed;
        result = prime * result + tripleKills;
        result = prime * result + (int)(trueDamageDealt ^ trueDamageDealt >>> 32);
        result = prime * result + (int)(trueDamageDealtToChampions ^ trueDamageDealtToChampions >>> 32);
        result = prime * result + (int)(trueDamageTaken ^ trueDamageTaken >>> 32);
        result = prime * result + turretKills;
        result = prime * result + unrealKills;
        result = prime * result + (int)(visionScore ^ visionScore >>> 32);
        result = prime * result + visionWardsBoughtInGame;
        result = prime * result + wardsKilled;
        result = prime * result + wardsPlaced;
        result = prime * result + (win ? 1231 : 1237);
        return result;
    }

    /**
     * @return the firstBloodAssist
     */
    public boolean isFirstBloodAssist() {
        return firstBloodAssist;
    }

    /**
     * @return the firstBloodKill
     */
    public boolean isFirstBloodKill() {
        return firstBloodKill;
    }

    /**
     * @return the firstInhibitorAssist
     */
    public boolean isFirstInhibitorAssist() {
        return firstInhibitorAssist;
    }

    /**
     * @return the firstInhibitorKill
     */
    public boolean isFirstInhibitorKill() {
        return firstInhibitorKill;
    }

    /**
     * @return the firstTowerAssist
     */
    public boolean isFirstTowerAssist() {
        return firstTowerAssist;
    }

    /**
     * @return the firstTowerKill
     */
    public boolean isFirstTowerKill() {
        return firstTowerKill;
    }

    /**
     * @return the win
     */
    public boolean isWin() {
        return win;
    }

    /**
     * @param altarsCaptured
     *        the altarsCaptured to set
     */
    public void setAltarsCaptured(final int altarsCaptured) {
        this.altarsCaptured = altarsCaptured;
    }

    /**
     * @param altarsNeutralized
     *        the altarsNeutralized to set
     */
    public void setAltarsNeutralized(final int altarsNeutralized) {
        this.altarsNeutralized = altarsNeutralized;
    }

    /**
     * @param assists
     *        the assists to set
     */
    public void setAssists(final int assists) {
        this.assists = assists;
    }

    /**
     * @param champLevel
     *        the champLevel to set
     */
    public void setChampLevel(final int champLevel) {
        this.champLevel = champLevel;
    }

    /**
     * @param combatPlayerScore
     *        the combatPlayerScore to set
     */
    public void setCombatPlayerScore(final int combatPlayerScore) {
        this.combatPlayerScore = combatPlayerScore;
    }

    /**
     * @param damageDealtToObjectives
     *        the damageDealtToObjectives to set
     */
    public void setDamageDealtToObjectives(final long damageDealtToObjectives) {
        this.damageDealtToObjectives = damageDealtToObjectives;
    }

    /**
     * @param damageDealtToTurrets
     *        the damageDealtToTurrets to set
     */
    public void setDamageDealtToTurrets(final long damageDealtToTurrets) {
        this.damageDealtToTurrets = damageDealtToTurrets;
    }

    /**
     * @param damageSelfMitigated
     *        the damageSelfMitigated to set
     */
    public void setDamageSelfMitigated(final long damageSelfMitigated) {
        this.damageSelfMitigated = damageSelfMitigated;
    }

    /**
     * @param deaths
     *        the deaths to set
     */
    public void setDeaths(final int deaths) {
        this.deaths = deaths;
    }

    /**
     * @param doubleKills
     *        the doubleKills to set
     */
    public void setDoubleKills(final int doubleKills) {
        this.doubleKills = doubleKills;
    }

    /**
     * @param firstBloodAssist
     *        the firstBloodAssist to set
     */
    public void setFirstBloodAssist(final boolean firstBloodAssist) {
        this.firstBloodAssist = firstBloodAssist;
    }

    /**
     * @param firstBloodKill
     *        the firstBloodKill to set
     */
    public void setFirstBloodKill(final boolean firstBloodKill) {
        this.firstBloodKill = firstBloodKill;
    }

    /**
     * @param firstInhibitorAssist
     *        the firstInhibitorAssist to set
     */
    public void setFirstInhibitorAssist(final boolean firstInhibitorAssist) {
        this.firstInhibitorAssist = firstInhibitorAssist;
    }

    /**
     * @param firstInhibitorKill
     *        the firstInhibitorKill to set
     */
    public void setFirstInhibitorKill(final boolean firstInhibitorKill) {
        this.firstInhibitorKill = firstInhibitorKill;
    }

    /**
     * @param firstTowerAssist
     *        the firstTowerAssist to set
     */
    public void setFirstTowerAssist(final boolean firstTowerAssist) {
        this.firstTowerAssist = firstTowerAssist;
    }

    /**
     * @param firstTowerKill
     *        the firstTowerKill to set
     */
    public void setFirstTowerKill(final boolean firstTowerKill) {
        this.firstTowerKill = firstTowerKill;
    }

    /**
     * @param goldEarned
     *        the goldEarned to set
     */
    public void setGoldEarned(final int goldEarned) {
        this.goldEarned = goldEarned;
    }

    /**
     * @param goldSpent
     *        the goldSpent to set
     */
    public void setGoldSpent(final int goldSpent) {
        this.goldSpent = goldSpent;
    }

    /**
     * @param inhibitorKills
     *        the inhibitorKills to set
     */
    public void setInhibitorKills(final int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    /**
     * @param item0
     *        the item0 to set
     */
    public void setItem0(final int item0) {
        this.item0 = item0;
    }

    /**
     * @param item1
     *        the item1 to set
     */
    public void setItem1(final int item1) {
        this.item1 = item1;
    }

    /**
     * @param item2
     *        the item2 to set
     */
    public void setItem2(final int item2) {
        this.item2 = item2;
    }

    /**
     * @param item3
     *        the item3 to set
     */
    public void setItem3(final int item3) {
        this.item3 = item3;
    }

    /**
     * @param item4
     *        the item4 to set
     */
    public void setItem4(final int item4) {
        this.item4 = item4;
    }

    /**
     * @param item5
     *        the item5 to set
     */
    public void setItem5(final int item5) {
        this.item5 = item5;
    }

    /**
     * @param item6
     *        the item6 to set
     */
    public void setItem6(final int item6) {
        this.item6 = item6;
    }

    /**
     * @param killingSprees
     *        the killingSprees to set
     */
    public void setKillingSprees(final int killingSprees) {
        this.killingSprees = killingSprees;
    }

    /**
     * @param kills
     *        the kills to set
     */
    public void setKills(final int kills) {
        this.kills = kills;
    }

    /**
     * @param largestCriticalStrike
     *        the largestCriticalStrike to set
     */
    public void setLargestCriticalStrike(final int largestCriticalStrike) {
        this.largestCriticalStrike = largestCriticalStrike;
    }

    /**
     * @param largestKillingSpree
     *        the largestKillingSpree to set
     */
    public void setLargestKillingSpree(final int largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }

    /**
     * @param largestMultiKill
     *        the largestMultiKill to set
     */
    public void setLargestMultiKill(final int largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    /**
     * @param longestTimeSpentLiving
     *        the longestTimeSpentLiving to set
     */
    public void setLongestTimeSpentLiving(final int longestTimeSpentLiving) {
        this.longestTimeSpentLiving = longestTimeSpentLiving;
    }

    /**
     * @param magicalDamageTaken
     *        the magicalDamageTaken to set
     */
    public void setMagicalDamageTaken(final long magicalDamageTaken) {
        this.magicalDamageTaken = magicalDamageTaken;
    }

    /**
     * @param magicDamageDealt
     *        the magicDamageDealt to set
     */
    public void setMagicDamageDealt(final long magicDamageDealt) {
        this.magicDamageDealt = magicDamageDealt;
    }

    /**
     * @param magicDamageDealtToChampions
     *        the magicDamageDealtToChampions to set
     */
    public void setMagicDamageDealtToChampions(final long magicDamageDealtToChampions) {
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
    }

    /**
     * @param neutralMinionsKilled
     *        the neutralMinionsKilled to set
     */
    public void setNeutralMinionsKilled(final int neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }

    /**
     * @param neutralMinionsKilledEnemyJungle
     *        the neutralMinionsKilledEnemyJungle to set
     */
    public void setNeutralMinionsKilledEnemyJungle(final int neutralMinionsKilledEnemyJungle) {
        this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
    }

    /**
     * @param neutralMinionsKilledTeamJungle
     *        the neutralMinionsKilledTeamJungle to set
     */
    public void setNeutralMinionsKilledTeamJungle(final int neutralMinionsKilledTeamJungle) {
        this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
    }

    /**
     * @param nodeCapture
     *        the nodeCapture to set
     */
    public void setNodeCapture(final int nodeCapture) {
        this.nodeCapture = nodeCapture;
    }

    /**
     * @param nodeCaptureAssist
     *        the nodeCaptureAssist to set
     */
    public void setNodeCaptureAssist(final int nodeCaptureAssist) {
        this.nodeCaptureAssist = nodeCaptureAssist;
    }

    /**
     * @param nodeNeutralize
     *        the nodeNeutralize to set
     */
    public void setNodeNeutralize(final int nodeNeutralize) {
        this.nodeNeutralize = nodeNeutralize;
    }

    /**
     * @param nodeNeutralizeAssist
     *        the nodeNeutralizeAssist to set
     */
    public void setNodeNeutralizeAssist(final int nodeNeutralizeAssist) {
        this.nodeNeutralizeAssist = nodeNeutralizeAssist;
    }

    /**
     * @param objectivePlayerScore
     *        the objectivePlayerScore to set
     */
    public void setObjectivePlayerScore(final int objectivePlayerScore) {
        this.objectivePlayerScore = objectivePlayerScore;
    }

    /**
     * @param participantId
     *        the participantId to set
     */
    public void setParticipantId(final int participantId) {
        this.participantId = participantId;
    }

    /**
     * @param pentaKills
     *        the pentaKills to set
     */
    public void setPentaKills(final int pentaKills) {
        this.pentaKills = pentaKills;
    }

    /**
     * @param physicalDamageDealt
     *        the physicalDamageDealt to set
     */
    public void setPhysicalDamageDealt(final long physicalDamageDealt) {
        this.physicalDamageDealt = physicalDamageDealt;
    }

    /**
     * @param physicalDamageDealtToChampions
     *        the physicalDamageDealtToChampions to set
     */
    public void setPhysicalDamageDealtToChampions(final long physicalDamageDealtToChampions) {
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
    }

    /**
     * @param physicalDamageTaken
     *        the physicalDamageTaken to set
     */
    public void setPhysicalDamageTaken(final long physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }

    /**
     * @param quadraKills
     *        the quadraKills to set
     */
    public void setQuadraKills(final int quadraKills) {
        this.quadraKills = quadraKills;
    }

    /**
     * @param sightWardsBoughtInGame
     *        the sightWardsBoughtInGame to set
     */
    public void setSightWardsBoughtInGame(final int sightWardsBoughtInGame) {
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
    }

    /**
     * @param teamObjective
     *        the teamObjective to set
     */
    public void setTeamObjective(final int teamObjective) {
        this.teamObjective = teamObjective;
    }

    /**
     * @param timeCCingOthers
     *        the timeCCingOthers to set
     */
    public void setTimeCCingOthers(final long timeCCingOthers) {
        this.timeCCingOthers = timeCCingOthers;
    }

    /**
     * @param totalDamageDealt
     *        the totalDamageDealt to set
     */
    public void setTotalDamageDealt(final long totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    /**
     * @param totalDamageDealtToChampions
     *        the totalDamageDealtToChampions to set
     */
    public void setTotalDamageDealtToChampions(final long totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }

    /**
     * @param totalDamageTaken
     *        the totalDamageTaken to set
     */
    public void setTotalDamageTaken(final long totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    /**
     * @param totalHeal
     *        the totalHeal to set
     */
    public void setTotalHeal(final long totalHeal) {
        this.totalHeal = totalHeal;
    }

    /**
     * @param totalMinionsKilled
     *        the totalMinionsKilled to set
     */
    public void setTotalMinionsKilled(final int totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }

    /**
     * @param totalPlayerScore
     *        the totalPlayerScore to set
     */
    public void setTotalPlayerScore(final int totalPlayerScore) {
        this.totalPlayerScore = totalPlayerScore;
    }

    /**
     * @param totalScoreRank
     *        the totalScoreRank to set
     */
    public void setTotalScoreRank(final int totalScoreRank) {
        this.totalScoreRank = totalScoreRank;
    }

    /**
     * @param totalTimeCrowdControlDealt
     *        the totalTimeCrowdControlDealt to set
     */
    public void setTotalTimeCrowdControlDealt(final int totalTimeCrowdControlDealt) {
        this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
    }

    /**
     * @param totalUnitsHealed
     *        the totalUnitsHealed to set
     */
    public void setTotalUnitsHealed(final int totalUnitsHealed) {
        this.totalUnitsHealed = totalUnitsHealed;
    }

    /**
     * @param tripleKills
     *        the tripleKills to set
     */
    public void setTripleKills(final int tripleKills) {
        this.tripleKills = tripleKills;
    }

    /**
     * @param trueDamageDealt
     *        the trueDamageDealt to set
     */
    public void setTrueDamageDealt(final long trueDamageDealt) {
        this.trueDamageDealt = trueDamageDealt;
    }

    /**
     * @param trueDamageDealtToChampions
     *        the trueDamageDealtToChampions to set
     */
    public void setTrueDamageDealtToChampions(final long trueDamageDealtToChampions) {
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
    }

    /**
     * @param trueDamageTaken
     *        the trueDamageTaken to set
     */
    public void setTrueDamageTaken(final long trueDamageTaken) {
        this.trueDamageTaken = trueDamageTaken;
    }

    /**
     * @param turretKills
     *        the turretKills to set
     */
    public void setTurretKills(final int turretKills) {
        this.turretKills = turretKills;
    }

    /**
     * @param unrealKills
     *        the unrealKills to set
     */
    public void setUnrealKills(final int unrealKills) {
        this.unrealKills = unrealKills;
    }

    /**
     * @param visionScore
     *        the visionScore to set
     */
    public void setVisionScore(final long visionScore) {
        this.visionScore = visionScore;
    }

    /**
     * @param visionWardsBoughtInGame
     *        the visionWardsBoughtInGame to set
     */
    public void setVisionWardsBoughtInGame(final int visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    /**
     * @param wardsKilled
     *        the wardsKilled to set
     */
    public void setWardsKilled(final int wardsKilled) {
        this.wardsKilled = wardsKilled;
    }

    /**
     * @param wardsPlaced
     *        the wardsPlaced to set
     */
    public void setWardsPlaced(final int wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }

    /**
     * @param win
     *        the win to set
     */
    public void setWin(final boolean win) {
        this.win = win;
    }
}
