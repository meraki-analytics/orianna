package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.UnmodifiableSearchableList;

public class Map extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.Map> {
    private static final long serialVersionUID = -2780461893645152056L;

    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            return new Image(coreData.getImage());
        }
    });

    private final Supplier<SearchableList<Item>> unpurchasableItems = Suppliers.memoize(new Supplier<SearchableList<Item>>() {
        @Override
        public SearchableList<Item> get() {
            return UnmodifiableSearchableList.of(Items.withIds(coreData.getUnpurchasableItems()).withPlatform(Platform.withTag(coreData.getPlatform()))
                .withVersion(coreData.getVersion()).withLocale(coreData.getLocale()).get());
        }
    });

    public Map(final com.merakianalytics.orianna.types.data.staticdata.Map coreData) {
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

    public com.merakianalytics.orianna.types.common.Map getMap() {
        return com.merakianalytics.orianna.types.common.Map.withId(coreData.getId());
    }

    @Searchable(String.class)
    public String getName() {
        return coreData.getName();
    }

    public Platform getPlatform() {
        return Platform.withTag(coreData.getPlatform());
    }

    public Region getRegion() {
        return Platform.withTag(coreData.getPlatform()).getRegion();
    }

    public SearchableList<Item> getUnpurchaseableItems() {
        return unpurchasableItems.get();
    }

    public String getVersion() {
        return coreData.getVersion();
    }
}
