package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class ItemStats extends CoreData {
    private static final long serialVersionUID = -4186822209538997366L;
    private double percentCriticalStrikeChance, percentMagicResist, percentHealthRegen, percentMovespeed, magicResist, criticalStrikeDamage, energy,
            percentBaseAttackDamage, gold, lethality, percentHealAndShieldPower, outOfCombatMovespeed,
            percentLifesteal, mana, movespeed, percentAttackSpeed, block, percentBlock, energyRegen, percentSpellVamp, manaRegen, percentDodge, attackSpeed,
            magicPenetration, percentArmorPenetration, percentTenacity, percentMagicPenetration,
            armor, healthRegen, percentAbilityPower, percentMana, abilityPower, percentManaRegen, percentAttackDamage, attackDamage, percentHealth,
            onHitHealthRegen, percentCooldownReduction, onHitPercentMaxHealthPhysicalDamage, onHitPercentHealthPhysicalDamage,
            percentArmor, percentCriticalStrikeChange, percentExperience, health, criticalStrikeChance, experience, onHitPhysicalDamage, onHitMagicDamage,
            percentManaRegenInJungle, percentLifestealAgainstMonsters, onHitPhysicalDamageAgainstMinions, onKillManaRegen, percentHealthRegenFromPotions;

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
        if(Double.doubleToLongBits(gold) != Double.doubleToLongBits(other.gold)) {
            return false;
        }
        if(Double.doubleToLongBits(health) != Double.doubleToLongBits(other.health)) {
            return false;
        }
        if(Double.doubleToLongBits(healthRegen) != Double.doubleToLongBits(other.healthRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(lethality) != Double.doubleToLongBits(other.lethality)) {
            return false;
        }
        if(Double.doubleToLongBits(magicPenetration) != Double.doubleToLongBits(other.magicPenetration)) {
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
        if(Double.doubleToLongBits(onHitHealthRegen) != Double.doubleToLongBits(other.onHitHealthRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(onHitMagicDamage) != Double.doubleToLongBits(other.onHitMagicDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(onHitPercentHealthPhysicalDamage) != Double.doubleToLongBits(other.onHitPercentHealthPhysicalDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(onHitPercentMaxHealthPhysicalDamage) != Double.doubleToLongBits(other.onHitPercentMaxHealthPhysicalDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(onHitPhysicalDamage) != Double.doubleToLongBits(other.onHitPhysicalDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(onHitPhysicalDamageAgainstMinions) != Double.doubleToLongBits(other.onHitPhysicalDamageAgainstMinions)) {
            return false;
        }
        if(Double.doubleToLongBits(onKillManaRegen) != Double.doubleToLongBits(other.onKillManaRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(outOfCombatMovespeed) != Double.doubleToLongBits(other.outOfCombatMovespeed)) {
            return false;
        }
        if(Double.doubleToLongBits(percentAbilityPower) != Double.doubleToLongBits(other.percentAbilityPower)) {
            return false;
        }
        if(Double.doubleToLongBits(percentArmor) != Double.doubleToLongBits(other.percentArmor)) {
            return false;
        }
        if(Double.doubleToLongBits(percentArmorPenetration) != Double.doubleToLongBits(other.percentArmorPenetration)) {
            return false;
        }
        if(Double.doubleToLongBits(percentAttackDamage) != Double.doubleToLongBits(other.percentAttackDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(percentAttackSpeed) != Double.doubleToLongBits(other.percentAttackSpeed)) {
            return false;
        }
        if(Double.doubleToLongBits(percentBaseAttackDamage) != Double.doubleToLongBits(other.percentBaseAttackDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(percentBlock) != Double.doubleToLongBits(other.percentBlock)) {
            return false;
        }
        if(Double.doubleToLongBits(percentCooldownReduction) != Double.doubleToLongBits(other.percentCooldownReduction)) {
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
        if(Double.doubleToLongBits(percentHealAndShieldPower) != Double.doubleToLongBits(other.percentHealAndShieldPower)) {
            return false;
        }
        if(Double.doubleToLongBits(percentHealth) != Double.doubleToLongBits(other.percentHealth)) {
            return false;
        }
        if(Double.doubleToLongBits(percentHealthRegen) != Double.doubleToLongBits(other.percentHealthRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(percentHealthRegenFromPotions) != Double.doubleToLongBits(other.percentHealthRegenFromPotions)) {
            return false;
        }
        if(Double.doubleToLongBits(percentLifesteal) != Double.doubleToLongBits(other.percentLifesteal)) {
            return false;
        }
        if(Double.doubleToLongBits(percentLifestealAgainstMonsters) != Double.doubleToLongBits(other.percentLifestealAgainstMonsters)) {
            return false;
        }
        if(Double.doubleToLongBits(percentMagicPenetration) != Double.doubleToLongBits(other.percentMagicPenetration)) {
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
        if(Double.doubleToLongBits(percentManaRegenInJungle) != Double.doubleToLongBits(other.percentManaRegenInJungle)) {
            return false;
        }
        if(Double.doubleToLongBits(percentMovespeed) != Double.doubleToLongBits(other.percentMovespeed)) {
            return false;
        }
        if(Double.doubleToLongBits(percentSpellVamp) != Double.doubleToLongBits(other.percentSpellVamp)) {
            return false;
        }
        if(Double.doubleToLongBits(percentTenacity) != Double.doubleToLongBits(other.percentTenacity)) {
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
     * @return the gold
     */
    public double getGold() {
        return gold;
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
     * @return the lethality
     */
    public double getLethality() {
        return lethality;
    }

    /**
     * @return the magicPenetration
     */
    public double getMagicPenetration() {
        return magicPenetration;
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
     * @return the onHitHealthRegen
     */
    public double getOnHitHealthRegen() {
        return onHitHealthRegen;
    }

    /**
     * @return the onHitMagicDamage
     */
    public double getOnHitMagicDamage() {
        return onHitMagicDamage;
    }

    /**
     * @return the onHitPercentHealthPhysicalDamage
     */
    public double getOnHitPercentHealthPhysicalDamage() {
        return onHitPercentHealthPhysicalDamage;
    }

    /**
     * @return the onHitPercentMaxHealthPhysicalDamage
     */
    public double getOnHitPercentMaxHealthPhysicalDamage() {
        return onHitPercentMaxHealthPhysicalDamage;
    }

    /**
     * @return the onHitPhysicalDamage
     */
    public double getOnHitPhysicalDamage() {
        return onHitPhysicalDamage;
    }

    /**
     * @return the onHitPhysicalDamageAgainstMinions
     */
    public double getOnHitPhysicalDamageAgainstMinions() {
        return onHitPhysicalDamageAgainstMinions;
    }

    /**
     * @return the onKillManaRegen
     */
    public double getOnKillManaRegen() {
        return onKillManaRegen;
    }

    /**
     * @return the outOfCombatMovespeed
     */
    public double getOutOfCombatMovespeed() {
        return outOfCombatMovespeed;
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
     * @return the percentArmorPenetration
     */
    public double getPercentArmorPenetration() {
        return percentArmorPenetration;
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
     * @return the percentBaseAttackDamage
     */
    public double getPercentBaseAttackDamage() {
        return percentBaseAttackDamage;
    }

    /**
     * @return the percentBlock
     */
    public double getPercentBlock() {
        return percentBlock;
    }

    /**
     * @return the percentCooldownReduction
     */
    public double getPercentCooldownReduction() {
        return percentCooldownReduction;
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
     * @return the percentHealAndShieldPower
     */
    public double getPercentHealAndShieldPower() {
        return percentHealAndShieldPower;
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
     * @return the percentHealthRegenFromPotions
     */
    public double getPercentHealthRegenFromPotions() {
        return percentHealthRegenFromPotions;
    }

    /**
     * @return the percentLifesteal
     */
    public double getPercentLifesteal() {
        return percentLifesteal;
    }

    /**
     * @return the percentLifestealAgainstMonsters
     */
    public double getPercentLifestealAgainstMonsters() {
        return percentLifestealAgainstMonsters;
    }

    /**
     * @return the percentMagicPenetration
     */
    public double getPercentMagicPenetration() {
        return percentMagicPenetration;
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
     * @return the percentManaRegenInJungle
     */
    public double getPercentManaRegenInJungle() {
        return percentManaRegenInJungle;
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

    /**
     * @return the percentTenacity
     */
    public double getPercentTenacity() {
        return percentTenacity;
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
        temp = Double.doubleToLongBits(gold);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(health);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(healthRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(lethality);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(magicPenetration);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(magicResist);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(mana);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(manaRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(movespeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(onHitHealthRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(onHitMagicDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(onHitPercentHealthPhysicalDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(onHitPercentMaxHealthPhysicalDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(onHitPhysicalDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(onHitPhysicalDamageAgainstMinions);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(onKillManaRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(outOfCombatMovespeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentAbilityPower);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentArmor);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentArmorPenetration);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentAttackDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentAttackSpeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentBaseAttackDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentBlock);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentCooldownReduction);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentCriticalStrikeChance);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentCriticalStrikeChange);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentDodge);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentExperience);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentHealAndShieldPower);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentHealth);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentHealthRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentHealthRegenFromPotions);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentLifesteal);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentLifestealAgainstMonsters);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMagicPenetration);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMagicResist);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMana);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentManaRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentManaRegenInJungle);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMovespeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentSpellVamp);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentTenacity);
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
     * @param gold
     *        the gold to set
     */
    public void setGold(final double gold) {
        this.gold = gold;
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
     * @param lethality
     *        the lethality to set
     */
    public void setLethality(final double lethality) {
        this.lethality = lethality;
    }

    /**
     * @param magicPenetration
     *        the magicPenetration to set
     */
    public void setMagicPenetration(final double magicPenetration) {
        this.magicPenetration = magicPenetration;
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
     * @param onHitHealthRegen
     *        the onHitHealthRegen to set
     */
    public void setOnHitHealthRegen(final double onHitHealthRegen) {
        this.onHitHealthRegen = onHitHealthRegen;
    }

    /**
     * @param onHitMagicDamage
     *        the onHitMagicDamage to set
     */
    public void setOnHitMagicDamage(final double onHitMagicDamage) {
        this.onHitMagicDamage = onHitMagicDamage;
    }

    /**
     * @param onHitPercentHealthPhysicalDamage
     *        the onHitPercentHealthPhysicalDamage to set
     */
    public void setOnHitPercentHealthPhysicalDamage(final double onHitPercentHealthPhysicalDamage) {
        this.onHitPercentHealthPhysicalDamage = onHitPercentHealthPhysicalDamage;
    }

    /**
     * @param onHitPercentMaxHealthPhysicalDamage
     *        the onHitPercentMaxHealthPhysicalDamage to set
     */
    public void setOnHitPercentMaxHealthPhysicalDamage(final double onHitPercentMaxHealthPhysicalDamage) {
        this.onHitPercentMaxHealthPhysicalDamage = onHitPercentMaxHealthPhysicalDamage;
    }

    /**
     * @param onHitPhysicalDamage
     *        the onHitPhysicalDamage to set
     */
    public void setOnHitPhysicalDamage(final double onHitPhysicalDamage) {
        this.onHitPhysicalDamage = onHitPhysicalDamage;
    }

    /**
     * @param onHitPhysicalDamageAgainstMinions
     *        the onHitPhysicalDamageAgainstMinions to set
     */
    public void setOnHitPhysicalDamageAgainstMinions(final double onHitPhysicalDamageAgainstMinions) {
        this.onHitPhysicalDamageAgainstMinions = onHitPhysicalDamageAgainstMinions;
    }

    /**
     * @param onKillManaRegen
     *        the onKillManaRegen to set
     */
    public void setOnKillManaRegen(final double onKillManaRegen) {
        this.onKillManaRegen = onKillManaRegen;
    }

    /**
     * @param outOfCombatMovespeed
     *        the outOfCombatMovespeed to set
     */
    public void setOutOfCombatMovespeed(final double outOfCombatMovespeed) {
        this.outOfCombatMovespeed = outOfCombatMovespeed;
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
     * @param percentArmorPenetration
     *        the percentArmorPenetration to set
     */
    public void setPercentArmorPenetration(final double percentArmorPenetration) {
        this.percentArmorPenetration = percentArmorPenetration;
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
     * @param percentBaseAttackDamage
     *        the percentBaseAttackDamage to set
     */
    public void setPercentBaseAttackDamage(final double percentBaseAttackDamage) {
        this.percentBaseAttackDamage = percentBaseAttackDamage;
    }

    /**
     * @param percentBlock
     *        the percentBlock to set
     */
    public void setPercentBlock(final double percentBlock) {
        this.percentBlock = percentBlock;
    }

    /**
     * @param percentCooldownReduction
     *        the percentCooldownReduction to set
     */
    public void setPercentCooldownReduction(final double percentCooldownReduction) {
        this.percentCooldownReduction = percentCooldownReduction;
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
     * @param percentHealAndShieldPower
     *        the percentHealAndShieldPower to set
     */
    public void setPercentHealAndShieldPower(final double percentHealAndShieldPower) {
        this.percentHealAndShieldPower = percentHealAndShieldPower;
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
     * @param percentHealthRegenFromPotions
     *        the percentHealthRegenFromPotions to set
     */
    public void setPercentHealthRegenFromPotions(final double percentHealthRegenFromPotions) {
        this.percentHealthRegenFromPotions = percentHealthRegenFromPotions;
    }

    /**
     * @param percentLifesteal
     *        the percentLifesteal to set
     */
    public void setPercentLifesteal(final double percentLifesteal) {
        this.percentLifesteal = percentLifesteal;
    }

    /**
     * @param percentLifestealAgainstMonsters
     *        the percentLifestealAgainstMonsters to set
     */
    public void setPercentLifestealAgainstMonsters(final double percentLifestealAgainstMonsters) {
        this.percentLifestealAgainstMonsters = percentLifestealAgainstMonsters;
    }

    /**
     * @param percentMagicPenetration
     *        the percentMagicPenetration to set
     */
    public void setPercentMagicPenetration(final double percentMagicPenetration) {
        this.percentMagicPenetration = percentMagicPenetration;
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
     * @param percentManaRegenInJungle
     *        the percentManaRegenInJungle to set
     */
    public void setPercentManaRegenInJungle(final double percentManaRegenInJungle) {
        this.percentManaRegenInJungle = percentManaRegenInJungle;
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

    /**
     * @param percentTenacity
     *        the percentTenacity to set
     */
    public void setPercentTenacity(final double percentTenacity) {
        this.percentTenacity = percentTenacity;
    }
}
