package com.example.dingdong_survey.workspace.controller;

import com.example.dingdong_survey.workspace.dto.CreateWorkspaceDTO;
import com.example.dingdong_survey.workspace.dto.UpdateWorkspaceDTO;
import com.example.dingdong_survey.workspace.dto.WorkspaceDTO;
import com.example.dingdong_survey.workspace.service.WorkspaceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @Autowired
    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping("")
    public List<WorkspaceDTO> getAllWorkspaces(@RequestParam int userId) {
        return workspaceService.getAllWorkspaces(userId);
    }

    @PostMapping("")
    public CreateWorkspaceDTO createWorkspace(@RequestBody WorkspaceDTO workspaceDTO) {
        workspaceDTO = workspaceService.createWorkspace(workspaceDTO);

        CreateWorkspaceDTO createWorkspaceDTO = new CreateWorkspaceDTO();
        createWorkspaceDTO.setId(workspaceDTO.getId());
        return createWorkspaceDTO;
    }

    @PatchMapping("/{workspaceId}")
    public void updateWorkspace(@PathVariable Long workspaceId, @RequestBody UpdateWorkspaceDTO updateWorkspaceDTO) {
        workspaceService.updateWorkspace(1, workspaceId, updateWorkspaceDTO);
    }

    @DeleteMapping("/{workspaceId}")
    public void deleteWorkspace(@PathVariable Long workspaceId) {
        workspaceService.deleteWorkspace(1, workspaceId);
    }
}
