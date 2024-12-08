package com.example.dingdong_survey.workspace.exception;

import com.example.dingdong_survey.exception.CustomException;

public class WrongOrderNoWorkspaceException extends CustomException {
    public WrongOrderNoWorkspaceException() {
        super(400, "WORKSPACE__WRONG_ORDER_NO", "invalid scope of workspace order_no");
    }
}