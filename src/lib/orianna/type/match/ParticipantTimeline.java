package lib.orianna.type.match;

import java.io.Serializable;

public class ParticipantTimeline implements Serializable {
    private static final long serialVersionUID = -8759068502582128430L;
    public final ParticipantTimelineData ancientGolemAssistsPerMinCounts, ancientGolemKillsPerMinCounts, assistedLaneDeathsPerMinDeltas,
            assistedLaneKillsPerMinDeltas, baronAssistsPerMinCounts, baronKillsPerMinCounts, creepsPerMinDeltas, csDiffPerMinDeltas,
            damageTakenDiffPerMinDeltas, damageTakenPerMinDeltas, dragonAssistsPerMinCounts, dragonKillsPerMinCounts, elderLizardAssistsPerMinCounts,
            elderLizardKillsPerMinCounts, goldPerMinDeltas, inhibitorAssistsPerMinCounts, inhibitorKillsPerMinCounts, towerAssistsPerMinCounts,
            towerKillsPerMinCounts, towerKillsPerMinDeltas, vilemawAssistsPerMinCounts, vilemawKillsPerMinCounts, wardsPerMinDeltas, XPDiffPerMinDeltas,
            XPPerMinDeltas;
    public final Lane lane;
    public final Role role;

    public ParticipantTimeline(final ParticipantTimelineData ancientGolemAssistsPerMinCounts, final ParticipantTimelineData ancientGolemKillsPerMinCounts,
            final ParticipantTimelineData assistedLaneDeathsPerMinDeltas, final ParticipantTimelineData assistedLaneKillsPerMinDeltas,
            final ParticipantTimelineData baronAssistsPerMinCounts, final ParticipantTimelineData baronKillsPerMinCounts,
            final ParticipantTimelineData creepsPerMinDeltas, final ParticipantTimelineData csDiffPerMinDeltas,
            final ParticipantTimelineData damageTakenDiffPerMinDeltas, final ParticipantTimelineData damageTakenPerMinDeltas,
            final ParticipantTimelineData dragonAssistsPerMinCounts, final ParticipantTimelineData dragonKillsPerMinCounts,
            final ParticipantTimelineData elderLizardAssistsPerMinCounts, final ParticipantTimelineData elderLizardKillsPerMinCounts,
            final ParticipantTimelineData goldPerMinDeltas, final ParticipantTimelineData inhibitorAssistsPerMinCounts,
            final ParticipantTimelineData inhibitorKillsPerMinCounts, final ParticipantTimelineData towerAssistsPerMinCounts,
            final ParticipantTimelineData towerKillsPerMinCounts, final ParticipantTimelineData towerKillsPerMinDeltas,
            final ParticipantTimelineData vilemawAssistsPerMinCounts, final ParticipantTimelineData vilemawKillsPerMinCounts,
            final ParticipantTimelineData wardsPerMinDeltas, final ParticipantTimelineData XPDiffPerMinDeltas, final ParticipantTimelineData XPPerMinDeltas,
            final Lane lane, final Role role) {
        this.ancientGolemAssistsPerMinCounts = ancientGolemAssistsPerMinCounts;
        this.ancientGolemKillsPerMinCounts = ancientGolemKillsPerMinCounts;
        this.assistedLaneDeathsPerMinDeltas = assistedLaneDeathsPerMinDeltas;
        this.assistedLaneKillsPerMinDeltas = assistedLaneKillsPerMinDeltas;
        this.baronAssistsPerMinCounts = baronAssistsPerMinCounts;
        this.baronKillsPerMinCounts = baronKillsPerMinCounts;
        this.creepsPerMinDeltas = creepsPerMinDeltas;
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
        this.dragonAssistsPerMinCounts = dragonAssistsPerMinCounts;
        this.dragonKillsPerMinCounts = dragonKillsPerMinCounts;
        this.elderLizardAssistsPerMinCounts = elderLizardAssistsPerMinCounts;
        this.elderLizardKillsPerMinCounts = elderLizardKillsPerMinCounts;
        this.goldPerMinDeltas = goldPerMinDeltas;
        this.inhibitorAssistsPerMinCounts = inhibitorAssistsPerMinCounts;
        this.inhibitorKillsPerMinCounts = inhibitorKillsPerMinCounts;
        this.towerAssistsPerMinCounts = towerAssistsPerMinCounts;
        this.towerKillsPerMinCounts = towerKillsPerMinCounts;
        this.towerKillsPerMinDeltas = towerKillsPerMinDeltas;
        this.vilemawAssistsPerMinCounts = vilemawAssistsPerMinCounts;
        this.vilemawKillsPerMinCounts = vilemawKillsPerMinCounts;
        this.wardsPerMinDeltas = wardsPerMinDeltas;
        this.XPDiffPerMinDeltas = XPDiffPerMinDeltas;
        this.XPPerMinDeltas = XPPerMinDeltas;
        this.lane = lane;
        this.role = role;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof ParticipantTimeline)) {
            return false;
        }
        final ParticipantTimeline other = (ParticipantTimeline)obj;
        if(XPDiffPerMinDeltas == null) {
            if(other.XPDiffPerMinDeltas != null) {
                return false;
            }
        }
        else if(!XPDiffPerMinDeltas.equals(other.XPDiffPerMinDeltas)) {
            return false;
        }
        if(XPPerMinDeltas == null) {
            if(other.XPPerMinDeltas != null) {
                return false;
            }
        }
        else if(!XPPerMinDeltas.equals(other.XPPerMinDeltas)) {
            return false;
        }
        if(ancientGolemAssistsPerMinCounts == null) {
            if(other.ancientGolemAssistsPerMinCounts != null) {
                return false;
            }
        }
        else if(!ancientGolemAssistsPerMinCounts.equals(other.ancientGolemAssistsPerMinCounts)) {
            return false;
        }
        if(ancientGolemKillsPerMinCounts == null) {
            if(other.ancientGolemKillsPerMinCounts != null) {
                return false;
            }
        }
        else if(!ancientGolemKillsPerMinCounts.equals(other.ancientGolemKillsPerMinCounts)) {
            return false;
        }
        if(assistedLaneDeathsPerMinDeltas == null) {
            if(other.assistedLaneDeathsPerMinDeltas != null) {
                return false;
            }
        }
        else if(!assistedLaneDeathsPerMinDeltas.equals(other.assistedLaneDeathsPerMinDeltas)) {
            return false;
        }
        if(assistedLaneKillsPerMinDeltas == null) {
            if(other.assistedLaneKillsPerMinDeltas != null) {
                return false;
            }
        }
        else if(!assistedLaneKillsPerMinDeltas.equals(other.assistedLaneKillsPerMinDeltas)) {
            return false;
        }
        if(baronAssistsPerMinCounts == null) {
            if(other.baronAssistsPerMinCounts != null) {
                return false;
            }
        }
        else if(!baronAssistsPerMinCounts.equals(other.baronAssistsPerMinCounts)) {
            return false;
        }
        if(baronKillsPerMinCounts == null) {
            if(other.baronKillsPerMinCounts != null) {
                return false;
            }
        }
        else if(!baronKillsPerMinCounts.equals(other.baronKillsPerMinCounts)) {
            return false;
        }
        if(creepsPerMinDeltas == null) {
            if(other.creepsPerMinDeltas != null) {
                return false;
            }
        }
        else if(!creepsPerMinDeltas.equals(other.creepsPerMinDeltas)) {
            return false;
        }
        if(csDiffPerMinDeltas == null) {
            if(other.csDiffPerMinDeltas != null) {
                return false;
            }
        }
        else if(!csDiffPerMinDeltas.equals(other.csDiffPerMinDeltas)) {
            return false;
        }
        if(damageTakenDiffPerMinDeltas == null) {
            if(other.damageTakenDiffPerMinDeltas != null) {
                return false;
            }
        }
        else if(!damageTakenDiffPerMinDeltas.equals(other.damageTakenDiffPerMinDeltas)) {
            return false;
        }
        if(damageTakenPerMinDeltas == null) {
            if(other.damageTakenPerMinDeltas != null) {
                return false;
            }
        }
        else if(!damageTakenPerMinDeltas.equals(other.damageTakenPerMinDeltas)) {
            return false;
        }
        if(dragonAssistsPerMinCounts == null) {
            if(other.dragonAssistsPerMinCounts != null) {
                return false;
            }
        }
        else if(!dragonAssistsPerMinCounts.equals(other.dragonAssistsPerMinCounts)) {
            return false;
        }
        if(dragonKillsPerMinCounts == null) {
            if(other.dragonKillsPerMinCounts != null) {
                return false;
            }
        }
        else if(!dragonKillsPerMinCounts.equals(other.dragonKillsPerMinCounts)) {
            return false;
        }
        if(elderLizardAssistsPerMinCounts == null) {
            if(other.elderLizardAssistsPerMinCounts != null) {
                return false;
            }
        }
        else if(!elderLizardAssistsPerMinCounts.equals(other.elderLizardAssistsPerMinCounts)) {
            return false;
        }
        if(elderLizardKillsPerMinCounts == null) {
            if(other.elderLizardKillsPerMinCounts != null) {
                return false;
            }
        }
        else if(!elderLizardKillsPerMinCounts.equals(other.elderLizardKillsPerMinCounts)) {
            return false;
        }
        if(goldPerMinDeltas == null) {
            if(other.goldPerMinDeltas != null) {
                return false;
            }
        }
        else if(!goldPerMinDeltas.equals(other.goldPerMinDeltas)) {
            return false;
        }
        if(inhibitorAssistsPerMinCounts == null) {
            if(other.inhibitorAssistsPerMinCounts != null) {
                return false;
            }
        }
        else if(!inhibitorAssistsPerMinCounts.equals(other.inhibitorAssistsPerMinCounts)) {
            return false;
        }
        if(inhibitorKillsPerMinCounts == null) {
            if(other.inhibitorKillsPerMinCounts != null) {
                return false;
            }
        }
        else if(!inhibitorKillsPerMinCounts.equals(other.inhibitorKillsPerMinCounts)) {
            return false;
        }
        if(lane != other.lane) {
            return false;
        }
        if(role != other.role) {
            return false;
        }
        if(towerAssistsPerMinCounts == null) {
            if(other.towerAssistsPerMinCounts != null) {
                return false;
            }
        }
        else if(!towerAssistsPerMinCounts.equals(other.towerAssistsPerMinCounts)) {
            return false;
        }
        if(towerKillsPerMinCounts == null) {
            if(other.towerKillsPerMinCounts != null) {
                return false;
            }
        }
        else if(!towerKillsPerMinCounts.equals(other.towerKillsPerMinCounts)) {
            return false;
        }
        if(towerKillsPerMinDeltas == null) {
            if(other.towerKillsPerMinDeltas != null) {
                return false;
            }
        }
        else if(!towerKillsPerMinDeltas.equals(other.towerKillsPerMinDeltas)) {
            return false;
        }
        if(vilemawAssistsPerMinCounts == null) {
            if(other.vilemawAssistsPerMinCounts != null) {
                return false;
            }
        }
        else if(!vilemawAssistsPerMinCounts.equals(other.vilemawAssistsPerMinCounts)) {
            return false;
        }
        if(vilemawKillsPerMinCounts == null) {
            if(other.vilemawKillsPerMinCounts != null) {
                return false;
            }
        }
        else if(!vilemawKillsPerMinCounts.equals(other.vilemawKillsPerMinCounts)) {
            return false;
        }
        if(wardsPerMinDeltas == null) {
            if(other.wardsPerMinDeltas != null) {
                return false;
            }
        }
        else if(!wardsPerMinDeltas.equals(other.wardsPerMinDeltas)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (XPDiffPerMinDeltas == null ? 0 : XPDiffPerMinDeltas.hashCode());
        result = prime * result + (XPPerMinDeltas == null ? 0 : XPPerMinDeltas.hashCode());
        result = prime * result + (ancientGolemAssistsPerMinCounts == null ? 0 : ancientGolemAssistsPerMinCounts.hashCode());
        result = prime * result + (ancientGolemKillsPerMinCounts == null ? 0 : ancientGolemKillsPerMinCounts.hashCode());
        result = prime * result + (assistedLaneDeathsPerMinDeltas == null ? 0 : assistedLaneDeathsPerMinDeltas.hashCode());
        result = prime * result + (assistedLaneKillsPerMinDeltas == null ? 0 : assistedLaneKillsPerMinDeltas.hashCode());
        result = prime * result + (baronAssistsPerMinCounts == null ? 0 : baronAssistsPerMinCounts.hashCode());
        result = prime * result + (baronKillsPerMinCounts == null ? 0 : baronKillsPerMinCounts.hashCode());
        result = prime * result + (creepsPerMinDeltas == null ? 0 : creepsPerMinDeltas.hashCode());
        result = prime * result + (csDiffPerMinDeltas == null ? 0 : csDiffPerMinDeltas.hashCode());
        result = prime * result + (damageTakenDiffPerMinDeltas == null ? 0 : damageTakenDiffPerMinDeltas.hashCode());
        result = prime * result + (damageTakenPerMinDeltas == null ? 0 : damageTakenPerMinDeltas.hashCode());
        result = prime * result + (dragonAssistsPerMinCounts == null ? 0 : dragonAssistsPerMinCounts.hashCode());
        result = prime * result + (dragonKillsPerMinCounts == null ? 0 : dragonKillsPerMinCounts.hashCode());
        result = prime * result + (elderLizardAssistsPerMinCounts == null ? 0 : elderLizardAssistsPerMinCounts.hashCode());
        result = prime * result + (elderLizardKillsPerMinCounts == null ? 0 : elderLizardKillsPerMinCounts.hashCode());
        result = prime * result + (goldPerMinDeltas == null ? 0 : goldPerMinDeltas.hashCode());
        result = prime * result + (inhibitorAssistsPerMinCounts == null ? 0 : inhibitorAssistsPerMinCounts.hashCode());
        result = prime * result + (inhibitorKillsPerMinCounts == null ? 0 : inhibitorKillsPerMinCounts.hashCode());
        result = prime * result + (lane == null ? 0 : lane.hashCode());
        result = prime * result + (role == null ? 0 : role.hashCode());
        result = prime * result + (towerAssistsPerMinCounts == null ? 0 : towerAssistsPerMinCounts.hashCode());
        result = prime * result + (towerKillsPerMinCounts == null ? 0 : towerKillsPerMinCounts.hashCode());
        result = prime * result + (towerKillsPerMinDeltas == null ? 0 : towerKillsPerMinDeltas.hashCode());
        result = prime * result + (vilemawAssistsPerMinCounts == null ? 0 : vilemawAssistsPerMinCounts.hashCode());
        result = prime * result + (vilemawKillsPerMinCounts == null ? 0 : vilemawKillsPerMinCounts.hashCode());
        result = prime * result + (wardsPerMinDeltas == null ? 0 : wardsPerMinDeltas.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ParticipantTimeline";
    }
}
