package lib.orianna.type.match;

import java.io.Serializable;

public class ParticipantTimelineData implements Serializable {
    private static final long serialVersionUID = 3274418203425750721L;
    public final Double tenToTwenty, thirtyToEnd, twentyToThirty, zeroToTen;

    public ParticipantTimelineData(final Double tenToTwenty, final Double thirtyToEnd, final Double twentyToThirty, final Double zeroToTen) {
        this.tenToTwenty = tenToTwenty;
        this.thirtyToEnd = thirtyToEnd;
        this.twentyToThirty = twentyToThirty;
        this.zeroToTen = zeroToTen;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof ParticipantTimelineData)) {
            return false;
        }
        final ParticipantTimelineData other = (ParticipantTimelineData)obj;
        if(tenToTwenty == null) {
            if(other.tenToTwenty != null) {
                return false;
            }
        }
        else if(!tenToTwenty.equals(other.tenToTwenty)) {
            return false;
        }
        if(thirtyToEnd == null) {
            if(other.thirtyToEnd != null) {
                return false;
            }
        }
        else if(!thirtyToEnd.equals(other.thirtyToEnd)) {
            return false;
        }
        if(twentyToThirty == null) {
            if(other.twentyToThirty != null) {
                return false;
            }
        }
        else if(!twentyToThirty.equals(other.twentyToThirty)) {
            return false;
        }
        if(zeroToTen == null) {
            if(other.zeroToTen != null) {
                return false;
            }
        }
        else if(!zeroToTen.equals(other.zeroToTen)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (tenToTwenty == null ? 0 : tenToTwenty.hashCode());
        result = prime * result + (thirtyToEnd == null ? 0 : thirtyToEnd.hashCode());
        result = prime * result + (twentyToThirty == null ? 0 : twentyToThirty.hashCode());
        result = prime * result + (zeroToTen == null ? 0 : zeroToTen.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ParticipantTimelineData [tenToTwenty=" + tenToTwenty + ", thirtyToEnd=" + thirtyToEnd + ", twentyToThirty=" + twentyToThirty + ", zeroToTen="
                + zeroToTen + "]";
    }
}
