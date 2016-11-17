package com.robrua.orianna.type.core.common;

import java.util.HashMap;
import java.util.Map;

public enum QueueType {
    ARAM_5x5(65), ARURF_5X5(318), ASCENSION_5x5(96), BILGEWATER_5x5(313), BILGEWATER_ARAM_5x5(100), BOT_5x5(7), BOT_5x5_BEGINNER(32), BOT_5x5_INTERMEDIATE(
            33), BOT_5x5_INTRO(31), BOT_ODIN_5x5(25), BOT_TT_3x3(52), BOT_URF_5x5(83), COUNTER_PICK(310), CUSTOM(0), DEFINITELY_NOT_DOMINION_5x5(
                    317), FIRSTBLOOD_1x1(72), FIRSTBLOOD_2x2(73), GROUP_FINDER_5x5(61), HEXAKILL(98), KING_PORO_5x5(300), NIGHTMARE_BOT_5x5_RANK1(
                            91), NIGHTMARE_BOT_5x5_RANK2(92), NIGHTMARE_BOT_5x5_RANK5(93), NORMAL_3x3(8), NORMAL_5x5_BLIND(2), NORMAL_5x5_DRAFT(
                                    14), ODIN_5x5_BLIND(16), ODIN_5x5_DRAFT(17), ONEFORALL_5x5(70), ONEFORALL_MIRRORMODE_5x5(78), RANKED_FLEX_SR(
                                            440), RANKED_FLEX_TT(9), RANKED_PREMADE_3x3(-1), RANKED_PREMADE_5x5(6), RANKED_SOLO_5x5(4), RANKED_TEAM_3x3(
                                                    41), RANKED_TEAM_5x5(42), SIEGE(315), SR_6x6(75), TEAM_BUILDER_DRAFT_RANKED_5x5(
                                                            410), TEAM_BUILDER_DRAFT_UNRANKED_5x5(400), URF_5x5(76);

    private static final Map<Long, QueueType> IDs = new HashMap<>();

    static {
        for(final QueueType t : QueueType.values()) {
            IDs.put(t.ID, t);
        }
    }

    /**
     * Gets the queue a specified ID
     *
     * @param ID
     *            the queue ID
     * @return the queue type
     */
    public static QueueType forID(final long ID) {
        return IDs.get(ID);
    }

    private final Long ID;

    /**
     * @param ID
     */
    private QueueType(final long ID) {
        this.ID = ID;
    }

    /**
     * Gets the ID for this queue
     *
     * @return the ID for this queue
     */
    public long getID() {
        return ID;
    }
}
