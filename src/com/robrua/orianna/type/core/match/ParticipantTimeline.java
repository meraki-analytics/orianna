package com.robrua.orianna.type.core.match;

import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.Lane;
import com.robrua.orianna.type.core.common.Role;

public class ParticipantTimeline extends OriannaObject<com.robrua.orianna.type.dto.match.ParticipantTimeline> {
    private static final long serialVersionUID = 5241943122498925721L;
    private ParticipantTimelineData ancientGolemAssistsPerMinCounts, ancientGolemKillsPerMinCounts, assistedLaneDeathsPerMinDeltas,
            assistedLaneKillsPerMinDeltas, baronAssistsPerMinCounts, baronKillsPerMinCounts, creepsPerMinDeltas, CSDiffPerMinDeltas,
            damageTakenDiffPerMinDeltas, damageTakenPerMinDeltas, dragonAssistsPerMinCounts, dragonKillsPerMinCounts, elderLizardAssistsPerMinCounts,
            elderLizardKillsPerMinCounts, goldPerMinDeltas, inhibitorAssistsPerMinCounts, inhibitorKillsPerMinCounts, towerAssistsPerMinCounts,
            towerKillsPerMinCounts, towerKillsPerMinDeltas, vilemawAssistsPerMinCounts, vilemawKillsPerMinCounts, wardsPerMinDeltas, XPDiffPerMinDeltas,
            XPPerMinDeltas;

    /**
     * @param data
     *            the underlying dto
     */
    public ParticipantTimeline(final com.robrua.orianna.type.dto.match.ParticipantTimeline data) {
        super(data, com.robrua.orianna.type.dto.match.ParticipantTimeline.class);
    }

    /**
     * Ancient golem assists per minute timeline counts
     *
     * @return ancient golem assists per minute timeline counts
     */
    public ParticipantTimelineData getAncientGolemAssistsPerMinCounts() {
        if(ancientGolemAssistsPerMinCounts == null) {
            ancientGolemAssistsPerMinCounts = new ParticipantTimelineData(data.getAncientGolemAssistsPerMinCounts());
        }

        return ancientGolemAssistsPerMinCounts;
    }

    /**
     * Ancient golem kills per minute timeline counts
     *
     * @return ancient golem kills per minute timeline counts
     */
    public ParticipantTimelineData getAncientGolemKillsPerMinCounts() {
        if(ancientGolemKillsPerMinCounts == null) {
            ancientGolemKillsPerMinCounts = new ParticipantTimelineData(data.getAncientGolemKillsPerMinCounts());
        }

        return ancientGolemKillsPerMinCounts;
    }

    /**
     * Assisted lane deaths per minute timeline data
     *
     * @return assisted lane deaths per minute timeline data
     */
    public ParticipantTimelineData getAssistedLaneDeathsPerMinDeltas() {
        if(assistedLaneDeathsPerMinDeltas == null) {
            assistedLaneDeathsPerMinDeltas = new ParticipantTimelineData(data.getAssistedLaneDeathsPerMinDeltas());
        }

        return assistedLaneDeathsPerMinDeltas;
    }

    /**
     * Assisted lane kills per minute timeline data
     *
     * @return assisted lane kills per minute timeline data
     */
    public ParticipantTimelineData getAssistedLaneKillsPerMinDeltas() {
        if(assistedLaneKillsPerMinDeltas == null) {
            assistedLaneKillsPerMinDeltas = new ParticipantTimelineData(data.getAssistedLaneKillsPerMinDeltas());
        }

        return assistedLaneKillsPerMinDeltas;
    }

    /**
     * Baron assists per minute timeline counts
     *
     * @return baron assists per minute timeline counts
     */
    public ParticipantTimelineData getBaronAssistsPerMinCounts() {
        if(baronAssistsPerMinCounts == null) {
            baronAssistsPerMinCounts = new ParticipantTimelineData(data.getBaronAssistsPerMinCounts());
        }

        return baronAssistsPerMinCounts;
    }

    /**
     * Baron kills per minute timeline counts
     *
     * @return baron kills per minute timeline counts
     */
    public ParticipantTimelineData getBaronKillsPerMinCounts() {
        if(baronKillsPerMinCounts == null) {
            baronKillsPerMinCounts = new ParticipantTimelineData(data.getBaronKillsPerMinCounts());
        }

        return baronKillsPerMinCounts;
    }

    /**
     * Creeps per minute timeline data
     *
     * @return creeps per minute timeline data
     */
    public ParticipantTimelineData getCreepsPerMinDeltas() {
        if(creepsPerMinDeltas == null) {
            creepsPerMinDeltas = new ParticipantTimelineData(data.getCreepsPerMinDeltas());
        }

        return creepsPerMinDeltas;
    }

    /**
     * Creep score difference per minute timeline data
     *
     * @return creep score difference per minute timeline data
     */
    public ParticipantTimelineData getCSDiffPerMinDeltas() {
        if(CSDiffPerMinDeltas == null) {
            CSDiffPerMinDeltas = new ParticipantTimelineData(data.getCsDiffPerMinDeltas());
        }

        return CSDiffPerMinDeltas;
    }

    /**
     * Damage taken difference per minute timeline data
     *
     * @return damage taken difference per minute timeline data
     */
    public ParticipantTimelineData getDamageTakenDiffPerMinDeltas() {
        if(damageTakenDiffPerMinDeltas == null) {
            damageTakenDiffPerMinDeltas = new ParticipantTimelineData(data.getDamageTakenDiffPerMinDeltas());
        }

        return damageTakenDiffPerMinDeltas;
    }

    /**
     * Damage taken per minute timeline data
     *
     * @return damage taken per minute timeline data
     */
    public ParticipantTimelineData getDamageTakenPerMinDeltas() {
        if(damageTakenPerMinDeltas == null) {
            damageTakenPerMinDeltas = new ParticipantTimelineData(data.getDamageTakenPerMinDeltas());
        }

        return damageTakenPerMinDeltas;
    }

    /**
     * Dragon assists per minute timeline counts
     *
     * @return dragon assists per minute timeline counts
     */
    public ParticipantTimelineData getDragonAssistsPerMinCounts() {
        if(dragonAssistsPerMinCounts == null) {
            dragonAssistsPerMinCounts = new ParticipantTimelineData(data.getDragonAssistsPerMinCounts());
        }

        return dragonAssistsPerMinCounts;
    }

    /**
     * Dragon kills per minute timeline counts
     *
     * @return dragon kills per minute timeline counts
     */
    public ParticipantTimelineData getDragonKillsPerMinCounts() {
        if(dragonKillsPerMinCounts == null) {
            dragonKillsPerMinCounts = new ParticipantTimelineData(data.getDragonKillsPerMinCounts());
        }

        return dragonKillsPerMinCounts;
    }

    /**
     * Elder lizard assists per minute timeline counts
     *
     * @return elder lizard assists per minute timeline counts
     */
    public ParticipantTimelineData getElderLizardAssistsPerMinCounts() {
        if(elderLizardAssistsPerMinCounts == null) {
            elderLizardAssistsPerMinCounts = new ParticipantTimelineData(data.getElderLizardAssistsPerMinCounts());
        }

        return elderLizardAssistsPerMinCounts;
    }

    /**
     * Elder lizard kills per minute timeline counts
     *
     * @return elder lizard kills per minute timeline counts
     */
    public ParticipantTimelineData getElderLizardKillsPerMinCounts() {
        if(elderLizardKillsPerMinCounts == null) {
            elderLizardKillsPerMinCounts = new ParticipantTimelineData(data.getElderLizardKillsPerMinCounts());
        }

        return elderLizardKillsPerMinCounts;
    }

    /**
     * Gold per minute timeline data
     *
     * @return gold per minute timeline data
     */
    public ParticipantTimelineData getGoldPerMinDeltas() {
        if(goldPerMinDeltas == null) {
            goldPerMinDeltas = new ParticipantTimelineData(data.getGoldPerMinDeltas());
        }

        return goldPerMinDeltas;
    }

    /**
     * Inhibitor assists per minute timeline counts
     *
     * @return inhibitor assists per minute timeline counts
     */
    public ParticipantTimelineData getInhibitorAssistsPerMinCounts() {
        if(inhibitorAssistsPerMinCounts == null) {
            inhibitorAssistsPerMinCounts = new ParticipantTimelineData(data.getInhibitorAssistsPerMinCounts());
        }

        return inhibitorAssistsPerMinCounts;
    }

    /**
     * Inhibitor kills per minute timeline counts
     *
     * @return inhibitor kills per minute timeline counts
     */
    public ParticipantTimelineData getInhibitorKillsPerMinCounts() {
        if(inhibitorKillsPerMinCounts == null) {
            inhibitorKillsPerMinCounts = new ParticipantTimelineData(data.getInhibitorKillsPerMinCounts());
        }

        return inhibitorKillsPerMinCounts;
    }

    /**
     * Participant's lane
     *
     * @return participant's lane
     */
    public Lane getLane() {
        return Lane.valueOf(super.getString(data.getLane()));
    }

    /**
     * Participant's role
     *
     * @return participant's role
     */
    public Role getRole() {
        return Role.valueOf(super.getString(data.getRole()));
    }

    /**
     * Tower assists per minute timeline counts
     *
     * @return tower assists per minute timeline counts
     */
    public ParticipantTimelineData getTowerAssistsPerMinCounts() {
        if(towerAssistsPerMinCounts == null) {
            towerAssistsPerMinCounts = new ParticipantTimelineData(data.getTowerAssistsPerMinCounts());
        }

        return towerAssistsPerMinCounts;
    }

    /**
     * Tower kills per minute timeline counts
     *
     * @return tower kills per minute timeline counts
     */
    public ParticipantTimelineData getTowerKillsPerMinCounts() {
        if(towerKillsPerMinCounts == null) {
            towerKillsPerMinCounts = new ParticipantTimelineData(data.getTowerKillsPerMinCounts());
        }

        return towerKillsPerMinCounts;
    }

    /**
     * Tower kills per minute timeline data
     *
     * @return tower kills per minute timeline data
     */
    public ParticipantTimelineData getTowerKillsPerMinDeltas() {
        if(towerKillsPerMinDeltas == null) {
            towerKillsPerMinDeltas = new ParticipantTimelineData(data.getTowerKillsPerMinDeltas());
        }

        return towerKillsPerMinDeltas;
    }

    /**
     * Vilemaw assists per minute timeline counts
     *
     * @return vilemaw assists per minute timeline counts
     */
    public ParticipantTimelineData getVilemawAssistsPerMinCounts() {
        if(vilemawAssistsPerMinCounts == null) {
            vilemawAssistsPerMinCounts = new ParticipantTimelineData(data.getVilemawAssistsPerMinCounts());
        }

        return vilemawAssistsPerMinCounts;
    }

    /**
     * Vilemaw kills per minute timeline counts
     *
     * @return vilemaw kills per minute timeline counts
     */
    public ParticipantTimelineData getVilemawKillsPerMinCounts() {
        if(vilemawKillsPerMinCounts == null) {
            vilemawKillsPerMinCounts = new ParticipantTimelineData(data.getVilemawKillsPerMinCounts());
        }

        return vilemawKillsPerMinCounts;
    }

    /**
     * Wards placed per minute timeline data
     *
     * @return wards placed per minute timeline data
     */
    public ParticipantTimelineData getWardsPerMinDeltas() {
        if(wardsPerMinDeltas == null) {
            wardsPerMinDeltas = new ParticipantTimelineData(data.getWardsPerMinDeltas());
        }

        return wardsPerMinDeltas;
    }

    /**
     * Experience difference per minute timeline data
     *
     * @return experience difference per minute timeline data
     */
    public ParticipantTimelineData getXPDiffPerMinDeltas() {
        if(XPDiffPerMinDeltas == null) {
            XPDiffPerMinDeltas = new ParticipantTimelineData(data.getXpDiffPerMinDeltas());
        }

        return XPDiffPerMinDeltas;
    }

    /**
     * Experience per minute timeline data
     *
     * @return experience per minute timeline data
     */
    public ParticipantTimelineData getXPPerMinDeltas() {
        if(XPPerMinDeltas == null) {
            XPPerMinDeltas = new ParticipantTimelineData(data.getXpPerMinDeltas());
        }

        return XPPerMinDeltas;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantTimeline";
    }

}
