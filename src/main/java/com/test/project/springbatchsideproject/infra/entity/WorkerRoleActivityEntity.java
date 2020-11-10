package com.test.project.springbatchsideproject.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WORKER_ROLE_ACTIVITY", schema = "sdm")
public class WorkerRoleActivityEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerID;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "ACTIVITY_NAME")
    private String activityName;
}
