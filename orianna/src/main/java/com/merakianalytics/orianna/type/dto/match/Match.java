package com.merakianalytics.orianna.type.dto.match;

import java.util.List;

import com.merakianalytics.orianna.type.dto.DataObject;

public class Match extends DataObject {
    private static final long serialVersionUID = 6336180262863006928L;
    private long gameId, gameDuration, gameCreation, forAccountId;
    private String gameVersion, platformId, gameMode, gameType, tournamentCode;
    private List<ParticipantIdentity> participantIdentities;
    private List<Participant> participants;
    private int seasonId, queueId, mapId;
    private List<TeamStats> teams;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final Match other = (Match)obj;
        if(forAccountId != other.forAccountId) {
            return false;
        }
        if(gameCreation != other.gameCreation) {
            return false;
        }
        if(gameDuration != other.gameDuration) {
            return false;
        }
        if(gameId != other.gameId) {
            return false;
        }
        if(gameMode == null) {
            if(other.gameMode != null) {
                return false;
            }
        } else if(!gameMode.equals(other.gameMode)) {
            return false;
        }
        if(gameType == null) {
            if(other.gameType != null) {
                return false;
            }
        } else if(!gameType.equals(other.gameType)) {
            return false;
        }
        if(gameVersion == null) {
            if(other.gameVersion != null) {
                return false;
            }
        } else if(!gameVersion.equals(other.gameVersion)) {
            return false;
        }
        if(mapId != other.mapId) {
            return false;
        }
        if(participantIdentities == null) {
            if(other.participantIdentities != null) {
                return false;
            }
        } else if(!participantIdentities.equals(other.participantIdentities)) {
            return false;
        }
        if(participants == null) {
            if(other.participants != null) {
                return false;
            }
        } else if(!participants.equals(other.participants)) {
            return false;
        }
        if(platformId == null) {
            if(other.platformId != null) {
                return false;
            }
        } else if(!platformId.equals(other.platformId)) {
            return false;
        }
        if(queueId != other.queueId) {
            return false;
        }
        if(seasonId != other.seasonId) {
            return false;
        }
        if(teams == null) {
            if(other.teams != null) {
                return false;
            }
        } else if(!teams.equals(other.teams)) {
            return false;
        }
        if(tournamentCode == null) {
            if(other.tournamentCode != null) {
                return false;
            }
        } else if(!tournamentCode.equals(other.tournamentCode)) {
            return false;
        }
        return true;
    }

    /**
     * @return the forAccountId
     */
    public long getForAccountId() {
        return forAccountId;
    }

    /**
     * @return the gameCreation
     */
    public long getGameCreation() {
        return gameCreation;
    }

    /**
     * @return the gameDuration
     */
    public long getGameDuration() {
        return gameDuration;
    }

    /**
     * @return the gameId
     */
    public long getGameId() {
        return gameId;
    }

    /**
     * @return the gameMode
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * @return the gameType
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * @return the gameVersion
     */
    public String getGameVersion() {
        return gameVersion;
    }

    /**
     * @return the mapId
     */
    public int getMapId() {
        return mapId;
    }

    /**
     * @return the participantIdentities
     */
    public List<ParticipantIdentity> getParticipantIdentities() {
        return participantIdentities;
    }

    /**
     * @return the participants
     */
    public List<Participant> getParticipants() {
        return participants;
    }

    /**
     * @return the platformId
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * @return the queueId
     */
    public int getQueueId() {
        return queueId;
    }

    /**
     * @return the seasonId
     */
    public int getSeasonId() {
        return seasonId;
    }

    /**
     * @return the teams
     */
    public List<TeamStats> getTeams() {
        return teams;
    }

    /**
     * @return the tournamentCode
     */
    public String getTournamentCode() {
        return tournamentCode;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(forAccountId ^ forAccountId >>> 32);
        result = prime * result + (int)(gameCreation ^ gameCreation >>> 32);
        result = prime * result + (int)(gameDuration ^ gameDuration >>> 32);
        result = prime * result + (int)(gameId ^ gameId >>> 32);
        result = prime * result + (gameMode == null ? 0 : gameMode.hashCode());
        result = prime * result + (gameType == null ? 0 : gameType.hashCode());
        result = prime * result + (gameVersion == null ? 0 : gameVersion.hashCode());
        result = prime * result + mapId;
        result = prime * result + (participantIdentities == null ? 0 : participantIdentities.hashCode());
        result = prime * result + (participants == null ? 0 : participants.hashCode());
        result = prime * result + (platformId == null ? 0 : platformId.hashCode());
        result = prime * result + queueId;
        result = prime * result + seasonId;
        result = prime * result + (teams == null ? 0 : teams.hashCode());
        result = prime * result + (tournamentCode == null ? 0 : tournamentCode.hashCode());
        return result;
    }

    /**
     * @param forAccountId
     *        the forAccountId to set
     */
    public void setForAccountId(final long forAccountId) {
        this.forAccountId = forAccountId;
    }

    /**
     * @param gameCreation
     *        the gameCreation to set
     */
    public void setGameCreation(final long gameCreation) {
        this.gameCreation = gameCreation;
    }

    /**
     * @param gameDuration
     *        the gameDuration to set
     */
    public void setGameDuration(final long gameDuration) {
        this.gameDuration = gameDuration;
    }

    /**
     * @param gameId
     *        the gameId to set
     */
    public void setGameId(final long gameId) {
        this.gameId = gameId;
    }

    /**
     * @param gameMode
     *        the gameMode to set
     */
    public void setGameMode(final String gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * @param gameType
     *        the gameType to set
     */
    public void setGameType(final String gameType) {
        this.gameType = gameType;
    }

    /**
     * @param gameVersion
     *        the gameVersion to set
     */
    public void setGameVersion(final String gameVersion) {
        this.gameVersion = gameVersion;
    }

    /**
     * @param mapId
     *        the mapId to set
     */
    public void setMapId(final int mapId) {
        this.mapId = mapId;
    }

    /**
     * @param participantIdentities
     *        the participantIdentities to set
     */
    public void setParticipantIdentities(final List<ParticipantIdentity> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    /**
     * @param participants
     *        the participants to set
     */
    public void setParticipants(final List<Participant> participants) {
        this.participants = participants;
    }

    /**
     * @param platformId
     *        the platformId to set
     */
    public void setPlatformId(final String platformId) {
        this.platformId = platformId;
    }

    /**
     * @param queueId
     *        the queueId to set
     */
    public void setQueueId(final int queueId) {
        this.queueId = queueId;
    }

    /**
     * @param seasonId
     *        the seasonId to set
     */
    public void setSeasonId(final int seasonId) {
        this.seasonId = seasonId;
    }

    /**
     * @param teams
     *        the teams to set
     */
    public void setTeams(final List<TeamStats> teams) {
        this.teams = teams;
    }

    /**
     * @param tournamentCode
     *        the tournamentCode to set
     */
    public void setTournamentCode(final String tournamentCode) {
        this.tournamentCode = tournamentCode;
    }
}
