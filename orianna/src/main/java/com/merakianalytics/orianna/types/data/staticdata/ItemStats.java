package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class ItemStats extends CoreData {
    private static final long serialVersionUID = -4225498074415380418L;
    private double percentCriticalStrikeChance, percentMagicResist, percentHealthRegen, percentMovespeed, magicResist, criticalStrikeDamage,
            energy, percentLifesteal, mana, movespeed, percentAttackSpeed, block, percentBlock,
            energyRegen, percentSpellVamp, manaRegen, percentDodge, attackSpeed, armor, healthRegen, percentAbilityPower,
            percentMana, abilityPower, percentManaRegen, percentAttackDamage, attackDamage, percentHealth, percentArmor,
            percentCriticalStrikeChange, percentExperience, health, criticalStrikeChance, experience;

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
        final ItemStats other = (ItemStats)obj;
        if(Double.doubleToLongBits(abilityPower) != Double.doubleToLongBits(other.abilityPower)) {
            return false;
        }
        if(Double.doubleToLongBits(armor) != Double.doubleToLongBits(other.armor)) {
            return false;
        }
        if(Double.doubleToLongBits(attackDamage) != Double.doubleToLongBits(other.attackDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(attackSpeed) != Double.doubleToLongBits(other.attackSpeed)) {
            return false;
        }
        if(Double.doubleToLongBits(block) != Double.doubleToLongBits(other.block)) {
            return false;
        }
        if(Double.doubleToLongBits(criticalStrikeChance) != Double.doubleToLongBits(other.criticalStrikeChance)) {
            return false;
        }
        if(Double.doubleToLongBits(criticalStrikeDamage) != Double.doubleToLongBits(other.criticalStrikeDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(energy) != Double.doubleToLongBits(other.energy)) {
            return false;
        }
        if(Double.doubleToLongBits(energyRegen) != Double.doubleToLongBits(other.energyRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(experience) != Double.doubleToLongBits(other.experience)) {
            return false;
        }
        if(Double.doubleToLongBits(health) != Double.doubleToLongBits(other.health)) {
            return false;
        }
        if(Double.doubleToLongBits(healthRegen) != Double.doubleToLongBits(other.healthRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(magicResist) != Double.doubleToLongBits(other.magicResist)) {
            return false;
        }
        if(Double.doubleToLongBits(mana) != Double.doubleToLongBits(other.mana)) {
            return false;
        }
        if(Double.doubleToLongBits(manaRegen) != Double.doubleToLongBits(other.manaRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(movespeed) != Double.doubleToLongBits(other.movespeed)) {
            return false;
        }
        if(Double.doubleToLongBits(percentAbilityPower) != Double.doubleToLongBits(other.percentAbilityPower)) {
            return false;
        }
        if(Double.doubleToLongBits(percentArmor) != Double.doubleToLongBits(other.percentArmor)) {
            return false;
        }
        if(Double.doubleToLongBits(percentAttackDamage) != Double.doubleToLongBits(other.percentAttackDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(percentAttackSpeed) != Double.doubleToLongBits(other.percentAttackSpeed)) {
            return false;
        }
        if(Double.doubleToLongBits(percentBlock) != Double.doubleToLongBits(other.percentBlock)) {
            return false;
        }
        if(Double.doubleToLongBits(percentCriticalStrikeChance) != Double.doubleToLongBits(other.percentCriticalStrikeChance)) {
            return false;
        }
        if(Double.doubleToLongBits(percentCriticalStrikeChange) != Double.doubleToLongBits(other.percentCriticalStrikeChange)) {
            return false;
        }
        if(Double.doubleToLongBits(percentDodge) != Double.doubleToLongBits(other.percentDodge)) {
            return false;
        }
        if(Double.doubleToLongBits(percentExperience) != Double.doubleToLongBits(other.percentExperience)) {
            return false;
        }
        if(Double.doubleToLongBits(percentHealth) != Double.doubleToLongBits(other.percentHealth)) {
            return false;
        }
        if(Double.doubleToLongBits(percentHealthRegen) != Double.doubleToLongBits(other.percentHealthRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(percentLifesteal) != Double.doubleToLongBits(other.percentLifesteal)) {
            return false;
        }
        if(Double.doubleToLongBits(percentMagicResist) != Double.doubleToLongBits(other.percentMagicResist)) {
            return false;
        }
        if(Double.doubleToLongBits(percentMana) != Double.doubleToLongBits(other.percentMana)) {
            return false;
        }
        if(Double.doubleToLongBits(percentManaRegen) != Double.doubleToLongBits(other.percentManaRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(percentMovespeed) != Double.doubleToLongBits(other.percentMovespeed)) {
            return false;
        }
        if(Double.doubleToLongBits(percentSpellVamp) != Double.doubleToLongBits(other.percentSpellVamp)) {
            return false;
        }
        return true;
    }

    /**
     * @return the abilityPower
     */
    public double getAbilityPower() {
        return abilityPower;
    }

    /**
     * @return the armor
     */
    public double getArmor() {
        return armor;
    }

    /**
     * @return the attackDamage
     */
    public double getAttackDamage() {
        return attackDamage;
    }

    /**
     * @return the attackSpeed
     */
    public double getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * @return the block
     */
    public double getBlock() {
        return block;
    }

    /**
     * @return the criticalStrikeChance
     */
    public double getCriticalStrikeChance() {
        return criticalStrikeChance;
    }

    /**
     * @return the criticalStrikeDamage
     */
    public double getCriticalStrikeDamage() {
        return criticalStrikeDamage;
    }

    /**
     * @return the energy
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * @return the energyRegen
     */
    public double getEnergyRegen() {
        return energyRegen;
    }

    /**
     * @return the experience
     */
    public double getExperience() {
        return experience;
    }

    /**
     * @return the health
     */
    public double getHealth() {
        return health;
    }

    /**
     * @return the healthRegen
     */
    public double getHealthRegen() {
        return healthRegen;
    }

    /**
     * @return the magicResist
     */
    public double getMagicResist() {
        return magicResist;
    }

    /**
     * @return the mana
     */
    public double getMana() {
        return mana;
    }

    /**
     * @return the manaRegen
     */
    public double getManaRegen() {
        return manaRegen;
    }

    /**
     * @return the movespeed
     */
    public double getMovespeed() {
        return movespeed;
    }

    /**
     * @return the percentAbilityPower
     */
    public double getPercentAbilityPower() {
        return percentAbilityPower;
    }

    /**
     * @return the percentArmor
     */
    public double getPercentArmor() {
        return percentArmor;
    }

    /**
     * @return the percentAttackDamage
     */
    public double getPercentAttackDamage() {
        return percentAttackDamage;
    }

    /**
     * @return the percentAttackSpeed
     */
    public double getPercentAttackSpeed() {
        return percentAttackSpeed;
    }

    /**
     * @return the percentBlock
     */
    public double getPercentBlock() {
        return percentBlock;
    }

    /**
     * @return the percentCriticalStrikeChance
     */
    public double getPercentCriticalStrikeChance() {
        return percentCriticalStrikeChance;
    }

    /**
     * @return the percentCriticalStrikeChange
     */
    public double getPercentCriticalStrikeChange() {
        return percentCriticalStrikeChange;
    }

    /**
     * @return the percentDodge
     */
    public double getPercentDodge() {
        return percentDodge;
    }

    /**
     * @return the percentExperience
     */
    public double getPercentExperience() {
        return percentExperience;
    }

    /**
     * @return the percentHealth
     */
    public double getPercentHealth() {
        return percentHealth;
    }

    /**
     * @return the percentHealthRegen
     */
    public double getPercentHealthRegen() {
        return percentHealthRegen;
    }

    /**
     * @return the percentLifesteal
     */
    public double getPercentLifesteal() {
        return percentLifesteal;
    }

    /**
     * @return the percentMagicResist
     */
    public double getPercentMagicResist() {
        return percentMagicResist;
    }

    /**
     * @return the percentMana
     */
    public double getPercentMana() {
        return percentMana;
    }

    /**
     * @return the percentManaRegen
     */
    public double getPercentManaRegen() {
        return percentManaRegen;
    }

    /**
     * @return the percentMovespeed
     */
    public double getPercentMovespeed() {
        return percentMovespeed;
    }

    /**
     * @return the percentSpellVamp
     */
    public double getPercentSpellVamp() {
        return percentSpellVamp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(abilityPower);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(armor);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackSpeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(block);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(criticalStrikeChance);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(criticalStrikeDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(energy);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(energyRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(experience);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(health);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(healthRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(magicResist);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(mana);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(manaRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(movespeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentAbilityPower);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentArmor);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentAttackDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentAttackSpeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentBlock);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentCriticalStrikeChance);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentCriticalStrikeChange);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentDodge);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentExperience);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentHealth);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentHealthRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentLifesteal);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMagicResist);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMana);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentManaRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMovespeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentSpellVamp);
        result = prime * result + (int)(temp ^ temp >>> 32);
        return result;
    }

    /**
     * @param abilityPower
     *        the abilityPower to set
     */
    public void setAbilityPower(final double abilityPower) {
        this.abilityPower = abilityPower;
    }

    /**
     * @param armor
     *        the armor to set
     */
    public void setArmor(final double armor) {
        this.armor = armor;
    }

    /**
     * @param attackDamage
     *        the attackDamage to set
     */
    public void setAttackDamage(final double attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * @param attackSpeed
     *        the attackSpeed to set
     */
    public void setAttackSpeed(final double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * @param block
     *        the block to set
     */
    public void setBlock(final double block) {
        this.block = block;
    }

    /**
     * @param criticalStrikeChance
     *        the criticalStrikeChance to set
     */
    public void setCriticalStrikeChance(final double criticalStrikeChance) {
        this.criticalStrikeChance = criticalStrikeChance;
    }

    /**
     * @param criticalStrikeDamage
     *        the criticalStrikeDamage to set
     */
    public void setCriticalStrikeDamage(final double criticalStrikeDamage) {
        this.criticalStrikeDamage = criticalStrikeDamage;
    }

    /**
     * @param energy
     *        the energy to set
     */
    public void setEnergy(final double energy) {
        this.energy = energy;
    }

    /**
     * @param energyRegen
     *        the energyRegen to set
     */
    public void setEnergyRegen(final double energyRegen) {
        this.energyRegen = energyRegen;
    }

    /**
     * @param experience
     *        the experience to set
     */
    public void setExperience(final double experience) {
        this.experience = experience;
    }

    /**
     * @param health
     *        the health to set
     */
    public void setHealth(final double health) {
        this.health = health;
    }

    /**
     * @param healthRegen
     *        the healthRegen to set
     */
    public void setHealthRegen(final double healthRegen) {
        this.healthRegen = healthRegen;
    }

    /**
     * @param magicResist
     *        the magicResist to set
     */
    public void setMagicResist(final double magicResist) {
        this.magicResist = magicResist;
    }

    /**
     * @param mana
     *        the mana to set
     */
    public void setMana(final double mana) {
        this.mana = mana;
    }

    /**
     * @param manaRegen
     *        the manaRegen to set
     */
    public void setManaRegen(final double manaRegen) {
        this.manaRegen = manaRegen;
    }

    /**
     * @param movespeed
     *        the movespeed to set
     */
    public void setMovespeed(final double movespeed) {
        this.movespeed = movespeed;
    }

    /**
     * @param percentAbilityPower
     *        the percentAbilityPower to set
     */
    public void setPercentAbilityPower(final double percentAbilityPower) {
        this.percentAbilityPower = percentAbilityPower;
    }

    /**
     * @param percentArmor
     *        the percentArmor to set
     */
    public void setPercentArmor(final double percentArmor) {
        this.percentArmor = percentArmor;
    }

    /**
     * @param percentAttackDamage
     *        the percentAttackDamage to set
     */
    public void setPercentAttackDamage(final double percentAttackDamage) {
        this.percentAttackDamage = percentAttackDamage;
    }

    /**
     * @param percentAttackSpeed
     *        the percentAttackSpeed to set
     */
    public void setPercentAttackSpeed(final double percentAttackSpeed) {
        this.percentAttackSpeed = percentAttackSpeed;
    }

    /**
     * @param percentBlock
     *        the percentBlock to set
     */
    public void setPercentBlock(final double percentBlock) {
        this.percentBlock = percentBlock;
    }

    /**
     * @param percentCriticalStrikeChance
     *        the percentCriticalStrikeChance to set
     */
    public void setPercentCriticalStrikeChance(final double percentCriticalStrikeChance) {
        this.percentCriticalStrikeChance = percentCriticalStrikeChance;
    }

    /**
     * @param percentCriticalStrikeChange
     *        the percentCriticalStrikeChange to set
     */
    public void setPercentCriticalStrikeChange(final double percentCriticalStrikeChange) {
        this.percentCriticalStrikeChange = percentCriticalStrikeChange;
    }

    /**
     * @param percentDodge
     *        the percentDodge to set
     */
    public void setPercentDodge(final double percentDodge) {
        this.percentDodge = percentDodge;
    }

    /**
     * @param percentExperience
     *        the percentExperience to set
     */
    public void setPercentExperience(final double percentExperience) {
        this.percentExperience = percentExperience;
    }

    /**
     * @param percentHealth
     *        the percentHealth to set
     */
    public void setPercentHealth(final double percentHealth) {
        this.percentHealth = percentHealth;
    }

    /**
     * @param percentHealthRegen
     *        the percentHealthRegen to set
     */
    public void setPercentHealthRegen(final double percentHealthRegen) {
        this.percentHealthRegen = percentHealthRegen;
    }

    /**
     * @param percentLifesteal
     *        the percentLifesteal to set
     */
    public void setPercentLifesteal(final double percentLifesteal) {
        this.percentLifesteal = percentLifesteal;
    }

    /**
     * @param percentMagicResist
     *        the percentMagicResist to set
     */
    public void setPercentMagicResist(final double percentMagicResist) {
        this.percentMagicResist = percentMagicResist;
    }

    /**
     * @param percentMana
     *        the percentMana to set
     */
    public void setPercentMana(final double percentMana) {
        this.percentMana = percentMana;
    }

    /**
     * @param percentManaRegen
     *        the percentManaRegen to set
     */
    public void setPercentManaRegen(final double percentManaRegen) {
        this.percentManaRegen = percentManaRegen;
    }

    /**
     * @param percentMovespeed
     *        the percentMovespeed to set
     */
    public void setPercentMovespeed(final double percentMovespeed) {
        this.percentMovespeed = percentMovespeed;
    }

    /**
     * @param percentSpellVamp
     *        the percentSpellVamp to set
     */
    public void setPercentSpellVamp(final double percentSpellVamp) {
        this.percentSpellVamp = percentSpellVamp;
    }
}
