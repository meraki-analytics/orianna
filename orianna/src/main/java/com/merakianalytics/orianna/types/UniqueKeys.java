package com.merakianalytics.orianna.types;

import java.util.Arrays;
import java.util.Map;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champion.ChampionData;

public abstract class UniqueKeys {
    public static int[] forChampion(final Champion champion) {
        final ChampionData data = champion.getCoreData();
        if(data.getId() != null && data.getName() != null) {
            return new int[] {
                              Arrays.hashCode(new Object[] {Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(),
                                                            champion.getLocale(), champion.getId()}),
                              Arrays.hashCode(new Object[] {Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(),
                                                            champion.getLocale(), champion.getName()})
            };
        } else if(data.getId() != null) {
            return new int[] {
                              Arrays.hashCode(new Object[] {Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(),
                                                            champion.getLocale(), champion.getId()})
            };
        } else if(data.getName() != null) {
            return new int[] {
                              Arrays.hashCode(new Object[] {Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(),
                                                            champion.getLocale(), champion.getName()})
            };
        } else {
            throw new IllegalArgumentException("Can't get key for champion without ID or name!");
        }
    }

    public static int forChampionQuery(final Map<String, Object> query) {
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        return Arrays.hashCode(new Object[] {Champion.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), (String)query.get("version"),
                                             (String)query.get("locale"),
                                             id == null ? name : id.intValue()});
    }
}
