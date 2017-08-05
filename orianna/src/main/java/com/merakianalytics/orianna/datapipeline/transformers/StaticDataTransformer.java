package com.merakianalytics.orianna.datapipeline.transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.Platform;
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
import com.merakianalytics.orianna.types.data.staticdata.Passive;
import com.merakianalytics.orianna.types.data.staticdata.RecommendedItems;
import com.merakianalytics.orianna.types.data.staticdata.Skin;
import com.merakianalytics.orianna.types.data.staticdata.SpellVariables;
import com.merakianalytics.orianna.types.data.staticdata.Sprite;
import com.merakianalytics.orianna.types.dto.staticdata.Block;
import com.merakianalytics.orianna.types.dto.staticdata.BlockItem;
import com.merakianalytics.orianna.types.dto.staticdata.ChampionList;
import com.merakianalytics.orianna.types.dto.staticdata.Gold;
import com.merakianalytics.orianna.types.dto.staticdata.Group;
import com.merakianalytics.orianna.types.dto.staticdata.Info;
import com.merakianalytics.orianna.types.dto.staticdata.InventoryDataStats;
import com.merakianalytics.orianna.types.dto.staticdata.ItemList;
import com.merakianalytics.orianna.types.dto.staticdata.LevelTip;
import com.merakianalytics.orianna.types.dto.staticdata.Recommended;
import com.merakianalytics.orianna.types.dto.staticdata.SpellVars;

public class StaticDataTransformer extends AbstractDataTransformer {
    private static final BiMap<String, com.merakianalytics.orianna.types.common.Map> RECOMMENDED_ITEMS_MAP_CONVERSIONS = ImmutableBiMap.of("SR",
        com.merakianalytics.orianna.types.common.Map.SUMMONERS_RIFT, "TT", com.merakianalytics.orianna.types.common.Map.TWISTED_TREELINE, "HA",
        com.merakianalytics.orianna.types.common.Map.HOWLING_ABYSS, "CS", com.merakianalytics.orianna.types.common.Map.THE_CRYSTAL_SCAR);

    @Transform(from = Block.class, to = ItemSet.class)
    public ItemSet transform(final Block item, final PipelineContext context) {
        final ItemSet set = new ItemSet();
        final Map<Integer, Integer> items = new HashMap<>();
        for(final BlockItem it : item.getItems()) {
            items.put(it.getId(), it.getCount());
        }
        set.setItems(items);
        set.setRecMath(item.isRecMath());
        set.setType(item.getType());
        return set;
    }

    @Transform(from = Champion.class, to = com.merakianalytics.orianna.types.dto.staticdata.Champion.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Champion transform(final Champion item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Champion champion = new com.merakianalytics.orianna.types.dto.staticdata.Champion();
        champion.setAllytips(new ArrayList<>(item.getAllyTips()));
        champion.setBlurb(item.getBlurb());
        final Info info = new Info();
        info.setAttack(item.getPhysicalRating());
        info.setDefense(item.getDefenseRating());
        info.setDifficulty(item.getDifficultyRating());
        info.setMagic(item.getMagicRating());
        champion.setInfo(info);
        champion.setEnemytips(new ArrayList<>(item.getEnemyTips()));
        champion.setId(item.getId());
        champion.setImage(transform(item.getImage(), context));
        champion.setIncludedData(new HashSet<>(item.getIncludedData()));
        champion.setKey(item.getKey());
        champion.setLocale(item.getLocale());
        champion.setLore(item.getLore());
        champion.setName(item.getName());
        champion.setPassive(transform(item.getPassive(), context));
        champion.setPlatform(item.getPlatform().getTag());
        final List<Recommended> items = new ArrayList<>(item.getRecommendedItems().size());
        for(final RecommendedItems rec : item.getRecommendedItems()) {
            items.add(transform(rec, context));
        }
        champion.setRecommended(items);
        champion.setPartype(item.getResource());
        final List<com.merakianalytics.orianna.types.dto.staticdata.Skin> skins = new ArrayList<>(item.getSkins().size());
        for(final Skin skin : item.getSkins()) {
            skins.add(transform(skin, context));
        }
        champion.setSkins(skins);
        final List<com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell> spells = new ArrayList<>(item.getSpells().size());
        for(final ChampionSpell spell : item.getSpells()) {
            spells.add(transform(spell, context));
        }
        champion.setSpells(spells);
        champion.setStats(transform(item.getStats(), context));
        champion.setTags(new ArrayList<>(item.getTags()));
        champion.setTitle(item.getTitle());
        champion.setVersion(item.getVersion());
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
        champions.setPlatform(Platform.withTag(item.getPlatform()));
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
        champions.setPlatform(item.getPlatform().getTag());
        champions.setType(item.getType());
        champions.setVersion(item.getVersion());
        return champions;
    }

    @Transform(from = ChampionSpell.class, to = com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell.class)
    public com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell transform(final ChampionSpell item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell spell = new com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell();
        final List<com.merakianalytics.orianna.types.dto.staticdata.Image> alternativeImages = new ArrayList<>(item.getAlternativeImages().size());
        for(final Image image : item.getAlternativeImages()) {
            alternativeImages.add(transform(image, context));
        }
        spell.setAltImages(alternativeImages);
        spell.setCooldown(new ArrayList<>(item.getCooldowns()));
        spell.setCost(new ArrayList<>(item.getCosts()));
        spell.setDescription(item.getDescription());
        final List<List<Double>> effects = new ArrayList<>(item.getEffects().size());
        for(final List<Double> effect : item.getEffects()) {
            effects.add(new ArrayList<>(effect));
        }
        spell.setEffect(effects);
        spell.setImage(transform(item.getImage(), context));
        spell.setKey(item.getKey());
        final LevelTip tip = new LevelTip();
        tip.setLabel(new ArrayList<>(item.getLevelUpKeywords()));
        tip.setEffect(new ArrayList<>(item.getLevelUpEffects()));
        spell.setLeveltip(tip);
        spell.setMaxrank(item.getMaxRank());
        spell.setName(item.getName());
        spell.setRange(new ArrayList<>(item.getRanges()));
        spell.setCostType(item.getResource());
        spell.setResource(item.getResourceDescription());
        spell.setSanitizedDescription(item.getSanitizedDescription());
        spell.setSanitizedTooltip(item.getSanitizedTooltip());
        spell.setTooltip(item.getTooltip());
        final List<SpellVars> variables = new ArrayList<>(item.getVariables().size());
        for(final SpellVariables vars : item.getVariables()) {
            variables.add(transform(vars, context));
        }
        spell.setVars(variables);
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

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Champion.class, to = Champion.class)
    public Champion transform(final com.merakianalytics.orianna.types.dto.staticdata.Champion item, final PipelineContext context) {
        final Champion champion = new Champion();
        champion.setAllyTips(new ArrayList<>(item.getAllytips()));
        champion.setBlurb(item.getBlurb());
        champion.setDefenseRating(item.getInfo().getDefense());
        champion.setDifficultyRating(item.getInfo().getDifficulty());
        champion.setEnemyTips(new ArrayList<>(item.getEnemytips()));
        champion.setId(item.getId());
        champion.setImage(transform(item.getImage(), context));
        champion.getImage().setVersion(item.getVersion());
        champion.getImage().getSprite().setVersion(item.getVersion());
        champion.setIncludedData(new HashSet<>(item.getIncludedData()));
        champion.setKey(item.getKey());
        champion.setLocale(item.getLocale());
        champion.setLore(item.getLore());
        champion.setMagicRating(item.getInfo().getMagic());
        champion.setName(item.getName());
        champion.setPassive(transform(item.getPassive(), context));
        champion.setPhysicalRating(item.getInfo().getAttack());
        champion.setPlatform(Platform.withTag(item.getPlatform()));
        final List<RecommendedItems> items = new ArrayList<>(item.getRecommended().size());
        for(final Recommended rec : item.getRecommended()) {
            items.add(transform(rec, context));
        }
        champion.setRecommendedItems(items);
        champion.setResource(item.getPartype());
        final List<Skin> skins = new ArrayList<>(item.getSkins().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.Skin skin : item.getSkins()) {
            skins.add(transform(skin, context));
        }
        champion.setSkins(skins);
        final List<ChampionSpell> spells = new ArrayList<>(item.getSpells().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell spell : item.getSpells()) {
            spells.add(transform(spell, context));
        }
        champion.setSpells(spells);
        champion.setStats(transform(item.getStats(), context));
        champion.setTags(new ArrayList<>(item.getTags()));
        champion.setTitle(item.getTitle());
        champion.setVersion(item.getVersion());
        return champion;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell.class, to = ChampionSpell.class)
    public ChampionSpell transform(final com.merakianalytics.orianna.types.dto.staticdata.ChampionSpell item, final PipelineContext context) {
        final ChampionSpell spell = new ChampionSpell();
        final List<Image> alternativeImages = new ArrayList<>(item.getAltImages().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.Image image : item.getAltImages()) {
            alternativeImages.add(transform(image, context));
        }
        spell.setAlternativeImages(alternativeImages);
        spell.setCooldowns(new ArrayList<>(item.getCooldown()));
        spell.setCosts(new ArrayList<>(item.getCost()));
        spell.setDescription(item.getDescription());
        final List<List<Double>> effects = new ArrayList<>(item.getEffect().size());
        for(final List<Double> effect : item.getEffect()) {
            effects.add(new ArrayList<>(effect));
        }
        spell.setEffects(effects);
        spell.setImage(transform(item.getImage(), context));
        spell.setKey(item.getKey());
        spell.setLevelUpKeywords(new ArrayList<>(item.getLeveltip().getLabel()));
        spell.setLevelUpEffects(new ArrayList<>(item.getLeveltip().getEffect()));
        spell.setMaxRank(item.getMaxrank());
        spell.setName(item.getName());
        spell.setRanges(new ArrayList<>(item.getRange()));
        spell.setResource(item.getCostType());
        spell.setResourceDescription(item.getResource());
        spell.setSanitizedDescription(item.getSanitizedDescription());
        spell.setSanitizedTooltip(item.getSanitizedTooltip());
        spell.setTooltip(item.getTooltip());
        final List<SpellVariables> variables = new ArrayList<>(item.getVars().size());
        for(final SpellVars vars : item.getVars()) {
            variables.add(transform(vars, context));
        }
        spell.setVariables(variables);
        return spell;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Image.class, to = Image.class)
    public Image transform(final com.merakianalytics.orianna.types.dto.staticdata.Image item, final PipelineContext context) {
        final Image image = new Image();
        image.setFull(item.getFull());
        image.setGroup(item.getGroup());
        final Sprite sprite = new Sprite();
        sprite.setFull(item.getSprite());
        sprite.setHeight(item.getH());
        sprite.setWidth(item.getW());
        sprite.setX(item.getX());
        sprite.setY(item.getY());
        image.setSprite(sprite);
        return image;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Item.class, to = Item.class)
    public Item transform(final com.merakianalytics.orianna.types.dto.staticdata.Item item, final PipelineContext context) {
        final Item converted = new Item();
        converted.setBasePrice(item.getGold().getBase());
        final List<Integer> buildsFrom = new ArrayList<>(item.getFrom().size());
        for(final String id : item.getFrom()) {
            buildsFrom.add(Integer.parseInt(id));
        }
        converted.setBuildsFrom(buildsFrom);
        final List<Integer> buildsInto = new ArrayList<>(item.getInto().size());
        for(final String id : item.getInto()) {
            buildsInto.add(Integer.parseInt(id));
        }
        converted.setBuildsInto(buildsInto);
        converted.setConsumed(item.isConsumed());
        converted.setConsumedWhenFull(item.isConsumeOnFull());
        converted.setDescription(item.getDescription());
        converted.setEffects(new HashMap<>(item.getEffect()));
        converted.setGroup(item.getGroup());
        converted.setHiddenFromAll(item.isHideFromAll());
        converted.setId(item.getId());
        converted.setImage(transform(item.getImage(), context));
        converted.getImage().setVersion(item.getVersion());
        converted.getImage().getSprite().setVersion(item.getVersion());
        converted.setIncludedData(new HashSet<>(item.getIncludedData()));
        converted.setInStore(item.isInStore());
        final Set<String> keywords = new HashSet<>();
        for(final String keyword : item.getColloq().split(";")) {
            keywords.add(keyword);
        }
        converted.setKeywords(keywords);
        converted.setLocale(item.getLocale());
        final Set<com.merakianalytics.orianna.types.common.Map> maps = new HashSet<>();
        for(final String id : item.getMaps().keySet()) {
            if(item.getMaps().get(id)) {
                maps.add(com.merakianalytics.orianna.types.common.Map.withId(Integer.parseInt(id)));
            }
        }
        converted.setMaps(maps);
        converted.setMaxStacks(item.getStacks());
        converted.setName(item.getName());
        converted.setPlaintext(item.getPlaintext());
        converted.setPlatform(Platform.withTag(item.getPlatform()));
        converted.setPurchasable(item.getGold().isPurchasable());
        converted.setRequiredChampionKey(item.getRequiredChampion());
        converted.setSanitizedDescription(item.getSanitizedDescription());
        converted.setSellPrice(item.getGold().getSell());
        converted.setSource(item.getSpecialRecipe());
        converted.setTags(new ArrayList<>(item.getTags()));
        converted.setTier(item.getDepth());
        converted.setTotalPrice(item.getGold().getTotal());
        converted.setVersion(item.getVersion());
        return converted;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.ItemTree.class, to = ItemTree.class)
    public ItemTree transform(final com.merakianalytics.orianna.types.dto.staticdata.ItemTree item, final PipelineContext context) {
        final ItemTree tree = new ItemTree();
        tree.setHeader(item.getHeader());
        tree.setTags(new ArrayList<>(item.getTags()));
        return tree;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Languages.class, to = Languages.class)
    public Languages transform(final com.merakianalytics.orianna.types.dto.staticdata.Languages item, final PipelineContext context) {
        final Languages languages = new Languages();
        languages.addAll(item);
        languages.setPlatform(Platform.withTag(item.getPlatform()));
        return languages;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings.class, to = LanguageStrings.class)
    public LanguageStrings transform(final com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings item, final PipelineContext context) {
        final LanguageStrings strings = new LanguageStrings();
        strings.putAll(item.getData());
        strings.setLocale(item.getLocale());
        strings.setPlatform(Platform.withTag(item.getPlatform()));
        strings.setType(item.getType());
        strings.setVersion(item.getVersion());
        return strings;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Passive.class, to = Passive.class)
    public Passive transform(final com.merakianalytics.orianna.types.dto.staticdata.Passive item, final PipelineContext context) {
        final Passive passive = new Passive();
        passive.setDescription(item.getDescription());
        passive.setImage(transform(item.getImage(), context));
        passive.setName(item.getName());
        passive.setSanitizedDescription(item.getSanitizedDescription());
        return passive;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.staticdata.Skin.class, to = Skin.class)
    public Skin transform(final com.merakianalytics.orianna.types.dto.staticdata.Skin item, final PipelineContext context) {
        final Skin skin = new Skin();
        skin.setId(item.getId());
        skin.setName(item.getName());
        skin.setNumber(item.getNum());
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

    @Transform(from = Group.class, to = ItemGroup.class)
    public ItemGroup transform(final Group item, final PipelineContext context) {
        final ItemGroup group = new ItemGroup();
        group.setKey(item.getKey());
        group.setMax(Integer.parseInt(item.getMaxGroupOwnable()));
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
        stats.setPercentLifeStreal(item.getPercentLifeStealMod());
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
        final List<String> from = new ArrayList<>(item.getBuildsFrom().size());
        for(final Integer id : item.getBuildsFrom()) {
            from.add(id.toString());
        }
        converted.setFrom(from);
        final List<String> into = new ArrayList<>(item.getBuildsInto().size());
        for(final Integer id : item.getBuildsInto()) {
            into.add(id.toString());
        }
        converted.setInto(into);
        converted.setConsumed(item.isConsumed());
        converted.setConsumeOnFull(item.isConsumedWhenFull());
        converted.setDescription(item.getDescription());
        converted.setEffect(new HashMap<>(item.getEffects()));
        converted.setGroup(item.getGroup());
        converted.setHideFromAll(item.isHiddenFromAll());
        converted.setId(item.getId());
        converted.setImage(transform(item.getImage(), context));
        converted.setIncludedData(new HashSet<>(item.getIncludedData()));
        converted.setInStore(item.isInStore());
        converted.setColloq(Joiner.on(";").join(item.getKeywords()));
        converted.setLocale(item.getLocale());
        final Map<String, Boolean> maps = new HashMap<>();
        for(final com.merakianalytics.orianna.types.common.Map map : com.merakianalytics.orianna.types.common.Map.values()) {
            maps.put(Integer.toString(map.getId()), item.getMaps().contains(map));
        }
        converted.setMaps(maps);
        converted.setStacks(item.getMaxStacks());
        converted.setName(item.getName());
        converted.setPlaintext(item.getPlaintext());
        converted.setPlatform(item.getPlatform().getTag());
        converted.setRequiredChampion(item.getRequiredChampionKey());
        converted.setSanitizedDescription(item.getSanitizedDescription());
        converted.setSpecialRecipe(item.getSource());
        converted.setTags(new ArrayList<>(item.getTags()));
        converted.setDepth(item.getTier());
        converted.setVersion(item.getVersion());
        return converted;
    }

    @Transform(from = ItemGroup.class, to = Group.class)
    public Group transform(final ItemGroup item, final PipelineContext context) {
        final Group group = new Group();
        group.setKey(item.getKey());
        group.setMaxGroupOwnable(Integer.toString(item.getMax()));
        return group;
    }

    @Transform(from = ItemList.class, to = Items.class)
    public Items transform(final ItemList item, final PipelineContext context) {
        final Items items = new Items(item.getData().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.Item it : item.getData().values()) {
            items.add(transform(it, context));
        }
        final List<ItemGroup> groups = new ArrayList<>(item.getGroups().size());
        for(final Group group : item.getGroups()) {
            groups.add(transform(group, context));
        }
        items.setGroups(groups);
        items.setIncludedData(new HashSet<>(item.getIncludedData()));
        items.setLocale(item.getLocale());
        items.setPlatform(Platform.withTag(item.getPlatform()));
        final List<ItemTree> tree = new ArrayList<>(item.getTree().size());
        for(final com.merakianalytics.orianna.types.dto.staticdata.ItemTree t : item.getTree()) {
            tree.add(transform(t, context));
        }
        items.setTree(tree);
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
        final List<Group> groups = new ArrayList<>(item.getGroups().size());
        for(final ItemGroup group : item.getGroups()) {
            groups.add(transform(group, context));
        }
        items.setGroups(groups);
        items.setIncludedData(new HashSet<>(item.getIncludedData()));
        items.setLocale(item.getLocale());
        items.setPlatform(item.getPlatform().getTag());
        final List<com.merakianalytics.orianna.types.dto.staticdata.ItemTree> tree = new ArrayList<>(item.getTree().size());
        for(final ItemTree t : item.getTree()) {
            tree.add(transform(t, context));
        }
        items.setTree(tree);
        items.setType(item.getType());
        items.setVersion(item.getVersion());
        return items;
    }

    @Transform(from = ItemSet.class, to = Block.class)
    public Block transform(final ItemSet item, final PipelineContext context) {
        final Block block = new Block();
        final List<BlockItem> items = new ArrayList<>(item.getItems().size());
        for(final Integer itemId : item.getItems().keySet()) {
            final BlockItem it = new BlockItem();
            it.setCount(item.getItems().get(itemId));
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
        stats.setPercentLifeStealMod(item.getPercentLifeStreal());
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
        tree.setTags(new ArrayList<>(item.getTags()));
        return tree;
    }

    @Transform(from = Languages.class, to = com.merakianalytics.orianna.types.dto.staticdata.Languages.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Languages transform(final Languages item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Languages languages = new com.merakianalytics.orianna.types.dto.staticdata.Languages();
        languages.addAll(item);
        languages.setPlatform(item.getPlatform().getTag());
        return languages;
    }

    @Transform(from = LanguageStrings.class, to = com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings.class)
    public com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings transform(final LanguageStrings item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings strings = new com.merakianalytics.orianna.types.dto.staticdata.LanguageStrings();
        strings.setData(new HashMap<>(item));
        strings.setLocale(item.getLocale());
        strings.setPlatform(item.getPlatform().getTag());
        strings.setType(item.getType());
        strings.setVersion(item.getVersion());
        return strings;
    }

    @Transform(from = Passive.class, to = com.merakianalytics.orianna.types.dto.staticdata.Passive.class)
    public com.merakianalytics.orianna.types.dto.staticdata.Passive transform(final Passive item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Passive passive = new com.merakianalytics.orianna.types.dto.staticdata.Passive();
        passive.setDescription(item.getDescription());
        passive.setImage(transform(item.getImage(), context));
        passive.setName(item.getName());
        passive.setSanitizedDescription(item.getSanitizedDescription());
        return passive;
    }

    @Transform(from = Recommended.class, to = RecommendedItems.class)
    public RecommendedItems transform(final Recommended item, final PipelineContext context) {
        final RecommendedItems items = new RecommendedItems(item.getBlocks().size());
        for(final Block block : item.getBlocks()) {
            items.add(transform(block, context));
        }
        items.setChampionKey(item.getChampion());
        items.setMap(RECOMMENDED_ITEMS_MAP_CONVERSIONS.get(item.getMap()));
        items.setMode(GameMode.valueOf(item.getMode()));
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
        items.setChampion(item.getChampionKey());
        items.setMap(RECOMMENDED_ITEMS_MAP_CONVERSIONS.inverse().get(item.getMap()));
        items.setMode(item.getMode().toString());
        items.setPriority(item.isPriority());
        items.setTitle(item.getTitle());
        items.setType(item.getType());
        return items;
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
}
