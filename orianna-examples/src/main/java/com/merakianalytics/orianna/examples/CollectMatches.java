package com.merakianalytics.orianna.examples;

import java.util.HashSet;

import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class CollectMatches {
	
	public static MatchHistory filterMatchHistory(final Summoner summoner) {
		MatchHistory matchHistory = MatchHistory.forSummoner(summoner).withQueues(Queue.ARAM).withSeasons(Season.SEASON_8).get();
		return matchHistory;
	}
	
    public static void main(final String[] args) {
        final Summoner summoner = Summoner.named("GustavEnk").withRegion(Region.EUROPE_WEST).get();
        Region region = summoner.getRegion();

        HashSet<Long> unpulledSummonerIds = new HashSet<Long>();
        unpulledSummonerIds.add(summoner.getId());
        HashSet<Long> pulledSummonerIds = new HashSet<Long>();
        
        HashSet<Long> unpulledMatchIds = new HashSet<Long>();
        HashSet<Long> pulledMatchIds = new HashSet<Long>();
       
        while(!unpulledSummonerIds.isEmpty()) {
        	// Get a new summoner from our list of unpulled summoners and pull their match history
        	long newSummonerId = unpulledSummonerIds.iterator().next();
        	Summoner newSummoner = Summoner.withId(newSummonerId).withRegion(region).get();
        	MatchHistory matches = filterMatchHistory(newSummoner);
        	for(Match match : matches) {
        		if(!pulledMatchIds.contains(match.getId())) {
        			unpulledMatchIds.add(match.getId());        			
        		}
        	}
        	unpulledSummonerIds.remove(newSummonerId);
        	pulledSummonerIds.add(newSummonerId);
        	
        	while(!unpulledMatchIds.isEmpty()) {
        		// Get a random match from our list of matches
        		long newMatchId = unpulledMatchIds.iterator().next();
        		Match newMatch = Match.withId(newMatchId).withRegion(region).get();
        		for(Participant p : newMatch.getParticipants()) {
        			if(!pulledSummonerIds.contains(p.getSummoner().getId()) && !unpulledSummonerIds.contains(p.getSummoner().getId())) {
        				unpulledSummonerIds.add(p.getSummoner().getId());
        			}
        		}
        		// The above lines will trigger the match to load its data by iterating over all the participants.
        		// If you have a database in your datapipeline, the match will automatically be stored in it.
        		unpulledMatchIds.remove(newMatchId);
        		pulledMatchIds.add(newMatchId);
        	}
        }
    }
}