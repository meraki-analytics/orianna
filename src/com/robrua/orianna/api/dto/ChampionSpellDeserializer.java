package com.robrua.orianna.api.dto;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.robrua.orianna.type.dto.staticdata.ChampionSpell;
import com.robrua.orianna.type.dto.staticdata.Image;
import com.robrua.orianna.type.dto.staticdata.LevelTip;
import com.robrua.orianna.type.dto.staticdata.SpellVars;

public class ChampionSpellDeserializer implements JsonDeserializer<ChampionSpell> {
    private static final Gson gson = new Gson();

    @SuppressWarnings("unchecked")
    @Override
    public ChampionSpell deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject obj = json.getAsJsonObject();
        final ChampionSpell spell = new ChampionSpell();
        Type t;

        t = new TypeToken<List<Image>>() {}.getType();
        spell.setAltimages((List<Image>)gson.fromJson(obj.get("altimages"), t));

        t = new TypeToken<List<Double>>() {}.getType();
        spell.setCooldown((List<Double>)gson.fromJson(obj.get("cooldown"), t));

        spell.setCooldownBurn(gson.fromJson(obj.get("cooldownBurn"), String.class));
        spell.setCostBurn(gson.fromJson(obj.get("costBurn"), String.class));
        spell.setCostType(gson.fromJson(obj.get("costType"), String.class));
        spell.setDescription(gson.fromJson(obj.get("description"), String.class));
        spell.setKey(gson.fromJson(obj.get("key"), String.class));
        spell.setName(gson.fromJson(obj.get("name"), String.class));
        spell.setRangeBurn(gson.fromJson(obj.get("rangeBurn"), String.class));
        spell.setResource(gson.fromJson(obj.get("resource"), String.class));
        spell.setSanitizedDescription(gson.fromJson(obj.get("sanitizedDescription"), String.class));
        spell.setSanitizedTooltip(gson.fromJson(obj.get("sanitizedTooltip"), String.class));
        spell.setTooltip(gson.fromJson(obj.get("tooltip"), String.class));

        t = new TypeToken<List<Integer>>() {}.getType();
        spell.setCost((List<Integer>)gson.fromJson(obj.get("cost"), t));

        try {
            spell.setRange((List<Integer>)gson.fromJson(obj.get("range"), t));
        }
        catch(final JsonSyntaxException e) {
            spell.setRange(null);
        }

        t = new TypeToken<List<List<Double>>>() {}.getType();
        spell.setEffect((List<List<Double>>)gson.fromJson(obj.get("effect"), t));

        t = new TypeToken<List<String>>() {}.getType();
        spell.setEffectBurn((List<String>)gson.fromJson(obj.get("effectBurn"), t));

        spell.setImage(gson.fromJson(obj.get("image"), Image.class));
        spell.setLeveltip(gson.fromJson(obj.get("leveltip"), LevelTip.class));
        spell.setMaxrank(gson.fromJson(obj.get("maxrank"), Integer.class));

        t = new TypeToken<List<SpellVars>>() {}.getType();
        spell.setVars((List<SpellVars>)gson.fromJson(obj.get("vars"), t));

        return spell;
    }
}
