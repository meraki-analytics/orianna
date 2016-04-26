package com.robrua.orianna.type.core.staticdata;

import com.robrua.orianna.type.core.OriannaObject;

public class BasicDataStats extends OriannaObject<com.robrua.orianna.type.dto.staticdata.BasicDataStats> {
    private static final long serialVersionUID = 7920872026162580279L;
    double percentCooldownReduction, flatArmorPenetration, percentAbilityPower, percentArmorPenetration, flatMagicPenetration, percentMagicPenetration,
            goldPer10, percentAttackDamage, percentLifeSteal, percentSpellVamp, percentManaRegen, percentHealthRegen, percentBonusHealth, percentMovespeed,
            tenacity;

    /**
     * @param data
     *            the underlying dto
     */
    public BasicDataStats(final com.robrua.orianna.type.dto.staticdata.BasicDataStats data) {
        super(data, com.robrua.orianna.type.dto.staticdata.BasicDataStats.class);
    }

    /**
     * Flat armor mod
     *
     * @return flat armor mod
     */
    public double getFlatArmorMod() {
        return super.getDouble(data.getFlatArmorMod());
    }

    /**
     * Flat Armor Penetration
     *
     * @return flat armor penetration
     */
    public double getFlatArmorPenetration() {
        return flatArmorPenetration;
    }

    /**
     * Flat attack speed mod
     *
     * @return flat attack speed mod
     */
    public double getFlatAttackSpeedMod() {
        return super.getDouble(data.getFlatAttackSpeedMod());
    }

    /**
     * Flat block mod
     *
     * @return flat block mod
     */
    public double getFlatBlockMod() {
        return super.getDouble(data.getFlatBlockMod());
    }

    /**
     * Flat crit chance mod
     *
     * @return flat crit chance mod
     */
    public double getFlatCritChanceMod() {
        return super.getDouble(data.getFlatCritChanceMod());
    }

    /**
     * Flat crit damage mod
     *
     * @return flat crit damage mod
     */
    public double getFlatCritDamageMod() {
        return super.getDouble(data.getFlatCritDamageMod());
    }

    /**
     * Flat energy pool mod
     *
     * @return flat energy pool mod
     */
    public double getFlatEnergyPoolMod() {
        return super.getDouble(data.getFlatEnergyPoolMod());
    }

    /**
     * Flat energy regen mod
     *
     * @return flat energy regen mod
     */
    public double getFlatEnergyRegenMod() {
        return super.getDouble(data.getFlatEnergyRegenMod());
    }

    /**
     * Flat EXP bonus
     *
     * @return flat EXP bonus
     */
    public double getFlatEXPBonus() {
        return super.getDouble(data.getFlatEXPBonus());
    }

    /**
     * Flat HP pool mod
     *
     * @return flat HP pool mod
     */
    public double getFlatHPPoolMod() {
        return super.getDouble(data.getFlatHPPoolMod());
    }

    /**
     * Flat HP regen mod
     *
     * @return flat HP regen mod
     */
    public double getFlatHPRegenMod() {
        return super.getDouble(data.getFlatHPRegenMod());
    }

    /**
     * Flat magic damage mod
     *
     * @return flat magic damage mod
     */
    public double getFlatMagicDamageMod() {
        return super.getDouble(data.getFlatMagicDamageMod());
    }

    /**
     * Flat Magic Penetration
     *
     * @return flat magic penetration
     */
    public double getFlatMagicPenetration() {
        return flatMagicPenetration;
    }

    /**
     * Flat movement speed mod
     *
     * @return flat movement speed mod
     */
    public double getFlatMovementSpeedMod() {
        return super.getDouble(data.getFlatMovementSpeedMod());
    }

    /**
     * FlatMPPoolMod
     *
     * @return FlatMPPoolMod
     */
    public double getFlatMPPoolMod() {
        return super.getDouble(data.getFlatMPPoolMod());
    }

    /**
     * Flat MP regen mod
     *
     * @return flat MP regen mod
     */
    public double getFlatMPRegenMod() {
        return super.getDouble(data.getFlatMPRegenMod());
    }

    /**
     * Flat physical damage mod
     *
     * @return flat physical damage mod
     */
    public double getFlatPhysicalDamageMod() {
        return super.getDouble(data.getFlatPhysicalDamageMod());
    }

    /**
     * Flat spell block mod
     *
     * @return flat spell block mod
     */
    public double getFlatSpellBlockMod() {
        return super.getDouble(data.getFlatSpellBlockMod());
    }

    /**
     * Gold Per 10
     *
     * @return gold per 10
     */
    public double getGoldPer10() {
        return goldPer10;
    }

    /**
     * Percent armor mod
     *
     * @return percent armor mod
     */
    public double getPercentArmorMod() {
        return super.getDouble(data.getPercentArmorMod());
    }

    /**
     * Percent Armor Penetration
     *
     * @return percent armor penetration
     */
    public double getPercentArmorPenetration() {
        return percentArmorPenetration;
    }

    /**
     * Percent attack speed mod
     *
     * @return percent attack speed mod
     */
    public double getPercentAttackSpeedMod() {
        return super.getDouble(data.getPercentAttackSpeedMod());
    }

    /**
     * Percent block mod
     *
     * @return percent block mod
     */
    public double getPercentBlockMod() {
        return super.getDouble(data.getPercentBlockMod());
    }

    /**
     * Cooldown Reduction
     *
     * @return cooldown reduction
     */
    public double getPercentCooldownReduction() {
        return Math.abs(percentCooldownReduction);
    }

    /**
     * Percent crit chance mod
     *
     * @return percent crit chance mod
     */
    public double getPercentCritChanceMod() {
        return super.getDouble(data.getPercentCritChanceMod());
    }

    /**
     * Percent crit damage mod
     *
     * @return percent crit damage mod
     */
    public double getPercentCritDamageMod() {
        return super.getDouble(data.getPercentCritDamageMod());
    }

    /**
     * Percent dodge mod
     *
     * @return percent dodge mod
     */
    public double getPercentDodgeMod() {
        return super.getDouble(data.getPercentDodgeMod());
    }

    /**
     * Percent EXP Bonus
     *
     * @return percent EXP bonus
     */
    public double getPercentEXPBonus() {
        return super.getDouble(data.getPercentEXPBonus());
    }

    /**
     * Percent HP pool mod
     *
     * @return percent HP pool mod
     */
    public double getPercentHPPoolMod() {
        return super.getDouble(data.getPercentHPPoolMod()) + percentBonusHealth;
    }

    /**
     * Percent HP regen mod
     *
     * @return percent HP regen mod
     */
    public double getPercentHPRegenMod() {
        return super.getDouble(data.getPercentHPRegenMod()) + percentHealthRegen;
    }

    /**
     * Percent life steal mod
     *
     * @return percent life steal mod
     */
    public double getPercentLifeStealMod() {
        return super.getDouble(data.getPercentLifeStealMod()) + percentLifeSteal;
    }

    /**
     * Percent magic damage mod
     *
     * @return percent magic damage mod
     */
    public double getPercentMagicDamageMod() {
        return super.getDouble(data.getPercentMagicDamageMod()) + percentAbilityPower;
    }

    /**
     * Percent Magic Penetration
     *
     * @return percent magic penetration
     */
    public double getPercentMagicPenetration() {
        return percentMagicPenetration;
    }

    /**
     * Percent movement speed mod
     *
     * @return percent movement speed mod
     */
    public double getPercentMovementSpeedMod() {
        return super.getDouble(data.getPercentMovementSpeedMod()) + percentMovespeed;
    }

    /**
     * Percent MP pool mod
     *
     * @return percent MP pool mod
     */
    public double getPercentMPPoolMod() {
        return super.getDouble(data.getPercentMPPoolMod());
    }

    /**
     * Percent MP regen mod
     *
     * @return percent MP regen mod
     */
    public double getPercentMPRegenMod() {
        return super.getDouble(data.getPercentMPRegenMod()) + percentManaRegen;
    }

    /**
     * Percent physical damage mod
     *
     * @return percent physical damage mod
     */
    public double getPercentPhysicalDamageMod() {
        return super.getDouble(data.getPercentPhysicalDamageMod()) + percentAttackDamage;
    }

    /**
     * Percent spell block mod
     *
     * @return percent spell block mod
     */
    public double getPercentSpellBlockMod() {
        return super.getDouble(data.getPercentSpellBlockMod());
    }

    /**
     * Percent spell vamp mod
     *
     * @return percent spell vamp mod
     */
    public double getPercentSpellVampMod() {
        return super.getDouble(data.getPercentSpellVampMod()) + percentSpellVamp;
    }

    /**
     * R flat armor mod per level
     *
     * @return r flat armor mod per level
     */
    public double getRFlatArmorModPerLevel() {
        return super.getDouble(data.getrFlatArmorModPerLevel());
    }

    /**
     * R flat armor penetration mod
     *
     * @return r flat armor penetration mod
     */
    public double getRFlatArmorPenetrationMod() {
        return super.getDouble(data.getrFlatArmorPenetrationMod());
    }

    /**
     * R flat armor penetration mod per level
     *
     * @return r flat armor penetration mod per level
     */
    public double getRFlatArmorPenetrationModPerLevel() {
        return super.getDouble(data.getrFlatArmorPenetrationModPerLevel());
    }

    /**
     * R flat crit chance mod per level
     *
     * @return r flat crit chance mod per level
     */
    public double getRFlatCritChanceModPerLevel() {
        return super.getDouble(data.getrFlatCritChanceModPerLevel());
    }

    /**
     * R flat crit damage mod per level
     *
     * @return r flat crit damage mod per level
     */
    public double getRFlatCritDamageModPerLevel() {
        return super.getDouble(data.getrFlatCritDamageModPerLevel());
    }

    /**
     * R flat dodge mod
     *
     * @return r flat dodge mod
     */
    public double getRFlatDodgeMod() {
        return super.getDouble(data.getrFlatDodgeMod());
    }

    /**
     * R flat dodge mod per level
     *
     * @return r flat dodge mod per level
     */
    public double getRFlatDodgeModPerLevel() {
        return super.getDouble(data.getrFlatDodgeModPerLevel());
    }

    /**
     * R flat energy mod per level
     *
     * @return r flat energy mod per level
     */
    public double getRFlatEnergyModPerLevel() {
        return super.getDouble(data.getrFlatEnergyModPerLevel());
    }

    /**
     * R flat energy regen mod per level
     *
     * @return r flat energy regen mod per level
     */
    public double getRFlatEnergyRegenModPerLevel() {
        return super.getDouble(data.getrFlatEnergyRegenModPerLevel());
    }

    /**
     * R flat gold per 10 mod
     *
     * @return r flat gold per 10 mod
     */
    public double getRFlatGoldPer10Mod() {
        return super.getDouble(data.getrFlatGoldPer10Mod());
    }

    /**
     * R flat HP mod per level
     *
     * @return r flat HP mod per level
     */
    public double getRFlatHPModPerLevel() {
        return super.getDouble(data.getrFlatHPModPerLevel());
    }

    /**
     * R flat HP regen mod per level
     *
     * @return r flat HP regen mod per level
     */
    public double getRFlatHPRegenModPerLevel() {
        return super.getDouble(data.getrFlatHPRegenModPerLevel());
    }

    /**
     * R flat magic damage mod per level
     *
     * @return r flat magic damage mod per level
     */
    public double getRFlatMagicDamageModPerLevel() {
        return super.getDouble(data.getrFlatMagicDamageModPerLevel());
    }

    /**
     * R flat magic penetration mod
     *
     * @return r flat magic penetration mod
     */
    public double getRFlatMagicPenetrationMod() {
        return super.getDouble(data.getrFlatMagicPenetrationMod());
    }

    /**
     * R flat magic penetration mod per level
     *
     * @return r flat magic penetration mod per level
     */
    public double getRFlatMagicPenetrationModPerLevel() {
        return super.getDouble(data.getrFlatMagicPenetrationModPerLevel());
    }

    /**
     * R flat movement speed mod per level
     *
     * @return r flat movement speed mod per level
     */
    public double getRFlatMovementSpeedModPerLevel() {
        return super.getDouble(data.getrFlatMovementSpeedModPerLevel());
    }

    /**
     * R flat MP mod per level
     *
     * @return r flat MP mod per level
     */
    public double getRFlatMPModPerLevel() {
        return super.getDouble(data.getrFlatMPModPerLevel());
    }

    /**
     * R flat MP regen mod per level
     *
     * @return r flat MP regen mod per level
     */
    public double getRFlatMPRegenModPerLevel() {
        return super.getDouble(data.getrFlatMPRegenModPerLevel());
    }

    /**
     * R flat physical damage mod per level
     *
     * @return r flat physical damage mod per level
     */
    public double getRFlatPhysicalDamageModPerLevel() {
        return super.getDouble(data.getrFlatPhysicalDamageModPerLevel());
    }

    /**
     * R flat spell block mod per level
     *
     * @return r flat spell block mod per level
     */
    public double getRFlatSpellBlockModPerLevel() {
        return super.getDouble(data.getrFlatSpellBlockModPerLevel());
    }

    /**
     * R flat time dead mod
     *
     * @return r flat time dead mod
     */
    public double getRFlatTimeDeadMod() {
        return Math.abs(super.getDouble(data.getrFlatTimeDeadMod()));
    }

    /**
     * R flat time dead mod per level
     *
     * @return r flat time dead mod per level
     */
    public double getRFlatTimeDeadModPerLevel() {
        return Math.abs(super.getDouble(data.getrFlatTimeDeadModPerLevel()));
    }

    /**
     * R percent armor penetration mod
     *
     * @return r percent armor penetration mod
     */
    public double getRPercentArmorPenetrationMod() {
        return super.getDouble(data.getrPercentArmorPenetrationMod());
    }

    /**
     * R percent armor penetration mod per level
     *
     * @return r percent armor penetration mod per level
     */
    public double getRPercentArmorPenetrationModPerLevel() {
        return super.getDouble(data.getrPercentArmorPenetrationModPerLevel());
    }

    /**
     * R percent attack speed mod per level
     *
     * @return r percent attack speed mod per level
     */
    public double getRPercentAttackSpeedModPerLevel() {
        return super.getDouble(data.getrPercentAttackSpeedModPerLevel());
    }

    /**
     * R percent cooldown mod
     *
     * @return r percent cooldown mod
     */
    public double getRPercentCooldownMod() {
        return Math.abs(super.getDouble(data.getrPercentCooldownMod()));
    }

    /**
     * R percent cooldown mod per level
     *
     * @return r percent cooldown mod per level
     */
    public double getRPercentCooldownModPerLevel() {
        return Math.abs(super.getDouble(data.getrPercentCooldownModPerLevel()));
    }

    /**
     * R percent magic penetration mod
     *
     * @return r percent magic penetration mod
     */
    public double getRPercentMagicPenetrationMod() {
        return super.getDouble(data.getrPercentMagicPenetrationMod());
    }

    /**
     * R percent magic penetration mod per level
     *
     * @return r percent magic penetration mod per level
     */
    public double getRPercentMagicPenetrationModPerLevel() {
        return super.getDouble(data.getrPercentMagicPenetrationModPerLevel());
    }

    /**
     * R percent movement speed mod per level
     *
     * @return r percent movement speed mod per level
     */
    public double getRPercentMovementSpeedModPerLevel() {
        return super.getDouble(data.getrPercentMovementSpeedModPerLevel());
    }

    /**
     * R percent time dead mod
     *
     * @return r percent time dead mod
     */
    public double getRPercentTimeDeadMod() {
        return Math.abs(super.getDouble(data.getrPercentTimeDeadMod()));
    }

    /**
     * R percent time dead mod per level
     *
     * @return r percent time dead mod per level
     */
    public double getRPercentTimeDeadModPerLevel() {
        return Math.abs(super.getDouble(data.getrPercentTimeDeadModPerLevel()));
    }

    /**
     * Tenacity
     *
     * @return tenacity
     */
    public double getTenacity() {
        return tenacity;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BasicDataStats";
    }

}
