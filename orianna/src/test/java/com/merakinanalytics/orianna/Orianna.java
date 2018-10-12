package com.merakinanalytics.orianna;

import com.google.common.collect.ImmutableList;
import com.merakianalytics.orianna.datapipeline.PipelineConfiguration;
import com.merakianalytics.orianna.Orianna.Configuration;
import com.merakianalytics.orianna.datapipeline.common.expiration.ExpirationPeriod;
import com.merakianalytics.orianna.datapipeline.riotapi.RiotAPI;
import com.merakianalytics.orianna.types.common.Platform;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

class Orianna {

    public static class TestConfiguration {

        private Configuration pipeConfig;

        @BeforeEach
        void setup() {
            this.pipeConfig = new Configuration();
        }

        @Test
        void testDefaultVersionExpiration() {
            Assertions.assertNotNull(this.pipeConfig.getCurrentVersionExpiration());
            Assertions.assertEquals(this.pipeConfig.getCurrentVersionExpiration().getClass(), ExpirationPeriod.class);
        }

        @Test
        void testDefaultPipelineElements() {
            List<PipelineConfiguration.PipelineElementConfiguration> actualElements = this.pipeConfig.getPipeline().getElements();
            Assertions.assertTrue(actualElements.size() > 0);
        }

        @Test
        void testDefaultPipelineTransformers() {
            Set<PipelineConfiguration.TransformerConfiguration> actualTransformers = this.pipeConfig.getPipeline().getTransformers();
            Assertions.assertTrue(actualTransformers.size() > 0);
        }

        @Test
        void testDefaultPlatform() {
            Assertions.assertNull(this.pipeConfig.getDefaultPlatform());
        }

        @Test
        void testDefaultLocale() {
            Assertions.assertNull(this.pipeConfig.getDefaultLocale());
        }

        @Test
        void testSetCurrentVersionExpiration() {
            ExpirationPeriod newPeriod = ExpirationPeriod.create(24L, TimeUnit.HOURS);
            this.pipeConfig.setCurrentVersionExpiration(newPeriod);
            Assertions.assertEquals(this.pipeConfig.getCurrentVersionExpiration().getPeriod(), 24L);
            Assertions.assertEquals(this.pipeConfig.getCurrentVersionExpiration().getUnit(), TimeUnit.HOURS);
        }

        @Test
        void testSetDefaultLocale() {
            String locale = "en-US";
            this.pipeConfig.setDefaultLocale(locale);
            Assertions.assertEquals(this.pipeConfig.getDefaultLocale(), locale);
        }

        @Test
        void testSetDefaultPlatform() {
            Platform newPlatform = Platform.NORTH_AMERICA;
            this.pipeConfig.setDefaultPlatform(newPlatform);
            Assertions.assertEquals(this.pipeConfig.getDefaultPlatform(), newPlatform);
        }

        @Test
        void testSetPipeline() {
            PipelineConfiguration newPipe = new PipelineConfiguration();
            newPipe.setElements(
                    ImmutableList.of(PipelineConfiguration.PipelineElementConfiguration.defaultConfiguration(RiotAPI.class))
            );
            this.pipeConfig.setPipeline(newPipe);
            Assertions.assertEquals(this.pipeConfig.getPipeline().getElements().size(), 1);
            Assertions.assertEquals(this.pipeConfig.getPipeline().getElements().get(0).getClassName(), RiotAPI.class.getName());
        }

    }

}
