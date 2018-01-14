package com.merakianalytics.orianna.types.core.spectator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class Player extends OriannaObject<com.merakianalytics.orianna.types.data.spectator.Player> {
    private static final long serialVersionUID = 4241892066509529815L;

    private final Supplier<Champion> champion = Suppliers.memoize(new Supplier<Champion>() {
        @Override
        public Champion get() {
            return Champion.withId(coreData.getChampionId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    private final Supplier<List<GameCustomizationObject>> customizationObjects = Suppliers.memoize(new Supplier<List<GameCustomizationObject>>() {
        @Override
        public List<GameCustomizationObject> get() {
            final List<GameCustomizationObject> objects = new ArrayList<>(coreData.getCustomizationObjects().size());
            for(final com.merakianalytics.orianna.types.data.spectator.GameCustomizationObject object : coreData.getCustomizationObjects()) {
                objects.add(new GameCustomizationObject(object));
            }
            return Collections.unmodifiableList(objects);
        }
    });

    private final Supplier<ProfileIcon> profileIcon = Suppliers.memoize(new Supplier<ProfileIcon>() {
        @Override
        public ProfileIcon get() {
            return ProfileIcon.withId(coreData.getProfileIconId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    private final Supplier<Runes> runes = Suppliers.memoize(new Supplier<Runes>() {
        @Override
        public Runes get() {
            return new Runes(coreData.getRunes());
        }
    });

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            final Summoner summoner = Summoner.withId(coreData.getSummonerId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            if(summoner.getCoreData().getName() == null) {
                summoner.getCoreData().setName(coreData.getSummonerName());
            }
            return summoner;
        }
    });

    private final Supplier<SummonerSpell> summonerSpellD = Suppliers.memoize(new Supplier<SummonerSpell>() {
        @Override
        public SummonerSpell get() {
            return SummonerSpell.withId(coreData.getSummonerSpellDId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    private final Supplier<SummonerSpell> summonerSpellF = Suppliers.memoize(new Supplier<SummonerSpell>() {
        @Override
        public SummonerSpell get() {
            return SummonerSpell.withId(coreData.getSummonerSpellDId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public Player(final com.merakianalytics.orianna.types.data.spectator.Player coreData) {
        super(coreData);
    }

    @Searchable({Champion.class, String.class, int.class})
    public Champion getChampion() {
        return champion.get();
    }

    public List<GameCustomizationObject> getCustomizationObjects() {
        return customizationObjects.get();
    }

    public ProfileIcon getProfileIcon() {
        return profileIcon.get();
    }

    public Runes getRunes() {
        return runes.get();
    }

    @Searchable({Summoner.class, String.class, long.class})
    public Summoner getSummoner() {
        return summoner.get();
    }

    public SummonerSpell getSummonerSpellD() {
        return summonerSpellD.get();
    }

    public SummonerSpell getSummonerSpellF() {
        return summonerSpellF.get();
    }

    public Side getTeam() {
        return Side.withId(coreData.getTeam());
    }

    public boolean isBot() {
        return coreData.isBot();
    }
}
