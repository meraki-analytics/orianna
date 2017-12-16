package com.merakianalytics.orianna.datapipeline;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merakianalytics.datapipelines.DataPipeline;
import com.merakianalytics.datapipelines.PipelineElement;
import com.merakianalytics.datapipelines.transformers.DataTransformer;

public class PipelineConfiguration {
    public static class PipelineElementConfiguration {
        public static PipelineElementConfiguration defaultConfiguration(final Class<? extends PipelineElement> clazz) {
            final PipelineElementConfiguration element = new PipelineElementConfiguration();
            element.setClassName(clazz.getCanonicalName());
            for(final Class<?> subClazz : clazz.getDeclaredClasses()) {
                // We're assuming there's a public static inner class named Configuration if the element needs a configuration.
                if(subClazz.getName().endsWith("Configuration")) {
                    element.setConfigClassName(subClazz.getName());

                    try {
                        final Object defaultConfig = subClazz.newInstance();
                        element.setConfig(new ObjectMapper().setSerializationInclusion(Include.NON_DEFAULT).valueToTree(defaultConfig));
                    } catch(InstantiationException | IllegalAccessException e) {
                        LOGGER.error("Failed to generate default configuration for " + clazz.getCanonicalName() + "!", e);
                    }
                }
            }
            return element;
        }

        private String className;
        private JsonNode config;
        private String configClassName;

        /**
         * @return the className
         */
        public String getClassName() {
            return className;
        }

        /**
         * @return the config
         */
        public JsonNode getConfig() {
            return config;
        }

        /**
         * @return the configClassName
         */
        public String getConfigClassName() {
            return configClassName;
        }

        /**
         * @param className
         *        the className to set
         */
        public void setClassName(final String className) {
            this.className = className;
        }

        /**
         * @param config
         *        the config to set
         */
        public void setConfig(final JsonNode config) {
            this.config = config;
        }

        /**
         * @param configClassName
         *        the configClassName to set
         */
        public void setConfigClassName(final String configClassName) {
            this.configClassName = configClassName;
        }
    }

    public static class TransformerConfiguration {
        public static TransformerConfiguration defaultConfiguration(final Class<? extends DataTransformer> clazz) {
            final TransformerConfiguration transformer = new TransformerConfiguration();
            transformer.setClassName(clazz.getCanonicalName());
            return transformer;
        }

        private String className;

        /**
         * @return the className
         */
        public String getClassName() {
            return className;
        }

        /**
         * @param className
         *        the className to set
         */
        public void setClassName(final String className) {
            this.className = className;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PipelineConfiguration.class);

    public static DataPipeline toPipeline(final PipelineConfiguration config) {
        final ObjectMapper mapper = new ObjectMapper();

        final Set<DataTransformer> transformers = new HashSet<>();
        if(config.getTransformers() != null) {
            for(final TransformerConfiguration tConfig : config.getTransformers()) {
                try {
                    final Class<?> clazz = Class.forName(tConfig.getClassName());
                    if(!DataTransformer.class.isAssignableFrom(clazz)) {
                        LOGGER.error("Class for name " + tConfig.getClassName() + " is not a DataTransformer! Skipping this transformer!");
                        continue;
                    }

                    final Constructor<?> defaultConstructor = clazz.getConstructor();
                    final DataTransformer transfomer = (DataTransformer)defaultConstructor.newInstance();
                    transformers.add(transfomer);
                } catch(final ClassNotFoundException e) {
                    LOGGER.error("Couldn't find class for name " + tConfig.getClassName() + " to create transformer! Skipping this transformer!", e);
                } catch(final NoSuchMethodException e) {
                    LOGGER.error("Class for name " + tConfig.getClassName() + " has no default no-arg constructor! Skipping this transformer!", e);
                } catch(final SecurityException e) {
                    LOGGER.error("Default no-arg constructor wasn't accessible in class for name " + tConfig.getClassName() + "! Skipping this transformer!",
                        e);
                } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.error("Couldn't instantiate transformer of class " + tConfig.getClassName() + "! Skipping this transformer!", e);
                }
            }
        }

        final List<PipelineElement> elements = new ArrayList<>(config.getElements().size());
        if(config.getElements() != null) {
            for(final PipelineElementConfiguration eConfig : config.getElements()) {
                Class<?> clazz;
                try {
                    clazz = Class.forName(eConfig.getClassName());
                } catch(final ClassNotFoundException e) {
                    LOGGER.error("Couldn't find class for name " + eConfig.getClassName() + " to create pipeline element! Skipping this pipeline element!", e);
                    continue;
                }
                if(!PipelineElement.class.isAssignableFrom(clazz)) {
                    LOGGER.error("Class for name " + eConfig.getClassName() + " is not a PipelineElement! Skipping this transformer!");
                    continue;
                }

                if(eConfig.getConfigClassName() != null) {
                    Class<?> configClazz;
                    try {
                        configClazz = Class.forName(eConfig.getConfigClassName());
                    } catch(final ClassNotFoundException e) {
                        LOGGER.error("Couldn't find class for name " + eConfig.getConfigClassName()
                            + " to create pipeline element configuration! Skipping this pipeline element!", e);
                        continue;
                    }

                    try {
                        final Constructor<?> constructor = clazz.getConstructor(configClazz);
                        final Object conf = mapper.treeToValue(eConfig.getConfig(), configClazz);
                        final PipelineElement element = (PipelineElement)constructor.newInstance(conf);
                        elements.add(element);
                    } catch(final NoSuchMethodException e) {
                        LOGGER.error("Class for name " + eConfig.getClassName() + " has no constructor which takes " + eConfig.getConfigClassName()
                            + "! Trying the default no-arg constructor instead!", e);
                    } catch(final SecurityException e) {
                        LOGGER
                            .error("Constructor which takes " + eConfig.getConfigClassName() + " wasn't accessible in class for name " + eConfig.getClassName()
                                + "! Trying the default no-arg constructor instead!", e);
                    } catch(final JsonProcessingException e) {
                        LOGGER.error("Failed to deserialize config of class " + eConfig.getConfigClassName() + " while building pipeline element of class "
                            + eConfig.getClassName() + "! Trying the default no-arg constructor instead!", e);
                    } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        LOGGER.error("Couldn't instantiate pipeline element of class " + eConfig.getClassName() + " using config of class "
                            + eConfig.getConfigClassName() + "! Trying the default no-arg constructor instead!", e);
                    }
                }

                try {
                    final Constructor<?> constructor = clazz.getConstructor();
                    final PipelineElement element = (PipelineElement)constructor.newInstance();
                    elements.add(element);
                } catch(final NoSuchMethodException e) {
                    LOGGER.error("Class for name " + eConfig.getClassName() + " has no default no-arg constructor! Skipping this pipeline element!", e);
                } catch(final SecurityException e) {
                    LOGGER.error(
                        "Default no-arg constructor wasn't accessible in class for name " + eConfig.getClassName() + "! Skipping this pipeline element!", e);
                } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.error("Couldn't instantiate pipeline element of class " + eConfig.getClassName() + "! Skipping this pipeline element!", e);
                }
            }
        }

        final DataPipeline pipeline = new DataPipeline(transformers, elements);
        return pipeline;
    }

    private List<PipelineElementConfiguration> elements = new ArrayList<>(1);
    private Set<TransformerConfiguration> transformers = new HashSet<>();

    /**
     * @return the elements
     */
    public List<PipelineElementConfiguration> getElements() {
        return elements;
    }

    /**
     * @return the transformers
     */
    public Set<TransformerConfiguration> getTransformers() {
        return transformers;
    }

    /**
     * @param elements
     *        the elements to set
     */
    public void setElements(final List<PipelineElementConfiguration> elements) {
        this.elements = elements;
    }

    /**
     * @param transformers
     *        the transformers to set
     */
    public void setTransformers(final Set<TransformerConfiguration> transformers) {
        this.transformers = transformers;
    }
}
