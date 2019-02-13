package com.merakianalytics.orianna.datapipeline.kernel.data;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
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
import com.merakianalytics.orianna.datapipeline.kernel.data.Kernel.Configuration;
import com.merakianalytics.orianna.datapipeline.kernel.exceptions.GatewayTimeoutException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.BadRequestException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.InternalServerErrorException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.NotFoundException;
import com.merakianalytics.orianna.datapipeline.riotapi.exceptions.ServiceUnavailableException;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.data.CoreData;

public class KernelService extends AbstractDataSource {
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
            public <T extends CoreData> T onFailedRequest(final KernelService service, final RequestContext<T> context, final Response response,
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
            public <T extends CoreData> T onFailedRequest(final KernelService service, final RequestContext<T> context, final Response response,
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
            public <T extends CoreData> T onFailedRequest(final KernelService service, final RequestContext<T> context, final Response response,
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
            public <T extends CoreData> T onFailedRequest(final KernelService service, final RequestContext<T> context, final Response response,
                final OriannaException e) {
                throw e;
            }
        }

        public static enum Type {
                EXPONENTIAL_BACKOFF(ExponentialBackoff.class),
                LINEAR_BACKOFF(LinearBackoff.class),
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

        public <T extends CoreData> T onFailedRequest(KernelService service, RequestContext<T> context, Response response, OriannaException e);
    }

    private static class RequestContext<T> {
        public int attemptCount = 0;
        public String endpoint;
        public Multimap<String, String> parameters;
        public Class<T> type;

        public RequestContext(final Class<T> type, final String endpoint, final Multimap<String, String> parameters) {
            this.type = type;
            this.endpoint = endpoint;
            this.parameters = parameters;
            this.attemptCount = 1;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(KernelService.class);

    private static Map<String, String> getDefaultHeaders(final Configuration config) {
        if(config.isMsgpack()) {
            return ImmutableMap.of("Accept", "application/msgpack");
        }
        return ImmutableMap.of("Accept", "application/json");
    }

    private final HTTPClient client;
    private final Map<String, String> defaultHeaders;
    private final String host;
    private final FailedRequestStrategy http404Strategy;
    private final FailedRequestStrategy http500Strategy;
    private final FailedRequestStrategy http503Strategy;
    private final FailedRequestStrategy http504Strategy;
    private final FailedRequestStrategy httpTimeoutStrategy;
    private final boolean msgpack;
    private final int port;

    public KernelService(final Configuration config, final HTTPClient client) {
        this.client = client;
        host = config.getHost();
        port = config.getPort();
        msgpack = config.isMsgpack();
        defaultHeaders = getDefaultHeaders(config);
        http404Strategy = config.getHttp404Strategy();
        http500Strategy = config.getHttp500Strategy();
        http503Strategy = config.getHttp503Strategy();
        http504Strategy = config.getHttp504Strategy();
        httpTimeoutStrategy = config.getHttpTimeoutStrategy();
    }

    protected <T extends CoreData> T get(final Class<T> type, final String endpoint) {
        final RequestContext<T> context = new RequestContext<>(type, endpoint, null);
        return get(context);
    }

    protected <T extends CoreData> T get(final Class<T> type, final String endpoint, final Map<String, String> parameters) {
        final RequestContext<T> context = new RequestContext<>(type, endpoint, parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()));
        return get(context);
    }

    protected <T extends CoreData> T get(final Class<T> type, final String endpoint, final Multimap<String, String> parameters) {
        final RequestContext<T> context = new RequestContext<>(type, endpoint, parameters);
        return get(context);
    }

    private <T extends CoreData> T get(final RequestContext<T> context) {
        context.attemptCount += 1;

        Response response = null;
        try {
            response = client.get(host, port, context.endpoint, context.parameters, defaultHeaders);
        } catch(final TimeoutException e) {
            LOGGER.info("Get request timed out to " + host + "/" + context.endpoint + "!", e);
            return httpTimeoutStrategy.onFailedRequest(this, context, null, e);
        } catch(final IOException e) {
            LOGGER.error("Get request failed to " + host + "/" + context.endpoint + "!", e);
            throw new OriannaException("Something went wrong with a request to Kernel at " + host + "/" + context.endpoint
                + "! Report this to the orianna team.", e);
        }

        switch(response.getStatusCode()) {
            case 400:
                LOGGER.error("Got \"Bad Request\" from " + host + "/" + context.endpoint + "!");
                throw new BadRequestException("A Kernel request to " + host + "/" + context.endpoint
                    + " returned \"Bad Request\". If the problem persists, report this to the orianna team.");
            case 404:
                LOGGER.info("Got \"Not Found\" from " + host + "/" + context.endpoint + "!");
                return http404Strategy.onFailedRequest(this, context, response,
                    new NotFoundException("A Kernel request to " + host + "/" + context.endpoint
                        + " returned \"Not Found\". If this was unexpected, check your query parameters to ensure they are correct."));
            case 500:
                LOGGER.error("Got \"Internal Server Error\" from " + host + "/" + context.endpoint + "!");
                return http500Strategy.onFailedRequest(this, context, response,
                    new InternalServerErrorException("A Kernel request to " + host + "/" + context.endpoint
                        + " returned \"Internal Server Error\". Check the Kernel logs for errors. Sometimes the Riot API experiences these when under extreme load and Kernel will forward them - if that problem persists, try catching this exception, waiting briefly, and trying again."));
            case 503:
                LOGGER.error("Got \"Service Unavailable\" from " + host + "/" + context.endpoint + "!");
                return http503Strategy.onFailedRequest(this, context, response,
                    new ServiceUnavailableException("A Kernel request to " + host + "/" + context.endpoint
                        + " returned \"Service Unavailable\". The Riot API is likely to be down for a short period of time, and can't be used in the meantime."));
            case 504:
                final TimeoutException.Type type = TimeoutException.Type.valueOf(response.getHeaders().get("X-Timeout-Type").iterator().next());
                LOGGER.error("Got \"Gateway Timeout\" from " + host + "/" + context.endpoint + "!");
                return http504Strategy.onFailedRequest(this, context, response,
                    new GatewayTimeoutException("A Kernel request to " + host + "/" + context.endpoint
                        + " returned \"Gateway Timeout\". This may mean connections to the Riot API are very slow right now.", type));
            default:
                if(response.getStatusCode() >= 400) {
                    LOGGER.error("Get request to " + host + "/" + context.endpoint + " returned " + response.getStatusCode() + ": " + response.getBody());
                    throw new OriannaException("An unknown error code (" + response.getStatusCode() + ") was returned from the Kernel with message: "
                        + response.getBody());
                }
                break;
        }

        return msgpack ? CoreData.fromBytes(context.type, response.getBytes()) : CoreData.fromJSON(context.type, response.getBody());
    }
}
