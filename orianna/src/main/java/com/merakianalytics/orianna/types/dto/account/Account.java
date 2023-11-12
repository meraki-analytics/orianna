package com.merakianalytics.orianna.types.dto.account;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Account extends DataObject {
    private String gameName, tagLne, puuid, platform;

    /**
     * @return the game name
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * @return the tag line
     */
    public String getTagLine() {
        return tagLne;
    }

    /**
     * @return the puuid
     */
    public String getPuuid() {
        return puuid;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param tagLne
     *        the tagline to set
     */

    public void setTagLine(String tagLne) {
        this.tagLne = tagLne;
    }

    /**
     * @param gameName
     *        the game name to set
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * @param puuid
     *        the puuid to set
     */
    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
}
