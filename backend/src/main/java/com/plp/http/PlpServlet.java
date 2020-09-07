package com.plp.http;

import com.plp.constants.DataPipelineConstants;
import com.plp.datapipeline.DefaultDataPipelines;
import com.plp.dto.ConsumerResultDto;
import com.plp.service.DataPipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PlpServlet {

    @Autowired
    private DataPipelineService dataPipelineService;

    @PostMapping("/transform/piglatin")
    public String applyPigLatin(@RequestBody final Object data) {
        final Map<String, ConsumerResultDto> result =
                dataPipelineService.processSingleData(DefaultDataPipelines.PIG_LATIN_PIPELINE, data);
        return getPigLatinMappedResult(result.get(DataPipelineConstants.PIG_LATIN_RESULT_CONSUMER_KEY));
    }

    protected String getPigLatinMappedResult(final ConsumerResultDto resultDto) {
        final Map<String, Object> resultMap = resultDto == null ? null : resultDto.getConsumedData();
        return resultMap == null ? "" : (String) resultMap.get(DataPipelineConstants.PIG_LATIN_RESULT_LOADER_KEY);
    }
}
