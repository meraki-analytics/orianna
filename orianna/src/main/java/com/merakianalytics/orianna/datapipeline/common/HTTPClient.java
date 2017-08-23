package com.merakianalytics.orianna.datapipeline.common;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimap;
import com.merakianalytics.orianna.datapipeline.common.TimeoutException.Type;
import com.merakianalytics.orianna.datapipeline.common.rates.RateLimiter;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class HTTPClient {
    public static class Configuration {
        private static final long DEFAULT_CONNECT_TIMEOUT = 3;
        private static final TimeUnit DEFAULT_CONNECT_TIMEOUT_UNIT = TimeUnit.SECONDS;
        private static final long DEFAULT_RATE_LIMITER_TIMEOUT = 30;
        private static final TimeUnit DEFAULT_RATE_LIMITER_TIMEOUT_UNIT = TimeUnit.SECONDS;
        private static final long DEFAULT_READ_TIMEOUT = 3;
        private static final TimeUnit DEFAULT_READ_TIMEOUT_UNIT = TimeUnit.SECONDS;

        private long connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        private TimeUnit connectTimeoutUnit = DEFAULT_CONNECT_TIMEOUT_UNIT;
        private long rateLimiterTimeout = DEFAULT_RATE_LIMITER_TIMEOUT;
        private TimeUnit rateLimiterTimeoutUnit = DEFAULT_RATE_LIMITER_TIMEOUT_UNIT;
        private long readTimeout = DEFAULT_READ_TIMEOUT;
        private TimeUnit readTimeoutUnit = DEFAULT_READ_TIMEOUT_UNIT;

        /**
         * @return the connectTimeout
         */
        public long getConnectTimeout() {
            return connectTimeout;
        }

        /**
         * @return the connectTimeoutUnit
         */
        public TimeUnit getConnectTimeoutUnit() {
            return connectTimeoutUnit;
        }

        /**
         * @return the rateLimiterTimeout
         */
        public long getRateLimiterTimeout() {
            return rateLimiterTimeout;
        }

        /**
         * @return the rateLimiterTimeoutUnit
         */
        public TimeUnit getRateLimiterTimeoutUnit() {
            return rateLimiterTimeoutUnit;
        }

        /**
         * @return the readTimeout
         */
        public long getReadTimeout() {
            return readTimeout;
        }

        /**
         * @return the readTimeoutUnit
         */
        public TimeUnit getReadTimeoutUnit() {
            return readTimeoutUnit;
        }

        /**
         * @param connectTimeout
         *        the connectTimeout to set
         */
        public void setConnectTimeout(final long connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        /**
         * @param connectTimeoutUnit
         *        the connectTimeoutUnit to set
         */
        public void setConnectTimeoutUnit(final TimeUnit connectTimeoutUnit) {
            this.connectTimeoutUnit = connectTimeoutUnit;
        }

        /**
         * @param rateLimiterTimeout
         *        the rateLimiterTimeout to set
         */
        public void setRateLimiterTimeout(final long rateLimiterTimeout) {
            this.rateLimiterTimeout = rateLimiterTimeout;
        }

        /**
         * @param rateLimiterTimeoutUnit
         *        the rateLimiterTimeoutUnit to set
         */
        public void setRateLimiterTimeoutUnit(final TimeUnit rateLimiterTimeoutUnit) {
            this.rateLimiterTimeoutUnit = rateLimiterTimeoutUnit;
        }

        /**
         * @param readTimeout
         *        the readTimeout to set
         */
        public void setReadTimeout(final long readTimeout) {
            this.readTimeout = readTimeout;
        }

        /**
         * @param readTimeoutUnit
         *        the readTimeoutUnit to set
         */
        public void setReadTimeoutUnit(final TimeUnit readTimeoutUnit) {
            this.readTimeoutUnit = readTimeoutUnit;
        }
    }

    public static class Response {
        private final String body;

        private final byte[] bytes;
        private final Multimap<String, String> headers;
        private final int statusCode;

        public Response(final byte[] bytes, final int statusCode, final Multimap<String, String> headers) {
            this.bytes = bytes;
            body = null;
            this.statusCode = statusCode;
            this.headers = headers;
        }

        public Response(final String body, final int statusCode, final Multimap<String, String> headers) {
            this.body = body;
            bytes = null;
            this.statusCode = statusCode;
            this.headers = headers;
        }

        /**
         * @return the body
         */
        public String getBody() {
            return body;
        }

        /**
         * @return the bytes
         */
        public byte[] getBytes() {
            return bytes;
        }

        /**
         * @return the headers
         */
        public Multimap<String, String> getHeaders() {
            return headers;
        }

        /**
         * @return the statusCode
         */
        public int getStatusCode() {
            return statusCode;
        }

        public boolean isString() {
            return body != null;
        }
    }

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json");
    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPClient.class);

    private final OkHttpClient client;
    private final long rateLimiterTimeout;
    private final TimeUnit rateLimiterTimeoutUnit;

    public HTTPClient() {
        this(new Configuration());
    }

    public HTTPClient(final Configuration config) {
        client = new OkHttpClient.Builder().connectTimeout(config.getConnectTimeout(), config.getConnectTimeoutUnit())
                                           .readTimeout(config.getReadTimeout(), config.getReadTimeoutUnit()).build();
        rateLimiterTimeout = config.getRateLimiterTimeout();
        rateLimiterTimeoutUnit = config.getRateLimiterTimeoutUnit();
    }

    public Response get(final String url) throws IOException {
        final HttpUrl parsed = HttpUrl.parse(url);
        return get(parsed.host(), parsed.encodedPath(), (Multimap<String, String>)null, null, null);
    }

    public Response get(final String host, final String url) throws IOException {
        return get(host, url, (Multimap<String, String>)null, null, null);
    }

    public Response get(final String host, final String url, final Map<String, String> parameters, final Map<String, String> headers) throws IOException {
        return get(host, url, parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()), headers, null);
    }

    public Response get(final String host, final String url, final Map<String, String> parameters, final Map<String, String> headers,
                        final RateLimiter rateLimiter) throws IOException {
        return get(host, url, parameters == null ? null : ImmutableListMultimap.copyOf(parameters.entrySet()), headers, rateLimiter);
    }

    public Response get(final String host, final String url, final Multimap<String, String> parameters, final Map<String, String> headers) throws IOException {
        return get(host, url, parameters, headers, null);
    }

    public Response get(final String host, final String url, final Multimap<String, String> parameters, final Map<String, String> headers,
                        final RateLimiter rateLimiter) throws IOException {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder().scheme("https").host(host).addPathSegments(url);
        if(parameters != null && !parameters.isEmpty()) {
            for(final String key : parameters.keySet()) {
                for(final String value : parameters.get(key)) {
                    urlBuilder = urlBuilder.addQueryParameter(key, value);
                }
            }
        }
        final HttpUrl httpURL = urlBuilder.build();

        Request.Builder requestBuilder = new Request.Builder().url(httpURL);
        if(headers != null && !headers.isEmpty()) {
            requestBuilder = requestBuilder.headers(Headers.of(headers));
        }
        final Request request = requestBuilder.build();

        final Callable<Response> requestor = new Callable<Response>() {
            @Override
            public Response call() throws IOException {
                LOGGER.info("Making GET request to " + httpURL);
                String body = null;
                byte[] bytes = null;
                int statusCode;
                Headers responseHeaders;
                try(okhttp3.Response response = client.newCall(request).execute()) {
                    statusCode = response.code();
                    responseHeaders = response.headers();
                    try(ResponseBody responseBody = response.body()) {
                        if(JSON_MEDIA_TYPE.type().equals(responseBody.contentType().type())
                           && JSON_MEDIA_TYPE.subtype().equals(responseBody.contentType().subtype())) {
                            body = responseBody.string();
                        } else {
                            bytes = responseBody.bytes();
                        }
                    }
                } catch(final SocketTimeoutException e) {
                    throw new TimeoutException("HTTP GET request timed out!", Type.HTTP);
                }

                ImmutableListMultimap.Builder<String, String> mapBuilder = ImmutableListMultimap.<String, String> builder();
                for(final String key : responseHeaders.names()) {
                    mapBuilder = mapBuilder.putAll(key, responseHeaders.get(key));
                }

                if(body != null) {
                    return new Response(body, statusCode, mapBuilder.build());
                } else {
                    return new Response(bytes, statusCode, mapBuilder.build());
                }
            }
        };

        if(rateLimiter == null) {
            try {
                return requestor.call();
            } catch(TimeoutException | IOException e) {
                throw e;
            } catch(final Exception e) {
                LOGGER.error("Unexpected error performing GET request!", e);
                throw new RuntimeException(e);
            }
        } else {
            try {
                return rateLimiter.call(requestor, rateLimiterTimeout, rateLimiterTimeoutUnit);
            } catch(final TimeoutException e) {
                throw e;
            } catch(final Exception e) {
                LOGGER.error("Unexpected error performing GET request!", e);
                throw new RuntimeException(e);
            }
        }
    }
}
