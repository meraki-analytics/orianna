package com.robrua.orianna.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.robrua.orianna.api.queryspecs.ChampionData;
import com.robrua.orianna.api.queryspecs.ItemData;
import com.robrua.orianna.api.queryspecs.MasteryData;
import com.robrua.orianna.api.queryspecs.Region;
import com.robrua.orianna.api.queryspecs.RuneData;
import com.robrua.orianna.api.queryspecs.Season;
import com.robrua.orianna.api.queryspecs.SummonerSpellData;
import com.robrua.orianna.type.champion.ChampionStatus;
import com.robrua.orianna.type.game.Game;
import com.robrua.orianna.type.league.League;
import com.robrua.orianna.type.league.LeagueType;
import com.robrua.orianna.type.match.MatchSummary;
import com.robrua.orianna.type.match.QueueType;
import com.robrua.orianna.type.staticdata.Champion;
import com.robrua.orianna.type.staticdata.Item;
import com.robrua.orianna.type.staticdata.Mastery;
import com.robrua.orianna.type.staticdata.MasteryTree;
import com.robrua.orianna.type.staticdata.Realm;
import com.robrua.orianna.type.staticdata.Rune;
import com.robrua.orianna.type.staticdata.SummonerSpell;
import com.robrua.orianna.type.stats.ChampionStats;
import com.robrua.orianna.type.stats.PlayerStatsSummary;
import com.robrua.orianna.type.stats.PlayerStatsSummaryType;
import com.robrua.orianna.type.summoner.MasteryPage;
import com.robrua.orianna.type.summoner.RunePage;
import com.robrua.orianna.type.summoner.Summoner;
import com.robrua.orianna.type.team.Team;

/**
 * Accesses the <a href="http://developer.riotgames.com/api/methods">LoL REST
 * API</a> and provides results in easy-to-use Java objects.
 *
 * <ul>
 * <li>Replaces foreign key ID values with the referenced object</li>
 * <li>Makes most efficient use of API calls to minimize load</li>
 * <li>Caches static data and summoner information to accelerate access</li>
 * <li>Automatically throttles requests to fit rate limits</li>
 * <li>Ensures well-formed API requests</li>
 * </ul>
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public class RiotAPI {
    private class Cache {
        public final Map<Integer, Champion> champions = new ConcurrentHashMap<Integer, Champion>();
        public boolean championsFilled = false;
        public final Map<Integer, Item> items = new ConcurrentHashMap<Integer, Item>();
        public boolean itemsFilled = false;
        public final Map<Integer, Mastery> masteries = new ConcurrentHashMap<Integer, Mastery>();
        public boolean masteriesFilled = false;
        public MasteryTree masteryTree = null;
        public final Map<Integer, Rune> runes = new ConcurrentHashMap<Integer, Rune>();
        public boolean runesFilled = false;
        public final Map<Long, Summoner> summonerIDs = new ConcurrentHashMap<Long, Summoner>();
        public final Map<String, Summoner> summonerNames = new ConcurrentHashMap<String, Summoner>();
        public final Map<Integer, SummonerSpell> summonerSpells = new ConcurrentHashMap<Integer, SummonerSpell>();
        public boolean summonerSpellsFilled = false;
    }

    // Item IDs that Riot sends which reference removed items
    private static final Set<Integer> IGNORE_IDS = new HashSet<>();

    private final static int LEAGUE_TEAM_ID_LIMIT = 10;

    private final static JSONParser parser = new JSONParser();
    private final static int STANDARD_ID_LIMIT = 40;
    static {
        IGNORE_IDS.add(3210);
        IGNORE_IDS.add(3176);
        IGNORE_IDS.add(3210);
        IGNORE_IDS.add(3005);
        IGNORE_IDS.add(3131);
        IGNORE_IDS.add(2040);
        IGNORE_IDS.add(2039);
        IGNORE_IDS.add(3186);
    }

    private static List<Long> getIDsFromSummoners(final List<Summoner> summoners) {
        return summoners.stream().map((summoner) -> summoner.ID).collect(Collectors.toList());
    }

    private static List<String> getIDsFromTeams(final List<Team> teams) {
        return teams.stream().map((team) -> team.ID).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private static <T> JSONObject handleIDCountLimit(final List<T> IDs, final Function<List<T>, String> APICall, final int limitPerCall) {
        if(IDs.size() > limitPerCall) {
            final JSONObject allIDs = new JSONObject();
            final List<List<T>> splitIDs = splitIDList(IDs, limitPerCall);
            for(final List<T> IDBatch : splitIDs) {
                final String json = APICall.apply(IDBatch);
                try {
                    final JSONObject moreIDs = (JSONObject)parser.parse(json);
                    allIDs.putAll(moreIDs);
                }
                catch(final ParseException e) {
                    throw handleParseException(e);
                }
            }

            return allIDs;
        }
        else {
            final String json = APICall.apply(IDs);

            try {
                return (JSONObject)parser.parse(json);
            }
            catch(final ParseException e) {
                throw handleParseException(e);
            }
        }
    }

    private static APIException handleParseException(final ParseException e) {
        return new APIException(APIException.Type.PARSE_FAILURE, e);
    }

    private static <T> List<List<T>> splitIDList(final List<T> IDs, final int maxSize) {
        final int numRequests = (IDs.size() - 1) / maxSize + 1;
        final List<List<T>> splitIDs = new ArrayList<List<T>>(numRequests);
        for(int i = 0; i < numRequests; i++) {
            final List<T> IDBatch = new ArrayList<T>();

            final int start = i * maxSize;
            for(int j = start; j < start + maxSize && j < IDs.size(); j++) {
                IDBatch.add(IDs.get(j));
            }

            splitIDs.add(Collections.unmodifiableList(IDBatch));
        }

        return Collections.unmodifiableList(splitIDs);
    }

    private static <T> Map<Summoner, T> summonerMapFromID(final List<Summoner> summoners, final Map<Long, T> IDMap) {
        final Map<Summoner, T> map = new HashMap<Summoner, T>();
        summoners.forEach((summoner) -> map.put(summoner, IDMap.get(summoner.ID)));
        return Collections.unmodifiableMap(map);
    }

    private static <T> Map<Team, T> teamMapFromID(final List<Team> teams, final Map<String, T> IDMap) {
        final Map<Team, T> map = new HashMap<Team, T>();
        teams.forEach((team) -> map.put(team, IDMap.get(team.ID)));
        return Collections.unmodifiableMap(map);
    }

    private final JSONRiotAPI API;
    private Cache cache;
    private final JSONConverter converter;

    /**
     * @param mirror
     *            the data server to query
     * @param forRegion
     *            the region for which to query data
     * @param APIKey
     *            your LoL API key
     */
    public RiotAPI(final Region mirror, final Region forRegion, final String APIKey) {
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
    public RiotAPI(final Region mirror, final Region forRegion, final String APIKey, final RateLimiter rateLimiter) {
        API = new JSONRiotAPI(mirror, forRegion, APIKey, rateLimiter);
        converter = new JSONConverter(this);
        cache = new Cache();
    }

    /**
     * @param forRegion
     *            the region for which to query data
     * @param APIKey
     *            your LoL API key
     */
    public RiotAPI(final Region forRegion, final String APIKey) {
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
    public RiotAPI(final Region forRegion, final String APIKey, final RateLimiter rateLimiter) {
        this(forRegion, forRegion, APIKey, rateLimiter);
    }

    /**
     * Caches all Items, Champions, Runes, Masteries, and Summoner Spells in
     * bulk.
     */
    public void cacheAllStaticData() {
        getItems();
        getChampions();
        getMasteries();
        getRunes();
        getSummonerSpells();
    }

    /**
     * Clears all cached information
     */
    public void clearCache() {
        cache = new Cache();
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @return the summoner's active mastery page
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public MasteryPage getActiveMasteryPage(final Summoner summoner) {
        return getActiveMasteryPageByID(summoner.ID);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's active mastery page
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public MasteryPage getActiveMasteryPageByID(final long summonerID) {
        return getMasteryPagesByID(summonerID).stream().filter((page) -> page.current).findAny().get();
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's active mastery page
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public MasteryPage getActiveMasteryPageByName(final String summonerName) {
        return getActiveMasteryPage(getSummoner(summonerName));
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' active mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Summoner, MasteryPage> getActiveMasteryPages(final List<Summoner> summoners) {
        final Map<Long, MasteryPage> allPages = getActiveMasteryPagesByIDs(getIDsFromSummoners(summoners));
        return summonerMapFromID(summoners, allPages);
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' active mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Summoner, MasteryPage> getActiveMasteryPages(final Summoner... summoners) {
        return getActiveMasteryPages(Arrays.asList(summoners));
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' active mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Long, MasteryPage> getActiveMasteryPagesByIDs(final List<Long> summonerIDs) {
        final Map<Long, List<MasteryPage>> pages = getMasteryPagesByIDs(summonerIDs);
        return pages.entrySet().stream()
                .collect(Collectors.toMap((entry) -> entry.getKey(), (entry) -> entry.getValue().stream().filter((page) -> page.current).findAny().get()));
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' active mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Long, MasteryPage> getActiveMasteryPagesByIDs(final Long... summonerIDs) {
        return getActiveMasteryPagesByIDs(Arrays.asList(summonerIDs));
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' active mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Summoner, MasteryPage> getActiveMasteryPagesByNames(final List<String> summonerNames) {
        final List<Summoner> summoners = getSummoners(summonerNames);
        return getActiveMasteryPages(summoners);
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' active mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Summoner, MasteryPage> getActiveMasteryPagesByNames(final String... summonerNames) {
        return getActiveMasteryPagesByNames(Arrays.asList(summonerNames));
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @return the summoner's active rune page
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public RunePage getActiveRunePage(final Summoner summoner) {
        return getActiveRunePageByID(summoner.ID);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's active rune page
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public RunePage getActiveRunePageByID(final long summonerID) {
        return getRunePagesByID(summonerID).stream().filter((page) -> page.current).findAny().get();
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's active rune page
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public RunePage getActiveRunePageByName(final String summonerName) {
        return getActiveRunePage(getSummoner(summonerName));
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' active rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Summoner, RunePage> getActiveRunePages(final List<Summoner> summoners) {
        final Map<Long, RunePage> allPages = getActiveRunePagesByIDs(getIDsFromSummoners(summoners));
        return summonerMapFromID(summoners, allPages);
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' active rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Summoner, RunePage> getActiveRunePages(final Summoner... summoners) {
        return getActiveRunePages(Arrays.asList(summoners));
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' active rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Long, RunePage> getActiveRunePagesByIDs(final List<Long> summonerIDs) {
        final Map<Long, List<RunePage>> pages = getRunePagesByIDs(summonerIDs);
        return pages.entrySet().stream()
                .collect(Collectors.toMap((entry) -> entry.getKey(), (entry) -> entry.getValue().stream().filter((page) -> page.current).findAny().get()));
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' active rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Long, RunePage> getActiveRunePagesByIDs(final Long... summonerIDs) {
        return getActiveRunePagesByIDs(Arrays.asList(summonerIDs));
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' active rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Summoner, RunePage> getActiveRunePagesByNames(final List<String> summonerNames) {
        final List<Summoner> summoners = getSummoners(summonerNames);
        return getActiveRunePages(summoners);
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' active rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Summoner, RunePage> getActiveRunePagesByNames(final String... summonerNames) {
        return getActiveRunePagesByNames(Arrays.asList(summonerNames));
    }

    /**
     * @return the solo-queue challenger league
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2637">LoL
     *      API Specification</a>
     */
    public League getChallengerLeague() {
        return getChallengerLeague(LeagueType.RANKED_SOLO_5x5);
    }

    /**
     * @param type
     *            the queue for which to get the Challenger league
     * @return the challenger league
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2637">LoL
     *      API Specification</a>
     */
    public League getChallengerLeague(final LeagueType type) {
        final String json = API.getChallengerLeague(type);
        try {
            return converter.getLeagueFromJSON((JSONObject)parser.parse(json));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @param championID
     *            the champion to get information for
     * @return the champion
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2526">LoL
     *      API Specification</a>
     */
    public Champion getChampion(final int championID) {
        Champion champion = cache.champions.get(championID);

        if(champion == null) {
            if(!cache.itemsFilled) {
                getItems(); // Cache in bulk to minimize API calls
            }

            final Set<ChampionData> championData = new HashSet<ChampionData>();
            championData.add(ChampionData.all);
            final String json = API.getChampion(championID, championData);

            try {
                champion = converter.getChampionFromJSON((JSONObject)parser.parse(json));
            }
            catch(final ParseException e) {
                throw handleParseException(e);
            }
            cache.champions.put(championID, champion);
        }

        return champion;
    }

    /**
     * Not supported by the API, done locally (and slowly at that)
     *
     * @param championName
     *            the champion's name
     * @return the champion
     */
    public Champion getChampion(final String championName) {
        final List<Champion> champions = getChampions();
        return champions.stream().filter((champion) -> champion.name.equals(championName)).findAny().get();
    }

    /**
     * @return all champions
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2529">LoL
     *      API Specification</a>
     */
    public List<Champion> getChampions() {
        if(cache.championsFilled) {
            final List<Champion> champions = new ArrayList<Champion>();
            champions.addAll(cache.champions.values());
            return Collections.unmodifiableList(champions);
        }

        if(!cache.itemsFilled) {
            getItems(); // Cache in bulk to minimize API calls
        }

        final Set<ChampionData> championData = new HashSet<ChampionData>();
        championData.add(ChampionData.all);

        final String json = API.getChampions(championData);
        JSONObject championList;
        try {
            championList = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }

        final List<Champion> champions = JSONConverter.getListFromMap(championList, "data", c -> converter.getChampionFromJSON((JSONObject)c));
        for(final Champion champion : champions) {
            cache.champions.put(champion.ID, champion);
        }
        cache.championsFilled = true;

        return champions;
    }

    /**
     * Not supported by the API, done locally (and slowly at that)
     *
     * @param championNames
     *            the champions' names
     * @return the champions
     */
    public List<Champion> getChampions(final List<String> championNames) {
        final List<Champion> champions = getChampions();
        return Collections.unmodifiableList(champions.stream().filter((champion) -> championNames.contains(champion.name)).collect(Collectors.toList()));
    }

    /**
     * Not supported by the API, done locally (and slowly at that)
     *
     * @param championNames
     *            the champions' names
     * @return the champions
     */
    public List<Champion> getChampions(final String... championNames) {
        return getChampions(Arrays.asList(championNames));
    }

    /**
     * @param champion
     *            the champion to get information about
     * @return the champion's status
     * @see <a href="http://developer.riotgames.com/api/methods#!/617/1922">LoL
     *      API Specification</a>
     */
    public ChampionStatus getChampionStatus(final Champion champion) {
        return getChampionStatus(champion.ID);
    }

    /**
     * @param championID
     *            the champion to get information for
     * @return the champion's status
     * @see <a href="http://developer.riotgames.com/api/methods#!/617/1922">LoL
     *      API Specification</a>
     */
    public ChampionStatus getChampionStatus(final int championID) {
        final String json = API.getChampionStatus(championID);
        try {
            return converter.getChampionStatusFromJSON((JSONObject)parser.parse(json));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @return all champions' statuses
     * @see <a href="http://developer.riotgames.com/api/methods#!/617/1923">LoL
     *      API Specification</a>
     */
    public List<ChampionStatus> getChampionStatuses() {
        return getChampionStatuses(false);
    }

    /**
     * @param onlyFreeToPlay
     *            whether to only show statuses for free to play champions
     * @return champions' statuses
     * @see <a href="http://developer.riotgames.com/api/methods#!/617/1923">LoL
     *      API Specification</a>
     */
    public List<ChampionStatus> getChampionStatuses(final boolean onlyFreeToPlay) {
        if(!cache.championsFilled) {
            getChampions(); // Cache in bulk to minimize API calls
        }

        final String json = API.getChampionStatuses(onlyFreeToPlay);
        try {
            return JSONConverter.getList((JSONObject)parser.parse(json), "champions", s -> converter.getChampionStatusFromJSON((JSONObject)s));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @param itemID
     *            the item to get information for
     * @return the item
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2534">LoL
     *      API Specification</a>
     */
    public Item getItem(final int itemID) {
        if(IGNORE_IDS.contains(itemID)) {
            return null;
        }

        Item item = cache.items.get(itemID);

        if(item == null) {
            final Set<ItemData> itemData = new HashSet<ItemData>();
            itemData.add(ItemData.all);
            final String json = API.getItem(itemID, itemData);

            try {
                item = converter.getItemFromJSON((JSONObject)parser.parse(json));
            }
            catch(final ParseException e) {
                throw handleParseException(e);
            }
            cache.items.put(itemID, item);
        }

        return item;
    }

    /**
     * @return all items
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2523">LoL
     *      API Specification</a>
     */
    public List<Item> getItems() {
        if(cache.itemsFilled) {
            final List<Item> items = new ArrayList<Item>();
            items.addAll(cache.items.values());
            return Collections.unmodifiableList(items);
        }

        final Set<ItemData> itemData = new HashSet<ItemData>();
        itemData.add(ItemData.all);

        final String json = API.getItems(itemData);
        JSONObject itemList;
        try {
            itemList = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }

        final List<Item> items = JSONConverter.getListFromMap(itemList, "data", i -> converter.getItemFromJSON((JSONObject)i));
        for(final Item item : items) {
            cache.items.put(item.ID, item);
        }
        cache.itemsFilled = true;

        return items;
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @return the summoner's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public List<League> getLeagueEntriesBySummoner(final Summoner summoner) {
        return getLeagueEntriesBySummonerID(summoner.ID);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public List<League> getLeagueEntriesBySummonerID(final Long summonerID) {
        final String json = API.getSummonerLeagueEntriesByID(summonerID);
        try {
            return JSONConverter.getList((JSONObject)parser.parse(json), Long.toString(summonerID), l -> converter.getLeagueFromJSON((JSONObject)l));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public Map<Long, List<League>> getLeagueEntriesBySummonerIDs(final List<Long> summonerIDs) {
        final JSONObject summonerList = handleIDCountLimit(summonerIDs, (IDs) -> API.getSummonersLeagueEntriesByID(IDs), LEAGUE_TEAM_ID_LIMIT);

        final Map<Long, List<League>> allLeagues = new HashMap<Long, List<League>>();
        for(final Long summonerID : summonerIDs) {
            final List<League> leagues = JSONConverter.getList(summonerList, Long.toString(summonerID), l -> converter.getLeagueFromJSON((JSONObject)l));
            allLeagues.put(summonerID, leagues);
        }

        return Collections.unmodifiableMap(allLeagues);
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public Map<Long, List<League>> getLeagueEntriesBySummonerIDs(final Long... summonerIDs) {
        return getLeagueEntriesBySummonerIDs(Arrays.asList(summonerIDs));
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public List<League> getLeagueEntriesBySummonerName(final String summonerName) {
        return getLeagueEntriesBySummoner(getSummoner(summonerName));
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<League>> getLeagueEntriesBySummonerNames(final List<String> summonerNames) {
        final List<Summoner> summoners = getSummoners(summonerNames);
        return getLeagueEntriesBySummoners(summoners);
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<League>> getLeagueEntriesBySummonerNames(final String... summonerNames) {
        return getLeagueEntriesBySummonerNames(Arrays.asList(summonerNames));
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<League>> getLeagueEntriesBySummoners(final List<Summoner> summoners) {
        final Map<Long, List<League>> byID = getLeagueEntriesBySummonerIDs(getIDsFromSummoners(summoners));
        return summonerMapFromID(summoners, byID);
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<League>> getLeagueEntriesBySummoners(final Summoner... summoners) {
        return getLeagueEntriesBySummoners(Arrays.asList(summoners));
    }

    /**
     * @param team
     *            the team to get information about
     * @return the team's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2638">LoL
     *      API Specification</a>
     */
    public List<League> getLeagueEntriesByTeam(final Team team) {
        return getLeagueEntriesByTeamID(team.ID);
    }

    /**
     * @param teamID
     *            the team to get information for
     * @return the team's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2638">LoL
     *      API Specification</a>
     */
    public List<League> getLeagueEntriesByTeamID(final String teamID) {
        final String json = API.getTeamLeagueEntriesByID(teamID);
        try {
            return JSONConverter.getList((JSONObject)parser.parse(json), teamID, l -> converter.getLeagueFromJSON((JSONObject)l));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @param teamIDs
     *            the teams to get information for
     * @return the teams' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2638">LoL
     *      API Specification</a>
     */
    public Map<String, List<League>> getLeagueEntriesByTeamIDs(final List<String> teamIDs) {
        final JSONObject teamList = handleIDCountLimit(teamIDs, (IDs) -> API.getTeamsLeagueEntriesByID(IDs), LEAGUE_TEAM_ID_LIMIT);

        final Map<String, List<League>> allLeagues = new HashMap<String, List<League>>();
        for(final String teamID : teamIDs) {
            final List<League> leagues = JSONConverter.getList(teamList, teamID, l -> converter.getLeagueFromJSON((JSONObject)l));
            allLeagues.put(teamID, leagues);
        }

        return Collections.unmodifiableMap(allLeagues);
    }

    /**
     * @param teamIDs
     *            the teams to get information for
     * @return the teams' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2638">LoL
     *      API Specification</a>
     */
    public Map<String, List<League>> getLeagueEntriesByTeamIDs(final String... teamIDs) {
        return getLeagueEntriesByTeamIDs(Arrays.asList(teamIDs));
    }

    /**
     * @param teams
     *            the teams to get information about
     * @return the teams' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2638">LoL
     *      API Specification</a>
     */
    public Map<Team, List<League>> getLeagueEntriesByTeams(final List<Team> teams) {
        final Map<String, List<League>> byID = getLeagueEntriesByTeamIDs(getIDsFromTeams(teams));
        return teamMapFromID(teams, byID);
    }

    /**
     * @param teams
     *            the teams to get information about
     * @return the teams' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2638">LoL
     *      API Specification</a>
     */
    public Map<Team, List<League>> getLeagueEntriesByTeams(final Team... teams) {
        return getLeagueEntriesByTeams(Arrays.asList(teams));
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @return the summoner's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public List<League> getLeaguesBySummoner(final Summoner summoner) {
        return getLeaguesBySummonerID(summoner.ID);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public List<League> getLeaguesBySummonerID(final Long summonerID) {
        final String json = API.getSummonerLeaguesByID(summonerID);
        try {
            return JSONConverter.getList((JSONObject)parser.parse(json), Long.toString(summonerID), l -> converter.getLeagueFromJSON((JSONObject)l));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public Map<Long, List<League>> getLeaguesBySummonerIDs(final List<Long> summonerIDs) {
        final JSONObject summonerList = handleIDCountLimit(summonerIDs, (IDs) -> API.getSummonersLeaguesByID(IDs), LEAGUE_TEAM_ID_LIMIT);

        final Map<Long, List<League>> allLeagues = new HashMap<Long, List<League>>();
        for(final Long summonerID : summonerIDs) {
            List<League> leagues;
            leagues = JSONConverter.getList(summonerList, Long.toString(summonerID), l -> converter.getLeagueFromJSON((JSONObject)l));
            allLeagues.put(summonerID, leagues);
        }

        return Collections.unmodifiableMap(allLeagues);
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public Map<Long, List<League>> getLeaguesBySummonerIDs(final Long... summonerIDs) {
        return getLeaguesBySummonerIDs(Arrays.asList(summonerIDs));
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public List<League> getLeaguesBySummonerName(final String summonerName) {
        return getLeaguesBySummoner(getSummoner(summonerName));
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<League>> getLeaguesBySummonerNames(final List<String> summonerNames) {
        final List<Summoner> summoners = getSummoners(summonerNames);
        return getLeaguesBySummoners(summoners);
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<League>> getLeaguesBySummonerNames(final String... summonerNames) {
        return getLeaguesBySummonerNames(Arrays.asList(summonerNames));
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<League>> getLeaguesBySummoners(final List<Summoner> summoners) {
        final Map<Long, List<League>> byID = getLeaguesBySummonerIDs(getIDsFromSummoners(summoners));
        return summonerMapFromID(summoners, byID);
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<League>> getLeaguesBySummoners(final Summoner... summoners) {
        return getLeaguesBySummoners(Arrays.asList(summoners));
    }

    /**
     * @param team
     *            the team to get information about
     * @return the team's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2639">LoL
     *      API Specification</a>
     */
    public List<League> getLeaguesByTeam(final Team team) {
        return getLeaguesByTeamID(team.ID);
    }

    /**
     * @param teamID
     *            the team to get information for
     * @return the team's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2639">LoL
     *      API Specification</a>
     */
    public List<League> getLeaguesByTeamID(final String teamID) {
        final String json = API.getTeamLeaguesByID(teamID);
        try {
            return JSONConverter.getList((JSONObject)parser.parse(json), teamID, t -> converter.getLeagueFromJSON((JSONObject)t));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @param teamIDs
     *            the teams to get information for
     * @return the teams' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2639">LoL
     *      API Specification</a>
     */
    public Map<String, List<League>> getLeaguesByTeamIDs(final List<String> teamIDs) {
        final JSONObject teamList = handleIDCountLimit(teamIDs, (IDs) -> API.getTeamsLeaguesByID(IDs), LEAGUE_TEAM_ID_LIMIT);

        final Map<String, List<League>> allLeagues = new HashMap<String, List<League>>();
        for(final String teamID : teamIDs) {
            final List<League> leagues = JSONConverter.getList(teamList, teamID, l -> converter.getLeagueFromJSON((JSONObject)l));
            allLeagues.put(teamID, leagues);
        }

        return Collections.unmodifiableMap(allLeagues);
    }

    /**
     * @param teamIDs
     *            the teams to get information for
     * @return the teams' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2639">LoL
     *      API Specification</a>
     */
    public Map<String, List<League>> getLeaguesByTeamIDs(final String... teamIDs) {
        return getLeaguesByTeamIDs(Arrays.asList(teamIDs));
    }

    /**
     * @param teams
     *            the teams to get information about
     * @return the teams' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2639">LoL
     *      API Specification</a>
     */
    public Map<Team, List<League>> getLeaguesByTeams(final List<Team> teams) {
        final Map<String, List<League>> byID = getLeaguesByTeamIDs(getIDsFromTeams(teams));
        return teamMapFromID(teams, byID);
    }

    /**
     * @param teams
     *            the teams to get information about
     * @return the teams' leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2639">LoL
     *      API Specification</a>
     */
    public Map<Team, List<League>> getLeaguesByTeams(final Team... teams) {
        return getLeaguesByTeams(Arrays.asList(teams));
    }

    /**
     * @return all masteries
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2531">LoL
     *      API Specification</a>
     */
    public List<Mastery> getMasteries() {
        if(cache.masteriesFilled) {
            final List<Mastery> masteries = new ArrayList<Mastery>();
            masteries.addAll(cache.masteries.values());
            return Collections.unmodifiableList(masteries);
        }

        final Set<MasteryData> masteryData = new HashSet<MasteryData>();
        masteryData.add(MasteryData.all);

        final String json = API.getMasteries(masteryData);
        JSONObject masteryList;
        try {
            masteryList = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }

        final List<Mastery> masteries = JSONConverter.getListFromMap(masteryList, "data", m -> converter.getMasteryFromJSON((JSONObject)m));
        for(final Mastery mastery : masteries) {
            cache.masteries.put(mastery.ID, mastery);
        }
        cache.masteryTree = converter.getMasteryTreeFromJSON((JSONObject)masteryList.get("tree"));
        cache.masteriesFilled = true;

        return masteries;
    }

    /**
     * @param masteryID
     *            the mastery to get information for
     * @return the mastery
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2533">LoL
     *      API Specification</a>
     */
    public Mastery getMastery(final int masteryID) {
        Mastery mastery = cache.masteries.get(masteryID);

        if(mastery == null) {
            final Set<MasteryData> masteryData = new HashSet<MasteryData>();
            masteryData.add(MasteryData.all);
            final String json = API.getMastery(masteryID, masteryData);

            try {
                mastery = converter.getMasteryFromJSON((JSONObject)parser.parse(json));
            }
            catch(final ParseException e) {
                throw handleParseException(e);
            }
            cache.masteries.put(masteryID, mastery);
        }

        return mastery;
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<MasteryPage>> getMasteryPages(final List<Summoner> summoners) {
        final Map<Long, List<MasteryPage>> allPages = getMasteryPagesByIDs(getIDsFromSummoners(summoners));
        return summonerMapFromID(summoners, allPages);
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<MasteryPage>> getMasteryPages(final Summoner... summoners) {
        return getMasteryPages(Arrays.asList(summoners));
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @return the summoner's mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public List<MasteryPage> getMasteryPages(final Summoner summoner) {
        return getMasteryPagesByID(summoner.ID);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public List<MasteryPage> getMasteryPagesByID(final long summonerID) {
        if(!cache.masteriesFilled) {
            getMasteries(); // Cache in bulk to minimize API calls
            // Necessary to get mastery tree as well
        }

        final String json = API.getSummonerMasteriesByID(summonerID);
        JSONObject summonerList;
        try {
            summonerList = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
        final JSONObject summonerObj = (JSONObject)summonerList.get(Long.toString(summonerID));

        return JSONConverter.getList(summonerObj, "pages", p -> converter.getMasteryPageFromJSON((JSONObject)p, cache.masteryTree));
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Long, List<MasteryPage>> getMasteryPagesByIDs(final List<Long> summonerIDs) {
        if(!cache.masteriesFilled) {
            getMasteries(); // Cache in bulk to minimize API calls
            // Necessary to get mastery tree as well
        }

        final JSONObject summonerList = handleIDCountLimit(summonerIDs, (IDs) -> API.getSummonersMasteriesByID(IDs), STANDARD_ID_LIMIT);

        final Map<Long, List<MasteryPage>> allPages = new HashMap<Long, List<MasteryPage>>();
        for(final Long summonerID : summonerIDs) {
            final JSONObject summonerObj = (JSONObject)summonerList.get(Long.toString(summonerID));

            final List<MasteryPage> masteryPages = JSONConverter.getList(summonerObj, "pages",
                    p -> converter.getMasteryPageFromJSON((JSONObject)p, cache.masteryTree));
            allPages.put(summonerID, masteryPages);
        }
        return Collections.unmodifiableMap(allPages);
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Long, List<MasteryPage>> getMasteryPagesByIDs(final Long... summonerIDs) {
        return getMasteryPagesByIDs(Arrays.asList(summonerIDs));
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public List<MasteryPage> getMasteryPagesByName(final String summonerName) {
        return getMasteryPages(getSummoner(summonerName));
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<MasteryPage>> getMasteryPagesByNames(final List<String> summonerNames) {
        final List<Summoner> summoners = getSummoners(summonerNames);
        return getMasteryPages(summoners);
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<MasteryPage>> getMasteryPagesByNames(final String... summonerNames) {
        return getMasteryPagesByNames(Arrays.asList(summonerNames));
    }

    /**
     * @return the mastery tree
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2531">LoL
     *      API Specification</a>
     */
    public MasteryTree getMasteryTree() {
        if(!cache.masteriesFilled) {
            final Set<MasteryData> masteryData = new HashSet<MasteryData>();
            masteryData.add(MasteryData.all);

            final String json = API.getMasteries(masteryData);
            try {
                final JSONObject masteryList = (JSONObject)parser.parse(json);

                final List<Mastery> masteries = JSONConverter.getListFromMap(masteryList, "data", m -> converter.getMasteryFromJSON((JSONObject)m));
                for(final Mastery mastery : masteries) {
                    cache.masteries.put(mastery.ID, mastery);
                }
                cache.masteryTree = converter.getMasteryTreeFromJSON((JSONObject)masteryList.get("tree"));
                cache.masteriesFilled = true;
            }
            catch(final ParseException e) {
                throw handleParseException(e);
            }
        }

        return cache.masteryTree;
    }

    /**
     * @param ID
     *            the match to get information for
     * @return the match
     * @see <a href="https://developer.riotgames.com/api/methods#!/806/2848">LoL
     *      API Specification</a>
     */
    public MatchSummary getMatch(final long ID) {
        return getMatch(ID, true);
    }

    /**
     * @param ID
     *            the match to get information for
     * @param includeTimeline
     *            whether to include the match timeline
     * @return the match
     * @see <a href="https://developer.riotgames.com/api/methods#!/806/2848">LoL
     *      API Specification</a>
     */
    public MatchSummary getMatch(final long ID, final boolean includeTimeline) {
        final String json = API.getMatch(ID, includeTimeline);
        try {
            return converter.getMatchSummaryFromJSON((JSONObject)parser.parse(json));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final long summonerID) {
        return getMatchHistory(summonerID, null, null, null, null);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @param beginIndex
     *            the begin index to use for fetching games. No more than 15
     *            games will be fetched.
     * @param endIndex
     *            the end index to use for fetching games. No more than 15 games
     *            will be fetched.
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final long summonerID, final Integer beginIndex, final Integer endIndex) {
        return getMatchHistory(summonerID, null, null, beginIndex, endIndex);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @param champions
     *            which champions to limit this search to
     * @param rankedQueues
     *            which queues to limit this search to. Any queues other than
     *            RANKED_SOLO_5x5, RANKED_TEAM_5x5, and RANKED_TEAM_3x3 will be
     *            ignored.
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final long summonerID, final List<Champion> champions, final List<QueueType> rankedQueues) {
        return getMatchHistory(summonerID, champions, rankedQueues, null, null);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @param champions
     *            which champions to limit this search to
     * @param rankedQueues
     *            which queues to limit this search to. Any queues other than
     *            RANKED_SOLO_5x5, RANKED_TEAM_5x5, and RANKED_TEAM_3x3 will be
     *            ignored.
     * @param beginIndex
     *            the begin index to use for fetching games. No more than 15
     *            games will be fetched.
     * @param endIndex
     *            the end index to use for fetching games. No more than 15 games
     *            will be fetched.
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final long summonerID, final List<Champion> champions, final List<QueueType> rankedQueues,
            final Integer beginIndex, final Integer endIndex) {
        final List<Integer> championIDs = champions == null ? null : champions.stream().map((champ) -> champ.ID).collect(Collectors.toList());
        final String json = API.getSummonerMatchHistory(summonerID, championIDs, rankedQueues, beginIndex, endIndex);
        try {
            return converter.getMatchHistoryFromJSON((JSONArray)((JSONObject)parser.parse(json)).get("matches"));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final String summonerName) {
        return getMatchHistory(getSummoner(summonerName));
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @param beginIndex
     *            the begin index to use for fetching games. No more than 15
     *            games will be fetched.
     * @param endIndex
     *            the end index to use for fetching games. No more than 15 games
     *            will be fetched.
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final String summonerName, final Integer beginIndex, final Integer endIndex) {
        return getMatchHistory(getSummoner(summonerName), beginIndex, endIndex);
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @param champions
     *            which champions to limit this search to
     * @param rankedQueues
     *            which queues to limit this search to. Any queues other than
     *            RANKED_SOLO_5x5, RANKED_TEAM_5x5, and RANKED_TEAM_3x3 will be
     *            ignored.
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final String summonerName, final List<Champion> champions, final List<QueueType> rankedQueues) {
        return getMatchHistory(getSummoner(summonerName), champions, rankedQueues);
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @param champions
     *            which champions to limit this search to
     * @param rankedQueues
     *            which queues to limit this search to. Any queues other than
     *            RANKED_SOLO_5x5, RANKED_TEAM_5x5, and RANKED_TEAM_3x3 will be
     *            ignored.
     * @param beginIndex
     *            the begin index to use for fetching games. No more than 15
     *            games will be fetched.
     * @param endIndex
     *            the end index to use for fetching games. No more than 15 games
     *            will be fetched.
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final String summonerName, final List<Champion> champions, final List<QueueType> rankedQueues,
            final Integer beginIndex, final Integer endIndex) {
        return getMatchHistory(getSummoner(summonerName), champions, rankedQueues, beginIndex, endIndex);
    }

    /**
     * @param summoner
     *            the summoner to get match history for
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final Summoner summoner) {
        return getMatchHistory(summoner.ID);
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @param beginIndex
     *            the begin index to use for fetching games. No more than 15
     *            games will be fetched.
     * @param endIndex
     *            the end index to use for fetching games. No more than 15 games
     *            will be fetched.
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final Summoner summoner, final Integer beginIndex, final Integer endIndex) {
        return getMatchHistory(summoner.ID, beginIndex, endIndex);
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @param champions
     *            which champions to limit this search to
     * @param rankedQueues
     *            which queues to limit this search to. Any queues other than
     *            RANKED_SOLO_5x5, RANKED_TEAM_5x5, and RANKED_TEAM_3x3 will be
     *            ignored.
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final Summoner summoner, final List<Champion> champions, final List<QueueType> rankedQueues) {
        return getMatchHistory(summoner.ID, champions, rankedQueues);
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @param champions
     *            which champions to limit this search to
     * @param rankedQueues
     *            which queues to limit this search to. Any queues other than
     *            RANKED_SOLO_5x5, RANKED_TEAM_5x5, and RANKED_TEAM_3x3 will be
     *            ignored.
     * @param beginIndex
     *            the begin index to use for fetching games. No more than 15
     *            games will be fetched.
     * @param endIndex
     *            the end index to use for fetching games. No more than 15 games
     *            will be fetched.
     * @return the summoner's match history
     * @see <a href="https://developer.riotgames.com/api/methods#!/805/2847">LoL
     *      API Specification</a>
     */
    public List<MatchSummary> getMatchHistory(final Summoner summoner, final List<Champion> champions, final List<QueueType> rankedQueues,
            final Integer beginIndex, final Integer endIndex) {
        return getMatchHistory(summoner.ID, champions, rankedQueues, beginIndex, endIndex);
    }

    /**
     * Entries are by champion name, include "All Champions" for totals.
     *
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's ranked stats
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1937">LoL
     *      API Specification</a>
     */
    public Map<String, ChampionStats> getRankedStats(final Long summonerID) {
        return getRankedStats(summonerID, null);
    }

    /**
     * Entries are by champion name, include "All Champions" for totals.
     *
     * @param summonerID
     *            the summoner to get information for
     * @param season
     *            the season to get stats for
     * @return the summoner's ranked stats
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1937">LoL
     *      API Specification</a>
     */
    public Map<String, ChampionStats> getRankedStats(final Long summonerID, final Season season) {
        if(!cache.championsFilled) {
            getChampions(); // Cache in bulk to minimize API calls
        }

        final String json = API.getSummonerRankedStatsByID(summonerID, season);
        JSONObject stats;
        try {
            stats = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }

        final JSONArray statList = (JSONArray)stats.get("champions");
        final Map<String, ChampionStats> championStats = new HashMap<String, ChampionStats>();
        for(final Object championStatObj : statList) {
            final JSONObject champStat = (JSONObject)championStatObj;
            final ChampionStats championStat = converter.getChampionStatsFromJSON(champStat);
            if(championStat.champion == null) {
                championStats.put("All Champions", championStat);
            }
            else {
                championStats.put(championStat.champion.name, championStat);
            }
        }

        return Collections.unmodifiableMap(championStats);
    }

    /**
     * Entries are by champion name, include "All Champions" for totals.
     *
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's ranked stats
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1937">LoL
     *      API Specification</a>
     */
    public Map<String, ChampionStats> getRankedStats(final String summonerName) {
        return getRankedStats(getSummoner(summonerName));
    }

    /**
     * Entries are by champion name, include "All Champions" for totals.
     *
     * @param summonerName
     *            the summoner to get information for
     * @param season
     *            the season to get stats for
     * @return the summoner's ranked stats
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1937">LoL
     *      API Specification</a>
     */
    public Map<String, ChampionStats> getRankedStats(final String summonerName, final Season season) {
        return getRankedStats(getSummoner(summonerName), season);
    }

    /**
     * Entries are by champion name, include "All Champions" for totals.
     *
     * @param summoner
     *            the summoner to get information for
     * @return the summoner's ranked stats
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1937">LoL
     *      API Specification</a>
     */
    public Map<String, ChampionStats> getRankedStats(final Summoner summoner) {
        return getRankedStats(summoner.ID);
    }

    /**
     * Entries are by champion name, include "All Champions" for totals.
     *
     * @param summoner
     *            the summoner to get information for
     * @param season
     *            the season to get stats for
     * @return the summoner's ranked stats
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1937">LoL
     *      API Specification</a>
     */
    public Map<String, ChampionStats> getRankedStats(final Summoner summoner, final Season season) {
        return getRankedStats(summoner.ID, season);
    }

    /**
     * @return the realm
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2528">LoL
     *      API Specification</a>
     */
    public Realm getRealm() {
        final String json = API.getRealm();
        try {
            return converter.getRealmFromJSON((JSONObject)parser.parse(json));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's recent games
     * @see <a href="http://developer.riotgames.com/api/methods#!/618/1924">LoL
     *      API Specification</a>
     */
    public List<Game> getRecentGames(final long summonerID) {
        if(!cache.championsFilled) {
            getChampions(); // Cache in bulk to minimize API calls
        }
        if(!cache.summonerSpellsFilled) {
            getSummonerSpells(); // Cache in bulk to minimize API calls
        }

        final String json = API.getSummonerGamesByID(summonerID);
        JSONObject gamesList;
        try {
            gamesList = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }

        return converter.getAllGamesFromJSON((JSONArray)gamesList.get("games"));
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's recent games
     * @see <a href="http://developer.riotgames.com/api/methods#!/618/1924">LoL
     *      API Specification</a>
     */
    public List<Game> getRecentGames(final String summonerName) {
        return getRecentGames(getSummoner(summonerName));
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @return the summoner's recent games
     * @see <a href="http://developer.riotgames.com/api/methods#!/618/1924">LoL
     *      API Specification</a>
     */
    public List<Game> getRecentGames(final Summoner summoner) {
        return getRecentGames(summoner.ID);
    }

    /**
     * @param runeID
     *            the rune to get information for
     * @return the rune
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2525">LoL
     *      API Specification</a>
     */
    public Rune getRune(final int runeID) {
        Rune rune = cache.runes.get(runeID);

        if(rune == null) {
            final Set<RuneData> runeData = new HashSet<RuneData>();
            runeData.add(RuneData.all);
            final String json = API.getRune(runeID, runeData);

            try {
                rune = converter.getRuneFromJSON((JSONObject)parser.parse(json));
            }
            catch(final ParseException e) {
                throw handleParseException(e);
            }
            cache.runes.put(runeID, rune);
        }

        return rune;
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<RunePage>> getRunePages(final List<Summoner> summoners) {
        final Map<Long, List<RunePage>> allPages = getRunePagesByIDs(getIDsFromSummoners(summoners));
        return summonerMapFromID(summoners, allPages);
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<RunePage>> getRunePages(final Summoner... summoners) {
        return getRunePages(summoners);
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @return the summoner's rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public List<RunePage> getRunePages(final Summoner summoner) {
        return getRunePagesByID(summoner.ID);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public List<RunePage> getRunePagesByID(final long summonerID) {
        if(!cache.runesFilled) {
            getRunes(); // Cache in bulk to minimize API calls
        }

        final String json = API.getSummonerRunesByID(summonerID);
        JSONObject summonerList;
        try {
            summonerList = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
        final JSONObject summonerObj = (JSONObject)summonerList.get(Long.toString(summonerID));

        return JSONConverter.getList(summonerObj, "pages", p -> converter.getRunePageFromJSON((JSONObject)p));
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Long, List<RunePage>> getRunePagesByIDs(final List<Long> summonerIDs) {
        if(!cache.runesFilled) {
            getRunes(); // Cache in bulk to minimize API calls
        }

        final JSONObject summonerList = handleIDCountLimit(summonerIDs, (IDs) -> API.getSummonersRunesByID(IDs), STANDARD_ID_LIMIT);
        final Map<Long, List<RunePage>> allPages = new HashMap<Long, List<RunePage>>();
        for(final Long summonerID : summonerIDs) {
            final JSONObject summonerObj = (JSONObject)summonerList.get(Long.toString(summonerID));

            final List<RunePage> runePages = JSONConverter.getList(summonerObj, "pages", p -> converter.getRunePageFromJSON((JSONObject)p));
            allPages.put(summonerID, Collections.unmodifiableList(runePages));
        }
        return Collections.unmodifiableMap(allPages);
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Long, List<RunePage>> getRunePagesByIDs(final Long... summonerIDs) {
        return getRunePagesByIDs(summonerIDs);
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public List<RunePage> getRunePagesByName(final String summonerName) {
        return getRunePages(getSummoner(summonerName));
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<RunePage>> getRunePagesByNames(final List<String> summonerNames) {
        final List<Summoner> summoners = getSummoners(summonerNames);
        return getRunePages(summoners);
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<RunePage>> getRunePagesByNames(final String... summonerNames) {
        return getRunePagesByNames(Arrays.asList(summonerNames));
    }

    /**
     * @return all runes
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2530">LoL
     *      API Specification</a>
     */
    public List<Rune> getRunes() {
        if(cache.runesFilled) {
            final List<Rune> runes = new ArrayList<Rune>();
            runes.addAll(cache.runes.values());
            return Collections.unmodifiableList(runes);
        }

        final Set<RuneData> runeData = new HashSet<RuneData>();
        runeData.add(RuneData.all);

        final String json = API.getRunes(runeData);
        JSONObject runeList;
        try {
            runeList = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }

        final List<Rune> runes = JSONConverter.getListFromMap(runeList, "data", r -> converter.getRuneFromJSON((JSONObject)r));
        for(final Rune rune : runes) {
            cache.runes.put(rune.ID, rune);
        }
        cache.runesFilled = true;

        return runes;
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1930">LoL
     *      API Specification</a>
     */
    public Summoner getSummoner(final String summonerName) {
        if(summonerName == null) {
            return null;
        }

        Summoner summoner = cache.summonerNames.get(summonerName);

        if(summoner == null) {
            final String json = API.getSummoner(summonerName);
            JSONObject list;
            try {
                list = (JSONObject)parser.parse(json);
            }
            catch(final ParseException e) {
                throw handleParseException(e);
            }
            final JSONObject summonerInfo = (JSONObject)list.get(summonerName.toLowerCase().replaceAll("\\s", ""));

            summoner = converter.getSummonerFromJSON(summonerInfo);
            cache.summonerNames.put(summoner.name, summoner);
            cache.summonerIDs.put(summoner.ID, summoner);
        }

        return summoner;
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1931">LoL
     *      API Specification</a>
     */
    public Summoner getSummonerByID(final long summonerID) {
        Summoner summoner = cache.summonerIDs.get(summonerID);

        if(summoner == null) {
            final String json = API.getSummonerByID(summonerID);
            JSONObject list;
            try {
                list = (JSONObject)parser.parse(json);
            }
            catch(final ParseException e) {
                throw handleParseException(e);
            }
            final JSONObject summonerInfo = (JSONObject)list.get(Long.toString(summonerID));

            summoner = converter.getSummonerFromJSON(summonerInfo);
            cache.summonerNames.put(summoner.name, summoner);
            cache.summonerIDs.put(summoner.ID, summoner);
        }

        return summoner;
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's name
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1934">LoL
     *      API Specification</a>
     */
    public String getSummonerName(final long summonerID) {
        final String json = API.getSummonerName(summonerID);
        JSONObject list;
        try {
            list = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
        final String name = (String)list.get(Long.toString(summonerID));

        return name;
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' names
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1934">LoL
     *      API Specification</a>
     */
    public List<String> getSummonerNames(final List<Long> summonerIDs) {
        final JSONObject list = handleIDCountLimit(summonerIDs, (IDs) -> API.getSummonerNames(IDs), STANDARD_ID_LIMIT);

        final List<String> names = new ArrayList<String>(summonerIDs.size());
        for(final Long summonerID : summonerIDs) {
            names.add((String)list.get(summonerID.toString()));
        }

        return Collections.unmodifiableList(names);
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' names
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1934">LoL
     *      API Specification</a>
     */
    public List<String> getSummonerNames(final Long... summonerIDs) {
        return getSummonerNames(Arrays.asList(summonerIDs));
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1930">LoL
     *      API Specification</a>
     */
    public List<Summoner> getSummoners(final List<String> summonerNames) {
        final List<String> summonerNamesLeft = new ArrayList<String>(summonerNames);
        summonerNamesLeft.removeAll(cache.summonerNames.keySet());

        if(summonerNamesLeft.size() > 0) {
            final JSONObject list = handleIDCountLimit(summonerNamesLeft, (names) -> API.getSummoners(names), STANDARD_ID_LIMIT);

            for(final String summonerName : summonerNamesLeft) {
                final JSONObject summonerInfo = (JSONObject)list.get(summonerName.toLowerCase().replaceAll("\\s", ""));
                if(summonerInfo != null) {
                    final Summoner summoner = converter.getSummonerFromJSON(summonerInfo);
                    cache.summonerNames.put(summoner.name, summoner);
                    cache.summonerIDs.put(summoner.ID, summoner);
                }
            }
        }

        final List<Summoner> summoners = new ArrayList<Summoner>(summonerNames.size());
        for(final String summonerName : summonerNames) {
            summoners.add(cache.summonerNames.get(summonerName));
        }
        return Collections.unmodifiableList(summoners);
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1930">LoL
     *      API Specification</a>
     */
    public List<Summoner> getSummoners(final String... summonerNames) {
        return getSummoners(Arrays.asList(summonerNames));
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1931">LoL
     *      API Specification</a>
     */
    public List<Summoner> getSummonersByID(final List<Long> summonerIDs) {
        final List<Long> summonerIDsLeft = new ArrayList<Long>(summonerIDs);
        summonerIDsLeft.removeAll(cache.summonerIDs.keySet());

        if(summonerIDsLeft.size() > 0) {
            final JSONObject list = handleIDCountLimit(summonerIDs, (IDs) -> API.getSummonersByID(IDs), STANDARD_ID_LIMIT);

            for(final Long summonerID : summonerIDsLeft) {
                final JSONObject summonerInfo = (JSONObject)list.get(Long.toString(summonerID));
                if(summonerInfo != null) {
                    final Summoner summoner = converter.getSummonerFromJSON(summonerInfo);
                    cache.summonerNames.put(summoner.name, summoner);
                    cache.summonerIDs.put(summoner.ID, summoner);
                }
            }
        }

        final List<Summoner> summoners = new ArrayList<Summoner>(summonerIDs.size());
        for(final Long summonerID : summonerIDs) {
            summoners.add(cache.summonerIDs.get(summonerID));
        }
        return Collections.unmodifiableList(summoners);
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1931">LoL
     *      API Specification</a>
     */
    public List<Summoner> getSummonersByID(final Long... summonerIDs) {
        return getSummonersByID(Arrays.asList(summonerIDs));
    }

    /**
     * @param spellID
     *            the spell to get information for
     * @return the summoner spell
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2524">LoL
     *      API Specification</a>
     */
    public SummonerSpell getSummonerSpell(final int spellID) {
        SummonerSpell spell = cache.summonerSpells.get(spellID);

        if(spell == null) {
            final Set<SummonerSpellData> spellData = new HashSet<SummonerSpellData>();
            spellData.add(SummonerSpellData.all);
            final String json = API.getSummonerSpell(spellID, spellData);

            try {
                spell = converter.getSummonerSpellFromJSON((JSONObject)parser.parse(json));
            }
            catch(final ParseException e) {
                throw handleParseException(e);
            }
            cache.summonerSpells.put(spellID, spell);
        }

        return spell;
    }

    /**
     * @return all summoner spells
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2532">LoL
     *      API Specification</a>
     */
    public List<SummonerSpell> getSummonerSpells() {
        if(cache.summonerSpellsFilled) {
            final List<SummonerSpell> summonerSpells = new ArrayList<SummonerSpell>();
            summonerSpells.addAll(cache.summonerSpells.values());
            return Collections.unmodifiableList(summonerSpells);
        }

        final Set<SummonerSpellData> summonerSpellData = new HashSet<SummonerSpellData>();
        summonerSpellData.add(SummonerSpellData.all);

        final String json = API.getSummonerSpells(summonerSpellData);
        JSONObject summonerSpellList;
        try {
            summonerSpellList = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }

        final List<SummonerSpell> summonerSpells = JSONConverter.getListFromMap(summonerSpellList, "data",
                r -> converter.getSummonerSpellFromJSON((JSONObject)r));
        for(final SummonerSpell summonerSpell : summonerSpells) {
            cache.summonerSpells.put(summonerSpell.ID, summonerSpell);
        }
        cache.summonerSpellsFilled = true;

        return summonerSpells;
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's stats summary
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1938">LoL
     *      API Specification</a>
     */
    public Map<PlayerStatsSummaryType, PlayerStatsSummary> getSummonerStats(final Long summonerID) {
        return getSummonerStats(summonerID, null);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @param season
     *            the season to get stats for
     * @return the summoner's stats summary
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1938">LoL
     *      API Specification</a>
     */
    public Map<PlayerStatsSummaryType, PlayerStatsSummary> getSummonerStats(final Long summonerID, final Season season) {
        final String json = API.getSummonerStatsByID(summonerID, season);
        JSONObject stats;
        try {
            stats = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }

        final JSONArray statList = (JSONArray)stats.get("playerStatSummaries");
        final Map<PlayerStatsSummaryType, PlayerStatsSummary> queueStats = new HashMap<PlayerStatsSummaryType, PlayerStatsSummary>();
        for(final Object queueStatsObj : statList) {
            final JSONObject qStat = (JSONObject)queueStatsObj;
            final PlayerStatsSummary queueStat = converter.getPlayerStatsSummaryFromJSON(qStat);
            queueStats.put(queueStat.playerStatSummaryType, queueStat);
        }

        return Collections.unmodifiableMap(queueStats);
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's stats summary
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1938">LoL
     *      API Specification</a>
     */
    public Map<PlayerStatsSummaryType, PlayerStatsSummary> getSummonerStats(final String summonerName) {
        return getSummonerStats(getSummoner(summonerName));
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @param season
     *            the season to get stats for
     * @return the summoner's stats summary
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1938">LoL
     *      API Specification</a>
     */
    public Map<PlayerStatsSummaryType, PlayerStatsSummary> getSummonerStats(final String summonerName, final Season season) {
        return getSummonerStats(getSummoner(summonerName), season);
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @return the summoner's stats summary
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1938">LoL
     *      API Specification</a>
     */
    public Map<PlayerStatsSummaryType, PlayerStatsSummary> getSummonerStats(final Summoner summoner) {
        return getSummonerStats(summoner.ID);
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @param season
     *            the season to get stats for
     * @return the summoner's stats summary
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1938">LoL
     *      API Specification</a>
     */
    public Map<PlayerStatsSummaryType, PlayerStatsSummary> getSummonerStats(final Summoner summoner, final Season season) {
        return getSummonerStats(summoner.ID, season);
    }

    /**
     * @param teamID
     *            the team to get information for
     * @return the team
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2645">LoL
     *      API Specification</a>
     */
    public Team getTeam(final String teamID) {
        final String json = API.getTeam(teamID);
        JSONObject teamList;
        try {
            teamList = (JSONObject)parser.parse(json);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
        final JSONObject team = (JSONObject)teamList.get(teamID);

        return converter.getTeamFromJSON(team);
    }

    /**
     * @param teamIDs
     *            the teams to get information for
     * @return the teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2645">LoL
     *      API Specification</a>
     */
    public List<Team> getTeams(final List<String> teamIDs) {
        final JSONObject teamList = handleIDCountLimit(teamIDs, (IDs) -> API.getTeams(IDs), LEAGUE_TEAM_ID_LIMIT);
        return converter.getAllTeamsFromJSON(teamList, teamIDs);
    }

    /**
     * @param teamIDs
     *            the teams to get information for
     * @return the teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2645">LoL
     *      API Specification</a>
     */
    public List<Team> getTeams(final String... teamIDs) {
        return getTeams(Arrays.asList(teamIDs));
    }

    /**
     * @param summoner
     *            the summoner to get information for
     * @return the summoner's teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public List<Team> getTeamsBySummoner(final Summoner summoner) {
        return getTeamsBySummonerID(summoner.ID);
    }

    /**
     * @param summonerID
     *            the summoner to get information for
     * @return the summoner's teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public List<Team> getTeamsBySummonerID(final Long summonerID) {
        final String json = API.getSummonerTeamsByID(summonerID);
        try {
            return JSONConverter.getList((JSONObject)parser.parse(json), Long.toString(summonerID), t -> converter.getTeamFromJSON((JSONObject)t));
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public Map<Long, List<Team>> getTeamsBySummonerIDs(final List<Long> summonerIDs) {
        final JSONObject summonerList = handleIDCountLimit(summonerIDs, (IDs) -> API.getSummonersTeamsByID(IDs), LEAGUE_TEAM_ID_LIMIT);
        return converter.getAllSummonersTeamsFromJSON(summonerList, summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the summoners to get information for
     * @return the summoners' teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public Map<Long, List<Team>> getTeamsBySummonerIDs(final Long... summonerIDs) {
        return getTeamsBySummonerIDs(Arrays.asList(summonerIDs));
    }

    /**
     * @param summonerName
     *            the summoner to get information for
     * @return the summoner's teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public List<Team> getTeamsBySummonerName(final String summonerName) {
        return getTeamsBySummoner(getSummoner(summonerName));
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<Team>> getTeamsBySummonerNames(final List<String> summonerNames) {
        final List<Summoner> summoners = getSummoners(summonerNames);
        return getTeamsBySummoners(summoners);
    }

    /**
     * @param summonerNames
     *            the summoners to get information for
     * @return the summoners' teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<Team>> getTeamsBySummonerNames(final String... summonerNames) {
        return getTeamsBySummonerNames(Arrays.asList(summonerNames));
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<Team>> getTeamsBySummoners(final List<Summoner> summoners) {
        final Map<Long, List<Team>> byID = getTeamsBySummonerIDs(getIDsFromSummoners(summoners));
        return summonerMapFromID(summoners, byID);
    }

    /**
     * @param summoners
     *            the summoners to get information for
     * @return the summoners' teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public Map<Summoner, List<Team>> getTeamsBySummoners(final Summoner... summoners) {
        return getTeamsBySummoners(Arrays.asList(summoners));
    }

    /**
     * @return the current allowable versions
     * @see <a href="http://developer.riotgames.com/api/methods#!/710/2527">LoL
     *      API Specification</a>
     */
    public List<String> getVersions() {
        final String json = API.getVersions();
        try {
            return JSONConverter.getList((JSONArray)parser.parse(json), s -> (String)s);
        }
        catch(final ParseException e) {
            throw handleParseException(e);
        }
    }
}
