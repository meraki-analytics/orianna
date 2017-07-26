package com.merakianalytics.orianna.datapipeline.common;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimap;
import com.merakianalytics.orianna.type.common.OriannaException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class HTTPClient {
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

    private static final long DEFAULT_CONNECT_TIMEOUT = 5;
    private static final TimeUnit DEFAULT_CONNECT_TIMEOUT_UNIT = TimeUnit.SECONDS;
    private static final long DEFAULT_READ_TIMEOUT = 5;
    private static final TimeUnit DEFAULT_READ_TIMEOUT_UNIT = TimeUnit.SECONDS;
    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPClient.class);

    private final OkHttpClient client;

    public HTTPClient() {
        this(DEFAULT_CONNECT_TIMEOUT, DEFAULT_CONNECT_TIMEOUT_UNIT, DEFAULT_READ_TIMEOUT, DEFAULT_READ_TIMEOUT_UNIT);
    }

    public HTTPClient(final long connectTimeout, final TimeUnit connectTimeoutUnit, final long readTimeout, final TimeUnit readTimeoutUnit) {
        client = new OkHttpClient.Builder().connectTimeout(connectTimeout, connectTimeoutUnit).readTimeout(readTimeout, readTimeoutUnit).build();
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

        LOGGER.info("Making GET request to " + httpURL);
        String body;
        int statusCode;
        Headers responseHeaders;
        if(rateLimiter != null) {
            // TODO: Rate limited call
            body = null;
            statusCode = -1;
            responseHeaders = null;
        } else {
            try(okhttp3.Response response = client.newCall(request).execute()) {
                statusCode = response.code();
                responseHeaders = response.headers();
                try(ResponseBody responseBody = response.body()) {
                    body = responseBody.string();
                }
            } catch(SocketTimeoutException e) {
                throw new TimeoutException("HTTP GET request timed out!");
            }
        }

        ImmutableListMultimap.Builder<String, String> mapBuilder = ImmutableListMultimap.<String, String> builder();
        for(final String key : responseHeaders.names()) {
            mapBuilder = mapBuilder.putAll(key, responseHeaders.get(key));
        }
        return new Response(body, statusCode, mapBuilder.build());
    }
    
    public static class TimeoutException extends OriannaException {
        private static final long serialVersionUID = 1728889991776994308L;

        public TimeoutException(String message) {
            super(message);
        }
    }
}
