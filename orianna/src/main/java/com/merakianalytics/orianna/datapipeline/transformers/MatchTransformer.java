package com.merakianalytics.orianna.datapipeline.transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Minutes;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.common.AscensionType;
import com.merakianalytics.orianna.types.common.BuildingType;
import com.merakianalytics.orianna.types.common.EventType;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.GameType;
import com.merakianalytics.orianna.types.common.Lane;
import com.merakianalytics.orianna.types.common.LaneType;
import com.merakianalytics.orianna.types.common.LevelUpType;
import com.merakianalytics.orianna.types.common.MonsterSubType;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Point;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Role;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.common.Skill;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.common.TurretType;
import com.merakianalytics.orianna.types.common.WardType;
import com.merakianalytics.orianna.types.data.match.Event;
import com.merakianalytics.orianna.types.data.match.Frame;
import com.merakianalytics.orianna.types.data.match.Match;
import com.merakianalytics.orianna.types.data.match.MatchList;
import com.merakianalytics.orianna.types.data.match.MatchReference;
import com.merakianalytics.orianna.types.data.match.ParticipantFrame;
import com.merakianalytics.orianna.types.data.match.ParticipantStats;
import com.merakianalytics.orianna.types.data.match.ParticipantTimeline;
import com.merakianalytics.orianna.types.data.match.Player;
import com.merakianalytics.orianna.types.data.match.Position;
import com.merakianalytics.orianna.types.data.match.StatTotals;
import com.merakianalytics.orianna.types.data.match.Team;
import com.merakianalytics.orianna.types.data.match.Timeline;
import com.merakianalytics.orianna.types.data.match.TournamentMatches;
import com.merakianalytics.orianna.types.dto.match.Mastery;
import com.merakianalytics.orianna.types.dto.match.MatchEvent;
import com.merakianalytics.orianna.types.dto.match.MatchFrame;
import com.merakianalytics.orianna.types.dto.match.MatchParticipantFrame;
import com.merakianalytics.orianna.types.dto.match.MatchPosition;
import com.merakianalytics.orianna.types.dto.match.MatchTimeline;
import com.merakianalytics.orianna.types.dto.match.Matchlist;
import com.merakianalytics.orianna.types.dto.match.Participant;
import com.merakianalytics.orianna.types.dto.match.ParticipantIdentity;
import com.merakianalytics.orianna.types.dto.match.Rune;
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
            totals.getAt10() + Math.min(10.0, Math.max(minutes - 10.0, 0.0)) * (perMinDeltas.get("10-20") == null ? 0.0 : perMinDeltas.get("20-30")));
        totals.setAt30(
            totals.getAt20() + Math.min(10.0, Math.max(minutes - 20.0, 0.0)) * (perMinDeltas.get("20-30") == null ? 0.0 : perMinDeltas.get("20-30")));
        totals.setAtGameEnd(
            totals.getAt30() + Math.min(10.0, Math.max(minutes - 30.0, 0.0)) * (perMinDeltas.get("30-end") == null ? 0.0 : perMinDeltas.get("30-end")));
        return totals;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.Match.class, to = Match.class)
    public Match transform(final com.merakianalytics.orianna.types.dto.match.Match item, final PipelineContext context) {
        final Object previousDuration = context.put("duration", item.getGameDuration());
        final Match match = new Match();
        for(final TeamStats team : item.getTeams()) {
            if(com.merakianalytics.orianna.types.common.Team.BLUE == com.merakianalytics.orianna.types.common.Team.withId(team.getTeamId())) {
                match.setBlueTeam(transform(team, context));
            } else {
                match.setRedTeam(transform(team, context));
            }
        }
        match.setCreationTime(new DateTime(item.getGameCreation()));
        match.setDuration(Duration.standardSeconds(item.getGameDuration()));
        match.setForAccountId(item.getForAccountId());
        match.setId(item.getGameId());
        match.setMap(com.merakianalytics.orianna.types.common.Map.withId(item.getMapId()));
        match.setMode(GameMode.valueOf(item.getGameMode()));
        match.setPlatform(Platform.withTag(item.getPlatformId()));

        final List<Player> players = new ArrayList<>(item.getParticipantIdentities().size());
        final Map<Integer, Participant> byId = new HashMap<>();
        for(final Participant participant : item.getParticipants()) {
            byId.put(participant.getParticipantId(), participant);
        }
        final Object previousPlayer = context.get("player");
        for(int i = 0; i < item.getParticipantIdentities().size(); i++) {
            final int participantId = item.getParticipantIdentities().get(i).getParticipantId();
            final com.merakianalytics.orianna.types.dto.match.Player player = item.getParticipantIdentities().get(i).getPlayer();
            final Participant participant = byId.get(participantId);
            context.put("player", player);
            players.add(transform(participant, context));
        }
        context.put("player", previousPlayer);
        match.setPlayers(players);

        match.setQueue(Queue.withId(item.getQueueId()));
        match.setSeason(Season.withId(item.getSeasonId()));
        match.setTournamentCode(item.getTournamentCode());
        match.setType(GameType.valueOf(item.getGameType()));
        match.setVersion(item.getGameVersion());
        context.put("duration", previousDuration);
        return match;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.MatchReference.class, to = MatchReference.class)
    public MatchReference transform(final com.merakianalytics.orianna.types.dto.match.MatchReference item, final PipelineContext context) {
        final MatchReference reference = new MatchReference();
        reference.setChampionId(item.getChampion());
        reference.setCreationTime(new DateTime(item.getTimestamp()));
        reference.setId(item.getGameId());
        reference.setLane(Lane.valueOf(item.getLane()));
        reference.setPlatform(Platform.withTag(item.getPlatformId()));
        reference.setQueue(Queue.withId(item.getQueue()));
        reference.setRole(Role.valueOf(item.getRole()));
        reference.setSeason(Season.withId(item.getSeason()));
        return reference;
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
        final List<Integer> items = new ArrayList<>(7);
        items.add(item.getItem0());
        items.add(item.getItem1());
        items.add(item.getItem2());
        items.add(item.getItem3());
        items.add(item.getItem4());
        items.add(item.getItem5());
        items.add(item.getItem6());
        stats.setItems(items);
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
        timeline.setCreepScore(getStatTotals(item.getCreepsPerMinDeltas(), durationInSeconds));
        timeline.setCreepScoreDifference(getStatTotals(item.getCsDiffPerMinDeltas(), durationInSeconds));
        timeline.setDamageTaken(getStatTotals(item.getDamageTakenPerMinDeltas(), durationInSeconds));
        timeline.setDamageTakenDifference(getStatTotals(item.getDamageTakenDiffPerMinDeltas(), durationInSeconds));
        timeline.setExperience(getStatTotals(item.getXpPerMinDeltas(), durationInSeconds));
        timeline.setExperienceDifference(getStatTotals(item.getXpDiffPerMinDeltas(), durationInSeconds));
        timeline.setGold(getStatTotals(item.getGoldPerMinDeltas(), durationInSeconds));
        timeline.setLane(Lane.valueOf(item.getLane()));
        timeline.setRole(Role.valueOf(item.getRole()));
        return timeline;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.Player.class, to = Player.class)
    public Player transform(final com.merakianalytics.orianna.types.dto.match.Player item, final PipelineContext context) {
        final Player player = new Player();
        player.setAccountId(item.getAccountId());
        player.setCurrentAccountId(item.getCurrentAccountId());
        player.setCurrentPlatform(Platform.withTag(item.getCurrentPlatformId()));
        player.setMatchHistoryURI(item.getMatchHistoryUri());
        player.setPlatform(Platform.withTag(item.getPlatformId()));
        player.setProfileIconId(item.getProfileIcon());
        player.setSummonerId(item.getSummonerId());
        player.setSummonerName(item.getSummonerName());
        final Participant participant = (Participant)context.get("participant");
        player.setChampionId(participant.getChampionId());
        player.setHighestTierInSeason(Tier.valueOf(participant.getHighestAchievedSeasonTier()));
        Map<Integer, Integer> counts = new HashMap<>();
        for(final Mastery mastery : participant.getMasteries()) {
            counts.put(mastery.getMasteryId(), mastery.getRank());
        }
        player.setMasteries(counts);
        counts = new HashMap<>();
        for(final Rune mastery : participant.getRunes()) {
            counts.put(mastery.getRuneId(), mastery.getRank());
        }
        player.setRunes(counts);
        player.setStats(transform(participant.getStats(), context));
        player.setSummonerSpellDId(participant.getSpell1Id());
        player.setSummonerSpellFId(participant.getSpell2Id());
        player.setTeam(com.merakianalytics.orianna.types.common.Team.withId(participant.getTeamId()));
        player.setTimeline(transform(participant.getTimeline(), context));
        return player;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.match.TournamentMatches.class, to = TournamentMatches.class)
    public TournamentMatches transform(final com.merakianalytics.orianna.types.dto.match.TournamentMatches item, final PipelineContext context) {
        final TournamentMatches matches = new TournamentMatches(item.size());
        matches.addAll(item);
        matches.setPlatform(Platform.withTag(item.getPlatform()));
        matches.setTournamentCode(item.getTournamentCode());
        return matches;
    }

    @Transform(from = Event.class, to = MatchEvent.class)
    public MatchEvent transform(final Event item, final PipelineContext context) {
        final MatchEvent event = new MatchEvent();
        event.setAfterId(item.getAfterId());
        event.setAscendedType(item.getAscensionType().toString());
        event.setAssistingParticipantIds(new ArrayList<>(item.getAssistingParticipants()));
        event.setBeforeId(item.getBeforeId());
        event.setBuildingType(item.getBuildingType().toString());
        event.setPointCaptured(item.getCapturedPoint().toString());
        event.setCreatorId(item.getCreatorId());
        event.setEventType(null);
        event.setItemId(item.getItemId());
        event.setKillerId(item.getKillerId());
        event.setLaneType(item.getLaneType().toString());
        event.setLevelUpType(item.getLevelUpType().toString());
        event.setMonsterSubType(item.getMonsterSubType().toString());
        event.setParticipantId(item.getParticipantId());
        event.setPosition(transform(item.getPosition(), context));
        event.setSkillSlot(item.getSkill().getSlot());
        event.setTeamId(item.getTeam().getId());
        event.setTimestamp(item.getTimestamp().getMillis());
        event.setTowerType(item.getTurretType().toString());
        event.setType(item.getType().toString());
        event.setVictimId(item.getVictimId());
        event.setWardType(item.getWardType().toString());
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
        final Map<Integer, MatchParticipantFrame> participantFrames = new HashMap<>();
        final Object previous = context.get("participantId");
        for(final Integer participantId : item.getParticipantFrames().keySet()) {
            context.put("participantId", participantId);
            participantFrames.put(participantId, transform(item.getParticipantFrames().get(participantId), context));
        }
        context.put("participantId", previous);
        frame.setParticipantFrames(participantFrames);
        return frame;
    }

    @Transform(from = Match.class, to = com.merakianalytics.orianna.types.dto.match.Match.class)
    public com.merakianalytics.orianna.types.dto.match.Match transform(final Match item, final PipelineContext context) {
        final Object previousDuration = context.put("duration", item.getDuration());
        final com.merakianalytics.orianna.types.dto.match.Match match = new com.merakianalytics.orianna.types.dto.match.Match();
        match.setForAccountId(item.getForAccountId());
        match.setGameCreation(item.getCreationTime().getMillis());
        match.setGameDuration(item.getDuration().getStandardSeconds());
        match.setGameId(item.getId());
        match.setGameMode(item.getMode().toString());
        match.setGameType(item.getType().toString());
        match.setGameVersion(item.getVersion());
        match.setMapId(item.getMap().getId());
        final List<ParticipantIdentity> identities = new ArrayList<>(item.getPlayers().size());
        final List<Participant> participants = new ArrayList<>(item.getPlayers().size());
        final int id = 1;
        final Object previousParticipantId = context.put("participantId", id);
        for(final Player player : item.getPlayers()) {
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
        match.setPlatformId(item.getPlatform().getTag());
        match.setQueueId(item.getQueue().getId());
        match.setSeasonId(item.getSeason().getId());
        final List<TeamStats> teams = new ArrayList<>(2);
        final Object previousTeam = context.put("team", com.merakianalytics.orianna.types.common.Team.BLUE);
        teams.add(transform(item.getBlueTeam(), context));
        context.put("team", com.merakianalytics.orianna.types.common.Team.RED);
        teams.add(transform(item.getRedTeam(), context));
        context.put("team", previousTeam);
        match.setTeams(teams);
        match.setTournamentCode(item.getTournamentCode());
        context.put("duration", previousDuration);
        return match;
    }

    @Transform(from = MatchEvent.class, to = Event.class)
    public Event transform(final MatchEvent item, final PipelineContext context) {
        final Event event = new Event();
        event.setAfterId(item.getAfterId());
        event.setAscensionType(AscensionType.valueOf(item.getAscendedType()));
        event.setAssistingParticipants(new ArrayList<>(item.getAssistingParticipantIds()));
        event.setBeforeId(item.getBeforeId());
        event.setBuildingType(BuildingType.valueOf(item.getBuildingType()));
        event.setCapturedPoint(Point.valueOf(item.getPointCaptured()));
        event.setCreatorId(item.getCreatorId());
        event.setItemId(item.getItemId());
        event.setKillerId(item.getKillerId());
        event.setLaneType(LaneType.valueOf(item.getLaneType()));
        event.setLevelUpType(LevelUpType.valueOf(item.getLevelUpType()));
        event.setMonsterSubType(MonsterSubType.valueOf(item.getMonsterSubType()));
        event.setParticipantId(item.getParticipantId());
        event.setPosition(transform(item.getPosition(), context));
        event.setSkill(Skill.withId(item.getSkillSlot()));
        event.setTeam(com.merakianalytics.orianna.types.common.Team.withId(item.getTeamId()));
        event.setTimestamp(Duration.millis(item.getTimestamp()));
        event.setTurretType(TurretType.valueOf(item.getTowerType()));
        event.setType(EventType.valueOf(item.getType()));
        event.setVictimId(item.getVictimId());
        event.setWardType(WardType.valueOf(item.getWardType()));
        return event;
    }

    @Transform(from = MatchFrame.class, to = Frame.class)
    public Frame transform(final MatchFrame item, final PipelineContext context) {
        final Frame frame = new Frame(item.getEvents().size());
        for(final MatchEvent event : item.getEvents()) {
            frame.add(transform(event, context));
        }
        final Map<Integer, ParticipantFrame> participantFrames = new HashMap<>();
        for(final Integer participantId : item.getParticipantFrames().keySet()) {
            participantFrames.put(participantId, transform(item.getParticipantFrames().get(participantId), context));
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
        list.setPlatform(Platform.withTag(item.getPlatform()));
        final Set<Queue> queues = new HashSet<>();
        for(final Integer queue : item.getQueues()) {
            queues.add(Queue.withId(queue));
        }
        list.setQueues(queues);
        list.setRecent(item.isRecent());
        final Set<Season> seasons = new HashSet<>();
        for(final Integer season : item.getSeasons()) {
            seasons.add(Season.withId(season));
        }
        list.setSeasons(seasons);
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
        list.setPlatform(item.getPlatform().getTag());
        final Set<Integer> queues = new HashSet<>();
        for(final Queue queue : item.getQueues()) {
            queues.add(queue.getId());
        }
        list.setQueues(queues);
        list.setRecent(item.isRecent());
        final Set<Integer> seasons = new HashSet<>();
        for(final Season season : item.getSeasons()) {
            seasons.add(season.getId());
        }
        list.setSeasons(seasons);
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
        frame.setPosition(transform(item.getPosition(), context));
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
        reference.setChampion(item.getChampionId());
        reference.setTimestamp(item.getCreationTime().getMillis());
        reference.setGameId(item.getId());
        reference.setLane(item.getLane().toString());
        reference.setPlatformId(item.getPlatform().getTag());
        reference.setQueue(item.getQueue().getId());
        reference.setRole(item.getRole().toString());
        reference.setSeason(item.getSeason().getId());
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
        timeline.setPlatform(Platform.withTag(item.getPlatform()));
        return timeline;
    }

    @Transform(from = Participant.class, to = Player.class)
    public Player transform(final Participant item, final PipelineContext context) {
        final Player converted = new Player();
        converted.setChampionId(item.getChampionId());
        converted.setHighestTierInSeason(Tier.valueOf(item.getHighestAchievedSeasonTier()));
        Map<Integer, Integer> counts = new HashMap<>();
        for(final Mastery mastery : item.getMasteries()) {
            counts.put(mastery.getMasteryId(), mastery.getRank());
        }
        converted.setMasteries(counts);
        counts = new HashMap<>();
        for(final Rune mastery : item.getRunes()) {
            counts.put(mastery.getRuneId(), mastery.getRank());
        }
        converted.setRunes(counts);
        converted.setStats(transform(item.getStats(), context));
        converted.setSummonerSpellDId(item.getSpell1Id());
        converted.setSummonerSpellFId(item.getSpell2Id());
        converted.setTeam(com.merakianalytics.orianna.types.common.Team.withId(item.getTeamId()));
        converted.setTimeline(transform(item.getTimeline(), context));
        final com.merakianalytics.orianna.types.dto.match.Player player = (com.merakianalytics.orianna.types.dto.match.Player)context.get("player");
        converted.setAccountId(player.getAccountId());
        converted.setCurrentAccountId(player.getCurrentAccountId());
        converted.setCurrentPlatform(Platform.withTag(player.getCurrentPlatformId()));
        converted.setMatchHistoryURI(player.getMatchHistoryUri());
        converted.setPlatform(Platform.withTag(player.getPlatformId()));
        converted.setProfileIconId(player.getProfileIcon());
        converted.setSummonerId(player.getSummonerId());
        converted.setSummonerName(player.getSummonerName());
        return converted;
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
        frame.setPosition(transform(item.getPosition(), context));
        frame.setTeamScore(item.getTeamScore());
        return frame;
    }

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
        stats.setItem0(item.getItems().get(0));
        stats.setItem1(item.getItems().get(1));
        stats.setItem2(item.getItems().get(2));
        stats.setItem3(item.getItems().get(3));
        stats.setItem4(item.getItems().get(4));
        stats.setItem5(item.getItems().get(5));
        stats.setItem6(item.getItems().get(6));
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
        timeline.setCreepsPerMinDeltas(getPerMinDeltas(item.getCreepScore(), duration));
        timeline.setCsDiffPerMinDeltas(getPerMinDeltas(item.getCreepScoreDifference(), duration));
        timeline.setDamageTakenDiffPerMinDeltas(getPerMinDeltas(item.getDamageTakenDifference(), duration));
        timeline.setDamageTakenPerMinDeltas(getPerMinDeltas(item.getDamageTaken(), duration));
        timeline.setGoldPerMinDeltas(getPerMinDeltas(item.getGold(), duration));
        timeline.setLane(item.getLane().toString());
        timeline.setParticipantId((Integer)context.get("participantId"));
        timeline.setRole(item.getRole().toString());
        timeline.setXpDiffPerMinDeltas(getPerMinDeltas(item.getExperienceDifference(), duration));
        timeline.setXpPerMinDeltas(getPerMinDeltas(item.getExperience(), duration));
        return timeline;
    }

    @Transform(from = Player.class, to = com.merakianalytics.orianna.types.dto.match.Player.class)
    public com.merakianalytics.orianna.types.dto.match.Player transform(final Player item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.match.Player player = new com.merakianalytics.orianna.types.dto.match.Player();
        player.setAccountId(item.getAccountId());
        player.setCurrentAccountId(item.getCurrentAccountId());
        player.setCurrentPlatformId(item.getCurrentPlatform().getTag());
        player.setMatchHistoryUri(item.getMatchHistoryURI());
        player.setPlatformId(item.getPlatform().getTag());
        player.setProfileIcon(item.getProfileIconId());
        player.setSummonerId(item.getSummonerId());
        player.setSummonerName(item.getSummonerName());
        return player;
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
        final com.merakianalytics.orianna.types.common.Team side = (com.merakianalytics.orianna.types.common.Team)context.get("team");
        final List<TeamBans> bans = new ArrayList<>(item.getBans().size());
        final boolean isTenBanMode = item.getBans().size() == 5;
        int turn = com.merakianalytics.orianna.types.common.Team.BLUE == side ? 1 : isTenBanMode ? 6 : 2;
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
        team.setTeamId(side.getId());
        team.setTowerKills(item.getTowerKills());
        team.setVilemawKills(item.getVilemawKills());
        team.setWin(item.isWinner() ? "Win" : "Fail");
        return team;
    }

    @Transform(from = TeamStats.class, to = Team.class)
    public Team transform(final TeamStats item, final PipelineContext context) {
        final Team team = new Team();
        final List<Integer> bans = new ArrayList<>(item.getBans().size());
        for(final TeamBans ban : item.getBans()) {
            bans.add(ban.getChampionId());
        }
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
        timeline.setPlatform(item.getPlatform().getTag());
        return timeline;
    }

    @Transform(from = TournamentMatches.class, to = com.merakianalytics.orianna.types.dto.match.TournamentMatches.class)
    public com.merakianalytics.orianna.types.dto.match.TournamentMatches transform(final TournamentMatches item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.match.TournamentMatches matches = new com.merakianalytics.orianna.types.dto.match.TournamentMatches(item.size());
        matches.addAll(item);
        matches.setPlatform(item.getPlatform().getTag());
        matches.setTournamentCode(item.getTournamentCode());
        return matches;
    }

    @Transform(from = Player.class, to = Participant.class)
    public Participant transformToParticipant(final Player item, final PipelineContext context) {
        final Object previous = context.put("participantId", item.getParticipantId());
        final Participant participant = new Participant();
        participant.setChampionId(item.getChampionId());
        participant.setHighestAchievedSeasonTier(item.getHighestTierInSeason().toString());
        final List<Mastery> masteries = new ArrayList<>(item.getMasteries().size());
        for(final Integer id : item.getMasteries().keySet()) {
            final Mastery mastery = new Mastery();
            mastery.setMasteryId(id);
            mastery.setRank(item.getMasteries().get(id));
            masteries.add(mastery);
        }
        participant.setMasteries(masteries);
        participant.setParticipantId(item.getParticipantId());
        final List<Rune> runes = new ArrayList<>(item.getRunes().size());
        for(final Integer id : item.getRunes().keySet()) {
            final Rune rune = new Rune();
            rune.setRuneId(id);
            rune.setRank(item.getRunes().get(id));
            runes.add(rune);
        }
        participant.setRunes(runes);
        participant.setSpell1Id(item.getSummonerSpellDId());
        participant.setSpell2Id(item.getSummonerSpellFId());
        participant.setStats(transform(item.getStats(), context));
        participant.setTeamId(item.getTeam().getId());
        participant.setTimeline(transform(item.getTimeline(), context));
        context.put("participantId", previous);
        return participant;
    }
}
