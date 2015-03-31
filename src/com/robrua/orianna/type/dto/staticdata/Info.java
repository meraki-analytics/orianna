package com.robrua.orianna.type.dto.staticdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "info")
public class Info extends OriannaDto {
    private static final long serialVersionUID = 1346443594745926604L;
    private Integer attack, defense, difficulty, magic;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

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
        if(!(obj instanceof Info)) {
            return false;
        }
        final Info other = (Info)obj;
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

    /**
     * @return the attack
     */
    public Integer getAttack() {
        return attack;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the defense
     */
    public Integer getDefense() {
        return defense;
    }

    /**
     * @return the difficulty
     */
    public Integer getDifficulty() {
        return difficulty;
    }

    /**
     * @return the magic
     */
    public Integer getMagic() {
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
        result = prime * result + (attack == null ? 0 : attack.hashCode());
        result = prime * result + (defense == null ? 0 : defense.hashCode());
        result = prime * result + (difficulty == null ? 0 : difficulty.hashCode());
        result = prime * result + (magic == null ? 0 : magic.hashCode());
        return result;
    }

    /**
     * @param attack
     *            the attack to set
     */
    public void setAttack(final Integer attack) {
        this.attack = attack;
    }

    /**
     * @param defense
     *            the defense to set
     */
    public void setDefense(final Integer defense) {
        this.defense = defense;
    }

    /**
     * @param difficulty
     *            the difficulty to set
     */
    public void setDifficulty(final Integer difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @param magic
     *            the magic to set
     */
    public void setMagic(final Integer magic) {
        this.magic = magic;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Info [attack=" + attack + ", defense=" + defense + ", difficulty=" + difficulty + ", magic=" + magic + "]";
    }
}
