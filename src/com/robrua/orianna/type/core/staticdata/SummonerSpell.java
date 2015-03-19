package com.robrua.orianna.type.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class SummonerSpell extends OriannaObject<com.robrua.orianna.type.dto.staticdata.SummonerSpell> {
    private static final long serialVersionUID = 1464517194317894609L;
    private List<Double> cooldown;
    private List<Integer> cost, range;
    private List<List<Double>> effect;
    private List<String> effectBurn, modes;
    private Image image;
    private LevelTip levelTip;
    private List<SpellVars> vars;

    /**
     * @param data
     *            the underlying dto
     */
    public SummonerSpell(final com.robrua.orianna.type.dto.staticdata.SummonerSpell data) {
        super(data, com.robrua.orianna.type.dto.staticdata.SummonerSpell.class);
    }

    /**
     * Cooldown
     *
     * @return cooldown
     */
    public List<Double> getCooldown() {
        if(cooldown == null) {
            cooldown = new ArrayList<Double>();
            cooldown.addAll(data.getCooldown());
        }

        return Collections.unmodifiableList(cooldown);
    }

    /**
     * Cooldown burn
     *
     * @return cooldown burn
     */
    public String getCooldownBurn() {
        return super.getString(data.getCooldownBurn());
    }

    /**
     * Cost
     *
     * @return cost
     */
    public List<Integer> getCost() {
        if(cost == null) {
            cost = new ArrayList<>();
            cost.addAll(data.getCost());
        }

        return Collections.unmodifiableList(cost);
    }

    /**
     * Cost burn
     *
     * @return cost burn
     */
    public String getCostBurn() {
        return super.getString(data.getCostBurn());
    }

    /**
     * Cost type
     *
     * @return cost type
     */
    public String getCostType() {
        return super.getString(data.getCostType());
    }

    /**
     * Description
     *
     * @return description
     */
    public String getDescription() {
        return super.getString(data.getDescription());
    }

    /**
     * Effect
     *
     * @return effect
     */
    public List<List<Double>> getEffect() {
        if(effect == null) {
            effect = new ArrayList<>();
            for(final List<Double> e : data.getEffect()) {
                effect.add(Collections.unmodifiableList(e));
            }
        }

        return Collections.unmodifiableList(effect);
    }

    /**
     * Effect burn
     *
     * @return effect burn
     */
    public List<String> getEffectBurn() {
        if(effectBurn == null) {
            effectBurn = new ArrayList<>();
            effectBurn.addAll(data.getEffectBurn());
        }

        return Collections.unmodifiableList(effectBurn);
    }

    /**
     * ID
     *
     * @return id
     */
    public long getID() {
        return super.getInteger(data.getId());
    }

    /**
     * Image
     *
     * @return image
     */
    public Image getImage() {
        if(image == null) {
            image = new Image(data.getImage());
        }

        return image;
    }

    /**
     * Key
     *
     * @return key
     */
    public String getKey() {
        return super.getString(data.getKey());
    }

    /**
     * Level tip
     *
     * @return level tip
     */
    public LevelTip getLevelTip() {
        if(levelTip == null) {
            levelTip = new LevelTip(data.getLeveltip());
        }

        return levelTip;
    }

    /**
     * Max rank
     *
     * @return max rank
     */
    public int getMaxRank() {
        return super.getInteger(data.getMaxrank());
    }

    /**
     * Modes
     *
     * @return modes
     */
    public List<String> getModes() {
        if(modes == null) {
            modes = new ArrayList<>();
            modes.addAll(data.getModes());
        }

        return Collections.unmodifiableList(modes);
    }

    /**
     * Name
     *
     * @return name
     */
    public String getName() {
        return super.getString(data.getName());
    }

    /**
     * Range
     *
     * @return range or null if this spell targets self
     */
    public List<Integer> getRange() {
        if(range == null) {
            if(data.getRange() == null) {
                return null;
            }

            range = new ArrayList<>();
            range.addAll(data.getRange());
        }

        return Collections.unmodifiableList(range);
    }

    /**
     * Range burn
     *
     * @return range burn
     */
    public String getRangeBurn() {
        return super.getString(data.getRangeBurn());
    }

    /**
     * Resource
     *
     * @return resource
     */
    public String getResource() {
        return super.getString(data.getResource());
    }

    /**
     * Sanitized description
     *
     * @return sanitized description
     */
    public String getSanitizedDescription() {
        return super.getString(data.getSanitizedDescription());
    }

    /**
     * Sanitized tooltip
     *
     * @return sanitized tooltip
     */
    public String getSanitizedTooltip() {
        return super.getString(data.getSanitizedTooltip());
    }

    /**
     * Replaces the variables in the sanitized tooltip with numerical values
     *
     * @param championLevel
     *            the champion level to get the specific sanitized tooltip for
     * @return the sanitized tooltip with numerical values
     */
    public String getSanitizedTooltip(final int championLevel) {
        return replaceVariables(getSanitizedTooltip(), championLevel);
    }

    /**
     * Spell vars
     *
     * @return spell vars
     */
    public List<SpellVars> getSpellVars() {
        if(vars == null) {
            vars = new ArrayList<>();
            if(data.getVars() != null) {
                for(final com.robrua.orianna.type.dto.staticdata.SpellVars v : data.getVars()) {
                    vars.add(new SpellVars(v));
                }
            }
        }

        return Collections.unmodifiableList(vars);
    }

    /**
     * Summoner level required
     *
     * @return summoner level required
     */
    public int getSummonerLevel() {
        return super.getInteger(data.getSummonerLevel());
    }

    /**
     * Tooltip
     *
     * @return tooltip
     */
    public String getTooltip() {
        return super.getString(data.getTooltip());
    }

    /**
     * Replaces the variables in the tooltip with numerical values
     *
     * @param championLevel
     *            the champion level to get the specific tooltip for
     * @return the tooltip with numerical values
     */
    public String getTooltip(final int championLevel) {
        return replaceVariables(getTooltip(), championLevel);
    }

    private String replaceVariables(String text, final int level) {
        if(level < 1 || level > 18) {
            throw new IllegalArgumentException("Not a valid champion level!");
        }

        for(final SpellVars var : getSpellVars()) {
            final Double val = var.getLink().equals("@player.level") ? var.getCoeffs().get(level - 1) : var.getCoeffs().get(0);
            text = text.replaceAll("\\{\\{ " + var.getKey() + " \\}\\}", val.toString());
        }
        return text;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getName();
    }
}
