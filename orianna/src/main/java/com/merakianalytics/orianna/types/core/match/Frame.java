package com.merakianalytics.orianna.types.core.match;

import java.util.List;
import java.util.Map;

import org.joda.time.Duration;

import com.google.common.base.Function;
import com.merakianalytics.orianna.types.core.OriannaObject;

public abstract class Frame extends OriannaObject.ListProxy<Event, com.merakianalytics.orianna.types.data.match.Event, com.merakianalytics.orianna.types.data.match.Frame> {
    private static final long serialVersionUID = 460862393234334223L;

    public Frame(final com.merakianalytics.orianna.types.data.match.Frame coreData) {
        super(coreData);
    }

    public Frame(final com.merakianalytics.orianna.types.data.match.Frame coreData,
        final Function<com.merakianalytics.orianna.types.data.match.Event, Event> transform) {
        super(coreData, transform);
    }

    public Frame(final com.merakianalytics.orianna.types.data.match.Frame coreData, final List<Event> data) {
        super(coreData, data);
    }

    public abstract Map<Participant, ParticipantFrame> getParticipantFrames();

    public abstract Duration getTimestamp();
}
