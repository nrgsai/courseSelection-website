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
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "national_code")
    private String nationalCode;

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "expertise_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Expertise expertise;

    @NotNull
    @Column(name = "expertise_id")
    private Long expertiseId;
}
