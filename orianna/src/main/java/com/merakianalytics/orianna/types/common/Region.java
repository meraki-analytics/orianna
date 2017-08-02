package com.merakianalytics.orianna.types.common;

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
    TURKEY("TR", "tr_TR");

    private String defaultLocale;
    private String tag;

    private Region(final String tag, final String defaultLocale) {
        this.tag = tag;
        this.defaultLocale = defaultLocale;
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public Platform getPlatform() {
        return Platform.valueOf(name());
    }

    public String getTag() {
        return tag;
    }
}
