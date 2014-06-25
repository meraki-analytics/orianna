package lib.orianna.type.team;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lib.orianna.api.RiotAPI;
import lib.orianna.type.league.League;

public class Team implements Serializable {
    private static final long serialVersionUID = 2751335336119768871L;
    public final LocalDateTime createDate, lastGameDate, lastJoinDate, lastJoinedRankedTeamQueueDate, modifyDate, secondLastJoinDate, thirdLastJoinDate;
    public final String ID, name, status, tag;
    public final List<MatchHistorySummary> matchHistory;
    public final Roster roster;
    public final List<TeamStatDetail> teamStatDetails;

    public Team(final LocalDateTime createDate, final LocalDateTime lastGameDate, final LocalDateTime lastJoinDate,
            final LocalDateTime lastJoinedRankedTeamQueueDate, final LocalDateTime modifyDate, final LocalDateTime secondLastJoinDate,
            final LocalDateTime thirdLastJoinDate, final String ID, final String name, final String status, final String tag,
            final List<MatchHistorySummary> matchHistory, final Roster roster, final List<TeamStatDetail> teamStatDetails) {
        this.createDate = createDate;
        this.lastGameDate = lastGameDate;
        this.lastJoinDate = lastJoinDate;
        this.lastJoinedRankedTeamQueueDate = lastJoinedRankedTeamQueueDate;
        this.modifyDate = modifyDate;
        this.secondLastJoinDate = secondLastJoinDate;
        this.thirdLastJoinDate = thirdLastJoinDate;
        this.ID = ID;
        this.name = name;
        this.status = status;
        this.tag = tag;
        this.matchHistory = matchHistory;
        this.roster = roster;
        this.teamStatDetails = teamStatDetails;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Team)) {
            return false;
        }
        final Team other = (Team)obj;
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

    /**
     * @param API
     *            the API to get the data with
     * @return the team's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2638">LoL
     *      API Specification</a>
     */
    public List<League> getLeagueEntries(final RiotAPI API) {
        return API.getLeagueEntriesByTeam(this);
    }

    /**
     * @param API
     *            the API to get the data with
     * @return the team's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2639">LoL
     *      API Specification</a>
     */
    public List<League> getLeagues(final RiotAPI API) {
        return API.getLeaguesByTeam(this);
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
        return name + " [" + tag + "]";
    }
}
