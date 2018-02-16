.. _configuring-orianna:

*******************
Configuring Orianna
*******************

Orianna is highly configurable to meet the needs of your application. Configuration can be done using either a JSON-based configuration file, or the programmatic configuration API.

Orianna ships with a default JSON-based configuration file which is designed to get users up & running as quickly and easily as possible, but you may find while developing your application that you'd prefer different behavior from Orianna's defaults.

This page is a guide to what configuration options are available in Orianna, and how to use them through both the JSON-based configuration file and the programmatic configuration API.

Before we dive into the two different configuration approaches, let's touch on one aspect of configuration that's shared between them:

Setting Your ``RIOT_API_KEY`` Environment Variable
==================================================

In most applications using Orianna (though not necessarily all of them), you'll be making requests to the Riot API, and will therefore need to use your API Key.

You can set your API key using either of the configuration approaches, but that makes it easy to forget to remove it before committing code to a shared repository, and that's no bueno.
As an alternative, you can leave the API Key out of your Orianna configuration and set your ``RIOT_API_KEY`` environment variable to it instead.
The RiotAPI DataSource will automatically check this environment variable when it loads to get your API Key without risking committing it to a shared repository.

Choose Your Configuration Method
================================

Next, choose which approach you'd like to use to configure Orianna. By using the JSON-based configuration file, you can change your configuration without changing your application code. By using the programmatic configuration API, you can change options easily during development:

.. toctree::
    :maxdepth: 2

    configuring-orianna-json
    configuring-orianna-programmatic
