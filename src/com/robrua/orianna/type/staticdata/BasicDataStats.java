package com.robrua.orianna.type.staticdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BasicDataStats implements Serializable {
    private static final long serialVersionUID = -3892233449766313123L;
    public final Double flatArmorMod, flatArmorPenetrationMod, flatAttackSpeedMod, flatBlockMod, flatCritChanceMod, flatCritDamageMod, flatEXPBonus,
            flatEnergyPoolMod, flatEnergyRegenMod, flatGoldPer10Mod, flatHPPoolMod, flatHPRegenMod, flatMPPoolMod, flatMPRegenMod, flatMagicDamageMod,
            flatMagicPenetrationMod, flatMovementSpeedMod, flatPhysicalDamageMod, flatSpellBlockMod, percentArmorMod, percentArmorPenetrationMod,
            percentAttackSpeedMod, percentBlockMod, percentCooldownMod, percentCritChanceMod, percentCritDamageMod, percentDodgeMod, percentEXPBonus,
            percentHPPoolMod, percentHPRegenMod, percentLifeStealMod, percentMPPoolMod, percentMPRegenMod, percentMagicDamageMod, percentMagicPenetrationMod,
            percentMovementSpeedMod, percentPhysicalDamageMod, percentSpellBlockMod, percentSpellVampMod, rFlatArmorModPerLevel, rFlatArmorPenetrationMod,
            rFlatArmorPenetrationModPerLevel, rFlatCritChanceModPerLevel, rFlatCritDamageModPerLevel, rFlatDodgeMod, rFlatDodgeModPerLevel,
            rFlatEnergyModPerLevel, rFlatEnergyRegenModPerLevel, rFlatGoldPer10Mod, rFlatHPModPerLevel, rFlatHPRegenModPerLevel, rFlatMPModPerLevel,
            rFlatMPRegenModPerLevel, rFlatMagicDamageModPerLevel, rFlatMagicPenetrationMod, rFlatMagicPenetrationModPerLevel, rFlatMovementSpeedModPerLevel,
            rFlatPhysicalDamageModPerLevel, rFlatSpellBlockModPerLevel, rFlatTimeDeadMod, rFlatTimeDeadModPerLevel, rPercentArmorPenetrationMod,
            rPercentArmorPenetrationModPerLevel, rPercentAttackSpeedModPerLevel, rPercentCooldownMod, rPercentCooldownModPerLevel, rPercentMagicPenetrationMod,
            rPercentMagicPenetrationModPerLevel, rPercentMovementSpeedModPerLevel, rPercentTimeDeadMod, rPercentTimeDeadModPerLevel;

    public BasicDataStats(final Double flatArmorMod, final Double flatArmorPenetrationMod, final Double flatAttackSpeedMod, final Double flatBlockMod,
            final Double flatCritChanceMod, final Double flatCritDamageMod, final Double flatEXPBonus, final Double flatEnergyPoolMod,
            final Double flatEnergyRegenMod, final Double flatGoldPer10Mod, final Double flatHPPoolMod, final Double flatHPRegenMod,
            final Double flatMPPoolMod, final Double flatMPRegenMod, final Double flatMagicDamageMod, final Double flatMagicPenetrationMod,
            final Double flatMovementSpeedMod, final Double flatPhysicalDamageMod, final Double flatSpellBlockMod, final Double percentArmorMod,
            final Double percentArmorPenetrationMod, final Double percentAttackSpeedMod, final Double percentBlockMod, final Double percentCooldownMod,
            final Double percentCritChanceMod, final Double percentCritDamageMod, final Double percentDodgeMod, final Double percentEXPBonus,
            final Double percentHPPoolMod, final Double percentHPRegenMod, final Double percentLifeStealMod, final Double percentMPPoolMod,
            final Double percentMPRegenMod, final Double percentMagicDamageMod, final Double percentMagicPenetrationMod, final Double percentMovementSpeedMod,
            final Double percentPhysicalDamageMod, final Double percentSpellBlockMod, final Double percentSpellVampMod, final Double rFlatArmorModPerLevel,
            final Double rFlatArmorPenetrationMod, final Double rFlatArmorPenetrationModPerLevel, final Double rFlatCritChanceModPerLevel,
            final Double rFlatCritDamageModPerLevel, final Double rFlatDodgeMod, final Double rFlatDodgeModPerLevel, final Double rFlatEnergyModPerLevel,
            final Double rFlatEnergyRegenModPerLevel, final Double rFlatGoldPer10Mod, final Double rFlatHPModPerLevel, final Double rFlatHPRegenModPerLevel,
            final Double rFlatMPModPerLevel, final Double rFlatMPRegenModPerLevel, final Double rFlatMagicDamageModPerLevel,
            final Double rFlatMagicPenetrationMod, final Double rFlatMagicPenetrationModPerLevel, final Double rFlatMovementSpeedModPerLevel,
            final Double rFlatPhysicalDamageModPerLevel, final Double rFlatSpellBlockModPerLevel, final Double rFlatTimeDeadMod,
            final Double rFlatTimeDeadModPerLevel, final Double rPercentArmorPenetrationMod, final Double rPercentArmorPenetrationModPerLevel,
            final Double rPercentAttackSpeedModPerLevel, final Double rPercentCooldownMod, final Double rPercentCooldownModPerLevel,
            final Double rPercentMagicPenetrationMod, final Double rPercentMagicPenetrationModPerLevel, final Double rPercentMovementSpeedModPerLevel,
            final Double rPercentTimeDeadMod, final Double rPercentTimeDeadModPerLevel) {
        this.flatArmorMod = flatArmorMod;
        this.flatArmorPenetrationMod = flatArmorPenetrationMod;
        this.flatAttackSpeedMod = flatAttackSpeedMod;
        this.flatBlockMod = flatBlockMod;
        this.flatCritChanceMod = flatCritChanceMod;
        this.flatCritDamageMod = flatCritDamageMod;
        this.flatEXPBonus = flatEXPBonus;
        this.flatEnergyPoolMod = flatEnergyPoolMod;
        this.flatEnergyRegenMod = flatEnergyRegenMod;
        this.flatGoldPer10Mod = flatGoldPer10Mod;
        this.flatHPPoolMod = flatHPPoolMod;
        this.flatHPRegenMod = flatHPRegenMod;
        this.flatMPPoolMod = flatMPPoolMod;
        this.flatMPRegenMod = flatMPRegenMod;
        this.flatMagicDamageMod = flatMagicDamageMod;
        this.flatMagicPenetrationMod = flatMagicPenetrationMod;
        this.flatMovementSpeedMod = flatMovementSpeedMod;
        this.flatPhysicalDamageMod = flatPhysicalDamageMod;
        this.flatSpellBlockMod = flatSpellBlockMod;
        this.percentArmorMod = percentArmorMod;
        this.percentArmorPenetrationMod = percentArmorPenetrationMod;
        this.percentAttackSpeedMod = percentAttackSpeedMod;
        this.percentBlockMod = percentBlockMod;
        this.percentCooldownMod = percentCooldownMod;
        this.percentCritChanceMod = percentCritChanceMod;
        this.percentCritDamageMod = percentCritDamageMod;
        this.percentDodgeMod = percentDodgeMod;
        this.percentEXPBonus = percentEXPBonus;
        this.percentHPPoolMod = percentHPPoolMod;
        this.percentHPRegenMod = percentHPRegenMod;
        this.percentLifeStealMod = percentLifeStealMod;
        this.percentMPPoolMod = percentMPPoolMod;
        this.percentMPRegenMod = percentMPRegenMod;
        this.percentMagicDamageMod = percentMagicDamageMod;
        this.percentMagicPenetrationMod = percentMagicPenetrationMod;
        this.percentMovementSpeedMod = percentMovementSpeedMod;
        this.percentPhysicalDamageMod = percentPhysicalDamageMod;
        this.percentSpellBlockMod = percentSpellBlockMod;
        this.percentSpellVampMod = percentSpellVampMod;
        this.rFlatArmorModPerLevel = rFlatArmorModPerLevel;
        this.rFlatArmorPenetrationMod = rFlatArmorPenetrationMod;
        this.rFlatArmorPenetrationModPerLevel = rFlatArmorPenetrationModPerLevel;
        this.rFlatCritChanceModPerLevel = rFlatCritChanceModPerLevel;
        this.rFlatCritDamageModPerLevel = rFlatCritDamageModPerLevel;
        this.rFlatDodgeMod = rFlatDodgeMod;
        this.rFlatDodgeModPerLevel = rFlatDodgeModPerLevel;
        this.rFlatEnergyModPerLevel = rFlatEnergyModPerLevel;
        this.rFlatEnergyRegenModPerLevel = rFlatEnergyRegenModPerLevel;
        this.rFlatGoldPer10Mod = rFlatGoldPer10Mod;
        this.rFlatHPModPerLevel = rFlatHPModPerLevel;
        this.rFlatHPRegenModPerLevel = rFlatHPRegenModPerLevel;
        this.rFlatMPModPerLevel = rFlatMPModPerLevel;
        this.rFlatMPRegenModPerLevel = rFlatMPRegenModPerLevel;
        this.rFlatMagicDamageModPerLevel = rFlatMagicDamageModPerLevel;
        this.rFlatMagicPenetrationMod = rFlatMagicPenetrationMod;
        this.rFlatMagicPenetrationModPerLevel = rFlatMagicPenetrationModPerLevel;
        this.rFlatMovementSpeedModPerLevel = rFlatMovementSpeedModPerLevel;
        this.rFlatPhysicalDamageModPerLevel = rFlatPhysicalDamageModPerLevel;
        this.rFlatSpellBlockModPerLevel = rFlatSpellBlockModPerLevel;
        this.rFlatTimeDeadMod = rFlatTimeDeadMod;
        this.rFlatTimeDeadModPerLevel = rFlatTimeDeadModPerLevel;
        this.rPercentArmorPenetrationMod = rPercentArmorPenetrationMod;
        this.rPercentArmorPenetrationModPerLevel = rPercentArmorPenetrationModPerLevel;
        this.rPercentAttackSpeedModPerLevel = rPercentAttackSpeedModPerLevel;
        this.rPercentCooldownMod = rPercentCooldownMod;
        this.rPercentCooldownModPerLevel = rPercentCooldownModPerLevel;
        this.rPercentMagicPenetrationMod = rPercentMagicPenetrationMod;
        this.rPercentMagicPenetrationModPerLevel = rPercentMagicPenetrationModPerLevel;
        this.rPercentMovementSpeedModPerLevel = rPercentMovementSpeedModPerLevel;
        this.rPercentTimeDeadMod = rPercentTimeDeadMod;
        this.rPercentTimeDeadModPerLevel = rPercentTimeDeadModPerLevel;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof BasicDataStats)) {
            return false;
        }
        final BasicDataStats other = (BasicDataStats)obj;
        if(flatArmorMod == null) {
            if(other.flatArmorMod != null) {
                return false;
            }
        }
        else if(!flatArmorMod.equals(other.flatArmorMod)) {
            return false;
        }
        if(flatArmorPenetrationMod == null) {
            if(other.flatArmorPenetrationMod != null) {
                return false;
            }
        }
        else if(!flatArmorPenetrationMod.equals(other.flatArmorPenetrationMod)) {
            return false;
        }
        if(flatAttackSpeedMod == null) {
            if(other.flatAttackSpeedMod != null) {
                return false;
            }
        }
        else if(!flatAttackSpeedMod.equals(other.flatAttackSpeedMod)) {
            return false;
        }
        if(flatBlockMod == null) {
            if(other.flatBlockMod != null) {
                return false;
            }
        }
        else if(!flatBlockMod.equals(other.flatBlockMod)) {
            return false;
        }
        if(flatCritChanceMod == null) {
            if(other.flatCritChanceMod != null) {
                return false;
            }
        }
        else if(!flatCritChanceMod.equals(other.flatCritChanceMod)) {
            return false;
        }
        if(flatCritDamageMod == null) {
            if(other.flatCritDamageMod != null) {
                return false;
            }
        }
        else if(!flatCritDamageMod.equals(other.flatCritDamageMod)) {
            return false;
        }
        if(flatEXPBonus == null) {
            if(other.flatEXPBonus != null) {
                return false;
            }
        }
        else if(!flatEXPBonus.equals(other.flatEXPBonus)) {
            return false;
        }
        if(flatEnergyPoolMod == null) {
            if(other.flatEnergyPoolMod != null) {
                return false;
            }
        }
        else if(!flatEnergyPoolMod.equals(other.flatEnergyPoolMod)) {
            return false;
        }
        if(flatEnergyRegenMod == null) {
            if(other.flatEnergyRegenMod != null) {
                return false;
            }
        }
        else if(!flatEnergyRegenMod.equals(other.flatEnergyRegenMod)) {
            return false;
        }
        if(flatGoldPer10Mod == null) {
            if(other.flatGoldPer10Mod != null) {
                return false;
            }
        }
        else if(!flatGoldPer10Mod.equals(other.flatGoldPer10Mod)) {
            return false;
        }
        if(flatHPPoolMod == null) {
            if(other.flatHPPoolMod != null) {
                return false;
            }
        }
        else if(!flatHPPoolMod.equals(other.flatHPPoolMod)) {
            return false;
        }
        if(flatHPRegenMod == null) {
            if(other.flatHPRegenMod != null) {
                return false;
            }
        }
        else if(!flatHPRegenMod.equals(other.flatHPRegenMod)) {
            return false;
        }
        if(flatMPPoolMod == null) {
            if(other.flatMPPoolMod != null) {
                return false;
            }
        }
        else if(!flatMPPoolMod.equals(other.flatMPPoolMod)) {
            return false;
        }
        if(flatMPRegenMod == null) {
            if(other.flatMPRegenMod != null) {
                return false;
            }
        }
        else if(!flatMPRegenMod.equals(other.flatMPRegenMod)) {
            return false;
        }
        if(flatMagicDamageMod == null) {
            if(other.flatMagicDamageMod != null) {
                return false;
            }
        }
        else if(!flatMagicDamageMod.equals(other.flatMagicDamageMod)) {
            return false;
        }
        if(flatMagicPenetrationMod == null) {
            if(other.flatMagicPenetrationMod != null) {
                return false;
            }
        }
        else if(!flatMagicPenetrationMod.equals(other.flatMagicPenetrationMod)) {
            return false;
        }
        if(flatMovementSpeedMod == null) {
            if(other.flatMovementSpeedMod != null) {
                return false;
            }
        }
        else if(!flatMovementSpeedMod.equals(other.flatMovementSpeedMod)) {
            return false;
        }
        if(flatPhysicalDamageMod == null) {
            if(other.flatPhysicalDamageMod != null) {
                return false;
            }
        }
        else if(!flatPhysicalDamageMod.equals(other.flatPhysicalDamageMod)) {
            return false;
        }
        if(flatSpellBlockMod == null) {
            if(other.flatSpellBlockMod != null) {
                return false;
            }
        }
        else if(!flatSpellBlockMod.equals(other.flatSpellBlockMod)) {
            return false;
        }
        if(percentArmorMod == null) {
            if(other.percentArmorMod != null) {
                return false;
            }
        }
        else if(!percentArmorMod.equals(other.percentArmorMod)) {
            return false;
        }
        if(percentArmorPenetrationMod == null) {
            if(other.percentArmorPenetrationMod != null) {
                return false;
            }
        }
        else if(!percentArmorPenetrationMod.equals(other.percentArmorPenetrationMod)) {
            return false;
        }
        if(percentAttackSpeedMod == null) {
            if(other.percentAttackSpeedMod != null) {
                return false;
            }
        }
        else if(!percentAttackSpeedMod.equals(other.percentAttackSpeedMod)) {
            return false;
        }
        if(percentBlockMod == null) {
            if(other.percentBlockMod != null) {
                return false;
            }
        }
        else if(!percentBlockMod.equals(other.percentBlockMod)) {
            return false;
        }
        if(percentCooldownMod == null) {
            if(other.percentCooldownMod != null) {
                return false;
            }
        }
        else if(!percentCooldownMod.equals(other.percentCooldownMod)) {
            return false;
        }
        if(percentCritChanceMod == null) {
            if(other.percentCritChanceMod != null) {
                return false;
            }
        }
        else if(!percentCritChanceMod.equals(other.percentCritChanceMod)) {
            return false;
        }
        if(percentCritDamageMod == null) {
            if(other.percentCritDamageMod != null) {
                return false;
            }
        }
        else if(!percentCritDamageMod.equals(other.percentCritDamageMod)) {
            return false;
        }
        if(percentDodgeMod == null) {
            if(other.percentDodgeMod != null) {
                return false;
            }
        }
        else if(!percentDodgeMod.equals(other.percentDodgeMod)) {
            return false;
        }
        if(percentEXPBonus == null) {
            if(other.percentEXPBonus != null) {
                return false;
            }
        }
        else if(!percentEXPBonus.equals(other.percentEXPBonus)) {
            return false;
        }
        if(percentHPPoolMod == null) {
            if(other.percentHPPoolMod != null) {
                return false;
            }
        }
        else if(!percentHPPoolMod.equals(other.percentHPPoolMod)) {
            return false;
        }
        if(percentHPRegenMod == null) {
            if(other.percentHPRegenMod != null) {
                return false;
            }
        }
        else if(!percentHPRegenMod.equals(other.percentHPRegenMod)) {
            return false;
        }
        if(percentLifeStealMod == null) {
            if(other.percentLifeStealMod != null) {
                return false;
            }
        }
        else if(!percentLifeStealMod.equals(other.percentLifeStealMod)) {
            return false;
        }
        if(percentMPPoolMod == null) {
            if(other.percentMPPoolMod != null) {
                return false;
            }
        }
        else if(!percentMPPoolMod.equals(other.percentMPPoolMod)) {
            return false;
        }
        if(percentMPRegenMod == null) {
            if(other.percentMPRegenMod != null) {
                return false;
            }
        }
        else if(!percentMPRegenMod.equals(other.percentMPRegenMod)) {
            return false;
        }
        if(percentMagicDamageMod == null) {
            if(other.percentMagicDamageMod != null) {
                return false;
            }
        }
        else if(!percentMagicDamageMod.equals(other.percentMagicDamageMod)) {
            return false;
        }
        if(percentMagicPenetrationMod == null) {
            if(other.percentMagicPenetrationMod != null) {
                return false;
            }
        }
        else if(!percentMagicPenetrationMod.equals(other.percentMagicPenetrationMod)) {
            return false;
        }
        if(percentMovementSpeedMod == null) {
            if(other.percentMovementSpeedMod != null) {
                return false;
            }
        }
        else if(!percentMovementSpeedMod.equals(other.percentMovementSpeedMod)) {
            return false;
        }
        if(percentPhysicalDamageMod == null) {
            if(other.percentPhysicalDamageMod != null) {
                return false;
            }
        }
        else if(!percentPhysicalDamageMod.equals(other.percentPhysicalDamageMod)) {
            return false;
        }
        if(percentSpellBlockMod == null) {
            if(other.percentSpellBlockMod != null) {
                return false;
            }
        }
        else if(!percentSpellBlockMod.equals(other.percentSpellBlockMod)) {
            return false;
        }
        if(percentSpellVampMod == null) {
            if(other.percentSpellVampMod != null) {
                return false;
            }
        }
        else if(!percentSpellVampMod.equals(other.percentSpellVampMod)) {
            return false;
        }
        if(rFlatArmorModPerLevel == null) {
            if(other.rFlatArmorModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatArmorModPerLevel.equals(other.rFlatArmorModPerLevel)) {
            return false;
        }
        if(rFlatArmorPenetrationMod == null) {
            if(other.rFlatArmorPenetrationMod != null) {
                return false;
            }
        }
        else if(!rFlatArmorPenetrationMod.equals(other.rFlatArmorPenetrationMod)) {
            return false;
        }
        if(rFlatArmorPenetrationModPerLevel == null) {
            if(other.rFlatArmorPenetrationModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatArmorPenetrationModPerLevel.equals(other.rFlatArmorPenetrationModPerLevel)) {
            return false;
        }
        if(rFlatCritChanceModPerLevel == null) {
            if(other.rFlatCritChanceModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatCritChanceModPerLevel.equals(other.rFlatCritChanceModPerLevel)) {
            return false;
        }
        if(rFlatCritDamageModPerLevel == null) {
            if(other.rFlatCritDamageModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatCritDamageModPerLevel.equals(other.rFlatCritDamageModPerLevel)) {
            return false;
        }
        if(rFlatDodgeMod == null) {
            if(other.rFlatDodgeMod != null) {
                return false;
            }
        }
        else if(!rFlatDodgeMod.equals(other.rFlatDodgeMod)) {
            return false;
        }
        if(rFlatDodgeModPerLevel == null) {
            if(other.rFlatDodgeModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatDodgeModPerLevel.equals(other.rFlatDodgeModPerLevel)) {
            return false;
        }
        if(rFlatEnergyModPerLevel == null) {
            if(other.rFlatEnergyModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatEnergyModPerLevel.equals(other.rFlatEnergyModPerLevel)) {
            return false;
        }
        if(rFlatEnergyRegenModPerLevel == null) {
            if(other.rFlatEnergyRegenModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatEnergyRegenModPerLevel.equals(other.rFlatEnergyRegenModPerLevel)) {
            return false;
        }
        if(rFlatGoldPer10Mod == null) {
            if(other.rFlatGoldPer10Mod != null) {
                return false;
            }
        }
        else if(!rFlatGoldPer10Mod.equals(other.rFlatGoldPer10Mod)) {
            return false;
        }
        if(rFlatHPModPerLevel == null) {
            if(other.rFlatHPModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatHPModPerLevel.equals(other.rFlatHPModPerLevel)) {
            return false;
        }
        if(rFlatHPRegenModPerLevel == null) {
            if(other.rFlatHPRegenModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatHPRegenModPerLevel.equals(other.rFlatHPRegenModPerLevel)) {
            return false;
        }
        if(rFlatMPModPerLevel == null) {
            if(other.rFlatMPModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatMPModPerLevel.equals(other.rFlatMPModPerLevel)) {
            return false;
        }
        if(rFlatMPRegenModPerLevel == null) {
            if(other.rFlatMPRegenModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatMPRegenModPerLevel.equals(other.rFlatMPRegenModPerLevel)) {
            return false;
        }
        if(rFlatMagicDamageModPerLevel == null) {
            if(other.rFlatMagicDamageModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatMagicDamageModPerLevel.equals(other.rFlatMagicDamageModPerLevel)) {
            return false;
        }
        if(rFlatMagicPenetrationMod == null) {
            if(other.rFlatMagicPenetrationMod != null) {
                return false;
            }
        }
        else if(!rFlatMagicPenetrationMod.equals(other.rFlatMagicPenetrationMod)) {
            return false;
        }
        if(rFlatMagicPenetrationModPerLevel == null) {
            if(other.rFlatMagicPenetrationModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatMagicPenetrationModPerLevel.equals(other.rFlatMagicPenetrationModPerLevel)) {
            return false;
        }
        if(rFlatMovementSpeedModPerLevel == null) {
            if(other.rFlatMovementSpeedModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatMovementSpeedModPerLevel.equals(other.rFlatMovementSpeedModPerLevel)) {
            return false;
        }
        if(rFlatPhysicalDamageModPerLevel == null) {
            if(other.rFlatPhysicalDamageModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatPhysicalDamageModPerLevel.equals(other.rFlatPhysicalDamageModPerLevel)) {
            return false;
        }
        if(rFlatSpellBlockModPerLevel == null) {
            if(other.rFlatSpellBlockModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatSpellBlockModPerLevel.equals(other.rFlatSpellBlockModPerLevel)) {
            return false;
        }
        if(rFlatTimeDeadMod == null) {
            if(other.rFlatTimeDeadMod != null) {
                return false;
            }
        }
        else if(!rFlatTimeDeadMod.equals(other.rFlatTimeDeadMod)) {
            return false;
        }
        if(rFlatTimeDeadModPerLevel == null) {
            if(other.rFlatTimeDeadModPerLevel != null) {
                return false;
            }
        }
        else if(!rFlatTimeDeadModPerLevel.equals(other.rFlatTimeDeadModPerLevel)) {
            return false;
        }
        if(rPercentArmorPenetrationMod == null) {
            if(other.rPercentArmorPenetrationMod != null) {
                return false;
            }
        }
        else if(!rPercentArmorPenetrationMod.equals(other.rPercentArmorPenetrationMod)) {
            return false;
        }
        if(rPercentArmorPenetrationModPerLevel == null) {
            if(other.rPercentArmorPenetrationModPerLevel != null) {
                return false;
            }
        }
        else if(!rPercentArmorPenetrationModPerLevel.equals(other.rPercentArmorPenetrationModPerLevel)) {
            return false;
        }
        if(rPercentAttackSpeedModPerLevel == null) {
            if(other.rPercentAttackSpeedModPerLevel != null) {
                return false;
            }
        }
        else if(!rPercentAttackSpeedModPerLevel.equals(other.rPercentAttackSpeedModPerLevel)) {
            return false;
        }
        if(rPercentCooldownMod == null) {
            if(other.rPercentCooldownMod != null) {
                return false;
            }
        }
        else if(!rPercentCooldownMod.equals(other.rPercentCooldownMod)) {
            return false;
        }
        if(rPercentCooldownModPerLevel == null) {
            if(other.rPercentCooldownModPerLevel != null) {
                return false;
            }
        }
        else if(!rPercentCooldownModPerLevel.equals(other.rPercentCooldownModPerLevel)) {
            return false;
        }
        if(rPercentMagicPenetrationMod == null) {
            if(other.rPercentMagicPenetrationMod != null) {
                return false;
            }
        }
        else if(!rPercentMagicPenetrationMod.equals(other.rPercentMagicPenetrationMod)) {
            return false;
        }
        if(rPercentMagicPenetrationModPerLevel == null) {
            if(other.rPercentMagicPenetrationModPerLevel != null) {
                return false;
            }
        }
        else if(!rPercentMagicPenetrationModPerLevel.equals(other.rPercentMagicPenetrationModPerLevel)) {
            return false;
        }
        if(rPercentMovementSpeedModPerLevel == null) {
            if(other.rPercentMovementSpeedModPerLevel != null) {
                return false;
            }
        }
        else if(!rPercentMovementSpeedModPerLevel.equals(other.rPercentMovementSpeedModPerLevel)) {
            return false;
        }
        if(rPercentTimeDeadMod == null) {
            if(other.rPercentTimeDeadMod != null) {
                return false;
            }
        }
        else if(!rPercentTimeDeadMod.equals(other.rPercentTimeDeadMod)) {
            return false;
        }
        if(rPercentTimeDeadModPerLevel == null) {
            if(other.rPercentTimeDeadModPerLevel != null) {
                return false;
            }
        }
        else if(!rPercentTimeDeadModPerLevel.equals(other.rPercentTimeDeadModPerLevel)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (flatArmorMod == null ? 0 : flatArmorMod.hashCode());
        result = prime * result + (flatArmorPenetrationMod == null ? 0 : flatArmorPenetrationMod.hashCode());
        result = prime * result + (flatAttackSpeedMod == null ? 0 : flatAttackSpeedMod.hashCode());
        result = prime * result + (flatBlockMod == null ? 0 : flatBlockMod.hashCode());
        result = prime * result + (flatCritChanceMod == null ? 0 : flatCritChanceMod.hashCode());
        result = prime * result + (flatCritDamageMod == null ? 0 : flatCritDamageMod.hashCode());
        result = prime * result + (flatEXPBonus == null ? 0 : flatEXPBonus.hashCode());
        result = prime * result + (flatEnergyPoolMod == null ? 0 : flatEnergyPoolMod.hashCode());
        result = prime * result + (flatEnergyRegenMod == null ? 0 : flatEnergyRegenMod.hashCode());
        result = prime * result + (flatGoldPer10Mod == null ? 0 : flatGoldPer10Mod.hashCode());
        result = prime * result + (flatHPPoolMod == null ? 0 : flatHPPoolMod.hashCode());
        result = prime * result + (flatHPRegenMod == null ? 0 : flatHPRegenMod.hashCode());
        result = prime * result + (flatMPPoolMod == null ? 0 : flatMPPoolMod.hashCode());
        result = prime * result + (flatMPRegenMod == null ? 0 : flatMPRegenMod.hashCode());
        result = prime * result + (flatMagicDamageMod == null ? 0 : flatMagicDamageMod.hashCode());
        result = prime * result + (flatMagicPenetrationMod == null ? 0 : flatMagicPenetrationMod.hashCode());
        result = prime * result + (flatMovementSpeedMod == null ? 0 : flatMovementSpeedMod.hashCode());
        result = prime * result + (flatPhysicalDamageMod == null ? 0 : flatPhysicalDamageMod.hashCode());
        result = prime * result + (flatSpellBlockMod == null ? 0 : flatSpellBlockMod.hashCode());
        result = prime * result + (percentArmorMod == null ? 0 : percentArmorMod.hashCode());
        result = prime * result + (percentArmorPenetrationMod == null ? 0 : percentArmorPenetrationMod.hashCode());
        result = prime * result + (percentAttackSpeedMod == null ? 0 : percentAttackSpeedMod.hashCode());
        result = prime * result + (percentBlockMod == null ? 0 : percentBlockMod.hashCode());
        result = prime * result + (percentCooldownMod == null ? 0 : percentCooldownMod.hashCode());
        result = prime * result + (percentCritChanceMod == null ? 0 : percentCritChanceMod.hashCode());
        result = prime * result + (percentCritDamageMod == null ? 0 : percentCritDamageMod.hashCode());
        result = prime * result + (percentDodgeMod == null ? 0 : percentDodgeMod.hashCode());
        result = prime * result + (percentEXPBonus == null ? 0 : percentEXPBonus.hashCode());
        result = prime * result + (percentHPPoolMod == null ? 0 : percentHPPoolMod.hashCode());
        result = prime * result + (percentHPRegenMod == null ? 0 : percentHPRegenMod.hashCode());
        result = prime * result + (percentLifeStealMod == null ? 0 : percentLifeStealMod.hashCode());
        result = prime * result + (percentMPPoolMod == null ? 0 : percentMPPoolMod.hashCode());
        result = prime * result + (percentMPRegenMod == null ? 0 : percentMPRegenMod.hashCode());
        result = prime * result + (percentMagicDamageMod == null ? 0 : percentMagicDamageMod.hashCode());
        result = prime * result + (percentMagicPenetrationMod == null ? 0 : percentMagicPenetrationMod.hashCode());
        result = prime * result + (percentMovementSpeedMod == null ? 0 : percentMovementSpeedMod.hashCode());
        result = prime * result + (percentPhysicalDamageMod == null ? 0 : percentPhysicalDamageMod.hashCode());
        result = prime * result + (percentSpellBlockMod == null ? 0 : percentSpellBlockMod.hashCode());
        result = prime * result + (percentSpellVampMod == null ? 0 : percentSpellVampMod.hashCode());
        result = prime * result + (rFlatArmorModPerLevel == null ? 0 : rFlatArmorModPerLevel.hashCode());
        result = prime * result + (rFlatArmorPenetrationMod == null ? 0 : rFlatArmorPenetrationMod.hashCode());
        result = prime * result + (rFlatArmorPenetrationModPerLevel == null ? 0 : rFlatArmorPenetrationModPerLevel.hashCode());
        result = prime * result + (rFlatCritChanceModPerLevel == null ? 0 : rFlatCritChanceModPerLevel.hashCode());
        result = prime * result + (rFlatCritDamageModPerLevel == null ? 0 : rFlatCritDamageModPerLevel.hashCode());
        result = prime * result + (rFlatDodgeMod == null ? 0 : rFlatDodgeMod.hashCode());
        result = prime * result + (rFlatDodgeModPerLevel == null ? 0 : rFlatDodgeModPerLevel.hashCode());
        result = prime * result + (rFlatEnergyModPerLevel == null ? 0 : rFlatEnergyModPerLevel.hashCode());
        result = prime * result + (rFlatEnergyRegenModPerLevel == null ? 0 : rFlatEnergyRegenModPerLevel.hashCode());
        result = prime * result + (rFlatGoldPer10Mod == null ? 0 : rFlatGoldPer10Mod.hashCode());
        result = prime * result + (rFlatHPModPerLevel == null ? 0 : rFlatHPModPerLevel.hashCode());
        result = prime * result + (rFlatHPRegenModPerLevel == null ? 0 : rFlatHPRegenModPerLevel.hashCode());
        result = prime * result + (rFlatMPModPerLevel == null ? 0 : rFlatMPModPerLevel.hashCode());
        result = prime * result + (rFlatMPRegenModPerLevel == null ? 0 : rFlatMPRegenModPerLevel.hashCode());
        result = prime * result + (rFlatMagicDamageModPerLevel == null ? 0 : rFlatMagicDamageModPerLevel.hashCode());
        result = prime * result + (rFlatMagicPenetrationMod == null ? 0 : rFlatMagicPenetrationMod.hashCode());
        result = prime * result + (rFlatMagicPenetrationModPerLevel == null ? 0 : rFlatMagicPenetrationModPerLevel.hashCode());
        result = prime * result + (rFlatMovementSpeedModPerLevel == null ? 0 : rFlatMovementSpeedModPerLevel.hashCode());
        result = prime * result + (rFlatPhysicalDamageModPerLevel == null ? 0 : rFlatPhysicalDamageModPerLevel.hashCode());
        result = prime * result + (rFlatSpellBlockModPerLevel == null ? 0 : rFlatSpellBlockModPerLevel.hashCode());
        result = prime * result + (rFlatTimeDeadMod == null ? 0 : rFlatTimeDeadMod.hashCode());
        result = prime * result + (rFlatTimeDeadModPerLevel == null ? 0 : rFlatTimeDeadModPerLevel.hashCode());
        result = prime * result + (rPercentArmorPenetrationMod == null ? 0 : rPercentArmorPenetrationMod.hashCode());
        result = prime * result + (rPercentArmorPenetrationModPerLevel == null ? 0 : rPercentArmorPenetrationModPerLevel.hashCode());
        result = prime * result + (rPercentAttackSpeedModPerLevel == null ? 0 : rPercentAttackSpeedModPerLevel.hashCode());
        result = prime * result + (rPercentCooldownMod == null ? 0 : rPercentCooldownMod.hashCode());
        result = prime * result + (rPercentCooldownModPerLevel == null ? 0 : rPercentCooldownModPerLevel.hashCode());
        result = prime * result + (rPercentMagicPenetrationMod == null ? 0 : rPercentMagicPenetrationMod.hashCode());
        result = prime * result + (rPercentMagicPenetrationModPerLevel == null ? 0 : rPercentMagicPenetrationModPerLevel.hashCode());
        result = prime * result + (rPercentMovementSpeedModPerLevel == null ? 0 : rPercentMovementSpeedModPerLevel.hashCode());
        result = prime * result + (rPercentTimeDeadMod == null ? 0 : rPercentTimeDeadMod.hashCode());
        result = prime * result + (rPercentTimeDeadModPerLevel == null ? 0 : rPercentTimeDeadModPerLevel.hashCode());
        return result;
    }

    /**
     * Gets only the stats which weren't null
     *
     * @return the non-null stats
     */
    public Map<String, Double> nonNullStats() {
        final Map<String, Double> nonNull = new HashMap<String, Double>();
        if(flatArmorMod != null) {
            nonNull.put("flatArmorMod", flatArmorMod);
        }
        if(flatArmorPenetrationMod != null) {
            nonNull.put("flatArmorPenetrationMod", flatArmorPenetrationMod);
        }
        if(flatAttackSpeedMod != null) {
            nonNull.put("flatAttackSpeedMod", flatAttackSpeedMod);
        }
        if(flatBlockMod != null) {
            nonNull.put("flatBlockMod", flatBlockMod);
        }
        if(flatCritChanceMod != null) {
            nonNull.put("flatCritChanceMod", flatCritChanceMod);
        }
        if(flatCritDamageMod != null) {
            nonNull.put("flatCritDamageMod", flatCritDamageMod);
        }
        if(flatEXPBonus != null) {
            nonNull.put("flatEXPBonus", flatEXPBonus);
        }
        if(flatEnergyPoolMod != null) {
            nonNull.put("flatEnergyPoolMod", flatEnergyPoolMod);
        }
        if(flatEnergyRegenMod != null) {
            nonNull.put("flatEnergyRegenMod", flatEnergyRegenMod);
        }
        if(flatGoldPer10Mod != null) {
            nonNull.put("flatGoldPer10Mod", flatGoldPer10Mod);
        }
        if(flatHPPoolMod != null) {
            nonNull.put("flatHPPoolMod", flatHPPoolMod);
        }
        if(flatHPRegenMod != null) {
            nonNull.put("flatHPRegenMod", flatHPRegenMod);
        }
        if(flatMPPoolMod != null) {
            nonNull.put("flatMPPoolMod", flatMPPoolMod);
        }
        if(flatMPRegenMod != null) {
            nonNull.put("flatMPRegenMod", flatMPRegenMod);
        }
        if(flatMagicDamageMod != null) {
            nonNull.put("flatMagicDamageMod", flatMagicDamageMod);
        }
        if(flatMagicPenetrationMod != null) {
            nonNull.put("flatMagicPenetrationMod", flatMagicPenetrationMod);
        }
        if(flatMovementSpeedMod != null) {
            nonNull.put("flatMovementSpeedMod", flatMovementSpeedMod);
        }
        if(flatPhysicalDamageMod != null) {
            nonNull.put("flatPhysicalDamageMod", flatPhysicalDamageMod);
        }
        if(flatSpellBlockMod != null) {
            nonNull.put("flatSpellBlockMod", flatSpellBlockMod);
        }
        if(percentArmorMod != null) {
            nonNull.put("percentArmorMod", percentArmorMod);
        }
        if(percentArmorPenetrationMod != null) {
            nonNull.put("percentArmorPenetrationMod", percentArmorPenetrationMod);
        }
        if(percentAttackSpeedMod != null) {
            nonNull.put("percentAttackSpeedMod", percentAttackSpeedMod);
        }
        if(percentBlockMod != null) {
            nonNull.put("percentBlockMod", percentBlockMod);
        }
        if(percentCooldownMod != null) {
            nonNull.put("percentCooldownMod", percentCooldownMod);
        }
        if(percentCritChanceMod != null) {
            nonNull.put("percentCritChanceMod", percentCritChanceMod);
        }
        if(percentCritDamageMod != null) {
            nonNull.put("percentCritDamageMod", percentCritDamageMod);
        }
        if(percentDodgeMod != null) {
            nonNull.put("percentDodgeMod", percentDodgeMod);
        }
        if(percentEXPBonus != null) {
            nonNull.put("percentEXPBonus", percentEXPBonus);
        }
        if(percentHPPoolMod != null) {
            nonNull.put("percentHPPoolMod", percentHPPoolMod);
        }
        if(percentHPRegenMod != null) {
            nonNull.put("percentHPRegenMod", percentHPRegenMod);
        }
        if(percentLifeStealMod != null) {
            nonNull.put("percentLifeStealMod", percentLifeStealMod);
        }
        if(percentMPPoolMod != null) {
            nonNull.put("percentMPPoolMod", percentMPPoolMod);
        }
        if(percentMPRegenMod != null) {
            nonNull.put("percentMPRegenMod", percentMPRegenMod);
        }
        if(percentMagicDamageMod != null) {
            nonNull.put("percentMagicDamageMod", percentMagicDamageMod);
        }
        if(percentMagicPenetrationMod != null) {
            nonNull.put("percentMagicPenetrationMod", percentMagicPenetrationMod);
        }
        if(percentMovementSpeedMod != null) {
            nonNull.put("percentMovementSpeedMod", percentMovementSpeedMod);
        }
        if(percentPhysicalDamageMod != null) {
            nonNull.put("percentPhysicalDamageMod", percentPhysicalDamageMod);
        }
        if(percentSpellBlockMod != null) {
            nonNull.put("percentSpellBlockMod", percentSpellBlockMod);
        }
        if(percentSpellVampMod != null) {
            nonNull.put("percentSpellVampMod", percentSpellVampMod);
        }
        if(rFlatArmorModPerLevel != null) {
            nonNull.put("rFlatArmorModPerLevel", rFlatArmorModPerLevel);
        }
        if(rFlatArmorPenetrationMod != null) {
            nonNull.put("rFlatArmorPenetrationMod", rFlatArmorPenetrationMod);
        }
        if(rFlatArmorPenetrationModPerLevel != null) {
            nonNull.put("rFlatArmorPenetrationModPerLevel", rFlatArmorPenetrationModPerLevel);
        }
        if(rFlatCritChanceModPerLevel != null) {
            nonNull.put("rFlatCritChanceModPerLevel", rFlatCritChanceModPerLevel);
        }
        if(rFlatCritDamageModPerLevel != null) {
            nonNull.put("rFlatCritDamageModPerLevel", rFlatCritDamageModPerLevel);
        }
        if(rFlatDodgeMod != null) {
            nonNull.put("rFlatDodgeMod", rFlatDodgeMod);
        }
        if(rFlatDodgeModPerLevel != null) {
            nonNull.put("rFlatDodgeModPerLevel", rFlatDodgeModPerLevel);
        }
        if(rFlatEnergyModPerLevel != null) {
            nonNull.put("rFlatEnergyModPerLevel", rFlatEnergyModPerLevel);
        }
        if(rFlatEnergyRegenModPerLevel != null) {
            nonNull.put("rFlatEnergyRegenModPerLevel", rFlatEnergyRegenModPerLevel);
        }
        if(rFlatGoldPer10Mod != null) {
            nonNull.put("rFlatGoldPer10Mod", rFlatGoldPer10Mod);
        }
        if(rFlatHPModPerLevel != null) {
            nonNull.put("rFlatHPModPerLevel", rFlatHPModPerLevel);
        }
        if(rFlatHPRegenModPerLevel != null) {
            nonNull.put("rFlatHPRegenModPerLevel", rFlatHPRegenModPerLevel);
        }
        if(rFlatMPModPerLevel != null) {
            nonNull.put("rFlatMPModPerLevel", rFlatMPModPerLevel);
        }
        if(rFlatMPRegenModPerLevel != null) {
            nonNull.put("rFlatMPRegenModPerLevel", rFlatMPRegenModPerLevel);
        }
        if(rFlatMagicDamageModPerLevel != null) {
            nonNull.put("rFlatMagicDamageModPerLevel", rFlatMagicDamageModPerLevel);
        }
        if(rFlatMagicPenetrationMod != null) {
            nonNull.put("rFlatMagicPenetrationMod", rFlatMagicPenetrationMod);
        }
        if(rFlatMagicPenetrationModPerLevel != null) {
            nonNull.put("rFlatMagicPenetrationModPerLevel", rFlatMagicPenetrationModPerLevel);
        }
        if(rFlatMovementSpeedModPerLevel != null) {
            nonNull.put("rFlatMovementSpeedModPerLevel", rFlatMovementSpeedModPerLevel);
        }
        if(rFlatPhysicalDamageModPerLevel != null) {
            nonNull.put("rFlatPhysicalDamageModPerLevel", rFlatPhysicalDamageModPerLevel);
        }
        if(rFlatSpellBlockModPerLevel != null) {
            nonNull.put("rFlatSpellBlockModPerLevel", rFlatSpellBlockModPerLevel);
        }
        if(rFlatTimeDeadMod != null) {
            nonNull.put("rFlatTimeDeadMod", rFlatTimeDeadMod);
        }
        if(rFlatTimeDeadModPerLevel != null) {
            nonNull.put("rFlatTimeDeadModPerLevel", rFlatTimeDeadModPerLevel);
        }
        if(rPercentArmorPenetrationMod != null) {
            nonNull.put("rPercentArmorPenetrationMod", rPercentArmorPenetrationMod);
        }
        if(rPercentArmorPenetrationModPerLevel != null) {
            nonNull.put("rPercentArmorPenetrationModPerLevel", rPercentArmorPenetrationModPerLevel);
        }
        if(rPercentAttackSpeedModPerLevel != null) {
            nonNull.put("rPercentAttackSpeedModPerLevel", rPercentAttackSpeedModPerLevel);
        }
        if(rPercentCooldownMod != null) {
            nonNull.put("rPercentCooldownMod", rPercentCooldownMod);
        }
        if(rPercentCooldownModPerLevel != null) {
            nonNull.put("rPercentCooldownModPerLevel", rPercentCooldownModPerLevel);
        }
        if(rPercentMagicPenetrationMod != null) {
            nonNull.put("rPercentMagicPenetrationMod", rPercentMagicPenetrationMod);
        }
        if(rPercentMagicPenetrationModPerLevel != null) {
            nonNull.put("rPercentMagicPenetrationModPerLevel", rPercentMagicPenetrationModPerLevel);
        }
        if(rPercentMovementSpeedModPerLevel != null) {
            nonNull.put("rPercentMovementSpeedModPerLevel", rPercentMovementSpeedModPerLevel);
        }
        if(rPercentTimeDeadMod != null) {
            nonNull.put("rPercentTimeDeadMod", rPercentTimeDeadMod);
        }
        if(rPercentTimeDeadModPerLevel != null) {
            nonNull.put("rPercentTimeDeadModPerLevel", rPercentTimeDeadModPerLevel);
        }

        return nonNull;
    }

    @Override
    public String toString() {
        return "BasicDataStats";
    }
}
