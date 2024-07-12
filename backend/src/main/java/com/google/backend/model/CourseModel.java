package com.google.backend.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "CourseModel")
public class CourseModel {

    private Long id;
    private String name;
    private String instructor;
}
