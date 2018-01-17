package com.merakianalytics.orianna.datapipeline.transformers.dtodata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Minutes;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.data.match.Event;
import com.merakianalytics.orianna.types.data.match.Frame;
import com.merakianalytics.orianna.types.data.match.Match;
import com.merakianalytics.orianna.types.data.match.MatchList;
import com.merakianalytics.orianna.types.data.match.MatchReference;
import com.merakianalytics.orianna.types.data.match.Participant;
import com.merakianalytics.orianna.types.data.match.ParticipantFrame;
import com.merakianalytics.orianna.types.data.match.ParticipantStats;
import com.merakianalytics.orianna.types.data.match.ParticipantTimeline;
import com.merakianalytics.orianna.types.data.match.Position;
import com.merakianalytics.orianna.types.data.match.RuneStats;
import com.merakianalytics.orianna.types.data.match.StatTotals;
import com.merakianalytics.orianna.types.data.match.Team;
import com.merakianalytics.orianna.types.data.match.Timeline;
import com.merakianalytics.orianna.types.data.match.TournamentMatches;
import com.merakianalytics.orianna.types.dto.match.MatchEvent;
import com.merakianalytics.orianna.types.dto.match.MatchFrame;
import com.merakianalytics.orianna.types.dto.match.MatchParticipantFrame;
import com.merakianalytics.orianna.types.dto.match.MatchPosition;
import com.merakianalytics.orianna.types.dto.match.MatchTimeline;
import com.merakianalytics.orianna.types.dto.match.Matchlist;
import com.merakianalytics.orianna.types.dto.match.ParticipantIdentity;
import com.merakianalytics.orianna.types.dto.match.TeamBans;
import com.merakianalytics.orianna.types.dto.match.TeamStats;

public class MatchTransformer extends AbstractDataTransformer {
    private static Map<String, Double> getPerMinDeltas(final StatTotals totals, final Duration duration) {
        final double minutes = (double)duration.getMillis() / (double)Minutes.ONE.toStandardDuration().getMillis();
        final Map<String, Double> perMinDeltas = new HashMap<>();
        perMinDeltas.put("0-10", totals.getAt10() / Math.min(10.0, Math.max(minutes, 0.0)));
        if(minutes > 10.0) {
            perMinDeltas.put("10-20", (totals.getAt20() - totals.getAt10()) / Math.min(10.0, minutes - 10.0));
        }
        if(minutes > 20.0) {
            perMinDeltas.put("20-30", (totals.getAt30() - totals.getAt20()) / Math.min(10.0, minutes - 20.0));
        }
        if(minutes > 30.0) {
            perMinDeltas.put("30-end", (totals.getAtGameEnd() - totals.getAt30()) / Math.min(10.0, minutes - 30.0));
        }
        return perMinDeltas;
    }

    private static StatTotals getStatTotals(final Map<String, Double> perMinDeltas, final long durationInSeconds) {
        final double minutes = durationInSeconds / 60.0;
        final StatTotals totals = new StatTotals();
        totals.setAt10(Math.min(10.0, Math.max(minutes, 0.0)) * (perMinDeltas.get("0-10") == null ? 0.0 : perMinDeltas.get("0-10")));
        totals.setAt20(
            totals.getAt10() + Math.min(10.0, Math.max(minutes - 10.0, 0.0)) * (perMinDeltas.get("10-20") == null ? 0.0 : perMinDeltas.get("10-20")));
        totals.setAt30(
            totals.getAt20() + Math.min(10.0, Math.max(minutes - 20.0, 0.0)) * (perMinDeltas.get("20-30") == null ? 0.0 : perMinDeltas.get("20-30")));
        totals.setAtGameEnd(
            totals.getAt30() + Math.min(10.0, Math.max(minutes - 30.0, 0.0)) * (perMinDeltas.get("30-end") == null ? 0.0 : perMinDeltas.get("30-end")));
        return totals;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.Match.class, to = Match.class)
    public Match transform(final com.merakianalytics.orianna.types.dto.match.Match item, final PipelineContext context) {
        final Object previousDuration = context.put("duration", item.getGameDuration());
        final Object previousPlatform = context.put("platform", item.getPlatformId());
        final Object previousVersion = context.put("version", item.getGameVersion());
        final Match match = new Match();
        for(final TeamStats team : item.getTeams()) {
            if(com.merakianalytics.orianna.types.common.Side.BLUE.getId() == team.getTeamId()) {
                match.setBlueTeam(transform(team, context));
            } else {
                match.setRedTeam(transform(team, context));
            }
        }
        match.setCreationTime(new DateTime(item.getGameCreation()));
        match.setDuration(Duration.standardSeconds(item.getGameDuration()));
        match.setId(item.getGameId());
        match.setMap(item.getMapId());
        match.setMode(item.getGameMode());
        match.setPlatform(item.getPlatformId());

        final List<Participant> players = new ArrayList<>(item.getParticipantIdentities().size());
        final Map<Integer, com.merakianalytics.orianna.types.dto.match.Participant> byId = new HashMap<>();
        for(final com.merakianalytics.orianna.types.dto.match.Participant participant : item.getParticipants()) {
            byId.put(participant.getParticipantId(), participant);
        }
        final Object previousPlayer = context.get("player");
        for(int i = 0; i < item.getParticipantIdentities().size(); i++) {
            final int participantId = item.getParticipantIdentities().get(i).getParticipantId();
            final com.merakianalytics.orianna.types.dto.match.Player player = item.getParticipantIdentities().get(i).getPlayer();
            final com.merakianalytics.orianna.types.dto.match.Participant participant = byId.get(participantId);
            context.put("player", player);
            players.add(transform(participant, context));
        }
        context.put("player", previousPlayer);
        match.setParticipants(players);

        match.setQueue(item.getQueueId());
        match.setSeason(item.getSeasonId());
        match.setTournamentCode(item.getTournamentCode());
        match.setType(item.getGameType());
        match.setVersion(item.getGameVersion());
        context.put("duration", previousDuration);
        context.put("platform", previousPlatform);
        context.put("version", previousVersion);
        return match;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.MatchReference.class, to = MatchReference.class)
    public MatchReference transform(final com.merakianalytics.orianna.types.dto.match.MatchReference item, final PipelineContext context) {
        final MatchReference reference = new MatchReference();
        reference.setAccountId(item.getAccountId());
        reference.setChampionId(item.getChampion());
        reference.setCreationTime(new DateTime(item.getTimestamp()));
        reference.setId(item.getGameId());
        reference.setLane(item.getLane());
        reference.setPlatform(item.getPlatformId());
        reference.setQueue(item.getQueue());
        reference.setRole(item.getRole());
        reference.setSeason(item.getSeason());
        return reference;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.Participant.class, to = Participant.class)
    public Participant transform(final com.merakianalytics.orianna.types.dto.match.Participant item, final PipelineContext context) {
        final Participant converted = new Participant();
        converted.setVersion((String)context.get("version"));
        converted.setParticipantId(item.getParticipantId());
        converted.setChampionId(item.getChampionId());
        converted.setHighestTierInSeason(item.getHighestAchievedSeasonTier());
        converted.setStats(transform(item.getStats(), context));
        converted.setSummonerSpellDId(item.getSpell1Id());
        converted.setSummonerSpellFId(item.getSpell2Id());
        converted.setTeam(item.getTeamId());
        converted.setTimeline(transform(item.getTimeline(), context));
        converted.setLane(item.getTimeline().getLane());
        converted.setRole(item.getTimeline().getRole());
        converted.setPrimaryRunePath(item.getStats().getPerkPrimaryStyle());
        converted.setSecondaryRunePath(item.getStats().getPerkSubStyle());
        final List<Integer> items = new ArrayList<>(7);
        items.add(item.getStats().getItem0());
        items.add(item.getStats().getItem1());
        items.add(item.getStats().getItem2());
        items.add(item.getStats().getItem3());
        items.add(item.getStats().getItem4());
        items.add(item.getStats().getItem5());
        items.add(item.getStats().getItem6());
        converted.setItems(items);
        final List<RuneStats> runes = new ArrayList<>(6);
        RuneStats rune = new RuneStats();
        rune.setId(item.getStats().getPerk0());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(item.getStats().getPerk0Var1());
        rune.getVariables().add(item.getStats().getPerk0Var2());
        rune.getVariables().add(item.getStats().getPerk0Var3());
        runes.add(rune);
        rune = new RuneStats();
        rune.setId(item.getStats().getPerk1());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(item.getStats().getPerk1Var1());
        rune.getVariables().add(item.getStats().getPerk1Var2());
        rune.getVariables().add(item.getStats().getPerk1Var3());
        runes.add(rune);
        rune = new RuneStats();
        rune.setId(item.getStats().getPerk2());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(item.getStats().getPerk2Var1());
        rune.getVariables().add(item.getStats().getPerk2Var2());
        rune.getVariables().add(item.getStats().getPerk2Var3());
        runes.add(rune);
        rune = new RuneStats();
        rune.setId(item.getStats().getPerk3());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(item.getStats().getPerk3Var1());
        rune.getVariables().add(item.getStats().getPerk3Var2());
        rune.getVariables().add(item.getStats().getPerk3Var3());
        runes.add(rune);
        rune = new RuneStats();
        rune.setId(item.getStats().getPerk4());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(item.getStats().getPerk4Var1());
        rune.getVariables().add(item.getStats().getPerk4Var2());
        rune.getVariables().add(item.getStats().getPerk4Var3());
        runes.add(rune);
        rune = new RuneStats();
        rune.setId(item.getStats().getPerk5());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(item.getStats().getPerk5Var1());
        rune.getVariables().add(item.getStats().getPerk5Var2());
        rune.getVariables().add(item.getStats().getPerk5Var3());
        runes.add(rune);
        converted.setRuneStats(runes);
        final com.merakianalytics.orianna.types.dto.match.Player player = (com.merakianalytics.orianna.types.dto.match.Player)context.get("player");
        converted.setAccountId(player.getAccountId());
        converted.setCurrentAccountId(player.getCurrentAccountId());
        converted.setCurrentPlatform(player.getCurrentPlatformId());
        converted.setMatchHistoryURI(player.getMatchHistoryUri());
        converted.setPlatform(player.getPlatformId());
        converted.setProfileIconId(player.getProfileIcon());
        converted.setSummonerId(player.getSummonerId());
        converted.setSummonerName(player.getSummonerName());
        return converted;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.ParticipantStats.class, to = ParticipantStats.class)
    public ParticipantStats transform(final com.merakianalytics.orianna.types.dto.match.ParticipantStats item, final PipelineContext context) {
        final ParticipantStats stats = new ParticipantStats();
        stats.setAltarsCaptured(item.getAltarsCaptured());
        stats.setAltarsNeutralized(item.getAltarsNeutralized());
        stats.setAssists(item.getAssists());
        stats.setChampionLevel(item.getChampLevel());
        stats.setCombatScore(item.getCombatPlayerScore());
        stats.setCreepScore(item.getTotalMinionsKilled());
        stats.setCrowdControlDealt(Duration.standardSeconds(item.getTotalTimeCrowdControlDealt()));
        stats.setCrowdControlDealtToChampions(Duration.standardSeconds(item.getTimeCCingOthers()));
        stats.setDamageDealt((int)item.getTotalDamageDealt());
        stats.setDamageDealtToChampions((int)item.getTotalDamageDealtToChampions());
        stats.setDamageHealed((int)item.getTotalHeal());
        stats.setDamageMitigated((int)item.getDamageSelfMitigated());
        stats.setDamageTaken((int)item.getTotalDamageTaken());
        stats.setDamageToObjectives((int)item.getDamageDealtToObjectives());
        stats.setDamageToTurrets((int)item.getDamageDealtToTurrets());
        stats.setDeaths(item.getDeaths());
        stats.setDoubleKills(item.getDoubleKills());
        stats.setFirstBloodAssistant(item.isFirstBloodAssist());
        stats.setFirstBloodKiller(item.isFirstBloodKill());
        stats.setFirstInhibitorKillAssistant(item.isFirstInhibitorAssist());
        stats.setFirstInhibitorKiller(item.isFirstInhibitorKill());
        stats.setFirstTowerKillAssistant(item.isFirstTowerAssist());
        stats.setFirstTowerKiller(item.isFirstTowerKill());
        stats.setGoldEarned(item.getGoldEarned());
        stats.setGoldSpent(item.getGoldSpent());
        stats.setGreenWardsPurchased(item.getSightWardsBoughtInGame());
        stats.setHexaKills(item.getUnrealKills());
        stats.setInhibitorKills(item.getInhibitorKills());
        stats.setPlayerScore0(item.getPlayerScore0());
        stats.setPlayerScore1(item.getPlayerScore1());
        stats.setPlayerScore2(item.getPlayerScore2());
        stats.setPlayerScore3(item.getPlayerScore3());
        stats.setPlayerScore4(item.getPlayerScore4());
        stats.setPlayerScore5(item.getPlayerScore5());
        stats.setPlayerScore6(item.getPlayerScore6());
        stats.setPlayerScore7(item.getPlayerScore7());
        stats.setPlayerScore8(item.getPlayerScore8());
        stats.setPlayerScore9(item.getPlayerScore9());
        stats.setKillingSprees(item.getKillingSprees());
        stats.setKills(item.getKills());
        stats.setLargestCriticalStrike(item.getLargestCriticalStrike());
        stats.setLargestKillingSpree(item.getLargestKillingSpree());
        stats.setLargestMultiKill(item.getLargestMultiKill());
        stats.setLongestTimeAlive(Duration.standardSeconds(item.getLongestTimeSpentLiving()));
        stats.setMagicDamageDealt((int)item.getMagicDamageDealt());
        stats.setMagicDamageDealtToChampions((int)item.getMagicDamageDealtToChampions());
        stats.setMagicDamageTaken((int)item.getMagicalDamageTaken());
        stats.setNeutralMinionsKilled(item.getNeutralMinionsKilled());
        stats.setNeutralMinionsKilledInAllyJungle(item.getNeutralMinionsKilledTeamJungle());
        stats.setNeutralMinionsKilledInEnemyJungle(item.getNeutralMinionsKilledEnemyJungle());
        stats.setNodeCaptureAssists(item.getNodeCaptureAssist());
        stats.setNodeNeutralizeAssists(item.getNodeNeutralizeAssist());
        stats.setNodesCaptured(item.getNodeCapture());
        stats.setNodesNeutralized(item.getNodeNeutralize());
        stats.setObjectiveScore(item.getObjectivePlayerScore());
        stats.setPhysicalDamageDealt((int)item.getPhysicalDamageDealt());
        stats.setPhysicalDamageDealtToChampions((int)item.getPhysicalDamageDealtToChampions());
        stats.setPhysicalDamageTaken((int)item.getPhysicalDamageTaken());
        stats.setPinkWardsPurchased(item.getVisionWardsBoughtInGame());
        stats.setQuadraKills(item.getQuadraKills());
        stats.setScore(item.getTotalPlayerScore());
        stats.setScoreRank(item.getTotalScoreRank());
        stats.setTeamObjectives(item.getTeamObjective());
        stats.setTripleKills(item.getTripleKills());
        stats.setTrueDamageDealt((int)item.getTrueDamageDealt());
        stats.setTrueDamageDealtToChampions((int)item.getTrueDamageDealtToChampions());
        stats.setTrueDamageTaken((int)item.getTrueDamageTaken());
        stats.setTurretKills(item.getTurretKills());
        stats.setUnitsHealed(item.getTotalUnitsHealed());
        stats.setVisionScore((int)item.getVisionScore());
        stats.setWardsKilled(item.getWardsKilled());
        stats.setWardsPlaced(item.getWardsPlaced());
        stats.setWinner(item.isWin());
        return stats;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.ParticipantTimeline.class, to = ParticipantTimeline.class)
    public ParticipantTimeline transform(final com.merakianalytics.orianna.types.dto.match.ParticipantTimeline item, final PipelineContext context) {
        final ParticipantTimeline timeline = new ParticipantTimeline();
        final long durationInSeconds = (Long)context.get("duration");
        if(item.getCreepsPerMinDeltas() != null) {
            timeline.setCreepScore(getStatTotals(item.getCreepsPerMinDeltas(), durationInSeconds));
        }
        if(item.getCsDiffPerMinDeltas() != null) {
            timeline.setCreepScoreDifference(getStatTotals(item.getCsDiffPerMinDeltas(), durationInSeconds));
        }
        if(item.getDamageTakenPerMinDeltas() != null) {
            timeline.setDamageTaken(getStatTotals(item.getDamageTakenPerMinDeltas(), durationInSeconds));
        }
        if(item.getDamageTakenDiffPerMinDeltas() != null) {
            timeline.setDamageTakenDifference(getStatTotals(item.getDamageTakenDiffPerMinDeltas(), durationInSeconds));
        }
        if(item.getXpPerMinDeltas() != null) {
            timeline.setExperience(getStatTotals(item.getXpPerMinDeltas(), durationInSeconds));
        }
        if(item.getXpDiffPerMinDeltas() != null) {
            timeline.setExperienceDifference(getStatTotals(item.getXpDiffPerMinDeltas(), durationInSeconds));
        }
        if(item.getGoldPerMinDeltas() != null) {
            timeline.setGold(getStatTotals(item.getGoldPerMinDeltas(), durationInSeconds));
        }
        return timeline;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.Player.class, to = Participant.class)
    public Participant transform(final com.merakianalytics.orianna.types.dto.match.Player item, final PipelineContext context) {
        final Participant player = new Participant();
        player.setVersion((String)context.get("version"));
        player.setAccountId(item.getAccountId());
        player.setCurrentAccountId(item.getCurrentAccountId());
        player.setCurrentPlatform(item.getCurrentPlatformId());
        player.setMatchHistoryURI(item.getMatchHistoryUri());
        player.setPlatform(item.getPlatformId());
        player.setProfileIconId(item.getProfileIcon());
        player.setSummonerId(item.getSummonerId());
        player.setSummonerName(item.getSummonerName());
        final com.merakianalytics.orianna.types.dto.match.Participant participant =
            (com.merakianalytics.orianna.types.dto.match.Participant)context.get("participant");
        player.setParticipantId(participant.getParticipantId());
        player.setChampionId(participant.getChampionId());
        player.setHighestTierInSeason(participant.getHighestAchievedSeasonTier());
        player.setStats(transform(participant.getStats(), context));
        player.setSummonerSpellDId(participant.getSpell1Id());
        player.setSummonerSpellFId(participant.getSpell2Id());
        player.setTeam(participant.getTeamId());
        player.setLane(participant.getTimeline().getLane());
        player.setRole(participant.getTimeline().getRole());
        player.setTimeline(transform(participant.getTimeline(), context));
        player.setPrimaryRunePath(participant.getStats().getPerkPrimaryStyle());
        player.setSecondaryRunePath(participant.getStats().getPerkSubStyle());
        final List<Integer> items = new ArrayList<>(7);
        items.add(participant.getStats().getItem0());
        items.add(participant.getStats().getItem1());
        items.add(participant.getStats().getItem2());
        items.add(participant.getStats().getItem3());
        items.add(participant.getStats().getItem4());
        items.add(participant.getStats().getItem5());
        items.add(participant.getStats().getItem6());
        player.setItems(items);
        final List<RuneStats> runes = new ArrayList<>(6);
        RuneStats rune = new RuneStats();
        rune.setId(participant.getStats().getPerk0());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(participant.getStats().getPerk0Var1());
        rune.getVariables().add(participant.getStats().getPerk0Var2());
        rune.getVariables().add(participant.getStats().getPerk0Var3());
        runes.add(rune);
        rune = new RuneStats();
        rune.setId(participant.getStats().getPerk1());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(participant.getStats().getPerk1Var1());
        rune.getVariables().add(participant.getStats().getPerk1Var2());
        rune.getVariables().add(participant.getStats().getPerk1Var3());
        runes.add(rune);
        rune = new RuneStats();
        rune.setId(participant.getStats().getPerk2());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(participant.getStats().getPerk2Var1());
        rune.getVariables().add(participant.getStats().getPerk2Var2());
        rune.getVariables().add(participant.getStats().getPerk2Var3());
        runes.add(rune);
        rune = new RuneStats();
        rune.setId(participant.getStats().getPerk3());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(participant.getStats().getPerk3Var1());
        rune.getVariables().add(participant.getStats().getPerk3Var2());
        rune.getVariables().add(participant.getStats().getPerk3Var3());
        runes.add(rune);
        rune = new RuneStats();
        rune.setId(participant.getStats().getPerk4());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(participant.getStats().getPerk4Var1());
        rune.getVariables().add(participant.getStats().getPerk4Var2());
        rune.getVariables().add(participant.getStats().getPerk4Var3());
        runes.add(rune);
        rune = new RuneStats();
        rune.setId(participant.getStats().getPerk5());
        rune.setVariables(new ArrayList<Integer>(3));
        rune.getVariables().add(participant.getStats().getPerk5Var1());
        rune.getVariables().add(participant.getStats().getPerk5Var2());
        rune.getVariables().add(participant.getStats().getPerk5Var3());
        runes.add(rune);
        player.setRuneStats(runes);
        return player;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.TournamentMatches.class, to = TournamentMatches.class)
    public TournamentMatches transform(final com.merakianalytics.orianna.types.dto.match.TournamentMatches item, final PipelineContext context) {
        final TournamentMatches matches = new TournamentMatches(item.size());
        matches.addAll(item);
        matches.setPlatform(item.getPlatform());
        matches.setTournamentCode(item.getTournamentCode());
        return matches;
    }

    @Transform(from = Event.class, to = MatchEvent.class)
    public MatchEvent transform(final Event item, final PipelineContext context) {
        final MatchEvent event = new MatchEvent();
        event.setAfterId(item.getAfterId());
        if(item.getAscensionType() != null) {
            event.setAscendedType(item.getAscensionType().toString());
        }
        if(item.getAssistingParticipants() != null) {
            event.setAssistingParticipantIds(new ArrayList<>(item.getAssistingParticipants()));
        }
        event.setBeforeId(item.getBeforeId());
        if(item.getBuildingType() != null) {
            event.setBuildingType(item.getBuildingType().toString());
        }
        if(item.getCapturedPoint() != null) {
            event.setPointCaptured(item.getCapturedPoint().toString());
        }
        event.setCreatorId(item.getCreatorId());
        event.setItemId(item.getItemId());
        event.setKillerId(item.getKillerId());
        if(item.getLaneType() != null) {
            event.setLaneType(item.getLaneType().toString());
        }
        if(item.getLevelUpType() != null) {
            event.setLevelUpType(item.getLevelUpType().toString());
        }
        if(item.getMonsterSubType() != null) {
            event.setMonsterSubType(item.getMonsterSubType().toString());
        }
        event.setParticipantId(item.getParticipantId());
        if(item.getPosition() != null) {
            event.setPosition(transform(item.getPosition(), context));
        }
        event.setSkillSlot(item.getSkill());
        event.setTeamId(item.getTeam());
        event.setTimestamp(item.getTimestamp().getMillis());
        if(item.getTurretType() != null) {
            event.setTowerType(item.getTurretType().toString());
        }
        if(item.getType() != null) {
            event.setType(item.getType().toString());
        }
        event.setVictimId(item.getVictimId());
        if(item.getWardType() != null) {
            event.setWardType(item.getWardType().toString());
        }
        return event;
    }

    @Transform(from = Frame.class, to = MatchFrame.class)
    public MatchFrame transform(final Frame item, final PipelineContext context) {
        final MatchFrame frame = new MatchFrame();
        final List<MatchEvent> events = new ArrayList<>(item.size());
        for(final Event event : item) {
            events.add(transform(event, context));
        }
        frame.setEvents(events);
        final Map<String, MatchParticipantFrame> participantFrames = new HashMap<>();
        final Object previous = context.get("participantId");
        for(final Integer participantId : item.getParticipantFrames().keySet()) {
            context.put("participantId", participantId);
            participantFrames.put(Integer.toString(participantId), transform(item.getParticipantFrames().get(participantId), context));
        }
        context.put("participantId", previous);
        frame.setParticipantFrames(participantFrames);
        return frame;
    }

    @Transform(from = Match.class, to = com.merakianalytics.orianna.types.dto.match.Match.class)
    public com.merakianalytics.orianna.types.dto.match.Match transform(final Match item, final PipelineContext context) {
        final Object previousDuration = context.put("duration", item.getDuration());
        final com.merakianalytics.orianna.types.dto.match.Match match = new com.merakianalytics.orianna.types.dto.match.Match();
        match.setGameCreation(item.getCreationTime().getMillis());
        match.setGameDuration(item.getDuration().getStandardSeconds());
        match.setGameId(item.getId());
        match.setGameMode(item.getMode().toString());
        match.setGameType(item.getType().toString());
        match.setGameVersion(item.getVersion());
        match.setMapId(item.getMap());
        final List<ParticipantIdentity> identities = new ArrayList<>(item.getParticipants().size());
        final List<com.merakianalytics.orianna.types.dto.match.Participant> participants = new ArrayList<>(item.getParticipants().size());
        final int id = 1;
        final Object previousParticipantId = context.put("participantId", id);
        for(final Participant player : item.getParticipants()) {
            context.put("participantId", id);
            final ParticipantIdentity identity = new ParticipantIdentity();
            identity.setParticipantId(id);
            identity.setPlayer(transform(player, context));
            identities.add(identity);
            participants.add(transformToParticipant(player, context));
        }
        context.put("participantId", previousParticipantId);
        match.setParticipantIdentities(identities);
        match.setParticipants(participants);
        match.setPlatformId(item.getPlatform());
        match.setQueueId(item.getQueue());
        match.setSeasonId(item.getSeason());
        final List<TeamStats> teams = new ArrayList<>(2);
        teams.add(transform(item.getBlueTeam(), context));
        teams.add(transform(item.getRedTeam(), context));
        match.setTeams(teams);
        match.setTournamentCode(item.getTournamentCode());
        context.put("duration", previousDuration);
        return match;
    }

    @Transform(from = MatchEvent.class, to = Event.class)
    public Event transform(final MatchEvent item, final PipelineContext context) {
        final Event event = new Event();
        event.setAfterId(item.getAfterId());
        if(item.getAscendedType() != null) {
            event.setAscensionType(item.getAscendedType());
        }
        if(item.getAssistingParticipantIds() != null) {
            event.setAssistingParticipants(new ArrayList<>(item.getAssistingParticipantIds()));
        }
        event.setBeforeId(item.getBeforeId());
        if(item.getBuildingType() != null) {
            event.setBuildingType(item.getBuildingType());
        }
        if(item.getPointCaptured() != null) {
            event.setCapturedPoint(item.getPointCaptured());
        }
        event.setCreatorId(item.getCreatorId());
        event.setItemId(item.getItemId());
        event.setKillerId(item.getKillerId());
        if(item.getLaneType() != null) {
            event.setLaneType(item.getLaneType());
        }
        if(item.getLevelUpType() != null) {
            event.setLevelUpType(item.getLevelUpType());
        }
        if(item.getMonsterSubType() != null) {
            event.setMonsterSubType(item.getMonsterSubType());
        }
        event.setParticipantId(item.getParticipantId());
        if(item.getPosition() != null) {
            event.setPosition(transform(item.getPosition(), context));
        }
        if(item.getSkillSlot() != 0) {
            event.setSkill(item.getSkillSlot());
        }
        if(item.getTeamId() != 0) {
            event.setTeam(item.getTeamId());
        }
        event.setTimestamp(Duration.millis(item.getTimestamp()));
        if(item.getTowerType() != null) {
            event.setTurretType(item.getTowerType());
        }
        if(item.getType() != null) {
            event.setType(item.getType());
        }
        event.setVictimId(item.getVictimId());
        if(item.getWardType() != null) {
            event.setWardType(item.getWardType());
        }
        return event;
    }

    @Transform(from = MatchFrame.class, to = Frame.class)
    public Frame transform(final MatchFrame item, final PipelineContext context) {
        final Frame frame = new Frame(item.getEvents().size());
        for(final MatchEvent event : item.getEvents()) {
            frame.add(transform(event, context));
        }
        final Map<Integer, ParticipantFrame> participantFrames = new HashMap<>();
        for(final String participantId : item.getParticipantFrames().keySet()) {
            participantFrames.put(Integer.parseInt(participantId), transform(item.getParticipantFrames().get(participantId), context));
        }
        frame.setParticipantFrames(participantFrames);
        frame.setTimestamp(Duration.millis(item.getTimestamp()));
        return frame;
    }

    @Transform(from = Matchlist.class, to = MatchList.class)
    public MatchList transform(final Matchlist item, final PipelineContext context) {
        final MatchList list = new MatchList(item.getMatches().size());
        for(final com.merakianalytics.orianna.types.dto.match.MatchReference match : item.getMatches()) {
            list.add(transform(match, context));
        }
        list.setAccountId(item.getAccountId());
        list.setChampions(new HashSet<>(item.getChampions()));
        list.setEndIndex(item.getEndIndex());
        list.setEndTime(new DateTime(item.getEndTime()));
        list.setPlatform(item.getPlatform());
        list.setQueues(new HashSet<>(item.getQueues()));
        list.setRecent(item.isRecent());
        list.setSeasons(new HashSet<>(item.getSeasons()));
        list.setStartIndex(item.getStartIndex());
        list.setStartTime(new DateTime(item.getStartTime()));
        return list;
    }

    @Transform(from = MatchList.class, to = Matchlist.class)
    public Matchlist transform(final MatchList item, final PipelineContext context) {
        final Matchlist list = new Matchlist();
        final List<com.merakianalytics.orianna.types.dto.match.MatchReference> matches = new ArrayList<>(item.size());
        for(final MatchReference match : item) {
            matches.add(transform(match, context));
        }
        list.setAccountId(item.getAccountId());
        list.setChampions(new HashSet<>(item.getChampions()));
        list.setEndIndex(item.getEndIndex());
        list.setEndTime(item.getEndTime().getMillis());
        list.setPlatform(item.getPlatform());
        list.setQueues(new HashSet<>(item.getQueues()));
        list.setRecent(item.isRecent());
        list.setSeasons(new HashSet<>(item.getSeasons()));
        list.setStartIndex(item.getStartIndex());
        list.setStartTime(item.getStartTime().getMillis());
        return list;
    }

    @Transform(from = MatchParticipantFrame.class, to = ParticipantFrame.class)
    public ParticipantFrame transform(final MatchParticipantFrame item, final PipelineContext context) {
        final ParticipantFrame frame = new ParticipantFrame();
        frame.setCreepScore(item.getMinionsKilled());
        frame.setDominionScore(item.getDominionScore());
        frame.setExperience(item.getXp());
        frame.setGold(item.getCurrentGold());
        frame.setGoldEarned(item.getTotalGold());
        frame.setLevel(item.getLevel());
        frame.setNeutralMinionsKilled(item.getJungleMinionsKilled());
        if(item.getPosition() != null) {
            frame.setPosition(transform(item.getPosition(), context));
        }
        frame.setTeamScore(item.getTeamScore());
        return frame;
    }

    @Transform(from = MatchPosition.class, to = Position.class)
    public Position transform(final MatchPosition item, final PipelineContext context) {
        final Position position = new Position();
        position.setX(item.getX());
        position.setY(item.getY());
        return position;
    }

    @Transform(from = MatchReference.class, to = com.merakianalytics.orianna.types.dto.match.MatchReference.class)
    public com.merakianalytics.orianna.types.dto.match.MatchReference transform(final MatchReference item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.match.MatchReference reference = new com.merakianalytics.orianna.types.dto.match.MatchReference();
        reference.setAccountId(item.getAccountId());
        reference.setChampion(item.getChampionId());
        reference.setTimestamp(item.getCreationTime().getMillis());
        reference.setGameId(item.getId());
        reference.setLane(item.getLane().toString());
        reference.setPlatformId(item.getPlatform());
        reference.setQueue(item.getQueue());
        reference.setRole(item.getRole().toString());
        reference.setSeason(item.getSeason());
        return reference;
    }

    @Transform(from = MatchTimeline.class, to = Timeline.class)
    public Timeline transform(final MatchTimeline item, final PipelineContext context) {
        final Timeline timeline = new Timeline(item.getFrames().size());
        for(final MatchFrame frame : item.getFrames()) {
            timeline.add(transform(frame, context));
        }
        timeline.setId(item.getMatchId());
        timeline.setInterval(Duration.millis(item.getFrameInterval()));
        timeline.setPlatform(item.getPlatform());
        return timeline;
    }

    @Transform(from = Participant.class, to = com.merakianalytics.orianna.types.dto.match.Player.class)
    public com.merakianalytics.orianna.types.dto.match.Player transform(final Participant item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.match.Player player = new com.merakianalytics.orianna.types.dto.match.Player();
        player.setAccountId(item.getAccountId());
        player.setCurrentAccountId(item.getCurrentAccountId());
        player.setCurrentPlatformId(item.getCurrentPlatform());
        player.setMatchHistoryUri(item.getMatchHistoryURI());
        player.setPlatformId(item.getPlatform());
        player.setProfileIcon(item.getProfileIconId());
        player.setSummonerId(item.getSummonerId());
        player.setSummonerName(item.getSummonerName());
        return player;
    }

    @Transform(from = ParticipantFrame.class, to = MatchParticipantFrame.class)
    public MatchParticipantFrame transform(final ParticipantFrame item, final PipelineContext context) {
        final MatchParticipantFrame frame = new MatchParticipantFrame();
        frame.setParticipantId((Integer)context.get("participantId"));
        frame.setMinionsKilled(item.getCreepScore());
        frame.setDominionScore(item.getDominionScore());
        frame.setXp(item.getExperience());
        frame.setCurrentGold(item.getGold());
        frame.setTotalGold(item.getGoldEarned());
        frame.setLevel(item.getLevel());
        frame.setJungleMinionsKilled(item.getNeutralMinionsKilled());
        if(item.getPosition() != null) {
            frame.setPosition(transform(item.getPosition(), context));
        }
        frame.setTeamScore(item.getTeamScore());
        return frame;
    }

    @SuppressWarnings("unchecked")
    @Transform(from = ParticipantStats.class, to = com.merakianalytics.orianna.types.dto.match.ParticipantStats.class)
    public com.merakianalytics.orianna.types.dto.match.ParticipantStats transform(final ParticipantStats item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.match.ParticipantStats stats = new com.merakianalytics.orianna.types.dto.match.ParticipantStats();
        stats.setAltarsCaptured(item.getAltarsCaptured());
        stats.setAltarsNeutralized(item.getAltarsNeutralized());
        stats.setAssists(item.getAssists());
        stats.setChampLevel(item.getChampionLevel());
        stats.setCombatPlayerScore(item.getCombatScore());
        stats.setTotalMinionsKilled(item.getCreepScore());
        stats.setTotalTimeCrowdControlDealt((int)item.getCrowdControlDealt().getStandardSeconds());
        stats.setTimeCCingOthers(item.getCrowdControlDealtToChampions().getStandardSeconds());
        stats.setTotalDamageDealt(item.getDamageDealt());
        stats.setTotalDamageDealtToChampions(item.getDamageDealtToChampions());
        stats.setTotalHeal(item.getDamageHealed());
        stats.setDamageSelfMitigated(item.getDamageMitigated());
        stats.setTotalDamageTaken(item.getDamageTaken());
        stats.setDamageDealtToObjectives(item.getDamageToObjectives());
        stats.setDamageDealtToTurrets(item.getDamageToTurrets());
        stats.setDeaths(item.getDeaths());
        stats.setDoubleKills(item.getDoubleKills());
        stats.setFirstBloodAssist(item.isFirstBloodAssistant());
        stats.setFirstBloodKill(item.isFirstBloodKiller());
        stats.setFirstInhibitorAssist(item.isFirstInhibitorKillAssistant());
        stats.setFirstInhibitorKill(item.isFirstInhibitorKiller());
        stats.setFirstTowerAssist(item.isFirstTowerKillAssistant());
        stats.setFirstTowerKill(item.isFirstTowerKiller());
        stats.setGoldEarned(item.getGoldEarned());
        stats.setGoldSpent(item.getGoldSpent());
        stats.setSightWardsBoughtInGame(item.getGreenWardsPurchased());
        stats.setUnrealKills(item.getHexaKills());
        stats.setInhibitorKills(item.getInhibitorKills());
        stats.setPlayerScore0(item.getPlayerScore0());
        stats.setPlayerScore1(item.getPlayerScore1());
        stats.setPlayerScore2(item.getPlayerScore2());
        stats.setPlayerScore3(item.getPlayerScore3());
        stats.setPlayerScore4(item.getPlayerScore4());
        stats.setPlayerScore5(item.getPlayerScore5());
        stats.setPlayerScore6(item.getPlayerScore6());
        stats.setPlayerScore7(item.getPlayerScore7());
        stats.setPlayerScore8(item.getPlayerScore8());
        stats.setPlayerScore9(item.getPlayerScore9());
        final List<Integer> items = (List<Integer>)context.get("items");
        stats.setItem0(items.get(0));
        stats.setItem1(items.get(1));
        stats.setItem2(items.get(2));
        stats.setItem3(items.get(3));
        stats.setItem4(items.get(4));
        stats.setItem5(items.get(5));
        stats.setItem6(items.get(6));
        stats.setPerkPrimaryStyle((Integer)context.get("primaryRunePath"));
        stats.setPerkSubStyle((Integer)context.get("secondaryRunePath"));
        final List<RuneStats> runeStats = (List<RuneStats>)context.get("runeStats");
        RuneStats rune = runeStats.get(0);
        stats.setPerk0(rune.getId());
        stats.setPerk0Var1(rune.getVariables().get(0));
        stats.setPerk0Var2(rune.getVariables().get(1));
        stats.setPerk0Var3(rune.getVariables().get(2));
        rune = runeStats.get(1);
        stats.setPerk1(rune.getId());
        stats.setPerk1Var1(rune.getVariables().get(0));
        stats.setPerk1Var2(rune.getVariables().get(1));
        stats.setPerk1Var3(rune.getVariables().get(2));
        rune = runeStats.get(2);
        stats.setPerk2(rune.getId());
        stats.setPerk2Var1(rune.getVariables().get(0));
        stats.setPerk2Var2(rune.getVariables().get(1));
        stats.setPerk2Var3(rune.getVariables().get(2));
        rune = runeStats.get(3);
        stats.setPerk3(rune.getId());
        stats.setPerk3Var1(rune.getVariables().get(0));
        stats.setPerk3Var2(rune.getVariables().get(1));
        stats.setPerk3Var3(rune.getVariables().get(2));
        rune = runeStats.get(4);
        stats.setPerk4(rune.getId());
        stats.setPerk4Var1(rune.getVariables().get(0));
        stats.setPerk4Var2(rune.getVariables().get(1));
        stats.setPerk4Var3(rune.getVariables().get(2));
        rune = runeStats.get(5);
        stats.setPerk5(rune.getId());
        stats.setPerk5Var1(rune.getVariables().get(0));
        stats.setPerk5Var2(rune.getVariables().get(1));
        stats.setPerk5Var3(rune.getVariables().get(2));
        stats.setKillingSprees(item.getKillingSprees());
        stats.setKills(item.getKills());
        stats.setLargestCriticalStrike(item.getLargestCriticalStrike());
        stats.setLargestKillingSpree(item.getLargestKillingSpree());
        stats.setLargestMultiKill(item.getLargestMultiKill());
        stats.setLongestTimeSpentLiving((int)item.getLongestTimeAlive().getStandardSeconds());
        stats.setMagicDamageDealt(item.getMagicDamageDealt());
        stats.setMagicDamageDealtToChampions(item.getMagicDamageDealtToChampions());
        stats.setMagicalDamageTaken(item.getMagicDamageTaken());
        stats.setNeutralMinionsKilled(item.getNeutralMinionsKilled());
        stats.setNeutralMinionsKilledTeamJungle(item.getNeutralMinionsKilledInAllyJungle());
        stats.setNeutralMinionsKilledEnemyJungle(item.getNeutralMinionsKilledInEnemyJungle());
        stats.setNodeCaptureAssist(item.getNodeCaptureAssists());
        stats.setNodeNeutralizeAssist(item.getNodeNeutralizeAssists());
        stats.setNodeCapture(item.getNodesCaptured());
        stats.setNodeNeutralize(item.getNodesNeutralized());
        stats.setObjectivePlayerScore(item.getObjectiveScore());
        stats.setParticipantId((Integer)context.get("participantId"));
        stats.setPhysicalDamageDealt(item.getPhysicalDamageDealt());
        stats.setPhysicalDamageDealtToChampions(item.getPhysicalDamageDealtToChampions());
        stats.setPhysicalDamageTaken(item.getPhysicalDamageTaken());
        stats.setVisionWardsBoughtInGame(item.getPinkWardsPurchased());
        stats.setQuadraKills(item.getQuadraKills());
        stats.setTotalPlayerScore(item.getScore());
        stats.setTotalScoreRank(item.getScoreRank());
        stats.setTeamObjective(item.getTeamObjectives());
        stats.setTripleKills(item.getTripleKills());
        stats.setTrueDamageDealt(item.getTrueDamageDealt());
        stats.setTrueDamageDealtToChampions(item.getTrueDamageDealtToChampions());
        stats.setTrueDamageTaken(item.getTrueDamageTaken());
        stats.setTurretKills(item.getTurretKills());
        stats.setTotalUnitsHealed(item.getUnitsHealed());
        stats.setVisionScore(item.getVisionScore());
        stats.setWardsKilled(item.getWardsKilled());
        stats.setWardsPlaced(item.getWardsPlaced());
        stats.setWin(item.isWinner());
        return stats;
    }

    @Transform(from = ParticipantTimeline.class, to = com.merakianalytics.orianna.types.dto.match.ParticipantTimeline.class)
    public com.merakianalytics.orianna.types.dto.match.ParticipantTimeline transform(final ParticipantTimeline item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.match.ParticipantTimeline timeline = new com.merakianalytics.orianna.types.dto.match.ParticipantTimeline();
        final Duration duration = (Duration)context.get("duration");
        if(item.getCreepScore() != null) {
            timeline.setCreepsPerMinDeltas(getPerMinDeltas(item.getCreepScore(), duration));
        }
        if(item.getCreepScoreDifference() != null) {
            timeline.setCsDiffPerMinDeltas(getPerMinDeltas(item.getCreepScoreDifference(), duration));
        }
        if(item.getDamageTakenDifference() != null) {
            timeline.setDamageTakenDiffPerMinDeltas(getPerMinDeltas(item.getDamageTakenDifference(), duration));
        }
        if(item.getDamageTaken() != null) {
            timeline.setDamageTakenPerMinDeltas(getPerMinDeltas(item.getDamageTaken(), duration));
        }
        if(item.getGold() != null) {
            timeline.setGoldPerMinDeltas(getPerMinDeltas(item.getGold(), duration));
        }
        timeline.setLane((String)context.get("lane"));
        timeline.setParticipantId((Integer)context.get("participantId"));
        timeline.setRole((String)context.get("role"));
        if(item.getExperienceDifference() != null) {
            timeline.setXpDiffPerMinDeltas(getPerMinDeltas(item.getExperienceDifference(), duration));
        }
        if(item.getExperience() != null) {
            timeline.setXpPerMinDeltas(getPerMinDeltas(item.getExperience(), duration));
        }
        return timeline;
    }

    @Transform(from = Position.class, to = MatchPosition.class)
    public MatchPosition transform(final Position item, final PipelineContext context) {
        final MatchPosition position = new MatchPosition();
        position.setX(item.getX());
        position.setY(item.getY());
        return position;
    }

    @Transform(from = Team.class, to = TeamStats.class)
    public TeamStats transform(final Team item, final PipelineContext context) {
        final TeamStats team = new TeamStats();
        final List<TeamBans> bans = new ArrayList<>(item.getBans().size());
        final boolean isTenBanMode = item.getBans().size() == 5;
        int turn = com.merakianalytics.orianna.types.common.Side.BLUE.getId() == item.getTeamId() ? 1 : isTenBanMode ? 6 : 2;
        final int increment = isTenBanMode ? 1 : 2;
        for(final Integer id : item.getBans()) {
            final TeamBans ban = new TeamBans();
            ban.setChampionId(id);
            ban.setPickTurn(turn);
            bans.add(ban);
            turn += increment;
        }
        team.setBans(bans);
        team.setBaronKills(item.getBaronKills());
        team.setDominionVictoryScore(item.getDominionScore());
        team.setDragonKills(item.getDragonKills());
        team.setFirstBaron(item.isFirstBaronKiller());
        team.setFirstBlood(item.isFirstBloodKiller());
        team.setFirstDragon(item.isFirstDragonKiller());
        team.setFirstInhibitor(item.isFirstInhibitorKiller());
        team.setFirstRiftHerald(item.isFirstRiftHeraldKiller());
        team.setFirstTower(item.isFirstTowerKiller());
        team.setInhibitorKills(item.getInhibitorKills());
        team.setRiftHeraldKills(item.getRiftHeraldKills());
        team.setTeamId(item.getTeamId());
        team.setTowerKills(item.getTowerKills());
        team.setVilemawKills(item.getVilemawKills());
        team.setWin(item.isWinner() ? "Win" : "Fail");
        return team;
    }

    @Transform(from = TeamStats.class, to = Team.class)
    public Team transform(final TeamStats item, final PipelineContext context) {
        final Team team = new Team();
        team.setPlatform((String)context.get("platform"));
        team.setVersion((String)context.get("version"));
        final List<Integer> bans = new ArrayList<>(item.getBans().size());
        for(final TeamBans ban : item.getBans()) {
            bans.add(ban.getChampionId());
        }
        team.setTeamId(item.getTeamId());
        team.setBans(bans);
        team.setBaronKills(item.getBaronKills());
        team.setDominionScore(item.getDominionVictoryScore());
        team.setDragonKills(item.getDragonKills());
        team.setFirstBaronKiller(item.isFirstBaron());
        team.setFirstBloodKiller(item.isFirstBlood());
        team.setFirstDragonKiller(item.isFirstDragon());
        team.setFirstInhibitorKiller(item.isFirstInhibitor());
        team.setFirstRiftHeraldKiller(item.isFirstRiftHerald());
        team.setFirstTowerKiller(item.isFirstTower());
        team.setInhibitorKills(item.getInhibitorKills());
        team.setRiftHeraldKills(item.getRiftHeraldKills());
        team.setTowerKills(item.getTowerKills());
        team.setVilemawKills(item.getVilemawKills());
        team.setWinner("Win".equals(item.getWin()));
        return team;
    }

    @Transform(from = Timeline.class, to = MatchTimeline.class)
    public MatchTimeline transform(final Timeline item, final PipelineContext context) {
        final MatchTimeline timeline = new MatchTimeline();
        final List<MatchFrame> frames = new ArrayList<>(item.size());
        for(final Frame frame : item) {
            frames.add(transform(frame, context));
        }
        timeline.setFrames(frames);
        timeline.setMatchId(item.getId());
        timeline.setFrameInterval(item.getInterval().getMillis());
        timeline.setPlatform(item.getPlatform());
        return timeline;
    }

    @Transform(from = TournamentMatches.class, to = com.merakianalytics.orianna.types.dto.match.TournamentMatches.class)
    public com.merakianalytics.orianna.types.dto.match.TournamentMatches transform(final TournamentMatches item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.match.TournamentMatches matches =
            new com.merakianalytics.orianna.types.dto.match.TournamentMatches(item.size());
        matches.addAll(item);
        matches.setPlatform(item.getPlatform());
        matches.setTournamentCode(item.getTournamentCode());
        return matches;
    }

    @Transform(from = Participant.class, to = com.merakianalytics.orianna.types.dto.match.Participant.class)
    public com.merakianalytics.orianna.types.dto.match.Participant transformToParticipant(final Participant item, final PipelineContext context) {
        final Object previousParticipant = context.put("participantId", item.getParticipantId());
        final Object previousLane = context.put("lane", item.getLane());
        final Object previousRole = context.put("role", item.getRole());
        final Object previousItems = context.put("items", item.getItems());
        final Object previousRuneStats = context.put("runeStats", item.getRuneStats());
        final Object previousPrimaryRunePath = context.put("primaryRunePath", item.getPrimaryRunePath());
        final Object previousSecondaryRunePath = context.put("secondaryRunePath", item.getSecondaryRunePath());
        final com.merakianalytics.orianna.types.dto.match.Participant participant = new com.merakianalytics.orianna.types.dto.match.Participant();
        participant.setChampionId(item.getChampionId());
        participant.setHighestAchievedSeasonTier(item.getHighestTierInSeason().toString());
        participant.setParticipantId(item.getParticipantId());
        participant.setSpell1Id(item.getSummonerSpellDId());
        participant.setSpell2Id(item.getSummonerSpellFId());
        participant.setStats(transform(item.getStats(), context));
        participant.setTeamId(item.getTeam());
        participant.setTimeline(transform(item.getTimeline(), context));
        context.put("participantId", previousParticipant);
        context.put("lane", previousLane);
        context.put("role", previousRole);
        context.put("items", previousItems);
        context.put("runeStats", previousRuneStats);
        context.put("primaryRunePath", previousPrimaryRunePath);
        context.put("secondaryRunePath", previousSecondaryRunePath);
        return participant;
    }
}
