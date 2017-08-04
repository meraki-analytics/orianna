package com.merakianalytics.datapipelines;

import java.util.concurrent.ConcurrentHashMap;

public class PipelineContext extends ConcurrentHashMap<String, Object> {
    private static final long serialVersionUID = -4510900737770535444L;
    private DataPipeline pipeline;

    /**
     * @return the pipeline
     */
    public DataPipeline getPipeline() {
        return pipeline;
    }

    /**
     * @param pipeline
     *        the pipeline to set
     */
    public void setPipeline(final DataPipeline pipeline) {
        this.pipeline = pipeline;
    }
}
