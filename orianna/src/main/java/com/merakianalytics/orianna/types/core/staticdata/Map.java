package com.merakianalytics.orianna.types.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.searchable.SearchableListWrapper;

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
            final List<Item> items = new ArrayList<>(coreData.getUnpurchasableItems().size());
            for(final Integer id : coreData.getUnpurchasableItems()) {
                items.add(Item.withId(id).withPlatform(coreData.getPlatform()).withVersion(coreData.getVersion()).withLocale(coreData.getLocale()).get());
            }
            return SearchableListWrapper.of(Collections.unmodifiableList(items));
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

    @Searchable(String.class)
    public String getName() {
        return coreData.getName();
    }

    public Platform getPlatform() {
        return coreData.getPlatform();
    }

    public Region getRegion() {
        return coreData.getPlatform().getRegion();
    }

    public SearchableList<Item> getUnpurchaseableItems() {
        return unpurchasableItems.get();
    }

    public String getVersion() {
        return coreData.getVersion();
    }
}
