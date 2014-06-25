package lib.orianna.type.staticdata;

import java.io.Serializable;
import java.util.List;

import lib.orianna.api.RiotAPI;
import lib.orianna.type.champion.ChampionStatus;

public class Champion implements Serializable {
    private static final long serialVersionUID = 417353393384473862L;
    public final List<String> allyTips, enemyTips, tags;
    public final String blurb, key, lore, name, partype, title;
    public final Integer ID;
    public final Image image;
    public final Information info;
    public final Passive passive;
    public final List<Recommended> recommended;
    public final List<Skin> skins;
    public final List<ChampionSpell> spells;
    public final Stats stats;

    public Champion(final List<String> allyTips, final List<String> enemyTips, final List<String> tags, final String blurb, final String key,
            final String lore, final String name, final String partype, final String title, final Integer ID, final Image image, final Information info,
            final Passive passive, final List<Recommended> recommended, final List<Skin> skins, final List<ChampionSpell> spells, final Stats stats) {
        this.allyTips = allyTips;
        this.enemyTips = enemyTips;
        this.tags = tags;
        this.blurb = blurb;
        this.key = key;
        this.lore = lore;
        this.name = name;
        this.partype = partype;
        this.title = title;
        this.ID = ID;
        this.image = image;
        this.info = info;
        this.passive = passive;
        this.recommended = recommended;
        this.skins = skins;
        this.spells = spells;
        this.stats = stats;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Champion)) {
            return false;
        }
        final Champion other = (Champion)obj;
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

    /**
     * @param API
     *            the API to get the data with
     * @return the champion's status
     * @see <a href="http://developer.riotgames.com/api/methods#!/617/1922">LoL
     *      API Specification</a>
     */
    public ChampionStatus getStatus(final RiotAPI API) {
        return API.getChampionStatus(this);
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
