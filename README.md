# Orianna V2

A Java adaptation of the Riot Games LoL API (http://developer.riotgames.com/).

Orianna is the sister library to [Cassiopeia](https://github.com/robrua/cassiopeia) (Python). It's been designed with usability in mind - making sure all the bookkeeping is done right so you can focus on getting the data you need and building your application. There's two main entry points - RiotAPI and BaseRiotAPI. The former handles a ton of stuff behind the scenes to make your development experience awesome, while the latter allows you fine-grained control by exposing the Riot API exactly as the website's documentation specifies.

## Features (RiotAPI)

- Usability-focused type system to make your life easy
- Automatically throttles requests to fit rate limits
- Ensures well-formed API requests
- Replaces foreign key ID values with the referenced object
- Option to lazy load referenced objects right when you need them or batch them up and load them upfront to minimize API calls
- Caches static data and summoner information to accelerate access and reduce API load
- Available automatic databasing using [Hibernate](http://hibernate.org/). See [the project here.](https://github.com/robrua/orianna-hibernate)

## Features (BaseRiotAPI)

- Meets the Riot API specification exactly
- Automatically throttles requests to fit rate limits
- Ensures well-formed API requests
- Make only the requests you want to make, no foreign keys are auto-filled
 
## Setup

Just [download](https://github.com/robrua/Orianna/releases) the latest .jar and add it to your project's build path.
 
To do this in eclipse, I recommend creating a lib/ directory in your project's root directory and putting the .jar there. Then just right click the .jar in eclipse and click Build Path -> Add to Build Path.

You can also find the latest SNAPSHOT build [here](http://robrua.com/orianna).

If you use Maven to manage your dependencies, Orianna is posted on Maven Central:

Standard Java
```xml
<dependency>
  <groupId>com.robrua</groupId>
  <artifactId>orianna</artifactId>
  <version>2.4.2</version>
</dependency>
```

Android
```xml
<dependency>
  <groupId>com.robrua</groupId>
  <artifactId>orianna-android</artifactId>
  <version>2.4.2</version>
</dependency>
```

Or using Gradle:

Standard Java
```
repositories {
    mavenCentral()
}

dependencies {
	compile 'com.robrua:orianna:2.4.2'
}
```

Android
```
repositories {
    mavenCentral()
}

dependencies {
	compile 'com.robrua:orianna-android:2.4.2'
}
```

## Dependencies

Orianna relies on [Apache HttpClient](http://hc.apache.org/httpcomponents-client-ga/) v4.3.5 (Android v4.3.5.1) and [Google GSON](https://code.google.com/p/google-gson/) v2.3.1. Both are included in the JARs distributed here.
 
## Usage

Here's an example of a few basic uses of the API. The full JavaDoc can be found at http://robrua.github.io/Orianna/.

```java
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.summoner.Summoner;

public class Example {
    public static void main(String[] args) {
        RiotAPI.setRegion(Region.NA);
        RiotAPI.setAPIKey("YOUR-API-KEY-HERE");
        
        Summoner summoner = RiotAPI.getSummonerByName("FatalElement");
        System.out.println(summoner.getName() + " is a level " + summoner.getLevel() + " summoner on the NA server.");
        
        List<Champion> champions = RiotAPI.getChampions();
        System.out.println("He enjoys playing LoL on all different champions, like " + champions.get((int)(champions.size() * Math.random())) + ".");
        
        League challenger = RiotAPI.getChallenger(QueueType.RANKED_SOLO_5x5);
        Summoner bestNA = challenger.getEntries().get(0).getSummoner();
        System.out.println("He's much better at writing Java code than he is at LoL. He'll never be as good as " + bestNA + ".");
    }
}
```

Make sure you set your rate limit! Orianna will limit you the the default development limit until you give it your production limit (if you have one).

```java
// 3,000 calls per 10 seconds
RiotAPI.setRateLimit(3000, 10);
// 3,000 calls per 10 seconds AND 180,000 calls per 10 minutes
RiotAPI.setRateLimit(new RateLimit(3000, 10), new RateLimit(180000, 600));
```

You can also set a load policy for filling in foreign key values to optimize internal call usage. UPFRONT will load everything ASAP, and will batch together calls where possible to minimize call usage. LAZY will load things as you ask for them, so you can save calls if you don't use some values, but it won't be able to take as much advantage of bulk loading.

```java
// Upfront loading is the default strategy
RiotAPI.setLoadPolicy(LoadPolicy.UPFRONT);
RiotAPI.setLoadPolicy(LoadPolicy.LAZY);
```

For you Android devs out there there's also a new AsyncRiotAPI, which will perform all of your API calls on a different thread (since no networking is allowed on the main thread).

```java
import java.util.List;

import com.robrua.orianna.api.core.AsyncRiotAPI;
import com.robrua.orianna.type.api.Action;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

public class Example {
    public static void main(String[] args) {
        AsyncRiotAPI.setRegion(Region.NA);
        AsyncRiotAPI.setAPIKey("YOUR-API-KEY-HERE");
        
        AsyncRiotAPI.getSummonerByName(new Action<Summoner>() {
            @Override
            public void perform(Summoner summoner) {
                System.out.println(summoner.getName() + " is a level " + summoner.getLevel() + " summoner on the NA server.");
            }
            
            public void handle(APIException e) {
                System.out.println("Couldn't get summoner FatalElement");
            }
        }, "FatalElement");
        
        AsyncRiotAPI.getChampions(new Action<List<Champion>>() {
            @Override
            public void perform(List<Champion> champions) {
                System.out.println("He enjoys playing LoL on all different champions, like " + champions.get((int)(champions.size() * Math.random())) + ".");
            }
            
            public void handle(APIException e) {
                System.out.println("Couldn't get champion list.");
            }
        });

        AsyncRiotAPI.getChallenger(new Action<League>() {
            @Override
            public void perform(League challenger) {
                Summoner bestNA = challenger.getEntries().get(0).getSummoner();
                System.out.println("He's much better at writing Java code than he is at LoL. He'll never be as good as " + bestNA + ".");
            }
            
            public void handle(APIException e) {
                System.out.println("Couldn't get solo queue challenger league.");
            }
        }, QueueType.RANKED_SOLO_5x5);
    }
}
```

Or, if you don't want all the bells and whistles and you'd just like to access the Riot API as the specification says, you can use BaseRiotAPI (there's also an AsyncBaseRiotAPI).

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.dto.league.League;
import com.robrua.orianna.type.dto.staticdata.Champion;
import com.robrua.orianna.type.dto.staticdata.ChampionList;
import com.robrua.orianna.type.dto.summoner.Summoner;

public class Example {
    public static void main(String[] args) {
        BaseRiotAPI.setRegion(Region.NA);
        BaseRiotAPI.setAPIKey("YOUR-API-KEY-HERE");
        
        Map<String, Summoner> summoners = BaseRiotAPI.getSummonersByName("FatalElement");
        Summoner summoner = summoners.get("fatalelement");
        System.out.println(summoner.getName() + " is a level " + summoner.getSummonerLevel() + " summoner on the NA server.");
        
        ChampionList champs = BaseRiotAPI.getChampions();
        List<Champion> champions = new ArrayList<>(champs.getData().values());
        System.out.println("He enjoys playing LoL on all different champions, like " + champions.get((int)(champions.size() * Math.random())).getName() + ".");
        
        League challenger = BaseRiotAPI.getChallenger(QueueType.RANKED_SOLO_5x5);
        String aChallenger = challenger.getEntries().get(0).getPlayerOrTeamName();
        System.out.println("He's much better at writing Java code than he is at LoL. He'll never be as good as " + aChallenger + ".");
    }
}
```

## JavaDoc
[Found Here](http://robrua.github.io/Orianna/)

## Download
[Releases](https://github.com/robrua/Orianna/releases)/[Snapshot](http://robrua.com/orianna)

## Questions/Contributions
Feel free to send pull requests or to contact me via github or email (robrua@alumni.cmu.edu).

## Bugs
There's probably typos or some data missing somewhere in the project. Let me know about any of them you run into. I'm also looking for consistent maintainers to help me out as the Riot API evolves.

## Disclaimer
Orianna isn't endorsed by Riot Games and doesn't reflect the views or opinions of Riot Games or anyone officially involved in producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. League of Legends © Riot Games, Inc.
