package com.merakianalytics.orianna.types.core.match;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.GhostObject;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

public class TournamentMatches extends GhostObject.ListProxy<Match, Long, com.merakianalytics.orianna.types.data.match.TournamentMatches> {
    public static class Builder {
        private Platform platform;
        private final String tournamentCode;

        private Builder(final String tournamentCode) {
            this.tournamentCode = tournamentCode;
        }

        public TournamentMatches get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", platform).put("tournamentCode", tournamentCode);

            return Orianna.getSettings().getPipeline().get(TournamentMatches.class, builder.build());
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

    public static class ManyBuilder {
        private Platform platform;
        private boolean streaming = false;
        private final Iterable<String> tournamentCodes;

        private ManyBuilder(final Iterable<String> tournamentCodes) {
            this.tournamentCodes = tournamentCodes;
        }

        public SearchableList<TournamentMatches> get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            final ImmutableMap.Builder<String, Object> builder =
                ImmutableMap.<String, Object> builder().put("platform", platform).put("tournamentCodes", tournamentCodes);

            final CloseableIterator<TournamentMatches> result =
                Orianna.getSettings().getPipeline().getMany(TournamentMatches.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public ManyBuilder streaming() {
            streaming = true;
            return this;
        }

        public ManyBuilder withPlatform(final Platform platform) {
            this.platform = platform;
            return this;
        }

        public ManyBuilder withRegion(final Region region) {
            platform = region.getPlatform();
            return this;
        }
    }

    private static final long serialVersionUID = 4715908923360892531L;

    public static Builder forTournamentCode(final String tournamentCode) {
        return new Builder(tournamentCode);
    }

    public static ManyBuilder forTournamentCodes(final Iterable<String> tournamentCodes) {
        return new ManyBuilder(tournamentCodes);
    }

    public static ManyBuilder forTournamentCodes(final String... tournamentCodes) {
        return new ManyBuilder(Arrays.asList(tournamentCodes));
    }

    public TournamentMatches(final com.merakianalytics.orianna.types.data.match.TournamentMatches coreData) {
        super(coreData, 1);
    }

    @Override
    public boolean exists() {
        if(coreData.isEmpty()) {
            load(LIST_PROXY_LOAD_GROUP);
        }
        return !coreData.isEmpty();
    }

    @Override
    protected List<String> getLoadGroups() {
        return Arrays.asList(new String[] {
            LIST_PROXY_LOAD_GROUP
        });
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public String getTournamentCode() {
        return coreData.getTournamentCode();
    }

    @Override
    protected void loadCoreData(final String group) {
        ImmutableMap.Builder<String, Object> builder;
        switch(group) {
            case LIST_PROXY_LOAD_GROUP:
                builder = ImmutableMap.builder();
                if(coreData.getPlatform() != null) {
                    builder.put("platform", Platform.withTag(coreData.getPlatform()));
                }
                if(coreData.getTournamentCode() != null) {
                    builder.put("tournamentCode", coreData.getTournamentCode());
                }
                final com.merakianalytics.orianna.types.data.match.TournamentMatches data =
                    Orianna.getSettings().getPipeline().get(com.merakianalytics.orianna.types.data.match.TournamentMatches.class, builder.build());
                if(data != null) {
                    coreData = data;
                }
                loadListProxyData(new Function<Long, Match>() {
                    @Override
                    public Match apply(final Long id) {
                        return Match.withId(id).get();
                    }
                });
                break;
            default:
                break;
        }
    }
}
