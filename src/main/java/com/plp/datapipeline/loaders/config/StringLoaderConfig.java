package com.plp.datapipeline.loaders.config;

public class StringLoaderConfig extends LoaderConfig {

    private String loadStringToKey;

    public String getLoadStringToKey() {
        return loadStringToKey;
    }

    public void setLoadStringToKey(final String loadStringToKey) {
        this.loadStringToKey = loadStringToKey;
    }
}
