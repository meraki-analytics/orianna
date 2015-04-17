package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.robrua.orianna.api.Utils;
import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.core.team.Team;

public abstract class LeagueAPI {
    /**
     * @param queueType
     *            the ranked queue type to get the challenger league of
     * @return the challenger league
     */
    public static League getChallenger(final QueueType queueType) {
        final com.robrua.orianna.type.dto.league.League league = BaseRiotAPI.getChallenger(queueType);

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getSummonersByID(new ArrayList<>(league.getSummonerIDs()));
            RiotAPI.getTeams(new ArrayList<>(league.getTeamIDs()));
        }

        return new League(league);
    }

    /**
     * @param summoners
     *            the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummoner(final List<Summoner> summoners) {
        final List<Long> IDs = new ArrayList<>();
        for(final Summoner summoner : summoners) {
            IDs.add(summoner.getID());
        }

        return getLeagueEntriesBySummonerID(IDs);
    }

    /**
     * @param summoners
     *            the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummoner(final Summoner... summoners) {
        final List<Long> IDs = new ArrayList<>();
        for(final Summoner summoner : summoners) {
            IDs.add(summoner.getID());
        }

        return getLeagueEntriesBySummonerID(IDs);
    }

    /**
     * @param summoner
     *            the summoner to get league entries for
     * @return the summoner's league entries
     */
    public static List<League> getLeagueEntriesBySummoner(final Summoner summoner) {
        return getLeagueEntriesBySummonerID(summoner.getID());
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummonerID(final List<Long> summonerIDs) {
        if(summonerIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<List<League>> leagues = new ArrayList<>();
        final Set<Long> sumIDs = new HashSet<>();
        final Set<String> teamIDs = new HashSet<>();
        for(final List<Long> get : Utils.breakUpList(summonerIDs, 10)) {
            final Map<Long, List<com.robrua.orianna.type.dto.league.League>> leg = BaseRiotAPI.getSummonerLeagueEntries(get);
            for(final Long ID : get) {
                final List<League> lgs = new ArrayList<>();
                if(leg.get(ID) != null) {
                    for(final com.robrua.orianna.type.dto.league.League l : leg.get(ID)) {
                        sumIDs.addAll(l.getSummonerIDs());
                        teamIDs.addAll(l.getTeamIDs());
                        lgs.add(new League(l));
                    }
                }
                leagues.add(Collections.unmodifiableList(lgs));
            }
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getSummonersByID(new ArrayList<>(sumIDs));
            RiotAPI.getTeams(new ArrayList<>(teamIDs));
        }

        return Collections.unmodifiableList(leagues);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummonerID(final long... summonerIDs) {
        return getLeagueEntriesBySummonerID(Utils.convert(summonerIDs));
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get league entries for
     * @return the summoner's league entries
     */
    public static List<League> getLeagueEntriesBySummonerID(final long summonerID) {
        return getLeagueEntriesBySummonerID(new long[] {summonerID}).get(0);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummonerName(final List<String> summonerNames) {
        return getLeagueEntriesBySummoner(RiotAPI.getSummonersByName(summonerNames));
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get league entries for
     * @return the summoners' league entries
     */
    public static List<List<League>> getLeagueEntriesBySummonerName(final String... summonerNames) {
        return getLeagueEntriesBySummoner(RiotAPI.getSummonersByName(summonerNames));
    }

    /**
     * @param summonerName
     *            the name of the summoner to get league entries for
     * @return the summoner's league entries
     */
    public static List<League> getLeagueEntriesBySummonerName(final String summonerName) {
        return getLeagueEntriesBySummoner(RiotAPI.getSummonerByName(summonerName));
    }

    /**
     * @param teams
     *            the teams to get league entries for
     * @return the teams' league entries
     */
    public static List<List<League>> getLeagueEntriesByTeam(final List<Team> teams) {
        final List<String> IDs = new ArrayList<>();
        for(final Team team : teams) {
            IDs.add(team.getID());
        }

        return getLeagueEntriesByTeamID(IDs);
    }

    /**
     * @param teams
     *            the teams to get league entries for
     * @return the teams' league entries
     */
    public static List<List<League>> getLeagueEntriesByTeam(final Team... teams) {
        final List<String> IDs = new ArrayList<>();
        for(final Team team : teams) {
            IDs.add(team.getID());
        }

        return getLeagueEntriesByTeamID(IDs);
    }

    /**
     * @param team
     *            the team to get league entries for
     * @return the team's league entries
     */
    public static List<League> getLeagueEntriesByTeam(final Team team) {
        return getLeagueEntriesByTeamID(team.getID());
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get league entries for
     * @return the teams' league entries
     */
    public static List<List<League>> getLeagueEntriesByTeamID(final List<String> teamIDs) {
        if(teamIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<List<League>> leagues = new ArrayList<>();
        final Set<Long> summonerIDs = new HashSet<>();
        final Set<String> tmIDs = new HashSet<>();
        for(final List<String> get : Utils.breakUpList(teamIDs, 10)) {
            final Map<String, List<com.robrua.orianna.type.dto.league.League>> leg = BaseRiotAPI.getTeamLeagueEntries(get);
            for(final String ID : get) {
                final List<League> lgs = new ArrayList<>();
                if(leg.get(ID) != null) {
                    for(final com.robrua.orianna.type.dto.league.League l : leg.get(ID)) {
                        summonerIDs.addAll(l.getSummonerIDs());
                        tmIDs.addAll(l.getTeamIDs());
                        lgs.add(new League(l));
                    }
                }
                leagues.add(Collections.unmodifiableList(lgs));
            }
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getSummonersByID(new ArrayList<>(summonerIDs));
            RiotAPI.getTeams(new ArrayList<>(tmIDs));
        }

        return Collections.unmodifiableList(leagues);
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get league entries for
     * @return the teams' league entries
     */
    public static List<List<League>> getLeagueEntriesByTeamID(final String... teamIDs) {
        return getLeagueEntriesByTeamID(Arrays.asList(teamIDs));
    }

    /**
     * @param teamID
     *            the ID of the team to get league entries for
     * @return the team's league entries
     */
    public static List<League> getLeagueEntriesByTeamID(final String teamID) {
        return getLeagueEntriesByTeamID(new String[] {teamID}).get(0);
    }

    /**
     * @param summoners
     *            the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummoner(final List<Summoner> summoners) {
        final List<Long> IDs = new ArrayList<>();
        for(final Summoner summoner : summoners) {
            IDs.add(summoner.getID());
        }

        return getLeaguesBySummonerID(IDs);
    }

    /**
     * @param summoners
     *            the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummoner(final Summoner... summoners) {
        final List<Long> IDs = new ArrayList<>();
        for(final Summoner summoner : summoners) {
            IDs.add(summoner.getID());
        }

        return getLeaguesBySummonerID(IDs);
    }

    /**
     * @param summoner
     *            the summoner to get leagues for
     * @return the summoner's leagues
     */
    public static List<League> getLeaguesBySummoner(final Summoner summoner) {
        return getLeaguesBySummonerID(summoner.getID());
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummonerID(final List<Long> summonerIDs) {
        if(summonerIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<List<League>> leagues = new ArrayList<>();
        final Set<Long> sumIDs = new HashSet<>();
        final Set<String> teamIDs = new HashSet<>();
        for(final List<Long> get : Utils.breakUpList(summonerIDs, 10)) {
            final Map<Long, List<com.robrua.orianna.type.dto.league.League>> leg = BaseRiotAPI.getSummonerLeagues(get);
            for(final Long ID : get) {
                final List<League> lgs = new ArrayList<>();
                if(leg.get(ID) != null) {
                    for(final com.robrua.orianna.type.dto.league.League l : leg.get(ID)) {
                        sumIDs.addAll(l.getSummonerIDs());
                        teamIDs.addAll(l.getTeamIDs());
                        lgs.add(new League(l));
                    }
                }
                leagues.add(Collections.unmodifiableList(lgs));
            }
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getSummonersByID(new ArrayList<>(sumIDs));
            RiotAPI.getTeams(new ArrayList<>(teamIDs));
        }

        return Collections.unmodifiableList(leagues);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummonerID(final long... summonerIDs) {
        return getLeaguesBySummonerID(Utils.convert(summonerIDs));
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get leagues for
     * @return the summoner's leagues
     */
    public static List<League> getLeaguesBySummonerID(final long summonerID) {
        return getLeaguesBySummonerID(new long[] {summonerID}).get(0);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummonerName(final List<String> summonerNames) {
        return getLeaguesBySummoner(RiotAPI.getSummonersByName(summonerNames));
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get leagues for
     * @return the summoners' leagues
     */
    public static List<List<League>> getLeaguesBySummonerName(final String... summonerNames) {
        return getLeaguesBySummoner(RiotAPI.getSummonersByName(summonerNames));
    }

    /**
     * @param summonerName
     *            the name of the summoner to get leagues for
     * @return the summoner's leagues
     */
    public static List<League> getLeaguesBySummonerName(final String summonerName) {
        return getLeaguesBySummoner(RiotAPI.getSummonerByName(summonerName));
    }

    /**
     * @param teams
     *            the teams to get leagues for
     * @return the teams' leagues
     */
    public static List<List<League>> getLeaguesByTeam(final List<Team> teams) {
        final List<String> IDs = new ArrayList<>();
        for(final Team team : teams) {
            IDs.add(team.getID());
        }

        return getLeaguesByTeamID(IDs);
    }

    /**
     * @param teams
     *            the teams to get leagues for
     * @return the teams' leagues
     */
    public static List<List<League>> getLeaguesByTeam(final Team... teams) {
        final List<String> IDs = new ArrayList<>();
        for(final Team team : teams) {
            IDs.add(team.getID());
        }

        return getLeaguesByTeamID(IDs);
    }

    /**
     * @param team
     *            the team to get leagues for
     * @return the team's leagues
     */
    public static List<League> getLeaguesByTeam(final Team team) {
        return getLeaguesByTeamID(team.getID());
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get leagues for
     * @return the teams' leagues
     */
    public static List<List<League>> getLeaguesByTeamID(final List<String> teamIDs) {
        if(teamIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<List<League>> leagues = new ArrayList<>();
        final Set<Long> summonerIDs = new HashSet<>();
        final Set<String> tmIDs = new HashSet<>();
        for(final List<String> get : Utils.breakUpList(teamIDs, 10)) {
            final Map<String, List<com.robrua.orianna.type.dto.league.League>> leg = BaseRiotAPI.getTeamLeagues(get);
            for(final String ID : get) {
                final List<League> lgs = new ArrayList<>();
                if(leg.get(ID) != null) {
                    for(final com.robrua.orianna.type.dto.league.League l : leg.get(ID)) {
                        summonerIDs.addAll(l.getSummonerIDs());
                        tmIDs.addAll(l.getTeamIDs());
                        lgs.add(new League(l));
                    }
                }
                leagues.add(Collections.unmodifiableList(lgs));
            }
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getSummonersByID(new ArrayList<>(summonerIDs));
            RiotAPI.getTeams(new ArrayList<>(tmIDs));
        }

        return Collections.unmodifiableList(leagues);
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get leagues for
     * @return the teams' leagues
     */
    public static List<List<League>> getLeaguesByTeamID(final String... teamIDs) {
        return getLeaguesByTeamID(Arrays.asList(teamIDs));
    }

    /**
     * @param teamID
     *            the ID of the team to get leagues for
     * @return the team's leagues
     */
    public static List<League> getLeaguesByTeamID(final String teamID) {
        return getLeaguesByTeamID(new String[] {teamID}).get(0);
    }

    /**
     * @param queueType
     *            the ranked queue type to get the master league of
     * @return the master league
     */
    public static League getMaster(final QueueType queueType) {
        final com.robrua.orianna.type.dto.league.League league = BaseRiotAPI.getMaster(queueType);

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getSummonersByID(new ArrayList<>(league.getSummonerIDs()));
            RiotAPI.getTeams(new ArrayList<>(league.getTeamIDs()));
        }

        return new League(league);
    }
}
