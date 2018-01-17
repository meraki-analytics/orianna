package com.merakianalytics.orianna.types.core.match;

import org.joda.time.Duration;

import com.merakianalytics.orianna.types.core.OriannaObject;

public class ParticipantStats extends OriannaObject<com.merakianalytics.orianna.types.data.match.ParticipantStats> {
    private static final long serialVersionUID = -989098350329762392L;

    public ParticipantStats(final com.merakianalytics.orianna.types.data.match.ParticipantStats coreData) {
        super(coreData);
    }

    public int getAltarsCaptured() {
        return coreData.getAltarsCaptured();
    }

    public int getAltarsNeutralized() {
        return coreData.getAltarsNeutralized();
    }

    public int getAssists() {
        return coreData.getAssists();
    }

    public int getChampionLevel() {
        return coreData.getChampionLevel();
    }

    public int getCombatScore() {
        return coreData.getCombatScore();
    }

    public int getCreepScore() {
        return coreData.getCreepScore();
    }

    public Duration getCrowdControlDealt() {
        return coreData.getCrowdControlDealt();
    }

    public Duration getCrowdControlDealtToChampions() {
        return coreData.getCrowdControlDealtToChampions();
    }

    public int getDamageDealt() {
        return coreData.getDamageDealt();
    }

    public int getDamageDealtToChampions() {
        return coreData.getDamageDealtToChampions();
    }

    public int getDamageHealed() {
        return coreData.getDamageHealed();
    }

    public int getDamageMitigated() {
        return coreData.getDamageMitigated();
    }

    public int getDamageTaken() {
        return coreData.getDamageTaken();
    }

    public int getDamageToObjectives() {
        return coreData.getDamageToObjectives();
    }

    public int getDamageToTurrets() {
        return coreData.getDamageToTurrets();
    }

    public int getDeaths() {
        return coreData.getDeaths();
    }

    public int getDoubleKills() {
        return coreData.getDoubleKills();
    }

    public int getGoldEarned() {
        return coreData.getGoldEarned();
    }

    public int getGoldSpent() {
        return coreData.getGoldSpent();
    }

    public int getGreenWardsPurchased() {
        return coreData.getGreenWardsPurchased();
    }

    public int getHexaKills() {
        return coreData.getHexaKills();
    }

    public int getInhibitorKills() {
        return coreData.getInhibitorKills();
    }

    public int getKillingSprees() {
        return coreData.getKillingSprees();
    }

    public int getKills() {
        return coreData.getKills();
    }

    public int getLargestCriticalStrike() {
        return coreData.getLargestCriticalStrike();
    }

    public int getLargestKillingSpree() {
        return coreData.getLargestKillingSpree();
    }

    public int getLargestMultiKill() {
        return coreData.getLargestMultiKill();
    }

    public Duration getLongestTimeAlive() {
        return coreData.getLongestTimeAlive();
    }

    public int getMagicDamageDealt() {
        return coreData.getMagicDamageDealt();
    }

    public int getMagicDamageDealtToChampions() {
        return coreData.getMagicDamageDealtToChampions();
    }

    public int getMagicDamageTaken() {
        return coreData.getMagicDamageTaken();
    }

    public int getNeutralMinionsKilled() {
        return coreData.getNeutralMinionsKilled();
    }

    public int getNeutralMinionsKilledInAllyJungle() {
        return coreData.getNeutralMinionsKilledInAllyJungle();
    }

    public int getNeutralMinionsKilledInEnemyJungle() {
        return coreData.getNeutralMinionsKilledInEnemyJungle();
    }

    public int getNodeCaptureAssists() {
        return coreData.getNodeCaptureAssists();
    }

    public int getNodeNeutralizeAssists() {
        return coreData.getNodeNeutralizeAssists();
    }

    public int getNodesCaptured() {
        return coreData.getNodesCaptured();
    }

    public int getNodesNeutralized() {
        return coreData.getNodesNeutralized();
    }

    public int getObjectiveScore() {
        return coreData.getObjectiveScore();
    }

    public int getPentaKills() {
        return coreData.getPentaKills();
    }

    public int getPhysicalDamageDealt() {
        return coreData.getPhysicalDamageDealt();
    }

    public int getPhysicalDamageDealtToChampions() {
        return coreData.getPhysicalDamageDealtToChampions();
    }

    public int getPhysicalDamageTaken() {
        return coreData.getPhysicalDamageTaken();
    }

    public int getPinkWardsPurchased() {
        return coreData.getPinkWardsPurchased();
    }

    public int getPlayerScore0() {
        return coreData.getPlayerScore0();
    }

    public int getPlayerScore1() {
        return coreData.getPlayerScore1();
    }

    public int getPlayerScore2() {
        return coreData.getPlayerScore2();
    }

    public int getPlayerScore3() {
        return coreData.getPlayerScore3();
    }

    public int getPlayerScore4() {
        return coreData.getPlayerScore4();
    }

    public int getPlayerScore5() {
        return coreData.getPlayerScore5();
    }

    public int getPlayerScore6() {
        return coreData.getPlayerScore6();
    }

    public int getPlayerScore7() {
        return coreData.getPlayerScore7();
    }

    public int getPlayerScore8() {
        return coreData.getPlayerScore8();
    }

    public int getPlayerScore9() {
        return coreData.getPlayerScore9();
    }

    public int getQuadraKills() {
        return coreData.getQuadraKills();
    }

    public int getScore() {
        return coreData.getScore();
    }

    public int getScoreRank() {
        return coreData.getScoreRank();
    }

    public int getTeamObjectives() {
        return coreData.getTeamObjectives();
    }

    public int getTripleKills() {
        return coreData.getTripleKills();
    }

    public int getTrueDamageDealt() {
        return coreData.getTrueDamageDealt();
    }

    public int getTrueDamageDealtToChampions() {
        return coreData.getTrueDamageDealtToChampions();
    }

    public int getTrueDamageTaken() {
        return coreData.getTrueDamageTaken();
    }

    public int getTurretKills() {
        return coreData.getTurretKills();
    }

    public int getUnitsHealed() {
        return coreData.getUnitsHealed();
    }

    public int getVisionScore() {
        return coreData.getVisionScore();
    }

    public int getWardsKilled() {
        return coreData.getWardsKilled();
    }

    public int getWardsPlaced() {
        return coreData.getWardsPlaced();
    }

    public boolean isFirstBloodAssistant() {
        return coreData.isFirstBloodAssistant();
    }

    public boolean isFirstBloodKiller() {
        return coreData.isFirstBloodKiller();
    }

    public boolean isFirstInhibitorKillAssistant() {
        return coreData.isFirstInhibitorKillAssistant();
    }

    public boolean isFirstInhibitorKiller() {
        return coreData.isFirstInhibitorKiller();
    }

    public boolean isFirstTowerKillAssistant() {
        return coreData.isFirstTowerKillAssistant();
    }

    public boolean isFirstTowerKiller() {
        return coreData.isFirstTowerKiller();
    }

    public boolean isWinner() {
        return coreData.isWinner();
    }
}
