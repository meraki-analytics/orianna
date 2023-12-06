package com.merakianalytics.orianna.types.data.championmastery;

import com.merakianalytics.orianna.types.data.CoreData;

public class ChampionMasteries extends CoreData.ListProxy<ChampionMastery> {
    private static final long serialVersionUID = 3856003080318985663L;
    private String platform, puuid;

    public ChampionMasteries() {
        super();
    }

    public ChampionMasteries(final int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(!super.equals(obj)) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final ChampionMasteries other = (ChampionMasteries)obj;
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(puuid == null) {
            if(other.puuid != null) {
                return false;
            }
        } else if(!puuid.equals(other.puuid)) {
            return false;
        }
        return true;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the puuid
     */
    public String getPuuid() {
        return puuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (puuid == null ? 0 : puuid.hashCode());
        return result;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param puuid
     *        the puuid to set
     */
    public void setPuuid(final String puuid) {
        this.puuid = puuid;
    }
}
