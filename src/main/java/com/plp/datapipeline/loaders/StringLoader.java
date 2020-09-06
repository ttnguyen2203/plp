package com.plp.datapipeline.loaders;

import com.plp.datapipeline.loaders.config.StringLoaderConfig;
import com.plp.exception.DataPipelineException;
import com.plp.exception.ErrorCodes;

import java.util.HashMap;
import java.util.Map;

public class StringLoader extends ILoader<StringLoaderConfig> {

    private String key;

    @Override
    public void init(final StringLoaderConfig loaderConfig) {
        this.key = loaderConfig.getLoadStringToKey();
    }

    @Override
    public Map<String, Object> load(final Object object) {
        if (object instanceof String) {
            final String asString = (String) object;
            final Map<String, Object> result = new HashMap<>();
            result.put(key, asString);
            return result;
        } else {
            throw new DataPipelineException(ErrorCodes.ERROR_LOADING_DATA,
                    "Input data is not a string");
        }
    }
}
