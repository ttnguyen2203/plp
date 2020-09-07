package com.plp.datapipeline.manager;

import com.plp.datapipeline.consumers.IConsumer;
import com.plp.datapipeline.consumers.config.ConsumerConfig;
import com.plp.dto.ConsumerResultDto;
import com.plp.exception.DataPipelineException;
import com.plp.exception.ErrorCodes;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConsumerManager extends DefaultComponentManager<ConsumerConfig, IConsumer> {

    public ConsumerResultDto consumeData(final ConsumerConfig config,
                                         final Map<String, Object> data) {
        return this.execute(config,
                this::initConsumer,
                consumer -> consumer.consume(data));

    }

    protected IConsumer initConsumer(final ConsumerConfig config) {
        IConsumer consumer;
        try {
            final Class<? extends IConsumer> consumerClass = config.getConsumerClass();
            consumer = consumerClass == null ? null : consumerClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            consumer = null;
        }
        if (consumer == null) {
            throw new DataPipelineException(ErrorCodes.ERROR_CANNOT_INSTANTIATE_COMPONENT,
                    "Unable to instantiate consumer");
        }
        consumer.init(config);
        return consumer;
    }
}
