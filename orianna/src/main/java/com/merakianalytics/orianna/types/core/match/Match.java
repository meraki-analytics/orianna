package com.merakianalytics.orianna.types.core.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.LazyList;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.GameType;
import com.merakianalytics.orianna.types.common.Lane;
import com.merakianalytics.orianna.types.common.Map;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Role;
import com.merakianalytics.orianna.types.common.RunePath;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.Items;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.data.match.MatchReference;

public class Match extends GhostObject<com.merakianalytics.orianna.types.data.match.Match> {
    public static class Builder {
        private final long id;
        private Platform platform;
        private String tournamentCode;

        private Builder(final long id) {
            this.id = id;
        }

        public Match get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("matchId", id);
            if(tournamentCode != null) {
                builder.put("tournamentCode", tournamentCode);
            }

            return Orianna.getSettings().getPipeline().get(Match.class, builder.build());
        }

        public Builder withPlatform(final Platform platform) {
            this.platform = platform;
            return this;
        }

        public Builder withRegion(final Region region) {
            platform = region.getPlatform();
            return this;
        }

        public Builder withTournamentCode(final String tournamentCode) {
            this.tournamentCode = tournamentCode;
            return this;
        }
    }

    public class Participant extends com.merakianalytics.orianna.types.core.match.Participant {
        private static final long serialVersionUID = -4802669460954679635L;

        private final Supplier<Champion> champion = Suppliers.memoize(new Supplier<Champion>() {
            @Override
            public Champion get() {
                Champion.Builder builder = Champion.withId(coreData.getChampionId()).withPlatform(Platform.withTag(coreData.getCurrentPlatform()));
                if(coreData.getVersion() != null) {
                    final String version = Versions.withPlatform(Platform.withTag(coreData.getCurrentPlatform())).get().truncate(coreData.getVersion());
                    builder = builder.withVersion(version);
                }
                return builder.get();
            }
        });

        private final Supplier<SearchableList<Item>> items = Suppliers.memoize(new Supplier<SearchableList<Item>>() {
            @Override
            public SearchableList<Item> get() {
                load(MATCH_LOAD_GROUP);
                final String version = Versions.withPlatform(Platform.withTag(coreData.getCurrentPlatform())).get().truncate(coreData.getVersion());
                return SearchableLists.unmodifiableFrom(
                    Items.withIds(coreData.getItems()).withPlatform(Platform.withTag(coreData.getCurrentPlatform())).withVersion(version).get());
            }
        });

        private final Supplier<Summoner> preTransferSummoner = Suppliers.memoize(new Supplier<Summoner>() {
            @Override
            public Summoner get() {
                load(MATCH_LOAD_GROUP);
                return Summoner.withAccountId(coreData.getAccountId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            }
        });

        private final Supplier<ProfileIcon> profileIcon = Suppliers.memoize(new Supplier<ProfileIcon>() {
            @Override
            public ProfileIcon get() {
                load(MATCH_LOAD_GROUP);
                final String version = Versions.withPlatform(Platform.withTag(coreData.getCurrentPlatform())).get().truncate(coreData.getVersion());
                return ProfileIcon.withId(coreData.getProfileIconId()).withPlatform(Platform.withTag(coreData.getCurrentPlatform())).withVersion(version)
                    .get();
            }
        });

        private final Supplier<SearchableList<RuneStats>> runeStats = Suppliers.memoize(new Supplier<SearchableList<RuneStats>>() {
            @Override
            public SearchableList<RuneStats> get() {
                load(MATCH_LOAD_GROUP);
                final List<RuneStats> runeStats = new ArrayList<>(coreData.getRuneStats().size());
                for(final com.merakianalytics.orianna.types.data.match.RuneStats stats : coreData.getRuneStats()) {
                    runeStats.add(new RuneStats(stats));
                }
                return SearchableLists.unmodifiableFrom(runeStats);
            }
        });

        private final Supplier<ParticipantStats> stats = Suppliers.memoize(new Supplier<ParticipantStats>() {
            @Override
            public ParticipantStats get() {
                load(MATCH_LOAD_GROUP);
                return new ParticipantStats(coreData.getStats());
            }
        });

        private final Supplier<Summoner> summoner = Suppliers.memoize(new Supplier<Summoner>() {
            @Override
            public Summoner get() {
                final Summoner summoner =
                    Summoner.withAccountId(coreData.getCurrentAccountId()).withPlatform(Platform.withTag(coreData.getCurrentPlatform())).get();
                if(summoner.getCoreData().getName() == null && coreData.getSummonerName() != null) {
                    summoner.getCoreData().setName(coreData.getSummonerName());
                }
                if(summoner.getCoreData().getId() == 0L && coreData.getSummonerId() != 0L) {
                    summoner.getCoreData().setId(coreData.getSummonerId());
                }
                return summoner;
            }
        });

        private final Supplier<SummonerSpell> summonerSpellD = Suppliers.memoize(new Supplier<SummonerSpell>() {
            @Override
            public SummonerSpell get() {
                load(MATCH_LOAD_GROUP);
                final String version = Versions.withPlatform(Platform.withTag(coreData.getCurrentPlatform())).get().truncate(coreData.getVersion());
                return SummonerSpell.withId(coreData.getSummonerSpellDId()).withPlatform(Platform.withTag(coreData.getCurrentPlatform())).withVersion(version)
                    .get();
            }
        });

        private final Supplier<SummonerSpell> summonerSpellF = Suppliers.memoize(new Supplier<SummonerSpell>() {
            @Override
            public SummonerSpell get() {
                load(MATCH_LOAD_GROUP);
                final String version = Versions.withPlatform(Platform.withTag(coreData.getCurrentPlatform())).get().truncate(coreData.getVersion());
                return SummonerSpell.withId(coreData.getSummonerSpellDId()).withPlatform(Platform.withTag(coreData.getCurrentPlatform())).withVersion(version)
                    .get();
            }
        });

        private final Supplier<com.merakianalytics.orianna.types.core.match.Team> team =
            Suppliers.memoize(new Supplier<com.merakianalytics.orianna.types.core.match.Team>() {
                @Override
                public com.merakianalytics.orianna.types.core.match.Team get() {
                    load(MATCH_LOAD_GROUP);
                    return coreData.getTeam() == Side.BLUE.getId() ? getBlueTeam() : getRedTeam();
                }
            });

        private final Supplier<ParticipantTimeline> timeline = Suppliers.memoize(new Supplier<ParticipantTimeline>() {
            @Override
            public ParticipantTimeline get() {
                load(MATCH_LOAD_GROUP);
                return new ParticipantTimeline(coreData.getTimeline());
            }
        });

        private Participant(final com.merakianalytics.orianna.types.data.match.Participant coreData) {
            super(coreData);
        }

        @Override
        @Searchable({Champion.class, String.class, int.class})
        public Champion getChampion() {
            return champion.get();
        }

        @Override
        public Tier getHighestTierInSeason() {
            load(MATCH_LOAD_GROUP);
            return Tier.valueOf(coreData.getHighestTierInSeason());
        }

        @Override
        @Searchable({Item.class, String.class, int.class})
        public SearchableList<Item> getItems() {
            return items.get();
        }

        @Override
        public Lane getLane() {
            if(coreData.getLane() == null) {
                load(MATCH_LOAD_GROUP);
            }
            return Lane.valueOf(coreData.getLane());
        }

        @Override
        public Summoner getPreTransferSummoner() {
            return preTransferSummoner.get();
        }

        @Override
        public RunePath getPrimaryRunePath() {
            load(MATCH_LOAD_GROUP);
            return RunePath.withId(coreData.getPrimaryRunePath());
        }

        @Override
        public ProfileIcon getProfileIcon() {
            return profileIcon.get();
        }

        @Override
        public Role getRole() {
            if(coreData.getRole() == null) {
                load(MATCH_LOAD_GROUP);
            }
            return Role.valueOf(coreData.getRole());
        }

        @Override
        @Searchable({int.class})
        public SearchableList<RuneStats> getRuneStats() {
            return runeStats.get();
        }

        @Override
        public RunePath getSecondaryRunePath() {
            load(MATCH_LOAD_GROUP);
            return RunePath.withId(coreData.getSecondaryRunePath());
        }

        @Override
        public ParticipantStats getStats() {
            return stats.get();
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
        public com.merakianalytics.orianna.types.core.match.Team getTeam() {
            return team.get();
        }

        @Override
        public ParticipantTimeline getTimeline() {
            return timeline.get();
        }
    }

    public class Team extends com.merakianalytics.orianna.types.core.match.Team {
        private static final long serialVersionUID = -5787154563875265507L;

        private final Supplier<SearchableList<Champion>> bans = Suppliers.memoize(new Supplier<SearchableList<Champion>>() {
            @Override
            public SearchableList<Champion> get() {
                final String version = Versions.withPlatform(Platform.withTag(coreData.getPlatform())).get().truncate(coreData.getVersion());
                return SearchableLists.unmodifiableFrom(
                    Champions.withIds(coreData.getBans()).withPlatform(Platform.withTag(coreData.getPlatform())).withVersion(version).get());
            }
        });

        private final Supplier<SearchableList<com.merakianalytics.orianna.types.core.match.Participant>> participants =
            Suppliers.memoize(new Supplier<SearchableList<com.merakianalytics.orianna.types.core.match.Participant>>() {
                @Override
                public SearchableList<com.merakianalytics.orianna.types.core.match.Participant> get() {
                    final List<com.merakianalytics.orianna.types.core.match.Participant> participants =
                        new ArrayList<>(Match.this.getParticipants().size() / 2);
                    for(final com.merakianalytics.orianna.types.core.match.Participant participant : Match.this.getParticipants()) {
                        if(participant.getCoreData().getTeam() == coreData.getTeamId()) {
                            participants.add(participant);
                        }
                    }
                    return SearchableLists.unmodifiableFrom(participants);
                }
            });

        private Team(final com.merakianalytics.orianna.types.data.match.Team coreData) {
            super(coreData);
        }

        @Override
        @Searchable({Champion.class, String.class, int.class})
        public SearchableList<Champion> getBans() {
            return bans.get();
        }

        @Override
        public int getBaronKills() {
            return coreData.getBaronKills();
        }

        @Override
        public int getDominionScore() {
            return coreData.getDominionScore();
        }

        @Override
        public int getDragonKills() {
            return coreData.getDragonKills();
        }

        @Override
        public int getInhibitorKills() {
            return coreData.getInhibitorKills();
        }

        @Override
        @Searchable({Summoner.class, Champion.class, Item.class, String.class, long.class, int.class})
        public SearchableList<com.merakianalytics.orianna.types.core.match.Participant> getParticipants() {
            return participants.get();
        }

        @Override
        public int getRiftHeraldKills() {
            return coreData.getRiftHeraldKills();
        }

        @Override
        public Side getSide() {
            return Side.withId(coreData.getTeamId());
        }

        @Override
        public int getTowerKills() {
            return coreData.getTowerKills();
        }

        @Override
        public int getVilemawKills() {
            return coreData.getVilemawKills();
        }

        @Override
        public boolean isFirstBaronKiller() {
            return coreData.isFirstBaronKiller();
        }

        @Override
        public boolean isFirstBloodKiller() {
            return coreData.isFirstBloodKiller();
        }

        @Override
        public boolean isFirstDragonKiller() {
            return coreData.isFirstDragonKiller();
        }

        @Override
        public boolean isFirstInhibitorKiller() {
            return coreData.isFirstInhibitorKiller();
        }

        @Override
        public boolean isFirstRiftHeraldKiller() {
            return coreData.isFirstRiftHeraldKiller();
        }

        @Override
        public boolean isFirstTowerKiller() {
            return coreData.isFirstTowerKiller();
        }

        @Override
        public boolean isWinner() {
            return coreData.isWinner();
        }
    }

    public static final String MATCH_LOAD_GROUP = "match";
    private static final long serialVersionUID = -9106364274355437548L;

    private static void replaceData(final com.merakianalytics.orianna.types.data.match.Participant from,
        final com.merakianalytics.orianna.types.data.match.Participant to) {
        to.setAccountId(from.getAccountId());
        to.setChampionId(from.getChampionId());
        to.setCurrentAccountId(from.getCurrentAccountId());
        to.setCurrentPlatform(from.getCurrentPlatform());
        to.setHighestTierInSeason(from.getHighestTierInSeason());
        to.setItems(from.getItems());
        to.setLane(from.getLane());
        to.setMatchHistoryURI(from.getMatchHistoryURI());
        to.setParticipantId(from.getParticipantId());
        to.setPlatform(from.getPlatform());
        to.setPrimaryRunePath(from.getPrimaryRunePath());
        to.setProfileIconId(from.getProfileIconId());
        to.setRole(from.getRole());
        to.setRuneStats(from.getRuneStats());
        to.setSecondaryRunePath(from.getSecondaryRunePath());
        to.setStats(from.getStats());
        to.setSummonerId(from.getSummonerId());
        to.setSummonerName(from.getSummonerName());
        to.setSummonerSpellDId(from.getSummonerSpellDId());
        to.setSummonerSpellFId(from.getSummonerSpellFId());
        to.setTeam(from.getTeam());
        to.setTimeline(from.getTimeline());
        to.setVersion(from.getVersion());
    }

    private static com.merakianalytics.orianna.types.data.match.Match toMatchData(final MatchReference reference) {
        final com.merakianalytics.orianna.types.data.match.Match coreData = new com.merakianalytics.orianna.types.data.match.Match();
        coreData.setQueue(reference.getQueue());
        coreData.setSeason(reference.getSeason());
        coreData.setCreationTime(reference.getCreationTime());
        coreData.setId(reference.getId());
        coreData.setPlatform(reference.getPlatform());

        final com.merakianalytics.orianna.types.data.match.Participant participant = new com.merakianalytics.orianna.types.data.match.Participant();
        participant.setCurrentAccountId(reference.getAccountId());
        participant.setChampionId(reference.getChampionId());
        participant.setLane(reference.getLane());
        participant.setRole(reference.getRole());

        final List<com.merakianalytics.orianna.types.data.match.Participant> participants = new ArrayList<>(1);
        participants.add(participant);
        coreData.setParticipants(participants);

        return coreData;
    }

    public static Builder withId(final long id) {
        return new Builder(id);
    }

    private final Supplier<com.merakianalytics.orianna.types.core.match.Team> blueTeam =
        Suppliers.memoize(new Supplier<com.merakianalytics.orianna.types.core.match.Team>() {
            @Override
            public com.merakianalytics.orianna.types.core.match.Team get() {
                load(MATCH_LOAD_GROUP);
                return new Team(coreData.getBlueTeam());
            }
        });

    private final boolean fromReference;

    private final Supplier<SearchableList<com.merakianalytics.orianna.types.core.match.Participant>> participants =
        Suppliers.memoize(new Supplier<SearchableList<com.merakianalytics.orianna.types.core.match.Participant>>() {
            @Override
            public SearchableList<com.merakianalytics.orianna.types.core.match.Participant> get() {
                if(!fromReference) {
                    load(MATCH_LOAD_GROUP);
                    final List<com.merakianalytics.orianna.types.core.match.Participant> participants = new ArrayList<>(coreData.getParticipants().size());
                    for(final com.merakianalytics.orianna.types.data.match.Participant participant : coreData.getParticipants()) {
                        participants.add(new Participant(participant));
                    }
                    return SearchableLists.unmodifiableFrom(participants);
                } else {
                    final CloseableIterator<com.merakianalytics.orianna.types.core.match.Participant> iterator =
                        new CloseableIterator<com.merakianalytics.orianna.types.core.match.Participant>() {
                            private ListIterator<com.merakianalytics.orianna.types.data.match.Participant> iterator = coreData.getParticipants().listIterator();

                            @Override
                            public void close() {}

                            @Override
                            public boolean hasNext() {
                                if(iterator.hasNext()) {
                                    return true;
                                }
                                load(MATCH_LOAD_GROUP);
                                iterator = coreData.getParticipants().listIterator(iterator.nextIndex());
                                return iterator.hasNext();
                            }

                            @Override
                            public Participant next() {
                                if(!hasNext()) {
                                    return null;
                                }
                                return new Participant(iterator.next());
                            }

                            @Override
                            public void remove() {
                                throw new UnsupportedOperationException();
                            }
                        };
                    return SearchableLists.unmodifiableFrom(new LazyList<>(iterator));
                }
            }
        });

    private final Supplier<com.merakianalytics.orianna.types.core.match.Team> redTeam =
        Suppliers.memoize(new Supplier<com.merakianalytics.orianna.types.core.match.Team>() {
            @Override
            public com.merakianalytics.orianna.types.core.match.Team get() {
                load(MATCH_LOAD_GROUP);
                return new Team(coreData.getRedTeam());
            }
        });

    private final Supplier<Timeline> timeline = Suppliers.memoize(new Supplier<Timeline>() {
        @Override
        public Timeline get() {
            return Timeline.withId(coreData.getId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public Match(final com.merakianalytics.orianna.types.data.match.Match coreData) {
        super(coreData, 1);
        fromReference = false;
    }

    public Match(final MatchReference reference) {
        super(toMatchData(reference), 1);
        fromReference = true;
    }

    public com.merakianalytics.orianna.types.core.match.Team getBlueTeam() {
        return blueTeam.get();
    }

    public DateTime getCreationTime() {
        if(coreData.getCreationTime() == null) {
            load(MATCH_LOAD_GROUP);
        }
        return coreData.getCreationTime();
    }

    public Duration getDuration() {
        load(MATCH_LOAD_GROUP);
        return coreData.getDuration();
    }

    public long getId() {
        return coreData.getId();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            MATCH_LOAD_GROUP
        });
    }

    public Map getMap() {
        load(MATCH_LOAD_GROUP);
        return Map.withId(coreData.getMap());
    }

    public GameMode getMode() {
        load(MATCH_LOAD_GROUP);
        return GameMode.valueOf(coreData.getMode());
    }

    @Searchable({Summoner.class, Champion.class, Item.class, String.class, long.class, int.class})
    public SearchableList<com.merakianalytics.orianna.types.core.match.Participant> getParticipants() {
        return participants.get();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Queue getQueue() {
        if(coreData.getQueue() == 0) {
            load(MATCH_LOAD_GROUP);
        }
        return Queue.withId(coreData.getQueue());
    }

    public com.merakianalytics.orianna.types.core.match.Team getRedTeam() {
        return redTeam.get();
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public Season getSeason() {
        if(coreData.getSeason() == 0) {
            load(MATCH_LOAD_GROUP);
        }
        return Season.withId(coreData.getSeason());
    }

    public Timeline getTimeline() {
        return timeline.get();
    }

    public String getTournamentCode() {
        return coreData.getTournamentCode();
    }

    public GameType getType() {
        load(MATCH_LOAD_GROUP);
        return GameType.valueOf(coreData.getType());
    }

    public String getVersion() {
        load(MATCH_LOAD_GROUP);
        return coreData.getVersion();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case MATCH_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                if(coreData.getId() != 0L) {
                    builder.put("matchId", coreData.getId());
                }
                if(coreData.getTournamentCode() != null) {
                    builder.put("tournamentCode", coreData.getTournamentCode());
                }

                if(!fromReference) {
                    coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.match.Match.class, builder.build());
                } else {
                    final com.merakianalytics.orianna.types.data.match.Match data =
                        Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.match.Match.class, builder.build());
                    final com.merakianalytics.orianna.types.data.match.Participant fromReference = coreData.getParticipants().get(0);
                    final Iterator<com.merakianalytics.orianna.types.data.match.Participant> iterator = data.getParticipants().iterator();
                    while(iterator.hasNext()) {
                        final com.merakianalytics.orianna.types.data.match.Participant participant = iterator.next();
                        if(participant.getCurrentAccountId() == fromReference.getCurrentAccountId()) {
                            replaceData(participant, fromReference);
                            iterator.remove();
                            break;
                        }
                    }
                    data.getParticipants().add(0, fromReference);
                    coreData = data;
                }
                break;
            default:
                break;
        }
    }
}
