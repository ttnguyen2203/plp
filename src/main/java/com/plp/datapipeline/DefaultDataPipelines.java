package com.plp.datapipeline;

import com.plp.constants.DataPipelineConstants;
import com.plp.datapipeline.consumers.IdentityConsumer;
import com.plp.datapipeline.consumers.config.IdentityConsumerConfig;
import com.plp.datapipeline.loaders.StringLoader;
import com.plp.datapipeline.loaders.config.StringLoaderConfig;
import com.plp.datapipeline.mappers.PigLatinMapper;
import com.plp.datapipeline.mappers.config.PigLatinMapperConfig;
import com.plp.dto.DataPipelineConfigDto;

import java.util.Collections;

public class DefaultDataPipelines {

    public static final DataPipelineConfigDto PIG_LATIN_PIPELINE;

    static {

        // pig latin
        PIG_LATIN_PIPELINE = new DataPipelineConfigDto();
        final StringLoaderConfig loaderConfig = new StringLoaderConfig();
        loaderConfig.setLoaderClass(StringLoader.class);
        loaderConfig.setLoadStringToKey(DataPipelineConstants.PIG_LATIN_RESULT_LOADER_KEY);

        final PigLatinMapperConfig mapperConfig = new PigLatinMapperConfig();
        mapperConfig.setMapperClass(PigLatinMapper.class);

        final IdentityConsumerConfig consumerConfig = new IdentityConsumerConfig();
        consumerConfig.setConsumerClass(IdentityConsumer.class);
        PIG_LATIN_PIPELINE.setLoaderConfig(loaderConfig);
        PIG_LATIN_PIPELINE.setMapperConfigs(Collections.singletonMap(
                "pigLatinMapper", mapperConfig));
        PIG_LATIN_PIPELINE.setConsumerConfigs(Collections.singletonMap(
                DataPipelineConstants.PIG_LATIN_RESULT_CONSUMER_KEY, consumerConfig));
    }
}
