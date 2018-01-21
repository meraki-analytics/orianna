package com.merakianalytics.orianna.types.core.staticdata;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;
import com.merakianalytics.orianna.types.core.searchable.Searchable;

public class Passive extends OriannaObject<com.merakianalytics.orianna.types.data.staticdata.Passive> {
    private static final long serialVersionUID = -5691212908005996446L;
    private final Supplier<Image> image = Suppliers.memoize(new Supplier<Image>() {
        @Override
        public Image get() {
            if(coreData.getImage() == null) {
                return null;
            }
            return new Image(coreData.getImage());
        }
    });

    public Passive(final com.merakianalytics.orianna.types.data.staticdata.Passive coreData) {
        super(coreData);
    }

    public String description() {
        return coreData.getDescription();
    }

    public Image getImage() {
        return image.get();
    }

    @Searchable(String.class)
    public String getName() {
        return coreData.getName();
    }

    public String getSanitizedDescription() {
        return coreData.getSanitizedDescription();
    }
}
