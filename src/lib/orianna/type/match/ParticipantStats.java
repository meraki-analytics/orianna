package lib.orianna.type.match;

import java.io.Serializable;

import lib.orianna.type.staticdata.Item;

public class ParticipantStats implements Serializable {
    private static final long serialVersionUID = 4318206811781227031L;
    public final Long assists, champLevel, combatPlayerScore, deaths, doubleKills, goldEarned, goldSpent, inhibitorKills, killingSpress, kills,
            largestCriticalStrike, largestKillingSpree, largestMultiKill, magicDamageDealt, magicDamageDealtToChampions, magicDamageTaken, minionsKilled,
            neutralMinionsKilled, neutralMinionsKilledEnemyJungle, neutralMinionsKilledTeamJungle, nodeCapture, nodeCaptureAssist, nodeNeutralize,
            nodeNeutralizeAssist, objectivePlayerScore, pentaKills, physicalDamageDealt, physicalDamageDealtToChampions, physicalDamageTaken, quadraKills,
            sightWardsBoughtInGame, teamObjective, totalDamageDealt, totalDamageDealtToChampions, totalDamageTaken, totalHeal, totalPlayerScore,
            totalScoreRank, totalTimeCrowdControlDealt, totalUnitsHealed, towerKills, tripleKills, trueDamageDealt, trueDamageDealtToChampions,
            trueDamageTaken, unrealKills, visionWardsBoughtInGame, wardsKilled, wardsPlaced;
    public final Item item0, item1, item2, item3, item4, item5, item6;
    public final Boolean winner, firstBloodAssist, firstBloodKill, firstInhibitorAssist, firstInhibitorKill, firstTowerAssist, firstTowerKill;

    public ParticipantStats(final Long assists, final Long champLevel, final Long combatPlayerScore, final Long deaths, final Long doubleKills,
            final Long goldEarned, final Long goldSpent, final Long inhibitorKills, final Long killingSpress, final Long kills,
            final Long largestCriticalStrike, final Long largestKillingSpree, final Long largestMultiKill, final Long magicDamageDealt,
            final Long magicDamageDealtToChampions, final Long magicDamageTaken, final Long minionsKilled, final Long neutralMinionsKilled,
            final Long neutralMinionsKilledEnemyJungle, final Long neutralMinionsKilledTeamJungle, final Long nodeCapture, final Long nodeCaptureAssist,
            final Long nodeNeutralize, final Long nodeNeutralizeAssist, final Long objectivePlayerScore, final Long pentaKills, final Long physicalDamageDealt,
            final Long physicalDamageDealtToChampions, final Long physicalDamageTaken, final Long quadraKills, final Long sightWardsBoughtInGame,
            final Long teamObjective, final Long totalDamageDealt, final Long totalDamageDealtToChampions, final Long totalDamageTaken, final Long totalHeal,
            final Long totalPlayerScore, final Long totalScoreRank, final Long totalTimeCrowdControlDealt, final Long totalUnitsHealed, final Long towerKills,
            final Long tripleKills, final Long trueDamageDealt, final Long trueDamageDealtToChampions, final Long trueDamageTaken, final Long unrealKills,
            final Long visionWardsBoughtInGame, final Long wardsKilled, final Long wardsPlaced, final Boolean winner, final Boolean firstBloodAssist,
            final Boolean firstBloodKill, final Boolean firstInhibitorAssist, final Boolean firstInhibitorKill, final Boolean firstTowerAssist,
            final Boolean firstTowerKill, final Item item0, final Item item1, final Item item2, final Item item3, final Item item4, final Item item5,
            final Item item6) {
        this.assists = assists;
        this.champLevel = champLevel;
        this.combatPlayerScore = combatPlayerScore;
        this.deaths = deaths;
        this.doubleKills = doubleKills;
        this.goldEarned = goldEarned;
        this.goldSpent = goldSpent;
        this.inhibitorKills = inhibitorKills;
        this.killingSpress = killingSpress;
        this.kills = kills;
        this.largestCriticalStrike = largestCriticalStrike;
        this.largestKillingSpree = largestKillingSpree;
        this.largestMultiKill = largestMultiKill;
        this.magicDamageDealt = magicDamageDealt;
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
        this.magicDamageTaken = magicDamageTaken;
        this.minionsKilled = minionsKilled;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
        this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
        this.nodeCapture = nodeCapture;
        this.nodeCaptureAssist = nodeCaptureAssist;
        this.nodeNeutralize = nodeNeutralize;
        this.nodeNeutralizeAssist = nodeNeutralizeAssist;
        this.objectivePlayerScore = objectivePlayerScore;
        this.pentaKills = pentaKills;
        this.physicalDamageDealt = physicalDamageDealt;
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
        this.physicalDamageTaken = physicalDamageTaken;
        this.quadraKills = quadraKills;
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
        this.teamObjective = teamObjective;
        this.totalDamageDealt = totalDamageDealt;
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
        this.totalDamageTaken = totalDamageTaken;
        this.totalHeal = totalHeal;
        this.totalPlayerScore = totalPlayerScore;
        this.totalScoreRank = totalScoreRank;
        this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
        this.totalUnitsHealed = totalUnitsHealed;
        this.towerKills = towerKills;
        this.tripleKills = tripleKills;
        this.trueDamageDealt = trueDamageDealt;
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
        this.trueDamageTaken = trueDamageTaken;
        this.unrealKills = unrealKills;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
        this.wardsKilled = wardsKilled;
        this.wardsPlaced = wardsPlaced;
        this.winner = winner;
        this.firstBloodAssist = firstBloodAssist;
        this.firstBloodKill = firstBloodKill;
        this.firstInhibitorAssist = firstInhibitorAssist;
        this.firstInhibitorKill = firstInhibitorKill;
        this.firstTowerAssist = firstTowerAssist;
        this.firstTowerKill = firstTowerKill;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
    }

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
        if(killingSpress == null) {
            if(other.killingSpress != null) {
                return false;
            }
        }
        else if(!killingSpress.equals(other.killingSpress)) {
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
        result = prime * result + (killingSpress == null ? 0 : killingSpress.hashCode());
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

    @Override
    public String toString() {
        return "ParticipantStats [assists=" + assists + ", deaths=" + deaths + ", kills=" + kills + "]";
    }
}
