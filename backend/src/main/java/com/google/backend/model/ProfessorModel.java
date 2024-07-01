package com.google.backend.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "ProfessorModel")
public class ProfessorModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String phoneNumber;
    private ExpertiseModel expertise;
    private Long expertiseId;
}
