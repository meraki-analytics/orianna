package com.robrua.orianna.type.core.staticdata;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.robrua.orianna.type.core.OriannaObject;

public class Realm extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Realm> {
    private static final long serialVersionUID = 8896982759119804645L;
    private Map<String, String> n;

    /**
     * @param data
     *            the underlying dto
     */
    public Realm(final com.robrua.orianna.type.dto.staticdata.Realm data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Realm.class);
    }

    /**
     * The base CDN url
     *
     * @return the base CDN url.
     */
    public String getCDN() {
        return super.getString(data.getCdn());
    }

    /**
     * Latest changed version of Dragon Magic's css file
     *
     * @return latest changed version of Dragon Magic's css file
     */
    public String getCSS() {
        return super.getString(data.getCss());
    }

    /**
     * Latest changed version of Dragon Magic
     *
     * @return latest changed version of Dragon Magic
     */
    public String getDD() {
        return super.getString(data.getDd());
    }

    /**
     * Default language for this realm
     *
     * @return default language for this realm
     */
    public String getLanguage() {
        return super.getString(data.getL());
    }

    /**
     * Legacy script mode for IE6 or older
     *
     * @return legacy script mode for IE6 or older
     */
    public String getLegacy() {
        return super.getString(data.getLg());
    }

    /**
     * Special behavior number identifying the largest profileicon id that can
     * be used under 500. Any profileicon that is requested between this number
     * and 500 should be mapped to 0.
     *
     * @return special behavior number identifying the largest profileicon id
     *         that can be used under 500. Any profileicon that is requested
     *         between this number and 500 should be mapped to 0.
     */
    public int getProfileIconMax() {
        return super.getInteger(data.getProfileiconmax());
    }

    /**
     * Additional api data drawn from other sources that may be related to data
     * dragon functionality.
     *
     * @return additional api data drawn from other sources that may be related
     *         to data dragon functionality.
     */
    public String getStore() {
        return super.getString(data.getStore());
    }

    /**
     * Current version of this file for this realm.
     *
     * @return current version of this file for this realm.
     */
    public String getVersion() {
        return super.getString(data.getV());
    }

    /**
     * Latest changed version for each data type listed.
     *
     * @return latest changed version for each data type listed.
     */
    public Map<String, String> getVersions() {
        if(n == null) {
            n = new HashMap<>();
            n.putAll(data.getN());
        }

        return Collections.unmodifiableMap(n);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Realm";
    }
}
