package com.test.project.springbatchsideproject.config;

import com.test.project.springbatchsideproject.core.model.Customer;
import com.test.project.springbatchsideproject.core.model.Person;
import com.test.project.springbatchsideproject.core.steps.customer.CustomerItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class CustomerBatchConfiguration {

    public final static String CUSTOMER_JOB_NAME = "importCustomerJob";
    public final static String CUSTOMER_STEP_NAME = "importCustomerStep";

    public final static String CUSTOMER_JOB = "CUSTOMER_JOB";
    public final static String CUSTOMER_STEP = "CUSTOMER_STEP";

    public final static String CUSTOMER_WRITER = "CUSTOMER_WRITER";

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @Qualifier(CUSTOMER_JOB)
    public Job importCustomerJob(CustomerJobCompletionNotificationListener listener, @Qualifier(CUSTOMER_STEP) Step importCustomerStep) {
        return jobBuilderFactory.get(CUSTOMER_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(importCustomerStep)
                .end()
                .build();
    }

    @Bean(CUSTOMER_STEP)
    public Step importCustomerStep(@Qualifier(CUSTOMER_WRITER) JdbcBatchItemWriter<Customer> writer, CustomerItemReader customerItemReader) {
        return stepBuilderFactory.get(CUSTOMER_STEP_NAME)
                .<Customer, Customer> chunk(10)
                .reader(customerItemReader)
                .processor(process())
                .writer(writer)
                .build();
    }

    @Bean
    public PassThroughItemProcessor<Customer> process() {
        return new PassThroughItemProcessor<>();
    }

    @Bean(CUSTOMER_WRITER)
    public JdbcBatchItemWriter<Customer> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Customer>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO customer (NAME, AGE, ADDRESS) VALUES (:name, :age, :address)")
                .dataSource(dataSource)
                .build();
    }
}
