package com.merakianalytics.orianna.datapipeline;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.*;
import com.merakianalytics.datapipelines.sources.*;
import com.merakianalytics.orianna.datapipeline.common.*;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient.Response;
import com.merakianalytics.orianna.types.common.OriannaException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JSONDataSource extends AbstractDataSource
{
    private final HTTPClient client;
    
    public JSONDataSource()
    {
        client = new HTTPClient();
    }
    
    @Get(String.class)
    public String getString(final Map<String, Object> query, final PipelineContext context)
    {
        final String url = (String) query.get("url");
        Utilities.checkNotNull(url, "url");
        
        try
        {
            final Response response = client.get(url);
            if (response.getBody() == null)
            {
                return new String(response.getBytes(), StandardCharsets.UTF_8);
            }
            
            return response.getBody();
            
        } catch (final IOException e)
        {
            throw new OriannaException("Failed to download file from " + url, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(String.class)
    public CloseableIterator<String> getManyString(final Map<String, Object> query, final PipelineContext context)
    {
        final Iterable<String> urls = (Iterable<String>) query.get("urls");
        Utilities.checkNotNull(urls, "urls");
        
        final Iterator<String> iterator = urls.iterator();
        return CloseableIterators.from(new Iterator<String>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public String next()
            {
                final String url = iterator.next();
                
                return getString(ImmutableMap.<String, Object>of("url", url), context);
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
}
