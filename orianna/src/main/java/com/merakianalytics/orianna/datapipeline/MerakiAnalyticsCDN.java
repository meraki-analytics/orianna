package com.merakianalytics.orianna.datapipeline;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.datapipeline.common.expiration.ExpirationPeriod;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.dto.DataObject;
import com.merakianalytics.orianna.types.dto.staticdata.Patch;
import com.merakianalytics.orianna.types.dto.staticdata.Patches;

public class MerakiAnalyticsCDN extends AbstractLocallyCachedCDN<String> {
    public static class Configuration extends AbstractLocallyCachedCDN.Configuration {
        private static final ExpirationPeriod DEFAULT_CACHE_DURATION = ExpirationPeriod.create(6L, TimeUnit.HOURS);
        private static final String DEFAULT_HOST = "cdn.merakianalytics.com";
        private static final HTTPClient.Configuration DEFAULT_REQUESTS = new HTTPClient.Configuration();
        private String host = DEFAULT_HOST;

        public Configuration() {
            setCacheDuration(DEFAULT_CACHE_DURATION);
            setRequests(DEFAULT_REQUESTS);
        }

        /**
         * @return the host
         */
        public String getHost() {
            return host;
        }

        /**
         * @param host
         *        the host to set
         */
        public void setHost(final String host) {
            this.host = host;
        }
    }

    private final String host;

    public MerakiAnalyticsCDN() {
        this(new Configuration());
    }

    public MerakiAnalyticsCDN(final Configuration config) {
        super(config);
        host = config.getHost();
    }

    @SuppressWarnings("unchecked")
    @GetMany(Patch.class)
    public CloseableIterator<Patch> getManyPatch(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkNotNull(platform, "platform", names, "names");

        final String content = get("patches");

        final Patches data = DataObject.fromJSON(Patches.class, content);
        if(data == null) {
            return null;
        }

        final long shift = data.getShifts().get(platform.getTag());
        for(final String tag : data.getShifts().keySet()) {
            data.getShifts().put(tag, data.getShifts().get(tag) - shift);
        }

        final Map<String, Patch> byName = new HashMap<>();
        final List<Patch> list = data.getPatches();
        for(int i = list.size(); i >= 0; i--) {
            list.get(i).setPlatform(platform.getTag());
            list.get(i).setStart(list.get(i).getStart() + shift);
            if(i < list.size() - 1) {
                list.get(i).setEnd(list.get(i + 1).getStart() + shift);
            }
            byName.put(list.get(i).getName(), list.get(i));
        }

        final Iterator<String> iterator = names.iterator();
        return CloseableIterators.from(new Iterator<Patch>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Patch next() {
                return byName.get(iterator.next());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @GetMany(Patches.class)
    public CloseableIterator<Patches> getManyPatches(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<Platform> platforms = (Iterable<Platform>)query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");

        final String content = get("patches");

        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<Patches>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Patches next() {
                final Platform platform = iterator.next();

                final Patches data = DataObject.fromJSON(Patches.class, content);
                if(data == null) {
                    return null;
                }

                final long shift = data.getShifts().get(platform.getTag());
                for(final String tag : data.getShifts().keySet()) {
                    data.getShifts().put(tag, data.getShifts().get(tag) - shift);
                }

                final List<Patch> list = data.getPatches();
                for(int i = 0; i < list.size(); i++) {
                    list.get(i).setPlatform(platform.getTag());
                    list.get(i).setStart(list.get(i).getStart() + shift);

                    if(i < list.size() - 1) {
                        list.get(i).setEnd(list.get(i + 1).getStart() + shift);
                    }
                }
                data.setPlatform(platform.getTag());

                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Get(Patch.class)
    public Patch getPatch(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        String name = (String)query.get("name");

        final String content = get("patches");

        final Patches data = DataObject.fromJSON(Patches.class, content);
        if(data == null) {
            return null;
        }

        // If latest was requested
        if(name == null) {
            name = data.getPatches().get(data.getPatches().size() - 1).getName();
        }

        final List<Patch> list = data.getPatches();
        for(int i = list.size() - 1; i >= 0; i--) {
            if(name.equals(list.get(i).getName())) {
                final long shift = data.getShifts().get(platform.getTag());
                for(final String tag : data.getShifts().keySet()) {
                    data.getShifts().put(tag, data.getShifts().get(tag) - shift);
                }

                list.get(i).setPlatform(platform.getTag());
                list.get(i).setStart(list.get(i).getStart() + shift);
                if(i < list.size() - 1) {
                    list.get(i).setEnd(list.get(i + 1).getStart() + shift);
                }
                return list.get(i);
            }
        }
        return null;
    }

    @Get(Patches.class)
    public Patches getPatches(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final String content = get("patches");

        final Patches data = DataObject.fromJSON(Patches.class, content);
        if(data == null) {
            return null;
        }

        final long shift = data.getShifts().get(platform.getTag());
        for(final String tag : data.getShifts().keySet()) {
            data.getShifts().put(tag, data.getShifts().get(tag) - shift);
        }

        final List<Patch> list = data.getPatches();
        for(int i = 0; i < list.size(); i++) {
            list.get(i).setPlatform(platform.getTag());
            list.get(i).setStart(list.get(i).getStart() + shift);

            if(i < list.size() - 1) {
                list.get(i).setEnd(list.get(i + 1).getStart() + shift);
            }
        }
        data.setPlatform(platform.getTag());

        return data;
    }

    @Override
    protected String getURL(final String resource) {
        return "https://" + host + "/riot/lol/resources/" + resource + ".json";
    }
}
