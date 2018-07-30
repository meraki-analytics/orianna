package com.merakianalytics.orianna.types.dto.staticdata;

import java.util.List;
import java.util.Map;

import com.merakianalytics.orianna.types.dto.DataObject;

public class Patches extends DataObject {
    private static final long serialVersionUID = -3955844801867797013L;
    private List<Patch> patches;
    private String platform;
    private Map<String, Long> shifts;

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
        final Patches other = (Patches)obj;
        if(patches == null) {
            if(other.patches != null) {
                return false;
            }
        } else if(!patches.equals(other.patches)) {
            return false;
        }
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(shifts == null) {
            if(other.shifts != null) {
                return false;
            }
        } else if(!shifts.equals(other.shifts)) {
            return false;
        }
        return true;
    }

    /**
     * @return the patches
     */
    public List<Patch> getPatches() {
        return patches;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the shifts
     */
    public Map<String, Long> getShifts() {
        return shifts;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (patches == null ? 0 : patches.hashCode());
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (shifts == null ? 0 : shifts.hashCode());
        return result;
    }

    /**
     * @param patches
     *        the patches to set
     */
    public void setPatches(final List<Patch> patches) {
        this.patches = patches;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param shifts
     *        the shifts to set
     */
    public void setShifts(final Map<String, Long> shifts) {
        this.shifts = shifts;
    }
}
