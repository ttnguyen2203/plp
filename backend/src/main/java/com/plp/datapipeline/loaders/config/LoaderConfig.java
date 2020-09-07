package com.plp.datapipeline.loaders.config;

import com.plp.datapipeline.loaders.ILoader;

public abstract class LoaderConfig {

    private Class<? extends ILoader> loaderClass;

    public Class<? extends ILoader> getLoaderClass() {
        return loaderClass;
    }

    public void setLoaderClass(final Class<? extends ILoader> loaderClass) {
        this.loaderClass = loaderClass;
    }
}
