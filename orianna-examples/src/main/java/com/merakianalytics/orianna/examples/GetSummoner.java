package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class GetSummoner {
    public static void main(final String[] args) {
        final Summoner summoner = Summoner.named("FatalElement").withRegion(Region.NORTH_AMERICA).get();
        System.out.println("Name: " + summoner.getName());
        System.out.println("ID: " + summoner.getId());
        System.out.println("Account ID: " + summoner.getAccountId());
        System.out.println("Level: " + summoner.getLevel());
        System.out.println("Last Updated: " + summoner.getUpdated());
        System.out.println("Profile Icon ID: " + summoner.getProfileIcon().getId());
        System.out.println("Profile Icon URL: " + summoner.getProfileIcon().getImage().getURL());
    }
}