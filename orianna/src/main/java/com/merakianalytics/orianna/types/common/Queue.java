package com.merakianalytics.orianna.types.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

public enum Queue {
    /**
     * Howling Abyss
     * <p>
     * 5v5 ARAM games
     */
    ARAM(450),
    /**
     * Summoner's Rift
     * <p>
     * All Random games
     */
    ARSR_5X5(325),
    /**
     * Summoner's Rift
     * <p>
     * ARURF games
     */
    ARURF_5X5(900),
    /**
     * Crystal Scar
     * <p>
     * Ascension games
     */
    PROJECT(910),
    /**
     * Summoner's Rift
     * <p>
     * Blood Hunt Assassin games
     */
    ASSASSINATE_5X5(600),
    /**
     * Summoner's Rift
     * <p>
     * Black Market Brawlers games
     */
    BILGEWATER_5X5(313),
    /**
     * Butcher's Bridge
     * <p>
     * 5v5 ARAM games
     */
    BILGEWATER_ARAM_5X5(100),
    /**
     * Twisted Treeline
     * <p>
     * Co-op vs. AI Beginner Bot games
     */
    BOT_3X3_BEGINNER(820),
    /**
     * Twisted Treeline
     * <p>
     * Co-op vs. AI Intermediate Bot games
     */
    BOT_3X3_INTERMEDIATE(800),
    /**
     * Twisted Treeline
     * <p>
     * Co-op vs. AI Intro Bot games
     */
    BOT_3X3_INTRO(810),
    /**
     * Summoner's Rift
     * <p>
     * Co-op vs. AI Beginner Bot games
     */
    BOT_5X5_BEGINNER(840),
    /**
     * Summoner's Rift
     * <p>
     * Co-op vs. AI Intermediate Bot games
     */
    BOT_5X5_INTERMEDIATE(850),
    /**
     * Summoner's Rift
     * <p>
     * Co-op vs. AI Intro Bot games
     */
    BOT_5X5_INTRO(830),
    /**
     * Summoner's Rift
     * <p>
     * Co-op vs AI Ultra Rapid Fire games
     */
    BOT_URF_5X5(83),
    /**
     * Summoner's Rift
     * <p>
     * Nemesis games
     */
    COUNTER_PICK(310),
    /**
     * Custom games
     */
    CUSTOM(0),
    /**
     * Cosmic Ruins
     * <p>
     * Dark Star: Singularity games
     */
    DARKSTAR_3X3(610),
    /**
     * Crystal Scar
     * <p>
     * Definitely Not Dominion games
     */
    DEFINITELY_NOT_DOMINION_5X5(317),
    /**
     * Howling Abyss
     * <p>
     * 1v1 Snowdown Showdown games
     */
    FIRSTBLOOD_1X1(72),
    /**
     * Howling Abyss
     * <p>
     * 2v2 Snowdown Showdown games
     */
    FIRSTBLOOD_2X2(73),
    /**
     * Twisted Treeline
     * <p>
     * 6v6 Hexakill games
     */
    HEXAKILL(98),
    /**
     * Valoran City Park
     * <p>
     * Star Guardian Invasion: Normal games
     */
    INVASION_NORMAL(980),
    /**
     * Valoran City Park
     * <p>
     * Star Guardian Invasion: Onslaught games
     */
    INVASION_ONSLAUGHT(990),
    /**
     * Howling Abyss
     * <p>
     * Legend of the Poro King games
     */
    KINGPORO(920),
    /**
     * Summoner's Rift
     * <p>
     * Nexus Siege games
     */
    NEXUS_SIEGE(940),
    /**
     * Summoner's Rift
     * <p>
     * Doom Bots Standard games
     */
    NIGHTMARE_BOT_5X5(960),
    /**
     * Summoner's Rift
     * <p>
     * Doom Bots Voting games
     */
    NIGHTMARE_BOT_5X5_VOTE(950),
    /**
     * Twisted Treeline
     * <p>
     * 3v3 Blind Pick games
     */
    NORMAL_3X3_BLIND_PICK(460),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Blind Pick games
     */
    NORMAL_5V5_BLIND_PICK(430),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Draft Pick games
     */
    TEAM_BUILDER_DRAFT_UNRANKED_5X5(400),
    /**
     * Crash Site
     * <p>
     * Odyssey Extraction: Intro games
     */
    ODYSSEY_INTRO(1030),
    /**
     * Summoner's Rift
     * <p>
     * One for All games
     */
    ONEFORALL_RAPID_5X5(1020),
    /**
     * Howling Abyss
     * <p>
     * One For All: Mirror Mode games
     */
    ONEFORALL_MIRRORMODE_5X5(78),
    /**
     * Overcharge
     * <p>
     * PROJECT: Hunters games
     */
    OVERCHARGE(1000),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Ranked Flex games
     */
    RANKED_FLEX_SR(440),
    /**
     * Twisted Treeline
     * <p>
     * 3v3 Ranked Flex games
     */
    RANKED_FLEX_TT(470),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Ranked Solo games
     */
    RANKED_SOLO_5X5(420, "RANKED_SOLO_5x5"),
    /**
     * Summoner's Rift
     * <p>
     * Snow ARURF games
     */
    SNOWURF(1010),
    /**
     * Summoner's Rift
     * <p>
     * 6v6 Hexakill games
     */
    SR_6X6(75),
    /**
     * Summoner's Rift
     * <p>
     * Clash games
     */
    CLASH(700),
    /**
     * Convergence
     * <p>
     * Teamfight Tactics games
     */
    NORMAL_TFT(1090),
    /**
     * Convergence
     * <p>
     * Ranked Teamfight Tactics games
     */
    RANKED_TFT(1100),
    /**
     * Summoner's Rift
     * <p>
     * Ultra Rapid Fire games
     */
    URF_5X5(76),
    /**
     * Crash Site
     * <p>
     * Odyssey Extraction: Crewmember games
     */
    ODYSSEY_CREWMEMBER(1050),
    /**
     * Crash Site
     * <p>
     * Odyssey Extraction: Onslaught games
     */
    ODYSSEY_ONSLAUGHT(1070),
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
     * Summoner's Rift
     * <p>
     * 5v5 Ranked Dynamic games
     * <p>
     * Game mode deprecated in patch 6.22
     */
    DEPRECATED_TEAM_BUILDER_DRAFT_RANKED_5X5(410),
    /**
     * Howling Abyss
     * <p>
     * 5v5 ARAM games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 450
     */
    DEPRECATED_ARAM_5X5(65),
    /**
     * Howling Abyss
     * <p>
     * ARAM Co-op vs AI games
     * <p>
     * Game mode deprecated
     */
    DEPRECATED_ARAM_COOP_VS_AI(67),
    /**
     * Summoner's Rift
     * <p>
     * ARURF games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 900
     */
    DEPRECATED_ARURF_5X5(318),
    /**
     * Crystal Scar
     * <p>
     * Ascension games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 910
     */
    DEPRECATED_ASCENSION_5X5(96),
    /**
     * Summoner's Rift
     * <p>
     * Co-op vs AI games
     * <p>
     * Deprecated in favor of queueId 32 and 33
     */
    DEPRECATED_BOT_5X5(7),
    /**
     * Summoner's Rift
     * <p>
     * Co-op vs AI Beginner Bot games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 840
     */
    DEPRECATED_BOT_5X5_BEGINNER(32),
    /**
     * Summoner's Rift
     * <p>
     * Co-op vs AI Intermediate Bot games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 850
     */
    DEPRECATED_BOT_5X5_INTERMEDIATE(33),
    /**
     * Summoner's Rift
     * <p>
     * Co-op vs AI Intro Bot games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 830
     */
    DEPRECATED_BOT_5X5_INTRO(31),
    /**
     * Crystal Scar
     * <p>
     * Dominion Co-op vs AI games
     * <p>
     * Game mode deprecated
     */
    DEPRECATED_BOT_ODIN_5X5(25),
    /**
     * Twisted Treeline
     * <p>
     * Co-op vs AI games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 800
     */
    DEPRECATED_BOT_TT_3X3(52),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Team Builder games
     * <p>
     * Game mode deprecated
     */
    DEPRECATED_GROUP_FINDER_5X5(61),
    /**
     * Howling Abyss
     * <p>
     * Legend of the Poro King games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 920
     */
    DEPRECATED_KING_PORO_5X5(300),
    /**
     * Nexus Blitz
     * <p>
     * Nexus Blitz games
     * <p>
     * Deprecated in patch 9.2
     */
    DEPRECATED_NEXUS_BLITZ(1200),
    /**
     * Summoner's Rift
     * <p>
     * Doom Bots Rank 1 games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 950
     */
    DEPRECATED_NIGHTMARE_BOT_5X5_RANK1(91),
    /**
     * Summoner's Rift
     * <p>
     * Doom Bots Rank 2 games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 950
     */
    DEPRECATED_NIGHTMARE_BOT_5X5_RANK2(92),
    /**
     * Summoner's Rift
     * <p>
     * Doom Bots Rank 2 games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 950
     */
    DEPRECATED_NIGHTMARE_BOT_5X5_RANK5(93),
    /**
     * Twisted Treeline
     * <p>
     * 3v3 Normal games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 460
     */
    DEPRECATED_NORMAL_3X3(8),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Blind Pick games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 430
     */
    DEPRECATED_NORMAL_5X5_BLIND(2),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Draft Pick games
     * <p>
     * 5v5 Draft Pick games
     */
    DEPRECATED_NORMAL_5X5_DRAFT(14),
    /**
     * Crystal Scar
     * <p>
     * 5v5 Dominion Blind Pick games
     * <p>
     * Game mode deprecated
     */
    DEPRECATED_ODIN_5X5_BLIND(16),
    /**
     * Crystal Scar
     * <p>
     * 5v5 Dominion Draft Pick games
     * <p>
     * Game mode deprecated
     */
    DEPRECATED_ODIN_5X5_DRAFT(17),
    /**
     * Summoner's Rift
     * <p>
     * One for All games
     * <p>
     * Deprecated in patch 8.6 in favor of queueId 1020
     */
    DEPRECATED_ONEFORALL_5X5(70),
    /**
     * Twisted Treeline
     * <p>
     * 3v3 Ranked Flex games
     * <p>
     * Deprecated in patch 7.19 in favor of queueId 470
     */
    DEPRECATED_RANKED_PREMADE_3X3(9),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Ranked Premade games
     * <p>
     * Game mode deprecated
     */
    DEPRECATED_RANKED_PREMADE_5X5(6),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Ranked Solo games
     * <p>
     * Deprecated in favor of queueId 420
     */
    DEPRECATED_RANKED_SOLO_5X5(4),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Ranked Solo games
     * <p>
     * Deprecated in favor of queueId 420
     */
    DEPRECATED_RANKED_TEAM_3X3(41),
    /**
     * Twisted Treeline
     * <p>
     * 3v3 Ranked Team games
     * <p>
     * Game mode deprecated
     */
    DEPRECATED_RANKED_TEAM_5X5(42),
    /**
     * Summoner's Rift
     * <p>
     * 5v5 Ranked Team games
     * <p>
     * Game mode deprecated
     */
    DEPRECATED_SIEGE(315);

    private static final Map<Integer, Queue> BY_ID = getById();
    private static final Map<String, Queue> BY_API_NAME = getByApiName();
    public static final Set<Queue> RANKED = ImmutableSet.of(RANKED_SOLO_5X5, RANKED_FLEX_SR, RANKED_FLEX_TT);

    private static Map<Integer, Queue> getById() {
        final Builder<Integer, Queue> builder = ImmutableMap.builder();
        for(final Queue queue : values()) {
            builder.put(queue.id, queue);
        }
        return builder.build();
    }

    public static Queue withId(final int id) {
        return BY_ID.get(id);
    }

    private static Map<String, Queue> getByApiName() {
        final Builder<String, Queue> builder = ImmutableMap.builder();
        for(final Queue queue : values()) {
            builder.put(queue.getApiName(), queue);
        }
        return builder.build();
    }

    public static Queue withApiName(final String apiName) {
        return BY_API_NAME.get(apiName);
    }

    private final int id;
    private final String apiName;

    Queue(final int id) {
        this(id, "");
    }

    Queue(final int id, final String apiName) {
        this.id = id;
        this.apiName = apiName;
    }

    public int getId() {
        return id;
    }

    public String getApiName() {
        return apiName.isEmpty() ? name() : apiName;
    }
}
