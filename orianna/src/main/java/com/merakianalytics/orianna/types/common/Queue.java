package com.merakianalytics.orianna.types.common;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;

public enum Queue {
        ARAM_5x5(65),
        ARSR_5x5(325),
        ARURF_5X5(318),
        ASCENSION_5x5(96),
        ASSASSINATE_5x5(600),
        BILGEWATER_5x5(313),
        BILGEWATER_ARAM_5x5(100),
        BOT_5x5(7),
        BOT_5x5_BEGINNER(32),
        BOT_5x5_INTERMEDIATE(33),
        BOT_5x5_INTRO(31),
        BOT_ODIN_5x5(25),
        BOT_TT_3x3(52),
        BOT_URF_5x5(83),
        COUNTER_PICK(310),
        CUSTOM(0),
        DARKSTAR_3x3(610),
        DEFINITELY_NOT_DOMINION_5x5(317),
        FIRSTBLOOD_1x1(72),
        FIRSTBLOOD_2x2(73),
        GROUP_FINDER_5x5(61),
        HEXAKILL(98),
        KING_PORO_5x5(300),
        NIGHTMARE_BOT_5x5_RANK1(91),
        NIGHTMARE_BOT_5x5_RANK2(92),
        NIGHTMARE_BOT_5x5_RANK5(93),
        NORMAL_3x3(8),
        NORMAL_5x5_BLIND(2),
        NORMAL_5x5_DRAFT(14),
        ODIN_5x5_BLIND(16),
        ODIN_5x5_DRAFT(17),
        ONEFORALL_5x5(70),
        ONEFORALL_MIRRORMODE_5x5(78),
        RANKED_FLEX_SR(440),
        RANKED_FLEX_TT(9),
        RANKED_PREMADE_5x5(6),
        RANKED_SOLO_5x5(4),
        RANKED_TEAM_3x3(41),
        RANKED_TEAM_5x5(42),
        SIEGE(315),
        SR_6x6(75),
        TB_BLIND_SUMMONERS_RIFT_5x5(430),
        TEAM_BUILDER_DRAFT_RANKED_5x5(410),
        TEAM_BUILDER_DRAFT_UNRANKED_5x5(400),
        TEAM_BUILDER_RANKED_SOLO(420),
        URF_5x5(76);

    private static final Map<Integer, Queue> BY_ID = getById();
    public static final Set<Queue> RANKED = ImmutableSet.of(RANKED_SOLO_5x5, RANKED_FLEX_SR, RANKED_FLEX_TT);

    private static final Map<Integer, Queue> getById() {
        final Builder<Integer, Queue> builder = ImmutableMap.builder();
        for(final Queue queue : values()) {
            builder.put(queue.id, queue);
        }
        return builder.build();
    }

    public static Queue withId(final int id) {
        return BY_ID.get(id);
    }

    private final int id;

    private Queue(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
