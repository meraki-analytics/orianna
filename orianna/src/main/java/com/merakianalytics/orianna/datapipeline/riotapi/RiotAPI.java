package com.merakianalytics.orianna.datapipeline.riotapi;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.merakianalytics.datapipelines.sources.CompositeDataSource;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter.Type;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPIService.FailedRequestStrategy;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.common.Platform;

public class RiotAPI extends CompositeDataSource {
    public static class Configuration {
        private static final FailedRequestStrategy DEFAULT_404_STRATEGY = new FailedRequestStrategy.ReturnNull();
        private static final FailedRequestStrategy DEFAULT_429_STRATEGY = new FailedRequestStrategy.RetryFromHeaders();
        private static final FailedRequestStrategy DEFAULT_500_STRATEGY = new FailedRequestStrategy.ExponentialBackoff();
        private static final FailedRequestStrategy DEFAULT_503_STRATEGY = new FailedRequestStrategy.ThrowException();
        private static final String DEFAULT_API_KEY = System.getenv("RIOT_API_KEY");
        private static final FailedRequestStrategy DEFAULT_HTTP_TIMEOUT_STRATEGY = new FailedRequestStrategy.ExponentialBackoff();
        private static final double DEFAULT_LIMITING_SHARE = 1.0;
        private static final Type DEFAULT_LIMITING_TYPE = Type.BURST;
        private static final FailedRequestStrategy DEFAULT_RATE_LIMITER_TIMEOUT_STRATEGY = new FailedRequestStrategy.ThrowException();
        private static final HTTPClient.Configuration DEFAULT_REQUESTS = new HTTPClient.Configuration();
        private static final Set<Class<? extends RiotAPIService>> DEFAULT_SERVICES = ImmutableSet.of(ChampionAPI.class, ChampionMasteryAPI.class,
            LeagueAPI.class, MatchAPI.class, SpectatorAPI.class, StaticDataAPI.class, StatusAPI.class, SummonerAPI.class);

        private String APIKey = DEFAULT_API_KEY;
        private FailedRequestStrategy http404Strategy = DEFAULT_404_STRATEGY;
        private FailedRequestStrategy http429Strategy = DEFAULT_429_STRATEGY;
        private FailedRequestStrategy http500Strategy = DEFAULT_500_STRATEGY;
        private FailedRequestStrategy http503Strategy = DEFAULT_503_STRATEGY;
        private FailedRequestStrategy httpTimeoutStrategy = DEFAULT_HTTP_TIMEOUT_STRATEGY;

        private double limitingShare = DEFAULT_LIMITING_SHARE;
        private Type limitingType = DEFAULT_LIMITING_TYPE;
        private FailedRequestStrategy rateLimiterTimeoutStrategy = DEFAULT_RATE_LIMITER_TIMEOUT_STRATEGY;
        private HTTPClient.Configuration requests = DEFAULT_REQUESTS;
        private Set<Class<? extends RiotAPIService>> services = DEFAULT_SERVICES;

        @Override
        public boolean equals(final Object obj) {
            if(this == obj) {
                return true;
            }
            if(obj == null) {
                return false;
            }
            if(getClass() != obj.getClass()) {
                return false;
            }
            final Configuration other = (Configuration)obj;
            if(APIKey == null) {
                if(other.APIKey != null) {
                    return false;
                }
            } else if(!APIKey.equals(other.APIKey)) {
                return false;
            }
            if(http404Strategy == null) {
                if(other.http404Strategy != null) {
                    return false;
                }
            } else if(!http404Strategy.equals(other.http404Strategy)) {
                return false;
            }
            if(http429Strategy == null) {
                if(other.http429Strategy != null) {
                    return false;
                }
            } else if(!http429Strategy.equals(other.http429Strategy)) {
                return false;
            }
            if(http500Strategy == null) {
                if(other.http500Strategy != null) {
                    return false;
                }
            } else if(!http500Strategy.equals(other.http500Strategy)) {
                return false;
            }
            if(http503Strategy == null) {
                if(other.http503Strategy != null) {
                    return false;
                }
            } else if(!http503Strategy.equals(other.http503Strategy)) {
                return false;
            }
            if(httpTimeoutStrategy == null) {
                if(other.httpTimeoutStrategy != null) {
                    return false;
                }
            } else if(!httpTimeoutStrategy.equals(other.httpTimeoutStrategy)) {
                return false;
            }
            if(Double.doubleToLongBits(limitingShare) != Double.doubleToLongBits(other.limitingShare)) {
                return false;
            }
            if(limitingType != other.limitingType) {
                return false;
            }
            if(rateLimiterTimeoutStrategy == null) {
                if(other.rateLimiterTimeoutStrategy != null) {
                    return false;
                }
            } else if(!rateLimiterTimeoutStrategy.equals(other.rateLimiterTimeoutStrategy)) {
                return false;
            }
            if(requests == null) {
                if(other.requests != null) {
                    return false;
                }
            } else if(!requests.equals(other.requests)) {
                return false;
            }
            if(services == null) {
                if(other.services != null) {
                    return false;
                }
            } else if(!services.equals(other.services)) {
                return false;
            }
            return true;
        }

        /**
         * @return the APIKey
         */
        public String getAPIKey() {
            return APIKey;
        }

        /**
         * @return the http404Strategy
         */
        public FailedRequestStrategy getHttp404Strategy() {
            return http404Strategy;
        }

        /**
         * @return the http429Strategy
         */
        public FailedRequestStrategy getHttp429Strategy() {
            return http429Strategy;
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
         * @return the httpTimeoutStrategy
         */
        public FailedRequestStrategy getHttpTimeoutStrategy() {
            return httpTimeoutStrategy;
        }

        /**
         * @return the limitingShare
         */
        public double getLimitingShare() {
            return limitingShare;
        }

        /**
         * @return the limitingType
         */
        public Type getLimitingType() {
            return limitingType;
        }

        /**
         * @return the rateLimiterTimeoutStrategy
         */
        public FailedRequestStrategy getRateLimiterTimeoutStrategy() {
            return rateLimiterTimeoutStrategy;
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
        public Set<Class<? extends RiotAPIService>> getServices() {
            return services;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (APIKey == null ? 0 : APIKey.hashCode());
            result = prime * result + (http404Strategy == null ? 0 : http404Strategy.hashCode());
            result = prime * result + (http429Strategy == null ? 0 : http429Strategy.hashCode());
            result = prime * result + (http500Strategy == null ? 0 : http500Strategy.hashCode());
            result = prime * result + (http503Strategy == null ? 0 : http503Strategy.hashCode());
            result = prime * result + (httpTimeoutStrategy == null ? 0 : httpTimeoutStrategy.hashCode());
            long temp;
            temp = Double.doubleToLongBits(limitingShare);
            result = prime * result + (int)(temp ^ temp >>> 32);
            result = prime * result + (limitingType == null ? 0 : limitingType.hashCode());
            result = prime * result + (rateLimiterTimeoutStrategy == null ? 0 : rateLimiterTimeoutStrategy.hashCode());
            result = prime * result + (requests == null ? 0 : requests.hashCode());
            result = prime * result + (services == null ? 0 : services.hashCode());
            return result;
        }

        /**
         * @param APIKey
         *        the APIKey to set
         */
        public void setAPIKey(final String APIKey) {
            this.APIKey = APIKey;
        }

        /**
         * @param http404Strategy
         *        the http404Strategy to set
         */
        public void setHttp404Strategy(final FailedRequestStrategy http404Strategy) {
            this.http404Strategy = http404Strategy;
        }

        /**
         * @param http429Strategy
         *        the http429Strategy to set
         */
        public void setHttp429Strategy(final FailedRequestStrategy http429Strategy) {
            this.http429Strategy = http429Strategy;
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
         * @param httpTimeoutStrategy
         *        the httpTimeoutStrategy to set
         */
        public void setHttpTimeoutStrategy(final FailedRequestStrategy httpTimeoutStrategy) {
            this.httpTimeoutStrategy = httpTimeoutStrategy;
        }

        /**
         * @param limitingShare
         *        the limitingShare to set
         */
        public void setLimitingShare(final double limitingShare) {
            this.limitingShare = limitingShare;
        }

        /**
         * @param limitingType
         *        the limitingType to set
         */
        public void setLimitingType(final Type limitingType) {
            this.limitingType = limitingType;
        }

        /**
         * @param rateLimiterTimeoutStrategy
         *        the rateLimiterTimeoutStrategy to set
         */
        public void setRateLimiterTimeoutStrategy(final FailedRequestStrategy rateLimiterTimeoutStrategy) {
            this.rateLimiterTimeoutStrategy = rateLimiterTimeoutStrategy;
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
        public void setServices(final Set<Class<? extends RiotAPIService>> services) {
            this.services = services;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RiotAPIService.class);

    private static Set<RiotAPIService> createServices(final Configuration config) {
        final HTTPClient client = new HTTPClient(config.getRequests());
        final Map<Platform, RateLimiter> applicationRateLimiters = new ConcurrentHashMap<>();
        final Map<Platform, RateLimiter> applicationRateLimiterLocks = new ConcurrentHashMap<>();

        final Set<RiotAPIService> services = new HashSet<>();
        for(final Class<? extends RiotAPIService> serviceType : config.getServices()) {
            try {
                final RiotAPIService service = serviceType.getConstructor(Configuration.class, HTTPClient.class, Map.class, Map.class).newInstance(config,
                    client, applicationRateLimiters, applicationRateLimiterLocks);
                services.add(service);
            } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
                LOGGER.error("Failed to instantiate service " + serviceType.getCanonicalName() + "!", e);
                throw new OriannaException("Failed to instantiate Riot API Service " + serviceType.getCanonicalName() + "! Report this to the orianna team.",
                    e);
            }
        }
        return services;
    }

    public RiotAPI() {
        this(new Configuration());
    }

    public RiotAPI(final Configuration config) {
        super(createServices(config));
    }
}
