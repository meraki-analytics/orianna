package com.merakianalytics.orianna.datapipeline.transformers.dtodata;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.data.account.Account;

public class AccountTransformer extends AbstractDataTransformer {
    @Transform(from = com.merakianalytics.orianna.types.dto.account.Account.class, to = Account.class)
    public Account transformer(final com.merakianalytics.orianna.types.dto.account.Account item, final PipelineContext context) {
        final Account account = new Account();
        account.setGameName(item.getGameName());
        account.setTagLine(item.getTagLine());
        account.setPlatform(item.getPlatform());
        account.setPuuid(item.getPuuid());
        return account;
    }

    @Transform(from = Account.class, to = com.merakianalytics.orianna.types.dto.account.Account.class)
    public com.merakianalytics.orianna.types.dto.account.Account transformer(final Account item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.account.Account account = new com.merakianalytics.orianna.types.dto.account.Account();
        account.setGameName(item.getGameName());
        account.setTagLine(item.getTagLine());
        account.setPlatform(item.getPlatform());
        account.setPuuid(item.getPuuid());
        return account;
    }
}
