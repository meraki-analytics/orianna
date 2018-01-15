package com.merakianalytics.orianna.types.core.match;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Lane;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Role;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class Participant extends OriannaObject<com.merakianalytics.orianna.types.data.match.Participant> {
    private static final long serialVersionUID = -4802669460954679635L;

    private final Supplier<Champion> champion = Suppliers.memoize(new Supplier<Champion>() {
        @Override
        public Champion get() {
            return Champion.withId(coreData.getChampionId()).withPlatform(Platform.withTag(coreData.getPlatform())).withVersion(coreData.getVersion()).get();
        }
    });

    private Match match;

    private final Supplier<Summoner> preTransferSummoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            return Summoner.withAccountId(coreData.getAccountId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    private final Supplier<ProfileIcon> profileIcon = Suppliers.memoize(new Supplier<ProfileIcon>() {
        @Override
        public ProfileIcon get() {
            return ProfileIcon.withId(coreData.getProfileIconId()).withPlatform(Platform.withTag(coreData.getPlatform())).withVersion(coreData.getVersion())
                .get();
        }
    });

    private final Supplier<ParticipantStats> stats = Suppliers.memoize(new Supplier<ParticipantStats>() {
        @Override
        public ParticipantStats get() {
            return new ParticipantStats(coreData.getStats());
        }
    });

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            final Summoner summoner =
                Summoner.withAccountId(coreData.getCurrentAccountId()).withPlatform(Platform.withTag(coreData.getCurrentPlatform())).get();
            if(summoner.getCoreData().getName() == null) {
                summoner.getCoreData().setName(coreData.getSummonerName());
            }
            if(summoner.getCoreData().getId() == 0L) {
                summoner.getCoreData().setId(coreData.getSummonerId());
            }
            return summoner;
        }
    });

    private final Supplier<SummonerSpell> summonerSpellD = Suppliers.memoize(new Supplier<SummonerSpell>() {
        @Override
        public SummonerSpell get() {
            return SummonerSpell.withId(coreData.getSummonerSpellDId()).withPlatform(Platform.withTag(coreData.getPlatform()))
                .withVersion(coreData.getVersion()).get();
        }
    });

    private final Supplier<SummonerSpell> summonerSpellF = Suppliers.memoize(new Supplier<SummonerSpell>() {
        @Override
        public SummonerSpell get() {
            return SummonerSpell.withId(coreData.getSummonerSpellDId()).withPlatform(Platform.withTag(coreData.getPlatform()))
                .withVersion(coreData.getVersion()).get();
        }
    });

    private final Supplier<Team> team = Suppliers.memoize(new Supplier<Team>() {
        @Override
        public Team get() {
            final Team team = coreData.getTeam() == Side.BLUE.getId() ? match.getBlueTeam() : match.getRedTeam();
            match = null;
            return team;
        }
    });

    private final Supplier<ParticipantTimeline> timeline = Suppliers.memoize(new Supplier<ParticipantTimeline>() {
        @Override
        public ParticipantTimeline get() {
            return new ParticipantTimeline(coreData.getTimeline());
        }
    });

    public Participant(final com.merakianalytics.orianna.types.data.match.Participant coreData, final Match match) {
        super(coreData);
        this.match = match;
    }

    @Searchable({Champion.class, String.class, int.class})
    public Champion getChampion() {
        return champion.get();
    }

    public Tier getHighestTierInSeason() {
        return Tier.valueOf(coreData.getHighestTierInSeason());
    }

    public Lane getLane() {
        return Lane.valueOf(coreData.getLane());
    }

    @Searchable({Summoner.class, String.class, long.class})
    public Summoner getPreTransferSummoner() {
        return preTransferSummoner.get();
    }

    public ProfileIcon getProfileIcon() {
        return profileIcon.get();
    }

    public Role getRole() {
        return Role.valueOf(coreData.getRole());
    }

    @Searchable({Item.class, String.class, int.class})
    public ParticipantStats getStats() {
        return stats.get();
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

    public Team getTeam() {
        return team.get();
    }

    public ParticipantTimeline getTimeline() {
        return timeline.get();
    }
}
