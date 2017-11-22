package com.merakianalytics.orianna.types.common;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum RuneType {
        GLYPH("blue"),
        MARK("red"),
        QUINTESSENCE("black"),
        SEAL("yellow");

    private static final Map<String, RuneType> BY_COLOR = getByColor();

    private static final Map<String, RuneType> getByColor() {
        final Builder<String, RuneType> builder = ImmutableMap.builder();
        for(final RuneType type : values()) {
            builder.put(type.color, type);
        }
        return builder.build();
    }

    public static RuneType withColor(final String color) {
        return BY_COLOR.get(color);
    }

    private final String color;

    private RuneType(final String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
