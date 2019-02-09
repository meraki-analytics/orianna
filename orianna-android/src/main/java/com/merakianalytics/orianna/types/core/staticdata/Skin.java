package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.collect.ImmutableMap;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

import android.graphics.Bitmap;

public class Skin extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.Skin> {
    private static final long serialVersionUID = 3312924952125014216L;

    public Skin(final com.merakianalytics.orianna.types.data.staticdata.Skin coreData) {
        super(coreData);
    }

    @Searchable(int.class)
    public int getId() {
        return coreData.getId();
    }

    public Bitmap getLoadingImage() {
        final Bitmap image = Orianna.getSettings().getPipeline().get(Bitmap.class, ImmutableMap.<String, Object> of("url", getLoadingImageURL()));
        return image;
    }

    public String getLoadingImageURL() {
        return "http://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + coreData.getChampionKey() + "_" + coreData.getNumber() + ".jpg";
    }

    @Searchable(String.class)
    public String getName() {
        return coreData.getName();
    }

    public int getNumber() {
        return coreData.getNumber();
    }

    public Bitmap getSpashImage() {
        final Bitmap image = Orianna.getSettings().getPipeline().get(Bitmap.class, ImmutableMap.<String, Object> of("url", getSplashImageURL()));
        return image;
    }

    public String getSplashImageURL() {
        return "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + coreData.getChampionKey() + "_" + coreData.getNumber() + ".jpg";
    }
}
