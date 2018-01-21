package com.merakianalytics.orianna.types.core.staticdata;

import java.awt.image.BufferedImage;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class Image extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.Image> {
    private static final long serialVersionUID = -6752564052626945287L;

    private final Supplier<BufferedImage> image = Suppliers.memoize(new Supplier<BufferedImage>() {
        @Override
        public BufferedImage get() {
            if(coreData.getVersion() == null || coreData.getGroup() == null || coreData.getFull() == null) {
                return null;
            }
            return Orianna.getSettings().getPipeline().get(BufferedImage.class, ImmutableMap.<String, Object> of("url", getURL()));
        }
    });

    private final Supplier<Sprite> sprite = Suppliers.memoize(new Supplier<Sprite>() {
        @Override
        public Sprite get() {
            if(coreData.getSprite() == null) {
                return null;
            }
            return new Sprite(coreData.getSprite());
        }
    });

    public Image(final com.merakianalytics.orianna.types.data.staticdata.Image coreData) {
        super(coreData);
    }

    public BufferedImage get() {
        return image.get();
    }

    public String getFull() {
        return coreData.getFull();
    }

    public String getGroup() {
        return coreData.getGroup();
    }

    public Sprite getSprite() {
        return sprite.get();
    }

    public String getURL() {
        return "http://ddragon.leagueoflegends.com/cdn/" + coreData.getVersion() + "/img/" + coreData.getGroup() + "/" + coreData.getFull();
    }
}
