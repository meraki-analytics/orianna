package com.merakianalytics.orianna.types;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champion.ChampionData;

public abstract class UniqueKeys {
    public static int[] forChampion(final Champion champion) {
        final ChampionData data = champion.getCoreData();
        if(data.getChampion().getId() != 0 && data.getChampion().getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(),
                    champion.getLocale(), champion.getId(), champion.getIncludedData()}),
                Arrays.hashCode(new Object[] {Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(),
                    champion.getLocale(), champion.getName(), champion.getIncludedData()})
            };
        } else if(data.getChampion().getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(),
                    champion.getLocale(), champion.getId(), champion.getIncludedData()})
            };
        } else if(data.getChampion().getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(),
                    champion.getLocale(), champion.getName(), champion.getIncludedData()})
            };
        } else {
            throw new IllegalArgumentException("Can't get key for champion without ID or name!");
        }
    }

    @SuppressWarnings("unchecked")
    public static int forChampionQuery(final Map<String, Object> query) {
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        return Arrays.hashCode(new Object[] {Champion.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), (String)query.get("version"),
            (String)query.get("locale"),
            id == null ? name : id.intValue(), (Set<String>)query.get("includedData")});
    }
}
