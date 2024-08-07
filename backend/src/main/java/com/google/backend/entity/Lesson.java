package com.google.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
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
    @Column(name = "unit_number")
    private Integer unitNumber;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "expertise_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Expertise expertise;

    @NotNull
    @Column(name = "expertise_id")
    private Long expertiseId;
}
