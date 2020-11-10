package com.test.project.springbatchsideproject.infra.repository;

import com.test.project.springbatchsideproject.infra.entity.WorkerRoleActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRoleActivityRepository extends JpaRepository<WorkerRoleActivityEntity, Long> {

    List<WorkerRoleActivityEntity> findAll();
}
