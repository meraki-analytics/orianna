package com.merakianalytics.orianna.datapipeline.transformers.dtodata;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.common.OriannaException;
import com.merakianalytics.orianna.types.common.RunePath;
import com.merakianalytics.orianna.types.data.staticdata.Champion;
import com.merakianalytics.orianna.types.data.staticdata.ChampionSpell;
import com.merakianalytics.orianna.types.data.staticdata.ChampionStats;
import com.merakianalytics.orianna.types.data.staticdata.Champions;
import com.merakianalytics.orianna.types.data.staticdata.Image;
import com.merakianalytics.orianna.types.data.staticdata.Item;
import com.merakianalytics.orianna.types.data.staticdata.ItemGroup;
import com.merakianalytics.orianna.types.data.staticdata.ItemSet;
import com.merakianalytics.orianna.types.data.staticdata.ItemStats;
import com.merakianalytics.orianna.types.data.staticdata.ItemTree;
import com.merakianalytics.orianna.types.data.staticdata.Items;
import com.merakianalytics.orianna.types.data.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.data.staticdata.Languages;
import com.merakianalytics.orianna.types.data.staticdata.Maps;
import com.merakianalytics.orianna.types.data.staticdata.Masteries;
import com.merakianalytics.orianna.types.data.staticdata.Mastery;
import com.merakianalytics.orianna.types.data.staticdata.MasteryTree;
import com.merakianalytics.orianna.types.data.staticdata.MasteryTreeItem;
import com.merakianalytics.orianna.types.data.staticdata.MasteryTreeTier;
import com.merakianalytics.orianna.types.data.staticdata.Passive;
import com.merakianalytics.orianna.types.data.staticdata.Patch;
import com.merakianalytics.orianna.types.data.staticdata.Patches;
import com.merakianalytics.orianna.types.data.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.data.staticdata.ProfileIcons;
import com.merakianalytics.orianna.types.data.staticdata.Realm;
import com.merakianalytics.orianna.types.data.staticdata.RecommendedItems;
import com.merakianalytics.orianna.types.data.staticdata.ReforgedRune;
import com.merakianalytics.orianna.types.data.staticdata.ReforgedRunePath;
import com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneSlot;
import com.merakianalytics.orianna.types.data.staticdata.ReforgedRuneTree;
import com.merakianalytics.orianna.types.data.staticdata.ReforgedRunes;
import com.merakianalytics.orianna.types.data.staticdata.Rune;
import com.merakianalytics.orianna.types.data.staticdata.RuneStats;
import com.merakianalytics.orianna.types.data.staticdata.Runes;
import com.merakianalytics.orianna.types.data.staticdata.Skin;
import com.merakianalytics.orianna.types.data.staticdata.SpellVariables;
import com.merakianalytics.orianna.types.data.staticdata.Sprite;
import com.merakianalytics.orianna.types.data.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.data.staticdata.SummonerSpells;
import com.merakianalytics.orianna.types.data.staticdata.Versions;
import com.merakianalytics.orianna.types.dto.staticdata.Block;
import com.merakianalytics.orianna.types.dto.staticdata.BlockItem;
import com.merakianalytics.orianna.types.dto.staticdata.ChampionList;
import com.merakianalytics.orianna.types.dto.staticdata.Gold;
import com.merakianalytics.orianna.types.dto.staticdata.Group;
import com.merakianalytics.orianna.types.dto.staticdata.Info;
import com.merakianalytics.orianna.types.dto.staticdata.InventoryDataStats;
import com.merakianalytics.orianna.types.dto.staticdata.ItemList;
import com.merakianalytics.orianna.types.dto.staticdata.LevelTip;
import com.merakianalytics.orianna.types.dto.staticdata.MapData;
import com.merakianalytics.orianna.types.dto.staticdata.MapDetails;
import com.merakianalytics.orianna.types.dto.staticdata.MasteryList;
import com.merakianalytics.orianna.types.dto.staticdata.MasteryTreeList;
import com.merakianalytics.orianna.types.dto.staticdata.MetaData;
import com.merakianalytics.orianna.types.dto.staticdata.ProfileIconData;
import com.merakianalytics.orianna.types.dto.staticdata.ProfileIconDetails;
import com.merakianalytics.orianna.types.dto.staticdata.Recommended;
import com.merakianalytics.orianna.types.dto.staticdata.RuneList;
import com.merakianalytics.orianna.types.dto.staticdata.SpellVars;
import com.merakianalytics.orianna.types.dto.staticdata.SummonerSpellList;

public class StaticDataTransformer extends AbstractDataTransformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(StaticDataTransformer.class);

    private static final Map<String, Pattern> SCRAPING_PATTERNS =
        ImmutableMap.<String, Pattern> builder().put("setManaRegen", Pattern.compile("\\+(\\d+) Mana regen per 5 seconds"))
            .put("setPercentManaRegen", Pattern.compile("\\+(\\d+)% Base Mana Regen (?!while in Jungle)"))
            .put("setPercentManaRegenInJungle", Pattern.compile("\\+(\\d+)% Base Mana Regen while in Jungle"))
            .put("setPercentHealth", Pattern.compile("\\+(\\d+)% Bonus Health")).put("setPercentHealthRegen", Pattern.compile("\\+(\\d+)% Base Health Regen"))
            .put("setPercentHealthRegenFromPotions", Pattern.compile("\\+(\\d+)% Increased Healing from Potions"))
            .put("setMagicPenetration", Pattern.compile("\\+(\\d+) <a href='FlatMagicPen'>Magic Penetration</a>"))
            .put("setPercentMagicPenetration", Pattern.compile("\\+(\\d+)% <a href='TotalMagicPen'>Magic Penetration</a>"))
            .put("setPercentArmorPenetration", Pattern.compile("\\+(\\d+)% <a href='BonusArmorPen'>Bonus Armor Penetration</a>"))
            .put("setPercentLifesteal",
                Pattern.compile(
                    "(?:Dealing physical damage heals for (\\d+)% of the damage dealt)|(?:\\+(\\d+)% Life Steal(?! .))|(?:Heal for (\\d+)% of damage dealt)"))
            .put("setPercentSpellVamp", Pattern.compile("Heal for (\\d+)% of damage dealt"))
            .put("setPercentLifestealAgainstMonsters", Pattern.compile("\\+(\\d+)% Life Steal vs. Monsters"))
            .put("setOnHitPhysicalDamage", Pattern.compile("Basic attacks deal an additional (\\d+) physical damage on hit"))
            .put("setOnHitMagicDamage",
                Pattern.compile("Basic attacks deal (?:(?:an additional (\\d+) magic damage on hit)|(?:(\\d+) bonus magic damage on hit))"))
            .put("setOnHitPhysicalDamageAgainstMinions", Pattern.compile("Basic attacks deal an additional (\\d+) physical damage to minions on hit"))
            .put("setOnHitPercentMaxHealthPhysicalDamage",
                Pattern.compile("Basic attacks deal (\\d+)% of the target's maximum Health in bonus physical damage"))
            .put("setOnHitPercentHealthPhysicalDamage", Pattern.compile("Basic attacks deal (\\d+)% of the target's current Health as bonus physical damage"))
            .put("setOnHitHealthRegen", Pattern.compile("\\+(\\d+) Life on Hit"))
            .put("setOnKillManaRegen", Pattern.compile("Restores (\\d+) Mana upon killing a unit"))
            .put("setPercentCooldownReduction", Pattern.compile("\\+(\\d+)% Cooldown Reduction"))
            .put("setLethality", Pattern.compile("\\+(\\d+) <a href='Lethality'>Lethality"))
            .put("setPercentHealAndShieldPower", Pattern.compile("\\+(\\d+)% Heal and Shield Power"))
            .put("setPercentMovespeed", Pattern.compile("\\+(\\d+)% Movement Speed(?! .)"))
            .put("setOutOfCombatMovespeed", Pattern.compile("\\+(\\d+) Movement Speed out of Combat"))
            .put("setGold", Pattern.compile("\\+(\\d+) Gold per 10 seconds"))
            .put("setPercentAbilityPower", Pattern.compile("Increases Ability Power by (\\d+)%"))
            .put("setPercentBaseAttackDamage", Pattern.compile("\\+(\\d+)% Base Attack Damage"))
            .put("setPercentTenacity",
                Pattern.compile(
                    "Tenacity:</unique> Reduces the duration of stuns, slows, taunts, fears, silences, blinds, polymorphs, and immobilizes by (\\d+)%"))
            .build();

    private static final Map<String, Method> STATS_MUTATORS = getScrapingMutators();

    private static String getBurn(final List<? extends Number> data) {
        if(data == null || data.isEmpty()) {
            return "";
        }

        final Number value = data.get(0);
        for(int i = 1; i < data.size(); i++) {
            if(!data.get(i).equals(value)) {
                return Joiner.on("/").join(data);
            }
        }
        return value.toString();
    }

    private static Map<String, Method> getScrapingMutators() {
        final Map<String, Method> mutators = new HashMap<>(SCRAPING_PATTERNS.size());

        for(final String mutator : SCRAPING_PATTERNS.keySet()) {
            try {
                final Method method = ItemStats.class.getDeclaredMethod(mutator, double.class);
                mutators.put(mutator, method);
            } catch(NoSuchMethodException | SecurityException e) {
                LOGGER.error("Failed to get ItemStats mutator named " + mutator + "!", e);
                throw new OriannaException("Failed to get ItemStats mutator named " + mutator + "! Report this to the Orianna team!", e);
            }
        }

        return Collections.unmodifiableMap(mutators);
    }

    private static void scrapeStats(final Item item) {
        if(!item.getLocale().startsWith("en_")) {
            // Currently the regexes are in english so we only parse english
            return;
        }

        final String description = item.getDescription();
        if(description == null || description.isEmpty()) {
            return;
        }

        for(final String mutator : SCRAPING_PATTERNS.keySet()) {
            final Pattern pattern = SCRAPING_PATTERNS.get(mutator);

            final Matcher matcher = pattern.matcher(description);
            if(matcher.find()) {
                int index = 1;
                String group = matcher.group(index);
                while(group == null) {
                    group = matcher.group(++index);
                }
                double value = Double.parseDouble(group);
                if(mutator.contains("Percent")) {
                    value /= 100;
                }
                if(mutator.equals("setGold")) {
                    value /= 10;
                }
                if(mutator.equals("setManaRegen")) {
                    value /= 5;
                }

                final Method method = STATS_MUTATORS.get(mutator);
                try {
                    method.invoke(item.getStats(), value);
                } catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.error("Failed to call ItemStats mutator named " + mutator + "!", e);
                    throw new OriannaException("Failed to call ItemStats mutator named " + mutator + "! Report this to the Orianna team!", e);
                }
            }
        }
    }

    @Transform(from = Block.class, to = ItemSet.class)
    public ItemSet transform(final Block item, final PipelineContext context) {
        final ItemSet set = new ItemSet();
        for(final BlockItem it : item.getItems()) {
            set.put(it.getId(), it.getCount());
        }
        set.setRecMath(item.isRecMath());
        set.setType(item.getType());
        set.setPlatform((String)context.get("platform"));
        set.setVersion((String)context.get("version"));
        set.setLocale((String)context.get("locale"));
        return set;
    }

    @Transform(from = Champion.class, to = com.merakianalytics.orianna.types.dto.staticdata.Champion.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Champion transform(final Champion item, final PipelineContext context) {
        final Object previous = context.put("key", item.getKey());
        final com.merakianalytics.orianna.types.dto.staticdata.Champion champion = new com.merakianalytics.orianna.types.dto.staticdata.Champion();
        if(item.getAllyTips() != null) {
            champion.setAllytips(new ArrayList<>(item.getAllyTips()));
        }
        champion.setBlurb(item.getBlurb());
        final Info info = new Info();
        info.setAttack(item.getPhysicalRating());
        info.setDefense(item.getDefenseRating());
        info.setDifficulty(item.getDifficultyRating());
        info.setMagic(item.getMagicRating());
        champion.setInfo(info);
        if(item.getEnemyTips() != null) {
            champion.setEnemytips(new ArrayList<>(item.getEnemyTips()));
        }
        champion.setId(item.getId());
        if(item.getImage() != null) {
            champion.setImage(transform(item.getImage(), context));
        }
        champion.setIncludedData(new HashSet<>(item.getIncludedData()));
        champion.setKey(item.getKey());
        champion.setLocale(item.getLocale());
        champion.setLore(item.getLore());
        champion.setName(item.getName());
        if(item.getPassive() != null) {
            champion.setPassive(transform(item.getPassive(), context));
        }
        champion.setPlatform(item.getPlatform());
        if(item.getRecommendedItems() != null) {
            final List<Recommended> items = new ArrayList<>(item.getRecommendedItems().size());
            for(final RecommendedItems rec : item.getRecommendedItems()) {
                items.add(transform(rec, context));
            }
            champion.setRecommended(items);
        }
        champion.setPartype(item.getResource());
        if(item.getSkins() != null) {
            final List<com.merakianalytics.orianna.types.dto.staticdata.Skin> skins = new ArrayList<>(item.getSkins().size());
            for(final Skin skin : item.getSkins()) {
                skins.add(transform(skin, context));
            }
            champion.setSkins(skins);
        }
        if(item.getSpells() != null) {
            final List<com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell> spells = new ArrayList<>(item.getSpells().size());
            for(final ChampionSpell spell : item.getSpells()) {
                spells.add(transform(spell, context));
            }
            champion.setSpells(spells);
        }
        if(item.getStats() != null) {
            champion.setStats(transform(item.getStats(), context));
        }
        if(item.getTags() != null) {
            champion.setTags(new ArrayList<>(item.getTags()));
        }
        champion.setTitle(item.getTitle());
        champion.setVersion(item.getVersion());
        context.put("key", previous);
        return champion;
    }

    @Transform(from = ChampionList.class, to = Champions.class)
    public Champions transform(final ChampionList item, final PipelineContext context) {
        final Champions champions = new Champions(item.getData().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.Champion champion : item.getData().values()) {
            champions.add(transform(champion, context));
        }
        champions.setFormat(item.getFormat());
        champions.setIncludedData(new HashSet<>(item.getIncludedData()));
        champions.setLocale(item.getLocale());
        champions.setPlatform(item.getPlatform());
        champions.setType(item.getType());
        champions.setVersion(item.getVersion());
        return champions;
    }

    @Transform(from = Champions.class, to = ChampionList.class)
    public ChampionList transform(final Champions item, final PipelineContext context) {
        final ChampionList champions = new ChampionList();
        final Map<String, com.merakianalytics.orianna.types.dto.staticdata.Champion> data = new HashMap<>();
        final Map<String, String> keys = new HashMap<>();
        for(final Champion champion : item) {
            data.put(Integer.toString(champion.getId()), transform(champion, context));
            keys.put(Integer.toString(champion.getId()), champion.getKey());
        }
        champions.setData(data);
        champions.setKeys(keys);
        champions.setFormat(item.getFormat());
        champions.setIncludedData(new HashSet<>(item.getIncludedData()));
        champions.setLocale(item.getLocale());
        champions.setPlatform(item.getPlatform());
        champions.setType(item.getType());
        champions.setVersion(item.getVersion());
        return champions;
    }

    @Transform(from = ChampionSpell.class, to = com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell.class)
    public com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell transform(final ChampionSpell item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell spell = new com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell();
        if(item.getAlternativeImages() != null) {
            final List<com.merakianalytics.orianna.types.dto.staticdata.Image> alternativeImages = new ArrayList<>(item.getAlternativeImages().size());
            for(final Image image : item.getAlternativeImages()) {
                alternativeImages.add(transform(image, context));
            }
            spell.setAltImages(alternativeImages);
        }
        if(item.getCooldowns() != null) {
            spell.setCooldown(new ArrayList<>(item.getCooldowns()));
            spell.setCooldownBurn(getBurn(item.getCooldowns()));
        }
        if(item.getCosts() != null) {
            spell.setCost(new ArrayList<>(item.getCosts()));
            spell.setCostBurn(getBurn(item.getCosts()));
        }
        spell.setDescription(item.getDescription());
        if(item.getEffects() != null) {
            final List<List<Double>> effects = new ArrayList<>(item.getEffects().size());
            final List<String> effectBurn = new ArrayList<>(item.getEffects().size());
            for(final List<Double> effect : item.getEffects()) {
                effects.add(new ArrayList<>(effect));
                effectBurn.add(getBurn(effect));
            }
            spell.setEffect(effects);
            spell.setEffectBurn(effectBurn);
        }
        if(item.getImage() != null) {
            spell.setImage(transform(item.getImage(), context));
        }
        spell.setKey(item.getKey());
        if(item.getLevelUpKeywords() != null || item.getLevelUpEffects() != null) {
            final LevelTip tip = new LevelTip();
            if(item.getLevelUpKeywords() != null) {
                tip.setLabel(new ArrayList<>(item.getLevelUpKeywords()));
            }
            if(item.getLevelUpEffects() != null) {
                tip.setEffect(new ArrayList<>(item.getLevelUpEffects()));
            }
            spell.setLeveltip(tip);
        }
        spell.setMaxrank(item.getMaxRank());
        spell.setName(item.getName());
        if(item.getRanges() != null) {
            spell.setRange(new ArrayList<>(item.getRanges()));
        }
        spell.setRangeBurn(getBurn(item.getRanges()));
        spell.setCostType(item.getResource());
        spell.setResource(item.getResourceDescription());
        spell.setSanitizedDescription(item.getSanitizedDescription());
        spell.setSanitizedTooltip(item.getSanitizedTooltip());
        spell.setTooltip(item.getTooltip());
        if(item.getVariables() != null) {
            final List<SpellVars> variables = new ArrayList<>(item.getVariables().size());
            for(final SpellVariables vars : item.getVariables()) {
                variables.add(transform(vars, context));
            }
            spell.setVars(variables);
        }
        return spell;
    }

    @Transform(from = ChampionStats.class, to = com.merakianalytics.orianna.types.dto.staticdata.Stats.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Stats transform(final ChampionStats item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Stats stats = new com.merakianalytics.orianna.types.dto.staticdata.Stats();
        stats.setArmor(item.getArmor());
        stats.setArmorperlevel(item.getArmorPerLevel());
        stats.setAttackdamage(item.getAttackDamage());
        stats.setAttackdamageperlevel(item.getAttackDamagePerLevel());
        stats.setAttackrange(item.getAttackRange());
        stats.setAttackspeedoffset(item.getAttackSpeedOffset());
        stats.setAttackspeedperlevel(item.getAttackSpeedPerLevel());
        stats.setCrit(item.getCriticalStrikeChance());
        stats.setCritperlevel(item.getCriticalStrikeChancePerLevel());
        stats.setHp(item.getHealth());
        stats.setHpperlevel(item.getHealthPerLevel());
        stats.setHpregen(item.getHealthRegen());
        stats.setHpregenperlevel(item.getHealthRegenPerLevel());
        stats.setSpellblock(item.getMagicResist());
        stats.setSpellblockperlevel(item.getMagicResistPerLevel());
        stats.setMp(item.getMana());
        stats.setMpperlevel(item.getManaPerLevel());
        stats.setMpregen(item.getManaRegen());
        stats.setMpregenperlevel(item.getManaRegenPerLevel());
        stats.setMovespeed(item.getMovespeed());
        return stats;
    }

    @Transform(from = com.merakianalytics.orianna.types.data.staticdata.Map.class, to = MapDetails.class)
    public MapDetails transform(final com.merakianalytics.orianna.types.data.staticdata.Map item, final PipelineContext context) {
        final MapDetails map = new MapDetails();
        map.setMapId(item.getId());
        map.setImage(transform(item.getImage(), context));
        map.setLocale(item.getLocale());
        map.setMapName(item.getName());
        map.setPlatform(item.getPlatform());
        if(item.getUnpurchasableItems() != null) {
            final List<Long> items = new ArrayList<>(item.getUnpurchasableItems().size());
            for(final Integer it : item.getUnpurchasableItems()) {
                items.add(it.longValue());
            }
            map.setUnpurchasableItemList(items);
        }
        map.setVersion(item.getVersion());
        return map;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Champion.class, to = Champion.class)
    public Champion transform(final com.merakianalytics.orianna.types.dto.staticdata.Champion item, final PipelineContext context) {
        final Object previousPlatform = context.put("platform", item.getPlatform());
        final Object previousVersion = context.put("version", item.getVersion());
        final Object previousLocale = context.put("locale", item.getLocale());
        final Object previousKey = context.put("key", item.getKey());
        final Champion champion = new Champion();
        if(item.getAllytips() != null) {
            champion.setAllyTips(new ArrayList<>(item.getAllytips()));
        }
        champion.setBlurb(item.getBlurb());
        if(item.getInfo() != null) {
            champion.setDefenseRating(item.getInfo().getDefense());
        }
        if(item.getInfo() != null) {
            champion.setDifficultyRating(item.getInfo().getDifficulty());
            champion.setMagicRating(item.getInfo().getMagic());
            champion.setPhysicalRating(item.getInfo().getAttack());
        }
        if(item.getEnemytips() != null) {
            champion.setEnemyTips(new ArrayList<>(item.getEnemytips()));
        }
        champion.setId(item.getId());
        if(item.getImage() != null) {
            champion.setImage(transform(item.getImage(), context));
        }
        champion.setIncludedData(new HashSet<>(item.getIncludedData()));
        champion.setKey(item.getKey());
        champion.setLocale(item.getLocale());
        champion.setLore(item.getLore());
        champion.setName(item.getName());
        if(item.getPassive() != null) {
            champion.setPassive(transform(item.getPassive(), context));
        }

        champion.setPlatform(item.getPlatform());
        if(item.getRecommended() != null) {
            final List<RecommendedItems> items = new ArrayList<>(item.getRecommended().size());
            for(final Recommended rec : item.getRecommended()) {
                items.add(transform(rec, context));
            }
            champion.setRecommendedItems(items);
        }
        champion.setResource(item.getPartype());
        if(item.getSkins() != null) {
            final List<Skin> skins = new ArrayList<>(item.getSkins().size());
            for(final com.merakianalytics.orianna.types.dto.staticdata.Skin skin : item.getSkins()) {
                skins.add(transform(skin, context));
            }
            champion.setSkins(skins);
        }
        if(item.getSpells() != null) {
            final List<ChampionSpell> spells = new ArrayList<>(item.getSpells().size());
            for(final com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell spell : item.getSpells()) {
                spells.add(transform(spell, context));
            }
            champion.setSpells(spells);
        }
        if(item.getStats() != null) {
            champion.setStats(transform(item.getStats(), context));
        }
        if(item.getTags() != null) {
            champion.setTags(new ArrayList<>(item.getTags()));
        }
        champion.setTitle(item.getTitle());
        champion.setVersion(item.getVersion());
        context.put("platform", previousPlatform);
        context.put("version", previousVersion);
        context.put("locale", previousLocale);
        context.put("key", previousKey);
        return champion;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell.class, to = ChampionSpell.class)
    public ChampionSpell transform(final com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell item, final PipelineContext context) {
        final ChampionSpell spell = new ChampionSpell();
        if(item.getAltImages() != null) {
            final List<Image> alternativeImages = new ArrayList<>(item.getAltImages().size());
            for(final com.merakianalytics.orianna.types.dto.staticdata.Image image : item.getAltImages()) {
                alternativeImages.add(transform(image, context));
            }
            spell.setAlternativeImages(alternativeImages);
        }
        if(item.getCooldown() != null) {
            spell.setCooldowns(new ArrayList<>(item.getCooldown()));
        }
        if(item.getCost() != null) {
            spell.setCosts(new ArrayList<>(item.getCost()));
        }
        spell.setDescription(item.getDescription());
        if(item.getEffect() != null) {
            final List<List<Double>> effects = new ArrayList<>(item.getEffect().size());
            for(final List<Double> effect : item.getEffect()) {
                effects.add(effect == null ? null : new ArrayList<>(effect));
            }
            spell.setEffects(effects);
        }
        if(item.getImage() != null) {
            spell.setImage(transform(item.getImage(), context));
        }
        spell.setKey(item.getKey());
        if(item.getLeveltip() != null) {
            if(item.getLeveltip().getLabel() != null) {
                spell.setLevelUpKeywords(new ArrayList<>(item.getLeveltip().getLabel()));
            }

            if(item.getLeveltip().getEffect() != null) {
                spell.setLevelUpEffects(new ArrayList<>(item.getLeveltip().getEffect()));
            }
        }
        spell.setMaxRank(item.getMaxrank());
        spell.setName(item.getName());
        if(item.getRange() != null) {
            spell.setRanges(new ArrayList<>(item.getRange()));
        }
        spell.setResource(item.getCostType());
        spell.setResourceDescription(item.getResource());
        spell.setSanitizedDescription(item.getSanitizedDescription());
        spell.setSanitizedTooltip(item.getSanitizedTooltip());
        spell.setTooltip(item.getTooltip());
        if(item.getVars() != null) {
            final List<SpellVariables> variables = new ArrayList<>(item.getVars().size());
            for(final SpellVars vars : item.getVars()) {
                variables.add(transform(vars, context));
            }
            spell.setVariables(variables);
        }
        return spell;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Image.class, to = Image.class)
    public Image transform(final com.merakianalytics.orianna.types.dto.staticdata.Image item, final PipelineContext context) {
        final Image image = new Image();
        image.setFull(item.getFull());
        image.setGroup(item.getGroup());
        image.setVersion((String)context.get("version"));
        final Sprite sprite = new Sprite();
        sprite.setFull(item.getSprite());
        sprite.setHeight(item.getH());
        sprite.setWidth(item.getW());
        sprite.setX(item.getX());
        sprite.setY(item.getY());
        sprite.setVersion((String)context.get("version"));
        image.setSprite(sprite);
        return image;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Item.class, to = Item.class)
    public Item transform(final com.merakianalytics.orianna.types.dto.staticdata.Item item, final PipelineContext context) {
        final Object previous = context.put("version", item.getVersion());
        final Item converted = new Item();
        if(item.getGold() != null) {
            converted.setBasePrice(item.getGold().getBase());
            converted.setPurchasable(item.getGold().isPurchasable());
            converted.setSellPrice(item.getGold().getSell());
            converted.setTotalPrice(item.getGold().getTotal());
        }
        if(item.getFrom() != null) {
            final List<Integer> buildsFrom = new ArrayList<>(item.getFrom().size());
            for(final String id : item.getFrom()) {
                buildsFrom.add(Integer.parseInt(id));
            }
            converted.setBuildsFrom(buildsFrom);
        }
        if(item.getInto() != null) {
            final List<Integer> buildsInto = new ArrayList<>(item.getInto().size());
            for(final String id : item.getInto()) {
                buildsInto.add(Integer.parseInt(id));
            }
            converted.setBuildsInto(buildsInto);
        }
        converted.setConsumed(item.isConsumed());
        converted.setConsumedWhenFull(item.isConsumeOnFull());
        converted.setDescription(item.getDescription());
        if(item.getEffect() != null) {
            converted.setEffects(new HashMap<>(item.getEffect()));
        }
        converted.setGroup(item.getGroup());
        converted.setHiddenFromAll(item.isHideFromAll());
        converted.setId(item.getId());
        if(item.getImage() != null) {
            converted.setImage(transform(item.getImage(), context));
        }
        converted.setIncludedData(new HashSet<>(item.getIncludedData()));
        converted.setInStore(item.isInStore());
        if(item.getColloq() != null) {
            final Set<String> keywords = new HashSet<>();
            for(final String keyword : item.getColloq().split(";")) {
                keywords.add(keyword);
            }
            converted.setKeywords(keywords);
        }
        converted.setLocale(item.getLocale());
        if(item.getMaps() != null) {
            final Set<Integer> maps = new HashSet<>();
            for(final String id : item.getMaps().keySet()) {
                if(item.getMaps().get(id)) {
                    maps.add(Integer.parseInt(id));
                }
            }
            converted.setMaps(maps);
        }
        converted.setMaxStacks(item.getStacks());
        converted.setName(item.getName());
        converted.setPlaintext(item.getPlaintext());
        converted.setPlatform(item.getPlatform());
        converted.setRequiredChampionKey(item.getRequiredChampion());
        converted.setSanitizedDescription(item.getSanitizedDescription());
        converted.setSource(item.getSpecialRecipe());
        if(item.getStats() != null) {
            converted.setStats(transform(item.getStats(), context));
        } else {
            converted.setStats(new ItemStats());
        }
        scrapeStats(converted); // Add stats from the item description
        if(item.getTags() != null) {
            converted.setTags(new ArrayList<>(item.getTags()));
        }
        converted.setTier(item.getDepth());
        converted.setVersion(item.getVersion());
        context.put("version", previous);
        return converted;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.ItemTree.class, to = ItemTree.class)
    public ItemTree transform(final com.merakianalytics.orianna.types.dto.staticdata.ItemTree item, final PipelineContext context) {
        final ItemTree tree = new ItemTree();
        tree.setHeader(item.getHeader());
        if(item.getTags() != null) {
            tree.setTags(new ArrayList<>(item.getTags()));
        }
        return tree;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Languages.class, to = Languages.class)
    public Languages transform(final com.merakianalytics.orianna.types.dto.staticdata.Languages item, final PipelineContext context) {
        final Languages languages = new Languages();
        languages.addAll(item);
        languages.setPlatform(item.getPlatform());
        return languages;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings.class, to = LanguageStrings.class)
    public LanguageStrings transform(final com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings item, final PipelineContext context) {
        final LanguageStrings strings = new LanguageStrings();
        strings.putAll(item.getData());
        strings.setLocale(item.getLocale());
        strings.setPlatform(item.getPlatform());
        strings.setType(item.getType());
        strings.setVersion(item.getVersion());
        return strings;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Mastery.class, to = Mastery.class)
    public Mastery transform(final com.merakianalytics.orianna.types.dto.staticdata.Mastery item, final PipelineContext context) {
        final Object previous = context.put("version", item.getVersion());
        final Mastery mastery = new Mastery();
        if(item.getDescription() != null) {
            mastery.setDescriptions(new ArrayList<>(item.getDescription()));
        }
        mastery.setId(item.getId());
        if(item.getImage() != null) {
            mastery.setImage(transform(item.getImage(), context));
        }
        mastery.setIncludedData(new HashSet<>(item.getIncludedData()));
        mastery.setLocale(item.getLocale());
        mastery.setName(item.getName());
        mastery.setPlatform(item.getPlatform());
        mastery.setPoints(item.getRanks());
        if(item.getPrereq() != null) {
            mastery.setPrerequisite(Integer.parseInt(item.getPrereq()));
        }
        if(item.getSanitizedDescription() != null) {
            mastery.setSanitizedDescriptions(new ArrayList<>(item.getSanitizedDescription()));
        }
        if(item.getMasteryTree() != null) {
            mastery.setTree(item.getMasteryTree().toUpperCase());
        }
        mastery.setVersion(item.getVersion());
        context.put("version", previous);
        return mastery;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.MasteryTree.class, to = MasteryTree.class)
    public MasteryTree transform(final com.merakianalytics.orianna.types.dto.staticdata.MasteryTree item, final PipelineContext context) {
        final MasteryTree tree = new MasteryTree();
        if(item.getCunning() != null) {
            final List<MasteryTreeTier> tiers = new ArrayList<>(item.getCunning().size());
            for(final MasteryTreeList list : item.getCunning()) {
                tiers.add(transform(list, context));
            }
            tree.setCunning(tiers);
        }
        if(item.getFerocity() != null) {
            final List<MasteryTreeTier> tiers = new ArrayList<>(item.getFerocity().size());
            for(final MasteryTreeList list : item.getFerocity()) {
                tiers.add(transform(list, context));
            }
            tree.setFerocity(tiers);
        }
        if(item.getResolve() != null) {
            final List<MasteryTreeTier> tiers = new ArrayList<>(item.getResolve().size());
            for(final MasteryTreeList list : item.getResolve()) {
                tiers.add(transform(list, context));
            }
            tree.setResolve(tiers);
        }
        return tree;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.MasteryTreeItem.class, to = MasteryTreeItem.class)
    public MasteryTreeItem transform(final com.merakianalytics.orianna.types.dto.staticdata.MasteryTreeItem item, final PipelineContext context) {
        final MasteryTreeItem converted = new MasteryTreeItem();
        converted.setId(item.getMasteryId());
        converted.setPrerequisiteId(Integer.parseInt(item.getPrereq()));
        converted.setPlatform((String)context.get("platform"));
        converted.setVersion((String)context.get("version"));
        converted.setLocale((String)context.get("locale"));
        return converted;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Passive.class, to = Passive.class)
    public Passive transform(final com.merakianalytics.orianna.types.dto.staticdata.Passive item, final PipelineContext context) {
        final Passive passive = new Passive();
        passive.setDescription(item.getDescription());
        if(item.getImage() != null) {
            passive.setImage(transform(item.getImage(), context));
        }
        passive.setName(item.getName());
        passive.setSanitizedDescription(item.getSanitizedDescription());
        return passive;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Patch.class, to = Patch.class)
    public Patch transform(final com.merakianalytics.orianna.types.dto.staticdata.Patch item, final PipelineContext context) {
        final Patch patch = new Patch();
        if(item.getEnd() != 0) {
            patch.setEndTime(new DateTime(item.getEnd() * 1000));
        }
        patch.setName(item.getName());
        patch.setPlatform(item.getPlatform());
        patch.setSeason(item.getSeason());
        patch.setStartTime(new DateTime(item.getStart() * 1000));
        return patch;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Patches.class, to = Patches.class)
    public Patches transform(final com.merakianalytics.orianna.types.dto.staticdata.Patches item, final PipelineContext context) {
        final Patches patches = new Patches(item.getPatches().size());
        for(int i = item.getPatches().size() - 1; i >= 0; i--) {
            patches.add(transform(item.getPatches().get(i), context));
        }
        patches.setPlatform(item.getPlatform());
        patches.setShifts(new HashMap<String, Duration>());
        for(final String platform : item.getShifts().keySet()) {
            final Duration shift = new Duration(item.getShifts().get(platform));
            patches.getShifts().put(platform, shift);
        }
        return patches;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Realm.class, to = Realm.class)
    public Realm transform(final com.merakianalytics.orianna.types.dto.staticdata.Realm item, final PipelineContext context) {
        final Realm realm = new Realm();
        realm.setCDN(item.getCdn());
        realm.setCSSVersion(item.getCss());
        realm.setDefaultLocale(item.getL());
        realm.setLatestDataDragon(item.getDd());
        realm.setLatestVersions(new HashMap<>(item.getN()));
        realm.setLegacyMode(item.getL());
        realm.setMaxProfileIconId(item.getProfileiconmax());
        realm.setPlatform(item.getPlatform());
        realm.setStore(item.getStore());
        realm.setVersion(item.getV());
        return realm;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.ReforgedRune.class, to = ReforgedRune.class)
    public ReforgedRune transform(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRune item, final PipelineContext context) {
        final ReforgedRune rune = new ReforgedRune();
        rune.setImage(item.getIcon());
        rune.setId(item.getId());
        rune.setKey(item.getKey());
        rune.setLocale(item.getLocale());
        rune.setLongDescription(item.getLongDesc());
        rune.setName(item.getName());
        rune.setPlatform(item.getPlatform());
        rune.setShortDescription(item.getShortDesc());
        rune.setVersion(item.getVersion());
        rune.setPath(item.getPathId());
        rune.setSlot(item.getSlot());
        return rune;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.ReforgedRunePath.class, to = ReforgedRunePath.class)
    public ReforgedRunePath transform(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRunePath item, final PipelineContext context) {
        final ReforgedRunePath path = new ReforgedRunePath(item.getSlots().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneSlot slot : item.getSlots()) {
            path.add(transform(slot, context));
        }
        path.setImage(item.getIcon());
        path.setId(item.getId());
        path.setKey(item.getKey());
        path.setName(item.getName());
        return path;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneSlot.class, to = ReforgedRuneSlot.class)
    public ReforgedRuneSlot transform(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneSlot item, final PipelineContext context) {
        final ReforgedRuneSlot slot = new ReforgedRuneSlot(item.getRunes().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRune rune : item.getRunes()) {
            slot.add(rune.getId());
        }
        return slot;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree.class, to = ReforgedRunes.class)
    public ReforgedRunes transform(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree item, final PipelineContext context) {
        int count = 0;
        for(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRunePath path : item) {
            for(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneSlot slot : path.getSlots()) {
                count += slot.getRunes().size();
            }
        }
        final ReforgedRunes runes = new ReforgedRunes(count);
        for(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRunePath path : item) {
            for(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneSlot slot : path.getSlots()) {
                for(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRune rune : slot.getRunes()) {
                    runes.add(transform(rune, context));
                }
            }
        }
        runes.setTree(transformTree(item, context));
        runes.setLocale(item.getLocale());
        runes.setPlatform(item.getPlatform());
        runes.setVersion(item.getVersion());
        return runes;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Rune.class, to = Rune.class)
    public Rune transform(final com.merakianalytics.orianna.types.dto.staticdata.Rune item, final PipelineContext context) {
        final Object previous = context.put("version", item.getVersion());
        final Rune rune = new Rune();
        rune.setDescription(item.getDescription());
        rune.setId(item.getId());
        if(item.getImage() != null) {
            rune.setImage(transform(item.getImage(), context));
        }
        rune.setIncludedData(new HashSet<>(item.getIncludedData()));
        rune.setLocale(item.getLocale());
        rune.setName(item.getName());
        rune.setPlatform(item.getPlatform());
        rune.setSanitizedDescription(item.getSanitizedDescription());
        if(item.getStats() != null) {
            rune.setStats(transform(item.getStats(), context));
        }
        if(item.getTags() != null) {
            rune.setTags(new ArrayList<>(item.getTags()));
        }
        if(item.getRune() != null) {
            rune.setTier(Integer.parseInt(item.getRune().getTier()));
            rune.setType(item.getRune().getType());
        }
        rune.setVersion(item.getVersion());
        context.put("version", previous);
        return rune;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.RuneStats.class, to = RuneStats.class)
    public RuneStats transform(final com.merakianalytics.orianna.types.dto.staticdata.RuneStats item, final PipelineContext context) {
        final RuneStats stats = new RuneStats();
        stats.setAbilityPower(item.getFlatMagicDamageMod());
        stats.setAbilityPowerPerLevel(item.getFlatMagicDamageModPerLevel());
        stats.setArmor(item.getFlatArmorMod());
        stats.setArmorPenetration(item.getFlatArmorPenetrationMod());
        stats.setArmorPenetrationPerLevel(item.getFlatArmorPenetrationModPerLevel());
        stats.setArmorPerLevel(item.getFlatArmorModPerLevel());
        stats.setAttackDamage(item.getFlatPhysicalDamageMod());
        stats.setAttackDamagePerLevel(item.getFlatPhysicalDamageModPerLevel());
        stats.setAttackSpeed(item.getFlatAttackSpeedMod());
        stats.setBlock(item.getFlatBlockMod());
        stats.setCriticalStrikeChance(item.getFlatCritChanceMod());
        stats.setCriticalStrikeChancePerLevel(item.getFlatCritChanceModPerLevel());
        stats.setCriticalStrikeDamage(item.getFlatCritDamageMod());
        stats.setCriticalStrikeDamagePerLevel(item.getFlatCritDamageModPerLevel());
        stats.setDodge(item.getFlatDodgeMod());
        stats.setDodgePerLevel(item.getFlatDodgeModPerLevel());
        stats.setEnergy(item.getFlatEnergyPoolMod());
        stats.setEnergyPerLevel(item.getFlatEnergyModPerLevel());
        stats.setEnergyRegen(item.getFlatEnergyRegenMod());
        stats.setEnergyRegenPerLevel(item.getFlatEnergyRegenModPerLevel());
        stats.setExperience(item.getFlatEXPBonus());
        stats.setGoldPer10(item.getFlatGoldPer10Mod());
        stats.setHealth(item.getFlatHPPoolMod());
        stats.setHealthPerLevel(item.getFlatHPModPerLevel());
        stats.setHealthRegen(item.getFlatHPRegenMod());
        stats.setHealthRegenPerLevel(item.getFlatHPRegenModPerLevel());
        stats.setMagicPenetration(item.getFlatMagicPenetrationMod());
        stats.setMagicPenetrationPerLevel(item.getFlatMagicPenetrationModPerLevel());
        stats.setMagicResist(item.getFlatSpellBlockMod());
        stats.setMagicResistPerLevel(item.getFlatSpellBlockModPerLevel());
        stats.setMana(item.getFlatMPPoolMod());
        stats.setManaPerLevel(item.getFlatMPModPerLevel());
        stats.setManaRegen(item.getFlatMPRegenMod());
        stats.setManaRegenPerLevel(item.getFlatMPRegenModPerLevel());
        stats.setMovespeed(item.getFlatMovementSpeedMod());
        stats.setMovespeedPerLevel(item.getFlatMovementSpeedModPerLevel());
        stats.setPercentAbilityPower(item.getPercentMagicDamageMod());
        stats.setPercentArmor(item.getPercentArmorMod());
        stats.setPercentArmorPenetration(item.getPercentArmorPenetrationMod());
        stats.setPercentArmorPenetrationPerLevel(item.getPercentArmorPenetrationModPerLevel());
        stats.setPercentAttackDamage(item.getPercentPhysicalDamageMod());
        stats.setPercentAttackSpeed(item.getPercentAttackSpeedMod());
        stats.setPercentAttackSpeedPerLevel(item.getPercentAttackSpeedModPerLevel());
        stats.setPercentBlock(item.getPercentBlockMod());
        stats.setPercentCooldownReduction(item.getPercentCooldownMod());
        stats.setPercentCooldownReductionPerLevel(item.getPercentCooldownModPerLevel());
        stats.setPercentCriticalStrikeChance(item.getFlatCritChanceModPerLevel());
        stats.setPercentCriticalStrikeDamage(item.getFlatCritDamageModPerLevel());
        stats.setPercentDodge(item.getPercentDodgeMod());
        stats.setPercentExperience(item.getPercentEXPBonus());
        stats.setPercentHealth(item.getPercentHPPoolMod());
        stats.setPercentHealthRegen(item.getPercentHPRegenMod());
        stats.setPercentLifesteal(item.getPercentLifeStealMod());
        stats.setPercentMagicPenetration(item.getPercentMagicPenetrationMod());
        stats.setPercentMagicPenetrationPerLevel(item.getPercentMagicPenetrationModPerLevel());
        stats.setPercentMagicResist(item.getPercentSpellBlockMod());
        stats.setPercentMana(item.getPercentMPPoolMod());
        stats.setPercentManaRegen(item.getPercentMPRegenMod());
        stats.setPercentMovespeed(item.getPercentMovementSpeedMod());
        stats.setPercentMovespeedPerLevel(item.getPercentMovementSpeedModPerLevel());
        stats.setPercentSpellVamp(item.getPercentSpellVampMod());
        stats.setPercentTimeSpentDead(item.getPercentTimeDeadMod());
        stats.setPercentTimeSpentDeadPerLevel(item.getPercentTimeDeadModPerLevel());
        stats.setTimeSpentDead(item.getFlatTimeDeadMod());
        stats.setTimeSpentDeadPerLevel(item.getFlatTimeDeadModPerLevel());
        return stats;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Skin.class, to = Skin.class)
    public Skin transform(final com.merakianalytics.orianna.types.dto.staticdata.Skin item, final PipelineContext context) {
        final Skin skin = new Skin();
        skin.setId(item.getId());
        skin.setName(item.getName());
        skin.setNumber(item.getNum());
        skin.setChampionKey((String)context.get("key"));
        return skin;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Stats.class, to = ChampionStats.class)
    public ChampionStats transform(final com.merakianalytics.orianna.types.dto.staticdata.Stats item, final PipelineContext context) {
        final ChampionStats stats = new ChampionStats();
        stats.setArmor(item.getArmor());
        stats.setArmorPerLevel(item.getArmorperlevel());
        stats.setAttackDamage(item.getAttackdamage());
        stats.setAttackDamagePerLevel(item.getAttackdamageperlevel());
        stats.setAttackRange(item.getAttackrange());
        stats.setAttackSpeedOffset(item.getAttackspeedoffset());
        stats.setAttackSpeedPerLevel(item.getAttackspeedperlevel());
        stats.setCriticalStrikeChance(item.getCrit());
        stats.setCriticalStrikeChancePerLevel(item.getCritperlevel());
        stats.setHealth(item.getHp());
        stats.setHealthPerLevel(item.getHpperlevel());
        stats.setHealthRegen(item.getHpregen());
        stats.setHealthRegenPerLevel(item.getHpregenperlevel());
        stats.setMagicResist(item.getSpellblock());
        stats.setMagicResistPerLevel(item.getSpellblockperlevel());
        stats.setMana(item.getMp());
        stats.setManaPerLevel(item.getMpperlevel());
        stats.setManaRegen(item.getMpregen());
        stats.setManaRegenPerLevel(item.getMpregenperlevel());
        stats.setMovespeed(item.getMovespeed());
        return stats;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.SummonerSpell.class, to = SummonerSpell.class)
    public SummonerSpell transform(final com.merakianalytics.orianna.types.dto.staticdata.SummonerSpell item, final PipelineContext context) {
        final Object previous = context.put("version", item.getVersion());
        final SummonerSpell spell = new SummonerSpell();
        if(item.getCooldown() != null) {
            spell.setCooldowns(new ArrayList<>(item.getCooldown()));
        }
        if(item.getCost() != null) {
            spell.setCosts(new ArrayList<>(item.getCost()));
        }
        spell.setDescription(item.getDescription());
        if(item.getEffect() != null) {
            final List<List<Double>> effects = new ArrayList<>(item.getEffect().size());
            for(final List<Double> effect : item.getEffect()) {
                effects.add(effect == null ? null : new ArrayList<>(effect));
            }
            spell.setEffects(effects);
        }
        spell.setId(item.getId());
        if(item.getImage() != null) {
            spell.setImage(transform(item.getImage(), context));
        }
        spell.setIncludedData(new HashSet<>(item.getIncludedData()));
        spell.setKey(item.getKey());
        if(item.getLeveltip() != null) {
            spell.setLevelUpEffects(new ArrayList<>(item.getLeveltip().getEffect()));
            spell.setLevelUpKeywords(new ArrayList<>(item.getLeveltip().getLabel()));
        }
        spell.setLocale(item.getLocale());
        spell.setMaxRank(item.getMaxrank());
        if(item.getModes() != null) {
            spell.setModes(new HashSet<>(item.getModes()));
        }
        spell.setName(item.getName());
        spell.setPlatform(item.getPlatform());
        if(item.getRange() != null) {
            spell.setRanges(new ArrayList<>(item.getRange()));
        }
        spell.setResource(item.getCostType());
        spell.setResourceDescription(item.getResource());
        spell.setSanitizedDescription(item.getSanitizedDescription());
        spell.setSanitizedTooltip(item.getSanitizedTooltip());
        if(item.getVars() != null) {
            final List<SpellVariables> variables = new ArrayList<>(item.getVars().size());
            for(final SpellVars vars : item.getVars()) {
                variables.add(transform(vars, context));
            }
            spell.setVariables(variables);
        }
        spell.setVersion(item.getVersion());
        context.put("version", previous);
        return spell;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Versions.class, to = Versions.class)
    public Versions transform(final com.merakianalytics.orianna.types.dto.staticdata.Versions item, final PipelineContext context) {
        final Versions versions = new Versions(item.size());
        versions.addAll(item);
        versions.setPlatform(item.getPlatform());
        return versions;
    }

    @Transform(from = Group.class, to = ItemGroup.class)
    public ItemGroup transform(final Group item, final PipelineContext context) {
        final ItemGroup group = new ItemGroup();
        group.setKey(item.getKey());
        group.setMax(item.getMaxGroupOwnable() == null ? -1 : Integer.parseInt(item.getMaxGroupOwnable()));
        return group;
    }

    @Transform(from = Image.class, to = com.merakianalytics.orianna.types.dto.staticdata.Image.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Image transform(final Image item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Image image = new com.merakianalytics.orianna.types.dto.staticdata.Image();
        image.setFull(item.getFull());
        image.setGroup(item.getGroup());
        image.setH(item.getSprite().getHeight());
        image.setSprite(item.getSprite().getFull());
        image.setW(item.getSprite().getWidth());
        image.setX(item.getSprite().getX());
        image.setY(item.getSprite().getY());
        return image;
    }

    @Transform(from = InventoryDataStats.class, to = ItemStats.class)
    public ItemStats transform(final InventoryDataStats item, final PipelineContext context) {
        final ItemStats stats = new ItemStats();
        stats.setAbilityPower(item.getFlatMagicDamageMod());
        stats.setArmor(item.getFlatArmorMod());
        stats.setAttackDamage(item.getFlatPhysicalDamageMod());
        stats.setAttackSpeed(item.getFlatAttackSpeedMod());
        stats.setBlock(item.getFlatBlockMod());
        stats.setCriticalStrikeChance(item.getFlatCritChanceMod());
        stats.setCriticalStrikeDamage(item.getFlatCritDamageMod());
        stats.setEnergy(item.getFlatEnergyPoolMod());
        stats.setEnergyRegen(item.getFlatEnergyRegenMod());
        stats.setExperience(item.getFlatEXPBonus());
        stats.setHealth(item.getFlatHPPoolMod());
        stats.setHealthRegen(item.getFlatHPRegenMod());
        stats.setMagicResist(item.getFlatSpellBlockMod());
        stats.setMana(item.getFlatMPPoolMod());
        stats.setManaRegen(item.getFlatMPRegenMod());
        stats.setMovespeed(item.getFlatMovementSpeedMod());
        stats.setPercentAbilityPower(item.getPercentMagicDamageMod());
        stats.setPercentArmor(item.getPercentArmorMod());
        stats.setPercentAttackDamage(item.getPercentPhysicalDamageMod());
        stats.setPercentAttackSpeed(item.getPercentAttackSpeedMod());
        stats.setPercentBlock(item.getPercentBlockMod());
        stats.setPercentCriticalStrikeChance(item.getPercentCritChanceMod());
        stats.setPercentCriticalStrikeChange(item.getPercentCritDamageMod());
        stats.setPercentDodge(item.getPercentDodgeMod());
        stats.setPercentExperience(item.getPercentEXPBonus());
        stats.setPercentHealth(item.getPercentHPPoolMod());
        stats.setPercentHealthRegen(item.getPercentHPRegenMod());
        stats.setPercentLifesteal(item.getPercentLifeStealMod());
        stats.setPercentMagicResist(item.getPercentSpellBlockMod());
        stats.setPercentMana(item.getPercentMPPoolMod());
        stats.setPercentManaRegen(item.getPercentMPRegenMod());
        stats.setPercentMovespeed(item.getPercentMovementSpeedMod());
        stats.setPercentSpellVamp(item.getPercentSpellVampMod());
        return stats;
    }

    @Transform(from = Item.class, to = com.merakianalytics.orianna.types.dto.staticdata.Item.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Item transform(final Item item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Item converted = new com.merakianalytics.orianna.types.dto.staticdata.Item();
        final Gold gold = new Gold();
        gold.setBase(item.getBasePrice());
        gold.setPurchasable(item.isPurchasable());
        gold.setSell(item.getSellPrice());
        gold.setTotal(item.getTotalPrice());
        converted.setGold(gold);
        if(item.getBuildsFrom() != null) {
            final List<String> from = new ArrayList<>(item.getBuildsFrom().size());
            for(final Integer id : item.getBuildsFrom()) {
                from.add(id.toString());
            }
            converted.setFrom(from);
        }
        if(item.getBuildsInto() != null) {
            final List<String> into = new ArrayList<>(item.getBuildsInto().size());
            for(final Integer id : item.getBuildsInto()) {
                into.add(id.toString());
            }
            converted.setInto(into);
        }
        converted.setConsumed(item.isConsumed());
        converted.setConsumeOnFull(item.isConsumedWhenFull());
        converted.setDescription(item.getDescription());
        if(item.getEffects() != null) {
            converted.setEffect(new HashMap<>(item.getEffects()));
        }
        converted.setGroup(item.getGroup());
        converted.setHideFromAll(item.isHiddenFromAll());
        converted.setId(item.getId());
        if(item.getImage() != null) {
            converted.setImage(transform(item.getImage(), context));
        }
        converted.setIncludedData(new HashSet<>(item.getIncludedData()));
        converted.setInStore(item.isInStore());
        if(item.getKeywords() != null) {
            converted.setColloq(Joiner.on(";").join(item.getKeywords()));
        }
        converted.setLocale(item.getLocale());
        final Map<String, Boolean> maps = new HashMap<>();
        for(final com.merakianalytics.orianna.types.common.Map map : com.merakianalytics.orianna.types.common.Map.values()) {
            maps.put(Integer.toString(map.getId()), item.getMaps().contains(map.getId()));
        }
        converted.setMaps(maps);
        converted.setStacks(item.getMaxStacks());
        converted.setName(item.getName());
        converted.setPlaintext(item.getPlaintext());
        converted.setPlatform(item.getPlatform());
        converted.setRequiredChampion(item.getRequiredChampionKey());
        converted.setSanitizedDescription(item.getSanitizedDescription());
        converted.setSpecialRecipe(item.getSource());
        if(item.getStats() != null) {
            converted.setStats(transform(item.getStats(), context));
        }
        if(item.getTags() != null) {
            converted.setTags(new ArrayList<>(item.getTags()));
        }
        converted.setDepth(item.getTier());
        converted.setVersion(item.getVersion());
        return converted;
    }

    @Transform(from = ItemGroup.class, to = Group.class)
    public Group transform(final ItemGroup item, final PipelineContext context) {
        final Group group = new Group();
        group.setKey(item.getKey());
        group.setMaxGroupOwnable(item.getMax() == -1 ? null : Integer.toString(item.getMax()));
        return group;
    }

    @Transform(from = ItemList.class, to = Items.class)
    public Items transform(final ItemList item, final PipelineContext context) {
        final Items items = new Items(item.getData().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.Item it : item.getData().values()) {
            items.add(transform(it, context));
        }
        if(item.getGroups() != null) {
            final List<ItemGroup> groups = new ArrayList<>(item.getGroups().size());
            for(final Group group : item.getGroups()) {
                groups.add(transform(group, context));
            }
            items.setGroups(groups);
        }
        items.setIncludedData(new HashSet<>(item.getIncludedData()));
        items.setLocale(item.getLocale());
        items.setPlatform(item.getPlatform());
        if(item.getTree() != null) {
            final List<ItemTree> tree = new ArrayList<>(item.getTree().size());
            for(final com.merakianalytics.orianna.types.dto.staticdata.ItemTree t : item.getTree()) {
                tree.add(transform(t, context));
            }
            items.setTree(tree);
        }
        items.setType(item.getType());
        items.setVersion(item.getVersion());
        return items;
    }

    @Transform(from = Items.class, to = ItemList.class)
    public ItemList transform(final Items item, final PipelineContext context) {
        final ItemList items = new ItemList();
        final Map<String, com.merakianalytics.orianna.types.dto.staticdata.Item> data = new HashMap<>();
        for(final Item it : item) {
            data.put(Integer.toString(it.getId()), transform(it, context));
        }
        items.setData(data);
        if(item.getGroups() != null) {
            final List<Group> groups = new ArrayList<>(item.getGroups().size());
            for(final ItemGroup group : item.getGroups()) {
                groups.add(transform(group, context));
            }
            items.setGroups(groups);
        }
        items.setIncludedData(new HashSet<>(item.getIncludedData()));
        items.setLocale(item.getLocale());
        items.setPlatform(item.getPlatform());
        if(item.getTree() != null) {
            final List<com.merakianalytics.orianna.types.dto.staticdata.ItemTree> tree = new ArrayList<>(item.getTree().size());
            for(final ItemTree t : item.getTree()) {
                tree.add(transform(t, context));
            }
            items.setTree(tree);
        }
        items.setType(item.getType());
        items.setVersion(item.getVersion());
        return items;
    }

    @Transform(from = ItemSet.class, to = Block.class)
    public Block transform(final ItemSet item, final PipelineContext context) {
        final Block block = new Block();
        final List<BlockItem> items = new ArrayList<>(item.size());
        for(final Integer itemId : item.keySet()) {
            final BlockItem it = new BlockItem();
            it.setCount(item.get(itemId));
            it.setId(itemId);
        }
        block.setItems(items);
        block.setRecMath(item.isRecMath());
        block.setType(item.getType());
        return block;
    }

    @Transform(from = ItemStats.class, to = InventoryDataStats.class)
    public InventoryDataStats transform(final ItemStats item, final PipelineContext context) {
        final InventoryDataStats stats = new InventoryDataStats();
        stats.setFlatMagicDamageMod(item.getAbilityPower());
        stats.setFlatArmorMod(item.getArmor());
        stats.setFlatPhysicalDamageMod(item.getAttackDamage());
        stats.setFlatAttackSpeedMod(item.getAttackSpeed());
        stats.setFlatBlockMod(item.getBlock());
        stats.setFlatCritChanceMod(item.getCriticalStrikeChance());
        stats.setFlatCritDamageMod(item.getCriticalStrikeDamage());
        stats.setFlatEnergyPoolMod(item.getEnergy());
        stats.setFlatEnergyRegenMod(item.getEnergyRegen());
        stats.setFlatEXPBonus(item.getExperience());
        stats.setFlatHPPoolMod(item.getHealth());
        stats.setFlatHPRegenMod(item.getHealthRegen());
        stats.setFlatSpellBlockMod(item.getMagicResist());
        stats.setFlatMPPoolMod(item.getMana());
        stats.setFlatMPRegenMod(item.getManaRegen());
        stats.setFlatMovementSpeedMod(item.getMovespeed());
        stats.setPercentMagicDamageMod(item.getPercentAbilityPower());
        stats.setPercentArmorMod(item.getPercentArmor());
        stats.setPercentPhysicalDamageMod(item.getPercentAttackDamage());
        stats.setPercentAttackSpeedMod(item.getPercentAttackSpeed());
        stats.setPercentBlockMod(item.getPercentBlock());
        stats.setPercentCritChanceMod(item.getPercentCriticalStrikeChance());
        stats.setPercentCritDamageMod(item.getPercentCriticalStrikeChange());
        stats.setPercentDodgeMod(item.getPercentDodge());
        stats.setPercentEXPBonus(item.getPercentExperience());
        stats.setPercentHPPoolMod(item.getPercentHealth());
        stats.setPercentHPRegenMod(item.getPercentHealthRegen());
        stats.setPercentLifeStealMod(item.getPercentLifesteal());
        stats.setPercentSpellBlockMod(item.getPercentMagicResist());
        stats.setPercentMPPoolMod(item.getPercentMana());
        stats.setPercentMPRegenMod(item.getPercentManaRegen());
        stats.setPercentMovementSpeedMod(item.getPercentMovespeed());
        stats.setPercentSpellVampMod(item.getPercentSpellVamp());
        return stats;
    }

    @Transform(from = ItemTree.class, to = com.merakianalytics.orianna.types.dto.staticdata.ItemTree.class)
    public com.merakianalytics.orianna.types.dto.staticdata.ItemTree transform(final ItemTree item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.ItemTree tree = new com.merakianalytics.orianna.types.dto.staticdata.ItemTree();
        tree.setHeader(item.getHeader());
        if(item.getTags() != null) {
            tree.setTags(new ArrayList<>(item.getTags()));
        }
        return tree;
    }

    @Transform(from = Languages.class, to = com.merakianalytics.orianna.types.dto.staticdata.Languages.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Languages transform(final Languages item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Languages languages = new com.merakianalytics.orianna.types.dto.staticdata.Languages();
        languages.addAll(item);
        languages.setPlatform(item.getPlatform());
        return languages;
    }

    @Transform(from = LanguageStrings.class, to = com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings.class)
    public com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings transform(final LanguageStrings item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings strings = new com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings();
        strings.setData(new HashMap<>(item));
        strings.setLocale(item.getLocale());
        strings.setPlatform(item.getPlatform());
        strings.setType(item.getType());
        strings.setVersion(item.getVersion());
        return strings;
    }

    @Transform(from = MapData.class, to = Maps.class)
    public Maps transform(final MapData item, final PipelineContext context) {
        final Maps maps = new Maps(item.getData().size());
        for(final MapDetails map : item.getData().values()) {
            maps.add(transform(map, context));
        }
        maps.setLocale(item.getLocale());
        maps.setPlatform(item.getPlatform());
        maps.setType(item.getType());
        maps.setVersion(item.getVersion());
        return maps;
    }

    @Transform(from = MapDetails.class, to = com.merakianalytics.orianna.types.data.staticdata.Map.class)
    public com.merakianalytics.orianna.types.data.staticdata.Map transform(final MapDetails item, final PipelineContext context) {
        final Object previous = context.put("version", item.getVersion());
        final com.merakianalytics.orianna.types.data.staticdata.Map map = new com.merakianalytics.orianna.types.data.staticdata.Map();
        map.setId((int)item.getMapId());
        map.setImage(transform(item.getImage(), context));
        map.setLocale(item.getLocale());
        map.setName(item.getMapName());
        map.setPlatform(item.getPlatform());
        if(item.getUnpurchasableItemList() != null) {
            final List<Integer> items = new ArrayList<>(item.getUnpurchasableItemList().size());
            for(final Long it : item.getUnpurchasableItemList()) {
                items.add(it.intValue());
            }
            map.setUnpurchasableItems(items);
        }
        map.setVersion(item.getVersion());
        context.put("version", previous);
        return map;
    }

    @Transform(from = Maps.class, to = MapData.class)
    public MapData transform(final Maps item, final PipelineContext context) {
        final MapData maps = new MapData();
        final Map<String, MapDetails> data = new HashMap<>();
        for(final com.merakianalytics.orianna.types.data.staticdata.Map map : item) {
            data.put(Integer.toString(map.getId()), transform(map, context));
        }
        maps.setData(data);
        maps.setLocale(item.getLocale());
        maps.setPlatform(item.getPlatform());
        maps.setType(item.getType());
        maps.setVersion(item.getVersion());
        return maps;
    }

    @Transform(from = Masteries.class, to = MasteryList.class)
    public MasteryList transform(final Masteries item, final PipelineContext context) {
        final MasteryList masteries = new MasteryList();
        final Map<String, com.merakianalytics.orianna.types.dto.staticdata.Mastery> data = new HashMap<>(item.size());
        for(final Mastery mastery : item) {
            data.put(Integer.toString(mastery.getId()), transform(mastery, context));
        }
        masteries.setData(data);
        masteries.setIncludedData(new HashSet<>(item.getIncludedData()));
        masteries.setLocale(item.getLocale());
        masteries.setPlatform(item.getPlatform());
        if(item.getTree() != null) {
            masteries.setTree(transform(item.getTree(), context));
        }
        masteries.setType(item.getType());
        masteries.setVersion(item.getVersion());
        return masteries;
    }

    @Transform(from = Mastery.class, to = com.merakianalytics.orianna.types.dto.staticdata.Mastery.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Mastery transform(final Mastery item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Mastery mastery = new com.merakianalytics.orianna.types.dto.staticdata.Mastery();
        if(item.getDescriptions() != null) {
            mastery.setDescription(new ArrayList<>(item.getDescriptions()));
        }
        mastery.setId(item.getId());
        if(item.getImage() != null) {
            mastery.setImage(transform(item.getImage(), context));
        }
        mastery.setIncludedData(new HashSet<>(item.getIncludedData()));
        mastery.setLocale(item.getLocale());
        mastery.setName(item.getName());
        mastery.setPlatform(item.getPlatform());
        mastery.setRanks(item.getPoints());
        mastery.setPrereq(Integer.toString(item.getPrerequisite()));
        if(item.getSanitizedDescriptions() != null) {
            mastery.setSanitizedDescription(new ArrayList<>(item.getSanitizedDescriptions()));
        }
        if(item.getTree() != null) {
            mastery.setMasteryTree(item.getTree().toString().substring(0, 1) + item.getTree().toString().toLowerCase().substring(1));
        }
        mastery.setVersion(item.getVersion());
        return mastery;
    }

    @Transform(from = MasteryList.class, to = Masteries.class)
    public Masteries transform(final MasteryList item, final PipelineContext context) {
        final Object previousPlatform = context.put("platform", item.getPlatform());
        final Object previousVersion = context.put("version", item.getVersion());
        final Object previousLocale = context.put("locale", item.getLocale());
        final Masteries masteries = new Masteries(item.getData().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.Mastery mastery : item.getData().values()) {
            masteries.add(transform(mastery, context));
        }
        masteries.setIncludedData(new HashSet<>(item.getIncludedData()));
        masteries.setLocale(item.getLocale());
        masteries.setPlatform(item.getPlatform());
        if(item.getTree() != null) {
            masteries.setTree(transform(item.getTree(), context));
        }
        masteries.setType(item.getType());
        masteries.setVersion(item.getVersion());
        context.put("platform", previousPlatform);
        context.put("version", previousVersion);
        context.put("locale", previousLocale);
        return masteries;
    }

    @Transform(from = MasteryTree.class, to = com.merakianalytics.orianna.types.dto.staticdata.MasteryTree.class)
    public com.merakianalytics.orianna.types.dto.staticdata.MasteryTree transform(final MasteryTree item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.MasteryTree tree = new com.merakianalytics.orianna.types.dto.staticdata.MasteryTree();
        if(item.getCunning() != null) {
            final List<MasteryTreeList> tiers = new ArrayList<>(item.getCunning().size());
            for(final MasteryTreeTier list : item.getCunning()) {
                tiers.add(transform(list, context));
            }
            tree.setCunning(tiers);
        }
        if(item.getFerocity() != null) {
            final List<MasteryTreeList> tiers = new ArrayList<>(item.getFerocity().size());
            for(final MasteryTreeTier list : item.getFerocity()) {
                tiers.add(transform(list, context));
            }
            tree.setFerocity(tiers);
        }
        if(item.getResolve() != null) {
            final List<MasteryTreeList> tiers = new ArrayList<>(item.getResolve().size());
            for(final MasteryTreeTier list : item.getResolve()) {
                tiers.add(transform(list, context));
            }
            tree.setResolve(tiers);
        }
        return tree;
    }

    @Transform(from = MasteryTreeItem.class, to = com.merakianalytics.orianna.types.dto.staticdata.MasteryTreeItem.class)
    public com.merakianalytics.orianna.types.dto.staticdata.MasteryTreeItem transform(final MasteryTreeItem item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.MasteryTreeItem converted =
            new com.merakianalytics.orianna.types.dto.staticdata.MasteryTreeItem();
        converted.setMasteryId(item.getId());
        converted.setPrereq(Integer.toString(item.getPrerequisiteId()));
        return converted;
    }

    @Transform(from = MasteryTreeList.class, to = MasteryTreeTier.class)
    public MasteryTreeTier transform(final MasteryTreeList item, final PipelineContext context) {
        final MasteryTreeTier tier = new MasteryTreeTier(item.getMasteryTreeItems().size());
        if(item.getMasteryTreeItems() != null) {
            for(final com.merakianalytics.orianna.types.dto.staticdata.MasteryTreeItem it : item.getMasteryTreeItems()) {
                tier.add(transform(it, context));
            }
        }
        return tier;
    }

    @Transform(from = MasteryTreeTier.class, to = MasteryTreeList.class)
    public MasteryTreeList transform(final MasteryTreeTier item, final PipelineContext context) {
        final MasteryTreeList tier = new MasteryTreeList();
        final List<com.merakianalytics.orianna.types.dto.staticdata.MasteryTreeItem> items = new ArrayList<>();
        for(final MasteryTreeItem it : item) {
            items.add(transform(it, context));
        }
        tier.setMasteryTreeItems(items);
        return tier;
    }

    @Transform(from = Passive.class, to = com.merakianalytics.orianna.types.dto.staticdata.Passive.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Passive transform(final Passive item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Passive passive = new com.merakianalytics.orianna.types.dto.staticdata.Passive();
        passive.setDescription(item.getDescription());
        if(item.getImage() != null) {
            passive.setImage(transform(item.getImage(), context));
        }
        passive.setName(item.getName());
        passive.setSanitizedDescription(item.getSanitizedDescription());
        return passive;
    }

    @Transform(from = Patch.class, to = com.merakianalytics.orianna.types.dto.staticdata.Patch.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Patch transform(final Patch item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Patch patch = new com.merakianalytics.orianna.types.dto.staticdata.Patch();
        if(item.getEndTime() != null) {
            patch.setEnd(item.getEndTime().getMillis() / 1000);
        }
        patch.setName(item.getName());
        patch.setPlatform(item.getPlatform());
        patch.setSeason(item.getSeason());
        patch.setStart(item.getEndTime().getMillis() / 1000);
        return patch;
    }

    @Transform(from = Patches.class, to = com.merakianalytics.orianna.types.dto.staticdata.Patches.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Patches transform(final Patches item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Patches patches = new com.merakianalytics.orianna.types.dto.staticdata.Patches();
        patches.setPatches(new ArrayList<com.merakianalytics.orianna.types.dto.staticdata.Patch>(item.size()));
        for(int i = item.size() - 1; i >= 0; i--) {
            patches.getPatches().add(transform(item.get(i), context));
        }
        patches.setPlatform(item.getPlatform());
        patches.setShifts(new HashMap<String, Long>());
        for(final String platform : item.getShifts().keySet()) {
            patches.getShifts().put(platform, item.getShifts().get(platform).getMillis());
        }
        return patches;
    }

    @Transform(from = ProfileIcon.class, to = ProfileIconDetails.class)
    public ProfileIconDetails transform(final ProfileIcon item, final PipelineContext context) {
        final ProfileIconDetails icon = new ProfileIconDetails();
        icon.setId(icon.getId());
        icon.setImage(transform(item.getImage(), context));
        icon.setLocale(item.getLocale());
        icon.setPlatform(item.getPlatform());
        icon.setVersion(item.getVersion());
        return icon;
    }

    @Transform(from = ProfileIconData.class, to = ProfileIcons.class)
    public ProfileIcons transform(final ProfileIconData item, final PipelineContext context) {
        final ProfileIcons icons = new ProfileIcons(item.getData().size());
        for(final ProfileIconDetails icon : item.getData().values()) {
            icons.add(transform(icon, context));
        }
        icons.setLocale(item.getLocale());
        icons.setPlatform(item.getPlatform());
        icons.setType(item.getType());
        icons.setVersion(item.getVersion());
        return icons;
    }

    @Transform(from = ProfileIconDetails.class, to = ProfileIcon.class)
    public ProfileIcon transform(final ProfileIconDetails item, final PipelineContext context) {
        final Object previous = context.put("version", item.getVersion());
        final ProfileIcon icon = new ProfileIcon();
        icon.setId(icon.getId());
        icon.setImage(transform(item.getImage(), context));
        icon.setLocale(item.getLocale());
        icon.setPlatform(item.getPlatform());
        icon.setVersion(item.getVersion());
        context.put("version", previous);
        return icon;
    }

    @Transform(from = ProfileIcons.class, to = ProfileIconData.class)
    public ProfileIconData transform(final ProfileIcons item, final PipelineContext context) {
        final ProfileIconData icons = new ProfileIconData();
        final Map<String, ProfileIconDetails> data = new HashMap<>();
        for(final ProfileIcon icon : item) {
            data.put(Integer.toString(icon.getId()), transform(icon, context));
        }
        icons.setData(data);
        icons.setLocale(item.getLocale());
        icons.setPlatform(item.getPlatform());
        icons.setType(item.getType());
        icons.setVersion(item.getVersion());
        return icons;
    }

    @Transform(from = Realm.class, to = com.merakianalytics.orianna.types.dto.staticdata.Realm.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Realm transform(final Realm item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Realm realm = new com.merakianalytics.orianna.types.dto.staticdata.Realm();
        realm.setCdn(item.getCDN());
        realm.setCss(item.getCSSVersion());
        realm.setL(item.getDefaultLocale());
        realm.setDd(item.getLatestDataDragon());
        realm.setN(new HashMap<>(item.getLatestVersions()));
        realm.setL(item.getLegacyMode());
        realm.setProfileiconmax(item.getMaxProfileIconId());
        realm.setPlatform(item.getPlatform());
        realm.setStore(item.getStore());
        realm.setV(item.getVersion());
        return realm;
    }

    @Transform(from = Recommended.class, to = RecommendedItems.class)
    public RecommendedItems transform(final Recommended item, final PipelineContext context) {
        final RecommendedItems items = new RecommendedItems(item.getBlocks().size());
        if(item.getBlocks() != null) {
            for(final Block block : item.getBlocks()) {
                items.add(transform(block, context));
            }
        }
        items.setMap(item.getMap());
        items.setMode(item.getMode());
        items.setPriority(item.isPriority());
        items.setTitle(item.getTitle());
        items.setType(item.getType());
        return items;
    }

    @Transform(from = RecommendedItems.class, to = Recommended.class)
    public Recommended transform(final RecommendedItems item, final PipelineContext context) {
        final Recommended items = new Recommended();
        final List<Block> blocks = new ArrayList<>(item.size());
        for(final ItemSet set : item) {
            blocks.add(transform(set, context));
        }
        items.setBlocks(blocks);
        items.setChampion((String)context.get("key"));
        items.setMap(item.getMap());
        items.setMode(item.getMode().toString());
        items.setPriority(item.isPriority());
        items.setTitle(item.getTitle());
        items.setType(item.getType());
        return items;
    }

    @Transform(from = ReforgedRune.class, to = com.merakianalytics.orianna.types.dto.staticdata.ReforgedRune.class)
    public com.merakianalytics.orianna.types.dto.staticdata.ReforgedRune transform(final ReforgedRune item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRune rune = new com.merakianalytics.orianna.types.dto.staticdata.ReforgedRune();
        rune.setIcon(item.getImage());
        rune.setId(item.getId());
        rune.setKey(item.getKey());
        rune.setLocale(item.getLocale());
        rune.setLongDesc(item.getLongDescription());
        rune.setName(item.getName());
        rune.setPlatform(item.getPlatform());
        rune.setShortDesc(item.getShortDescription());
        rune.setVersion(item.getVersion());
        rune.setPathId(item.getPath());
        rune.setSlot(item.getSlot());
        return rune;
    }

    @Transform(from = ReforgedRunePath.class, to = com.merakianalytics.orianna.types.dto.staticdata.ReforgedRunePath.class)
    public com.merakianalytics.orianna.types.dto.staticdata.ReforgedRunePath transform(final ReforgedRunePath item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRunePath path = new com.merakianalytics.orianna.types.dto.staticdata.ReforgedRunePath();
        final List<com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneSlot> list = new ArrayList<>(item.size());
        for(final ReforgedRuneSlot slot : item) {
            list.add(transform(slot, context));
        }
        path.setSlots(list);
        path.setIcon(item.getImage());
        path.setId(item.getId());
        path.setKey(item.getKey());
        path.setName(item.getName());
        return path;
    }

    @Transform(from = ReforgedRunes.class, to = com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree.class)
    public com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree transform(final ReforgedRunes item, final PipelineContext context) {
        final Map<Integer, ReforgedRune> runes = new HashMap<>();
        for(final ReforgedRune rune : item) {
            runes.put(rune.getId(), rune);
        }
        final Object previousRunes = context.put("runes", runes);
        final Object previousLocale = context.put("locale", item.getLocale());
        final Object previousPlatform = context.put("platform", item.getPlatform());
        final Object previousVersion = context.put("version", item.getVersion());
        final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree tree = transform(item, context);
        context.put("runes", previousRunes);
        context.put("locale", previousLocale);
        context.put("platform", previousPlatform);
        context.put("version", previousVersion);
        return tree;
    }

    @SuppressWarnings("unchecked")
    @Transform(from = ReforgedRuneSlot.class, to = com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneSlot.class)
    public com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneSlot transform(final ReforgedRuneSlot item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneSlot slot = new com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneSlot();
        final Map<Integer, ReforgedRune> runes = (Map<Integer, ReforgedRune>)context.get("runes");
        final List<com.merakianalytics.orianna.types.dto.staticdata.ReforgedRune> list = new ArrayList<>(item.size());
        for(final Integer id : item) {
            list.add(transform(runes.get(id), context));
        }
        slot.setRunes(list);
        return slot;
    }

    @Transform(from = ReforgedRuneTree.class, to = com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree.class)
    public com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree transform(final ReforgedRuneTree item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree tree = new com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree(5);
        tree.add(transform(item.getDomination(), context));
        tree.add(transform(item.getInspiration(), context));
        tree.add(transform(item.getPrecision(), context));
        tree.add(transform(item.getResolve(), context));
        tree.add(transform(item.getSorcery(), context));
        tree.setLocale((String)context.get("locale"));
        tree.setPlatform((String)context.get("platform"));
        tree.setVersion((String)context.get("version"));
        return tree;
    }

    @Transform(from = Rune.class, to = com.merakianalytics.orianna.types.dto.staticdata.Rune.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Rune transform(final Rune item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Rune rune = new com.merakianalytics.orianna.types.dto.staticdata.Rune();
        rune.setDescription(item.getDescription());
        rune.setId(item.getId());
        if(item.getImage() != null) {
            rune.setImage(transform(item.getImage(), context));
        }
        rune.setIncludedData(new HashSet<>(item.getIncludedData()));
        rune.setLocale(item.getLocale());
        rune.setName(item.getName());
        rune.setPlatform(item.getPlatform());
        rune.setSanitizedDescription(item.getSanitizedDescription());
        if(item.getStats() != null) {
            rune.setStats(transform(item.getStats(), context));
        }
        if(item.getTags() != null) {
            rune.setTags(new ArrayList<>(item.getTags()));
        }
        final MetaData metadata = new MetaData();
        metadata.setRune(true);
        metadata.setTier(Integer.toString(item.getTier()));
        metadata.setType(item.getType());
        rune.setRune(metadata);
        rune.setVersion(item.getVersion());
        return rune;
    }

    @Transform(from = RuneList.class, to = Runes.class)
    public Runes transform(final RuneList item, final PipelineContext context) {
        final Runes runes = new Runes(item.getData().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.Rune rune : item.getData().values()) {
            runes.add(transform(rune, context));
        }
        runes.setIncludedData(new HashSet<>(item.getIncludedData()));
        runes.setLocale(item.getLocale());
        runes.setPlatform(item.getPlatform());
        runes.setType(item.getType());
        runes.setVersion(item.getVersion());
        return runes;
    }

    @Transform(from = Runes.class, to = RuneList.class)
    public RuneList transform(final Runes item, final PipelineContext context) {
        final RuneList runes = new RuneList();
        final Map<String, com.merakianalytics.orianna.types.dto.staticdata.Rune> data = new HashMap<>();
        for(final Rune rune : item) {
            data.put(Integer.toString(rune.getId()), transform(rune, context));
        }
        runes.setData(data);
        runes.setIncludedData(new HashSet<>(item.getIncludedData()));
        runes.setLocale(item.getLocale());
        runes.setPlatform(item.getPlatform());
        runes.setType(item.getType());
        runes.setVersion(item.getVersion());
        return runes;
    }

    @Transform(from = RuneStats.class, to = com.merakianalytics.orianna.types.dto.staticdata.RuneStats.class)
    public com.merakianalytics.orianna.types.dto.staticdata.RuneStats transform(final RuneStats item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.RuneStats stats = new com.merakianalytics.orianna.types.dto.staticdata.RuneStats();
        stats.setFlatMagicDamageMod(item.getAbilityPower());
        stats.setFlatMagicDamageModPerLevel(item.getAbilityPowerPerLevel());
        stats.setFlatArmorMod(item.getArmor());
        stats.setFlatArmorPenetrationMod(item.getArmorPenetration());
        stats.setFlatArmorPenetrationModPerLevel(item.getArmorPenetrationPerLevel());
        stats.setFlatArmorModPerLevel(item.getArmorPerLevel());
        stats.setFlatPhysicalDamageMod(item.getAttackDamage());
        stats.setFlatPhysicalDamageModPerLevel(item.getAttackDamagePerLevel());
        stats.setFlatAttackSpeedMod(item.getAttackSpeed());
        stats.setFlatBlockMod(item.getBlock());
        stats.setFlatCritChanceMod(item.getCriticalStrikeChance());
        stats.setFlatCritChanceModPerLevel(item.getCriticalStrikeChancePerLevel());
        stats.setFlatCritDamageMod(item.getCriticalStrikeDamage());
        stats.setFlatCritDamageModPerLevel(item.getCriticalStrikeDamagePerLevel());
        stats.setFlatDodgeMod(item.getDodge());
        stats.setFlatDodgeModPerLevel(item.getDodgePerLevel());
        stats.setFlatEnergyPoolMod(item.getEnergy());
        stats.setFlatEnergyModPerLevel(item.getEnergyPerLevel());
        stats.setFlatEnergyRegenMod(item.getEnergyRegen());
        stats.setFlatEnergyRegenModPerLevel(item.getEnergyRegenPerLevel());
        stats.setFlatEXPBonus(item.getExperience());
        stats.setFlatGoldPer10Mod(item.getGoldPer10());
        stats.setFlatHPPoolMod(item.getHealth());
        stats.setFlatHPModPerLevel(item.getHealthPerLevel());
        stats.setFlatHPRegenMod(item.getHealthRegen());
        stats.setFlatHPRegenModPerLevel(item.getHealthRegenPerLevel());
        stats.setFlatMagicPenetrationMod(item.getMagicPenetration());
        stats.setFlatMagicPenetrationModPerLevel(item.getMagicPenetrationPerLevel());
        stats.setFlatSpellBlockMod(item.getMagicResist());
        stats.setFlatSpellBlockModPerLevel(item.getMagicResistPerLevel());
        stats.setFlatMPPoolMod(item.getMana());
        stats.setFlatMPModPerLevel(item.getManaPerLevel());
        stats.setFlatMPRegenMod(item.getManaRegen());
        stats.setFlatMPRegenModPerLevel(item.getManaRegenPerLevel());
        stats.setFlatMovementSpeedMod(item.getMovespeed());
        stats.setFlatMovementSpeedModPerLevel(item.getMovespeedPerLevel());
        stats.setPercentMagicDamageMod(item.getPercentAbilityPower());
        stats.setPercentArmorMod(item.getPercentArmor());
        stats.setPercentArmorPenetrationMod(item.getPercentArmorPenetration());
        stats.setPercentArmorPenetrationModPerLevel(item.getPercentArmorPenetrationPerLevel());
        stats.setPercentPhysicalDamageMod(item.getPercentAttackDamage());
        stats.setPercentAttackSpeedMod(item.getPercentAttackSpeed());
        stats.setPercentAttackSpeedModPerLevel(item.getPercentAttackSpeedPerLevel());
        stats.setPercentBlockMod(item.getPercentBlock());
        stats.setPercentCooldownMod(item.getPercentCooldownReduction());
        stats.setPercentCooldownModPerLevel(item.getPercentCooldownReductionPerLevel());
        stats.setFlatCritChanceModPerLevel(item.getPercentCriticalStrikeChance());
        stats.setFlatCritDamageModPerLevel(item.getPercentCriticalStrikeDamage());
        stats.setPercentDodgeMod(item.getPercentDodge());
        stats.setPercentEXPBonus(item.getPercentExperience());
        stats.setPercentHPPoolMod(item.getPercentHealth());
        stats.setPercentHPRegenMod(item.getPercentHealthRegen());
        stats.setPercentLifeStealMod(item.getPercentLifesteal());
        stats.setPercentMagicPenetrationMod(item.getPercentMagicPenetration());
        stats.setPercentMagicPenetrationModPerLevel(item.getPercentMagicPenetrationPerLevel());
        stats.setPercentSpellBlockMod(item.getPercentMagicResist());
        stats.setPercentMPPoolMod(item.getPercentMana());
        stats.setPercentMPRegenMod(item.getPercentManaRegen());
        stats.setPercentMovementSpeedMod(item.getPercentMovespeed());
        stats.setPercentMovementSpeedModPerLevel(item.getPercentMovespeedPerLevel());
        stats.setPercentSpellVampMod(item.getPercentSpellVamp());
        stats.setPercentTimeDeadMod(item.getPercentTimeSpentDead());
        stats.setPercentTimeDeadModPerLevel(item.getPercentTimeSpentDeadPerLevel());
        stats.setFlatTimeDeadMod(item.getTimeSpentDead());
        stats.setFlatTimeDeadModPerLevel(item.getTimeSpentDeadPerLevel());
        return stats;
    }

    @Transform(from = Skin.class, to = com.merakianalytics.orianna.types.dto.staticdata.Skin.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Skin transform(final Skin item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Skin skin = new com.merakianalytics.orianna.types.dto.staticdata.Skin();
        skin.setId(item.getId());
        skin.setName(item.getName());
        skin.setNum(item.getNumber());
        return skin;
    }

    @Transform(from = SpellVariables.class, to = SpellVars.class)
    public SpellVars transform(final SpellVariables item, final PipelineContext context) {
        final SpellVars variables = new SpellVars();
        variables.setCoeff(new ArrayList<>(item.getCoefficients()));
        variables.setDyn(item.getDynamic());
        variables.setKey(item.getKey());
        variables.setLink(item.getLink());
        variables.setRanksWith(item.getRanksWith());
        return variables;
    }

    @Transform(from = SpellVars.class, to = SpellVariables.class)
    public SpellVariables transform(final SpellVars item, final PipelineContext context) {
        final SpellVariables variables = new SpellVariables();
        variables.setCoefficients(new ArrayList<>(item.getCoeff()));
        variables.setDynamic(item.getDyn());
        variables.setKey(item.getKey());
        variables.setLink(item.getLink());
        variables.setRanksWith(item.getRanksWith());
        return variables;
    }

    @Transform(from = SummonerSpell.class, to = com.merakianalytics.orianna.types.dto.staticdata.SummonerSpell.class)
    public com.merakianalytics.orianna.types.dto.staticdata.SummonerSpell transform(final SummonerSpell item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.SummonerSpell spell = new com.merakianalytics.orianna.types.dto.staticdata.SummonerSpell();
        if(item.getCooldowns() != null) {
            spell.setCooldown(new ArrayList<>(item.getCooldowns()));
            spell.setCooldownBurn(getBurn(item.getCooldowns()));
        }
        if(item.getCosts() != null) {
            spell.setCost(new ArrayList<>(item.getCosts()));
            spell.setCostBurn(getBurn(item.getCosts()));
        }
        spell.setDescription(item.getDescription());
        if(item.getEffects() != null) {
            final List<List<Double>> effects = new ArrayList<>(item.getEffects().size());
            final List<String> effectBurn = new ArrayList<>(item.getEffects().size());
            for(final List<Double> effect : item.getEffects()) {
                effects.add(new ArrayList<>(effect));
                effectBurn.add(getBurn(effect));
            }
            spell.setEffect(effects);
            spell.setEffectBurn(effectBurn);
        }
        spell.setId(item.getId());
        if(item.getImage() != null) {
            spell.setImage(transform(item.getImage(), context));
        }
        spell.setIncludedData(new HashSet<>(item.getIncludedData()));
        spell.setKey(item.getKey());
        final LevelTip tip = new LevelTip();
        if(item.getLevelUpEffects() != null) {
            tip.setEffect(new ArrayList<>(item.getLevelUpEffects()));
        }
        if(item.getLevelUpKeywords() != null) {
            tip.setLabel(new ArrayList<>(item.getLevelUpKeywords()));
        }
        spell.setLeveltip(tip);
        spell.setLocale(item.getLocale());
        spell.setMaxrank(item.getMaxRank());
        if(item.getModes() != null) {
            spell.setModes(new HashSet<>(item.getModes()));
        }
        spell.setName(item.getName());
        spell.setPlatform(item.getPlatform());
        if(item.getRanges() != null) {
            spell.setRange(new ArrayList<>(item.getRanges()));
            spell.setRangeBurn(getBurn(item.getRanges()));
        }
        spell.setCostType(item.getResource());
        spell.setResource(item.getResourceDescription());
        spell.setSanitizedDescription(item.getSanitizedDescription());
        spell.setSanitizedTooltip(item.getSanitizedTooltip());
        if(item.getVariables() != null) {
            final List<SpellVars> variables = new ArrayList<>(item.getVariables().size());
            for(final SpellVariables vars : item.getVariables()) {
                variables.add(transform(vars, context));
            }
            spell.setVars(variables);
        }
        spell.setVersion(item.getVersion());
        return spell;
    }

    @Transform(from = SummonerSpellList.class, to = SummonerSpells.class)
    public SummonerSpells transform(final SummonerSpellList item, final PipelineContext context) {
        final SummonerSpells spells = new SummonerSpells(item.getData().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.SummonerSpell spell : item.getData().values()) {
            spells.add(transform(spell, context));
        }
        spells.setIncludedData(new HashSet<>(item.getIncludedData()));
        spells.setLocale(item.getLocale());
        spells.setPlatform(item.getPlatform());
        spells.setType(item.getType());
        spells.setVersion(item.getVersion());
        return spells;
    }

    @Transform(from = SummonerSpells.class, to = SummonerSpellList.class)
    public SummonerSpellList transform(final SummonerSpells item, final PipelineContext context) {
        final SummonerSpellList spells = new SummonerSpellList();
        final Map<String, com.merakianalytics.orianna.types.dto.staticdata.SummonerSpell> data = new HashMap<>();
        for(final SummonerSpell spell : item) {
            data.put(Integer.toString(spell.getId()), transform(spell, context));
        }
        spells.setData(data);
        spells.setIncludedData(new HashSet<>(item.getIncludedData()));
        spells.setLocale(item.getLocale());
        spells.setPlatform(item.getPlatform());
        spells.setType(item.getType());
        spells.setVersion(item.getVersion());
        return spells;
    }

    @Transform(from = Versions.class, to = com.merakianalytics.orianna.types.dto.staticdata.Versions.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Versions transform(final Versions item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Versions versions = new com.merakianalytics.orianna.types.dto.staticdata.Versions(item.size());
        versions.addAll(item);
        versions.setPlatform(item.getPlatform());
        return versions;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree.class, to = ReforgedRuneTree.class)
    public ReforgedRuneTree transformTree(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRuneTree item, final PipelineContext context) {
        final ReforgedRuneTree tree = new ReforgedRuneTree();
        for(final com.merakianalytics.orianna.types.dto.staticdata.ReforgedRunePath path : item) {
            final RunePath p = RunePath.valueOf(path.getName().toUpperCase());
            switch(p) {
                case DOMINATION:
                    tree.setDomination(transform(path, context));
                    break;
                case INSPIRATION:
                    tree.setInspriation(transform(path, context));
                    break;
                case PRECISION:
                    tree.setPrecision(transform(path, context));
                    break;
                case RESOLVE:
                    tree.setResolve(transform(path, context));
                    break;
                case SORCERY:
                    tree.setSorcery(transform(path, context));
                    break;
                default:
                    break;
            }
        }
        return tree;
    }
}
