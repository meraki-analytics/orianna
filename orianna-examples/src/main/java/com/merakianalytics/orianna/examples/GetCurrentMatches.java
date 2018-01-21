package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.spectator.CurrentGame;
import com.merakianalytics.orianna.types.core.spectator.FeaturedGame;
import com.merakianalytics.orianna.types.core.spectator.FeaturedGames;
import com.merakianalytics.orianna.types.core.spectator.Player;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class GetCurrentMatches {
    public static void main(final String[] args) {
        final FeaturedGames featured = FeaturedGames.forRegion(Region.NORTH_AMERICA).get();
        for(final FeaturedGame game : featured) {
            System.out.println(game.getRegion() + " " + game.getId());
        }

        final FeaturedGame game = featured.get(0);
        final String aSummonerName = game.getPlayers().get(0).getSummoner().getName();
        final Summoner summoner = Summoner.named(aSummonerName).withRegion(game.getRegion()).get();
        final CurrentGame currentGame = summoner.getCurrentGame();
        System.out.println(currentGame.getMap());

        for(final Player player : currentGame.getPlayers()) {
            System.out.println(player.getSummoner().getName());
        }
    }
}