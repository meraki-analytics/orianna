package com.robrua.orianna.type.dto.staticdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "stats")
public class Stats extends OriannaDto {
    private static final long serialVersionUID = -5686446191361451936L;
    private Double armor, armorperlevel, attackdamage, attackdamageperlevel, attackrange, attackspeedoffset, attackspeedperlevel, crit, critperlevel, hp,
            hpperlevel, hpregen, hpregenperlevel, movespeed, mp, mpperlevel, mpregen, mpregenperlevel, spellblock, spellblockperlevel;

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
        if(!(obj instanceof Stats)) {
            return false;
        }
        final Stats other = (Stats)obj;
        if(armor == null) {
            if(other.armor != null) {
                return false;
            }
        }
        else if(!armor.equals(other.armor)) {
            return false;
        }
        if(armorperlevel == null) {
            if(other.armorperlevel != null) {
                return false;
            }
        }
        else if(!armorperlevel.equals(other.armorperlevel)) {
            return false;
        }
        if(attackdamage == null) {
            if(other.attackdamage != null) {
                return false;
            }
        }
        else if(!attackdamage.equals(other.attackdamage)) {
            return false;
        }
        if(attackdamageperlevel == null) {
            if(other.attackdamageperlevel != null) {
                return false;
            }
        }
        else if(!attackdamageperlevel.equals(other.attackdamageperlevel)) {
            return false;
        }
        if(attackrange == null) {
            if(other.attackrange != null) {
                return false;
            }
        }
        else if(!attackrange.equals(other.attackrange)) {
            return false;
        }
        if(attackspeedoffset == null) {
            if(other.attackspeedoffset != null) {
                return false;
            }
        }
        else if(!attackspeedoffset.equals(other.attackspeedoffset)) {
            return false;
        }
        if(attackspeedperlevel == null) {
            if(other.attackspeedperlevel != null) {
                return false;
            }
        }
        else if(!attackspeedperlevel.equals(other.attackspeedperlevel)) {
            return false;
        }
        if(crit == null) {
            if(other.crit != null) {
                return false;
            }
        }
        else if(!crit.equals(other.crit)) {
            return false;
        }
        if(critperlevel == null) {
            if(other.critperlevel != null) {
                return false;
            }
        }
        else if(!critperlevel.equals(other.critperlevel)) {
            return false;
        }
        if(hp == null) {
            if(other.hp != null) {
                return false;
            }
        }
        else if(!hp.equals(other.hp)) {
            return false;
        }
        if(hpperlevel == null) {
            if(other.hpperlevel != null) {
                return false;
            }
        }
        else if(!hpperlevel.equals(other.hpperlevel)) {
            return false;
        }
        if(hpregen == null) {
            if(other.hpregen != null) {
                return false;
            }
        }
        else if(!hpregen.equals(other.hpregen)) {
            return false;
        }
        if(hpregenperlevel == null) {
            if(other.hpregenperlevel != null) {
                return false;
            }
        }
        else if(!hpregenperlevel.equals(other.hpregenperlevel)) {
            return false;
        }
        if(movespeed == null) {
            if(other.movespeed != null) {
                return false;
            }
        }
        else if(!movespeed.equals(other.movespeed)) {
            return false;
        }
        if(mp == null) {
            if(other.mp != null) {
                return false;
            }
        }
        else if(!mp.equals(other.mp)) {
            return false;
        }
        if(mpperlevel == null) {
            if(other.mpperlevel != null) {
                return false;
            }
        }
        else if(!mpperlevel.equals(other.mpperlevel)) {
            return false;
        }
        if(mpregen == null) {
            if(other.mpregen != null) {
                return false;
            }
        }
        else if(!mpregen.equals(other.mpregen)) {
            return false;
        }
        if(mpregenperlevel == null) {
            if(other.mpregenperlevel != null) {
                return false;
            }
        }
        else if(!mpregenperlevel.equals(other.mpregenperlevel)) {
            return false;
        }
        if(spellblock == null) {
            if(other.spellblock != null) {
                return false;
            }
        }
        else if(!spellblock.equals(other.spellblock)) {
            return false;
        }
        if(spellblockperlevel == null) {
            if(other.spellblockperlevel != null) {
                return false;
            }
        }
        else if(!spellblockperlevel.equals(other.spellblockperlevel)) {
            return false;
        }
        return true;
    }

    /**
     * @return the armor
     */
    public Double getArmor() {
        return armor;
    }

    /**
     * @return the armorperlevel
     */
    public Double getArmorperlevel() {
        return armorperlevel;
    }

    /**
     * @return the attackdamage
     */
    public Double getAttackdamage() {
        return attackdamage;
    }

    /**
     * @return the attackdamageperlevel
     */
    public Double getAttackdamageperlevel() {
        return attackdamageperlevel;
    }

    /**
     * @return the attackrange
     */
    public Double getAttackrange() {
        return attackrange;
    }

    /**
     * @return the attackspeedoffset
     */
    public Double getAttackspeedoffset() {
        return attackspeedoffset;
    }

    /**
     * @return the attackspeedperlevel
     */
    public Double getAttackspeedperlevel() {
        return attackspeedperlevel;
    }

    /**
     * @return the crit
     */
    public Double getCrit() {
        return crit;
    }

    /**
     * @return the critperlevel
     */
    public Double getCritperlevel() {
        return critperlevel;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the hp
     */
    public Double getHp() {
        return hp;
    }

    /**
     * @return the hpperlevel
     */
    public Double getHpperlevel() {
        return hpperlevel;
    }

    /**
     * @return the hpregen
     */
    public Double getHpregen() {
        return hpregen;
    }

    /**
     * @return the hpregenperlevel
     */
    public Double getHpregenperlevel() {
        return hpregenperlevel;
    }

    /**
     * @return the movespeed
     */
    public Double getMovespeed() {
        return movespeed;
    }

    /**
     * @return the mp
     */
    public Double getMp() {
        return mp;
    }

    /**
     * @return the mpperlevel
     */
    public Double getMpperlevel() {
        return mpperlevel;
    }

    /**
     * @return the mpregen
     */
    public Double getMpregen() {
        return mpregen;
    }

    /**
     * @return the mpregenperlevel
     */
    public Double getMpregenperlevel() {
        return mpregenperlevel;
    }

    /**
     * @return the spellblock
     */
    public Double getSpellblock() {
        return spellblock;
    }

    /**
     * @return the spellblockperlevel
     */
    public Double getSpellblockperlevel() {
        return spellblockperlevel;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (armor == null ? 0 : armor.hashCode());
        result = prime * result + (armorperlevel == null ? 0 : armorperlevel.hashCode());
        result = prime * result + (attackdamage == null ? 0 : attackdamage.hashCode());
        result = prime * result + (attackdamageperlevel == null ? 0 : attackdamageperlevel.hashCode());
        result = prime * result + (attackrange == null ? 0 : attackrange.hashCode());
        result = prime * result + (attackspeedoffset == null ? 0 : attackspeedoffset.hashCode());
        result = prime * result + (attackspeedperlevel == null ? 0 : attackspeedperlevel.hashCode());
        result = prime * result + (crit == null ? 0 : crit.hashCode());
        result = prime * result + (critperlevel == null ? 0 : critperlevel.hashCode());
        result = prime * result + (hp == null ? 0 : hp.hashCode());
        result = prime * result + (hpperlevel == null ? 0 : hpperlevel.hashCode());
        result = prime * result + (hpregen == null ? 0 : hpregen.hashCode());
        result = prime * result + (hpregenperlevel == null ? 0 : hpregenperlevel.hashCode());
        result = prime * result + (movespeed == null ? 0 : movespeed.hashCode());
        result = prime * result + (mp == null ? 0 : mp.hashCode());
        result = prime * result + (mpperlevel == null ? 0 : mpperlevel.hashCode());
        result = prime * result + (mpregen == null ? 0 : mpregen.hashCode());
        result = prime * result + (mpregenperlevel == null ? 0 : mpregenperlevel.hashCode());
        result = prime * result + (spellblock == null ? 0 : spellblock.hashCode());
        result = prime * result + (spellblockperlevel == null ? 0 : spellblockperlevel.hashCode());
        return result;
    }

    /**
     * @param armor
     *            the armor to set
     */
    public void setArmor(final Double armor) {
        this.armor = armor;
    }

    /**
     * @param armorperlevel
     *            the armorperlevel to set
     */
    public void setArmorperlevel(final Double armorperlevel) {
        this.armorperlevel = armorperlevel;
    }

    /**
     * @param attackdamage
     *            the attackdamage to set
     */
    public void setAttackdamage(final Double attackdamage) {
        this.attackdamage = attackdamage;
    }

    /**
     * @param attackdamageperlevel
     *            the attackdamageperlevel to set
     */
    public void setAttackdamageperlevel(final Double attackdamageperlevel) {
        this.attackdamageperlevel = attackdamageperlevel;
    }

    /**
     * @param attackrange
     *            the attackrange to set
     */
    public void setAttackrange(final Double attackrange) {
        this.attackrange = attackrange;
    }

    /**
     * @param attackspeedoffset
     *            the attackspeedoffset to set
     */
    public void setAttackspeedoffset(final Double attackspeedoffset) {
        this.attackspeedoffset = attackspeedoffset;
    }

    /**
     * @param attackspeedperlevel
     *            the attackspeedperlevel to set
     */
    public void setAttackspeedperlevel(final Double attackspeedperlevel) {
        this.attackspeedperlevel = attackspeedperlevel;
    }

    /**
     * @param crit
     *            the crit to set
     */
    public void setCrit(final Double crit) {
        this.crit = crit;
    }

    /**
     * @param critperlevel
     *            the critperlevel to set
     */
    public void setCritperlevel(final Double critperlevel) {
        this.critperlevel = critperlevel;
    }

    /**
     * @param hp
     *            the hp to set
     */
    public void setHp(final Double hp) {
        this.hp = hp;
    }

    /**
     * @param hpperlevel
     *            the hpperlevel to set
     */
    public void setHpperlevel(final Double hpperlevel) {
        this.hpperlevel = hpperlevel;
    }

    /**
     * @param hpregen
     *            the hpregen to set
     */
    public void setHpregen(final Double hpregen) {
        this.hpregen = hpregen;
    }

    /**
     * @param hpregenperlevel
     *            the hpregenperlevel to set
     */
    public void setHpregenperlevel(final Double hpregenperlevel) {
        this.hpregenperlevel = hpregenperlevel;
    }

    /**
     * @param movespeed
     *            the movespeed to set
     */
    public void setMovespeed(final Double movespeed) {
        this.movespeed = movespeed;
    }

    /**
     * @param mp
     *            the mp to set
     */
    public void setMp(final Double mp) {
        this.mp = mp;
    }

    /**
     * @param mpperlevel
     *            the mpperlevel to set
     */
    public void setMpperlevel(final Double mpperlevel) {
        this.mpperlevel = mpperlevel;
    }

    /**
     * @param mpregen
     *            the mpregen to set
     */
    public void setMpregen(final Double mpregen) {
        this.mpregen = mpregen;
    }

    /**
     * @param mpregenperlevel
     *            the mpregenperlevel to set
     */
    public void setMpregenperlevel(final Double mpregenperlevel) {
        this.mpregenperlevel = mpregenperlevel;
    }

    /**
     * @param spellblock
     *            the spellblock to set
     */
    public void setSpellblock(final Double spellblock) {
        this.spellblock = spellblock;
    }

    /**
     * @param spellblockperlevel
     *            the spellblockperlevel to set
     */
    public void setSpellblockperlevel(final Double spellblockperlevel) {
        this.spellblockperlevel = spellblockperlevel;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Stats [armor=" + armor + ", armorperlevel=" + armorperlevel + ", attackdamage=" + attackdamage + ", attackdamageperlevel="
                + attackdamageperlevel + ", attackrange=" + attackrange + ", attackspeedoffset=" + attackspeedoffset + ", attackspeedperlevel="
                + attackspeedperlevel + ", crit=" + crit + ", critperlevel=" + critperlevel + ", hp=" + hp + ", hpperlevel=" + hpperlevel + ", hpregen="
                + hpregen + ", hpregenperlevel=" + hpregenperlevel + ", movespeed=" + movespeed + ", mp=" + mp + ", mpperlevel=" + mpperlevel + ", mpregen="
                + mpregen + ", mpregenperlevel=" + mpregenperlevel + ", spellblock=" + spellblock + ", spellblockperlevel=" + spellblockperlevel + "]";
    }
}
