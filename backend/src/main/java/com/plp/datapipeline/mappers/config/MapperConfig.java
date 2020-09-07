package com.plp.datapipeline.mappers.config;

import com.plp.datapipeline.mappers.IMapper;

public abstract  class MapperConfig {

    private Class<? extends IMapper> mapperClass;

    public Class<? extends IMapper> getMapperClass() {
        return mapperClass;
    }

    public void setMapperClass(final Class<? extends IMapper> mapperClass) {
        this.mapperClass = mapperClass;
    }
}
