package com.plp.dto;

import com.plp.datapipeline.consumers.config.ConsumerConfig;
import com.plp.datapipeline.loaders.config.LoaderConfig;
import com.plp.datapipeline.mappers.config.MapperConfig;

import java.util.Map;

public class DataPipelineConfigDto {

    private LoaderConfig loaderConfig;
    private Map<String, MapperConfig> mapperConfigs;
    private Map<String, ConsumerConfig> consumerConfigs;

    public LoaderConfig getLoaderConfig() {
        return loaderConfig;
    }

    public void setLoaderConfig(final LoaderConfig loaderConfig) {
        this.loaderConfig = loaderConfig;
    }

    public Map<String, MapperConfig> getMapperConfigs() {
        return mapperConfigs;
    }

    public void setMapperConfigs(final Map<String, MapperConfig> mapperConfigs) {
        this.mapperConfigs = mapperConfigs;
    }

    public Map<String, ConsumerConfig> getConsumerConfigs() {
        return consumerConfigs;
    }

    public void setConsumerConfigs(final Map<String, ConsumerConfig> consumerConfigs) {
        this.consumerConfigs = consumerConfigs;
    }
}
