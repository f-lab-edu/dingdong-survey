package com.example.dingdong_survey.exception;

public class ErrorResponse {
    private int code;
    private String errorCode;
    private String message;

    public ErrorResponse(int code, String errorCode, String message) {
        this.code = code;
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}