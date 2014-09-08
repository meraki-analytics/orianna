package com.robrua.orianna.type.staticdata;

public enum RuneType {
    GLYPH, MARK, QUINTESSENCE, SEAL;

    public static RuneType fromString(final String type) {
        switch(type) {
            case "black":
                return QUINTESSENCE;
            case "red":
                return MARK;
            case "yellow":
                return SEAL;
            case "blue":
                return GLYPH;
            default:
                return null;
        }
    }
}
