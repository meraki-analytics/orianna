package com.merakianalytics.orianna.types.core.staticdata;

import com.merakianalytics.orianna.types.core.OriannaObject;

public class ChampionStats extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.ChampionStats> {
    private static final long serialVersionUID = 2634777703036666390L;

    public ChampionStats(final com.merakianalytics.orianna.types.data.staticdata.ChampionStats coreData) {
        super(coreData);
    }

    public double getArmor() {
        return coreData.getArmor();
    }

    public double getArmorPerLevel() {
        return coreData.getArmorPerLevel();
    }

    public double getAttackDamage() {
        return coreData.getAttackDamage();
    }

    public double getAttackDamagePerLevel() {
        return coreData.getAttackDamagePerLevel();
    }

    public double getAttackRange() {
        return coreData.getAttackRange();
    }

    public double getAttackSpeedOffset() {
        return coreData.getAttackSpeedOffset();
    }

    public double getAttackSpeedPerLevel() {
        return coreData.getAttackSpeedPerLevel();
    }

    public double getCriticalStrikeChance() {
        return coreData.getCriticalStrikeChance();
    }

    public double getCriticalStrikeChancePerLevel() {
        return coreData.getCriticalStrikeChancePerLevel();
    }

    public double getHealth() {
        return coreData.getHealth();
    }

    public double getHealthPerLevel() {
        return coreData.getHealthPerLevel();
    }

    public double getHealthRegen() {
        return coreData.getHealthRegen();
    }

    public double getHealthRegenPerLevel() {
        return coreData.getHealthRegenPerLevel();
    }

    public double getMagicResist() {
        return coreData.getMagicResist();
    }

    public double getMagicResistPerLevel() {
        return coreData.getMagicResistPerLevel();
    }

    public double getMana() {
        return coreData.getMana();
    }

    public double getManaPerLevel() {
        return coreData.getManaPerLevel();
    }

    public double getManaRegen() {
        return coreData.getManaRegen();
    }

    public double getManaRegenPerLevel() {
        return coreData.getManaRegenPerLevel();
    }

    public double getMovespeed() {
        return coreData.getMovespeed();
    }

}
