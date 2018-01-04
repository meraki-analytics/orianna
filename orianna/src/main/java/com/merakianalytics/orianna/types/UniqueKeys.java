package com.merakianalytics.orianna.types;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.Items;
import com.merakianalytics.orianna.types.core.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Maps;
import com.merakianalytics.orianna.types.core.staticdata.Masteries;
import com.merakianalytics.orianna.types.core.staticdata.Mastery;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcons;
import com.merakianalytics.orianna.types.core.staticdata.Realm;
import com.merakianalytics.orianna.types.core.staticdata.Rune;
import com.merakianalytics.orianna.types.core.staticdata.Runes;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpells;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public abstract class UniqueKeys {
    public static int[] forChampion(final Champion champion) {
        final com.merakianalytics.orianna.types.data.staticdata.Champion data = champion.getCoreData().getChampion();
        if(data.getId() != 0 && data.getName() != null && data.getKey() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getKey(), data.getIncludedData()
                })
            };
        } else if(data.getId() != 0 && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
                })
            };
        } else if(data.getId() != 0 && data.getKey() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getKey(), data.getIncludedData()
                })
            };
        } else if(data.getName() != null && data.getKey() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getKey(), data.getIncludedData()
                })
            };
        } else if(data.getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
                })
            };
        } else if(data.getKey() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Champion.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getKey(), data.getIncludedData()
                })
            };
        } else {
            throw new IllegalArgumentException("Can't get key for Champion without ID, name, or key!");
        }
    }

    @SuppressWarnings("unchecked")
    public static int forChampionQuery(final Map<String, Object> query) {
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        final String key = (String)query.get("key");
        return Arrays.hashCode(new Object[] {
            Champion.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
            id == null ? name == null ? key : name : id.intValue(), (Set<String>)query.get("includedData")
        });
    }

    public static int[] forItem(final Item item) {
        final com.merakianalytics.orianna.types.data.staticdata.Item data = item.getCoreData();
        if(data.getId() != 0 && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Item.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Item.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
                })
            };
        } else if(data.getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Item.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Item.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
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
            Item.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
            id == null ? name : id.intValue(), (Set<String>)query.get("includedData")
        });
    }

    public static int forItems(final Items items) {
        final com.merakianalytics.orianna.types.data.staticdata.Items data = items.getCoreData();
        return Arrays.hashCode(new Object[] {
            Items.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getIncludedData()
        });
    }

    @SuppressWarnings("unchecked")
    public static int forItemsQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Items.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
            (Set<String>)query.get("includedData")
        });
    }

    public static int forLanguages(final Languages languages) {
        final com.merakianalytics.orianna.types.data.staticdata.Languages data = languages.getCoreData();
        return Arrays.hashCode(new Object[] {
            Languages.class.getCanonicalName(), data.getPlatform()
        });
    }

    public static int forLanguagesQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Languages.class.getCanonicalName(), ((Platform)query.get("platform")).getTag()
        });
    }

    public static int forLanguageStrings(final LanguageStrings languageStrings) {
        final com.merakianalytics.orianna.types.data.staticdata.LanguageStrings data = languageStrings.getCoreData();
        return Arrays.hashCode(new Object[] {
            LanguageStrings.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale()
        });
    }

    public static int forLanguageStringsQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            LanguageStrings.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale")
        });
    }

    @SuppressWarnings("unchecked")
    public static Iterator<Integer> forManyItemQuery(final Map<String, Object> query) {
        final Iterable<Integer> ids = (Iterable<Integer>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                return Arrays.hashCode(new Object[] {
                    Item.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
                    iterator.next(), (Set<String>)query.get("includedData")
                });
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static Iterator<Integer> forManyMasteryQuery(final Map<String, Object> query) {
        final Iterable<Integer> ids = (Iterable<Integer>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                return Arrays.hashCode(new Object[] {
                    Mastery.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
                    iterator.next(), (Set<String>)query.get("includedData")
                });
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static Iterator<Integer> forManyRuneQuery(final Map<String, Object> query) {
        final Iterable<Integer> ids = (Iterable<Integer>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                return Arrays.hashCode(new Object[] {
                    Rune.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
                    iterator.next(), (Set<String>)query.get("includedData")
                });
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static Iterator<Integer> forManySummonerQuery(final Map<String, Object> query) {
        final Iterable<Long> ids = (Iterable<Long>)query.get("ids");
        final Iterable<Long> accountIds = (Iterable<Long>)query.get("accountIds");
        final Iterable<String> names = (Iterable<String>)query.get("names");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(accountIds != null) {
            iterator = accountIds.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                return Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), iterator.next()
                });
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static Iterator<Integer> forManySummonerSpellQuery(final Map<String, Object> query) {
        final Iterable<Integer> ids = (Iterable<Integer>)query.get("ids");
        final Iterable<String> names = (Iterable<String>)query.get("names");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                return Arrays.hashCode(new Object[] {
                    SummonerSpell.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"),
                    (String)query.get("locale"),
                    iterator.next(), (Set<String>)query.get("includedData")
                });
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static int forMaps(final Maps maps) {
        final com.merakianalytics.orianna.types.data.staticdata.Maps data = maps.getCoreData();
        return Arrays.hashCode(new Object[] {
            Maps.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale()
        });
    }

    public static int forMapsQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Maps.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale")
        });
    }

    public static int forMasteries(final Masteries masteries) {
        final com.merakianalytics.orianna.types.data.staticdata.Masteries data = masteries.getCoreData();
        return Arrays.hashCode(new Object[] {
            Masteries.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getIncludedData()
        });
    }

    @SuppressWarnings("unchecked")
    public static int forMasteriesQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Masteries.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
            (Set<String>)query.get("includedData")
        });
    }

    public static int[] forMastery(final Mastery mastery) {
        final com.merakianalytics.orianna.types.data.staticdata.Mastery data = mastery.getCoreData();
        if(data.getId() != 0 && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Mastery.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Mastery.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
                })
            };
        } else if(data.getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Mastery.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Mastery.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
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
            Mastery.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
            id == null ? name : id.intValue(), (Set<String>)query.get("includedData")
        });
    }

    public static int forProfileIcons(final ProfileIcons profileIcons) {
        final com.merakianalytics.orianna.types.data.staticdata.ProfileIcons data = profileIcons.getCoreData();
        return Arrays.hashCode(new Object[] {
            ProfileIcons.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale()
        });
    }

    public static int forProfileIconsQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            ProfileIcons.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale")
        });
    }

    public static int forRealm(final Realm realm) {
        final com.merakianalytics.orianna.types.data.staticdata.Realm data = realm.getCoreData();
        return Arrays.hashCode(new Object[] {
            Realm.class.getCanonicalName(), data.getPlatform()
        });
    }

    public static int forRealmQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Realm.class.getCanonicalName(), ((Platform)query.get("platform")).getTag()
        });
    }

    public static int[] forRune(final Rune rune) {
        final com.merakianalytics.orianna.types.data.staticdata.Rune data = rune.getCoreData();
        if(data.getId() != 0 && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Rune.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    Rune.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
                })
            };
        } else if(data.getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Rune.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Rune.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
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
            Rune.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
            id == null ? name : id.intValue(), (Set<String>)query.get("includedData")
        });
    }

    public static int forRunes(final Runes runes) {
        final com.merakianalytics.orianna.types.data.staticdata.Runes data = runes.getCoreData();
        return Arrays.hashCode(new Object[] {
            Runes.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getIncludedData()
        });
    }

    @SuppressWarnings("unchecked")
    public static int forRunesQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Runes.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
            (Set<String>)query.get("includedData")
        });
    }

    public static int[] forSummoner(final Summoner summoner) {
        final com.merakianalytics.orianna.types.data.summoner.Summoner data = summoner.getCoreData();
        if(data.getId() != 0L && data.getAccountId() != 0L && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getId()
                }),
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getAccountId()
                }),
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getName()
                })
            };
        } else if(data.getId() != 0L && data.getAccountId() != 0L) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getId()
                }),
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getAccountId()
                }),
            };
        } else if(data.getId() != 0L && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getId()
                }),
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getName()
                })
            };
        } else if(data.getAccountId() != 0L && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getAccountId()
                }),
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getName()
                })
            };
        } else if(data.getId() != 0L) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getId()
                })
            };
        } else if(data.getAccountId() != 0L) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getAccountId()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    Summoner.class.getCanonicalName(), data.getPlatform(), data.getName()
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
            Summoner.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), id == null ? accountId == null ? name : accountId : id
        });
    }

    public static int[] forSummonerSpell(final SummonerSpell summonerSpell) {
        final com.merakianalytics.orianna.types.data.staticdata.SummonerSpell data = summonerSpell.getCoreData();
        if(data.getId() != 0 && data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    SummonerSpell.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                }),
                Arrays.hashCode(new Object[] {
                    SummonerSpell.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
                })
            };
        } else if(data.getId() != 0) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    SummonerSpell.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getId(), data.getIncludedData()
                })
            };
        } else if(data.getName() != null) {
            return new int[] {
                Arrays.hashCode(new Object[] {
                    SummonerSpell.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getName(), data.getIncludedData()
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
            SummonerSpell.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
            id == null ? name : id.intValue(), (Set<String>)query.get("includedData")
        });
    }

    public static int forSummonerSpells(final SummonerSpells spells) {
        final com.merakianalytics.orianna.types.data.staticdata.SummonerSpells data = spells.getCoreData();
        return Arrays.hashCode(new Object[] {
            SummonerSpells.class.getCanonicalName(), data.getPlatform(), data.getVersion(), data.getLocale(), data.getIncludedData()
        });
    }

    @SuppressWarnings("unchecked")
    public static int forSummonerSpellsQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            SummonerSpells.class.getCanonicalName(), ((Platform)query.get("platform")).getTag(), (String)query.get("version"), (String)query.get("locale"),
            (Set<String>)query.get("includedData")
        });
    }

    public static int forVersions(final Versions versions) {
        final com.merakianalytics.orianna.types.data.staticdata.Versions data = versions.getCoreData();
        return Arrays.hashCode(new Object[] {
            Versions.class.getCanonicalName(), data.getPlatform()
        });
    }

    public static int forVersionsQuery(final Map<String, Object> query) {
        return Arrays.hashCode(new Object[] {
            Versions.class.getCanonicalName(), ((Platform)query.get("platform")).getTag()
        });
    }
}
