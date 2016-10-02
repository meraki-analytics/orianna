package com.robrua.orianna.type.api;

import com.robrua.orianna.type.core.common.Region;

import java.util.*;

/**
 * @author Dominic Gunn (Dominic.Gunn@protonmail.ch)
 */
public class RegionSpecificRateLimiter implements RateLimiter {

    private Map<Region, Set<SingleRateLimiter>> regionRateLimits = new HashMap<>();

    public RegionSpecificRateLimiter(final RateLimit... rateLimits) {
        this(Arrays.asList(rateLimits));
    }

    public RegionSpecificRateLimiter(final Collection<RateLimit> rateLimits) {
        for (Region region : Region.values()) {
            HashSet<SingleRateLimiter> regionLimits = new HashSet<>();
            for(final RateLimit limit : rateLimits) {
                regionLimits.add(new SingleRateLimiter(limit));
            }
            regionRateLimits.put(region, regionLimits);
        }
    }

    @Override
    public void registerCall(Region region) {
        for(final RateLimiter rateLimiter : regionRateLimits.get(region)) {
            rateLimiter.registerCall(region);
        }
    }

    @Override
    public void resetIn(Region region, long millis) {
        for(final RateLimiter limit : regionRateLimits.get(region)) {
            limit.resetIn(region, millis);
        }
    }

    @Override
    public void waitForCall(Region region) {
        for(final RateLimiter limit : regionRateLimits.get(region)) {
            limit.waitForCall(region);
        }
    }
}
