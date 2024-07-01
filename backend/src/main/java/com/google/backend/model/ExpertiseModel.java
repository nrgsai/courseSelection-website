package com.google.backend.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "ExpertiseModel")
public class ExpertiseModel {

    private Long id;
    private String title;
}
