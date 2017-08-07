package com.merakianalytics.orianna.types.dto.staticdata;

import java.util.List;

import com.merakianalytics.orianna.types.dto.DataObject;

public class ChampionSpell extends DataObject {
    private static final long serialVersionUID = -4120852259618975907L;
    private List<Image> altImages;
    private List<Double> cooldown;
    private String cooldownBurn, resource, costType, sanitizedDescription, sanitizedTooltip, tooltip, costBurn, rangeBurn, key, description, name;
    private List<Integer> cost, range;
    private List<List<Double>> effect;
    private List<String> effectBurn;
    private Image image;
    private LevelTip leveltip;
    private int maxrank;
    private List<SpellVars> vars;

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
        final ChampionSpell other = (ChampionSpell)obj;
        if(altImages == null) {
            if(other.altImages != null) {
                return false;
            }
        } else if(!altImages.equals(other.altImages)) {
            return false;
        }
        if(cooldown == null) {
            if(other.cooldown != null) {
                return false;
            }
        } else if(!cooldown.equals(other.cooldown)) {
            return false;
        }
        if(cooldownBurn == null) {
            if(other.cooldownBurn != null) {
                return false;
            }
        } else if(!cooldownBurn.equals(other.cooldownBurn)) {
            return false;
        }
        if(cost == null) {
            if(other.cost != null) {
                return false;
            }
        } else if(!cost.equals(other.cost)) {
            return false;
        }
        if(costBurn == null) {
            if(other.costBurn != null) {
                return false;
            }
        } else if(!costBurn.equals(other.costBurn)) {
            return false;
        }
        if(costType == null) {
            if(other.costType != null) {
                return false;
            }
        } else if(!costType.equals(other.costType)) {
            return false;
        }
        if(description == null) {
            if(other.description != null) {
                return false;
            }
        } else if(!description.equals(other.description)) {
            return false;
        }
        if(effect == null) {
            if(other.effect != null) {
                return false;
            }
        } else if(!effect.equals(other.effect)) {
            return false;
        }
        if(effectBurn == null) {
            if(other.effectBurn != null) {
                return false;
            }
        } else if(!effectBurn.equals(other.effectBurn)) {
            return false;
        }
        if(image == null) {
            if(other.image != null) {
                return false;
            }
        } else if(!image.equals(other.image)) {
            return false;
        }
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        } else if(!key.equals(other.key)) {
            return false;
        }
        if(leveltip == null) {
            if(other.leveltip != null) {
                return false;
            }
        } else if(!leveltip.equals(other.leveltip)) {
            return false;
        }
        if(maxrank != other.maxrank) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(range == null) {
            if(other.range != null) {
                return false;
            }
        } else if(!range.equals(other.range)) {
            return false;
        }
        if(rangeBurn == null) {
            if(other.rangeBurn != null) {
                return false;
            }
        } else if(!rangeBurn.equals(other.rangeBurn)) {
            return false;
        }
        if(resource == null) {
            if(other.resource != null) {
                return false;
            }
        } else if(!resource.equals(other.resource)) {
            return false;
        }
        if(sanitizedDescription == null) {
            if(other.sanitizedDescription != null) {
                return false;
            }
        } else if(!sanitizedDescription.equals(other.sanitizedDescription)) {
            return false;
        }
        if(sanitizedTooltip == null) {
            if(other.sanitizedTooltip != null) {
                return false;
            }
        } else if(!sanitizedTooltip.equals(other.sanitizedTooltip)) {
            return false;
        }
        if(tooltip == null) {
            if(other.tooltip != null) {
                return false;
            }
        } else if(!tooltip.equals(other.tooltip)) {
            return false;
        }
        if(vars == null) {
            if(other.vars != null) {
                return false;
            }
        } else if(!vars.equals(other.vars)) {
            return false;
        }
        return true;
    }

    /**
     * @return the altImages
     */
    public List<Image> getAltImages() {
        return altImages;
    }

    /**
     * @return the cooldown
     */
    public List<Double> getCooldown() {
        return cooldown;
    }

    /**
     * @return the cooldownBurn
     */
    public String getCooldownBurn() {
        return cooldownBurn;
    }

    /**
     * @return the cost
     */
    public List<Integer> getCost() {
        return cost;
    }

    /**
     * @return the costBurn
     */
    public String getCostBurn() {
        return costBurn;
    }

    /**
     * @return the costType
     */
    public String getCostType() {
        return costType;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the effect
     */
    public List<List<Double>> getEffect() {
        return effect;
    }

    /**
     * @return the effectBurn
     */
    public List<String> getEffectBurn() {
        return effectBurn;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the leveltip
     */
    public LevelTip getLeveltip() {
        return leveltip;
    }

    /**
     * @return the maxrank
     */
    public int getMaxrank() {
        return maxrank;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the range
     */
    public List<Integer> getRange() {
        return range;
    }

    /**
     * @return the rangeBurn
     */
    public String getRangeBurn() {
        return rangeBurn;
    }

    /**
     * @return the resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * @return the sanitizedDescription
     */
    public String getSanitizedDescription() {
        return sanitizedDescription;
    }

    /**
     * @return the sanitizedTooltip
     */
    public String getSanitizedTooltip() {
        return sanitizedTooltip;
    }

    /**
     * @return the tooltip
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * @return the vars
     */
    public List<SpellVars> getVars() {
        return vars;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (altImages == null ? 0 : altImages.hashCode());
        result = prime * result + (cooldown == null ? 0 : cooldown.hashCode());
        result = prime * result + (cooldownBurn == null ? 0 : cooldownBurn.hashCode());
        result = prime * result + (cost == null ? 0 : cost.hashCode());
        result = prime * result + (costBurn == null ? 0 : costBurn.hashCode());
        result = prime * result + (costType == null ? 0 : costType.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (effect == null ? 0 : effect.hashCode());
        result = prime * result + (effectBurn == null ? 0 : effectBurn.hashCode());
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (key == null ? 0 : key.hashCode());
        result = prime * result + (leveltip == null ? 0 : leveltip.hashCode());
        result = prime * result + maxrank;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (range == null ? 0 : range.hashCode());
        result = prime * result + (rangeBurn == null ? 0 : rangeBurn.hashCode());
        result = prime * result + (resource == null ? 0 : resource.hashCode());
        result = prime * result + (sanitizedDescription == null ? 0 : sanitizedDescription.hashCode());
        result = prime * result + (sanitizedTooltip == null ? 0 : sanitizedTooltip.hashCode());
        result = prime * result + (tooltip == null ? 0 : tooltip.hashCode());
        result = prime * result + (vars == null ? 0 : vars.hashCode());
        return result;
    }

    /**
     * @param altImages
     *        the altImages to set
     */
    public void setAltImages(final List<Image> altImages) {
        this.altImages = altImages;
    }

    /**
     * @param cooldown
     *        the cooldown to set
     */
    public void setCooldown(final List<Double> cooldown) {
        this.cooldown = cooldown;
    }

    /**
     * @param cooldownBurn
     *        the cooldownBurn to set
     */
    public void setCooldownBurn(final String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    /**
     * @param cost
     *        the cost to set
     */
    public void setCost(final List<Integer> cost) {
        this.cost = cost;
    }

    /**
     * @param costBurn
     *        the costBurn to set
     */
    public void setCostBurn(final String costBurn) {
        this.costBurn = costBurn;
    }

    /**
     * @param costType
     *        the costType to set
     */
    public void setCostType(final String costType) {
        this.costType = costType;
    }

    /**
     * @param description
     *        the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param effect
     *        the effect to set
     */
    public void setEffect(final List<List<Double>> effect) {
        this.effect = effect;
    }

    /**
     * @param effectBurn
     *        the effectBurn to set
     */
    public void setEffectBurn(final List<String> effectBurn) {
        this.effectBurn = effectBurn;
    }

    /**
     * @param image
     *        the image to set
     */
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * @param key
     *        the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @param leveltip
     *        the leveltip to set
     */
    public void setLeveltip(final LevelTip leveltip) {
        this.leveltip = leveltip;
    }

    /**
     * @param maxrank
     *        the maxrank to set
     */
    public void setMaxrank(final int maxrank) {
        this.maxrank = maxrank;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param range
     *        the range to set
     */
    public void setRange(final List<Integer> range) {
        this.range = range;
    }

    /**
     * @param rangeBurn
     *        the rangeBurn to set
     */
    public void setRangeBurn(final String rangeBurn) {
        this.rangeBurn = rangeBurn;
    }

    /**
     * @param resource
     *        the resource to set
     */
    public void setResource(final String resource) {
        this.resource = resource;
    }

    /**
     * @param sanitizedDescription
     *        the sanitizedDescription to set
     */
    public void setSanitizedDescription(final String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    /**
     * @param sanitizedTooltip
     *        the sanitizedTooltip to set
     */
    public void setSanitizedTooltip(final String sanitizedTooltip) {
        this.sanitizedTooltip = sanitizedTooltip;
    }

    /**
     * @param tooltip
     *        the tooltip to set
     */
    public void setTooltip(final String tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * @param vars
     *        the vars to set
     */
    public void setVars(final List<SpellVars> vars) {
        this.vars = vars;
    }
}
