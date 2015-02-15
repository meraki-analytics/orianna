package com.robrua.orianna.type.core.staticdata;

import com.robrua.orianna.type.core.OriannaObject;

public class Info extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Info> {
    private static final long serialVersionUID = -3808966746079805627L;

    /**
     * @param data
     *            the underlying dto
     */
    public Info(final com.robrua.orianna.type.dto.staticdata.Info data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Info.class);
    }

    /**
     * Attack
     *
     * @return attack
     */
    public int getAttack() {
        return super.getInteger(data.getAttack());
    }

    /**
     * Defense
     *
     * @return defense
     */
    public int getDefense() {
        return super.getInteger(data.getDefense());
    }

    /**
     * Diffculty
     *
     * @return difficulty
     */
    public int getDifficulty() {
        return super.getInteger(data.getDifficulty());
    }

    /**
     * Magic
     *
     * @return magic
     */
    public int getMagic() {
        return super.getInteger(data.getMagic());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Info (ATK: " + getAttack() + ", DEF: " + getDefense() + ", DIF: " + getDifficulty() + ", MAG: " + getMagic() + ")";
    }
}
