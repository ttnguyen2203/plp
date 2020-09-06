package com.plp.datapipeline.consumers;

import com.plp.datapipeline.consumers.config.ConsumerConfig;
import com.plp.dto.ConsumerResultDto;
import org.springframework.lang.NonNull;

import java.util.Map;

public abstract class IConsumer<C extends ConsumerConfig> {

    public abstract void init(@NonNull final C config);

    public abstract ConsumerResultDto consume(@NonNull final Map<String, Object> data);

}
