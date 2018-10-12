package com.merakinanalytics.orianna;

import com.merakianalytics.datapipelines.PipelineElement;
import com.merakianalytics.datapipelines.transformers.DataTransformer;
import com.merakianalytics.orianna.Orianna;
import com.google.common.collect.ImmutableList;
import com.merakianalytics.orianna.datapipeline.PipelineConfiguration;
import com.merakianalytics.orianna.Orianna.Configuration;
import com.merakianalytics.orianna.Orianna.Settings;
import com.merakianalytics.orianna.datapipeline.common.expiration.ExpirationPeriod;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI;
import com.merakianalytics.orianna.types.common.Platform;

import org.junit.jupiter.api.*;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class OriannaTest {

    @Nested
    public static class ConfigurationTest {

        private Configuration pipeConfig;

        @BeforeEach
        public void setup() {
            this.pipeConfig = new Configuration();
        }

        @Test
        public void testDefaultVersionExpiration() {
            Assertions.assertNotNull(this.pipeConfig.getCurrentVersionExpiration());
            Assertions.assertEquals(this.pipeConfig.getCurrentVersionExpiration().getClass(), ExpirationPeriod.class);
        }

        @Test
        public void testDefaultPipelineElements() {
            List<PipelineConfiguration.PipelineElementConfiguration> actualElements = this.pipeConfig.getPipeline().getElements();
            Assertions.assertTrue(actualElements.size() > 0);
        }

        @Test
        public void testDefaultPipelineTransformers() {
            Set<PipelineConfiguration.TransformerConfiguration> actualTransformers = this.pipeConfig.getPipeline().getTransformers();
            Assertions.assertTrue(actualTransformers.size() > 0);
        }

        @Test
        public void testDefaultPlatform() {
            Assertions.assertNull(this.pipeConfig.getDefaultPlatform());
        }

        @Test
        public void testDefaultLocale() {
            Assertions.assertNull(this.pipeConfig.getDefaultLocale());
        }

        @Test
        public void testSetCurrentVersionExpiration() {
            ExpirationPeriod newPeriod = ExpirationPeriod.create(24L, TimeUnit.HOURS);
            this.pipeConfig.setCurrentVersionExpiration(newPeriod);
            Assertions.assertEquals(this.pipeConfig.getCurrentVersionExpiration().getPeriod(), 24L);
            Assertions.assertEquals(this.pipeConfig.getCurrentVersionExpiration().getUnit(), TimeUnit.HOURS);
        }

        @Test
        public void testSetDefaultLocale() {
            String locale = "en-US";
            this.pipeConfig.setDefaultLocale(locale);
            Assertions.assertEquals(this.pipeConfig.getDefaultLocale(), locale);
        }

        @Test
        public void testSetDefaultPlatform() {
            Platform newPlatform = Platform.NORTH_AMERICA;
            this.pipeConfig.setDefaultPlatform(newPlatform);
            Assertions.assertEquals(this.pipeConfig.getDefaultPlatform(), newPlatform);
        }

        @Test
        public void testSetPipeline() {
            PipelineConfiguration newPipe = new PipelineConfiguration();
            newPipe.setElements(
                    ImmutableList.of(PipelineConfiguration.PipelineElementConfiguration.defaultConfiguration(RiotAPI.class))
            );
            this.pipeConfig.setPipeline(newPipe);
            Assertions.assertEquals(this.pipeConfig.getPipeline().getElements().size(), 1);
            Assertions.assertEquals(this.pipeConfig.getPipeline().getElements().get(0).getClassName(), RiotAPI.class.getName());
        }

    }

    public static class SettingsTest {

        private Settings settings;

        @BeforeEach
        public void setup() {
            this.settings = Orianna.getSettings();
        }

        //TODO: Mock Get Current Version. This will require response mocking.

        @Test
        public void testGetDefaultLocale() {
            Assertions.assertNull(this.settings.getDefaultLocale());
        }

        @Test
        public void testGetDefaultPlatform() {
            Assertions.assertNull(this.settings.getDefaultPlatform());
        }

        @Test
        public void testDefaultPipelineElements() {
            List<PipelineElement> actualElements = this.settings.getPipeline().getElements();
            Assertions.assertTrue(actualElements.size() > 0);
        }

        @Test
        public void testDefaultPipelineTransformers() {
            Set<DataTransformer> actualTransformers = this.settings.getPipeline().getTransformers();
            Assertions.assertTrue(actualTransformers.size() > 0);
        }

    }

}
