package com.merakianalytics.orianna.types.data.staticdata;

import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.CoreData;

public class ItemSet extends CoreData.MapProxy<Integer, Integer> {
    private static final long serialVersionUID = 4668994735378556124L;
    private String locale;
    private Platform platform;
    private boolean recMath;
    private String type;
    private String version;

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the recMath
     */
    public boolean isRecMath() {
        return recMath;
    }

    /**
     * @param locale
     *        the locale to set
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    /**
     * @param recMath
     *        the recMath to set
     */
    public void setRecMath(final boolean recMath) {
        this.recMath = recMath;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * @param version
     *        the version to set
     */
    public void setVersion(final String version) {
        this.version = version;
    }
}
