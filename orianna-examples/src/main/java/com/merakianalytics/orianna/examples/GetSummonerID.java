package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class GetSummonerID {
    public static void main(final String[] args) {
        final Summoner summoner = Summoner.named("FatalElement").get();
        System.out.println("Summoner ID for " + summoner.getName() + " is " + summoner.getId());
    }
}
