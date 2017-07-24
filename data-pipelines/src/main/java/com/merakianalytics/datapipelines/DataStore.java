package com.merakianalytics.datapipelines;

import com.merakianalytics.datapipelines.sinks.DataSink;
import com.merakianalytics.datapipelines.sources.DataSource;

public interface DataStore extends DataSource, DataSink {}
