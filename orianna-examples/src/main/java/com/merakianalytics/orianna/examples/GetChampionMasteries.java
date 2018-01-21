package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class GetChampionMasteries {
    public static void main(final String[] args) {
        // Name: FatalElement
        // ID: 22508641
        // Account ID: 36321079
        final Summoner summoner = Summoner.named("FatalElement").withRegion(Region.NORTH_AMERICA).get();
        final Champion taliyah = Champion.named("Taliyah").withRegion(Region.NORTH_AMERICA).get();
        final ChampionMastery cm = summoner.getChampionMastery(taliyah);
        System.out.println("Champion ID: " + cm.getChampion().getId());
        System.out.println("Mastery points: " + cm.getPoints());
        System.out.println("Mastery level: " + cm.getLevel());
        System.out.println("Points until next level: " + cm.getPointsUntilNextLevel());

        // ChampionMasteries cms = ChampionMasteries.forSummoner(summoner).get();
        final ChampionMasteries cms = summoner.getChampionMasteries();
        System.out.println(cms.get(3).getPoints());
        System.out.println(cms.find(taliyah.getName()).getPoints());

        System.out.println(summoner.getName() + " has mastery level 6 or higher on:");
        final SearchableList<ChampionMastery> pro = cms.filter((final ChampionMastery mastery) -> mastery.getLevel() >= 6);
        for(final ChampionMastery mastery : pro) {
            System.out.println(mastery.getChampion().getName());
        }
    }
}