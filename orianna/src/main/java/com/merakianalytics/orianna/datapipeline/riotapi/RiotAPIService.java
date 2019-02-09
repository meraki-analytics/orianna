package com.merakianalytics.orianna.datapipeline.riotapi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Multimap;
import com.merakianalytics.datapipelines.sources.AbstractDataSource;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient.Response;
import com.merakianalytics.orianna.datapipeline.common.TimeoutException;
import com.merakianalytics.orianna.datapipeline.common.TimeoutException.Type;
import com.merakianalytics.orianna.datapipeline.common.rates.AbstractRateLimiter;
import com.merakianalytics.orianna.datapipeline.common.rates.MultiRateLimiter;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI.Configuration;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.BadRequestException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.ForbiddenException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.InternalServerErrorException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.NotFoundException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.RateLimitExceededException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.ServiceUnavailableException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.UnauthorizedException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.UnsupportedMediaTypeException;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.dto.DataObject;

public class RiotAPIService extends AbstractDataSource {
    @JsonDeserialize(using = FailedRequestStrategy.Deserializer.class)
    public static interface FailedRequestStrategy {
        public static class Configuration {
            private long backoff;
            private TimeUnit backoffUnit;
            private FailedRequestStrategy backupStrategy;
            private int maxAttempts, backoffFactor;
            private Type type;

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
                if(backoff != other.backoff) {
                    return false;
                }
                if(backoffFactor != other.backoffFactor) {
                    return false;
                }
                if(backoffUnit != other.backoffUnit) {
                    return false;
                }
                if(backupStrategy == null) {
                    if(other.backupStrategy != null) {
                        return false;
                    }
                } else if(!backupStrategy.equals(other.backupStrategy)) {
                    return false;
                }
                if(maxAttempts != other.maxAttempts) {
                    return false;
                }
                if(type != other.type) {
                    return false;
                }
                return true;
            }

            /**
             * @return the backoff
             */
            public long getBackoff() {
                return backoff;
            }

            /**
             * @return the backoffFactor
             */
            public int getBackoffFactor() {
                return backoffFactor;
            }

            /**
             * @return the backoffUnit
             */
            public TimeUnit getBackoffUnit() {
                return backoffUnit;
            }

            /**
             * @return the backupStrategy
             */
            public FailedRequestStrategy getBackupStrategy() {
                return backupStrategy;
            }

            /**
             * @return the maxAttempts
             */
            public int getMaxAttempts() {
                return maxAttempts;
            }

            /**
             * @return the type
             */
            public Type getType() {
                return type;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + (int)(backoff ^ backoff >>> 32);
                result = prime * result + backoffFactor;
                result = prime * result + (backoffUnit == null ? 0 : backoffUnit.hashCode());
                result = prime * result + (backupStrategy == null ? 0 : backupStrategy.hashCode());
                result = prime * result + maxAttempts;
                result = prime * result + (type == null ? 0 : type.hashCode());
                return result;
            }

            /**
             * @param backoff
             *        the backoff to set
             */
            public void setBackoff(final long backoff) {
                this.backoff = backoff;
            }

            /**
             * @param backoffFactor
             *        the backoffFactor to set
             */
            public void setBackoffFactor(final int backoffFactor) {
                this.backoffFactor = backoffFactor;
            }

            /**
             * @param backoffUnit
             *        the backoffUnit to set
             */
            public void setBackoffUnit(final TimeUnit backoffUnit) {
                this.backoffUnit = backoffUnit;
            }

            /**
             * @param backupStrategy
             *        the backupStrategy to set
             */
            public void setBackupStrategy(final FailedRequestStrategy backupStrategy) {
                this.backupStrategy = backupStrategy;
            }

            /**
             * @param maxAttempts
             *        the maxAttempts to set
             */
            public void setMaxAttempts(final int maxAttempts) {
                this.maxAttempts = maxAttempts;
            }

            /**
             * @param type
             *        the type to set
             */
            public void setType(final Type type) {
                this.type = type;
            }
        }

        public static class Deserializer extends JsonDeserializer<FailedRequestStrategy> {
            @Override
            public FailedRequestStrategy deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
                final Configuration config = context.readValue(parser, Configuration.class);
                try {
                    return config.getType().getType().getConstructor(Configuration.class).newInstance(config);
                } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                    | SecurityException e) {
                    LOGGER.error("Failed to instantiate strategy " + config.getType().getType().getCanonicalName() + "!", e);
                    throw new OriannaException("Failed to instantiate strategy " + config.getType().getType().getCanonicalName()
                        + "! Report this to the orianna team.", e);
                }
            }
        }

        public static class ExponentialBackoff implements FailedRequestStrategy {
            private static final long DEFAULT_BACKOFF = 1;
            private static final int DEFAULT_BACKOFF_FACTOR = 2;
            private static final TimeUnit DEFAULT_BACKOFF_UNIT = TimeUnit.SECONDS;
            private static final FailedRequestStrategy DEFAULT_BACKUP_STRATEGY = new ThrowException();
            private static final int DEFAULT_MAX_ATTEMPTS = 4;
            private final long backoff;
            private final int backoffFactor;
            private final TimeUnit backoffUnit;
            private final FailedRequestStrategy backupStrategy;
            private final int maxAttempts;

            public ExponentialBackoff() {
                this(new Configuration());
            }

            public ExponentialBackoff(final Configuration config) {
                maxAttempts = config.getMaxAttempts() == 0 ? DEFAULT_MAX_ATTEMPTS : config.getMaxAttempts();
                backoff = config.getBackoff() == 0 ? DEFAULT_BACKOFF : config.getBackoff();
                backoffFactor = config.getBackoffFactor() == 0 ? DEFAULT_BACKOFF_FACTOR : config.getBackoffFactor();
                backoffUnit = config.getBackoffUnit() == null ? DEFAULT_BACKOFF_UNIT : config.getBackoffUnit();
                backupStrategy = config.getBackupStrategy() == null ? DEFAULT_BACKUP_STRATEGY : config.getBackupStrategy();
            }

            @Override
            public Configuration getConfiguration() {
                final Configuration config = new Configuration();
                config.setMaxAttempts(maxAttempts);
                config.setBackoff(backoff);
                config.setBackoffFactor(backoffFactor);
                config.setBackoffUnit(backoffUnit);
                config.setBackupStrategy(backupStrategy);
                config.setType(Type.forClass(this.getClass()));
                return config;
            }

            @Override
            public <T extends DataObject> T onFailedRequest(final RiotAPIService service, final RequestContext<T> context, final Response response,
                final OriannaException e) {
                final int attempts = context.attemptCount;
                if(attempts > maxAttempts) {
                    return backupStrategy.onFailedRequest(service, context, response, e);
                }

                final long nextBackoff = (long)(backoff * Math.pow(backoffFactor, attempts - 1));
                try {
                    backoffUnit.sleep(nextBackoff);
                } catch(final InterruptedException e1) {
                    return backupStrategy.onFailedRequest(service, context, response, e);
                }

                return service.get(context);
            }
        }

        public static class LinearBackoff implements FailedRequestStrategy {
            private static final long DEFAULT_BACKOFF = 1;
            private static final TimeUnit DEFAULT_BACKOFF_UNIT = TimeUnit.SECONDS;
            private static final FailedRequestStrategy DEFAULT_BACKUP_STRATEGY = new ThrowException();
            private static final int DEFAULT_MAX_ATTEMPTS = 4;
            private final long backoff;
            private final TimeUnit backoffUnit;
            private final FailedRequestStrategy backupStrategy;
            private final int maxAttempts;

            public LinearBackoff() {
                this(new Configuration());
            }

            public LinearBackoff(final Configuration config) {
                maxAttempts = config.getMaxAttempts() == 0 ? DEFAULT_MAX_ATTEMPTS : config.getMaxAttempts();
                backoff = config.getBackoff() == 0 ? DEFAULT_BACKOFF : config.getBackoff();
                backoffUnit = config.getBackoffUnit() == null ? DEFAULT_BACKOFF_UNIT : config.getBackoffUnit();
                backupStrategy = config.getBackupStrategy() == null ? DEFAULT_BACKUP_STRATEGY : config.getBackupStrategy();
            }

            @Override
            public Configuration getConfiguration() {
                final Configuration config = new Configuration();
                config.setMaxAttempts(maxAttempts);
                config.setBackoff(backoff);
                config.setBackoffUnit(backoffUnit);
                config.setBackupStrategy(backupStrategy);
                config.setType(Type.forClass(this.getClass()));
                return config;
            }

            @Override
            public <T extends DataObject> T onFailedRequest(final RiotAPIService service, final RequestContext<T> context, final Response response,
                final OriannaException e) {
                final int attempts = context.attemptCount;
                if(attempts > maxAttempts) {
                    return backupStrategy.onFailedRequest(service, context, response, e);
                }

                try {
                    backoffUnit.sleep(backoff);
                } catch(final InterruptedException e1) {
                    return backupStrategy.onFailedRequest(service, context, response, e);
                }

                return service.get(context);
            }
        }

        public static class RetryFromHeaders implements FailedRequestStrategy {
            private static final FailedRequestStrategy DEFAULT_BACKUP_STRATEGY = new ExponentialBackoff();
            private final FailedRequestStrategy backupStrategy;

            public RetryFromHeaders() {
                this(new Configuration());
            }

            public RetryFromHeaders(final Configuration config) {
                backupStrategy = config.getBackupStrategy() == null ? DEFAULT_BACKUP_STRATEGY : config.getBackupStrategy();
            }

            @Override
            public Configuration getConfiguration() {
                final Configuration config = new Configuration();
                config.setBackupStrategy(backupStrategy);
                config.setType(Type.forClass(this.getClass()));
                return config;
            }

            @Override
            public <T extends DataObject> T onFailedRequest(final RiotAPIService service, final RequestContext<T> context, final Response response,
                final OriannaException e) {
                final Collection<String> retryAfterHeaders = response.getHeaders().get("Retry-After");
                if(retryAfterHeaders == null || retryAfterHeaders.isEmpty()) {
                    backupStrategy.onFailedRequest(service, context, response, e);
                }

                final long retryAfter = Long.parseLong(retryAfterHeaders.iterator().next());
                if(retryAfter <= 0) {
                    backupStrategy.onFailedRequest(service, context, response, e);
                }
                final String type = response.getHeaders().get("X-Rate-Limit-Type").iterator().next();

                final RateLimiter limiter = service.getRateLimiter(context.platform, context.rateLimiterName).limiter(type);
                limiter.restrictFor(retryAfter, TimeUnit.SECONDS);
                return service.get(context);
            }
        }

        public static class ReturnNull implements FailedRequestStrategy {
            public ReturnNull() {
                this(new Configuration());
            }

            public ReturnNull(final Configuration config) {

            }

            @Override
            public Configuration getConfiguration() {
                final Configuration config = new Configuration();
                config.setType(Type.forClass(this.getClass()));
                return config;
            }

            @Override
            public <T extends DataObject> T onFailedRequest(final RiotAPIService service, final RequestContext<T> context, final Response response,
                final OriannaException e) {
                return null;
            }
        }

        public static class Serializer extends JsonSerializer<FailedRequestStrategy> {
            @Override
            public void serialize(final FailedRequestStrategy strategy, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
                generator.writeObject(strategy.getConfiguration());
            }
        }

        public static class ThrowException implements FailedRequestStrategy {
            public ThrowException() {
                this(new Configuration());
            }

            public ThrowException(final Configuration config) {

            }

            @Override
            public Configuration getConfiguration() {
                final Configuration config = new Configuration();
                config.setType(Type.forClass(this.getClass()));
                return config;
            }

            @Override
            public <T extends DataObject> T onFailedRequest(final RiotAPIService service, final RequestContext<T> context, final Response response,
                final OriannaException e) {
                throw e;
            }
        }

        public static enum Type {
                EXPONENTIAL_BACKOFF(ExponentialBackoff.class),
                LINEAR_BACKOFF(LinearBackoff.class),
                RETRY_FROM_HEADERS(RetryFromHeaders.class),
                RETURN_NULL(ReturnNull.class),
                THROW_EXCEPTION(ThrowException.class);

            private static final Map<Class<? extends FailedRequestStrategy>, Type> FOR_CLASS = getForClass();

            public static Type forClass(final Class<? extends FailedRequestStrategy> clazz) {
                return FOR_CLASS.get(clazz);
            }

            private static final Map<Class<? extends FailedRequestStrategy>, Type> getForClass() {
                final Builder<Class<? extends FailedRequestStrategy>, Type> builder = ImmutableMap.builder();
                for(final Type type : values()) {
                    builder.put(type.type, type);
                }
                return builder.build();
            }

            private final Class<? extends FailedRequestStrategy> type;

            private Type(final Class<? extends FailedRequestStrategy> type) {
                this.type = type;
            }

            public Class<? extends FailedRequestStrategy> getType() {
                return type;
            }

        }

        @JsonValue
        public Configuration getConfiguration();

        public <T extends DataObject> T onFailedRequest(RiotAPIService service, RequestContext<T> context, Response response, OriannaException e);
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

    private static final Logger LOGGER = LoggerFactory.getLogger(RiotAPIService.class);

    private static Map<String, String> getDefaultHeaders(final Configuration config) {
        if(config.getApiKey() != null) {
            return ImmutableMap.of("Accept", "application/json", "X-Riot-Token", config.getApiKey());
        }
        return ImmutableMap.of("Accept", "application/json");
    }

    private static AbstractRateLimiter getSpecificLimiterForRate(final RateLimiter limiter, final String epochSeconds) {
        if(limiter instanceof AbstractRateLimiter) {
            return (AbstractRateLimiter)limiter;
        } else {
            final MultiRateLimiter multi = (MultiRateLimiter)limiter;
            return (AbstractRateLimiter)multi.limiter(epochSeconds);
        }
    }

    private final Map<Platform, Object> applicationRateLimiterLocks;
    private final Map<Platform, RateLimiter> applicationRateLimiters;
    private final HTTPClient client;
    private final Map<String, String> defaultHeaders;
    private final FailedRequestStrategy http404Strategy;
    private final FailedRequestStrategy http429Strategy;
    private final FailedRequestStrategy http500Strategy;
    private final FailedRequestStrategy http503Strategy;
    private final FailedRequestStrategy httpTimeoutStrategy;
    private final FailedRequestStrategy limiterTimeoutStrategy;
    private final double limitingShare;
    private final RateLimiter.Type limitingType;
    private final Map<Platform, Map<String, Object>> rateLimiterLocks;
    private final Map<Platform, Map<String, MultiRateLimiter>> rateLimiters;

    public RiotAPIService(final Configuration config, final HTTPClient client, final Map<Platform, RateLimiter> applicationRateLimiters,
        final Map<Platform, Object> applicationRateLimiterLocks) {
        this.client = client;
        this.applicationRateLimiters = applicationRateLimiters;
        this.applicationRateLimiterLocks = applicationRateLimiterLocks;
        defaultHeaders = getDefaultHeaders(config);
        http404Strategy = config.getHttp404Strategy();
        http429Strategy = config.getHttp429Strategy();
        http500Strategy = config.getHttp500Strategy();
        http503Strategy = config.getHttp503Strategy();
        httpTimeoutStrategy = config.getHttpTimeoutStrategy();
        limiterTimeoutStrategy = config.getRateLimiterTimeoutStrategy();
        limitingShare = config.getLimitingShare();
        limitingType = config.getLimitingType();

        rateLimiters = new ConcurrentHashMap<>();
        rateLimiterLocks = new ConcurrentHashMap<>();
    }

    private void adjustRateLimitsIfNecessary(final MultiRateLimiter multiLimiter, final Response response) {
        final Collection<String> applicationLimitHeaders = response.getHeaders().get("X-App-Rate-Limit");
        if(applicationLimitHeaders != null && !applicationLimitHeaders.isEmpty()) {
            final String[] limits = applicationLimitHeaders.iterator().next().split(",");
            final RateLimiter limiter = multiLimiter.limiter("application");
            for(final String limit : limits) {
                final String[] parts = limit.split(":");
                final AbstractRateLimiter forRate = getSpecificLimiterForRate(limiter, parts[1]);
                final int permits = (int)(Double.parseDouble(parts[0]) * limitingShare);
                if(permits != forRate.getPermits()) {
                    forRate.setPermits(permits);
                }
            }
        }

        final Collection<String> methodLimitHeaders = response.getHeaders().get("X-Method-Rate-Limit");
        if(methodLimitHeaders != null && !methodLimitHeaders.isEmpty()) {
            final String[] limits = methodLimitHeaders.iterator().next().split(",");
            RateLimiter limiter = multiLimiter.limiter("method");
            if(limiter == null) {
                limiter = multiLimiter;
            }
            for(final String limit : limits) {
                final String[] parts = limit.split(":");
                final AbstractRateLimiter forRate = getSpecificLimiterForRate(limiter, parts[1]);
                final int permits = (int)(Double.parseDouble(parts[0]) * limitingShare);
                if(permits != forRate.getPermits()) {
                    forRate.setPermits(permits);
                }
            }
        }
    }

    private void createRateLimiter(final Platform platform, final List<Long> epochsInSeconds, final List<Integer> limits, final long windowLowerBound,
        final long windowUpperBound) {
        RateLimiter limiter = applicationRateLimiters.get(platform);
        if(limiter == null) {
            synchronized(getCreateRateLimiterLock(platform)) {
                limiter = applicationRateLimiters.get(platform);
                if(limiter == null) {
                    limiter = newRateLimiter(epochsInSeconds, limits, windowLowerBound, windowUpperBound);
                    applicationRateLimiters.put(platform, limiter);
                }
            }
        }
    }

    private void createRateLimiter(final Platform platform, final String name, final List<Long> epochsInSeconds, final List<Integer> limits,
        final long windowLowerBound, final long windowUpperBound) {
        Map<String, MultiRateLimiter> limiters = rateLimiters.get(platform);
        if(limiters == null) {
            synchronized(rateLimiters) {
                limiters = rateLimiters.get(platform);
                if(limiters == null) {
                    limiters = new ConcurrentHashMap<>();
                    rateLimiters.put(platform, limiters);
                }
            }
        }

        MultiRateLimiter limiter = limiters.get(name);
        if(limiter == null) {
            synchronized(getCreateRateLimiterLock(platform, name)) {
                limiter = limiters.get(name);
                if(limiter == null) {
                    limiter = newRateLimiter(epochsInSeconds, limits, windowLowerBound, windowUpperBound);
                    final RateLimiter application = getRateLimiter(platform);
                    if(application != null) {
                        limiter = new MultiRateLimiter(ImmutableMap.of("application", application, "method", limiter));
                    }
                    try {
                        limiter.acquire();
                    } catch(final InterruptedException e) {
                        LOGGER.error("Request was interrupted while creating rate limiter!", e);
                        throw new OriannaException("Request was interrupted while creating rate limiter! Report this to the orianna team.", e);
                    }
                    limiter.release();
                    limiters.put(name, limiter);
                }
            }
        }
    }

    private void createRateLimiter(final Platform platform, final String rateLimiterName, final Response response, final long timeBeforeRequest,
        final long timeAfterRequest) {
        final Collection<String> applicationLimitHeaders = response.getHeaders().get("X-App-Rate-Limit");
        if(applicationLimitHeaders != null && !applicationLimitHeaders.isEmpty()) {
            final String[] limits = applicationLimitHeaders.iterator().next().split(",");
            final List<Long> epochsInSeconds = new ArrayList<>(limits.length);
            final List<Integer> epochLimits = new ArrayList<>(limits.length);
            for(final String limit : limits) {
                final String[] parts = limit.split(":");
                epochsInSeconds.add(Long.parseLong(parts[1]));
                epochLimits.add((int)(Double.parseDouble(parts[0]) * limitingShare));
            }
            createRateLimiter(platform, epochsInSeconds, epochLimits, timeBeforeRequest, timeAfterRequest);
        }

        final Collection<String> methodLimitHeaders = response.getHeaders().get("X-Method-Rate-Limit");
        if(methodLimitHeaders != null && !methodLimitHeaders.isEmpty()) {
            final String[] limits = methodLimitHeaders.iterator().next().split(",");
            final List<Long> epochsInSeconds = new ArrayList<>(limits.length);
            final List<Integer> epochLimits = new ArrayList<>(limits.length);
            for(final String limit : limits) {
                final String[] parts = limit.split(":");
                epochsInSeconds.add(Long.parseLong(parts[1]));
                epochLimits.add((int)(Double.parseDouble(parts[0]) * limitingShare));
            }
            createRateLimiter(platform, rateLimiterName, epochsInSeconds, epochLimits, timeBeforeRequest, timeAfterRequest);
        }
    }

    protected <T extends DataObject> T get(final Class<T> type, final String endpoint, final Platform platform) {
        final RequestContext<T> context = new RequestContext<>(type, endpoint, platform, null, null);
        return get(context);
    }

    protected <T extends DataObject> T get(final Class<T> type, final String endpoint, final Platform platform, final Map<String, String> parameters) {
        final RequestContext<T> context = new RequestContext<>(type, endpoint, platform,
            parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()), null);
        return get(context);
    }

    protected <T extends DataObject> T get(final Class<T> type, final String endpoint, final Platform platform, final Map<String, String> parameters,
        final String rateLimiterName) {
        final RequestContext<T> context = new RequestContext<>(type, endpoint, platform,
            parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()),
            rateLimiterName);
        return get(context);
    }

    protected <T extends DataObject> T get(final Class<T> type, final String endpoint, final Platform platform, final Multimap<String, String> parameters) {
        final RequestContext<T> context = new RequestContext<>(type, endpoint, platform, parameters, null);
        return get(context);
    }

    protected <T extends DataObject> T get(final Class<T> type, final String endpoint, final Platform platform, final Multimap<String, String> parameters,
        final String rateLimiterName) {
        final RequestContext<T> context = new RequestContext<>(type, endpoint, platform, parameters, rateLimiterName);
        return get(context);
    }

    protected <T extends DataObject> T get(final Class<T> type, final String endpoint, final Platform platform, final String rateLimiterName) {
        final RequestContext<T> context = new RequestContext<>(type, endpoint, platform, null, rateLimiterName);
        return get(context);
    }

    private <T extends DataObject> T get(final RequestContext<T> context) {
        context.attemptCount += 1;
        final String host = context.platform.getTag().toLowerCase() + ".api.riotgames.com";

        Response response = null;
        MultiRateLimiter limiter = getRateLimiter(context.platform, context.rateLimiterName);
        try {
            if(limiter == null) {
                synchronized(getCreateRateLimiterLock(context.platform, context.rateLimiterName)) {
                    limiter = getRateLimiter(context.platform, context.rateLimiterName);
                    if(limiter == null) {
                        final long timeBefore = System.currentTimeMillis();
                        response = client.get(host, context.endpoint, context.parameters, defaultHeaders, null);
                        final long timeAfter = System.currentTimeMillis();
                        createRateLimiter(context.platform, context.rateLimiterName, response, timeBefore, timeAfter);
                    }
                }
            }

            if(limiter != null) {
                response = client.get(host, context.endpoint, context.parameters, defaultHeaders, limiter);
            }
        } catch(final TimeoutException e) {
            LOGGER.info("Get request timed out to " + host + "/" + context.endpoint + "!", e);
            return Type.RATE_LIMITER == e.getType() ? limiterTimeoutStrategy.onFailedRequest(this, context, null, e)
                : httpTimeoutStrategy.onFailedRequest(this, context, null, e);
        } catch(final IOException e) {
            LOGGER.error("Get request failed to " + host + "/" + context.endpoint + "!", e);
            throw new OriannaException("Something went wrong with a request to the Riot API at " + host + "/" + context.endpoint
                + "! Report this to the orianna team.", e);
        }

        if(limiter != null) {
            adjustRateLimitsIfNecessary(limiter, response);
        }

        switch(response.getStatusCode()) {
            case 400:
                LOGGER.error("Got \"Bad Request\" from " + host + "/" + context.endpoint + "!");
                throw new BadRequestException("A Riot API request to " + host + "/" + context.endpoint
                    + " returned \"Bad Request\". If the problem persists, report this to the orianna team.");
            case 401:
                LOGGER.error("Got \"Unauthorized\" from " + host + "/" + context.endpoint + "!");
                throw new UnauthorizedException("A Riot API request to " + host + "/" + context.endpoint
                    + " returned \"Unauthorized\". Check to make sure you're using the right API key, it hasn't expired, and you haven't been blacklisted. If the problem persists with a valid key, report this to the orianna team.");
            case 403:
                LOGGER.error("Got \"Forbidden\" from " + host + "/" + context.endpoint + "!");
                throw new ForbiddenException("A Riot API request to " + host + "/" + context.endpoint
                    + " returned \"Forbidden\". Check to make sure you're using the right API key, it hasn't expired, and you haven't been blacklisted. If the problem persists with a valid key, report this to the orianna team.");
            case 404:
                LOGGER.info("Got \"Not Found\" from " + host + "/" + context.endpoint + "!");
                return http404Strategy.onFailedRequest(this, context, response,
                    new NotFoundException("A Riot API request to " + host + "/" + context.endpoint
                        + " returned \"Not Found\". If this was unexpected, check your query parameters to ensure they are correct."));
            case 415:
                LOGGER.error("Got \"Unsupported Media Type\" from " + host + "/" + context.endpoint + "!");
                throw new UnsupportedMediaTypeException("A Riot API request to " + host + "/" + context.endpoint
                    + " returned \"Unsupported Media Type\". If the problem persists, report this to the orianna team.");
            case 429:
                LOGGER.info("Got \"Rate Limit Exceeded (" + response.getHeaders().get("X-Rate-Limit-Type") + ")\" from " + host + "/" + context.endpoint + "!");
                return http429Strategy.onFailedRequest(this, context, response,
                    new RateLimitExceededException("A Riot API request to " + host + "/" + context.endpoint
                        + " returned \"Rate Limit Exceeded (" + response.getHeaders().get("X-Rate-Limit-Type")
                        + ")\". If this occurs frequently, report it to the orianna team."));
            case 500:
                LOGGER.error("Got \"Internal Server Error\" from " + host + "/" + context.endpoint + "!");
                return http500Strategy.onFailedRequest(this, context, response,
                    new InternalServerErrorException("A Riot API request to " + host + "/" + context.endpoint
                        + " returned \"Internal Server Error\". Sometimes the Riot API experiences these when under extreme load. If the problem persists, try catching this exception, waiting briefly, and trying again."));
            case 503:
                LOGGER.error("Got \"Service Unavailable\" from " + host + "/" + context.endpoint + "!");
                return http503Strategy.onFailedRequest(this, context, response,
                    new ServiceUnavailableException("A Riot API request to " + host + "/" + context.endpoint
                        + " returned \"Service Unavailable\". This Riot API Service is likely to be down for a short period of time, and can't be used in the meantime."));
            default:
                if(response.getStatusCode() >= 400) {
                    LOGGER.error("Get request to " + host + "/" + context.endpoint + " returned " + response.getStatusCode() + ": " + response.getBody());
                    throw new OriannaException("An unknown error code (" + response.getStatusCode() + ") was returned from the Riot API with message: "
                        + response.getBody());
                }
                break;
        }

        return DataObject.fromJSON(context.type, response.getBody());
    }

    private Object getCreateRateLimiterLock(final Platform platform) {
        Object lock = applicationRateLimiterLocks.get(platform);
        if(lock == null) {
            synchronized(applicationRateLimiterLocks) {
                lock = applicationRateLimiterLocks.get(platform);
                if(lock == null) {
                    lock = new Object();
                    applicationRateLimiterLocks.put(platform, lock);
                }
            }
        }
        return lock;
    }

    private Object getCreateRateLimiterLock(final Platform platform, final String name) {
        Map<String, Object> forPlatform = rateLimiterLocks.get(platform);
        if(forPlatform == null) {
            synchronized(rateLimiterLocks) {
                forPlatform = rateLimiterLocks.get(platform);
                if(forPlatform == null) {
                    forPlatform = new ConcurrentHashMap<>();
                    rateLimiterLocks.put(platform, forPlatform);
                }
            }
        }

        Object lock = forPlatform.get(name);
        if(lock == null) {
            synchronized(forPlatform) {
                lock = forPlatform.get(name);
                if(lock == null) {
                    lock = new Object();
                    forPlatform.put(name, lock);
                }
            }
        }

        return lock;
    }

    private RateLimiter getRateLimiter(final Platform platform) {
        return applicationRateLimiters.get(platform);
    }

    private MultiRateLimiter getRateLimiter(final Platform platform, final String name) {
        final Map<String, MultiRateLimiter> limiters = rateLimiters.get(platform);
        if(limiters == null) {
            return null;
        }

        return limiters.get(name);
    }

    private MultiRateLimiter newRateLimiter(final List<Long> epochsInSeconds, final List<Integer> limits, final long windowLowerBound,
        final long windowUpperBound) {
        final Map<String, AbstractRateLimiter> limiters = new HashMap<>();
        for(int i = 0; i < epochsInSeconds.size(); i++) {
            try {
                final AbstractRateLimiter limiter = limitingType.getLimiterClass().getConstructor(int.class, long.class, TimeUnit.class)
                    .newInstance(limits.get(i), epochsInSeconds.get(i), TimeUnit.SECONDS);
                final long windowLockoutIn = Math.max(0, TimeUnit.SECONDS.toMillis(epochsInSeconds.get(i)) + windowLowerBound - System.currentTimeMillis());
                limiter.restrict(windowLockoutIn, TimeUnit.MILLISECONDS, windowUpperBound - windowLowerBound, TimeUnit.MILLISECONDS);
                limiters.put(epochsInSeconds.get(i).toString(), limiter);
            } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
                LOGGER.error("Failed to instantiate " + limitingType + " Rate Limiter!", e);
                throw new OriannaException("Failed to instantiate " + limitingType + " Rate Limiter! Report this to the orianna team.", e);
            }

        }
        return new MultiRateLimiter(limiters);
    }
}
