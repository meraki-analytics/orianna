package com.merakianalytics.orianna.types.data.match;

import java.util.List;

import org.joda.time.Duration;

import com.merakianalytics.orianna.types.data.CoreData;

public class ParticipantStats extends CoreData {
    private static final long serialVersionUID = -1889815557872266659L;
    private int altarsCaptured, altarsNeutralized, assists, championLevel, combatScore, deaths, doubleKills, goldEarned, goldSpent, inhibitorKills,
            killingSprees, kills, largestCriticalStrike, largestKillingSpree, largestMultiKill, neutralMinionsKilled, neutralMinionsKilledInEnemyJungle,
            neutralMinionsKilledInAllyJungle, nodesCaptured, nodeCaptureAssists, nodesNeutralized, nodeNeutralizeAssists, objectiveScore, pentaKills,
            quadraKills, greenWardsPurchased, teamObjectives, creepScore, score, scoreRank, unitsHealed, tripleKills, turretKills, hexaKills,
            pinkWardsPurchased, wardsKilled, wardsPlaced, damageToObjectives, damageToTurrets, damageMitigated, magicDamageTaken, magicDamageDealt,
            magicDamageDealtToChampions, physicalDamageDealt, physicalDamageDealtToChampions, physicalDamageTaken, damageDealt, damageDealtToChampions,
            damageTaken, damageHealed, trueDamageDealt, trueDamageDealtToChampions, trueDamageTaken, visionScore;
    private boolean firstBloodAssistant, firstBloodKiller, firstInhibitorKillAssistant, firstInhibitorKiller, firstTowerKillAssistant, firstTowerKiller, winner;
    private List<Integer> items;
    private Duration longestTimeAlive, crowdControlDealt, crowdControlDealtToChampions;

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
        if(championLevel != other.championLevel) {
            return false;
        }
        if(combatScore != other.combatScore) {
            return false;
        }
        if(creepScore != other.creepScore) {
            return false;
        }
        if(crowdControlDealt == null) {
            if(other.crowdControlDealt != null) {
                return false;
            }
        } else if(!crowdControlDealt.equals(other.crowdControlDealt)) {
            return false;
        }
        if(crowdControlDealtToChampions == null) {
            if(other.crowdControlDealtToChampions != null) {
                return false;
            }
        } else if(!crowdControlDealtToChampions.equals(other.crowdControlDealtToChampions)) {
            return false;
        }
        if(damageDealt != other.damageDealt) {
            return false;
        }
        if(damageDealtToChampions != other.damageDealtToChampions) {
            return false;
        }
        if(damageHealed != other.damageHealed) {
            return false;
        }
        if(damageMitigated != other.damageMitigated) {
            return false;
        }
        if(damageTaken != other.damageTaken) {
            return false;
        }
        if(damageToObjectives != other.damageToObjectives) {
            return false;
        }
        if(damageToTurrets != other.damageToTurrets) {
            return false;
        }
        if(deaths != other.deaths) {
            return false;
        }
        if(doubleKills != other.doubleKills) {
            return false;
        }
        if(firstBloodAssistant != other.firstBloodAssistant) {
            return false;
        }
        if(firstBloodKiller != other.firstBloodKiller) {
            return false;
        }
        if(firstInhibitorKillAssistant != other.firstInhibitorKillAssistant) {
            return false;
        }
        if(firstInhibitorKiller != other.firstInhibitorKiller) {
            return false;
        }
        if(firstTowerKillAssistant != other.firstTowerKillAssistant) {
            return false;
        }
        if(firstTowerKiller != other.firstTowerKiller) {
            return false;
        }
        if(goldEarned != other.goldEarned) {
            return false;
        }
        if(goldSpent != other.goldSpent) {
            return false;
        }
        if(greenWardsPurchased != other.greenWardsPurchased) {
            return false;
        }
        if(hexaKills != other.hexaKills) {
            return false;
        }
        if(inhibitorKills != other.inhibitorKills) {
            return false;
        }
        if(items == null) {
            if(other.items != null) {
                return false;
            }
        } else if(!items.equals(other.items)) {
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
        if(longestTimeAlive == null) {
            if(other.longestTimeAlive != null) {
                return false;
            }
        } else if(!longestTimeAlive.equals(other.longestTimeAlive)) {
            return false;
        }
        if(magicDamageDealt != other.magicDamageDealt) {
            return false;
        }
        if(magicDamageDealtToChampions != other.magicDamageDealtToChampions) {
            return false;
        }
        if(magicDamageTaken != other.magicDamageTaken) {
            return false;
        }
        if(neutralMinionsKilled != other.neutralMinionsKilled) {
            return false;
        }
        if(neutralMinionsKilledInAllyJungle != other.neutralMinionsKilledInAllyJungle) {
            return false;
        }
        if(neutralMinionsKilledInEnemyJungle != other.neutralMinionsKilledInEnemyJungle) {
            return false;
        }
        if(nodeCaptureAssists != other.nodeCaptureAssists) {
            return false;
        }
        if(nodeNeutralizeAssists != other.nodeNeutralizeAssists) {
            return false;
        }
        if(nodesCaptured != other.nodesCaptured) {
            return false;
        }
        if(nodesNeutralized != other.nodesNeutralized) {
            return false;
        }
        if(objectiveScore != other.objectiveScore) {
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
        if(pinkWardsPurchased != other.pinkWardsPurchased) {
            return false;
        }
        if(quadraKills != other.quadraKills) {
            return false;
        }
        if(score != other.score) {
            return false;
        }
        if(scoreRank != other.scoreRank) {
            return false;
        }
        if(teamObjectives != other.teamObjectives) {
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
        if(unitsHealed != other.unitsHealed) {
            return false;
        }
        if(visionScore != other.visionScore) {
            return false;
        }
        if(wardsKilled != other.wardsKilled) {
            return false;
        }
        if(wardsPlaced != other.wardsPlaced) {
            return false;
        }
        if(winner != other.winner) {
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
     * @return the championLevel
     */
    public int getChampionLevel() {
        return championLevel;
    }

    /**
     * @return the combatScore
     */
    public int getCombatScore() {
        return combatScore;
    }

    /**
     * @return the creepScore
     */
    public int getCreepScore() {
        return creepScore;
    }

    /**
     * @return the crowdControlDealt
     */
    public Duration getCrowdControlDealt() {
        return crowdControlDealt;
    }

    /**
     * @return the crowdControlDealtToChampions
     */
    public Duration getCrowdControlDealtToChampions() {
        return crowdControlDealtToChampions;
    }

    /**
     * @return the damageDealt
     */
    public int getDamageDealt() {
        return damageDealt;
    }

    /**
     * @return the damageDealtToChampions
     */
    public int getDamageDealtToChampions() {
        return damageDealtToChampions;
    }

    /**
     * @return the damageHealed
     */
    public int getDamageHealed() {
        return damageHealed;
    }

    /**
     * @return the damageMitigated
     */
    public int getDamageMitigated() {
        return damageMitigated;
    }

    /**
     * @return the damageTaken
     */
    public int getDamageTaken() {
        return damageTaken;
    }

    /**
     * @return the damageToObjectives
     */
    public int getDamageToObjectives() {
        return damageToObjectives;
    }

    /**
     * @return the damageToTurrets
     */
    public int getDamageToTurrets() {
        return damageToTurrets;
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
     * @return the greenWardsPurchased
     */
    public int getGreenWardsPurchased() {
        return greenWardsPurchased;
    }

    /**
     * @return the hexaKills
     */
    public int getHexaKills() {
        return hexaKills;
    }

    /**
     * @return the inhibitorKills
     */
    public int getInhibitorKills() {
        return inhibitorKills;
    }

    /**
     * @return the items
     */
    public List<Integer> getItems() {
        return items;
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
     * @return the longestTimeAlive
     */
    public Duration getLongestTimeAlive() {
        return longestTimeAlive;
    }

    /**
     * @return the magicDamageDealt
     */
    public int getMagicDamageDealt() {
        return magicDamageDealt;
    }

    /**
     * @return the magicDamageDealtToChampions
     */
    public int getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    /**
     * @return the magicDamageTaken
     */
    public int getMagicDamageTaken() {
        return magicDamageTaken;
    }

    /**
     * @return the neutralMinionsKilled
     */
    public int getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    /**
     * @return the neutralMinionsKilledInAllyJungle
     */
    public int getNeutralMinionsKilledInAllyJungle() {
        return neutralMinionsKilledInAllyJungle;
    }

    /**
     * @return the neutralMinionsKilledInEnemyJungle
     */
    public int getNeutralMinionsKilledInEnemyJungle() {
        return neutralMinionsKilledInEnemyJungle;
    }

    /**
     * @return the nodeCaptureAssists
     */
    public int getNodeCaptureAssists() {
        return nodeCaptureAssists;
    }

    /**
     * @return the nodeNeutralizeAssists
     */
    public int getNodeNeutralizeAssists() {
        return nodeNeutralizeAssists;
    }

    /**
     * @return the nodesCaptured
     */
    public int getNodesCaptured() {
        return nodesCaptured;
    }

    /**
     * @return the nodesNeutralized
     */
    public int getNodesNeutralized() {
        return nodesNeutralized;
    }

    /**
     * @return the objectiveScore
     */
    public int getObjectiveScore() {
        return objectiveScore;
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
    public int getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    /**
     * @return the physicalDamageDealtToChampions
     */
    public int getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    /**
     * @return the physicalDamageTaken
     */
    public int getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    /**
     * @return the pinkWardsPurchased
     */
    public int getPinkWardsPurchased() {
        return pinkWardsPurchased;
    }

    /**
     * @return the quadraKills
     */
    public int getQuadraKills() {
        return quadraKills;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the scoreRank
     */
    public int getScoreRank() {
        return scoreRank;
    }

    /**
     * @return the teamObjectives
     */
    public int getTeamObjectives() {
        return teamObjectives;
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
    public int getTrueDamageDealt() {
        return trueDamageDealt;
    }

    /**
     * @return the trueDamageDealtToChampions
     */
    public int getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    /**
     * @return the trueDamageTaken
     */
    public int getTrueDamageTaken() {
        return trueDamageTaken;
    }

    /**
     * @return the turretKills
     */
    public int getTurretKills() {
        return turretKills;
    }

    /**
     * @return the unitsHealed
     */
    public int getUnitsHealed() {
        return unitsHealed;
    }

    /**
     * @return the visionScore
     */
    public int getVisionScore() {
        return visionScore;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + altarsCaptured;
        result = prime * result + altarsNeutralized;
        result = prime * result + assists;
        result = prime * result + championLevel;
        result = prime * result + combatScore;
        result = prime * result + creepScore;
        result = prime * result + (crowdControlDealt == null ? 0 : crowdControlDealt.hashCode());
        result = prime * result + (crowdControlDealtToChampions == null ? 0 : crowdControlDealtToChampions.hashCode());
        result = prime * result + damageDealt;
        result = prime * result + damageDealtToChampions;
        result = prime * result + damageHealed;
        result = prime * result + damageMitigated;
        result = prime * result + damageTaken;
        result = prime * result + damageToObjectives;
        result = prime * result + damageToTurrets;
        result = prime * result + deaths;
        result = prime * result + doubleKills;
        result = prime * result + (firstBloodAssistant ? 1231 : 1237);
        result = prime * result + (firstBloodKiller ? 1231 : 1237);
        result = prime * result + (firstInhibitorKillAssistant ? 1231 : 1237);
        result = prime * result + (firstInhibitorKiller ? 1231 : 1237);
        result = prime * result + (firstTowerKillAssistant ? 1231 : 1237);
        result = prime * result + (firstTowerKiller ? 1231 : 1237);
        result = prime * result + goldEarned;
        result = prime * result + goldSpent;
        result = prime * result + greenWardsPurchased;
        result = prime * result + hexaKills;
        result = prime * result + inhibitorKills;
        result = prime * result + (items == null ? 0 : items.hashCode());
        result = prime * result + killingSprees;
        result = prime * result + kills;
        result = prime * result + largestCriticalStrike;
        result = prime * result + largestKillingSpree;
        result = prime * result + largestMultiKill;
        result = prime * result + (longestTimeAlive == null ? 0 : longestTimeAlive.hashCode());
        result = prime * result + magicDamageDealt;
        result = prime * result + magicDamageDealtToChampions;
        result = prime * result + magicDamageTaken;
        result = prime * result + neutralMinionsKilled;
        result = prime * result + neutralMinionsKilledInAllyJungle;
        result = prime * result + neutralMinionsKilledInEnemyJungle;
        result = prime * result + nodeCaptureAssists;
        result = prime * result + nodeNeutralizeAssists;
        result = prime * result + nodesCaptured;
        result = prime * result + nodesNeutralized;
        result = prime * result + objectiveScore;
        result = prime * result + pentaKills;
        result = prime * result + physicalDamageDealt;
        result = prime * result + physicalDamageDealtToChampions;
        result = prime * result + physicalDamageTaken;
        result = prime * result + pinkWardsPurchased;
        result = prime * result + quadraKills;
        result = prime * result + score;
        result = prime * result + scoreRank;
        result = prime * result + teamObjectives;
        result = prime * result + tripleKills;
        result = prime * result + trueDamageDealt;
        result = prime * result + trueDamageDealtToChampions;
        result = prime * result + trueDamageTaken;
        result = prime * result + turretKills;
        result = prime * result + unitsHealed;
        result = prime * result + visionScore;
        result = prime * result + wardsKilled;
        result = prime * result + wardsPlaced;
        result = prime * result + (winner ? 1231 : 1237);
        return result;
    }

    /**
     * @return the firstBloodAssistant
     */
    public boolean isFirstBloodAssistant() {
        return firstBloodAssistant;
    }

    /**
     * @return the firstBloodKiller
     */
    public boolean isFirstBloodKiller() {
        return firstBloodKiller;
    }

    /**
     * @return the firstInhibitorKillAssistant
     */
    public boolean isFirstInhibitorKillAssistant() {
        return firstInhibitorKillAssistant;
    }

    /**
     * @return the firstInhibitorKiller
     */
    public boolean isFirstInhibitorKiller() {
        return firstInhibitorKiller;
    }

    /**
     * @return the firstTowerKillAssistant
     */
    public boolean isFirstTowerKillAssistant() {
        return firstTowerKillAssistant;
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
     * @param championLevel
     *        the championLevel to set
     */
    public void setChampionLevel(final int championLevel) {
        this.championLevel = championLevel;
    }

    /**
     * @param combatScore
     *        the combatScore to set
     */
    public void setCombatScore(final int combatScore) {
        this.combatScore = combatScore;
    }

    /**
     * @param creepScore
     *        the creepScore to set
     */
    public void setCreepScore(final int creepScore) {
        this.creepScore = creepScore;
    }

    /**
     * @param crowdControlDealt
     *        the crowdControlDealt to set
     */
    public void setCrowdControlDealt(final Duration crowdControlDealt) {
        this.crowdControlDealt = crowdControlDealt;
    }

    /**
     * @param crowdControlDealtToChampions
     *        the crowdControlDealtToChampions to set
     */
    public void setCrowdControlDealtToChampions(final Duration crowdControlDealtToChampions) {
        this.crowdControlDealtToChampions = crowdControlDealtToChampions;
    }

    /**
     * @param damageDealt
     *        the damageDealt to set
     */
    public void setDamageDealt(final int damageDealt) {
        this.damageDealt = damageDealt;
    }

    /**
     * @param damageDealtToChampions
     *        the damageDealtToChampions to set
     */
    public void setDamageDealtToChampions(final int damageDealtToChampions) {
        this.damageDealtToChampions = damageDealtToChampions;
    }

    /**
     * @param damageHealed
     *        the damageHealed to set
     */
    public void setDamageHealed(final int damageHealed) {
        this.damageHealed = damageHealed;
    }

    /**
     * @param damageMitigated
     *        the damageMitigated to set
     */
    public void setDamageMitigated(final int damageMitigated) {
        this.damageMitigated = damageMitigated;
    }

    /**
     * @param damageTaken
     *        the damageTaken to set
     */
    public void setDamageTaken(final int damageTaken) {
        this.damageTaken = damageTaken;
    }

    /**
     * @param damageToObjectives
     *        the damageToObjectives to set
     */
    public void setDamageToObjectives(final int damageToObjectives) {
        this.damageToObjectives = damageToObjectives;
    }

    /**
     * @param damageToTurrets
     *        the damageToTurrets to set
     */
    public void setDamageToTurrets(final int damageToTurrets) {
        this.damageToTurrets = damageToTurrets;
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
     * @param firstBloodAssistant
     *        the firstBloodAssistant to set
     */
    public void setFirstBloodAssistant(final boolean firstBloodAssistant) {
        this.firstBloodAssistant = firstBloodAssistant;
    }

    /**
     * @param firstBloodKiller
     *        the firstBloodKiller to set
     */
    public void setFirstBloodKiller(final boolean firstBloodKiller) {
        this.firstBloodKiller = firstBloodKiller;
    }

    /**
     * @param firstInhibitorKillAssistant
     *        the firstInhibitorKillAssistant to set
     */
    public void setFirstInhibitorKillAssistant(final boolean firstInhibitorKillAssistant) {
        this.firstInhibitorKillAssistant = firstInhibitorKillAssistant;
    }

    /**
     * @param firstInhibitorKiller
     *        the firstInhibitorKiller to set
     */
    public void setFirstInhibitorKiller(final boolean firstInhibitorKiller) {
        this.firstInhibitorKiller = firstInhibitorKiller;
    }

    /**
     * @param firstTowerKillAssistant
     *        the firstTowerKillAssistant to set
     */
    public void setFirstTowerKillAssistant(final boolean firstTowerKillAssistant) {
        this.firstTowerKillAssistant = firstTowerKillAssistant;
    }

    /**
     * @param firstTowerKiller
     *        the firstTowerKiller to set
     */
    public void setFirstTowerKiller(final boolean firstTowerKiller) {
        this.firstTowerKiller = firstTowerKiller;
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
     * @param greenWardsPurchased
     *        the greenWardsPurchased to set
     */
    public void setGreenWardsPurchased(final int greenWardsPurchased) {
        this.greenWardsPurchased = greenWardsPurchased;
    }

    /**
     * @param hexaKills
     *        the hexaKills to set
     */
    public void setHexaKills(final int hexaKills) {
        this.hexaKills = hexaKills;
    }

    /**
     * @param inhibitorKills
     *        the inhibitorKills to set
     */
    public void setInhibitorKills(final int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    /**
     * @param items
     *        the items to set
     */
    public void setItems(final List<Integer> items) {
        this.items = items;
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
     * @param longestTimeAlive
     *        the longestTimeAlive to set
     */
    public void setLongestTimeAlive(final Duration longestTimeAlive) {
        this.longestTimeAlive = longestTimeAlive;
    }

    /**
     * @param magicDamageDealt
     *        the magicDamageDealt to set
     */
    public void setMagicDamageDealt(final int magicDamageDealt) {
        this.magicDamageDealt = magicDamageDealt;
    }

    /**
     * @param magicDamageDealtToChampions
     *        the magicDamageDealtToChampions to set
     */
    public void setMagicDamageDealtToChampions(final int magicDamageDealtToChampions) {
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
    }

    /**
     * @param magicDamageTaken
     *        the magicDamageTaken to set
     */
    public void setMagicDamageTaken(final int magicDamageTaken) {
        this.magicDamageTaken = magicDamageTaken;
    }

    /**
     * @param neutralMinionsKilled
     *        the neutralMinionsKilled to set
     */
    public void setNeutralMinionsKilled(final int neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }

    /**
     * @param neutralMinionsKilledInAllyJungle
     *        the neutralMinionsKilledInAllyJungle to set
     */
    public void setNeutralMinionsKilledInAllyJungle(final int neutralMinionsKilledInAllyJungle) {
        this.neutralMinionsKilledInAllyJungle = neutralMinionsKilledInAllyJungle;
    }

    /**
     * @param neutralMinionsKilledInEnemyJungle
     *        the neutralMinionsKilledInEnemyJungle to set
     */
    public void setNeutralMinionsKilledInEnemyJungle(final int neutralMinionsKilledInEnemyJungle) {
        this.neutralMinionsKilledInEnemyJungle = neutralMinionsKilledInEnemyJungle;
    }

    /**
     * @param nodeCaptureAssists
     *        the nodeCaptureAssists to set
     */
    public void setNodeCaptureAssists(final int nodeCaptureAssists) {
        this.nodeCaptureAssists = nodeCaptureAssists;
    }

    /**
     * @param nodeNeutralizeAssists
     *        the nodeNeutralizeAssists to set
     */
    public void setNodeNeutralizeAssists(final int nodeNeutralizeAssists) {
        this.nodeNeutralizeAssists = nodeNeutralizeAssists;
    }

    /**
     * @param nodesCaptured
     *        the nodesCaptured to set
     */
    public void setNodesCaptured(final int nodesCaptured) {
        this.nodesCaptured = nodesCaptured;
    }

    /**
     * @param nodesNeutralized
     *        the nodesNeutralized to set
     */
    public void setNodesNeutralized(final int nodesNeutralized) {
        this.nodesNeutralized = nodesNeutralized;
    }

    /**
     * @param objectiveScore
     *        the objectiveScore to set
     */
    public void setObjectiveScore(final int objectiveScore) {
        this.objectiveScore = objectiveScore;
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
    public void setPhysicalDamageDealt(final int physicalDamageDealt) {
        this.physicalDamageDealt = physicalDamageDealt;
    }

    /**
     * @param physicalDamageDealtToChampions
     *        the physicalDamageDealtToChampions to set
     */
    public void setPhysicalDamageDealtToChampions(final int physicalDamageDealtToChampions) {
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
    }

    /**
     * @param physicalDamageTaken
     *        the physicalDamageTaken to set
     */
    public void setPhysicalDamageTaken(final int physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }

    /**
     * @param pinkWardsPurchased
     *        the pinkWardsPurchased to set
     */
    public void setPinkWardsPurchased(final int pinkWardsPurchased) {
        this.pinkWardsPurchased = pinkWardsPurchased;
    }

    /**
     * @param quadraKills
     *        the quadraKills to set
     */
    public void setQuadraKills(final int quadraKills) {
        this.quadraKills = quadraKills;
    }

    /**
     * @param score
     *        the score to set
     */
    public void setScore(final int score) {
        this.score = score;
    }

    /**
     * @param scoreRank
     *        the scoreRank to set
     */
    public void setScoreRank(final int scoreRank) {
        this.scoreRank = scoreRank;
    }

    /**
     * @param teamObjectives
     *        the teamObjectives to set
     */
    public void setTeamObjectives(final int teamObjectives) {
        this.teamObjectives = teamObjectives;
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
    public void setTrueDamageDealt(final int trueDamageDealt) {
        this.trueDamageDealt = trueDamageDealt;
    }

    /**
     * @param trueDamageDealtToChampions
     *        the trueDamageDealtToChampions to set
     */
    public void setTrueDamageDealtToChampions(final int trueDamageDealtToChampions) {
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
    }

    /**
     * @param trueDamageTaken
     *        the trueDamageTaken to set
     */
    public void setTrueDamageTaken(final int trueDamageTaken) {
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
     * @param unitsHealed
     *        the unitsHealed to set
     */
    public void setUnitsHealed(final int unitsHealed) {
        this.unitsHealed = unitsHealed;
    }

    /**
     * @param visionScore
     *        the visionScore to set
     */
    public void setVisionScore(final int visionScore) {
        this.visionScore = visionScore;
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
     * @param winner
     *        the winner to set
     */
    public void setWinner(final boolean winner) {
        this.winner = winner;
    }
}
