package com.merakianalytics.orianna.types;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champion.ChampionData;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.core.staticdata.Languages;

public abstract class UniqueKeys {
    public static int[] forChampion(final Champion champion) {
        final ChampionData data = champion.getCoreData();
        if(data.getChampion().getId() != 0 && data.getChampion().getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(), champion.getLocale(), champion.getId(),
                    champion.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(), champion.getLocale(), champion.getName(),
                    champion.getIncludedData()
                })
            };
        } else if(data.getChampion().getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(), champion.getLocale(), champion.getId(),
                    champion.getIncludedData()
                })
            };
        } else if(data.getChampion().getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), champion.getPlatform().toString(), champion.getVersion(), champion.getLocale(), champion.getName(),
                    champion.getIncludedData()
                })
            };
        } else {
            throw new IllegalArgumentException("Can't get key for champion without ID or name!");
        }
    }

    @SuppressWarnings("unchecked")
    public static int forChampionQuery(final Map<String, Object> query) {
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        return Arrays.hashCode(new Object[] {
            Champion.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), (String)query.get("version"), (String)query.get("locale"),
            id == null ? name : id.intValue(), (Set<String>)query.get("includedData")
        });
    }

    public static int[] forItem(final Item item) {
        final com.merakianalytics.orianna.types.data.staticdata.Item data = item.getCoreData();
        if(data.getId() != 0 && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Item.class.getCanonicalName(), item.getPlatform().toString(), item.getVersion(), item.getLocale(), item.getId(), item.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Item.class.getCanonicalName(), item.getPlatform().toString(), item.getVersion(), item.getLocale(), item.getName(), item.getIncludedData()
                })
            };
        } else if(data.getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Item.class.getCanonicalName(), item.getPlatform().toString(), item.getVersion(), item.getLocale(), item.getId(), item.getIncludedData()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Item.class.getCanonicalName(), item.getPlatform().toString(), item.getVersion(), item.getLocale(), item.getName(), item.getIncludedData()
                })
            };
        } else {
            throw new IllegalArgumentException("Can't get key for champion without ID or name!");
        }
    }

    @SuppressWarnings("unchecked")
    public static int forItemQuery(final Map<String, Object> query) {
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        return Arrays.hashCode(new Object[] {
            Item.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), (String)query.get("version"), (String)query.get("locale"),
            id == null ? name : id.intValue(), (Set<String>)query.get("includedData")
        });
    }

    public static int forLanguages(final Languages languages) {
        return Arrays.hashCode(new Object[] {
            Languages.class.getCanonicalName(), languages.getPlatform().toString()
        });
    }

    public static int forLanguagesQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Languages.class.getCanonicalName(), ((Platform)query.get("platform")).toString()
        });
    }

    public static int forLanguageStrings(final LanguageStrings languageStrings) {
        return Arrays.hashCode(new Object[] {
            LanguageStrings.class.getCanonicalName(), languageStrings.getPlatform().toString(), languageStrings.getVersion(), languageStrings.getLocale()
        });
    }

    public static int forLanguageStringsQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            LanguageStrings.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), (String)query.get("version"), (String)query.get("locale")
        });
    }
}
