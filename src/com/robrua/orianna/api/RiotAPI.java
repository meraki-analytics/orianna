package com.robrua.orianna.api;

import java.util.List;

import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.staticdata.Item;
import com.robrua.orianna.type.core.staticdata.Mastery;
import com.robrua.orianna.type.core.staticdata.Rune;
import com.robrua.orianna.type.core.staticdata.SummonerSpell;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.core.team.Team;

public abstract class RiotAPI {
    private static LoadPolicy loadPolicy = LoadPolicy.UPFRONT;

    public static Champion getChampionByID(final long ID) {
        return null;
    }

    public static Item getItemByID(final long ID) {
        return null;
    }

    public static List<Item> getItemsByID(final List<Long> IDs) {
        return null;
    }

    /**
     * Gets the current load policy for the API
     *
     * @return the current load policy
     */
    public static LoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    public static Mastery getMasteryByID(final long ID) {
        return null;
    }

    public static Rune getRuneByID(final long ID) {
        return null;
    }

    public static Summoner getSummonerByID(final long ID) {
        return null;
    }

    public static SummonerSpell getSummonerSpellByID(final long ID) {
        return null;
    }

    public static Team getTeamByID(final String ID) {
        return null;
    }

    /**
     * Sets the load policy for the API
     *
     * @param policy
     *            the new load policy
     */
    public static void setLoadPolicy(final LoadPolicy policy) {
        loadPolicy = policy;
    }
}
