package com.app.modal;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "profile")
public class Student {

    @Id
    private String id;

    private String name;

    private String email;

    private Integer age;
}
