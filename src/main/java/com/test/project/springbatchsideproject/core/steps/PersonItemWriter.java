package com.test.project.springbatchsideproject.core.steps;

import com.test.project.springbatchsideproject.core.model.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonItemWriter implements ItemWriter<Person> {
    @Override
    public void write(List<? extends Person> items) throws Exception {
        items.stream().forEach(person -> {print(person);});
    }

    private void print(Person person) {
        System.out.println(person);
    }
}
