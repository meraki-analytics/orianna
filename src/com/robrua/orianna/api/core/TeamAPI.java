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
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.core.team.Team;

public abstract class TeamAPI {
    /**
     * @param teamID
     *            the ID of the team to get
     * @return the team
     */
    public static Team getTeam(final String teamID) {
        return getTeams(new String[] {teamID}).get(0);
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get
     * @return the teams
     */
    public static List<Team> getTeams(final List<String> teamIDs) {
        if(teamIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Team> teams = new ArrayList<>();
        final Set<Long> sumIDs = new HashSet<>();
        for(final List<String> get : Utils.breakUpList(teamIDs, 10)) {
            final Map<String, com.robrua.orianna.type.dto.team.Team> tms = BaseRiotAPI.getTeamsByID(get);
            for(final String ID : get) {
                final com.robrua.orianna.type.dto.team.Team team = tms.get(ID);
                sumIDs.addAll(team.getSummonerIDs());
                teams.add(new Team(team));
            }
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getSummonersByID(new ArrayList<>(sumIDs));
        }

        return Collections.unmodifiableList(teams);
    }

    /**
     * @param teamIDs
     *            the IDs of the teams to get
     * @return the teams
     */
    public static List<Team> getTeams(final String... teamIDs) {
        return getTeams(Arrays.asList(teamIDs));
    }

    /**
     * @param summoners
     *            the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummoner(final List<Summoner> summoners) {
        final List<Long> IDs = new ArrayList<>();
        for(final Summoner summoner : summoners) {
            IDs.add(summoner.getID());
        }

        return getTeamsBySummonerID(IDs);
    }

    /**
     * @param summoners
     *            the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummoner(final Summoner... summoners) {
        final List<Long> IDs = new ArrayList<>();
        for(final Summoner summoner : summoners) {
            IDs.add(summoner.getID());
        }

        return getTeamsBySummonerID(IDs);
    }

    /**
     * @param summoner
     *            the summoner to get teams for
     * @return the summoners' teams
     */
    public static List<Team> getTeamsBySummoner(final Summoner summoner) {
        return getTeamsBySummonerID(summoner.getID());
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummonerID(final List<Long> summonerIDs) {
        if(summonerIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<List<Team>> teams = new ArrayList<>();
        final Set<Long> sumIDs = new HashSet<>();
        for(final List<Long> get : Utils.breakUpList(summonerIDs, 10)) {
            final Map<Long, List<com.robrua.orianna.type.dto.team.Team>> tms = BaseRiotAPI.getTeamsBySummoner(get);
            for(final Long ID : get) {
                final List<Team> tm = new ArrayList<>();
                if(tms.get(ID) != null) {
                    for(final com.robrua.orianna.type.dto.team.Team team : tms.get(ID)) {
                        sumIDs.addAll(team.getSummonerIDs());
                        tm.add(new Team(team));
                    }
                }
                teams.add(Collections.unmodifiableList(tm));
            }
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getSummonersByID(new ArrayList<>(sumIDs));
        }

        return Collections.unmodifiableList(teams);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummonerID(final long... summonerIDs) {
        return getTeamsBySummonerID(Utils.convert(summonerIDs));
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get teams for
     * @return the summoner's teams
     */
    public static List<Team> getTeamsBySummonerID(final long summonerID) {
        return getTeamsBySummonerID(new long[] {summonerID}).get(0);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummonerName(final List<String> summonerNames) {
        return getTeamsBySummoner(RiotAPI.getSummonersByName(summonerNames));
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get teams for
     * @return the summoners' teams
     */
    public static List<List<Team>> getTeamsBySummonerName(final String... summonerNames) {
        return getTeamsBySummoner(RiotAPI.getSummonersByName(summonerNames));
    }

    /**
     * @param summonerName
     *            the names of the summoner to get teams for
     * @return the summoner's teams
     */
    public static List<List<Team>> getTeamsBySummonerName(final String summonerName) {
        return getTeamsBySummoner(RiotAPI.getSummonersByName(summonerName));
    }
}
