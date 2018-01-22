package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.ChampionSpell;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.SpellVariables;

public class GetChampions {
    public static void main(final String[] args) {
        final Champions champions = Champions.withRegion(Region.NORTH_AMERICA).get();
        for(final Champion champion : champions) {
            System.out.println(champion.getName() + " " + champion.getId());
        }

        final Champion annie = Champion.named("Annie").withRegion(Region.NORTH_AMERICA).get();
        System.out.println(annie.getName());
        System.out.println(annie.getTitle());
        for(final ChampionSpell spell : annie.getSpells()) {
            System.out.println(spell.getName() + " " + spell.getLevelUpKeywords());
        }

        System.out.println(annie.getDifficultyRating());
        System.out.println(annie.getPassive().getName());

        for(final Item item : annie.getRecommendedItems().get(0).get(0).keySet()) {
            System.out.println(item.getName());
        }
        System.out.println(annie.isFreeToPlay());

        final Champion ziggs = Champion.named("Ziggs").withRegion(Region.NORTH_AMERICA).get();
        System.out.println(ziggs.getName());
        System.out.println(ziggs.getRegion());
        for(final Item item : annie.getRecommendedItems().get(0).get(0).keySet()) {
            System.out.println(item.getName());
        }
        System.out.println(annie.isFreeToPlay());
        for(final ChampionSpell spell : ziggs.getSpells()) {
            for(final SpellVariables var : spell.getVariables()) {
                System.out.println(spell.getName() + " " + var);
            }
        }
    }
}