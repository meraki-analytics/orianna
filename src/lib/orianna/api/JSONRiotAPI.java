package lib.orianna.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.easyjava.net.rest.SimpleRESTClient;
import lib.orianna.api.queryspecs.ChampionData;
import lib.orianna.api.queryspecs.ItemData;
import lib.orianna.api.queryspecs.MasteryData;
import lib.orianna.api.queryspecs.Region;
import lib.orianna.api.queryspecs.RuneData;
import lib.orianna.api.queryspecs.Season;
import lib.orianna.api.queryspecs.SummonerSpellData;
import lib.orianna.type.league.LeagueType;

/**
 * Queries the <a href="http://developer.riotgames.com/api/methods">LoL REST
 * API</a> for information, returning the JSON strings it receives from the API.
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class JSONRiotAPI {
    private static final Map<String, String> APIVersions;
    private final static Pattern IOEXCEPTION_PATTERN = Pattern.compile("Server returned HTTP response code: (.*?) for URL:? (.*)");

    static {
        APIVersions = new HashMap<String, String>();
        APIVersions.put("champion", "v1.2");
        APIVersions.put("game", "v1.3");
        APIVersions.put("league", "v2.5");
        APIVersions.put("static-data", "v1.2");
        APIVersions.put("match", "v2.2");
        APIVersions.put("matchhistory", "v2.2");
        APIVersions.put("stats", "v1.3");
        APIVersions.put("summoner", "v1.4");
        APIVersions.put("team", "v2.4");
    }

    private static String getCollectionString(final Collection<?> items) {
        final StringBuilder string = new StringBuilder();
        for(final Object item : items) {
            string.append("," + item.toString());
        }

        return string.substring(1);
    }

    private static String getNameCollectionString(final Collection<String> names) {
        final StringBuilder string = new StringBuilder();
        for(final String name : names) {
            string.append("," + name.toLowerCase().replaceAll("\\s", ""));
        }

        return string.substring(1);
    }

    private static APIException handleIOException(final IOException e) {
        final Matcher matcher = IOEXCEPTION_PATTERN.matcher(e.getMessage());
        if(matcher.find()) {
            final String URL = matcher.group(2);
            final int responseCode = Integer.parseInt(matcher.group(1));
            switch(responseCode) {
                case 400:
                    return new APIException(APIException.Type.BAD_REQUEST, URL, e);
                case 401:
                    return new APIException(APIException.Type.UNAUTHORIZED, URL, e);
                case 404:
                    return new APIException(APIException.Type.DATA_NOT_FOUND, URL, e);
                case 429:
                    return new APIException(APIException.Type.RATE_LIMITED, URL, e);
                case 500:
                    return new APIException(APIException.Type.SERVER_ERROR, URL, e);
                case 503:
                    return new APIException(APIException.Type.UNAVAILABLE, URL, e);
                default:
                    return new APIException(APIException.Type.UNKNOWN, URL, e);
            }
        }
        return new APIException(APIException.Type.UNKNOWN, e);
    }

    private final String APIKey;
    private final SimpleRESTClient dynamicData;
    private final RateLimiter rateLimiter;
    private final SimpleRESTClient staticData;

    /**
     * @param mirror
     *            the data server to query
     * @param forRegion
     *            the region for which to query data
     * @param APIKey
     *            your LoL API key
     */
    public JSONRiotAPI(final Region mirror, final Region forRegion, final String APIKey) {
        this(mirror, forRegion, APIKey, null);
    }

    /**
     * @param mirror
     *            the data server to query
     * @param forRegion
     *            the region for which to query data
     * @param APIKey
     *            your LoL API key
     * @param rateLimiter
     *            queries will be throttled automatically to meet this rate
     *            limit
     */
    public JSONRiotAPI(final Region mirror, final Region forRegion, final String APIKey, final RateLimiter rateLimiter) {
        this.APIKey = APIKey;
        this.rateLimiter = rateLimiter;

        final String regionName = forRegion.toString().toLowerCase();
        staticData = new SimpleRESTClient("https://global.api.pvp.net/api/lol/static-data/" + regionName + "/");
        dynamicData = new SimpleRESTClient("https://" + mirror.toString().toLowerCase() + ".api.pvp.net/api/lol/" + regionName + "/");
    }

    /**
     * @param forRegion
     *            the region for which to query data
     * @param APIKey
     *            your LoL API key
     */
    public JSONRiotAPI(final Region forRegion, final String APIKey) {
        this(forRegion, forRegion, APIKey, null);
    }

    /**
     * @param forRegion
     *            the region for which to query data
     * @param APIKey
     *            your LoL API key
     * @param rateLimiter
     *            queries will be throttled automatically to meet this rate
     *            limit
     */
    public JSONRiotAPI(final Region forRegion, final String APIKey, final RateLimiter rateLimiter) {
        this(forRegion, forRegion, APIKey, rateLimiter);
    }

    private Map<String, String> baseParams() {
        final HashMap<String, String> params = new HashMap<String, String>();
        params.put("api_key", APIKey);
        return params;
    }

    /**
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2637">LoL
     *      API Specification</a>
     */
    public String getChallengerLeague() {
        return getChallengerLeague(LeagueType.RANKED_SOLO_5x5);
    }

    /**
     * @param type
     *            the queue for which to get the Challenger league
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2637">LoL
     *      API Specification</a>
     */
    public String getChallengerLeague(final LeagueType type) {
        final Map<String, String> params = baseParams();
        params.put("type", type.toString());

        return waitForRateLimiter(APIVersions.get("league") + "/league/challenger", params);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2526">LoL
     *      API Specification</a>
     */
    public String getChampion(final int ID) {
        return getChampion(ID, null, null, null);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param champData
     *            the additional information to get about the champion
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2526">LoL
     *      API Specification</a>
     */
    public String getChampion(final int ID, final Set<ChampionData> champData) {
        return getChampion(ID, champData, null, null);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param champData
     *            the additional information to get about the champion
     * @param locale
     *            the locale to specify
     * @param version
     *            the data version number
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2526">LoL
     *      API Specification</a>
     *
     */
    public String getChampion(final int ID, final Set<ChampionData> champData, final String locale, final String version) {
        final Map<String, String> params = baseParams();
        if(champData != null) {
            params.put("champData", getCollectionString(champData));
        }
        if(locale != null) {
            params.put("locale", locale);
        }
        if(version != null) {
            params.put("version", version);
        }

        try {
            return staticData.get(APIVersions.get("static-data") + "/champion/" + ID, params);
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2529">LoL
     *      API Specification</a>
     */
    public String getChampions() {
        return getChampions(null, null, null, true);
    }

    /**
     * @param champData
     *            the additional information to get about all champions
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2529">LoL
     *      API Specification</a>
     */
    public String getChampions(final Set<ChampionData> champData) {
        return getChampions(champData, null, null, true);
    }

    /**
     * @param champData
     *            the additional information to get about all champions
     * @param byChampionName
     *            whether the results should be organized by name or not (if
     *            not, will be by ID)
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2529">LoL
     *      API Specification</a>
     */
    public String getChampions(final Set<ChampionData> champData, final boolean byChampionName) {
        return getChampions(champData, null, null, byChampionName);
    }

    /**
     * @param champData
     *            the additional information to get about all champions
     * @param locale
     *            the locale to specify
     * @param version
     *            the data version number
     * @param byChampionName
     *            whether the results should be organized by name or not (if
     *            not, will be by ID)
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2529">LoL
     *      API Specification</a>
     */
    public String getChampions(final Set<ChampionData> champData, final String locale, final String version, final boolean byChampionName) {
        final Map<String, String> params = baseParams();
        if(champData != null) {
            params.put("champData", getCollectionString(champData));
        }
        if(locale != null) {
            params.put("locale", locale);
        }
        if(version != null) {
            params.put("version", version);
        }
        params.put("dataById", Boolean.toString(!byChampionName));

        try {
            return staticData.get(APIVersions.get("static-data") + "/champion", params);
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @param ID
     *            the ID to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/617/1922">LoL
     *      API Specification</a>
     */
    public String getChampionStatus(final int ID) {
        return waitForRateLimiter(APIVersions.get("champion") + "/champion/" + ID, baseParams());
    }

    /**
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/617/1923">LoL
     *      API Specification</a>
     */
    public String getChampionStatuses() {
        return getChampionStatuses(false);
    }

    /**
     * @param onlyFreeToPlay
     *            whether to only show statuses for free to play champions
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/617/1923">LoL
     *      API Specification</a>
     */
    public String getChampionStatuses(final boolean onlyFreeToPlay) {
        final Map<String, String> params = baseParams();
        params.put("freeToPlay", Boolean.toString(onlyFreeToPlay));

        return waitForRateLimiter(APIVersions.get("champion") + "/champion", params);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2534">LoL
     *      API Specification</a>
     */
    public String getItem(final int ID) {
        return getItem(ID, null, null, null);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param itemData
     *            the additional information to get about the item
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2534">LoL
     *      API Specification</a>
     */
    public String getItem(final int ID, final Set<ItemData> itemData) {
        return getItem(ID, itemData, null, null);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param itemData
     *            the additional information to get about the item
     * @param locale
     *            the locale to specify
     * @param version
     *            the data version number
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2534">LoL
     *      API Specification</a>
     */
    public String getItem(final int ID, final Set<ItemData> itemData, final String locale, final String version) {
        final Map<String, String> params = baseParams();
        if(itemData != null) {
            params.put("itemData", getCollectionString(itemData));
        }
        if(locale != null) {
            params.put("locale", locale);
        }
        if(version != null) {
            params.put("version", version);
        }

        try {
            return staticData.get(APIVersions.get("static-data") + "/item/" + ID, params);
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2523">LoL
     *      API Specification</a>
     */
    public String getItems() {
        return getItems(null, null, null);
    }

    /**
     * @param itemListData
     *            the additional information to get about all items
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2523">LoL
     *      API Specification</a>
     */
    public String getItems(final Set<ItemData> itemListData) {
        return getItems(itemListData, null, null);
    }

    /**
     * @param itemListData
     *            the additional information to get about all items
     * @param locale
     *            the locale to specify
     * @param version
     *            the data version number
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2523">LoL
     *      API Specification</a>
     */
    public String getItems(final Set<ItemData> itemListData, final String locale, final String version) {
        final Map<String, String> params = baseParams();
        if(itemListData != null) {
            params.put("itemListData", getCollectionString(itemListData));
        }
        if(locale != null) {
            params.put("locale", locale);
        }
        if(version != null) {
            params.put("version", version);
        }

        try {
            return staticData.get(APIVersions.get("static-data") + "/item", params);
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2531">LoL
     *      API Specification</a>
     */
    public String getMasteries() {
        return getMasteries(null, null, null);
    }

    /**
     * @param masteryListData
     *            the additional information to get about all masteries
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2531">LoL
     *      API Specification</a>
     */
    public String getMasteries(final Set<MasteryData> masteryListData) {
        return getMasteries(masteryListData, null, null);
    }

    /**
     * @param masteryListData
     *            the additional information to get about all masteries
     * @param locale
     *            the locale to specify
     * @param version
     *            the data version number
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2531">LoL
     *      API Specification</a>
     */
    public String getMasteries(final Set<MasteryData> masteryListData, final String locale, final String version) {
        final Map<String, String> params = baseParams();
        if(masteryListData != null) {
            params.put("masteryListData", getCollectionString(masteryListData));
        }
        if(locale != null) {
            params.put("locale", locale);
        }
        if(version != null) {
            params.put("version", version);
        }

        try {
            return staticData.get(APIVersions.get("static-data") + "/mastery", params);
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @param ID
     *            the ID to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2533">LoL
     *      API Specification</a>
     */
    public String getMastery(final int ID) {
        return getMastery(ID, null, null, null);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param masteryData
     *            the additional information to get about the mastery
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2533">LoL
     *      API Specification</a>
     */
    public String getMastery(final int ID, final Set<MasteryData> masteryData) {
        return getMastery(ID, masteryData, null, null);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param masteryData
     *            the additional information to get about the mastery
     * @param locale
     *            the locale to specify
     * @param version
     *            the data version number
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2533">LoL
     *      API Specification</a>
     */
    public String getMastery(final int ID, final Set<MasteryData> masteryData, final String locale, final String version) {
        final Map<String, String> params = baseParams();
        if(masteryData != null) {
            params.put("masteryData", getCollectionString(masteryData));
        }
        if(locale != null) {
            params.put("locale", locale);
        }
        if(version != null) {
            params.put("version", version);
        }

        try {
            return staticData.get(APIVersions.get("static-data") + "/mastery/" + ID, params);
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @param ID
     *            the ID to get information for
     * @return the API's JSON formatted response
     * @see <a href="https://developer.riotgames.com/api/methods#!/806/2848">LoL
     *      API Specification</a>
     */
    public String getMatch(final long ID) {
        return getMatch(ID, true);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param includeTimeline
     *            whether to include the timeline
     * @return the API's JSON formatted response
     * @see <a href="https://developer.riotgames.com/api/methods#!/806/2848">LoL
     *      API Specification</a>
     */
    public String getMatch(final long ID, final boolean includeTimeline) {
        final Map<String, String> params = baseParams();
        params.put("includeTimeline", Boolean.toString(includeTimeline));

        return waitForRateLimiter(APIVersions.get("match") + "/match/" + ID, params);
    }

    /**
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2528">LoL
     *      API Specification</a>
     */
    public String getRealm() {
        try {
            return staticData.get(APIVersions.get("static-data") + "/realm", baseParams());
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @param ID
     *            the ID to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2525">LoL
     *      API Specification</a>
     */
    public String getRune(final int ID) {
        return getRune(ID, null, null, null);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param runeData
     *            the additional information to get about the rune
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2525">LoL
     *      API Specification</a>
     */
    public String getRune(final int ID, final Set<RuneData> runeData) {
        return getRune(ID, runeData, null, null);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param runeData
     *            the additional information to get about the rune
     * @param locale
     *            the locale to specify
     * @param version
     *            the data version number
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2525">LoL
     *      API Specification</a>
     */
    public String getRune(final int ID, final Set<RuneData> runeData, final String locale, final String version) {
        final Map<String, String> params = baseParams();
        if(runeData != null) {
            params.put("runeData", getCollectionString(runeData));
        }
        if(locale != null) {
            params.put("locale", locale);
        }
        if(version != null) {
            params.put("version", version);
        }

        try {
            return staticData.get(APIVersions.get("static-data") + "/rune/" + ID, params);
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2530">LoL
     *      API Specification</a>
     */
    public String getRunes() {
        return getRunes(null, null, null);
    }

    /**
     * @param runeListData
     *            the additional information to get about all runes
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2530">LoL
     *      API Specification</a>
     */
    public String getRunes(final Set<RuneData> runeListData) {
        return getRunes(runeListData, null, null);
    }

    /**
     * @param runeListData
     *            the additional information to get about all runes
     * @param locale
     *            the locale to specify
     * @param version
     *            the data version number
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2530">LoL
     *      API Specification</a>
     */
    public String getRunes(final Set<RuneData> runeListData, final String locale, final String version) {
        final Map<String, String> params = baseParams();
        if(runeListData != null) {
            params.put("runeListData", getCollectionString(runeListData));
        }
        if(locale != null) {
            params.put("locale", locale);
        }
        if(version != null) {
            params.put("version", version);
        }

        try {
            return staticData.get(APIVersions.get("static-data") + "/rune", params);
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1930">LoL
     *      API Specification</a>
     */
    public String getSummoner(String summonerName) {
        try {
            summonerName = URLEncoder.encode(summonerName, "UTF-8").replace("+", "%20");
        }
        catch(final UnsupportedEncodingException e) {}

        return waitForRateLimiter(APIVersions.get("summoner") + "/summoner/by-name/" + summonerName, baseParams());
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1931">LoL
     *      API Specification</a>
     */
    public String getSummonerByID(final long summonerID) {
        return waitForRateLimiter(APIVersions.get("summoner") + "/summoner/" + summonerID, baseParams());
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/618/1924">LoL
     *      API Specification</a>
     */
    public String getSummonerGamesByID(final long summonerID) {
        return waitForRateLimiter(APIVersions.get("game") + "/game/by-summoner/" + summonerID + "/recent", baseParams());
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public String getSummonerLeagueEntriesByID(final long summonerID) {
        return waitForRateLimiter(APIVersions.get("league") + "/league/by-summoner/" + summonerID + "/entry", baseParams());
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public String getSummonerLeaguesByID(final long summonerID) {
        return waitForRateLimiter(APIVersions.get("league") + "/league/by-summoner/" + summonerID, baseParams());
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public String getSummonerMasteriesByID(final long summonerID) {
        return waitForRateLimiter(APIVersions.get("summoner") + "/summoner/" + summonerID + "/masteries", baseParams());
    }

    /**
     * @param summonerID
     *            the ID to get information for
     * @return the API's JSON formatted response
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public String getSummonerMatchHistory(final long summonerID) {
        return waitForRateLimiter(APIVersions.get("matchhistory") + "/matchhistory/" + summonerID, baseParams());
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1934">LoL
     *      API Specification</a>
     */
    public String getSummonerName(final long summonerID) {
        return waitForRateLimiter(APIVersions.get("summoner") + "/summoner/" + summonerID + "/name", baseParams());
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1934">LoL
     *      API Specification</a>
     */
    public String getSummonerNames(final List<Long> summonerIDs) {
        return waitForRateLimiter(APIVersions.get("summoner") + "/summoner/" + getCollectionString(summonerIDs) + "/name", baseParams());
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1937">LoL
     *      API Specification</a>
     */
    public String getSummonerRankedStatsByID(final long summonerID) {
        return getSummonerRankedStatsByID(summonerID, null);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @param season
     *            the season to get stats for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1937">LoL
     *      API Specification</a>
     */
    public String getSummonerRankedStatsByID(final long summonerID, final Season season) {
        final Map<String, String> params = baseParams();
        if(season != null) {
            params.put("season", season.toString());
        }

        return waitForRateLimiter(APIVersions.get("stats") + "/stats/by-summoner/" + summonerID + "/ranked", params);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public String getSummonerRunesByID(final long summonerID) {
        return waitForRateLimiter(APIVersions.get("summoner") + "/summoner/" + summonerID + "/runes", baseParams());
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1930">LoL
     *      API Specification</a>
     */
    public String getSummoners(final List<String> summonerNames) {
        return waitForRateLimiter(APIVersions.get("summoner") + "/summoner/by-name/" + getNameCollectionString(summonerNames), baseParams());
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1931">LoL
     *      API Specification</a>
     */
    public String getSummonersByID(final List<Long> summonerIDs) {
        return waitForRateLimiter(APIVersions.get("summoner") + "/summoner/" + getCollectionString(summonerIDs), baseParams());
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public String getSummonersLeagueEntriesByID(final List<Long> summonerIDs) {
        return waitForRateLimiter(APIVersions.get("league") + "/league/by-summoner/" + getCollectionString(summonerIDs) + "/entry", baseParams());
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public String getSummonersLeaguesByID(final List<Long> summonerIDs) {
        return waitForRateLimiter(APIVersions.get("league") + "/league/by-summoner/" + getCollectionString(summonerIDs), baseParams());
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public String getSummonersMasteriesByID(final List<Long> summonerIDs) {
        return waitForRateLimiter(APIVersions.get("summoner") + "/summoner/" + getCollectionString(summonerIDs) + "/masteries", baseParams());
    }

    /**
     * @param ID
     *            the ID to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2524">LoL
     *      API Specification</a>
     */
    public String getSummonerSpell(final int ID) {
        return getSummonerSpell(ID, null, null, null);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param spellData
     *            the additional information to get about the summoner spell
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2524">LoL
     *      API Specification</a>
     */
    public String getSummonerSpell(final int ID, final Set<SummonerSpellData> spellData) {
        return getSummonerSpell(ID, spellData, null, null);
    }

    /**
     * @param ID
     *            the ID to get information for
     * @param spellData
     *            the additional information to get about the summoner spell
     * @param locale
     *            the locale to specify
     * @param version
     *            the data version number
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2524">LoL
     *      API Specification</a>
     */
    public String getSummonerSpell(final int ID, final Set<SummonerSpellData> spellData, final String locale, final String version) {
        final Map<String, String> params = baseParams();
        if(spellData != null) {
            params.put("spellData", getCollectionString(spellData));
        }
        if(locale != null) {
            params.put("locale", locale);
        }
        if(version != null) {
            params.put("version", version);
        }

        try {
            return staticData.get(APIVersions.get("static-data") + "/summoner-spell/" + ID, params);
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2532">LoL
     *      API Specification</a>
     */
    public String getSummonerSpells() {
        return getSummonerSpells(null, null, null, true);
    }

    /**
     * @param spellData
     *            the additional information to get about all summoner spells
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2532">LoL
     *      API Specification</a>
     */
    public String getSummonerSpells(final Set<SummonerSpellData> spellData) {
        return getSummonerSpells(spellData, null, null, true);
    }

    /**
     * @param spellData
     *            the additional information to get about all summoner spells
     * @param bySpellName
     *            whether the results should be organized by name or not (if
     *            not, will be by ID)
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2532">LoL
     *      API Specification</a>
     */
    public String getSummonerSpells(final Set<SummonerSpellData> spellData, final boolean bySpellName) {
        return getSummonerSpells(spellData, null, null, bySpellName);
    }

    /**
     * @param spellData
     *            the additional information to get about all summoner spells
     * @param locale
     *            the locale to specify
     * @param version
     *            the data version number
     * @param bySpellName
     *            whether the results should be organized by name or not (if
     *            not, will be by ID)
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2532">LoL
     *      API Specification</a>
     */
    public String getSummonerSpells(final Set<SummonerSpellData> spellData, final String locale, final String version, final boolean bySpellName) {
        final Map<String, String> params = baseParams();
        if(spellData != null) {
            params.put("spellData", getCollectionString(spellData));
        }
        if(locale != null) {
            params.put("locale", locale);
        }
        if(version != null) {
            params.put("version", version);
        }
        params.put("dataById", Boolean.toString(!bySpellName));

        try {
            return staticData.get(APIVersions.get("static-data") + "/summoner-spell", params);
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public String getSummonersRunesByID(final List<Long> summonerIDs) {
        return waitForRateLimiter(APIVersions.get("summoner") + "/summoner/" + getCollectionString(summonerIDs) + "/runes", baseParams());
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1938">LoL
     *      API Specification</a>
     */
    public String getSummonerStatsByID(final long summonerID) {
        return getSummonerStatsByID(summonerID, null);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @param season
     *            the season to get stats for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1938">LoL
     *      API Specification</a>
     */
    public String getSummonerStatsByID(final long summonerID, final Season season) {
        final Map<String, String> params = baseParams();
        if(season != null) {
            params.put("season", season.toString());
        }

        return waitForRateLimiter(APIVersions.get("stats") + "/stats/by-summoner/" + summonerID + "/summary", params);
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public String getSummonersTeamsByID(final List<Long> summonerIDs) {
        return waitForRateLimiter(APIVersions.get("team") + "/team/by-summoner/" + getCollectionString(summonerIDs), baseParams());
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public String getSummonerTeamsByID(final long summonerID) {
        return waitForRateLimiter(APIVersions.get("team") + "/team/by-summoner/" + summonerID, baseParams());
    }

    /**
     * @param teamID
     *            the team to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2645">LoL
     *      API Specification</a>
     */
    public String getTeam(final String teamID) {
        return waitForRateLimiter(APIVersions.get("team") + "/team/" + teamID, baseParams());
    }

    /**
     * @param teamID
     *            the team to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2638">LoL
     *      API Specification</a>
     */
    public String getTeamLeagueEntriesByID(final String teamID) {
        return waitForRateLimiter(APIVersions.get("league") + "/league/by-team/" + teamID + "/entry", baseParams());
    }

    /**
     * @param teamID
     *            the team to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2639">LoL
     *      API Specification</a>
     */
    public String getTeamLeaguesByID(final String teamID) {
        return waitForRateLimiter(APIVersions.get("league") + "/league/by-team/" + teamID, baseParams());
    }

    /**
     * @param teamIDs
     *            the teams to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2645">LoL
     *      API Specification</a>
     */
    public String getTeams(final List<String> teamIDs) {
        return waitForRateLimiter(APIVersions.get("team") + "/team/" + getCollectionString(teamIDs), baseParams());
    }

    /**
     * @param teamIDs
     *            the teams to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2638">LoL
     *      API Specification</a>
     */
    public String getTeamsLeagueEntriesByID(final List<String> teamIDs) {
        return waitForRateLimiter(APIVersions.get("league") + "/league/by-team/" + getCollectionString(teamIDs) + "/entry", baseParams());
    }

    /**
     * @param teamIDs
     *            the teams to get information for
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2639">LoL
     *      API Specification</a>
     */
    public String getTeamsLeaguesByID(final List<String> teamIDs) {
        return waitForRateLimiter(APIVersions.get("league") + "/league/by-team/" + getCollectionString(teamIDs), baseParams());
    }

    /**
     * @return the API's JSON formatted response
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2527">LoL
     *      API Specification</a>
     */
    public String getVersions() {
        try {
            return staticData.get(APIVersions.get("static-data") + "/versions", baseParams());
        }
        catch(final IOException e) {
            throw handleIOException(e);
        }
    }

    private String waitForRateLimiter(final String URI, final Map<String, String> params) {
        final Supplier<String> call = () -> {
            try {
                return dynamicData.get(URI, params);
            }
            catch(final IOException e) {
                throw handleIOException(e);
            }
        };

        if(rateLimiter != null) {
            try {
                return rateLimiter.waitForCall(call);
            }
            catch(final InterruptedException e) {
                throw new APIException(APIException.Type.RATE_LIMITED, e);
            }
        }

        return call.get();
    }
}
