package com.test.project.springbatchsideproject.core.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    private String name;
    private int age;
    private String address;

}
