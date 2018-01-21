package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpells;

public class GetSummonerSpells {
    public static void main(final String[] args) {
        final SummonerSpells summonerSpells = SummonerSpells.withRegion(Region.NORTH_AMERICA).get();
        for(final SummonerSpell summonerSpell : summonerSpells) {
            System.out.println(summonerSpell.getName() + " " + summonerSpell.getId());
        }
    }
}