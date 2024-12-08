package com.example.dingdong_survey.workspace.service;

import com.example.dingdong_survey.workspace.dto.UpdateWorkspaceDTO;
import com.example.dingdong_survey.workspace.dto.WorkspaceDTO;
import com.example.dingdong_survey.workspace.entity.WorkspaceEntity;
import com.example.dingdong_survey.workspace.exception.TooManyWorkspacesException;
import com.example.dingdong_survey.workspace.exception.WorkspaceAccessDeniedException;
import com.example.dingdong_survey.workspace.exception.WorkspaceNotFoundException;
import com.example.dingdong_survey.workspace.exception.WrongOrderNoWorkspaceException;
import com.example.dingdong_survey.workspace.repository.WorkspaceRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public WorkspaceEntity getWorkspace(int userId, Long workspaceId)
        throws WorkspaceNotFoundException {
        Optional<WorkspaceEntity> optionalWorkspace = workspaceRepository.findByIdAndNotDeleted(
            workspaceId);
        if (optionalWorkspace.isEmpty()) {
            throw new WorkspaceNotFoundException();
        }

        WorkspaceEntity workspace = optionalWorkspace.get();
        if (workspace.getUserId() != userId) {
            throw new WorkspaceAccessDeniedException();
        }

        return workspace;
    }


    public List<WorkspaceDTO> getAllWorkspaces(int userId) {
        List<WorkspaceEntity> workspaces = workspaceRepository.findByUserIdAndNotDeleted(userId);

        for (int idx = 0; idx < workspaces.size(); idx++) {
            WorkspaceEntity workspace = workspaces.get(idx);
            workspace.setOrderNo(idx + 1);
        }

        return workspaces.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private WorkspaceDTO convertToDTO(WorkspaceEntity workspace) {
        WorkspaceDTO dto = new WorkspaceDTO();
        dto.setId(workspace.getId());
        dto.setTitle(workspace.getTitle());
        dto.setOrderNo(workspace.getOrderNo());
        return dto;
    }

    public WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO) {
        int workspaceCount = workspaceRepository.countByUserIdAndNotDeleted(
            workspaceDTO.getUserId());
        if (workspaceCount >= 10) {
            throw new TooManyWorkspacesException();
        }

        WorkspaceEntity entity = new WorkspaceEntity();
        entity.setUserId(workspaceDTO.getUserId());
        entity.setTitle(workspaceDTO.getTitle());
        entity.setOrderNo(workspaceDTO.getOrderNo());

        WorkspaceEntity savedEntity = workspaceRepository.save(entity);

        WorkspaceDTO savedWorkspaceDTO = new WorkspaceDTO();
        savedWorkspaceDTO.setUserId(savedEntity.getUserId());

        return savedWorkspaceDTO;
    }

    ;

    public void updateWorkspace(int userId, Long workspaceId,
        UpdateWorkspaceDTO updateWorkspaceDTO) {
        Optional<WorkspaceEntity> optionalWorkspace = workspaceRepository.findByIdAndNotDeleted(
            workspaceId);
        if (optionalWorkspace.isEmpty()) {
            throw new WorkspaceNotFoundException();
        }

        WorkspaceEntity workspace = optionalWorkspace.get();
        if (workspace.getUserId() != userId) {
            throw new WorkspaceAccessDeniedException();
        }

        if (updateWorkspaceDTO.getTitle() != null) {
            workspace.setTitle(updateWorkspaceDTO.getTitle());
        }

        if (updateWorkspaceDTO.getOrderNo() != null) {
            int orderNo = updateWorkspaceDTO.getOrderNo();

            long totalWorkspaceCount = workspaceRepository.countByUserIdAndNotDeleted(userId);
            if (orderNo < 1 || orderNo > totalWorkspaceCount) {
                throw new WrongOrderNoWorkspaceException();
            }

            List<WorkspaceEntity> workspaces = workspaceRepository.findByUserIdAndNotDeleted(userId);
            int newOrderNo = workspaces.get(orderNo - 1).getOrderNo();

            if (workspace.getOrderNo() < newOrderNo) {
                newOrderNo += 1;
            }

            workspaceRepository.reorderWorkspaces(userId, newOrderNo);
            workspace.setOrderNo(newOrderNo);
        }
        workspaceRepository.save(workspace);
    }

    public void deleteWorkspace(int userId, Long workspaceId) {
        Optional<WorkspaceEntity> optionalWorkspace = workspaceRepository.findByIdAndNotDeleted(
            workspaceId);
        if (optionalWorkspace.isEmpty()) {
            throw new WorkspaceNotFoundException();
        }

        WorkspaceEntity workspace = optionalWorkspace.get();
        if (workspace.getUserId() != userId) {
            throw new WorkspaceAccessDeniedException();
        }

        workspace.setDeleted(true);
    }
}
