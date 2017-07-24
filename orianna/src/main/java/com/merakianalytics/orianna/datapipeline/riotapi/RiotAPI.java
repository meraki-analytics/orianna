package com.merakianalytics.orianna.datapipeline.riotapi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
import com.merakianalytics.orianna.datapipeline.common.RateLimiter;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.BadRequestException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.ForbiddenException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.InternalServerErrorException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.RateLimitExceededException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.ServiceUnavailableException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.UnsupportedMediaTypeException;
import com.merakianalytics.orianna.type.common.OriannaException;
import com.merakianalytics.orianna.type.common.Platform;

public class RiotAPI extends CompositeDataSource {
    public static abstract class Service extends AbstractDataSource {
        private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

        private final Map<Platform, RateLimiter> applicationRateLimiters;
        private final HTTPClient client;
        private final Map<String, String> defaultHeaders;
        private final ObjectMapper mapper;
        private final Map<Platform, Map<String, Object>> rateLimiterLocks;
        private final Map<Platform, Map<String, RateLimiter>> rateLimiters;

        public Service(final String key, final Map<Platform, RateLimiter> applicationRateLimiters, final HTTPClient client) {
            this.client = client;
            this.applicationRateLimiters = applicationRateLimiters;
            mapper = new ObjectMapper();
            defaultHeaders = ImmutableMap.of("X-Riot-Token", key);
            rateLimiters = new ConcurrentHashMap<>();
            rateLimiterLocks = new ConcurrentHashMap<>();
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform) {
            return get(type, endpoint, platform, (Multimap<String, String>)null, null);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Map<String, String> parameters) {
            return get(type, endpoint, platform, parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()), null);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Map<String, String> parameters,
                            final String rateLimiterName) {
            return get(type, endpoint, platform, parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()), rateLimiterName);
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final Multimap<String, String> parameters,
                            final String rateLimiterName) {
            final String host = platform.getTag().toLowerCase() + ".api.riotgames.com";

            Response response;
            try {
                response = client.get(host, endpoint, parameters, defaultHeaders, getRateLimiter(platform, rateLimiterName));
            } catch(final IOException e) {
                LOGGER.error("Get request failed to " + host + "/" + endpoint + "!", e);
                throw new OriannaException("Something went wrong with a request to the Riot API at " + host + "/" + endpoint
                                           + "! Report this to the orianna team.", e);
            }

            switch(response.getStatusCode()) {
                case 400:
                    LOGGER.error("Got \"Bad Request\" from " + host + "/" + endpoint + "!");
                    throw new BadRequestException("A Riot API request to " + host + "/" + endpoint
                                                  + "returned \"Bad Request\". If the problem persists, report this to the orianna team.");
                case 403:
                    LOGGER.error("Got \"Forbidden\" from " + host + "/" + endpoint + "!");
                    throw new ForbiddenException("A Riot API request to " + host + "/" + endpoint
                                                 + "returned \"Forbidden\". Check to make sure you're using the right API key, and it hasn't expired. If the problem persists with a valid key, report this to the orianna team.");
                case 404:
                    return null;
                case 415:
                    LOGGER.error("Got \"Unsupported Media Type\" from " + host + "/" + endpoint + "!");
                    throw new UnsupportedMediaTypeException("A Riot API request to " + host + "/" + endpoint
                                                            + "returned \"Unsupported Media Type\". If the problem persists, report this to the orianna team.");
                case 429:
                    // TODO: Handle this interally
                    LOGGER.info("Got \"Rate Limit Exceeded\" from " + host + "/" + endpoint + "!");
                    throw new RateLimitExceededException("A Riot API request to " + host + "/" + endpoint
                                                         + "returned \"Rate Limit Exceeded\". If this exception was not handled internally by orianna, report it to the orianna team.");
                case 500:
                    LOGGER.error("Got \"Internal Server Error\" from " + host + "/" + endpoint + "!");
                    throw new InternalServerErrorException("A Riot API request to " + host + "/" + endpoint
                                                           + "returned \"Bad Request\". Sometimes the Riot API experiences these when under extreme load. If the problem persists, try catching this exception, waiting briefly, and trying again.");
                case 503:
                    LOGGER.error("Got \"Service Unavailable\" from " + host + "/" + endpoint + "!");
                    throw new ServiceUnavailableException("A Riot API request to " + host + "/" + endpoint
                                                          + "returned \"Service Unavailable\". This Riot API Service is likely to be down for a short period of time, and can't be used in the meantime.");
                default:
                    if(response.getStatusCode() >= 400) {
                        LOGGER.error("Get request to " + host + "/" + endpoint + " returned " + response.getStatusCode() + ": " + response.getBody());
                        throw new OriannaException("An unknown error code (" + response.getStatusCode() + ") was returned from the Riot API with message: "
                                                   + response.getBody());
                    }
                    break;
            }

            try {
                return mapper.readValue(response.getBody(), type);
            } catch(final IOException e) {
                LOGGER.error("Failed to deserialize response body for type " + type.getCanonicalName() + "!", e);
                throw new OriannaException("Couldn't deserialize the response from the Riot API at " + host + "/" + endpoint + " into "
                                           + type.getCanonicalName() + "! Report this to the orianna team.", e);
            }
        }

        protected <T> T get(final Class<T> type, final String endpoint, final Platform platform, final String rateLimiterName) {
            return get(type, endpoint, platform, (Multimap<String, String>)null, rateLimiterName);
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
                Object lock;
                synchronized(rateLimiterLocks) {
                    Map<String, Object> locks = rateLimiterLocks.get(platform);
                    if(locks == null) {
                        locks = new HashMap<>();
                        rateLimiterLocks.put(platform, locks);
                    }

                    lock = locks.get(name);
                    if(lock == null) {
                        lock = new Object();
                        locks.put(name, lock);
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
