package com.merakianalytics.orianna.types.core.spectator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.GameType;
import com.merakianalytics.orianna.types.common.Map;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class CurrentMatch extends GhostObject<com.merakianalytics.orianna.types.data.spectator.CurrentMatch> {
    public static class Builder {
        private final Summoner summoner;

        private Builder(final Summoner summoner) {
            this.summoner = summoner;
        }

        public CurrentMatch get() {
            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", summoner.getPlatform()).put("summonerId", summoner.getId());

            return Orianna.getSettings().getPipeline().get(CurrentMatch.class, builder.build());
        }
    }

    public class Player extends com.merakianalytics.orianna.types.core.spectator.Player {
        private static final long serialVersionUID = 4959827158399514603L;

        private final Supplier<Champion> champion = Suppliers.memoize(new Supplier<Champion>() {
            @Override
            public Champion get() {
                if(coreData.getChampionId() == 0) {
                    return null;
                }
                return Champion.withId(coreData.getChampionId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            }
        });

        private final Supplier<List<GameCustomizationObject>> customizationObjects = Suppliers.memoize(new Supplier<List<GameCustomizationObject>>() {
            @Override
            public List<GameCustomizationObject> get() {
                if(coreData.getCustomizationObjects() == null) {
                    return null;
                }
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
                if(coreData.getProfileIconId() == -1) {
                    return null;
                }
                return ProfileIcon.withId(coreData.getProfileIconId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            }
        });

        private final Supplier<Runes> runes = Suppliers.memoize(new Supplier<Runes>() {
            @Override
            public Runes get() {
                if(coreData.getRunes() == null) {
                    return null;
                }
                return new Runes(coreData.getRunes());
            }
        });

        private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
            @Override
            public Summoner get() {
                if(coreData.getSummonerId() == null) {
                    return null;
                }
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

        public Player(final com.merakianalytics.orianna.types.data.spectator.Player coreData) {
            super(coreData);
        }

        @Override
        @Searchable({Champion.class, String.class, int.class})
        public Champion getChampion() {
            return champion.get();
        }

        @Override
        public List<GameCustomizationObject> getCustomizationObjects() {
            return customizationObjects.get();
        }

        @Override
        public ProfileIcon getProfileIcon() {
            return profileIcon.get();
        }

        @Override
        public Runes getRunes() {
            return runes.get();
        }

        @Override
        @Searchable({Summoner.class, String.class, long.class})
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
        public com.merakianalytics.orianna.types.core.spectator.CurrentMatchTeam getTeam() {
            return coreData.getTeam() == Side.BLUE.getId() ? blueTeam.get() : redTeam.get();
        }

        @Override
        public boolean isBot() {
            return coreData.isBot();
        }
    }

    public class Team extends com.merakianalytics.orianna.types.core.spectator.CurrentMatchTeam {
        private static final long serialVersionUID = 6283190060704502909L;

        private final Supplier<SearchableList<Champion>> bans = Suppliers.memoize(new Supplier<SearchableList<Champion>>() {
            @Override
            public SearchableList<Champion> get() {
                if(coreData.getBans() == null) {
                    return null;
                }
                return SearchableLists.unmodifiableFrom(Champions.withIds(coreData.getBans()).withPlatform(Platform.withTag(coreData.getPlatform())).get());
            }
        });

        private final Supplier<SearchableList<com.merakianalytics.orianna.types.core.spectator.Player>> participants =
            Suppliers.memoize(new Supplier<SearchableList<com.merakianalytics.orianna.types.core.spectator.Player>>() {
                @Override
                public SearchableList<com.merakianalytics.orianna.types.core.spectator.Player> get() {
                    final List<com.merakianalytics.orianna.types.core.spectator.Player> participants =
                        new ArrayList<>(CurrentMatch.this.getParticipants().size() / 2);
                    for(final com.merakianalytics.orianna.types.core.spectator.Player participant : CurrentMatch.this.getParticipants()) {
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
        public SearchableList<com.merakianalytics.orianna.types.core.spectator.Player> getParticipants() {
            return participants.get();
        }

        @Override
        public Side getSide() {
            return Side.withId(coreData.getSide());
        }
    }

    public static final String CURRENT_GAME_LOAD_GROUP = "current-game";
    private static final long serialVersionUID = 2151849959267002960L;

    public static CurrentMatch.Builder forSummoner(final Summoner summoner) {
        return new Builder(summoner);
    }

    private final Supplier<com.merakianalytics.orianna.types.core.spectator.CurrentMatchTeam> blueTeam =
        Suppliers.memoize(new Supplier<com.merakianalytics.orianna.types.core.spectator.CurrentMatchTeam>() {
            @Override
            public com.merakianalytics.orianna.types.core.spectator.CurrentMatchTeam get() {
                load(CURRENT_GAME_LOAD_GROUP);
                if(coreData.getBlueTeam() == null) {
                    return null;
                }
                return new Team(coreData.getBlueTeam());
            }
        });

    private final Supplier<SearchableList<com.merakianalytics.orianna.types.core.spectator.Player>> participants =
        Suppliers.memoize(new Supplier<SearchableList<com.merakianalytics.orianna.types.core.spectator.Player>>() {
            @Override
            public SearchableList<com.merakianalytics.orianna.types.core.spectator.Player> get() {
                load(CURRENT_GAME_LOAD_GROUP);
                if(coreData.getPlayers() == null) {
                    return null;
                }
                final List<com.merakianalytics.orianna.types.core.spectator.Player> players = new ArrayList<>(coreData.getPlayers().size());
                for(final com.merakianalytics.orianna.types.data.spectator.Player player : coreData.getPlayers()) {
                    players.add(new Player(player));
                }
                return SearchableLists.unmodifiableFrom(players);
            }
        });

    private final Supplier<com.merakianalytics.orianna.types.core.spectator.CurrentMatchTeam> redTeam =
        Suppliers.memoize(new Supplier<com.merakianalytics.orianna.types.core.spectator.CurrentMatchTeam>() {
            @Override
            public com.merakianalytics.orianna.types.core.spectator.CurrentMatchTeam get() {
                load(CURRENT_GAME_LOAD_GROUP);
                if(coreData.getRedTeam() == null) {
                    return null;
                }
                return new Team(coreData.getRedTeam());
            }
        });

    private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
        @Override
        public Summoner get() {
            if(coreData.getSummonerId() == null) {
                return null;
            }
            return Summoner.withId(coreData.getSummonerId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public CurrentMatch(final com.merakianalytics.orianna.types.data.spectator.CurrentMatch coreData) {
        super(coreData, 1);
    }

    @Override
    public boolean exists() {
        if(coreData.getId() == 0L) {
            load(CURRENT_GAME_LOAD_GROUP);
        }
        return coreData.getId() != 0L;
    }

    public com.merakianalytics.orianna.types.core.spectator.CurrentMatchTeam getBlueTeam() {
        return blueTeam.get();
    }

    public DateTime getCreationTime() {
        if(coreData.getCreationTime() == null) {
            load(CURRENT_GAME_LOAD_GROUP);
        }
        return coreData.getCreationTime();
    }

    public Duration getDuration() {
        if(coreData.getDuration() == null) {
            load(CURRENT_GAME_LOAD_GROUP);
        }
        return coreData.getDuration();
    }

    public long getId() {
        if(coreData.getId() == 0L) {
            load(CURRENT_GAME_LOAD_GROUP);
        }
        return coreData.getId();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            CURRENT_GAME_LOAD_GROUP
        });
    }

    public Map getMap() {
        if(coreData.getMap() == 0) {
            load(CURRENT_GAME_LOAD_GROUP);
        }
        return Map.withId(coreData.getMap());
    }

    public GameMode getMode() {
        if(coreData.getMode() == null) {
            load(CURRENT_GAME_LOAD_GROUP);
        }
        return GameMode.valueOf(coreData.getMode());
    }

    public String getObserverEncryptionKey() {
        if(coreData.getObserverEncryptionKey() == null) {
            load(CURRENT_GAME_LOAD_GROUP);
        }
        return coreData.getObserverEncryptionKey();
    }

    @Searchable({Summoner.class, Champion.class, String.class, long.class, int.class})
    public SearchableList<com.merakianalytics.orianna.types.core.spectator.Player> getParticipants() {
        return participants.get();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Queue getQueue() {
        if(coreData.getQueue() == 0) {
            load(CURRENT_GAME_LOAD_GROUP);
        }
        return Queue.withId(coreData.getQueue());
    }

    public com.merakianalytics.orianna.types.core.spectator.CurrentMatchTeam getRedTeam() {
        return redTeam.get();
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    @Searchable({Summoner.class, String.class, long.class})
    public Summoner getSummoner() {
        return summoner.get();
    }

    public GameType getType() {
        if(coreData.getType() == null) {
            load(CURRENT_GAME_LOAD_GROUP);
        }
        return GameType.valueOf(coreData.getType());
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case CURRENT_GAME_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                if(coreData.getSummonerId() != null) {
                    builder.put("summonerId", coreData.getSummonerId());
                }
                final com.merakianalytics.orianna.types.data.spectator.CurrentMatch data =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.spectator.CurrentMatch.class, builder.build());
                if(data != null) {
                    coreData = data;
                }
                break;
            default:
                break;
        }
    }
}
