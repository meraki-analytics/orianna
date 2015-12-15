package com.robrua.orianna.type.dto.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "participantstats")
public class ParticipantStats extends OriannaDto {
    private static final long serialVersionUID = 6303683508721189211L;
    private Long assists, champLevel, combatPlayerScore, deaths, doubleKills, goldEarned, goldSpent, inhibitorKills, item0, item1, item2, item3, item4, item5,
            item6, killingSprees, kills, largestCriticalStrike, largestKillingSpree, largestMultiKill, magicDamageDealt, magicDamageDealtToChampions,
            magicDamageTaken, minionsKilled, neutralMinionsKilled, neutralMinionsKilledEnemyJungle, neutralMinionsKilledTeamJungle, nodeCapture,
            nodeCaptureAssist, nodeNeutralize, nodeNeutralizeAssist, objectivePlayerScore, pentaKills, physicalDamageDealt, physicalDamageDealtToChampions,
            physicalDamageTaken, quadraKills, sightWardsBoughtInGame, teamObjective, totalDamageDealt, totalDamageDealtToChampions, totalDamageTaken, totalHeal,
            totalPlayerScore, totalScoreRank, totalTimeCrowdControlDealt, totalUnitsHealed, towerKills, tripleKills, trueDamageDealt,
            trueDamageDealtToChampions, trueDamageTaken, unrealKills, visionWardsBoughtInGame, wardsKilled, wardsPlaced;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Boolean winner, firstBloodAssist, firstBloodKill, firstInhibitorKill, firstInhibitorAssist, firstTowerAssist, firstTowerKill;

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
        if(!(obj instanceof ParticipantStats)) {
            return false;
        }
        final ParticipantStats other = (ParticipantStats)obj;
        if(assists == null) {
            if(other.assists != null) {
                return false;
            }
        }
        else if(!assists.equals(other.assists)) {
            return false;
        }
        if(champLevel == null) {
            if(other.champLevel != null) {
                return false;
            }
        }
        else if(!champLevel.equals(other.champLevel)) {
            return false;
        }
        if(combatPlayerScore == null) {
            if(other.combatPlayerScore != null) {
                return false;
            }
        }
        else if(!combatPlayerScore.equals(other.combatPlayerScore)) {
            return false;
        }
        if(deaths == null) {
            if(other.deaths != null) {
                return false;
            }
        }
        else if(!deaths.equals(other.deaths)) {
            return false;
        }
        if(doubleKills == null) {
            if(other.doubleKills != null) {
                return false;
            }
        }
        else if(!doubleKills.equals(other.doubleKills)) {
            return false;
        }
        if(firstBloodAssist == null) {
            if(other.firstBloodAssist != null) {
                return false;
            }
        }
        else if(!firstBloodAssist.equals(other.firstBloodAssist)) {
            return false;
        }
        if(firstBloodKill == null) {
            if(other.firstBloodKill != null) {
                return false;
            }
        }
        else if(!firstBloodKill.equals(other.firstBloodKill)) {
            return false;
        }
        if(firstInhibitorAssist == null) {
            if(other.firstInhibitorAssist != null) {
                return false;
            }
        }
        else if(!firstInhibitorAssist.equals(other.firstInhibitorAssist)) {
            return false;
        }
        if(firstInhibitorKill == null) {
            if(other.firstInhibitorKill != null) {
                return false;
            }
        }
        else if(!firstInhibitorKill.equals(other.firstInhibitorKill)) {
            return false;
        }
        if(firstTowerAssist == null) {
            if(other.firstTowerAssist != null) {
                return false;
            }
        }
        else if(!firstTowerAssist.equals(other.firstTowerAssist)) {
            return false;
        }
        if(firstTowerKill == null) {
            if(other.firstTowerKill != null) {
                return false;
            }
        }
        else if(!firstTowerKill.equals(other.firstTowerKill)) {
            return false;
        }
        if(goldEarned == null) {
            if(other.goldEarned != null) {
                return false;
            }
        }
        else if(!goldEarned.equals(other.goldEarned)) {
            return false;
        }
        if(goldSpent == null) {
            if(other.goldSpent != null) {
                return false;
            }
        }
        else if(!goldSpent.equals(other.goldSpent)) {
            return false;
        }
        if(inhibitorKills == null) {
            if(other.inhibitorKills != null) {
                return false;
            }
        }
        else if(!inhibitorKills.equals(other.inhibitorKills)) {
            return false;
        }
        if(item0 == null) {
            if(other.item0 != null) {
                return false;
            }
        }
        else if(!item0.equals(other.item0)) {
            return false;
        }
        if(item1 == null) {
            if(other.item1 != null) {
                return false;
            }
        }
        else if(!item1.equals(other.item1)) {
            return false;
        }
        if(item2 == null) {
            if(other.item2 != null) {
                return false;
            }
        }
        else if(!item2.equals(other.item2)) {
            return false;
        }
        if(item3 == null) {
            if(other.item3 != null) {
                return false;
            }
        }
        else if(!item3.equals(other.item3)) {
            return false;
        }
        if(item4 == null) {
            if(other.item4 != null) {
                return false;
            }
        }
        else if(!item4.equals(other.item4)) {
            return false;
        }
        if(item5 == null) {
            if(other.item5 != null) {
                return false;
            }
        }
        else if(!item5.equals(other.item5)) {
            return false;
        }
        if(item6 == null) {
            if(other.item6 != null) {
                return false;
            }
        }
        else if(!item6.equals(other.item6)) {
            return false;
        }
        if(killingSprees == null) {
            if(other.killingSprees != null) {
                return false;
            }
        }
        else if(!killingSprees.equals(other.killingSprees)) {
            return false;
        }
        if(kills == null) {
            if(other.kills != null) {
                return false;
            }
        }
        else if(!kills.equals(other.kills)) {
            return false;
        }
        if(largestCriticalStrike == null) {
            if(other.largestCriticalStrike != null) {
                return false;
            }
        }
        else if(!largestCriticalStrike.equals(other.largestCriticalStrike)) {
            return false;
        }
        if(largestKillingSpree == null) {
            if(other.largestKillingSpree != null) {
                return false;
            }
        }
        else if(!largestKillingSpree.equals(other.largestKillingSpree)) {
            return false;
        }
        if(largestMultiKill == null) {
            if(other.largestMultiKill != null) {
                return false;
            }
        }
        else if(!largestMultiKill.equals(other.largestMultiKill)) {
            return false;
        }
        if(magicDamageDealt == null) {
            if(other.magicDamageDealt != null) {
                return false;
            }
        }
        else if(!magicDamageDealt.equals(other.magicDamageDealt)) {
            return false;
        }
        if(magicDamageDealtToChampions == null) {
            if(other.magicDamageDealtToChampions != null) {
                return false;
            }
        }
        else if(!magicDamageDealtToChampions.equals(other.magicDamageDealtToChampions)) {
            return false;
        }
        if(magicDamageTaken == null) {
            if(other.magicDamageTaken != null) {
                return false;
            }
        }
        else if(!magicDamageTaken.equals(other.magicDamageTaken)) {
            return false;
        }
        if(minionsKilled == null) {
            if(other.minionsKilled != null) {
                return false;
            }
        }
        else if(!minionsKilled.equals(other.minionsKilled)) {
            return false;
        }
        if(neutralMinionsKilled == null) {
            if(other.neutralMinionsKilled != null) {
                return false;
            }
        }
        else if(!neutralMinionsKilled.equals(other.neutralMinionsKilled)) {
            return false;
        }
        if(neutralMinionsKilledEnemyJungle == null) {
            if(other.neutralMinionsKilledEnemyJungle != null) {
                return false;
            }
        }
        else if(!neutralMinionsKilledEnemyJungle.equals(other.neutralMinionsKilledEnemyJungle)) {
            return false;
        }
        if(neutralMinionsKilledTeamJungle == null) {
            if(other.neutralMinionsKilledTeamJungle != null) {
                return false;
            }
        }
        else if(!neutralMinionsKilledTeamJungle.equals(other.neutralMinionsKilledTeamJungle)) {
            return false;
        }
        if(nodeCapture == null) {
            if(other.nodeCapture != null) {
                return false;
            }
        }
        else if(!nodeCapture.equals(other.nodeCapture)) {
            return false;
        }
        if(nodeCaptureAssist == null) {
            if(other.nodeCaptureAssist != null) {
                return false;
            }
        }
        else if(!nodeCaptureAssist.equals(other.nodeCaptureAssist)) {
            return false;
        }
        if(nodeNeutralize == null) {
            if(other.nodeNeutralize != null) {
                return false;
            }
        }
        else if(!nodeNeutralize.equals(other.nodeNeutralize)) {
            return false;
        }
        if(nodeNeutralizeAssist == null) {
            if(other.nodeNeutralizeAssist != null) {
                return false;
            }
        }
        else if(!nodeNeutralizeAssist.equals(other.nodeNeutralizeAssist)) {
            return false;
        }
        if(objectivePlayerScore == null) {
            if(other.objectivePlayerScore != null) {
                return false;
            }
        }
        else if(!objectivePlayerScore.equals(other.objectivePlayerScore)) {
            return false;
        }
        if(pentaKills == null) {
            if(other.pentaKills != null) {
                return false;
            }
        }
        else if(!pentaKills.equals(other.pentaKills)) {
            return false;
        }
        if(physicalDamageDealt == null) {
            if(other.physicalDamageDealt != null) {
                return false;
            }
        }
        else if(!physicalDamageDealt.equals(other.physicalDamageDealt)) {
            return false;
        }
        if(physicalDamageDealtToChampions == null) {
            if(other.physicalDamageDealtToChampions != null) {
                return false;
            }
        }
        else if(!physicalDamageDealtToChampions.equals(other.physicalDamageDealtToChampions)) {
            return false;
        }
        if(physicalDamageTaken == null) {
            if(other.physicalDamageTaken != null) {
                return false;
            }
        }
        else if(!physicalDamageTaken.equals(other.physicalDamageTaken)) {
            return false;
        }
        if(quadraKills == null) {
            if(other.quadraKills != null) {
                return false;
            }
        }
        else if(!quadraKills.equals(other.quadraKills)) {
            return false;
        }
        if(sightWardsBoughtInGame == null) {
            if(other.sightWardsBoughtInGame != null) {
                return false;
            }
        }
        else if(!sightWardsBoughtInGame.equals(other.sightWardsBoughtInGame)) {
            return false;
        }
        if(teamObjective == null) {
            if(other.teamObjective != null) {
                return false;
            }
        }
        else if(!teamObjective.equals(other.teamObjective)) {
            return false;
        }
        if(totalDamageDealt == null) {
            if(other.totalDamageDealt != null) {
                return false;
            }
        }
        else if(!totalDamageDealt.equals(other.totalDamageDealt)) {
            return false;
        }
        if(totalDamageDealtToChampions == null) {
            if(other.totalDamageDealtToChampions != null) {
                return false;
            }
        }
        else if(!totalDamageDealtToChampions.equals(other.totalDamageDealtToChampions)) {
            return false;
        }
        if(totalDamageTaken == null) {
            if(other.totalDamageTaken != null) {
                return false;
            }
        }
        else if(!totalDamageTaken.equals(other.totalDamageTaken)) {
            return false;
        }
        if(totalHeal == null) {
            if(other.totalHeal != null) {
                return false;
            }
        }
        else if(!totalHeal.equals(other.totalHeal)) {
            return false;
        }
        if(totalPlayerScore == null) {
            if(other.totalPlayerScore != null) {
                return false;
            }
        }
        else if(!totalPlayerScore.equals(other.totalPlayerScore)) {
            return false;
        }
        if(totalScoreRank == null) {
            if(other.totalScoreRank != null) {
                return false;
            }
        }
        else if(!totalScoreRank.equals(other.totalScoreRank)) {
            return false;
        }
        if(totalTimeCrowdControlDealt == null) {
            if(other.totalTimeCrowdControlDealt != null) {
                return false;
            }
        }
        else if(!totalTimeCrowdControlDealt.equals(other.totalTimeCrowdControlDealt)) {
            return false;
        }
        if(totalUnitsHealed == null) {
            if(other.totalUnitsHealed != null) {
                return false;
            }
        }
        else if(!totalUnitsHealed.equals(other.totalUnitsHealed)) {
            return false;
        }
        if(towerKills == null) {
            if(other.towerKills != null) {
                return false;
            }
        }
        else if(!towerKills.equals(other.towerKills)) {
            return false;
        }
        if(tripleKills == null) {
            if(other.tripleKills != null) {
                return false;
            }
        }
        else if(!tripleKills.equals(other.tripleKills)) {
            return false;
        }
        if(trueDamageDealt == null) {
            if(other.trueDamageDealt != null) {
                return false;
            }
        }
        else if(!trueDamageDealt.equals(other.trueDamageDealt)) {
            return false;
        }
        if(trueDamageDealtToChampions == null) {
            if(other.trueDamageDealtToChampions != null) {
                return false;
            }
        }
        else if(!trueDamageDealtToChampions.equals(other.trueDamageDealtToChampions)) {
            return false;
        }
        if(trueDamageTaken == null) {
            if(other.trueDamageTaken != null) {
                return false;
            }
        }
        else if(!trueDamageTaken.equals(other.trueDamageTaken)) {
            return false;
        }
        if(unrealKills == null) {
            if(other.unrealKills != null) {
                return false;
            }
        }
        else if(!unrealKills.equals(other.unrealKills)) {
            return false;
        }
        if(visionWardsBoughtInGame == null) {
            if(other.visionWardsBoughtInGame != null) {
                return false;
            }
        }
        else if(!visionWardsBoughtInGame.equals(other.visionWardsBoughtInGame)) {
            return false;
        }
        if(wardsKilled == null) {
            if(other.wardsKilled != null) {
                return false;
            }
        }
        else if(!wardsKilled.equals(other.wardsKilled)) {
            return false;
        }
        if(wardsPlaced == null) {
            if(other.wardsPlaced != null) {
                return false;
            }
        }
        else if(!wardsPlaced.equals(other.wardsPlaced)) {
            return false;
        }
        if(winner == null) {
            if(other.winner != null) {
                return false;
            }
        }
        else if(!winner.equals(other.winner)) {
            return false;
        }
        return true;
    }

    /**
     * @return the assists
     */
    public Long getAssists() {
        return assists;
    }

    /**
     * @return the champLevel
     */
    public Long getChampLevel() {
        return champLevel;
    }

    /**
     * @return the combatPlayerScore
     */
    public Long getCombatPlayerScore() {
        return combatPlayerScore;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the deaths
     */
    public Long getDeaths() {
        return deaths;
    }

    /**
     * @return the doubleKills
     */
    public Long getDoubleKills() {
        return doubleKills;
    }

    /**
     * @return the firstBloodAssist
     */
    public Boolean getFirstBloodAssist() {
        return firstBloodAssist;
    }

    /**
     * @return the firstBloodKill
     */
    public Boolean getFirstBloodKill() {
        return firstBloodKill;
    }

    /**
     * @return the firstInhibitorAssist
     */
    public Boolean getFirstInhibitorAssist() {
        return firstInhibitorAssist;
    }

    /**
     * @return the firstInhibitorKill
     */
    public Boolean getFirstInhibitorKill() {
        return firstInhibitorKill;
    }

    /**
     * @return the firstTowerAssist
     */
    public Boolean getFirstTowerAssist() {
        return firstTowerAssist;
    }

    /**
     * @return the firstTowerKill
     */
    public Boolean getFirstTowerKill() {
        return firstTowerKill;
    }

    /**
     * @return the goldEarned
     */
    public Long getGoldEarned() {
        return goldEarned;
    }

    /**
     * @return the goldSpent
     */
    public Long getGoldSpent() {
        return goldSpent;
    }

    /**
     * @return the inhibitorKills
     */
    public Long getInhibitorKills() {
        return inhibitorKills;
    }

    /**
     * @return the item0
     */
    public Long getItem0() {
        return item0;
    }

    /**
     * @return the item1
     */
    public Long getItem1() {
        return item1;
    }

    /**
     * @return the item2
     */
    public Long getItem2() {
        return item2;
    }

    /**
     * @return the item3
     */
    public Long getItem3() {
        return item3;
    }

    /**
     * @return the item4
     */
    public Long getItem4() {
        return item4;
    }

    /**
     * @return the item5
     */
    public Long getItem5() {
        return item5;
    }

    /**
     * @return the item6
     */
    public Long getItem6() {
        return item6;
    }

    /**
     * @return the killingSprees
     */
    public Long getKillingSprees() {
        return killingSprees;
    }

    /**
     * @return the kills
     */
    public Long getKills() {
        return kills;
    }

    /**
     * @return the largestCriticalStrike
     */
    public Long getLargestCriticalStrike() {
        return largestCriticalStrike;
    }

    /**
     * @return the largestKillingSpree
     */
    public Long getLargestKillingSpree() {
        return largestKillingSpree;
    }

    /**
     * @return the largestMultiKill
     */
    public Long getLargestMultiKill() {
        return largestMultiKill;
    }

    /**
     * @return the magicDamageDealt
     */
    public Long getMagicDamageDealt() {
        return magicDamageDealt;
    }

    /**
     * @return the magicDamageDealtToChampions
     */
    public Long getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    /**
     * @return the magicDamageTaken
     */
    public Long getMagicDamageTaken() {
        return magicDamageTaken;
    }

    /**
     * @return the minionsKilled
     */
    public Long getMinionsKilled() {
        return minionsKilled;
    }

    /**
     * @return the neutralMinionsKilled
     */
    public Long getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    /**
     * @return the neutralMinionsKilledEnemyJungle
     */
    public Long getNeutralMinionsKilledEnemyJungle() {
        return neutralMinionsKilledEnemyJungle;
    }

    /**
     * @return the neutralMinionsKilledTeamJungle
     */
    public Long getNeutralMinionsKilledTeamJungle() {
        return neutralMinionsKilledTeamJungle;
    }

    /**
     * @return the nodeCapture
     */
    public Long getNodeCapture() {
        return nodeCapture;
    }

    /**
     * @return the nodeCaptureAssist
     */
    public Long getNodeCaptureAssist() {
        return nodeCaptureAssist;
    }

    /**
     * @return the nodeNeutralize
     */
    public Long getNodeNeutralize() {
        return nodeNeutralize;
    }

    /**
     * @return the nodeNeutralizeAssist
     */
    public Long getNodeNeutralizeAssist() {
        return nodeNeutralizeAssist;
    }

    /**
     * @return the objectivePlayerScore
     */
    public Long getObjectivePlayerScore() {
        return objectivePlayerScore;
    }

    /**
     * @return the pentaKills
     */
    public Long getPentaKills() {
        return pentaKills;
    }

    /**
     * @return the physicalDamageDealt
     */
    public Long getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    /**
     * @return the physicalDamageDealtToChampions
     */
    public Long getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    /**
     * @return the physicalDamageTaken
     */
    public Long getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    /**
     * @return the quadraKills
     */
    public Long getQuadraKills() {
        return quadraKills;
    }

    /**
     * @return the sightWardsBoughtInGame
     */
    public Long getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    /**
     * @return the teamObjective
     */
    public Long getTeamObjective() {
        return teamObjective;
    }

    /**
     * @return the totalDamageDealt
     */
    public Long getTotalDamageDealt() {
        return totalDamageDealt;
    }

    /**
     * @return the totalDamageDealtToChampions
     */
    public Long getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    /**
     * @return the totalDamageTaken
     */
    public Long getTotalDamageTaken() {
        return totalDamageTaken;
    }

    /**
     * @return the totalHeal
     */
    public Long getTotalHeal() {
        return totalHeal;
    }

    /**
     * @return the totalPlayerScore
     */
    public Long getTotalPlayerScore() {
        return totalPlayerScore;
    }

    /**
     * @return the totalScoreRank
     */
    public Long getTotalScoreRank() {
        return totalScoreRank;
    }

    /**
     * @return the totalTimeCrowdControlDealt
     */
    public Long getTotalTimeCrowdControlDealt() {
        return totalTimeCrowdControlDealt;
    }

    /**
     * @return the totalUnitsHealed
     */
    public Long getTotalUnitsHealed() {
        return totalUnitsHealed;
    }

    /**
     * @return the towerKills
     */
    public Long getTowerKills() {
        return towerKills;
    }

    /**
     * @return the tripleKills
     */
    public Long getTripleKills() {
        return tripleKills;
    }

    /**
     * @return the trueDamageDealt
     */
    public Long getTrueDamageDealt() {
        return trueDamageDealt;
    }

    /**
     * @return the trueDamageDealtToChampions
     */
    public Long getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    /**
     * @return the trueDamageTaken
     */
    public Long getTrueDamageTaken() {
        return trueDamageTaken;
    }

    /**
     * @return the unrealKills
     */
    public Long getUnrealKills() {
        return unrealKills;
    }

    /**
     * @return the visionWardsBoughtInGame
     */
    public Long getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    /**
     * @return the wardsKilled
     */
    public Long getWardsKilled() {
        return wardsKilled;
    }

    /**
     * @return the wardsPlaced
     */
    public Long getWardsPlaced() {
        return wardsPlaced;
    }

    /**
     * @return the winner
     */
    public Boolean getWinner() {
        return winner;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (assists == null ? 0 : assists.hashCode());
        result = prime * result + (champLevel == null ? 0 : champLevel.hashCode());
        result = prime * result + (combatPlayerScore == null ? 0 : combatPlayerScore.hashCode());
        result = prime * result + (deaths == null ? 0 : deaths.hashCode());
        result = prime * result + (doubleKills == null ? 0 : doubleKills.hashCode());
        result = prime * result + (firstBloodAssist == null ? 0 : firstBloodAssist.hashCode());
        result = prime * result + (firstBloodKill == null ? 0 : firstBloodKill.hashCode());
        result = prime * result + (firstInhibitorAssist == null ? 0 : firstInhibitorAssist.hashCode());
        result = prime * result + (firstInhibitorKill == null ? 0 : firstInhibitorKill.hashCode());
        result = prime * result + (firstTowerAssist == null ? 0 : firstTowerAssist.hashCode());
        result = prime * result + (firstTowerKill == null ? 0 : firstTowerKill.hashCode());
        result = prime * result + (goldEarned == null ? 0 : goldEarned.hashCode());
        result = prime * result + (goldSpent == null ? 0 : goldSpent.hashCode());
        result = prime * result + (inhibitorKills == null ? 0 : inhibitorKills.hashCode());
        result = prime * result + (item0 == null ? 0 : item0.hashCode());
        result = prime * result + (item1 == null ? 0 : item1.hashCode());
        result = prime * result + (item2 == null ? 0 : item2.hashCode());
        result = prime * result + (item3 == null ? 0 : item3.hashCode());
        result = prime * result + (item4 == null ? 0 : item4.hashCode());
        result = prime * result + (item5 == null ? 0 : item5.hashCode());
        result = prime * result + (item6 == null ? 0 : item6.hashCode());
        result = prime * result + (killingSprees == null ? 0 : killingSprees.hashCode());
        result = prime * result + (kills == null ? 0 : kills.hashCode());
        result = prime * result + (largestCriticalStrike == null ? 0 : largestCriticalStrike.hashCode());
        result = prime * result + (largestKillingSpree == null ? 0 : largestKillingSpree.hashCode());
        result = prime * result + (largestMultiKill == null ? 0 : largestMultiKill.hashCode());
        result = prime * result + (magicDamageDealt == null ? 0 : magicDamageDealt.hashCode());
        result = prime * result + (magicDamageDealtToChampions == null ? 0 : magicDamageDealtToChampions.hashCode());
        result = prime * result + (magicDamageTaken == null ? 0 : magicDamageTaken.hashCode());
        result = prime * result + (minionsKilled == null ? 0 : minionsKilled.hashCode());
        result = prime * result + (neutralMinionsKilled == null ? 0 : neutralMinionsKilled.hashCode());
        result = prime * result + (neutralMinionsKilledEnemyJungle == null ? 0 : neutralMinionsKilledEnemyJungle.hashCode());
        result = prime * result + (neutralMinionsKilledTeamJungle == null ? 0 : neutralMinionsKilledTeamJungle.hashCode());
        result = prime * result + (nodeCapture == null ? 0 : nodeCapture.hashCode());
        result = prime * result + (nodeCaptureAssist == null ? 0 : nodeCaptureAssist.hashCode());
        result = prime * result + (nodeNeutralize == null ? 0 : nodeNeutralize.hashCode());
        result = prime * result + (nodeNeutralizeAssist == null ? 0 : nodeNeutralizeAssist.hashCode());
        result = prime * result + (objectivePlayerScore == null ? 0 : objectivePlayerScore.hashCode());
        result = prime * result + (pentaKills == null ? 0 : pentaKills.hashCode());
        result = prime * result + (physicalDamageDealt == null ? 0 : physicalDamageDealt.hashCode());
        result = prime * result + (physicalDamageDealtToChampions == null ? 0 : physicalDamageDealtToChampions.hashCode());
        result = prime * result + (physicalDamageTaken == null ? 0 : physicalDamageTaken.hashCode());
        result = prime * result + (quadraKills == null ? 0 : quadraKills.hashCode());
        result = prime * result + (sightWardsBoughtInGame == null ? 0 : sightWardsBoughtInGame.hashCode());
        result = prime * result + (teamObjective == null ? 0 : teamObjective.hashCode());
        result = prime * result + (totalDamageDealt == null ? 0 : totalDamageDealt.hashCode());
        result = prime * result + (totalDamageDealtToChampions == null ? 0 : totalDamageDealtToChampions.hashCode());
        result = prime * result + (totalDamageTaken == null ? 0 : totalDamageTaken.hashCode());
        result = prime * result + (totalHeal == null ? 0 : totalHeal.hashCode());
        result = prime * result + (totalPlayerScore == null ? 0 : totalPlayerScore.hashCode());
        result = prime * result + (totalScoreRank == null ? 0 : totalScoreRank.hashCode());
        result = prime * result + (totalTimeCrowdControlDealt == null ? 0 : totalTimeCrowdControlDealt.hashCode());
        result = prime * result + (totalUnitsHealed == null ? 0 : totalUnitsHealed.hashCode());
        result = prime * result + (towerKills == null ? 0 : towerKills.hashCode());
        result = prime * result + (tripleKills == null ? 0 : tripleKills.hashCode());
        result = prime * result + (trueDamageDealt == null ? 0 : trueDamageDealt.hashCode());
        result = prime * result + (trueDamageDealtToChampions == null ? 0 : trueDamageDealtToChampions.hashCode());
        result = prime * result + (trueDamageTaken == null ? 0 : trueDamageTaken.hashCode());
        result = prime * result + (unrealKills == null ? 0 : unrealKills.hashCode());
        result = prime * result + (visionWardsBoughtInGame == null ? 0 : visionWardsBoughtInGame.hashCode());
        result = prime * result + (wardsKilled == null ? 0 : wardsKilled.hashCode());
        result = prime * result + (wardsPlaced == null ? 0 : wardsPlaced.hashCode());
        result = prime * result + (winner == null ? 0 : winner.hashCode());
        return result;
    }

    /**
     * @param assists
     *            the assists to set
     */
    public void setAssists(final Long assists) {
        this.assists = assists;
    }

    /**
     * @param champLevel
     *            the champLevel to set
     */
    public void setChampLevel(final Long champLevel) {
        this.champLevel = champLevel;
    }

    /**
     * @param combatPlayerScore
     *            the combatPlayerScore to set
     */
    public void setCombatPlayerScore(final Long combatPlayerScore) {
        this.combatPlayerScore = combatPlayerScore;
    }

    /**
     * @param deaths
     *            the deaths to set
     */
    public void setDeaths(final Long deaths) {
        this.deaths = deaths;
    }

    /**
     * @param doubleKills
     *            the doubleKills to set
     */
    public void setDoubleKills(final Long doubleKills) {
        this.doubleKills = doubleKills;
    }

    /**
     * @param firstBloodAssist
     *            the firstBloodAssist to set
     */
    public void setFirstBloodAssist(final Boolean firstBloodAssist) {
        this.firstBloodAssist = firstBloodAssist;
    }

    /**
     * @param firstBloodKill
     *            the firstBloodKill to set
     */
    public void setFirstBloodKill(final Boolean firstBloodKill) {
        this.firstBloodKill = firstBloodKill;
    }

    /**
     * @param firstInhibitorAssist
     *            the firstInhibitorAssist to set
     */
    public void setFirstInhibitorAssist(final Boolean firstInhibitorAssist) {
        this.firstInhibitorAssist = firstInhibitorAssist;
    }

    /**
     * @param firstInhibitorKill
     *            the firstInhibitorKill to set
     */
    public void setFirstInhibitorKill(final Boolean firstInhibitorKill) {
        this.firstInhibitorKill = firstInhibitorKill;
    }

    /**
     * @param firstTowerAssist
     *            the firstTowerAssist to set
     */
    public void setFirstTowerAssist(final Boolean firstTowerAssist) {
        this.firstTowerAssist = firstTowerAssist;
    }

    /**
     * @param firstTowerKill
     *            the firstTowerKill to set
     */
    public void setFirstTowerKill(final Boolean firstTowerKill) {
        this.firstTowerKill = firstTowerKill;
    }

    /**
     * @param goldEarned
     *            the goldEarned to set
     */
    public void setGoldEarned(final Long goldEarned) {
        this.goldEarned = goldEarned;
    }

    /**
     * @param goldSpent
     *            the goldSpent to set
     */
    public void setGoldSpent(final Long goldSpent) {
        this.goldSpent = goldSpent;
    }

    /**
     * @param inhibitorKills
     *            the inhibitorKills to set
     */
    public void setInhibitorKills(final Long inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    /**
     * @param item0
     *            the item0 to set
     */
    public void setItem0(final Long item0) {
        this.item0 = item0;
    }

    /**
     * @param item1
     *            the item1 to set
     */
    public void setItem1(final Long item1) {
        this.item1 = item1;
    }

    /**
     * @param item2
     *            the item2 to set
     */
    public void setItem2(final Long item2) {
        this.item2 = item2;
    }

    /**
     * @param item3
     *            the item3 to set
     */
    public void setItem3(final Long item3) {
        this.item3 = item3;
    }

    /**
     * @param item4
     *            the item4 to set
     */
    public void setItem4(final Long item4) {
        this.item4 = item4;
    }

    /**
     * @param item5
     *            the item5 to set
     */
    public void setItem5(final Long item5) {
        this.item5 = item5;
    }

    /**
     * @param item6
     *            the item6 to set
     */
    public void setItem6(final Long item6) {
        this.item6 = item6;
    }

    /**
     * @param killingSprees
     *            the killingSprees to set
     */
    public void setKillingSprees(final Long killingSprees) {
        this.killingSprees = killingSprees;
    }

    /**
     * @param kills
     *            the kills to set
     */
    public void setKills(final Long kills) {
        this.kills = kills;
    }

    /**
     * @param largestCriticalStrike
     *            the largestCriticalStrike to set
     */
    public void setLargestCriticalStrike(final Long largestCriticalStrike) {
        this.largestCriticalStrike = largestCriticalStrike;
    }

    /**
     * @param largestKillingSpree
     *            the largestKillingSpree to set
     */
    public void setLargestKillingSpree(final Long largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }

    /**
     * @param largestMultiKill
     *            the largestMultiKill to set
     */
    public void setLargestMultiKill(final Long largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    /**
     * @param magicDamageDealt
     *            the magicDamageDealt to set
     */
    public void setMagicDamageDealt(final Long magicDamageDealt) {
        this.magicDamageDealt = magicDamageDealt;
    }

    /**
     * @param magicDamageDealtToChampions
     *            the magicDamageDealtToChampions to set
     */
    public void setMagicDamageDealtToChampions(final Long magicDamageDealtToChampions) {
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
    }

    /**
     * @param magicDamageTaken
     *            the magicDamageTaken to set
     */
    public void setMagicDamageTaken(final Long magicDamageTaken) {
        this.magicDamageTaken = magicDamageTaken;
    }

    /**
     * @param minionsKilled
     *            the minionsKilled to set
     */
    public void setMinionsKilled(final Long minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    /**
     * @param neutralMinionsKilled
     *            the neutralMinionsKilled to set
     */
    public void setNeutralMinionsKilled(final Long neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }

    /**
     * @param neutralMinionsKilledEnemyJungle
     *            the neutralMinionsKilledEnemyJungle to set
     */
    public void setNeutralMinionsKilledEnemyJungle(final Long neutralMinionsKilledEnemyJungle) {
        this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
    }

    /**
     * @param neutralMinionsKilledTeamJungle
     *            the neutralMinionsKilledTeamJungle to set
     */
    public void setNeutralMinionsKilledTeamJungle(final Long neutralMinionsKilledTeamJungle) {
        this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
    }

    /**
     * @param nodeCapture
     *            the nodeCapture to set
     */
    public void setNodeCapture(final Long nodeCapture) {
        this.nodeCapture = nodeCapture;
    }

    /**
     * @param nodeCaptureAssist
     *            the nodeCaptureAssist to set
     */
    public void setNodeCaptureAssist(final Long nodeCaptureAssist) {
        this.nodeCaptureAssist = nodeCaptureAssist;
    }

    /**
     * @param nodeNeutralize
     *            the nodeNeutralize to set
     */
    public void setNodeNeutralize(final Long nodeNeutralize) {
        this.nodeNeutralize = nodeNeutralize;
    }

    /**
     * @param nodeNeutralizeAssist
     *            the nodeNeutralizeAssist to set
     */
    public void setNodeNeutralizeAssist(final Long nodeNeutralizeAssist) {
        this.nodeNeutralizeAssist = nodeNeutralizeAssist;
    }

    /**
     * @param objectivePlayerScore
     *            the objectivePlayerScore to set
     */
    public void setObjectivePlayerScore(final Long objectivePlayerScore) {
        this.objectivePlayerScore = objectivePlayerScore;
    }

    /**
     * @param pentaKills
     *            the pentaKills to set
     */
    public void setPentaKills(final Long pentaKills) {
        this.pentaKills = pentaKills;
    }

    /**
     * @param physicalDamageDealt
     *            the physicalDamageDealt to set
     */
    public void setPhysicalDamageDealt(final Long physicalDamageDealt) {
        this.physicalDamageDealt = physicalDamageDealt;
    }

    /**
     * @param physicalDamageDealtToChampions
     *            the physicalDamageDealtToChampions to set
     */
    public void setPhysicalDamageDealtToChampions(final Long physicalDamageDealtToChampions) {
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
    }

    /**
     * @param physicalDamageTaken
     *            the physicalDamageTaken to set
     */
    public void setPhysicalDamageTaken(final Long physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }

    /**
     * @param quadraKills
     *            the quadraKills to set
     */
    public void setQuadraKills(final Long quadraKills) {
        this.quadraKills = quadraKills;
    }

    /**
     * @param sightWardsBoughtInGame
     *            the sightWardsBoughtInGame to set
     */
    public void setSightWardsBoughtInGame(final Long sightWardsBoughtInGame) {
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
    }

    /**
     * @param teamObjective
     *            the teamObjective to set
     */
    public void setTeamObjective(final Long teamObjective) {
        this.teamObjective = teamObjective;
    }

    /**
     * @param totalDamageDealt
     *            the totalDamageDealt to set
     */
    public void setTotalDamageDealt(final Long totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    /**
     * @param totalDamageDealtToChampions
     *            the totalDamageDealtToChampions to set
     */
    public void setTotalDamageDealtToChampions(final Long totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }

    /**
     * @param totalDamageTaken
     *            the totalDamageTaken to set
     */
    public void setTotalDamageTaken(final Long totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    /**
     * @param totalHeal
     *            the totalHeal to set
     */
    public void setTotalHeal(final Long totalHeal) {
        this.totalHeal = totalHeal;
    }

    /**
     * @param totalPlayerScore
     *            the totalPlayerScore to set
     */
    public void setTotalPlayerScore(final Long totalPlayerScore) {
        this.totalPlayerScore = totalPlayerScore;
    }

    /**
     * @param totalScoreRank
     *            the totalScoreRank to set
     */
    public void setTotalScoreRank(final Long totalScoreRank) {
        this.totalScoreRank = totalScoreRank;
    }

    /**
     * @param totalTimeCrowdControlDealt
     *            the totalTimeCrowdControlDealt to set
     */
    public void setTotalTimeCrowdControlDealt(final Long totalTimeCrowdControlDealt) {
        this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
    }

    /**
     * @param totalUnitsHealed
     *            the totalUnitsHealed to set
     */
    public void setTotalUnitsHealed(final Long totalUnitsHealed) {
        this.totalUnitsHealed = totalUnitsHealed;
    }

    /**
     * @param towerKills
     *            the towerKills to set
     */
    public void setTowerKills(final Long towerKills) {
        this.towerKills = towerKills;
    }

    /**
     * @param tripleKills
     *            the tripleKills to set
     */
    public void setTripleKills(final Long tripleKills) {
        this.tripleKills = tripleKills;
    }

    /**
     * @param trueDamageDealt
     *            the trueDamageDealt to set
     */
    public void setTrueDamageDealt(final Long trueDamageDealt) {
        this.trueDamageDealt = trueDamageDealt;
    }

    /**
     * @param trueDamageDealtToChampions
     *            the trueDamageDealtToChampions to set
     */
    public void setTrueDamageDealtToChampions(final Long trueDamageDealtToChampions) {
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
    }

    /**
     * @param trueDamageTaken
     *            the trueDamageTaken to set
     */
    public void setTrueDamageTaken(final Long trueDamageTaken) {
        this.trueDamageTaken = trueDamageTaken;
    }

    /**
     * @param unrealKills
     *            the unrealKills to set
     */
    public void setUnrealKills(final Long unrealKills) {
        this.unrealKills = unrealKills;
    }

    /**
     * @param visionWardsBoughtInGame
     *            the visionWardsBoughtInGame to set
     */
    public void setVisionWardsBoughtInGame(final Long visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    /**
     * @param wardsKilled
     *            the wardsKilled to set
     */
    public void setWardsKilled(final Long wardsKilled) {
        this.wardsKilled = wardsKilled;
    }

    /**
     * @param wardsPlaced
     *            the wardsPlaced to set
     */
    public void setWardsPlaced(final Long wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }

    /**
     * @param winner
     *            the winner to set
     */
    public void setWinner(final Boolean winner) {
        this.winner = winner;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantStats [assists=" + assists + ", champLevel=" + champLevel + ", combatPlayerScore=" + combatPlayerScore + ", deaths=" + deaths
                + ", doubleKills=" + doubleKills + ", goldEarned=" + goldEarned + ", goldSpent=" + goldSpent + ", inhibitorKills=" + inhibitorKills + ", item0="
                + item0 + ", item1=" + item1 + ", item2=" + item2 + ", item3=" + item3 + ", item4=" + item4 + ", item5=" + item5 + ", item6=" + item6
                + ", killingSprees=" + killingSprees + ", kills=" + kills + ", largestCriticalStrike=" + largestCriticalStrike + ", largestKillingSpree="
                + largestKillingSpree + ", largestMultiKill=" + largestMultiKill + ", magicDamageDealt=" + magicDamageDealt + ", magicDamageDealtToChampions="
                + magicDamageDealtToChampions + ", magicDamageTaken=" + magicDamageTaken + ", minionsKilled=" + minionsKilled + ", neutralMinionsKilled="
                + neutralMinionsKilled + ", neutralMinionsKilledEnemyJungle=" + neutralMinionsKilledEnemyJungle + ", neutralMinionsKilledTeamJungle="
                + neutralMinionsKilledTeamJungle + ", nodeCapture=" + nodeCapture + ", nodeCaptureAssist=" + nodeCaptureAssist + ", nodeNeutralize="
                + nodeNeutralize + ", nodeNeutralizeAssist=" + nodeNeutralizeAssist + ", objectivePlayerScore=" + objectivePlayerScore + ", pentaKills="
                + pentaKills + ", physicalDamageDealt=" + physicalDamageDealt + ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions
                + ", physicalDamageTaken=" + physicalDamageTaken + ", quadraKills=" + quadraKills + ", sightWardsBoughtInGame=" + sightWardsBoughtInGame
                + ", teamObjective=" + teamObjective + ", totalDamageDealt=" + totalDamageDealt + ", totalDamageDealtToChampions=" + totalDamageDealtToChampions
                + ", totalDamageTaken=" + totalDamageTaken + ", totalHeal=" + totalHeal + ", totalPlayerScore=" + totalPlayerScore + ", totalScoreRank="
                + totalScoreRank + ", totalTimeCrowdControlDealt=" + totalTimeCrowdControlDealt + ", totalUnitsHealed=" + totalUnitsHealed + ", towerKills="
                + towerKills + ", tripleKills=" + tripleKills + ", trueDamageDealt=" + trueDamageDealt + ", trueDamageDealtToChampions="
                + trueDamageDealtToChampions + ", trueDamageTaken=" + trueDamageTaken + ", unrealKills=" + unrealKills + ", visionWardsBoughtInGame="
                + visionWardsBoughtInGame + ", wardsKilled=" + wardsKilled + ", wardsPlaced=" + wardsPlaced + ", winner=" + winner + ", firstBloodAssist="
                + firstBloodAssist + ", firstBloodKill=" + firstBloodKill + ", firstInhibitorKill=" + firstInhibitorKill + ", firstInhibitorAssist="
                + firstInhibitorAssist + ", firstTowerAssist=" + firstTowerAssist + ", firstTowerKill=" + firstTowerKill + "]";
    }
}