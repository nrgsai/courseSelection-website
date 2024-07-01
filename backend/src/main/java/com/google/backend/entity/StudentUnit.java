package com.google.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@Table(name = "student_unit")
public class StudentUnit {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @NotNull
    @Column(name = "users_id")
    private Long usersId;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "unit_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Unit unit;

    @NotNull
    @Column(name = "unit_id")
    private Long unitId;
}
