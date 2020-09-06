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
    public ConsumerResultDto applyPigLatin(@RequestBody final Object data) {
        final Map<String, ConsumerResultDto> result =
                dataPipelineService.processSingleData(DefaultDataPipelines.PIG_LATIN_PIPELINE, data);
        return result.get(DataPipelineConstants.PIG_LATIN_RESULT_CONSUMER_KEY);
    }
}
