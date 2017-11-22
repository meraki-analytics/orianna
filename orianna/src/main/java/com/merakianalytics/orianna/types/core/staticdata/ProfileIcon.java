package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class ProfileIcon extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.ProfileIcon> {
    private static final long serialVersionUID = 4915081654310775218L;

    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            return new Image(coreData.getImage());
        }
    });

    public ProfileIcon(final com.merakianalytics.orianna.types.data.staticdata.ProfileIcon coreData) {
        super(coreData);
    }

    @Searchable(int.class)
    public int getId() {
        return coreData.getId();
    }

    public Image getImage() {
        return image.get();
    }

    public String getLocale() {
        return coreData.getLocale();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public String getVersion() {
        return coreData.getVersion();
    }
}
