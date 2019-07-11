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
    private static MatchHistory filterMatchHistory(final Summoner summoner) {
        final MatchHistory matchHistory = MatchHistory.forSummoner(summoner).withQueues(Queue.ARAM).withSeasons(Season.SEASON_8).get();
        return matchHistory;
    }

    public static void main(final String[] args) {
        final Summoner summoner = Summoner.named("GustavEnk").withRegion(Region.EUROPE_WEST).get();
        final Region region = summoner.getRegion();

        final HashSet<String> unpulledAccountIds = new HashSet<>();
        unpulledAccountIds.add(summoner.getAccountId());
        final HashSet<String> pulledAccountIds = new HashSet<>();

        final HashSet<Long> unpulledMatchIds = new HashSet<>();
        final HashSet<Long> pulledMatchIds = new HashSet<>();

        while(!unpulledAccountIds.isEmpty()) {
            // Get a new summoner from our list of unpulled summoners and pull their match history
            final String newAccountId = unpulledAccountIds.iterator().next();
            final Summoner newSummoner = Summoner.withId(newAccountId).withRegion(region).get();
            final MatchHistory matches = filterMatchHistory(newSummoner);
            for(final Match match : matches) {
                if(!pulledMatchIds.contains(match.getId())) {
                    unpulledMatchIds.add(match.getId());
                }
            }
            unpulledAccountIds.remove(newAccountId);
            pulledAccountIds.add(newAccountId);

            while(!unpulledMatchIds.isEmpty()) {
                // Get a random match from our list of matches
                final long newMatchId = unpulledMatchIds.iterator().next();
                final Match newMatch = Match.withId(newMatchId).withRegion(region).get();
                for(final Participant p : newMatch.getParticipants()) {
                    if(!pulledAccountIds.contains(p.getSummoner().getAccountId()) && !unpulledAccountIds.contains(p.getSummoner().getAccountId())) {
                        unpulledAccountIds.add(p.getSummoner().getId());
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