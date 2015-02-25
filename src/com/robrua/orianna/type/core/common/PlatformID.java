package com.robrua.orianna.type.core.common;

public enum PlatformID {
    BR1, EUN1, EUW1, KR, LA1, LA2, NA1, OC1, RU, TR1;

    public static PlatformID fromRegion(final Region region) {
        switch(region) {
            case BR:
                return BR1;
            case EUNE:
                return EUN1;
            case EUW:
                return EUW1;
            case KR:
                return KR;
            case LAN:
                return LA1;
            case LAS:
                return LA2;
            case NA:
                return NA1;
            case OCE:
                return OC1;
            case RU:
                return RU;
            case TR:
                return TR1;
            default:
                return null;
        }
    }
}
