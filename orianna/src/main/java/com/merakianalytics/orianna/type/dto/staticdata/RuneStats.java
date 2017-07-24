package com.merakianalytics.orianna.type.dto.staticdata;

import com.merakianalytics.orianna.type.dto.DataObject;

public class RuneStats extends DataObject {
    private static final long serialVersionUID = 6641812278072766150L;
    private double PercentTimeDeadModPerLevel, PercentArmorPenetrationModPerLevel, PercentCritDamageMod, PercentSpellBlockMod, PercentHPRegenMod,
            PercentMovementSpeedMod, FlatSpellBlockMod, FlatEnergyRegenModPerLevel, FlatEnergyPoolMod, FlatMagicPenetrationModPerLevel, PercentLifeStealMod,
            FlatMPPoolMod, PercentCooldownMod, PercentMagicPenetrationMod, FlatArmorPenetrationModPerLevel, FlatMovementSpeedMod, FlatTimeDeadModPerLevel,
            FlatArmorModPerLevel, PercentAttackSpeedMod, FlatDodgeModPerLevel, PercentMagicDamageMod, PercentBlockMod, FlatDodgeMod, FlatEnergyRegenMod,
            FlatHPModPerLevel, PercentAttackSpeedModPerLevel, PercentSpellVampMod, FlatMPRegenMod, PercentHPPoolMod, PercentDodgeMod, FlatAttackSpeedMod,
            FlatArmorMod, FlatMagicDamageModPerLevel, FlatHPRegenMod, PercentPhysicalDamageMod, FlatCritChanceModPerLevel, FlatSpellBlockModPerLevel,
            PercentTimeDeadMod, FlatBlockMod, PercentMPPoolMod, FlatMagicDamageMod, PercentMPRegenMod, PercentMovementSpeedModPerLevel,
            PercentCooldownModPerLevel, FlatMPModPerLevel, FlatEnergyModPerLevel, FlatPhysicalDamageMod, FlatHPRegenModPerLevel, FlatCritDamageMod,
            PercentArmorMod, FlatMagicPenetrationMod, PercentCritChanceMod, FlatPhysicalDamageModPerLevel, PercentArmorPenetrationMod, PercentEXPBonus,
            FlatMPRegenModPerLevel, PercentMagicPenetrationModPerLevel, FlatTimeDeadMod, FlatMovementSpeedModPerLevel, FlatGoldPer10Mod,
            FlatArmorPenetrationMod, FlatCritDamageModPerLevel, FlatHPPoolMod, FlatCritChanceMod, FlatEXPBonus;

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
        final RuneStats other = (RuneStats)obj;
        if(Double.doubleToLongBits(FlatArmorMod) != Double.doubleToLongBits(other.FlatArmorMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatArmorModPerLevel) != Double.doubleToLongBits(other.FlatArmorModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatArmorPenetrationMod) != Double.doubleToLongBits(other.FlatArmorPenetrationMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatArmorPenetrationModPerLevel) != Double.doubleToLongBits(other.FlatArmorPenetrationModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatAttackSpeedMod) != Double.doubleToLongBits(other.FlatAttackSpeedMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatBlockMod) != Double.doubleToLongBits(other.FlatBlockMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatCritChanceMod) != Double.doubleToLongBits(other.FlatCritChanceMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatCritChanceModPerLevel) != Double.doubleToLongBits(other.FlatCritChanceModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatCritDamageMod) != Double.doubleToLongBits(other.FlatCritDamageMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatCritDamageModPerLevel) != Double.doubleToLongBits(other.FlatCritDamageModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatDodgeMod) != Double.doubleToLongBits(other.FlatDodgeMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatDodgeModPerLevel) != Double.doubleToLongBits(other.FlatDodgeModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatEXPBonus) != Double.doubleToLongBits(other.FlatEXPBonus)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatEnergyModPerLevel) != Double.doubleToLongBits(other.FlatEnergyModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatEnergyPoolMod) != Double.doubleToLongBits(other.FlatEnergyPoolMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatEnergyRegenMod) != Double.doubleToLongBits(other.FlatEnergyRegenMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatEnergyRegenModPerLevel) != Double.doubleToLongBits(other.FlatEnergyRegenModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatGoldPer10Mod) != Double.doubleToLongBits(other.FlatGoldPer10Mod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatHPModPerLevel) != Double.doubleToLongBits(other.FlatHPModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatHPPoolMod) != Double.doubleToLongBits(other.FlatHPPoolMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatHPRegenMod) != Double.doubleToLongBits(other.FlatHPRegenMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatHPRegenModPerLevel) != Double.doubleToLongBits(other.FlatHPRegenModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMPModPerLevel) != Double.doubleToLongBits(other.FlatMPModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMPPoolMod) != Double.doubleToLongBits(other.FlatMPPoolMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMPRegenMod) != Double.doubleToLongBits(other.FlatMPRegenMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMPRegenModPerLevel) != Double.doubleToLongBits(other.FlatMPRegenModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMagicDamageMod) != Double.doubleToLongBits(other.FlatMagicDamageMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMagicDamageModPerLevel) != Double.doubleToLongBits(other.FlatMagicDamageModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMagicPenetrationMod) != Double.doubleToLongBits(other.FlatMagicPenetrationMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMagicPenetrationModPerLevel) != Double.doubleToLongBits(other.FlatMagicPenetrationModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMovementSpeedMod) != Double.doubleToLongBits(other.FlatMovementSpeedMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMovementSpeedModPerLevel) != Double.doubleToLongBits(other.FlatMovementSpeedModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatPhysicalDamageMod) != Double.doubleToLongBits(other.FlatPhysicalDamageMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatPhysicalDamageModPerLevel) != Double.doubleToLongBits(other.FlatPhysicalDamageModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatSpellBlockMod) != Double.doubleToLongBits(other.FlatSpellBlockMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatSpellBlockModPerLevel) != Double.doubleToLongBits(other.FlatSpellBlockModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatTimeDeadMod) != Double.doubleToLongBits(other.FlatTimeDeadMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatTimeDeadModPerLevel) != Double.doubleToLongBits(other.FlatTimeDeadModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentArmorMod) != Double.doubleToLongBits(other.PercentArmorMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentArmorPenetrationMod) != Double.doubleToLongBits(other.PercentArmorPenetrationMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentArmorPenetrationModPerLevel) != Double.doubleToLongBits(other.PercentArmorPenetrationModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentAttackSpeedMod) != Double.doubleToLongBits(other.PercentAttackSpeedMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentAttackSpeedModPerLevel) != Double.doubleToLongBits(other.PercentAttackSpeedModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentBlockMod) != Double.doubleToLongBits(other.PercentBlockMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentCooldownMod) != Double.doubleToLongBits(other.PercentCooldownMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentCooldownModPerLevel) != Double.doubleToLongBits(other.PercentCooldownModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentCritChanceMod) != Double.doubleToLongBits(other.PercentCritChanceMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentCritDamageMod) != Double.doubleToLongBits(other.PercentCritDamageMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentDodgeMod) != Double.doubleToLongBits(other.PercentDodgeMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentEXPBonus) != Double.doubleToLongBits(other.PercentEXPBonus)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentHPPoolMod) != Double.doubleToLongBits(other.PercentHPPoolMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentHPRegenMod) != Double.doubleToLongBits(other.PercentHPRegenMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentLifeStealMod) != Double.doubleToLongBits(other.PercentLifeStealMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentMPPoolMod) != Double.doubleToLongBits(other.PercentMPPoolMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentMPRegenMod) != Double.doubleToLongBits(other.PercentMPRegenMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentMagicDamageMod) != Double.doubleToLongBits(other.PercentMagicDamageMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentMagicPenetrationMod) != Double.doubleToLongBits(other.PercentMagicPenetrationMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentMagicPenetrationModPerLevel) != Double.doubleToLongBits(other.PercentMagicPenetrationModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentMovementSpeedMod) != Double.doubleToLongBits(other.PercentMovementSpeedMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentMovementSpeedModPerLevel) != Double.doubleToLongBits(other.PercentMovementSpeedModPerLevel)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentPhysicalDamageMod) != Double.doubleToLongBits(other.PercentPhysicalDamageMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentSpellBlockMod) != Double.doubleToLongBits(other.PercentSpellBlockMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentSpellVampMod) != Double.doubleToLongBits(other.PercentSpellVampMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentTimeDeadMod) != Double.doubleToLongBits(other.PercentTimeDeadMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentTimeDeadModPerLevel) != Double.doubleToLongBits(other.PercentTimeDeadModPerLevel)) {
            return false;
        }
        return true;
    }

    /**
     * @return the flatArmorMod
     */
    public double getFlatArmorMod() {
        return FlatArmorMod;
    }

    /**
     * @return the flatArmorModPerLevel
     */
    public double getFlatArmorModPerLevel() {
        return FlatArmorModPerLevel;
    }

    /**
     * @return the flatArmorPenetrationMod
     */
    public double getFlatArmorPenetrationMod() {
        return FlatArmorPenetrationMod;
    }

    /**
     * @return the flatArmorPenetrationModPerLevel
     */
    public double getFlatArmorPenetrationModPerLevel() {
        return FlatArmorPenetrationModPerLevel;
    }

    /**
     * @return the flatAttackSpeedMod
     */
    public double getFlatAttackSpeedMod() {
        return FlatAttackSpeedMod;
    }

    /**
     * @return the flatBlockMod
     */
    public double getFlatBlockMod() {
        return FlatBlockMod;
    }

    /**
     * @return the flatCritChanceMod
     */
    public double getFlatCritChanceMod() {
        return FlatCritChanceMod;
    }

    /**
     * @return the flatCritChanceModPerLevel
     */
    public double getFlatCritChanceModPerLevel() {
        return FlatCritChanceModPerLevel;
    }

    /**
     * @return the flatCritDamageMod
     */
    public double getFlatCritDamageMod() {
        return FlatCritDamageMod;
    }

    /**
     * @return the flatCritDamageModPerLevel
     */
    public double getFlatCritDamageModPerLevel() {
        return FlatCritDamageModPerLevel;
    }

    /**
     * @return the flatDodgeMod
     */
    public double getFlatDodgeMod() {
        return FlatDodgeMod;
    }

    /**
     * @return the flatDodgeModPerLevel
     */
    public double getFlatDodgeModPerLevel() {
        return FlatDodgeModPerLevel;
    }

    /**
     * @return the flatEnergyModPerLevel
     */
    public double getFlatEnergyModPerLevel() {
        return FlatEnergyModPerLevel;
    }

    /**
     * @return the flatEnergyPoolMod
     */
    public double getFlatEnergyPoolMod() {
        return FlatEnergyPoolMod;
    }

    /**
     * @return the flatEnergyRegenMod
     */
    public double getFlatEnergyRegenMod() {
        return FlatEnergyRegenMod;
    }

    /**
     * @return the flatEnergyRegenModPerLevel
     */
    public double getFlatEnergyRegenModPerLevel() {
        return FlatEnergyRegenModPerLevel;
    }

    /**
     * @return the flatEXPBonus
     */
    public double getFlatEXPBonus() {
        return FlatEXPBonus;
    }

    /**
     * @return the flatGoldPer10Mod
     */
    public double getFlatGoldPer10Mod() {
        return FlatGoldPer10Mod;
    }

    /**
     * @return the flatHPModPerLevel
     */
    public double getFlatHPModPerLevel() {
        return FlatHPModPerLevel;
    }

    /**
     * @return the flatHPPoolMod
     */
    public double getFlatHPPoolMod() {
        return FlatHPPoolMod;
    }

    /**
     * @return the flatHPRegenMod
     */
    public double getFlatHPRegenMod() {
        return FlatHPRegenMod;
    }

    /**
     * @return the flatHPRegenModPerLevel
     */
    public double getFlatHPRegenModPerLevel() {
        return FlatHPRegenModPerLevel;
    }

    /**
     * @return the flatMagicDamageMod
     */
    public double getFlatMagicDamageMod() {
        return FlatMagicDamageMod;
    }

    /**
     * @return the flatMagicDamageModPerLevel
     */
    public double getFlatMagicDamageModPerLevel() {
        return FlatMagicDamageModPerLevel;
    }

    /**
     * @return the flatMagicPenetrationMod
     */
    public double getFlatMagicPenetrationMod() {
        return FlatMagicPenetrationMod;
    }

    /**
     * @return the flatMagicPenetrationModPerLevel
     */
    public double getFlatMagicPenetrationModPerLevel() {
        return FlatMagicPenetrationModPerLevel;
    }

    /**
     * @return the flatMovementSpeedMod
     */
    public double getFlatMovementSpeedMod() {
        return FlatMovementSpeedMod;
    }

    /**
     * @return the flatMovementSpeedModPerLevel
     */
    public double getFlatMovementSpeedModPerLevel() {
        return FlatMovementSpeedModPerLevel;
    }

    /**
     * @return the flatMPModPerLevel
     */
    public double getFlatMPModPerLevel() {
        return FlatMPModPerLevel;
    }

    /**
     * @return the flatMPPoolMod
     */
    public double getFlatMPPoolMod() {
        return FlatMPPoolMod;
    }

    /**
     * @return the flatMPRegenMod
     */
    public double getFlatMPRegenMod() {
        return FlatMPRegenMod;
    }

    /**
     * @return the flatMPRegenModPerLevel
     */
    public double getFlatMPRegenModPerLevel() {
        return FlatMPRegenModPerLevel;
    }

    /**
     * @return the flatPhysicalDamageMod
     */
    public double getFlatPhysicalDamageMod() {
        return FlatPhysicalDamageMod;
    }

    /**
     * @return the flatPhysicalDamageModPerLevel
     */
    public double getFlatPhysicalDamageModPerLevel() {
        return FlatPhysicalDamageModPerLevel;
    }

    /**
     * @return the flatSpellBlockMod
     */
    public double getFlatSpellBlockMod() {
        return FlatSpellBlockMod;
    }

    /**
     * @return the flatSpellBlockModPerLevel
     */
    public double getFlatSpellBlockModPerLevel() {
        return FlatSpellBlockModPerLevel;
    }

    /**
     * @return the flatTimeDeadMod
     */
    public double getFlatTimeDeadMod() {
        return FlatTimeDeadMod;
    }

    /**
     * @return the flatTimeDeadModPerLevel
     */
    public double getFlatTimeDeadModPerLevel() {
        return FlatTimeDeadModPerLevel;
    }

    /**
     * @return the percentArmorMod
     */
    public double getPercentArmorMod() {
        return PercentArmorMod;
    }

    /**
     * @return the percentArmorPenetrationMod
     */
    public double getPercentArmorPenetrationMod() {
        return PercentArmorPenetrationMod;
    }

    /**
     * @return the percentArmorPenetrationModPerLevel
     */
    public double getPercentArmorPenetrationModPerLevel() {
        return PercentArmorPenetrationModPerLevel;
    }

    /**
     * @return the percentAttackSpeedMod
     */
    public double getPercentAttackSpeedMod() {
        return PercentAttackSpeedMod;
    }

    /**
     * @return the percentAttackSpeedModPerLevel
     */
    public double getPercentAttackSpeedModPerLevel() {
        return PercentAttackSpeedModPerLevel;
    }

    /**
     * @return the percentBlockMod
     */
    public double getPercentBlockMod() {
        return PercentBlockMod;
    }

    /**
     * @return the percentCooldownMod
     */
    public double getPercentCooldownMod() {
        return PercentCooldownMod;
    }

    /**
     * @return the percentCooldownModPerLevel
     */
    public double getPercentCooldownModPerLevel() {
        return PercentCooldownModPerLevel;
    }

    /**
     * @return the percentCritChanceMod
     */
    public double getPercentCritChanceMod() {
        return PercentCritChanceMod;
    }

    /**
     * @return the percentCritDamageMod
     */
    public double getPercentCritDamageMod() {
        return PercentCritDamageMod;
    }

    /**
     * @return the percentDodgeMod
     */
    public double getPercentDodgeMod() {
        return PercentDodgeMod;
    }

    /**
     * @return the percentEXPBonus
     */
    public double getPercentEXPBonus() {
        return PercentEXPBonus;
    }

    /**
     * @return the percentHPPoolMod
     */
    public double getPercentHPPoolMod() {
        return PercentHPPoolMod;
    }

    /**
     * @return the percentHPRegenMod
     */
    public double getPercentHPRegenMod() {
        return PercentHPRegenMod;
    }

    /**
     * @return the percentLifeStealMod
     */
    public double getPercentLifeStealMod() {
        return PercentLifeStealMod;
    }

    /**
     * @return the percentMagicDamageMod
     */
    public double getPercentMagicDamageMod() {
        return PercentMagicDamageMod;
    }

    /**
     * @return the percentMagicPenetrationMod
     */
    public double getPercentMagicPenetrationMod() {
        return PercentMagicPenetrationMod;
    }

    /**
     * @return the percentMagicPenetrationModPerLevel
     */
    public double getPercentMagicPenetrationModPerLevel() {
        return PercentMagicPenetrationModPerLevel;
    }

    /**
     * @return the percentMovementSpeedMod
     */
    public double getPercentMovementSpeedMod() {
        return PercentMovementSpeedMod;
    }

    /**
     * @return the percentMovementSpeedModPerLevel
     */
    public double getPercentMovementSpeedModPerLevel() {
        return PercentMovementSpeedModPerLevel;
    }

    /**
     * @return the percentMPPoolMod
     */
    public double getPercentMPPoolMod() {
        return PercentMPPoolMod;
    }

    /**
     * @return the percentMPRegenMod
     */
    public double getPercentMPRegenMod() {
        return PercentMPRegenMod;
    }

    /**
     * @return the percentPhysicalDamageMod
     */
    public double getPercentPhysicalDamageMod() {
        return PercentPhysicalDamageMod;
    }

    /**
     * @return the percentSpellBlockMod
     */
    public double getPercentSpellBlockMod() {
        return PercentSpellBlockMod;
    }

    /**
     * @return the percentSpellVampMod
     */
    public double getPercentSpellVampMod() {
        return PercentSpellVampMod;
    }

    /**
     * @return the percentTimeDeadMod
     */
    public double getPercentTimeDeadMod() {
        return PercentTimeDeadMod;
    }

    /**
     * @return the percentTimeDeadModPerLevel
     */
    public double getPercentTimeDeadModPerLevel() {
        return PercentTimeDeadModPerLevel;
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
        temp = Double.doubleToLongBits(FlatArmorMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatArmorModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatArmorPenetrationMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatArmorPenetrationModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatAttackSpeedMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatBlockMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatCritChanceMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatCritChanceModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatCritDamageMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatCritDamageModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatDodgeMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatDodgeModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatEXPBonus);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatEnergyModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatEnergyPoolMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatEnergyRegenMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatEnergyRegenModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatGoldPer10Mod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatHPModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatHPPoolMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatHPRegenMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatHPRegenModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMPModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMPPoolMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMPRegenMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMPRegenModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMagicDamageMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMagicDamageModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMagicPenetrationMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMagicPenetrationModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMovementSpeedMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMovementSpeedModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatPhysicalDamageMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatPhysicalDamageModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatSpellBlockMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatSpellBlockModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatTimeDeadMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatTimeDeadModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentArmorMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentArmorPenetrationMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentArmorPenetrationModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentAttackSpeedMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentAttackSpeedModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentBlockMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentCooldownMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentCooldownModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentCritChanceMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentCritDamageMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentDodgeMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentEXPBonus);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentHPPoolMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentHPRegenMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentLifeStealMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentMPPoolMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentMPRegenMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentMagicDamageMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentMagicPenetrationMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentMagicPenetrationModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentMovementSpeedMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentMovementSpeedModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentPhysicalDamageMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentSpellBlockMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentSpellVampMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentTimeDeadMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentTimeDeadModPerLevel);
        result = prime * result + (int)(temp ^ temp >>> 32);
        return result;
    }

    /**
     * @param flatArmorMod
     *        the flatArmorMod to set
     */
    public void setFlatArmorMod(final double flatArmorMod) {
        FlatArmorMod = flatArmorMod;
    }

    /**
     * @param flatArmorModPerLevel
     *        the flatArmorModPerLevel to set
     */
    public void setFlatArmorModPerLevel(final double flatArmorModPerLevel) {
        FlatArmorModPerLevel = flatArmorModPerLevel;
    }

    /**
     * @param flatArmorPenetrationMod
     *        the flatArmorPenetrationMod to set
     */
    public void setFlatArmorPenetrationMod(final double flatArmorPenetrationMod) {
        FlatArmorPenetrationMod = flatArmorPenetrationMod;
    }

    /**
     * @param flatArmorPenetrationModPerLevel
     *        the flatArmorPenetrationModPerLevel to set
     */
    public void setFlatArmorPenetrationModPerLevel(final double flatArmorPenetrationModPerLevel) {
        FlatArmorPenetrationModPerLevel = flatArmorPenetrationModPerLevel;
    }

    /**
     * @param flatAttackSpeedMod
     *        the flatAttackSpeedMod to set
     */
    public void setFlatAttackSpeedMod(final double flatAttackSpeedMod) {
        FlatAttackSpeedMod = flatAttackSpeedMod;
    }

    /**
     * @param flatBlockMod
     *        the flatBlockMod to set
     */
    public void setFlatBlockMod(final double flatBlockMod) {
        FlatBlockMod = flatBlockMod;
    }

    /**
     * @param flatCritChanceMod
     *        the flatCritChanceMod to set
     */
    public void setFlatCritChanceMod(final double flatCritChanceMod) {
        FlatCritChanceMod = flatCritChanceMod;
    }

    /**
     * @param flatCritChanceModPerLevel
     *        the flatCritChanceModPerLevel to set
     */
    public void setFlatCritChanceModPerLevel(final double flatCritChanceModPerLevel) {
        FlatCritChanceModPerLevel = flatCritChanceModPerLevel;
    }

    /**
     * @param flatCritDamageMod
     *        the flatCritDamageMod to set
     */
    public void setFlatCritDamageMod(final double flatCritDamageMod) {
        FlatCritDamageMod = flatCritDamageMod;
    }

    /**
     * @param flatCritDamageModPerLevel
     *        the flatCritDamageModPerLevel to set
     */
    public void setFlatCritDamageModPerLevel(final double flatCritDamageModPerLevel) {
        FlatCritDamageModPerLevel = flatCritDamageModPerLevel;
    }

    /**
     * @param flatDodgeMod
     *        the flatDodgeMod to set
     */
    public void setFlatDodgeMod(final double flatDodgeMod) {
        FlatDodgeMod = flatDodgeMod;
    }

    /**
     * @param flatDodgeModPerLevel
     *        the flatDodgeModPerLevel to set
     */
    public void setFlatDodgeModPerLevel(final double flatDodgeModPerLevel) {
        FlatDodgeModPerLevel = flatDodgeModPerLevel;
    }

    /**
     * @param flatEnergyModPerLevel
     *        the flatEnergyModPerLevel to set
     */
    public void setFlatEnergyModPerLevel(final double flatEnergyModPerLevel) {
        FlatEnergyModPerLevel = flatEnergyModPerLevel;
    }

    /**
     * @param flatEnergyPoolMod
     *        the flatEnergyPoolMod to set
     */
    public void setFlatEnergyPoolMod(final double flatEnergyPoolMod) {
        FlatEnergyPoolMod = flatEnergyPoolMod;
    }

    /**
     * @param flatEnergyRegenMod
     *        the flatEnergyRegenMod to set
     */
    public void setFlatEnergyRegenMod(final double flatEnergyRegenMod) {
        FlatEnergyRegenMod = flatEnergyRegenMod;
    }

    /**
     * @param flatEnergyRegenModPerLevel
     *        the flatEnergyRegenModPerLevel to set
     */
    public void setFlatEnergyRegenModPerLevel(final double flatEnergyRegenModPerLevel) {
        FlatEnergyRegenModPerLevel = flatEnergyRegenModPerLevel;
    }

    /**
     * @param flatEXPBonus
     *        the flatEXPBonus to set
     */
    public void setFlatEXPBonus(final double flatEXPBonus) {
        FlatEXPBonus = flatEXPBonus;
    }

    /**
     * @param flatGoldPer10Mod
     *        the flatGoldPer10Mod to set
     */
    public void setFlatGoldPer10Mod(final double flatGoldPer10Mod) {
        FlatGoldPer10Mod = flatGoldPer10Mod;
    }

    /**
     * @param flatHPModPerLevel
     *        the flatHPModPerLevel to set
     */
    public void setFlatHPModPerLevel(final double flatHPModPerLevel) {
        FlatHPModPerLevel = flatHPModPerLevel;
    }

    /**
     * @param flatHPPoolMod
     *        the flatHPPoolMod to set
     */
    public void setFlatHPPoolMod(final double flatHPPoolMod) {
        FlatHPPoolMod = flatHPPoolMod;
    }

    /**
     * @param flatHPRegenMod
     *        the flatHPRegenMod to set
     */
    public void setFlatHPRegenMod(final double flatHPRegenMod) {
        FlatHPRegenMod = flatHPRegenMod;
    }

    /**
     * @param flatHPRegenModPerLevel
     *        the flatHPRegenModPerLevel to set
     */
    public void setFlatHPRegenModPerLevel(final double flatHPRegenModPerLevel) {
        FlatHPRegenModPerLevel = flatHPRegenModPerLevel;
    }

    /**
     * @param flatMagicDamageMod
     *        the flatMagicDamageMod to set
     */
    public void setFlatMagicDamageMod(final double flatMagicDamageMod) {
        FlatMagicDamageMod = flatMagicDamageMod;
    }

    /**
     * @param flatMagicDamageModPerLevel
     *        the flatMagicDamageModPerLevel to set
     */
    public void setFlatMagicDamageModPerLevel(final double flatMagicDamageModPerLevel) {
        FlatMagicDamageModPerLevel = flatMagicDamageModPerLevel;
    }

    /**
     * @param flatMagicPenetrationMod
     *        the flatMagicPenetrationMod to set
     */
    public void setFlatMagicPenetrationMod(final double flatMagicPenetrationMod) {
        FlatMagicPenetrationMod = flatMagicPenetrationMod;
    }

    /**
     * @param flatMagicPenetrationModPerLevel
     *        the flatMagicPenetrationModPerLevel to set
     */
    public void setFlatMagicPenetrationModPerLevel(final double flatMagicPenetrationModPerLevel) {
        FlatMagicPenetrationModPerLevel = flatMagicPenetrationModPerLevel;
    }

    /**
     * @param flatMovementSpeedMod
     *        the flatMovementSpeedMod to set
     */
    public void setFlatMovementSpeedMod(final double flatMovementSpeedMod) {
        FlatMovementSpeedMod = flatMovementSpeedMod;
    }

    /**
     * @param flatMovementSpeedModPerLevel
     *        the flatMovementSpeedModPerLevel to set
     */
    public void setFlatMovementSpeedModPerLevel(final double flatMovementSpeedModPerLevel) {
        FlatMovementSpeedModPerLevel = flatMovementSpeedModPerLevel;
    }

    /**
     * @param flatMPModPerLevel
     *        the flatMPModPerLevel to set
     */
    public void setFlatMPModPerLevel(final double flatMPModPerLevel) {
        FlatMPModPerLevel = flatMPModPerLevel;
    }

    /**
     * @param flatMPPoolMod
     *        the flatMPPoolMod to set
     */
    public void setFlatMPPoolMod(final double flatMPPoolMod) {
        FlatMPPoolMod = flatMPPoolMod;
    }

    /**
     * @param flatMPRegenMod
     *        the flatMPRegenMod to set
     */
    public void setFlatMPRegenMod(final double flatMPRegenMod) {
        FlatMPRegenMod = flatMPRegenMod;
    }

    /**
     * @param flatMPRegenModPerLevel
     *        the flatMPRegenModPerLevel to set
     */
    public void setFlatMPRegenModPerLevel(final double flatMPRegenModPerLevel) {
        FlatMPRegenModPerLevel = flatMPRegenModPerLevel;
    }

    /**
     * @param flatPhysicalDamageMod
     *        the flatPhysicalDamageMod to set
     */
    public void setFlatPhysicalDamageMod(final double flatPhysicalDamageMod) {
        FlatPhysicalDamageMod = flatPhysicalDamageMod;
    }

    /**
     * @param flatPhysicalDamageModPerLevel
     *        the flatPhysicalDamageModPerLevel to set
     */
    public void setFlatPhysicalDamageModPerLevel(final double flatPhysicalDamageModPerLevel) {
        FlatPhysicalDamageModPerLevel = flatPhysicalDamageModPerLevel;
    }

    /**
     * @param flatSpellBlockMod
     *        the flatSpellBlockMod to set
     */
    public void setFlatSpellBlockMod(final double flatSpellBlockMod) {
        FlatSpellBlockMod = flatSpellBlockMod;
    }

    /**
     * @param flatSpellBlockModPerLevel
     *        the flatSpellBlockModPerLevel to set
     */
    public void setFlatSpellBlockModPerLevel(final double flatSpellBlockModPerLevel) {
        FlatSpellBlockModPerLevel = flatSpellBlockModPerLevel;
    }

    /**
     * @param flatTimeDeadMod
     *        the flatTimeDeadMod to set
     */
    public void setFlatTimeDeadMod(final double flatTimeDeadMod) {
        FlatTimeDeadMod = flatTimeDeadMod;
    }

    /**
     * @param flatTimeDeadModPerLevel
     *        the flatTimeDeadModPerLevel to set
     */
    public void setFlatTimeDeadModPerLevel(final double flatTimeDeadModPerLevel) {
        FlatTimeDeadModPerLevel = flatTimeDeadModPerLevel;
    }

    /**
     * @param percentArmorMod
     *        the percentArmorMod to set
     */
    public void setPercentArmorMod(final double percentArmorMod) {
        PercentArmorMod = percentArmorMod;
    }

    /**
     * @param percentArmorPenetrationMod
     *        the percentArmorPenetrationMod to set
     */
    public void setPercentArmorPenetrationMod(final double percentArmorPenetrationMod) {
        PercentArmorPenetrationMod = percentArmorPenetrationMod;
    }

    /**
     * @param percentArmorPenetrationModPerLevel
     *        the percentArmorPenetrationModPerLevel to set
     */
    public void setPercentArmorPenetrationModPerLevel(final double percentArmorPenetrationModPerLevel) {
        PercentArmorPenetrationModPerLevel = percentArmorPenetrationModPerLevel;
    }

    /**
     * @param percentAttackSpeedMod
     *        the percentAttackSpeedMod to set
     */
    public void setPercentAttackSpeedMod(final double percentAttackSpeedMod) {
        PercentAttackSpeedMod = percentAttackSpeedMod;
    }

    /**
     * @param percentAttackSpeedModPerLevel
     *        the percentAttackSpeedModPerLevel to set
     */
    public void setPercentAttackSpeedModPerLevel(final double percentAttackSpeedModPerLevel) {
        PercentAttackSpeedModPerLevel = percentAttackSpeedModPerLevel;
    }

    /**
     * @param percentBlockMod
     *        the percentBlockMod to set
     */
    public void setPercentBlockMod(final double percentBlockMod) {
        PercentBlockMod = percentBlockMod;
    }

    /**
     * @param percentCooldownMod
     *        the percentCooldownMod to set
     */
    public void setPercentCooldownMod(final double percentCooldownMod) {
        PercentCooldownMod = percentCooldownMod;
    }

    /**
     * @param percentCooldownModPerLevel
     *        the percentCooldownModPerLevel to set
     */
    public void setPercentCooldownModPerLevel(final double percentCooldownModPerLevel) {
        PercentCooldownModPerLevel = percentCooldownModPerLevel;
    }

    /**
     * @param percentCritChanceMod
     *        the percentCritChanceMod to set
     */
    public void setPercentCritChanceMod(final double percentCritChanceMod) {
        PercentCritChanceMod = percentCritChanceMod;
    }

    /**
     * @param percentCritDamageMod
     *        the percentCritDamageMod to set
     */
    public void setPercentCritDamageMod(final double percentCritDamageMod) {
        PercentCritDamageMod = percentCritDamageMod;
    }

    /**
     * @param percentDodgeMod
     *        the percentDodgeMod to set
     */
    public void setPercentDodgeMod(final double percentDodgeMod) {
        PercentDodgeMod = percentDodgeMod;
    }

    /**
     * @param percentEXPBonus
     *        the percentEXPBonus to set
     */
    public void setPercentEXPBonus(final double percentEXPBonus) {
        PercentEXPBonus = percentEXPBonus;
    }

    /**
     * @param percentHPPoolMod
     *        the percentHPPoolMod to set
     */
    public void setPercentHPPoolMod(final double percentHPPoolMod) {
        PercentHPPoolMod = percentHPPoolMod;
    }

    /**
     * @param percentHPRegenMod
     *        the percentHPRegenMod to set
     */
    public void setPercentHPRegenMod(final double percentHPRegenMod) {
        PercentHPRegenMod = percentHPRegenMod;
    }

    /**
     * @param percentLifeStealMod
     *        the percentLifeStealMod to set
     */
    public void setPercentLifeStealMod(final double percentLifeStealMod) {
        PercentLifeStealMod = percentLifeStealMod;
    }

    /**
     * @param percentMagicDamageMod
     *        the percentMagicDamageMod to set
     */
    public void setPercentMagicDamageMod(final double percentMagicDamageMod) {
        PercentMagicDamageMod = percentMagicDamageMod;
    }

    /**
     * @param percentMagicPenetrationMod
     *        the percentMagicPenetrationMod to set
     */
    public void setPercentMagicPenetrationMod(final double percentMagicPenetrationMod) {
        PercentMagicPenetrationMod = percentMagicPenetrationMod;
    }

    /**
     * @param percentMagicPenetrationModPerLevel
     *        the percentMagicPenetrationModPerLevel to set
     */
    public void setPercentMagicPenetrationModPerLevel(final double percentMagicPenetrationModPerLevel) {
        PercentMagicPenetrationModPerLevel = percentMagicPenetrationModPerLevel;
    }

    /**
     * @param percentMovementSpeedMod
     *        the percentMovementSpeedMod to set
     */
    public void setPercentMovementSpeedMod(final double percentMovementSpeedMod) {
        PercentMovementSpeedMod = percentMovementSpeedMod;
    }

    /**
     * @param percentMovementSpeedModPerLevel
     *        the percentMovementSpeedModPerLevel to set
     */
    public void setPercentMovementSpeedModPerLevel(final double percentMovementSpeedModPerLevel) {
        PercentMovementSpeedModPerLevel = percentMovementSpeedModPerLevel;
    }

    /**
     * @param percentMPPoolMod
     *        the percentMPPoolMod to set
     */
    public void setPercentMPPoolMod(final double percentMPPoolMod) {
        PercentMPPoolMod = percentMPPoolMod;
    }

    /**
     * @param percentMPRegenMod
     *        the percentMPRegenMod to set
     */
    public void setPercentMPRegenMod(final double percentMPRegenMod) {
        PercentMPRegenMod = percentMPRegenMod;
    }

    /**
     * @param percentPhysicalDamageMod
     *        the percentPhysicalDamageMod to set
     */
    public void setPercentPhysicalDamageMod(final double percentPhysicalDamageMod) {
        PercentPhysicalDamageMod = percentPhysicalDamageMod;
    }

    /**
     * @param percentSpellBlockMod
     *        the percentSpellBlockMod to set
     */
    public void setPercentSpellBlockMod(final double percentSpellBlockMod) {
        PercentSpellBlockMod = percentSpellBlockMod;
    }

    /**
     * @param percentSpellVampMod
     *        the percentSpellVampMod to set
     */
    public void setPercentSpellVampMod(final double percentSpellVampMod) {
        PercentSpellVampMod = percentSpellVampMod;
    }

    /**
     * @param percentTimeDeadMod
     *        the percentTimeDeadMod to set
     */
    public void setPercentTimeDeadMod(final double percentTimeDeadMod) {
        PercentTimeDeadMod = percentTimeDeadMod;
    }

    /**
     * @param percentTimeDeadModPerLevel
     *        the percentTimeDeadModPerLevel to set
     */
    public void setPercentTimeDeadModPerLevel(final double percentTimeDeadModPerLevel) {
        PercentTimeDeadModPerLevel = percentTimeDeadModPerLevel;
    }
}
