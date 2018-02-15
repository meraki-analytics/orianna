package com.merakianalytics.orianna.types.dto.staticdata;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.merakianalytics.orianna.types.dto.DataObject;

@JsonNaming(InventoryDataStats.JacksonNamingStrategy.class)
public class InventoryDataStats extends DataObject {
    public static class JacksonNamingStrategy extends PropertyNamingStrategyBase {
        private static final long serialVersionUID = 860782375575167663L;

        @Override
        public String translate(final String propertyName) {
            if(propertyName == null || propertyName.isEmpty()) {
                return propertyName;
            }
            final StringBuilder sb = new StringBuilder(propertyName);
            sb.setCharAt(0, Character.toUpperCase(propertyName.charAt(0)));
            return sb.toString();
        }
    }

    private static final long serialVersionUID = 277601872683286664L;
    private double PercentCritDamageMod, PercentSpellBlockMod, PercentHPRegenMod, PercentMovementSpeedMod, FlatSpellBlockMod, FlatCritDamageMod,
            FlatEnergyPoolMod, PercentLifeStealMod, FlatMPPoolMod, FlatMovementSpeedMod, PercentAttackSpeedMod, FlatBlockMod, PercentBlockMod,
            FlatEnergyRegenMod, PercentSpellVampMod, FlatMPRegenMod, PercentDodgeMod, FlatAttackSpeedMod, FlatArmorMod, FlatHPRegenMod, PercentMagicDamageMod,
            PercentMPPoolMod, FlatMagicDamageMod, PercentMPRegenMod, PercentPhysicalDamageMod, FlatPhysicalDamageMod, PercentHPPoolMod, PercentArmorMod,
            PercentCritChanceMod, PercentEXPBonus, FlatHPPoolMod, FlatCritChanceMod, FlatEXPBonus;

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
        final InventoryDataStats other = (InventoryDataStats)obj;
        if(Double.doubleToLongBits(FlatArmorMod) != Double.doubleToLongBits(other.FlatArmorMod)) {
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
        if(Double.doubleToLongBits(FlatCritDamageMod) != Double.doubleToLongBits(other.FlatCritDamageMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatEXPBonus) != Double.doubleToLongBits(other.FlatEXPBonus)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatEnergyPoolMod) != Double.doubleToLongBits(other.FlatEnergyPoolMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatEnergyRegenMod) != Double.doubleToLongBits(other.FlatEnergyRegenMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatHPPoolMod) != Double.doubleToLongBits(other.FlatHPPoolMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatHPRegenMod) != Double.doubleToLongBits(other.FlatHPRegenMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMPPoolMod) != Double.doubleToLongBits(other.FlatMPPoolMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMPRegenMod) != Double.doubleToLongBits(other.FlatMPRegenMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMagicDamageMod) != Double.doubleToLongBits(other.FlatMagicDamageMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatMovementSpeedMod) != Double.doubleToLongBits(other.FlatMovementSpeedMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatPhysicalDamageMod) != Double.doubleToLongBits(other.FlatPhysicalDamageMod)) {
            return false;
        }
        if(Double.doubleToLongBits(FlatSpellBlockMod) != Double.doubleToLongBits(other.FlatSpellBlockMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentArmorMod) != Double.doubleToLongBits(other.PercentArmorMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentAttackSpeedMod) != Double.doubleToLongBits(other.PercentAttackSpeedMod)) {
            return false;
        }
        if(Double.doubleToLongBits(PercentBlockMod) != Double.doubleToLongBits(other.PercentBlockMod)) {
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
        if(Double.doubleToLongBits(PercentMovementSpeedMod) != Double.doubleToLongBits(other.PercentMovementSpeedMod)) {
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
        return true;
    }

    /**
     * @return the flatArmorMod
     */
    public double getFlatArmorMod() {
        return FlatArmorMod;
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
     * @return the flatCritDamageMod
     */
    public double getFlatCritDamageMod() {
        return FlatCritDamageMod;
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
     * @return the flatEXPBonus
     */
    public double getFlatEXPBonus() {
        return FlatEXPBonus;
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
     * @return the flatMagicDamageMod
     */
    public double getFlatMagicDamageMod() {
        return FlatMagicDamageMod;
    }

    /**
     * @return the flatMovementSpeedMod
     */
    public double getFlatMovementSpeedMod() {
        return FlatMovementSpeedMod;
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
     * @return the flatPhysicalDamageMod
     */
    public double getFlatPhysicalDamageMod() {
        return FlatPhysicalDamageMod;
    }

    /**
     * @return the flatSpellBlockMod
     */
    public double getFlatSpellBlockMod() {
        return FlatSpellBlockMod;
    }

    /**
     * @return the percentArmorMod
     */
    public double getPercentArmorMod() {
        return PercentArmorMod;
    }

    /**
     * @return the percentAttackSpeedMod
     */
    public double getPercentAttackSpeedMod() {
        return PercentAttackSpeedMod;
    }

    /**
     * @return the percentBlockMod
     */
    public double getPercentBlockMod() {
        return PercentBlockMod;
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
     * @return the percentMovementSpeedMod
     */
    public double getPercentMovementSpeedMod() {
        return PercentMovementSpeedMod;
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
        temp = Double.doubleToLongBits(FlatAttackSpeedMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatBlockMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatCritChanceMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatCritDamageMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatEXPBonus);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatEnergyPoolMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatEnergyRegenMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatHPPoolMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatHPRegenMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMPPoolMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMPRegenMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMagicDamageMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatMovementSpeedMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatPhysicalDamageMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(FlatSpellBlockMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentArmorMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentAttackSpeedMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentBlockMod);
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
        temp = Double.doubleToLongBits(PercentMovementSpeedMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentPhysicalDamageMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentSpellBlockMod);
        result = prime * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(PercentSpellVampMod);
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
     * @param flatCritDamageMod
     *        the flatCritDamageMod to set
     */
    public void setFlatCritDamageMod(final double flatCritDamageMod) {
        FlatCritDamageMod = flatCritDamageMod;
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
     * @param flatEXPBonus
     *        the flatEXPBonus to set
     */
    public void setFlatEXPBonus(final double flatEXPBonus) {
        FlatEXPBonus = flatEXPBonus;
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
     * @param flatMagicDamageMod
     *        the flatMagicDamageMod to set
     */
    public void setFlatMagicDamageMod(final double flatMagicDamageMod) {
        FlatMagicDamageMod = flatMagicDamageMod;
    }

    /**
     * @param flatMovementSpeedMod
     *        the flatMovementSpeedMod to set
     */
    public void setFlatMovementSpeedMod(final double flatMovementSpeedMod) {
        FlatMovementSpeedMod = flatMovementSpeedMod;
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
     * @param flatPhysicalDamageMod
     *        the flatPhysicalDamageMod to set
     */
    public void setFlatPhysicalDamageMod(final double flatPhysicalDamageMod) {
        FlatPhysicalDamageMod = flatPhysicalDamageMod;
    }

    /**
     * @param flatSpellBlockMod
     *        the flatSpellBlockMod to set
     */
    public void setFlatSpellBlockMod(final double flatSpellBlockMod) {
        FlatSpellBlockMod = flatSpellBlockMod;
    }

    /**
     * @param percentArmorMod
     *        the percentArmorMod to set
     */
    public void setPercentArmorMod(final double percentArmorMod) {
        PercentArmorMod = percentArmorMod;
    }

    /**
     * @param percentAttackSpeedMod
     *        the percentAttackSpeedMod to set
     */
    public void setPercentAttackSpeedMod(final double percentAttackSpeedMod) {
        PercentAttackSpeedMod = percentAttackSpeedMod;
    }

    /**
     * @param percentBlockMod
     *        the percentBlockMod to set
     */
    public void setPercentBlockMod(final double percentBlockMod) {
        PercentBlockMod = percentBlockMod;
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
     * @param percentMovementSpeedMod
     *        the percentMovementSpeedMod to set
     */
    public void setPercentMovementSpeedMod(final double percentMovementSpeedMod) {
        PercentMovementSpeedMod = percentMovementSpeedMod;
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
}
