package com.merakianalytics.orianna.datapipeline.common.rates;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FixedWindowRateLimiter extends AbstractRateLimiter {
    private class Drainer extends TimerTask {
        private boolean cancelled = false;

        @Override
        public void run() {
            synchronized(resetterLock) {
                if(!cancelled) {
                    permitter.drainPermits();
                }
            }
        }
    }

    private class Resetter extends TimerTask {
        private boolean cancelled = false;

        @Override
        public void run() {
            synchronized(resetterLock) {
                if(!cancelled) {
                    permitter.drainPermits();
                    synchronized(currentlyProcessingLock) {
                        permitter.release(permits - currentlyProcessing);
                    }
                    resetter = null;
                    drainer = null;
                    timer.purge();
                }
            }
        }
    }

    private int currentlyProcessing = 0;
    private final Object currentlyProcessingLock = new Object();
    private Drainer drainer = null;
    private final long epoch;
    private final TimeUnit epochUnit;
    private int permits;
    private final Semaphore permitter;
    private Resetter resetter = null;
    private final Object resetterLock = new Object();
    private final Timer timer = new Timer(true);

    public FixedWindowRateLimiter(final int permits, final long epoch, final TimeUnit epochUnit) {
        super(permits, epoch, epochUnit);
        this.permits = permits;
        this.epoch = epoch;
        this.epochUnit = epochUnit;
        permitter = new Semaphore(permits, true);
    }

    @Override
    public void acquire() throws InterruptedException {
        permitter.acquire();

        if(drainer == null) {
            synchronized(resetterLock) {
                if(drainer == null) {
                    drainer = new Drainer();
                    timer.schedule(drainer, epochUnit.toMillis(epoch));
                }
            }
        }

        synchronized(currentlyProcessingLock) {
            currentlyProcessing += 1;
        }
    }

    @Override
    public boolean acquire(final long timeout, final TimeUnit unit) throws InterruptedException {
        if(!permitter.tryAcquire(timeout, unit)) {
            return false;
        }

        synchronized(currentlyProcessingLock) {
            currentlyProcessing += 1;
        }

        if(drainer == null) {
            synchronized(resetterLock) {
                if(drainer == null) {
                    drainer = new Drainer();
                    timer.schedule(drainer, epochUnit.toMillis(epoch));
                }
            }
        }

        return true;
    }

    @Override
    public long getEpoch() {
        return epoch;
    }

    @Override
    public TimeUnit getEpochUnit() {
        return epochUnit;
    }

    @Override
    public int getPermits() {
        synchronized(resetterLock) {
            return permits;
        }
    }

    @Override
    public void release() {
        synchronized(currentlyProcessingLock) {
            currentlyProcessing -= 1;
        }

        if(resetter == null) {
            synchronized(resetterLock) {
                if(resetter == null) {
                    resetter = new Resetter();
                    timer.schedule(resetter, epochUnit.toMillis(epoch));
                }
            }
        }
    }

    @Override
    public ReservedPermit reserve() throws InterruptedException {
        permitter.acquire();

        synchronized(currentlyProcessingLock) {
            currentlyProcessing += 1;
        }

        return new ReservedPermit() {
            @Override
            public void acquire() {}

            @Override
            public void cancel() {
                synchronized(currentlyProcessingLock) {
                    currentlyProcessing -= 1;
                    permitter.release();
                }
            }
        };
    }

    @Override
    public ReservedPermit reserve(final long timeout, final TimeUnit unit) throws InterruptedException {
        if(!permitter.tryAcquire(timeout, unit)) {
            return null;
        }

        synchronized(currentlyProcessingLock) {
            currentlyProcessing += 1;
        }

        return new ReservedPermit() {
            @Override
            public void acquire() {
                if(drainer == null) {
                    synchronized(resetterLock) {
                        if(drainer == null) {
                            drainer = new Drainer();
                            timer.schedule(drainer, epochUnit.toMillis(epoch));
                        }
                    }
                }
            }

            @Override
            public void cancel() {
                synchronized(currentlyProcessingLock) {
                    currentlyProcessing -= 1;
                    permitter.release();
                }
            }
        };
    }

    @Override
    public void restrict(final long afterTime, final TimeUnit afterUnit, final long forTime, final TimeUnit forUnit) {
        synchronized(resetterLock) {
            if(drainer != null) {
                drainer.cancel();
                drainer.cancelled = true;
            }

            if(resetter != null) {
                resetter.cancel();
                resetter.cancelled = true;
            }

            drainer = new Drainer();
            timer.schedule(drainer, afterUnit.toMillis(afterTime));
            resetter = new Resetter();
            timer.schedule(resetter, forUnit.toMillis(forTime));
        }
    }

    @Override
    public void restrictFor(final long time, final TimeUnit unit) {
        synchronized(resetterLock) {
            permitter.drainPermits();

            if(drainer != null) {
                drainer.cancel();
                drainer.cancelled = true;
            }

            if(resetter != null) {
                resetter.cancel();
                resetter.cancelled = true;
            }

            resetter = new Resetter();
            timer.schedule(resetter, unit.toMillis(time));
        }
    }

    @Override
    public void setPermits(final int permits) {
        synchronized(resetterLock) {
            final int difference = permits - this.permits;
            if(difference > 0) {
                permitter.release(difference);
            } else if(difference < 0) {
                if(!permitter.tryAcquire(difference)) {
                    permitter.drainPermits();
                }
            }

            this.permits = permits;
        }
    }
}
