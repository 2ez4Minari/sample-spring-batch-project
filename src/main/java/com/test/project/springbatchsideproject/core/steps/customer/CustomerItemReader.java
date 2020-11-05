package com.test.project.springbatchsideproject.core.steps.customer;

import com.test.project.springbatchsideproject.infra.CustomerEntity;
import com.test.project.springbatchsideproject.infra.repository.CustomerRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class CustomerItemReader  {

}
