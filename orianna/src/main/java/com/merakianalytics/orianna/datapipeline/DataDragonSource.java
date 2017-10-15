package com.merakianalytics.orianna.datapipeline;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.*;
import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.iterators.*;
import com.merakianalytics.datapipelines.sources.*;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.datapipeline.common.Utilities;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.dto.DataObject;
import com.merakianalytics.orianna.types.dto.staticdata.*;

import java.io.IOException;
import java.util.*;

public class DataDragonSource extends AbstractDataSource
{
    
    private String get(String file, String version, String locale)
    {
        String host = "http://ddragon.leagueoflegends.com";
        String part = "";
        
        if (version != null && locale != null)
        {
            part = "/cdn/" + version + "/data/" + locale + "/";
        }
        String finalUrl = host + part + file + ".json";
        return Orianna.getSettings().getPipeline().get(String.class, ImmutableMap.<String, Object>of("url", finalUrl));
    }
    
    private String filterToTags(String json, Set<String> includedData)
    {
        if (includedData.contains("all"))
        {
            return json;
        }
        
        Set<String> defaultChampionTags      = ImmutableSet.of("title", "id", /*"key",*/ "name");
        Set<String> defaultItemTags          = ImmutableSet.of("plaintext", "description", "id", "name");
        Set<String> defaultMasteryTags       = ImmutableSet.of("description", "id", "name");
        Set<String> defaultRuneTags          = ImmutableSet.of("description", "rune", "id", "name");
        Set<String> defaultSummonerSpellTags = ImmutableSet.of("name", /*"key",*/ "summonerLevel", "id", "description");
        
        Set<String> okTags = new ImmutableSet.Builder<String>().addAll(defaultChampionTags)
                                                               .addAll(defaultItemTags)
                                                               .addAll(defaultMasteryTags)
                                                               .addAll(defaultRuneTags)
                                                               .addAll(defaultSummonerSpellTags)
                                                               .addAll(includedData)
                                                               .build();
        
        try
        {
            JsonNode parent = new ObjectMapper().readTree(json);
            JsonNode data   = parent.get("data");
            for (JsonNode child : data)
            {
                if (child instanceof ObjectNode)
                {
                    ObjectNode childObject = (ObjectNode) child;
                    
                    if (childObject.get("key") != null)
                    {
                        childObject.set("id", childObject.get("key"));
                    }
                    
                    childObject.retain(okTags);
                }
            }
            return parent.toString();
        } catch (IOException e)
        {
            e.printStackTrace();
            return json;
        }
    }
    
    private String filterToField(String json, String field, String value)
    {
        try
        {
            JsonNode parent = new ObjectMapper().readTree(json).get("data");
            for (Iterator<String> it = parent.fieldNames(); it.hasNext(); )
            {
                String   fieldName = it.next();
                JsonNode child     = parent.get(fieldName);
                
                if (child instanceof ObjectNode)
                {
                    ObjectNode childObject = (ObjectNode) child;
                    if ("USE_PARENT".equalsIgnoreCase(field) && fieldName.equalsIgnoreCase(value))
                    {
                        return childObject.toString();
                    }
                    
                    if (child.has(field) && childObject.get(field).asText().equalsIgnoreCase(value))
                    {
                        return childObject.toString();
                    }
                }
            }
            return json;
        } catch (IOException e)
        {
            e.printStackTrace();
            return json;
        }
        
    }
    
    private String swapKeyAndId(String json)
    {
        try
        {
            JsonNode   parent = new ObjectMapper().readTree(json);
            ObjectNode data   = (ObjectNode) parent.get("data");
            
            for (JsonNode child : data)
            {
                if (child instanceof ObjectNode)
                {
                    ObjectNode childObject = (ObjectNode) child;
                    childObject.set("id", childObject.get("key"));
                }
            }
            
            return parent.toString();
        } catch (IOException e)
        {
            e.printStackTrace();
            return json;
        }
    }
    
    private String makeById(String json)
    {
        try
        {
            JsonNode dataClone = new ObjectMapper().readTree(json).get("data");
            
            JsonNode   parent = new ObjectMapper().readTree(json);
            ObjectNode data   = (ObjectNode) parent.get("data");
            
            for (JsonNode child : dataClone)
            {
                if (child instanceof ObjectNode)
                {
                    ObjectNode childObject = (ObjectNode) child;
                    childObject.set("id", childObject.get("key"));
                    data.set(childObject.get("key").asText(), child);
                }
            }
            
            for (Iterator<String> iterator = dataClone.fieldNames(); iterator.hasNext(); )
            {
                String field = iterator.next();
                data.remove(field);
            }
            
            return parent.toString();
        } catch (IOException e)
        {
            e.printStackTrace();
            return json;
        }
    }
    
    @Get(Versions.class)
    public Versions getVersions(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        
        String content = get("/api/versions", null, null);
        
        Versions data = DataObject.fromJSON(Versions.class, content);
        data.setPlatform(platform.getTag());
        
        return data;
    }
    
    @Get(SummonerSpellList.class)
    public SummonerSpellList getSummonerSpellList(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String content  = get("summoner", version, locale);
        String filtered = filterToTags(content, includedData);
        
        SummonerSpellList data = DataObject.fromJSON(SummonerSpellList.class, filtered);
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for (final SummonerSpell spell : data.getData().values())
        {
            spell.setPlatform(platform.getTag());
            spell.setVersion(data.getVersion());
            spell.setLocale(locale);
            spell.setIncludedData(includedData);
        }
        return data;
    }
    
    @SuppressWarnings("unchecked")
    @Get(SummonerSpell.class)
    public SummonerSpell getSummonerSpell(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        final Number   id       = (Number) query.get("id");
        Utilities.checkNotNull(platform, "platform", id, "id");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String content       = get("summoner", version, locale);
        String filteredToTag = filterToTags(content, includedData);
        String filteredToId  = filterToField(filteredToTag, "id", id.toString());
        
        SummonerSpell data = DataObject.fromJSON(SummonerSpell.class, filteredToId);
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return data;
    }
    
    @SuppressWarnings("unchecked")
    @Get(RuneList.class)
    public RuneList getRuneList(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String      version      = (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String         content        = get("rune", version, locale);
        String         filteredToTags = filterToTags(content, includedData);
        final RuneList data           = DataObject.fromJSON(RuneList.class, filteredToTags);
        
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for (final Rune rune : data.getData().values())
        {
            rune.setPlatform(platform.getTag());
            rune.setVersion(data.getVersion());
            rune.setLocale(locale);
            rune.setIncludedData(includedData);
        }
        return data;
    }
    
    @SuppressWarnings("unchecked")
    @Get(Rune.class)
    public Rune getRune(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        final Number   id       = (Number) query.get("id");
        Utilities.checkNotNull(platform, "platform", id, "id");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String     content        = get("rune", version, locale);
        String     filteredToTags = filterToTags(content, includedData);
        String     filteredToId   = filterToField(filteredToTags, "USE_PARENT", id.toString());
        final Rune data           = DataObject.fromJSON(Rune.class, filteredToId);
        
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return data;
    }
    
    @Get(Realm.class)
    public Realm getRealm(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        
        String content = get("/realms/" + platform.getTag().replaceAll("[0-9]", ""), null, null);
        Realm  data    = DataObject.fromJSON(Realm.class, content);
        
        data.setPlatform(platform.getTag());
        return data;
    }
    
    @Get(ProfileIconData.class)
    public ProfileIconData getProfileIconData(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String locale  = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        
        String          content = get("profileicon", version, locale);
        ProfileIconData data    = DataObject.fromJSON(ProfileIconData.class, content);
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        for (final ProfileIconDetails icon : data.getData().values())
        {
            icon.setPlatform(platform.getTag());
            icon.setVersion(data.getVersion());
            icon.setLocale(locale);
        }
        return data;
    }
    
    @SuppressWarnings("unchecked")
    @Get(MasteryList.class)
    public MasteryList getMasteryList(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String      content        = get("mastery", version, locale);
        String      filteredToTags = filterToTags(content, includedData);
        MasteryList data           = DataObject.fromJSON(MasteryList.class, filteredToTags);
        
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for (final Mastery mastery : data.getData().values())
        {
            mastery.setPlatform(platform.getTag());
            mastery.setVersion(data.getVersion());
            mastery.setLocale(locale);
            mastery.setIncludedData(includedData);
        }
        return data;
    }
    
    
    @SuppressWarnings("unchecked")
    @Get(Mastery.class)
    public Mastery getMastery(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        final Number   id       = (Number) query.get("id");
        Utilities.checkNotNull(platform, "platform", id, "id");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String        content        = get("mastery", version, locale);
        String        filteredToTags = filterToTags(content, includedData);
        String        filteredToId   = filterToField(filteredToTags, "USE_PARENT", id.toString());
        final Mastery data           = DataObject.fromJSON(Mastery.class, filteredToId);
        
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return data;
    }
    
    @Get(MapData.class)
    public MapData getMapData(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String locale  = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        
        String  content = get("map", version, locale);
        MapData data    = DataObject.fromJSON(MapData.class, content);
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        for (final MapDetails map : data.getData().values())
        {
            map.setPlatform(platform.getTag());
            map.setVersion(data.getVersion());
            map.setLocale(locale);
        }
        return data;
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(Versions.class)
    public CloseableIterator<Versions> getManyVersions(final Map<String, Object> query, final PipelineContext context)
    {
        final Iterable<Platform> platforms = (Iterable<Platform>) query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");
        
        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<Versions>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public Versions next()
            {
                final Platform platform = iterator.next();
                Utilities.checkNotNull(platform, "platform");
                
                String content = get("/api/versions", null, null);
                
                Versions data = DataObject.fromJSON(Versions.class, content);
                data.setPlatform(platform.getTag());
                
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    @GetMany(SummonerSpellList.class)
    public CloseableIterator<SummonerSpellList> getManySummonerSpellList(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<String> versions = (Iterable<String>) query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<SummonerSpellList>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public SummonerSpellList next()
            {
                final String version = iterator.next();
                
                String content  = get("summoner", version, locale);
                String filtered = filterToTags(content, includedData);
                
                final SummonerSpellList data = DataObject.fromJSON(SummonerSpellList.class, filtered);
                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                data.setIncludedData(includedData);
                for (final SummonerSpell spell : data.getData().values())
                {
                    spell.setPlatform(platform.getTag());
                    spell.setVersion(data.getVersion());
                    spell.setLocale(locale);
                    spell.setIncludedData(includedData);
                }
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(SummonerSpell.class)
    public CloseableIterator<SummonerSpell> getManySummonerSpell(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<Number> ids      = (Iterable<Number>) query.get("ids");
        Utilities.checkNotNull(platform, "platform", ids, "ids");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String content  = get("summoner", version, locale);
        String filtered = filterToTags(content, includedData);
        
        final SummonerSpellList data = DataObject.fromJSON(SummonerSpellList.class, filtered);
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for (final SummonerSpell spell : data.getData().values())
        {
            spell.setPlatform(platform.getTag());
            spell.setVersion(data.getVersion());
            spell.setLocale(locale);
            spell.setIncludedData(includedData);
        }
        
        final Iterator<Number> iterator = ids.iterator();
        return CloseableIterators.from(new Iterator<SummonerSpell>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public SummonerSpell next()
            {
                final Number id = iterator.next();
                return data.getData().get(id.toString());
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(RuneList.class)
    public CloseableIterator<RuneList> getManyRuneList(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<String> versions = (Iterable<String>) query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<RuneList>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public RuneList next()
            {
                final String version = iterator.next();
                
                String         content        = get("rune", version, locale);
                String         filteredToTags = filterToTags(content, includedData);
                final RuneList data           = DataObject.fromJSON(RuneList.class, filteredToTags);
                
                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                data.setIncludedData(includedData);
                for (final Rune rune : data.getData().values())
                {
                    rune.setPlatform(platform.getTag());
                    rune.setVersion(data.getVersion());
                    rune.setLocale(locale);
                    rune.setIncludedData(includedData);
                }
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(Rune.class)
    public CloseableIterator<Rune> getManyRune(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<Number> ids      = (Iterable<Number>) query.get("ids");
        Utilities.checkNotNull(platform, "platform", ids, "ids");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        
        String         content        = get("rune", version, locale);
        String         filteredToTags = filterToTags(content, includedData);
        final RuneList data           = DataObject.fromJSON(RuneList.class, filteredToTags);
        
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for (final Rune rune : data.getData().values())
        {
            rune.setPlatform(platform.getTag());
            rune.setVersion(data.getVersion());
            rune.setLocale(locale);
            rune.setIncludedData(includedData);
        }
        
        final Iterator<Number> iterator = ids.iterator();
        return CloseableIterators.from(new Iterator<Rune>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public Rune next()
            {
                final Number id = iterator.next();
                return data.getData().get(id.toString());
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(Realm.class)
    public CloseableIterator<Realm> getManyRealm(final Map<String, Object> query, final PipelineContext context)
    {
        final Iterable<Platform> platforms = (Iterable<Platform>) query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");
        
        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<Realm>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public Realm next()
            {
                final Platform platform = iterator.next();
                
                String content = get("/realms/" + platform.getTag().replaceAll("[0-9]", ""), null, null);
                Realm  data    = DataObject.fromJSON(Realm.class, content);
                
                data.setPlatform(platform.getTag());
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(ProfileIconData.class)
    public CloseableIterator<ProfileIconData> getManyProfileIconData(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<String> versions = (Iterable<String>) query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        
        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<ProfileIconData>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public ProfileIconData next()
            {
                final String version = iterator.next();
                
                String          content = get("profileicon", version, locale);
                ProfileIconData data    = DataObject.fromJSON(ProfileIconData.class, content);
                
                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                for (final ProfileIconDetails icon : data.getData().values())
                {
                    icon.setPlatform(platform.getTag());
                    icon.setVersion(data.getVersion());
                    icon.setLocale(locale);
                }
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(MasteryList.class)
    public CloseableIterator<MasteryList> getManyMasteryList(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<String> versions = (Iterable<String>) query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<MasteryList>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public MasteryList next()
            {
                final String version = iterator.next();
                
                String      content        = get("mastery", version, locale);
                String      filteredToTags = filterToTags(content, includedData);
                MasteryList data           = DataObject.fromJSON(MasteryList.class, filteredToTags);
                
                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                data.setIncludedData(includedData);
                for (final Mastery mastery : data.getData().values())
                {
                    mastery.setPlatform(platform.getTag());
                    mastery.setVersion(data.getVersion());
                    mastery.setLocale(locale);
                    mastery.setIncludedData(includedData);
                }
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(Mastery.class)
    public CloseableIterator<Mastery> getManyMastery(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<Number> ids      = (Iterable<Number>) query.get("ids");
        Utilities.checkNotNull(platform, "platform", ids, "ids");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String            content        = get("mastery", version, locale);
        String            filteredToTags = filterToTags(content, includedData);
        final MasteryList data           = DataObject.fromJSON(MasteryList.class, filteredToTags);
        
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for (final Mastery mastery : data.getData().values())
        {
            mastery.setPlatform(platform.getTag());
            mastery.setVersion(data.getVersion());
            mastery.setLocale(locale);
            mastery.setIncludedData(includedData);
        }
        
        final Iterator<Number> iterator = ids.iterator();
        return CloseableIterators.from(new Iterator<Mastery>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public Mastery next()
            {
                final Number id = iterator.next();
                return data.getData().get(id.toString());
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(MapData.class)
    public CloseableIterator<MapData> getManyMapData(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<String> versions = (Iterable<String>) query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String locale = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        
        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<MapData>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public MapData next()
            {
                final String version = iterator.next();
                
                String  content = get("map", version, locale);
                MapData data    = DataObject.fromJSON(MapData.class, content);
                
                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                for (final MapDetails map : data.getData().values())
                {
                    map.setPlatform(platform.getTag());
                    map.setVersion(data.getVersion());
                    map.setLocale(locale);
                }
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    @GetMany(LanguageStrings.class)
    public CloseableIterator<LanguageStrings> getManyLanguageStrings(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<String> locales  = (Iterable<String>) query.get("locales");
        Utilities.checkNotNull(platform, "platform", locales, "locales");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        
        
        final Iterator<String> iterator = locales.iterator();
        return CloseableIterators.from(new Iterator<LanguageStrings>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public LanguageStrings next()
            {
                final String locale = iterator.next();
                
                String          content = get("language", version, locale);
                LanguageStrings data    = DataObject.fromJSON(LanguageStrings.class, content);
                
                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    @GetMany(Languages.class)
    public CloseableIterator<Languages> getManyLanguages(final Map<String, Object> query, final PipelineContext context)
    {
        final Iterable<Platform> platforms = (Iterable<Platform>) query.get("platforms");
        Utilities.checkNotNull(platforms, "platforms");
        
        final Iterator<Platform> iterator = platforms.iterator();
        return CloseableIterators.from(new Iterator<Languages>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public Languages next()
            {
                final Platform platform = iterator.next();
                
                String    content = get("/cdn/languages", null, null);
                Languages data    = DataObject.fromJSON(Languages.class, content);
                
                data.setPlatform(platform.getTag());
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    @GetMany(ItemList.class)
    public CloseableIterator<ItemList> getManyItemList(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<String> versions = (Iterable<String>) query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<ItemList>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public ItemList next()
            {
                final String version = iterator.next();
                
                
                String   content        = get("item", version, locale);
                String   filteredToTags = filterToTags(content, includedData);
                ItemList data           = DataObject.fromJSON(ItemList.class, filteredToTags);
                
                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                data.setIncludedData(includedData);
                for (final Item item : data.getData().values())
                {
                    item.setPlatform(platform.getTag());
                    item.setVersion(data.getVersion());
                    item.setLocale(locale);
                    item.setIncludedData(includedData);
                }
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    @GetMany(Item.class)
    public CloseableIterator<Item> getManyItem(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<Number> ids      = (Iterable<Number>) query.get("ids");
        Utilities.checkNotNull(platform, "platform", ids, "ids");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String         content        = get("item", version, locale);
        String         filteredToTags = filterToTags(content, includedData);
        final ItemList data           = DataObject.fromJSON(ItemList.class, filteredToTags);
        
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for (final Item item : data.getData().values())
        {
            item.setPlatform(platform.getTag());
            item.setVersion(data.getVersion());
            item.setLocale(locale);
            item.setIncludedData(includedData);
        }
        
        final Iterator<Number> iterator = ids.iterator();
        return CloseableIterators.from(new Iterator<Item>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public Item next()
            {
                final Number id = iterator.next();
                return data.getData().get(id.toString());
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    @GetMany(ChampionList.class)
    public CloseableIterator<ChampionList> getManyChampionList(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform         platform = (Platform) query.get("platform");
        final Iterable<String> versions = (Iterable<String>) query.get("versions");
        Utilities.checkNotNull(platform, "platform", versions, "versions");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        final Boolean     dataById     = query.get("dataById") == null ? Boolean.FALSE : (Boolean) query.get("dataById");
        
        final Multimap<String, String> parameters = HashMultimap.create();
        parameters.put("locale", locale);
        parameters.putAll("tags", includedData);
        parameters.put("dataById", dataById.toString());
        
        final Iterator<String> iterator = versions.iterator();
        return CloseableIterators.from(new Iterator<ChampionList>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public ChampionList next()
            {
                final String version = iterator.next();
                
                
                String       content        = swapKeyAndId(get("championFull", version, locale));
                String       filteredToTags = filterToTags(dataById ? makeById(content) : content, includedData);
                ChampionList data           = DataObject.fromJSON(ChampionList.class, filteredToTags);
                
                data.setPlatform(platform.getTag());
                data.setLocale(locale);
                data.setIncludedData(includedData);
                for (final Champion champion : data.getData().values())
                {
                    champion.setPlatform(platform.getTag());
                    champion.setVersion(data.getVersion());
                    champion.setLocale(locale);
                    champion.setIncludedData(includedData);
                }
                return data;
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    
    private static String getCurrentVersion(final Platform platform, final PipelineContext context)
    {
        final Realm realm = context.getPipeline().get(Realm.class, ImmutableMap.<String, Object>of("platform", platform));
        return realm.getV();
    }
    
    @SuppressWarnings("unchecked")
    @GetMany(Champion.class)
    public CloseableIterator<Champion> getManyChampion(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Iterable<Number> ids   = (Iterable<Number>) query.get("ids");
        final Iterable<String> names = (Iterable<String>) query.get("names");
        Utilities.checkAtLeastOneNotNull(ids, "ids", names, "names");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String content        = get("championFull", version, locale);
        String filteredToTags = filterToTags(makeById(content), includedData);
        
        final ChampionList data = DataObject.fromJSON(ChampionList.class, filteredToTags);
        
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        final Map<String, Champion> byName = ids == null ? new HashMap<String, Champion>() : null;
        for (final Champion champion : data.getData().values())
        {
            champion.setPlatform(platform.getTag());
            champion.setVersion(data.getVersion());
            champion.setLocale(locale);
            champion.setIncludedData(includedData);
            
            if (ids == null)
            {
                byName.put(champion.getName(), champion);
            }
        }
        
        final Iterator<?> iterator = ids == null ? names.iterator() : ids.iterator();
        return CloseableIterators.from(new Iterator<Champion>()
        {
            @Override
            public boolean hasNext()
            {
                return iterator.hasNext();
            }
            
            @Override
            public Champion next()
            {
                if (ids != null)
                {
                    final Number id = (Number) iterator.next();
                    return data.getData().get(id.toString());
                } else
                {
                    final String name = (String) iterator.next();
                    return byName.get(name);
                }
            }
            
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        });
    }
    
    
    @Get(LanguageStrings.class)
    public LanguageStrings getLanguageStrings(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String version = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String locale  = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        
        String          content = get("language", version, locale);
        LanguageStrings data    = DataObject.fromJSON(LanguageStrings.class, content);
        
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        return data;
    }
    
    
    @Get(Languages.class)
    public Languages getLanguages(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        
        String    content = get("/cdn/languages", null, null);
        Languages data    = DataObject.fromJSON(Languages.class, content);
        
        data.setPlatform(platform.getTag());
        return data;
    }
    
    @SuppressWarnings("unchecked")
    @Get(ItemList.class)
    public ItemList getItemList(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String   content        = get("item", version, locale);
        String   filteredToTags = filterToTags(content, includedData);
        ItemList data           = DataObject.fromJSON(ItemList.class, filteredToTags);
        
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for (final Item item : data.getData().values())
        {
            item.setPlatform(platform.getTag());
            item.setVersion(data.getVersion());
            item.setLocale(locale);
            item.setIncludedData(includedData);
        }
        return data;
    }
    
    @SuppressWarnings("unchecked")
    @Get(Item.class)
    public Item getItem(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        final Number   id       = (Number) query.get("id");
        Utilities.checkNotNull(platform, "platform", id, "id");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String content        = get("item", version, locale);
        String filteredToTags = filterToTags(content, includedData);
        String filteredToId   = filterToField(filteredToTags, "USE_PARENT", id.toString());
        Item   data           = DataObject.fromJSON(Item.class, filteredToId);
        
        if (data.getId() == 0)
        {
            return null;
        }
        
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return data;
    }
    
    @SuppressWarnings("unchecked")
    @Get(Champion.class)
    public Champion getChampion(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final Number id   = (Number) query.get("id");
        final String name = (String) query.get("name");
        Utilities.checkAtLeastOneNotNull(id, "id", name, "name");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        
        String content        = get("championFull", version, locale);
        String filteredToTags = filterToTags(makeById(content), includedData);
        String filteredToId   = id == null ? filterToField(filteredToTags, "name", name) : filterToField(filteredToTags, "id", id.toString());
        
        Champion data = DataObject.fromJSON(Champion.class, filteredToId);
        if (data.getId() == 0)
        {
            return null;
        }
        
        data.setPlatform(platform.getTag());
        data.setVersion(version);
        data.setLocale(locale);
        data.setIncludedData(includedData);
        return data;
    }
    
    
    @SuppressWarnings("unchecked")
    @Get(ChampionList.class)
    public ChampionList getChampionList(final Map<String, Object> query, final PipelineContext context)
    {
        final Platform platform = (Platform) query.get("platform");
        Utilities.checkNotNull(platform, "platform");
        final String      version      = query.get("version") == null ? getCurrentVersion(platform, context) : (String) query.get("version");
        final String      locale       = query.get("locale") == null ? platform.getDefaultLocale() : (String) query.get("locale");
        final Set<String> includedData = query.get("includedData") == null ? ImmutableSet.of("all") : (Set<String>) query.get("includedData");
        final Boolean     dataById     = query.get("dataById") == null ? Boolean.FALSE : (Boolean) query.get("dataById");
        
        String       content        = swapKeyAndId(get("championFull", version, locale));
        String       filteredToTags = filterToTags(dataById ? makeById(content) : content, includedData);
        ChampionList data           = DataObject.fromJSON(ChampionList.class, filteredToTags);
        
        
        data.setPlatform(platform.getTag());
        data.setLocale(locale);
        data.setIncludedData(includedData);
        for (final Champion champion : data.getData().values())
        {
            champion.setPlatform(platform.getTag());
            champion.setVersion(data.getVersion());
            champion.setLocale(locale);
            champion.setIncludedData(includedData);
        }
        return data;
    }
    
}
