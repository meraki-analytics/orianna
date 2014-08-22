package lib.orianna.type.match;

import java.io.Serializable;

import lib.orianna.type.staticdata.Champion;
import lib.orianna.type.staticdata.SummonerSpell;

public class Participant implements Serializable {
    private static final long serialVersionUID = 1693746519976746740L;
    public final Champion champion;
    public final Integer ID;
    public final SummonerSpell spell1, spell2;
    public final ParticipantStats stats;
    public final MatchTeam team;
    public final ParticipantTimeline timeline;

    public Participant(final Champion champion, final Integer ID, final SummonerSpell spell1, final SummonerSpell spell2, final ParticipantStats stats,
            final MatchTeam team, final ParticipantTimeline timeline) {
        this.champion = champion;
        this.ID = ID;
        this.spell1 = spell1;
        this.spell2 = spell2;
        this.stats = stats;
        this.team = team;
        this.timeline = timeline;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Participant)) {
            return false;
        }
        final Participant other = (Participant)obj;
        if(ID == null) {
            if(other.ID != null) {
                return false;
            }
        }
        else if(!ID.equals(other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ID == null ? 0 : ID.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Participant [champion=" + champion + ", team=" + team + "]";
    }
}
