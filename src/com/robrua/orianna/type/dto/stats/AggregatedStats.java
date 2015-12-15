package com.robrua.orianna.type.dto.stats;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "aggregatedstats")
public class AggregatedStats extends OriannaDto {
    private static final long serialVersionUID = 4301211932958625756L;
    private Integer averageAssists, averageChampionsKilled, averageCombatPlayerScore, averageNodeCapture, averageNodeCaptureAssist, averageNodeNeutralize,
            averageNodeNeutralizeAssist, averageNumDeaths, averageObjectivePlayerScore, averageTeamObjective, averageTotalPlayerScore, botGamesPlayed,
            killingSpree, maxAssists, maxChampionsKilled, maxCombatPlayerScore, maxLargestCriticalStrike, maxLargestKillingSpree, maxNodeCapture,
            maxNodeCaptureAssist, maxNodeNeutralize, maxNodeNeutralizeAssist, maxNumDeaths, maxObjectivePlayerScore, maxTeamObjective, maxTimePlayed,
            maxTimeSpentLiving, maxTotalPlayerScore, mostChampionKillsPerSession, mostSpellsCast, normalGamesPlayed, rankedPremadeGamesPlayed,
            rankedSoloGamesPlayed, totalAssists, totalChampionKills, totalDamageDealt, totalDamageTaken, totalDeathsPerSession, totalDoubleKills,
            totalFirstBlood, totalGoldEarned, totalHeal, totalMagicDamageDealt, totalMinionKills, totalNeutralMinionsKilled, totalNodeCapture,
            totalNodeNeutralize, totalPentaKills, totalPhysicalDamageDealt, totalQuadraKills, totalSessionsLost, totalSessionsPlayed, totalSessionsWon,
            totalTripleKills, totalTurretsKilled, totalUnrealKills;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

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
        if(!(obj instanceof AggregatedStats)) {
            return false;
        }
        final AggregatedStats other = (AggregatedStats)obj;
        if(averageAssists == null) {
            if(other.averageAssists != null) {
                return false;
            }
        }
        else if(!averageAssists.equals(other.averageAssists)) {
            return false;
        }
        if(averageChampionsKilled == null) {
            if(other.averageChampionsKilled != null) {
                return false;
            }
        }
        else if(!averageChampionsKilled.equals(other.averageChampionsKilled)) {
            return false;
        }
        if(averageCombatPlayerScore == null) {
            if(other.averageCombatPlayerScore != null) {
                return false;
            }
        }
        else if(!averageCombatPlayerScore.equals(other.averageCombatPlayerScore)) {
            return false;
        }
        if(averageNodeCapture == null) {
            if(other.averageNodeCapture != null) {
                return false;
            }
        }
        else if(!averageNodeCapture.equals(other.averageNodeCapture)) {
            return false;
        }
        if(averageNodeCaptureAssist == null) {
            if(other.averageNodeCaptureAssist != null) {
                return false;
            }
        }
        else if(!averageNodeCaptureAssist.equals(other.averageNodeCaptureAssist)) {
            return false;
        }
        if(averageNodeNeutralize == null) {
            if(other.averageNodeNeutralize != null) {
                return false;
            }
        }
        else if(!averageNodeNeutralize.equals(other.averageNodeNeutralize)) {
            return false;
        }
        if(averageNodeNeutralizeAssist == null) {
            if(other.averageNodeNeutralizeAssist != null) {
                return false;
            }
        }
        else if(!averageNodeNeutralizeAssist.equals(other.averageNodeNeutralizeAssist)) {
            return false;
        }
        if(averageNumDeaths == null) {
            if(other.averageNumDeaths != null) {
                return false;
            }
        }
        else if(!averageNumDeaths.equals(other.averageNumDeaths)) {
            return false;
        }
        if(averageObjectivePlayerScore == null) {
            if(other.averageObjectivePlayerScore != null) {
                return false;
            }
        }
        else if(!averageObjectivePlayerScore.equals(other.averageObjectivePlayerScore)) {
            return false;
        }
        if(averageTeamObjective == null) {
            if(other.averageTeamObjective != null) {
                return false;
            }
        }
        else if(!averageTeamObjective.equals(other.averageTeamObjective)) {
            return false;
        }
        if(averageTotalPlayerScore == null) {
            if(other.averageTotalPlayerScore != null) {
                return false;
            }
        }
        else if(!averageTotalPlayerScore.equals(other.averageTotalPlayerScore)) {
            return false;
        }
        if(botGamesPlayed == null) {
            if(other.botGamesPlayed != null) {
                return false;
            }
        }
        else if(!botGamesPlayed.equals(other.botGamesPlayed)) {
            return false;
        }
        if(killingSpree == null) {
            if(other.killingSpree != null) {
                return false;
            }
        }
        else if(!killingSpree.equals(other.killingSpree)) {
            return false;
        }
        if(maxAssists == null) {
            if(other.maxAssists != null) {
                return false;
            }
        }
        else if(!maxAssists.equals(other.maxAssists)) {
            return false;
        }
        if(maxChampionsKilled == null) {
            if(other.maxChampionsKilled != null) {
                return false;
            }
        }
        else if(!maxChampionsKilled.equals(other.maxChampionsKilled)) {
            return false;
        }
        if(maxCombatPlayerScore == null) {
            if(other.maxCombatPlayerScore != null) {
                return false;
            }
        }
        else if(!maxCombatPlayerScore.equals(other.maxCombatPlayerScore)) {
            return false;
        }
        if(maxLargestCriticalStrike == null) {
            if(other.maxLargestCriticalStrike != null) {
                return false;
            }
        }
        else if(!maxLargestCriticalStrike.equals(other.maxLargestCriticalStrike)) {
            return false;
        }
        if(maxLargestKillingSpree == null) {
            if(other.maxLargestKillingSpree != null) {
                return false;
            }
        }
        else if(!maxLargestKillingSpree.equals(other.maxLargestKillingSpree)) {
            return false;
        }
        if(maxNodeCapture == null) {
            if(other.maxNodeCapture != null) {
                return false;
            }
        }
        else if(!maxNodeCapture.equals(other.maxNodeCapture)) {
            return false;
        }
        if(maxNodeCaptureAssist == null) {
            if(other.maxNodeCaptureAssist != null) {
                return false;
            }
        }
        else if(!maxNodeCaptureAssist.equals(other.maxNodeCaptureAssist)) {
            return false;
        }
        if(maxNodeNeutralize == null) {
            if(other.maxNodeNeutralize != null) {
                return false;
            }
        }
        else if(!maxNodeNeutralize.equals(other.maxNodeNeutralize)) {
            return false;
        }
        if(maxNodeNeutralizeAssist == null) {
            if(other.maxNodeNeutralizeAssist != null) {
                return false;
            }
        }
        else if(!maxNodeNeutralizeAssist.equals(other.maxNodeNeutralizeAssist)) {
            return false;
        }
        if(maxNumDeaths == null) {
            if(other.maxNumDeaths != null) {
                return false;
            }
        }
        else if(!maxNumDeaths.equals(other.maxNumDeaths)) {
            return false;
        }
        if(maxObjectivePlayerScore == null) {
            if(other.maxObjectivePlayerScore != null) {
                return false;
            }
        }
        else if(!maxObjectivePlayerScore.equals(other.maxObjectivePlayerScore)) {
            return false;
        }
        if(maxTeamObjective == null) {
            if(other.maxTeamObjective != null) {
                return false;
            }
        }
        else if(!maxTeamObjective.equals(other.maxTeamObjective)) {
            return false;
        }
        if(maxTimePlayed == null) {
            if(other.maxTimePlayed != null) {
                return false;
            }
        }
        else if(!maxTimePlayed.equals(other.maxTimePlayed)) {
            return false;
        }
        if(maxTimeSpentLiving == null) {
            if(other.maxTimeSpentLiving != null) {
                return false;
            }
        }
        else if(!maxTimeSpentLiving.equals(other.maxTimeSpentLiving)) {
            return false;
        }
        if(maxTotalPlayerScore == null) {
            if(other.maxTotalPlayerScore != null) {
                return false;
            }
        }
        else if(!maxTotalPlayerScore.equals(other.maxTotalPlayerScore)) {
            return false;
        }
        if(mostChampionKillsPerSession == null) {
            if(other.mostChampionKillsPerSession != null) {
                return false;
            }
        }
        else if(!mostChampionKillsPerSession.equals(other.mostChampionKillsPerSession)) {
            return false;
        }
        if(mostSpellsCast == null) {
            if(other.mostSpellsCast != null) {
                return false;
            }
        }
        else if(!mostSpellsCast.equals(other.mostSpellsCast)) {
            return false;
        }
        if(normalGamesPlayed == null) {
            if(other.normalGamesPlayed != null) {
                return false;
            }
        }
        else if(!normalGamesPlayed.equals(other.normalGamesPlayed)) {
            return false;
        }
        if(rankedPremadeGamesPlayed == null) {
            if(other.rankedPremadeGamesPlayed != null) {
                return false;
            }
        }
        else if(!rankedPremadeGamesPlayed.equals(other.rankedPremadeGamesPlayed)) {
            return false;
        }
        if(rankedSoloGamesPlayed == null) {
            if(other.rankedSoloGamesPlayed != null) {
                return false;
            }
        }
        else if(!rankedSoloGamesPlayed.equals(other.rankedSoloGamesPlayed)) {
            return false;
        }
        if(totalAssists == null) {
            if(other.totalAssists != null) {
                return false;
            }
        }
        else if(!totalAssists.equals(other.totalAssists)) {
            return false;
        }
        if(totalChampionKills == null) {
            if(other.totalChampionKills != null) {
                return false;
            }
        }
        else if(!totalChampionKills.equals(other.totalChampionKills)) {
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
        if(totalDamageTaken == null) {
            if(other.totalDamageTaken != null) {
                return false;
            }
        }
        else if(!totalDamageTaken.equals(other.totalDamageTaken)) {
            return false;
        }
        if(totalDeathsPerSession == null) {
            if(other.totalDeathsPerSession != null) {
                return false;
            }
        }
        else if(!totalDeathsPerSession.equals(other.totalDeathsPerSession)) {
            return false;
        }
        if(totalDoubleKills == null) {
            if(other.totalDoubleKills != null) {
                return false;
            }
        }
        else if(!totalDoubleKills.equals(other.totalDoubleKills)) {
            return false;
        }
        if(totalFirstBlood == null) {
            if(other.totalFirstBlood != null) {
                return false;
            }
        }
        else if(!totalFirstBlood.equals(other.totalFirstBlood)) {
            return false;
        }
        if(totalGoldEarned == null) {
            if(other.totalGoldEarned != null) {
                return false;
            }
        }
        else if(!totalGoldEarned.equals(other.totalGoldEarned)) {
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
        if(totalMagicDamageDealt == null) {
            if(other.totalMagicDamageDealt != null) {
                return false;
            }
        }
        else if(!totalMagicDamageDealt.equals(other.totalMagicDamageDealt)) {
            return false;
        }
        if(totalMinionKills == null) {
            if(other.totalMinionKills != null) {
                return false;
            }
        }
        else if(!totalMinionKills.equals(other.totalMinionKills)) {
            return false;
        }
        if(totalNeutralMinionsKilled == null) {
            if(other.totalNeutralMinionsKilled != null) {
                return false;
            }
        }
        else if(!totalNeutralMinionsKilled.equals(other.totalNeutralMinionsKilled)) {
            return false;
        }
        if(totalNodeCapture == null) {
            if(other.totalNodeCapture != null) {
                return false;
            }
        }
        else if(!totalNodeCapture.equals(other.totalNodeCapture)) {
            return false;
        }
        if(totalNodeNeutralize == null) {
            if(other.totalNodeNeutralize != null) {
                return false;
            }
        }
        else if(!totalNodeNeutralize.equals(other.totalNodeNeutralize)) {
            return false;
        }
        if(totalPentaKills == null) {
            if(other.totalPentaKills != null) {
                return false;
            }
        }
        else if(!totalPentaKills.equals(other.totalPentaKills)) {
            return false;
        }
        if(totalPhysicalDamageDealt == null) {
            if(other.totalPhysicalDamageDealt != null) {
                return false;
            }
        }
        else if(!totalPhysicalDamageDealt.equals(other.totalPhysicalDamageDealt)) {
            return false;
        }
        if(totalQuadraKills == null) {
            if(other.totalQuadraKills != null) {
                return false;
            }
        }
        else if(!totalQuadraKills.equals(other.totalQuadraKills)) {
            return false;
        }
        if(totalSessionsLost == null) {
            if(other.totalSessionsLost != null) {
                return false;
            }
        }
        else if(!totalSessionsLost.equals(other.totalSessionsLost)) {
            return false;
        }
        if(totalSessionsPlayed == null) {
            if(other.totalSessionsPlayed != null) {
                return false;
            }
        }
        else if(!totalSessionsPlayed.equals(other.totalSessionsPlayed)) {
            return false;
        }
        if(totalSessionsWon == null) {
            if(other.totalSessionsWon != null) {
                return false;
            }
        }
        else if(!totalSessionsWon.equals(other.totalSessionsWon)) {
            return false;
        }
        if(totalTripleKills == null) {
            if(other.totalTripleKills != null) {
                return false;
            }
        }
        else if(!totalTripleKills.equals(other.totalTripleKills)) {
            return false;
        }
        if(totalTurretsKilled == null) {
            if(other.totalTurretsKilled != null) {
                return false;
            }
        }
        else if(!totalTurretsKilled.equals(other.totalTurretsKilled)) {
            return false;
        }
        if(totalUnrealKills == null) {
            if(other.totalUnrealKills != null) {
                return false;
            }
        }
        else if(!totalUnrealKills.equals(other.totalUnrealKills)) {
            return false;
        }
        return true;
    }

    /**
     * @return the averageAssists
     */
    public Integer getAverageAssists() {
        return averageAssists;
    }

    /**
     * @return the averageChampionsKilled
     */
    public Integer getAverageChampionsKilled() {
        return averageChampionsKilled;
    }

    /**
     * @return the averageCombatPlayerScore
     */
    public Integer getAverageCombatPlayerScore() {
        return averageCombatPlayerScore;
    }

    /**
     * @return the averageNodeCapture
     */
    public Integer getAverageNodeCapture() {
        return averageNodeCapture;
    }

    /**
     * @return the averageNodeCaptureAssist
     */
    public Integer getAverageNodeCaptureAssist() {
        return averageNodeCaptureAssist;
    }

    /**
     * @return the averageNodeNeutralize
     */
    public Integer getAverageNodeNeutralize() {
        return averageNodeNeutralize;
    }

    /**
     * @return the averageNodeNeutralizeAssist
     */
    public Integer getAverageNodeNeutralizeAssist() {
        return averageNodeNeutralizeAssist;
    }

    /**
     * @return the averageNumDeaths
     */
    public Integer getAverageNumDeaths() {
        return averageNumDeaths;
    }

    /**
     * @return the averageObjectivePlayerScore
     */
    public Integer getAverageObjectivePlayerScore() {
        return averageObjectivePlayerScore;
    }

    /**
     * @return the averageTeamObjective
     */
    public Integer getAverageTeamObjective() {
        return averageTeamObjective;
    }

    /**
     * @return the averageTotalPlayerScore
     */
    public Integer getAverageTotalPlayerScore() {
        return averageTotalPlayerScore;
    }

    /**
     * @return the botGamesPlayed
     */
    public Integer getBotGamesPlayed() {
        return botGamesPlayed;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the killingSpree
     */
    public Integer getKillingSpree() {
        return killingSpree;
    }

    /**
     * @return the maxAssists
     */
    public Integer getMaxAssists() {
        return maxAssists;
    }

    /**
     * @return the maxChampionsKilled
     */
    public Integer getMaxChampionsKilled() {
        return maxChampionsKilled;
    }

    /**
     * @return the maxCombatPlayerScore
     */
    public Integer getMaxCombatPlayerScore() {
        return maxCombatPlayerScore;
    }

    /**
     * @return the maxLargestCriticalStrike
     */
    public Integer getMaxLargestCriticalStrike() {
        return maxLargestCriticalStrike;
    }

    /**
     * @return the maxLargestKillingSpree
     */
    public Integer getMaxLargestKillingSpree() {
        return maxLargestKillingSpree;
    }

    /**
     * @return the maxNodeCapture
     */
    public Integer getMaxNodeCapture() {
        return maxNodeCapture;
    }

    /**
     * @return the maxNodeCaptureAssist
     */
    public Integer getMaxNodeCaptureAssist() {
        return maxNodeCaptureAssist;
    }

    /**
     * @return the maxNodeNeutralize
     */
    public Integer getMaxNodeNeutralize() {
        return maxNodeNeutralize;
    }

    /**
     * @return the maxNodeNeutralizeAssist
     */
    public Integer getMaxNodeNeutralizeAssist() {
        return maxNodeNeutralizeAssist;
    }

    /**
     * @return the maxNumDeaths
     */
    public Integer getMaxNumDeaths() {
        return maxNumDeaths;
    }

    /**
     * @return the maxObjectivePlayerScore
     */
    public Integer getMaxObjectivePlayerScore() {
        return maxObjectivePlayerScore;
    }

    /**
     * @return the maxTeamObjective
     */
    public Integer getMaxTeamObjective() {
        return maxTeamObjective;
    }

    /**
     * @return the maxTimePlayed
     */
    public Integer getMaxTimePlayed() {
        return maxTimePlayed;
    }

    /**
     * @return the maxTimeSpentLiving
     */
    public Integer getMaxTimeSpentLiving() {
        return maxTimeSpentLiving;
    }

    /**
     * @return the maxTotalPlayerScore
     */
    public Integer getMaxTotalPlayerScore() {
        return maxTotalPlayerScore;
    }

    /**
     * @return the mostChampionKillsPerSession
     */
    public Integer getMostChampionKillsPerSession() {
        return mostChampionKillsPerSession;
    }

    /**
     * @return the mostSpellsCast
     */
    public Integer getMostSpellsCast() {
        return mostSpellsCast;
    }

    /**
     * @return the normalGamesPlayed
     */
    public Integer getNormalGamesPlayed() {
        return normalGamesPlayed;
    }

    /**
     * @return the rankedPremadeGamesPlayed
     */
    public Integer getRankedPremadeGamesPlayed() {
        return rankedPremadeGamesPlayed;
    }

    /**
     * @return the rankedSoloGamesPlayed
     */
    public Integer getRankedSoloGamesPlayed() {
        return rankedSoloGamesPlayed;
    }

    /**
     * @return the totalAssists
     */
    public Integer getTotalAssists() {
        return totalAssists;
    }

    /**
     * @return the totalChampionKills
     */
    public Integer getTotalChampionKills() {
        return totalChampionKills;
    }

    /**
     * @return the totalDamageDealt
     */
    public Integer getTotalDamageDealt() {
        return totalDamageDealt;
    }

    /**
     * @return the totalDamageTaken
     */
    public Integer getTotalDamageTaken() {
        return totalDamageTaken;
    }

    /**
     * @return the totalDeathsPerSession
     */
    public Integer getTotalDeathsPerSession() {
        return totalDeathsPerSession;
    }

    /**
     * @return the totalDoubleKills
     */
    public Integer getTotalDoubleKills() {
        return totalDoubleKills;
    }

    /**
     * @return the totalFirstBlood
     */
    public Integer getTotalFirstBlood() {
        return totalFirstBlood;
    }

    /**
     * @return the totalGoldEarned
     */
    public Integer getTotalGoldEarned() {
        return totalGoldEarned;
    }

    /**
     * @return the totalHeal
     */
    public Integer getTotalHeal() {
        return totalHeal;
    }

    /**
     * @return the totalMagicDamageDealt
     */
    public Integer getTotalMagicDamageDealt() {
        return totalMagicDamageDealt;
    }

    /**
     * @return the totalMinionKills
     */
    public Integer getTotalMinionKills() {
        return totalMinionKills;
    }

    /**
     * @return the totalNeutralMinionsKilled
     */
    public Integer getTotalNeutralMinionsKilled() {
        return totalNeutralMinionsKilled;
    }

    /**
     * @return the totalNodeCapture
     */
    public Integer getTotalNodeCapture() {
        return totalNodeCapture;
    }

    /**
     * @return the totalNodeNeutralize
     */
    public Integer getTotalNodeNeutralize() {
        return totalNodeNeutralize;
    }

    /**
     * @return the totalPentaKills
     */
    public Integer getTotalPentaKills() {
        return totalPentaKills;
    }

    /**
     * @return the totalPhysicalDamageDealt
     */
    public Integer getTotalPhysicalDamageDealt() {
        return totalPhysicalDamageDealt;
    }

    /**
     * @return the totalQuadraKills
     */
    public Integer getTotalQuadraKills() {
        return totalQuadraKills;
    }

    /**
     * @return the totalSessionsLost
     */
    public Integer getTotalSessionsLost() {
        return totalSessionsLost;
    }

    /**
     * @return the totalSessionsPlayed
     */
    public Integer getTotalSessionsPlayed() {
        return totalSessionsPlayed;
    }

    /**
     * @return the totalSessionsWon
     */
    public Integer getTotalSessionsWon() {
        return totalSessionsWon;
    }

    /**
     * @return the totalTripleKills
     */
    public Integer getTotalTripleKills() {
        return totalTripleKills;
    }

    /**
     * @return the totalTurretsKilled
     */
    public Integer getTotalTurretsKilled() {
        return totalTurretsKilled;
    }

    /**
     * @return the totalUnrealKills
     */
    public Integer getTotalUnrealKills() {
        return totalUnrealKills;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (averageAssists == null ? 0 : averageAssists.hashCode());
        result = prime * result + (averageChampionsKilled == null ? 0 : averageChampionsKilled.hashCode());
        result = prime * result + (averageCombatPlayerScore == null ? 0 : averageCombatPlayerScore.hashCode());
        result = prime * result + (averageNodeCapture == null ? 0 : averageNodeCapture.hashCode());
        result = prime * result + (averageNodeCaptureAssist == null ? 0 : averageNodeCaptureAssist.hashCode());
        result = prime * result + (averageNodeNeutralize == null ? 0 : averageNodeNeutralize.hashCode());
        result = prime * result + (averageNodeNeutralizeAssist == null ? 0 : averageNodeNeutralizeAssist.hashCode());
        result = prime * result + (averageNumDeaths == null ? 0 : averageNumDeaths.hashCode());
        result = prime * result + (averageObjectivePlayerScore == null ? 0 : averageObjectivePlayerScore.hashCode());
        result = prime * result + (averageTeamObjective == null ? 0 : averageTeamObjective.hashCode());
        result = prime * result + (averageTotalPlayerScore == null ? 0 : averageTotalPlayerScore.hashCode());
        result = prime * result + (botGamesPlayed == null ? 0 : botGamesPlayed.hashCode());
        result = prime * result + (killingSpree == null ? 0 : killingSpree.hashCode());
        result = prime * result + (maxAssists == null ? 0 : maxAssists.hashCode());
        result = prime * result + (maxChampionsKilled == null ? 0 : maxChampionsKilled.hashCode());
        result = prime * result + (maxCombatPlayerScore == null ? 0 : maxCombatPlayerScore.hashCode());
        result = prime * result + (maxLargestCriticalStrike == null ? 0 : maxLargestCriticalStrike.hashCode());
        result = prime * result + (maxLargestKillingSpree == null ? 0 : maxLargestKillingSpree.hashCode());
        result = prime * result + (maxNodeCapture == null ? 0 : maxNodeCapture.hashCode());
        result = prime * result + (maxNodeCaptureAssist == null ? 0 : maxNodeCaptureAssist.hashCode());
        result = prime * result + (maxNodeNeutralize == null ? 0 : maxNodeNeutralize.hashCode());
        result = prime * result + (maxNodeNeutralizeAssist == null ? 0 : maxNodeNeutralizeAssist.hashCode());
        result = prime * result + (maxNumDeaths == null ? 0 : maxNumDeaths.hashCode());
        result = prime * result + (maxObjectivePlayerScore == null ? 0 : maxObjectivePlayerScore.hashCode());
        result = prime * result + (maxTeamObjective == null ? 0 : maxTeamObjective.hashCode());
        result = prime * result + (maxTimePlayed == null ? 0 : maxTimePlayed.hashCode());
        result = prime * result + (maxTimeSpentLiving == null ? 0 : maxTimeSpentLiving.hashCode());
        result = prime * result + (maxTotalPlayerScore == null ? 0 : maxTotalPlayerScore.hashCode());
        result = prime * result + (mostChampionKillsPerSession == null ? 0 : mostChampionKillsPerSession.hashCode());
        result = prime * result + (mostSpellsCast == null ? 0 : mostSpellsCast.hashCode());
        result = prime * result + (normalGamesPlayed == null ? 0 : normalGamesPlayed.hashCode());
        result = prime * result + (rankedPremadeGamesPlayed == null ? 0 : rankedPremadeGamesPlayed.hashCode());
        result = prime * result + (rankedSoloGamesPlayed == null ? 0 : rankedSoloGamesPlayed.hashCode());
        result = prime * result + (totalAssists == null ? 0 : totalAssists.hashCode());
        result = prime * result + (totalChampionKills == null ? 0 : totalChampionKills.hashCode());
        result = prime * result + (totalDamageDealt == null ? 0 : totalDamageDealt.hashCode());
        result = prime * result + (totalDamageTaken == null ? 0 : totalDamageTaken.hashCode());
        result = prime * result + (totalDeathsPerSession == null ? 0 : totalDeathsPerSession.hashCode());
        result = prime * result + (totalDoubleKills == null ? 0 : totalDoubleKills.hashCode());
        result = prime * result + (totalFirstBlood == null ? 0 : totalFirstBlood.hashCode());
        result = prime * result + (totalGoldEarned == null ? 0 : totalGoldEarned.hashCode());
        result = prime * result + (totalHeal == null ? 0 : totalHeal.hashCode());
        result = prime * result + (totalMagicDamageDealt == null ? 0 : totalMagicDamageDealt.hashCode());
        result = prime * result + (totalMinionKills == null ? 0 : totalMinionKills.hashCode());
        result = prime * result + (totalNeutralMinionsKilled == null ? 0 : totalNeutralMinionsKilled.hashCode());
        result = prime * result + (totalNodeCapture == null ? 0 : totalNodeCapture.hashCode());
        result = prime * result + (totalNodeNeutralize == null ? 0 : totalNodeNeutralize.hashCode());
        result = prime * result + (totalPentaKills == null ? 0 : totalPentaKills.hashCode());
        result = prime * result + (totalPhysicalDamageDealt == null ? 0 : totalPhysicalDamageDealt.hashCode());
        result = prime * result + (totalQuadraKills == null ? 0 : totalQuadraKills.hashCode());
        result = prime * result + (totalSessionsLost == null ? 0 : totalSessionsLost.hashCode());
        result = prime * result + (totalSessionsPlayed == null ? 0 : totalSessionsPlayed.hashCode());
        result = prime * result + (totalSessionsWon == null ? 0 : totalSessionsWon.hashCode());
        result = prime * result + (totalTripleKills == null ? 0 : totalTripleKills.hashCode());
        result = prime * result + (totalTurretsKilled == null ? 0 : totalTurretsKilled.hashCode());
        result = prime * result + (totalUnrealKills == null ? 0 : totalUnrealKills.hashCode());
        return result;
    }

    /**
     * @param averageAssists
     *            the averageAssists to set
     */
    public void setAverageAssists(final Integer averageAssists) {
        this.averageAssists = averageAssists;
    }

    /**
     * @param averageChampionsKilled
     *            the averageChampionsKilled to set
     */
    public void setAverageChampionsKilled(final Integer averageChampionsKilled) {
        this.averageChampionsKilled = averageChampionsKilled;
    }

    /**
     * @param averageCombatPlayerScore
     *            the averageCombatPlayerScore to set
     */
    public void setAverageCombatPlayerScore(final Integer averageCombatPlayerScore) {
        this.averageCombatPlayerScore = averageCombatPlayerScore;
    }

    /**
     * @param averageNodeCapture
     *            the averageNodeCapture to set
     */
    public void setAverageNodeCapture(final Integer averageNodeCapture) {
        this.averageNodeCapture = averageNodeCapture;
    }

    /**
     * @param averageNodeCaptureAssist
     *            the averageNodeCaptureAssist to set
     */
    public void setAverageNodeCaptureAssist(final Integer averageNodeCaptureAssist) {
        this.averageNodeCaptureAssist = averageNodeCaptureAssist;
    }

    /**
     * @param averageNodeNeutralize
     *            the averageNodeNeutralize to set
     */
    public void setAverageNodeNeutralize(final Integer averageNodeNeutralize) {
        this.averageNodeNeutralize = averageNodeNeutralize;
    }

    /**
     * @param averageNodeNeutralizeAssist
     *            the averageNodeNeutralizeAssist to set
     */
    public void setAverageNodeNeutralizeAssist(final Integer averageNodeNeutralizeAssist) {
        this.averageNodeNeutralizeAssist = averageNodeNeutralizeAssist;
    }

    /**
     * @param averageNumDeaths
     *            the averageNumDeaths to set
     */
    public void setAverageNumDeaths(final Integer averageNumDeaths) {
        this.averageNumDeaths = averageNumDeaths;
    }

    /**
     * @param averageObjectivePlayerScore
     *            the averageObjectivePlayerScore to set
     */
    public void setAverageObjectivePlayerScore(final Integer averageObjectivePlayerScore) {
        this.averageObjectivePlayerScore = averageObjectivePlayerScore;
    }

    /**
     * @param averageTeamObjective
     *            the averageTeamObjective to set
     */
    public void setAverageTeamObjective(final Integer averageTeamObjective) {
        this.averageTeamObjective = averageTeamObjective;
    }

    /**
     * @param averageTotalPlayerScore
     *            the averageTotalPlayerScore to set
     */
    public void setAverageTotalPlayerScore(final Integer averageTotalPlayerScore) {
        this.averageTotalPlayerScore = averageTotalPlayerScore;
    }

    /**
     * @param botGamesPlayed
     *            the botGamesPlayed to set
     */
    public void setBotGamesPlayed(final Integer botGamesPlayed) {
        this.botGamesPlayed = botGamesPlayed;
    }

    /**
     * @param killingSpree
     *            the killingSpree to set
     */
    public void setKillingSpree(final Integer killingSpree) {
        this.killingSpree = killingSpree;
    }

    /**
     * @param maxAssists
     *            the maxAssists to set
     */
    public void setMaxAssists(final Integer maxAssists) {
        this.maxAssists = maxAssists;
    }

    /**
     * @param maxChampionsKilled
     *            the maxChampionsKilled to set
     */
    public void setMaxChampionsKilled(final Integer maxChampionsKilled) {
        this.maxChampionsKilled = maxChampionsKilled;
    }

    /**
     * @param maxCombatPlayerScore
     *            the maxCombatPlayerScore to set
     */
    public void setMaxCombatPlayerScore(final Integer maxCombatPlayerScore) {
        this.maxCombatPlayerScore = maxCombatPlayerScore;
    }

    /**
     * @param maxLargestCriticalStrike
     *            the maxLargestCriticalStrike to set
     */
    public void setMaxLargestCriticalStrike(final Integer maxLargestCriticalStrike) {
        this.maxLargestCriticalStrike = maxLargestCriticalStrike;
    }

    /**
     * @param maxLargestKillingSpree
     *            the maxLargestKillingSpree to set
     */
    public void setMaxLargestKillingSpree(final Integer maxLargestKillingSpree) {
        this.maxLargestKillingSpree = maxLargestKillingSpree;
    }

    /**
     * @param maxNodeCapture
     *            the maxNodeCapture to set
     */
    public void setMaxNodeCapture(final Integer maxNodeCapture) {
        this.maxNodeCapture = maxNodeCapture;
    }

    /**
     * @param maxNodeCaptureAssist
     *            the maxNodeCaptureAssist to set
     */
    public void setMaxNodeCaptureAssist(final Integer maxNodeCaptureAssist) {
        this.maxNodeCaptureAssist = maxNodeCaptureAssist;
    }

    /**
     * @param maxNodeNeutralize
     *            the maxNodeNeutralize to set
     */
    public void setMaxNodeNeutralize(final Integer maxNodeNeutralize) {
        this.maxNodeNeutralize = maxNodeNeutralize;
    }

    /**
     * @param maxNodeNeutralizeAssist
     *            the maxNodeNeutralizeAssist to set
     */
    public void setMaxNodeNeutralizeAssist(final Integer maxNodeNeutralizeAssist) {
        this.maxNodeNeutralizeAssist = maxNodeNeutralizeAssist;
    }

    /**
     * @param maxNumDeaths
     *            the maxNumDeaths to set
     */
    public void setMaxNumDeaths(final Integer maxNumDeaths) {
        this.maxNumDeaths = maxNumDeaths;
    }

    /**
     * @param maxObjectivePlayerScore
     *            the maxObjectivePlayerScore to set
     */
    public void setMaxObjectivePlayerScore(final Integer maxObjectivePlayerScore) {
        this.maxObjectivePlayerScore = maxObjectivePlayerScore;
    }

    /**
     * @param maxTeamObjective
     *            the maxTeamObjective to set
     */
    public void setMaxTeamObjective(final Integer maxTeamObjective) {
        this.maxTeamObjective = maxTeamObjective;
    }

    /**
     * @param maxTimePlayed
     *            the maxTimePlayed to set
     */
    public void setMaxTimePlayed(final Integer maxTimePlayed) {
        this.maxTimePlayed = maxTimePlayed;
    }

    /**
     * @param maxTimeSpentLiving
     *            the maxTimeSpentLiving to set
     */
    public void setMaxTimeSpentLiving(final Integer maxTimeSpentLiving) {
        this.maxTimeSpentLiving = maxTimeSpentLiving;
    }

    /**
     * @param maxTotalPlayerScore
     *            the maxTotalPlayerScore to set
     */
    public void setMaxTotalPlayerScore(final Integer maxTotalPlayerScore) {
        this.maxTotalPlayerScore = maxTotalPlayerScore;
    }

    /**
     * @param mostChampionKillsPerSession
     *            the mostChampionKillsPerSession to set
     */
    public void setMostChampionKillsPerSession(final Integer mostChampionKillsPerSession) {
        this.mostChampionKillsPerSession = mostChampionKillsPerSession;
    }

    /**
     * @param mostSpellsCast
     *            the mostSpellsCast to set
     */
    public void setMostSpellsCast(final Integer mostSpellsCast) {
        this.mostSpellsCast = mostSpellsCast;
    }

    /**
     * @param normalGamesPlayed
     *            the normalGamesPlayed to set
     */
    public void setNormalGamesPlayed(final Integer normalGamesPlayed) {
        this.normalGamesPlayed = normalGamesPlayed;
    }

    /**
     * @param rankedPremadeGamesPlayed
     *            the rankedPremadeGamesPlayed to set
     */
    public void setRankedPremadeGamesPlayed(final Integer rankedPremadeGamesPlayed) {
        this.rankedPremadeGamesPlayed = rankedPremadeGamesPlayed;
    }

    /**
     * @param rankedSoloGamesPlayed
     *            the rankedSoloGamesPlayed to set
     */
    public void setRankedSoloGamesPlayed(final Integer rankedSoloGamesPlayed) {
        this.rankedSoloGamesPlayed = rankedSoloGamesPlayed;
    }

    /**
     * @param totalAssists
     *            the totalAssists to set
     */
    public void setTotalAssists(final Integer totalAssists) {
        this.totalAssists = totalAssists;
    }

    /**
     * @param totalChampionKills
     *            the totalChampionKills to set
     */
    public void setTotalChampionKills(final Integer totalChampionKills) {
        this.totalChampionKills = totalChampionKills;
    }

    /**
     * @param totalDamageDealt
     *            the totalDamageDealt to set
     */
    public void setTotalDamageDealt(final Integer totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    /**
     * @param totalDamageTaken
     *            the totalDamageTaken to set
     */
    public void setTotalDamageTaken(final Integer totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    /**
     * @param totalDeathsPerSession
     *            the totalDeathsPerSession to set
     */
    public void setTotalDeathsPerSession(final Integer totalDeathsPerSession) {
        this.totalDeathsPerSession = totalDeathsPerSession;
    }

    /**
     * @param totalDoubleKills
     *            the totalDoubleKills to set
     */
    public void setTotalDoubleKills(final Integer totalDoubleKills) {
        this.totalDoubleKills = totalDoubleKills;
    }

    /**
     * @param totalFirstBlood
     *            the totalFirstBlood to set
     */
    public void setTotalFirstBlood(final Integer totalFirstBlood) {
        this.totalFirstBlood = totalFirstBlood;
    }

    /**
     * @param totalGoldEarned
     *            the totalGoldEarned to set
     */
    public void setTotalGoldEarned(final Integer totalGoldEarned) {
        this.totalGoldEarned = totalGoldEarned;
    }

    /**
     * @param totalHeal
     *            the totalHeal to set
     */
    public void setTotalHeal(final Integer totalHeal) {
        this.totalHeal = totalHeal;
    }

    /**
     * @param totalMagicDamageDealt
     *            the totalMagicDamageDealt to set
     */
    public void setTotalMagicDamageDealt(final Integer totalMagicDamageDealt) {
        this.totalMagicDamageDealt = totalMagicDamageDealt;
    }

    /**
     * @param totalMinionKills
     *            the totalMinionKills to set
     */
    public void setTotalMinionKills(final Integer totalMinionKills) {
        this.totalMinionKills = totalMinionKills;
    }

    /**
     * @param totalNeutralMinionsKilled
     *            the totalNeutralMinionsKilled to set
     */
    public void setTotalNeutralMinionsKilled(final Integer totalNeutralMinionsKilled) {
        this.totalNeutralMinionsKilled = totalNeutralMinionsKilled;
    }

    /**
     * @param totalNodeCapture
     *            the totalNodeCapture to set
     */
    public void setTotalNodeCapture(final Integer totalNodeCapture) {
        this.totalNodeCapture = totalNodeCapture;
    }

    /**
     * @param totalNodeNeutralize
     *            the totalNodeNeutralize to set
     */
    public void setTotalNodeNeutralize(final Integer totalNodeNeutralize) {
        this.totalNodeNeutralize = totalNodeNeutralize;
    }

    /**
     * @param totalPentaKills
     *            the totalPentaKills to set
     */
    public void setTotalPentaKills(final Integer totalPentaKills) {
        this.totalPentaKills = totalPentaKills;
    }

    /**
     * @param totalPhysicalDamageDealt
     *            the totalPhysicalDamageDealt to set
     */
    public void setTotalPhysicalDamageDealt(final Integer totalPhysicalDamageDealt) {
        this.totalPhysicalDamageDealt = totalPhysicalDamageDealt;
    }

    /**
     * @param totalQuadraKills
     *            the totalQuadraKills to set
     */
    public void setTotalQuadraKills(final Integer totalQuadraKills) {
        this.totalQuadraKills = totalQuadraKills;
    }

    /**
     * @param totalSessionsLost
     *            the totalSessionsLost to set
     */
    public void setTotalSessionsLost(final Integer totalSessionsLost) {
        this.totalSessionsLost = totalSessionsLost;
    }

    /**
     * @param totalSessionsPlayed
     *            the totalSessionsPlayed to set
     */
    public void setTotalSessionsPlayed(final Integer totalSessionsPlayed) {
        this.totalSessionsPlayed = totalSessionsPlayed;
    }

    /**
     * @param totalSessionsWon
     *            the totalSessionsWon to set
     */
    public void setTotalSessionsWon(final Integer totalSessionsWon) {
        this.totalSessionsWon = totalSessionsWon;
    }

    /**
     * @param totalTripleKills
     *            the totalTripleKills to set
     */
    public void setTotalTripleKills(final Integer totalTripleKills) {
        this.totalTripleKills = totalTripleKills;
    }

    /**
     * @param totalTurretsKilled
     *            the totalTurretsKilled to set
     */
    public void setTotalTurretsKilled(final Integer totalTurretsKilled) {
        this.totalTurretsKilled = totalTurretsKilled;
    }

    /**
     * @param totalUnrealKills
     *            the totalUnrealKills to set
     */
    public void setTotalUnrealKills(final Integer totalUnrealKills) {
        this.totalUnrealKills = totalUnrealKills;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AggregatedStats [averageAssists=" + averageAssists + ", averageChampionsKilled=" + averageChampionsKilled + ", averageCombatPlayerScore="
                + averageCombatPlayerScore + ", averageNodeCapture=" + averageNodeCapture + ", averageNodeCaptureAssist=" + averageNodeCaptureAssist
                + ", averageNodeNeutralize=" + averageNodeNeutralize + ", averageNodeNeutralizeAssist=" + averageNodeNeutralizeAssist + ", averageNumDeaths="
                + averageNumDeaths + ", averageObjectivePlayerScore=" + averageObjectivePlayerScore + ", averageTeamObjective=" + averageTeamObjective
                + ", averageTotalPlayerScore=" + averageTotalPlayerScore + ", botGamesPlayed=" + botGamesPlayed + ", killingSpree=" + killingSpree
                + ", maxAssists=" + maxAssists + ", maxChampionsKilled=" + maxChampionsKilled + ", maxCombatPlayerScore=" + maxCombatPlayerScore
                + ", maxLargestCriticalStrike=" + maxLargestCriticalStrike + ", maxLargestKillingSpree=" + maxLargestKillingSpree + ", maxNodeCapture="
                + maxNodeCapture + ", maxNodeCaptureAssist=" + maxNodeCaptureAssist + ", maxNodeNeutralize=" + maxNodeNeutralize + ", maxNodeNeutralizeAssist="
                + maxNodeNeutralizeAssist + ", maxNumDeaths=" + maxNumDeaths + ", maxObjectivePlayerScore=" + maxObjectivePlayerScore + ", maxTeamObjective="
                + maxTeamObjective + ", maxTimePlayed=" + maxTimePlayed + ", maxTimeSpentLiving=" + maxTimeSpentLiving + ", maxTotalPlayerScore="
                + maxTotalPlayerScore + ", mostChampionKillsPerSession=" + mostChampionKillsPerSession + ", mostSpellsCast=" + mostSpellsCast
                + ", normalGamesPlayed=" + normalGamesPlayed + ", rankedPremadeGamesPlayed=" + rankedPremadeGamesPlayed + ", rankedSoloGamesPlayed="
                + rankedSoloGamesPlayed + ", totalAssists=" + totalAssists + ", totalChampionKills=" + totalChampionKills + ", totalDamageDealt="
                + totalDamageDealt + ", totalDamageTaken=" + totalDamageTaken + ", totalDeathsPerSession=" + totalDeathsPerSession + ", totalDoubleKills="
                + totalDoubleKills + ", totalFirstBlood=" + totalFirstBlood + ", totalGoldEarned=" + totalGoldEarned + ", totalHeal=" + totalHeal
                + ", totalMagicDamageDealt=" + totalMagicDamageDealt + ", totalMinionKills=" + totalMinionKills + ", totalNeutralMinionsKilled="
                + totalNeutralMinionsKilled + ", totalNodeCapture=" + totalNodeCapture + ", totalNodeNeutralize=" + totalNodeNeutralize + ", totalPentaKills="
                + totalPentaKills + ", totalPhysicalDamageDealt=" + totalPhysicalDamageDealt + ", totalQuadraKills=" + totalQuadraKills + ", totalSessionsLost="
                + totalSessionsLost + ", totalSessionsPlayed=" + totalSessionsPlayed + ", totalSessionsWon=" + totalSessionsWon + ", totalTripleKills="
                + totalTripleKills + ", totalTurretsKilled=" + totalTurretsKilled + ", totalUnrealKills=" + totalUnrealKills + "]";
    }
}
