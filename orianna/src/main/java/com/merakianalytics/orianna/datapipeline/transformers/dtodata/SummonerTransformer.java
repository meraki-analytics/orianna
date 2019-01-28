package com.merakianalytics.orianna.datapipeline.transformers.dtodata;

import org.joda.time.DateTime;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.data.summoner.Summoner;

public class SummonerTransformer extends AbstractDataTransformer {
    @Transform(from = com.merakianalytics.orianna.types.dto.summoner.Summoner.class, to = Summoner.class)
    public Summoner transformer(final com.merakianalytics.orianna.types.dto.summoner.Summoner item, final PipelineContext context) {
        final Summoner summoner = new Summoner();
        summoner.setPuuid(item.getPuuid());
        summoner.setAccountId(item.getAccountId());
        summoner.setId(item.getId());
        summoner.setLevel((int)item.getSummonerLevel());
        summoner.setName(item.getName());
        summoner.setPlatform(item.getPlatform());
        summoner.setProfileIconId(item.getProfileIconId());
        summoner.setUpdated(new DateTime(item.getRevisionDate()));
        return summoner;
    }

    @Transform(from = Summoner.class, to = com.merakianalytics.orianna.types.dto.summoner.Summoner.class)
    public com.merakianalytics.orianna.types.dto.summoner.Summoner transformer(final Summoner item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.summoner.Summoner summoner = new com.merakianalytics.orianna.types.dto.summoner.Summoner();
        summoner.setPuuid(item.getPuuid());
        summoner.setAccountId(item.getAccountId());
        summoner.setId(item.getId());
        summoner.setSummonerLevel(item.getLevel());
        summoner.setName(item.getName());
        summoner.setPlatform(item.getPlatform());
        summoner.setProfileIconId(item.getProfileIconId());
        summoner.setRevisionDate(item.getUpdated().getMillis());
        return summoner;
    }
}
