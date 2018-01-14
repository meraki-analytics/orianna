package com.merakianalytics.orianna.types.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.merakianalytics.orianna.types.core.spectator.FeaturedGames;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Realm;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.status.ShardStatus;

public enum Platform {
        BRAZIL("BR1", "pt_BR"),
        EUROPE_NORTH_EAST("EUN1", "en_GB"),
        EUROPE_WEST("EUW1", "en_GB"),
        JAPAN("JP1", "ja_JP"),
        KOREA("KR", "ko_KR"),
        LATIN_AMERICA_NORTH("LA1", "es_MX"),
        LATIN_AMERICA_SOUTH("LA2", "es_AR"),
        NORTH_AMERICA("NA1", "en_US"),
        OCEANIA("OC1", "en_AU"),
        RUSSIA("RU", "ru_RU"),
        TURKEY("TR1", "tr_TR");

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

    private Platform(final String tag, final String defaultLocale) {
        this.tag = tag;
        this.defaultLocale = defaultLocale;
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public FeaturedGames getFeaturedGames() {
        return FeaturedGames.forPlatform(this).get();
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
        return ShardStatus.forPlatform(this).get();
    }

    public String getTag() {
        return tag;
    }

    public Versions getVersions() {
        return Versions.withPlatform(this).get();
    }
}
