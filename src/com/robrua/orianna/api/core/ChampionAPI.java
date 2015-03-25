package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.robrua.orianna.api.Utils;
import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.champion.ChampionStatus;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.dto.champion.ChampionList;

public abstract class ChampionAPI {
    /**
     * @param champion
     *            the champion to get status for
     * @return the champion's status
     */
    public static ChampionStatus getChampionStatus(final Champion champion) {
        return getChampionStatus(champion.getID());
    }

    /**
     * @param ID
     *            the ID of the champion to get status for
     * @return the champion's status
     */
    public static ChampionStatus getChampionStatus(final long ID) {
        final com.robrua.orianna.type.dto.champion.Champion status = BaseRiotAPI.getChampionStatus(ID);
        RiotAPI.getChampionsByID(status.getId().longValue());

        return new ChampionStatus(status);
    }

    /**
     * @return all champion's statuses
     */
    public static Map<Champion, ChampionStatus> getChampionStatuses() {
        return getChampionStatuses(false);
    }

    /**
     * @param freeToPlay
     *            whether to only get free to play champions
     * @return all (possibly only free to play) champion's statuses
     */
    public static Map<Champion, ChampionStatus> getChampionStatuses(final boolean freeToPlay) {
        final ChampionList st = BaseRiotAPI.getChampionStatuses(freeToPlay);

        final List<Long> index = new ArrayList<>(st.getChampionIDs());
        final List<Champion> champions = RiotAPI.getChampionsByID(index);

        final Map<Champion, ChampionStatus> statuses = new HashMap<>();
        for(final com.robrua.orianna.type.dto.champion.Champion ch : st.getChampions()) {
            statuses.put(champions.get(index.indexOf(ch.getId().longValue())), new ChampionStatus(ch));
        }

        return Collections.unmodifiableMap(statuses);
    }

    /**
     * @param IDs
     *            the IDs of the champions to get statuses for
     * @return the champions' statuses
     */
    public static List<ChampionStatus> getChampionStatuses(final List<Long> IDs) {
        if(IDs.isEmpty()) {
            return Collections.emptyList();
        }

        final ChampionList st = BaseRiotAPI.getChampionStatuses(false);

        final Set<Long> set = new HashSet<>(IDs);
        for(int i = 0; i < st.getChampions().size(); i++) {
            if(!set.contains(st.getChampions().get(i).getId())) {
                st.getChampions().remove(i);
                i--;
            }
        }

        final List<ChampionStatus> statuses = new ArrayList<>(st.getChampions().size());
        for(final com.robrua.orianna.type.dto.champion.Champion ch : st.getChampions()) {
            statuses.add(new ChampionStatus(ch));
        }
        RiotAPI.getChampionsByID(new ArrayList<>(st.getChampionIDs()));

        return Collections.unmodifiableList(statuses);
    }

    /**
     * @param IDs
     *            the IDs of the champions to get statuses for
     * @return the champions' statuses
     */
    public static List<ChampionStatus> getChampionStatuses(final long... IDs) {
        return getChampionStatuses(Utils.convert(IDs));
    }
}
