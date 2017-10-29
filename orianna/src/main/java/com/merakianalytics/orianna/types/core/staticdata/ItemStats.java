package com.merakianalytics.orianna.types.core.staticdata;

import com.merakianalytics.orianna.types.core.OriannaObject;

public class ItemStats extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.ItemStats> {
    private static final long serialVersionUID = 4503039969668296325L;

    public ItemStats(final com.merakianalytics.orianna.types.data.staticdata.ItemStats coreData) {
        super(coreData);
    }

    public double getAbilityPower() {
        return coreData.getAbilityPower();
    }

    public double getArmor() {
        return coreData.getArmor();
    }

    public double getAttackDamage() {
        return coreData.getAttackDamage();
    }

    public double getAttackSpeed() {
        return coreData.getAttackSpeed();
    }

    public double getBlock() {
        return coreData.getBlock();
    }

    public double getCriticalStrikeChance() {
        return coreData.getCriticalStrikeChance();
    }

    public double getCriticalStrikeDamage() {
        return coreData.getCriticalStrikeDamage();
    }

    public double getEnergy() {
        return coreData.getEnergy();
    }

    public double getEnergyRegen() {
        return coreData.getEnergyRegen();
    }

    public double getExperience() {
        return coreData.getExperience();
    }

    public double getHealth() {
        return coreData.getHealth();
    }

    public double getHealthRegen() {
        return coreData.getHealthRegen();
    }

    public double getMagicResist() {
        return coreData.getMagicResist();
    }

    public double getMana() {
        return coreData.getMana();
    }

    public double getManaRegen() {
        return coreData.getManaRegen();
    }

    public double getMovespeed() {
        return coreData.getMovespeed();
    }

    public double getPercentAbilityPower() {
        return coreData.getPercentAbilityPower();
    }

    public double getPercentArmor() {
        return coreData.getPercentArmor();
    }

    public double getPercentAttackDamage() {
        return coreData.getPercentAttackDamage();
    }

    public double getPercentAttackSpeed() {
        return coreData.getPercentAttackSpeed();
    }

    public double getPercentBlock() {
        return coreData.getPercentBlock();
    }

    public double getPercentCriticalStrikeChance() {
        return coreData.getPercentCriticalStrikeChance();
    }

    public double getPercentCriticalStrikeChange() {
        return coreData.getPercentCriticalStrikeChange();
    }

    public double getPercentDodge() {
        return coreData.getPercentDodge();
    }

    public double getPercentExperience() {
        return coreData.getPercentExperience();
    }

    public double getPercentHealth() {
        return coreData.getPercentHealth();
    }

    public double getPercentHealthRegen() {
        return coreData.getPercentHealthRegen();
    }

    public double getPercentLifesteal() {
        return coreData.getPercentLifesteal();
    }

    public double getPercentMagicResist() {
        return coreData.getPercentMagicResist();
    }

    public double getPercentMana() {
        return coreData.getPercentMana();
    }

    public double getPercentManaRegen() {
        return coreData.getPercentManaRegen();
    }

    public double getPercentMovespeed() {
        return coreData.getPercentMovespeed();
    }

    public double getPercentSpellVamp() {
        return coreData.getPercentSpellVamp();
    }
}
