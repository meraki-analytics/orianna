package com.merakianalytics.orianna.datapipeline;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.datapipelines.sources.AbstractDataSource;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.TimeoutException;
import com.merakianalytics.orianna.datapipeline.common.expiration.ExpirationPeriod;
import com.merakianalytics.orianna.types.common.OriannaException;

public abstract class AbstractLocallyCachedCDN<T> extends AbstractDataSource {
    protected static class Configuration {
        private ExpirationPeriod cacheDuration;
        private HTTPClient.Configuration requests;

        /**
         * @return the cacheDuration
         */
        public ExpirationPeriod getCacheDuration() {
            return cacheDuration;
        }

        /**
         * @return the requests
         */
        public HTTPClient.Configuration getRequests() {
            return requests;
        }

        /**
         * @param cacheDuration
         *        the cacheDuration to set
         */
        public void setCacheDuration(final ExpirationPeriod cacheDuration) {
            this.cacheDuration = cacheDuration;
        }

        /**
         * @param requests
         *        the requests to set
         */
        public void setRequests(final HTTPClient.Configuration requests) {
            this.requests = requests;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractLocallyCachedCDN.class);

    private final ConcurrentHashMap<T, Supplier<String>> cache = new ConcurrentHashMap<>();
    private final ExpirationPeriod cacheDuration;
    private final ConcurrentHashMap<T, Object> cacheLocks = new ConcurrentHashMap<>();
    private final HTTPClient client;

    public AbstractLocallyCachedCDN() {
        this(new Configuration());
    }

    public AbstractLocallyCachedCDN(final Configuration config) {
        client = new HTTPClient(config.getRequests());
        cacheDuration = config.getCacheDuration();
    }

    protected String get(final T request) {
        Supplier<String> supplier = cache.get(request);
        if(supplier == null) {
            Object lock = cacheLocks.get(request);
            if(lock == null) {
                synchronized(cacheLocks) {
                    lock = cacheLocks.get(request);
                    if(lock == null) {
                        lock = new Object();
                        cacheLocks.put(request, lock);
                    }
                }
            }

            synchronized(lock) {
                supplier = cache.get(request);
                if(supplier == null) {
                    supplier = new Supplier<String>() {
                        @Override
                        public String get() {
                            final String URL = getURL(request);

                            try {
                                return client.get(URL).getBody();
                            } catch(final TimeoutException e) {
                                LOGGER.info("Get request timed out to " + URL + "!", e);
                                return null;
                            } catch(final IOException e) {
                                LOGGER.error("Get request failed to " + URL + "!", e);
                                throw new OriannaException("Something went wrong with a request to " + URL + "! Report this to the orianna team.", e);
                            }
                        }
                    };

                    if(cacheDuration.getPeriod() < 0) {
                        supplier = Suppliers.memoize(supplier);
                    } else if(cacheDuration.getPeriod() > 0) {
                        supplier = Suppliers.memoizeWithExpiration(supplier, cacheDuration.getPeriod(), cacheDuration.getUnit());
                    }
                    cache.put(request, supplier);
                }
            }
        }

        return supplier.get();
    }

    protected abstract String getURL(T request);
}
