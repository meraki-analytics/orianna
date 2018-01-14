package com.merakianalytics.orianna.types.core.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.merakianalytics.orianna.types.core.OriannaObject;

public class Service extends OriannaObject<com.merakianalytics.orianna.types.data.status.Service> {
    private static final long serialVersionUID = 7027647640617848231L;

    private final Supplier<List<Incident>> incidents = Suppliers.memoize(new Supplier<List<Incident>>() {
        @Override
        public List<Incident> get() {
            final List<Incident> incidents = new ArrayList<>(coreData.getIncidents().size());
            for(final com.merakianalytics.orianna.types.data.status.Incident incident : coreData.getIncidents()) {
                incidents.add(new Incident(incident));
            }
            return Collections.unmodifiableList(incidents);
        }
    });

    public Service(final com.merakianalytics.orianna.types.data.status.Service coreData) {
        super(coreData);
    }

    public List<Incident> getIncidents() {
        return incidents.get();
    }

    public String getName() {
        return coreData.getName();
    }

    public String getStatus() {
        return coreData.getStatus();
    }
}
