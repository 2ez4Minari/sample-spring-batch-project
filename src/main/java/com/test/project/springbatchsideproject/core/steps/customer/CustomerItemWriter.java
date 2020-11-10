package com.test.project.springbatchsideproject.core.steps.customer;

import com.test.project.springbatchsideproject.infra.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CustomerItemWriter implements ItemWriter<CustomerEntity> {

    @Override
    public void write(List<? extends CustomerEntity> items) throws Exception {
        log.info("Started Writer!");
        items.stream().forEach(customerEntity -> print(customerEntity));
    }

    private void print(CustomerEntity customerEntity) {
        System.out.println(customerEntity.toString());
    }
}
