package com.merakianalytics.orianna.datapipeline.transformers;

import java.util.ArrayList;
import java.util.List;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.champion.ChampionStatus;
import com.merakianalytics.orianna.types.data.champion.ChampionStatuses;
import com.merakianalytics.orianna.types.dto.champion.Champion;
import com.merakianalytics.orianna.types.dto.champion.ChampionList;

public class ChampionTransformer extends AbstractDataTransformer {
    @Transform(from = Champion.class, to = ChampionStatus.class)
    public ChampionStatus transform(final Champion item, final PipelineContext context) {
        final ChampionStatus status = new ChampionStatus();
        status.setEnabled(item.isActive());
        status.setEnabledInCoopVsAI(item.isBotMmEnabled());
        status.setEnabledInCustoms(item.isBotEnabled());
        status.setEnabledInRanked(item.isRankedPlayEnabled());
        status.setFreeToPlay(item.isFreeToPlay());
        status.setId(item.getId());
        status.setPlatform(Platform.valueOf(item.getPlatform()));
        return status;
    }

    @Transform(from = ChampionList.class, to = ChampionStatuses.class)
    public ChampionStatuses transform(final ChampionList item, final PipelineContext context) {
        final ChampionStatuses statuses = new ChampionStatuses(item.getChampions().size());
        for(final Champion champion : item.getChampions()) {
            statuses.add(transform(champion, context));
        }
        statuses.setFreeToPlay(item.isFreeToPlay());
        statuses.setPlatform(Platform.valueOf(item.getPlatform()));
        return statuses;
    }

    @Transform(from = ChampionStatus.class, to = Champion.class)
    public Champion transform(final ChampionStatus item, final PipelineContext context) {
        final Champion status = new Champion();
        status.setActive(item.isEnabled());
        status.setBotMmEnabled(item.isEnabledInCoopVsAI());
        status.setBotEnabled(item.isEnabledInCustoms());
        status.setRankedPlayEnabled(item.isEnabledInRanked());
        status.setFreeToPlay(item.isFreeToPlay());
        status.setId(item.getId());
        status.setPlatform(item.getPlatform().toString());
        return status;
    }

    @Transform(from = ChampionStatuses.class, to = ChampionList.class)
    public ChampionList transform(final ChampionStatuses item, final PipelineContext context) {
        final ChampionList statuses = new ChampionList();
        final List<Champion> stats = new ArrayList<>(item.size());
        for(final ChampionStatus status : item) {
            stats.add(transform(status, context));
        }
        statuses.setChampions(stats);
        statuses.setFreeToPlay(item.isFreeToPlay());
        statuses.setPlatform(item.getPlatform().toString());
        return statuses;
    }
}
