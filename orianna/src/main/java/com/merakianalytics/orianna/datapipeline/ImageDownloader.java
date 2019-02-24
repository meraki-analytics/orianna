package com.merakianalytics.orianna.datapipeline;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.AbstractDataSource;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient.Configuration;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient.Response;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.types.common.OriannaException;

public class ImageDownloader extends AbstractDataSource {
    private final HTTPClient client;

    public ImageDownloader() {
        final Configuration config = new Configuration();
        config.setHttps(false); // TODO: Make this configurable
        client = new HTTPClient(config);
    }

    @Get(BufferedImage.class)
    public BufferedImage getBufferedImage(final Map<String, Object> query, final PipelineContext context) {
        final String url = (String)query.get("url");
        Utilities.checkNotNull(url, "url");

        try {
            final Response response = client.get(url);
            return ImageIO.read(new ByteArrayInputStream(response.getBytes()));
        } catch(final IOException e) {
            throw new OriannaException("Failed to download image from " + url, e);
        }
    }

    @SuppressWarnings("unchecked")
    @GetMany(BufferedImage.class)
    public CloseableIterator<BufferedImage> getManyBufferedImage(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<String> urls = (Iterable<String>)query.get("urls");
        Utilities.checkNotNull(urls, "urls");

        final Iterator<String> iterator = urls.iterator();
        return CloseableIterators.from(new Iterator<BufferedImage>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public BufferedImage next() {
                final String url = iterator.next();

                try {
                    final Response response = client.get(url);
                    return ImageIO.read(new ByteArrayInputStream(response.getBytes()));
                } catch(final IOException e) {
                    throw new OriannaException("Failed to download image from " + url, e);
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }
}
