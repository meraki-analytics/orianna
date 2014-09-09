# Orianna

A Java adaptation of the Riot Games LoL API (http://developer.riotgames.com/). Requires Java 8.

## Features

- Replaces foreign key ID values with the referenced object
- Makes most efficient use of API calls to minimize load
- Caches static data and summoner information to accelerate access
- Automatically throttles requests to fit rate limits
- Ensures well-formed API requests
 
## Setup

Just [download](https://github.com/robrua/Orianna/releases) the latest .jar and add it to your project's build path.
 
To do this in eclipse, I recommend creating a lib/ directory in your project's root directory and putting the .jar there. Then just right click the .jar in eclipse and click Build Path -> Add to Build Path.

If you use Maven to manage your dependencies, you Orianna is posted on Maven Central @ com.robrua.orianna
 
## Usage

The library loosely follows Riot's API specifications (http://developer.riotgames.com/) with a few changes for ease of use.
Here's some examples of a few simple uses of the API. The full JavaDoc can be found at http://robrua.github.io/Orianna/.
 

```java
import java.util.List;

import com.robrua.orianna.api.RateLimiter;
import com.robrua.orianna.api.RiotAPI;
import com.robrua.orianna.api.queryspecs.Region;
import com.robrua.orianna.type.league.League;
import com.robrua.orianna.type.league.LeagueType;
import com.robrua.orianna.type.staticdata.Champion;
import com.robrua.orianna.type.summoner.Summoner;

public class Example {
    public static void main(String[] args) {
        // This constructor uses a rate limiter to throttle your requests automatically so you don't exceed your limit.
        // There's also a constructor to allow to you manage your rate limit yourself, but I recommend using this one.
        RiotAPI API = new RiotAPI(Region.NA, "YOUR-API-KEY-HERE", RateLimiter.defaultDevelopmentRateLimiter());
        
        Summoner summoner = API.getSummoner("FatalElement");
        String name = summoner.name;
        long level = summoner.summonerLevel;
        
        List<Champion> champions = API.getChampions();
        String aChampionName = champions.get(0).name;
        
        League challenger = API.getChallengerLeague(LeagueType.RANKED_SOLO_5x5);
        Summoner bestNA = challenger.entries.get(0).player;
    }
}
```

You can also use the JSONRiotAPI if for some reason you'd like to do the JSON parsing yourself. I use JSON.simple for JSON parsing (https://code.google.com/p/json-simple/).
If you choose to use JSON.simple then JSONConverter can help convert data to the Orianna type system.

## JavaDoc
[Found Here](http://robrua.github.io/Orianna/)

## Download
[Check Releases](https://github.com/robrua/Orianna/releases)

## Questions/Contributions
Feel free to send pull requests or to contact me via github or email (robrua@alumni.cmu.edu).

## Bugs
There's probably typos or some data missing somewhere in the project. Let me know about any of them you run into.

## Disclaimer
This product is not endorsed, certified or otherwise approved in any way by Riot Games, Inc. or any of its affiliates.
