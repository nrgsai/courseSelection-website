package com.google.backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@Table(name = "lesson")
public class Lesson {

    //todo : must be check with nrgs

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "unitNumber")
    private Integer unitNumber;

    @NotBlank
    @Column(name = "range")
    private String range;
}
