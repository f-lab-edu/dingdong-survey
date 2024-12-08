package com.example.dingdong_survey.workspace.repository;

import com.example.dingdong_survey.workspace.dto.WorkspaceDTO;
import com.example.dingdong_survey.workspace.entity.WorkspaceEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkspaceRepository extends JpaRepository<WorkspaceEntity, Long> {

    @Query("SELECT w FROM WorkspaceEntity w WHERE w.id = :workspaceId AND NOT w.isDeleted")
    Optional<WorkspaceEntity> findByIdAndNotDeleted(@Param("workspaceId") Long workspaceId);

    @Query("SELECT w FROM WorkspaceEntity w WHERE w.userId = :userId AND NOT w.isDeleted ORDER BY w.orderNo")
    List<WorkspaceEntity> findByUserIdAndNotDeleted(@Param("userId") int userId);

    @Query("SELECT COUNT(w.id) FROM WorkspaceEntity w WHERE w.userId = :userId AND NOT w.isDeleted")
    int countByUserIdAndNotDeleted(@Param("userId") int userId);

    @Modifying
    @Query("UPDATE WorkspaceEntity w SET w.orderNo = w.orderNo + 1 WHERE w.userId = :userId AND w.orderNo >= :orderNo")
    void reorderWorkspaces(@Param("userId") int userId, @Param("orderNo") int orderNo);

}
