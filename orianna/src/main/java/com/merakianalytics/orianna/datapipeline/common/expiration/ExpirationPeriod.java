package com.merakianalytics.orianna.datapipeline.common.expiration;

import java.util.concurrent.TimeUnit;

public class ExpirationPeriod {
    public static ExpirationPeriod create(final long time, final TimeUnit unit) {
        final ExpirationPeriod period = new ExpirationPeriod();
        period.setPeriod(time);
        period.setUnit(unit);
        return period;
    }

    private long period;
    private TimeUnit unit;

    private ExpirationPeriod() {}

    /**
     * @return the period
     */
    public long getPeriod() {
        return period;
    }

    /**
     * @return the unit
     */
    public TimeUnit getUnit() {
        return unit;
    }

    /**
     * @param period
     *        the period to set
     */
    public void setPeriod(final long period) {
        this.period = period;
    }

    /**
     * @param unit
     *        the unit to set
     */
    public void setUnit(final TimeUnit unit) {
        this.unit = unit;
    }
}
