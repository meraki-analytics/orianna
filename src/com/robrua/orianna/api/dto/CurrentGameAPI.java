package com.robrua.orianna.api.dto;

import com.robrua.orianna.type.core.common.PlatformID;
import com.robrua.orianna.type.dto.currentgame.CurrentGameInfo;
import com.robrua.orianna.type.exception.APIException;
import com.robrua.orianna.type.exception.OriannaException;

public abstract class CurrentGameAPI {
    /**
     * @param summonerID
     *            summoner to look up current game for
     * @return the summoner's current game
     * @see <a href="https://developer.riotgames.com/api/methods#!/956/3287">
     *      Riot API Specification</a>
     */
    public static CurrentGameInfo getCurrentGame(final long summonerID) {
        if(BaseRiotAPI.region == null) {
            throw new OriannaException("Must set region for the API using setRegion before the server can be queried!");
        }

        final String request = "/observer-mode/rest/consumer/getSpectatorGameInfo/" + PlatformID.fromRegion(BaseRiotAPI.region) + "/" + summonerID;
        try {
            return BaseRiotAPI.GSON.fromJson(BaseRiotAPI.getRoot(request, null, false), CurrentGameInfo.class);
        }
        catch(final APIException e) {
            if(e.getStatus() == APIException.Status.NOT_FOUND) {
                return null;
            }
            else {
                throw e;
            }
        }
    }
}
