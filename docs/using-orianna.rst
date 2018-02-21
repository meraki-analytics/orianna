.. _using-orianna:

*************
Using Orianna
*************

Orianna is a feature-rich framework for working with Riot API data. When used out-of-the-box, Orianna pulls data from the Riot API (Static Data comes from DataDragon) and caches it in-memory behind the scenes. Just ask for the data you want and caching is handled automatically.
You can also configure Orianna to use a different cache, database, or combination of caches and databases. It can also be configured to pull data from sources other than the Riot API or DataDragon.
See :ref:`the configuration documentation<configuring-orianna>` for more information on how to do this.

Orianna APIs
============

There are two top-level APIs for accessing data with Orianna. Both use Fluent APIs to define the parameters for API requests.
The two APIs get the same data, but they look a little different. Here's an example:

- Data API: ``Summoner.named("FatalElement").withRegion(Region.NORTH_AMERICA).get()``
- Orianna API: ``Orianna.summonerNamed("FatalElement").withRegion(Region.NORTH_AMERICA).get()``

.. toctree::
    :maxdepth: 2

    using-orianna/type-api
    using-orianna/orianna-api
