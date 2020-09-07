package com.plp.service;

import com.plp.datapipeline.consumers.config.ConsumerConfig;
import com.plp.datapipeline.manager.ConsumerManager;
import com.plp.datapipeline.manager.LoaderManager;
import com.plp.datapipeline.manager.MapperManager;
import com.plp.datapipeline.mappers.config.MapperConfig;
import com.plp.dto.ConsumerResultDto;
import com.plp.dto.DataPipelineConfigDto;
import com.plp.exception.DataPipelineException;
import com.plp.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DataPipelineService {

    private final LoaderManager loaderManager;
    private final MapperManager mapperManager;
    private final ConsumerManager consumerManager;

    @Autowired
    public DataPipelineService(final LoaderManager loaderManager,
                               final MapperManager mapperManager,
                               final ConsumerManager consumerManager) {
        this.loaderManager = loaderManager;
        this.mapperManager = mapperManager;
        this.consumerManager = consumerManager;
    }

    public Map<String, ConsumerResultDto> processSingleData(@NonNull final DataPipelineConfigDto pipelineConfigDto,
                                                            @NonNull final Object data) {
        validatePipelineConfig(pipelineConfigDto);

        final Map<String, Object> loadedData =
                loaderManager.loadData(pipelineConfigDto.getLoaderConfig(), data);

        return mapAndConsume(pipelineConfigDto, loadedData);

    }

    protected Map<String, ConsumerResultDto> mapAndConsume(final DataPipelineConfigDto pipelineConfigDto,
                                                           final Map<String, Object> loadedData) {

        final Map<String, MapperConfig> mapperConfigs = pipelineConfigDto.getMapperConfigs();
        final Map<String, ConsumerConfig> consumerConfigs = pipelineConfigDto.getConsumerConfigs();
        final AtomicReference<Map<String, Object>> mappedData = new AtomicReference<>(loadedData);

        mapperConfigs.values().forEach(mapperConfig -> {
                    final Map<String, Object> mapped = this.mapperManager.mapData(mapperConfig, mappedData.get());
                    mappedData.set(mapped);
                }
        );

        final Map<String, ConsumerResultDto> consumerResults = new HashMap<>();
        consumerConfigs.forEach((key, consumerConfig) -> {
            final ConsumerResultDto result = this.consumerManager.consumeData(consumerConfig, mappedData.get());
            consumerResults.put(key, result);
        });

        return consumerResults;
    }

    protected void validatePipelineConfig(final DataPipelineConfigDto config) {
        if (config.getLoaderConfig() == null
                || config.getMapperConfigs() == null
                || config.getConsumerConfigs() == null) {
            throw new DataPipelineException(ErrorCodes.ERROR_MISSING_CONFIGURATION,
                    "Requires all of the following: loaderConfig, mapperConfig, consumerConfig");
        }
    }

}
