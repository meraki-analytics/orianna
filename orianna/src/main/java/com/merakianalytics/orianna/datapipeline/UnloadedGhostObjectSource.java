package com.merakianalytics.orianna.datapipeline;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.sources.AbstractDataSource;
import com.merakianalytics.datapipelines.sources.Get;
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

public class UnloadedGhostObjectSource extends AbstractDataSource {
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
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String)query.get("version");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String)query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>)query.get("includedData");

        final ChampionData data = new ChampionData();
        data.getChampion().setId(id == null ? 0 : id.intValue());
        data.getStatus().setId(id == null ? 0 : id.intValue());
        data.getChampion().setName(name);
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
}
