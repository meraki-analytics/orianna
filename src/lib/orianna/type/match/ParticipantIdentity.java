package lib.orianna.type.match;

import java.io.Serializable;

public class ParticipantIdentity implements Serializable {
    private static final long serialVersionUID = -5082256951005417298L;
    public final Participant participant;
    public final Player player;

    public ParticipantIdentity(final Participant participant, final Player player) {
        this.participant = participant;
        this.player = player;
    }

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
        if(participant == null) {
            if(other.participant != null) {
                return false;
            }
        }
        else if(!participant.equals(other.participant)) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (participant == null ? 0 : participant.hashCode());
        result = prime * result + (player == null ? 0 : player.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ParticipantIdentity [participant=" + participant + ", player=" + player + "]";
    }
}
