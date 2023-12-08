package com.merakianalytics.orianna.types.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.merakianalytics.orianna.types.core.spectator.FeaturedMatches;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Realm;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.status.ShardStatus;

public enum Platform {
    BRAZIL("BR1", "pt_BR", "AMERICAS"),
    EUROPE_NORTH_EAST("EUN1", "en_GB", "EUROPE"),
    EUROPE_WEST("EUW1", "en_GB", "EUROPE"),
    JAPAN("JP1", "ja_JP", "ASIA"),
    KOREA("KR", "ko_KR", "ASIA"),
    LATIN_AMERICA_NORTH("LA1", "es_MX", "AMERICAS"),
    LATIN_AMERICA_SOUTH("LA2", "es_AR", "AMERICAS"),
    NORTH_AMERICA("NA1", "en_US", "AMERICAS"),
    OCEANIA("OC1", "en_AU", "SEA"),
    RUSSIA("RU", "ru_RU", "EUROPE"),
    TURKEY("TR1", "tr_TR", "EUROPE"),
    PHILIPPINES("PH2", "en_PH", "SEA"),
    SINGAPORE("SG2", "en_SG", "SEA"),
    THAILAND("TH2", "th_TH", "SEA"),
    TAIWAN("TW2", "zh_TW", "SEA"),
    VIETNAM("VN2", "vn_VN", "SEA");

    private static final java.util.Map<String, Platform> BY_TAG = getByTag();

    private static final java.util.Map<String, Platform> getByTag() {
        final Builder<String, Platform> builder = ImmutableMap.builder();
        for(final Platform platform : values()) {
            builder.put(platform.tag, platform);
        }
        return builder.build();
    }

    public static Platform withTag(final String tag) {
        return BY_TAG.get(tag);
    }

    private final String defaultLocale;
    private final String tag;
    private final String regionalRoute;

    private Platform(final String tag, final String defaultLocale, String regionalRoute) {
        this.tag = tag;
        this.defaultLocale = defaultLocale;
        this.regionalRoute = regionalRoute;
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public FeaturedMatches getFeaturedMatches() {
        return FeaturedMatches.withPlatform(this).get();
    }

    public Languages getLanguages() {
        return Languages.withPlatform(this).get();
    }

    public Realm getRealm() {
        return Realm.withPlatform(this).get();
    }

    public Region getRegion() {
        return Region.valueOf(name());
    }

    public ShardStatus getStatus() {
        return ShardStatus.withPlatform(this).get();
    }

    public String getTag() {
        return tag;
    }

    public Versions getVersions() {
        return Versions.withPlatform(this).get();
    }

    public String getRegionalRoute(String endpoint) {
        // "There are three routing values for account-v1; americas, asia, and europe. You can query for any account in
        // any region. We recommend using the nearest cluster." - https://developer.riotgames.com/apis#account-v1/
        if (endpoint.startsWith("riot/account/v1/") && regionalRoute.equalsIgnoreCase("SEA")) {
            return JAPAN.regionalRoute;
        }
        return regionalRoute;
    }
}
