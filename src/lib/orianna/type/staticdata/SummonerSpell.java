package lib.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

public class SummonerSpell implements Serializable {
    private static final long serialVersionUID = 6739867294753097546L;
    public final List<Double> cooldown;
    public final String cooldownBurn, costBurn, costType, description, key, name, rangeBurn, resource, sanitizedDescription, sanitizedTooltip, tooltip;
    public final List<Integer> cost, range;
    public final List<List<Double>> effect;
    public final List<String> effectBurn, modes;
    public final Integer ID, maxRank, summonerLevel;
    public final Image image;
    public final LevelTip levelTip;
    public final List<SpellVariables> vars;

    public SummonerSpell(final String cooldownBurn, final String costBurn, final String costType, final String description, final String key,
            final String name, final String rangeBurn, final String resource, final String sanitizedDescription, final String sanitizedTooltip,
            final String tooltip, final List<Double> cooldown, final List<Integer> cost, final List<Integer> range, final List<List<Double>> effect,
            final List<String> effectBurn, final List<String> modes, final Integer ID, final Integer maxRank, final Integer summonerLevel, final Image image,
            final LevelTip levelTip, final List<SpellVariables> vars) {
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
        this.cooldown = cooldown;
        this.cost = cost;
        this.range = range;
        this.effect = effect;
        this.effectBurn = effectBurn;
        this.modes = modes;
        this.ID = ID;
        this.maxRank = maxRank;
        this.summonerLevel = summonerLevel;
        this.image = image;
        this.levelTip = levelTip;
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
        if(!(obj instanceof SummonerSpell)) {
            return false;
        }
        final SummonerSpell other = (SummonerSpell)obj;
        if(ID == null) {
            if(other.ID != null) {
                return false;
            }
        }
        else if(!ID.equals(other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ID == null ? 0 : ID.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
