package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.spectator.FeaturedGame;
import com.merakianalytics.orianna.types.core.spectator.FeaturedGames;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.spectator.Player;
import com.merakianalytics.orianna.types.core.spectator.CurrentGame;

public class GetCurrentMatches {
    public static void main(final String[] args) {
    		final FeaturedGames featured = FeaturedGames.forRegion(Region.NORTH_AMERICA).get();
    		for(FeaturedGame game : featured) {
    			System.out.println(game.getRegion() + " " + game.getId());
    		}
    		
    		FeaturedGame game = featured.get(0);
    		String a_summoner_name = game.getPlayers().get(0).getSummoner().getName();
    		Summoner summoner = Summoner.named(a_summoner_name).withRegion(game.getRegion()).get();
    		CurrentGame current_game = summoner.getCurrentGame();
    		System.out.println(current_game.getMap());
    		
    		for(Player player : current_game.getPlayers()) {
    			System.out.println(player.getSummoner().getName());
    		}
    }
}