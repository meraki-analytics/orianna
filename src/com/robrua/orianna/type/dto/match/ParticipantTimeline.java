package com.robrua.orianna.type.dto.match;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "participanttimeline")
public class ParticipantTimeline extends OriannaDto {
    private static final long serialVersionUID = 7060687250015890854L;
    @OneToOne(cascade = CascadeType.ALL)
    private ParticipantTimelineData ancientGolemAssistsPerMinCounts, ancientGolemKillsPerMinCounts, assistedLaneDeathsPerMinDeltas,
            assistedLaneKillsPerMinDeltas, baronAssistsPerMinCounts, baronKillsPerMinCounts, creepsPerMinDeltas, csDiffPerMinDeltas,
            damageTakenDiffPerMinDeltas, damageTakenPerMinDeltas, dragonAssistsPerMinCounts, dragonKillsPerMinCounts, elderLizardAssistsPerMinCounts,
            elderLizardKillsPerMinCounts, goldPerMinDeltas, inhibitorAssistsPerMinCounts, inhibitorKillsPerMinCounts, towerAssistsPerMinCounts,
            towerKillsPerMinCounts, towerKillsPerMinDeltas, vilemawAssistsPerMinCounts, vilemawKillsPerMinCounts, wardsPerMinDeltas, xpDiffPerMinDeltas,
            xpPerMinDeltas;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dbId;

    private String lane, role;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        if(lane == null) {
            if(other.lane != null) {
                return false;
            }
        }
        else if(!lane.equals(other.lane)) {
            return false;
        }
        if(role == null) {
            if(other.role != null) {
                return false;
            }
        }
        else if(!role.equals(other.role)) {
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
        if(xpDiffPerMinDeltas == null) {
            if(other.xpDiffPerMinDeltas != null) {
                return false;
            }
        }
        else if(!xpDiffPerMinDeltas.equals(other.xpDiffPerMinDeltas)) {
            return false;
        }
        if(xpPerMinDeltas == null) {
            if(other.xpPerMinDeltas != null) {
                return false;
            }
        }
        else if(!xpPerMinDeltas.equals(other.xpPerMinDeltas)) {
            return false;
        }
        return true;
    }

    /**
     * @return the ancientGolemAssistsPerMinCounts
     */
    public ParticipantTimelineData getAncientGolemAssistsPerMinCounts() {
        return ancientGolemAssistsPerMinCounts;
    }

    /**
     * @return the ancientGolemKillsPerMinCounts
     */
    public ParticipantTimelineData getAncientGolemKillsPerMinCounts() {
        return ancientGolemKillsPerMinCounts;
    }

    /**
     * @return the assistedLaneDeathsPerMinDeltas
     */
    public ParticipantTimelineData getAssistedLaneDeathsPerMinDeltas() {
        return assistedLaneDeathsPerMinDeltas;
    }

    /**
     * @return the assistedLaneKillsPerMinDeltas
     */
    public ParticipantTimelineData getAssistedLaneKillsPerMinDeltas() {
        return assistedLaneKillsPerMinDeltas;
    }

    /**
     * @return the baronAssistsPerMinCounts
     */
    public ParticipantTimelineData getBaronAssistsPerMinCounts() {
        return baronAssistsPerMinCounts;
    }

    /**
     * @return the baronKillsPerMinCounts
     */
    public ParticipantTimelineData getBaronKillsPerMinCounts() {
        return baronKillsPerMinCounts;
    }

    /**
     * @return the creepsPerMinDeltas
     */
    public ParticipantTimelineData getCreepsPerMinDeltas() {
        return creepsPerMinDeltas;
    }

    /**
     * @return the csDiffPerMinDeltas
     */
    public ParticipantTimelineData getCsDiffPerMinDeltas() {
        return csDiffPerMinDeltas;
    }

    /**
     * @return the damageTakenDiffPerMinDeltas
     */
    public ParticipantTimelineData getDamageTakenDiffPerMinDeltas() {
        return damageTakenDiffPerMinDeltas;
    }

    /**
     * @return the damageTakenPerMinDeltas
     */
    public ParticipantTimelineData getDamageTakenPerMinDeltas() {
        return damageTakenPerMinDeltas;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Long.class)) {
            return "dbId";
        }
        return null;
    }

    /**
     * @return the dragonAssistsPerMinCounts
     */
    public ParticipantTimelineData getDragonAssistsPerMinCounts() {
        return dragonAssistsPerMinCounts;
    }

    /**
     * @return the dragonKillsPerMinCounts
     */
    public ParticipantTimelineData getDragonKillsPerMinCounts() {
        return dragonKillsPerMinCounts;
    }

    /**
     * @return the elderLizardAssistsPerMinCounts
     */
    public ParticipantTimelineData getElderLizardAssistsPerMinCounts() {
        return elderLizardAssistsPerMinCounts;
    }

    /**
     * @return the elderLizardKillsPerMinCounts
     */
    public ParticipantTimelineData getElderLizardKillsPerMinCounts() {
        return elderLizardKillsPerMinCounts;
    }

    /**
     * @return the goldPerMinDeltas
     */
    public ParticipantTimelineData getGoldPerMinDeltas() {
        return goldPerMinDeltas;
    }

    /**
     * @return the inhibitorAssistsPerMinCounts
     */
    public ParticipantTimelineData getInhibitorAssistsPerMinCounts() {
        return inhibitorAssistsPerMinCounts;
    }

    /**
     * @return the inhibitorKillsPerMinCounts
     */
    public ParticipantTimelineData getInhibitorKillsPerMinCounts() {
        return inhibitorKillsPerMinCounts;
    }

    /**
     * @return the lane
     */
    public String getLane() {
        return lane;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the towerAssistsPerMinCounts
     */
    public ParticipantTimelineData getTowerAssistsPerMinCounts() {
        return towerAssistsPerMinCounts;
    }

    /**
     * @return the towerKillsPerMinCounts
     */
    public ParticipantTimelineData getTowerKillsPerMinCounts() {
        return towerKillsPerMinCounts;
    }

    /**
     * @return the towerKillsPerMinDeltas
     */
    public ParticipantTimelineData getTowerKillsPerMinDeltas() {
        return towerKillsPerMinDeltas;
    }

    /**
     * @return the vilemawAssistsPerMinCounts
     */
    public ParticipantTimelineData getVilemawAssistsPerMinCounts() {
        return vilemawAssistsPerMinCounts;
    }

    /**
     * @return the vilemawKillsPerMinCounts
     */
    public ParticipantTimelineData getVilemawKillsPerMinCounts() {
        return vilemawKillsPerMinCounts;
    }

    /**
     * @return the wardsPerMinDeltas
     */
    public ParticipantTimelineData getWardsPerMinDeltas() {
        return wardsPerMinDeltas;
    }

    /**
     * @return the xpDiffPerMinDeltas
     */
    public ParticipantTimelineData getXpDiffPerMinDeltas() {
        return xpDiffPerMinDeltas;
    }

    /**
     * @return the xpPerMinDeltas
     */
    public ParticipantTimelineData getXpPerMinDeltas() {
        return xpPerMinDeltas;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        result = prime * result + (xpDiffPerMinDeltas == null ? 0 : xpDiffPerMinDeltas.hashCode());
        result = prime * result + (xpPerMinDeltas == null ? 0 : xpPerMinDeltas.hashCode());
        return result;
    }

    /**
     * @param ancientGolemAssistsPerMinCounts
     *            the ancientGolemAssistsPerMinCounts to set
     */
    public void setAncientGolemAssistsPerMinCounts(final ParticipantTimelineData ancientGolemAssistsPerMinCounts) {
        this.ancientGolemAssistsPerMinCounts = ancientGolemAssistsPerMinCounts;
    }

    /**
     * @param ancientGolemKillsPerMinCounts
     *            the ancientGolemKillsPerMinCounts to set
     */
    public void setAncientGolemKillsPerMinCounts(final ParticipantTimelineData ancientGolemKillsPerMinCounts) {
        this.ancientGolemKillsPerMinCounts = ancientGolemKillsPerMinCounts;
    }

    /**
     * @param assistedLaneDeathsPerMinDeltas
     *            the assistedLaneDeathsPerMinDeltas to set
     */
    public void setAssistedLaneDeathsPerMinDeltas(final ParticipantTimelineData assistedLaneDeathsPerMinDeltas) {
        this.assistedLaneDeathsPerMinDeltas = assistedLaneDeathsPerMinDeltas;
    }

    /**
     * @param assistedLaneKillsPerMinDeltas
     *            the assistedLaneKillsPerMinDeltas to set
     */
    public void setAssistedLaneKillsPerMinDeltas(final ParticipantTimelineData assistedLaneKillsPerMinDeltas) {
        this.assistedLaneKillsPerMinDeltas = assistedLaneKillsPerMinDeltas;
    }

    /**
     * @param baronAssistsPerMinCounts
     *            the baronAssistsPerMinCounts to set
     */
    public void setBaronAssistsPerMinCounts(final ParticipantTimelineData baronAssistsPerMinCounts) {
        this.baronAssistsPerMinCounts = baronAssistsPerMinCounts;
    }

    /**
     * @param baronKillsPerMinCounts
     *            the baronKillsPerMinCounts to set
     */
    public void setBaronKillsPerMinCounts(final ParticipantTimelineData baronKillsPerMinCounts) {
        this.baronKillsPerMinCounts = baronKillsPerMinCounts;
    }

    /**
     * @param creepsPerMinDeltas
     *            the creepsPerMinDeltas to set
     */
    public void setCreepsPerMinDeltas(final ParticipantTimelineData creepsPerMinDeltas) {
        this.creepsPerMinDeltas = creepsPerMinDeltas;
    }

    /**
     * @param csDiffPerMinDeltas
     *            the csDiffPerMinDeltas to set
     */
    public void setCsDiffPerMinDeltas(final ParticipantTimelineData csDiffPerMinDeltas) {
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
    }

    /**
     * @param damageTakenDiffPerMinDeltas
     *            the damageTakenDiffPerMinDeltas to set
     */
    public void setDamageTakenDiffPerMinDeltas(final ParticipantTimelineData damageTakenDiffPerMinDeltas) {
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
    }

    /**
     * @param damageTakenPerMinDeltas
     *            the damageTakenPerMinDeltas to set
     */
    public void setDamageTakenPerMinDeltas(final ParticipantTimelineData damageTakenPerMinDeltas) {
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
    }

    /**
     * @param dragonAssistsPerMinCounts
     *            the dragonAssistsPerMinCounts to set
     */
    public void setDragonAssistsPerMinCounts(final ParticipantTimelineData dragonAssistsPerMinCounts) {
        this.dragonAssistsPerMinCounts = dragonAssistsPerMinCounts;
    }

    /**
     * @param dragonKillsPerMinCounts
     *            the dragonKillsPerMinCounts to set
     */
    public void setDragonKillsPerMinCounts(final ParticipantTimelineData dragonKillsPerMinCounts) {
        this.dragonKillsPerMinCounts = dragonKillsPerMinCounts;
    }

    /**
     * @param elderLizardAssistsPerMinCounts
     *            the elderLizardAssistsPerMinCounts to set
     */
    public void setElderLizardAssistsPerMinCounts(final ParticipantTimelineData elderLizardAssistsPerMinCounts) {
        this.elderLizardAssistsPerMinCounts = elderLizardAssistsPerMinCounts;
    }

    /**
     * @param elderLizardKillsPerMinCounts
     *            the elderLizardKillsPerMinCounts to set
     */
    public void setElderLizardKillsPerMinCounts(final ParticipantTimelineData elderLizardKillsPerMinCounts) {
        this.elderLizardKillsPerMinCounts = elderLizardKillsPerMinCounts;
    }

    /**
     * @param goldPerMinDeltas
     *            the goldPerMinDeltas to set
     */
    public void setGoldPerMinDeltas(final ParticipantTimelineData goldPerMinDeltas) {
        this.goldPerMinDeltas = goldPerMinDeltas;
    }

    /**
     * @param inhibitorAssistsPerMinCounts
     *            the inhibitorAssistsPerMinCounts to set
     */
    public void setInhibitorAssistsPerMinCounts(final ParticipantTimelineData inhibitorAssistsPerMinCounts) {
        this.inhibitorAssistsPerMinCounts = inhibitorAssistsPerMinCounts;
    }

    /**
     * @param inhibitorKillsPerMinCounts
     *            the inhibitorKillsPerMinCounts to set
     */
    public void setInhibitorKillsPerMinCounts(final ParticipantTimelineData inhibitorKillsPerMinCounts) {
        this.inhibitorKillsPerMinCounts = inhibitorKillsPerMinCounts;
    }

    /**
     * @param lane
     *            the lane to set
     */
    public void setLane(final String lane) {
        this.lane = lane;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(final String role) {
        this.role = role;
    }

    /**
     * @param towerAssistsPerMinCounts
     *            the towerAssistsPerMinCounts to set
     */
    public void setTowerAssistsPerMinCounts(final ParticipantTimelineData towerAssistsPerMinCounts) {
        this.towerAssistsPerMinCounts = towerAssistsPerMinCounts;
    }

    /**
     * @param towerKillsPerMinCounts
     *            the towerKillsPerMinCounts to set
     */
    public void setTowerKillsPerMinCounts(final ParticipantTimelineData towerKillsPerMinCounts) {
        this.towerKillsPerMinCounts = towerKillsPerMinCounts;
    }

    /**
     * @param towerKillsPerMinDeltas
     *            the towerKillsPerMinDeltas to set
     */
    public void setTowerKillsPerMinDeltas(final ParticipantTimelineData towerKillsPerMinDeltas) {
        this.towerKillsPerMinDeltas = towerKillsPerMinDeltas;
    }

    /**
     * @param vilemawAssistsPerMinCounts
     *            the vilemawAssistsPerMinCounts to set
     */
    public void setVilemawAssistsPerMinCounts(final ParticipantTimelineData vilemawAssistsPerMinCounts) {
        this.vilemawAssistsPerMinCounts = vilemawAssistsPerMinCounts;
    }

    /**
     * @param vilemawKillsPerMinCounts
     *            the vilemawKillsPerMinCounts to set
     */
    public void setVilemawKillsPerMinCounts(final ParticipantTimelineData vilemawKillsPerMinCounts) {
        this.vilemawKillsPerMinCounts = vilemawKillsPerMinCounts;
    }

    /**
     * @param wardsPerMinDeltas
     *            the wardsPerMinDeltas to set
     */
    public void setWardsPerMinDeltas(final ParticipantTimelineData wardsPerMinDeltas) {
        this.wardsPerMinDeltas = wardsPerMinDeltas;
    }

    /**
     * @param xpDiffPerMinDeltas
     *            the xpDiffPerMinDeltas to set
     */
    public void setXpDiffPerMinDeltas(final ParticipantTimelineData xpDiffPerMinDeltas) {
        this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
    }

    /**
     * @param xpPerMinDeltas
     *            the xpPerMinDeltas to set
     */
    public void setXpPerMinDeltas(final ParticipantTimelineData xpPerMinDeltas) {
        this.xpPerMinDeltas = xpPerMinDeltas;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParticipantTimeline [lane=" + lane + ", role=" + role + ", ancientGolemAssistsPerMinCounts=" + ancientGolemAssistsPerMinCounts
                + ", ancientGolemKillsPerMinCounts=" + ancientGolemKillsPerMinCounts + ", assistedLaneDeathsPerMinDeltas=" + assistedLaneDeathsPerMinDeltas
                + ", assistedLaneKillsPerMinDeltas=" + assistedLaneKillsPerMinDeltas + ", baronAssistsPerMinCounts=" + baronAssistsPerMinCounts
                + ", baronKillsPerMinCounts=" + baronKillsPerMinCounts + ", creepsPerMinDeltas=" + creepsPerMinDeltas + ", csDiffPerMinDeltas="
                + csDiffPerMinDeltas + ", damageTakenDiffPerMinDeltas=" + damageTakenDiffPerMinDeltas + ", damageTakenPerMinDeltas=" + damageTakenPerMinDeltas
                + ", dragonAssistsPerMinCounts=" + dragonAssistsPerMinCounts + ", dragonKillsPerMinCounts=" + dragonKillsPerMinCounts
                + ", elderLizardAssistsPerMinCounts=" + elderLizardAssistsPerMinCounts + ", elderLizardKillsPerMinCounts=" + elderLizardKillsPerMinCounts
                + ", goldPerMinDeltas=" + goldPerMinDeltas + ", inhibitorAssistsPerMinCounts=" + inhibitorAssistsPerMinCounts + ", inhibitorKillsPerMinCounts="
                + inhibitorKillsPerMinCounts + ", towerAssistsPerMinCounts=" + towerAssistsPerMinCounts + ", towerKillsPerMinCounts=" + towerKillsPerMinCounts
                + ", towerKillsPerMinDeltas=" + towerKillsPerMinDeltas + ", vilemawAssistsPerMinCounts=" + vilemawAssistsPerMinCounts
                + ", vilemawKillsPerMinCounts=" + vilemawKillsPerMinCounts + ", wardsPerMinDeltas=" + wardsPerMinDeltas + ", xpDiffPerMinDeltas="
                + xpDiffPerMinDeltas + ", xpPerMinDeltas=" + xpPerMinDeltas + "]";
    }
}
