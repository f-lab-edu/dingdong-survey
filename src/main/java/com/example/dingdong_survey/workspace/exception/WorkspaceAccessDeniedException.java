package com.example.dingdong_survey.workspace.exception;

import com.example.dingdong_survey.exception.CustomException;

public class WorkspaceAccessDeniedException extends CustomException {
    public WorkspaceAccessDeniedException() {
        super(403, "WORKSPACE__ACCESS_DENIED", "workspace access denied");
    }
}