package com.plp.datapipeline.loaders;

import com.plp.datapipeline.loaders.config.StringLoaderConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Objects;

class TestStringLoader {
    private StringLoader loader;

    @BeforeEach
    void setupEnv() {
        this.loader = new StringLoader();
    }

    @Test
    void testStringLoading() {
        final String input = "this is an input";

        final StringLoaderConfig config = new StringLoaderConfig();
        config.setLoadStringToKey("key");
        loader.init(config);

        final Map<String, Object> output = loader.load(input);

        Assert.isTrue(Objects.equals(input, output.get("key")));
    }
}