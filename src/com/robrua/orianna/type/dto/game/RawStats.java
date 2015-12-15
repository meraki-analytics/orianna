package com.robrua.orianna.type.dto.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "gamestats")
public class RawStats extends OriannaDto {
    private static final long serialVersionUID = -7331494090056610673L;
    private Integer assists, barracksKilled, championsKilled, combatPlayerScore, consumablesPurchased, damageDealtPlayer, doubleKills, firstBlood, gold,
            goldEarned, goldSpent, item0, item1, item2, item3, item4, item5, item6, itemsPurchased, killingSprees, largestCriticalStrike, largestKillingSpree,
            largestMultiKill, legendaryItemsCreated, level, magicDamageDealtPlayer, magicDamageDealtToChampions, magicDamageTaken, minionsDenied, minionsKilled,
            neutralMinionsKilled, neutralMinionsKilledEnemyJungle, neutralMinionsKilledYourJungle, nodeCapture, nodeCaptureAssist, nodeNeutralize,
            nodeNeutralizeAssist, numDeaths, numItemsBought, objectivePlayerScore, pentaKills, physicalDamageDealtPlayer, physicalDamageDealtToChampions,
            physicalDamageTaken, quadraKills, sightWardsBought, spell1Cast, spell2Cast, spell3Cast, spell4Cast, summonSpell1Cast, summonSpell2Cast,
            superMonsterKilled, team, teamObjective, timePlayed, totalDamageDealt, totalDamageDealtToChampions, totalDamageTaken, totalHeal, totalPlayerScore,
            totalScoreRank, totalTimeCrowdControlDealt, totalUnitsHealed, tripleKills, trueDamageDealtPlayer, trueDamageDealtToChampions, trueDamageTaken,
            turretsKilled, unrealKills, victoryPointTotal, visionWardsBought, wardKilled, wardPlaced;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Boolean win, nexusKilled;

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
        if(!(obj instanceof RawStats)) {
            return false;
        }
        final RawStats other = (RawStats)obj;
        if(assists == null) {
            if(other.assists != null) {
                return false;
            }
        }
        else if(!assists.equals(other.assists)) {
            return false;
        }
        if(barracksKilled == null) {
            if(other.barracksKilled != null) {
                return false;
            }
        }
        else if(!barracksKilled.equals(other.barracksKilled)) {
            return false;
        }
        if(championsKilled == null) {
            if(other.championsKilled != null) {
                return false;
            }
        }
        else if(!championsKilled.equals(other.championsKilled)) {
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
        if(consumablesPurchased == null) {
            if(other.consumablesPurchased != null) {
                return false;
            }
        }
        else if(!consumablesPurchased.equals(other.consumablesPurchased)) {
            return false;
        }
        if(damageDealtPlayer == null) {
            if(other.damageDealtPlayer != null) {
                return false;
            }
        }
        else if(!damageDealtPlayer.equals(other.damageDealtPlayer)) {
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
        if(firstBlood == null) {
            if(other.firstBlood != null) {
                return false;
            }
        }
        else if(!firstBlood.equals(other.firstBlood)) {
            return false;
        }
        if(gold == null) {
            if(other.gold != null) {
                return false;
            }
        }
        else if(!gold.equals(other.gold)) {
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
        if(itemsPurchased == null) {
            if(other.itemsPurchased != null) {
                return false;
            }
        }
        else if(!itemsPurchased.equals(other.itemsPurchased)) {
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
        if(legendaryItemsCreated == null) {
            if(other.legendaryItemsCreated != null) {
                return false;
            }
        }
        else if(!legendaryItemsCreated.equals(other.legendaryItemsCreated)) {
            return false;
        }
        if(level == null) {
            if(other.level != null) {
                return false;
            }
        }
        else if(!level.equals(other.level)) {
            return false;
        }
        if(magicDamageDealtPlayer == null) {
            if(other.magicDamageDealtPlayer != null) {
                return false;
            }
        }
        else if(!magicDamageDealtPlayer.equals(other.magicDamageDealtPlayer)) {
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
        if(minionsDenied == null) {
            if(other.minionsDenied != null) {
                return false;
            }
        }
        else if(!minionsDenied.equals(other.minionsDenied)) {
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
        if(neutralMinionsKilledYourJungle == null) {
            if(other.neutralMinionsKilledYourJungle != null) {
                return false;
            }
        }
        else if(!neutralMinionsKilledYourJungle.equals(other.neutralMinionsKilledYourJungle)) {
            return false;
        }
        if(nexusKilled == null) {
            if(other.nexusKilled != null) {
                return false;
            }
        }
        else if(!nexusKilled.equals(other.nexusKilled)) {
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
        if(numDeaths == null) {
            if(other.numDeaths != null) {
                return false;
            }
        }
        else if(!numDeaths.equals(other.numDeaths)) {
            return false;
        }
        if(numItemsBought == null) {
            if(other.numItemsBought != null) {
                return false;
            }
        }
        else if(!numItemsBought.equals(other.numItemsBought)) {
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
        if(physicalDamageDealtPlayer == null) {
            if(other.physicalDamageDealtPlayer != null) {
                return false;
            }
        }
        else if(!physicalDamageDealtPlayer.equals(other.physicalDamageDealtPlayer)) {
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
        if(sightWardsBought == null) {
            if(other.sightWardsBought != null) {
                return false;
            }
        }
        else if(!sightWardsBought.equals(other.sightWardsBought)) {
            return false;
        }
        if(spell1Cast == null) {
            if(other.spell1Cast != null) {
                return false;
            }
        }
        else if(!spell1Cast.equals(other.spell1Cast)) {
            return false;
        }
        if(spell2Cast == null) {
            if(other.spell2Cast != null) {
                return false;
            }
        }
        else if(!spell2Cast.equals(other.spell2Cast)) {
            return false;
        }
        if(spell3Cast == null) {
            if(other.spell3Cast != null) {
                return false;
            }
        }
        else if(!spell3Cast.equals(other.spell3Cast)) {
            return false;
        }
        if(spell4Cast == null) {
            if(other.spell4Cast != null) {
                return false;
            }
        }
        else if(!spell4Cast.equals(other.spell4Cast)) {
            return false;
        }
        if(summonSpell1Cast == null) {
            if(other.summonSpell1Cast != null) {
                return false;
            }
        }
        else if(!summonSpell1Cast.equals(other.summonSpell1Cast)) {
            return false;
        }
        if(summonSpell2Cast == null) {
            if(other.summonSpell2Cast != null) {
                return false;
            }
        }
        else if(!summonSpell2Cast.equals(other.summonSpell2Cast)) {
            return false;
        }
        if(superMonsterKilled == null) {
            if(other.superMonsterKilled != null) {
                return false;
            }
        }
        else if(!superMonsterKilled.equals(other.superMonsterKilled)) {
            return false;
        }
        if(team == null) {
            if(other.team != null) {
                return false;
            }
        }
        else if(!team.equals(other.team)) {
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
        if(timePlayed == null) {
            if(other.timePlayed != null) {
                return false;
            }
        }
        else if(!timePlayed.equals(other.timePlayed)) {
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
        if(tripleKills == null) {
            if(other.tripleKills != null) {
                return false;
            }
        }
        else if(!tripleKills.equals(other.tripleKills)) {
            return false;
        }
        if(trueDamageDealtPlayer == null) {
            if(other.trueDamageDealtPlayer != null) {
                return false;
            }
        }
        else if(!trueDamageDealtPlayer.equals(other.trueDamageDealtPlayer)) {
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
        if(turretsKilled == null) {
            if(other.turretsKilled != null) {
                return false;
            }
        }
        else if(!turretsKilled.equals(other.turretsKilled)) {
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
        if(victoryPointTotal == null) {
            if(other.victoryPointTotal != null) {
                return false;
            }
        }
        else if(!victoryPointTotal.equals(other.victoryPointTotal)) {
            return false;
        }
        if(visionWardsBought == null) {
            if(other.visionWardsBought != null) {
                return false;
            }
        }
        else if(!visionWardsBought.equals(other.visionWardsBought)) {
            return false;
        }
        if(wardKilled == null) {
            if(other.wardKilled != null) {
                return false;
            }
        }
        else if(!wardKilled.equals(other.wardKilled)) {
            return false;
        }
        if(wardPlaced == null) {
            if(other.wardPlaced != null) {
                return false;
            }
        }
        else if(!wardPlaced.equals(other.wardPlaced)) {
            return false;
        }
        if(win == null) {
            if(other.win != null) {
                return false;
            }
        }
        else if(!win.equals(other.win)) {
            return false;
        }
        return true;
    }

    /**
     * @return the assists
     */
    public Integer getAssists() {
        return assists;
    }

    /**
     * @return the barracksKilled
     */
    public Integer getBarracksKilled() {
        return barracksKilled;
    }

    /**
     * @return the championsKilled
     */
    public Integer getChampionsKilled() {
        return championsKilled;
    }

    /**
     * @return the combatPlayerScore
     */
    public Integer getCombatPlayerScore() {
        return combatPlayerScore;
    }

    /**
     * @return the consumablesPurchased
     */
    public Integer getConsumablesPurchased() {
        return consumablesPurchased;
    }

    /**
     * @return the damageDealtPlayer
     */
    public Integer getDamageDealtPlayer() {
        return damageDealtPlayer;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the doubleKills
     */
    public Integer getDoubleKills() {
        return doubleKills;
    }

    /**
     * @return the firstBlood
     */
    public Integer getFirstBlood() {
        return firstBlood;
    }

    /**
     * @return the gold
     */
    public Integer getGold() {
        return gold;
    }

    /**
     * @return the goldEarned
     */
    public Integer getGoldEarned() {
        return goldEarned;
    }

    /**
     * @return the goldSpent
     */
    public Integer getGoldSpent() {
        return goldSpent;
    }

    /**
     * @return the item0
     */
    public Integer getItem0() {
        return item0;
    }

    /**
     * @return the item1
     */
    public Integer getItem1() {
        return item1;
    }

    /**
     * @return the item2
     */
    public Integer getItem2() {
        return item2;
    }

    /**
     * @return the item3
     */
    public Integer getItem3() {
        return item3;
    }

    /**
     * @return the item4
     */
    public Integer getItem4() {
        return item4;
    }

    /**
     * @return the item5
     */
    public Integer getItem5() {
        return item5;
    }

    /**
     * @return the item6
     */
    public Integer getItem6() {
        return item6;
    }

    /**
     * @return the itemsPurchased
     */
    public Integer getItemsPurchased() {
        return itemsPurchased;
    }

    /**
     * @return the killingSprees
     */
    public Integer getKillingSprees() {
        return killingSprees;
    }

    /**
     * @return the largestCriticalStrike
     */
    public Integer getLargestCriticalStrike() {
        return largestCriticalStrike;
    }

    /**
     * @return the largestKillingSpree
     */
    public Integer getLargestKillingSpree() {
        return largestKillingSpree;
    }

    /**
     * @return the largestMultiKill
     */
    public Integer getLargestMultiKill() {
        return largestMultiKill;
    }

    /**
     * @return the legendaryItemsCreated
     */
    public Integer getLegendaryItemsCreated() {
        return legendaryItemsCreated;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @return the magicDamageDealtPlayer
     */
    public Integer getMagicDamageDealtPlayer() {
        return magicDamageDealtPlayer;
    }

    /**
     * @return the magicDamageDealtToChampions
     */
    public Integer getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    /**
     * @return the magicDamageTaken
     */
    public Integer getMagicDamageTaken() {
        return magicDamageTaken;
    }

    /**
     * @return the minionsDenied
     */
    public Integer getMinionsDenied() {
        return minionsDenied;
    }

    /**
     * @return the minionsKilled
     */
    public Integer getMinionsKilled() {
        return minionsKilled;
    }

    /**
     * @return the neutralMinionsKilled
     */
    public Integer getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    /**
     * @return the neutralMinionsKilledEnemyJungle
     */
    public Integer getNeutralMinionsKilledEnemyJungle() {
        return neutralMinionsKilledEnemyJungle;
    }

    /**
     * @return the neutralMinionsKilledYourJungle
     */
    public Integer getNeutralMinionsKilledYourJungle() {
        return neutralMinionsKilledYourJungle;
    }

    /**
     * @return the nexusKilled
     */
    public Boolean getNexusKilled() {
        return nexusKilled;
    }

    /**
     * @return the nodeCapture
     */
    public Integer getNodeCapture() {
        return nodeCapture;
    }

    /**
     * @return the nodeCaptureAssist
     */
    public Integer getNodeCaptureAssist() {
        return nodeCaptureAssist;
    }

    /**
     * @return the nodeNeutralize
     */
    public Integer getNodeNeutralize() {
        return nodeNeutralize;
    }

    /**
     * @return the nodeNeutralizeAssist
     */
    public Integer getNodeNeutralizeAssist() {
        return nodeNeutralizeAssist;
    }

    /**
     * @return the numDeaths
     */
    public Integer getNumDeaths() {
        return numDeaths;
    }

    /**
     * @return the numItemsBought
     */
    public Integer getNumItemsBought() {
        return numItemsBought;
    }

    /**
     * @return the objectivePlayerScore
     */
    public Integer getObjectivePlayerScore() {
        return objectivePlayerScore;
    }

    /**
     * @return the pentaKills
     */
    public Integer getPentaKills() {
        return pentaKills;
    }

    /**
     * @return the physicalDamageDealtPlayer
     */
    public Integer getPhysicalDamageDealtPlayer() {
        return physicalDamageDealtPlayer;
    }

    /**
     * @return the physicalDamageDealtToChampions
     */
    public Integer getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    /**
     * @return the physicalDamageTaken
     */
    public Integer getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    /**
     * @return the quadraKills
     */
    public Integer getQuadraKills() {
        return quadraKills;
    }

    /**
     * @return the sightWardsBought
     */
    public Integer getSightWardsBought() {
        return sightWardsBought;
    }

    /**
     * @return the spell1Cast
     */
    public Integer getSpell1Cast() {
        return spell1Cast;
    }

    /**
     * @return the spell2Cast
     */
    public Integer getSpell2Cast() {
        return spell2Cast;
    }

    /**
     * @return the spell3Cast
     */
    public Integer getSpell3Cast() {
        return spell3Cast;
    }

    /**
     * @return the spell4Cast
     */
    public Integer getSpell4Cast() {
        return spell4Cast;
    }

    /**
     * @return the summonSpell1Cast
     */
    public Integer getSummonSpell1Cast() {
        return summonSpell1Cast;
    }

    /**
     * @return the summonSpell2Cast
     */
    public Integer getSummonSpell2Cast() {
        return summonSpell2Cast;
    }

    /**
     * @return the superMonsterKilled
     */
    public Integer getSuperMonsterKilled() {
        return superMonsterKilled;
    }

    /**
     * @return the team
     */
    public Integer getTeam() {
        return team;
    }

    /**
     * @return the teamObjective
     */
    public Integer getTeamObjective() {
        return teamObjective;
    }

    /**
     * @return the timePlayed
     */
    public Integer getTimePlayed() {
        return timePlayed;
    }

    /**
     * @return the totalDamageDealt
     */
    public Integer getTotalDamageDealt() {
        return totalDamageDealt;
    }

    /**
     * @return the totalDamageDealtToChampions
     */
    public Integer getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    /**
     * @return the totalDamageTaken
     */
    public Integer getTotalDamageTaken() {
        return totalDamageTaken;
    }

    /**
     * @return the totalHeal
     */
    public Integer getTotalHeal() {
        return totalHeal;
    }

    /**
     * @return the totalPlayerScore
     */
    public Integer getTotalPlayerScore() {
        return totalPlayerScore;
    }

    /**
     * @return the totalScoreRank
     */
    public Integer getTotalScoreRank() {
        return totalScoreRank;
    }

    /**
     * @return the totalTimeCrowdControlDealt
     */
    public Integer getTotalTimeCrowdControlDealt() {
        return totalTimeCrowdControlDealt;
    }

    /**
     * @return the totalUnitsHealed
     */
    public Integer getTotalUnitsHealed() {
        return totalUnitsHealed;
    }

    /**
     * @return the tripleKills
     */
    public Integer getTripleKills() {
        return tripleKills;
    }

    /**
     * @return the trueDamageDealtPlayer
     */
    public Integer getTrueDamageDealtPlayer() {
        return trueDamageDealtPlayer;
    }

    /**
     * @return the trueDamageDealtToChampions
     */
    public Integer getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    /**
     * @return the trueDamageTaken
     */
    public Integer getTrueDamageTaken() {
        return trueDamageTaken;
    }

    /**
     * @return the turretsKilled
     */
    public Integer getTurretsKilled() {
        return turretsKilled;
    }

    /**
     * @return the unrealKills
     */
    public Integer getUnrealKills() {
        return unrealKills;
    }

    /**
     * @return the victoryPointTotal
     */
    public Integer getVictoryPointTotal() {
        return victoryPointTotal;
    }

    /**
     * @return the visionWardsBought
     */
    public Integer getVisionWardsBought() {
        return visionWardsBought;
    }

    /**
     * @return the wardKilled
     */
    public Integer getWardKilled() {
        return wardKilled;
    }

    /**
     * @return the wardPlaced
     */
    public Integer getWardPlaced() {
        return wardPlaced;
    }

    /**
     * @return the win
     */
    public Boolean getWin() {
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
        result = prime * result + (assists == null ? 0 : assists.hashCode());
        result = prime * result + (barracksKilled == null ? 0 : barracksKilled.hashCode());
        result = prime * result + (championsKilled == null ? 0 : championsKilled.hashCode());
        result = prime * result + (combatPlayerScore == null ? 0 : combatPlayerScore.hashCode());
        result = prime * result + (consumablesPurchased == null ? 0 : consumablesPurchased.hashCode());
        result = prime * result + (damageDealtPlayer == null ? 0 : damageDealtPlayer.hashCode());
        result = prime * result + (doubleKills == null ? 0 : doubleKills.hashCode());
        result = prime * result + (firstBlood == null ? 0 : firstBlood.hashCode());
        result = prime * result + (gold == null ? 0 : gold.hashCode());
        result = prime * result + (goldEarned == null ? 0 : goldEarned.hashCode());
        result = prime * result + (goldSpent == null ? 0 : goldSpent.hashCode());
        result = prime * result + (item0 == null ? 0 : item0.hashCode());
        result = prime * result + (item1 == null ? 0 : item1.hashCode());
        result = prime * result + (item2 == null ? 0 : item2.hashCode());
        result = prime * result + (item3 == null ? 0 : item3.hashCode());
        result = prime * result + (item4 == null ? 0 : item4.hashCode());
        result = prime * result + (item5 == null ? 0 : item5.hashCode());
        result = prime * result + (item6 == null ? 0 : item6.hashCode());
        result = prime * result + (itemsPurchased == null ? 0 : itemsPurchased.hashCode());
        result = prime * result + (killingSprees == null ? 0 : killingSprees.hashCode());
        result = prime * result + (largestCriticalStrike == null ? 0 : largestCriticalStrike.hashCode());
        result = prime * result + (largestKillingSpree == null ? 0 : largestKillingSpree.hashCode());
        result = prime * result + (largestMultiKill == null ? 0 : largestMultiKill.hashCode());
        result = prime * result + (legendaryItemsCreated == null ? 0 : legendaryItemsCreated.hashCode());
        result = prime * result + (level == null ? 0 : level.hashCode());
        result = prime * result + (magicDamageDealtPlayer == null ? 0 : magicDamageDealtPlayer.hashCode());
        result = prime * result + (magicDamageDealtToChampions == null ? 0 : magicDamageDealtToChampions.hashCode());
        result = prime * result + (magicDamageTaken == null ? 0 : magicDamageTaken.hashCode());
        result = prime * result + (minionsDenied == null ? 0 : minionsDenied.hashCode());
        result = prime * result + (minionsKilled == null ? 0 : minionsKilled.hashCode());
        result = prime * result + (neutralMinionsKilled == null ? 0 : neutralMinionsKilled.hashCode());
        result = prime * result + (neutralMinionsKilledEnemyJungle == null ? 0 : neutralMinionsKilledEnemyJungle.hashCode());
        result = prime * result + (neutralMinionsKilledYourJungle == null ? 0 : neutralMinionsKilledYourJungle.hashCode());
        result = prime * result + (nexusKilled == null ? 0 : nexusKilled.hashCode());
        result = prime * result + (nodeCapture == null ? 0 : nodeCapture.hashCode());
        result = prime * result + (nodeCaptureAssist == null ? 0 : nodeCaptureAssist.hashCode());
        result = prime * result + (nodeNeutralize == null ? 0 : nodeNeutralize.hashCode());
        result = prime * result + (nodeNeutralizeAssist == null ? 0 : nodeNeutralizeAssist.hashCode());
        result = prime * result + (numDeaths == null ? 0 : numDeaths.hashCode());
        result = prime * result + (numItemsBought == null ? 0 : numItemsBought.hashCode());
        result = prime * result + (objectivePlayerScore == null ? 0 : objectivePlayerScore.hashCode());
        result = prime * result + (pentaKills == null ? 0 : pentaKills.hashCode());
        result = prime * result + (physicalDamageDealtPlayer == null ? 0 : physicalDamageDealtPlayer.hashCode());
        result = prime * result + (physicalDamageDealtToChampions == null ? 0 : physicalDamageDealtToChampions.hashCode());
        result = prime * result + (physicalDamageTaken == null ? 0 : physicalDamageTaken.hashCode());
        result = prime * result + (quadraKills == null ? 0 : quadraKills.hashCode());
        result = prime * result + (sightWardsBought == null ? 0 : sightWardsBought.hashCode());
        result = prime * result + (spell1Cast == null ? 0 : spell1Cast.hashCode());
        result = prime * result + (spell2Cast == null ? 0 : spell2Cast.hashCode());
        result = prime * result + (spell3Cast == null ? 0 : spell3Cast.hashCode());
        result = prime * result + (spell4Cast == null ? 0 : spell4Cast.hashCode());
        result = prime * result + (summonSpell1Cast == null ? 0 : summonSpell1Cast.hashCode());
        result = prime * result + (summonSpell2Cast == null ? 0 : summonSpell2Cast.hashCode());
        result = prime * result + (superMonsterKilled == null ? 0 : superMonsterKilled.hashCode());
        result = prime * result + (team == null ? 0 : team.hashCode());
        result = prime * result + (teamObjective == null ? 0 : teamObjective.hashCode());
        result = prime * result + (timePlayed == null ? 0 : timePlayed.hashCode());
        result = prime * result + (totalDamageDealt == null ? 0 : totalDamageDealt.hashCode());
        result = prime * result + (totalDamageDealtToChampions == null ? 0 : totalDamageDealtToChampions.hashCode());
        result = prime * result + (totalDamageTaken == null ? 0 : totalDamageTaken.hashCode());
        result = prime * result + (totalHeal == null ? 0 : totalHeal.hashCode());
        result = prime * result + (totalPlayerScore == null ? 0 : totalPlayerScore.hashCode());
        result = prime * result + (totalScoreRank == null ? 0 : totalScoreRank.hashCode());
        result = prime * result + (totalTimeCrowdControlDealt == null ? 0 : totalTimeCrowdControlDealt.hashCode());
        result = prime * result + (totalUnitsHealed == null ? 0 : totalUnitsHealed.hashCode());
        result = prime * result + (tripleKills == null ? 0 : tripleKills.hashCode());
        result = prime * result + (trueDamageDealtPlayer == null ? 0 : trueDamageDealtPlayer.hashCode());
        result = prime * result + (trueDamageDealtToChampions == null ? 0 : trueDamageDealtToChampions.hashCode());
        result = prime * result + (trueDamageTaken == null ? 0 : trueDamageTaken.hashCode());
        result = prime * result + (turretsKilled == null ? 0 : turretsKilled.hashCode());
        result = prime * result + (unrealKills == null ? 0 : unrealKills.hashCode());
        result = prime * result + (victoryPointTotal == null ? 0 : victoryPointTotal.hashCode());
        result = prime * result + (visionWardsBought == null ? 0 : visionWardsBought.hashCode());
        result = prime * result + (wardKilled == null ? 0 : wardKilled.hashCode());
        result = prime * result + (wardPlaced == null ? 0 : wardPlaced.hashCode());
        result = prime * result + (win == null ? 0 : win.hashCode());
        return result;
    }

    /**
     * @param assists
     *            the assists to set
     */
    public void setAssists(final Integer assists) {
        this.assists = assists;
    }

    /**
     * @param barracksKilled
     *            the barracksKilled to set
     */
    public void setBarracksKilled(final Integer barracksKilled) {
        this.barracksKilled = barracksKilled;
    }

    /**
     * @param championsKilled
     *            the championsKilled to set
     */
    public void setChampionsKilled(final Integer championsKilled) {
        this.championsKilled = championsKilled;
    }

    /**
     * @param combatPlayerScore
     *            the combatPlayerScore to set
     */
    public void setCombatPlayerScore(final Integer combatPlayerScore) {
        this.combatPlayerScore = combatPlayerScore;
    }

    /**
     * @param consumablesPurchased
     *            the consumablesPurchased to set
     */
    public void setConsumablesPurchased(final Integer consumablesPurchased) {
        this.consumablesPurchased = consumablesPurchased;
    }

    /**
     * @param damageDealtPlayer
     *            the damageDealtPlayer to set
     */
    public void setDamageDealtPlayer(final Integer damageDealtPlayer) {
        this.damageDealtPlayer = damageDealtPlayer;
    }

    /**
     * @param doubleKills
     *            the doubleKills to set
     */
    public void setDoubleKills(final Integer doubleKills) {
        this.doubleKills = doubleKills;
    }

    /**
     * @param firstBlood
     *            the firstBlood to set
     */
    public void setFirstBlood(final Integer firstBlood) {
        this.firstBlood = firstBlood;
    }

    /**
     * @param gold
     *            the gold to set
     */
    public void setGold(final Integer gold) {
        this.gold = gold;
    }

    /**
     * @param goldEarned
     *            the goldEarned to set
     */
    public void setGoldEarned(final Integer goldEarned) {
        this.goldEarned = goldEarned;
    }

    /**
     * @param goldSpent
     *            the goldSpent to set
     */
    public void setGoldSpent(final Integer goldSpent) {
        this.goldSpent = goldSpent;
    }

    /**
     * @param item0
     *            the item0 to set
     */
    public void setItem0(final Integer item0) {
        this.item0 = item0;
    }

    /**
     * @param item1
     *            the item1 to set
     */
    public void setItem1(final Integer item1) {
        this.item1 = item1;
    }

    /**
     * @param item2
     *            the item2 to set
     */
    public void setItem2(final Integer item2) {
        this.item2 = item2;
    }

    /**
     * @param item3
     *            the item3 to set
     */
    public void setItem3(final Integer item3) {
        this.item3 = item3;
    }

    /**
     * @param item4
     *            the item4 to set
     */
    public void setItem4(final Integer item4) {
        this.item4 = item4;
    }

    /**
     * @param item5
     *            the item5 to set
     */
    public void setItem5(final Integer item5) {
        this.item5 = item5;
    }

    /**
     * @param item6
     *            the item6 to set
     */
    public void setItem6(final Integer item6) {
        this.item6 = item6;
    }

    /**
     * @param itemsPurchased
     *            the itemsPurchased to set
     */
    public void setItemsPurchased(final Integer itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }

    /**
     * @param killingSprees
     *            the killingSprees to set
     */
    public void setKillingSprees(final Integer killingSprees) {
        this.killingSprees = killingSprees;
    }

    /**
     * @param largestCriticalStrike
     *            the largestCriticalStrike to set
     */
    public void setLargestCriticalStrike(final Integer largestCriticalStrike) {
        this.largestCriticalStrike = largestCriticalStrike;
    }

    /**
     * @param largestKillingSpree
     *            the largestKillingSpree to set
     */
    public void setLargestKillingSpree(final Integer largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }

    /**
     * @param largestMultiKill
     *            the largestMultiKill to set
     */
    public void setLargestMultiKill(final Integer largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    /**
     * @param legendaryItemsCreated
     *            the legendaryItemsCreated to set
     */
    public void setLegendaryItemsCreated(final Integer legendaryItemsCreated) {
        this.legendaryItemsCreated = legendaryItemsCreated;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(final Integer level) {
        this.level = level;
    }

    /**
     * @param magicDamageDealtPlayer
     *            the magicDamageDealtPlayer to set
     */
    public void setMagicDamageDealtPlayer(final Integer magicDamageDealtPlayer) {
        this.magicDamageDealtPlayer = magicDamageDealtPlayer;
    }

    /**
     * @param magicDamageDealtToChampions
     *            the magicDamageDealtToChampions to set
     */
    public void setMagicDamageDealtToChampions(final Integer magicDamageDealtToChampions) {
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
    }

    /**
     * @param magicDamageTaken
     *            the magicDamageTaken to set
     */
    public void setMagicDamageTaken(final Integer magicDamageTaken) {
        this.magicDamageTaken = magicDamageTaken;
    }

    /**
     * @param minionsDenied
     *            the minionsDenied to set
     */
    public void setMinionsDenied(final Integer minionsDenied) {
        this.minionsDenied = minionsDenied;
    }

    /**
     * @param minionsKilled
     *            the minionsKilled to set
     */
    public void setMinionsKilled(final Integer minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    /**
     * @param neutralMinionsKilled
     *            the neutralMinionsKilled to set
     */
    public void setNeutralMinionsKilled(final Integer neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }

    /**
     * @param neutralMinionsKilledEnemyJungle
     *            the neutralMinionsKilledEnemyJungle to set
     */
    public void setNeutralMinionsKilledEnemyJungle(final Integer neutralMinionsKilledEnemyJungle) {
        this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
    }

    /**
     * @param neutralMinionsKilledYourJungle
     *            the neutralMinionsKilledYourJungle to set
     */
    public void setNeutralMinionsKilledYourJungle(final Integer neutralMinionsKilledYourJungle) {
        this.neutralMinionsKilledYourJungle = neutralMinionsKilledYourJungle;
    }

    /**
     * @param nexusKilled
     *            the nexusKilled to set
     */
    public void setNexusKilled(final Boolean nexusKilled) {
        this.nexusKilled = nexusKilled;
    }

    /**
     * @param nodeCapture
     *            the nodeCapture to set
     */
    public void setNodeCapture(final Integer nodeCapture) {
        this.nodeCapture = nodeCapture;
    }

    /**
     * @param nodeCaptureAssist
     *            the nodeCaptureAssist to set
     */
    public void setNodeCaptureAssist(final Integer nodeCaptureAssist) {
        this.nodeCaptureAssist = nodeCaptureAssist;
    }

    /**
     * @param nodeNeutralize
     *            the nodeNeutralize to set
     */
    public void setNodeNeutralize(final Integer nodeNeutralize) {
        this.nodeNeutralize = nodeNeutralize;
    }

    /**
     * @param nodeNeutralizeAssist
     *            the nodeNeutralizeAssist to set
     */
    public void setNodeNeutralizeAssist(final Integer nodeNeutralizeAssist) {
        this.nodeNeutralizeAssist = nodeNeutralizeAssist;
    }

    /**
     * @param numDeaths
     *            the numDeaths to set
     */
    public void setNumDeaths(final Integer numDeaths) {
        this.numDeaths = numDeaths;
    }

    /**
     * @param numItemsBought
     *            the numItemsBought to set
     */
    public void setNumItemsBought(final Integer numItemsBought) {
        this.numItemsBought = numItemsBought;
    }

    /**
     * @param objectivePlayerScore
     *            the objectivePlayerScore to set
     */
    public void setObjectivePlayerScore(final Integer objectivePlayerScore) {
        this.objectivePlayerScore = objectivePlayerScore;
    }

    /**
     * @param pentaKills
     *            the pentaKills to set
     */
    public void setPentaKills(final Integer pentaKills) {
        this.pentaKills = pentaKills;
    }

    /**
     * @param physicalDamageDealtPlayer
     *            the physicalDamageDealtPlayer to set
     */
    public void setPhysicalDamageDealtPlayer(final Integer physicalDamageDealtPlayer) {
        this.physicalDamageDealtPlayer = physicalDamageDealtPlayer;
    }

    /**
     * @param physicalDamageDealtToChampions
     *            the physicalDamageDealtToChampions to set
     */
    public void setPhysicalDamageDealtToChampions(final Integer physicalDamageDealtToChampions) {
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
    }

    /**
     * @param physicalDamageTaken
     *            the physicalDamageTaken to set
     */
    public void setPhysicalDamageTaken(final Integer physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }

    /**
     * @param quadraKills
     *            the quadraKills to set
     */
    public void setQuadraKills(final Integer quadraKills) {
        this.quadraKills = quadraKills;
    }

    /**
     * @param sightWardsBought
     *            the sightWardsBought to set
     */
    public void setSightWardsBought(final Integer sightWardsBought) {
        this.sightWardsBought = sightWardsBought;
    }

    /**
     * @param spell1Cast
     *            the spell1Cast to set
     */
    public void setSpell1Cast(final Integer spell1Cast) {
        this.spell1Cast = spell1Cast;
    }

    /**
     * @param spell2Cast
     *            the spell2Cast to set
     */
    public void setSpell2Cast(final Integer spell2Cast) {
        this.spell2Cast = spell2Cast;
    }

    /**
     * @param spell3Cast
     *            the spell3Cast to set
     */
    public void setSpell3Cast(final Integer spell3Cast) {
        this.spell3Cast = spell3Cast;
    }

    /**
     * @param spell4Cast
     *            the spell4Cast to set
     */
    public void setSpell4Cast(final Integer spell4Cast) {
        this.spell4Cast = spell4Cast;
    }

    /**
     * @param summonSpell1Cast
     *            the summonSpell1Cast to set
     */
    public void setSummonSpell1Cast(final Integer summonSpell1Cast) {
        this.summonSpell1Cast = summonSpell1Cast;
    }

    /**
     * @param summonSpell2Cast
     *            the summonSpell2Cast to set
     */
    public void setSummonSpell2Cast(final Integer summonSpell2Cast) {
        this.summonSpell2Cast = summonSpell2Cast;
    }

    /**
     * @param superMonsterKilled
     *            the superMonsterKilled to set
     */
    public void setSuperMonsterKilled(final Integer superMonsterKilled) {
        this.superMonsterKilled = superMonsterKilled;
    }

    /**
     * @param team
     *            the team to set
     */
    public void setTeam(final Integer team) {
        this.team = team;
    }

    /**
     * @param teamObjective
     *            the teamObjective to set
     */
    public void setTeamObjective(final Integer teamObjective) {
        this.teamObjective = teamObjective;
    }

    /**
     * @param timePlayed
     *            the timePlayed to set
     */
    public void setTimePlayed(final Integer timePlayed) {
        this.timePlayed = timePlayed;
    }

    /**
     * @param totalDamageDealt
     *            the totalDamageDealt to set
     */
    public void setTotalDamageDealt(final Integer totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    /**
     * @param totalDamageDealtToChampions
     *            the totalDamageDealtToChampions to set
     */
    public void setTotalDamageDealtToChampions(final Integer totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }

    /**
     * @param totalDamageTaken
     *            the totalDamageTaken to set
     */
    public void setTotalDamageTaken(final Integer totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    /**
     * @param totalHeal
     *            the totalHeal to set
     */
    public void setTotalHeal(final Integer totalHeal) {
        this.totalHeal = totalHeal;
    }

    /**
     * @param totalPlayerScore
     *            the totalPlayerScore to set
     */
    public void setTotalPlayerScore(final Integer totalPlayerScore) {
        this.totalPlayerScore = totalPlayerScore;
    }

    /**
     * @param totalScoreRank
     *            the totalScoreRank to set
     */
    public void setTotalScoreRank(final Integer totalScoreRank) {
        this.totalScoreRank = totalScoreRank;
    }

    /**
     * @param totalTimeCrowdControlDealt
     *            the totalTimeCrowdControlDealt to set
     */
    public void setTotalTimeCrowdControlDealt(final Integer totalTimeCrowdControlDealt) {
        this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
    }

    /**
     * @param totalUnitsHealed
     *            the totalUnitsHealed to set
     */
    public void setTotalUnitsHealed(final Integer totalUnitsHealed) {
        this.totalUnitsHealed = totalUnitsHealed;
    }

    /**
     * @param tripleKills
     *            the tripleKills to set
     */
    public void setTripleKills(final Integer tripleKills) {
        this.tripleKills = tripleKills;
    }

    /**
     * @param trueDamageDealtPlayer
     *            the trueDamageDealtPlayer to set
     */
    public void setTrueDamageDealtPlayer(final Integer trueDamageDealtPlayer) {
        this.trueDamageDealtPlayer = trueDamageDealtPlayer;
    }

    /**
     * @param trueDamageDealtToChampions
     *            the trueDamageDealtToChampions to set
     */
    public void setTrueDamageDealtToChampions(final Integer trueDamageDealtToChampions) {
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
    }

    /**
     * @param trueDamageTaken
     *            the trueDamageTaken to set
     */
    public void setTrueDamageTaken(final Integer trueDamageTaken) {
        this.trueDamageTaken = trueDamageTaken;
    }

    /**
     * @param turretsKilled
     *            the turretsKilled to set
     */
    public void setTurretsKilled(final Integer turretsKilled) {
        this.turretsKilled = turretsKilled;
    }

    /**
     * @param unrealKills
     *            the unrealKills to set
     */
    public void setUnrealKills(final Integer unrealKills) {
        this.unrealKills = unrealKills;
    }

    /**
     * @param victoryPointTotal
     *            the victoryPointTotal to set
     */
    public void setVictoryPointTotal(final Integer victoryPointTotal) {
        this.victoryPointTotal = victoryPointTotal;
    }

    /**
     * @param visionWardsBought
     *            the visionWardsBought to set
     */
    public void setVisionWardsBought(final Integer visionWardsBought) {
        this.visionWardsBought = visionWardsBought;
    }

    /**
     * @param wardKilled
     *            the wardKilled to set
     */
    public void setWardKilled(final Integer wardKilled) {
        this.wardKilled = wardKilled;
    }

    /**
     * @param wardPlaced
     *            the wardPlaced to set
     */
    public void setWardPlaced(final Integer wardPlaced) {
        this.wardPlaced = wardPlaced;
    }

    /**
     * @param win
     *            the win to set
     */
    public void setWin(final Boolean win) {
        this.win = win;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RawStats [assists=" + assists + ", barracksKilled=" + barracksKilled + ", championsKilled=" + championsKilled + ", combatPlayerScore="
                + combatPlayerScore + ", consumablesPurchased=" + consumablesPurchased + ", damageDealtPlayer=" + damageDealtPlayer + ", doubleKills="
                + doubleKills + ", firstBlood=" + firstBlood + ", gold=" + gold + ", goldEarned=" + goldEarned + ", goldSpent=" + goldSpent + ", item0=" + item0
                + ", item1=" + item1 + ", item2=" + item2 + ", item3=" + item3 + ", item4=" + item4 + ", item5=" + item5 + ", item6=" + item6
                + ", itemsPurchased=" + itemsPurchased + ", killingSprees=" + killingSprees + ", largestCriticalStrike=" + largestCriticalStrike
                + ", largestKillingSpree=" + largestKillingSpree + ", largestMultiKill=" + largestMultiKill + ", legendaryItemsCreated=" + legendaryItemsCreated
                + ", level=" + level + ", magicDamageDealtPlayer=" + magicDamageDealtPlayer + ", magicDamageDealtToChampions=" + magicDamageDealtToChampions
                + ", magicDamageTaken=" + magicDamageTaken + ", minionsDenied=" + minionsDenied + ", minionsKilled=" + minionsKilled + ", neutralMinionsKilled="
                + neutralMinionsKilled + ", neutralMinionsKilledEnemyJungle=" + neutralMinionsKilledEnemyJungle + ", neutralMinionsKilledYourJungle="
                + neutralMinionsKilledYourJungle + ", nodeCapture=" + nodeCapture + ", nodeCaptureAssist=" + nodeCaptureAssist + ", nodeNeutralize="
                + nodeNeutralize + ", nodeNeutralizeAssist=" + nodeNeutralizeAssist + ", numDeaths=" + numDeaths + ", numItemsBought=" + numItemsBought
                + ", objectivePlayerScore=" + objectivePlayerScore + ", pentaKills=" + pentaKills + ", physicalDamageDealtPlayer=" + physicalDamageDealtPlayer
                + ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions + ", physicalDamageTaken=" + physicalDamageTaken + ", quadraKills="
                + quadraKills + ", sightWardsBought=" + sightWardsBought + ", spell1Cast=" + spell1Cast + ", spell2Cast=" + spell2Cast + ", spell3Cast="
                + spell3Cast + ", spell4Cast=" + spell4Cast + ", summonSpell1Cast=" + summonSpell1Cast + ", summonSpell2Cast=" + summonSpell2Cast
                + ", superMonsterKilled=" + superMonsterKilled + ", team=" + team + ", teamObjective=" + teamObjective + ", timePlayed=" + timePlayed
                + ", totalDamageDealt=" + totalDamageDealt + ", totalDamageDealtToChampions=" + totalDamageDealtToChampions + ", totalDamageTaken="
                + totalDamageTaken + ", totalHeal=" + totalHeal + ", totalPlayerScore=" + totalPlayerScore + ", totalScoreRank=" + totalScoreRank
                + ", totalTimeCrowdControlDealt=" + totalTimeCrowdControlDealt + ", totalUnitsHealed=" + totalUnitsHealed + ", tripleKills=" + tripleKills
                + ", trueDamageDealtPlayer=" + trueDamageDealtPlayer + ", trueDamageDealtToChampions=" + trueDamageDealtToChampions + ", trueDamageTaken="
                + trueDamageTaken + ", turretsKilled=" + turretsKilled + ", unrealKills=" + unrealKills + ", victoryPointTotal=" + victoryPointTotal
                + ", visionWardsBought=" + visionWardsBought + ", wardKilled=" + wardKilled + ", wardPlaced=" + wardPlaced + ", win=" + win + ", nexusKilled="
                + nexusKilled + "]";
    }
}
