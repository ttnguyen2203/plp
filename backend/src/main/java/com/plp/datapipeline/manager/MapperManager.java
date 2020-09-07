package com.plp.datapipeline.manager;

import com.plp.datapipeline.mappers.IMapper;
import com.plp.datapipeline.mappers.config.MapperConfig;
import com.plp.exception.DataPipelineException;
import com.plp.exception.ErrorCodes;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MapperManager extends DefaultComponentManager<MapperConfig, IMapper> {

    public Map<String, Object> mapData(final MapperConfig config,
                                        final Map<String, Object> data) {
        //noinspection unchecked
        return this.execute(config,
                this::initMapper,
                mapper -> mapper.map(data));
    }

    protected IMapper initMapper(final MapperConfig config) {
        IMapper mapper;
        try {
            final Class<? extends IMapper> mapperClass = config.getMapperClass();
            mapper = mapperClass == null ? null : mapperClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            mapper = null;
        }
        if (mapper == null) {
            throw new DataPipelineException(ErrorCodes.ERROR_CANNOT_INSTANTIATE_COMPONENT,
                    "Unable to instantiate mapper");
        }
        mapper.init(config);
        return mapper;
    }
}
