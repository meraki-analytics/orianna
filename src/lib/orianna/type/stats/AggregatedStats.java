package lib.orianna.type.stats;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AggregatedStats implements Serializable {
    private static final long serialVersionUID = -3459235144300883382L;
    public final Integer averageAssists, averageChampionsKilled, averageCombatPlayerScore, averageNodeCapture, averageNodeCaptureAssist, averageNodeNeutralize,
            averageNodeNeutralizeAssist, averageNumDeaths, averageObjectivePlayerScore, averageTeamObjective, averageTotalPlayerScore, botGamesPlayed,
            killingSpree, maxAssists, maxChampionsKilled, maxCombatPlayerScore, maxLargestCriticalStrike, maxLargestKillingSpree, maxNodeCapture,
            maxNodeCaputreAssist, maxNodeNeutralize, maxNodeNeutralizeAssist, maxNumDeaths, maxObjectivePlayerScore, maxTeamObjective, maxTimePlayed,
            maxTimeSpentLiving, maxTotalPlayerScore, mostChampionKillsPerSession, mostSpellsCast, normalGamesPlayed, rankedPremadeGamesPlayed,
            rankedSoloGamesPlayed, totalAssists, totalChampionKills, totalDamageDealt, totalDamageTaken, totalDeathsPerSession, totalDoubleKills,
            totalFirstBlood, totalGoldEarned, totalHeal, totalMagicDamageDealt, totalMinionKills, totalNeutralMinionsKilled, totalNodeCapture,
            totalNodeNeutralize, totalPentaKills, totalPhysicalDamageDealt, totalQuadraKills, totalSessionsLost, totalSessionsWon, totalTripleKills,
            totalTurretsKilled, totalUnrealKills;

    public AggregatedStats(final Integer averageAssists, final Integer averageChampionsKilled, final Integer averageCombatPlayerScore,
            final Integer averageNodeCapture, final Integer averageNodeCaptureAssist, final Integer averageNodeNeutralize,
            final Integer averageNodeNeutralizeAssist, final Integer averageNumDeaths, final Integer averageObjectivePlayerScore,
            final Integer averageTeamObjective, final Integer averageTotalPlayerScore, final Integer botGamesPlayed, final Integer killingSpree,
            final Integer maxAssists, final Integer maxChampionsKilled, final Integer maxCombatPlayerScore, final Integer maxLargestCriticalStrike,
            final Integer maxLargestKillingSpree, final Integer maxNodeCapture, final Integer maxNodeCaputreAssist, final Integer maxNodeNeutralize,
            final Integer maxNodeNeutralizeAssist, final Integer maxNumDeaths, final Integer maxObjectivePlayerScore, final Integer maxTeamObjective,
            final Integer maxTimePlayed, final Integer maxTimeSpentLiving, final Integer maxTotalPlayerScore, final Integer mostChampionKillsPerSession,
            final Integer mostSpellsCast, final Integer normalGamesPlayed, final Integer rankedPremadeGamesPlayed, final Integer rankedSoloGamesPlayed,
            final Integer totalAssists, final Integer totalChampionKills, final Integer totalDamageDealt, final Integer totalDamageTaken,
            final Integer totalDeathsPerSession, final Integer totalDoubleKills, final Integer totalFirstBlood, final Integer totalGoldEarned,
            final Integer totalHeal, final Integer totalMagicDamageDealt, final Integer totalMinionKills, final Integer totalNeutralMinionsKilled,
            final Integer totalNodeCapture, final Integer totalNodeNeutralize, final Integer totalPentaKills, final Integer totalPhysicalDamageDealt,
            final Integer totalQuadraKills, final Integer totalSessionsLost, final Integer totalSessionsWon, final Integer totalTripleKills,
            final Integer totalTurretsKilled, final Integer totalUnrealKills) {
        this.averageAssists = averageAssists;
        this.averageChampionsKilled = averageChampionsKilled;
        this.averageCombatPlayerScore = averageCombatPlayerScore;
        this.averageNodeCapture = averageNodeCapture;
        this.averageNodeCaptureAssist = averageNodeCaptureAssist;
        this.averageNodeNeutralize = averageNodeNeutralize;
        this.averageNodeNeutralizeAssist = averageNodeNeutralizeAssist;
        this.averageNumDeaths = averageNumDeaths;
        this.averageObjectivePlayerScore = averageObjectivePlayerScore;
        this.averageTeamObjective = averageTeamObjective;
        this.averageTotalPlayerScore = averageTotalPlayerScore;
        this.botGamesPlayed = botGamesPlayed;
        this.killingSpree = killingSpree;
        this.maxAssists = maxAssists;
        this.maxChampionsKilled = maxChampionsKilled;
        this.maxCombatPlayerScore = maxCombatPlayerScore;
        this.maxLargestCriticalStrike = maxLargestCriticalStrike;
        this.maxLargestKillingSpree = maxLargestKillingSpree;
        this.maxNodeCapture = maxNodeCapture;
        this.maxNodeCaputreAssist = maxNodeCaputreAssist;
        this.maxNodeNeutralize = maxNodeNeutralize;
        this.maxNodeNeutralizeAssist = maxNodeNeutralizeAssist;
        this.maxNumDeaths = maxNumDeaths;
        this.maxObjectivePlayerScore = maxObjectivePlayerScore;
        this.maxTeamObjective = maxTeamObjective;
        this.maxTimePlayed = maxTimePlayed;
        this.maxTimeSpentLiving = maxTimeSpentLiving;
        this.maxTotalPlayerScore = maxTotalPlayerScore;
        this.mostChampionKillsPerSession = mostChampionKillsPerSession;
        this.mostSpellsCast = mostSpellsCast;
        this.normalGamesPlayed = normalGamesPlayed;
        this.rankedPremadeGamesPlayed = rankedPremadeGamesPlayed;
        this.rankedSoloGamesPlayed = rankedSoloGamesPlayed;
        this.totalAssists = totalAssists;
        this.totalChampionKills = totalChampionKills;
        this.totalDamageDealt = totalDamageDealt;
        this.totalDamageTaken = totalDamageTaken;
        this.totalDeathsPerSession = totalDeathsPerSession;
        this.totalDoubleKills = totalDoubleKills;
        this.totalFirstBlood = totalFirstBlood;
        this.totalGoldEarned = totalGoldEarned;
        this.totalHeal = totalHeal;
        this.totalMagicDamageDealt = totalMagicDamageDealt;
        this.totalMinionKills = totalMinionKills;
        this.totalNeutralMinionsKilled = totalNeutralMinionsKilled;
        this.totalNodeCapture = totalNodeCapture;
        this.totalNodeNeutralize = totalNodeNeutralize;
        this.totalPentaKills = totalPentaKills;
        this.totalPhysicalDamageDealt = totalPhysicalDamageDealt;
        this.totalQuadraKills = totalQuadraKills;
        this.totalSessionsLost = totalSessionsLost;
        this.totalSessionsWon = totalSessionsWon;
        this.totalTripleKills = totalTripleKills;
        this.totalTurretsKilled = totalTurretsKilled;
        this.totalUnrealKills = totalUnrealKills;
    }

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
        if(maxNodeCaputreAssist == null) {
            if(other.maxNodeCaputreAssist != null) {
                return false;
            }
        }
        else if(!maxNodeCaputreAssist.equals(other.maxNodeCaputreAssist)) {
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
        result = prime * result + (maxNodeCaputreAssist == null ? 0 : maxNodeCaputreAssist.hashCode());
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
        result = prime * result + (totalSessionsWon == null ? 0 : totalSessionsWon.hashCode());
        result = prime * result + (totalTripleKills == null ? 0 : totalTripleKills.hashCode());
        result = prime * result + (totalTurretsKilled == null ? 0 : totalTurretsKilled.hashCode());
        result = prime * result + (totalUnrealKills == null ? 0 : totalUnrealKills.hashCode());
        return result;
    }

    /**
     * Gets only the stats which weren't null
     *
     * @return the non-null stats
     */
    public Map<String, Integer> nonNullStats() {
        final Map<String, Integer> nonNull = new HashMap<String, Integer>();
        if(averageAssists != null) {
            nonNull.put("averageAssists", averageAssists);
        }
        if(averageChampionsKilled != null) {
            nonNull.put("averageChampionsKilled", averageChampionsKilled);
        }
        if(averageCombatPlayerScore != null) {
            nonNull.put("averageCombatPlayerScore", averageCombatPlayerScore);
        }
        if(averageNodeCapture != null) {
            nonNull.put("averageNodeCapture", averageNodeCapture);
        }
        if(averageNodeCaptureAssist != null) {
            nonNull.put("averageNodeCaptureAssist", averageNodeCaptureAssist);
        }
        if(averageNodeNeutralize != null) {
            nonNull.put("averageNodeNeutralize", averageNodeNeutralize);
        }
        if(averageNodeNeutralizeAssist != null) {
            nonNull.put("averageNodeNeutralizeAssist", averageNodeNeutralizeAssist);
        }
        if(averageNumDeaths != null) {
            nonNull.put("averageNumDeaths", averageNumDeaths);
        }
        if(averageObjectivePlayerScore != null) {
            nonNull.put("averageObjectivePlayerScore", averageObjectivePlayerScore);
        }
        if(averageTeamObjective != null) {
            nonNull.put("averageTeamObjective", averageTeamObjective);
        }
        if(averageTotalPlayerScore != null) {
            nonNull.put("averageTotalPlayerScore", averageTotalPlayerScore);
        }
        if(botGamesPlayed != null) {
            nonNull.put("botGamesPlayed", botGamesPlayed);
        }
        if(killingSpree != null) {
            nonNull.put("killingSpree", killingSpree);
        }
        if(maxAssists != null) {
            nonNull.put("maxAssists", maxAssists);
        }
        if(maxChampionsKilled != null) {
            nonNull.put("maxChampionsKilled", maxChampionsKilled);
        }
        if(maxCombatPlayerScore != null) {
            nonNull.put("maxCombatPlayerScore", maxCombatPlayerScore);
        }
        if(maxLargestCriticalStrike != null) {
            nonNull.put("maxLargestCriticalStrike", maxLargestCriticalStrike);
        }
        if(maxLargestKillingSpree != null) {
            nonNull.put("maxLargestKillingSpree", maxLargestKillingSpree);
        }
        if(maxNodeCapture != null) {
            nonNull.put("maxNodeCapture", maxNodeCapture);
        }
        if(maxNodeCaputreAssist != null) {
            nonNull.put("maxNodeCaputreAssist", maxNodeCaputreAssist);
        }
        if(maxNodeNeutralize != null) {
            nonNull.put("maxNodeNeutralize", maxNodeNeutralize);
        }
        if(maxNodeNeutralizeAssist != null) {
            nonNull.put("maxNodeNeutralizeAssist", maxNodeNeutralizeAssist);
        }
        if(maxNumDeaths != null) {
            nonNull.put("maxNumDeaths", maxNumDeaths);
        }
        if(maxObjectivePlayerScore != null) {
            nonNull.put("maxObjectivePlayerScore", maxObjectivePlayerScore);
        }
        if(maxTeamObjective != null) {
            nonNull.put("maxTeamObjective", maxTeamObjective);
        }
        if(maxTimePlayed != null) {
            nonNull.put("maxTimePlayed", maxTimePlayed);
        }
        if(maxTimeSpentLiving != null) {
            nonNull.put("maxTimeSpentLiving", maxTimeSpentLiving);
        }
        if(maxTotalPlayerScore != null) {
            nonNull.put("maxTotalPlayerScore", maxTotalPlayerScore);
        }
        if(mostChampionKillsPerSession != null) {
            nonNull.put("mostChampionKillsPerSession", mostChampionKillsPerSession);
        }
        if(mostSpellsCast != null) {
            nonNull.put("mostSpellsCast", mostSpellsCast);
        }
        if(normalGamesPlayed != null) {
            nonNull.put("normalGamesPlayed", normalGamesPlayed);
        }
        if(rankedPremadeGamesPlayed != null) {
            nonNull.put("rankedPremadeGamesPlayed", rankedPremadeGamesPlayed);
        }
        if(rankedSoloGamesPlayed != null) {
            nonNull.put("rankedSoloGamesPlayed", rankedSoloGamesPlayed);
        }
        if(totalAssists != null) {
            nonNull.put("totalAssists", totalAssists);
        }
        if(totalChampionKills != null) {
            nonNull.put("totalChampionKills", totalChampionKills);
        }
        if(totalDamageDealt != null) {
            nonNull.put("totalDamageDealt", totalDamageDealt);
        }
        if(totalDamageTaken != null) {
            nonNull.put("totalDamageTaken", totalDamageTaken);
        }
        if(totalDeathsPerSession != null) {
            nonNull.put("totalDeathsPerSession", totalDeathsPerSession);
        }
        if(totalDoubleKills != null) {
            nonNull.put("totalDoubleKills", totalDoubleKills);
        }
        if(totalFirstBlood != null) {
            nonNull.put("totalFirstBlood", totalFirstBlood);
        }
        if(totalGoldEarned != null) {
            nonNull.put("totalGoldEarned", totalGoldEarned);
        }
        if(totalHeal != null) {
            nonNull.put("totalHeal", totalHeal);
        }
        if(totalMagicDamageDealt != null) {
            nonNull.put("totalMagicDamageDealt", totalMagicDamageDealt);
        }
        if(totalMinionKills != null) {
            nonNull.put("totalMinionKills", totalMinionKills);
        }
        if(totalNeutralMinionsKilled != null) {
            nonNull.put("totalNeutralMinionsKilled", totalNeutralMinionsKilled);
        }
        if(totalNodeCapture != null) {
            nonNull.put("totalNodeCapture", totalNodeCapture);
        }
        if(totalNodeNeutralize != null) {
            nonNull.put("totalNodeNeutralize", totalNodeNeutralize);
        }
        if(totalPentaKills != null) {
            nonNull.put("totalPentaKills", totalPentaKills);
        }
        if(totalPhysicalDamageDealt != null) {
            nonNull.put("totalPhysicalDamageDealt", totalPhysicalDamageDealt);
        }
        if(totalQuadraKills != null) {
            nonNull.put("totalQuadraKills", totalQuadraKills);
        }
        if(totalSessionsLost != null) {
            nonNull.put("totalSessionsLost", totalSessionsLost);
        }
        if(totalSessionsWon != null) {
            nonNull.put("totalSessionsWon", totalSessionsWon);
        }
        if(totalTripleKills != null) {
            nonNull.put("totalTripleKills", totalTripleKills);
        }
        if(totalTurretsKilled != null) {
            nonNull.put("totalTurretsKilled", totalTurretsKilled);
        }
        if(totalUnrealKills != null) {
            nonNull.put("totalUnrealKills", totalUnrealKills);
        }

        return Collections.unmodifiableMap(nonNull);
    }

    @Override
    public String toString() {
        return "AggregatedStats";
    }

}
