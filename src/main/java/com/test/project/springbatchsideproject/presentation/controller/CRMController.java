package com.test.project.springbatchsideproject.presentation.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import static com.test.project.springbatchsideproject.config.CRMBatchConfig.CRM_JOB;

@RestController
@RequestMapping("/crm")
public class CRMController {

    @Value("${timezone.utc}")
    private String timezone;

    @Qualifier(CRM_JOB)
    @Autowired
    Job job;

    @Autowired
    JobLauncher jobLauncher;

    @GetMapping("/update")
    void updateCRMRefereeDetails() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Date dateToday = Date.valueOf(LocalDate.now(ZoneId.of(timezone)));
        JobExecution jobExecution = jobLauncher.run(job, createJobParameter(dateToday));
    }

    private JobParameters createJobParameter(Date dateToday) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("dateToday", dateToday);
        return jobParametersBuilder.toJobParameters();
    }
}
