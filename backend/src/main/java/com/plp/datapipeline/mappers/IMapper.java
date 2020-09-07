package com.plp.datapipeline.mappers;

import com.plp.datapipeline.mappers.config.MapperConfig;
import org.springframework.lang.NonNull;

import java.util.Map;

public abstract class IMapper<C extends MapperConfig> {

    public abstract void init(@NonNull final C config);

    public abstract Map<String, Object> map(@NonNull final Map<String, Object> data);
}
