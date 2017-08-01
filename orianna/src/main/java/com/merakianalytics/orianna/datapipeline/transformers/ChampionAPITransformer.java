package com.merakianalytics.orianna.datapipeline.transformers;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.type.common.Platform;
import com.merakianalytics.orianna.type.data.champion.ChampionStatus;
import com.merakianalytics.orianna.type.data.champion.ChampionStatusList;
import com.merakianalytics.orianna.type.dto.champion.Champion;
import com.merakianalytics.orianna.type.dto.champion.ChampionList;

public class ChampionAPITransformer extends AbstractDataTransformer {
    @Transform(from = Champion.class, to = ChampionStatus.class)
    public ChampionStatus transform(final Champion item, final PipelineContext context) {
        final ChampionStatus transformed = new ChampionStatus();
        transformed.setEnabled(item.isActive());
        transformed.setEnabledInCoopVsAI(item.isBotMmEnabled());
        transformed.setEnabledInCustoms(item.isBotEnabled());
        transformed.setEnabledInRanked(item.isRankedPlayEnabled());
        transformed.setFreeToPlay(item.isFreeToPlay());
        transformed.setId(item.getId());
        transformed.setPlatform(Platform.valueOf(item.getPlatform()));
        return transformed;
    }

    @Transform(from = ChampionList.class, to = ChampionStatusList.class)
    public ChampionStatusList transform(final ChampionList item, final PipelineContext context) {
        final ChampionStatusList transformed = new ChampionStatusList();
        for(final Champion champion : item.getChampions()) {
            transformed.add(transform(champion, context));
        }
        transformed.setFreeToPlay(item.isFreeToPlay());
        transformed.setPlatform(Platform.valueOf(item.getPlatform()));
        return transformed;
    }
}
