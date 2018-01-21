package com.merakianalytics.orianna.types.core.match;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;

public abstract class Matches {
    public static class Builder {
        private final Iterable<Long> ids;
        private Platform platform;
        private boolean streaming = false;
        private String tournamentCode;

        private Builder(final Iterable<Long> ids) {
            this.ids = ids;
        }

        public SearchableList<Match> get() {
            if(platform == null) {
                platform = Orianna.getSettings().getDefaultPlatform();
                if(platform == null) {
                    throw new IllegalStateException(
                        "No platform/region was set! Must either set a default platform/region with Orianna.setDefaultPlatform or Orianna.setDefaultRegion, or include a platform/region with the request!");
                }
            }

            final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object> builder().put("platform", platform).put("matchIds", ids);
            if(tournamentCode != null) {
                builder.put("tournamentCode", tournamentCode);
            }

            final CloseableIterator<Match> result = Orianna.getSettings().getPipeline().getMany(Match.class, builder.build(), streaming);
            return streaming ? SearchableLists.from(CloseableIterators.toLazyList(result)) : SearchableLists.from(CloseableIterators.toList(result));
        }

        public Builder streaming() {
            streaming = true;
            return this;
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

    public static Builder withIds(final Iterable<Long> ids) {
        return new Builder(ids);
    }

    public static Builder withIds(final long... ids) {
        final List<Long> list = new ArrayList<>(ids.length);
        for(final long id : ids) {
            list.add(id);
        }
        return new Builder(list);
    }
}
