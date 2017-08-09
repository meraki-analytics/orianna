package com.merakianalytics.orianna.datapipeline.common.rates;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public interface RateLimiter {
    public static class Configuration {
        public static Configuration of(final int permits, final long epoch, final TimeUnit epochUnit) {
            final Configuration config = new Configuration();
            config.setPermits(permits);
            config.setEpoch(epoch);
            config.setEpochUnit(epochUnit);
            return config;
        }

        public static Configuration of(final Type type, final int permits, final long epoch, final TimeUnit epochUnit) {
            final Configuration config = new Configuration();
            config.setType(type);
            config.setPermits(permits);
            config.setEpoch(epoch);
            config.setEpochUnit(epochUnit);
            return config;
        }

        private long epoch;

        private TimeUnit epochUnit;
        private int permits;
        private Type type = Type.BURST;

        public Configuration() {

        }

        /**
         * @return the epoch
         */
        public long getEpoch() {
            return epoch;
        }

        /**
         * @return the epochUnit
         */
        public TimeUnit getEpochUnit() {
            return epochUnit;
        }

        /**
         * @return the permits
         */
        public int getPermits() {
            return permits;
        }

        /**
         * @return the type
         */
        public Type getType() {
            return type;
        }

        /**
         * @param epoch
         *        the epoch to set
         */
        public void setEpoch(final long epoch) {
            this.epoch = epoch;
        }

        /**
         * @param epochUnit
         *        the epochUnit to set
         */
        public void setEpochUnit(final TimeUnit epochUnit) {
            this.epochUnit = epochUnit;
        }

        /**
         * @param permits
         *        the permits to set
         */
        public void setPermits(final int permits) {
            this.permits = permits;
        }

        /**
         * @param type
         *        the type to set
         */
        public void setType(final Type type) {
            this.type = type;
        }
    }

    public static interface ReservedPermit {
        public void acquire();

        public void cancel();
    }

    public static enum Type {
        BURST(FixedWindowRateLimiter.class),
        NONE(null);

        private final Class<? extends AbstractRateLimiter> clazz;

        private Type(final Class<? extends AbstractRateLimiter> clazz) {
            this.clazz = clazz;
        }

        public Class<? extends AbstractRateLimiter> getLimiterClass() {
            return clazz;
        }
    }

    public void acquire() throws InterruptedException;

    public boolean acquire(final long timeout, final TimeUnit unit) throws InterruptedException;

    public <T> T call(final Callable<T> callable) throws InterruptedException, Exception;

    public <T> T call(final Callable<T> callable, final long timeout, final TimeUnit unit) throws InterruptedException, Exception;

    public void call(final Runnable runnable) throws InterruptedException;

    public void call(final Runnable runnable, final long timeout, final TimeUnit unit) throws InterruptedException;

    public int permitsIssued();

    public void release();

    public ReservedPermit reserve() throws InterruptedException;

    public ReservedPermit reserve(final long timeout, final TimeUnit unit) throws InterruptedException;

    public void restrictFor(final long time, final TimeUnit unit);
}
