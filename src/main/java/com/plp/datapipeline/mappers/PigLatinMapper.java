package com.plp.datapipeline.mappers;

import com.plp.datapipeline.mappers.config.PigLatinMapperConfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PigLatinMapper extends IMapper<PigLatinMapperConfig> {

    private static final Set<Character> VOWELS = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    @Override
    public void init(final PigLatinMapperConfig config) {
        //no settings to init
    }

    @Override
    public Map<String, Object> map(final Map<String, Object> data) {
        //todo: impl

        return data;
    }
}
