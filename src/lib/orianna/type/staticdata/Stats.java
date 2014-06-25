package lib.orianna.type.staticdata;

import java.io.Serializable;

public class Stats implements Serializable {
    private static final long serialVersionUID = 8261789180245619L;
    public final Double armor, armorPerLevel, attackDamage, attackDamagePerLevel, attackRange, attackSpeedOffset, attackSpeedPerLevel, crit, critPerLevel, HP,
            HPPerLevel, HPRegen, HPRegenPerLevel, moveSpeed, MP, MPPerLevel, MPRegen, MPRegenPerLevel, spellBlock, spellBlockPerLevel;

    public Stats(final Double armor, final Double armorPerLevel, final Double attackDamage, final Double attackDamagePerLevel, final Double attackRange,
            final Double attackSpeedOffset, final Double attackSpeedPerLevel, final Double crit, final Double critPerLevel, final Double HP,
            final Double HPPerLevel, final Double HPRegen, final Double HPRegenPerLevel, final Double moveSpeed, final Double MP, final Double MPPerLevel,
            final Double MPRegen, final Double MPRegenPerLevel, final Double spellBlock, final Double spellBlockPerLevel) {
        this.armor = armor;
        this.armorPerLevel = armorPerLevel;
        this.attackDamage = attackDamage;
        this.attackDamagePerLevel = attackDamagePerLevel;
        this.attackRange = attackRange;
        this.attackSpeedOffset = attackSpeedOffset;
        this.attackSpeedPerLevel = attackSpeedPerLevel;
        this.crit = crit;
        this.critPerLevel = critPerLevel;
        this.HP = HP;
        this.HPPerLevel = HPPerLevel;
        this.HPRegen = HPRegen;
        this.HPRegenPerLevel = HPRegenPerLevel;
        this.moveSpeed = moveSpeed;
        this.MP = MP;
        this.MPPerLevel = MPPerLevel;
        this.MPRegen = MPRegen;
        this.MPRegenPerLevel = MPRegenPerLevel;
        this.spellBlock = spellBlock;
        this.spellBlockPerLevel = spellBlockPerLevel;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Stats)) {
            return false;
        }
        final Stats other = (Stats)obj;
        if(HP == null) {
            if(other.HP != null) {
                return false;
            }
        }
        else if(!HP.equals(other.HP)) {
            return false;
        }
        if(HPPerLevel == null) {
            if(other.HPPerLevel != null) {
                return false;
            }
        }
        else if(!HPPerLevel.equals(other.HPPerLevel)) {
            return false;
        }
        if(HPRegen == null) {
            if(other.HPRegen != null) {
                return false;
            }
        }
        else if(!HPRegen.equals(other.HPRegen)) {
            return false;
        }
        if(HPRegenPerLevel == null) {
            if(other.HPRegenPerLevel != null) {
                return false;
            }
        }
        else if(!HPRegenPerLevel.equals(other.HPRegenPerLevel)) {
            return false;
        }
        if(MP == null) {
            if(other.MP != null) {
                return false;
            }
        }
        else if(!MP.equals(other.MP)) {
            return false;
        }
        if(MPPerLevel == null) {
            if(other.MPPerLevel != null) {
                return false;
            }
        }
        else if(!MPPerLevel.equals(other.MPPerLevel)) {
            return false;
        }
        if(MPRegen == null) {
            if(other.MPRegen != null) {
                return false;
            }
        }
        else if(!MPRegen.equals(other.MPRegen)) {
            return false;
        }
        if(MPRegenPerLevel == null) {
            if(other.MPRegenPerLevel != null) {
                return false;
            }
        }
        else if(!MPRegenPerLevel.equals(other.MPRegenPerLevel)) {
            return false;
        }
        if(armor == null) {
            if(other.armor != null) {
                return false;
            }
        }
        else if(!armor.equals(other.armor)) {
            return false;
        }
        if(armorPerLevel == null) {
            if(other.armorPerLevel != null) {
                return false;
            }
        }
        else if(!armorPerLevel.equals(other.armorPerLevel)) {
            return false;
        }
        if(attackDamage == null) {
            if(other.attackDamage != null) {
                return false;
            }
        }
        else if(!attackDamage.equals(other.attackDamage)) {
            return false;
        }
        if(attackDamagePerLevel == null) {
            if(other.attackDamagePerLevel != null) {
                return false;
            }
        }
        else if(!attackDamagePerLevel.equals(other.attackDamagePerLevel)) {
            return false;
        }
        if(attackRange == null) {
            if(other.attackRange != null) {
                return false;
            }
        }
        else if(!attackRange.equals(other.attackRange)) {
            return false;
        }
        if(attackSpeedOffset == null) {
            if(other.attackSpeedOffset != null) {
                return false;
            }
        }
        else if(!attackSpeedOffset.equals(other.attackSpeedOffset)) {
            return false;
        }
        if(attackSpeedPerLevel == null) {
            if(other.attackSpeedPerLevel != null) {
                return false;
            }
        }
        else if(!attackSpeedPerLevel.equals(other.attackSpeedPerLevel)) {
            return false;
        }
        if(crit == null) {
            if(other.crit != null) {
                return false;
            }
        }
        else if(!crit.equals(other.crit)) {
            return false;
        }
        if(critPerLevel == null) {
            if(other.critPerLevel != null) {
                return false;
            }
        }
        else if(!critPerLevel.equals(other.critPerLevel)) {
            return false;
        }
        if(moveSpeed == null) {
            if(other.moveSpeed != null) {
                return false;
            }
        }
        else if(!moveSpeed.equals(other.moveSpeed)) {
            return false;
        }
        if(spellBlock == null) {
            if(other.spellBlock != null) {
                return false;
            }
        }
        else if(!spellBlock.equals(other.spellBlock)) {
            return false;
        }
        if(spellBlockPerLevel == null) {
            if(other.spellBlockPerLevel != null) {
                return false;
            }
        }
        else if(!spellBlockPerLevel.equals(other.spellBlockPerLevel)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (HP == null ? 0 : HP.hashCode());
        result = prime * result + (HPPerLevel == null ? 0 : HPPerLevel.hashCode());
        result = prime * result + (HPRegen == null ? 0 : HPRegen.hashCode());
        result = prime * result + (HPRegenPerLevel == null ? 0 : HPRegenPerLevel.hashCode());
        result = prime * result + (MP == null ? 0 : MP.hashCode());
        result = prime * result + (MPPerLevel == null ? 0 : MPPerLevel.hashCode());
        result = prime * result + (MPRegen == null ? 0 : MPRegen.hashCode());
        result = prime * result + (MPRegenPerLevel == null ? 0 : MPRegenPerLevel.hashCode());
        result = prime * result + (armor == null ? 0 : armor.hashCode());
        result = prime * result + (armorPerLevel == null ? 0 : armorPerLevel.hashCode());
        result = prime * result + (attackDamage == null ? 0 : attackDamage.hashCode());
        result = prime * result + (attackDamagePerLevel == null ? 0 : attackDamagePerLevel.hashCode());
        result = prime * result + (attackRange == null ? 0 : attackRange.hashCode());
        result = prime * result + (attackSpeedOffset == null ? 0 : attackSpeedOffset.hashCode());
        result = prime * result + (attackSpeedPerLevel == null ? 0 : attackSpeedPerLevel.hashCode());
        result = prime * result + (crit == null ? 0 : crit.hashCode());
        result = prime * result + (critPerLevel == null ? 0 : critPerLevel.hashCode());
        result = prime * result + (moveSpeed == null ? 0 : moveSpeed.hashCode());
        result = prime * result + (spellBlock == null ? 0 : spellBlock.hashCode());
        result = prime * result + (spellBlockPerLevel == null ? 0 : spellBlockPerLevel.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Stats";
    }
}
