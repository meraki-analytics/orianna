package com.merakianalytics.orianna.types.core.spectator;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.GameType;
import com.merakianalytics.orianna.types.common.Map;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class FeaturedMatch extends OriannaObject<com.merakianalytics.orianna.types.data.spectator.FeaturedMatch> {
    public class Participant extends com.merakianalytics.orianna.types.core.spectator.Participant {
        private static final long serialVersionUID = -9203624759697672200L;

        private final Supplier<Champion> champion = Suppliers.memoize(new Supplier<Champion>() {
            @Override
            public Champion get() {
                if(coreData.getChampionId() == 0) {
                    return null;
                }
                return Champion.withId(coreData.getChampionId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            }
        });

        private final Supplier<ProfileIcon> profileIcon = Suppliers.memoize(new Supplier<ProfileIcon>() {
            @Override
            public ProfileIcon get() {
                if(coreData.getProfileIconId() == 0) {
                    return null;
                }
                return ProfileIcon.withId(coreData.getProfileIconId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            }
        });

        private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
            @Override
            public Summoner get() {
                if(coreData.getSummonerName() == null) {
                    return null;
                }
                return Summoner.named(coreData.getSummonerName()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            }
        });

        private final Supplier<SummonerSpell> summonerSpellD = Suppliers.memoize(new Supplier<SummonerSpell>() {
            @Override
            public SummonerSpell get() {
                if(coreData.getSummonerSpellDId() == 0) {
                    return null;
                }
                return SummonerSpell.withId(coreData.getSummonerSpellDId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            }
        });

        private final Supplier<SummonerSpell> summonerSpellF = Suppliers.memoize(new Supplier<SummonerSpell>() {
            @Override
            public SummonerSpell get() {
                if(coreData.getSummonerSpellFId() == 0) {
                    return null;
                }
                return SummonerSpell.withId(coreData.getSummonerSpellFId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            }
        });

        public Participant(final com.merakianalytics.orianna.types.data.spectator.Participant coreData) {
            super(coreData);
        }

        @Override
        public Champion getChampion() {
            return champion.get();
        }

        @Override
        public ProfileIcon getProfileIcon() {
            return profileIcon.get();
        }

        @Override
        public Summoner getSummoner() {
            return summoner.get();
        }

        @Override
        public SummonerSpell getSummonerSpellD() {
            return summonerSpellD.get();
        }

        @Override
        public SummonerSpell getSummonerSpellF() {
            return summonerSpellF.get();
        }

        @Override
        public com.merakianalytics.orianna.types.core.spectator.FeaturedMatchTeam getTeam() {
            return coreData.getTeam() == Side.BLUE.getId() ? blueTeam.get() : redTeam.get();
        }

        @Override
        public boolean isBot() {
            return coreData.isBot();
        }
    }

    public class Team extends com.merakianalytics.orianna.types.core.spectator.FeaturedMatchTeam {
        private static final long serialVersionUID = -3789921485244822620L;

        private final Supplier<SearchableList<Champion>> bans = Suppliers.memoize(new Supplier<SearchableList<Champion>>() {
            @Override
            public SearchableList<Champion> get() {
                if(coreData.getBans() == null) {
                    return null;
                }
                return SearchableLists.unmodifiableFrom(Champions.withIds(coreData.getBans()).withPlatform(Platform.withTag(coreData.getPlatform())).get());
            }
        });

        private final Supplier<SearchableList<com.merakianalytics.orianna.types.core.spectator.Participant>> participants =
            Suppliers.memoize(new Supplier<SearchableList<com.merakianalytics.orianna.types.core.spectator.Participant>>() {
                @Override
                public SearchableList<com.merakianalytics.orianna.types.core.spectator.Participant> get() {
                    final List<com.merakianalytics.orianna.types.core.spectator.Participant> participants =
                        new ArrayList<>(FeaturedMatch.this.getParticipants().size() / 2);
                    for(final com.merakianalytics.orianna.types.core.spectator.Participant participant : FeaturedMatch.this.getParticipants()) {
                        if(participant.getCoreData().getTeam() == coreData.getSide()) {
                            participants.add(participant);
                        }
                    }
                    return SearchableLists.unmodifiableFrom(participants);
                }
            });

        public Team(final com.merakianalytics.orianna.types.data.spectator.Team coreData) {
            super(coreData);
        }

        @Override
        public SearchableList<Champion> getBans() {
            return bans.get();
        }

        @Override
        public SearchableList<com.merakianalytics.orianna.types.core.spectator.Participant> getParticipants() {
            return participants.get();
        }

        @Override
        public Side getSide() {
            return Side.withId(coreData.getSide());
        }
    }

    private static final long serialVersionUID = 1986854843022789219L;

    private final Supplier<com.merakianalytics.orianna.types.core.spectator.FeaturedMatchTeam> blueTeam =
        Suppliers.memoize(new Supplier<com.merakianalytics.orianna.types.core.spectator.FeaturedMatchTeam>() {
            @Override
            public com.merakianalytics.orianna.types.core.spectator.FeaturedMatchTeam get() {
                if(coreData.getBlueTeam() == null) {
                    return null;
                }
                return new Team(coreData.getBlueTeam());
            }
        });

    private final Supplier<SearchableList<com.merakianalytics.orianna.types.core.spectator.Participant>> participants =
        Suppliers.memoize(new Supplier<SearchableList<com.merakianalytics.orianna.types.core.spectator.Participant>>() {
            @Override
            public SearchableList<com.merakianalytics.orianna.types.core.spectator.Participant> get() {
                if(coreData.getPlayers() == null) {
                    return null;
                }
                final List<com.merakianalytics.orianna.types.core.spectator.Participant> players = new ArrayList<>(coreData.getPlayers().size());
                for(final com.merakianalytics.orianna.types.data.spectator.Participant player : coreData.getPlayers()) {
                    players.add(new Participant(player));
                }
                return SearchableLists.unmodifiableFrom(players);
            }
        });

    private final Supplier<com.merakianalytics.orianna.types.core.spectator.FeaturedMatchTeam> redTeam =
        Suppliers.memoize(new Supplier<com.merakianalytics.orianna.types.core.spectator.FeaturedMatchTeam>() {
            @Override
            public com.merakianalytics.orianna.types.core.spectator.FeaturedMatchTeam get() {
                if(coreData.getRedTeam() == null) {
                    return null;
                }
                return new Team(coreData.getRedTeam());
            }
        });

    public FeaturedMatch(final com.merakianalytics.orianna.types.data.spectator.FeaturedMatch coreData) {
        super(coreData);
    }

    public com.merakianalytics.orianna.types.core.spectator.FeaturedMatchTeam getBlueTeam() {
        return blueTeam.get();
    }

    public DateTime getCreationTime() {
        return coreData.getCreationTime();
    }

    public Duration getDuration() {
        return coreData.getDuration();
    }

    public long getId() {
        return coreData.getId();
    }

    public Map getMap() {
        return Map.withId(coreData.getMap());
    }

    public GameMode getMode() {
        return GameMode.valueOf(coreData.getMode());
    }

    public String getObserverEncryptionKey() {
        return coreData.getObserverEncryptionKey();
    }

    @Searchable({Summoner.class, Champion.class, String.class, long.class, int.class})
    public SearchableList<com.merakianalytics.orianna.types.core.spectator.Participant> getParticipants() {
        return participants.get();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Queue getQueue() {
        return Queue.withId(coreData.getQueue());
    }

    public com.merakianalytics.orianna.types.core.spectator.FeaturedMatchTeam getRedTeam() {
        return redTeam.get();
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public GameType getType() {
        return GameType.valueOf(coreData.getType());
    }
}
