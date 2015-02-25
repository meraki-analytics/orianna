package com.robrua.orianna.type.core.stats;

import com.robrua.orianna.type.core.OriannaObject;

public class AggregatedStats extends OriannaObject<com.robrua.orianna.type.dto.stats.AggregatedStats> {
    private static final long serialVersionUID = -2824319850398502919L;

    /**
     * @param data
     *            the underlying dto
     */
    public AggregatedStats(final com.robrua.orianna.type.dto.stats.AggregatedStats data) {
        super(data, com.robrua.orianna.type.dto.stats.AggregatedStats.class);
    }

    /**
     * Average assists
     *
     * @return average assists
     */
    public int getAverageAssists() {
        return super.getInteger(data.getAverageAssists());
    }

    /**
     * Average combat player score
     *
     * @return average combat player score
     */
    public int getAverageCombatPlayerScore() {
        return super.getInteger(data.getAverageCombatPlayerScore());
    }

    /**
     * Average number of deaths
     *
     * @return average number of deaths
     */
    public int getAverageDeaths() {
        return super.getInteger(data.getAverageNumDeaths());
    }

    /**
     * Average champions killed
     *
     * @return average champions killed
     */
    public int getAverageKills() {
        return super.getInteger(data.getAverageChampionsKilled());
    }

    /**
     * Average node capture assists
     *
     * @return average node capture assists
     */
    public int getAverageNodeCaptureAssists() {
        return super.getInteger(data.getAverageNodeCaptureAssist());
    }

    /**
     * Average nodes captured
     *
     * @return average nodes captured
     */
    public int getAverageNodeCaptures() {
        return super.getInteger(data.getAverageNodeCapture());
    }

    /**
     * Average node neutralization assists
     *
     * @return average node neutralization assists
     */
    public int getAverageNodeNeutralizeAssists() {
        return super.getInteger(data.getAverageNodeNeutralizeAssist());
    }

    /**
     * Average nodes neutralized
     *
     * @return average nodes neutralized
     */
    public int getAverageNodesNeutralized() {
        return super.getInteger(data.getAverageNodeNeutralize());
    }

    /**
     * Average objective player score
     *
     * @return average objective player score
     */
    public int getAverageObjectivePlayerScore() {
        return super.getInteger(data.getAverageObjectivePlayerScore());
    }

    /**
     * Average team objective score
     *
     * @return average team objective score
     */
    public int getAverageTeamObjective() {
        return super.getInteger(data.getAverageTeamObjective());
    }

    /**
     * Average total player score
     *
     * @return average total player score
     */
    public int getAverageTotalPlayerScore() {
        return super.getInteger(data.getAverageTotalPlayerScore());
    }

    /**
     * Total number of bot games played
     *
     * @return total number of bot games played
     */
    public int getBotGamesPlayed() {
        return super.getInteger(data.getBotGamesPlayed());
    }

    /**
     * Total number of killing sprees
     *
     * @return total number of killing sprees
     */
    public int getKillingSprees() {
        return super.getInteger(data.getKillingSpree());
    }

    /**
     * Maximum number of assists
     *
     * @return maximum number of assists
     */
    public int getMaxAssists() {
        return super.getInteger(data.getMaxAssists());
    }

    /**
     * Maximum combat player score
     *
     * @return maximum combat player score
     */
    public int getMaxCombatPlayerScore() {
        return super.getInteger(data.getMaxCombatPlayerScore());
    }

    /**
     * Maximum critical strike
     *
     * @return maximum critical strike
     */
    public int getMaxCriticalStrike() {
        return super.getInteger(data.getMaxLargestCriticalStrike());
    }

    /**
     * Maximum number of deaths
     *
     * @return maximum number of deaths
     */
    public int getMaxDeaths() {
        return super.getInteger(data.getMaxNumDeaths());
    }

    /**
     * Maximum killing spree
     *
     * @return maximum killing spree
     */
    public int getMaxKillingSpree() {
        return super.getInteger(data.getMaxLargestKillingSpree());
    }

    /**
     * Maximum number of champions killed
     *
     * @return maximum number of champions killed
     */
    public int getMaxKills() {
        return super.getInteger(data.getMaxChampionsKilled());
    }

    /**
     * Maximum node capture assists
     *
     * @return maximum node capture assists
     */
    public int getMaxNodeCaptureAssists() {
        return super.getInteger(data.getMaxNodeCaptureAssist());
    }

    /**
     * Maximum nodes captured
     *
     * @return maximum nodes captured
     */
    public int getMaxNodeCaptures() {
        return super.getInteger(data.getMaxNodeCapture());
    }

    /**
     * Maximum nodes neutralized
     *
     * @return maximum nodes neutralized
     */
    public int getMaxNodeNeutralizations() {
        return super.getInteger(data.getMaxNodeNeutralize());
    }

    /**
     * Maximum node neutralization assists
     *
     * @return maximum node neutralization assists
     */
    public int getMaxNodeNeutralizeAssists() {
        return super.getInteger(data.getMaxNodeNeutralizeAssist());
    }

    /**
     * Maximum objective player score
     *
     * @return maximum objective player score
     */
    public int getMaxObjectivePlayerScore() {
        return super.getInteger(data.getMaxObjectivePlayerScore());
    }

    /**
     * Maximum team objectives
     *
     * @return maximum team objectives
     */
    public int getMaxTeamObjectives() {
        return super.getInteger(data.getMaxTeamObjective());
    }

    /**
     * Maximum game length
     *
     * @return maximum game length
     */
    public int getMaxTimePlayed() {
        return super.getInteger(data.getMaxTimePlayed());
    }

    /**
     * Maximum time spent living
     *
     * @return maximum time spent living
     */
    public int getMaxTimeSpentLiving() {
        return super.getInteger(data.getMaxTimeSpentLiving());
    }

    /**
     * Maximum total player score
     *
     * @return maximum total player score
     */
    public int getMaxTotalPlayerScore() {
        return super.getInteger(data.getMaxTotalPlayerScore());
    }

    /**
     * Maximum player kills in a session
     *
     * @return maximum player kills in a session
     */
    public int getMostKillsPerSession() {
        return super.getInteger(data.getMostChampionKillsPerSession());
    }

    /**
     * Maximum spells cast
     *
     * @return maximum spells cast
     */
    public int getMostSpellsCast() {
        return super.getInteger(data.getMostSpellsCast());
    }

    /**
     * Total number of normal games played
     *
     * @return total number of normal games played
     */
    public int getNormalGamesPlayed() {
        return super.getInteger(data.getNormalGamesPlayed());
    }

    /**
     * Total number of ranked premade games played
     *
     * @return total number of ranked premade games played
     */
    public int getRankedPremadeGamesPlayed() {
        return super.getInteger(data.getRankedPremadeGamesPlayed());
    }

    /**
     * Total number of ranked solo games played
     *
     * @return total number of ranked solo games played
     */
    public int getRankedSoloGamesPlayed() {
        return super.getInteger(data.getRankedSoloGamesPlayed());
    }

    /**
     * Total number of assists
     *
     * @return total number of assists
     */
    public int getTotalAssists() {
        return super.getInteger(data.getTotalAssists());
    }

    /**
     * Total damage dealt
     *
     * @return total damage dealt
     */
    public int getTotalDamageDealt() {
        return super.getInteger(data.getTotalDamageDealt());
    }

    /**
     * Total damage taken
     *
     * @return total damage taken
     */
    public int getTotalDamageTaken() {
        return super.getInteger(data.getTotalDamageTaken());
    }

    /**
     * Total number of deaths
     *
     * @return total number of deaths
     */
    public int getTotalDeaths() {
        return super.getInteger(data.getTotalDeathsPerSession());
    }

    /**
     * Total number of double kills
     *
     * @return total number of double kills
     */
    public int getTotalDoubleKills() {
        return super.getInteger(data.getTotalDoubleKills());
    }

    /**
     * Total number of first bloods
     *
     * @return total number of first bloods
     */
    public int getTotalFirstBloods() {
        return super.getInteger(data.getTotalFirstBlood());
    }

    /**
     * Total number of games played
     *
     * @return total number of games played
     */
    public int getTotalGamesPlayed() {
        return super.getInteger(data.getTotalSessionsPlayed());
    }

    /**
     * Total gold earned
     *
     * @return total gold earned
     */
    public int getTotalGoldEarned() {
        return super.getInteger(data.getTotalGoldEarned());
    }

    /**
     * Total healing done
     *
     * @return total healing done
     */
    public int getTotalHealing() {
        return super.getInteger(data.getTotalHeal());
    }

    /**
     * Total number of kills
     *
     * @return total number of kills
     */
    public int getTotalKills() {
        return super.getInteger(data.getTotalChampionKills());
    }

    /**
     * Total number of losses
     *
     * @return total number of losses
     */
    public int getTotalLosses() {
        return super.getInteger(data.getTotalSessionsLost());
    }

    /**
     * Total magic damage dealt
     *
     * @return total magic damage dealt
     */
    public int getTotalMagicDamageDealt() {
        return super.getInteger(data.getTotalMagicDamageDealt());
    }

    /**
     * Total number of minion kills
     *
     * @return total number of minion kills
     */
    public int getTotalMinionKills() {
        return super.getInteger(data.getTotalMinionKills());
    }

    /**
     * Total number of neutral minions killed
     *
     * @return total number of neutral minions killed
     */
    public int getTotalNeutralMinionsKilled() {
        return super.getInteger(data.getTotalNeutralMinionsKilled());
    }

    /**
     * Total number of nodes caputred
     *
     * @return total number of nodes caputred
     */
    public int getTotalNodeCaptures() {
        return super.getInteger(data.getTotalNodeCapture());
    }

    /**
     * Total number of nodes neutralized
     *
     * @return total number of nodes neutralized
     */
    public int getTotalNodeNeutralizations() {
        return super.getInteger(data.getTotalNodeNeutralize());
    }

    /**
     * Total number of penta kills
     *
     * @return total number of penta kills
     */
    public int getTotalPentaKills() {
        return super.getInteger(data.getTotalPentaKills());
    }

    /**
     * Total physical damage dealt
     *
     * @return total physical damage dealt
     */
    public int getTotalPhysicalDamageDealt() {
        return super.getInteger(data.getTotalPhysicalDamageDealt());
    }

    /**
     * Total number of quadra kills
     *
     * @return total number of quadra kills
     */
    public int getTotalQuadraKills() {
        return super.getInteger(data.getTotalQuadraKills());
    }

    /**
     * Total number of triple kills
     *
     * @return total number of triple kills
     */
    public int getTotalTripleKills() {
        return super.getInteger(data.getTotalTripleKills());
    }

    /**
     * Total number of turrets killed
     *
     * @return total number of turrets killed
     */
    public int getTotalTurretsKilled() {
        return super.getInteger(data.getTotalTurretsKilled());
    }

    /**
     * Total number of unreal kills
     *
     * @return total number of unreal kills
     */
    public int getTotalUnrealKills() {
        return super.getInteger(data.getTotalUnrealKills());
    }

    /**
     * Total number of wins
     *
     * @return total number of wins
     */
    public int getTotalWins() {
        return super.getInteger(data.getTotalSessionsWon());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AggregatedStats";
    }

}
