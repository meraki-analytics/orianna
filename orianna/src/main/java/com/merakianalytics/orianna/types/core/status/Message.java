package com.merakianalytics.orianna.types.core.status;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class Message extends OriannaObject<com.merakianalytics.orianna.types.data.status.Message> {
    private static final long serialVersionUID = -5235637338340977481L;

    private final Supplier<Map<String, Translation>> translations = Suppliers.memoize(new Supplier<Map<String, Translation>>() {
        @Override
        public Map<String, Translation> get() {
            final Map<String, Translation> translations = new HashMap<>(coreData.getTranslations().size());
            for(final String locale : coreData.getTranslations().keySet()) {
                translations.put(locale, new Translation(coreData.getTranslations().get(locale)));
            }
            return Collections.unmodifiableMap(translations);
        }
    });

    public Message(final com.merakianalytics.orianna.types.data.status.Message coreData) {
        super(coreData);
    }

    public String getAuthor() {
        return coreData.getAuthor();
    }

    public String getContent() {
        return coreData.getContent();
    }

    public DateTime getCreated() {
        return coreData.getCreated();
    }

    public String getId() {
        return coreData.getId();
    }

    public String getSeverity() {
        return coreData.getSeverity();
    }

    public Map<String, Translation> getTranslations() {
        return translations.get();
    }

    public DateTime getUpdated() {
        return coreData.getUpdated();
    }
}
