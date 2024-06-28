package com.google.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@Table(name = "unit")
public class Unit {

    //todo : must be check with nrgs

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "lesson_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Lesson lesson;

    @NotNull
    @Column(name = "lesson_id")
    private Long lessonId;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "professor_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Professor professor;

    @NotNull
    @Column(name = "professor_id")
    private Long professorId;

    @NotNull
    @Column(name = "day")
    @Min(1)
    @Max(5)
    private Integer day;

    @NotNull
    @Column(name = "time")
    @Min(1)
    @Max(4)
    private Integer time;
}
