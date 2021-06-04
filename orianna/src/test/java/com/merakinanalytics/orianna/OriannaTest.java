package com.merakinanalytics.orianna;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Array;
import java.sql.Ref;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Predicate;
import com.merakianalytics.orianna.datapipeline.GhostLoader;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.RunePath;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.staticdata.*;
import com.merakianalytics.orianna.types.core.staticdata.Map;
import com.merakianalytics.orianna.types.data.CoreData;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.merakianalytics.datapipelines.PipelineElement;
import com.merakianalytics.datapipelines.transformers.DataTransformer;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.Orianna.Configuration;
import com.merakianalytics.orianna.Orianna.Settings;
import com.merakianalytics.orianna.datapipeline.PipelineConfiguration;
import com.merakianalytics.orianna.datapipeline.PipelineConfiguration.PipelineElementConfiguration;
import com.merakianalytics.orianna.datapipeline.PipelineConfiguration.TransformerConfiguration;
import com.merakianalytics.orianna.datapipeline.common.expiration.ExpirationPeriod;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI;
import com.merakianalytics.orianna.types.common.Platform;

public class OriannaTest {
    public static class ConfigurationTest {
        private Configuration pipeConfig;

        @Before
        public void setUp() {
            pipeConfig = new Configuration();
        }

        @Test
        public void testDefaultLocale() {
            assertNull(pipeConfig.getDefaultLocale());
        }

        @Test
        public void testDefaultPipelineElements() {
            final List<PipelineElementConfiguration> actualElements = pipeConfig.getPipeline().getElements();
            assertThat(actualElements.size(), greaterThan(0));
        }

        @Test
        public void testDefaultPipelineTransformers() {
            final Set<TransformerConfiguration> actualTransformers = pipeConfig.getPipeline().getTransformers();
            assertThat(actualTransformers.size(), greaterThan(0));
        }

        @Test
        public void testDefaultPlatform() {
            assertNull(pipeConfig.getDefaultPlatform());
        }

        @Test
        public void testDefaultVersionExpiration() {
            assertNotNull(pipeConfig.getCurrentVersionExpiration());
            assertEquals(pipeConfig.getCurrentVersionExpiration().getClass(), ExpirationPeriod.class);
        }

        @Test
        public void testSetCurrentVersionExpiration() {
            final ExpirationPeriod newPeriod = ExpirationPeriod.create(24L, TimeUnit.HOURS);
            pipeConfig.setCurrentVersionExpiration(newPeriod);
            assertEquals(pipeConfig.getCurrentVersionExpiration().getPeriod(), 24L);
            assertEquals(pipeConfig.getCurrentVersionExpiration().getUnit(), TimeUnit.HOURS);
        }

        @Test
        public void testSetDefaultLocale() {
            final String locale = "en-US";
            pipeConfig.setDefaultLocale(locale);
            assertEquals(pipeConfig.getDefaultLocale(), locale);
        }

        @Test
        public void testSetDefaultPlatform() {
            final Platform newPlatform = Platform.NORTH_AMERICA;
            pipeConfig.setDefaultPlatform(newPlatform);
            assertEquals(pipeConfig.getDefaultPlatform(), newPlatform);
        }

        @Test
        public void testSetPipeline() {
            final PipelineConfiguration newPipe = new PipelineConfiguration();
            newPipe.setElements(
                    ImmutableList.of(PipelineElementConfiguration.defaultConfiguration(RiotAPI.class)));
            pipeConfig.setPipeline(newPipe);
            assertEquals(pipeConfig.getPipeline().getElements().size(), 1);
            assertEquals(pipeConfig.getPipeline().getElements().get(0).getClassName(), RiotAPI.class.getName());
        }

    }

    public static class SettingsTest {
        private Settings settings;

        @Before
        public void setup() {
            settings = Orianna.getSettings();
        }

        // TODO: Mock Get Current Version. This will require response mocking.

        @Test
        public void testDefaultPipelineElements() {
            final List<PipelineElement> actualElements = settings.getPipeline().getElements();
            assertThat(actualElements.size(), greaterThan(0));
        }

        @Test
        public void testDefaultPipelineTransformers() {
            final Set<DataTransformer> actualTransformers = settings.getPipeline().getTransformers();
            assertThat(actualTransformers.size(), greaterThan(0));
        }

        @Test
        public void testGetDefaultLocale() {
            assertNull(settings.getDefaultLocale());
        }

        @Test
        public void testGetDefaultPlatform() {
            assertNull(settings.getDefaultPlatform());
        }
    }

    public static class CoreStaticDataTest {
        private static Region region = Region.NORTH_AMERICA;
        private static Champion annie = Champion.named("Annie").withRegion(region).get();
        private static Item long_sword = Item.named("Long Sword").withRegion(region).get();
        private static ReforgedRune reforgedRune = ReforgedRune.withId(8112).withRegion(region).get();

        @Test
        public void testChampion() {
            assertEquals(annie.getId(), 1);
        }

        @Test
        public void testChampions() {
            Champions champs = Champions.withRegion(region).get();
            Champion ann = null;
            for (Champion champion : champs) {
                if (champion.getId() == 1) {
                    ann = champion;
                    break;
                }
            }
            assertEquals(ann.getId(), 1);
        }

        @Test
        public void testChampionSpell() {
            ChampionSpell disintegrate = annie.getSpells().get(0);
            assertEquals(disintegrate.getName(),"Disintegrate");
        }

        @Test
        public void testChampionStats() {
            ChampionStats annieStats = annie.getStats();
            assertEquals(annieStats.getArmorPerLevel(),4.0,0.01);
        }

        @Test
        public void testImage() {
            Image image = annie.getImage();
            assertEquals(image.getFull(),"Annie.png");
        }

        @Test
        public void testItem() {
            assertEquals(long_sword.getId(),1036);
        }

        @Test
        public void testItems() {
            Items items = Items.withRegion(region).get();
            boolean found = false;
            for (Item i : items) {
                if (i.getId()==long_sword.getId()) {
                    assertEquals(i.getName(),long_sword.getName());
                    found = true;
                    break;
                }
            }
            assertEquals(found, true);
        }

        @Test
        public void testItemStats() {
            ItemStats itemStats = long_sword.getStats();
            assertEquals(itemStats.getAttackDamage(),10.0, 0.01);
        }

        @Test
        public void testLanguages() {
            Languages languages = Languages.withRegion(region).get();
            assertEquals(languages.get(0), "en_US");
        }

        @Test
        public void testPassive() {
            Passive passive = annie.getPassive();
            assertEquals(passive.getName(),"Pyromania");
        }

        @Test
        public void testPatch() {
            Patch patch = Patch.withRegion(region).get();
            assertEquals(patch.getRegion().toString(),"NORTH_AMERICA");
        }

        @Test
        public void testPatches() {
            Patches patches = Patches.withRegion(region).get();
            assertEquals(patches.get(0).getPlatform().toString(),"NORTH_AMERICA");
            assertEquals(patches.size()>10,true);
        }

        @Test
        public void testProfileIcon() {
            ProfileIcon profileIcon = ProfileIcon.withId(1).withRegion(region).get();
            assertEquals(profileIcon.getImage().getGroup(),"profileicon");
        }

        @Test
        public void testProfileIcons() {
            ProfileIcons profileIcons = ProfileIcons.withRegion(region).get();
            assertEquals(profileIcons.get(0).getImage().getFull(),"0.png");
        }

        @Test
        public void testRealm() {
            Realm realm = Realm.withRegion(region).get();
            assertEquals(realm.getLegacyMode(),"en_US");
        }

        @Test
        public void testRealms() {
            List<Region> regions = Arrays.asList(region,Region.BRAZIL);
            SearchableList<Realm> realms = Realms.withRegions(regions).get();
            assertEquals(realms.get(0).getLegacyMode(),"en_US");
        }

        @Test
        public void testRecommendedItems() {
            SearchableList<RecommendedItems> slri = annie.getRecommendedItems();
            RecommendedItems ri = slri.find(new Predicate<RecommendedItems>() {
                @Override
                public boolean apply(RecommendedItems i) {
                    return i.getMap().getId() == 12;
                }
            });
            assertEquals(ri.getTitle(),"AnnieARAM");
        }

        @Test
        public void testReforgedRune() {
            assertEquals(reforgedRune.getName(),"Electrocute");
        }

        @Test
        public void testReforgedRunePath() {
            assertEquals(reforgedRune.getPath().toString(),"DOMINATION");
        }

        @Test
        public void testReforgedRunes() {
            ReforgedRunes reforgedRunes = ReforgedRunes.withRegion(region).get();
            System.out.println(reforgedRunes);
            boolean found = false;
            for (ReforgedRune reforgedRune : reforgedRunes) {
                if (reforgedRune.getId() == 8112) {
                    assertEquals(reforgedRune.getName(),"Electrocute");
                    found = true;
                    break;
                }
            }
            assertEquals(found,true);
        }

        @Test
        public void testReforgedRuneSlot() {
            ReforgedRune reforgedRune2 = ReforgedRune.withId(8126).withRegion(region).get();
            assertEquals(reforgedRune.getSlot(),0);
            assertEquals(reforgedRune2.getSlot(),1);
        }

        @Test
        public void testReforgedRuneTree() {
            ReforgedRunes reforgedRunes = ReforgedRunes.withRegion(region).get();
            ReforgedRuneTree reforgedRuneTree = reforgedRunes.getTree();
            assertEquals(reforgedRuneTree.getDomination().getKey(),"Domination");
        }

        @Test
        public void testSkin() {
            Skin skin = annie.getSkins().get(0);
            assertEquals(skin.getName(),"default");
        }

        @Test
        public void testSpellVariables() {
            SearchableList<SpellVariables> spellVariables = annie.getSpells().get(0).getVariables();
            assertEquals(spellVariables.get(0).getKey(),"a1");
        }

        @Test
        public void testSprite() {
            Sprite sprite = annie.getImage().getSprite();
            assertEquals(sprite.getFull(),"champion0.png");
        }

        @Test
        public void testSummonerSpell() {
            SummonerSpell summonerSpell = SummonerSpell.withId(1).withRegion(region).get();
            assertEquals(summonerSpell.getName(),"Cleanse");
        }

        @Test
        public void testSummonerSpells() {
            SummonerSpells summonerSpells = SummonerSpells.withRegion(region).get();
            boolean found = false;
            for (SummonerSpell ss : summonerSpells) {
                if (ss.getId() == 21) {
                    assertEquals(ss.getName(),"Barrier");
                    found = true;
                    break;
                }
            }
            assertEquals(found,true);
        }

        @Test
        public void testVersions() {
            Versions versions = Versions.withRegion(region).get();
            assertEquals(versions.size()>10,true);
        }
    }
}
