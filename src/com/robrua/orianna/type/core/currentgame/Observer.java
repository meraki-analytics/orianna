package com.robrua.orianna.type.core.currentgame;

import com.robrua.orianna.type.core.OriannaObject;

public class Observer extends OriannaObject<com.robrua.orianna.type.dto.currentgame.Observer> {
    private static final long serialVersionUID = 174822075548678548L;

    /**
     * @param data
     *            the underlying dto
     */
    public Observer(final com.robrua.orianna.type.dto.currentgame.Observer data) {
        super(data, com.robrua.orianna.type.dto.currentgame.Observer.class);
    }

    /**
     * Key used to decrypt the spectator grid game data for playback
     *
     * @return the key used to decrypt the spectator grid game data for playback
     */
    public String getEncryptionKey() {
        return super.getString(data.getEncryptionKey());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Observer (" + getEncryptionKey() + ")";
    }
}
