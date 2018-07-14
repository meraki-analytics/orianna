package com.merakianalytics.orianna.types.core.summoner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteryScore;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeaguePosition;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.league.Leagues;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.thirdpartycode.VerificationString;

public class Summoner extends GhostObject<com.merakianalytics.orianna.types.data.summoner.Summoner> {
    public static class Builder {
        private Long id, accountId;
        private String name;
        private Platform platform;

        private Builder(final long id, final boolean isSummonerId) {
            if(isSummonerId) {
                this.id = id;
            } else {
                accountId = id;
            }
        }

        private Builder(final String name) {
            this.name = name;
        }

        public Summoner get() {
            if(name == null && id == null && accountId == null) {
                throw new IllegalStateException("Must set an ID, account ID, or name for the Summoner!");
            }

            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform);

            if(id != null) {
                builder.put("id", id);
            } else if(accountId != null) {
                builder.put("accountId", accountId);
            } else {
                builder.put("name", name);
            }

            return Orianna.getSettings().getPipeline().get(Summoner.class, builder.build());
        }

        public Builder withPlatform(final Platform platform) {
            this.platform = platform;
            return this;
        }

        public Builder withRegion(final Region region) {
            platform = region.getPlatform();
            return this;
        }
    }

    private static final long serialVersionUID = 4280855397190856618L;
    public static final String SUMMONER_LOAD_GROUP = "summoner";

    public static Builder named(final String name) {
        return new Builder(name);
    }

    public static Builder withAccountId(final long accountId) {
        return new Builder(accountId, false);
    }

    public static Builder withId(final long id) {
        return new Builder(id, true);
    }

    private final Supplier<ProfileIcon> profileIcon = Suppliers.memoize(new Supplier<ProfileIcon>() {
        @Override
        public ProfileIcon get() {
            load(SUMMONER_LOAD_GROUP);
            if(coreData.getProfileIconId() == -1) {
                return null;
            }
            return ProfileIcon.withId(coreData.getProfileIconId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
        }
    });

    public Summoner(final com.merakianalytics.orianna.types.data.summoner.Summoner coreData) {
        super(coreData, 1);
    }

    @Searchable(long.class)
    public long getAccountId() {
        if(coreData.getAccountId() == 0L) {
            load(SUMMONER_LOAD_GROUP);
        }
        return coreData.getAccountId();
    }

    public ChampionMasteries getChampionMasteries() {
        return ChampionMasteries.forSummoner(this).get();
    }

    public SearchableList<ChampionMastery> getChampionMasteries(final Champion... champions) {
        return ChampionMasteries.forSummoner(this).withChampions(champions).get();
    }

    public SearchableList<ChampionMastery> getChampionMasteries(final Iterable<Champion> champions) {
        return ChampionMasteries.forSummoner(this).withChampions(champions).get();
    }

    public ChampionMastery getChampionMastery(final Champion champion) {
        return ChampionMastery.forSummoner(this).withChampion(champion).get();
    }

    public ChampionMasteryScore getChampionMasteryScore() {
        return ChampionMasteryScore.forSummoner(this).get();
    }

    public CurrentMatch getCurrentMatch() {
        return CurrentMatch.forSummoner(this).get();
    }

    public Tier getHighestTier(final Season season) {
        final MatchHistory one = MatchHistory.forSummoner(this).withSeasons(season).withStartIndex(0).withEndIndex(1).get();
        if(!one.isEmpty()) {
            final Match match = one.get(0);
            final Participant summoner = match.getParticipants().get(0);
            return summoner.getHighestTierInSeason();
        }
        return null;
    }

    @Searchable(long.class)
    public long getId() {
        if(coreData.getId() == 0L) {
            load(SUMMONER_LOAD_GROUP);
        }
        return coreData.getId();
    }

    public League getLeague(final Queue queue) {
        if(!Queue.RANKED.contains(queue)) {
            throw new IllegalArgumentException("Can only get league for ranked queues!");
        }

        final LeaguePositions positions = LeaguePositions.forSummoner(this).get();
        for(final LeaguePosition position : positions) {
            if(queue == position.getQueue()) {
                return League.withId(position.getLeagueId()).withPlatform(Platform.withTag(coreData.getPlatform())).get();
            }
        }
        return null;
    }

    public LeaguePosition getLeaguePosition(final Queue queue) {
        if(!Queue.RANKED.contains(queue)) {
            throw new IllegalArgumentException("Can only get league positions for ranked queues!");
        }

        final LeaguePositions positions = LeaguePositions.forSummoner(this).get();
        for(final LeaguePosition position : positions) {
            if(queue == position.getQueue()) {
                return position;
            }
        }
        return null;
    }

    public LeaguePositions getLeaguePositions() {
        return LeaguePositions.forSummoner(this).get();
    }

    public SearchableList<League> getLeagues() {
        final LeaguePositions positions = LeaguePositions.forSummoner(this).get();
        final List<String> ids = new ArrayList<>(positions.size());
        for(final LeaguePosition position : positions) {
            ids.add(position.getLeagueId());
        }

        return Leagues.withIds(ids).withPlatform(Platform.withTag(coreData.getPlatform())).get();
    }

    public int getLevel() {
        load(SUMMONER_LOAD_GROUP);
        return coreData.getLevel();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            SUMMONER_LOAD_GROUP
        });
    }

    @Searchable(String.class)
    public String getName() {
        if(coreData.getName() == null) {
            load(SUMMONER_LOAD_GROUP);
        }
        return coreData.getName();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public ProfileIcon getProfileIcon() {
        return profileIcon.get();
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public DateTime getUpdated() {
        load(SUMMONER_LOAD_GROUP);
        return coreData.getUpdated();
    }

    public VerificationString getVerificationString() {
        return VerificationString.forSummoner(this).get();
    }

    public boolean isInGame() {
        return CurrentMatch.forSummoner(this).get().exists();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case SUMMONER_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getId() != 0) {
                    builder.put("id", coreData.getId());
                }
                if(coreData.getAccountId() != 0) {
                    builder.put("accountId", coreData.getAccountId());
                }
                if(coreData.getName() != null) {
                    builder.put("name", coreData.getName());
                }
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.summoner.Summoner.class, builder.build());
                break;
            default:
                break;
        }
    }

    public MatchHistory.Builder matchHistory() {
        return MatchHistory.forSummoner(this);
    }
}
