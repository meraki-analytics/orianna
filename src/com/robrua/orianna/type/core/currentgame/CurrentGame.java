package com.robrua.orianna.type.core.currentgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.GameMap;
import com.robrua.orianna.type.core.common.GameMode;
import com.robrua.orianna.type.core.common.GameType;
import com.robrua.orianna.type.core.common.PlatformID;
import com.robrua.orianna.type.core.common.QueueType;

public class CurrentGame extends OriannaObject<com.robrua.orianna.type.dto.currentgame.CurrentGameInfo> {
    private static final long serialVersionUID = -2835507404250272815L;
    private List<BannedChampion> bannedChampions;
    private Observer observer;
    private List<Participant> participants;

    /**
     * @param data
     *            the underlying dto
     */
    public CurrentGame(final com.robrua.orianna.type.dto.currentgame.CurrentGameInfo data) {
        super(data, com.robrua.orianna.type.dto.currentgame.CurrentGameInfo.class);
    }

    /**
     * Banned champion information
     *
     * @return banned champion information
     */
    public List<BannedChampion> getBannedChampions() {
        if(bannedChampions == null) {
            bannedChampions = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.currentgame.BannedChampion ban : data.getBannedChampions()) {
                bannedChampions.add(new BannedChampion(ban));
            }
        }

        return Collections.unmodifiableList(bannedChampions);
    }

    /**
     * The ID of the game
     *
     * @return the ID of the game
     */
    public long getID() {
        return super.getLong(data.getGameId());
    }

    /**
     * The amount of time in seconds that has passed since the game started
     *
     * @return the amount of time in seconds that has passed since the game
     *         started
     */
    public long getLength() {
        return super.getLong(data.getGameLength());
    }

    /**
     * The map the game is on
     *
     * @return the map the game is on
     */
    public GameMap getMap() {
        return GameMap.forID(super.getLong(data.getMapId()));
    }

    /**
     * The game mode
     *
     * @return the game mode
     */
    public GameMode getMode() {
        return GameMode.valueOf(super.getString(data.getGameMode()));
    }

    /**
     * The observer information
     *
     * @return the observer information
     */
    public Observer getObservers() {
        if(observer == null) {
            observer = new Observer(data.getObservers());
        }

        return observer;
    }

    /**
     * The participant information
     *
     * @return the participant information
     */
    public List<Participant> getParticipants() {
        if(participants == null) {
            participants = new ArrayList<>();
            if(data.getParticipants() != null) {
                for(final com.robrua.orianna.type.dto.currentgame.Participant part : data.getParticipants()) {
                    participants.add(new Participant(part));
                }
            }
        }

        return Collections.unmodifiableList(participants);
    }

    /**
     * The ID of the platform on which the game is being played
     *
     * @return the ID of the platform on which the game is being played
     */
    public PlatformID getPlatformID() {
        return PlatformID.valueOf(super.getString(data.getPlatformId()));
    }

    /**
     * The queue type
     *
     * @return the queue type
     */
    public QueueType getQueueType() {
        return QueueType.forID(super.getLong(data.getGameQueueConfigId()));
    }

    /**
     * The game start time
     *
     * @return the game start time
     */
    public Date getStartTime() {
        return super.getDate(data.getGameStartTime());
    }

    /**
     * The game type
     *
     * @return the game type
     */
    public GameType getType() {
        return GameType.valueOf(super.getString(data.getGameType()));
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CurrentGameInfo (" + getParticipants() + ")";
    }
}
