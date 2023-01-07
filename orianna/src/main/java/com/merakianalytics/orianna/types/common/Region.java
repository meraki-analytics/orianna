package com.merakianalytics.orianna.types.common;

import com.merakianalytics.orianna.types.core.spectator.FeaturedMatches;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Realm;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.status.ShardStatus;

public enum Region {
        BRAZIL("BR", "pt_BR"),
        EUROPE_NORTH_EAST("EUNE", "en_GB"),
        EUROPE_WEST("EUW", "en_GB"),
        JAPAN("JP", "ja_JP"),
        KOREA("KR", "ko_KR"),
        LATIN_AMERICA_NORTH("LAN", "es_MX"),
        LATIN_AMERICA_SOUTH("LAS", "es_AR"),
        NORTH_AMERICA("NA", "en_US"),
        OCEANIA("OCE", "en_AU"),
        RUSSIA("RU", "ru_RU"),
        TURKEY("TR", "tr_TR"),
        PHILIPPINES("PH", "en_PH"),
        SINGAPORE("SG", "en_SG"),
        THAILAND("TH", "th_TH"),
        TAIWAN("TW", "zh_TW"),
        VIETNAM("VN", "vn_VN");

    private final String defaultLocale;
    private final String tag;

    private Region(final String tag, final String defaultLocale) {
        this.tag = tag;
        this.defaultLocale = defaultLocale;
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public FeaturedMatches getFeaturedMatches() {
        return FeaturedMatches.withRegion(this).get();
    }

    public Languages getLanguages() {
        return Languages.withRegion(this).get();
    }

    public Platform getPlatform() {
        return Platform.valueOf(name());
    }

    public Realm getRealm() {
        return Realm.withRegion(this).get();
    }

    public ShardStatus getStatus() {
        return ShardStatus.withRegion(this).get();
    }

    public String getTag() {
        return tag;
    }

    public Versions getVersions() {
        return Versions.withRegion(this).get();
    }
}
