package com.example.dingdong_survey.workspace.exception;

import com.example.dingdong_survey.exception.CustomException;

public class TooManyWorkspacesException extends CustomException {
    public TooManyWorkspacesException() {
        super(400, "WORKSPACE__TOO_MANY", "too many workspaces");
    }
}