package com.plp.dto;

import java.util.Map;

public class ConsumerResultDto {

    private Map<String, Object> consumedData;
    private boolean success;

    public Map<String, Object> getConsumedData() {
        return consumedData;
    }

    public void setConsumedData(final Map<String, Object> consumedData) {
        this.consumedData = consumedData;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }
}
