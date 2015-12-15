package com.robrua.orianna.api.dto;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.robrua.orianna.type.api.ParamsBuilder;
import com.robrua.orianna.type.dto.staticdata.Champion;
import com.robrua.orianna.type.dto.staticdata.ChampionList;
import com.robrua.orianna.type.dto.staticdata.Item;
import com.robrua.orianna.type.dto.staticdata.ItemList;
import com.robrua.orianna.type.dto.staticdata.LanguageStrings;
import com.robrua.orianna.type.dto.staticdata.MapData;
import com.robrua.orianna.type.dto.staticdata.Mastery;
import com.robrua.orianna.type.dto.staticdata.MasteryList;
import com.robrua.orianna.type.dto.staticdata.Realm;
import com.robrua.orianna.type.dto.staticdata.Rune;
import com.robrua.orianna.type.dto.staticdata.RuneList;
import com.robrua.orianna.type.dto.staticdata.SummonerSpell;
import com.robrua.orianna.type.dto.staticdata.SummonerSpellList;

public abstract class StaticDataAPI {
    /**
     * @param ID
     *            the champion's ID
     * @return the champion
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3256">
     *      Riot API Specification</a>
     */
    public static Champion getChampion(final long ID) {
        if(ID == 0) {
            return null;
        }

        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/champion/" + ID;
        final Map<String, String> params = new ParamsBuilder().add("champData", "all").addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), Champion.class);
    }

    /**
     * @return the list all of champions
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3260">
     *      Riot API Specification</a>
     */
    public static ChampionList getChampions() {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/champion";
        final Map<String, String> params = new ParamsBuilder().add("champData", "all").addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), ChampionList.class);
    }

    /**
     * @param ID
     *            the item's ID
     * @return the item
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3253">
     *      Riot API Specification</a>
     */
    public static Item getItem(final long ID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/item/" + ID;
        final Map<String, String> params = new ParamsBuilder().add("itemData", "all").addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), Item.class);
    }

    /**
     * @return the list of all items
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3248">
     *      Riot API Specification</a>
     */
    public static ItemList getItems() {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/item";
        final Map<String, String> params = new ParamsBuilder().add("itemListData", "all").addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), ItemList.class);
    }

    /**
     * @return the languages
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3258">
     *      Riot API Specification</a>
     */
    public static List<String> getLanguages() {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/languages";
        final Type type = new TypeToken<List<String>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, true), type);
    }

    /**
     * @return the language strings
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3250">
     *      Riot API Specification</a>
     */
    public static LanguageStrings getLanguageStrings() {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/language-strings";
        final Map<String, String> params = new ParamsBuilder().addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), LanguageStrings.class);
    }

    /**
     * @return the map information
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3262">
     *      Riot API Specification</a>
     */
    public static MapData getMapInformation() {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/map";
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, true), MapData.class);
    }

    /**
     * @return the list of all masteries
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3251">
     *      Riot API Specification</a>
     */
    public static MasteryList getMasteries() {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/mastery";
        final Map<String, String> params = new ParamsBuilder().add("masteryListData", "all").addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), MasteryList.class);
    }

    /**
     * @param ID
     *            the mastery's ID
     * @return the mastery
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3252">
     *      Riot API Specification</a>
     */
    public static Mastery getMastery(final long ID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/mastery/" + ID;
        final Map<String, String> params = new ParamsBuilder().add("masteryData", "all").addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), Mastery.class);
    }

    /**
     * @return the realm
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3259">
     *      Riot API Specification</a>
     */
    public static Realm getRealm() {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/realm";
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, true), Realm.class);
    }

    /**
     * @param ID
     *            the rune's ID
     * @return the rune
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3255">
     *      Riot API Specification</a>
     */
    public static Rune getRune(final long ID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/rune/" + ID;
        final Map<String, String> params = new ParamsBuilder().add("runeData", "all").addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), Rune.class);
    }

    /**
     * @return the list of all runes
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3249">
     *      Riot API Specification</a>
     */
    public static RuneList getRunes() {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/rune";
        final Map<String, String> params = new ParamsBuilder().add("runeListData", "all").addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), RuneList.class);
    }

    /**
     * @param ID
     *            the summoner spell's ID
     * @return the summoner spell
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3254">
     *      Riot API Specification</a>
     */
    public static SummonerSpell getSummonerSpell(final long ID) {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/summoner-spell/" + ID;
        final Map<String, String> params = new ParamsBuilder().add("spellData", "all").addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), SummonerSpell.class);
    }

    /**
     * @return the list of all summoner spells
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3261">
     *      Riot API Specification</a>
     */
    public static SummonerSpellList getSummonerSpells() {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/summoner-spell";
        final Map<String, String> params = new ParamsBuilder().add("spellData", "all").addIfNotNull("locale", BaseRiotAPI.locale).build();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, params, true), SummonerSpellList.class);
    }

    /**
     * @return the versions
     * @see <a href="https://developer.riotgames.com/api/methods#!/938/3257">
     *      Riot API Specification</a>
     */
    public static List<String> getVersions() {
        final String request = BaseRiotAPI.API_VERSIONS.get("static-data") + "/versions";
        final Type type = new TypeToken<List<String>>() {}.getType();
        return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.get(request, null, true), type);
    }
}
