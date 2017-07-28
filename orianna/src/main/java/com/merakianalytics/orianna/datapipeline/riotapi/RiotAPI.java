package com.merakianalytics.orianna.datapipeline.riotapi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.merakianalytics.datapipelines.sources.AbstractDataSource;
import com.merakianalytics.datapipelines.sources.CompositeDataSource;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient.Response;
import com.merakianalytics.orianna.datapipeline.common.TimeoutException;
import com.merakianalytics.orianna.datapipeline.common.rates.MultiRateLimiter;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI.FailedRequestStrategies.FailedRequestStrategy;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.BadRequestException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.ForbiddenException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.InternalServerErrorException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.NotFoundException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.RateLimitExceededException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.ServiceUnavailableException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.UnsupportedMediaTypeException;
import com.merakianalytics.orianna.type.common.OriannaException;
import com.merakianalytics.orianna.type.common.Platform;

public class RiotAPI extends CompositeDataSource {
    public static class Configuration {
        private static Set<Service.Configuration> defaultServices() {
            final Set<Class<? extends Service>> services = ImmutableSet.of(ChampionAPI.class, ChampionMasteryAPI.class, LeagueAPI.class, MasteriesAPI.class,
                                                                           MatchAPI.class, RunesAPI.class, SpectatorAPI.class, StaticDataAPI.class,
                                                                           SummonerAPI.class);

            final Set<Service.Configuration> configs = new HashSet<>();
            for(final Class<? extends Service> service : services) {
                final Service.Configuration config = new Service.Configuration();
                config.setType(service);
                configs.add(config);
            }
            return configs;
        }

        private Collection<RateLimiter.Configuration> applicationLimiters = RateLimiter.Configuration.defaultDeveloperRateLimits();
        private String key = System.getenv("RIOT_API_KEY");
        private Collection<Service.Configuration> services = defaultServices();

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
            if(applicationLimiters == null) {
                if(other.applicationLimiters != null) {
                    return false;
                }
            } else if(!applicationLimiters.equals(other.applicationLimiters)) {
                return false;
            }
            if(key == null) {
                if(other.key != null) {
                    return false;
                }
            } else if(!key.equals(other.key)) {
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
         * @return the applicationLimiters
         */
        public Collection<RateLimiter.Configuration> getApplicationLimiters() {
            return applicationLimiters;
        }

        /**
         * @return the key
         */
        public String getKey() {
            return key;
        }

        /**
         * @return the services
         */
        public Collection<Service.Configuration> getServices() {
            return services;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (applicationLimiters == null ? 0 : applicationLimiters.hashCode());
            result = prime * result + (key == null ? 0 : key.hashCode());
            result = prime * result + (services == null ? 0 : services.hashCode());
            return result;
        }

        /**
         * @param applicationLimiters
         *        the applicationLimiters to set
         */
        public void setApplicationLimiters(final Collection<RateLimiter.Configuration> applicationLimiters) {
            this.applicationLimiters = applicationLimiters;
        }

        /**
         * @param key
         *        the key to set
         */
        public void setKey(final String key) {
            this.key = key;
        }

        /**
         * @param services
         *        the services to set
         */
        public void setServices(final Collection<Service.Configuration> services) {
            this.services = services;
        }
    }

    public static class FailedRequestStrategies {
        private static class ExponentialBackoff implements FailedRequestStrategy {
            private static final int DEFAULT_BACKOFF_FACTOR = 2;
            private static final TimeUnit DEFAULT_BACKOFF_UNIT = TimeUnit.SECONDS;
            private static final long DEFAULT_INITIAL_BACKOFF = 1;
            private static final int DEFAULT_MAX_ATTEMPTS = 4;
            private static final FailedRequestStrategy DEFAULT_ON_FAILURE = FailedRequestStrategies.throwException();
            private final int backoffFactor;
            private final TimeUnit backoffUnit;
            private final long initialBackoff;
            private final int maxAttempts;
            private final FailedRequestStrategy onFailure;

            public ExponentialBackoff() {
                this(DEFAULT_MAX_ATTEMPTS, DEFAULT_INITIAL_BACKOFF, DEFAULT_BACKOFF_FACTOR, DEFAULT_BACKOFF_UNIT, DEFAULT_ON_FAILURE);
            }

            public ExponentialBackoff(final int maxAttempts, final long initialBackoff, final int backoffFactor, final TimeUnit backoffUnit,
                                      final FailedRequestStrategy onFailure) {
                this.maxAttempts = maxAttempts;
                this.initialBackoff = initialBackoff;
                this.backoffFactor = backoffFactor;
                this.backoffUnit = backoffUnit;
                this.onFailure = onFailure;
            }

            @Override
            public <T> T onFailedRequest(final RiotAPI.Service service, final RequestContext<T> context, final Response response, final OriannaException e) {
                final int attempts = context.attemptCount;
                if(attempts > maxAttempts) {
                    return onFailure.onFailedRequest(service, context, response, e);
                }

                final long backoff = (long)(initialBackoff * Math.pow(backoffFactor, attempts - 1));
                try {
                    backoffUnit.sleep(backoff);
                } catch(final InterruptedException e1) {
                    return onFailure.onFailedRequest(service, context, response, e);
                }

                return service.get(context);
            }
        }

        public static interface FailedRequestStrategy {
            public <T> T onFailedRequest(RiotAPI.Service service, RequestContext<T> context, Response response, OriannaException e);
        }

        private static class LinearBackoff implements FailedRequestStrategy {
            private static final long DEFAULT_BACKOFF = 1;
            private static final TimeUnit DEFAULT_BACKOFF_UNIT = TimeUnit.SECONDS;
            private static final int DEFAULT_MAX_ATTEMPTS = 4;
            private static final FailedRequestStrategy DEFAULT_ON_FAILURE = FailedRequestStrategies.throwException();
            private final long backoff;
            private final TimeUnit backoffUnit;
            private final int maxAttempts;
            private final FailedRequestStrategy onFailure;

            public LinearBackoff() {
                this(DEFAULT_MAX_ATTEMPTS, DEFAULT_BACKOFF, DEFAULT_BACKOFF_UNIT, DEFAULT_ON_FAILURE);
            }

            public LinearBackoff(final int maxAttempts, final long backoff, final TimeUnit backoffUnit,
                                 final FailedRequestStrategy onFailure) {
                this.maxAttempts = maxAttempts;
                this.backoff = backoff;
                this.backoffUnit = backoffUnit;
                this.onFailure = onFailure;
            }

            @Override
            public <T> T onFailedRequest(final RiotAPI.Service service, final RequestContext<T> context, final Response response, final OriannaException e) {
                final int attempts = context.attemptCount;
                if(attempts > maxAttempts) {
                    return onFailure.onFailedRequest(service, context, response, e);
                }

                try {
                    backoffUnit.sleep(backoff);
                } catch(final InterruptedException e1) {
                    return onFailure.onFailedRequest(service, context, response, e);
                }

                return service.get(context);
            }
        }

        private static class ReturnNull implements FailedRequestStrategy {
            @Override
            public <T> T onFailedRequest(final RiotAPI.Service service, final RequestContext<T> context, final Response response, final OriannaException e) {
                return null;
            }
        }

        private static class ThrowException implements FailedRequestStrategy {
            @Override
            public <T> T onFailedRequest(final RiotAPI.Service service, final RequestContext<T> context, final Response response, final OriannaException e) {
                throw e;
            }
        }

        public static FailedRequestStrategy exponentialBackoff() {
            return new ExponentialBackoff();
        }

        public static FailedRequestStrategy exponentialBackoff(final int maxAttempts, final long initialBackoff, final int backoffFactor,
                                                               final TimeUnit backoffUnit, final FailedRequestStrategy onFailure) {
            return new ExponentialBackoff(maxAttempts, initialBackoff, backoffFactor, backoffUnit, onFailure);
        }

        public static FailedRequestStrategy linearBackoff() {
            return new LinearBackoff();
        }

        public static FailedRequestStrategy linearBackoff(final int maxAttempts, final long backoff, final TimeUnit backoffUnit,
                                                          final FailedRequestStrategy onFailure) {
            return new LinearBackoff(maxAttempts, backoff, backoffUnit, onFailure);
        }

        public static FailedRequestStrategy returnNull() {
            return new ReturnNull();
        }

        public static FailedRequestStrategy throwException() {
            return new ThrowException();
        }
    }

    private static class RequestContext<T> {
        public int attemptCount = 0;
        public String endpoint;
        public Multimap<String, String> parameters;
        public Platform platform;
        public String rateLimiterName;
        public Class<T> type;

        public RequestContext(final Class<T> type, final String endpoint, final Platform platform, final Multimap<String, String> parameters,
                              final String rateLimiterName) {
            this.type = type;
            this.endpoint = endpoint;
            this.platform = platform;
            this.parameters = parameters;
            this.rateLimiterName = rateLimiterName;
            this.attemptCount = 1;
        }
    }

    public static abstract class Service extends AbstractDataSource {
        public static class Configuration {
            private static final FailedRequestStrategy DEFAULT_404_STRATEGY = FailedRequestStrategies.returnNull();
            private static final FailedRequestStrategy DEFAULT_429_STRATEGY = FailedRequestStrategies.throwException(); // TODO: Make this use "Retry-After"
            private static final FailedRequestStrategy DEFAULT_500_STRATEGY = FailedRequestStrategies.exponentialBackoff();
            private static final FailedRequestStrategy DEFAULT_503_STRATEGY = FailedRequestStrategies.throwException();
            private static final FailedRequestStrategy DEFAULT_TIMEOUT_STRATEGY = FailedRequestStrategies.exponentialBackoff();
            private FailedRequestStrategy http404Strategy = DEFAULT_404_STRATEGY;
            private FailedRequestStrategy http429Strategy = DEFAULT_429_STRATEGY;
            private FailedRequestStrategy http500Strategy = DEFAULT_500_STRATEGY;
            private FailedRequestStrategy http503Strategy = DEFAULT_503_STRATEGY;
            private Collection<RateLimiter.Configuration> methodLimiters = RateLimiter.Configuration.defaultDeveloperRateLimits();
            private FailedRequestStrategy timeoutStrategy = DEFAULT_TIMEOUT_STRATEGY;
            private Class<? extends Service> type;

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
                if(methodLimiters == null) {
                    if(other.methodLimiters != null) {
                        return false;
                    }
                } else if(!methodLimiters.equals(other.methodLimiters)) {
                    return false;
                }
                if(timeoutStrategy == null) {
                    if(other.timeoutStrategy != null) {
                        return false;
                    }
                } else if(!timeoutStrategy.equals(other.timeoutStrategy)) {
                    return false;
                }
                if(type == null) {
                    if(other.type != null) {
                        return false;
                    }
                } else if(!type.equals(other.type)) {
                    return false;
                }
                return true;
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
             * @return the methodLimiters
             */
            public Collection<RateLimiter.Configuration> getMethodLimiters() {
                return methodLimiters;
            }

            /**
             * @return the timeoutStrategy
             */
            public FailedRequestStrategy getTimeoutStrategy() {
                return timeoutStrategy;
            }

            /**
             * @return the type
             */
            public Class<? extends Service> getType() {
                return type;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + (http404Strategy == null ? 0 : http404Strategy.hashCode());
                result = prime * result + (http429Strategy == null ? 0 : http429Strategy.hashCode());
                result = prime * result + (http500Strategy == null ? 0 : http500Strategy.hashCode());
                result = prime * result + (http503Strategy == null ? 0 : http503Strategy.hashCode());
                result = prime * result + (methodLimiters == null ? 0 : methodLimiters.hashCode());
                result = prime * result + (timeoutStrategy == null ? 0 : timeoutStrategy.hashCode());
                result = prime * result + (type == null ? 0 : type.hashCode());
                return result;
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
             * @param methodLimiters
             *        the methodLimiters to set
             */
            public void setMethodLimiters(final Collection<RateLimiter.Configuration> methodLimiters) {
                this.methodLimiters = methodLimiters;
            }

            /**
             * @param timeoutStrategy
             *        the timeoutStrategy to set
             */
            public void setTimeoutStrategy(final FailedRequestStrategy timeoutStrategy) {
                this.timeoutStrategy = timeoutStrategy;
            }

            /**
             * @param type
             *        the type to set
             */
            public void setType(final Class<? extends Service> type) {
                this.type = type;
            }
        }

        private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

        private static RateLimiter newRateLimiter(final Collection<RateLimiter.Configuration> configs) {
            if(configs == null || configs.isEmpty()) {
                return null;
            }
            if(configs.size() == 1) {
                final RateLimiter.Configuration config = configs.iterator().next();
                try {
                    return config.getType().getLimiterClass().getConstructor(RateLimiter.Configuration.class).newInstance(config);
                } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                        | SecurityException e) {
                    LOGGER.error("Failed to instantiate " + config.getType() + " Rate Limiter!", e);
                    throw new OriannaException("Failed to instantiate " + config.getType() + " Rate Limiter! Report this to the orianna team.", e);
                }
            } else {
                final Set<RateLimiter> limiters = new HashSet<>();
                for(final RateLimiter.Configuration config : configs) {
                    try {
                        limiters.add(config.getType().getLimiterClass().getConstructor(RateLimiter.Configuration.class).newInstance(config));
                    } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                            | SecurityException e) {
                        LOGGER.error("Failed to instantiate " + config.getType() + " Rate Limiter!", e);
                        throw new OriannaException("Failed to instantiate " + config.getType() + " Rate Limiter! Report this to the orianna team.", e);
                    }
                }
                return new MultiRateLimiter(limiters);
            }
        }
        private final Set<RateLimiter.Configuration> applicationLimiterConfigs;
        private final Map<Platform, RateLimiter> applicationRateLimiters;
        private final HTTPClient client;
        private final Map<String, String> defaultHeaders;
        private final FailedRequestStrategy http404Strategy;
        private final FailedRequestStrategy http429Strategy;
        private final FailedRequestStrategy http500Strategy;
        private final FailedRequestStrategy http503Strategy;
        private final ObjectMapper mapper;
        private final Set<RateLimiter.Configuration> methodLimiterConfigs;
        private final Map<Platform, Map<String, Object>> rateLimiterLocks;
        private final Map<Platform, Map<String, RateLimiter>> rateLimiters;

        private final FailedRequestStrategy timeoutStrategy;

        public Service(final String key, final Map<Platform, RateLimiter> applicationRateLimiters,
                       final Collection<RateLimiter.Configuration> applicationLimiterConfigs, final HTTPClient client, final Configuration config) {
            this.client = client;
            this.applicationRateLimiters = applicationRateLimiters;
            this.applicationLimiterConfigs = ImmutableSet.copyOf(applicationLimiterConfigs);
            timeoutStrategy = config.getTimeoutStrategy();
            http404Strategy = config.getHttp404Strategy();
            http429Strategy = config.getHttp429Strategy();
            http500Strategy = config.getHttp500Strategy();
            http503Strategy = config.getHttp503Strategy();
            methodLimiterConfigs = ImmutableSet.copyOf(config.getMethodLimiters());
            mapper = new ObjectMapper();
            defaultHeaders = ImmutableMap.of("X-Riot-Token", key);
            rateLimiters = new ConcurrentHashMap<>();
            rateLimiterLocks = new ConcurrentHashMap<>();
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform) {
            final RequestContext<T> context = new RequestContext<>(type, endpoint, platform, null, null);
            return get(context);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Map<String, String> parameters) {
            final RequestContext<T> context = new RequestContext<>(type, endpoint, platform,
                                                                   parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()), null);
            return get(context);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Map<String, String> parameters,
                            final String rateLimiterName) {
            final RequestContext<T> context = new RequestContext<>(type, endpoint, platform,
                                                                   parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()),
                                                                   rateLimiterName);
            return get(context);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Multimap<String, String> parameters) {
            final RequestContext<T> context = new RequestContext<>(type, endpoint, platform, parameters, null);
            return get(context);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Multimap<String, String> parameters,
                            final String rateLimiterName) {
            final RequestContext<T> context = new RequestContext<>(type, endpoint, platform, parameters, rateLimiterName);
            return get(context);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final String rateLimiterName) {
            final RequestContext<T> context = new RequestContext<>(type, endpoint, platform, null, rateLimiterName);
            return get(context);
        }

        private <T> T get(final RequestContext<T> context) {
            context.attemptCount += 1;
            final String host = context.platform.getTag().toLowerCase() + ".api.riotgames.com";

            Response response;
            try {
                response = client.get(host, context.endpoint, context.parameters, defaultHeaders, getRateLimiter(context.platform, context.rateLimiterName));
            } catch(final TimeoutException e) {
                LOGGER.info("Get request timed out to " + host + "/" + context.endpoint + "!", e);
                return timeoutStrategy.onFailedRequest(this, context, null, e);
            } catch(final IOException e) {
                LOGGER.error("Get request failed to " + host + "/" + context.endpoint + "!", e);
                throw new OriannaException("Something went wrong with a request to the Riot API at " + host + "/" + context.endpoint
                                           + "! Report this to the orianna team.", e);
            }
            
            // TODO: Check headers for reduced rates and use them

            switch(response.getStatusCode()) {
                case 400:
                    LOGGER.error("Got \"Bad Request\" from " + host + "/" + context.endpoint + "!");
                    throw new BadRequestException("A Riot API request to " + host + "/" + context.endpoint
                                                  + "returned \"Bad Request\". If the problem persists, report this to the orianna team.");
                case 403:
                    LOGGER.error("Got \"Forbidden\" from " + host + "/" + context.endpoint + "!");
                    throw new ForbiddenException("A Riot API request to " + host + "/" + context.endpoint
                                                 + "returned \"Forbidden\". Check to make sure you're using the right API key, and it hasn't expired. If the problem persists with a valid key, report this to the orianna team.");
                case 404:
                    LOGGER.info("Got \"Not Found\" from " + host + "/" + context.endpoint + "!");
                    return http404Strategy.onFailedRequest(this, context, response,
                                                           new NotFoundException("A Riot API request to " + host + "/" + context.endpoint
                                                                                 + "returned \"Not Found\". If this was unexpected, check your query parameters to ensure they are correct."));
                case 415:
                    LOGGER.error("Got \"Unsupported Media Type\" from " + host + "/" + context.endpoint + "!");
                    throw new UnsupportedMediaTypeException("A Riot API request to " + host + "/" + context.endpoint
                                                            + "returned \"Unsupported Media Type\". If the problem persists, report this to the orianna team.");
                case 429:
                    LOGGER.info("Got \"Rate Limit Exceeded\" from " + host + "/" + context.endpoint + "!");
                    return http429Strategy.onFailedRequest(this, context, response,
                                                           new RateLimitExceededException("A Riot API request to " + host + "/" + context.endpoint
                                                                                          + "returned \"Rate Limit Exceeded\". If this was unexpected, report it to the orianna team."));
                case 500:
                    LOGGER.error("Got \"Internal Server Error\" from " + host + "/" + context.endpoint + "!");
                    return http500Strategy.onFailedRequest(this, context, response,
                                                           new InternalServerErrorException("A Riot API request to " + host + "/" + context.endpoint
                                                                                            + "returned \"Bad Request\". Sometimes the Riot API experiences these when under extreme load. If the problem persists, try catching this exception, waiting briefly, and trying again."));
                case 503:
                    LOGGER.error("Got \"Service Unavailable\" from " + host + "/" + context.endpoint + "!");
                    return http503Strategy.onFailedRequest(this, context, response,
                                                           new ServiceUnavailableException("A Riot API request to " + host + "/" + context.endpoint
                                                                                           + "returned \"Service Unavailable\". This Riot API Service is likely to be down for a short period of time, and can't be used in the meantime."));
                default:
                    if(response.getStatusCode() >= 400) {
                        LOGGER.error("Get request to " + host + "/" + context.endpoint + " returned " + response.getStatusCode() + ": " + response.getBody());
                        throw new OriannaException("An unknown error code (" + response.getStatusCode() + ") was returned from the Riot API with message: "
                                                   + response.getBody());
                    }
                    break;
            }

            try {
                return mapper.readValue(response.getBody(), context.type);
            } catch(final IOException e) {
                LOGGER.error("Failed to deserialize response body for type " + context.type.getCanonicalName() + "!", e);
                throw new OriannaException("Couldn't deserialize the response from the Riot API at " + host + "/" + context.endpoint + " into "
                                           + context.type.getCanonicalName() + "! Report this to the orianna team.", e);
            }
        }

        private RateLimiter getRateLimiter(final Platform platform) {
            RateLimiter limiter = applicationRateLimiters.get(platform);
            if(limiter == null && applicationLimiterConfigs != null && !applicationLimiterConfigs.isEmpty()) {
                synchronized(applicationRateLimiters) {
                    limiter = applicationRateLimiters.get(platform);
                    if(limiter == null) {
                        limiter = newRateLimiter(applicationLimiterConfigs);
                        applicationRateLimiters.put(platform, limiter);
                    }
                }
            }
            return limiter;
        }

        private RateLimiter getRateLimiter(final Platform platform, final String name) {
            final boolean anyApplication = applicationLimiterConfigs != null && !applicationLimiterConfigs.isEmpty();
            final boolean anyMethod = methodLimiterConfigs != null && !methodLimiterConfigs.isEmpty();
            if(!anyApplication && !anyMethod) {
                return null;
            } else if(anyApplication && !anyMethod) {
                return getRateLimiter(platform);
            }

            Map<String, RateLimiter> limiters = rateLimiters.get(platform);
            if(limiters == null) {
                synchronized(rateLimiters) {
                    limiters = rateLimiters.get(platform);
                    if(limiters == null) {
                        limiters = new ConcurrentHashMap<>();
                        rateLimiters.put(platform, limiters);
                        rateLimiterLocks.put(platform, new ConcurrentHashMap<String, Object>());
                    }
                }
            }

            RateLimiter limiter = limiters.get(name);
            if(limiter == null) {
                final Map<String, Object> locks = rateLimiterLocks.get(platform);
                Object lock = locks.get(name);
                if(lock == null) {
                    synchronized(locks) {
                        lock = locks.get(name);
                        if(lock == null) {
                            lock = new Object();
                            locks.put(name, locks);
                        }
                    }
                }

                synchronized(lock) {
                    limiter = limiters.get(name);
                    if(limiter == null) {
                        limiter = newRateLimiter(methodLimiterConfigs);
                        if(anyApplication) {
                            limiter = new MultiRateLimiter(getRateLimiter(platform), limiter);
                        }
                        limiters.put(name, limiter);
                    }
                }
            }

            return limiter;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RiotAPI.class);

    public static RiotAPI create() {
        return create(new Configuration());
    }

    public static RiotAPI create(final Configuration config) {
        if(config.getKey() == null || config.getKey().isEmpty()) {
            throw new IllegalArgumentException("API Key cannot be null or empty!");
        }

        final Map<Platform, RateLimiter> applicationRateLimiters = new ConcurrentHashMap<>();
        final HTTPClient client = new HTTPClient();

        final Set<Service> sources = new HashSet<>();
        for(final Service.Configuration serviceConfig : config.getServices()) {
            try {
                final Service source = serviceConfig.getType()
                                                    .getConstructor(String.class, Map.class, Collection.class, HTTPClient.class, Service.Configuration.class)
                                                    .newInstance(config.getKey(), applicationRateLimiters, config.getApplicationLimiters(), client,
                                                                 serviceConfig);
                sources.add(source);
            } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                    | SecurityException e) {
                LOGGER.error("Failed to instantiate service " + serviceConfig.getType().getCanonicalName() + "!", e);
                throw new OriannaException("Failed to instantiate Riot API Service " + serviceConfig.getType().getCanonicalName()
                                           + "! Report this to the orianna team.", e);
            }
        }
        return new RiotAPI(sources);
    }

    private RiotAPI(final Collection<? extends Service> sources) {
        super(sources);
    }
}
