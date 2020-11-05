package com.test.project.springbatchsideproject.core.steps.customer;


import com.test.project.springbatchsideproject.infra.CustomerEntity;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class CustomerDBItemReader extends JpaPagingItemReader<CustomerEntity> {


}
