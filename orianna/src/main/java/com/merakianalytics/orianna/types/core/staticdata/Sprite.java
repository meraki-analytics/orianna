package com.merakianalytics.orianna.types.core.staticdata;

import java.awt.image.BufferedImage;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class Sprite extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.Sprite> {
    private static final long serialVersionUID = -8084940963099982290L;

    private final Supplier<BufferedImage> image = Suppliers.memoize(new Supplier<BufferedImage>() {
        @Override
        public BufferedImage get() {
            if(coreData.getVersion() == null || coreData.getFull() == null) {
                return null;
            }
            final BufferedImage image = Orianna.getSettings().getPipeline().get(BufferedImage.class, ImmutableMap.<String, Object> of("url", getURL()));
            return image.getSubimage(coreData.getX(), coreData.getY(), coreData.getWidth(), coreData.getHeight());
        }
    });

    public Sprite(final com.merakianalytics.orianna.types.data.staticdata.Sprite coreData) {
        super(coreData);
    }

    public BufferedImage get() {
        return image.get();
    }

    public String getFull() {
        return coreData.getFull();
    }

    public int getHeight() {
        return coreData.getHeight();
    }

    public String getURL() {
        return "http://ddragon.leagueoflegends.com/cdn/" + coreData.getVersion() + "/img/sprite/" + coreData.getFull();
    }

    public int getWidth() {
        return coreData.getWidth();
    }

    public int getX() {
        return coreData.getX();
    }

    public int getY() {
        return coreData.getY();
    }
}
