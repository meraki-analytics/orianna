package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;

public class GetChampionMasteries {
    public static void main(final String[] args) {
    		// Name: FatalElement
		// ID: 22508641
		// Account ID: 36321079
        final Summoner summoner = Summoner.named("FatalElement").withRegion(Region.NORTH_AMERICA).get();
    		final Champion taliyah = Champion.named("Taliyah").withRegion(Region.NORTH_AMERICA).get();
    		ChampionMastery cm = summoner.getChampionMastery(taliyah);
    		System.out.println("Champion ID: " + cm.getChampion().getId());
    		System.out.println("Mastery points: " + cm.getPoints());
    		System.out.println("Mastery level: " + cm.getLevel());
    		System.out.println("Points until next level: " + cm.getPointsUntilNextLevel());

    		// ChampionMasteries cms = ChampionMasteries.forSummoner(summoner).get();
    		ChampionMasteries cms = summoner.getChampionMasteries();
    		System.out.println(cms.get(3).getPoints());
    		System.out.println(cms.find(taliyah.getName()).getPoints());
    		
    		System.out.println(summoner.getName() + " has mastery level 6 or higher on:");
    		SearchableList<ChampionMastery> pro = cms.filter((ChampionMastery _cm) -> _cm.getLevel() >= 6);
    		for(ChampionMastery _cm : pro) {
    			System.out.println(_cm.getChampion().getName());
    		}
    }
}
/*
cms = cass.get_champion_masteries(summoner=me, region="NA")
cms = me.champion_masteries
print(cms[0].points)
# print(cms["Karma"].points)  # Does a ton of calls without a cache

print("{} has mastery level 6 or higher on:".format(me.name))
pro = cms.filter(lambda cm: cm.level >= 6)
print([cm.champion.name for cm in pro])
*/