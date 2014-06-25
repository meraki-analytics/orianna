package lib.orianna.type.team;

import java.io.Serializable;
import java.util.List;

import lib.orianna.type.summoner.Summoner;

public class Roster implements Serializable {
    private static final long serialVersionUID = 2047791037452146723L;
    public final List<TeamMemberInformation> memberList;
    public final Summoner owner;

    public Roster(final List<TeamMemberInformation> memberList, final Summoner owner) {
        this.memberList = memberList;
        this.owner = owner;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Roster)) {
            return false;
        }
        final Roster other = (Roster)obj;
        if(memberList == null) {
            if(other.memberList != null) {
                return false;
            }
        }
        else if(!memberList.equals(other.memberList)) {
            return false;
        }
        if(owner == null) {
            if(other.owner != null) {
                return false;
            }
        }
        else if(!owner.equals(other.owner)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (memberList == null ? 0 : memberList.hashCode());
        result = prime * result + (owner == null ? 0 : owner.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return memberList.toString();
    }
}
