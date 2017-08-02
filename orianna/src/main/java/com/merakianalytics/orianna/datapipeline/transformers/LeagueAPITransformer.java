package com.merakianalytics.orianna.datapipeline.transformers;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.common.Division;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.data.league.League;
import com.merakianalytics.orianna.types.data.league.LeagueEntry;
import com.merakianalytics.orianna.types.data.league.LeaguePosition;
import com.merakianalytics.orianna.types.data.league.LeaguePositions;
import com.merakianalytics.orianna.types.data.league.Leagues;
import com.merakianalytics.orianna.types.data.league.Series;
import com.merakianalytics.orianna.types.dto.league.LeagueItem;
import com.merakianalytics.orianna.types.dto.league.LeagueList;
import com.merakianalytics.orianna.types.dto.league.MiniSeries;
import com.merakianalytics.orianna.types.dto.league.SummonerLeagues;
import com.merakianalytics.orianna.types.dto.league.SummonerPositions;

public class LeagueAPITransformer extends AbstractDataTransformer {
    @Transform(from = com.merakianalytics.orianna.types.dto.league.LeaguePosition.class, to = LeaguePosition.class)
    public LeaguePosition transform(final com.merakianalytics.orianna.types.dto.league.LeaguePosition item, final PipelineContext context) {
        final LeaguePosition position = new LeaguePosition();
        position.setDivision(Division.valueOf(item.getRank()));
        position.setFreshBlood(item.isFreshBlood());
        position.setInactive(item.isInactive());
        position.setLeaguePoints(item.getLeaguePoints());
        position.setLosses(item.getLosses());
        position.setName(item.getLeagueName());
        position.setOnHotStreak(item.isHotStreak());
        position.setPlatform(Platform.valueOf(item.getPlatform()));
        position.setPromos(transform(item.getMiniSeries(), context));
        position.setQueue(Queue.valueOf(item.getQueueType()));
        position.setSummonerId(Long.parseLong(item.getPlayerOrTeamId()));
        position.setSummonerName(item.getPlayerOrTeamName());
        position.setTier(Tier.valueOf(item.getTier()));
        position.setVeteran(item.isVeteran());
        position.setWins(item.getWins());
        return position;
    }

    @Transform(from = League.class, to = LeagueList.class)
    public LeagueList transform(final League item, final PipelineContext context) {
        final LeagueList list = new LeagueList();
        for(final LeagueEntry entry : item) {
            list.getEntries().add(transform(entry, context));
        }
        list.setName(item.getName());
        list.setPlatform(item.getPlatform().toString());
        list.setQueue(item.getQueue().toString());
        list.setSummonerId(item.getSummonerId());
        list.setTier(item.getTier().toString());
        return list;
    }

    @Transform(from = LeagueEntry.class, to = LeagueItem.class)
    public LeagueItem transform(final LeagueEntry item, final PipelineContext context) {
        final LeagueItem entry = new LeagueItem();
        entry.setRank(item.getDivision().toString());
        entry.setFreshBlood(item.isFreshBlood());
        entry.setInactive(item.isInactive());
        entry.setLeaguePoints(item.getLeaguePoints());
        entry.setLosses(item.getLosses());
        entry.setHotStreak(item.isOnHotStreak());
        entry.setMiniSeries(transform(item.getPromos(), context));
        entry.setPlayerOrTeamId(Long.toString(item.getSummonerId()));
        entry.setPlayerOrTeamName(item.getSummonerName());
        entry.setVeteran(item.isVeteran());
        entry.setWins(item.getWins());
        return entry;
    }

    @Transform(from = LeagueItem.class, to = LeagueEntry.class)
    public LeagueEntry transform(final LeagueItem item, final PipelineContext context) {
        final LeagueEntry entry = new LeagueEntry();
        entry.setDivision(Division.valueOf(item.getRank()));
        entry.setFreshBlood(item.isFreshBlood());
        entry.setInactive(item.isInactive());
        entry.setLeaguePoints(item.getLeaguePoints());
        entry.setLosses(item.getLosses());
        entry.setOnHotStreak(item.isHotStreak());
        entry.setPromos(transform(item.getMiniSeries(), context));
        entry.setSummonerId(Long.parseLong(item.getPlayerOrTeamId()));
        entry.setSummonerName(item.getPlayerOrTeamName());
        entry.setVeteran(item.isVeteran());
        entry.setWins(item.getWins());
        return entry;
    }

    @Transform(from = LeagueList.class, to = League.class)
    public League transform(final LeagueList item, final PipelineContext context) {
        final League league = new League();
        for(final LeagueItem entry : item.getEntries()) {
            league.add(transform(entry, context));
        }
        league.setName(item.getName());
        league.setPlatform(Platform.valueOf(item.getPlatform()));
        league.setQueue(Queue.valueOf(item.getQueue()));
        league.setSummonerId(item.getSummonerId());
        league.setTier(Tier.valueOf(item.getTier()));
        return league;
    }

    @Transform(from = LeaguePosition.class, to = com.merakianalytics.orianna.types.dto.league.LeaguePosition.class)
    public com.merakianalytics.orianna.types.dto.league.LeaguePosition transform(final LeaguePosition item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.league.LeaguePosition position = new com.merakianalytics.orianna.types.dto.league.LeaguePosition();
        position.setRank(item.getDivision().toString());
        position.setFreshBlood(item.isFreshBlood());
        position.setInactive(item.isInactive());
        position.setLeaguePoints(item.getLeaguePoints());
        position.setLosses(item.getLosses());
        position.setLeagueName(item.getName());
        position.setHotStreak(item.isOnHotStreak());
        position.setPlatform(item.getPlatform().toString());
        position.setMiniSeries(transform(item.getPromos(), context));
        position.setQueueType(item.getQueue().toString());
        position.setPlayerOrTeamId(Long.toString(item.getSummonerId()));
        position.setPlayerOrTeamName(item.getSummonerName());
        position.setTier(item.getTier().toString());
        position.setVeteran(item.isVeteran());
        position.setWins(item.getWins());
        return position;
    }

    @Transform(from = LeaguePositions.class, to = SummonerPositions.class)
    public SummonerPositions transform(final LeaguePositions item, final PipelineContext context) {
        final SummonerPositions positions = new SummonerPositions();
        for(final LeaguePosition position : item) {
            positions.add(transform(position, context));
        }
        positions.setPlatform(item.getPlatform().toString());
        positions.setSummonerId(item.getSummonerId());
        return positions;
    }

    @Transform(from = Leagues.class, to = SummonerLeagues.class)
    public SummonerLeagues transform(final Leagues item, final PipelineContext context) {
        final SummonerLeagues leagues = new SummonerLeagues();
        for(final League league : item) {
            leagues.add(transform(league, context));
        }
        leagues.setPlatform(item.getPlatform().toString());
        leagues.setSummonerId(item.getSummonerId());
        return leagues;
    }

    @Transform(from = MiniSeries.class, to = Series.class)
    public Series transform(final MiniSeries item, final PipelineContext context) {
        final Series series = new Series();
        series.setLosses(item.getLosses());
        series.setProgress(item.getProgress());
        series.setTarget(item.getTarget());
        series.setWins(item.getWins());
        return series;
    }

    @Transform(from = Series.class, to = MiniSeries.class)
    public MiniSeries transform(final Series item, final PipelineContext context) {
        final MiniSeries series = new MiniSeries();
        series.setLosses(item.getLosses());
        series.setProgress(item.getProgress());
        series.setTarget(item.getTarget());
        series.setWins(item.getWins());
        return series;
    }

    @Transform(from = SummonerLeagues.class, to = Leagues.class)
    public Leagues transform(final SummonerLeagues item, final PipelineContext context) {
        final Leagues leagues = new Leagues();
        for(final LeagueList list : item) {
            leagues.add(transform(list, context));
        }
        leagues.setPlatform(Platform.valueOf(item.getPlatform()));
        leagues.setSummonerId(item.getSummonerId());
        return leagues;
    }

    @Transform(from = SummonerPositions.class, to = LeaguePositions.class)
    public LeaguePositions transform(final SummonerPositions item, final PipelineContext context) {
        final LeaguePositions positions = new LeaguePositions();
        for(final com.merakianalytics.orianna.types.dto.league.LeaguePosition position : item) {
            positions.add(transform(position, context));
        }
        positions.setPlatform(Platform.valueOf(item.getPlatform()));
        positions.setSummonerId(item.getSummonerId());
        return positions;
    }
}
