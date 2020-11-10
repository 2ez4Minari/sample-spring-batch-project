package com.test.project.springbatchsideproject.config;

import com.test.project.springbatchsideproject.core.steps.crm.CRMUpdateReader;
import com.test.project.springbatchsideproject.core.steps.crm.CRMUpdateWriter;
import com.test.project.springbatchsideproject.infra.entity.BalanceAndIndexEntity;
import com.test.project.springbatchsideproject.infra.repository.BalanceAndIndexRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;

@Slf4j
@Configuration
@EnableBatchProcessing
public class CRMBatchConfig {

    public final static String CRM_JOB = "CRM_JOB";
    public final static String CRM_STEP = "CRM_STEP";

    private final static String CRM_READER = "CRMReader";
    private final static String CRM_WRITER = "CRMWriter";

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean(CRM_JOB)
    public Job updateCRMTableJob(@Qualifier(CRM_STEP) Step updateCRMTableStep) {
        return jobBuilderFactory.get("updateCRMTableJob")
                .incrementer(new RunIdIncrementer())
                .flow(updateCRMTableStep)
                .end()
                .build();
    }

    @Bean(CRM_STEP)
    public Step updateCRMTableStep(@Qualifier(CRM_READER) RepositoryItemReader<BalanceAndIndexEntity> itemReader,
                                   @Qualifier(CRM_WRITER) ItemWriter<BalanceAndIndexEntity> itemWriter) {
        return stepBuilderFactory.get("updateCRMTableStep")
                .<BalanceAndIndexEntity, BalanceAndIndexEntity> chunk(10)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }

    @Bean(CRM_READER)
    @StepScope
    public RepositoryItemReader<BalanceAndIndexEntity> reader(BalanceAndIndexRepository balanceAndIndexRepository, @Value("#{jobParameters['dateToday']}") Date dateToday) {
        log.info("Started Repository Item Reader");
        return new CRMUpdateReader(balanceAndIndexRepository, dateToday);
    }

    @Bean(CRM_WRITER)
    public ItemWriter<BalanceAndIndexEntity> writer() {
        log.info("Started Item Writer");
        return new CRMUpdateWriter();
    }

}
