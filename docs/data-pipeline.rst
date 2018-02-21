.. _data-pipeline:

*************
Data Pipeline
*************

The Data Pipeline is the core of Orianna's functionality. It's a configurable collection of Data Sources, Databases, and Caches that Orianna gets its API data from. The Data Pipeline automatically figures out where the best place to get the data from is and makes sure that data get stored in the configured Caches and Databases.

The Data Pipeline is organized as an ordered list of PipelineElements. Each PipelineElement is a DataSource (provides data), a DataSink (stores data), or a DataStore (both a DataSource *and* DataSink at once).

When an Orianna user asks for data from Orianna, Orianna sends a query to the Data Pipline for the request data type:

1) The Data Pipeline checks which DataSources can supploy that data type.
2) It queries each DataSource in order until one returns data for the query.
3) It stores the data in all of the DataSinks that came *before* the DataSource that returned the data in the Data Pipeline.
4) It returns the data to Orianna, which returns it to the user.

The default Orianna Data Pipeline looks like this:

``InMemoryCache -> GhostObjectSource -> DataDragon -> RiotAPI -> ImageDataSource``

Because Data Dragon only supplies static-data, this means that for static-data requests the Riot API will only be queried if Data Dragon fails to return the data, but other API data requests will be routed directly to the Riot API.
For more details about what each of these PipelineElements is for, and what others are available in Orianna, check out the pages below.

For more information about how to configure Orianna's Data Pipeline to fit your needs, see :ref:`the configuration documentation<configuring-orianna>`.

.. toctree::
    :maxdepth: 1

    data-pipeline/riot-api
    data-pipeline/data-dragon
    data-pipeline/image-data-source
    data-pipeline/ghost-object-source
    data-pipeline/in-memory-cache
    data-pipeline/xodus
    data-pipeline/mongo
