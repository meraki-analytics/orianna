package lib.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

public class ChampionSpell implements Serializable {
    private static final long serialVersionUID = 2916541967904381781L;
    public final List<Image> altImages;
    public final List<Double> cooldown;
    public final String cooldownBurn, costBurn, costType, description, key, name, rangeBurn, resource, sanitizedDescription, sanitizedTooltip, tooltip;
    public final List<Integer> cost, range;
    public final List<List<Integer>> effect;
    public final List<String> effectBurn;
    public final Image image;
    public final LevelTip levelTip;
    public final Integer maxRank;
    public final List<SpellVariables> vars;

    public ChampionSpell(final List<Image> altImages, final List<Double> cooldown, final String cooldownBurn, final String costBurn, final String costType,
            final String description, final String key, final String name, final String rangeBurn, final String resource, final String sanitizedDescription,
            final String sanitizedTooltip, final String tooltip, final List<Integer> cost, final List<Integer> range, final List<List<Integer>> effect,
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (key == null ? 0 : key.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
