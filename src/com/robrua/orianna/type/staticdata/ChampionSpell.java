package com.robrua.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

public class ChampionSpell implements Serializable {
    private static final long serialVersionUID = 2916541967904381781L;
    public final List<Image> altImages;
    public final List<Double> cooldown;
    public final String cooldownBurn, costBurn, costType, description, key, name, rangeBurn, resource, sanitizedDescription, sanitizedTooltip, tooltip;
    public final List<Integer> cost, range;
    public final List<List<Double>> effect;
    public final List<String> effectBurn;
    public final Image image;
    public final LevelTip levelTip;
    public final Integer maxRank;
    public final List<SpellVariables> vars;

    public ChampionSpell(final List<Image> altImages, final List<Double> cooldown, final String cooldownBurn, final String costBurn, final String costType,
            final String description, final String key, final String name, final String rangeBurn, final String resource, final String sanitizedDescription,
            final String sanitizedTooltip, final String tooltip, final List<Integer> cost, final List<Integer> range, final List<List<Double>> effect,
            final List<String> effectBurn, final Image image, final LevelTip levelTip, final Integer maxRank, final List<SpellVariables> vars) {
        this.altImages = altImages;
        this.cooldown = cooldown;
        this.cooldownBurn = cooldownBurn;
        this.costBurn = costBurn;
        this.costType = costType;
        this.description = description;
        this.key = key;
        this.name = name;
        this.rangeBurn = rangeBurn;
        this.resource = resource;
        this.sanitizedDescription = sanitizedDescription;
        this.sanitizedTooltip = sanitizedTooltip;
        this.tooltip = tooltip;
        this.cost = cost;
        this.range = range;
        this.effect = effect;
        this.effectBurn = effectBurn;
        this.image = image;
        this.levelTip = levelTip;
        this.maxRank = maxRank;
        this.vars = vars;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof ChampionSpell)) {
            return false;
        }
        final ChampionSpell other = (ChampionSpell)obj;
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        }
        else if(!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    /**
     * Replaces the variables in the sanitized tooltip with numerical values
     *
     * @param championLevel
     *            the champion level to get the specific sanititzed tooltip for
     * @param spellLevel
     *            the spell rank to get the specific sanitized tooltip for
     * @return the sanitized tooltip with numerical values
     */
    public String getSanitizedTooltip(final int championLevel, final int spellLevel) {
        return replaceVariables(sanitizedTooltip, championLevel, spellLevel);
    }

    /**
     * Replaces the variables in the tooltip with numerical values
     *
     * @param championLevel
     *            the champion level to get the specific tooltip for
     * @param spellLevel
     *            the spell rank to get the specific tooltip for
     * @return the tooltip with numerical values
     */
    public String getTooltip(final int championLevel, final int spellLevel) {
        return replaceVariables(tooltip, championLevel, spellLevel);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (key == null ? 0 : key.hashCode());
        return result;
    }

    private String replaceVariables(String text, final int championLevel, final int spellLevel) {
        if(spellLevel < 1 || spellLevel > maxRank) {
            throw new IllegalArgumentException("Not a valid level for this spell!");
        }
        if(championLevel < 1 || championLevel > 18) {
            throw new IllegalArgumentException("Not a valid champion level!");
        }

        if(effect != null) {
            int i = 1;
            for(final List<Double> e : effect) {
                text = text.replaceAll("\\{\\{ e" + i++ + " \\}\\}", e.get(spellLevel - 1).toString());
            }
        }

        if(vars != null) {
            for(final SpellVariables var : vars) {
                Double val = var.coeff.get(0);
                if(var.coeff.size() == maxRank) {
                    val = var.coeff.get(spellLevel - 1);
                }
                else if(var.coeff.size() == 18) {
                    val = var.coeff.get(championLevel - 1);
                }
                text = text.replaceAll("\\{\\{ " + var.key + " \\}\\}", val.toString());
            }
        }

        return text;
    }

    @Override
    public String toString() {
        return name;
    }
}
