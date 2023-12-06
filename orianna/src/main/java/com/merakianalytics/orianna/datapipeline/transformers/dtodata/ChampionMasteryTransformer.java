package com.merakianalytics.orianna.datapipeline.transformers.dtodata;

import org.joda.time.DateTime;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.data.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.data.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.data.championmastery.ChampionMasteryScore;

public class ChampionMasteryTransformer extends AbstractDataTransformer {
    @Transform(from = ChampionMasteries.class, to = com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteries.class)
    public com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteries transform(final ChampionMasteries item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteries masteries =
            new com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteries(item.size());
        for(final ChampionMastery mastery : item) {
            masteries.add(transform(mastery, context));
        }
        masteries.setPlatform(item.getPlatform());
        masteries.setPuuid(item.getPuuid());
        return masteries;
    }

    @Transform(from = ChampionMastery.class, to = com.merakianalytics.orianna.types.dto.championmastery.ChampionMastery.class)
    public com.merakianalytics.orianna.types.dto.championmastery.ChampionMastery transform(final ChampionMastery item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.championmastery.ChampionMastery mastery =
            new com.merakianalytics.orianna.types.dto.championmastery.ChampionMastery();
        mastery.setChampionId(item.getChampionId());
        mastery.setChampionLevel(item.getLevel());
        mastery.setChampionPoints(item.getPoints());
        mastery.setChampionPointsSinceLastLevel(item.getPointsSinceLastLevel());
        mastery.setChampionPointsUntilNextLevel(item.getPointsUntilNextLevel());
        mastery.setChestGranted(item.isChestGranted());
        mastery.setLastPlayTime(item.getLastPlayed().getMillis());
        mastery.setPlatform(item.getPlatform());
        mastery.setPuuid(item.getPuuid());
        mastery.setTokensEarned(item.getTokens());
        return mastery;
    }

    @Transform(from = ChampionMasteryScore.class, to = com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteryScore.class)
    public com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteryScore transform(final ChampionMasteryScore item,
        final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteryScore score =
            new com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteryScore();
        score.setPlatform(item.getPlatform());
        score.setScore(item.getScore());
        score.setPuuid(item.getPuuid());
        return score;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteries.class, to = ChampionMasteries.class)
    public ChampionMasteries transform(final com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteries item, final PipelineContext context) {
        final ChampionMasteries masteries = new ChampionMasteries(item.size());
        for(final com.merakianalytics.orianna.types.dto.championmastery.ChampionMastery mastery : item) {
            masteries.add(transform(mastery, context));
        }
        masteries.setPlatform(item.getPlatform());
        masteries.setPuuid(item.getPuuid());
        return masteries;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.championmastery.ChampionMastery.class, to = ChampionMastery.class)
    public ChampionMastery transform(final com.merakianalytics.orianna.types.dto.championmastery.ChampionMastery item, final PipelineContext context) {
        final ChampionMastery mastery = new ChampionMastery();
        mastery.setChampionId((int)item.getChampionId());
        mastery.setChestGranted(item.isChestGranted());
        mastery.setLastPlayed(new DateTime(item.getLastPlayTime()));
        mastery.setLevel(item.getChampionLevel());
        mastery.setPlatform(item.getPlatform());
        mastery.setPuuid(item.getPuuid());
        mastery.setPoints(item.getChampionPoints());
        mastery.setPointsSinceLastLevel((int)item.getChampionPointsSinceLastLevel());
        mastery.setPointsUntilNextLevel((int)item.getChampionPointsUntilNextLevel());
        mastery.setTokens(item.getTokensEarned());
        return mastery;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteryScore.class, to = ChampionMasteryScore.class)
    public ChampionMasteryScore transform(final com.merakianalytics.orianna.types.dto.championmastery.ChampionMasteryScore item,
        final PipelineContext context) {
        final ChampionMasteryScore score = new ChampionMasteryScore();
        score.setPlatform(item.getPlatform());
        score.setScore(item.getScore());
        score.setPuuid(item.getPuuid());
        return score;
    }
}
