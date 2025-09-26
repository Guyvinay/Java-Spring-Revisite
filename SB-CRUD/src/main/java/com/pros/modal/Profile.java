package com.pros.modal;

import lombok.*;
//import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Document(indexName = "student")
@ToString
public class Profile {

//    @Id
    private String id;

    private String name;

    private String email;

    private Integer age;

}
