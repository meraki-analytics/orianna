package com.merakianalytics.orianna.types.core.thirdpartycode;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class VerificationString extends GhostObject<com.merakianalytics.orianna.types.data.thirdpartycode.VerificationString> {
    public static class Builder {
        private final Summoner summoner;

        private Builder(final Summoner summoner) {
            this.summoner = summoner;
        }

        public VerificationString get() {
            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("summonerId", summoner.getId());

            return Orianna.getSettings().getPipeline().get(VerificationString.class, builder.build());
        }
    }

    private static final long serialVersionUID = -7674629544327997718L;
    public static final String VERIFICATION_STRING_LOAD_GROUP = "verification-string";

    public static Builder forSummoner(final Summoner summoner) {
        return new Builder(summoner);
    }

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            return Summoner.withId(coreData.getSummonerId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public VerificationString(final com.merakianalytics.orianna.types.data.thirdpartycode.VerificationString coreData) {
        super(coreData, 1);
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public String getString() {
        load(VERIFICATION_STRING_LOAD_GROUP);
        return coreData.getString();
    }

    public Summoner getSummoner() {
        return summoner.get();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case VERIFICATION_STRING_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getSummonerId() != 0L) {
                    builder.put("summonerId", coreData.getSummonerId());
                }
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                coreData =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.thirdpartycode.VerificationString.class, builder.build());
                break;
            default:
                break;
        }
    }
}
