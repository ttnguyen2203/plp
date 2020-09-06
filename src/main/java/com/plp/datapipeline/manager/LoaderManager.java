package com.plp.datapipeline.manager;

import com.plp.datapipeline.loaders.ILoader;
import com.plp.datapipeline.loaders.config.LoaderConfig;
import com.plp.exception.DataPipelineException;
import com.plp.exception.ErrorCodes;

import java.util.Map;

public class LoaderManager extends DefaultComponentManager<LoaderConfig, ILoader<LoaderConfig>> {

    public Map<String, Object> loadData(final LoaderConfig config,
                                        final Object data) {
        return this.execute(config,
                this::initLoader,
                loader -> loader.load(data));

    }

    protected ILoader initLoader(final LoaderConfig config) {
        ILoader loader;
        try {
            final Class<? extends ILoader> loaderClass = config.getLoaderClass();
            loader = loaderClass == null ? null : loaderClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            loader = null;
        }
        if (loader == null) {
            throw new DataPipelineException(ErrorCodes.ERROR_CANNOT_INSTANTIATE_COMPONENT,
                    "Unable to instantiate loader");
        }
        return loader;
    }

}
