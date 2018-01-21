package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class GetThirdPartyCodeVerification {
    public static void main(final String[] args) {
        final Summoner summoner = Summoner.named("FatalElement").withRegion(Region.NORTH_AMERICA).get();
        System.out.println(summoner.getVerificationString());
    }
}