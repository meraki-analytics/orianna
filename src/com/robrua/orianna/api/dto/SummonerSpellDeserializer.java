package com.robrua.orianna.api.dto;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.robrua.orianna.type.dto.staticdata.Image;
import com.robrua.orianna.type.dto.staticdata.LevelTip;
import com.robrua.orianna.type.dto.staticdata.SpellVars;
import com.robrua.orianna.type.dto.staticdata.SummonerSpell;

public class SummonerSpellDeserializer extends DtoDeserializer<SummonerSpell> {
    @SuppressWarnings("unchecked")
    @Override
    public SummonerSpell deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject obj = json.getAsJsonObject();
        final SummonerSpell spell = new SummonerSpell();
        Type t;

        t = new TypeToken<List<Double>>() {}.getType();
        spell.setCooldown((List<Double>)GSON.fromJson(obj.get("cooldown"), t));

        spell.setCooldownBurn(GSON.fromJson(obj.get("cooldownBurn"), String.class));
        spell.setCostBurn(GSON.fromJson(obj.get("costBurn"), String.class));
        spell.setCostType(GSON.fromJson(obj.get("costType"), String.class));
        spell.setDescription(GSON.fromJson(obj.get("description"), String.class));
        spell.setKey(GSON.fromJson(obj.get("key"), String.class));
        spell.setName(GSON.fromJson(obj.get("name"), String.class));
        spell.setRangeBurn(GSON.fromJson(obj.get("rangeBurn"), String.class));
        spell.setResource(GSON.fromJson(obj.get("resource"), String.class));
        spell.setSanitizedDescription(GSON.fromJson(obj.get("sanitizedDescription"), String.class));
        spell.setSanitizedTooltip(GSON.fromJson(obj.get("sanitizedTooltip"), String.class));
        spell.setTooltip(GSON.fromJson(obj.get("tooltip"), String.class));

        t = new TypeToken<List<Integer>>() {}.getType();
        spell.setCost((List<Integer>)GSON.fromJson(obj.get("cost"), t));

        try {
            spell.setRange((List<Integer>)GSON.fromJson(obj.get("range"), t));
        }
        catch(final JsonParseException e) {
            spell.setRange(null);
        }

        t = new TypeToken<List<List<Double>>>() {}.getType();
        spell.setEffect((List<List<Double>>)GSON.fromJson(obj.get("effect"), t));

        t = new TypeToken<List<String>>() {}.getType();
        spell.setEffectBurn((List<String>)GSON.fromJson(obj.get("effectBurn"), t));
        spell.setModes((List<String>)GSON.fromJson(obj.get("modes"), t));

        spell.setImage(GSON.fromJson(obj.get("image"), Image.class));
        spell.setLeveltip(GSON.fromJson(obj.get("leveltip"), LevelTip.class));
        spell.setMaxrank(GSON.fromJson(obj.get("maxrank"), Integer.class));
        spell.setId(GSON.fromJson(obj.get("id"), Integer.class));
        spell.setSummonerLevel(GSON.fromJson(obj.get("summonerLevel"), Integer.class));

        t = new TypeToken<List<SpellVars>>() {}.getType();
        spell.setVars((List<SpellVars>)GSON.fromJson(obj.get("vars"), t));

        return spell;
    }
}
