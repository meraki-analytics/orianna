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
import com.merakianalytics.orianna.types.core.staticdata.Maps;
import com.merakianalytics.orianna.types.core.staticdata.Mastery;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcons;
import com.merakianalytics.orianna.types.core.staticdata.Realm;
import com.merakianalytics.orianna.types.core.staticdata.Rune;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

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
            throw new IllegalArgumentException("Can't get key for Champion without ID or name!");
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
            throw new IllegalArgumentException("Can't get key for Item without ID or name!");
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

    public static int forMaps(final Maps maps) {
        return Arrays.hashCode(new Object[] {
            Maps.class.getCanonicalName(), maps.getPlatform().toString(), maps.getVersion(), maps.getLocale()
        });
    }

    public static int forMapsQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Maps.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), (String)query.get("version"), (String)query.get("locale")
        });
    }

    public static int[] forMastery(final Mastery mastery) {
        final com.merakianalytics.orianna.types.data.staticdata.Mastery data = mastery.getCoreData();
        if(data.getId() != 0 && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Mastery.class.getCanonicalName(), mastery.getPlatform().toString(), mastery.getVersion(), mastery.getLocale(), mastery.getId(),
                    mastery.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Mastery.class.getCanonicalName(), mastery.getPlatform().toString(), mastery.getVersion(), mastery.getLocale(), mastery.getName(),
                    mastery.getIncludedData()
                })
            };
        } else if(data.getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Mastery.class.getCanonicalName(), mastery.getPlatform().toString(), mastery.getVersion(), mastery.getLocale(), mastery.getId(),
                    mastery.getIncludedData()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Mastery.class.getCanonicalName(), mastery.getPlatform().toString(), mastery.getVersion(), mastery.getLocale(), mastery.getName(),
                    mastery.getIncludedData()
                })
            };
        } else {
            throw new IllegalArgumentException("Can't get key for Mastery without ID or name!");
        }
    }

    @SuppressWarnings("unchecked")
    public static int forMasteryQuery(final Map<String, Object> query) {
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        return Arrays.hashCode(new Object[] {
            Mastery.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), (String)query.get("version"), (String)query.get("locale"),
            id == null ? name : id.intValue(), (Set<String>)query.get("includedData")
        });
    }

    public static int forProfileIcons(final ProfileIcons profileIcons) {
        return Arrays.hashCode(new Object[] {
            ProfileIcons.class.getCanonicalName(), profileIcons.getPlatform().toString(), profileIcons.getVersion(), profileIcons.getLocale()
        });
    }

    public static int forProfileIconsQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            ProfileIcons.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), (String)query.get("version"), (String)query.get("locale")
        });
    }

    public static int forRealm(final Realm realm) {
        return Arrays.hashCode(new Object[] {
            Realm.class.getCanonicalName(), realm.getPlatform().toString()
        });
    }

    public static int forRealmQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Realm.class.getCanonicalName(), ((Platform)query.get("platform")).toString()
        });
    }

    public static int[] forRune(final Rune rune) {
        final com.merakianalytics.orianna.types.data.staticdata.Rune data = rune.getCoreData();
        if(data.getId() != 0 && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Rune.class.getCanonicalName(), rune.getPlatform().toString(), rune.getVersion(), rune.getLocale(), rune.getId(), rune.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Rune.class.getCanonicalName(), rune.getPlatform().toString(), rune.getVersion(), rune.getLocale(), rune.getName(), rune.getIncludedData()
                })
            };
        } else if(data.getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Rune.class.getCanonicalName(), rune.getPlatform().toString(), rune.getVersion(), rune.getLocale(), rune.getId(), rune.getIncludedData()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Rune.class.getCanonicalName(), rune.getPlatform().toString(), rune.getVersion(), rune.getLocale(), rune.getName(), rune.getIncludedData()
                })
            };
        } else {
            throw new IllegalArgumentException("Can't get key for Item without ID or name!");
        }
    }

    @SuppressWarnings("unchecked")
    public static int forRuneQuery(final Map<String, Object> query) {
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        return Arrays.hashCode(new Object[] {
            Rune.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), (String)query.get("version"), (String)query.get("locale"),
            id == null ? name : id.intValue(), (Set<String>)query.get("includedData")
        });
    }

    public static int[] forSummoner(final Summoner summoner) {
        final com.merakianalytics.orianna.types.data.summoner.Summoner data = summoner.getCoreData();
        if(data.getId() != 0L && data.getAccountId() != 0L && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), summoner.getPlatform().toString(), summoner.getId()
                }),
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), summoner.getPlatform().toString(), summoner.getAccountId()
                }),
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), summoner.getPlatform().toString(), summoner.getName()
                })
            };
        } else if(data.getId() != 0L && data.getAccountId() != 0L) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), summoner.getPlatform().toString(), summoner.getId()
                }),
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), summoner.getPlatform().toString(), summoner.getAccountId()
                }),
            };
        } else if(data.getId() != 0L && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), summoner.getPlatform().toString(), summoner.getId()
                }),
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), summoner.getPlatform().toString(), summoner.getName()
                })
            };
        } else if(data.getId() != 0L) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), summoner.getPlatform().toString(), summoner.getId()
                })
            };
        } else if(data.getAccountId() != 0L) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), summoner.getPlatform().toString(), summoner.getAccountId()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), summoner.getPlatform().toString(), summoner.getName()
                })
            };
        } else {
            throw new IllegalArgumentException("Can't get key for Summoner without ID, account ID, or name!");
        }
    }

    public static int forSummonerQuery(final Map<String, Object> query) {
        final Long id = (Long)query.get("id");
        final Long accountId = (Long)query.get("accountId");
        final String name = (String)query.get("name");
        return Arrays.hashCode(new Object[] {
            Summoner.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), id == null ? accountId == null ? name : accountId : id
        });
    }

    public static int[] forSummonerSpell(final SummonerSpell summonerSpell) {
        final com.merakianalytics.orianna.types.data.staticdata.SummonerSpell data = summonerSpell.getCoreData();
        if(data.getId() != 0 && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    SummonerSpell.class.getCanonicalName(), summonerSpell.getPlatform().toString(), summonerSpell.getVersion(), summonerSpell.getLocale(),
                    summonerSpell.getId(), summonerSpell.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    SummonerSpell.class.getCanonicalName(), summonerSpell.getPlatform().toString(), summonerSpell.getVersion(), summonerSpell.getLocale(),
                    summonerSpell.getName(), summonerSpell.getIncludedData()
                })
            };
        } else if(data.getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    SummonerSpell.class.getCanonicalName(), summonerSpell.getPlatform().toString(), summonerSpell.getVersion(), summonerSpell.getLocale(),
                    summonerSpell.getId(), summonerSpell.getIncludedData()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    SummonerSpell.class.getCanonicalName(), summonerSpell.getPlatform().toString(), summonerSpell.getVersion(), summonerSpell.getLocale(),
                    summonerSpell.getName(), summonerSpell.getIncludedData()
                })
            };
        } else {
            throw new IllegalArgumentException("Can't get key for Item without ID or name!");
        }
    }

    @SuppressWarnings("unchecked")
    public static int forSummonerSpellQuery(final Map<String, Object> query) {
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        return Arrays.hashCode(new Object[] {
            SummonerSpell.class.getCanonicalName(), ((Platform)query.get("platform")).toString(), (String)query.get("version"), (String)query.get("locale"),
            id == null ? name : id.intValue(), (Set<String>)query.get("includedData")
        });
    }

    public static int forVersions(final Versions versions) {
        return Arrays.hashCode(new Object[] {
            Versions.class.getCanonicalName(), versions.getPlatform().toString()
        });
    }

    public static int forVersionsQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Versions.class.getCanonicalName(), ((Platform)query.get("platform")).toString()
        });
    }

}
