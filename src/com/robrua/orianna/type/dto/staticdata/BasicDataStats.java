package com.robrua.orianna.type.dto.staticdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "basicdatastats")
public class BasicDataStats extends OriannaDto {
    private static final long serialVersionUID = 3565726158610875915L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private Double FlatArmorMod, FlatAttackSpeedMod, FlatBlockMod, FlatCritChanceMod, FlatCritDamageMod, FlatEXPBonus, FlatEnergyPoolMod, FlatEnergyRegenMod,
            FlatHPPoolMod, FlatHPRegenMod, FlatMPPoolMod, FlatMPRegenMod, FlatMagicDamageMod, FlatMovementSpeedMod, FlatPhysicalDamageMod, FlatSpellBlockMod,
            PercentArmorMod, PercentAttackSpeedMod, PercentBlockMod, PercentCritChanceMod, PercentCritDamageMod, PercentDodgeMod, PercentEXPBonus,
            PercentHPPoolMod, PercentHPRegenMod, PercentLifeStealMod, PercentMPPoolMod, PercentMPRegenMod, PercentMagicDamageMod, PercentMovementSpeedMod,
            PercentPhysicalDamageMod, PercentSpellBlockMod, PercentSpellVampMod, rFlatArmorModPerLevel, rFlatArmorPenetrationMod,
            rFlatArmorPenetrationModPerLevel, rFlatCritChanceModPerLevel, rFlatCritDamageModPerLevel, rFlatDodgeMod, rFlatDodgeModPerLevel,
            rFlatEnergyModPerLevel, rFlatEnergyRegenModPerLevel, rFlatGoldPer10Mod, rFlatHPModPerLevel, rFlatHPRegenModPerLevel, rFlatMPModPerLevel,
            rFlatMPRegenModPerLevel, rFlatMagicDamageModPerLevel, rFlatMagicPenetrationMod, rFlatMagicPenetrationModPerLevel, rFlatMovementSpeedModPerLevel,
            rFlatPhysicalDamageModPerLevel, rFlatSpellBlockModPerLevel, rFlatTimeDeadMod, rFlatTimeDeadModPerLevel, rPercentArmorPenetrationMod,
            rPercentArmorPenetrationModPerLevel, rPercentAttackSpeedModPerLevel, rPercentCooldownMod, rPercentCooldownModPerLevel, rPercentMagicPenetrationMod,
            rPercentMagicPenetrationModPerLevel, rPercentMovementSpeedModPerLevel, rPercentTimeDeadMod, rPercentTimeDeadModPerLevel;

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
        if(!(obj instanceof BasicDataStats)) {
            return false;
        }
        final BasicDataStats other = (BasicDataStats)obj;
        if(FlatArmorMod == null) {
            if(other.FlatArmorMod != null) {
                return false;
            }
        }
        else if(!FlatArmorMod.equals(other.FlatArmorMod)) {
            return false;
        }
        if(FlatAttackSpeedMod == null) {
            if(other.FlatAttackSpeedMod != null) {
                return false;
            }
        }
        else if(!FlatAttackSpeedMod.equals(other.FlatAttackSpeedMod)) {
            return false;
        }
        if(FlatBlockMod == null) {
            if(other.FlatBlockMod != null) {
                return false;
            }
        }
        else if(!FlatBlockMod.equals(other.FlatBlockMod)) {
            return false;
        }
        if(FlatCritChanceMod == null) {
            if(other.FlatCritChanceMod != null) {
                return false;
            }
        }
        else if(!FlatCritChanceMod.equals(other.FlatCritChanceMod)) {
            return false;
        }
        if(FlatCritDamageMod == null) {
            if(other.FlatCritDamageMod != null) {
                return false;
            }
        }
        else if(!FlatCritDamageMod.equals(other.FlatCritDamageMod)) {
            return false;
        }
        if(FlatEXPBonus == null) {
            if(other.FlatEXPBonus != null) {
                return false;
            }
        }
        else if(!FlatEXPBonus.equals(other.FlatEXPBonus)) {
            return false;
        }
        if(FlatEnergyPoolMod == null) {
            if(other.FlatEnergyPoolMod != null) {
                return false;
            }
        }
        else if(!FlatEnergyPoolMod.equals(other.FlatEnergyPoolMod)) {
            return false;
        }
        if(FlatEnergyRegenMod == null) {
            if(other.FlatEnergyRegenMod != null) {
                return false;
            }
        }
        else if(!FlatEnergyRegenMod.equals(other.FlatEnergyRegenMod)) {
            return false;
        }
        if(FlatHPPoolMod == null) {
            if(other.FlatHPPoolMod != null) {
                return false;
            }
        }
        else if(!FlatHPPoolMod.equals(other.FlatHPPoolMod)) {
            return false;
        }
        if(FlatHPRegenMod == null) {
            if(other.FlatHPRegenMod != null) {
                return false;
            }
        }
        else if(!FlatHPRegenMod.equals(other.FlatHPRegenMod)) {
            return false;
        }
        if(FlatMPPoolMod == null) {
            if(other.FlatMPPoolMod != null) {
                return false;
            }
        }
        else if(!FlatMPPoolMod.equals(other.FlatMPPoolMod)) {
            return false;
        }
        if(FlatMPRegenMod == null) {
            if(other.FlatMPRegenMod != null) {
                return false;
            }
        }
        else if(!FlatMPRegenMod.equals(other.FlatMPRegenMod)) {
            return false;
        }
        if(FlatMagicDamageMod == null) {
            if(other.FlatMagicDamageMod != null) {
                return false;
            }
        }
        else if(!FlatMagicDamageMod.equals(other.FlatMagicDamageMod)) {
            return false;
        }
        if(FlatMovementSpeedMod == null) {
            if(other.FlatMovementSpeedMod != null) {
                return false;
            }
        }
        else if(!FlatMovementSpeedMod.equals(other.FlatMovementSpeedMod)) {
            return false;
        }
        if(FlatPhysicalDamageMod == null) {
            if(other.FlatPhysicalDamageMod != null) {
                return false;
            }
        }
        else if(!FlatPhysicalDamageMod.equals(other.FlatPhysicalDamageMod)) {
            return false;
        }
        if(FlatSpellBlockMod == null) {
            if(other.FlatSpellBlockMod != null) {
                return false;
            }
        }
        else if(!FlatSpellBlockMod.equals(other.FlatSpellBlockMod)) {
            return false;
        }
        if(PercentArmorMod == null) {
            if(other.PercentArmorMod != null) {
                return false;
            }
        }
        else if(!PercentArmorMod.equals(other.PercentArmorMod)) {
            return false;
        }
        if(PercentAttackSpeedMod == null) {
            if(other.PercentAttackSpeedMod != null) {
                return false;
            }
        }
        else if(!PercentAttackSpeedMod.equals(other.PercentAttackSpeedMod)) {
            return false;
        }
        if(PercentBlockMod == null) {
            if(other.PercentBlockMod != null) {
                return false;
            }
        }
        else if(!PercentBlockMod.equals(other.PercentBlockMod)) {
            return false;
        }
        if(PercentCritChanceMod == null) {
            if(other.PercentCritChanceMod != null) {
                return false;
            }
        }
        else if(!PercentCritChanceMod.equals(other.PercentCritChanceMod)) {
            return false;
        }
        if(PercentCritDamageMod == null) {
            if(other.PercentCritDamageMod != null) {
                return false;
            }
        }
        else if(!PercentCritDamageMod.equals(other.PercentCritDamageMod)) {
            return false;
        }
        if(PercentDodgeMod == null) {
            if(other.PercentDodgeMod != null) {
                return false;
            }
        }
        else if(!PercentDodgeMod.equals(other.PercentDodgeMod)) {
            return false;
        }
        if(PercentEXPBonus == null) {
            if(other.PercentEXPBonus != null) {
                return false;
            }
        }
        else if(!PercentEXPBonus.equals(other.PercentEXPBonus)) {
            return false;
        }
        if(PercentHPPoolMod == null) {
            if(other.PercentHPPoolMod != null) {
                return false;
            }
        }
        else if(!PercentHPPoolMod.equals(other.PercentHPPoolMod)) {
            return false;
        }
        if(PercentHPRegenMod == null) {
            if(other.PercentHPRegenMod != null) {
                return false;
            }
        }
        else if(!PercentHPRegenMod.equals(other.PercentHPRegenMod)) {
            return false;
        }
        if(PercentLifeStealMod == null) {
            if(other.PercentLifeStealMod != null) {
                return false;
            }
        }
        else if(!PercentLifeStealMod.equals(other.PercentLifeStealMod)) {
            return false;
        }
        if(PercentMPPoolMod == null) {
            if(other.PercentMPPoolMod != null) {
                return false;
            }
        }
        else if(!PercentMPPoolMod.equals(other.PercentMPPoolMod)) {
            return false;
        }
        if(PercentMPRegenMod == null) {
            if(other.PercentMPRegenMod != null) {
                return false;
            }
        }
        else if(!PercentMPRegenMod.equals(other.PercentMPRegenMod)) {
            return false;
        }
        if(PercentMagicDamageMod == null) {
            if(other.PercentMagicDamageMod != null) {
                return false;
            }
        }
        else if(!PercentMagicDamageMod.equals(other.PercentMagicDamageMod)) {
            return false;
        }
        if(PercentMovementSpeedMod == null) {
            if(other.PercentMovementSpeedMod != null) {
                return false;
            }
        }
        else if(!PercentMovementSpeedMod.equals(other.PercentMovementSpeedMod)) {
            return false;
        }
        if(PercentPhysicalDamageMod == null) {
            if(other.PercentPhysicalDamageMod != null) {
                return false;
            }
        }
        else if(!PercentPhysicalDamageMod.equals(other.PercentPhysicalDamageMod)) {
            return false;
        }
        if(PercentSpellBlockMod == null) {
            if(other.PercentSpellBlockMod != null) {
                return false;
            }
        }
        else if(!PercentSpellBlockMod.equals(other.PercentSpellBlockMod)) {
            return false;
        }
        if(PercentSpellVampMod == null) {
            if(other.PercentSpellVampMod != null) {
                return false;
            }
        }
        else if(!PercentSpellVampMod.equals(other.PercentSpellVampMod)) {
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
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the flatArmorMod
     */
    public Double getFlatArmorMod() {
        return FlatArmorMod;
    }

    /**
     * @return the flatAttackSpeedMod
     */
    public Double getFlatAttackSpeedMod() {
        return FlatAttackSpeedMod;
    }

    /**
     * @return the flatBlockMod
     */
    public Double getFlatBlockMod() {
        return FlatBlockMod;
    }

    /**
     * @return the flatCritChanceMod
     */
    public Double getFlatCritChanceMod() {
        return FlatCritChanceMod;
    }

    /**
     * @return the flatCritDamageMod
     */
    public Double getFlatCritDamageMod() {
        return FlatCritDamageMod;
    }

    /**
     * @return the flatEnergyPoolMod
     */
    public Double getFlatEnergyPoolMod() {
        return FlatEnergyPoolMod;
    }

    /**
     * @return the flatEnergyRegenMod
     */
    public Double getFlatEnergyRegenMod() {
        return FlatEnergyRegenMod;
    }

    /**
     * @return the flatEXPBonus
     */
    public Double getFlatEXPBonus() {
        return FlatEXPBonus;
    }

    /**
     * @return the flatHPPoolMod
     */
    public Double getFlatHPPoolMod() {
        return FlatHPPoolMod;
    }

    /**
     * @return the flatHPRegenMod
     */
    public Double getFlatHPRegenMod() {
        return FlatHPRegenMod;
    }

    /**
     * @return the flatMagicDamageMod
     */
    public Double getFlatMagicDamageMod() {
        return FlatMagicDamageMod;
    }

    /**
     * @return the flatMovementSpeedMod
     */
    public Double getFlatMovementSpeedMod() {
        return FlatMovementSpeedMod;
    }

    /**
     * @return the flatMPPoolMod
     */
    public Double getFlatMPPoolMod() {
        return FlatMPPoolMod;
    }

    /**
     * @return the flatMPRegenMod
     */
    public Double getFlatMPRegenMod() {
        return FlatMPRegenMod;
    }

    /**
     * @return the flatPhysicalDamageMod
     */
    public Double getFlatPhysicalDamageMod() {
        return FlatPhysicalDamageMod;
    }

    /**
     * @return the flatSpellBlockMod
     */
    public Double getFlatSpellBlockMod() {
        return FlatSpellBlockMod;
    }

    /**
     * @return the percentArmorMod
     */
    public Double getPercentArmorMod() {
        return PercentArmorMod;
    }

    /**
     * @return the percentAttackSpeedMod
     */
    public Double getPercentAttackSpeedMod() {
        return PercentAttackSpeedMod;
    }

    /**
     * @return the percentBlockMod
     */
    public Double getPercentBlockMod() {
        return PercentBlockMod;
    }

    /**
     * @return the percentCritChanceMod
     */
    public Double getPercentCritChanceMod() {
        return PercentCritChanceMod;
    }

    /**
     * @return the percentCritDamageMod
     */
    public Double getPercentCritDamageMod() {
        return PercentCritDamageMod;
    }

    /**
     * @return the percentDodgeMod
     */
    public Double getPercentDodgeMod() {
        return PercentDodgeMod;
    }

    /**
     * @return the percentEXPBonus
     */
    public Double getPercentEXPBonus() {
        return PercentEXPBonus;
    }

    /**
     * @return the percentHPPoolMod
     */
    public Double getPercentHPPoolMod() {
        return PercentHPPoolMod;
    }

    /**
     * @return the percentHPRegenMod
     */
    public Double getPercentHPRegenMod() {
        return PercentHPRegenMod;
    }

    /**
     * @return the percentLifeStealMod
     */
    public Double getPercentLifeStealMod() {
        return PercentLifeStealMod;
    }

    /**
     * @return the percentMagicDamageMod
     */
    public Double getPercentMagicDamageMod() {
        return PercentMagicDamageMod;
    }

    /**
     * @return the percentMovementSpeedMod
     */
    public Double getPercentMovementSpeedMod() {
        return PercentMovementSpeedMod;
    }

    /**
     * @return the percentMPPoolMod
     */
    public Double getPercentMPPoolMod() {
        return PercentMPPoolMod;
    }

    /**
     * @return the percentMPRegenMod
     */
    public Double getPercentMPRegenMod() {
        return PercentMPRegenMod;
    }

    /**
     * @return the percentPhysicalDamageMod
     */
    public Double getPercentPhysicalDamageMod() {
        return PercentPhysicalDamageMod;
    }

    /**
     * @return the percentSpellBlockMod
     */
    public Double getPercentSpellBlockMod() {
        return PercentSpellBlockMod;
    }

    /**
     * @return the percentSpellVampMod
     */
    public Double getPercentSpellVampMod() {
        return PercentSpellVampMod;
    }

    /**
     * @return the rFlatArmorModPerLevel
     */
    public Double getrFlatArmorModPerLevel() {
        return rFlatArmorModPerLevel;
    }

    /**
     * @return the rFlatArmorPenetrationMod
     */
    public Double getrFlatArmorPenetrationMod() {
        return rFlatArmorPenetrationMod;
    }

    /**
     * @return the rFlatArmorPenetrationModPerLevel
     */
    public Double getrFlatArmorPenetrationModPerLevel() {
        return rFlatArmorPenetrationModPerLevel;
    }

    /**
     * @return the rFlatCritChanceModPerLevel
     */
    public Double getrFlatCritChanceModPerLevel() {
        return rFlatCritChanceModPerLevel;
    }

    /**
     * @return the rFlatCritDamageModPerLevel
     */
    public Double getrFlatCritDamageModPerLevel() {
        return rFlatCritDamageModPerLevel;
    }

    /**
     * @return the rFlatDodgeMod
     */
    public Double getrFlatDodgeMod() {
        return rFlatDodgeMod;
    }

    /**
     * @return the rFlatDodgeModPerLevel
     */
    public Double getrFlatDodgeModPerLevel() {
        return rFlatDodgeModPerLevel;
    }

    /**
     * @return the rFlatEnergyModPerLevel
     */
    public Double getrFlatEnergyModPerLevel() {
        return rFlatEnergyModPerLevel;
    }

    /**
     * @return the rFlatEnergyRegenModPerLevel
     */
    public Double getrFlatEnergyRegenModPerLevel() {
        return rFlatEnergyRegenModPerLevel;
    }

    /**
     * @return the rFlatGoldPer10Mod
     */
    public Double getrFlatGoldPer10Mod() {
        return rFlatGoldPer10Mod;
    }

    /**
     * @return the rFlatHPModPerLevel
     */
    public Double getrFlatHPModPerLevel() {
        return rFlatHPModPerLevel;
    }

    /**
     * @return the rFlatHPRegenModPerLevel
     */
    public Double getrFlatHPRegenModPerLevel() {
        return rFlatHPRegenModPerLevel;
    }

    /**
     * @return the rFlatMagicDamageModPerLevel
     */
    public Double getrFlatMagicDamageModPerLevel() {
        return rFlatMagicDamageModPerLevel;
    }

    /**
     * @return the rFlatMagicPenetrationMod
     */
    public Double getrFlatMagicPenetrationMod() {
        return rFlatMagicPenetrationMod;
    }

    /**
     * @return the rFlatMagicPenetrationModPerLevel
     */
    public Double getrFlatMagicPenetrationModPerLevel() {
        return rFlatMagicPenetrationModPerLevel;
    }

    /**
     * @return the rFlatMovementSpeedModPerLevel
     */
    public Double getrFlatMovementSpeedModPerLevel() {
        return rFlatMovementSpeedModPerLevel;
    }

    /**
     * @return the rFlatMPModPerLevel
     */
    public Double getrFlatMPModPerLevel() {
        return rFlatMPModPerLevel;
    }

    /**
     * @return the rFlatMPRegenModPerLevel
     */
    public Double getrFlatMPRegenModPerLevel() {
        return rFlatMPRegenModPerLevel;
    }

    /**
     * @return the rFlatPhysicalDamageModPerLevel
     */
    public Double getrFlatPhysicalDamageModPerLevel() {
        return rFlatPhysicalDamageModPerLevel;
    }

    /**
     * @return the rFlatSpellBlockModPerLevel
     */
    public Double getrFlatSpellBlockModPerLevel() {
        return rFlatSpellBlockModPerLevel;
    }

    /**
     * @return the rFlatTimeDeadMod
     */
    public Double getrFlatTimeDeadMod() {
        return rFlatTimeDeadMod;
    }

    /**
     * @return the rFlatTimeDeadModPerLevel
     */
    public Double getrFlatTimeDeadModPerLevel() {
        return rFlatTimeDeadModPerLevel;
    }

    /**
     * @return the rPercentArmorPenetrationMod
     */
    public Double getrPercentArmorPenetrationMod() {
        return rPercentArmorPenetrationMod;
    }

    /**
     * @return the rPercentArmorPenetrationModPerLevel
     */
    public Double getrPercentArmorPenetrationModPerLevel() {
        return rPercentArmorPenetrationModPerLevel;
    }

    /**
     * @return the rPercentAttackSpeedModPerLevel
     */
    public Double getrPercentAttackSpeedModPerLevel() {
        return rPercentAttackSpeedModPerLevel;
    }

    /**
     * @return the rPercentCooldownMod
     */
    public Double getrPercentCooldownMod() {
        return rPercentCooldownMod;
    }

    /**
     * @return the rPercentCooldownModPerLevel
     */
    public Double getrPercentCooldownModPerLevel() {
        return rPercentCooldownModPerLevel;
    }

    /**
     * @return the rPercentMagicPenetrationMod
     */
    public Double getrPercentMagicPenetrationMod() {
        return rPercentMagicPenetrationMod;
    }

    /**
     * @return the rPercentMagicPenetrationModPerLevel
     */
    public Double getrPercentMagicPenetrationModPerLevel() {
        return rPercentMagicPenetrationModPerLevel;
    }

    /**
     * @return the rPercentMovementSpeedModPerLevel
     */
    public Double getrPercentMovementSpeedModPerLevel() {
        return rPercentMovementSpeedModPerLevel;
    }

    /**
     * @return the rPercentTimeDeadMod
     */
    public Double getrPercentTimeDeadMod() {
        return rPercentTimeDeadMod;
    }

    /**
     * @return the rPercentTimeDeadModPerLevel
     */
    public Double getrPercentTimeDeadModPerLevel() {
        return rPercentTimeDeadModPerLevel;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (FlatArmorMod == null ? 0 : FlatArmorMod.hashCode());
        result = prime * result + (FlatAttackSpeedMod == null ? 0 : FlatAttackSpeedMod.hashCode());
        result = prime * result + (FlatBlockMod == null ? 0 : FlatBlockMod.hashCode());
        result = prime * result + (FlatCritChanceMod == null ? 0 : FlatCritChanceMod.hashCode());
        result = prime * result + (FlatCritDamageMod == null ? 0 : FlatCritDamageMod.hashCode());
        result = prime * result + (FlatEXPBonus == null ? 0 : FlatEXPBonus.hashCode());
        result = prime * result + (FlatEnergyPoolMod == null ? 0 : FlatEnergyPoolMod.hashCode());
        result = prime * result + (FlatEnergyRegenMod == null ? 0 : FlatEnergyRegenMod.hashCode());
        result = prime * result + (FlatHPPoolMod == null ? 0 : FlatHPPoolMod.hashCode());
        result = prime * result + (FlatHPRegenMod == null ? 0 : FlatHPRegenMod.hashCode());
        result = prime * result + (FlatMPPoolMod == null ? 0 : FlatMPPoolMod.hashCode());
        result = prime * result + (FlatMPRegenMod == null ? 0 : FlatMPRegenMod.hashCode());
        result = prime * result + (FlatMagicDamageMod == null ? 0 : FlatMagicDamageMod.hashCode());
        result = prime * result + (FlatMovementSpeedMod == null ? 0 : FlatMovementSpeedMod.hashCode());
        result = prime * result + (FlatPhysicalDamageMod == null ? 0 : FlatPhysicalDamageMod.hashCode());
        result = prime * result + (FlatSpellBlockMod == null ? 0 : FlatSpellBlockMod.hashCode());
        result = prime * result + (PercentArmorMod == null ? 0 : PercentArmorMod.hashCode());
        result = prime * result + (PercentAttackSpeedMod == null ? 0 : PercentAttackSpeedMod.hashCode());
        result = prime * result + (PercentBlockMod == null ? 0 : PercentBlockMod.hashCode());
        result = prime * result + (PercentCritChanceMod == null ? 0 : PercentCritChanceMod.hashCode());
        result = prime * result + (PercentCritDamageMod == null ? 0 : PercentCritDamageMod.hashCode());
        result = prime * result + (PercentDodgeMod == null ? 0 : PercentDodgeMod.hashCode());
        result = prime * result + (PercentEXPBonus == null ? 0 : PercentEXPBonus.hashCode());
        result = prime * result + (PercentHPPoolMod == null ? 0 : PercentHPPoolMod.hashCode());
        result = prime * result + (PercentHPRegenMod == null ? 0 : PercentHPRegenMod.hashCode());
        result = prime * result + (PercentLifeStealMod == null ? 0 : PercentLifeStealMod.hashCode());
        result = prime * result + (PercentMPPoolMod == null ? 0 : PercentMPPoolMod.hashCode());
        result = prime * result + (PercentMPRegenMod == null ? 0 : PercentMPRegenMod.hashCode());
        result = prime * result + (PercentMagicDamageMod == null ? 0 : PercentMagicDamageMod.hashCode());
        result = prime * result + (PercentMovementSpeedMod == null ? 0 : PercentMovementSpeedMod.hashCode());
        result = prime * result + (PercentPhysicalDamageMod == null ? 0 : PercentPhysicalDamageMod.hashCode());
        result = prime * result + (PercentSpellBlockMod == null ? 0 : PercentSpellBlockMod.hashCode());
        result = prime * result + (PercentSpellVampMod == null ? 0 : PercentSpellVampMod.hashCode());
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
     * @param flatArmorMod
     *            the flatArmorMod to set
     */
    public void setFlatArmorMod(final Double flatArmorMod) {
        FlatArmorMod = flatArmorMod;
    }

    /**
     * @param flatAttackSpeedMod
     *            the flatAttackSpeedMod to set
     */
    public void setFlatAttackSpeedMod(final Double flatAttackSpeedMod) {
        FlatAttackSpeedMod = flatAttackSpeedMod;
    }

    /**
     * @param flatBlockMod
     *            the flatBlockMod to set
     */
    public void setFlatBlockMod(final Double flatBlockMod) {
        FlatBlockMod = flatBlockMod;
    }

    /**
     * @param flatCritChanceMod
     *            the flatCritChanceMod to set
     */
    public void setFlatCritChanceMod(final Double flatCritChanceMod) {
        FlatCritChanceMod = flatCritChanceMod;
    }

    /**
     * @param flatCritDamageMod
     *            the flatCritDamageMod to set
     */
    public void setFlatCritDamageMod(final Double flatCritDamageMod) {
        FlatCritDamageMod = flatCritDamageMod;
    }

    /**
     * @param flatEnergyPoolMod
     *            the flatEnergyPoolMod to set
     */
    public void setFlatEnergyPoolMod(final Double flatEnergyPoolMod) {
        FlatEnergyPoolMod = flatEnergyPoolMod;
    }

    /**
     * @param flatEnergyRegenMod
     *            the flatEnergyRegenMod to set
     */
    public void setFlatEnergyRegenMod(final Double flatEnergyRegenMod) {
        FlatEnergyRegenMod = flatEnergyRegenMod;
    }

    /**
     * @param flatEXPBonus
     *            the flatEXPBonus to set
     */
    public void setFlatEXPBonus(final Double flatEXPBonus) {
        FlatEXPBonus = flatEXPBonus;
    }

    /**
     * @param flatHPPoolMod
     *            the flatHPPoolMod to set
     */
    public void setFlatHPPoolMod(final Double flatHPPoolMod) {
        FlatHPPoolMod = flatHPPoolMod;
    }

    /**
     * @param flatHPRegenMod
     *            the flatHPRegenMod to set
     */
    public void setFlatHPRegenMod(final Double flatHPRegenMod) {
        FlatHPRegenMod = flatHPRegenMod;
    }

    /**
     * @param flatMagicDamageMod
     *            the flatMagicDamageMod to set
     */
    public void setFlatMagicDamageMod(final Double flatMagicDamageMod) {
        FlatMagicDamageMod = flatMagicDamageMod;
    }

    /**
     * @param flatMovementSpeedMod
     *            the flatMovementSpeedMod to set
     */
    public void setFlatMovementSpeedMod(final Double flatMovementSpeedMod) {
        FlatMovementSpeedMod = flatMovementSpeedMod;
    }

    /**
     * @param flatMPPoolMod
     *            the flatMPPoolMod to set
     */
    public void setFlatMPPoolMod(final Double flatMPPoolMod) {
        FlatMPPoolMod = flatMPPoolMod;
    }

    /**
     * @param flatMPRegenMod
     *            the flatMPRegenMod to set
     */
    public void setFlatMPRegenMod(final Double flatMPRegenMod) {
        FlatMPRegenMod = flatMPRegenMod;
    }

    /**
     * @param flatPhysicalDamageMod
     *            the flatPhysicalDamageMod to set
     */
    public void setFlatPhysicalDamageMod(final Double flatPhysicalDamageMod) {
        FlatPhysicalDamageMod = flatPhysicalDamageMod;
    }

    /**
     * @param flatSpellBlockMod
     *            the flatSpellBlockMod to set
     */
    public void setFlatSpellBlockMod(final Double flatSpellBlockMod) {
        FlatSpellBlockMod = flatSpellBlockMod;
    }

    /**
     * @param percentArmorMod
     *            the percentArmorMod to set
     */
    public void setPercentArmorMod(final Double percentArmorMod) {
        PercentArmorMod = percentArmorMod;
    }

    /**
     * @param percentAttackSpeedMod
     *            the percentAttackSpeedMod to set
     */
    public void setPercentAttackSpeedMod(final Double percentAttackSpeedMod) {
        PercentAttackSpeedMod = percentAttackSpeedMod;
    }

    /**
     * @param percentBlockMod
     *            the percentBlockMod to set
     */
    public void setPercentBlockMod(final Double percentBlockMod) {
        PercentBlockMod = percentBlockMod;
    }

    /**
     * @param percentCritChanceMod
     *            the percentCritChanceMod to set
     */
    public void setPercentCritChanceMod(final Double percentCritChanceMod) {
        PercentCritChanceMod = percentCritChanceMod;
    }

    /**
     * @param percentCritDamageMod
     *            the percentCritDamageMod to set
     */
    public void setPercentCritDamageMod(final Double percentCritDamageMod) {
        PercentCritDamageMod = percentCritDamageMod;
    }

    /**
     * @param percentDodgeMod
     *            the percentDodgeMod to set
     */
    public void setPercentDodgeMod(final Double percentDodgeMod) {
        PercentDodgeMod = percentDodgeMod;
    }

    /**
     * @param percentEXPBonus
     *            the percentEXPBonus to set
     */
    public void setPercentEXPBonus(final Double percentEXPBonus) {
        PercentEXPBonus = percentEXPBonus;
    }

    /**
     * @param percentHPPoolMod
     *            the percentHPPoolMod to set
     */
    public void setPercentHPPoolMod(final Double percentHPPoolMod) {
        PercentHPPoolMod = percentHPPoolMod;
    }

    /**
     * @param percentHPRegenMod
     *            the percentHPRegenMod to set
     */
    public void setPercentHPRegenMod(final Double percentHPRegenMod) {
        PercentHPRegenMod = percentHPRegenMod;
    }

    /**
     * @param percentLifeStealMod
     *            the percentLifeStealMod to set
     */
    public void setPercentLifeStealMod(final Double percentLifeStealMod) {
        PercentLifeStealMod = percentLifeStealMod;
    }

    /**
     * @param percentMagicDamageMod
     *            the percentMagicDamageMod to set
     */
    public void setPercentMagicDamageMod(final Double percentMagicDamageMod) {
        PercentMagicDamageMod = percentMagicDamageMod;
    }

    /**
     * @param percentMovementSpeedMod
     *            the percentMovementSpeedMod to set
     */
    public void setPercentMovementSpeedMod(final Double percentMovementSpeedMod) {
        PercentMovementSpeedMod = percentMovementSpeedMod;
    }

    /**
     * @param percentMPPoolMod
     *            the percentMPPoolMod to set
     */
    public void setPercentMPPoolMod(final Double percentMPPoolMod) {
        PercentMPPoolMod = percentMPPoolMod;
    }

    /**
     * @param percentMPRegenMod
     *            the percentMPRegenMod to set
     */
    public void setPercentMPRegenMod(final Double percentMPRegenMod) {
        PercentMPRegenMod = percentMPRegenMod;
    }

    /**
     * @param percentPhysicalDamageMod
     *            the percentPhysicalDamageMod to set
     */
    public void setPercentPhysicalDamageMod(final Double percentPhysicalDamageMod) {
        PercentPhysicalDamageMod = percentPhysicalDamageMod;
    }

    /**
     * @param percentSpellBlockMod
     *            the percentSpellBlockMod to set
     */
    public void setPercentSpellBlockMod(final Double percentSpellBlockMod) {
        PercentSpellBlockMod = percentSpellBlockMod;
    }

    /**
     * @param percentSpellVampMod
     *            the percentSpellVampMod to set
     */
    public void setPercentSpellVampMod(final Double percentSpellVampMod) {
        PercentSpellVampMod = percentSpellVampMod;
    }

    /**
     * @param rFlatArmorModPerLevel
     *            the rFlatArmorModPerLevel to set
     */
    public void setrFlatArmorModPerLevel(final Double rFlatArmorModPerLevel) {
        this.rFlatArmorModPerLevel = rFlatArmorModPerLevel;
    }

    /**
     * @param rFlatArmorPenetrationMod
     *            the rFlatArmorPenetrationMod to set
     */
    public void setrFlatArmorPenetrationMod(final Double rFlatArmorPenetrationMod) {
        this.rFlatArmorPenetrationMod = rFlatArmorPenetrationMod;
    }

    /**
     * @param rFlatArmorPenetrationModPerLevel
     *            the rFlatArmorPenetrationModPerLevel to set
     */
    public void setrFlatArmorPenetrationModPerLevel(final Double rFlatArmorPenetrationModPerLevel) {
        this.rFlatArmorPenetrationModPerLevel = rFlatArmorPenetrationModPerLevel;
    }

    /**
     * @param rFlatCritChanceModPerLevel
     *            the rFlatCritChanceModPerLevel to set
     */
    public void setrFlatCritChanceModPerLevel(final Double rFlatCritChanceModPerLevel) {
        this.rFlatCritChanceModPerLevel = rFlatCritChanceModPerLevel;
    }

    /**
     * @param rFlatCritDamageModPerLevel
     *            the rFlatCritDamageModPerLevel to set
     */
    public void setrFlatCritDamageModPerLevel(final Double rFlatCritDamageModPerLevel) {
        this.rFlatCritDamageModPerLevel = rFlatCritDamageModPerLevel;
    }

    /**
     * @param rFlatDodgeMod
     *            the rFlatDodgeMod to set
     */
    public void setrFlatDodgeMod(final Double rFlatDodgeMod) {
        this.rFlatDodgeMod = rFlatDodgeMod;
    }

    /**
     * @param rFlatDodgeModPerLevel
     *            the rFlatDodgeModPerLevel to set
     */
    public void setrFlatDodgeModPerLevel(final Double rFlatDodgeModPerLevel) {
        this.rFlatDodgeModPerLevel = rFlatDodgeModPerLevel;
    }

    /**
     * @param rFlatEnergyModPerLevel
     *            the rFlatEnergyModPerLevel to set
     */
    public void setrFlatEnergyModPerLevel(final Double rFlatEnergyModPerLevel) {
        this.rFlatEnergyModPerLevel = rFlatEnergyModPerLevel;
    }

    /**
     * @param rFlatEnergyRegenModPerLevel
     *            the rFlatEnergyRegenModPerLevel to set
     */
    public void setrFlatEnergyRegenModPerLevel(final Double rFlatEnergyRegenModPerLevel) {
        this.rFlatEnergyRegenModPerLevel = rFlatEnergyRegenModPerLevel;
    }

    /**
     * @param rFlatGoldPer10Mod
     *            the rFlatGoldPer10Mod to set
     */
    public void setrFlatGoldPer10Mod(final Double rFlatGoldPer10Mod) {
        this.rFlatGoldPer10Mod = rFlatGoldPer10Mod;
    }

    /**
     * @param rFlatHPModPerLevel
     *            the rFlatHPModPerLevel to set
     */
    public void setrFlatHPModPerLevel(final Double rFlatHPModPerLevel) {
        this.rFlatHPModPerLevel = rFlatHPModPerLevel;
    }

    /**
     * @param rFlatHPRegenModPerLevel
     *            the rFlatHPRegenModPerLevel to set
     */
    public void setrFlatHPRegenModPerLevel(final Double rFlatHPRegenModPerLevel) {
        this.rFlatHPRegenModPerLevel = rFlatHPRegenModPerLevel;
    }

    /**
     * @param rFlatMagicDamageModPerLevel
     *            the rFlatMagicDamageModPerLevel to set
     */
    public void setrFlatMagicDamageModPerLevel(final Double rFlatMagicDamageModPerLevel) {
        this.rFlatMagicDamageModPerLevel = rFlatMagicDamageModPerLevel;
    }

    /**
     * @param rFlatMagicPenetrationMod
     *            the rFlatMagicPenetrationMod to set
     */
    public void setrFlatMagicPenetrationMod(final Double rFlatMagicPenetrationMod) {
        this.rFlatMagicPenetrationMod = rFlatMagicPenetrationMod;
    }

    /**
     * @param rFlatMagicPenetrationModPerLevel
     *            the rFlatMagicPenetrationModPerLevel to set
     */
    public void setrFlatMagicPenetrationModPerLevel(final Double rFlatMagicPenetrationModPerLevel) {
        this.rFlatMagicPenetrationModPerLevel = rFlatMagicPenetrationModPerLevel;
    }

    /**
     * @param rFlatMovementSpeedModPerLevel
     *            the rFlatMovementSpeedModPerLevel to set
     */
    public void setrFlatMovementSpeedModPerLevel(final Double rFlatMovementSpeedModPerLevel) {
        this.rFlatMovementSpeedModPerLevel = rFlatMovementSpeedModPerLevel;
    }

    /**
     * @param rFlatMPModPerLevel
     *            the rFlatMPModPerLevel to set
     */
    public void setrFlatMPModPerLevel(final Double rFlatMPModPerLevel) {
        this.rFlatMPModPerLevel = rFlatMPModPerLevel;
    }

    /**
     * @param rFlatMPRegenModPerLevel
     *            the rFlatMPRegenModPerLevel to set
     */
    public void setrFlatMPRegenModPerLevel(final Double rFlatMPRegenModPerLevel) {
        this.rFlatMPRegenModPerLevel = rFlatMPRegenModPerLevel;
    }

    /**
     * @param rFlatPhysicalDamageModPerLevel
     *            the rFlatPhysicalDamageModPerLevel to set
     */
    public void setrFlatPhysicalDamageModPerLevel(final Double rFlatPhysicalDamageModPerLevel) {
        this.rFlatPhysicalDamageModPerLevel = rFlatPhysicalDamageModPerLevel;
    }

    /**
     * @param rFlatSpellBlockModPerLevel
     *            the rFlatSpellBlockModPerLevel to set
     */
    public void setrFlatSpellBlockModPerLevel(final Double rFlatSpellBlockModPerLevel) {
        this.rFlatSpellBlockModPerLevel = rFlatSpellBlockModPerLevel;
    }

    /**
     * @param rFlatTimeDeadMod
     *            the rFlatTimeDeadMod to set
     */
    public void setrFlatTimeDeadMod(final Double rFlatTimeDeadMod) {
        this.rFlatTimeDeadMod = rFlatTimeDeadMod;
    }

    /**
     * @param rFlatTimeDeadModPerLevel
     *            the rFlatTimeDeadModPerLevel to set
     */
    public void setrFlatTimeDeadModPerLevel(final Double rFlatTimeDeadModPerLevel) {
        this.rFlatTimeDeadModPerLevel = rFlatTimeDeadModPerLevel;
    }

    /**
     * @param rPercentArmorPenetrationMod
     *            the rPercentArmorPenetrationMod to set
     */
    public void setrPercentArmorPenetrationMod(final Double rPercentArmorPenetrationMod) {
        this.rPercentArmorPenetrationMod = rPercentArmorPenetrationMod;
    }

    /**
     * @param rPercentArmorPenetrationModPerLevel
     *            the rPercentArmorPenetrationModPerLevel to set
     */
    public void setrPercentArmorPenetrationModPerLevel(final Double rPercentArmorPenetrationModPerLevel) {
        this.rPercentArmorPenetrationModPerLevel = rPercentArmorPenetrationModPerLevel;
    }

    /**
     * @param rPercentAttackSpeedModPerLevel
     *            the rPercentAttackSpeedModPerLevel to set
     */
    public void setrPercentAttackSpeedModPerLevel(final Double rPercentAttackSpeedModPerLevel) {
        this.rPercentAttackSpeedModPerLevel = rPercentAttackSpeedModPerLevel;
    }

    /**
     * @param rPercentCooldownMod
     *            the rPercentCooldownMod to set
     */
    public void setrPercentCooldownMod(final Double rPercentCooldownMod) {
        this.rPercentCooldownMod = rPercentCooldownMod;
    }

    /**
     * @param rPercentCooldownModPerLevel
     *            the rPercentCooldownModPerLevel to set
     */
    public void setrPercentCooldownModPerLevel(final Double rPercentCooldownModPerLevel) {
        this.rPercentCooldownModPerLevel = rPercentCooldownModPerLevel;
    }

    /**
     * @param rPercentMagicPenetrationMod
     *            the rPercentMagicPenetrationMod to set
     */
    public void setrPercentMagicPenetrationMod(final Double rPercentMagicPenetrationMod) {
        this.rPercentMagicPenetrationMod = rPercentMagicPenetrationMod;
    }

    /**
     * @param rPercentMagicPenetrationModPerLevel
     *            the rPercentMagicPenetrationModPerLevel to set
     */
    public void setrPercentMagicPenetrationModPerLevel(final Double rPercentMagicPenetrationModPerLevel) {
        this.rPercentMagicPenetrationModPerLevel = rPercentMagicPenetrationModPerLevel;
    }

    /**
     * @param rPercentMovementSpeedModPerLevel
     *            the rPercentMovementSpeedModPerLevel to set
     */
    public void setrPercentMovementSpeedModPerLevel(final Double rPercentMovementSpeedModPerLevel) {
        this.rPercentMovementSpeedModPerLevel = rPercentMovementSpeedModPerLevel;
    }

    /**
     * @param rPercentTimeDeadMod
     *            the rPercentTimeDeadMod to set
     */
    public void setrPercentTimeDeadMod(final Double rPercentTimeDeadMod) {
        this.rPercentTimeDeadMod = rPercentTimeDeadMod;
    }

    /**
     * @param rPercentTimeDeadModPerLevel
     *            the rPercentTimeDeadModPerLevel to set
     */
    public void setrPercentTimeDeadModPerLevel(final Double rPercentTimeDeadModPerLevel) {
        this.rPercentTimeDeadModPerLevel = rPercentTimeDeadModPerLevel;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BasicStats [FlatArmorMod=" + FlatArmorMod + ", FlatAttackSpeedMod=" + FlatAttackSpeedMod + ", FlatBlockMod=" + FlatBlockMod
                + ", FlatCritChanceMod=" + FlatCritChanceMod + ", FlatCritDamageMod=" + FlatCritDamageMod + ", FlatEXPBonus=" + FlatEXPBonus
                + ", FlatEnergyPoolMod=" + FlatEnergyPoolMod + ", FlatEnergyRegenMod=" + FlatEnergyRegenMod + ", FlatHPPoolMod=" + FlatHPPoolMod
                + ", FlatHPRegenMod=" + FlatHPRegenMod + ", FlatMPPoolMod=" + FlatMPPoolMod + ", FlatMPRegenMod=" + FlatMPRegenMod + ", FlatMagicDamageMod="
                + FlatMagicDamageMod + ", FlatMovementSpeedMod=" + FlatMovementSpeedMod + ", FlatPhysicalDamageMod=" + FlatPhysicalDamageMod
                + ", FlatSpellBlockMod=" + FlatSpellBlockMod + ", PercentArmorMod=" + PercentArmorMod + ", PercentAttackSpeedMod=" + PercentAttackSpeedMod
                + ", PercentBlockMod=" + PercentBlockMod + ", PercentCritChanceMod=" + PercentCritChanceMod + ", PercentCritDamageMod=" + PercentCritDamageMod
                + ", PercentDodgeMod=" + PercentDodgeMod + ", PercentEXPBonus=" + PercentEXPBonus + ", PercentHPPoolMod=" + PercentHPPoolMod
                + ", PercentHPRegenMod=" + PercentHPRegenMod + ", PercentLifeStealMod=" + PercentLifeStealMod + ", PercentMPPoolMod=" + PercentMPPoolMod
                + ", PercentMPRegenMod=" + PercentMPRegenMod + ", PercentMagicDamageMod=" + PercentMagicDamageMod + ", PercentMovementSpeedMod="
                + PercentMovementSpeedMod + ", PercentPhysicalDamageMod=" + PercentPhysicalDamageMod + ", PercentSpellBlockMod=" + PercentSpellBlockMod
                + ", PercentSpellVampMod=" + PercentSpellVampMod + ", rFlatArmorModPerLevel=" + rFlatArmorModPerLevel + ", rFlatArmorPenetrationMod="
                + rFlatArmorPenetrationMod + ", rFlatArmorPenetrationModPerLevel=" + rFlatArmorPenetrationModPerLevel + ", rFlatCritChanceModPerLevel="
                + rFlatCritChanceModPerLevel + ", rFlatCritDamageModPerLevel=" + rFlatCritDamageModPerLevel + ", rFlatDodgeMod=" + rFlatDodgeMod
                + ", rFlatDodgeModPerLevel=" + rFlatDodgeModPerLevel + ", rFlatEnergyModPerLevel=" + rFlatEnergyModPerLevel + ", rFlatEnergyRegenModPerLevel="
                + rFlatEnergyRegenModPerLevel + ", rFlatGoldPer10Mod=" + rFlatGoldPer10Mod + ", rFlatHPModPerLevel=" + rFlatHPModPerLevel
                + ", rFlatHPRegenModPerLevel=" + rFlatHPRegenModPerLevel + ", rFlatMPModPerLevel=" + rFlatMPModPerLevel + ", rFlatMPRegenModPerLevel="
                + rFlatMPRegenModPerLevel + ", rFlatMagicDamageModPerLevel=" + rFlatMagicDamageModPerLevel + ", rFlatMagicPenetrationMod="
                + rFlatMagicPenetrationMod + ", rFlatMagicPenetrationModPerLevel=" + rFlatMagicPenetrationModPerLevel + ", rFlatMovementSpeedModPerLevel="
                + rFlatMovementSpeedModPerLevel + ", rFlatPhysicalDamageModPerLevel=" + rFlatPhysicalDamageModPerLevel + ", rFlatSpellBlockModPerLevel="
                + rFlatSpellBlockModPerLevel + ", rFlatTimeDeadMod=" + rFlatTimeDeadMod + ", rFlatTimeDeadModPerLevel=" + rFlatTimeDeadModPerLevel
                + ", rPercentArmorPenetrationMod=" + rPercentArmorPenetrationMod + ", rPercentArmorPenetrationModPerLevel="
                + rPercentArmorPenetrationModPerLevel + ", rPercentAttackSpeedModPerLevel=" + rPercentAttackSpeedModPerLevel + ", rPercentCooldownMod="
                + rPercentCooldownMod + ", rPercentCooldownModPerLevel=" + rPercentCooldownModPerLevel + ", rPercentMagicPenetrationMod="
                + rPercentMagicPenetrationMod + ", rPercentMagicPenetrationModPerLevel=" + rPercentMagicPenetrationModPerLevel
                + ", rPercentMovementSpeedModPerLevel=" + rPercentMovementSpeedModPerLevel + ", rPercentTimeDeadMod=" + rPercentTimeDeadMod
                + ", rPercentTimeDeadModPerLevel=" + rPercentTimeDeadModPerLevel + "]";
    }
}
