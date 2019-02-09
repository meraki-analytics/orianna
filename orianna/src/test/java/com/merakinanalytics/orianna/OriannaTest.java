package com.merakinanalytics.orianna;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
}
