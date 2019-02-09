package com.merakianalytics.orianna.datapipeline.kernel;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.merakianalytics.datapipelines.sources.CompositeDataSource;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.kernel.KernelService.FailedRequestStrategy;
import com.merakianalytics.orianna.types.common.OriannaException;

public class Kernel extends CompositeDataSource {
    public static class Configuration {
        private static final FailedRequestStrategy DEFAULT_404_STRATEGY = new FailedRequestStrategy.ReturnNull();
        private static final FailedRequestStrategy DEFAULT_500_STRATEGY = new FailedRequestStrategy.ExponentialBackoff();
        private static final FailedRequestStrategy DEFAULT_503_STRATEGY = new FailedRequestStrategy.ExponentialBackoff();
        private static final FailedRequestStrategy DEFAULT_504_STRATEGY = new FailedRequestStrategy.ExponentialBackoff();
        private static final String DEFAULT_HOST = "localhost";
        private static final FailedRequestStrategy DEFAULT_HTTP_TIMEOUT_STRATEGY = new FailedRequestStrategy.ExponentialBackoff();
        private static final boolean DEFAULT_MSGPACK = true;
        private static final int DEFAULT_PORT = 80;
        private static final HTTPClient.Configuration DEFAULT_REQUESTS = new HTTPClient.Configuration();
        private static final Set<Class<? extends KernelService>> DEFAULT_SERVICES = ImmutableSet.of(ChampionAPI.class, ChampionMasteryAPI.class,
            LeagueAPI.class, MatchAPI.class, SpectatorAPI.class, StatusAPI.class, SummonerAPI.class, ThirdPartyCodeAPI.class);

        private String host = DEFAULT_HOST;
        private FailedRequestStrategy http404Strategy = DEFAULT_404_STRATEGY;
        private FailedRequestStrategy http500Strategy = DEFAULT_500_STRATEGY;
        private FailedRequestStrategy http503Strategy = DEFAULT_503_STRATEGY;
        private FailedRequestStrategy http504Strategy = DEFAULT_504_STRATEGY;
        private FailedRequestStrategy httpTimeoutStrategy = DEFAULT_HTTP_TIMEOUT_STRATEGY;
        private boolean msgpack = DEFAULT_MSGPACK;
        private int port = DEFAULT_PORT;
        private HTTPClient.Configuration requests = DEFAULT_REQUESTS;
        private Set<Class<? extends KernelService>> services = DEFAULT_SERVICES;

        /**
         * @return the host
         */
        public String getHost() {
            return host;
        }

        /**
         * @return the http404Strategy
         */
        public FailedRequestStrategy getHttp404Strategy() {
            return http404Strategy;
        }

        /**
         * @return the http500Strategy
         */
        public FailedRequestStrategy getHttp500Strategy() {
            return http500Strategy;
        }

        /**
         * @return the http503Strategy
         */
        public FailedRequestStrategy getHttp503Strategy() {
            return http503Strategy;
        }

        /**
         * @return the http504Strategy
         */
        public FailedRequestStrategy getHttp504Strategy() {
            return http504Strategy;
        }

        /**
         * @return the httpTimeoutStrategy
         */
        public FailedRequestStrategy getHttpTimeoutStrategy() {
            return httpTimeoutStrategy;
        }

        /**
         * @return the port
         */
        public int getPort() {
            return port;
        }

        /**
         * @return the requests
         */
        public HTTPClient.Configuration getRequests() {
            return requests;
        }

        /**
         * @return the services
         */
        public Set<Class<? extends KernelService>> getServices() {
            return services;
        }

        /**
         * @return the msgpack
         */
        public boolean isMsgpack() {
            return msgpack;
        }

        /**
         * @param host
         *        the host to set
         */
        public void setHost(final String host) {
            this.host = host;
        }

        /**
         * @param http404Strategy
         *        the http404Strategy to set
         */
        public void setHttp404Strategy(final FailedRequestStrategy http404Strategy) {
            this.http404Strategy = http404Strategy;
        }

        /**
         * @param http500Strategy
         *        the http500Strategy to set
         */
        public void setHttp500Strategy(final FailedRequestStrategy http500Strategy) {
            this.http500Strategy = http500Strategy;
        }

        /**
         * @param http503Strategy
         *        the http503Strategy to set
         */
        public void setHttp503Strategy(final FailedRequestStrategy http503Strategy) {
            this.http503Strategy = http503Strategy;
        }

        /**
         * @param http504Strategy
         *        the http504Strategy to set
         */
        public void setHttp504Strategy(final FailedRequestStrategy http504Strategy) {
            this.http504Strategy = http504Strategy;
        }

        /**
         * @param httpTimeoutStrategy
         *        the httpTimeoutStrategy to set
         */
        public void setHttpTimeoutStrategy(final FailedRequestStrategy httpTimeoutStrategy) {
            this.httpTimeoutStrategy = httpTimeoutStrategy;
        }

        /**
         * @param msgpack
         *        the msgpack to set
         */
        public void setMsgpack(final boolean useMsgpack) {
            msgpack = useMsgpack;
        }

        /**
         * @param port
         *        the port to set
         */
        public void setPort(final int port) {
            this.port = port;
        }

        /**
         * @param requests
         *        the requests to set
         */
        public void setRequests(final HTTPClient.Configuration requests) {
            this.requests = requests;
        }

        /**
         * @param services
         *        the services to set
         */
        public void setServices(final Set<Class<? extends KernelService>> services) {
            this.services = services;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(KernelService.class);

    private static Set<KernelService> createServices(final Configuration config) {
        final HTTPClient client = new HTTPClient(config.getRequests());

        final Set<KernelService> services = new HashSet<>();
        for(final Class<? extends KernelService> serviceType : config.getServices()) {
            try {
                final KernelService service = serviceType.getConstructor(Configuration.class, HTTPClient.class).newInstance(config, client);
                services.add(service);
            } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
                LOGGER.error("Failed to instantiate service " + serviceType.getCanonicalName() + "!", e);
                throw new OriannaException("Failed to instantiate Kernel Service " + serviceType.getCanonicalName() + "! Report this to the orianna team.",
                    e);
            }
        }
        return services;
    }

    public Kernel() {
        this(new Configuration());
    }

    public Kernel(final Configuration config) {
        super(createServices(config));
    }
}
