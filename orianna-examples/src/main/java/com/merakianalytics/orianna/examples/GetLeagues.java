package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.league.Leagues;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.core.league.LeaguePosition;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class GetLeagues {
    public static void main(final String[] args) {
    		final Summoner summoner = Summoner.named("FatalElement").withRegion(Region.NORTH_AMERICA).get();
    		System.out.println("Name: " + summoner.getName());
    		System.out.println("ID: " + summoner.getId());
    		
    		LeaguePositions positions = summoner.getLeaguePositions();
    		if(positions.get(0).getPromos() != null) {  // TODO change .get(0) to .getFives() or something like that (and in the below lines)
    			// If the summoner is in their promos, print some info
			System.out.println("Promos progress: " + positions.get(0).getPromos().getProgess());
			System.out.println("Promos wins: " + positions.get(0).getPromos().getWins());
			System.out.println("Promos losses: " + positions.get(0).getPromos().getLosses());
			//System.out.println("Games not yet played in promos: " + positions.get(0).getPromos().getNotPlayed());
			//System.out.println("Number of wins required to win promos: " + positions.get(0).getPromos().getWinsRequired());
    		}
    		else {
    			System.out.println("The summoner is not in their promos.");
    		}

		System.out.println("Name of leagues this summoner is in:");
		for(LeaguePosition leaguePosition : positions) {
			System.out.println(leaguePosition.getName());
		}
		System.out.println("\n");
		
		Leagues leagues = summoner.getLeagues();
		System.out.println("Name of leagues this summoner is in (called from a different endpoint):");
		for(League league : leagues) {
			System.out.println(league.getName());
		}
		System.out.println("\n");

		System.out.println("Listing all summoners in " + leagues.get(0).getName());  // TODO ibid
		for(LeagueEntry entry : leagues.get(0)) {  // TOOD ibid
			System.out.println(entry.getSummoner().getName() + " " + entry.getLeaguePoints() + " " + leagues.get(0).getTier() + " " + entry.getDivision());  // TODO ibid
		}
		System.out.println("\n");
		System.out.println("Challenger League name:");
		ChallengerLeague challenger = ChallengerLeague.withQueue(Queue.RANKED_SOLO_5x5).withRegion(Region.NORTH_AMERICA);
		System.out.println(challenger.getName());
    }
}