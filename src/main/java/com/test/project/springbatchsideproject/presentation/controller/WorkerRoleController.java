package com.test.project.springbatchsideproject.presentation.controller;

import com.test.project.springbatchsideproject.infra.entity.WorkerRoleActivityEntity;
import com.test.project.springbatchsideproject.infra.repository.WorkerRoleActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerRoleController {

    @Autowired
    WorkerRoleActivityRepository workerRoleActivityRepository;

    @GetMapping("/get-all")
    public List<WorkerRoleActivityEntity> getWorkerRoleData() {
        return workerRoleActivityRepository.findAll();
    }
}
