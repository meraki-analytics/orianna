package com.robrua.orianna.type.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.champion.ChampionStatus;
import com.robrua.orianna.type.core.championmastery.ChampionMastery;
import com.robrua.orianna.type.core.summoner.Summoner;

public class Champion extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Champion> {
    private static final long serialVersionUID = -1285569935446443640L;
    private List<String> allyTips, enemyTips, tags;
    private Image image;
    private Info info;
    private Passive passive;
    private List<RecommendedItems> recommended;
    private List<Skin> skins;
    private List<ChampionSpell> spells;
    private Stats stats;

    /**
     * @param data
     *            the underlying dto
     */
    public Champion(final com.robrua.orianna.type.dto.staticdata.Champion data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Champion.class);
    }

    /**
     * Ally tips
     *
     * @return ally tips
     */
    public List<String> getAllyTips() {
        if(allyTips == null) {
            allyTips = new ArrayList<>();
            allyTips.addAll(data.getAllytips());
        }

        return Collections.unmodifiableList(allyTips);
    }

    /**
     * Blurb
     *
     * @return blurb
     */
    public String getBlurb() {
        return super.getString(data.getBlurb());
    }

    /**
     * Gets a summoner's mastery on this champion
     *
     * @param summoner
     *            the summoner to get rating for
     * @return the summoner's champion mastery rating
     */
    public ChampionMastery getChampionMastery(final Summoner summoner) {
        return RiotAPI.getChampionMastery(summoner.getID(), getID());
    }

    /**
     * Enemy tips
     *
     * @return enemy tips
     */
    public List<String> getEnemyTips() {
        if(enemyTips == null) {
            enemyTips = new ArrayList<>();
            enemyTips.addAll(data.getEnemytips());
        }

        return Collections.unmodifiableList(enemyTips);
    }

    /**
     * ID
     *
     * @return ID
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
     * Info
     *
     * @return info
     */
    public Info getInfo() {
        if(info == null) {
            info = new Info(data.getInfo());
        }

        return info;
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
     * Lore
     *
     * @return lore
     */
    public String getLore() {
        return super.getString(data.getLore());
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
     * Partype
     *
     * @return partype
     */
    public String getPartype() {
        return super.getString(data.getPartype());
    }

    /**
     * Passive
     *
     * @return passive
     */
    public Passive getPassive() {
        if(passive == null) {
            passive = new Passive(data.getPassive());
        }

        return passive;
    }

    /**
     * Recommended items
     *
     * @return recommended items
     */
    public List<RecommendedItems> getRecommendedItems() {
        if(recommended == null) {
            recommended = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.staticdata.Recommended rec : data.getRecommended()) {
                recommended.add(new RecommendedItems(rec));
            }
        }

        return Collections.unmodifiableList(recommended);
    }

    /**
     * Skins
     *
     * @return skins
     */
    public List<Skin> getSkins() {
        if(skins == null) {
            skins = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.staticdata.Skin skin : data.getSkins()) {
                skins.add(new Skin(skin));
            }
        }

        return Collections.unmodifiableList(skins);
    }

    /**
     * Spells
     *
     * @return spells
     */
    public List<ChampionSpell> getSpells() {
        if(spells == null) {
            spells = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.staticdata.ChampionSpell spell : data.getSpells()) {
                spells.add(new ChampionSpell(spell));
            }
        }

        return Collections.unmodifiableList(spells);
    }

    /**
     * Stats
     *
     * @return stats
     */
    public Stats getStats() {
        if(stats == null) {
            stats = new Stats(data.getStats());
        }

        return stats;
    }

    /**
     * Status
     *
     * @return status
     */
    public ChampionStatus getStatus() {
        return RiotAPI.getChampionStatus(getID());
    }

    /**
     * Tags
     *
     * @return tags
     */
    public List<String> getTags() {
        if(tags == null) {
            tags = new ArrayList<>();
            tags.addAll(data.getTags());
        }

        return Collections.unmodifiableList(tags);
    }

    /**
     * Title
     *
     * @return title
     */
    public String getTitle() {
        return super.getString(data.getTitle());
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
