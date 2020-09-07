package com.plp.exception;

public class DataPipelineException extends RuntimeException {

    private int errorCode;

    public DataPipelineException(final int errorCode,
                                 final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return String.format("ErrorCode: %s, Message: %s", this.errorCode, super.getMessage());
    }
}
