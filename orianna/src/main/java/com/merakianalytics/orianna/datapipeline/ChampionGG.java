package com.merakianalytics.orianna.datapipeline;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.google.common.base.*;
import com.google.common.collect.*;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.sources.*;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.datapipeline.common.*;
import com.merakianalytics.orianna.types.common.*;
import com.merakianalytics.orianna.types.core.match.*;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.dto.DataObject;
import com.merakianalytics.orianna.types.dto.championgg.*;
import org.slf4j.*;

import java.io.*;
import java.math.RoundingMode;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.*;
import java.util.*;
import java.util.Map;

public class ChampionGG extends AbstractDataSource
{
    private final HTTPClient client;
    private static final Logger                       LOGGER                      = LoggerFactory.getLogger(ChampionGG.class);
    private static final String                       DEFAULT_CHAMPION_GG_API_KEY = System.getenv("CHAMPION_GG_API_KEY");
    public static final  DecimalFormat                DECIMAL_FORMAT              = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    private              Function<JsonNode, JsonNode> preProcessor                = new Function<JsonNode, JsonNode>()
    {
        @Override
        public JsonNode apply(JsonNode input)
        {
            return new ObjectNode(JsonNodeFactory.instance).set("data", input);
        }
    };
    
    
    public ChampionGG()
    {
        client = new HTTPClient();
        DECIMAL_FORMAT.setRoundingMode(RoundingMode.HALF_UP);
    }
    
    // TODO: ChampionGG API is ratelimited, so this is not really a solution for the endgame
    private String get(final String tags, final String elo, final int count, final int championId)
    {
        StringBuilder url          = new StringBuilder("http://api.champion.gg/v2/champions/");
        String        usedTags     = tags;
        String        usedElo      = elo;
        String        usedCount    = String.valueOf(count);
        String        usedChampion = String.valueOf(championId);
        
        if ("ALL".equals(tags))
        {
            usedTags = "kda,damage,minions,wins,wards,positions,normalized,averageGames,overallPerformanceScore,goldEarned,sprees,hashes,wins,maxMins,matchups";
        }
        
        if ("ALL".equals(elo))
        {
            usedElo = "BRONZE,SILVER,GOLD,PLATINUM,PLATINUM,DIAMOND,MASTER,CHALLENGER";
        }
        
        if (-1 == count)
        {
            usedCount = "10000";
        }
        
        if (-1 == championId)
        {
            usedChampion = "";
        }
        
        url.append(usedChampion);
        
        Map<String, String> parameters = ImmutableMap.of("limit", usedCount, "elo", usedElo, "champData", usedTags, "api_key", DEFAULT_CHAMPION_GG_API_KEY);
        url.append("?limit=").append(usedCount);
        url.append("&elo=").append(usedElo);
        url.append("&champData=").append(usedTags);
        url.append("&api_key=").append(DEFAULT_CHAMPION_GG_API_KEY);
        
        try
        {
            //return client.get(url.toString()).getBody();
            return callUrl(url.toString());
        } catch (final TimeoutException e)
        {
            LOGGER.info(String.format("Get request timed out to %s!", url), e);
            return null;
        } catch (final IOException e)
        {
            LOGGER.error(String.format("Get request failed to %s!", url), e);
            throw new OriannaException(String.format("Something went wrong with a request to ChampionGG API at %s! Report this to the orianna team.", url), e);
        }
    }
    
    private String callUrl(String inUrl) throws IOException
    {
        final StringBuilder joiner = new StringBuilder();
        final URL           outUrl = new URL(inUrl);
        final URLConnection uc     = outUrl.openConnection();
        uc.setUseCaches(false);
        uc.setDoInput(true);
        uc.setDoOutput(true);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8)))
        {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                joiner.append(inputLine);
                joiner.append("\n");
            }
            in.close();
        }
        return joiner.toString();
    }
    
    @Get(ChampionDataList.class)
    public ChampionDataList getChampions(final Map<String, Object> query, final PipelineContext context)
    {
        final String           content = get("", "", -1, -1);
        final ChampionDataList data    = DataObject.fromJSON(ChampionDataList.class, preProcessor, content);
        if (data == null)
        {
            return null;
        }
        
        return data;
    }
    
    
    public static ChampionGGRolePredictionResults predictRoles(SearchableList<Participant> ownTeam)
    {
        final ChampionDataList dataSet = Orianna.getSettings().getPipeline().get(ChampionDataList.class, null);
        ChampionGGRolePredictor.prepareDataset(dataSet);
        
        List<Integer> champions  = new ArrayList<>(Collections2.transform(ownTeam, toChampionID));
        List<Integer> smiteUsers = new ArrayList<>(Collections2.transform(ownTeam.filter(hasSmite), toChampionID));
        
        ChampionGGRoleAssignment defaults = new ChampionGGRoleAssignment(null, smiteUsers.size() == 1 ? smiteUsers.get(0) : null, null, null, null);
        
        return ChampionGGRolePredictor.iterativeGetRoles(champions, defaults);
    }
    
    
    private static Function<Participant, Integer> toChampionID = new Function<Participant, Integer>()
    {
        @Override
        public Integer apply(Participant input)
        {
            return input.getChampion().getId();
        }
    };
    
    private static Predicate<Participant> hasSmite = new Predicate<Participant>()
    {
        @Override
        public boolean apply(Participant input)
        {
            // 11 == smite id
            return input.getSummonerSpellD().getId() == 11 || input.getSummonerSpellF().getId() == 11;
        }
    };
    
    public static void printResults(Match match, SearchableList<Participant> teammates, Side team, ChampionGGRolePredictionResults results)
    {
        StringBuilder alternative = new StringBuilder();
        if (results.getSecondRoles() != null)
        {
            for (ChampionGGRole role : ChampionGGRole.values())
            {
                if (!results.getBestRoles().get(role).equals(results.getSecondRoles().get(role)))
                {
                    alternative.append(String.format("%s: %s", role, Champion.withId(results.getSecondRoles().get(role)).get().getName()));
                }
            }
        }
        
        Participant toplaner = teammates.filter(matchesRole(results.getBestRoles(), ChampionGGRole.TOP)).get(0);
        Participant jungler  = teammates.filter(matchesRole(results.getBestRoles(), ChampionGGRole.JUNGLE)).get(0);
        Participant midlaner = teammates.filter(matchesRole(results.getBestRoles(), ChampionGGRole.MIDDLE)).get(0);
        Participant carry    = teammates.filter(matchesRole(results.getBestRoles(), ChampionGGRole.DUO_CARRY)).get(0);
        Participant helper   = teammates.filter(matchesRole(results.getBestRoles(), ChampionGGRole.DUO_SUPPORT)).get(0);
        
        String top = String.format("%s (%s %s)", Champion.withId(results.getBestRoles().get(ChampionGGRole.TOP)).get().getName(), toplaner.getSummonerSpellD(), toplaner.getSummonerSpellF());
        String jun = String.format("%s (%s %s)", Champion.withId(results.getBestRoles().get(ChampionGGRole.JUNGLE)).get().getName(), jungler.getSummonerSpellD(), jungler.getSummonerSpellF());
        String mid = String.format("%s (%s %s)", Champion.withId(results.getBestRoles().get(ChampionGGRole.MIDDLE)).get().getName(), midlaner.getSummonerSpellD(), midlaner.getSummonerSpellF());
        String adc = String.format("%s (%s %s)", Champion.withId(results.getBestRoles().get(ChampionGGRole.DUO_CARRY)).get().getName(), carry.getSummonerSpellD(), carry.getSummonerSpellF());
        String sup = String.format("%s (%s %s)", Champion.withId(results.getBestRoles().get(ChampionGGRole.DUO_SUPPORT)).get().getName(), helper.getSummonerSpellD(), helper.getSummonerSpellF());
        
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Match: %s:%s", match.getPlatform().toString().toUpperCase(Locale.ENGLISH), match.getId())).append("\n");
        sb.append(String.format("Team: %s (%s)", team, team.getId())).append("\n");
        sb.append(String.format("Probability: %s%%", results.getProbability())).append("\n");
        sb.append(String.format("Confidence: %s%%", results.getConficende())).append("\n");
        sb.append("Top: ").append(top).append("\n");
        sb.append("Jungle: ").append(jun).append("\n");
        sb.append("Mid: ").append(mid).append("\n");
        sb.append("ADC: ").append(adc).append("\n");
        sb.append("Support: ").append(sup).append("\n");
        sb.append(String.format("Alternative roles: [%s]", alternative.toString())).append("\n");
        
        System.out.println(sb.toString());
    }
    
    private static Predicate<Participant> matchesRole(final Map<ChampionGGRole, Integer> bestRoles, final ChampionGGRole lane)
    {
        return new Predicate<Participant>()
        {
            @Override
            public boolean apply(Participant input)
            {
                return input.getChampion().getId() == bestRoles.get(lane);
            }
        };
    }
}
