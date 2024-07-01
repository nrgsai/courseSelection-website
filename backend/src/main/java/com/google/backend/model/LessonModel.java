package com.google.backend.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "LessonModel")
public class LessonModel {

    private Long id;
    private String name;
    private String code;
    private Integer unitNumber;
    private ExpertiseModel expertise;
    private Long expertiseId;
}
