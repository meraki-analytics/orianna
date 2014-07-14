package lib.orianna.type.summoner;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lib.orianna.api.RiotAPI;
import lib.orianna.api.queryspecs.Season;
import lib.orianna.type.game.Game;
import lib.orianna.type.league.League;
import lib.orianna.type.stats.ChampionStats;
import lib.orianna.type.stats.PlayerStatsSummary;
import lib.orianna.type.stats.PlayerStatsSummaryType;
import lib.orianna.type.team.Team;

public class Summoner implements Serializable {
    private static final long serialVersionUID = 3567907646302193607L;
    public final Long ID, summonerLevel;
    public final String name;
    public final Integer profileIconID;
    public final LocalDateTime revisionDate;

    public Summoner(final Long ID, final Long summonerLevel, final String name, final Integer profileIconID, final LocalDateTime revisionDate) {
        this.ID = ID;
        this.summonerLevel = summonerLevel;
        this.name = name;
        this.profileIconID = profileIconID;
        this.revisionDate = revisionDate;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Summoner)) {
            return false;
        }
        final Summoner other = (Summoner)obj;
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
     * @return the summoner's active mastery page
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public MasteryPage getActiveMasteryPage(final RiotAPI API) {
        return API.getActiveMasteryPage(this);
    }

    /**
     * @param API
     *            the API to get the data with
     * @return the summoner's active rune page
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public RunePage getActiveRunePage(final RiotAPI API) {
        return API.getActiveRunePage(this);
    }

    /**
     * @param API
     *            the API to get the data with
     * @return the summoner's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2641">LoL
     *      API Specification</a>
     */
    public List<League> getLeagueEntries(final RiotAPI API) {
        return API.getLeagueEntriesBySummoner(this);
    }

    /**
     * @param API
     *            the API to get the data with
     * @return the summoner's leagues
     * @see <a href="http://developer.riotgames.com/api/methods#!/741/2640">LoL
     *      API Specification</a>
     */
    public List<League> getLeagues(final RiotAPI API) {
        return API.getLeaguesBySummoner(this);
    }

    /**
     * @param API
     *            the API to get the data with
     * @return the summoner's mastery pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1933">LoL
     *      API Specification</a>
     */
    public List<MasteryPage> getMasteryPages(final RiotAPI API) {
        return API.getMasteryPages(this);
    }

    /**
     * Entries are by champion name, include "All Champions" for totals.
     *
     * @param API
     *            the API to get the data with
     * @return the summoner's ranked stats
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1937">LoL
     *      API Specification</a>
     */
    public Map<String, ChampionStats> getRankedStats(final RiotAPI API) {
        return API.getRankedStats(this);
    }

    /**
     * @param API
     *            the API to get the data with
     * @param season
     *            the season to get data for
     * @return the summoner's ranked stats
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1937">LoL
     *      API Specification</a>
     */
    public Map<String, ChampionStats> getRankedStats(final RiotAPI API, final Season season) {
        return API.getRankedStats(this, season);
    }

    /**
     * @param API
     *            the API to get the data with
     * @return the summoner's recent games
     * @see <a href="http://developer.riotgames.com/api/methods#!/618/1924">LoL
     *      API Specification</a>
     */
    public List<Game> getRecentGames(final RiotAPI API) {
        return API.getRecentGames(this);
    }

    /**
     * @param API
     *            the API to get the data with
     * @return the summoner's rune pages
     * @see <a href="http://developer.riotgames.com/api/methods#!/620/1932">LoL
     *      API Specification</a>
     */
    public List<RunePage> getRunePages(final RiotAPI API) {
        return API.getRunePages(this);
    }

    /**
     * @param API
     *            the API to get the data with
     * @return the summoner's stats summary
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1938">LoL
     *      API Specification</a>
     */
    public Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final RiotAPI API) {
        return API.getSummonerStats(this);
    }

    /**
     * @param API
     *            the API to get the data with
     * @param season
     *            the season to get data for
     * @return the summoner's stats summary
     * @see <a href="http://developer.riotgames.com/api/methods#!/622/1938">LoL
     *      API Specification</a>
     */
    public Map<PlayerStatsSummaryType, PlayerStatsSummary> getStats(final RiotAPI API, final Season season) {
        return API.getSummonerStats(this, season);
    }

    /**
     * @param API
     *            the API to get the data with
     * @return the summoner's teams
     * @see <a href="http://developer.riotgames.com/api/methods#!/743/2644">LoL
     *      API Specification</a>
     */
    public List<Team> getTeams(final RiotAPI API) {
        return API.getTeamsBySummoner(this);
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
        return name;
    }
}
