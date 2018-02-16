.. _configuring-orianna-programmatic:

******************************
Programmatic Configuration API
******************************

Below, we'll discuss each element of the programmatic configuration API, what it does, and what the available options are for that setting. First, though, let's talk about about how to create and load a new Configuration.

Creating and Loading Your Configuration
=======================================

The first step in setting your configuration is to create a Configuration object.

.. code-block:: java

    Orianna.Configuration config = new Orianna.Configuration();

This will create a configuration with all the default settings. You can make changes using the mutator methods on the Configuration object.

Once you've finalized your Configuration, you can have Orianna load it using the following method. Add this call to the initialization of your application to ensure Orianna is correctly configured.

.. code-block:: java

    Orianna.loadConfiguration(config);

Configuration Options
=====================

Now that we know how to load a Configuration, let's break down the options and see what settings are available in Orianna.

Default Platform
----------------

Normally when making calls using Orianna, you have to specify which Region/Platform you'd like to do the request for. If you're primarily using one Platform/Region in your application, you can set it as the default to be used if no Platform/Region is included in the request.

.. code-block:: java

    config.setDefaultPlatform(Platform.NORTH_AMERICA);

The full set of available options for the ``defaultPlatform`` can be found `here <https://github.com/meraki-analytics/orianna/blob/master/orianna/src/main/java/com/merakianalytics/orianna/types/common/Platform.java#L12-L22>`__.

Default Locale
--------------

When requesting static-data from Orianna, data must be requested with a specific locale, which determines what language the returned data will be in. If a ``defaultLocale`` isn't set, the default locale for the Platform/Region the request is for will be used.

.. code-block:: java

    Orianna.setDefaultLocale("en_US");

You can check the full list of available locales for a Platform/Region with the following Orianna call:

.. code-block:: java

    System.out.println(Languages.withPlatform(Platform.NORTH_AMERICA).get());

Current Version Expiration
--------------------------

Whenever static-data is requested in Orianna without a version specified, it defaults to using the current version for the data.
Orianna caches the current version for each Platform/Region to minimize how often it has to be requested from Orianna's DataSources.

.. code-block:: java

    config.setCurrentVersionExpiration(ExpirationPeriod.create(6, TimeUnit.HOURS));

If you set ``period < 0`` it will be cached until your Java process terminates. If you set ``period == 0`` it won't be cached and will be requested every time it's needed.

Data Pipeline
-------------

The last, and most complicated pieces of the Orianna configuration is the ``pipeline``. It determines how data is retrieved and cached within Orianna, and is composed of ``elements`` and ``transformers``.

Please check out :ref:`the Data Pipeline page <data-pipeline>` for the details about what ``elements`` and ``transformers`` are available and when to use them. For example purposes, let's add the RiotAPI as a DataSource in the ``pipeline`` to see how to add elements.
We'll use the default settings for illustration purposes.

.. code-block:: java

    PipelineConfiguration pipeline = new PipelineConfiguration();
    List<PipelineElementConfiguration> elements = Arrays.asList(new PipelineElementConfiguration[] {
        PipelineElementConfiguration.defaultConfiguration(RiotAPI.class)
    });
    pipeline.setElements(elements);
    config.setPipeline(pipeline);

If you need to users settings other than default configuration for a Pipeline Element, it's suggested to use :ref:`the JSON-based configuration file <configuring-orianna-json>` configuration approach instead, as it's far less complicated for detailed configuration changes.

We also need to add the dto-to-data Transformers to make Orianna usable with just the RiotAPI in the pipeline.

.. code-block:: java

    PipelineConfiguration pipeline = new PipelineConfiguration();

    List<PipelineElementConfiguration> elements = Arrays.asList(new PipelineElementConfiguration[] {
        PipelineElementConfiguration.defaultConfiguration(RiotAPI.class)
    });
    pipeline.setElements(elements);

    final Set<TransformerConfiguration> transformers = new HashSet<>(Arrays.asList(new TransformerConfiguration[] {
        TransformerConfiguration.defaultConfiguration(ChampionMasteryTransformer.class),
        TransformerConfiguration.defaultConfiguration(ChampionTransformer.class),
        TransformerConfiguration.defaultConfiguration(LeagueTransformer.class),
        TransformerConfiguration.defaultConfiguration(MatchTransformer.class),
        TransformerConfiguration.defaultConfiguration(SpectatorTransformer.class),
        TransformerConfiguration.defaultConfiguration(StaticDataTransformer.class),
        TransformerConfiguration.defaultConfiguration(StatusTransformer.class),
        TransformerConfiguration.defaultConfiguration(SummonerTransformer.class),
        TransformerConfiguration.defaultConfiguration(ThirdPartyCodeTransformer.class)
    }));
    pipeline.setTransformers(transformers);

    config.setPipeline(pipeline);

Hopefully this illustrates how to add a new Pipeline Element or Transformer to Orianna's Data Pipeline. Please check out :ref:`the Data Pipeline page <data-pipeline>` for the details about what ``elements`` and ``transformers`` are available and when to use them.
