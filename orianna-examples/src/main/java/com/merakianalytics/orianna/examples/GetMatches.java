package com.merakianalytics.orianna.examples;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.staticdata.Champion;

public class GetMatches {
    public static void main(final String[] args) {
    		// Notice how this object never requires a call to the summoner endpoint because we provide all the needed data!
    		final Summoner summoner = Summoner.withAccountId(36321079).withRegion(Region.NORTH_AMERICA).get(); //.withId(22508641).named("FatalElement")

    		// A MatchHistory is a lazy list, meaning it's elements only get loaded as-needed.

    		MatchHistory matchHistory = MatchHistory.forSummoner(summoner).get();
    		// MatchHistory match_history = MatchHistory.forSummoner(summoner).withQueues([Queue.RANKED_SOLO_5x5]).withSeasons([Season.SEASON_7]).get();

		// Load the entire match history by iterating over all its elements so that we know how long it is.
		// Unfortunately since we are iterating over the match history and accessing the summoner's champion for each match,
		// we need to know what version of the static data the champion should have. To avoid pulling many different
		// static data versions, we will instead create a {champion_id -> champion_name} mapping and just access the champion's
		// ID from the match data (which it provides directly).
    		Map<Integer, String> championIdToNameMapping = new HashMap<Integer, String>();
    		for(Champion champion : Champions.withRegion(Region.NORTH_AMERICA).get()) {
    			championIdToNameMapping.put(champion.getId(), champion.getName());
    		}
    		Map<String, Integer> playedChampions = new HashMap<String, Integer>();
    		for(Match match : matchHistory) {
    			Integer championId = match.getParticipants().find(summoner).getChampion().getId();
    			String championName = championIdToNameMapping.get(championId);
    			Integer count = playedChampions.get(championName);
    			if(count == null) {
    			  count = 0;
    			  playedChampions.put(championName, count);
    			}
    			playedChampions.put(championName, playedChampions.get(championName) + 1);
    		}
    		System.out.println("Length of match history: " + matchHistory.size());

    		// Print the aggregated champion results
    		playedChampions.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
            .collect(Collectors.toMap(
              Map.Entry::getKey, 
              Map.Entry::getValue, 
              (e1, e2) -> e1, 
              LinkedHashMap::new
            ));

    		// TODO Fix this so it only prints the top 10
    		System.out.println("Top 10 champions " + summoner.getName() + " played:");
    		for(String championName : playedChampions.keySet()) {
    			Integer count = playedChampions.get(championName);
    			System.out.println(championName + " " + count);
    		}
    		/*
    		for(String championName : 
				playedChampions.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(
				  Map.Entry::getKey, 
				  Map.Entry::getValue, 
				  (e1, e2) -> e1, 
				  LinkedHashMap::new
				))) {
    			Integer count = playedChampions.get(championName);
    			System.out.println(championName + " " + count);
    		}
    		*/
    		System.out.println("\n");
    		
    		Match match = matchHistory.get(0);
    		System.out.println("Match ID: " + match.getId());

    		Participant p = match.getParticipants().find(summoner);
    		System.out.println("\nSince the match was created from a matchref, we only know one participant:");
    		System.out.println(p.getSummoner().getName() + " played " + p.getChampion().getName());
    		
    		System.out.println("\nNow pull the full match data by iterating over all the participants:");
    		for(Participant _p : match.getParticipants()) {
    			System.out.println(_p.getSummoner().getName() + " played " + _p.getChampion().getName());
    		}

    		System.out.println("\nIterate over all the participants again and note that the data is not repulled:");
    		for(Participant _p : match.getParticipants()) {
    			System.out.println(_p.getSummoner().getName() + " played " + _p.getChampion().getName());
    		}

		System.out.println("\nBlue team won? " + match.getBlueTeam().isWinner());
		System.out.println("Red team won? " + match.getRedTeam().isWinner());
		System.out.println("Participants on blue team:");
		for(Participant _p : match.getBlueTeam().getParticipants()) {
			System.out.println(_p.getSummoner().getName());
			}
		}
}