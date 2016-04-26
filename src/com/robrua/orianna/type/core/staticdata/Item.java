package com.robrua.orianna.type.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.exception.OriannaException;

public class Item extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Item> {
    private static final Map<String, Pattern> PATTERNS = getScrapedStatPatterns();
    private static final long serialVersionUID = 8240618175038660294L;

    /**
     * Patterns for scraping non-included stat data from item descriptions
     *
     * @return the patterns
     */
    private static Map<String, Pattern> getScrapedStatPatterns() {
        final Map<String, Pattern> patterns = new HashMap<String, Pattern>();
        patterns.put("percentAttackDamage", Pattern.compile("\\+(\\d+)% Base Attack Damage"));
        patterns.put("percentCooldownReduction", Pattern.compile("\\+(\\d+) *% Cooldown Reduction"));
        patterns.put("flatArmorPenetation", Pattern.compile("\\+(\\d+) <a href='FlatArmorPen'>Armor Penetration</a>"));
        patterns.put("percentAbilityPower", Pattern.compile("Increases Ability Power by (\\d+)%"));
        patterns.put("percentArmorPenetration", Pattern.compile("\\+(\\d+)% <a href='BonusArmorPen'>Bonus Armor Penetration</a>"));
        patterns.put("flatMagicPenetration", Pattern.compile("\\+(\\d+) <a href='FlatMagicPen'>Magic Penetration</a>"));
        patterns.put("percentMagicPenetration", Pattern.compile("\\+(\\d+)% <a href='TotalMagicPen'>Magic Penetration</a>"));
        patterns.put("goldPer10", Pattern.compile("\\+(\\d+) *Gold per 10 seconds"));
        patterns.put("percentLifeSteal", Pattern.compile("(?:Dealing physical damage heals for (\\d+)% of the damage dealt)|(?:\\+(\\d+)% Life Steal)"));
        patterns.put("percentSpellVamp",
                Pattern.compile("(?:\\+(\\d+)% <a href='SpellVamp'>Spell Vamp</a>)|(?:Your spells and abilities heal you for (\\d+)% of the damage dealt)"));
        patterns.put("percentManaRegen", Pattern.compile("\\+(\\d+)% Base Mana Regen"));
        patterns.put("percentHealthRegen", Pattern.compile("\\+(\\d+)% Base Health Regen"));
        patterns.put("percentBonusHealth", Pattern.compile("\\+(\\d+)% Bonus Health"));
        patterns.put("percentMovespeed", Pattern.compile("\\+(\\d+)% Movement Speed"));
        patterns.put("tenacity", Pattern
                .compile("Tenacity:</unique> Reduces the duration of stuns, slows, taunts, fears, silences, blinds, polymorphs, and immobilizes by (\\d+)%"));

        return patterns;
    }

    private Map<String, String> effect;
    private List<String> from, into, tags;
    private Gold gold;
    private Image image;
    private Map<String, Boolean> maps;
    private MetaData rune;

    private BasicDataStats stats;

    /**
     * @param data
     *            the underlying dto
     */
    public Item(final com.robrua.orianna.type.dto.staticdata.Item data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Item.class);
    }

    /**
     * Colloq
     *
     * @return colloq
     */
    public String getColloq() {
        return super.getString(data.getColloq());
    }

    /**
     * Consumed
     *
     * @return consumed
     */
    public boolean getConsumed() {
        return super.getBoolean(data.getConsumed());
    }

    /**
     * Consume on full
     *
     * @return consume on full
     */
    public boolean getConsumeOnFull() {
        return super.getBoolean(data.getConsumeOnFull());
    }

    /**
     * Depth
     *
     * @return depth
     */
    public int getDepth() {
        return super.getInteger(data.getDepth());
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
    public Map<String, String> getEffect() {
        if(effect == null) {
            effect = new HashMap<>();
            if(data.getEffect() != null) {
                effect.putAll(data.getEffect());
            }
        }

        return Collections.unmodifiableMap(effect);
    }

    /**
     * From
     *
     * @return from
     */
    public List<String> getFrom() {
        if(from == null) {
            from = new ArrayList<>();
            if(data.getFrom() != null) {
                from.addAll(data.getFrom());
            }
        }

        return Collections.unmodifiableList(from);
    }

    /**
     * Gold
     *
     * @return gold
     */
    public Gold getGold() {
        if(gold == null) {
            gold = new Gold(data.getGold());
        }

        return gold;
    }

    /**
     * Group
     *
     * @return group
     */
    public String getGroup() {
        return super.getString(data.getGroup());
    }

    /**
     * Hide from all
     *
     * @return hide from all
     */
    public boolean getHideFromAll() {
        return super.getBoolean(data.getHideFromAll());
    }

    /**
     * ID
     *
     * @return iD
     */
    public int getID() {
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
     * In store
     *
     * @return in store
     */
    public boolean getInStore() {
        return super.getBoolean(data.getInStore());
    }

    /**
     * Into
     *
     * @return into
     */
    public List<String> getInto() {
        if(into == null) {
            into = new ArrayList<>();
            if(data.getInto() != null) {
                into.addAll(data.getInto());
            }
        }

        return Collections.unmodifiableList(into);
    }

    /**
     * Maps
     *
     * @return maps
     */
    public Map<String, Boolean> getMaps() {
        if(maps == null) {
            maps = new HashMap<>();
            maps.putAll(data.getMaps());
        }

        return Collections.unmodifiableMap(maps);
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
     * Plain text
     *
     * @return plain text
     */
    public String getPlainText() {
        return super.getString(data.getPlaintext());
    }

    /**
     * Required champion name
     *
     * @return required champion name
     */
    public String getRequiredChampionName() {
        return super.getString(data.getRequiredChampion());
    }

    /**
     * Rune
     *
     * @return rune
     */
    public MetaData getRune() {
        if(rune == null) {
            rune = new MetaData(data.getRune());
        }

        return rune;
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
     * Special recipe
     *
     * @return special recipe
     */
    public int getSpecialRecipe() {
        return super.getInteger(data.getSpecialRecipe());
    }

    /**
     * Stacks
     *
     * @return stacks
     */
    public int getStacks() {
        return super.getInteger(data.getStacks());
    }

    /**
     * Stats
     *
     * @return stats
     */
    public BasicDataStats getStats() {
        if(stats == null) {
            stats = new BasicDataStats(data.getStats());
            scrapeStats();
        }

        return stats;
    }

    /**
     * Tags
     *
     * @return tags
     */
    public List<String> getTags() {
        if(tags == null) {
            tags = new ArrayList<>();
            if(data.getTags() != null) {
                tags.addAll(data.getTags());
            }
        }

        return Collections.unmodifiableList(tags);
    }

    /**
     * Scrapes the item description to get stats Riot doesn't send in a workable
     * format
     */
    private void scrapeStats() {
        if(stats == null) {
            return;
        }

        final Class<BasicDataStats> clazz = BasicDataStats.class;
        for(final String stat : PATTERNS.keySet()) {
            final Matcher matcher = PATTERNS.get(stat).matcher(getDescription());
            if(matcher.find()) {
                try {
                    clazz.getDeclaredField(stat).set(stats, Double.parseDouble(matcher.group(1)));
                }
                catch(IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                    throw new OriannaException("Error while scraping for Item stats. Report this to the Orianna team.");
                }
            }
        }
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
