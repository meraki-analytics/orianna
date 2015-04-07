package com.robrua.orianna.type.core.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.type.core.OriannaObject;

public class Frame extends OriannaObject<com.robrua.orianna.type.dto.match.Frame> {
    private static final long serialVersionUID = 8025192341833725847L;
    private List<Event> events;
    private Map<Integer, ParticipantFrame> participantFrames;
    private final Map<Integer, Participant> participants;

    /**
     * @param data
     *            the underlying dto
     * @param participants
     *            the participants
     */
    public Frame(final com.robrua.orianna.type.dto.match.Frame data, final Map<Integer, Participant> participants) {
        super(data, com.robrua.orianna.type.dto.match.Frame.class);
        this.participants = participants;
    }

    /**
     * List of events for this frame
     *
     * @return list of events for this frame
     */
    public List<Event> getEvents() {
        if(events == null) {
            events = new ArrayList<>();
            if(data.getEvents() != null) {
                for(final com.robrua.orianna.type.dto.match.Event event : data.getEvents()) {
                    events.add(new Event(event, participants));
                }
            }
        }

        return Collections.unmodifiableList(events);
    }

    /**
     * The participant's information for the frame
     *
     * @param participant
     *            the participant
     * @return the participant's information for the frame
     */
    public ParticipantFrame getFrameForParticipant(final Participant participant) {
        if(participantFrames == null) {
            participantFrames = new HashMap<>();
            for(final String ID : data.getParticipantFrames().keySet()) {
                participantFrames.put(new Integer(ID), new ParticipantFrame(data.getParticipantFrames().get(ID)));
            }
        }

        return participantFrames.get(participant.getParticipantID());
    }

    /**
     * Map of each participant ID to the participant's information for the frame
     *
     * @return map of each participant ID to the participant's information for
     *         the frame
     */
    public Map<Integer, ParticipantFrame> getParticipantFrames() {
        if(participantFrames == null) {
            participantFrames = new HashMap<>();
            for(final String ID : data.getParticipantFrames().keySet()) {
                participantFrames.put(new Integer(ID), new ParticipantFrame(data.getParticipantFrames().get(ID)));
            }
        }

        return Collections.unmodifiableMap(participantFrames);
    }

    /**
     * Represents how many milliseconds into the game the frame occurred
     *
     * @return how many milliseconds into the game the frame occurred
     */
    public long getTimestamp() {
        return super.getLong(data.getTimestamp());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Frame (" + getTimestamp() + "ms)";
    }
}
