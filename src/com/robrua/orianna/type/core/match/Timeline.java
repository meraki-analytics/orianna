package com.robrua.orianna.type.core.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.type.core.OriannaObject;

public class Timeline extends OriannaObject<com.robrua.orianna.type.dto.match.Timeline> {
    private static final long serialVersionUID = -4454826154950239510L;
    private List<Frame> frames;
    private final Map<Integer, Participant> participants;

    /**
     * @param data
     *            the underlying dto
     * @param participants
     *            the participants
     */
    public Timeline(final com.robrua.orianna.type.dto.match.Timeline data, final Map<Integer, Participant> participants) {
        super(data, com.robrua.orianna.type.dto.match.Timeline.class);
        this.participants = participants;
    }

    /**
     * Time between each returned frame in milliseconds
     *
     * @return time between each returned frame in milliseconds
     */
    public long getFrameInterval() {
        return super.getLong(data.getFrameInterval());
    }

    /**
     * List of timeline frames for the game
     *
     * @return list of timeline frames for the game.
     */
    public List<Frame> getFrames() {
        if(frames == null) {
            frames = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.match.Frame frame : data.getFrames()) {
                frames.add(new Frame(frame, participants));
            }
        }

        return Collections.unmodifiableList(frames);
    }

    /**
     * @param participant
     *            the participant to get frames for
     * @return that participant's frames
     */
    public List<ParticipantFrame> getFrames(final Participant participant) {
        final List<Frame> frames = getFrames();
        final List<ParticipantFrame> parts = new ArrayList<>(frames.size());
        for(final Frame frame : frames) {
            parts.add(frame.getFrameForParticipant(participant));
        }

        return parts;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Timeline";
    }
}
