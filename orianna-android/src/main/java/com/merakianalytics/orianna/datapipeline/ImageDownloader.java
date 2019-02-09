package com.merakianalytics.orianna.datapipeline;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.AbstractDataSource;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient;
import com.merakianalytics.orianna.datapipeline.common.HTTPClient.Response;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.types.common.OriannaException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageDownloader extends AbstractDataSource {
    private final HTTPClient client;

    public ImageDownloader() {
        client = new HTTPClient();
    }

    @Get(Bitmap.class)
    public Bitmap getBitmap(final Map<String, Object> query, final PipelineContext context) {
        final String url = (String)query.get("url");
        Utilities.checkNotNull(url, "url");

        try {
            final Response response = client.get(url);
            byte[] bytes = response.getBytes();
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch(final IOException e) {
            throw new OriannaException("Failed to download image from " + url, e);
        }
    }

    @SuppressWarnings("unchecked")
    @GetMany(Bitmap.class)
    public CloseableIterator<Bitmap> getManyBitmap(final Map<String, Object> query, final PipelineContext context) {
        final Iterable<String> urls = (Iterable<String>)query.get("urls");
        Utilities.checkNotNull(urls, "urls");

        final Iterator<String> iterator = urls.iterator();
        return CloseableIterators.from(new Iterator<Bitmap>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Bitmap next() {
                final String url = iterator.next();

                try {
                    final Response response = client.get(url);
                    byte[] bytes = response.getBytes();
                    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
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
