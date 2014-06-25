package lib.orianna.type.game;

import java.io.Serializable;

import lib.orianna.type.staticdata.Item;

public class RawStats implements Serializable {
    private static final long serialVersionUID = -8604137571187419055L;
    public final Integer assists, barracksKilled, championsKilled, combatPlayerScore, consumablesPurchased, damageDealtPlayer, doubleKills, firstBlood, gold,
            goldEarned, goldSpent, itemsPurchased, killingSprees, largestCriticalStrike, largestKillingSpree, largestMultiKill, legendaryItemsCreated, level,
            magicDamageDealtPlayer, magicDamageDealtToChampions, magicDamageTaken, minionsDenied, minionsKilled, neutralMinionsKilled,
            neutralMinionsKilledEnemyJungle, neutralMinionsKilledYourJungle, nodeCapture, nodeCaptureAssist, nodeNeutralize, nodeNeutralizeAssist, numDeaths,
            numItemsBought, objectivePlayerScore, pentaKills, physicalDamageDealtPlayer, physicalDamageDealtToChampions, physicalDamageTaken, quadraKills,
            sightWardsBought, spell1Cast, spell2Cast, spell3Cast, spell4Cast, summonSpell1Cast, summonSpell2Cast, superMonsterKilled, team, teamObjective,
            timePlayed, totalDamageDealt, totalDamageDealtToChampions, totalDamageTaken, totalHeal, totalPlayerScore, totalScoreRank,
            totalTimeCrowdControlDealt, totalUnitsHealed, tripleKills, trueDamageDealtPlayer, trueDamageDealtToChampions, trueDamageTaken, turretsKilled,
            unrealKills, victoryPointTotal, visionWardsBought, wardKilled, wardPlaced;
    public final Item item0, item1, item2, item3, item4, item5, item6;
    public final Boolean nexusKilled, win;

    public RawStats(final Boolean nexusKilled, final Boolean win, final Integer assists, final Integer barracksKilled, final Integer championsKilled,
            final Integer combatPlayerScore, final Integer consumablesPurchased, final Integer damageDealtPlayer, final Integer doubleKills,
            final Integer firstBlood, final Integer gold, final Integer goldEarned, final Integer goldSpent, final Integer itemsPurchased,
            final Integer killingSprees, final Integer largestCriticalStrike, final Integer largestKillingSpree, final Integer largestMultiKill,
            final Integer legendaryItemsCreated, final Integer level, final Integer magicDamageDealtPlayer, final Integer magicDamageDealtToChampions,
            final Integer magicDamageTaken, final Integer minionsDenied, final Integer minionsKilled, final Integer neutralMinionsKilled,
            final Integer neutralMinionsKilledEnemyJungle, final Integer neutralMinionsKilledYourJungle, final Integer nodeCapture,
            final Integer nodeCaptureAssist, final Integer nodeNeutralize, final Integer nodeNeutralizeAssist, final Integer numDeaths,
            final Integer numItemsBought, final Integer objectivePlayerScore, final Integer pentaKills, final Integer physicalDamageDealtPlayer,
            final Integer physicalDamageDealtToChampions, final Integer physicalDamageTaken, final Integer quadraKills, final Integer sightWardsBought,
            final Integer spell1Cast, final Integer spell2Cast, final Integer spell3Cast, final Integer spell4Cast, final Integer summonSpell1Cast,
            final Integer summonSpell2Cast, final Integer superMonsterKilled, final Integer team, final Integer teamObjective, final Integer timePlayed,
            final Integer totalDamageDealt, final Integer totalDamageDealtToChampions, final Integer totalDamageTaken, final Integer totalHeal,
            final Integer totalPlayerScore, final Integer totalScoreRank, final Integer totalTimeCrowdControlDealt, final Integer totalUnitsHealed,
            final Integer tripleKills, final Integer trueDamageDealtPlayer, final Integer trueDamageDealtToChampions, final Integer trueDamageTaken,
            final Integer turretsKilled, final Integer unrealKills, final Integer victoryPointTotal, final Integer visionWardsBought, final Integer wardKilled,
            final Integer wardPlaced, final Item item0, final Item item1, final Item item2, final Item item3, final Item item4, final Item item5,
            final Item item6) {
        this.nexusKilled = nexusKilled;
        this.win = win;
        this.assists = assists;
        this.barracksKilled = barracksKilled;
        this.championsKilled = championsKilled;
        this.combatPlayerScore = combatPlayerScore;
        this.consumablesPurchased = consumablesPurchased;
        this.damageDealtPlayer = damageDealtPlayer;
        this.doubleKills = doubleKills;
        this.firstBlood = firstBlood;
        this.gold = gold;
        this.goldEarned = goldEarned;
        this.goldSpent = goldSpent;
        this.itemsPurchased = itemsPurchased;
        this.killingSprees = killingSprees;
        this.largestCriticalStrike = largestCriticalStrike;
        this.largestKillingSpree = largestKillingSpree;
        this.largestMultiKill = largestMultiKill;
        this.legendaryItemsCreated = legendaryItemsCreated;
        this.level = level;
        this.magicDamageDealtPlayer = magicDamageDealtPlayer;
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
        this.magicDamageTaken = magicDamageTaken;
        this.minionsDenied = minionsDenied;
        this.minionsKilled = minionsKilled;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
        this.neutralMinionsKilledYourJungle = neutralMinionsKilledYourJungle;
        this.nodeCapture = nodeCapture;
        this.nodeCaptureAssist = nodeCaptureAssist;
        this.nodeNeutralize = nodeNeutralize;
        this.nodeNeutralizeAssist = nodeNeutralizeAssist;
        this.numDeaths = numDeaths;
        this.numItemsBought = numItemsBought;
        this.objectivePlayerScore = objectivePlayerScore;
        this.pentaKills = pentaKills;
        this.physicalDamageDealtPlayer = physicalDamageDealtPlayer;
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
        this.physicalDamageTaken = physicalDamageTaken;
        this.quadraKills = quadraKills;
        this.sightWardsBought = sightWardsBought;
        this.spell1Cast = spell1Cast;
        this.spell2Cast = spell2Cast;
        this.spell3Cast = spell3Cast;
        this.spell4Cast = spell4Cast;
        this.summonSpell1Cast = summonSpell1Cast;
        this.summonSpell2Cast = summonSpell2Cast;
        this.superMonsterKilled = superMonsterKilled;
        this.team = team;
        this.teamObjective = teamObjective;
        this.timePlayed = timePlayed;
        this.totalDamageDealt = totalDamageDealt;
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
        this.totalDamageTaken = totalDamageTaken;
        this.totalHeal = totalHeal;
        this.totalPlayerScore = totalPlayerScore;
        this.totalScoreRank = totalScoreRank;
        this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
        this.totalUnitsHealed = totalUnitsHealed;
        this.tripleKills = tripleKills;
        this.trueDamageDealtPlayer = trueDamageDealtPlayer;
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
        this.trueDamageTaken = trueDamageTaken;
        this.turretsKilled = turretsKilled;
        this.unrealKills = unrealKills;
        this.victoryPointTotal = victoryPointTotal;
        this.visionWardsBought = visionWardsBought;
        this.wardKilled = wardKilled;
        this.wardPlaced = wardPlaced;
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

    @Override
    public String toString() {
        return "RawStats";
    }
}
