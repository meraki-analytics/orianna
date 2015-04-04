package com.robrua.orianna.api.dto;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.api.MultiRateLimiter;
import com.robrua.orianna.type.api.RateLimit;
import com.robrua.orianna.type.api.RateLimiter;
import com.robrua.orianna.type.api.SingleRateLimiter;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.dto.currentgame.CurrentGameInfo;
import com.robrua.orianna.type.dto.featuredgames.FeaturedGames;
import com.robrua.orianna.type.dto.game.RecentGames;
import com.robrua.orianna.type.dto.league.League;
import com.robrua.orianna.type.dto.match.MatchDetail;
import com.robrua.orianna.type.dto.matchhistory.PlayerHistory;
import com.robrua.orianna.type.dto.staticdata.ChampionSpell;
import com.robrua.orianna.type.dto.staticdata.Item;
import com.robrua.orianna.type.dto.staticdata.ItemList;
import com.robrua.orianna.type.dto.staticdata.LanguageStrings;
import com.robrua.orianna.type.dto.staticdata.MapData;
import com.robrua.orianna.type.dto.staticdata.Mastery;
import com.robrua.orianna.type.dto.staticdata.MasteryList;
import com.robrua.orianna.type.dto.staticdata.Realm;
import com.robrua.orianna.type.dto.staticdata.Rune;
import com.robrua.orianna.type.dto.staticdata.RuneList;
import com.robrua.orianna.type.dto.staticdata.SummonerSpell;
import com.robrua.orianna.type.dto.staticdata.SummonerSpellList;
import com.robrua.orianna.type.dto.stats.PlayerStatsSummaryList;
import com.robrua.orianna.type.dto.stats.RankedStats;
import com.robrua.orianna.type.dto.status.Shard;
import com.robrua.orianna.type.dto.status.ShardStatus;
import com.robrua.orianna.type.dto.summoner.MasteryPages;
import com.robrua.orianna.type.dto.summoner.RunePages;
import com.robrua.orianna.type.dto.summoner.Summoner;
import com.robrua.orianna.type.dto.team.Team;
import com.robrua.orianna.type.exception.APIException;
import com.robrua.orianna.type.exception.OriannaException;

/**
 * Queries the <a href="http://developer.riotgames.com/api/methods">LoL REST
 * API</a> for information, returning Java objects matching the exact API spec
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public abstract class BaseRiotAPI {
    static final Map<String, String> API_VERSIONS;
    private static String APIKey;
    private static CloseableHttpClient CLIENT = HttpClients.createDefault();
    static final Gson GSON = new GsonBuilder().registerTypeAdapter(ChampionSpell.class, new ChampionSpellDeserializer())
            .registerTypeAdapter(SummonerSpell.class, new SummonerSpellDeserializer()).create();
    static Region mirror, region;
    private static boolean printCalls = false;
    private static HttpHost proxy;
    private static RateLimiter rateLimiter = RiotAPI.getDefaultDevelopmentRateLimiter();

    static {
        API_VERSIONS = new HashMap<>();
        API_VERSIONS.put("champion", "v1.2");
        API_VERSIONS.put("current-game", "v1.0");
        API_VERSIONS.put("featured-games", "v1.0");
        API_VERSIONS.put("game", "v1.3");
        API_VERSIONS.put("league", "v2.5");
        API_VERSIONS.put("static-data", "v1.2");
        API_VERSIONS.put("status", "v1.0");
        API_VERSIONS.put("match", "v2.2");
        API_VERSIONS.put("matchhistory", "v2.2");
        API_VERSIONS.put("stats", "v1.3");
        API_VERSIONS.put("summoner", "v1.4");
        API_VERSIONS.put("team", "v2.4");
        API_VERSIONS.put("challenge", "v4.1");
    }

    /**
     * Consumes the entity, closing resources
     *
     * @param entity
     *            the entity to consume
     * @throws IOException
     */
    private static void consume(final HttpEntity entity) throws IOException {
        if(entity == null) {
            return;
        }
        if(entity.isStreaming()) {
            final InputStream instream = entity.getContent();
            if(instream != null) {
                instream.close();
            }
        }
    }

    /**
     * Uses Apache HttpClient to make a GET request to the server and return the
     * JSON response. Blocks if necessary to meet rate limits. Throws an
     * APIException if the server sends an error code.
     *
     * @param request
     *            the latter part of the request (everything following /api/lol/
     *            but without params)
     * @param params
     *            the parameters to use for the request. API Key is
     *            automatically added in this method.
     * @param staticServer
     *            whether to query the static data sever or not (regional
     *            server)
     * @return the JSON response from the API
     */
    static String get(final String request, final Map<String, String> params, final boolean staticServer) {
        // Check that mirror, region, and API key are set
        if(region == null) {
            throw new OriannaException("Must set region for the API using setRegion before the server can be queried!");
        }
        if(mirror == null) {
            throw new OriannaException("Must set mirror for the API using setRegion before the server can be queried!");
        }
        if(APIKey == null) {
            throw new OriannaException("Must set API key for using setAPIKey before the server can be queried!");
        }

        // Convert params for Apache HTTP
        final List<NameValuePair> param = new ArrayList<>();
        param.add(new BasicNameValuePair("api_key", APIKey));

        if(params != null) {
            for(final String name : params.keySet()) {
                param.add(new BasicNameValuePair(name, params.get(name)));
            }
        }

        // Build URI for request
        final String server = staticServer ? "global" : mirror.toString().toLowerCase();
        final String rgn = staticServer ? "static-data/" + region.toString().toLowerCase() : region.toString().toLowerCase();

        // Make request
        try {
            final URI uri = new URIBuilder().setScheme("https").setHost(server + ".api.pvp.net").setPath("/api/lol/" + rgn + "/" + request)
                    .setParameters(param).build();
            return get(uri, staticServer);
        }
        catch(final URISyntaxException e) {
            throw new OriannaException("Generated http request wasn't valid! Report this to the Orianna team.");
        }
    }

    /**
     * Uses Apache HttpClient to make a GET request to the server and return the
     * JSON response. Blocks if necessary to meet rate limits. Throws an
     * APIException if the server sends an error code.
     *
     * @param uri
     *            the URI to send the request to
     * @param staticServer
     *            whether to query the static data sever or not (regional
     *            server)
     * @return the JSON response from the API
     */
    static String get(final URI uri, final boolean staticServer) {
        // Wait for a call to be available if this is a call to a rate
        // limited server
        if(!staticServer) {
            rateLimiter.waitForCall();
        }

        boolean registered = false;

        // Send request to Riot and register call
        try {
            final HttpGet get = new HttpGet(uri);
            if(proxy != null) {
                final RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
                get.setConfig(config);
            }

            if(printCalls) {
                System.out.println(uri);
            }

            final CloseableHttpResponse response = CLIENT.execute(get);
            try {
                final HttpEntity entity = response.getEntity();
                final String content = EntityUtils.toString(entity);
                consume(entity);

                // Handle API errors
                if(response.getStatusLine().getStatusCode() == 429) {
                    // Force rate limiter to wait after a 429
                    final int retryAfter = Integer.parseInt(response.getFirstHeader("Retry-After").getValue()) + 1;
                    rateLimiter.resetIn(retryAfter * 1000L);

                    // Release resources and exit from rate limited call, then
                    // retry call
                    response.close();
                    rateLimiter.registerCall();
                    registered = true;
                    return get(uri, staticServer);
                }
                else if(response.getStatusLine().getStatusCode() != 200) {
                    throw new APIException(uri.toString(), response.getStatusLine().getStatusCode());
                }

                return content;
            }
            finally {
                if(!registered) {
                    response.close();
                }
            }
        }
        catch(final IOException e) {
            throw new OriannaException("Request to Riot server failed! Report this to the Orianna team.");
        }
        finally {
            if(!staticServer && !registered) {
                rateLimiter.registerCall();
            }
        }
    }

    /**
     * @param queueType
     *            the queue type to get the challenger league for
     * @return the challenger league
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/936/3243">Riot
     *      API Specification</a>
     */
    public static League getChallenger(final QueueType queueType) {
        return LeagueAPI.getChallenger(queueType);
    }

    /**
     * @param ID
     *            the champion's ID
     * @return the champion
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3256">Riot
     *      API Specification</a>
     */
    public static com.robrua.orianna.type.dto.staticdata.Champion getChampion(final long ID) {
        return StaticDataAPI.getChampion(ID);
    }

    /**
     * @return the list all of champions
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3260">Riot
     *      API Specification</a>
     */
    public static com.robrua.orianna.type.dto.staticdata.ChampionList getChampions() {
        return StaticDataAPI.getChampions();
    }

    /**
     * @param ID
     *            the ID of the champion to look up
     * @return the champion
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/958/3289">Riot
     *      API Specification</a>
     */
    public static com.robrua.orianna.type.dto.champion.Champion getChampionStatus(final long ID) {
        return ChampionAPI.getChampionStatus(ID);
    }

    /**
     * @param freeToPlay
     *            whether to only return free champions
     * @return all champions
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/958/3290">Riot
     *      API Specification</a>
     */
    public static com.robrua.orianna.type.dto.champion.ChampionList getChampionStatuses(final boolean freeToPlay) {
        return ChampionAPI.getChampionStatuses(freeToPlay);
    }

    /**
     * @param summonerID
     *            summoner to look up current game for
     * @return the summoner's current game
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/956/3287">Riot
     *      API Specification</a>
     */
    public static CurrentGameInfo getCurrentGame(final long summonerID) {
        return CurrentGameAPI.getCurrentGame(summonerID);
    }

    /**
     * @return the featured games
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/957/3288">Riot
     *      API Specification</a>
     */
    public static FeaturedGames getFeaturedGames() {
        return FeaturedGamesAPI.getFeaturedGames();
    }

    /**
     * @param ID
     *            the item's ID
     * @return the item
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3253">Riot
     *      API Specification</a>
     */
    public static Item getItem(final long ID) {
        return StaticDataAPI.getItem(ID);
    }

    /**
     * @return the list of all items
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3248">Riot
     *      API Specification</a>
     */
    public static ItemList getItems() {
        return StaticDataAPI.getItems();
    }

    /**
     * @return the languages
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3258">Riot
     *      API Specification</a>
     */
    public static List<String> getLanguages() {
        return StaticDataAPI.getLanguages();
    }

    /**
     * @return the language strings
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3250">Riot
     *      API Specification</a>
     */
    public static LanguageStrings getLanguageStrings() {
        return StaticDataAPI.getLanguageStrings();
    }

    /**
     * @return the map information
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3262">Riot
     *      API Specification</a>
     */
    public static MapData getMapInformation() {
        return StaticDataAPI.getMapInformation();
    }

    /**
     * @return the list of all masteries
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3251">Riot
     *      API Specification</a>
     */
    public static MasteryList getMasteries() {
        return StaticDataAPI.getMasteries();
    }

    /**
     * @param ID
     *            the mastery's ID
     * @return the mastery
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3252">Riot
     *      API Specification</a>
     */
    public static Mastery getMastery(final long ID) {
        return StaticDataAPI.getMastery(ID);
    }

    /**
     * @param ID
     *            the ID of the match to look up
     * @return the match
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/967/3313">Riot
     *      API Specification</a>
     */
    public static MatchDetail getMatch(final long ID) {
        return MatchAPI.getMatch(ID);
    }

    /**
     * Gets the 15 most recent matches for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID) {
        return MatchHistoryAPI.getMatchHistory(summonerID);
    }

    /**
     * Gets the 15 most recent matches after beginIndex for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final int beginIndex) {
        return MatchHistoryAPI.getMatchHistory(summonerID, beginIndex);
    }

    /**
     * Gets the 15 most recent matches after beginIndex for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param championIDs
     *            the champions to limit games to
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final int beginIndex, final List<Long> championIDs) {
        return MatchHistoryAPI.getMatchHistory(summonerID, beginIndex, championIDs);
    }

    /**
     * Gets the 15 most recent matches after beginIndex for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final int beginIndex, final QueueType queueType) {
        return MatchHistoryAPI.getMatchHistory(summonerID, beginIndex, queueType);
    }

    /**
     * Gets the 15 most recent matches after beginIndex for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final int beginIndex, final QueueType queueType, final List<Long> championIDs) {
        return MatchHistoryAPI.getMatchHistory(summonerID, beginIndex, queueType, championIDs);
    }

    /**
     * Gets the 15 most recent matches for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param championIDs
     *            the champions to limit games to
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final List<Long> championIDs) {
        return MatchHistoryAPI.getMatchHistory(summonerID, championIDs);
    }

    /**
     * Gets the 15 most recent matches for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final QueueType queueType) {
        return MatchHistoryAPI.getMatchHistory(summonerID, queueType);
    }

    /**
     * Gets the 15 most recent matches for the summoner
     *
     * @param summonerID
     *            the ID of the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     * @return the match history for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/966/3312">Riot
     *      API Specification</a>
     */
    public static PlayerHistory getMatchHistory(final long summonerID, final QueueType queueType, final List<Long> championIDs) {
        return MatchHistoryAPI.getMatchHistory(summonerID, queueType, championIDs);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get ranked stats for
     * @return the ranked stats for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/961/3297">Riot
     *      API Specification</a>
     */
    public static RankedStats getRankedStats(final long summonerID) {
        return StatsAPI.getRankedStats(summonerID);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get ranked stats for
     * @param season
     *            the season to get stats for
     * @return the ranked stats for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/961/3297">Riot
     *      API Specification</a>
     */
    public static RankedStats getRankedStats(final long summonerID, final Season season) {
        return StatsAPI.getRankedStats(summonerID, season);
    }

    /**
     * @return the realm
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3259">Riot
     *      API Specification</a>
     */
    public static Realm getRealm() {
        return StaticDataAPI.getRealm();
    }

    /**
     * @param summonerID
     *            the ID of the summoner to look up recent games for
     * @return the summoner's recent games
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/959/3291">Riot
     *      API Specification</a>
     */
    public static RecentGames getRecentGames(final long summonerID) {
        return GameAPI.getRecentGames(summonerID);
    }

    /**
     * Uses Apache HttpClient to make a GET request to the server and return the
     * JSON response. Blocks if necessary to meet rate limits. Throws an
     * APIException if the server sends an error code.
     *
     * @param request
     *            the latter part of the request (everything following the host)
     * @param params
     *            the parameters to use for the request. API Key is
     *            automatically added in this method.
     * @param staticServer
     *            whether to query the static data sever or not (regional
     *            server)
     * @return the JSON response from the API
     */
    static String getRoot(final String request, final Map<String, String> params, final boolean staticServer) {
        // Check that mirror, region, and API key are set
        if(mirror == null) {
            throw new OriannaException("Must set mirror for the API using setRegion before the server can be queried!");
        }
        if(APIKey == null) {
            throw new OriannaException("Must set API key for using setAPIKey before the server can be queried!");
        }

        // Convert params for Apache HTTP
        final List<NameValuePair> param = new ArrayList<>();
        param.add(new BasicNameValuePair("api_key", APIKey));

        if(params != null) {
            for(final String name : params.keySet()) {
                param.add(new BasicNameValuePair(name, params.get(name)));
            }
        }

        // Make request
        try {
            final String server = staticServer ? "global" : mirror.toString().toLowerCase();
            final URI uri = new URIBuilder().setScheme("https").setHost(server + ".api.pvp.net").setPath(request).setParameters(param).build();
            return get(uri, staticServer);
        }
        catch(final URISyntaxException e) {
            throw new OriannaException("Generated http request wasn't valid! Report this to the Orianna team.");
        }
    }

    /**
     * @param ID
     *            the rune's ID
     * @return the rune
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3255">Riot
     *      API Specification</a>
     */
    public static Rune getRune(final long ID) {
        return StaticDataAPI.getRune(ID);
    }

    /**
     * @return the list of all runes
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3249">Riot
     *      API Specification</a>
     */
    public static RuneList getRunes() {
        return StaticDataAPI.getRunes();
    }

    /**
     * @param region
     *            the region's shard to get
     * @return the shard
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/908/3142">Riot
     *      API Specification</a>
     */
    public static ShardStatus getShard(final Region region) {
        return StatusAPI.getShard(region);
    }

    /**
     * @return the list of all shards
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/908/3143">Riot
     *      API Specification</a>
     */
    public static List<Shard> getShards() {
        return StatusAPI.getShards();
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get stats for
     * @return the stats for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/961/3298">Riot
     *      API Specification</a>
     */
    public static PlayerStatsSummaryList getStats(final long summonerID) {
        return StatsAPI.getStats(summonerID);
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get stats for
     * @param season
     *            the season to get stats for
     * @return the stats for that summoner
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/961/3298">Riot
     *      API Specification</a>
     */
    public static PlayerStatsSummaryList getStats(final long summonerID, final Season season) {
        return StatsAPI.getStats(summonerID, season);
    }

    /**
     * @param summonerIDs
     *            the summoners to get league entries for
     * @return the summoners' league entries
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/936/3245">Riot
     *      API Specification</a>
     */
    public static Map<Long, List<League>> getSummonerLeagueEntries(final List<Long> summonerIDs) {
        return LeagueAPI.getSummonerLeagueEntries(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the summoners to get league entries for
     * @return the summoners' league entries
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/936/3245">Riot
     *      API Specification</a>
     */
    public static Map<Long, List<League>> getSummonerLeagueEntries(final long... summonerIDs) {
        return LeagueAPI.getSummonerLeagueEntries(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the summoners to get leagues for
     * @return the summoners' league
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/936/3241">Riot
     *      API Specification</a>
     */
    public static Map<Long, List<League>> getSummonerLeagues(final List<Long> summonerIDs) {
        return LeagueAPI.getSummonerLeagues(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the summoners to get leagues for
     * @return the summoners' league
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/936/3241">Riot
     *      API Specification</a>
     */
    public static Map<Long, List<League>> getSummonerLeagues(final long... summonerIDs) {
        return LeagueAPI.getSummonerLeagues(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get
     * @return the summoners
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/960/3293">Riot
     *      API Specification</a>
     */
    public static Map<Long, Summoner> getSummonersByID(final List<Long> summonerIDs) {
        return SummonerAPI.getSummonersByID(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get
     * @return the summoners
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/960/3293">Riot
     *      API Specification</a>
     */
    public static Map<Long, Summoner> getSummonersByID(final long... summonerIDs) {
        return SummonerAPI.getSummonersByID(summonerIDs);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get
     * @return the summoners
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/960/3292">Riot
     *      API Specification</a>
     */
    public static Map<String, Summoner> getSummonersByName(final List<String> summonerNames) {
        return SummonerAPI.getSummonersByName(summonerNames);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get
     * @return the summoners
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/960/3292">Riot
     *      API Specification</a>
     */
    public static Map<String, Summoner> getSummonersByName(final String... summonerNames) {
        return SummonerAPI.getSummonersByName(summonerNames);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get masteries for
     * @return the summoners' masteries
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/960/3295">Riot
     *      API Specification</a>
     */
    public static Map<Long, MasteryPages> getSummonersMasteries(final List<Long> summonerIDs) {
        return SummonerAPI.getSummonersMasteries(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get masteries for
     * @return the summoners' masteries
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/960/3295">Riot
     *      API Specification</a>
     */
    public static Map<Long, MasteryPages> getSummonersMasteries(final long... summonerIDs) {
        return SummonerAPI.getSummonersMasteries(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get names of
     * @return the summoners' names
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/960/3296">Riot
     *      API Specification</a>
     */
    public static Map<Long, String> getSummonersNames(final List<Long> summonerIDs) {
        return SummonerAPI.getSummonersNames(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get names of
     * @return the summoners' names
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/960/3296">Riot
     *      API Specification</a>
     */
    public static Map<Long, String> getSummonersNames(final long... summonerIDs) {
        return SummonerAPI.getSummonersNames(summonerIDs);
    }

    /**
     * @param ID
     *            the summoner spell's ID
     * @return the summoner spell
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3254">Riot
     *      API Specification</a>
     */
    public static SummonerSpell getSummonerSpell(final long ID) {
        return StaticDataAPI.getSummonerSpell(ID);
    }

    /**
     * @return the list of all summoner spells
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3261">Riot
     *      API Specification</a>
     */
    public static SummonerSpellList getSummonerSpells() {
        return StaticDataAPI.getSummonerSpells();
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get runes for
     * @return the summoners' runes
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/960/3294">Riot
     *      API Specification</a>
     */
    public static Map<Long, RunePages> getSummonersRunes(final List<Long> summonerIDs) {
        return SummonerAPI.getSummonersRunes(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get runes for
     * @return the summoners' runes
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/960/3294">Riot
     *      API Specification</a>
     */
    public static Map<Long, RunePages> getSummonersRunes(final long... summonerIDs) {
        return SummonerAPI.getSummonersRunes(summonerIDs);
    }

    /**
     * @param teamIDs
     *            the summoners to get leagues for
     * @return the team's leagues
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/936/3242">Riot
     *      API Specification</a>
     */
    public static Map<String, List<League>> getTeamLeagueEntries(final List<String> teamIDs) {
        return LeagueAPI.getTeamLeagueEntries(teamIDs);
    }

    /**
     * @param teamIDs
     *            the summoners to get leagues for
     * @return the team's leagues
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/936/3242">Riot
     *      API Specification</a>
     */
    public static Map<String, List<League>> getTeamLeagueEntries(final String... teamIDs) {
        return LeagueAPI.getTeamLeagueEntries(teamIDs);
    }

    /**
     * @param teamIDs
     *            the summoners to get leagues for
     * @return the team's leagues
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/936/3242">Riot
     *      API Specification</a>
     */
    public static Map<String, List<League>> getTeamLeagues(final List<String> teamIDs) {
        return LeagueAPI.getTeamLeagues(teamIDs);
    }

    /**
     * @param teamIDs
     *            the summoners to get leagues for
     * @return the team's leagues
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/936/3242">Riot
     *      API Specification</a>
     */
    public static Map<String, List<League>> getTeamLeagues(final String... teamIDs) {
        return LeagueAPI.getTeamLeagues(teamIDs);
    }

    /**
     * @param IDs
     *            the IDs of the teams
     * @return the teams
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/937/3246">Riot
     *      API Specification</a>
     */
    public static Map<String, Team> getTeamsByID(final List<String> IDs) {
        return TeamAPI.getTeamsByID(IDs);
    }

    /**
     * @param IDs
     *            the IDs of the teams
     * @return the teams
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/937/3246">Riot
     *      API Specification</a>
     */
    public static Map<String, Team> getTeamsByID(final String... IDs) {
        return TeamAPI.getTeamsByID(IDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     * @return the summoners' teams
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/937/3247">Riot
     *      API Specification</a>
     */
    public static Map<Long, List<Team>> getTeamsBySummoner(final List<Long> summonerIDs) {
        return TeamAPI.getTeamsBySummoner(summonerIDs);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     * @return the summoners' teams
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/937/3247">Riot
     *      API Specification</a>
     */
    public static Map<Long, List<Team>> getTeamsBySummoner(final long... summonerIDs) {
        return TeamAPI.getTeamsBySummoner(summonerIDs);
    }

    /**
     * @param bucketStartTime
     *            the start time for the 5-minute bucket to query
     * @return randomized match IDs from that bucket
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/980/3340">Riot
     *      API Specification</a>
     */
    public static List<Long> getURFMatchIDs(final Date bucketStartTime) {
        return ChallengeAPI.getURFMatchIDs(bucketStartTime);
    }

    /**
     * @param bucketStartTime
     *            the start time for the 5-minute bucket to query (in epoch
     *            milliseconds)
     * @return randomized match IDs from that bucket
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/980/3340">Riot
     *      API Specification</a>
     */
    public static List<Long> getURFMatchIDs(final long bucketStartTime) {
        return ChallengeAPI.getURFMatchIDs(bucketStartTime);
    }

    /**
     * @return the versions
     * @see <a
     *      href="https://developer.riotgames.com/api/methods#!/938/3257">Riot
     *      API Specification</a>
     */
    public static List<String> getVersions() {
        return StaticDataAPI.getVersions();
    }

    /**
     * If turned on, prints the URI of calls made to stdout
     *
     * @param on
     *            whether to print the URI of calls to stdout
     */
    public static void printCalls(final boolean on) {
        printCalls = on;
    }

    /**
     * Removes any set proxy
     */
    public static void removeProxy() {
        proxy = null;
    }

    /**
     * Sets the API Key to use for queries
     *
     * @param newAPIKey
     *            the API Key to use for queries
     */
    public static void setAPIKey(final String newAPIKey) {
        APIKey = newAPIKey;
    }

    /**
     * Sets the server mirror to hit for queries
     *
     * @param newMirror
     *            the server mirror to hit for queries
     */
    public static void setMirror(final Region newMirror) {
        mirror = newMirror;
    }

    /**
     * Sets the proxy to access the API through
     *
     * @param IP
     *            the IP address of the proxy server
     * @param port
     *            the working port for the proxy server
     */
    public static void setProxy(final String IP, final int port) {
        proxy = new HttpHost(IP, port);
    }

    /**
     * Sets multiple new rate limits for the API, removing any old ones
     *
     * @param limits
     *            the rate limits to apply
     */
    public static void setRateLimit(final Collection<RateLimit> limits) {
        rateLimiter = new MultiRateLimiter(limits);
    }

    /**
     * Sets a new rate limit for the API, removing any old ones
     *
     * @param callsPerEpoch
     *            the number of calls allowed in each epoch
     * @param secondsPerEpoch
     *            the number of seconds in each epoch
     */
    public static void setRateLimit(final int callsPerEpoch, final int secondsPerEpoch) {
        rateLimiter = new SingleRateLimiter(callsPerEpoch, secondsPerEpoch);
    }

    /**
     * Sets a new rate limit for the API, removing any old ones
     *
     * @param limit
     *            the rate limit
     */
    public static void setRateLimit(final RateLimit limit) {
        rateLimiter = new SingleRateLimiter(limit);
    }

    /**
     * Sets multiple new rate limits for the API, removing any old ones
     *
     * @param limits
     *            the rate limits to apply
     */
    public static void setRateLimit(final RateLimit... limits) {
        rateLimiter = new MultiRateLimiter(limits);
    }

    /**
     * Sets the region to get information from the API for
     *
     * @param newRegion
     *            the region to get information from the API for
     */
    public static void setRegion(final Region newRegion) {
        region = newRegion;
    }
}
