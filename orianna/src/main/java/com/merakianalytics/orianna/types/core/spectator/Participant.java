package com.merakianalytics.orianna.types.core.spectator;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class Participant extends OriannaObject<com.merakianalytics.orianna.types.data.spectator.Participant> {
    private static final long serialVersionUID = -919093957040517072L;

    private final Supplier<Champion> champion = Suppliers.memoize(new Supplier<Champion>() {
        @Override
        public Champion get() {
            return Champion.withId(coreData.getChampionId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    private final Supplier<ProfileIcon> profileIcon = Suppliers.memoize(new Supplier<ProfileIcon>() {
        @Override
        public ProfileIcon get() {
            return ProfileIcon.withId(coreData.getProfileIconId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            return Summoner.named(coreData.getSummonerName()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
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

    public Participant(final com.merakianalytics.orianna.types.data.spectator.Participant coreData) {
        super(coreData);
    }

    public Champion getChampion() {
        return champion.get();
    }

    public ProfileIcon getProfileIcon() {
        return profileIcon.get();
    }

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
