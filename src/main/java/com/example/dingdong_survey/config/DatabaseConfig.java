package com.example.dingdong_survey.config;

import com.example.dingdong_survey.workspace.repository.WorkspaceRepository;
import com.example.dingdong_survey.workspace.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.example.dingdong_survey")
public class DatabaseConfig {
    private final WorkspaceRepository workspaceRepository;

    @Autowired
    public DatabaseConfig(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Bean
    public WorkspaceService workspaceService() {
        return new WorkspaceService(workspaceRepository);
    }

}
