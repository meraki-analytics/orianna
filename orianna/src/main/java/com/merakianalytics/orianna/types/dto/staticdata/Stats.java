package com.merakianalytics.orianna.types.dto.staticdata;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Stats extends DataObject {
    private static final long serialVersionUID = -6639707481634048220L;
    private double armorperlevel, hpperlevel, attackdamage, mpperlevel, attackspeedoffset, armor, hp, hpregenperlevel, spellblock, attackrange, movespeed,
            attackdamageperlevel, mpregenperlevel, mp, spellblockperlevel, crit, mpregen, attackspeedperlevel, hpregen, critperlevel;

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
        final Stats other = (Stats)obj;
        if(Double.doubleToLongBits(armor) != Double.doubleToLongBits(other.armor)) {
            return false;
        }
        if(Double.doubleToLongBits(armorperlevel) != Double.doubleToLongBits(other.armorperlevel)) {
            return false;
        }
        if(Double.doubleToLongBits(attackdamage) != Double.doubleToLongBits(other.attackdamage)) {
            return false;
        }
        if(Double.doubleToLongBits(attackdamageperlevel) != Double.doubleToLongBits(other.attackdamageperlevel)) {
            return false;
        }
        if(Double.doubleToLongBits(attackrange) != Double.doubleToLongBits(other.attackrange)) {
            return false;
        }
        if(Double.doubleToLongBits(attackspeedoffset) != Double.doubleToLongBits(other.attackspeedoffset)) {
            return false;
        }
        if(Double.doubleToLongBits(attackspeedperlevel) != Double.doubleToLongBits(other.attackspeedperlevel)) {
            return false;
        }
        if(Double.doubleToLongBits(crit) != Double.doubleToLongBits(other.crit)) {
            return false;
        }
        if(Double.doubleToLongBits(critperlevel) != Double.doubleToLongBits(other.critperlevel)) {
            return false;
        }
        if(Double.doubleToLongBits(hp) != Double.doubleToLongBits(other.hp)) {
            return false;
        }
        if(Double.doubleToLongBits(hpperlevel) != Double.doubleToLongBits(other.hpperlevel)) {
            return false;
        }
        if(Double.doubleToLongBits(hpregen) != Double.doubleToLongBits(other.hpregen)) {
            return false;
        }
        if(Double.doubleToLongBits(hpregenperlevel) != Double.doubleToLongBits(other.hpregenperlevel)) {
            return false;
        }
        if(Double.doubleToLongBits(movespeed) != Double.doubleToLongBits(other.movespeed)) {
            return false;
        }
        if(Double.doubleToLongBits(mp) != Double.doubleToLongBits(other.mp)) {
            return false;
        }
        if(Double.doubleToLongBits(mpperlevel) != Double.doubleToLongBits(other.mpperlevel)) {
            return false;
        }
        if(Double.doubleToLongBits(mpregen) != Double.doubleToLongBits(other.mpregen)) {
            return false;
        }
        if(Double.doubleToLongBits(mpregenperlevel) != Double.doubleToLongBits(other.mpregenperlevel)) {
            return false;
        }
        if(Double.doubleToLongBits(spellblock) != Double.doubleToLongBits(other.spellblock)) {
            return false;
        }
        if(Double.doubleToLongBits(spellblockperlevel) != Double.doubleToLongBits(other.spellblockperlevel)) {
            return false;
        }
        return true;
    }

    /**
     * @return the armor
     */
    public double getArmor() {
        return armor;
    }

    /**
     * @return the armorperlevel
     */
    public double getArmorperlevel() {
        return armorperlevel;
    }

    /**
     * @return the attackdamage
     */
    public double getAttackdamage() {
        return attackdamage;
    }

    /**
     * @return the attackdamageperlevel
     */
    public double getAttackdamageperlevel() {
        return attackdamageperlevel;
    }

    /**
     * @return the attackrange
     */
    public double getAttackrange() {
        return attackrange;
    }

    /**
     * @return the attackspeedoffset
     */
    public double getAttackspeedoffset() {
        return attackspeedoffset;
    }

    /**
     * @return the attackspeedperlevel
     */
    public double getAttackspeedperlevel() {
        return attackspeedperlevel;
    }

    /**
     * @return the crit
     */
    public double getCrit() {
        return crit;
    }

    /**
     * @return the critperlevel
     */
    public double getCritperlevel() {
        return critperlevel;
    }

    /**
     * @return the hp
     */
    public double getHp() {
        return hp;
    }

    /**
     * @return the hpperlevel
     */
    public double getHpperlevel() {
        return hpperlevel;
    }

    /**
     * @return the hpregen
     */
    public double getHpregen() {
        return hpregen;
    }

    /**
     * @return the hpregenperlevel
     */
    public double getHpregenperlevel() {
        return hpregenperlevel;
    }

    /**
     * @return the movespeed
     */
    public double getMovespeed() {
        return movespeed;
    }

    /**
     * @return the mp
     */
    public double getMp() {
        return mp;
    }

    /**
     * @return the mpperlevel
     */
    public double getMpperlevel() {
        return mpperlevel;
    }

    /**
     * @return the mpregen
     */
    public double getMpregen() {
        return mpregen;
    }

    /**
     * @return the mpregenperlevel
     */
    public double getMpregenperlevel() {
        return mpregenperlevel;
    }

    /**
     * @return the spellblock
     */
    public double getSpellblock() {
        return spellblock;
    }

    /**
     * @return the spellblockperlevel
     */
    public double getSpellblockperlevel() {
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
        long temp;
        temp = Double.doubleToLongBits(armor);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(armorperlevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackdamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackdamageperlevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackrange);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackspeedoffset);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackspeedperlevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(crit);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(critperlevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(hp);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(hpperlevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(hpregen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(hpregenperlevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(movespeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(mp);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(mpperlevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(mpregen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(mpregenperlevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(spellblock);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(spellblockperlevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        return result;
    }

    /**
     * @param armor
     *        the armor to set
     */
    public void setArmor(final double armor) {
        this.armor = armor;
    }

    /**
     * @param armorperlevel
     *        the armorperlevel to set
     */
    public void setArmorperlevel(final double armorperlevel) {
        this.armorperlevel = armorperlevel;
    }

    /**
     * @param attackdamage
     *        the attackdamage to set
     */
    public void setAttackdamage(final double attackdamage) {
        this.attackdamage = attackdamage;
    }

    /**
     * @param attackdamageperlevel
     *        the attackdamageperlevel to set
     */
    public void setAttackdamageperlevel(final double attackdamageperlevel) {
        this.attackdamageperlevel = attackdamageperlevel;
    }

    /**
     * @param attackrange
     *        the attackrange to set
     */
    public void setAttackrange(final double attackrange) {
        this.attackrange = attackrange;
    }

    /**
     * @param attackspeedoffset
     *        the attackspeedoffset to set
     */
    public void setAttackspeedoffset(final double attackspeedoffset) {
        this.attackspeedoffset = attackspeedoffset;
    }

    /**
     * @param attackspeedperlevel
     *        the attackspeedperlevel to set
     */
    public void setAttackspeedperlevel(final double attackspeedperlevel) {
        this.attackspeedperlevel = attackspeedperlevel;
    }

    /**
     * @param crit
     *        the crit to set
     */
    public void setCrit(final double crit) {
        this.crit = crit;
    }

    /**
     * @param critperlevel
     *        the critperlevel to set
     */
    public void setCritperlevel(final double critperlevel) {
        this.critperlevel = critperlevel;
    }

    /**
     * @param hp
     *        the hp to set
     */
    public void setHp(final double hp) {
        this.hp = hp;
    }

    /**
     * @param hpperlevel
     *        the hpperlevel to set
     */
    public void setHpperlevel(final double hpperlevel) {
        this.hpperlevel = hpperlevel;
    }

    /**
     * @param hpregen
     *        the hpregen to set
     */
    public void setHpregen(final double hpregen) {
        this.hpregen = hpregen;
    }

    /**
     * @param hpregenperlevel
     *        the hpregenperlevel to set
     */
    public void setHpregenperlevel(final double hpregenperlevel) {
        this.hpregenperlevel = hpregenperlevel;
    }

    /**
     * @param movespeed
     *        the movespeed to set
     */
    public void setMovespeed(final double movespeed) {
        this.movespeed = movespeed;
    }

    /**
     * @param mp
     *        the mp to set
     */
    public void setMp(final double mp) {
        this.mp = mp;
    }

    /**
     * @param mpperlevel
     *        the mpperlevel to set
     */
    public void setMpperlevel(final double mpperlevel) {
        this.mpperlevel = mpperlevel;
    }

    /**
     * @param mpregen
     *        the mpregen to set
     */
    public void setMpregen(final double mpregen) {
        this.mpregen = mpregen;
    }

    /**
     * @param mpregenperlevel
     *        the mpregenperlevel to set
     */
    public void setMpregenperlevel(final double mpregenperlevel) {
        this.mpregenperlevel = mpregenperlevel;
    }

    /**
     * @param spellblock
     *        the spellblock to set
     */
    public void setSpellblock(final double spellblock) {
        this.spellblock = spellblock;
    }

    /**
     * @param spellblockperlevel
     *        the spellblockperlevel to set
     */
    public void setSpellblockperlevel(final double spellblockperlevel) {
        this.spellblockperlevel = spellblockperlevel;
    }
}
