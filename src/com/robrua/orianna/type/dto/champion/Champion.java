package com.robrua.orianna.type.dto.champion;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity(name = "champion.Champion")
@Table(name = "championstatus")
public class Champion extends OriannaDto {
    private static final long serialVersionUID = 6532858269176881617L;
    private Boolean active, botEnabled, botMmEnabled, freeToPlay, rankedPlayEnabled;

    @Id
    private Long id;

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
        if(!(obj instanceof Champion)) {
            return false;
        }
        final Champion other = (Champion)obj;
        if(active == null) {
            if(other.active != null) {
                return false;
            }
        }
        else if(!active.equals(other.active)) {
            return false;
        }
        if(botEnabled == null) {
            if(other.botEnabled != null) {
                return false;
            }
        }
        else if(!botEnabled.equals(other.botEnabled)) {
            return false;
        }
        if(botMmEnabled == null) {
            if(other.botMmEnabled != null) {
                return false;
            }
        }
        else if(!botMmEnabled.equals(other.botMmEnabled)) {
            return false;
        }
        if(freeToPlay == null) {
            if(other.freeToPlay != null) {
                return false;
            }
        }
        else if(!freeToPlay.equals(other.freeToPlay)) {
            return false;
        }
        if(id == null) {
            if(other.id != null) {
                return false;
            }
        }
        else if(!id.equals(other.id)) {
            return false;
        }
        if(rankedPlayEnabled == null) {
            if(other.rankedPlayEnabled != null) {
                return false;
            }
        }
        else if(!rankedPlayEnabled.equals(other.rankedPlayEnabled)) {
            return false;
        }
        return true;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @return the botEnabled
     */
    public Boolean getBotEnabled() {
        return botEnabled;
    }

    /**
     * @return the botMmEnabled
     */
    public Boolean getBotMmEnabled() {
        return botMmEnabled;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "id";
        }
        return null;
    }

    /**
     * @return the freeToPlay
     */
    public Boolean getFreeToPlay() {
        return freeToPlay;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the rankedPlayEnabled
     */
    public Boolean getRankedPlayEnabled() {
        return rankedPlayEnabled;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active == null ? 0 : active.hashCode());
        result = prime * result + (botEnabled == null ? 0 : botEnabled.hashCode());
        result = prime * result + (botMmEnabled == null ? 0 : botMmEnabled.hashCode());
        result = prime * result + (freeToPlay == null ? 0 : freeToPlay.hashCode());
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (rankedPlayEnabled == null ? 0 : rankedPlayEnabled.hashCode());
        return result;
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(final Boolean active) {
        this.active = active;
    }

    /**
     * @param botEnabled
     *            the botEnabled to set
     */
    public void setBotEnabled(final Boolean botEnabled) {
        this.botEnabled = botEnabled;
    }

    /**
     * @param botMmEnabled
     *            the botMmEnabled to set
     */
    public void setBotMmEnabled(final Boolean botMmEnabled) {
        this.botMmEnabled = botMmEnabled;
    }

    /**
     * @param freeToPlay
     *            the freeToPlay to set
     */
    public void setFreeToPlay(final Boolean freeToPlay) {
        this.freeToPlay = freeToPlay;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param rankedPlayEnabled
     *            the rankedPlayEnabled to set
     */
    public void setRankedPlayEnabled(final Boolean rankedPlayEnabled) {
        this.rankedPlayEnabled = rankedPlayEnabled;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Champion [active=" + active + ", botEnabled=" + botEnabled + ", botMmEnabled=" + botMmEnabled + ", freeToPlay=" + freeToPlay
                + ", rankedPlayEnabled=" + rankedPlayEnabled + ", id=" + id + "]";
    }
}
