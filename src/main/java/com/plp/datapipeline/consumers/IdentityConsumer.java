package com.plp.datapipeline.consumers;

import com.plp.datapipeline.consumers.config.IdentityConsumerConfig;
import com.plp.dto.ConsumerResultDto;

import java.util.Map;

/**
 * This consumer returns the mapped data as is
 */
public class IdentityConsumer extends IConsumer<IdentityConsumerConfig> {

    @Override
    public void init(final IdentityConsumerConfig config) {
        //no additional settings
    }

    @Override
    public ConsumerResultDto consume(final Map<String, Object> data) {
        final ConsumerResultDto dto = new ConsumerResultDto();
        dto.setConsumedData(data);
        dto.setSuccess(true);
        return dto;
    }
}
