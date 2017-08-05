package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.data.CoreData;

public class RuneStats extends CoreData {
    private static final long serialVersionUID = -5080946844563036159L;
    private double percentTimeSpentDeadPerLevel, percentArmorPenetrationPerLevel, percentCriticalStrikeDamage, percentMagicResist, percentHealthRegen,
            percentMovespeed, magicResist, energyRegenPerLevel, energy, magicPenetrationPerLevel, percentLifesteal,
            mana, percentCooldownReduction, percentMagicPenetration, armorPenetrationPerLevel, movespeed, timeSpentDeadPerLevel,
            armorPerLevel, percentAttackSpeed, dodgePerLevel, percentAbilityPower, percentBlock, dodge, energyRegen,
            healthPerLevel, percentAttackSpeedPerLevel, percentSpellVamp, manaRegen, percentHealth, percentDodge, attackSpeed,
            armor, abilityPowerPerLevel, healthRegen, percentAttackDamage, criticalStrikeChancePerLevel, magicResistPerLevel,
            percentTimeSpentDead, block, percentMana, abilityPower, percentManaRegen, percentMovespeedPerLevel,
            percentCooldownReductionPerLevel, manaPerLevel, energyPerLevel, attackDamage, healthRegenPerLevel, criticalStrikeDamage,
            percentArmor, magicPenetration, percentCriticalStrikeChance, attackDamagePerLevel, percentArmorPenetration, percentExperience,
            manaRegenPerLevel, percentMagicPenetrationPerLevel, timeSpentDead, movespeedPerLevel, goldPer10,
            armorPenetration, criticalStrikeDamagePerLevel, health, criticalStrikeChance, experience;

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
        final RuneStats other = (RuneStats)obj;
        if(Double.doubleToLongBits(abilityPower) != Double.doubleToLongBits(other.abilityPower)) {
            return false;
        }
        if(Double.doubleToLongBits(abilityPowerPerLevel) != Double.doubleToLongBits(other.abilityPowerPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(armor) != Double.doubleToLongBits(other.armor)) {
            return false;
        }
        if(Double.doubleToLongBits(armorPenetration) != Double.doubleToLongBits(other.armorPenetration)) {
            return false;
        }
        if(Double.doubleToLongBits(armorPenetrationPerLevel) != Double.doubleToLongBits(other.armorPenetrationPerLevel)) {
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
        if(Double.doubleToLongBits(attackSpeed) != Double.doubleToLongBits(other.attackSpeed)) {
            return false;
        }
        if(Double.doubleToLongBits(block) != Double.doubleToLongBits(other.block)) {
            return false;
        }
        if(Double.doubleToLongBits(criticalStrikeChance) != Double.doubleToLongBits(other.criticalStrikeChance)) {
            return false;
        }
        if(Double.doubleToLongBits(criticalStrikeChancePerLevel) != Double.doubleToLongBits(other.criticalStrikeChancePerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(criticalStrikeDamage) != Double.doubleToLongBits(other.criticalStrikeDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(criticalStrikeDamagePerLevel) != Double.doubleToLongBits(other.criticalStrikeDamagePerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(dodge) != Double.doubleToLongBits(other.dodge)) {
            return false;
        }
        if(Double.doubleToLongBits(dodgePerLevel) != Double.doubleToLongBits(other.dodgePerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(energy) != Double.doubleToLongBits(other.energy)) {
            return false;
        }
        if(Double.doubleToLongBits(energyPerLevel) != Double.doubleToLongBits(other.energyPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(energyRegen) != Double.doubleToLongBits(other.energyRegen)) {
            return false;
        }
        if(Double.doubleToLongBits(energyRegenPerLevel) != Double.doubleToLongBits(other.energyRegenPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(experience) != Double.doubleToLongBits(other.experience)) {
            return false;
        }
        if(Double.doubleToLongBits(goldPer10) != Double.doubleToLongBits(other.goldPer10)) {
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
        if(Double.doubleToLongBits(magicPenetration) != Double.doubleToLongBits(other.magicPenetration)) {
            return false;
        }
        if(Double.doubleToLongBits(magicPenetrationPerLevel) != Double.doubleToLongBits(other.magicPenetrationPerLevel)) {
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
        if(Double.doubleToLongBits(movespeedPerLevel) != Double.doubleToLongBits(other.movespeedPerLevel)) {
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
        if(Double.doubleToLongBits(percentArmorPenetrationPerLevel) != Double.doubleToLongBits(other.percentArmorPenetrationPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(percentAttackDamage) != Double.doubleToLongBits(other.percentAttackDamage)) {
            return false;
        }
        if(Double.doubleToLongBits(percentAttackSpeed) != Double.doubleToLongBits(other.percentAttackSpeed)) {
            return false;
        }
        if(Double.doubleToLongBits(percentAttackSpeedPerLevel) != Double.doubleToLongBits(other.percentAttackSpeedPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(percentBlock) != Double.doubleToLongBits(other.percentBlock)) {
            return false;
        }
        if(Double.doubleToLongBits(percentCooldownReductionPerLevel) != Double.doubleToLongBits(other.percentCooldownReductionPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(percentCooldownReduction) != Double.doubleToLongBits(other.percentCooldownReduction)) {
            return false;
        }
        if(Double.doubleToLongBits(percentCriticalStrikeChance) != Double.doubleToLongBits(other.percentCriticalStrikeChance)) {
            return false;
        }
        if(Double.doubleToLongBits(percentCriticalStrikeDamage) != Double.doubleToLongBits(other.percentCriticalStrikeDamage)) {
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
        if(Double.doubleToLongBits(percentMagicPenetration) != Double.doubleToLongBits(other.percentMagicPenetration)) {
            return false;
        }
        if(Double.doubleToLongBits(percentMagicPenetrationPerLevel) != Double.doubleToLongBits(other.percentMagicPenetrationPerLevel)) {
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
        if(Double.doubleToLongBits(percentMovespeedPerLevel) != Double.doubleToLongBits(other.percentMovespeedPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(percentSpellVamp) != Double.doubleToLongBits(other.percentSpellVamp)) {
            return false;
        }
        if(Double.doubleToLongBits(percentTimeSpentDead) != Double.doubleToLongBits(other.percentTimeSpentDead)) {
            return false;
        }
        if(Double.doubleToLongBits(percentTimeSpentDeadPerLevel) != Double.doubleToLongBits(other.percentTimeSpentDeadPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(timeSpentDead) != Double.doubleToLongBits(other.timeSpentDead)) {
            return false;
        }
        if(Double.doubleToLongBits(timeSpentDeadPerLevel) != Double.doubleToLongBits(other.timeSpentDeadPerLevel)) {
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
     * @return the abilityPowerPerLevel
     */
    public double getAbilityPowerPerLevel() {
        return abilityPowerPerLevel;
    }

    /**
     * @return the armor
     */
    public double getArmor() {
        return armor;
    }

    /**
     * @return the armorPenetration
     */
    public double getArmorPenetration() {
        return armorPenetration;
    }

    /**
     * @return the armorPenetrationPerLevel
     */
    public double getArmorPenetrationPerLevel() {
        return armorPenetrationPerLevel;
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
     * @return the criticalStrikeChancePerLevel
     */
    public double getCriticalStrikeChancePerLevel() {
        return criticalStrikeChancePerLevel;
    }

    /**
     * @return the criticalStrikeDamage
     */
    public double getCriticalStrikeDamage() {
        return criticalStrikeDamage;
    }

    /**
     * @return the criticalStrikeDamagePerLevel
     */
    public double getCriticalStrikeDamagePerLevel() {
        return criticalStrikeDamagePerLevel;
    }

    /**
     * @return the dodge
     */
    public double getDodge() {
        return dodge;
    }

    /**
     * @return the dodgePerLevel
     */
    public double getDodgePerLevel() {
        return dodgePerLevel;
    }

    /**
     * @return the energy
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * @return the energyPerLevel
     */
    public double getEnergyPerLevel() {
        return energyPerLevel;
    }

    /**
     * @return the energyRegen
     */
    public double getEnergyRegen() {
        return energyRegen;
    }

    /**
     * @return the energyRegenPerLevel
     */
    public double getEnergyRegenPerLevel() {
        return energyRegenPerLevel;
    }

    /**
     * @return the experience
     */
    public double getExperience() {
        return experience;
    }

    /**
     * @return the goldPer10
     */
    public double getGoldPer10() {
        return goldPer10;
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
     * @return the magicPenetration
     */
    public double getMagicPenetration() {
        return magicPenetration;
    }

    /**
     * @return the magicPenetrationPerLevel
     */
    public double getMagicPenetrationPerLevel() {
        return magicPenetrationPerLevel;
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

    /**
     * @return the movespeedPerLevel
     */
    public double getMovespeedPerLevel() {
        return movespeedPerLevel;
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
     * @return the percentArmorPenetrationPerLevel
     */
    public double getPercentArmorPenetrationPerLevel() {
        return percentArmorPenetrationPerLevel;
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
     * @return the percentAttackSpeedPerLevel
     */
    public double getPercentAttackSpeedPerLevel() {
        return percentAttackSpeedPerLevel;
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
     * @return the percentCooldownReductionPerLevel
     */
    public double getPercentCooldownReductionPerLevel() {
        return percentCooldownReductionPerLevel;
    }

    /**
     * @return the percentCriticalStrikeChance
     */
    public double getPercentCriticalStrikeChance() {
        return percentCriticalStrikeChance;
    }

    /**
     * @return the percentCriticalStrikeDamage
     */
    public double getPercentCriticalStrikeDamage() {
        return percentCriticalStrikeDamage;
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
     * @return the percentMagicPenetration
     */
    public double getPercentMagicPenetration() {
        return percentMagicPenetration;
    }

    /**
     * @return the percentMagicPenetrationPerLevel
     */
    public double getPercentMagicPenetrationPerLevel() {
        return percentMagicPenetrationPerLevel;
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
     * @return the percentMovespeedPerLevel
     */
    public double getPercentMovespeedPerLevel() {
        return percentMovespeedPerLevel;
    }

    /**
     * @return the percentSpellVamp
     */
    public double getPercentSpellVamp() {
        return percentSpellVamp;
    }

    /**
     * @return the percentTimeSpentDead
     */
    public double getPercentTimeSpentDead() {
        return percentTimeSpentDead;
    }

    /**
     * @return the percentTimeSpentDeadPerLevel
     */
    public double getPercentTimeSpentDeadPerLevel() {
        return percentTimeSpentDeadPerLevel;
    }

    /**
     * @return the timeSpentDead
     */
    public double getTimeSpentDead() {
        return timeSpentDead;
    }

    /**
     * @return the timeSpentDeadPerLevel
     */
    public double getTimeSpentDeadPerLevel() {
        return timeSpentDeadPerLevel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(abilityPower);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(abilityPowerPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(armor);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(armorPenetration);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(armorPenetrationPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(armorPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackDamagePerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(attackSpeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(block);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(criticalStrikeChance);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(criticalStrikeChancePerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(criticalStrikeDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(criticalStrikeDamagePerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(dodge);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(dodgePerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(energy);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(energyPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(energyRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(energyRegenPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(experience);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(goldPer10);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(health);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(healthPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(healthRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(healthRegenPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(magicPenetration);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(magicPenetrationPerLevel);
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
        temp = Double.doubleToLongBits(movespeedPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentAbilityPower);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentArmor);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentArmorPenetration);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentArmorPenetrationPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentAttackDamage);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentAttackSpeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentAttackSpeedPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentBlock);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentCooldownReductionPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentCooldownReduction);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentCriticalStrikeChance);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentCriticalStrikeDamage);
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
        temp = Double.doubleToLongBits(percentMagicPenetration);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMagicPenetrationPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMagicResist);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMana);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentManaRegen);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMovespeed);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentMovespeedPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentSpellVamp);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentTimeSpentDead);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(percentTimeSpentDeadPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(timeSpentDead);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(timeSpentDeadPerLevel);
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
     * @param abilityPowerPerLevel
     *        the abilityPowerPerLevel to set
     */
    public void setAbilityPowerPerLevel(final double abilityPowerPerLevel) {
        this.abilityPowerPerLevel = abilityPowerPerLevel;
    }

    /**
     * @param armor
     *        the armor to set
     */
    public void setArmor(final double armor) {
        this.armor = armor;
    }

    /**
     * @param armorPenetration
     *        the armorPenetration to set
     */
    public void setArmorPenetration(final double armorPenetration) {
        this.armorPenetration = armorPenetration;
    }

    /**
     * @param armorPenetrationPerLevel
     *        the armorPenetrationPerLevel to set
     */
    public void setArmorPenetrationPerLevel(final double armorPenetrationPerLevel) {
        this.armorPenetrationPerLevel = armorPenetrationPerLevel;
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
     * @param criticalStrikeChancePerLevel
     *        the criticalStrikeChancePerLevel to set
     */
    public void setCriticalStrikeChancePerLevel(final double criticalStrikeChancePerLevel) {
        this.criticalStrikeChancePerLevel = criticalStrikeChancePerLevel;
    }

    /**
     * @param criticalStrikeDamage
     *        the criticalStrikeDamage to set
     */
    public void setCriticalStrikeDamage(final double criticalStrikeDamage) {
        this.criticalStrikeDamage = criticalStrikeDamage;
    }

    /**
     * @param criticalStrikeDamagePerLevel
     *        the criticalStrikeDamagePerLevel to set
     */
    public void setCriticalStrikeDamagePerLevel(final double criticalStrikeDamagePerLevel) {
        this.criticalStrikeDamagePerLevel = criticalStrikeDamagePerLevel;
    }

    /**
     * @param dodge
     *        the dodge to set
     */
    public void setDodge(final double dodge) {
        this.dodge = dodge;
    }

    /**
     * @param dodgePerLevel
     *        the dodgePerLevel to set
     */
    public void setDodgePerLevel(final double dodgePerLevel) {
        this.dodgePerLevel = dodgePerLevel;
    }

    /**
     * @param energy
     *        the energy to set
     */
    public void setEnergy(final double energy) {
        this.energy = energy;
    }

    /**
     * @param energyPerLevel
     *        the energyPerLevel to set
     */
    public void setEnergyPerLevel(final double energyPerLevel) {
        this.energyPerLevel = energyPerLevel;
    }

    /**
     * @param energyRegen
     *        the energyRegen to set
     */
    public void setEnergyRegen(final double energyRegen) {
        this.energyRegen = energyRegen;
    }

    /**
     * @param energyRegenPerLevel
     *        the energyRegenPerLevel to set
     */
    public void setEnergyRegenPerLevel(final double energyRegenPerLevel) {
        this.energyRegenPerLevel = energyRegenPerLevel;
    }

    /**
     * @param experience
     *        the experience to set
     */
    public void setExperience(final double experience) {
        this.experience = experience;
    }

    /**
     * @param goldPer10
     *        the goldPer10 to set
     */
    public void setGoldPer10(final double goldPer10) {
        this.goldPer10 = goldPer10;
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
     * @param magicPenetration
     *        the magicPenetration to set
     */
    public void setMagicPenetration(final double magicPenetration) {
        this.magicPenetration = magicPenetration;
    }

    /**
     * @param magicPenetrationPerLevel
     *        the magicPenetrationPerLevel to set
     */
    public void setMagicPenetrationPerLevel(final double magicPenetrationPerLevel) {
        this.magicPenetrationPerLevel = magicPenetrationPerLevel;
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

    /**
     * @param movespeedPerLevel
     *        the movespeedPerLevel to set
     */
    public void setMovespeedPerLevel(final double movespeedPerLevel) {
        this.movespeedPerLevel = movespeedPerLevel;
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
     * @param percentArmorPenetrationPerLevel
     *        the percentArmorPenetrationPerLevel to set
     */
    public void setPercentArmorPenetrationPerLevel(final double percentArmorPenetrationPerLevel) {
        this.percentArmorPenetrationPerLevel = percentArmorPenetrationPerLevel;
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
     * @param percentAttackSpeedPerLevel
     *        the percentAttackSpeedPerLevel to set
     */
    public void setPercentAttackSpeedPerLevel(final double percentAttackSpeedPerLevel) {
        this.percentAttackSpeedPerLevel = percentAttackSpeedPerLevel;
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
     * @param percentCooldownReductionPerLevel
     *        the percentCooldownReductionPerLevel to set
     */
    public void setPercentCooldownReductionPerLevel(final double percentCooldownReductionPerLevel) {
        this.percentCooldownReductionPerLevel = percentCooldownReductionPerLevel;
    }

    /**
     * @param percentCriticalStrikeChance
     *        the percentCriticalStrikeChance to set
     */
    public void setPercentCriticalStrikeChance(final double percentCriticalStrikeChance) {
        this.percentCriticalStrikeChance = percentCriticalStrikeChance;
    }

    /**
     * @param percentCriticalStrikeDamage
     *        the percentCriticalStrikeDamage to set
     */
    public void setPercentCriticalStrikeDamage(final double percentCriticalStrikeDamage) {
        this.percentCriticalStrikeDamage = percentCriticalStrikeDamage;
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
     * @param percentMagicPenetration
     *        the percentMagicPenetration to set
     */
    public void setPercentMagicPenetration(final double percentMagicPenetration) {
        this.percentMagicPenetration = percentMagicPenetration;
    }

    /**
     * @param percentMagicPenetrationPerLevel
     *        the percentMagicPenetrationPerLevel to set
     */
    public void setPercentMagicPenetrationPerLevel(final double percentMagicPenetrationPerLevel) {
        this.percentMagicPenetrationPerLevel = percentMagicPenetrationPerLevel;
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
     * @param percentMovespeedPerLevel
     *        the percentMovespeedPerLevel to set
     */
    public void setPercentMovespeedPerLevel(final double percentMovespeedPerLevel) {
        this.percentMovespeedPerLevel = percentMovespeedPerLevel;
    }

    /**
     * @param percentSpellVamp
     *        the percentSpellVamp to set
     */
    public void setPercentSpellVamp(final double percentSpellVamp) {
        this.percentSpellVamp = percentSpellVamp;
    }

    /**
     * @param percentTimeSpentDead
     *        the percentTimeSpentDead to set
     */
    public void setPercentTimeSpentDead(final double percentTimeSpentDead) {
        this.percentTimeSpentDead = percentTimeSpentDead;
    }

    /**
     * @param percentTimeSpentDeadPerLevel
     *        the percentTimeSpentDeadPerLevel to set
     */
    public void setPercentTimeSpentDeadPerLevel(final double percentTimeSpentDeadPerLevel) {
        this.percentTimeSpentDeadPerLevel = percentTimeSpentDeadPerLevel;
    }

    /**
     * @param timeSpentDead
     *        the timeSpentDead to set
     */
    public void setTimeSpentDead(final double timeSpentDead) {
        this.timeSpentDead = timeSpentDead;
    }

    /**
     * @param timeSpentDeadPerLevel
     *        the timeSpentDeadPerLevel to set
     */
    public void setTimeSpentDeadPerLevel(final double timeSpentDeadPerLevel) {
        this.timeSpentDeadPerLevel = timeSpentDeadPerLevel;
    }

}
