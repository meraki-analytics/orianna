package com.merakianalytics.orianna.datapipeline.common.rates;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.ImmutableSet;

public interface RateLimiter {
    public static class Configuration {
        public static Set<Configuration> defaultDeveloperRateLimits() {
            final Configuration perSecond = new Configuration();
            perSecond.setEpoch(1);
            perSecond.setEpochUnit(TimeUnit.SECONDS);
            perSecond.setPermits(20);

            final Configuration per2Minutes = new Configuration();
            per2Minutes.setEpoch(2);
            per2Minutes.setEpochUnit(TimeUnit.MINUTES);
            per2Minutes.setPermits(100);

            return ImmutableSet.of(perSecond, per2Minutes);
        }

        private long epoch;
        private TimeUnit epochUnit;
        private int permits;
        private Type type = Type.BURST; // TODO: Change to SMOOTH

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
            if(epoch != other.epoch) {
                return false;
            }
            if(epochUnit != other.epochUnit) {
                return false;
            }
            if(permits != other.permits) {
                return false;
            }
            if(type != other.type) {
                return false;
            }
            return true;
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

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int)(epoch ^ epoch >>> 32);
            result = prime * result + (epochUnit == null ? 0 : epochUnit.hashCode());
            result = prime * result + permits;
            result = prime * result + (type == null ? 0 : type.hashCode());
            return result;
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

    public static enum Type {
        BURST(FixedWindowRateLimiter.class),
        NONE(null),
        SMOOTH(null); // TODO

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

    public void restrictFor(final long time, final TimeUnit unit);
}
