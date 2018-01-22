package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.ReforgedRune;
import com.merakianalytics.orianna.types.core.staticdata.ReforgedRunes;

public class GetRunes {
    public static void main(final String[] args) {
        final ReforgedRunes runes = ReforgedRunes.withRegion(Region.NORTH_AMERICA).get();
        for(final ReforgedRune rune : runes) {
            System.out.println(rune.getName() + " " + rune.getId());
        }
    }
}