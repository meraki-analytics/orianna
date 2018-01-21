package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.spectator.FeaturedMatch;
import com.merakianalytics.orianna.types.core.spectator.FeaturedMatches;
import com.merakianalytics.orianna.types.core.spectator.Player;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class GetCurrentMatches {
    public static void main(final String[] args) {
        final FeaturedMatches featured = FeaturedMatches.forRegion(Region.NORTH_AMERICA).get();
        for(final FeaturedMatch game : featured) {
            System.out.println(game.getRegion() + " " + game.getId());
        }

        final FeaturedMatch game = featured.get(0);
        final String aSummonerName = game.getPlayers().get(0).getSummoner().getName();
        final Summoner summoner = Summoner.named(aSummonerName).withRegion(game.getRegion()).get();
        final CurrentMatch currentGame = summoner.getCurrentMatch();
        System.out.println(currentGame.getMap());

        for(final Player player : currentGame.getPlayers()) {
            System.out.println(player.getSummoner().getName());
        }
    }
}