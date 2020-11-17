package com.test.project.springbatchsideproject.core.steps.person;

import com.test.project.springbatchsideproject.core.model.Person;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonListItemReader implements ItemReader<Person> {

    List<Person> personList;

    private List<Person> getPersonsList() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("John", "Doe"));
        personList.add(new Person("John2", "Doe2"));
        personList.add(new Person("John3", "Doe3"));
        return personList;
    }

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (Objects.isNull(personList)) {
            System.out.println(Objects.isNull(personList));
            personList = getPersonsList();
        }

        if (!personList.isEmpty()) {
            return personList.remove(0);
        }
        return null;
    }
}
