package com.merakianalytics.orianna.types.core.match;

import java.util.ArrayList;
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
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
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

    public static final String MATCH_LOAD_GROUP = "match";
    private static final long serialVersionUID = -9106364274355437548L;

    private static com.merakianalytics.orianna.types.data.match.Match toMatchData(final MatchReference reference) {
        final com.merakianalytics.orianna.types.data.match.Match coreData = new com.merakianalytics.orianna.types.data.match.Match();
        coreData.setQueue(reference.getQueue());
        coreData.setSeason(reference.getSeason());
        coreData.setCreationTime(reference.getCreationTime());
        coreData.setId(reference.getId());
        coreData.setPlatform(reference.getPlatform());
        return coreData;
    }

    public static Builder withId(final long id) {
        return new Builder(id);
    }

    private final Supplier<Team> blueTeam = Suppliers.memoize(new Supplier<Team>() {
        @Override
        public Team get() {
            load(MATCH_LOAD_GROUP);
            return new Team(coreData.getBlueTeam(), Match.this);
        }
    });

    private final Supplier<SearchableList<Participant>> participants = Suppliers.memoize(new Supplier<SearchableList<Participant>>() {
        @Override
        public SearchableList<Participant> get() {
            load(MATCH_LOAD_GROUP);
            final List<Participant> participants = new ArrayList<>(coreData.getParticipants().size());
            for(final com.merakianalytics.orianna.types.data.match.Participant participant : coreData.getParticipants()) {
                participants.add(new Participant(participant, Match.this));
            }
            return SearchableLists.unmodifiableFrom(participants);
        }
    });

    private final Supplier<Team> redTeam = Suppliers.memoize(new Supplier<Team>() {
        @Override
        public Team get() {
            load(MATCH_LOAD_GROUP);
            return new Team(coreData.getRedTeam(), Match.this);
        }
    });

    public Match(final com.merakianalytics.orianna.types.data.match.Match coreData) {
        super(coreData, 1);
    }

    public Match(final MatchReference reference) {
        super(toMatchData(reference), 1);
    }

    public Team getBlueTeam() {
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

    public Map getMap() {
        load(MATCH_LOAD_GROUP);
        return Map.withId(coreData.getMap());
    }

    public GameMode getMode() {
        load(MATCH_LOAD_GROUP);
        return GameMode.valueOf(coreData.getMode());
    }

    public SearchableList<Participant> getParticipants() {
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

    public Team getRedTeam() {
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
                coreData = Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.match.Match.class, builder.build());
                break;
            default:
                break;
        }
    }
}
