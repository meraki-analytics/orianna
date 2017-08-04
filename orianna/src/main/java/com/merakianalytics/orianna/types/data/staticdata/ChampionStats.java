package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class ChampionStats extends CoreData {
    private static final long serialVersionUID = 8070402776138698435L;
    private double armorPerLevel, healthPerLevel, attackDamage, manaPerLevel, attackSpeedOffset, armor, health, healthRegenPerLevel, magicResist, attackRange,
            movespeed, attackDamagePerLevel, manaRegenPerLevel, mana, magicResistPerLevel, criticalStrikeChance, manaRegen, attackSpeedPerLevel, healthRegen,
            criticalStrikeChancePerLevel;

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
        final ChampionStats other = (ChampionStats)obj;
        if(Double.doubleToLongBits(armor) != Double.doubleToLongBits(other.armor)) {
            return false;
        }
        if(Double.doubleToLongBits(armorPerLevel) != Double.doubleToLongBits(other.armorPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(attackDamage) != Double.doubleToLongBits(other.attackDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(attackDamagePerLevel) != Double.doubleToLongBits(other.attackDamagePerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(attackRange) != Double.doubleToLongBits(other.attackRange)) {
            return false;
        }
        if(Double.doubleToLongBits(attackSpeedOffset) != Double.doubleToLongBits(other.attackSpeedOffset)) {
            return false;
        }
        if(Double.doubleToLongBits(attackSpeedPerLevel) != Double.doubleToLongBits(other.attackSpeedPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(criticalStrikeChance) != Double.doubleToLongBits(other.criticalStrikeChance)) {
            return false;
        }
        if(Double.doubleToLongBits(criticalStrikeChancePerLevel) != Double.doubleToLongBits(other.criticalStrikeChancePerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(health) != Double.doubleToLongBits(other.health)) {
            return false;
        }
        if(Double.doubleToLongBits(healthPerLevel) != Double.doubleToLongBits(other.healthPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(healthRegen) != Double.doubleToLongBits(other.healthRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(healthRegenPerLevel) != Double.doubleToLongBits(other.healthRegenPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(magicResist) != Double.doubleToLongBits(other.magicResist)) {
            return false;
        }
        if(Double.doubleToLongBits(magicResistPerLevel) != Double.doubleToLongBits(other.magicResistPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(mana) != Double.doubleToLongBits(other.mana)) {
            return false;
        }
        if(Double.doubleToLongBits(manaPerLevel) != Double.doubleToLongBits(other.manaPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(manaRegen) != Double.doubleToLongBits(other.manaRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(manaRegenPerLevel) != Double.doubleToLongBits(other.manaRegenPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(movespeed) != Double.doubleToLongBits(other.movespeed)) {
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
     * @return the armorPerLevel
     */
    public double getArmorPerLevel() {
        return armorPerLevel;
    }

    /**
     * @return the attackDamage
     */
    public double getAttackDamage() {
        return attackDamage;
    }

    /**
     * @return the attackDamagePerLevel
     */
    public double getAttackDamagePerLevel() {
        return attackDamagePerLevel;
    }

    /**
     * @return the attackRange
     */
    public double getAttackRange() {
        return attackRange;
    }

    /**
     * @return the attackSpeedOffset
     */
    public double getAttackSpeedOffset() {
        return attackSpeedOffset;
    }

    /**
     * @return the attackSpeedPerLevel
     */
    public double getAttackSpeedPerLevel() {
        return attackSpeedPerLevel;
    }

    /**
     * @return the criticalStrikeChance
     */
    public double getCriticalStrikeChance() {
        return criticalStrikeChance;
    }

    /**
     * @return the criticalStrikeChancePerLevel
     */
    public double getCriticalStrikeChancePerLevel() {
        return criticalStrikeChancePerLevel;
    }

    /**
     * @return the health
     */
    public double getHealth() {
        return health;
    }

    /**
     * @return the healthPerLevel
     */
    public double getHealthPerLevel() {
        return healthPerLevel;
    }

    /**
     * @return the healthRegen
     */
    public double getHealthRegen() {
        return healthRegen;
    }

    /**
     * @return the healthRegenPerLevel
     */
    public double getHealthRegenPerLevel() {
        return healthRegenPerLevel;
    }

    /**
     * @return the magicResist
     */
    public double getMagicResist() {
        return magicResist;
    }

    /**
     * @return the magicResistPerLevel
     */
    public double getMagicResistPerLevel() {
        return magicResistPerLevel;
    }

    /**
     * @return the mana
     */
    public double getMana() {
        return mana;
    }

    /**
     * @return the manaPerLevel
     */
    public double getManaPerLevel() {
        return manaPerLevel;
    }

    /**
     * @return the manaRegen
     */
    public double getManaRegen() {
        return manaRegen;
    }

    /**
     * @return the manaRegenPerLevel
     */
    public double getManaRegenPerLevel() {
        return manaRegenPerLevel;
    }

    /**
     * @return the movespeed
     */
    public double getMovespeed() {
        return movespeed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(armor);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(armorPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackDamagePerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackRange);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackSpeedOffset);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackSpeedPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(criticalStrikeChance);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(criticalStrikeChancePerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(health);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(healthPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(healthRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(healthRegenPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(magicResist);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(magicResistPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(mana);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(manaPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(manaRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(manaRegenPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(movespeed);
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
     * @param armorPerLevel
     *        the armorPerLevel to set
     */
    public void setArmorPerLevel(final double armorPerLevel) {
        this.armorPerLevel = armorPerLevel;
    }

    /**
     * @param attackDamage
     *        the attackDamage to set
     */
    public void setAttackDamage(final double attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * @param attackDamagePerLevel
     *        the attackDamagePerLevel to set
     */
    public void setAttackDamagePerLevel(final double attackDamagePerLevel) {
        this.attackDamagePerLevel = attackDamagePerLevel;
    }

    /**
     * @param attackRange
     *        the attackRange to set
     */
    public void setAttackRange(final double attackRange) {
        this.attackRange = attackRange;
    }

    /**
     * @param attackSpeedOffset
     *        the attackSpeedOffset to set
     */
    public void setAttackSpeedOffset(final double attackSpeedOffset) {
        this.attackSpeedOffset = attackSpeedOffset;
    }

    /**
     * @param attackSpeedPerLevel
     *        the attackSpeedPerLevel to set
     */
    public void setAttackSpeedPerLevel(final double attackSpeedPerLevel) {
        this.attackSpeedPerLevel = attackSpeedPerLevel;
    }

    /**
     * @param criticalStrikeChance
     *        the criticalStrikeChance to set
     */
    public void setCriticalStrikeChance(final double criticalStrikeChance) {
        this.criticalStrikeChance = criticalStrikeChance;
    }

    /**
     * @param criticalStrikeChancePerLevel
     *        the criticalStrikeChancePerLevel to set
     */
    public void setCriticalStrikeChancePerLevel(final double criticalStrikeChancePerLevel) {
        this.criticalStrikeChancePerLevel = criticalStrikeChancePerLevel;
    }

    /**
     * @param health
     *        the health to set
     */
    public void setHealth(final double health) {
        this.health = health;
    }

    /**
     * @param healthPerLevel
     *        the healthPerLevel to set
     */
    public void setHealthPerLevel(final double healthPerLevel) {
        this.healthPerLevel = healthPerLevel;
    }

    /**
     * @param healthRegen
     *        the healthRegen to set
     */
    public void setHealthRegen(final double healthRegen) {
        this.healthRegen = healthRegen;
    }

    /**
     * @param healthRegenPerLevel
     *        the healthRegenPerLevel to set
     */
    public void setHealthRegenPerLevel(final double healthRegenPerLevel) {
        this.healthRegenPerLevel = healthRegenPerLevel;
    }

    /**
     * @param magicResist
     *        the magicResist to set
     */
    public void setMagicResist(final double magicResist) {
        this.magicResist = magicResist;
    }

    /**
     * @param magicResistPerLevel
     *        the magicResistPerLevel to set
     */
    public void setMagicResistPerLevel(final double magicResistPerLevel) {
        this.magicResistPerLevel = magicResistPerLevel;
    }

    /**
     * @param mana
     *        the mana to set
     */
    public void setMana(final double mana) {
        this.mana = mana;
    }

    /**
     * @param manaPerLevel
     *        the manaPerLevel to set
     */
    public void setManaPerLevel(final double manaPerLevel) {
        this.manaPerLevel = manaPerLevel;
    }

    /**
     * @param manaRegen
     *        the manaRegen to set
     */
    public void setManaRegen(final double manaRegen) {
        this.manaRegen = manaRegen;
    }

    /**
     * @param manaRegenPerLevel
     *        the manaRegenPerLevel to set
     */
    public void setManaRegenPerLevel(final double manaRegenPerLevel) {
        this.manaRegenPerLevel = manaRegenPerLevel;
    }

    /**
     * @param movespeed
     *        the movespeed to set
     */
    public void setMovespeed(final double movespeed) {
        this.movespeed = movespeed;
    }
}
