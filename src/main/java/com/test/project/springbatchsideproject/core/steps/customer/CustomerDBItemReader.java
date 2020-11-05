package com.test.project.springbatchsideproject.core.steps.customer;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.test.project.springbatchsideproject.infra.CustomerEntity;
import com.test.project.springbatchsideproject.infra.repository.CustomerRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

public class CustomerDBItemReader extends RepositoryItemReader<CustomerEntity> {

    private static final String QUERY_METHOD = "findAllByAccountOpeningDate";
    private static final int ITEMS_PER_PAGE = 5;
    private static final String SORT_COLUMN = "customerID";
    private static final Map<String, Sort.Direction> sortOrder = ImmutableMap.of(SORT_COLUMN, Sort.Direction.ASC);

    private final CustomerRepository customerRepository;
    private Date dateToday;

    public CustomerDBItemReader(CustomerRepository customerRepository, Date dateToday) {
        this.customerRepository = customerRepository;
        this.dateToday = dateToday;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRepository(customerRepository);
        setMethodName(QUERY_METHOD);
        setPageSize(ITEMS_PER_PAGE);
        setSort(sortOrder);
        setArguments(Arrays.asList(dateToday));
        super.afterPropertiesSet();
    }

}