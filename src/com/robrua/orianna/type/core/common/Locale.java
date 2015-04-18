package com.robrua.orianna.type.core.common;

public enum Locale {
    cs_CZ("Czech (Czech Republic)"), de_DE("German (Germany)"), el_GR("Greek (Greece)"), en_AU("English (Australia)"), en_GB("English (United Kingdom)"), en_PH(
            "English (Republic of the Philippines)"), en_PL("English (Poland)"), en_SG("English (Singapore)"), en_US("English (United States)"), es_AR(
            "Spanish (Argentina)"), es_ES("Spanish (Spain)"), es_MX("Spanish (Mexico)"), fr_FR("French (France)"), hu_HU("Hungarian (Hungary)"), id_ID(
            "Indonesian (Indonesia)"), it_IT("Italian (Italy)"), ja_JP("Japanese (Japan)"), ko_KR("Korean (Korea)"), ms_MY("Malay (Malaysia)"), pl_PL(
            "Polish (Poland)"), pt_BR("Portuguese (Brazil)"), ro_RO("Romanian (Romania)"), ru_RU("Russian (Russia)"), th_TH("Thai (Thailand)"), tr_TR(
            "Turkish (Turkey)"), vn_VN("Vietnamese (Viet Nam)"), zh_CN("Chinese (China)"), zh_MY("Chinese (Malaysia)"), zh_TW("Chinese (Taiwan)");

    private final String language;

    /**
     * @param language
     */
    private Locale(final String language) {
        this.language = language;
    }

    /**
     * @return the name of the language this locale specifies
     */
    public String getLanguage() {
        return language;
    }
}
