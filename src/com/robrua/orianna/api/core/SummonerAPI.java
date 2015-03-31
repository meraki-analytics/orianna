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
import com.robrua.orianna.type.core.summoner.MasteryPage;
import com.robrua.orianna.type.core.summoner.RunePage;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.dto.summoner.MasteryPages;
import com.robrua.orianna.type.dto.summoner.RunePages;

public abstract class SummonerAPI {
    /**
     * @param summoners
     *            the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPages(final List<Summoner> summoners) {
        final List<Long> IDs = new ArrayList<>();
        for(final Summoner summoner : summoners) {
            IDs.add(summoner.getID());
        }

        return getMasteryPagesByID(IDs);
    }

    /**
     * @param summoners
     *            the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPages(final Summoner... summoners) {
        final List<Long> IDs = new ArrayList<>();
        for(final Summoner summoner : summoners) {
            IDs.add(summoner.getID());
        }

        return getMasteryPagesByID(IDs);
    }

    /**
     * @param summoner
     *            the summoner to get mastery pages for
     * @return the summoner's mastery pages
     */
    public static List<MasteryPage> getMasteryPages(final Summoner summoner) {
        return getMasteryPagesByID(summoner.getID());
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPagesByID(final List<Long> summonerIDs) {
        if(summonerIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<List<MasteryPage>> pages = new ArrayList<>();
        final Set<Long> masteryIDs = new HashSet<>();
        for(final List<Long> get : Utils.breakUpList(summonerIDs, 40)) {
            final Map<Long, MasteryPages> pgs = BaseRiotAPI.getSummonersMasteries(get);
            for(final Long ID : get) {
                final MasteryPages pg = pgs.get(ID);
                final List<MasteryPage> forOne = new ArrayList<>(pg.getPages().size());
                for(final com.robrua.orianna.type.dto.summoner.MasteryPage p : pg.getPages()) {
                    forOne.add(new MasteryPage(p));
                }

                pages.add(Collections.unmodifiableList(forOne));
                masteryIDs.addAll(pg.getMasteryIDs());
            }
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getMasteries(new ArrayList<>(masteryIDs));
        }

        return Collections.unmodifiableList(pages);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPagesByID(final long... summonerIDs) {
        return getMasteryPagesByID(Utils.convert(summonerIDs));
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get mastery pages for
     * @return the summoner's mastery pages
     */
    public static List<MasteryPage> getMasteryPagesByID(final long summonerID) {
        return getMasteryPagesByID(new long[] {summonerID}).get(0);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPagesByName(final List<String> summonerNames) {
        return getMasteryPages(getSummonersByName(summonerNames));
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get mastery pages for
     * @return the summoners' mastery pages
     */
    public static List<List<MasteryPage>> getMasteryPagesByName(final String... summonerNames) {
        return getMasteryPages(getSummonersByName(summonerNames));
    }

    /**
     * @param summonerName
     *            the name of the summoner to get mastery pages for
     * @return the summoner's mastery pages
     */
    public static List<MasteryPage> getMasteryPagesByName(final String summonerName) {
        return getMasteryPages(getSummonerByName(summonerName));
    }

    /**
     * @param summoners
     *            the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePages(final List<Summoner> summoners) {
        final List<Long> IDs = new ArrayList<>();
        for(final Summoner summoner : summoners) {
            IDs.add(summoner.getID());
        }

        return getRunePagesByID(IDs);
    }

    /**
     * @param summoners
     *            the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePages(final Summoner... summoners) {
        final List<Long> IDs = new ArrayList<>();
        for(final Summoner summoner : summoners) {
            IDs.add(summoner.getID());
        }

        return getRunePagesByID(IDs);
    }

    /**
     * @param summoner
     *            the summoner to get rune pages for
     * @return the summoner's rune pages
     */
    public static List<RunePage> getRunePages(final Summoner summoner) {
        return getRunePagesByID(summoner.getID());
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePagesByID(final List<Long> summonerIDs) {
        if(summonerIDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<List<RunePage>> pages = new ArrayList<>();
        final Set<Long> runeIDs = new HashSet<>();
        for(final List<Long> get : Utils.breakUpList(summonerIDs, 40)) {
            final Map<Long, RunePages> pgs = BaseRiotAPI.getSummonersRunes(get);
            for(final Long ID : get) {
                final RunePages pg = pgs.get(ID);
                final List<RunePage> forOne = new ArrayList<>(pg.getPages().size());
                for(final com.robrua.orianna.type.dto.summoner.RunePage p : pg.getPages()) {
                    forOne.add(new RunePage(p));
                }

                pages.add(Collections.unmodifiableList(forOne));
                runeIDs.addAll(pg.getRuneIDs());
            }
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getRunes(new ArrayList<>(runeIDs));
        }

        return Collections.unmodifiableList(pages);
    }

    /**
     * @param summonerIDs
     *            the IDs of the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePagesByID(final long... summonerIDs) {
        return getRunePagesByID(Utils.convert(summonerIDs));
    }

    /**
     * @param summonerID
     *            the ID of the summoner to get rune pages for
     * @return the summoner's rune pages
     */
    public static List<RunePage> getRunePagesByID(final long summonerID) {
        return getRunePagesByID(new long[] {summonerID}).get(0);
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePagesByName(final List<String> summonerNames) {
        return getRunePages(getSummonersByName(summonerNames));
    }

    /**
     * @param summonerNames
     *            the names of the summoners to get rune pages for
     * @return the summoners' rune pages
     */
    public static List<List<RunePage>> getRunePagesByName(final String... summonerNames) {
        return getRunePages(getSummonersByName(summonerNames));
    }

    /**
     * @param summonerName
     *            the name of the summoner to get rune pages for
     * @return the summoner's rune pages
     */
    public static List<RunePage> getRunePagesByName(final String summonerName) {
        return getRunePages(getSummonerByName(summonerName));
    }

    /**
     * @param ID
     *            the ID of the summoner to get
     * @return the summoner
     */
    public static Summoner getSummonerByID(final long ID) {
        return getSummonersByID(ID).get(0);
    }

    /**
     * @param name
     *            the name of the summoner to get
     * @return the summoners
     */
    public static Summoner getSummonerByName(final String name) {
        return getSummonersByName(name).get(0);
    }

    /**
     * @param ID
     *            the ID of the summoner to get the names of
     * @return the summoner's name
     */
    public static String getSummonerName(final long ID) {
        return getSummonersNames(ID).get(0);
    }

    /**
     * @param IDs
     *            the IDs of the summoners to get
     * @return the summoners
     */
    public synchronized static List<Summoner> getSummonersByID(final List<Long> IDs) {
        if(IDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Summoner> summoners = RiotAPI.store.get(Summoner.class, IDs);
        final List<Long> toGet = new ArrayList<>();
        final List<Integer> index = new ArrayList<>();
        for(int i = 0; i < IDs.size(); i++) {
            if(summoners.get(i) == null) {
                toGet.add(IDs.get(i));
                index.add(i);
            }
        }

        if(toGet.isEmpty()) {
            return summoners;
        }

        final List<Summoner> gotten = new ArrayList<>();
        final List<String> names = new ArrayList<>();
        for(final List<Long> get : Utils.breakUpList(toGet, 40)) {
            final Map<Long, com.robrua.orianna.type.dto.summoner.Summoner> sums = BaseRiotAPI.getSummonersByID(get);
            for(final Long ID : get) {
                gotten.add(new Summoner(sums.get(ID)));
                names.add(sums.get(ID).getName());
            }
        }

        RiotAPI.store.store(gotten, toGet, false);
        RiotAPI.store.store(gotten, names, false);

        int count = 0;
        for(final Integer id : index) {
            summoners.set(id, gotten.get(count++));
        }

        return Collections.unmodifiableList(summoners);
    }

    /**
     * @param IDs
     *            the IDs of the summoners to get
     * @return the summoners
     */
    public static List<Summoner> getSummonersByID(final long... IDs) {
        return getSummonersByID(Utils.convert(IDs));
    }

    /**
     * @param names
     *            the names of the summoners to get
     * @return the summoners
     */
    public synchronized static List<Summoner> getSummonersByName(final List<String> names) {
        if(names.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Summoner> summoners = RiotAPI.store.get(Summoner.class, names);
        final List<String> toGet = new ArrayList<>();
        final List<Integer> index = new ArrayList<>();
        for(int i = 0; i < names.size(); i++) {
            if(summoners.get(i) == null) {
                toGet.add(names.get(i));
                index.add(i);
            }
        }

        if(toGet.isEmpty()) {
            return summoners;
        }

        final List<Summoner> gotten = new ArrayList<>();
        final List<String> IDs = new ArrayList<>();
        for(final List<String> get : Utils.breakUpList(toGet, 40)) {
            final Map<String, com.robrua.orianna.type.dto.summoner.Summoner> sums = BaseRiotAPI.getSummonersByName(get);
            for(final String name : get) {
                final String std = standardize(name);
                gotten.add(new Summoner(sums.get(std)));

                if(sums.get(std) == null) {
                    IDs.add("[" + null + "]");
                }
                else {
                    IDs.add("[" + sums.get(std).getId() + "]");
                }
            }
        }

        RiotAPI.store.store(gotten, toGet, false);
        RiotAPI.store.store(gotten, IDs, false);

        int count = 0;
        for(final Integer id : index) {
            summoners.set(id, gotten.get(count++));
        }

        return Collections.unmodifiableList(summoners);
    }

    /**
     * @param names
     *            the names of the summoners to get
     * @return the summoners
     */
    public static List<Summoner> getSummonersByName(final String... names) {
        return getSummonersByName(Arrays.asList(names));
    }

    /**
     * @param IDs
     *            the IDs of the summoners to get the names of
     * @return the summoners' names
     */
    public synchronized static List<String> getSummonersNames(final List<Long> IDs) {
        if(IDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Summoner> summoners = RiotAPI.store.get(Summoner.class, IDs);
        final List<String> names = new ArrayList<>(summoners.size());
        for(final Summoner summoner : summoners) {
            if(summoner != null) {
                names.add(summoner.getName());
            }
            else {
                names.add(null);
            }
        }

        final List<Long> toGet = new ArrayList<>();
        final List<Integer> index = new ArrayList<>();
        for(int i = 0; i < IDs.size(); i++) {
            if(summoners.get(i) == null) {
                toGet.add(IDs.get(i));
                index.add(i);
            }
        }

        if(toGet.isEmpty()) {
            return names;
        }

        final List<String> gotten = new ArrayList<>();
        for(final List<Long> get : Utils.breakUpList(toGet, 40)) {
            final Map<Long, String> sums = BaseRiotAPI.getSummonersNames(get);
            for(final Long ID : get) {
                gotten.add(sums.get(ID));
            }
        }

        int count = 0;
        for(final Integer id : index) {
            names.set(id, gotten.get(count++));
        }

        return Collections.unmodifiableList(names);
    }

    /**
     * @param IDs
     *            the IDs of the summoners to get the names of
     * @return the summoners' names
     */
    public static List<String> getSummonersNames(final long... IDs) {
        return getSummonersNames(Utils.convert(IDs));
    }

    /**
     * Standardizes a summoner name, which is the summoner name in all lower
     * case and with spaces removed (per the API spec)
     *
     * @param summonerName
     *            the name to standardize
     * @return the standardized summoner name
     */
    private static String standardize(final String summonerName) {
        return summonerName.replaceAll(" ", "").toLowerCase();
    }
}
