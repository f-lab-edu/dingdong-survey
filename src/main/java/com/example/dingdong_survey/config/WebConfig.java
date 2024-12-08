package com.example.dingdong_survey.config;

import com.example.dingdong_survey.workspace.controller.WorkspaceController;
import com.example.dingdong_survey.workspace.repository.WorkspaceRepository;
import com.example.dingdong_survey.workspace.service.WorkspaceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.dingdong_survey")
public class WebConfig {

}