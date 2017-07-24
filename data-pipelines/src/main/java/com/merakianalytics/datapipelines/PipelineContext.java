package com.merakianalytics.datapipelines;

public class PipelineContext {
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
