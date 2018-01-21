package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Rune;
import com.merakianalytics.orianna.types.core.staticdata.Runes;

public class GetRunes {
    public static void main(final String[] args) {
    		final Runes runes = Runes.withRegion(Region.NORTH_AMERICA).get();
    		for(Rune rune : runes) {
    			System.out.println(rune.getName() + " " + rune.getId());
    		}
    }
}