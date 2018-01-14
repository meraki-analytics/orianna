package com.merakianalytics.orianna.types.core.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class Incident extends OriannaObject<com.merakianalytics.orianna.types.data.status.Incident> {
    private static final long serialVersionUID = 7833049943482732990L;

    private final Supplier<List<Message>> updates = Suppliers.memoize(new Supplier<List<Message>>() {
        @Override
        public List<Message> get() {
            final List<Message> updates = new ArrayList<>(coreData.getUpdates().size());
            for(final com.merakianalytics.orianna.types.data.status.Message update : coreData.getUpdates()) {
                updates.add(new Message(update));
            }
            return Collections.unmodifiableList(updates);
        }
    });

    public Incident(final com.merakianalytics.orianna.types.data.status.Incident coreData) {
        super(coreData);
    }

    public DateTime getCreated() {
        return coreData.getCreated();
    }

    public long getId() {
        return coreData.getId();
    }

    public List<Message> getUpdates() {
        return updates.get();
    }

    public boolean isActive() {
        return coreData.isActive();
    }
}
