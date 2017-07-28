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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class HTTPClient {
    public static class Configuration {
        private static final long DEFAULT_CONNECT_TIMEOUT = 5;
        private static final TimeUnit DEFAULT_CONNECT_TIMEOUT_UNIT = TimeUnit.SECONDS;
        private static final long DEFAULT_RATE_LIMITER_TIMEOUT = 30;
        private static final TimeUnit DEFAULT_RATE_LIMITER_TIMEOUT_UNIT = TimeUnit.SECONDS;
        private static final long DEFAULT_READ_TIMEOUT = 5;
        private static final TimeUnit DEFAULT_READ_TIMEOUT_UNIT = TimeUnit.SECONDS;

        private long connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        private TimeUnit connectTimeoutUnit = DEFAULT_CONNECT_TIMEOUT_UNIT;
        private long rateLimiterTimeout = DEFAULT_RATE_LIMITER_TIMEOUT;
        private TimeUnit rateLimiterTimeoutUnit = DEFAULT_RATE_LIMITER_TIMEOUT_UNIT;
        private long readTimeout = DEFAULT_READ_TIMEOUT;
        private TimeUnit readTimeoutUnit = DEFAULT_READ_TIMEOUT_UNIT;

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
            if(connectTimeout != other.connectTimeout) {
                return false;
            }
            if(connectTimeoutUnit != other.connectTimeoutUnit) {
                return false;
            }
            if(rateLimiterTimeout != other.rateLimiterTimeout) {
                return false;
            }
            if(rateLimiterTimeoutUnit != other.rateLimiterTimeoutUnit) {
                return false;
            }
            if(readTimeout != other.readTimeout) {
                return false;
            }
            if(readTimeoutUnit != other.readTimeoutUnit) {
                return false;
            }
            return true;
        }

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

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int)(connectTimeout ^ connectTimeout >>> 32);
            result = prime * result + (connectTimeoutUnit == null ? 0 : connectTimeoutUnit.hashCode());
            result = prime * result + (int)(rateLimiterTimeout ^ rateLimiterTimeout >>> 32);
            result = prime * result + (rateLimiterTimeoutUnit == null ? 0 : rateLimiterTimeoutUnit.hashCode());
            result = prime * result + (int)(readTimeout ^ readTimeout >>> 32);
            result = prime * result + (readTimeoutUnit == null ? 0 : readTimeoutUnit.hashCode());
            return result;
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
        private final Multimap<String, String> headers;
        private final int statusCode;

        public Response(final String body, final int statusCode, final Multimap<String, String> headers) {
            this.body = body;
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
    }

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
                String body;
                int statusCode;
                Headers responseHeaders;
                try(okhttp3.Response response = client.newCall(request).execute()) {
                    statusCode = response.code();
                    responseHeaders = response.headers();
                    try(ResponseBody responseBody = response.body()) {
                        body = responseBody.string();
                    }
                } catch(final SocketTimeoutException e) {
                    throw new TimeoutException("HTTP GET request timed out!", Type.HTTP);
                }

                ImmutableListMultimap.Builder<String, String> mapBuilder = ImmutableListMultimap.<String, String> builder();
                for(final String key : responseHeaders.names()) {
                    mapBuilder = mapBuilder.putAll(key, responseHeaders.get(key));
                }
                return new Response(body, statusCode, mapBuilder.build());
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
