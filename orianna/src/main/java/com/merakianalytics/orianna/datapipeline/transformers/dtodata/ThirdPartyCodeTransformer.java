package com.merakianalytics.orianna.datapipeline.transformers.dtodata;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.data.thirdpartycode.VerificationString;

public class ThirdPartyCodeTransformer extends AbstractDataTransformer {
    @Transform(from = com.merakianalytics.orianna.types.dto.thirdpartycode.VerificationString.class, to = VerificationString.class)
    public VerificationString transform(final com.merakianalytics.orianna.types.dto.thirdpartycode.VerificationString item, final PipelineContext context) {
        final VerificationString string = new VerificationString();
        string.setPlatform(item.getPlatform());
        string.setString(item.getString());
        string.setSummonerId(item.getSummonerId());
        return string;
    }

    @Transform(from = VerificationString.class, to = com.merakianalytics.orianna.types.dto.thirdpartycode.VerificationString.class)
    public com.merakianalytics.orianna.types.dto.thirdpartycode.VerificationString transform(final VerificationString item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.thirdpartycode.VerificationString string =
            new com.merakianalytics.orianna.types.dto.thirdpartycode.VerificationString();
        string.setPlatform(item.getPlatform());
        string.setString(item.getString());
        string.setSummonerId(item.getSummonerId());
        return string;
    }
}
