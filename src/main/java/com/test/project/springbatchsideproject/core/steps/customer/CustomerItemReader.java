package com.test.project.springbatchsideproject.core.steps.customer;

import com.test.project.springbatchsideproject.core.model.Customer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class CustomerItemReader extends FlatFileItemReader<Customer> {
    @Override
    public void afterPropertiesSet() throws Exception {
        setName("customerItemReader");
        setResource(new ClassPathResource("customer-data.csv"));
        setLineMapper(lineMapper());
        super.afterPropertiesSet();
    }

    private LineMapper<Customer> lineMapper() {
        DefaultLineMapper<Customer> mapper = new DefaultLineMapper<>();
        mapper.setFieldSetMapper(fieldSet -> {
            return new Customer(fieldSet.readString(0), fieldSet.readInt(1), fieldSet.readString(2));
        });
        mapper.setLineTokenizer(new DelimitedLineTokenizer());
        return mapper;
    }
}
