package com.robrua.orianna.type.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class ChampionSpell extends OriannaObject<com.robrua.orianna.type.dto.staticdata.ChampionSpell> {
    private static final long serialVersionUID = -7519485867013847385L;
    private List<Image> altImages;
    private List<Double> cooldown;
    private List<Integer> cost, range;
    private List<List<Double>> effect;
    private List<String> effectBurn;
    private Image image;
    private LevelTip levelTip;
    private List<SpellVars> vars;

    /**
     * @param data
     *            the underlying dto
     */
    public ChampionSpell(final com.robrua.orianna.type.dto.staticdata.ChampionSpell data) {
        super(data, com.robrua.orianna.type.dto.staticdata.ChampionSpell.class);
    }

    /**
     * Alternate images
     *
     * @return alternate images
     */
    public List<Image> getAlternateImages() {
        if(altImages == null) {
            altImages = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.staticdata.Image img : data.getAltimages()) {
                altImages.add(new Image(img));
            }
        }

        return Collections.unmodifiableList(altImages);
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
            if(data.getEffect() != null) {
                for(final List<Double> e : data.getEffect()) {
                    effect.add(e == null ? null : Collections.unmodifiableList(e));
                }
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
     * @param champLevel
     *            the champion level to get the specific sanitized tooltip for
     * @param spellLevel
     *            the spell rank to get the specific sanitized tooltip for
     * @return the sanitized tooltip with numerical values
     */
    public String getSanitizedTooltip(final int champLevel, final int spellLevel) {
        return replaceVariables(getSanitizedTooltip(), champLevel, spellLevel);
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
     * @param champLevel
     *            the champion level to get the specific tooltip for
     * @param spellLevel
     *            the spell rank to get the specific tooltip for
     * @return the tooltip with numerical values
     */
    public String getTooltip(final int champLevel, final int spellLevel) {
        return replaceVariables(getTooltip(), champLevel, spellLevel);
    }

    /**
     * Replaces spell variables in description with values
     *
     * @param text
     *            the text to replace in
     * @param champLevel
     *            the champion level to get values for
     * @param spellLevel
     *            the spell level to get values for
     * @return the fixed text
     */
    private String replaceVariables(String text, final int champLevel, final int spellLevel) {
        final int maxRank = getMaxRank();
        if(spellLevel < 1 || spellLevel > maxRank) {
            throw new IllegalArgumentException("Not a valid level for this spell!");
        }
        if(champLevel < 1 || champLevel > 18) {
            throw new IllegalArgumentException("Not a valid champion level!");
        }

        int i = 1;
        for(final List<Double> e : getEffect()) {
            if(e == null) {
                continue;
            }

            text = text.replaceAll("\\{\\{ e" + i + " \\}\\}", e.get(spellLevel - 1).toString());
            i++;
        }

        for(final SpellVars var : getSpellVars()) {
            final List<Double> coeffs = var.getCoeffs();
            Double val = coeffs.get(0);
            if(coeffs.size() == maxRank) {
                val = coeffs.get(spellLevel - 1);
            }
            else if(coeffs.size() == 18) {
                val = coeffs.get(champLevel - 1);
            }
            String replacement = val.toString();

            final String link = var.getLink();
            if(link.equals("attackdamage")) {
                replacement += " AD";
            }
            else if(link.equals("spelldamage")) {
                replacement += " AP";
            }

            text = text.replaceAll("\\{\\{ " + var.getKey() + " \\}\\}", replacement);
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
