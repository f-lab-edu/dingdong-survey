package com.example.dingdong_survey.exception;

public class CustomException extends RuntimeException {
    private final int code;
    private final String errorCode;

    public CustomException(int code, String errorCode, String message) {
        super(message);
        this.code = code;
        this.errorCode = errorCode;
    }

    public int getCode() {
        return code;
    }

    public String getErrorCode() {
        return errorCode;
    }
}