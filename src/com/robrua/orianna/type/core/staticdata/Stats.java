package com.robrua.orianna.type.core.staticdata;

import com.robrua.orianna.type.core.OriannaObject;

public class Stats extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Stats> {
    private static final long serialVersionUID = 5035984188285319160L;

    /**
     * @param data
     *            the underlying dto
     */
    public Stats(final com.robrua.orianna.type.dto.staticdata.Stats data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Stats.class);
    }

    /**
     * Armor
     *
     * @return armor
     */
    public double getArmor() {
        return super.getDouble(data.getArmor());
    }

    /**
     * Armor per level
     *
     * @return armor per level
     */
    public double getArmorPerLevel() {
        return super.getDouble(data.getArmorperlevel());
    }

    /**
     * Attack damage
     *
     * @return attack damage
     */
    public double getAttackDamage() {
        return super.getDouble(data.getAttackdamage());
    }

    /**
     * Attack damage per level
     *
     * @return attack damage per level
     */
    public double getAttackDamagePerLevel() {
        return super.getDouble(data.getAttackdamageperlevel());
    }

    /**
     * Attack range
     *
     * @return attack range
     */
    public double getAttackRange() {
        return super.getDouble(data.getAttackrange());
    }

    /**
     * Attack speed offset
     *
     * @return attack speed offset
     */
    public double getAttackSpeedOffset() {
        return super.getDouble(data.getAttackspeedoffset());
    }

    /**
     * Attack speed per level
     *
     * @return attack speed per level
     */
    public double getAttackSpeedPerLevel() {
        return super.getDouble(data.getAttackspeedperlevel());
    }

    /**
     * Crit
     *
     * @return crit
     */
    public double getCrit() {
        return super.getDouble(data.getCrit());
    }

    /**
     * Crit per level
     *
     * @return crit per level
     */
    public double getCritPerLevel() {
        return super.getDouble(data.getCritperlevel());
    }

    /**
     * HP
     *
     * @return HP
     */
    public double getHP() {
        return super.getDouble(data.getHp());
    }

    /**
     * HP per level
     *
     * @return HP per level
     */
    public double getHPPerLevel() {
        return super.getDouble(data.getHpperlevel());
    }

    /**
     * HP regen
     *
     * @return HP regen
     */
    public double getHPRegen() {
        return super.getDouble(data.getHpregen());
    }

    /**
     * HP regen per level
     *
     * @return HP regen per level
     */
    public double getHPRegenPerLevel() {
        return super.getDouble(data.getHpregenperlevel());
    }

    /**
     * Movespeed
     *
     * @return movespeed
     */
    public double getMovespeed() {
        return super.getDouble(data.getMovespeed());
    }

    /**
     * MP
     *
     * @return MP
     */
    public double getMP() {
        return super.getDouble(data.getMp());
    }

    /**
     * MP per level
     *
     * @return MP per level
     */
    public double getMPPerLevel() {
        return super.getDouble(data.getMpperlevel());
    }

    /**
     * MP regen
     *
     * @return MP regen
     */
    public double getMPRegen() {
        return super.getDouble(data.getMpregen());
    }

    /**
     * MP regen per level
     *
     * @return MP regen per level
     */
    public double getMPRegenPerLevel() {
        return super.getDouble(data.getMpregenperlevel());
    }

    /**
     * Spell block
     *
     * @return spell block
     */
    public double getSpellBlock() {
        return super.getDouble(data.getSpellblock());
    }

    /**
     * Spell block per level
     *
     * @return spell block per level
     */
    public double getSpellBlockPerLevel() {
        return super.getDouble(data.getSpellblockperlevel());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Stats";
    }

}
