package com.test.project.springbatchsideproject.presentation.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.test.project.springbatchsideproject.config.CustomerBatchConfiguration.CUSTOMER_JOB;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Qualifier(CUSTOMER_JOB)
    @Autowired
    Job job;

    @Autowired
    JobLauncher jobLauncher;

    @GetMapping("/write")
    void movePersonFromCSVToDB() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobExecution jobExecution = jobLauncher.run(job, createJobParameter());
    }

    private JobParameters createJobParameter() {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("Date", new Date());
        return jobParametersBuilder.toJobParameters();
    }
}
