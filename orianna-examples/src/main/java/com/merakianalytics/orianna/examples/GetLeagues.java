package com.merakianalytics.orianna.examples;

import java.util.List;

import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.core.league.LeaguePosition;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class GetLeagues {
    public static void main(final String[] args) {
        final Summoner summoner = Summoner.named("Spartan324").withRegion(Region.NORTH_AMERICA).get();
        System.out.println("Name: " + summoner.getName());
        System.out.println("ID: " + summoner.getId());

        final LeaguePosition rankedFivesPositions = summoner.getLeaguePosition(Queue.RANKED_SOLO_5x5);
        if(rankedFivesPositions.getPromos() != null) {
            // If the summoner is in their promos, print some info
            System.out.println("Promos progress: " + rankedFivesPositions.getPromos().getProgess());
            System.out.println("Promos wins: " + rankedFivesPositions.getPromos().getWins());
            System.out.println("Promos losses: " + rankedFivesPositions.getPromos().getLosses());
            System.out.println("Games not yet played in promos: " + rankedFivesPositions.getPromos().getNotPlayed());
            System.out.println("Number of wins required to win promos: " + rankedFivesPositions.getPromos().getWinsRequired());
        } else {
            System.out.println("The summoner is not in their promos.");
        }

        System.out.println("Name of leagues this summoner is in:");
        final LeaguePositions positions = summoner.getLeaguePositions();
        for(final LeaguePosition leaguePosition : positions) {
            System.out.println(leaguePosition.getName());
        }
        System.out.println("\n");

        final List<League> leagues = summoner.getLeagues();
        System.out.println("Name of leagues this summoner is in (called from a different endpoint):");
        for(final League league : leagues) {
            System.out.println(league.getName());
        }
        System.out.println("\n");

        League rankedFivesLeague = summoner.getLeague(Queue.RANKED_SOLO_5x5);
        System.out.println("Listing all summoners in " + rankedFivesLeague.getName());
        for(final LeagueEntry entry : rankedFivesLeague) {
            System.out.println(entry.getSummoner().getName() + " " + entry.getLeaguePoints() + " " + rankedFivesLeague.getTier() + " " + entry.getDivision());
        }
        System.out.println("\n");
        System.out.println("Challenger League name:");
        final League challenger = League.challengerInQueue(Queue.RANKED_SOLO_5x5).withRegion(Region.NORTH_AMERICA).get();
        System.out.println(challenger.getName());
    }
}