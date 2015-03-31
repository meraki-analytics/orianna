package com.robrua.orianna.type.dto.match;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "participantidentity")
public class ParticipantIdentity extends OriannaDto {
    private static final long serialVersionUID = 7691992421814077470L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Integer participantId;

    @OneToOne(cascade = CascadeType.ALL)
    private Player player;

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
        if(!(obj instanceof ParticipantIdentity)) {
            return false;
        }
        final ParticipantIdentity other = (ParticipantIdentity)obj;
        if(participantId == null) {
            if(other.participantId != null) {
                return false;
            }
        }
        else if(!participantId.equals(other.participantId)) {
            return false;
        }
        if(player == null) {
            if(other.player != null) {
                return false;
            }
        }
        else if(!player.equals(other.player)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the participantId
     */
    public Integer getParticipantId() {
        return participantId;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (participantId == null ? 0 : participantId.hashCode());
        result = prime * result + (player == null ? 0 : player.hashCode());
        return result;
    }

    /**
     * @param participantId
     *            the participantId to set
     */
    public void setParticipantId(final Integer participantId) {
        this.participantId = participantId;
    }

    /**
     * @param player
     *            the player to set
     */
    public void setPlayer(final Player player) {
        this.player = player;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantIdentity [participantId=" + participantId + ", player=" + player + "]";
    }
}
