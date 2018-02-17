#####################
Orianna Documentation
#####################

What is Orianna?
================

Orianna is a Java framework for working with data from the `Riot API <https://developer.riotgames.com/>`__. It's focused on usability and convenience, taking care of the all the little details that come with using the Riot API for you, so you can focus on building your application.
Here's a quick overview of some of the main features that set Orianna apart from other Riot API wrappers:

- An enhanced user interface that makes using the Riot API easy and fun
    - Restructured and renamed API data for maximum clarity
    - Fluent APIs for requesting data
    - Automatic conversion of foreign keys into the objects they specify (e.g player.getChampion() instead of player.getChampionId())
- Rate limits handled automatically with optimal usage of your API key
- Configurable handling of API errors (e.g. automatic retry on 500 errors)
- Built-in automatic caching, ready out of the box
- DataDragon support
- A highly configurable pipeline for requesting and caching Riot API data
    - Allows flexibility in what database(s) you want to use to cache your data
    - Off-the-shelf support for many popular databases under construction `here <https://github.com/meraki-analytics/orianna-datastores>`__
    - Extensible to seamlessly integrate other data types and APIs, such as the ChampionGG API (ChampionGG support coming soon!)

Orianna is the sister library to `Cassiopeia <https://github.com/meraki-analytics/cassiopeia>`__ (Python).

Orianna is distributed under the `MIT License <https://github.com/meraki-analytics/cassiopeia/blob/master/LICENSE.txt>`__.

Documentation Overview
======================

.. toctree::
    :maxdepth: 3

    how-to-get-orianna
    using-orianna
    configuring-orianna
    how-orianna-works
    data-pipeline
    JavaDocs <http://javadoc.io/doc/com.merakianalytics.orianna/orianna>
    GitHub <https://github.com/meraki-analytics/orianna>

A Few Examples
==============

First, we'll quickly and efficiently look up the champion masteries for the summoner "FatalElement" (one of the developers) and print the champions he is best at:

.. code-block:: java

    import java.util.List;
    import java.util.stream.Collectors;

    import com.merakianalytics.orianna.Orianna;
    import com.merakianalytics.orianna.types.common.Platform;
    import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;
    import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;

    public class Example {
        public static void main(String[] args) {
            Orianna.setRiotAPIKey("YOUR-API-KEY");
            Orianna.setDefaultPlatform(Platform.NORTH_AMERICA);

            Summoner fatalElement = Summoner.named("FatalElement").get();
            ChampionMasteries masteries = fatalElement.getChampionMasteries();
            List<ChampionMastery> goodWith = masteries.filter((ChampionMastery mastery) -> mastery.getLevel() >= 6);

            List<String> names = goodWith.stream().map((ChampionMastery mastery) -> mastery.getChampion().getName()).collect(Collectors.toList());
            System.out.println("[" + String.join(", ", names) + "]");
        }
    }

At the time of writing, this prints ``[Taliyah, Kennen, Thresh, Kog'Maw, Annie, Bard, Miss Fortune, Ashe, Swain]``.

Next, we'll compare the combined Team K/D/A scores from his most recent match:

.. code-block:: java

    import com.merakianalytics.orianna.Orianna;
    import com.merakianalytics.orianna.types.common.Platform;
    import com.merakianalytics.orianna.types.common.Side;
    import com.merakianalytics.orianna.types.core.match.Match;
    import com.merakianalytics.orianna.types.core.match.MatchHistory;
    import com.merakianalytics.orianna.types.core.match.Participant;
    import com.merakianalytics.orianna.types.core.match.Team;
    import com.merakianalytics.orianna.types.core.summoner.Summoner;

    public class Example {
        public static void main(String[] args) {
            Orianna.setRiotAPIKey("YOUR-API-KEY");
            Orianna.setDefaultPlatform(Platform.NORTH_AMERICA);

            Summoner summoner = Summoner.named("FatalElement").get();
            MatchHistory matches = summoner.matchHistory().get();
            Match lastMatch = matches.get(0);

            Participant fatalElement = lastMatch.getParticipants().find((Participant participant) -> participant.getSummoner().equals(summoner));

            int kills = 0;
            int deaths = 0;
            int assists = 0;
            for(Participant participant : fatalElement.getTeam().getParticipants()) {
                kills += participant.getStats().getKills();
                deaths += participant.getStats().getDeaths();
                assists += participant.getStats().getAssists();
            }

            System.out.println("FatalElement's Team: " + kills + "/" + deaths + "/" + assists);

            kills = 0;
            deaths = 0;
            assists = 0;
            Team otherTeam = fatalElement.getTeam().getSide() == Side.BLUE ? lastMatch.getRedTeam() : lastMatch.getBlueTeam();
            for(Participant participant : otherTeam.getParticipants()) {
                kills += participant.getStats().getKills();
                deaths += participant.getStats().getDeaths();
                assists += participant.getStats().getAssists();
            }

            System.out.println("Other Team: " + kills + "/" + deaths + "/" + assists);
        }
    }

At the time of writing, this prints ``FatalElement's Team: 43/31/78`` and ``Other Team: 31/43/70``.
