package com.google.backend.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "UnitModel")
public class UnitModel {

    private Long id;
    private LessonModel lesson;
    private Long lessonId;
    private ProfessorModel professor;
    private Long professorId;
    private Integer day;
    private Integer time;
}
