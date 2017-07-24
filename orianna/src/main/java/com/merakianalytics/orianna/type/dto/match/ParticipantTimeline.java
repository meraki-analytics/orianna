package com.merakianalytics.orianna.type.dto.match;

import java.util.Map;

import com.merakianalytics.orianna.type.dto.DataObject;

public class ParticipantTimeline extends DataObject {
    private static final long serialVersionUID = -9108740894475294174L;
    private Map<String, Double> csDiffPerMinDeltas, goldPerMinDeltas, xpDiffPerMinDeltas, creepsPerMinDeltas, xpPerMinDeltas, damageTakenDiffPerMinDeltas,
            damageTakenPerMinDeltas;
    private String lane, role;
    private int participantId;

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
        if(getClass() != obj.getClass()) {
            return false;
        }
        final ParticipantTimeline other = (ParticipantTimeline)obj;
        if(creepsPerMinDeltas == null) {
            if(other.creepsPerMinDeltas != null) {
                return false;
            }
        } else if(!creepsPerMinDeltas.equals(other.creepsPerMinDeltas)) {
            return false;
        }
        if(csDiffPerMinDeltas == null) {
            if(other.csDiffPerMinDeltas != null) {
                return false;
            }
        } else if(!csDiffPerMinDeltas.equals(other.csDiffPerMinDeltas)) {
            return false;
        }
        if(damageTakenDiffPerMinDeltas == null) {
            if(other.damageTakenDiffPerMinDeltas != null) {
                return false;
            }
        } else if(!damageTakenDiffPerMinDeltas.equals(other.damageTakenDiffPerMinDeltas)) {
            return false;
        }
        if(damageTakenPerMinDeltas == null) {
            if(other.damageTakenPerMinDeltas != null) {
                return false;
            }
        } else if(!damageTakenPerMinDeltas.equals(other.damageTakenPerMinDeltas)) {
            return false;
        }
        if(goldPerMinDeltas == null) {
            if(other.goldPerMinDeltas != null) {
                return false;
            }
        } else if(!goldPerMinDeltas.equals(other.goldPerMinDeltas)) {
            return false;
        }
        if(lane == null) {
            if(other.lane != null) {
                return false;
            }
        } else if(!lane.equals(other.lane)) {
            return false;
        }
        if(participantId != other.participantId) {
            return false;
        }
        if(role == null) {
            if(other.role != null) {
                return false;
            }
        } else if(!role.equals(other.role)) {
            return false;
        }
        if(xpDiffPerMinDeltas == null) {
            if(other.xpDiffPerMinDeltas != null) {
                return false;
            }
        } else if(!xpDiffPerMinDeltas.equals(other.xpDiffPerMinDeltas)) {
            return false;
        }
        if(xpPerMinDeltas == null) {
            if(other.xpPerMinDeltas != null) {
                return false;
            }
        } else if(!xpPerMinDeltas.equals(other.xpPerMinDeltas)) {
            return false;
        }
        return true;
    }

    /**
     * @return the creepsPerMinDeltas
     */
    public Map<String, Double> getCreepsPerMinDeltas() {
        return creepsPerMinDeltas;
    }

    /**
     * @return the csDiffPerMinDeltas
     */
    public Map<String, Double> getCsDiffPerMinDeltas() {
        return csDiffPerMinDeltas;
    }

    /**
     * @return the damageTakenDiffPerMinDeltas
     */
    public Map<String, Double> getDamageTakenDiffPerMinDeltas() {
        return damageTakenDiffPerMinDeltas;
    }

    /**
     * @return the damageTakenPerMinDeltas
     */
    public Map<String, Double> getDamageTakenPerMinDeltas() {
        return damageTakenPerMinDeltas;
    }

    /**
     * @return the goldPerMinDeltas
     */
    public Map<String, Double> getGoldPerMinDeltas() {
        return goldPerMinDeltas;
    }

    /**
     * @return the lane
     */
    public String getLane() {
        return lane;
    }

    /**
     * @return the participantId
     */
    public int getParticipantId() {
        return participantId;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the xpDiffPerMinDeltas
     */
    public Map<String, Double> getXpDiffPerMinDeltas() {
        return xpDiffPerMinDeltas;
    }

    /**
     * @return the xpPerMinDeltas
     */
    public Map<String, Double> getXpPerMinDeltas() {
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
        result = prime * result + (creepsPerMinDeltas == null ? 0 : creepsPerMinDeltas.hashCode());
        result = prime * result + (csDiffPerMinDeltas == null ? 0 : csDiffPerMinDeltas.hashCode());
        result = prime * result + (damageTakenDiffPerMinDeltas == null ? 0 : damageTakenDiffPerMinDeltas.hashCode());
        result = prime * result + (damageTakenPerMinDeltas == null ? 0 : damageTakenPerMinDeltas.hashCode());
        result = prime * result + (goldPerMinDeltas == null ? 0 : goldPerMinDeltas.hashCode());
        result = prime * result + (lane == null ? 0 : lane.hashCode());
        result = prime * result + participantId;
        result = prime * result + (role == null ? 0 : role.hashCode());
        result = prime * result + (xpDiffPerMinDeltas == null ? 0 : xpDiffPerMinDeltas.hashCode());
        result = prime * result + (xpPerMinDeltas == null ? 0 : xpPerMinDeltas.hashCode());
        return result;
    }

    /**
     * @param creepsPerMinDeltas
     *        the creepsPerMinDeltas to set
     */
    public void setCreepsPerMinDeltas(final Map<String, Double> creepsPerMinDeltas) {
        this.creepsPerMinDeltas = creepsPerMinDeltas;
    }

    /**
     * @param csDiffPerMinDeltas
     *        the csDiffPerMinDeltas to set
     */
    public void setCsDiffPerMinDeltas(final Map<String, Double> csDiffPerMinDeltas) {
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
    }

    /**
     * @param damageTakenDiffPerMinDeltas
     *        the damageTakenDiffPerMinDeltas to set
     */
    public void setDamageTakenDiffPerMinDeltas(final Map<String, Double> damageTakenDiffPerMinDeltas) {
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
    }

    /**
     * @param damageTakenPerMinDeltas
     *        the damageTakenPerMinDeltas to set
     */
    public void setDamageTakenPerMinDeltas(final Map<String, Double> damageTakenPerMinDeltas) {
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
    }

    /**
     * @param goldPerMinDeltas
     *        the goldPerMinDeltas to set
     */
    public void setGoldPerMinDeltas(final Map<String, Double> goldPerMinDeltas) {
        this.goldPerMinDeltas = goldPerMinDeltas;
    }

    /**
     * @param lane
     *        the lane to set
     */
    public void setLane(final String lane) {
        this.lane = lane;
    }

    /**
     * @param participantId
     *        the participantId to set
     */
    public void setParticipantId(final int participantId) {
        this.participantId = participantId;
    }

    /**
     * @param role
     *        the role to set
     */
    public void setRole(final String role) {
        this.role = role;
    }

    /**
     * @param xpDiffPerMinDeltas
     *        the xpDiffPerMinDeltas to set
     */
    public void setXpDiffPerMinDeltas(final Map<String, Double> xpDiffPerMinDeltas) {
        this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
    }

    /**
     * @param xpPerMinDeltas
     *        the xpPerMinDeltas to set
     */
    public void setXpPerMinDeltas(final Map<String, Double> xpPerMinDeltas) {
        this.xpPerMinDeltas = xpPerMinDeltas;
    }
}
