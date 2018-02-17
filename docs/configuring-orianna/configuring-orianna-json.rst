.. _configuring-orianna-json:

*****************************
JSON-Based Configuration File
*****************************

Below, we'll discuss each element of the JSON configuration, what it does, and what the available options are for that setting. First, though, let's talk about about how to create and load a ``config.json`` file.

Creating and Loading Your Configuration
=======================================

The first step in writing your own ``config.json`` is to download the default configuration file, :download:`available here <../../orianna/src/main/resources/com/merakianalytics/orianna/default-orianna-config.json>`.
Use the default configuration as a base for your ``config.json`` file, adding, removing, and editing settings as needed for your application.
You can save your ``config.json`` wherever you'd like on your computer, but it's recommended to put it in your application resources directory. This is usually the ``src/main/resources`` directory within your Java project.

Once you've finalized your ``config.json``, you can have Orianna load it using one of the following methods. Add one of these calls to the initialization of your application to ensure Orianna is correctly configured.

.. code-block:: java

    // Use this method if you placed your config.json in your application resources directory
    Orianna.loadConfiguration("config.json");

    // Use this method if you placed your config.json somewhere else on your computer
    Orianna.loadConfiguration(new File("/path/to/your/config.json"));

Alternatively, if you set your ``ORIANNA_CONFIGURATION_PATH`` environment variable to the path to your ``config.json``, Orianna will automatically pick it up when it loads.

Full Default Configuration
==========================

Now that we know how to load a JSON configuration, let's break down the default configuration to see what settings are available in Orianna. First, here's the full default configuration:

.. literalinclude:: ../../orianna/src/main/resources/com/merakianalytics/orianna/default-orianna-config.json
    :language: json

Configuration Options
=====================

There's a lot here, so let's break it down piece by piece and see what options are available.

Default Platform
----------------

Normally when making calls using Orianna, you have to specify which Region/Platform you'd like to do the request for. If you're primarily using one Platform/Region in your application, you can set it as the default to be used if no Platform/Region is included in the request.

.. code-block:: json

    {
      "defaultPlatform": "NORTH_AMERICA"
    }

The full set of available options for the ``defaultPlatform`` can be found `here <https://github.com/meraki-analytics/orianna/blob/master/orianna/src/main/java/com/merakianalytics/orianna/types/common/Platform.java#L12-L22>`__.

Default Locale
--------------

When requesting static-data from Orianna, data must be requested with a specific locale, which determines what language the returned data will be in. If a ``defaultLocale`` isn't set, the default locale for the Platform/Region the request is for will be used.

.. code-block:: json

    {
      "defaultLocale": "en_US"
    }

You can check the full list of available locales for a Platform/Region with the following Orianna call:

.. code-block:: java

    System.out.println(Languages.withPlatform(Platform.NORTH_AMERICA).get());

Current Version Expiration
--------------------------

Whenever static-data is requested in Orianna without a version specified, it defaults to using the current version for the data.
Orianna caches the current version for each Platform/Region to minimize how often it has to be requested from Orianna's DataSources.

.. code-block:: json

    {
      "currentVersionExpiration": {
        "period": 6,
        "unit": "HOURS"
      }
    }

If you set ``period < 0`` it will be cached until your Java process terminates. If you set ``period == 0`` it won't be cached and will be requested every time it's needed.

Data Pipeline
-------------

The last, and most complicated pieces of the Orianna configuration is the ``pipeline``. It determines how data is retrieved and cached within Orianna. Here's what the skeleton of a ``pipeline`` looks like with nothing in it:

.. code-block:: json

    {
      "pipeline": {
        "elements": [],
        "transformers": []
      }
    }

Please check out :ref:`the Data Pipeline page <data-pipeline>` for the details about what ``elements`` and ``transformers`` are available and when to use them. For example purposes, let's add the RiotAPI as a DataSource in the ``pipeline`` to see how to add elements.
We'll use the minimal settings for illustration purposes.

.. code-block:: json

    {
      "pipeline": {
        "elements": [
          {
            "className": "com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI",
            "configClassName": "com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI$Configuration",
            "config": {
              "services": [
                "com.merakianalytics.orianna.datapipeline.riotapi.ChampionAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.ChampionMasteryAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.LeagueAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.MatchAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.SpectatorAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.StaticDataAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.StatusAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.SummonerAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.ThirdPartyCodeAPI"
              ]
            }
          }
        ],
        "transformers": []
      }
    }

There are essentially 3 parts to each ``pipeline`` element:

    1) ``className`` is set to the fully qualified Java class name of the desired Pipeline Element.

    2) ``configClassName`` is set to the fully qualified Java class name of the Pipeline Element's configuration class. It's assumed that the Pipeline Element class will have a constructor which takes just one argument of this type, or if no ``config`` is included, it must have a no-argument constructor.

    3) ``config`` is set to the configuration to use for the Pipeline Element. This will be deserialized into the ``configClassName`` class and passed as the single argument to the constructor for ``className`` .

We also need to add the dto-to-data Transformers to make Orianna usable with just the RiotAPI in the pipeline.

.. code-block:: json

    {
      "pipeline": {
        "elements": [
          {
            "className": "com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI",
            "configClassName": "com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI$Configuration",
            "config": {
              "services": [
                "com.merakianalytics.orianna.datapipeline.riotapi.ChampionAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.ChampionMasteryAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.LeagueAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.MatchAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.SpectatorAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.StaticDataAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.StatusAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.SummonerAPI",
                "com.merakianalytics.orianna.datapipeline.riotapi.ThirdPartyCodeAPI"
              ]
            }
          }
        ],
        "transformers": [
          {
            "className": "com.merakianalytics.orianna.datapipeline.transformers.dtodata.ChampionMasteryTransformer"
          }, {
            "className": "com.merakianalytics.orianna.datapipeline.transformers.dtodata.ChampionTransformer"
          }, {
            "className": "com.merakianalytics.orianna.datapipeline.transformers.dtodata.LeagueTransformer"
          }, {
            "className": "com.merakianalytics.orianna.datapipeline.transformers.dtodata.MatchTransformer"
          }, {
            "className": "com.merakianalytics.orianna.datapipeline.transformers.dtodata.SpectatorTransformer"
          }, {
            "className": "com.merakianalytics.orianna.datapipeline.transformers.dtodata.StaticDataTransformer"
          }, {
            "className": "com.merakianalytics.orianna.datapipeline.transformers.dtodata.StatusTransformer"
          }, {
            "className": "com.merakianalytics.orianna.datapipeline.transformers.dtodata.SummonerTransformer"
          }, {
            "className": "com.merakianalytics.orianna.datapipeline.transformers.dtodata.ThirdPartyCodeTransformer"
          }
        ]
      }
    }

Each ``transformers`` element only has 1 part, the ``className``. It's assumed that Transformers will have no-argument constructors that will be used to create them.

Hopefully this illustrates how to add a new Pipeline Element or Transformer to Orianna's Data Pipeline. Please check out :ref:`the Data Pipeline page <data-pipeline>` for the details about what ``elements`` and ``transformers`` are available and when to use them.
