package com.merakianalytics.orianna.types.dto.staticdata;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Info extends DataObject {
    private static final long serialVersionUID = -7050088759392929214L;
    private int difficulty, attack, defense, magic;

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
        final Info other = (Info)obj;
        if(attack != other.attack) {
            return false;
        }
        if(defense != other.defense) {
            return false;
        }
        if(difficulty != other.difficulty) {
            return false;
        }
        if(magic != other.magic) {
            return false;
        }
        return true;
    }

    /**
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @return the defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * @return the magic
     */
    public int getMagic() {
        return magic;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + attack;
        result = prime * result + defense;
        result = prime * result + difficulty;
        result = prime * result + magic;
        return result;
    }

    /**
     * @param attack
     *        the attack to set
     */
    public void setAttack(final int attack) {
        this.attack = attack;
    }

    /**
     * @param defense
     *        the defense to set
     */
    public void setDefense(final int defense) {
        this.defense = defense;
    }

    /**
     * @param difficulty
     *        the difficulty to set
     */
    public void setDifficulty(final int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @param magic
     *        the magic to set
     */
    public void setMagic(final int magic) {
        this.magic = magic;
    }
}
