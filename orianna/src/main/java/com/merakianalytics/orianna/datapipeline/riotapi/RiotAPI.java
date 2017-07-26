package com.merakianalytics.orianna.datapipeline.riotapi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
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
import com.google.common.collect.Multimap;
import com.merakianalytics.datapipelines.sources.AbstractDataSource;
import com.merakianalytics.datapipelines.sources.CompositeDataSource;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient.Response;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient.TimeoutException;
import com.merakianalytics.orianna.datapipeline.common.RateLimiter;
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
        private static final FailedRequestStrategy DEFAULT_404_STRATEGY = FailedRequestStrategies.returnNull();
        private static final FailedRequestStrategy DEFAULT_429_STRATEGY = FailedRequestStrategies.throwException(); // TODO: Make this use "Retry-After" header
        private static final FailedRequestStrategy DEFAULT_500_STRATEGY = FailedRequestStrategies.exponentialBackoff();
        private static final FailedRequestStrategy DEFAULT_503_STRATEGY = FailedRequestStrategies.throwException();
        private static final FailedRequestStrategy DEFAULT_TIMEOUT_STRATEGY = FailedRequestStrategies.exponentialBackoff();
        private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

        private final Map<Platform, RateLimiter> applicationRateLimiters;
        private final HTTPClient client;
        private final Map<String, String> defaultHeaders;
        private final FailedRequestStrategy http404Strategy;
        private final FailedRequestStrategy http429Strategy;
        private final FailedRequestStrategy http500Strategy;
        private final FailedRequestStrategy http503Strategy;
        private final ObjectMapper mapper;
        private final Map<Platform, Map<String, Object>> rateLimiterLocks;
        private final Map<Platform, Map<String, RateLimiter>> rateLimiters;
        private final FailedRequestStrategy timeoutStrategy;

        public Service(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client) {
            this(key, applicationRateLimiters, client, DEFAULT_TIMEOUT_STRATEGY, DEFAULT_404_STRATEGY, DEFAULT_429_STRATEGY, DEFAULT_500_STRATEGY,
                 DEFAULT_503_STRATEGY);
        }

        public Service(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client,
                       final FailedRequestStrategy timeoutStrategy, final FailedRequestStrategy http404Strategy, final FailedRequestStrategy http429Strategy,
                       final FailedRequestStrategy http500Strategy, final FailedRequestStrategy http503Strategy) {
            this.client = client;
            this.applicationRateLimiters = applicationRateLimiters;
            this.timeoutStrategy = timeoutStrategy;
            this.http404Strategy = http404Strategy;
            this.http429Strategy = http429Strategy;
            this.http500Strategy = http500Strategy;
            this.http503Strategy = http503Strategy;
            mapper = new ObjectMapper();
            defaultHeaders = ImmutableMap.of("X-Riot-Token", key);
            rateLimiters = new ConcurrentHashMap<>();
            rateLimiterLocks = new ConcurrentHashMap<>();
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform) {
            final RequestContext<T> context = new RequestContext<T>(type, endpoint, platform, null, null);
            return get(context);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Map<String, String> parameters) {
            final RequestContext<T> context = new RequestContext<T>(type, endpoint, platform,
                                                                    parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()), null);
            return get(context);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Map<String, String> parameters,
                            final String rateLimiterName) {
            final RequestContext<T> context = new RequestContext<T>(type, endpoint, platform,
                                                                    parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()),
                                                                    rateLimiterName);
            return get(context);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Multimap<String, String> parameters) {
            final RequestContext<T> context = new RequestContext<T>(type, endpoint, platform, parameters, null);
            return get(context);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Multimap<String, String> parameters,
                            final String rateLimiterName) {
            final RequestContext<T> context = new RequestContext<T>(type, endpoint, platform, parameters, rateLimiterName);
            return get(context);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final String rateLimiterName) {
            final RequestContext<T> context = new RequestContext<T>(type, endpoint, platform, null, rateLimiterName);
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

        private RateLimiter getRateLimiter(final Platform platform, final String name) {
            Map<String, RateLimiter> limiters = rateLimiters.get(platform);

            if(limiters == null) {
                synchronized(rateLimiters) {
                    limiters = rateLimiters.get(platform);
                    if(limiters == null) {
                        limiters = new HashMap<>();
                        rateLimiters.put(platform, limiters);
                    }
                }
            }

            RateLimiter limiter = limiters.get(name);

            if(limiter == null) {
                Map<String, Object> locks = rateLimiterLocks.get(platform);
                if(locks == null) {
                    synchronized(rateLimiterLocks) {
                        locks = rateLimiterLocks.get(platform);
                        if(locks == null) {
                            locks = new HashMap<>();
                            rateLimiterLocks.put(platform, locks);
                        }
                    }
                }

                Object lock = locks.get(name);
                if(lock == null) {
                    synchronized(locks) {
                        lock = locks.get(name);
                        if(lock == null) {
                            lock = new Object();
                            locks.put(name, lock);
                        }
                    }
                }

                synchronized(lock) {
                    limiter = limiters.get(name);
                    if(limiter == null) {
                        limiter = newRateLimiter(platform, name);
                        limiters.put(name, limiter);
                    }
                }
            }

            return limiter;
        }

        private RateLimiter newRateLimiter(final Platform platform, final String name) {
            RateLimiter applicationRateLimiter = applicationRateLimiters.get(platform);
            if(applicationRateLimiter == null) {
                synchronized(applicationRateLimiters) {
                    applicationRateLimiter = applicationRateLimiters.get(platform);
                    if(applicationRateLimiter == null) {
                        // TODO: Create application rate limiter
                        applicationRateLimiter = null;
                        // applicationRateLimiters.put(platform, applicationRateLimiter);
                    }
                }
            }

            // TODO: Create rate limiter
            final RateLimiter limiter = null;
            return limiter;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RiotAPI.class);

    @SafeVarargs
    public static RiotAPI create(final String key, final Class<? extends Service>... services) {
        final Map<Platform, RateLimiter> applicationRateLimiters = new ConcurrentHashMap<>();
        final HTTPClient client = new HTTPClient();

        final Set<Service> sources = new HashSet<>();
        for(final Class<? extends Service> service : services) {
            try {
                final Service source = service.getConstructor(String.class, Map.class, HTTPClient.class).newInstance(key, applicationRateLimiters, client);
                sources.add(source);
            } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                    | SecurityException e) {
                LOGGER.error("Failed to instantiate service " + service.getCanonicalName() + "!", e);
                throw new OriannaException("Failed to instantiate Riot API Service " + service.getCanonicalName() + "! Report this to the orianna team.", e);
            }
        }
        return new RiotAPI(sources);
    }

    private RiotAPI(final Collection<? extends Service> sources) {
        super(sources);
    }
}
