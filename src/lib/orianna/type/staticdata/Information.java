package lib.orianna.type.staticdata;

import java.io.Serializable;

public class Information implements Serializable {
    private static final long serialVersionUID = -5379089112253863302L;
    public final Integer attack, defense, difficulty, magic;

    public Information(final Integer attack, final Integer defense, final Integer difficulty, final Integer magic) {
        this.attack = attack;
        this.defense = defense;
        this.difficulty = difficulty;
        this.magic = magic;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Information)) {
            return false;
        }
        final Information other = (Information)obj;
        if(attack == null) {
            if(other.attack != null) {
                return false;
            }
        }
        else if(!attack.equals(other.attack)) {
            return false;
        }
        if(defense == null) {
            if(other.defense != null) {
                return false;
            }
        }
        else if(!defense.equals(other.defense)) {
            return false;
        }
        if(difficulty == null) {
            if(other.difficulty != null) {
                return false;
            }
        }
        else if(!difficulty.equals(other.difficulty)) {
            return false;
        }
        if(magic == null) {
            if(other.magic != null) {
                return false;
            }
        }
        else if(!magic.equals(other.magic)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (attack == null ? 0 : attack.hashCode());
        result = prime * result + (defense == null ? 0 : defense.hashCode());
        result = prime * result + (difficulty == null ? 0 : difficulty.hashCode());
        result = prime * result + (magic == null ? 0 : magic.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Information [attack=" + attack + ", defense=" + defense + ", difficulty=" + difficulty + ", magic=" + magic + "]";
    }
}
