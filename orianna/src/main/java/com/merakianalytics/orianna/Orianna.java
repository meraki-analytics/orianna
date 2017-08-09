package com.merakianalytics.orianna;

import com.merakianalytics.datapipelines.DataPipeline;

public class Orianna {
    public static class Configuration {

    }

    public static class Settings {
        private DataPipeline pipeline;

        /**
         * @return the pipeline
         */
        public DataPipeline getPipeline() {
            return pipeline;
        }
    }

    private static Orianna instance = new Orianna();

    public static Settings getSettings() {
        return instance.settings;
    }

    private final Settings settings;;

    private Orianna() {
        this(new Configuration());
    }

    private Orianna(final Configuration configuration) {
        settings = new Settings();
    }
}
