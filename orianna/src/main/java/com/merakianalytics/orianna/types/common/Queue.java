package com.merakianalytics.orianna.types.common;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;

public enum Queue {
        /**
         * Summoner's Rift
         * <p>
         * All Random games
         */
        ALL_RANDOM(325, "ARSR_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * ARURF games
         */
        ALL_RANDOM_URF(900, "ARURF_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * Snow ARURF games
         */
        ALL_RANDOM_URF_SNOW(1010, "SNOWURF"),

        /**
         * Howling Abyss
         * <p>
         * 5v5 ARAM games
         */
        ARAM(450),

        /**
         * Crystal Scar
         * <p>
         * Ascension games
         */
        ASCENSION(910, "PROJECT"),

        /**
         * Summoner's Rift
         * <p>
         * Black Market Brawlers games
         */
        BLACK_MARKET_BRAWLERS(313, "BILGEWATER_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * 5v5 Blind Pick games
         */
        BLIND_PICK(430, "NORMAL_5V5_BLIND_PICK"),

        /**
         * Summoner's Rift
         * <p>
         * Blood Hunt Assassin games
         */
        BLOOD_MOON(600, "ASSASSINATE_5X5"),

        /**
         * Butcher's Bridge
         * <p>
         * 5v5 ARAM games
         */
        BUTCHERS_BRIDGE(100, "BILGEWATER_ARAM_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * Clash games
         */
        CLASH(700),

        /**
         * Summoner's Rift
         * <p>
         * Co-op vs. AI Beginner Bot games
         */
        COOP_VS_AI_BEGINNER(840, "BOT_5X5_BEGINNER"),

        /**
         * Summoner's Rift
         * <p>
         * Co-op vs. AI Intermediate Bot games
         */
        COOP_VS_AI_INTERMEDIATE(850, "BOT_5X5_INTERMEDIATE"),

        /**
         * Summoner's Rift
         * <p>
         * Co-op vs. AI Intro Bot games
         */
        COOP_VS_AI_INTRO(830, "BOT_5X5_INTRO"),

        /**
         * Twisted Treeline
         * <p>
         * Co-op vs. AI Beginner Bot games
         */
        COOP_VS_AI_THREES_BEGINNER(820, "BOT_3X3_BEGINNER"),

        /**
         * Twisted Treeline
         * <p>
         * Co-op vs. AI Intermediate Bot games
         */
        COOP_VS_AI_THREES_INTERMEDIATE(800, "BOT_3X3_INTERMEDIATE"),

        /**
         * Twisted Treeline
         * <p>
         * Co-op vs. AI Intro Bot games
         */
        COOP_VS_AI_THREES_INTRO(810, "BOT_3X3_INTRO"),

        /**
         * Summoner's Rift
         * <p>
         * Co-op vs AI Ultra Rapid Fire games
         */
        COOP_VS_AI_URF(83, "BOT_URF_5X5"),

        /**
         * Custom games
         */
        CUSTOM(0),

        /**
         * Cosmic Ruins
         * <p>
         * Dark Star: Singularity games
         */
        DARKSTAR(610, "DARKSTAR_3X3"),

        /**
         * Crystal Scar
         * <p>
         * Definitely Not Dominion games
         */
        DEFINITELY_NOT_DOMINION(317, "DEFINITELY_NOT_DOMINION_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * ARURF games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 900
         */
        @Deprecated
        DEPRECATED_ALL_RANDOM_URF(318, "DEPRECATED_ARURF_5X5"),

        /**
         * Howling Abyss
         * <p>
         * 5v5 ARAM games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 450
         */
        @Deprecated
        DEPRECATED_ARAM(65, "ARAM_5X5"),

        /**
         * Crystal Scar
         * <p>
         * Ascension games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 910
         */
        @Deprecated
        DEPRECATED_ASCENSION(96, "ASCENSION_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * 5v5 Blind Pick games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 430
         */
        @Deprecated
        DEPRECATED_BLIND_PICK(2, "NORMAL_5X5_BLIND"),

        /**
         * Summoner's Rift
         * <p>
         * Co-op vs AI games
         * <p>
         * Deprecated in favor of queueId 32 and 33
         */
        @Deprecated
        DEPRECATED_COOP_VS_AI(7, "BOT_5X5"),

        /**
         * Howling Abyss
         * <p>
         * ARAM Co-op vs AI games
         * <p>
         * Game mode deprecated
         */
        @Deprecated
        DEPRECATED_COOP_VS_AI_ARAM(67, "ARAM_COOP_VS_AI"),

        /**
         * Summoner's Rift
         * <p>
         * Co-op vs AI Beginner Bot games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 840
         */
        @Deprecated
        DEPRECATED_COOP_VS_AI_BEGINNER(32, "DEPRECATED_BOT_5X5_BEGINNER"),

        /**
         * Crystal Scar
         * <p>
         * Dominion Co-op vs AI games
         * <p>
         * Game mode deprecated
         */
        @Deprecated
        DEPRECATED_COOP_VS_AI_DOMINION(25, "BOT_ODIN_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * Co-op vs AI Intermediate Bot games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 850
         */
        @Deprecated
        DEPRECATED_COOP_VS_AI_INTERMEDIATE(33, "DEPRECATED_BOT_5X5_INTERMEDIATE"),

        /**
         * Summoner's Rift
         * <p>
         * Co-op vs AI Intro Bot games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 830
         */
        @Deprecated
        DEPRECATED_COOP_VS_AI_INTRO(31, "DEPRECATED_BOT_5X5_INTRO"),

        /**
         * Twisted Treeline
         * <p>
         * Co-op vs AI games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 800
         */
        @Deprecated
        DEPRECATED_COOP_VS_AI_THREES(52, "BOT_TT_3X3"),

        /**
         * Crystal Scar
         * <p>
         * 5v5 Dominion Blind Pick games
         * <p>
         * Game mode deprecated
         */
        @Deprecated
        DEPRECATED_DOMINION(16, "ODIN_5X5_BLIND"),

        /**
         * Crystal Scar
         * <p>
         * 5v5 Dominion Draft Pick games
         * <p>
         * Game mode deprecated
         */
        @Deprecated
        DEPRECATED_DOMINION_DRAFT_PICK(17, "ODIN_5X5_DRAFT"),

        /**
         * Summoner's Rift
         * <p>
         * Doom Bots Rank 1 games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 950
         */
        @Deprecated
        DEPRECATED_DOOM_BOTS_RANK_1(91, "NIGHTMARE_BOT_5X5_RANK1"),

        /**
         * Summoner's Rift
         * <p>
         * Doom Bots Rank 2 games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 950
         */
        @Deprecated
        DEPRECATED_DOOM_BOTS_RANK_2(92, "NIGHTMARE_BOT_5X5_RANK2"),

        /**
         * Summoner's Rift
         * <p>
         * Doom Bots Rank 5 games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 950
         */
        @Deprecated
        DEPRECATED_DOOM_BOTS_RANK_5(93, "NIGHTMARE_BOT_5X5_RANK5"),

        /**
         * Summoner's Rift
         * <p>
         * 5v5 Draft Pick games
         * <p>
         * 5v5 Draft Pick games
         */
        @Deprecated
        DEPRECATED_DRAFT_PICK(14, "NORMAL_5X5_DRAFT"),

        /**
         * Nexus Blitz
         * <p>
         * Nexus Blitz games
         * <p>
         * Deprecated in patch 9.2
         */
        @Deprecated
        DEPRECATED_NEXUS_BLITZ(1200, "NEXUS_BLITZ"),
                
         /**
         * Nexus Blitz
         * <p>
         * Nexus Blitz games
         * <p>
         */
        @Deprecated
        NEXUS_BLITZ(1300, "NEXUS_BLITZ"),

        /**
         * Summoner's Rift
         * <p>
         * Nexus Siege games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 940
         */
        @Deprecated
        DEPRECATED_NEXUS_SIEGE(315, "DEPRECATED_SIEGE"),

        /**
         * Summoner's Rift
         * <p>
         * One for All games
         * <p>
         * Deprecated in patch 8.6 in favor of queueId 1020
         */
        @Deprecated
        DEPRECATED_ONE_FOR_ALL(70, "ONEFORALL_5X5"),

        /**
         * Howling Abyss
         * <p>
         * Legend of the Poro King games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 920
         */
        @Deprecated
        DEPRECATED_PORO_KING(300, "KING_PORO_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * 5v5 Ranked Premade games
         * <p>
         * Game mode deprecated
         */
        @Deprecated
        DEPRECATED_RANKED_FIVES(6, "RANKED_PREMADE_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * 5v5 Ranked Solo games
         * <p>
         * Deprecated in favor of queueId 420
         */
        @Deprecated
        DEPRECATED_RANKED_SOLO(4, "RANKED_SOLO_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * 5v5 Ranked Dynamic games
         * <p>
         * Game mode deprecated in patch 6.22
         */
        @Deprecated
        DEPRECATED_RANKED_TEAM_BUILDER(410, "TEAM_BUILDER_DRAFT_RANKED_5X5"),

        /**
         * Twisted Treeline
         * <p>
         * 5v5 Ranked Team games
         * <p>
         * Game mode deprecated
         */
        @Deprecated
        DEPRECATED_RANKED_TEAM_FIVES(42, "RANKED_TEAM_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * 3v3 Ranked Team games
         * <p>
         * Game mode deprecated
         */
        @Deprecated
        DEPRECATED_RANKED_THREES(41, "RANKED_TEAM_3X3"),

        /**
         * Twisted Treeline
         * <p>
         * 3v3 Ranked Flex games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 470
         */
        @Deprecated
        DEPRECATED_RANKED_THREES_FLEX(9, "RANKED_PREMADE_3X3"),

        /**
         * Summoner's Rift
         * <p>
         * 5v5 Team Builder games
         * <p>
         * Game mode deprecated
         */
        @Deprecated
        DEPRECATED_TEAM_BUILDER(61, "GROUP_FINDER_5X5"),

        /**
         * Twisted Treeline
         * <p>
         * 3v3 Normal games
         * <p>
         * Deprecated in patch 7.19 in favor of queueId 460
         */
        @Deprecated
        DEPRECATED_THREES(8, "NORMAL_3X3"),

        /**
         * Summoner's Rift
         * <p>
         * Doom Bots Standard games
         */
        DOOM_BOTS(960, "NIGHTMARE_BOT_5X5"),

        /**
         * Summoner's Rift
         * <p>
         * Doom Bots Voting games
         */
        DOOM_BOTS_WITH_VOTING(950, "NIGHTMARE_BOT_5X5_VOTE"),

        /**
         * Summoner's Rift
         * <p>
         * 6v6 Hexakill games
         */
        HEXAKILL(75, "SR_6X6"),

        /**
         * Twisted Treeline
         * <p>
         * 6v6 Hexakill games
         */
        HEXAKILL_THREES(98),

        /**
         * Valoran City Park
         * <p>
         * Star Guardian Invasion: Normal games
         */
        INVASION(980, "INVASION_NORMAL"),

        /**
         * Valoran City Park
         * <p>
         * Star Guardian Invasion: Onslaught games
         */
        INVASION_ONSLAUGHT(990),

        /**
         * Summoner's Rift
         * <p>
         * Nemesis games
         */
        NEMESIS_DRAFT(310, "COUNTER_PICK"),

        /**
         * Summoner's Rift
         * <p>
         * Nexus Siege games
         */
        NEXUS_SIEGE(940),

        /**
         * Summoner's Rift
         * <p>
         * 5v5 Draft Pick games
         */
        NORMAL(400, "TEAM_BUILDER_DRAFT_UNRANKED_5X5"),

        /**
         * Crash Site
         * <p>
         * Odyssey Extraction: Cadet games
         */
        ODYSSEY_CADET(1040),

        /**
         * Crash Site
         * <p>
         * Odyssey Extraction: Captain games
         */
        ODYSSEY_CAPTAIN(1060),

        /**
         * Crash Site
         * <p>
         * Odyssey Extraction: Crewmember games
         */
        ODYSSEY_CREWMEMBER(1050),

        /**
         * Crash Site
         * <p>
         * Odyssey Extraction: Intro games
         */
        ODYSSEY_INTRO(1030),

        /**
         * Crash Site
         * <p>
         * Odyssey Extraction: Onslaught games
         */
        ODYSSEY_ONSLAUGHT(1070),

        /**
         * Summoner's Rift
         * <p>
         * One for All games
         */
        ONE_FOR_ALL(1020, "ONEFORALL_RAPID_5X5"),

        /**
         * Howling Abyss
         * <p>
         * One For All: Mirror Mode games
         */
        ONE_FOR_ALL_MIRROR(78, "ONEFORALL_MIRRORMODE_5X5"),

        /**
         * Overcharge
         * <p>
         * PROJECT: Hunters games
         */
        OVERCHARGE(1000),

        /**
         * Howling Abyss
         * <p>
         * Legend of the Poro King games
         */
        PORO_KING(920, "KINGPORO"),

        /**
         * Summoner's Rift
         * <p>
         * 5v5 Ranked Flex games
         */
        RANKED_FLEX(440, "RANKED_FLEX_SR"),

        /**
         * Summoner's Rift
         * <p>
         * 5v5 Ranked Solo games
         */
        RANKED_SOLO(420, "RANKED_SOLO_5x5"),

        /**
         * Convergence
         * <p>
         * Ranked Teamfight Tactics games
         */
        RANKED_TFT(1100),

        /**
         * Twisted Treeline
         * <p>
         * 3v3 Ranked Flex games
         */
        RANKED_THREES(470, "RANKED_FLEX_TT"),

        /**
         * Howling Abyss
         * <p>
         * 2v2 Snowdown Showdown games
         */
        SHOWDOWN_DUO(73, "FIRSTBLOOD_2X2"),

        /**
         * Howling Abyss
         * <p>
         * 1v1 Snowdown Showdown games
         */
        SHOWDOWN_SOLO(72, "FIRSTBLOOD_1X1"),

        /**
         * Convergence
         * <p>
         * Teamfight Tactics games
         */
        TFT(1090, "NORMAL_TFT"),

        /**
         * Twisted Treeline
         * <p>
         * 3v3 Blind Pick games
         */
        THREES(460, "NORMAL_3X3_BLIND_PICK"),

        /**
         * Summoner's Rift
         * <p>
         * Ultra Rapid Fire games
         */
        URF(76, "URF_5X5");

    private static final Map<Integer, Queue> BY_ID = getById();
    private static final Map<String, Queue> BY_TAG = getByTag();
    public static final Set<Queue> RANKED = ImmutableSet.of(RANKED_SOLO, RANKED_FLEX, RANKED_THREES, RANKED_TFT);

    private static Map<Integer, Queue> getById() {
        final Builder<Integer, Queue> builder = ImmutableMap.builder();
        for(final Queue queue : values()) {
            builder.put(queue.getId(), queue);
        }
        return builder.build();
    }

    private static Map<String, Queue> getByTag() {
        final Builder<String, Queue> builder = ImmutableMap.builder();
        for(final Queue queue : values()) {
            builder.put(queue.getTag(), queue);
        }
        return builder.build();
    }

    public static Queue withId(final int id) {
        return BY_ID.get(id);
    }

    public static Queue withTag(final String tag) {
        return BY_TAG.get(tag);
    }

    private final int id;
    private final String tag;

    private Queue(final int id) {
        this(id, null);
    }

    private Queue(final int id, final String tag) {
        this.id = id;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag == null ? name() : tag;
    }
}
