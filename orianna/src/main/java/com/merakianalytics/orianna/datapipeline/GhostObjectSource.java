package com.merakianalytics.orianna.datapipeline;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.CloseableIterator;
import com.merakianalytics.datapipelines.iterators.CloseableIterators;
import com.merakianalytics.datapipelines.sources.AbstractDataSource;
import com.merakianalytics.datapipelines.sources.Get;
import com.merakianalytics.datapipelines.sources.GetMany;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champion.ChampionData;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.LanguageStrings;
import com.merakianalytics.orianna.types.core.staticdata.Languages;
import com.merakianalytics.orianna.types.core.staticdata.Maps;
import com.merakianalytics.orianna.types.core.staticdata.Mastery;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcons;
import com.merakianalytics.orianna.types.core.staticdata.Realm;
import com.merakianalytics.orianna.types.core.staticdata.Rune;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import com.merakianalytics.orianna.types.core.staticdata.Versions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class GhostObjectSource extends AbstractDataSource {
    private static String getCurrentVersion(final Platform platform, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.staticdata.Realm realm =
            context.getPipeline().get(com.merakianalytics.orianna.types.dto.staticdata.Realm.class, ImmutableMap.<String, Object> of("platform", platform));
        return realm.getV();
    }

    @SuppressWarnings("unchecked")
    @Get(Champion.class)
    public Champion getChampion(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        final String key = (String)query.get("key");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name", key, "key");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final ChampionData data = new ChampionData();
        data.getChampion().setId(id == null ? 0 : id.intValue());
        data.getStatus().setId(id == null ? 0 : id.intValue());
        data.getChampion().setName(name);
        data.getChampion().setKey(key);
        data.getChampion().setPlatform(platform);
        data.getStatus().setPlatform(platform);
        data.getChampion().setVersion(version);
        data.getChampion().setLocale(locale);
        data.getChampion().setIncludedData(includedData);
        return new Champion(data);
    }

    @SuppressWarnings("unchecked")
    @Get(Item.class)
    public Item getItem(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Item data = new com.merakianalytics.orianna.types.data.staticdata.Item();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setPlatform(platform);
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Item(data);
    }

    @Get(Languages.class)
    public Languages getLanguages(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final com.merakianalytics.orianna.types.data.staticdata.Languages data = new com.merakianalytics.orianna.types.data.staticdata.Languages();
        data.setPlatform(platform);
        return new Languages(data);
    }

    @Get(LanguageStrings.class)
    public LanguageStrings getLanguageStrings(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final com.merakianalytics.orianna.types.data.staticdata.LanguageStrings data = new com.merakianalytics.orianna.types.data.staticdata.LanguageStrings();
        data.setPlatform(platform);
        data.setVersion(version);
        data.setLocale(locale);
        return new LanguageStrings(data);
    }

    @SuppressWarnings("unchecked")
    @GetMany(Summoner.class)
    public CloseableIterator<Summoner> getManySummoner(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Long> ids = (Iterable<Long>)query.get("ids");
        final Iterable<Long> accountIds = (Iterable<Long>)query.get("accountIds");
        final Iterable<String> names = (Iterable<String>)query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names", accountIds, "accountIds");

        final Iterator<?> iterator;
        if(ids != null) {
            iterator = ids.iterator();
        } else if(accountIds != null) {
            iterator = accountIds.iterator();
        } else if(names != null) {
            iterator = names.iterator();
        } else {
            return null;
        }

        return CloseableIterators.from(new Iterator<Summoner>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Summoner next() {
                final com.merakianalytics.orianna.types.data.summoner.Summoner data = new com.merakianalytics.orianna.types.data.summoner.Summoner();
                data.setPlatform(platform);
                if(ids != null) {
                    data.setId((Long)iterator.next());
                } else if(accountIds != null) {
                    data.setAccountId((Long)iterator.next());
                } else if(names != null) {
                    data.setName((String)iterator.next());
                } else {
                    return null;
                }
                return new Summoner(data);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Get(Maps.class)
    public Maps getMaps(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final com.merakianalytics.orianna.types.data.staticdata.Maps data = new com.merakianalytics.orianna.types.data.staticdata.Maps();
        data.setPlatform(platform);
        data.setVersion(version);
        data.setLocale(locale);
        return new Maps(data);
    }

    @SuppressWarnings("unchecked")
    @Get(Mastery.class)
    public Mastery getMastery(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Mastery data = new com.merakianalytics.orianna.types.data.staticdata.Mastery();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setPlatform(platform);
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Mastery(data);
    }

    @Get(ProfileIcons.class)
    public ProfileIcons getProfileIcons(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");

        final com.merakianalytics.orianna.types.data.staticdata.ProfileIcons data = new com.merakianalytics.orianna.types.data.staticdata.ProfileIcons();
        data.setPlatform(platform);
        data.setVersion(version);
        data.setLocale(locale);
        return new ProfileIcons(data);
    }

    @Get(Realm.class)
    public Realm getRealm(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final com.merakianalytics.orianna.types.data.staticdata.Realm data = new com.merakianalytics.orianna.types.data.staticdata.Realm();
        data.setPlatform(platform);
        return new Realm(data);
    }

    @SuppressWarnings("unchecked")
    @Get(Rune.class)
    public Rune getRune(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.Rune data = new com.merakianalytics.orianna.types.data.staticdata.Rune();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setPlatform(platform);
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new Rune(data);
    }

    @Get(Summoner.class)
    public Summoner getSummoner(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Long id = (Long)query.get("id");
        final Long accountId = (Long)query.get("accountId");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name", accountId, "accountId");

        final com.merakianalytics.orianna.types.data.summoner.Summoner data = new com.merakianalytics.orianna.types.data.summoner.Summoner();
        data.setPlatform(platform);
        data.setName(name);
        data.setId(id == null ? 0 : id.longValue());
        data.setAccountId(accountId == null ? 0 : accountId.longValue());
        return new Summoner(data);
    }

    @SuppressWarnings("unchecked")
    @Get(SummonerSpell.class)
    public SummonerSpell getSummonerSpell(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Integer id = (Integer)query.get("id");
        final String name = (String)query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final com.merakianalytics.orianna.types.data.staticdata.SummonerSpell data = new com.merakianalytics.orianna.types.data.staticdata.SummonerSpell();
        data.setId(id == null ? 0 : id.intValue());
        data.setName(name);
        data.setPlatform(platform);
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return new SummonerSpell(data);
    }

    @Get(Versions.class)
    public Versions getVersions(final Map<String, Object> query, final PipelineContext context) {
        final Platform platform = (Platform)query.get("platform");
        Utilities.checkNotNull(platform, "platform");

        final com.merakianalytics.orianna.types.data.staticdata.Versions data = new com.merakianalytics.orianna.types.data.staticdata.Versions();
        data.setPlatform(platform);
        return new Versions(data);
    }
}
