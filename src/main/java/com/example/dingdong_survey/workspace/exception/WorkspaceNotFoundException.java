package com.example.dingdong_survey.workspace.exception;

import com.example.dingdong_survey.exception.CustomException;

public class WorkspaceNotFoundException extends CustomException {
    public WorkspaceNotFoundException() {
        super(404, "WORKSPACE__NOT_FOUND", "workspace not found");
    }
}