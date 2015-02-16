package com.robrua.orianna.type.core.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.staticdata.Item;
import com.robrua.orianna.type.exception.MissingDataException;

public class ParticipantStats extends OriannaObject<com.robrua.orianna.type.dto.match.ParticipantStats> {
    private static final long serialVersionUID = -6687839465360280189L;
    private Item item0, item1, item2, item3, item4, item5, item6;

    /**
     * @param data
     *            the underlying dto
     */
    public ParticipantStats(final com.robrua.orianna.type.dto.match.ParticipantStats data) {
        super(data, com.robrua.orianna.type.dto.match.ParticipantStats.class);
    }

    /**
     * Number of assists
     *
     * @return number of assists
     */
    public long getAssists() {
        return super.getLong(data.getAssists());
    }

    /**
     * If game was a dominion game, player's combat score, otherwise 0
     *
     * @return if game was a dominion game, player's combat score, otherwise 0
     */
    public long getCombatPlayerScore() {
        return super.getLong(data.getCombatPlayerScore());
    }

    /**
     * Number of deaths
     *
     * @return number of deaths
     */
    public long getDeaths() {
        return super.getLong(data.getDeaths());
    }

    /**
     * Number of double kills
     *
     * @return number of double kills
     */
    public long getDoubleKills() {
        return super.getLong(data.getDoubleKills());
    }

    /**
     * Flag indicating if participant got an assist on first blood
     *
     * @return flag indicating if participant got an assist on first blood
     */
    public boolean getFirstBloodAssist() {
        return super.getBoolean(data.getFirstBloodAssist());
    }

    /**
     * Flag indicating if participant got first blood
     *
     * @return flag indicating if participant got first blood
     */
    public boolean getFirstBloodKill() {
        return super.getBoolean(data.getFirstBloodKill());
    }

    /**
     * Flag indicating if participant got an assist on the first inhibitor
     *
     * @return flag indicating if participant got an assist on the first
     *         inhibitor
     */
    public boolean getFirstInhibitorAssist() {
        return super.getBoolean(data.getFirstInhibitorAssist());
    }

    /**
     * Flag indicating if participant destroyed the first inhibitor
     *
     * @return flag indicating if participant destroyed the first inhibitor
     */
    public boolean getFirstInhibitorKill() {
        return super.getBoolean(data.getFirstInhibitorKill());
    }

    /**
     * Flag indicating if participant got an assist on the first tower
     *
     * @return flag indicating if participant got an assist on the first tower
     */
    public boolean getFirstTowerAssist() {
        return super.getBoolean(data.getFirstTowerAssist());
    }

    /**
     * Flag indicating if participant destroyed the first tower
     *
     * @return flag indicating if participant destroyed the first tower
     */
    public boolean getFirstTowerKill() {
        return super.getBoolean(data.getFirstTowerKill());
    }

    /**
     * Gold earned
     *
     * @return gold earned
     */
    public long getGoldEarned() {
        return super.getLong(data.getGoldEarned());
    }

    /**
     * Gold spent
     *
     * @return gold spent
     */
    public long getGoldSpent() {
        return super.getLong(data.getGoldSpent());
    }

    /**
     * Number of inhibitor kills
     *
     * @return number of inhibitor kills
     */
    public long getInhibitorKills() {
        return super.getLong(data.getInhibitorKills());
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

        final Long l = data.getItem0();
        if(l == null) {
            throw new MissingDataException("Item #0 ID is null.");
        }

        item0 = RiotAPI.getItem(l.longValue());
        return item0;
    }

    /**
     * Item 0 ID
     *
     * @return item 0
     */
    public long getItem0ID() {
        return super.getLong(data.getItem0());
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

        final Long l = data.getItem1();
        if(l == null) {
            throw new MissingDataException("Item #1 ID is null.");
        }

        item1 = RiotAPI.getItem(l.longValue());
        return item1;
    }

    /**
     * Item 1 ID
     *
     * @return item 1 ID
     */
    public long getItem1ID() {
        return super.getLong(data.getItem1());
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

        final Long l = data.getItem2();
        if(l == null) {
            throw new MissingDataException("Item #2 ID is null.");
        }

        item2 = RiotAPI.getItem(l.longValue());
        return item2;
    }

    /**
     * Item 2 ID
     *
     * @return item 2 ID
     */
    public long getItem2ID() {
        return super.getLong(data.getItem2());
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

        final Long l = data.getItem3();
        if(l == null) {
            throw new MissingDataException("Item #3 ID is null.");
        }

        item3 = RiotAPI.getItem(l.longValue());
        return item3;
    }

    /**
     * Item 3 ID
     *
     * @return item 3 ID
     */
    public long getItem3ID() {
        return super.getLong(data.getItem3());
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

        final Long l = data.getItem4();
        if(l == null) {
            throw new MissingDataException("Item #4 ID is null.");
        }

        item4 = RiotAPI.getItem(l.longValue());
        return item4;
    }

    /**
     * Item 4 ID
     *
     * @return item 4 ID
     */
    public long getItem4ID() {
        return super.getLong(data.getItem4());
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

        final Long l = data.getItem5();
        if(l == null) {
            throw new MissingDataException("Item #5 ID is null.");
        }

        item5 = RiotAPI.getItem(l.longValue());
        return item5;
    }

    /**
     * Item 5 ID
     *
     * @return item 5 ID
     */
    public long getItem5ID() {
        return super.getLong(data.getItem5());
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

        final Long l = data.getItem6();
        if(l == null) {
            throw new MissingDataException("Item #6 ID is null.");
        }

        item6 = RiotAPI.getItem(l.longValue());
        return item6;
    }

    /**
     * Item 6 ID
     *
     * @return item 6 ID
     */
    public long getItem6ID() {
        return super.getLong(data.getItem6());
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
            final Long l = data.getItem0();
            if(l == null) {
                throw new MissingDataException("Item #0 ID is null.");
            }

            toLoad.add(l.longValue());
        }

        if(item1 != null) {
            items.add(1, item1);
        }
        else {
            final Long l = data.getItem1();
            if(l == null) {
                throw new MissingDataException("Item #1 ID is null.");
            }

            toLoad.add(l.longValue());
        }

        if(item2 != null) {
            items.add(2, item2);
        }
        else {
            final Long l = data.getItem2();
            if(l == null) {
                throw new MissingDataException("Item #2 ID is null.");
            }

            toLoad.add(l.longValue());
        }

        if(item3 != null) {
            items.add(3, item3);
        }
        else {
            final Long l = data.getItem3();
            if(l == null) {
                throw new MissingDataException("Item #3 ID is null.");
            }

            toLoad.add(l.longValue());
        }

        if(item4 != null) {
            items.add(4, item4);
        }
        else {
            final Long l = data.getItem4();
            if(l == null) {
                throw new MissingDataException("Item #4 ID is null.");
            }

            toLoad.add(l.longValue());
        }

        if(item5 != null) {
            items.add(5, item5);
        }
        else {
            final Long l = data.getItem5();
            if(l == null) {
                throw new MissingDataException("Item #5 ID is null.");
            }

            toLoad.add(l.longValue());
        }

        if(item6 != null) {
            items.add(6, item6);
        }
        else {
            final Long l = data.getItem6();
            if(l == null) {
                throw new MissingDataException("Item #6 ID is null.");
            }

            toLoad.add(l.longValue());
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
     * Number of killing sprees
     *
     * @return number of killing sprees
     */
    public long getKillingSprees() {
        return super.getLong(data.getKillingSprees());
    }

    /**
     * Number of kills
     *
     * @return number of kills
     */
    public long getKills() {
        return super.getLong(data.getKills());
    }

    /**
     * Largest critical strike
     *
     * @return largest critical strike
     */
    public long getLargestCriticalStrike() {
        return super.getLong(data.getLargestCriticalStrike());
    }

    /**
     * Largest killing spree
     *
     * @return largest killing spree
     */
    public long getLargestKillingSpree() {
        return super.getLong(data.getLargestKillingSpree());
    }

    /**
     * Largest multi kill
     *
     * @return largest multi kill
     */
    public long getLargestMultiKill() {
        return super.getLong(data.getLargestMultiKill());
    }

    /**
     * Champion level achieved
     *
     * @return champion level achieved
     */
    public long getLevel() {
        return super.getLong(data.getChampLevel());
    }

    /**
     * Magical damage dealt
     *
     * @return magical damage dealt
     */
    public long getMagicDamageDealt() {
        return super.getLong(data.getMagicDamageDealt());
    }

    /**
     * Magical damage dealt to champions
     *
     * @return magical damage dealt to champions
     */
    public long getMagicDamageDealtToChampions() {
        return super.getLong(data.getMagicDamageDealtToChampions());
    }

    /**
     * Magic damage taken
     *
     * @return magic damage taken
     */
    public long getMagicDamageTaken() {
        return super.getLong(data.getMagicDamageTaken());
    }

    /**
     * Minions killed
     *
     * @return minions killed
     */
    public long getMinionsKilled() {
        return super.getLong(data.getMinionsKilled());
    }

    /**
     * Neutral minions killed
     *
     * @return neutral minions killed
     */
    public long getNeutralMinionsKilled() {
        return super.getLong(data.getNeutralMinionsKilled());
    }

    /**
     * Neutral jungle minions killed in the enemy team's jungle
     *
     * @return neutral jungle minions killed in the enemy team's jungle
     */
    public long getNeutralMinionsKilledEnemyJungle() {
        return super.getLong(data.getNeutralMinionsKilledEnemyJungle());
    }

    /**
     * Neutral jungle minions killed in your team's jungle
     *
     * @return neutral jungle minions killed in your team's jungle
     */
    public long getNeutralMinionsKilledTeamJungle() {
        return super.getLong(data.getNeutralMinionsKilledTeamJungle());
    }

    /**
     * If game was a dominion game, number of node capture assists
     *
     * @return if game was a dominion game, number of node capture assists
     */
    public long getNodeCaptureAssists() {
        return super.getLong(data.getNodeCaptureAssist());
    }

    /**
     * If game was a dominion game, number of node captures
     *
     * @return if game was a dominion game, number of node captures
     */
    public long getNodeCaptures() {
        return super.getLong(data.getNodeCapture());
    }

    /**
     * If game was a dominion game, number of node neutralizations
     *
     * @return if game was a dominion game, number of node neutralizations
     */
    public long getNodeNeutralizations() {
        return super.getLong(data.getNodeNeutralize());
    }

    /**
     * If game was a dominion game, number of node neutralization assists
     *
     * @return if game was a dominion game, number of node neutralization
     *         assists
     */
    public long getNodeNeutralizeAssists() {
        return super.getLong(data.getNodeNeutralizeAssist());
    }

    /**
     * If game was a dominion game, player's objectives score, otherwise 0
     *
     * @return if game was a dominion game, player's objectives score, otherwise
     *         0
     */
    public long getObjectivePlayerScore() {
        return super.getLong(data.getObjectivePlayerScore());
    }

    /**
     * Number of penta kills
     *
     * @return number of penta kills
     */
    public long getPentaKills() {
        return super.getLong(data.getPentaKills());
    }

    /**
     * Physical damage dealt
     *
     * @return physical damage dealt
     */
    public long getPhysicalDamageDealt() {
        return super.getLong(data.getPhysicalDamageDealt());
    }

    /**
     * Physical damage dealt to champions
     *
     * @return physical damage dealt to champions
     */
    public long getPhysicalDamageDealtToChampions() {
        return super.getLong(data.getPhysicalDamageDealtToChampions());
    }

    /**
     * Physical damage taken
     *
     * @return physical damage taken
     */
    public long getPhysicalDamageTaken() {
        return super.getLong(data.getPhysicalDamageTaken());
    }

    /**
     * Number of quadra kills
     *
     * @return number of quadra kills
     */
    public long getQuadraKills() {
        return super.getLong(data.getQuadraKills());
    }

    /**
     * Sight wards purchased
     *
     * @return sight wards purchased
     */
    public long getSightWardsBought() {
        return super.getLong(data.getSightWardsBoughtInGame());
    }

    /**
     * If game was a dominion game, number of completed team objectives (i.e.,
     * quests)
     *
     * @return if game was a dominion game, number of completed team objectives
     *         (i.e., quests)
     */
    public long getTeamObjectives() {
        return super.getLong(data.getTeamObjective());
    }

    /**
     * Total damage dealt
     *
     * @return total damage dealt
     */
    public long getTotalDamageDealt() {
        return super.getLong(data.getTotalDamageDealt());
    }

    /**
     * Total damage dealt to champions
     *
     * @return total damage dealt to champions
     */
    public long getTotalDamageDealtToChampions() {
        return super.getLong(data.getTotalDamageDealtToChampions());
    }

    /**
     * Total damage taken
     *
     * @return total damage taken
     */
    public long getTotalDamageTaken() {
        return super.getLong(data.getTotalDamageTaken());
    }

    /**
     * Total heal amount
     *
     * @return total heal amount
     */
    public long getTotalHealing() {
        return super.getLong(data.getTotalHeal());
    }

    /**
     * If game was a dominion game, player's total score, otherwise 0
     *
     * @return if game was a dominion game, player's total score, otherwise 0
     */
    public long getTotalPlayerScore() {
        return super.getLong(data.getTotalPlayerScore());
    }

    /**
     * If game was a dominion game, team rank of the player's total score (e.g.,
     * 1-5)
     *
     * @return if game was a dominion game, team rank of the player's total
     *         score (e.g., 1-5)
     */
    public long getTotalScoreRank() {
        return super.getLong(data.getTotalScoreRank());
    }

    /**
     * Total dealt crowd control time
     *
     * @return total dealt crowd control time
     */
    public long getTotalTimeCrowdControlDealt() {
        return super.getLong(data.getTotalTimeCrowdControlDealt());
    }

    /**
     * Total units healed
     *
     * @return total units healed
     */
    public long getTotalUnitsHealed() {
        return super.getLong(data.getTotalUnitsHealed());
    }

    /**
     * Number of tower kills
     *
     * @return number of tower kills
     */
    public long getTowerKills() {
        return super.getLong(data.getTowerKills());
    }

    /**
     * Number of triple kills
     *
     * @return number of triple kills
     */
    public long getTripleKills() {
        return super.getLong(data.getTripleKills());
    }

    /**
     * True damage dealt
     *
     * @return true damage dealt
     */
    public long getTrueDamageDealt() {
        return super.getLong(data.getTrueDamageDealt());
    }

    /**
     * True damage dealt to champions
     *
     * @return true damage dealt to champions
     */
    public long getTrueDamageDealtToChampions() {
        return super.getLong(data.getTrueDamageDealtToChampions());
    }

    /**
     * True damage taken
     *
     * @return true damage taken
     */
    public long getTrueDamageTaken() {
        return super.getLong(data.getTrueDamageTaken());
    }

    /**
     * Number of unreal kills
     *
     * @return number of unreal kills
     */
    public long getUnrealKills() {
        return super.getLong(data.getUnrealKills());
    }

    /**
     * Vision wards purchased
     *
     * @return vision wards purchased
     */
    public long getVisionWardsBought() {
        return super.getLong(data.getVisionWardsBoughtInGame());
    }

    /**
     * Number of wards killed
     *
     * @return number of wards killed
     */
    public long getWardsKilled() {
        return super.getLong(data.getWardsKilled());
    }

    /**
     * Number of wards placed
     *
     * @return number of wards placed
     */
    public long getWardsPlaced() {
        return super.getLong(data.getWardsPlaced());
    }

    /**
     * Flag indicating whether or not the participant won
     *
     * @return flag indicating whether or not the participant won
     */
    public boolean getWinner() {
        return super.getBoolean(data.getWinner());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantStats";
    }

}
