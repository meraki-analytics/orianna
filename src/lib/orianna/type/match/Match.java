package lib.orianna.type.match;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import lib.orianna.api.queryspecs.Region;
import lib.orianna.api.queryspecs.Season;
import lib.orianna.type.game.GameMap;

public class Match implements Serializable {
    private static final long serialVersionUID = 8123251024401619484L;
    public final LocalDateTime creation;
    public final Duration duration;
    public final Long ID;
    public final GameMap map;
    public final List<ParticipantIdentity> participantIdentities;
    public final List<Participant> participants;
    public final QueueType queueType;
    public final Region region;
    public final Season season;
    public final List<MatchTeam> teams;
    public final MatchTimeline timeline;
    public final String version;

    public Match(final LocalDateTime creation, final Duration duration, final Long ID, final GameMap map,
            final List<ParticipantIdentity> participantIdentities, final List<Participant> participants, final QueueType queueType, final Region region,
            final Season season, final List<MatchTeam> teams, final MatchTimeline timeline, final String version) {
        this.creation = creation;
        this.duration = duration;
        this.ID = ID;
        this.map = map;
        this.participantIdentities = participantIdentities;
        this.participants = participants;
        this.queueType = queueType;
        this.region = region;
        this.season = season;
        this.teams = teams;
        this.timeline = timeline;
        this.version = version;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Match)) {
            return false;
        }
        final Match other = (Match)obj;
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
        return "Match [creation=" + creation + ", queueType=" + queueType + "]";
    }
}
