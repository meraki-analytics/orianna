package com.robrua.orianna.type.core.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.Side;
import com.robrua.orianna.type.core.staticdata.Item;
import com.robrua.orianna.type.exception.MissingDataException;

public class RawStats extends OriannaObject<com.robrua.orianna.type.dto.game.RawStats> {
    private static final long serialVersionUID = -8317184539839181991L;
    private Item item0, item1, item2, item3, item4, item5, item6;

    /**
     * @param data
     *            the underlying dto
     */
    public RawStats(final com.robrua.orianna.type.dto.game.RawStats data) {
        super(data, com.robrua.orianna.type.dto.game.RawStats.class);
    }

    /**
     * Assists
     *
     * @return assists
     */
    public int getAssists() {
        return super.getInteger(data.getAssists());
    }

    /**
     * Number of enemy inhibitors killed
     *
     * @return number of enemy inhibitors killed
     */
    public int getBarracksKilled() {
        return super.getInteger(data.getBarracksKilled());
    }

    /**
     * Combat player score
     *
     * @return combat player score
     */
    public int getCombatPlayerScore() {
        return super.getInteger(data.getCombatPlayerScore());
    }

    /**
     * Consumables purchased
     *
     * @return consumables purchased
     */
    public int getConsumablesPurchased() {
        return super.getInteger(data.getConsumablesPurchased());
    }

    /**
     * Damage dealt player
     *
     * @return damage dealt player
     */
    public int getDamageDealtPlayer() {
        return super.getInteger(data.getDamageDealtPlayer());
    }

    /**
     * Deaths
     *
     * @return deaths
     */
    public int getDeaths() {
        return super.getInteger(data.getNumDeaths());
    }

    /**
     * Double kills
     *
     * @return double kills
     */
    public int getDoubleKills() {
        return super.getInteger(data.getDoubleKills());
    }

    /**
     * First blood
     *
     * @return first blood
     */
    public boolean getFirstBlood() {
        return super.getInteger(data.getFirstBlood()) == 1;
    }

    /**
     * Gold at end of game
     *
     * @return gold at end of game
     */
    public int getGold() {
        return super.getInteger(data.getGold());
    }

    /**
     * Gold earned
     *
     * @return gold earned
     */
    public int getGoldEarned() {
        return super.getInteger(data.getGoldEarned());
    }

    /**
     * Gold spent
     *
     * @return gold spent
     */
    public int getGoldSpent() {
        return super.getInteger(data.getGoldSpent());
    }

    /**
     * Item 0
     *
     * @return item 0
     */
    public Item getItem0() {
        if(item0 != null) {
            return item0;
        }

        final Integer i = data.getItem0();
        if(i == null) {
            throw new MissingDataException("Item #0 ID is null.");
        }

        item0 = RiotAPI.getItem(i.longValue());
        return item0;
    }

    /**
     * Item 0 ID
     *
     * @return item 0
     */
    public long getItem0ID() {
        return super.getInteger(data.getItem0());
    }

    /**
     * Item 1
     *
     * @return item 1
     */
    public Item getItem1() {
        if(item1 != null) {
            return item1;
        }

        final Integer i = data.getItem1();
        if(i == null) {
            throw new MissingDataException("Item #1 ID is null.");
        }

        item1 = RiotAPI.getItem(i.longValue());
        return item1;
    }

    /**
     * Item 1 ID
     *
     * @return item 1 ID
     */
    public long getItem1ID() {
        return super.getInteger(data.getItem1());
    }

    /**
     * Item 2
     *
     * @return item 2
     */
    public Item getItem2() {
        if(item2 != null) {
            return item2;
        }

        final Integer i = data.getItem2();
        if(i == null) {
            throw new MissingDataException("Item #2 ID is null.");
        }

        item2 = RiotAPI.getItem(i.longValue());
        return item2;
    }

    /**
     * Item 2 ID
     *
     * @return item 2 ID
     */
    public long getItem2ID() {
        return super.getInteger(data.getItem2());
    }

    /**
     * Item 3
     *
     * @return item 3
     */
    public Item getItem3() {
        if(item3 != null) {
            return item3;
        }

        final Integer i = data.getItem3();
        if(i == null) {
            throw new MissingDataException("Item #3 ID is null.");
        }

        item3 = RiotAPI.getItem(i.longValue());
        return item3;
    }

    /**
     * Item 3 ID
     *
     * @return item 3 ID
     */
    public long getItem3ID() {
        return super.getInteger(data.getItem3());
    }

    /**
     * Item 4
     *
     * @return item 4
     */
    public Item getItem4() {
        if(item4 != null) {
            return item4;
        }

        final Integer i = data.getItem4();
        if(i == null) {
            throw new MissingDataException("Item #4 ID is null.");
        }

        item4 = RiotAPI.getItem(i.longValue());
        return item4;
    }

    /**
     * Item 4 ID
     *
     * @return item 4 ID
     */
    public long getItem4ID() {
        return super.getInteger(data.getItem4());
    }

    /**
     * Item 5
     *
     * @return item 5
     */
    public Item getItem5() {
        if(item5 != null) {
            return item5;
        }

        final Integer i = data.getItem5();
        if(i == null) {
            throw new MissingDataException("Item #5 ID is null.");
        }

        item5 = RiotAPI.getItem(i.longValue());
        return item5;
    }

    /**
     * Item 5 ID
     *
     * @return item 5 ID
     */
    public long getItem5ID() {
        return super.getInteger(data.getItem5());
    }

    /**
     * Item 6
     *
     * @return item 6
     */
    public Item getItem6() {
        if(item6 != null) {
            return item6;
        }

        final Integer i = data.getItem6();
        if(i == null) {
            throw new MissingDataException("Item #6 ID is null.");
        }

        item6 = RiotAPI.getItem(i.longValue());
        return item6;
    }

    /**
     * Item 6 ID
     *
     * @return item 6 ID
     */
    public long getItem6ID() {
        return super.getInteger(data.getItem6());
    }

    /**
     * All items
     *
     * @return all items
     */
    public List<Item> getItems() {
        final List<Item> items = new ArrayList<>(7);
        final List<Long> toLoad = new ArrayList<>(7);

        if(item0 != null) {
            items.add(0, item0);
        }
        else {
            final Integer i = data.getItem0();
            if(i == null) {
                throw new MissingDataException("Item #0 ID is null.");
            }

            toLoad.add(i.longValue());
        }

        if(item1 != null) {
            items.add(1, item1);
        }
        else {
            final Integer i = data.getItem1();
            if(i == null) {
                throw new MissingDataException("Item #1 ID is null.");
            }

            toLoad.add(i.longValue());
        }

        if(item2 != null) {
            items.add(2, item2);
        }
        else {
            final Integer i = data.getItem2();
            if(i == null) {
                throw new MissingDataException("Item #2 ID is null.");
            }

            toLoad.add(i.longValue());
        }

        if(item3 != null) {
            items.add(3, item3);
        }
        else {
            final Integer i = data.getItem3();
            if(i == null) {
                throw new MissingDataException("Item #3 ID is null.");
            }

            toLoad.add(i.longValue());
        }

        if(item4 != null) {
            items.add(4, item4);
        }
        else {
            final Integer i = data.getItem4();
            if(i == null) {
                throw new MissingDataException("Item #4 ID is null.");
            }

            toLoad.add(i.longValue());
        }

        if(item5 != null) {
            items.add(5, item5);
        }
        else {
            final Integer i = data.getItem5();
            if(i == null) {
                throw new MissingDataException("Item #5 ID is null.");
            }

            toLoad.add(i.longValue());
        }

        if(item6 != null) {
            items.add(6, item6);
        }
        else {
            final Integer i = data.getItem6();
            if(i == null) {
                throw new MissingDataException("Item #6 ID is null.");
            }

            toLoad.add(i.longValue());
        }

        if(toLoad.isEmpty()) {
            return Collections.unmodifiableList(items);
        }

        final List<Item> newItems = RiotAPI.getItems(toLoad);
        for(final Item item : newItems) {
            final int index = items.indexOf(null);
            items.add(index, item);
        }

        return Collections.unmodifiableList(items);
    }

    /**
     * Items bought
     *
     * @return items bought
     */
    public int getItemsBought() {
        return super.getInteger(data.getNumItemsBought());
    }

    /**
     * Items purchased
     *
     * @return items purchased
     */
    public int getItemsPurchased() {
        return super.getInteger(data.getItemsPurchased());
    }

    /**
     * Killing sprees
     *
     * @return killing sprees
     */
    public int getKillingSprees() {
        return super.getInteger(data.getKillingSprees());
    }

    /**
     * Champions killed
     *
     * @return champions killed
     */
    public int getKills() {
        return super.getInteger(data.getChampionsKilled());
    }

    /**
     * Largest critical strike
     *
     * @return largest critial strike
     */
    public int getLargestCriticalStrike() {
        return super.getInteger(data.getLargestCriticalStrike());
    }

    /**
     * Largest killing spree
     *
     * @return largest killing spree
     */
    public int getLargestKillingSpree() {
        return super.getInteger(data.getLargestKillingSpree());
    }

    /**
     * Largest multi kill
     *
     * @return largest multi kill
     */
    public int getLargestMultiKill() {
        return super.getInteger(data.getLargestMultiKill());
    }

    /**
     * Number of tier 3 items built
     *
     * @return number of tier 3 items built
     */
    public int getLegendaryItemsCreated() {
        return super.getInteger(data.getLegendaryItemsCreated());
    }

    /**
     * Level
     *
     * @return level
     */
    public int getLevel() {
        return super.getInteger(data.getLevel());
    }

    /**
     * Magic damage dealt player
     *
     * @return magic damage dealt player
     */
    public int getMagicDamageDealtPlayer() {
        return super.getInteger(data.getMagicDamageDealtPlayer());
    }

    /**
     * Magic damage dealt to champions
     *
     * @return magic damage dealt to champions
     */
    public int getMagicDamageDealtToChampions() {
        return super.getInteger(data.getMagicDamageDealtToChampions());
    }

    /**
     * Magic damage taken
     *
     * @return magic damage taken
     */
    public int getMagicDamageTaken() {
        return super.getInteger(data.getMagicDamageTaken());
    }

    /**
     * Minions denied
     *
     * @return minions denied
     */
    public int getMinionsDenied() {
        return super.getInteger(data.getMinionsDenied());
    }

    /**
     * Minions killed
     *
     * @return minions killed
     */
    public int getMinionsKilled() {
        return super.getInteger(data.getMinionsKilled());
    }

    /**
     * Neutral minions killed
     *
     * @return neutral minions killed
     */
    public int getNeutralMinionsKilled() {
        return super.getInteger(data.getNeutralMinionsKilled());
    }

    /**
     * Neutral minions killed inside enemy jungle
     *
     * @return neutral minions killed inside enemy jungle
     */
    public int getNeutralMinionsKilledEnemyJungle() {
        return super.getInteger(data.getNeutralMinionsKilledEnemyJungle());
    }

    /**
     * Neutral minions killed inside friendly jungle
     *
     * @return neutral minions killed inside friendly jungle
     */
    public int getNeutralMinionsKilledYourJungle() {
        return super.getInteger(data.getNeutralMinionsKilledYourJungle());
    }

    /**
     * Flag specifying if the summoner got the killing blow on the nexus
     *
     * @return whether the summoner got the killing blow on the nexus
     */
    public boolean getNexusKilled() {
        return super.getBoolean(data.getNexusKilled());
    }

    /**
     * Node captures assisted
     *
     * @return node captures assisted
     */
    public int getNodeCaptureAssists() {
        return super.getInteger(data.getNodeCaptureAssist());
    }

    /**
     * Nodes captured
     *
     * @return nodes captured
     */
    public int getNodeCaptures() {
        return super.getInteger(data.getNodeCapture());
    }

    /**
     * Nodes neutralized
     *
     * @return nodes neutralized
     */
    public int getNodeNeutralizations() {
        return super.getInteger(data.getNodeNeutralize());
    }

    /**
     * Node neutralizations assisted
     *
     * @return node neutralizations assisted
     */
    public int getNodeNeutralizeAssists() {
        return super.getInteger(data.getNodeNeutralizeAssist());
    }

    /**
     * Objective player score
     *
     * @return objective player score
     */
    public int getObjectivePlayerScore() {
        return super.getInteger(data.getObjectivePlayerScore());
    }

    /**
     * Penta kills
     *
     * @return penta kills
     */
    public int getPentaKills() {
        return super.getInteger(data.getPentaKills());
    }

    /**
     * Physical damage dealt player
     *
     * @return Physical damage dealt player
     */
    public int getPhysicalDamageDealtPlayer() {
        return super.getInteger(data.getPhysicalDamageDealtPlayer());
    }

    /**
     * Physical damage dealt to champions
     *
     * @return physical damage dealt to champions
     */
    public int getPhysicalDamageDealtToChampions() {
        return super.getInteger(data.getPhysicalDamageDealtToChampions());
    }

    /**
     * Physical damage taken
     *
     * @return physical damage taken
     */
    public int getPhysicalDamageTaken() {
        return super.getInteger(data.getPhysicalDamageTaken());
    }

    /**
     * Quadra kills
     *
     * @return quadra kills
     */
    public int getQuadraKills() {
        return super.getInteger(data.getQuadraKills());
    }

    /**
     * Sight wards bought
     *
     * @return sight wards bought
     */
    public int getSightWardsBought() {
        return super.getInteger(data.getSightWardsBought());
    }

    /**
     * Number of times first champion spell (Q) was cast
     *
     * @return number of times first champion spell (Q) was cast
     */
    public int getSpell1Casts() {
        return super.getInteger(data.getSpell1Cast());
    }

    /**
     * Number of times second champion spell (W) was cast
     *
     * @return number of times second champion spell (W) was cast
     */
    public int getSpell2Casts() {
        return super.getInteger(data.getSpell2Cast());
    }

    /**
     * Number of times third champion spell (E) was cast
     *
     * @return number of times third champion spell (E) was cast
     */
    public int getSpell3Casts() {
        return super.getInteger(data.getSpell3Cast());
    }

    /**
     * Number of times fourth champion spell (R) was cast
     *
     * @return number of times fourth champion spell (R) was cast
     */
    public int getSpell4Casts() {
        return super.getInteger(data.getSpell4Cast());
    }

    /**
     * Number of times first summoner spell was cast
     *
     * @return number of times first summoner spell was cast
     */
    public int getSummonSpell1Casts() {
        return super.getInteger(data.getSummonSpell1Cast());
    }

    /**
     * Number of times second summoner spell was cast
     *
     * @return number of times second summoner spell was cast
     */
    public int getSummonSpell2Casts() {
        return super.getInteger(data.getSummonSpell2Cast());
    }

    /**
     * Super monsters killed
     *
     * @return super monsters killed
     */
    public int getSuperMonsterKilled() {
        return super.getInteger(data.getSuperMonsterKilled());
    }

    /**
     * Team
     *
     * @return team
     */
    public Side getTeam() {
        return Side.forID(super.getInteger(data.getTeam()));
    }

    /**
     * Team objectives
     *
     * @return team objectives
     */
    public int getTeamObjectives() {
        return super.getInteger(data.getTeamObjective());
    }

    /**
     * Time played (in seconds)
     *
     * @return time played (in seconds)
     */
    public long getTimePlayed() {
        return super.getInteger(data.getTimePlayed());
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
     * Damage dealt to champions
     *
     * @return damage dealt to champions
     */
    public int getTotalDamageDealtToChampions() {
        return super.getInteger(data.getTotalDamageDealtToChampions());
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
     * Total healing done
     *
     * @return total healing done
     */
    public int getTotalHealing() {
        return super.getInteger(data.getTotalHeal());
    }

    /**
     * Total player score
     *
     * @return total player score
     */
    public int getTotalPlayerScore() {
        return super.getInteger(data.getTotalPlayerScore());
    }

    /**
     * Total score rank
     *
     * @return total score rank
     */
    public int getTotalScoreRank() {
        return super.getInteger(data.getTotalScoreRank());
    }

    /**
     * Total time of crowd controls dealt (in milliseconds)
     *
     * @return total time of crowd controls dealt (in milliseconds)
     */
    public long getTotalTimeCrowdControlDealt() {
        return super.getInteger(data.getTotalTimeCrowdControlDealt());
    }

    /**
     * Total units healed
     *
     * @return total units healed
     */
    public int getTotalUnitsHealed() {
        return super.getInteger(data.getTotalUnitsHealed());
    }

    /**
     * Triple kills
     *
     * @return triple kills
     */
    public int getTripleKills() {
        return super.getInteger(data.getTripleKills());
    }

    /**
     * True damage dealt player
     *
     * @return true damage dealt player
     */
    public int getTrueDamageDealtPlayer() {
        return super.getInteger(data.getTrueDamageDealtPlayer());
    }

    /**
     * True damage dealt to champions
     *
     * @return true damage dealt to champions
     */
    public int getTrueDamageDealtToChampions() {
        return super.getInteger(data.getTrueDamageDealtToChampions());
    }

    /**
     * True damage taken
     *
     * @return true damage taken
     */
    public int getTrueDamageTaken() {
        return super.getInteger(data.getTrueDamageTaken());
    }

    /**
     * Turrets killed
     *
     * @return turrets killed
     */
    public int getTurretsKilled() {
        return super.getInteger(data.getTurretsKilled());
    }

    /**
     * Unreal kills
     *
     * @return unreal kills
     */
    public int getUnrealKills() {
        return super.getInteger(data.getUnrealKills());
    }

    /**
     * Victory point total
     *
     * @return victory point total
     */
    public int getVictoryPointTotal() {
        return super.getInteger(data.getVictoryPointTotal());
    }

    /**
     * Vision wards bought
     *
     * @return vision wards bought
     */
    public int getVisionWardsBought() {
        return super.getInteger(data.getVisionWardsBought());
    }

    /**
     * Wards killed
     *
     * @return wards killed
     */
    public int getWardsKilled() {
        return super.getInteger(data.getWardKilled());
    }

    /**
     * Wards placed
     *
     * @return wards placed
     */
    public int getWardsPlaced() {
        return super.getInteger(data.getWardPlaced());
    }

    /**
     * Flag specifying whether or not this game was won
     *
     * @return whether or not this game was won
     */
    public boolean getWin() {
        return super.getBoolean(data.getWin());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RawStats";
    }

}
