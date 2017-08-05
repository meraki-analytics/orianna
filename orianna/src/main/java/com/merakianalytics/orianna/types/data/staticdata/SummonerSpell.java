package com.merakianalytics.orianna.types.data.staticdata;

import java.util.List;
import java.util.Set;

import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.CoreData;

public class SummonerSpell extends CoreData {
    private static final long serialVersionUID = 2110625692594925345L;
    private List<Double> cooldowns;
    private List<Integer> costs, ranges;
    private List<List<Double>> effects;
    private int id, maxRank, summonerLevelRequirement;
    private Image image;
    private Set<String> includedData;
    private List<String> levelUpKeywords, levelUpEffects;
    private Set<GameMode> modes;
    private Platform platform;
    private String tooltip, description, key, resourceDescription, name, resource, sanitizedDescription, sanitizedTooltip, version, locale;
    private List<SpellVariables> variables;

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
        final SummonerSpell other = (SummonerSpell)obj;
        if(cooldowns == null) {
            if(other.cooldowns != null) {
                return false;
            }
        } else if(!cooldowns.equals(other.cooldowns)) {
            return false;
        }
        if(costs == null) {
            if(other.costs != null) {
                return false;
            }
        } else if(!costs.equals(other.costs)) {
            return false;
        }
        if(description == null) {
            if(other.description != null) {
                return false;
            }
        } else if(!description.equals(other.description)) {
            return false;
        }
        if(effects == null) {
            if(other.effects != null) {
                return false;
            }
        } else if(!effects.equals(other.effects)) {
            return false;
        }
        if(id != other.id) {
            return false;
        }
        if(image == null) {
            if(other.image != null) {
                return false;
            }
        } else if(!image.equals(other.image)) {
            return false;
        }
        if(includedData == null) {
            if(other.includedData != null) {
                return false;
            }
        } else if(!includedData.equals(other.includedData)) {
            return false;
        }
        if(key == null) {
            if(other.key != null) {
                return false;
            }
        } else if(!key.equals(other.key)) {
            return false;
        }
        if(levelUpEffects == null) {
            if(other.levelUpEffects != null) {
                return false;
            }
        } else if(!levelUpEffects.equals(other.levelUpEffects)) {
            return false;
        }
        if(levelUpKeywords == null) {
            if(other.levelUpKeywords != null) {
                return false;
            }
        } else if(!levelUpKeywords.equals(other.levelUpKeywords)) {
            return false;
        }
        if(locale == null) {
            if(other.locale != null) {
                return false;
            }
        } else if(!locale.equals(other.locale)) {
            return false;
        }
        if(maxRank != other.maxRank) {
            return false;
        }
        if(modes == null) {
            if(other.modes != null) {
                return false;
            }
        } else if(!modes.equals(other.modes)) {
            return false;
        }
        if(name == null) {
            if(other.name != null) {
                return false;
            }
        } else if(!name.equals(other.name)) {
            return false;
        }
        if(platform != other.platform) {
            return false;
        }
        if(ranges == null) {
            if(other.ranges != null) {
                return false;
            }
        } else if(!ranges.equals(other.ranges)) {
            return false;
        }
        if(resource == null) {
            if(other.resource != null) {
                return false;
            }
        } else if(!resource.equals(other.resource)) {
            return false;
        }
        if(resourceDescription == null) {
            if(other.resourceDescription != null) {
                return false;
            }
        } else if(!resourceDescription.equals(other.resourceDescription)) {
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
        if(summonerLevelRequirement != other.summonerLevelRequirement) {
            return false;
        }
        if(tooltip == null) {
            if(other.tooltip != null) {
                return false;
            }
        } else if(!tooltip.equals(other.tooltip)) {
            return false;
        }
        if(variables == null) {
            if(other.variables != null) {
                return false;
            }
        } else if(!variables.equals(other.variables)) {
            return false;
        }
        if(version == null) {
            if(other.version != null) {
                return false;
            }
        } else if(!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    /**
     * @return the cooldowns
     */
    public List<Double> getCooldowns() {
        return cooldowns;
    }

    /**
     * @return the costs
     */
    public List<Integer> getCosts() {
        return costs;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the effects
     */
    public List<List<Double>> getEffects() {
        return effects;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the includedData
     */
    public Set<String> getIncludedData() {
        return includedData;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the levelUpEffects
     */
    public List<String> getLevelUpEffects() {
        return levelUpEffects;
    }

    /**
     * @return the levelUpKeywords
     */
    public List<String> getLevelUpKeywords() {
        return levelUpKeywords;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @return the maxRank
     */
    public int getMaxRank() {
        return maxRank;
    }

    /**
     * @return the modes
     */
    public Set<GameMode> getModes() {
        return modes;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the ranges
     */
    public List<Integer> getRanges() {
        return ranges;
    }

    /**
     * @return the resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * @return the resourceDescription
     */
    public String getResourceDescription() {
        return resourceDescription;
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
     * @return the summonerLevelRequirement
     */
    public int getSummonerLevelRequirement() {
        return summonerLevelRequirement;
    }

    /**
     * @return the tooltip
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * @return the variables
     */
    public List<SpellVariables> getVariables() {
        return variables;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (cooldowns == null ? 0 : cooldowns.hashCode());
        result = prime * result + (costs == null ? 0 : costs.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (effects == null ? 0 : effects.hashCode());
        result = prime * result + id;
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (includedData == null ? 0 : includedData.hashCode());
        result = prime * result + (key == null ? 0 : key.hashCode());
        result = prime * result + (levelUpEffects == null ? 0 : levelUpEffects.hashCode());
        result = prime * result + (levelUpKeywords == null ? 0 : levelUpKeywords.hashCode());
        result = prime * result + (locale == null ? 0 : locale.hashCode());
        result = prime * result + maxRank;
        result = prime * result + (modes == null ? 0 : modes.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (ranges == null ? 0 : ranges.hashCode());
        result = prime * result + (resource == null ? 0 : resource.hashCode());
        result = prime * result + (resourceDescription == null ? 0 : resourceDescription.hashCode());
        result = prime * result + (sanitizedDescription == null ? 0 : sanitizedDescription.hashCode());
        result = prime * result + (sanitizedTooltip == null ? 0 : sanitizedTooltip.hashCode());
        result = prime * result + summonerLevelRequirement;
        result = prime * result + (tooltip == null ? 0 : tooltip.hashCode());
        result = prime * result + (variables == null ? 0 : variables.hashCode());
        result = prime * result + (version == null ? 0 : version.hashCode());
        return result;
    }

    /**
     * @param cooldowns
     *        the cooldowns to set
     */
    public void setCooldowns(final List<Double> cooldowns) {
        this.cooldowns = cooldowns;
    }

    /**
     * @param costs
     *        the costs to set
     */
    public void setCosts(final List<Integer> costs) {
        this.costs = costs;
    }

    /**
     * @param description
     *        the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param effects
     *        the effects to set
     */
    public void setEffects(final List<List<Double>> effects) {
        this.effects = effects;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param image
     *        the image to set
     */
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * @param includedData
     *        the includedData to set
     */
    public void setIncludedData(final Set<String> includedData) {
        this.includedData = includedData;
    }

    /**
     * @param key
     *        the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @param levelUpEffects
     *        the levelUpEffects to set
     */
    public void setLevelUpEffects(final List<String> levelUpEffects) {
        this.levelUpEffects = levelUpEffects;
    }

    /**
     * @param levelUpKeywords
     *        the levelUpKeywords to set
     */
    public void setLevelUpKeywords(final List<String> levelUpKeywords) {
        this.levelUpKeywords = levelUpKeywords;
    }

    /**
     * @param locale
     *        the locale to set
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * @param maxRank
     *        the maxRank to set
     */
    public void setMaxRank(final int maxRank) {
        this.maxRank = maxRank;
    }

    /**
     * @param modes
     *        the modes to set
     */
    public void setModes(final Set<GameMode> modes) {
        this.modes = modes;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param ranges
     *        the ranges to set
     */
    public void setRanges(final List<Integer> ranges) {
        this.ranges = ranges;
    }

    /**
     * @param resource
     *        the resource to set
     */
    public void setResource(final String resource) {
        this.resource = resource;
    }

    /**
     * @param resourceDescription
     *        the resourceDescription to set
     */
    public void setResourceDescription(final String resourceDescription) {
        this.resourceDescription = resourceDescription;
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
     * @param summonerLevelRequirement
     *        the summonerLevelRequirement to set
     */
    public void setSummonerLevelRequirement(final int summonerLevelRequirement) {
        this.summonerLevelRequirement = summonerLevelRequirement;
    }

    /**
     * @param tooltip
     *        the tooltip to set
     */
    public void setTooltip(final String tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * @param variables
     *        the variables to set
     */
    public void setVariables(final List<SpellVariables> variables) {
        this.variables = variables;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
