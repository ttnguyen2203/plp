package com.plp.datapipeline.loaders;

import com.plp.datapipeline.loaders.config.LoaderConfig;
import org.springframework.lang.NonNull;

import java.util.Map;

public abstract class ILoader<C extends LoaderConfig> {

    public abstract void init(@NonNull final C loaderConfig);

    public abstract Map<String, Object> load(@NonNull final Object object);
}
