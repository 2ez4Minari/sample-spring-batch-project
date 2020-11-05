package com.test.project.springbatchsideproject.config;

import com.test.project.springbatchsideproject.core.model.Customer;
import com.test.project.springbatchsideproject.core.steps.customer.CustomerDBItemReader;
import com.test.project.springbatchsideproject.core.steps.customer.CustomerItemReader;
import com.test.project.springbatchsideproject.core.steps.customer.CustomerItemWriter;
import com.test.project.springbatchsideproject.infra.CustomerEntity;
import com.test.project.springbatchsideproject.infra.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
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

    @Autowired
    private CustomerItemWriter customerItemWriter;

    @Autowired
    private CustomerItemReader customerItemReader;

    @Autowired
    private CustomerRepository customerRepository;

    @Bean(CUSTOMER_JOB)
    public Job importCustomerJob(CustomerJobCompletionNotificationListener listener, @Qualifier(CUSTOMER_STEP) Step importCustomerStep) {
        return jobBuilderFactory.get(CUSTOMER_JOB_NAME)
                .incrementer(new RunIdIncrementer())
//                .listener(listener)
                .flow(importCustomerStep)
                .end()
                .build();
    }

    @Bean(CUSTOMER_STEP)
    public Step importCustomerStep(@Qualifier(CUSTOMER_WRITER) JdbcBatchItemWriter<Customer> writer, @Qualifier("ConcreteReader") RepositoryItemReader<CustomerEntity> repositoryReader) {
        return stepBuilderFactory.get(CUSTOMER_STEP_NAME)
                .<CustomerEntity, CustomerEntity> chunk(10)
                .reader(repositoryReader)
                .processor(process())
                .writer(customerItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public PassThroughItemProcessor<CustomerEntity> process() {
        log.info("Started Pass Through Processor");
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

    @Bean("SimpleReader")
    @StepScope
    public ListItemReader<CustomerEntity> simpleReader() {
        log.info("Started Simple Reader");
        List<CustomerEntity> customerEntityList = customerRepository.findAllUsingNativeQuery("HK");
        log.info("CustomerEntityList: {}", customerEntityList.toString());
        System.out.println(customerEntityList);
        return new ListItemReader<>(customerEntityList);
    }

    @Bean("ConcreteReader")
    @StepScope
    public RepositoryItemReader<CustomerEntity> repositoryReader(CustomerRepository customerRepository, @Value("#{jobParameters['dateToday']}") Date dateToday) {
        System.out.println("Concrete Reader!");
        System.out.println(dateToday);
        return new CustomerDBItemReader(customerRepository, dateToday);
    }

    @Bean
    @StepScope
    public RepositoryItemReader<CustomerEntity> repositoryReader(@Value("#{jobParameters['dateToday']}") Date dateToday) {
        log.info("Started Repository Reader");
        System.out.println(Date.valueOf(LocalDate.now()));
        HashMap sorts = new HashMap<>();
        sorts.put("customerID", Sort.Direction.ASC);
        RepositoryItemReaderBuilder repositoryItemReaderBuilder = new RepositoryItemReaderBuilder();
        repositoryItemReaderBuilder.name("CustomerRepositoryReader");
        repositoryItemReaderBuilder.repository(customerRepository);
        repositoryItemReaderBuilder.methodName("findAllByAccountOpeningDate");
        repositoryItemReaderBuilder.arguments(Arrays.asList(dateToday));
        repositoryItemReaderBuilder.sorts(sorts);
        repositoryItemReaderBuilder.pageSize(2);
        return repositoryItemReaderBuilder.build();

    }
}
