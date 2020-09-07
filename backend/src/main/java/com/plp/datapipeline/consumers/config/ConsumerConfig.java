package com.plp.datapipeline.consumers.config;

import com.plp.datapipeline.consumers.IConsumer;

public class ConsumerConfig {

    private Class<? extends IConsumer> consumerClass;

    public Class<? extends IConsumer> getConsumerClass() {
        return consumerClass;
    }

    public void setConsumerClass(final Class<? extends IConsumer> consumerClass) {
        this.consumerClass = consumerClass;
    }
}
