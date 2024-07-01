package com.google.backend.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "StudentUnitModel")
public class StudentUnitModel {

    private Long id;
    private UsersModel users;
    private Long usersId;
    private UnitModel unit;
    private Long unitId;
}
