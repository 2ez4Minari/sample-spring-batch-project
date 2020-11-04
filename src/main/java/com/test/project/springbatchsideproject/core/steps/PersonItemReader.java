package com.test.project.springbatchsideproject.core.steps;

import com.test.project.springbatchsideproject.core.model.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class PersonItemReader extends FlatFileItemReader<Person>{
    @Override
    public void afterPropertiesSet() throws Exception {
        setName("personItemReader");
        setResource(new ClassPathResource("sample-data.csv"));
        setLineMapper(lineMapper());
        super.afterPropertiesSet();
    }

    private LineMapper<Person> lineMapper() {
        DefaultLineMapper<Person> mapper = new DefaultLineMapper<>();
        mapper.setFieldSetMapper(fieldSet -> {
            return new Person(fieldSet.readString(0), fieldSet.readString(1));
        });
        mapper.setLineTokenizer(new DelimitedLineTokenizer());
        return mapper;
    }
}

