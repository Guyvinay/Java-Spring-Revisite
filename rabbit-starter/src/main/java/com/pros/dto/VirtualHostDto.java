package com.pros.dto;

import lombok.Data;

import java.util.List;

@Data
public class VirtualHostDto {

    private String name;
    private String description;
    private List<String> tags;
    private String defaultqueuetype;

}
