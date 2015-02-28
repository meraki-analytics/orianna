package com.robrua.orianna.api.dto;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.robrua.orianna.type.dto.OriannaDto;

public abstract class DtoDeserializer<T extends OriannaDto> implements JsonDeserializer<T> {
    protected static final Gson GSON = new Gson();
}
